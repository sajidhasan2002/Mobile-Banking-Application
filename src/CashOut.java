import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.Objects;

public class CashOut extends JFrame implements ActionListener {
    String userName, userNumber;
    JLabel cashLabel, passwordLabel;
    JTextField cashField, passwordField;
    JButton confirmButton, canselButton;

    //File userInfoFile;
    FileWriter fw;
    FileReader fr;
    BufferedWriter bw;
    BufferedReader br;

    CashOut(String userName, String userNumber) {
        this.userName = userName;
        this.userNumber = userNumber;

        setTitle("Mobile Banking [Cash Out]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 450);
        getContentPane().setBackground(new Color(0xAFAEAD));
        setLocationRelativeTo(null);
        setLayout(null);

        cashLabel = new JLabel("Cash Out Amount ");
        cashLabel.setBounds(50, 95, 150, 30);
        add(cashLabel);
        cashField = new JTextField();
        cashField.setBounds(200, 100, 100, 20);
        add(cashField);

        passwordLabel = new JLabel("PIN");
        passwordLabel.setBounds(50, 145, 50, 30);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 70, 20);
        add(passwordField);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(150, 250, 80, 25);
        confirmButton.setBackground(new Color(0xE3E3E3));
        confirmButton.addActionListener(this);
        add(confirmButton);

        canselButton = new JButton("Cansel");
        canselButton.setBounds(320,330,80,25);
        canselButton.setBackground(new Color(0xE3E3E3));
        canselButton.addActionListener(this);
        add(canselButton);

        //userInfoFile = new File("UserInfo.txt");

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == confirmButton) {
            File userInfoFile = new File("UserInfo.txt");
            File tempFile = new File("UserInfo_temp.txt");

            try {
                fr = new FileReader(userInfoFile);
                br = new BufferedReader(fr);
                fw = new FileWriter(tempFile);
                bw = new BufferedWriter(fw);

                String line = br.readLine();
                while (line != null) {
                    String[] info = line.split(" ");

                    if (Objects.equals(userName, info[0]) && Objects.equals(userNumber, info[1])) {
                        if (Objects.equals(passwordField.getText(), info[2])) {
                            int balance = Integer.parseInt(info[3]) - Integer.parseInt(cashField.getText());
                            //double balance = Double.parseDouble(info[3]) - Double.parseDouble(cashField.getText());
                            if (balance < 0) {
                                JOptionPane.showMessageDialog(null, "Insufficient balance!");
                                return;
                            }
                            info[3] = String.valueOf(balance);
                            JOptionPane.showMessageDialog(null, "Transaction Successful...");
                            dispose();
                            new Dashboard(userName,userNumber,info[3]);
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect PIN!");
                        }
                    }
                    bw.write(String.join(" ", info));
                    bw.newLine();
                    line = br.readLine();
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
            //dispose();
        }
        
        if (e.getSource() == canselButton){
            dispose();
            new LogIn();
        }
    }
}
