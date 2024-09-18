/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.formdev.flatlaf.FlatDarkLaf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Aroshana Prabhath
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssuedBooks
     */
    public IssueBook() {
        initComponents();
    }

    // load books
    public void getBookDetails() {

        int bookId = Integer.parseInt(jTextField2.getText());

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from  book_details where id = ?");

            pst.setInt(1, bookId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                book.setText(rs.getString("id"));
                name.setText(rs.getString("name"));
                author.setText(rs.getString("author"));
                quantity.setText(rs.getString("quantity"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void issueBooks() {

        int bookId;
        int studentId;

        try {
            bookId = Integer.parseInt(jTextField2.getText());
            studentId = Integer.parseInt(jTextField1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Book ID or Student ID. Please enter valid numbers.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String bookName = name.getText();
        String studentName = ssname.getText();

        Date uIssueDate = rSDateChooser2.getDatoFecha();
        Date uDueDate = rSDateChooser1.getDatoFecha();

        if (uIssueDate == null || uDueDate == null) {
            JOptionPane.showMessageDialog(null, "Please select both issue date and due date.", "Date Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection connection = DBConnection.getConnection();

        try {
            String searchBookDetail = "SELECT * FROM book_details WHERE book_details.id = ?";
            PreparedStatement searchBookDetailsQuery = connection.prepareStatement(searchBookDetail);
            searchBookDetailsQuery.setInt(1, bookId);

            ResultSet bookDetails = searchBookDetailsQuery.executeQuery();
            if (bookDetails.next()) {
                int qty = bookDetails.getInt("quantity");
                if (qty >= 1) {
                    try {
                        String sql = "INSERT INTO issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) VALUES (?,?,?,?,?,?,?)";

                        PreparedStatement pst = connection.prepareStatement(sql);
                        pst.setInt(1, bookId);
                        pst.setString(2, bookName);
                        pst.setInt(3, studentId);
                        pst.setString(4, studentName);
                        pst.setDate(5, new java.sql.Date(uIssueDate.getTime()));
                        pst.setDate(6, new java.sql.Date(uDueDate.getTime()));
                        pst.setString(7, "pending");

                        int rowCount = pst.executeUpdate();

                        if (rowCount > 0) {
                            String updateBookQuery = "UPDATE book_details SET quantity = quantity - 1 WHERE id = ?";
                            PreparedStatement updateBook = connection.prepareStatement(updateBookQuery);
                            updateBook.setInt(1, bookId);

                            int updateResult = updateBook.executeUpdate();

                            if (updateResult > 0) {
                                JOptionPane.showMessageDialog(null, "Book issued successfully!", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to update book quantity.", "Update Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to issue book. Please try again.",
                                    "Issue Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,
                                "An error occurred while issuing the book: " + e.getMessage(), "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The selected book is out of stock.", "Out of Stock",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Book not found with the given ID.", "Book Not Found",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    // load Students
    public void getStudentDetails() {

        int studentId = Integer.parseInt(jTextField1.getText());

        try {

            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from  students where id = ?");

            pst.setInt(1, studentId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                student.setText(rs.getString("id"));
                ssname.setText(rs.getString("name"));
                course.setText(rs.getString("course"));
                branch.setText(rs.getString("branch"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        branch = new javax.swing.JLabel();
        student = new javax.swing.JLabel();
        ssname = new javax.swing.JLabel();
        course = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        book = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        author = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rSDateChooser1 = new rojeru_san.componentes.RSDateChooser();
        rSDateChooser2 = new rojeru_san.componentes.RSDateChooser();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel2.setText("Student Details");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Branch:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 80, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("StudentID:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 100, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Student Name:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 140, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Course:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 80, 20));

        branch.setBackground(new java.awt.Color(0, 0, 0));
        branch.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 220, 30));

        student.setBackground(new java.awt.Color(0, 0, 0));
        student.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(student, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 210, 30));

        ssname.setBackground(new java.awt.Color(0, 0, 0));
        ssname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(ssname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 220, 30));

        course.setBackground(new java.awt.Color(0, 0, 0));
        course.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(course, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 220, 30));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 380, 710));

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        jLabel12.setText("Book Details");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Quantity:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 80, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Book ID:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 80, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book Name:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 100, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Author:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 80, 20));

        quantity.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 220, 30));

        book.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(book, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 220, 30));

        name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 220, 30));

        author.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(author, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 220, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 710));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("X");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 0, 50, 40));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Books_26px.png"))); // NOI18N
        jLabel7.setText("Issue Book");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Due Date:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, 100, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Book ID:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 100, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("StudentID:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, 100, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Issued Date:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 360, 100, 30));

        rSDateChooser1.setColorForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(rSDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 440, 230, 30));

        rSDateChooser2.setColorForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(rSDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 360, 230, 30));

        jButton2.setBackground(new java.awt.Color(153, 153, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 51));
        jButton2.setText("Issue Book");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 560, 240, 50));

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 270, 230, 30));

        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 210, 230, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1157,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE));

        setSize(new java.awt.Dimension(1163, 714));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel1MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseClicked
        System.exit(0);
    }// GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        issueBooks();

    }// GEN-LAST:event_jButton2ActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextField2FocusLost

        if (!jTextField2.getText().equals("")) {
            getBookDetails();
        }

    }// GEN-LAST:event_jTextField2FocusLost

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jTextField1FocusLost

        if (!jTextField1.getText().equals("")) {
            getStudentDetails();

        }

    }// GEN-LAST:event_jTextField1FocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel author;
    private javax.swing.JLabel book;
    private javax.swing.JLabel branch;
    private javax.swing.JLabel course;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel name;
    private javax.swing.JLabel quantity;
    private rojeru_san.componentes.RSDateChooser rSDateChooser1;
    private rojeru_san.componentes.RSDateChooser rSDateChooser2;
    private javax.swing.JLabel ssname;
    private javax.swing.JLabel student;
    // End of variables declaration//GEN-END:variables
}
