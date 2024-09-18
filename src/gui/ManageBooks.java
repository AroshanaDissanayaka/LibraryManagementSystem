/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.formdev.flatlaf.FlatDarkLaf;
import static gui.DBConnection.con;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
/**
 *
 * @author Aroshana Prabhath
 */
public class ManageBooks extends javax.swing.JFrame {

    
    String name,author;
    int id,quantity;
    DefaultTableModel model;
    
    public ManageBooks() {
        initComponents();
        setBookDetailsToTable();
         
    }
    
    
    
    public void setBookDetailsToTable(){
    
        try {
            
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Lbms", "root", "DBMS@aroshana2024");
            Statement st = con.createStatement();
            
            ResultSet rs =  st.executeQuery("select * from book_details ");
            
            
            while(rs.next()){
            
            String bookId = rs.getString("id");
            String bookName = rs.getString("name");
            String author = rs.getString("author");
            int quantity = rs.getInt("quantity");
            
            
            
            Object[] obj = {bookId,bookName,author,quantity};
            model = (DefaultTableModel) tblBookDetails.getModel();
            model.addRow(obj);
            }
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    
    
    }
    
    
    
    public boolean addBook(){
    
     boolean isAdded = false;   
     id = Integer.parseInt(Bookid.getText());
     name = Bookname.getText();
     author = Authername.getText();
     quantity = Integer.parseInt(Quantity.getText());
     
     
     
        try {
            
            Connection con = DBConnection.getConnection();
            String sql = "insert into book_details values (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, author);
            pst.setInt(4, quantity);
            
            int rowCount = pst.executeUpdate();
            
            if(rowCount > 0 ){
            
            isAdded = true;
            
            }else{
            
            
            isAdded = false;
            
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return isAdded;
    
    }
    
    
    public void clearTable(){
    
    
    DefaultTableModel model = (DefaultTableModel) tblBookDetails.getModel();
    model.setRowCount(0);
    
    
    }
    
    
    
     public boolean updateBook(){
     
     
      boolean isUpdated = false;   
      id = Integer.parseInt(Bookid.getText());
      name = Bookname.getText();
      author = Authername.getText();
      quantity = Integer.parseInt(Quantity.getText());
     
     
         try {
             
             Connection con = DBConnection.getConnection();
             String sql = "update book_details set name = ?,author = ?,quantity = ? WHERE id = ?";
             PreparedStatement pst = con.prepareStatement(sql);
             
           
            pst.setString(1, name);
            pst.setString(2, author);
            pst.setInt(3, quantity);
            pst.setInt(4, id);
            
             
            int rowCount =  pst.executeUpdate();
            
             if (rowCount > 0) {
                 
                 isUpdated = true;
             }else{
             
             
                 isUpdated = false;
             }
             
         } catch (Exception e) {
             e.printStackTrace();
         }
     
         
         return isUpdated;
     }
     
     
     
     
     
     public boolean deleteBook(){
     
      boolean isDeleted = false;   
      id = Integer.parseInt(Bookid.getText());
     
      
         try {
             
             Connection con = DBConnection.getConnection();
             String sql = "delete from book_details where id = ?";
             PreparedStatement pst = con.prepareStatement(sql);
             
             pst.setInt(1, id);
             
             int rowCount = pst.executeUpdate();
             
             if (rowCount>0) {
                 
                 isDeleted = true;
             }else{
             
             
                isDeleted = false;
             
             }
             
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         
         
         return isDeleted;
      
      
     
     
     
     
     
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Quantity = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Bookid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Bookname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Authername = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBookDetails = new rojeru_san.complementos.RSTableMetro();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1724, 824));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jLabel2.setText("Quantity");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 250, -1));
        jPanel1.add(Quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 380, 40));

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jLabel3.setText("Enter Book ID");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 210, -1));
        jPanel1.add(Bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 380, 40));

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jLabel4.setText("Enter Book Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 250, -1));
        jPanel1.add(Bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 380, 40));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jLabel5.setText("Authur Name");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 250, -1));
        jPanel1.add(Authername, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 380, 40));

        jButton1.setBackground(new java.awt.Color(153, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 51));
        jButton1.setText("DELETE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 680, 120, 50));

        jButton2.setBackground(new java.awt.Color(153, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 120, 50));

        jButton4.setBackground(new java.awt.Color(153, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 51, 51));
        jButton4.setText("UPDATE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 680, 110, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 820));

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));

        tblBookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book Id", "Name", "Author", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBookDetails.setRowHeight(44);
        tblBookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBookDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBookDetails);

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Books_26px.png"))); // NOI18N
        jLabel6.setText("Manage Books");

        jButton6.setBackground(new java.awt.Color(255, 51, 0));
        jButton6.setFont(new java.awt.Font("Imprint MT Shadow", 1, 24)); // NOI18N
        jButton6.setText("Print");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(109, 109, 109))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(416, 416, 416)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(95, 95, 95)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addGap(98, 98, 98)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(168, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 1280, 820));

        setSize(new java.awt.Dimension(1612, 824));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
         if (deleteBook() == true) {
            
             JOptionPane.showMessageDialog(this, " Book is Succesfully Deleted");
             clearTable();
             setBookDetailsToTable();
            
        }else{
        
             
            JOptionPane.showMessageDialog(this, " Book is Deleted Fail");
            
        
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (addBook() == true) {
            
            JOptionPane.showMessageDialog(this, "Your  Book  is Added Succesfully");
             clearTable();
             setBookDetailsToTable();
            
        }else{
        
             
            JOptionPane.showMessageDialog(this, "Your Book is Added Fail");
            
        
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if ( updateBook() == true) {
            
             JOptionPane.showMessageDialog(this, "Your  Book  is Update Succesfully");
             clearTable();
             setBookDetailsToTable();
            
        }else{
        
             
            JOptionPane.showMessageDialog(this, "Your Book is Update Fail");
            
        
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblBookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookDetailsMouseClicked
      
        int rowNo =  tblBookDetails.getSelectedRow();
        TableModel model =  tblBookDetails.getModel();
        
        Bookid.setText(model.getValueAt(rowNo, 0).toString());
        Bookname.setText(model.getValueAt(rowNo, 1).toString());
        Authername.setText(model.getValueAt(rowNo, 2).toString());
        Quantity.setText(model.getValueAt(rowNo, 3).toString());        
        
        
    }//GEN-LAST:event_tblBookDetailsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Authername;
    private javax.swing.JTextField Bookid;
    private javax.swing.JTextField Bookname;
    private javax.swing.JTextField Quantity;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.RSTableMetro tblBookDetails;
    // End of variables declaration//GEN-END:variables
}
