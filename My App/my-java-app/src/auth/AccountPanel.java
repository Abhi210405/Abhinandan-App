package auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AccountPanel extends JPanel {
    private final CardLayout cardLayout;
    private final JPanel cards;

    public AccountPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(40, 40, 40, 40));
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(loginTitle);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField loginUsername = new JTextField();
        loginUsername.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        loginUsername.setBorder(BorderFactory.createTitledBorder("Username"));
        loginPanel.add(loginUsername);

        JPasswordField loginPassword = new JPasswordField();
        loginPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        loginPassword.setBorder(BorderFactory.createTitledBorder("Password"));
        loginPanel.add(loginPassword);

        JButton loginBtn = new JButton("Login");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(loginBtn);

        JButton switchToRegister = new JButton("Don't have an account? Register");
        switchToRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchToRegister.setBorderPainted(false);
        switchToRegister.setContentAreaFilled(false);
        switchToRegister.setForeground(Color.BLUE.darker());
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(switchToRegister);

        // Register Panel
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel registerTitle = new JLabel("Register");
        registerTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        registerTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerPanel.add(registerTitle);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField registerUsername = new JTextField();
        registerUsername.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        registerUsername.setBorder(BorderFactory.createTitledBorder("Username"));
        registerPanel.add(registerUsername);

        JPasswordField registerPassword = new JPasswordField();
        registerPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        registerPassword.setBorder(BorderFactory.createTitledBorder("Password"));
        registerPanel.add(registerPassword);

        JPasswordField registerConfirmPassword = new JPasswordField();
        registerConfirmPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        registerConfirmPassword.setBorder(BorderFactory.createTitledBorder("Confirm Password"));
        registerPanel.add(registerConfirmPassword);

        JTextField registerEmail = new JTextField();
        registerEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        registerEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        registerPanel.add(registerEmail);

        JButton registerBtn = new JButton("Register");
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(registerBtn);

        JButton switchToLogin = new JButton("Already have an account? Login");
        switchToLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchToLogin.setBorderPainted(false);
        switchToLogin.setContentAreaFilled(false);
        switchToLogin.setForeground(Color.BLUE.darker());
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(switchToLogin);

        // Add panels to cards
        cards.add(loginPanel, "login");
        cards.add(registerPanel, "register");
        add(cards, BorderLayout.CENTER);

        // Switch actions
        switchToRegister.addActionListener(e -> cardLayout.show(cards, "register"));
        switchToLogin.addActionListener(e -> cardLayout.show(cards, "login"));

        // Example login/register actions (replace with your logic)
        loginBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Login clicked"));
        registerBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Register clicked"));
    }
}