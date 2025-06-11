import javax.accessibility.AccessibleAction;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Account extends JFrame implements ActionListener {
    JLabel label, nameLabel, numberLabel, passwordLabel, depositLabel, genderLabel, branchLabel;
    JTextField nameField,numberField,depositField;
    JPasswordField passwordField;
    JButton regButton, haveAcButton;
    JRadioButton maleButton, femaleButton, others;
    JComboBox branchBox;


    File userInfoFile;
    FileWriter fw;
    FileReader fr;
    BufferedWriter bw;
    BufferedReader br;

    Account(){
        setTitle("Mobile Banking [Registration]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,450);
        getContentPane().setBackground(new Color(0xAFAEAD));
        setLocationRelativeTo(null);
        setLayout(null);

        label = new JLabel("------Registration------");
        label.setFont(new Font("Times New",Font.BOLD,20));
        label.setBounds(100,0,200,50);
        add(label);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(30,45,40,30);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150,50,200,20);
        add(nameField);

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

        depositLabel = new JLabel("Deposit");
        depositLabel.setBounds(30,195,100,30);
        add(depositLabel);
        depositField = new JTextField();
        depositField.setBounds(150,200,200,20);
        add(depositField);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(30,245,100,30);
        add(genderLabel);
        maleButton = new JRadioButton("Male");
        maleButton.setBounds(150,250,60,20);
        maleButton.setBackground(new Color(0xAFAEAD));
        femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(210,250,70,20);
        femaleButton.setBackground(new Color(0xAFAEAD));
        others = new JRadioButton("Others");
        others.setBounds(280,250,70,20);
        others.setBackground(new Color(0xAFAEAD));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);
        buttonGroup.add(others);
        maleButton.addActionListener(this);
        femaleButton.addActionListener(this);
        others.addActionListener(this);
        add(maleButton);
        add(femaleButton);
        add(others);

        branchLabel = new JLabel("Branch");
        branchLabel.setBounds(30,295,100,30);
        add(branchLabel);
        String[] branches = {"Dhanmondi","Gulsan","Badda","Notun Bazar"};
        branchBox = new JComboBox(branches);
        branchBox.setBounds(150,300,100,20);
        add(branchBox);

        regButton = new JButton("Register");
        regButton.setBounds(60,350,90,25);
        regButton.setBackground(new Color(0xE3E3E3));
        regButton.addActionListener(this);
        add(regButton);
        haveAcButton = new JButton("Have an account?");
        haveAcButton.setBounds(200,350,150,25);
        haveAcButton.setBackground(new Color(0xE3E3E3));
        haveAcButton.addActionListener(this);
        add(haveAcButton);

        userInfoFile = new File("UserInfo.txt");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regButton){
            String name = nameField.getText();
            String number = numberField.getText();
            String pass = passwordField.getText();
            String deposit = depositField.getText();

            String gender="";
            if (maleButton.isSelected()) gender = maleButton.getText();
            else if (femaleButton.isSelected()) gender = femaleButton.getText();
            else if (others.isSelected()) gender = others.getText();

            String branch = (String) branchBox.getSelectedItem();

            String userInfo = name+" "+number+" "+pass+" "+deposit+" "+gender+" "+branch+"\n";
            System.out.println(userInfo);

            try {
                fw = new FileWriter("UserInfo.txt",true);
                bw = new BufferedWriter(fw);
                bw.write(userInfo);
                bw.close();


            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            dispose();
            new Dashboard(name,number,deposit);
        }

        if (e.getSource() == haveAcButton){
            dispose();
            new LogIn();
        }
    }
}
