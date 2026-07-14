


//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class Login02 extends JFrame {
//     JTextField userNameField;
//     JPasswordField passwordField;
//     JComboBox userTypeCombo;
//
//    public Login02() {
//        setTitle("Login Page");
//        setSize(500, 340);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // Panel for top logo and title
//        JPanel topPanel = new JPanel();
//        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
//        topPanel.setBackground(Color.LIGHT_GRAY);
//
//        JPanel centerPanel = new JPanel();
//        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
//        centerPanel.setBackground(Color.WHITE);
//        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 60, 60, 60));
//
//        JLabel instructionLabel = new JLabel("Please Log On To Your Page !!!!");
//        instructionLabel.setFont(new Font("Serif", Font.ITALIC, 16));
//        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        instructionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 50, 0));
//        centerPanel.add(instructionLabel);
//
//        // User Input Panel with GridBagLayout for labels and fields
//        JPanel formPanel = new JPanel(new GridBagLayout());
//        formPanel.setBackground(Color.DARK_GRAY);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 15, 5, 15);
//
//
//        JLabel userLabel = new JLabel("User Name");
//         userLabel.setForeground(Color.WHITE); 
//        formPanel.add(userLabel);
//
//    
//       
//        userNameField = new JTextField(15);
//        userNameField.setText("lavanya");
//        formPanel.add(userNameField, gbc);
//
//        // Password
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.anchor = GridBagConstraints.EAST;
//        JLabel passLabel = new JLabel("Password");
//        passLabel.setForeground(Color.WHITE); 
//        formPanel.add(passLabel, gbc);
//
//        gbc.gridx = 1;
//        gbc.anchor = GridBagConstraints.WEST;
//        passwordField = new JPasswordField(15);
//        passwordField.setText("$$$$$$$");
//        formPanel.add(passwordField, gbc);
//
//        // Type of User combo box
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.anchor = GridBagConstraints.EAST;
//        JLabel typeLabel = new JLabel("Type of User");
//        typeLabel.setForeground(Color.WHITE); 
//        formPanel.add(typeLabel, gbc);
//
//        gbc.gridx = 1;
//        gbc.anchor = GridBagConstraints.WEST;
//        String[] userTypes = {"Administrator", "General User", "Guest"};
//        userTypeCombo = new JComboBox<>(userTypes);
//        userTypeCombo.setSelectedIndex(0);
//        formPanel.add(userTypeCombo, gbc);
//
//        centerPanel.add(formPanel);
//
//        // Buttons panel (Submit and Reset)
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
//        buttonPanel.setBackground(Color.white);
//
//        JButton submitBtn = new JButton("Submit");
//        JButton resetBtn = new JButton("Reset");
//        submitBtn.setPreferredSize(new Dimension(100, 30));
//        resetBtn.setPreferredSize(new Dimension(100, 30));
//
//        buttonPanel.add(submitBtn);
//        buttonPanel.add(resetBtn);
//
//        centerPanel.add(buttonPanel);
//
//        add(centerPanel, BorderLayout.SOUTH);
//
//        // Reset button action listener
//        resetBtn.addActionListener(e -> {
//            userNameField.setText("");
//            passwordField.setText("");
//            userTypeCombo.setSelectedIndex(0);
//        });
//
//      
//
//        // Set background color for frame content pane
//        getContentPane().setBackground(Color.WHITE);
//
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        // Run on Event Dispatch Thread
//      new Login02();
//    }
//}
package criminalfacedetection;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class Login02 extends JFrame  {
    JTextField  username;
    JPasswordField password;
    JButton login ,cancel;
    Login02(){
       getContentPane().setBackground(Color.WHITE);
        setLayout(null);
          setTitle("Receptionist Login"); 
          
         JLabel user  =new JLabel("Username");
         user.setBounds(40,20,100,30);
         add(user);
         
         username = new JTextField();
         username.setBounds(150,20,150,30);
         add(username);
         
         
         JLabel pass  =new JLabel("Password");
         pass.setBounds(40,70,100,30);
         add(pass);
         
         login  =new JButton("Login");
         login.setBounds(180,150,120,30);
         login.setBackground(Color.black);
         login.setForeground(Color.white);
//         login.addActionListener(this);
         add(login);
         
        cancel  =new JButton("Cancel");
        cancel.setBounds(40,150,120,30);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
//        cancel.addActionListener(this);
         add(cancel);
                 
        password = new  JPasswordField();
        password.setBounds(150,70,150,30);
         add(password);
         
         ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
         Image i2=i1.getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT);
         ImageIcon i3= new ImageIcon(i2);
            JLabel image= new JLabel(i3);
             image.setBounds(350,10,200,200);
                add(image);
        
        setBounds(330,200,600,300);
        setVisible(true);
        
//    }
//    public void actionPerformed(ActionEvent ae){
//        if (ae.getSource()==login){
//
//            String user= username.getText();
//            String pass= password.getText();
//         try{
//             Conn c= new Conn();
//            String query= "select * from login  where username= '" + user+"' and password ='" +pass+"'";
//            ResultSet rs=c.s.executeQuery(query);
//                if (rs.next()){
//                setVisible(false);
////                 new Dashboard();
//        }else{
//                JOptionPane.showMessageDialog(null,"Invalid username or password");
//                    setVisible(false);
//}
//        } catch(Exception e){
//             e.printStackTrace();
//
//
//                }
//
//           } else if (ae.getSource()==cancel) {
//                    setVisible(false);
//                    }
//        }
//    
//    
    
    }
    

    
    public static void main(String[] args){
        new Login02();
    
}
}
