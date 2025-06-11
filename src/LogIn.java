import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LogIn extends JFrame implements ActionListener {
    JLabel welcomeLabel,numberLabel,passwordLabel;
    JTextField numberField;
    JPasswordField passwordField;
    JButton loginButton;

    File userInfoFile;
    FileWriter fw;
    FileReader fr;
    BufferedWriter bw;
    BufferedReader br;

    LogIn(){
        setTitle("LogIn Window");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,450);
        getContentPane().setBackground(new Color(0xAFAEAD));
        setLocationRelativeTo(null);
        setLayout(null);

        welcomeLabel = new JLabel("Welcome To LogIn Panel");
        welcomeLabel.setFont(new Font("Times New",Font.BOLD,20));
        welcomeLabel.setBounds(100,0,250,50);
        add(welcomeLabel);

        numberLabel = new JLabel("Mobile Number");
        numberLabel.setBounds(30,95,100,30);
        add(numberLabel);
        numberField = new JTextField();
        numberField.setBounds(150,100,200,20);
        add(numberField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30,145,100,30);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(150,150,200,20);
        add(passwordField);

        loginButton = new JButton("LogIn");
        loginButton.setBounds(150,200,80,25);
        loginButton.setBackground(new Color(0xE3E3E3));
        loginButton.addActionListener(this);
        add(loginButton);

        userInfoFile = new File("UserInfo.txt");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){
            try {
                fr = new FileReader("UserInfo.txt");
                br = new BufferedReader(fr);
                boolean userFound = false;

                String line = br.readLine();
                while (line != null){
                    String[] info  = line.split(" ");

                    if (numberField.getText().equals(info[1].trim()) && passwordField.getText().equals(info[2].trim())){
                        userFound = true;
                        dispose();
                        new Dashboard(info[0].trim(),info[1].trim(),info[3].trim());
                    }
                    line = br.readLine();
                }
                br.close();
                if (!userFound){
                    JOptionPane.showMessageDialog(null,"Incorrect LogIn Password");
                }

            }catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
