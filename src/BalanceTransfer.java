import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;

public class BalanceTransfer extends JFrame implements ActionListener {
    String userName, userNumber, userBalance;

    JLabel numberLabel, amountLabel, passwordLabel;
    JTextField numberField, amountField;
    JPasswordField passwordField;
    JButton confirmButton, canselButton;

    FileWriter fw;
    FileReader fr;
    BufferedWriter bw;
    BufferedReader br;
    
    BalanceTransfer(String userName, String userNumber,String userBalance){
        this.userName = userName;
        this.userNumber = userNumber;
        this.userBalance = userBalance;

        setTitle("Mobile Banking [Balance Transfer]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,450);
        getContentPane().setBackground(new Color(0xAFAEAD));
        setLocationRelativeTo(null);
        setLayout(null);

        numberLabel = new JLabel("Phone Number");
        numberLabel.setBounds(50,95,150,30);
        add(numberLabel);
        numberField = new JTextField();
        numberField.setBounds(200,100,120,20);
        add(numberField);

        amountLabel = new JLabel("Amount");
        amountLabel.setBounds(50,145,150,30);
        add(amountLabel);
        amountField = new JTextField();
        amountField.setBounds(200,150,100,20);
        add(amountField);

        passwordLabel = new JLabel("PIN");
        passwordLabel.setBounds(50,195,50,30);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(200,200,70,20);
        add(passwordField);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(150,250,80,25);
        confirmButton.setBackground(new Color(0xE3E3E3));
        confirmButton.addActionListener(this);
        add(confirmButton);

        canselButton = new JButton("Cansel");
        canselButton.setBounds(320,330,80,25);
        canselButton.setBackground(new Color(0xE3E3E3));
        canselButton.addActionListener(this);
        add(canselButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton){
            int targetBalance = 0;
            File userInfoFile = new File("UserInfo.txt");
            File tempFile = new File("UserInfo_temp.txt");

            try{
                fr = new FileReader(userInfoFile);
                br = new BufferedReader(fr);
                fw = new FileWriter(tempFile);
                bw = new BufferedWriter(fw);
                boolean userFound = false;

                String line = br.readLine();

                while (line != null){
                    String[] info = line.split(" ");
                    int currentAmount = Integer.parseInt(info[3]);
                    if (Objects.equals(numberField.getText(),info[1])){
                        int currentBalance = Integer.parseInt(info[3]);
                        int transferBalance = Integer.parseInt(amountField.getText());
                        int balance = currentBalance + transferBalance;
                        info[3] = String.valueOf(balance);
                        userFound = true;
                    }
                    else if (Objects.equals(userName, info[0]) && Objects.equals(userNumber, info[1])) {
                        if (Objects.equals(passwordField.getText(), info[2])) {
                            int currentBalance = Integer.parseInt(info[3]);
                            int transferBalance = Integer.parseInt(amountField.getText());
                            int balance = currentBalance - transferBalance;
                            if (balance < 0) {
                                JOptionPane.showMessageDialog(null, "Insufficient balance!");
                                return;
                            }
                            if (userFound){
                                info[3] = String.valueOf(balance);
                                JOptionPane.showMessageDialog(null, "Transaction Successful...");
                            }
                            //info[3] = String.valueOf(balance);
                            //JOptionPane.showMessageDialog(null, "Transaction Successful...");
                            new Dashboard(userName,userNumber,info[3]);
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect PIN!");
                        }
                    }
                    if (!userFound){
                        info[3] = String.valueOf(currentAmount);
                        //JOptionPane.showMessageDialog(null, "Try Again!");
                    }
                    bw.write(String.join(" ", info));
                    bw.newLine();
                    line = br.readLine();
                }
                if (!userFound){
                    JOptionPane.showMessageDialog(null, "Try Again!");
                }
                bw.close();
                fw.close();
                br.close();
                userInfoFile.delete();
                File temp = new File("UserInfo.txt");
                tempFile.renameTo(temp);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            dispose();
        }
        if (e.getSource() == canselButton){
            dispose();
            new LogIn();
        }
    }
}
