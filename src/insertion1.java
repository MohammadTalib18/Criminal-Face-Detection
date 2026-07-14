package criminalfacedetection;


import javax.swing.*;
import java.awt.*;

public class insertion1 extends JFrame {

    public insertion1() {
        setTitle("");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel for Logo and first label + 3 buttons
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Logo in the center
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        // Load your image here; replace "logo.png" with your image path
        ImageIcon logoIcon = new ImageIcon("logo.png");
        // Scale the icon if needed
        Image scaledImage = logoIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaledImage));
        topPanel.add(logoLabel, BorderLayout.NORTH);

        // Label "Criminal Image Insertion Form"
        JLabel titleLabel = new JLabel("Criminal Image Insertion Form", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.ITALIC, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Panel for 3 buttons
        JPanel menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton homeButton = new JButton("Home");
        JButton aboutUsButton = new JButton("About us");
        JButton aboutProductButton = new JButton("About Product");

        menuButtonPanel.add(homeButton);
        menuButtonPanel.add(aboutUsButton);
        menuButtonPanel.add(aboutProductButton);

        topPanel.add(menuButtonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Center panel for "Plz Select Ur Choice" label and 2 buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel choiceLabel = new JLabel("Plz Select Ur Choice");
        choiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        choiceLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        choiceLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        centerPanel.add(choiceLabel);

        JPanel choiceButtonPanel = new JPanel();
        choiceButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

        JButton newCriminalButton = new JButton("New Criminal");
        JButton existingCriminalButton = new JButton("Existing Criminal");

        choiceButtonPanel.add(newCriminalButton);
        choiceButtonPanel.add(existingCriminalButton);

        centerPanel.add(choiceButtonPanel);

        add(centerPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            insertion1 form = new insertion1();
            form.setVisible(true);
        });
    }
}
