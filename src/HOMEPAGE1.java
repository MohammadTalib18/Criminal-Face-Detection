////
//package criminalfacedetection;
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//// --- IMAGE CHANGE ---
//import java.io.File;
//import javax.swing.border.LineBorder;
//// --------------------
//
//public class HOMEPAGE1 extends JFrame {
//
//    public HOMEPAGE1() {
//        // Frame setup
//        setTitle("Criminal Face Detection System - Home");
//        setBounds(0, 0, 1560, 1000);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);  // Center on screen
//        getContentPane().setBackground(Color.WHITE);  // Main background
//        setLayout(null);  // Simple absolute positioning
//
//        // Title label - Red bold text
//        JLabel titleLabel = new JLabel("Criminal Face Detection System");
//        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
//        titleLabel.setForeground(Color.BLACK);
//        titleLabel.setBounds(450, 20, 400, 70);
//        add(titleLabel);
//
//        // Subtitle - Orange text
//        JLabel subtitleLabel = new JLabel("Welcome to Advanced Security Solutions");
//        subtitleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
//        subtitleLabel.setForeground(Color.ORANGE);
//        subtitleLabel.setBounds(470, 70, 400, 70);
//        add(subtitleLabel);
//
//        // Navigation buttons panel (top row)
//        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        navPanel.setBackground(Color.ORANGE);
//        navPanel.setBounds(350, 200, 650, 70);
//        navPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
//        String[] navButtons = {"Home", "Login", "About Us", "About Product", "Exit"};
//        for (String btnText : navButtons) {
//            JButton btn = new JButton(btnText);
//            btn.setPreferredSize(new Dimension(90, 30));
//            btn.setForeground(Color.BLACK);
//            btn.setBackground(Color.LIGHT_GRAY);
//            btn.addActionListener(e -> handleNavigation(btnText));
//            navPanel.add(btn);
//        }
//        add(navPanel);
//
//    
//        JTextArea welcomeArea = new JTextArea(
//                "Welcome to the Criminal Face Detection System!\n\n" +
//                        "This application uses advanced computer vision to detect and match faces against a database of known criminals.\n" +
//                        "Features:\n" +
//                        "- Secure login for administrators and users.\n" +
//                        "- Upload images/videos for real-time detection.\n" +
//                        "- Manage criminal database (admin only).\n" +
//                        "- Ethical and privacy-compliant (uses synthetic data).\n\n" +
//                        "Get started by logging in!"
//        );
//        welcomeArea.setFont(new Font("Serif", Font.PLAIN, 15));
//        welcomeArea.setForeground(Color.BLACK);
//        welcomeArea.setBackground(Color.ORANGE);
//        welcomeArea.setEditable(false);
//        welcomeArea.setLineWrap(true);
//        welcomeArea.setWrapStyleWord(true);
//        // Your original coordinates for text area
//        welcomeArea.setBounds(420, 280, 500, 250);
//        welcomeArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
//        add(welcomeArea);
//
//        // Footer label - Gray text
//        JLabel footerLabel = new JLabel("© 2026 Criminal Face Detection Project");
//        footerLabel.setForeground(Color.GRAY);
//        footerLabel.setBounds(1030, 600, 250, 50);
//        add(footerLabel);
//
//        setVisible(true);
//    }
//
//    // Handle navigation button clicks
//    private void handleNavigation(String buttonText) {
//        switch (buttonText) {
//            case "Home":
//                JOptionPane.showMessageDialog(this, "You are already on the Home Page!");
//                break;
//        case "Login":
//    new Login();   // opens login without closing home
//    break;
//            case "About Us":
//                JOptionPane.showMessageDialog(this,
//                        "About Us:\n" +
//                                "Developed by Team SecurityAI.\n" +
//                                "This project focuses on ethical AI for public safety.\n" +
//                                "Contact: info@securityai.com",
//                        "About Us",
//                        JOptionPane.INFORMATION_MESSAGE);
//                break;
//            case "About Product":
//                JOptionPane.showMessageDialog(this,
//                        "About Product:\n" +
//                                "Criminal Face Detection System uses OpenCV and ML models (e.g., FaceNet) to identify suspects.\n" +
//                                "Key Tech: Java Swing UI, SQL DB, Synthetic Face Data.\n" +
//                                "Version: 1.0",
//                        "About Product",
//                        JOptionPane.INFORMATION_MESSAGE);
//                break;
//            case "Exit":
//                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?",
//                        "Exit Confirmation", JOptionPane.YES_NO_OPTION);
//                if (confirm == JOptionPane.YES_OPTION) {
//                    System.exit(0);
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    public static void main(String[] args) {
//        // Run on Event Dispatch Thread (Best Practice for Swing)
//        SwingUtilities.invokeLater(() -> new HOMEPAGE1());
//    }
//}




package criminalfacedetection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HOMEPAGE1 extends JFrame {

    Color primary = new Color(15, 32, 65);      // Dark Blue
    Color secondary = new Color(0, 102, 204);   // Light Blue
    Color accent = new Color(255, 153, 0);      // Orange

    public HOMEPAGE1() {

        setTitle("Criminal Face Detection System");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setBackground(primary);
        header.setPreferredSize(new Dimension(100, 70));
        header.setLayout(new BorderLayout());

        JLabel title = new JLabel(" CRIMINAL FACE DETECTION SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // ===== SIDEBAR =====
        JPanel sidebar = new JPanel();
        sidebar.setBackground(primary);
        sidebar.setPreferredSize(new Dimension(200, 100));
        sidebar.setLayout(new GridLayout(6, 1, 0, 10));

        String[] menuItems = {"Home", "Login", "About Us", "About Product", "Exit"};

        for (String item : menuItems) {
            JButton btn = new JButton(item);
            styleSidebarButton(btn);
            btn.addActionListener(e -> handleNavigation(item));
            sidebar.add(btn);
        }

        add(sidebar, BorderLayout.WEST);

        // ===== MAIN CONTENT =====
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        JTextArea content = new JTextArea(
                "Welcome to the Criminal Face Detection System\n\n" +
                "This system helps law enforcement agencies identify suspects.\n\n" +
                "Features:\n" +
                "• Face Recognition using OpenCV\n" +
                "• Criminal Database Management\n" +
                "• Real-time Surveillance\n" +
                "• Secure Login System\n\n" +
                "Please login to continue..."
        );

        content.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        content.setEditable(false);
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        mainPanel.add(content, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // ===== FOOTER =====
        JPanel footer = new JPanel();
        footer.setBackground(primary);
        footer.setPreferredSize(new Dimension(100, 40));

        JLabel footText = new JLabel("© 2026 Security System");
        footText.setForeground(Color.LIGHT_GRAY);

        footer.add(footText);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ===== BUTTON STYLE =====
    private void styleSidebarButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(25, 45, 85));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover Effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(0, 102, 204));
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(25, 45, 85));
            }
        });
    }

    // ===== NAVIGATION =====
    private void handleNavigation(String action) {
        switch (action) {

            case "Home":
                JOptionPane.showMessageDialog(this, "Already on Home");
                break;

            case "Login":
                new Login();
                break;

            case "About Us":
                JOptionPane.showMessageDialog(this,
                        "Developed for Law Enforcement Agencies\nUsing AI & Computer Vision",
                        "About Us", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "About Product":
                JOptionPane.showMessageDialog(this,
                        "Face Detection using OpenCV & Machine Learning\nVersion 1.0",
                        "Product Info", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "Exit":
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Exit Application?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HOMEPAGE1());
    }
}