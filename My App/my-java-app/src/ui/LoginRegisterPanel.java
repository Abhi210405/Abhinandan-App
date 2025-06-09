import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginRegisterPanel extends JPanel {
    public LoginRegisterPanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Sign In", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 1));
        formPanel.setBackground(new Color(255, 204, 0));

        JTextField usernameField = new JTextField("Username");
        formPanel.add(usernameField);

        JPasswordField passwordField = new JPasswordField("Password");
        formPanel.add(passwordField);

        JCheckBox rememberMeCheckBox = new JCheckBox("Remember me");
        formPanel.add(rememberMeCheckBox);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBackground(new Color(0, 204, 255));
        loginButton.setForeground(Color.WHITE);
        formPanel.add(loginButton);

        JLabel forgotPasswordLabel = new JLabel("Forgot Password?", SwingConstants.CENTER);
        formPanel.add(forgotPasswordLabel);

        JLabel registerLabel = new JLabel("Don't have an account? Register here", SwingConstants.CENTER);
        registerLabel.setForeground(Color.BLUE);
        formPanel.add(registerLabel);

        add(formPanel, BorderLayout.CENTER);

        // Action listeners
        loginButton.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Login button clicked")
        );
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(LoginRegisterPanel.this, "Register link clicked");
            }
        });
    }
}