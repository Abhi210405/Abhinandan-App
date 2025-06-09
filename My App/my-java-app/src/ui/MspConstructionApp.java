import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MspConstructionApp extends JFrame {

    private static final Color BG_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(107, 114, 128); // Neutral gray #6b7280
    private static final Color TITLE_COLOR = new Color(17, 24, 39); // Darker text for titles #111827
    private static final Color BUTTON_COLOR = new Color(0, 0, 0); // Black
    private static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 48);
    private static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 18);
    private static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);

    private final JTabbedPane tabbedPane;
    private final JPanel accountPanel;
    private final UserAuth userAuth;
    private String currentUser = null;

    public MspConstructionApp() {
        setTitle("M S P CONSTRUCTION AND PROPERTIES - Please Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        userAuth = new UserAuth();

        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(20, 40, 20, 40));
        tabbedPane.setFont(NORMAL_FONT);

        // Create account panel with login/register forms
        accountPanel = new AccountPanel();
        tabbedPane.addTab("Account", accountPanel);

        // Add other tabs but disable them initially until login
        tabbedPane.addTab("Home", createHomePanel());
        tabbedPane.addTab("Services", createServicesPanel());
        tabbedPane.addTab("Properties", createPropertiesPanel());
        tabbedPane.addTab("Contact", createContactPanel());

        setTabsEnabled(false);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void setTabsEnabled(boolean enabled) {
        // account tab always enabled (index 0)
        for (int i = 1; i < tabbedPane.getTabCount(); i++) {
            tabbedPane.setEnabledAt(i, enabled);
        }
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(BG_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(80, 60, 80, 60));

        JLabel title = new JLabel("M S P CONSTRUCTION");
        title.setFont(TITLE_FONT);
        title.setForeground(TITLE_COLOR);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Building Dreams, Creating Properties");
        subtitle.setFont(SUBTITLE_FONT);
        subtitle.setForeground(TEXT_COLOR);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setBorder(new EmptyBorder(20, 0, 40, 0));

        JButton btnGetStarted = new JButton("Get Started");
        btnGetStarted.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGetStarted.setBackground(BUTTON_COLOR);
        btnGetStarted.setForeground(BUTTON_TEXT_COLOR);
        btnGetStarted.setFont(BUTTON_FONT);
        btnGetStarted.setFocusPainted(false);
        btnGetStarted.setBorder(BorderFactory.createEmptyBorder(12, 40, 12, 40));
        btnGetStarted.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGetStarted.addActionListener(_ -> JOptionPane.showMessageDialog(this,
                "Welcome to M S P CONSTRUCTION AND PROPERTIES!\nExplore our services and properties.",
                "Welcome", JOptionPane.INFORMATION_MESSAGE));

        panel.add(title);
        panel.add(subtitle);
        panel.add(btnGetStarted);

        return panel;
    }

    private JPanel createServicesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);
        panel.setBorder(new EmptyBorder(40, 60, 40, 60));

        JLabel heading = new JLabel("Our Construction Services");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 32));
        heading.setForeground(TITLE_COLOR);
        heading.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(heading, BorderLayout.NORTH);

        // Services content using cards
        JPanel servicesContent = new JPanel();
        servicesContent.setLayout(new GridLayout(0, 2, 20, 20));
        servicesContent.setBackground(BG_COLOR);

        String[][] services = {
                {"Residential Construction", "Building quality homes with excellence and attention to detail."},
                {"Commercial Buildings", "Office spaces, retail outlets, and more built to your specifications."},
                {"Property Renovations", "Modernizing and upgrading properties with expert craftsmanship."},
                {"Project Management", "End-to-end project coordination ensuring timely delivery."}
        };

        for (String[] service : services) {
            servicesContent.add(createCard(service[0], service[1]));
        }

        JScrollPane scrollPane = new JScrollPane(servicesContent);
        scrollPane.setBorder(null);
        scrollPane.setBackground(BG_COLOR);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPropertiesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_COLOR);
        panel.setBorder(new EmptyBorder(40, 60, 40, 60));

        JLabel heading = new JLabel("Available Properties");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 32));
        heading.setForeground(TITLE_COLOR);
        heading.setBorder(new EmptyBorder(0, 0, 20, 0));
        panel.add(heading, BorderLayout.NORTH);

        // Properties content with cards
        JPanel propertiesContent = new JPanel();
        propertiesContent.setLayout(new GridLayout(0, 2, 20, 20));
        propertiesContent.setBackground(BG_COLOR);

        Property[] properties = {
            new Property("Sunshine Apartments", "3 BHK apartments in prime location", "Price: $150,000"),
            new Property("Greenfield Villas", "Luxury villas with private gardens", "Price: $350,000"),
            new Property("Downtown Offices", "Commercial office space available", "Price: $500,000"),
            new Property("Lakeview Homes", "Cozy homes near the lake", "Price: $200,000")
        };

        for (Property property : properties) {
            // Use HTML with <br> inside <html> tag for multiple lines.
            String descHtml = "<html>" + property.description + "<br>" + property.price + "</html>";
            propertiesContent.add(createCard(property.name, descHtml));
        }

        JScrollPane scrollPane = new JScrollPane(propertiesContent);
        scrollPane.setBorder(null);
        scrollPane.setBackground(BG_COLOR);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createContactPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(BG_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(40, 60, 40, 60));

        JLabel heading = new JLabel("Contact Us");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 32));
        heading.setForeground(TITLE_COLOR);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        heading.setBorder(new EmptyBorder(0, 0, 30, 0));
        panel.add(heading);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(BG_COLOR);
        formPanel.setMaximumSize(new Dimension(600, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(NORMAL_FONT);
        nameLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField();
        nameField.setFont(NORMAL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(NORMAL_FONT);
        emailLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField();
        emailField.setFont(NORMAL_FONT);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(emailField, gbc);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setFont(NORMAL_FONT);
        messageLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(messageLabel, gbc);

        JTextArea messageArea = new JTextArea(5, 20);
        messageArea.setFont(NORMAL_FONT);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane messageScroll = new JScrollPane(messageArea);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(messageScroll, gbc);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBackground(BUTTON_COLOR);
        submitBtn.setForeground(BUTTON_TEXT_COLOR);
        submitBtn.setFont(BUTTON_FONT);
        submitBtn.setFocusPainted(false);
        submitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(submitBtn, gbc);

        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String message = messageArea.getText().trim();

            if(name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!email.matches("^\\S+@\\S+\\.\\S+$")) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(panel,
                    "Thank you for contacting us, " + name + "!\nWe will get back to you shortly.",
                    "Message Sent", JOptionPane.INFORMATION_MESSAGE);

            nameField.setText("");
            emailField.setText("");
            messageArea.setText("");
        });

        panel.add(formPanel);
        return panel;
    }

    // Utility method to create a card panel with title and description (supports html)
    private JPanel createCard(String title, String description) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230,230,230)),
                BorderFactory.createEmptyBorder(16, 24, 16, 24)
        ));
        card.setLayout(new BorderLayout());
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.setPreferredSize(new Dimension(300, 140));
        card.setMaximumSize(new Dimension(300, 140));

        JLabel titleLabel = new JLabel("<html><b>" + title + "</b></html>");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(TITLE_COLOR);

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(NORMAL_FONT);
        descLabel.setForeground(TEXT_COLOR);
        descLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(descLabel, BorderLayout.CENTER);

        // subtle hover effect
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(245, 245, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(Color.WHITE);
            }
        });

        return card;
    }

    // Property class to hold property details
    private static class Property {
        String name;
        String description;
        String price;

        Property(String name, String description, String price) {
            this.name = name;
            this.description = description;
            this.price = price;
        }
    }

    // Simple user authentication management with in-memory store
    private class UserAuth {
        private final HashMap<String, String> users = new HashMap<>();

        public boolean register(String username, String password) {
            if(users.containsKey(username.toLowerCase())) {
                return false; // user exists
            }
            users.put(username.toLowerCase(), password);
            return true;
        }

        public boolean login(String username, String password) {
            String stored = users.get(username.toLowerCase());
            if(stored == null) return false;
            return stored.equals(password);
        }
    }

    // Account panel with login and register forms and switching
    private class AccountPanel extends JPanel {
        private final CardLayout cardLayout;
        private final JPanel cards;

        private final JTextField loginUsername;
        private final JPasswordField loginPassword;
        private final JButton loginBtn;
        private final JButton switchToRegister;

        private final JTextField registerUsername;
        private final JPasswordField registerPassword;
        private final JPasswordField registerConfirmPassword;
        private final JButton registerBtn;
        private final JButton switchToLogin;
        private final JTextField registerEmail;
        private String generatedOtp = null;
        private String pendingEmail = null;
        private String pendingUsername = null;
        private String pendingPassword = null;
        private final JTextField otpField;
        private final JButton verifyOtpBtn;

        AccountPanel() {
            setBackground(BG_COLOR);
            setLayout(new BorderLayout());
            setBorder(new EmptyBorder(60, 60, 60, 60));

            cardLayout = new CardLayout();
            cards = new JPanel(cardLayout);
            cards.setBackground(BG_COLOR);

            // Login Panel
            JPanel loginPanel = new JPanel();
            loginPanel.setBackground(new Color(255, 255, 255, 220)); // White with some transparency
            loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

            JLabel loginTitle = new JLabel("Login");
            loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
            loginTitle.setForeground(new Color(0, 102, 204)); // Blue title
            loginTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginTitle.setBorder(new EmptyBorder(0, 0, 30, 0));
            loginPanel.add(loginTitle);

            loginUsername = new JTextField();
            styleTextField(loginUsername, "Username");
            loginPanel.add(loginUsername);
            loginPanel.add(Box.createRigidArea(new Dimension(0, 15)));

            loginPassword = new JPasswordField();
            styleTextField(loginPassword, "Password");
            loginPanel.add(loginPassword);
            loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            loginBtn = new JButton("Login");
            styleButton(loginBtn);
            loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginPanel.add(loginBtn);
            loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            switchToRegister = new JButton("Don't have an account? Register");
            switchToRegister.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            switchToRegister.setForeground(Color.BLUE.darker());
            switchToRegister.setContentAreaFilled(false);
            switchToRegister.setBorderPainted(false);
            switchToRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
            switchToRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginPanel.add(switchToRegister);

            // Register Panel
            JPanel registerPanel = new JPanel();
            registerPanel.setBackground(new Color(255, 255, 255, 220));
            registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));

            JLabel registerTitle = new JLabel("Register");
            registerTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
            registerTitle.setForeground(new Color(0, 102, 204));
            registerTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            registerTitle.setBorder(new EmptyBorder(0, 0, 30, 0));
            registerPanel.add(registerTitle);

            registerUsername = new JTextField();
            styleTextField(registerUsername, "Username");
            registerPanel.add(registerUsername);
            registerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

            registerPassword = new JPasswordField();
            styleTextField(registerPassword, "Password");
            registerPanel.add(registerPassword);
            registerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

            registerConfirmPassword = new JPasswordField();
            styleTextField(registerConfirmPassword, "Confirm Password");
            registerPanel.add(registerConfirmPassword);
            registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            registerEmail = new JTextField();
            styleTextField(registerEmail, "Email");
            registerPanel.add(registerEmail);
            registerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

            otpField = new JTextField();
            styleTextField(otpField, "Enter OTP");
            otpField.setVisible(false);
            registerPanel.add(otpField);
            registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            verifyOtpBtn = new JButton("Verify OTP");
            styleButton(verifyOtpBtn);
            verifyOtpBtn.setVisible(false);
            registerPanel.add(verifyOtpBtn);
            registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            registerBtn = new JButton("Register");
            styleButton(registerBtn);
            registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            registerPanel.add(registerBtn);
            registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            switchToLogin = new JButton("Already have an account? Login");
            switchToLogin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            switchToLogin.setForeground(Color.BLUE.darker());
            switchToLogin.setContentAreaFilled(false);
            switchToLogin.setBorderPainted(false);
            switchToLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
            switchToLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
            registerPanel.add(switchToLogin);

            cards.add(loginPanel, "login");
            cards.add(registerPanel, "register");

            add(cards, BorderLayout.CENTER);

            // Event handlers
            switchToRegister.addActionListener(_ -> {
                clearLoginFields();
                cardLayout.show(cards, "register");
            });

            switchToLogin.addActionListener(_ -> {
                clearRegisterFields();
                cardLayout.show(cards, "login");
            });

            loginBtn.addActionListener(_ -> performLogin());
            registerBtn.addActionListener(_ -> performRegister());
            verifyOtpBtn.addActionListener(_ -> {
                String enteredOtp = otpField.getText().trim();
                if(enteredOtp.equals(generatedOtp)) {
                    if(userAuth.register(pendingUsername, pendingPassword)) {
                        JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        clearRegisterFields();
                        cardLayout.show(cards, "login");
                    } else {
                        JOptionPane.showMessageDialog(this, "Username already exists. Choose a different username.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    }
                    otpField.setVisible(false);
                    verifyOtpBtn.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid OTP. Please try again.", "OTP Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        private void styleTextField(JTextField tf, String placeholder) {
            tf.setFont(NORMAL_FONT);
            tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            tf.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220)),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
            ));
            tf.setToolTipText(placeholder);
        }

        private void styleButton(JButton btn) {
            btn.setBackground(BUTTON_COLOR);
            btn.setForeground(BUTTON_TEXT_COLOR);
            btn.setFont(BUTTON_FONT);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        }

        private void performLogin() {
            String username = loginUsername.getText().trim();
            String password = new String(loginPassword.getPassword());

            if(username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(userAuth.login(username, password)) {
                currentUser = username;
                JOptionPane.showMessageDialog(this,
                        "Welcome " + currentUser + "!\nLogin successful.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                setTabsEnabled(true);
                setTitle("M S P CONSTRUCTION AND PROPERTIES - Logged in as " + currentUser);
                // Automatically switch to Home tab
                tabbedPane.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void performRegister() {
            String username = registerUsername.getText().trim();
            String password = new String(registerPassword.getPassword());
            String confirmPassword = new String(registerConfirmPassword.getPassword());
            String email = registerEmail.getText().trim();

            if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(!email.matches("^\\S+@\\S+\\.\\S+$")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Generate OTP and send email
            generatedOtp = String.valueOf((int)(Math.random() * 900000) + 100000);
            try {
                EmailUtil.sendOtp(email, generatedOtp);
                JOptionPane.showMessageDialog(this, "OTP sent to your email. Please enter it below.", "OTP Sent", JOptionPane.INFORMATION_MESSAGE);
                otpField.setVisible(true);
                verifyOtpBtn.setVisible(true);
                pendingEmail = email;
                pendingUsername = username;
                pendingPassword = password;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to send OTP: " + ex.getMessage(), "Email Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void clearLoginFields() {
            loginUsername.setText("");
            loginPassword.setText("");
        }

        private void clearRegisterFields() {
            registerUsername.setText("");
            registerPassword.setText("");
            registerConfirmPassword.setText("");
            registerEmail.setText("");
            otpField.setText("");
            otpField.setVisible(false);
            verifyOtpBtn.setVisible(false);
            generatedOtp = null;
            pendingEmail = null;
            pendingUsername = null;
            pendingPassword = null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MspConstructionApp app = new MspConstructionApp();
            app.setVisible(true);
        });
    }
}

class EmailUtil {
    public static void sendOtp(String toEmail, String otp) throws Exception {
        final String fromEmail = "your_email@gmail.com"; // change to your email
        final String password = "your_app_password"; // use app password for Gmail

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(fromEmail));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        msg.setSubject("Your OTP Code");
        msg.setText("Your OTP code is: " + otp);

        Transport.send(msg);
    }
}

