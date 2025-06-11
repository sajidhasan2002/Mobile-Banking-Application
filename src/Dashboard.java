import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Dashboard extends JFrame implements ActionListener {
    String userName, userNumber, userBalance;

    JLabel welcomeLabel,nameLabel,numberLabel,balanceLabel;
    JLabel name, number, balance;
    JButton cashOutButton, balanceTransferButton, logOutButton;

    File userInfoFile;
    FileWriter fw;
    FileReader fr;
    BufferedWriter bw;
    BufferedReader br;

    Dashboard(String userName, String userNumber, String userBalance){
        this.userName = userName;
        this.userNumber = userNumber;
        this.userBalance = userBalance;

        setTitle("Mobile Banking [Dashboard]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450,450);
        getContentPane().setBackground(new Color(0xAFAEAD));
        setLocationRelativeTo(null);
        setLayout(null);

        welcomeLabel = new JLabel("Welcome To your Profile");
        welcomeLabel.setFont(new Font("Times New",Font.BOLD,20));
        welcomeLabel.setBounds(100,0,250,50);
        add(welcomeLabel);

        nameLabel = new JLabel("Name :");
        nameLabel.setBounds(30,95,100,30);
        add(nameLabel);
        name = new JLabel(userName);
        name.setBounds(150,100,200,20);
        add(name);

        numberLabel = new JLabel("Mobile Number :");
        numberLabel.setBounds(30,135,100,30);
        add(numberLabel);
        number = new JLabel(userNumber);
        number.setBounds(150,140,200,20);
        add(number);

        balanceLabel = new JLabel("Balance :");
        balanceLabel.setBounds(30,175,100,30);
        add(balanceLabel);
        balance = new JLabel(userBalance);
        balance.setBounds(150,180,200,20);
        add(balance);

        cashOutButton = new JButton("CashOut");
        cashOutButton.setBounds(60,250,100,25);
        cashOutButton.setBackground(new Color(0xE3E3E3));
        cashOutButton.addActionListener(this);
        add(cashOutButton);

        balanceTransferButton = new JButton("Balance Transfer");
        balanceTransferButton.setBounds(200,250,140,25);
        balanceTransferButton.setBackground(new Color(0xE3E3E3));
        balanceTransferButton.addActionListener(this);
        add(balanceTransferButton);

        logOutButton = new JButton("LogOut");
        logOutButton.setBounds(320,330,80,25);
        logOutButton.setBackground(new Color(0xE3E3E3));
        logOutButton.addActionListener(this);
        add(logOutButton);

        userInfoFile = new File("UserInfo.txt");

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cashOutButton){
           new CashOut(userName,userNumber);
            dispose();
        }
        if (e.getSource() == balanceTransferButton){
            new BalanceTransfer(userName,userNumber,userBalance);
            dispose();
        }
        if (e.getSource() == logOutButton){
            dispose();
            new Account();
        }
    }
}
