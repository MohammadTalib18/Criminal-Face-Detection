package criminalfacedetection;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.File;
import java.sql.*;

public class RegisterCriminal extends JFrame {

    JLabel photoLabel;
    JTextField txtId, txtName, txtAge, txtCrime, txtDate;
    JComboBox<String> genderBox;
    JTextArea txtAddress, txtNotes;

    String imagePath = "";
    int faceLabel; // ✅ NEW

    public RegisterCriminal() {

        setTitle("Criminal Information");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Color bgColor = new Color(10, 30, 60);
        Color fieldColor = new Color(25, 55, 100);

        JPanel panel = new JPanel(null);
        panel.setBackground(bgColor);
        panel.setPreferredSize(new Dimension(800, 780));

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        // ===== PHOTO =====
        JLabel lblPhoto = new JLabel("Photo:");
        lblPhoto.setBounds(150, 80, 100, 25);
        lblPhoto.setForeground(Color.WHITE);
        panel.add(lblPhoto);

        photoLabel = new JLabel("No Image", SwingConstants.CENTER);
        photoLabel.setBounds(250, 60, 180, 180);
        photoLabel.setBorder(new LineBorder(Color.WHITE));
        photoLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(photoLabel);

        JButton browseBtn = new JButton("Browse...");
        browseBtn.setBounds(450, 120, 120, 35);
        panel.add(browseBtn);
        browseBtn.addActionListener(e -> chooseImage());

        // ===== LABELS =====
        addLabel(panel, "Criminal ID:", 150, 260);
        addLabel(panel, "Full Name:", 150, 300);
        addLabel(panel, "Age:", 150, 340);
        addLabel(panel, "Gender:", 150, 380);
        addLabel(panel, "Crime Type:", 150, 420);
        addLabel(panel, "Date (Y-M-D):", 150, 460);
        addLabel(panel, "Address:", 150, 500);
        addLabel(panel, "Notes:", 150, 580);

        // ===== FIELDS =====
        txtId = addTextField(panel, 250, 260, fieldColor);
        txtId.setEditable(false);

        txtName = addTextField(panel, 250, 300, fieldColor);
        txtAge = addTextField(panel, 250, 340, fieldColor);

        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderBox.setBounds(250, 380, 350, 30);
        panel.add(genderBox);

        txtCrime = addTextField(panel, 250, 420, fieldColor);

        txtDate = addTextField(panel, 250, 460, fieldColor);
        txtDate.setText("2026-04-04");

        txtAddress = addTextArea(panel, 250, 500);
        txtNotes = addTextArea(panel, 250, 580);

        txtId.setText(generateCriminalId()); // also sets faceLabel

        // ===== BUTTONS =====
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(250, 670, 100, 35);
        panel.add(submitBtn);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(370, 670, 100, 35);
        panel.add(clearBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(490, 670, 100, 35);
        panel.add(backBtn);

        submitBtn.addActionListener(e -> submitForm());
        clearBtn.addActionListener(e -> clearForm());

        backBtn.addActionListener(e -> {
            new Homepage2().setVisible(true);
            dispose();
        });
    }

    // ✅ AUTO ID + FACE LABEL
    String generateCriminalId() {
        String newId = "CR-001";

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery(
                    "SELECT id FROM criminals ORDER BY id DESC LIMIT 1");

            if (rs.next()) {
                String lastId = rs.getString("id");
                int num = Integer.parseInt(lastId.split("-")[1]);
                num++;

                faceLabel = num; // ✅ IMPORTANT
                newId = String.format("CR-%03d", num);

            } else {
                faceLabel = 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
            faceLabel = 1;
        }

        return newId;
    }

    void addLabel(JPanel panel, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 150, 25);
        lbl.setForeground(Color.WHITE);
        panel.add(lbl);
    }

    JTextField addTextField(JPanel panel, int x, int y, Color color) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 350, 30);
        tf.setBackground(color);
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(new LineBorder(Color.WHITE));
        panel.add(tf);
        return tf;
    }

    JTextArea addTextArea(JPanel panel, int x, int y) {
        JTextArea ta = new JTextArea();
        ta.setBounds(x, y, 350, 60);
        ta.setBorder(new LineBorder(Color.WHITE));
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        panel.add(ta);
        return ta;
    }

    // ===== IMAGE =====
    void chooseImage() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            imagePath = file.getAbsolutePath();

            ImageIcon icon = new ImageIcon(
                    new ImageIcon(imagePath)
                            .getImage()
                            .getScaledInstance(180, 180, Image.SCALE_SMOOTH)
            );

            photoLabel.setText("");
            photoLabel.setIcon(icon);
        }
    }

    // ===== SUBMIT =====
    void submitForm() {
        try {

            if (txtName.getText().isEmpty() || txtAge.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill required fields!");
                return;
            }

            java.sql.Date sqlDate = java.sql.Date.valueOf(txtDate.getText());

            Conn conn = new Conn();

            String query = "INSERT INTO criminals (id, name, age, gender, crime, arrest_date, address, notes, image_path, face_label) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.c.prepareStatement(query);

            pst.setString(1, txtId.getText());
            pst.setString(2, txtName.getText());
            pst.setInt(3, Integer.parseInt(txtAge.getText()));
            pst.setString(4, (String) genderBox.getSelectedItem());
            pst.setString(5, txtCrime.getText());
            pst.setDate(6, sqlDate);
            pst.setString(7, txtAddress.getText());
            pst.setString(8, txtNotes.getText());
            pst.setString(9, imagePath);
            pst.setInt(10, faceLabel); // ✅ ADDED

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "✅ Record Saved!\nFace Label: " + faceLabel);

            clearForm();
            txtId.setText(generateCriminalId());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    // ===== CLEAR =====
    void clearForm() {
        txtName.setText("");
        txtAge.setText("");
        txtCrime.setText("");
        txtDate.setText("");
        txtAddress.setText("");
        txtNotes.setText("");
        genderBox.setSelectedIndex(0);
        photoLabel.setIcon(null);
        photoLabel.setText("No Image");
        imagePath = "";
    }

    // ===== MAIN =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegisterCriminal().setVisible(true);
        });
    }
}