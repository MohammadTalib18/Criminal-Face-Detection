package criminalfacedetection;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class InsertImage extends JFrame {

    private JTextField criminalIdField;
    private JTextField imageIdField;
    private JTextField imageNameField;
    private JTextField imagePathField;
    private JTextArea imageDetailsArea;

    public InsertImage() {
        setTitle("Insertion of Images");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel for logo
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.white);
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("your_logo_path_here.png"); // Provide your icon file path here
        logoLabel.setIcon(logoIcon);
        topPanel.add(logoLabel);
        add(topPanel, BorderLayout.NORTH);

        // Center panel for content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(15, 50,10,50));
        centerPanel.setBackground(Color.white);

        // Title label
        JLabel titleLabel = new JLabel("Insertion Of Images");
        titleLabel.setFont(new Font("Serif", Font.ITALIC, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(titleLabel);

        centerPanel.add(Box.createRigidArea(new Dimension(0,15)));

        // Navigation buttons panel
        JPanel navPanel = new JPanel();
        navPanel.setBackground(Color.white);
        navPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton homeBtn = new JButton("Home");
        JButton aboutUsBtn = new JButton("About us");
        JButton aboutProductBtn = new JButton("About Product");

        // Customize About us button to highlight in green and bold (like a label)
        aboutUsBtn.setForeground(new Color(0, 128, 0));
        aboutUsBtn.setFont(new Font("Dialog", Font.BOLD, 12));

        navPanel.add(homeBtn);
        navPanel.add(aboutUsBtn);
        navPanel.add(aboutProductBtn);

        centerPanel.add(navPanel);

        centerPanel.add(Box.createRigidArea(new Dimension(0,15)));

        // Subtitle
        JLabel subtitleLabel = new JLabel("<html><b>Please Fill Details & Insert the Image !!!!</b></html>");
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(subtitleLabel);

        centerPanel.add(Box.createRigidArea(new Dimension(0,15)));

        // Input form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.LINE_START;

        // Row 1: Enter Criminal Id
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Enter Criminal Id"), gbc);

        criminalIdField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(criminalIdField, gbc);

        // Row 2: Enter a Image Id
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Enter a Image Id"), gbc);

        imageIdField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(imageIdField, gbc);

        // Row 3: Image Name
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Image Name"), gbc);

        imageNameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(imageNameField, gbc);

        // Row 4: Insert the Image + Browse Button
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Insert the Image"), gbc);

        JPanel browsePanel = new JPanel(new BorderLayout());
        browsePanel.setBackground(Color.white);
        imagePathField = new JTextField(15);
        browsePanel.add(imagePathField, BorderLayout.CENTER);
        JButton browseBtn = new JButton("Browse");
        browsePanel.add(browseBtn, BorderLayout.EAST);

        gbc.gridx = 1;
        formPanel.add(browsePanel, gbc);

        // Row 5: (Upload image) label in green below the browse button
        gbc.gridx = 1;
        gbc.gridy++;
        JLabel uploadLabel = new JLabel("(Upload image)");
        uploadLabel.setForeground(new Color(0, 128, 0));
        formPanel.add(uploadLabel, gbc);

        // Add form panel to centerPanel
        centerPanel.add(formPanel);

        centerPanel.add(Box.createRigidArea(new Dimension(0,15)));

        // Image Details label and text area
        JPanel imageDetailsPanel = new JPanel(new BorderLayout());
        imageDetailsPanel.setBackground(Color.white);
        JLabel imageDetailsLabel = new JLabel("Image Details");
        imageDetailsLabel.setBorder(new EmptyBorder(0,0,5,0));
        imageDetailsPanel.add(imageDetailsLabel, BorderLayout.NORTH);

        imageDetailsArea = new JTextArea(5, 30);
        imageDetailsArea.setLineWrap(true);
        imageDetailsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(imageDetailsArea);
        imageDetailsPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(imageDetailsPanel);

        centerPanel.add(Box.createRigidArea(new Dimension(0,15)));

        // Buttons panel for Save and Cancel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.white);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        buttonsPanel.add(saveBtn);
        buttonsPanel.add(cancelBtn);

        centerPanel.add(buttonsPanel);

        add(centerPanel, BorderLayout.CENTER);

        // Browse button action to open file chooser
        browseBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                imagePathField.setText(file.getAbsolutePath());
            }
        });

        // Save and Cancel button actions (placeholders)
        saveBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Save button clicked!");
            // Implement your save logic here
        });

        cancelBtn.addActionListener(e -> {
            // Clear all fields
            criminalIdField.setText("");
            imageIdField.setText("");
            imageNameField.setText("");
            imagePathField.setText("");
            imageDetailsArea.setText("");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InsertImage().setVisible(true);
        });
    }
}
