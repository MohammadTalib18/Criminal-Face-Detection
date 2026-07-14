/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author mohdt
 */

package criminalfacedetection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Homepage2 extends JFrame {

    public Homepage2() {

        setTitle("Dashboard");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JPanel header = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(52, 152, 219),
                        getWidth(), getHeight(), new Color(41, 128, 185));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        header.setPreferredSize(new Dimension(1000, 80));
        header.setLayout(new BorderLayout());

        JLabel title = new JLabel("CRIMINAL FACE DETECTION SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // ===== CENTER =====
        JPanel center = new JPanel(new GridLayout(1, 4, 25, 25));
        center.setBorder(BorderFactory.createEmptyBorder(40, 30, 40, 30));
        center.setBackground(new Color(240, 242, 245));

        JButton btn1 = createCardButton("👤 Register", new Color(231, 76, 60));
        JButton btn2 = createCardButton("📋 View Records", new Color(46, 204, 113));
        JButton btn3 = createCardButton("🖼 Image Detect", new Color(155, 89, 182));
        JButton btn4 = createCardButton("🎥 Video Detect", new Color(241, 196, 15));

        center.add(btn1);
        center.add(btn2);
        center.add(btn3);
        center.add(btn4);

        add(center, BorderLayout.CENTER);

        // ===== FOOTER =====
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(230, 230, 230));
        footer.setPreferredSize(new Dimension(1000, 60));

        JLabel copyright = new JLabel("© 2026 Security System", JLabel.LEFT);
        copyright.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

        JButton logoutBtn = createLogoutButton();

        footer.add(copyright, BorderLayout.WEST);
        footer.add(logoutBtn, BorderLayout.EAST);

        add(footer, BorderLayout.SOUTH);

        // ===== ACTIONS =====
        btn1.addActionListener(e -> {
            new RegisterCriminal().setVisible(true);
            dispose();
        });

        btn2.addActionListener(e -> {
            new ViewCriminal().setVisible(true);
            dispose();
        });

        // ✅ IMAGE DETECT BUTTON (IMPORTANT)
        btn3.addActionListener(e -> {
            new ImageDetection().detectFromImage();
        });

        btn4.addActionListener(e -> {
    new VideoDetection().startCamera();
});
        setVisible(true);
    }

    private JButton createCardButton(String text, Color color) {

        JButton btn = new JButton("<html><center>" + text + "</center></html>");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });

        return btn;
    }

    private JButton createLogoutButton() {

        JButton logout = new JButton("⏻ Logout");
        logout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logout.setForeground(Color.WHITE);
        logout.setBackground(new Color(231, 76, 60));
        logout.setFocusPainted(false);
        logout.setPreferredSize(new Dimension(140, 40));

        logout.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                dispose();
                new Login();
            }
        });

        return logout;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Homepage2::new);
    }
}