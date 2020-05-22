/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Photo_gallery_practise;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Nancy Radadia
 */
public class MainClass extends javax.swing.JFrame {

    // counter is the index of the nodes in double linked list
    int counter = 0;
    String s;

    // creatig object of double linked class
    DoubleLinkedList dll = new DoubleLinkedList();

    // function to get data at given index(counter) 
    int getc(int y) {
        int x = dll.getNth(y);
        return x;
    }

    // constructor of frame1
    public MainClass() {
        initComponents();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d.width, d.height);
        System.out.println(d.width + " " + d.height);
        setVisible(true);
        int check = dll.findSize(dll.head);
        if (check == 0) {
            jLabel5.setVisible(true);
        } else {
            jLabel5.setVisible(false);
        }
       
        currentalbum();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(290, 170, 1350, 630);

        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\OOP\\Sem3_project\\Gallery\\left.png")); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(90, 430, 58, 61);

        jLabel3.setIcon(new javax.swing.ImageIcon("D:\\OOP\\Sem3_project\\Gallery\\right.png")); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3);
        jLabel3.setBounds(1790, 430, 48, 64);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon("D:\\OOP\\Sem3_project\\Gallery\\delete.png")); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4);
        jLabel4.setBounds(790, 900, 48, 70);

        jLabel6.setIcon(new javax.swing.ImageIcon("D:\\OOP\\Sem3_project\\Gallery\\iconfinder_folder-storage-files_2931141.png")); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6);
        jLabel6.setBounds(960, 910, 56, 58);

        jLabel5.setIcon(new javax.swing.ImageIcon("D:\\OOP\\Sem3_project\\Gallery\\no_photos.PNG")); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(820, 560, 300, 50);

        jLabel8.setIcon(new javax.swing.ImageIcon("D:\\OOP\\Sem3_project\\Gallery\\iconfinder_zoom_in_172635 (1).png")); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8);
        jLabel8.setBounds(1130, 910, 60, 50);

        jLabel7.setIcon(new javax.swing.ImageIcon("D:\\OOP\\Sem3_project\\Gallery\\white1.png")); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, -10, 1900, 1120);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public ImageIcon ImageIcon(String filename) {
        filename = "";
        ImageIcon icon = new ImageIcon(filename);
        return icon;
    }

    public Image getImage(String path) {
        Image image = null;
        // TODO OOP_PO2_A1: Insert code here
        return image;
    }

    // swipe right 
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        if (counter >= dll.findSize(dll.head) - 1) {
            jLabel3.setVisible(false);
            jLabel2.setVisible(true);
            System.out.println("Cannot go right...");
        } else {
            jLabel3.setVisible(true);
            jLabel2.setVisible(true);
            counter++;
            System.out.println("index: " + counter);
            Connection con = null;
            try {

                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/practise1_sem3", "root", "");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from gallery where Number =" + getc(counter));
                if (rs.next()) {
                    String p = rs.getString("path");
                    System.out.println(p);

                    Image image = null;
                    try {
                        File sourceimage = new File(p);
                        image = ImageIO.read(sourceimage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ImageIcon image1 = new ImageIcon(image);
                    Image im = image1.getImage();
                    Image myImg = im.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    jLabel1.setIcon(newImage);
                    stmt.close();
                    con.close();

                }
            } catch (Exception e) {
                System.out.println("Connection not established " + e);
            }
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    // swipe left
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

        if (counter == 0) {
            jLabel3.setVisible(true);
            jLabel2.setVisible(false);
            System.out.println("Cannot go left...");
        } else {
            jLabel3.setVisible(true);
            jLabel2.setVisible(true);
            counter--;
            System.out.println("index = " + counter);
            Connection con = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/practise1_sem3", "root", "");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from gallery where Number =" + getc(counter));
                if (rs.next()) {
//                    byte[] img = rs.getBytes("image");
//                    ImageIcon image = new ImageIcon(img);
//                    Image im = image.getImage();
//                    Image myImg = im.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
//                    ImageIcon newImage = new ImageIcon(myImg);
//                    jLabel1.setIcon(newImage);
//                    stmt.close();
//                    con.close();

                    String p = rs.getString("path");
                    System.out.println(p);

                    Image image = null;
                    try {
                        File sourceimage = new File(p);
                        image = ImageIO.read(sourceimage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ImageIcon image1 = new ImageIcon(image);
                    Image im = image1.getImage();
                    Image myImg = im.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    jLabel1.setIcon(newImage);
                    stmt.close();
                    con.close();

                }
            } catch (Exception e) {
                System.out.println("Connection not established " + e);
            }
        }
    }//GEN-LAST:event_jLabel2MouseClicked
    // delete a node(pic)
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

        int current = 0, next = 0;
        Connection con = null;
        Statement stmt = null;
        String dbURL = "jdbc:mysql://localhost:3306/practise1_sem3";
        if (counter > 0) {
            current = getc(counter);
            next = getc(counter - 1);
            dll.deleteNodeAtGivenPos(dll.head, counter + 1);
            System.out.println("Nodes printing");
            dll.printList(dll.head);
        } else if (counter == 0) {
            current = getc(counter);

            dll.deleteNode1(dll.head, dll.head);
            //System.out.println("Nodes printing");
            dll.printList(dll.head);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, "root", "");
            stmt = con.createStatement();
            String query = "DELETE FROM gallery WHERE Number = " + current;
            System.out.println("deleteQuery = " + query);
            int deletedRows = stmt.executeUpdate(query);

            if (deletedRows == 1) {
                JOptionPane.showMessageDialog(null, "Photo delted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (counter == 0 && dll.findSize(dll.head) >= 1) {
                    counter = 0;
                    ResultSet rs = stmt.executeQuery("select * from gallery where Number =" + getc(counter));
                    if (rs.next()) {

                        String p = rs.getString("path");
                        System.out.println(p);

                        Image image = null;
                        try {
                            File sourceimage = new File(p);
                            image = ImageIO.read(sourceimage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ImageIcon image1 = new ImageIcon(image);
                        Image im = image1.getImage();
                        Image myImg = im.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon newImage = new ImageIcon(myImg);
                        jLabel1.setIcon(newImage);
                        stmt.close();
                        con.close();

                    }
                } else if (counter == 0 && dll.findSize(dll.head) == 0) {
                    jLabel1.setVisible(false);
                    jLabel5.setVisible(true);
                    jLabel1.setText("No pics");
                    System.out.println("NO Photos");

                } else {
                    counter--;
                    ResultSet rs = stmt.executeQuery("select * from gallery where Number =" + getc(counter));
                    if (rs.next()) {


                        String p = rs.getString("path");
                        System.out.println(p);

                        Image image = null;
                        try {
                            File sourceimage = new File(p);
                            image = ImageIO.read(sourceimage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ImageIcon image1 = new ImageIcon(image);
                        Image im = image1.getImage();
                        Image myImg = im.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon newImage = new ImageIcon(myImg);
                        jLabel1.setIcon(newImage);
                        stmt.close();
                        con.close();
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error in deleting Photo", "Error", JOptionPane.ERROR_MESSAGE);
            }
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Exception caught --> " + e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jLabel4MouseClicked
    // (browse)insert a pic at the end of the double linked list
    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:


        try {
            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "gif", "png");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                jLabel1.setIcon(ResizeImage(path));
                s = path;
            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("No File Select");
            }

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/practise1_sem3", "root", "");
            PreparedStatement ps = con.prepareStatement("insert into gallery(Number,path) values(?,?)");
            InputStream is = new FileInputStream(new File(s));

            int validity = dll.findSize(dll.head);
            int data = 0;
            if (validity == 0) {
                data = 1;
                dll.append(1);
            } else {
                data = dll.largestInDLL(dll.head) + 1;
                dll.append(data);
            }
            ps.setString(1, Integer.toString(data));
            
            ps.setString(2, s);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Inserted");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "First browse");
            System.out.println(ex);
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        Connection con = null;
        String p = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/practise1_sem3", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select path from gallery where Number =" + getc(counter));
            if (rs.next()) {
                p = rs.getString("path");
                stmt.close();
                con.close();

            }
        } catch (Exception e) {
            System.out.println("Connection not established " + e);
        }

        //  frame2 ob = new frame2(p);
        try {

            //String path = "C:\\Users\\Dhatri\\Pictures\\Me.jpg";
            BufferedImage image = ImageIO.read(new File(p));
            MapScale test = new MapScale(image);
            JFrame f = new JFrame();
            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.getContentPane().add(new JScrollPane(test));
            f.getContentPane().add(test.getSlider(), "Last");
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            f.setSize(d.width, d.height);
            f.setVisible(true);
            //f.setLocation(200, 200);
            f.setVisible(true);        // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    // funtion to resize the image and fit in label
    public ImageIcon ResizeImage(String imgPath) {
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

    // to fetch the all the existing nodes(pics) and displaying in album
    public void currentalbum() {
        int arr[] = new int[20];
        System.out.println("First pic: " + getc(counter));
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/practise1_sem3", "root", "");
            Statement stmt = con.createStatement();
            String query2 = "SELECT * FROM gallery";
            ResultSet rs = stmt.executeQuery(query2);
            int i = 0;
            while (rs.next()) {
                arr[i] = rs.getInt("Number");
                i++;
            }
            System.out.println(Arrays.toString(arr));
            i = 0;
            int flag = 0;
            while (arr[i] != 0) {
                dll.append(arr[i]);
                i++;
                flag = 1;
            }
            dll.printList(dll.head);
            if (flag == 1) {
                String query1 = "select path from gallery where Number =";
                rs = stmt.executeQuery(query1 + arr[0]);
                if (rs.next()) {

                    String p = rs.getString("path");
                    System.out.println(p);

                    Image image = null;
                    try {
                        File sourceimage = new File(p);
                        image = ImageIO.read(sourceimage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ImageIcon image1 = new ImageIcon(image);
                    Image im = image1.getImage();
                    Image myImg = im.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    jLabel1.setIcon(newImage);
                    stmt.close();
                    con.close();
                }
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Connection not established " + e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
