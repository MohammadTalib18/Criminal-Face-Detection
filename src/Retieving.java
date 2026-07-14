package CriminalFaceDetection;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class Retieving extends JFrame {
    private JTextField txtCriminalId, txtCriminalIdDisplay, txtImageId, txtImageName, txtImage;
    private JTextArea txtImageDetails;

    public Retieving() {
        setTitle("Retrieving Of Images");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); // Using null layout to manually position components

        // Logo image label
        ImageIcon logo = new ImageIcon("logo.png"); // Replace with your image path
        JLabel lblLogo = new JLabel(logo);
        lblLogo.setBounds(250, 10, 100, 100);
        add(lblLogo);

        // Heading label
        JLabel lblHeading = new JLabel("<html><i><b><font size='5'>Retrieving Of Images</font></b></i></html>", JLabel.CENTER);
        lblHeading.setBounds(150, 120, 300, 30);
        add(lblHeading);

        // Navigation Buttons (Add action listeners as needed)
        JButton btnHome = new JButton("Home");
        btnHome.setBounds(100, 160, 100, 30);
        add(btnHome);

        JButton btnAboutUs = new JButton("About us");
        btnAboutUs.setBounds(210, 160, 110, 30);
        add(btnAboutUs);

        JButton btnAboutProduct = new JButton("About Product");
        btnAboutProduct.setBounds(325, 160, 130, 30);
        add(btnAboutProduct);

        // Criminal ID Submission
        JLabel lblSubmit = new JLabel("Please submit the Criminal ID");
        lblSubmit.setBounds(230, 200, 200, 20);
        add(lblSubmit);

        JLabel lblEnterCriminalId = new JLabel("Enter Criminal Id");
        lblEnterCriminalId.setBounds(120, 230, 100, 20);
        add(lblEnterCriminalId);

        txtCriminalId = new JTextField();
        txtCriminalId.setBounds(250, 230, 150, 25);
        add(txtCriminalId);

        JButton btnSubmitId = new JButton("Submit");
        btnSubmitId.setBounds(410, 230, 80, 25);
        add(btnSubmitId);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(500, 230, 80, 25);
        add(btnReset);

        // Details Form
        int labelX = 120, labelWidth = 120;
        int fieldX = 250, fieldWidth = 200, height = 25, startY = 270, gap = 35;

        JLabel lblCriminalId = new JLabel("Criminal Id");
        lblCriminalId.setBounds(labelX, startY, labelWidth, height);
        add(lblCriminalId);

        txtCriminalIdDisplay = new JTextField();
        txtCriminalIdDisplay.setBounds(fieldX, startY, fieldWidth, height);
        txtCriminalIdDisplay.setEditable(false); // Display only
        add(txtCriminalIdDisplay);

        JLabel lblImageId = new JLabel("Image Id");
        lblImageId.setBounds(labelX, startY + gap, labelWidth, height);
        add(lblImageId);

        txtImageId = new JTextField();
        txtImageId.setBounds(fieldX, startY + gap, fieldWidth, height);
        add(txtImageId);

        JLabel lblImageName = new JLabel("<html><i>Image Name</i></html>");
        lblImageName.setBounds(labelX, startY + 2 * gap, labelWidth, height);
        add(lblImageName);

        txtImageName = new JTextField();
        txtImageName.setBounds(fieldX, startY + 2 * gap, fieldWidth, height);
        add(txtImageName);

        JLabel lblImage = new JLabel("Image");
        lblImage.setBounds(labelX, startY + 3 * gap, labelWidth, height);
        add(lblImage);

        txtImage = new JTextField();
        txtImage.setBounds(fieldX, startY + 3 * gap, fieldWidth - 80, height);
        add(txtImage);

        JButton btnImageSubmit = new JButton("Submit");
        btnImageSubmit.setBounds(fieldX + fieldWidth - 70, startY + 3 * gap, 80, height);
        add(btnImageSubmit);

        JLabel lblImageDetails = new JLabel("Image details");
        lblImageDetails.setBounds(labelX, startY + 4 * gap, labelWidth, height);
        add(lblImageDetails);

        txtImageDetails = new JTextArea();
        txtImageDetails.setLineWrap(true);
        txtImageDetails.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtImageDetails);
        scrollPane.setBounds(fieldX, startY + 4 * gap, fieldWidth, 60);
        add(scrollPane);

        // Update and Cancel Buttons
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(250, 470, 100, 30);
        add(btnUpdate);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(370, 470, 100, 30);
        add(btnCancel);

        // Action Listeners for buttons
        btnSubmitId.addActionListener(e -> {
            String criminalId = txtCriminalId.getText().trim();
            if (criminalId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Criminal ID.");
            } else {
                // Example: Display the entered ID in the display field
                txtCriminalIdDisplay.setText(criminalId);
                JOptionPane.showMessageDialog(this, "Submit Criminal ID: " + criminalId);
            }
        });

        btnReset.addActionListener(e -> {
            txtCriminalId.setText("");
            txtCriminalIdDisplay.setText("");
            txtImageId.setText("");
            txtImageName.setText("");
            txtImage.setText("");
            txtImageDetails.setText("");
        });

        btnUpdate.addActionListener(e -> {
            // Update logic here
            JOptionPane.showMessageDialog(this, "Updated successfully!");
        });

        btnCancel.addActionListener(e -> {
            System.exit(0);
        });

        btnImageSubmit.addActionListener(e -> {
            // Image submit logic here
            JOptionPane.showMessageDialog(this, "Image submitted!");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Retieving().setVisible(true);
        });
    }
}
