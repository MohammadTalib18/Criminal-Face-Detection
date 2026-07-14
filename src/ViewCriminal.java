/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package criminalfacedetection;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class ViewCriminal extends JFrame {
//
//    JTable table;
//    DefaultTableModel model;
//
//    public ViewCriminal() {
//
//        setTitle("Criminal Records");
//        setSize(1100, 500);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout());
//
//        // ===== SEARCH PANEL =====
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        topPanel.setBackground(new Color(245, 245, 245));
//
//        JTextField searchField = new JTextField(20);
//        JButton searchBtn = new JButton("Search");
//        JButton showAllBtn = new JButton("Show All");
//
//        topPanel.add(new JLabel("Search:"));
//        topPanel.add(searchField);
//        topPanel.add(searchBtn);
//        topPanel.add(showAllBtn);
//
//        add(topPanel, BorderLayout.NORTH);
//
//        // ===== TABLE =====
//        String[] columns = {
//                "ID", "Name", "Age", "Gender",
//                "Crime", "Date", "Address", "Notes", "Photo"
//        };
//
//        model = new DefaultTableModel(columns, 0) {
//            public Class<?> getColumnClass(int col) {
//                if (col == 8) return ImageIcon.class;
//                return String.class;
//            }
//        };
//
//        table = new JTable(model);
//        table.setRowHeight(60);
//
//        // 👉 IMAGE CLICK LISTENER
//        table.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//
//                int row = table.getSelectedRow();
//                int col = table.getSelectedColumn();
//
//                if (col == 8) {
//                    Object value = table.getValueAt(row, col);
//
//                    if (value instanceof ImageIcon) {
//                        showZoomPreview((ImageIcon) value);
//                    }
//                }
//            }
//        });
//
//        add(new JScrollPane(table), BorderLayout.CENTER);
//
//        // ===== BUTTONS =====
//        JButton updateBtn = new JButton("Update");
//        JButton deleteBtn = new JButton("Delete");
//        JButton backBtn = new JButton("Back");
//
//        updateBtn.setPreferredSize(new Dimension(120, 40));
//        deleteBtn.setPreferredSize(new Dimension(120, 40));
//        backBtn.setPreferredSize(new Dimension(120, 40));
//
//        updateBtn.setBackground(new Color(52, 152, 219));
//        deleteBtn.setBackground(new Color(231, 76, 60));
//        backBtn.setBackground(new Color(44, 62, 80));
//
//        updateBtn.setForeground(Color.WHITE);
//        deleteBtn.setForeground(Color.WHITE);
//        backBtn.setForeground(Color.WHITE);
//
//        updateBtn.setFocusPainted(false);
//        deleteBtn.setFocusPainted(false);
//        backBtn.setFocusPainted(false);
//
//        addHover(updateBtn, new Color(41, 128, 185));
//        addHover(deleteBtn, new Color(192, 57, 43));
//        addHover(backBtn, new Color(52, 73, 94));
//
//        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
//        bottomPanel.setBackground(new Color(240, 240, 240));
//
//        bottomPanel.add(backBtn);
//        bottomPanel.add(updateBtn);
//        bottomPanel.add(deleteBtn);
//
//        add(bottomPanel, BorderLayout.SOUTH);
//
//        // ===== LOAD DATA =====
//        fetchData();
//
//        // ===== SEARCH =====
//        searchBtn.addActionListener(e -> {
//            String keyword = searchField.getText();
//
//            if (keyword.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Enter ID or Name!");
//                return;
//            }
//
//            model.setRowCount(0);
//
//            try {
//                Conn conn = new Conn();
//                String query = "SELECT * FROM criminals WHERE id LIKE ? OR name LIKE ?";
//                PreparedStatement pst = conn.c.prepareStatement(query);
//
//                pst.setString(1, "%" + keyword + "%");
//                pst.setString(2, "%" + keyword + "%");
//
//                ResultSet rs = pst.executeQuery();
//
//                while (rs.next()) {
//                    addRowWithImage(rs);
//                }
//
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, ex.getMessage());
//            }
//        });
//
//        // ===== SHOW ALL =====
//        showAllBtn.addActionListener(e -> {
//            model.setRowCount(0);
//            fetchData();
//        });
//
//        // ===== DELETE =====
//        deleteBtn.addActionListener(e -> {
//
//            int row = table.getSelectedRow();
//
//            if (row == -1) {
//                JOptionPane.showMessageDialog(this, "Select a row!");
//                return;
//            }
//
//            int confirm = JOptionPane.showConfirmDialog(this,
//                    "Delete this record?", "Confirm",
//                    JOptionPane.YES_NO_OPTION);
//
//            if (confirm == JOptionPane.YES_OPTION) {
//                try {
//                    String id = model.getValueAt(row, 0).toString();
//
//                    Conn conn = new Conn();
//                    PreparedStatement pst = conn.c.prepareStatement(
//                            "DELETE FROM criminals WHERE id=?");
//
//                    pst.setString(1, id);
//                    pst.executeUpdate();
//
//                    model.removeRow(row);
//
//                    JOptionPane.showMessageDialog(this, "Deleted!");
//
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(this, ex.getMessage());
//                }
//            }
//        });
//
//        // ===== UPDATE =====
//        updateBtn.addActionListener(e -> {
//
//            int row = table.getSelectedRow();
//
//            if (row == -1) {
//                JOptionPane.showMessageDialog(this, "Select row!");
//                return;
//            }
//
//            try {
//                Conn conn = new Conn();
//
//                String query = "UPDATE criminals SET name=?, age=?, gender=?, crime=?, arrest_date=?, address=?, notes=? WHERE id=?";
//                PreparedStatement pst = conn.c.prepareStatement(query);
//
//                pst.setString(1, model.getValueAt(row, 1).toString());
//                pst.setInt(2, Integer.parseInt(model.getValueAt(row, 2).toString()));
//                pst.setString(3, model.getValueAt(row, 3).toString());
//                pst.setString(4, model.getValueAt(row, 4).toString());
//
//                java.sql.Date date = java.sql.Date.valueOf(model.getValueAt(row, 5).toString());
//                pst.setDate(5, date);
//
//                pst.setString(6, model.getValueAt(row, 6).toString());
//                pst.setString(7, model.getValueAt(row, 7).toString());
//
//                pst.setString(8, model.getValueAt(row, 0).toString());
//
//                pst.executeUpdate();
//
//                JOptionPane.showMessageDialog(this, "Updated!");
//
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, ex.getMessage());
//            }
//        });
//
//        // ===== BACK =====
//        backBtn.addActionListener(e -> {
//            dispose();
//            new Homepage2();
//        });
//
//        setVisible(true);
//    }
//
//    // ===== FETCH DATA =====
//    void fetchData() {
//        try {
//            Conn conn = new Conn();
//            ResultSet rs = conn.s.executeQuery("SELECT * FROM criminals");
//
//            while (rs.next()) {
//                addRowWithImage(rs);
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//    }
//
//    // ===== ADD ROW WITH IMAGE =====
//    void addRowWithImage(ResultSet rs) throws Exception {
//
//        String path = rs.getString("image_path");
//        ImageIcon icon = null;
//
//        if (path != null && !path.isEmpty()) {
//            icon = new ImageIcon(
//                    new ImageIcon(path)
//                            .getImage()
//                            .getScaledInstance(60, 60, Image.SCALE_SMOOTH)
//            );
//        }
//
//        model.addRow(new Object[]{
//                rs.getString("id"),
//                rs.getString("name"),
//                rs.getInt("age"),
//                rs.getString("gender"),
//                rs.getString("crime"),
//                rs.getDate("arrest_date"),
//                rs.getString("address"),
//                rs.getString("notes"),
//                icon
//        });
//    }
//
//    // ===== ZOOM IMAGE PREVIEW =====
//    void showZoomPreview(ImageIcon icon) {
//
//        JDialog dialog = new JDialog(this, "Image Preview", true);
//        dialog.setSize(400, 350);
//        dialog.setLocationRelativeTo(this);
//        dialog.setLayout(new BorderLayout());
//
//        Image originalImage = icon.getImage();
//        JLabel imageLabel = new JLabel();
//        imageLabel.setHorizontalAlignment(JLabel.CENTER);
//
//        final double[] scale = {1.0};
//
//        Runnable updateImage = () -> {
//            int width = (int) (originalImage.getWidth(null) * scale[0]);
//            int height = (int) (originalImage.getHeight(null) * scale[0]);
//
//            Image scaled = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//            imageLabel.setIcon(new ImageIcon(scaled));
//        };
//
//        updateImage.run();
//
//        JButton zoomIn = new JButton("Zoom In");
//        JButton zoomOut = new JButton("Zoom Out");
//        JButton closeBtn = new JButton("Close");
//
//        zoomIn.setBackground(new Color(46, 204, 113));
//        zoomOut.setBackground(new Color(241, 196, 15));
//        closeBtn.setBackground(new Color(231, 76, 60));
//
//        zoomIn.setForeground(Color.WHITE);
//        closeBtn.setForeground(Color.WHITE);
//
//        zoomIn.addActionListener(e -> {
//            scale[0] += 0.1;
//            updateImage.run();
//        });
//
//        zoomOut.addActionListener(e -> {
//            if (scale[0] > 0.2) {
//                scale[0] -= 0.1;
//                updateImage.run();
//            }
//        });
//
//        closeBtn.addActionListener(e -> dialog.dispose());
//
//        // Mouse Wheel Zoom
//        imageLabel.addMouseWheelListener(e -> {
//            if (e.getWheelRotation() < 0) scale[0] += 0.1;
//            else if (scale[0] > 0.2) scale[0] -= 0.1;
//            updateImage.run();
//        });
//
//        JPanel panel = new JPanel();
//        panel.add(zoomIn);
//        panel.add(zoomOut);
//        panel.add(closeBtn);
//
//        dialog.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
//        dialog.add(panel, BorderLayout.SOUTH);
//
//        dialog.setVisible(true);
//    }
//
//    // ===== HOVER =====
//    void addHover(JButton btn, Color hoverColor) {
//        Color original = btn.getBackground();
//
//        btn.addMouseListener(new MouseAdapter() {
//            public void mouseEntered(MouseEvent e) {
//                btn.setBackground(hoverColor);
//                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//            }
//
//            public void mouseExited(MouseEvent e) {
//                btn.setBackground(original);
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new ViewCriminal());
//    }
//}
//
package criminalfacedetection;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewCriminal extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewCriminal() {

        setTitle("Criminal Records");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== TOP PANEL =====
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        topPanel.setBackground(new Color(30, 144, 255));

        JTextField searchField = new JTextField(20);
        JButton searchBtn = createButton("Search", new Color(46, 204, 113));
        JButton showAllBtn = createButton("Show All", new Color(52, 152, 219));

        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        topPanel.add(showAllBtn);

        add(topPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        String[] cols = {"ID","Name","Age","Gender","Crime","Date","Address","Notes","Face Label","Photo"};

        model = new DefaultTableModel(cols,0){
            public Class<?> getColumnClass(int c){
                return c==9 ? ImageIcon.class : String.class;
            }
        };

        table = new JTable(model);
        table.setRowHeight(160);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // CLICK IMAGE
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();

                if (col == 9) { // Photo column
                    String path = getImagePath(row);
                    if (path != null) showImagePreview(path);
                }
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BOTTOM PANEL =====
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
        bottom.setBackground(new Color(240,240,240));

        JButton updateBtn = createButton("Update", new Color(52,152,219));
        JButton deleteBtn = createButton("Delete", new Color(231,76,60));
        JButton backBtn = createButton("Back", new Color(44,62,80));

        bottom.add(backBtn);
        bottom.add(updateBtn);
        bottom.add(deleteBtn);

        add(bottom, BorderLayout.SOUTH);

        // ===== LOAD DATA =====
        fetchData();

        // ===== SEARCH =====
        searchBtn.addActionListener(e -> {
            String key = searchField.getText();
            if(key.isEmpty()){
                JOptionPane.showMessageDialog(this,"Enter ID or Name!");
                return;
            }

            model.setRowCount(0);

            try{
                Conn c = new Conn();
                PreparedStatement pst = c.c.prepareStatement(
                        "SELECT * FROM criminals WHERE id LIKE ? OR name LIKE ?");
                pst.setString(1,"%"+key+"%");
                pst.setString(2,"%"+key+"%");

                ResultSet rs = pst.executeQuery();
                while(rs.next()) addRow(rs);

                adjustColumns();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }
        });

        // ===== SHOW ALL =====
        showAllBtn.addActionListener(e -> {
            model.setRowCount(0);
            fetchData();
        });

        // ===== DELETE =====
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(this,"Select row!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,"Delete?","Confirm",JOptionPane.YES_NO_OPTION);

            if(confirm==JOptionPane.YES_OPTION){
                try{
                    String id = model.getValueAt(row,0).toString();

                    Conn c = new Conn();
                    PreparedStatement pst = c.c.prepareStatement("DELETE FROM criminals WHERE id=?");
                    pst.setString(1,id);
                    pst.executeUpdate();

                    model.removeRow(row);
                    JOptionPane.showMessageDialog(this,"Deleted!");

                }catch(Exception ex){
                    JOptionPane.showMessageDialog(this,ex.getMessage());
                }
            }
        });

        // ===== UPDATE =====
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(this,"Select row!");
                return;
            }

            try{
                Conn c = new Conn();

                PreparedStatement pst = c.c.prepareStatement(
                        "UPDATE criminals SET name=?,age=?,gender=?,crime=?,arrest_date=?,address=?,notes=?,face_label=? WHERE id=?");

                pst.setString(1,model.getValueAt(row,1).toString());
                pst.setInt(2,Integer.parseInt(model.getValueAt(row,2).toString()));
                pst.setString(3,model.getValueAt(row,3).toString());
                pst.setString(4,model.getValueAt(row,4).toString());
                pst.setDate(5,Date.valueOf(model.getValueAt(row,5).toString()));
                pst.setString(6,model.getValueAt(row,6).toString());
                pst.setString(7,model.getValueAt(row,7).toString());
                pst.setInt(8,Integer.parseInt(model.getValueAt(row,8).toString())); // face label
                pst.setString(9,model.getValueAt(row,0).toString());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this,"Updated!");

            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }
        });

        // ===== BACK =====
        backBtn.addActionListener(e -> {
            dispose();
            new Homepage2().setVisible(true);
        });

        setVisible(true);
    }

    // ===== FETCH =====
    void fetchData(){
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM criminals");

            while(rs.next()) addRow(rs);

            adjustColumns();

        }catch(Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }

    // ===== ADD ROW =====
    void addRow(ResultSet rs)throws Exception{

        String path = rs.getString("image_path");
        ImageIcon icon=null;

        if(path!=null && !path.isEmpty()){
            ImageIcon img = new ImageIcon(path);

            int maxW=140, maxH=160;
            int w=img.getIconWidth(), h=img.getIconHeight();

            double r=Math.min((double)maxW/w,(double)maxH/h);

            icon=new ImageIcon(img.getImage().getScaledInstance((int)(w*r),(int)(h*r),Image.SCALE_SMOOTH));
        }

        model.addRow(new Object[]{
                rs.getString("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getString("crime"),
                rs.getDate("arrest_date"),
                rs.getString("address"),
                rs.getString("notes"),
                rs.getInt("face_label"), // ✅ FACE LABEL
                icon
        });
    }

    // ===== IMAGE PATH =====
    String getImagePath(int row){
        try{
            String id=model.getValueAt(row,0).toString();
            Conn c=new Conn();
            PreparedStatement pst=c.c.prepareStatement("SELECT image_path FROM criminals WHERE id=?");
            pst.setString(1,id);

            ResultSet rs=pst.executeQuery();
            if(rs.next()) return rs.getString("image_path");

        }catch(Exception e){ e.printStackTrace(); }
        return null;
    }

    // ===== IMAGE PREVIEW =====
    void showImagePreview(String path){

        JDialog d=new JDialog(this,"Preview",true);
        d.setSize(700,600);
        d.setLocationRelativeTo(this);

        JLabel imgLabel=new JLabel();
        imgLabel.setHorizontalAlignment(JLabel.CENTER);

        Image original=new ImageIcon(path).getImage();

        double[] scale={1.0};

        Runnable update=()->{
            int w=(int)(original.getWidth(null)*scale[0]);
            int h=(int)(original.getHeight(null)*scale[0]);
            imgLabel.setIcon(new ImageIcon(original.getScaledInstance(w,h,Image.SCALE_SMOOTH)));
        };

        update.run();

        JButton zoomIn=createButton("Zoom +",new Color(46,204,113));
        JButton zoomOut=createButton("Zoom -",new Color(241,196,15));
        JButton close=createButton("Close",new Color(231,76,60));

        zoomIn.addActionListener(e->{scale[0]+=0.1;update.run();});
        zoomOut.addActionListener(e->{if(scale[0]>0.2){scale[0]-=0.1;update.run();}});
        close.addActionListener(e->d.dispose());

        imgLabel.addMouseWheelListener(e->{
            if(e.getWheelRotation()<0) scale[0]+=0.1;
            else if(scale[0]>0.2) scale[0]-=0.1;
            update.run();
        });

        JPanel p=new JPanel();
        p.add(zoomIn);p.add(zoomOut);p.add(close);

        d.add(new JScrollPane(imgLabel),BorderLayout.CENTER);
        d.add(p,BorderLayout.SOUTH);

        d.setVisible(true);
    }

    // ===== AUTO COLUMN =====
    void adjustColumns(){
        for(int c=0;c<table.getColumnCount();c++){
            int max=50;
            for(int r=0;r<table.getRowCount();r++){
                Component comp=table.prepareRenderer(
                        table.getCellRenderer(r,c),r,c);
                max=Math.max(comp.getPreferredSize().width+10,max);
            }
            table.getColumnModel().getColumn(c).setPreferredWidth(max);
        }
    }

    // ===== BUTTON STYLE =====
    JButton createButton(String text,Color color){
        JButton b=new JButton(text);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI",Font.BOLD,14));

        b.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){b.setBackground(color.darker());}
            public void mouseExited(MouseEvent e){b.setBackground(color);}
        });

        return b;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(ViewCriminal::new);
    }
}