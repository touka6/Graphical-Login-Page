import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Login
{
    JLabel label0 , label1 , label2 , label3 , label4;
    JButton login , register;
    JTextField username;
    JPasswordField password;
    JCheckBox check;

    Login()
    {
        JFrame Login = new JFrame("Login");
        Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        label0 = new JLabel("LOGIN");
        label0.setFont(new Font("serif", Font.BOLD, 25));
        label0.setBounds(150,10,100, 50);;
        Login.add(label0);

        label1 = new JLabel();
        label1.setText("Don't have an account?");
        label1.setBounds(90, 40, 200, 50);
        Login.add(label1);

        login = new JButton("Login");
        login.setBounds(140, 250, 100, 30);
        Login.add(login);

        register = new JButton("Register");
        register.setBounds(225, 56, 85, 20);
        Login.add(register);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Login.dispose();
                new Register();
            }
        });

        label2 = new JLabel("Username:");
        label2.setFont(new Font("Verdana",Font.PLAIN,12));
        label2.setBounds(80, 105, 100, 30);
        Login.add(label2);

        username = new JTextField();
        username.setBounds(150,110, 162, 25);
        Login.add(username);

        label3 = new JLabel("Password:");
        label3.setFont(new Font("Verdana",Font.PLAIN,12));
        label3.setBounds(80, 180, 100, 30);
        Login.add(label3);

        password = new JPasswordField();
        password.setBounds(150, 185, 162, 25);
        Login.add(password);

        check = new JCheckBox("Show Password");
        check.setBounds(145, 210, 150, 30);
        Login.add(check);

        check.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if(e.getStateChange()==1)
                {
                    password.setEchoChar((char)0);
                }
                else
                {
                    password.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
        });

        JFrame error = new JFrame("Error");
        label4 = new JLabel();
        JButton errorButton = new JButton("KK");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String pass = new String(password.getPassword());
                UserStore read = new UserStore();
                int t = read.Read(username.getText(), pass);
                switch (t) {
                    case 0:
                    {
                        label4.setText("no match found.");
                        label4.setBounds(5, 30, 300, 30);
                        errorButton.setBounds(200, 120, 60, 25);
                        error.setSize(300, 250);
                        error.setLayout(null);
                        error.setVisible(true);
                        errorButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                error.dispose();
                            }
                        });
                        break;
                    }
                    case 1:
                    {
                        label4.setText("wrong password.");
                        label4.setBounds(5, 30, 300, 30);
                        errorButton.setBounds(200, 120, 60, 25);
                        error.add(label4);
                        error.add(errorButton);
                        error.setSize(475, 200);
                        error.setLayout(null);
                        error.setResizable(false);
                        error.setVisible(true);
                        errorButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                error.dispose();
                            }
                        });
                        break;
                    }
                    default:
                    {
                        JFrame success = new JFrame("Notice");
                            JLabel huh = new JLabel("successfully Logined");
                            JButton hmm = new JButton("YAYY");
                            huh.setBounds(5, 30, 300, 30);
                            hmm.setBounds(200, 120, 70, 25);
                            success.add(huh);
                            success.add(hmm);
                            success.setSize(475, 200);
                            success.setLayout(null);
                            success.setResizable(false);
                            success.setVisible(true);
                            hmm.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    success.dispose();
                                }
                            });
                        break;
                    }
                }
            }
        });

        Login.setSize(400, 400);
        Login.setLayout(null);
        Login.setResizable(false);
        Login.setVisible(true);
    }
}
