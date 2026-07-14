package criminalfacedetection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton submitBtn, resetBtn;

    public Login() {
        setTitle("Login - Criminal Face Detection");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== MAIN PANEL WITH GRADIENT =====
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(36, 59, 85),
                        0, getHeight(), new Color(0, 0, 0)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());

        // ===== LOGIN CARD =====
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(350, 300));
        card.setBackground(Color.WHITE);
        card.setLayout(new GridBagLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== TITLE =====
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        card.add(title, gbc);

        // ===== USERNAME =====
        gbc.gridy++;
        gbc.gridwidth = 1;
        card.add(new JLabel("Username"), gbc);

        gbc.gridx = 1;
        userNameField = new JTextField();
        userNameField.setPreferredSize(new Dimension(150, 30));
        card.add(userNameField, gbc);

        // ===== PASSWORD =====
        gbc.gridx = 0;
        gbc.gridy++;
        card.add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(150, 30));
        card.add(passwordField, gbc);

        // ===== SHOW PASSWORD =====
        gbc.gridy++;
        JCheckBox showPass = new JCheckBox("Show Password");
        showPass.setBackground(Color.WHITE);
        gbc.gridx = 1;
        card.add(showPass, gbc);

        showPass.addActionListener(e -> {
            passwordField.setEchoChar(showPass.isSelected() ? (char) 0 : '*');
        });

        // ===== BUTTONS =====
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);

        submitBtn = new JButton("Login");
        resetBtn = new JButton("Reset");

        // Modern button styles
        submitBtn.setBackground(new Color(0, 123, 255));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        resetBtn.setBackground(new Color(220, 53, 69));
        resetBtn.setForeground(Color.WHITE);
        resetBtn.setFocusPainted(false);
        resetBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnPanel.add(submitBtn);
        btnPanel.add(resetBtn);

        card.add(btnPanel, gbc);

        mainPanel.add(card);
        add(mainPanel);

        // ===== ACTIONS =====
        submitBtn.addActionListener(this);

        resetBtn.addActionListener(e -> {
            userNameField.setText("");
            passwordField.setText("");
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String user = userNameField.getText().trim();
        String pass = new String(passwordField.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter all fields!");
            return;
        }

        try {
            Conn c = new Conn();
            String query = "select * from login where username='" + user + "' and password='" + pass + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful 🎉");

                dispose();
                new Homepage2(); // ✅ connected

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials ❌");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}