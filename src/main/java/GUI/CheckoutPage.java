/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import static GUI.Login.userIDLogin;
import data.DataManager;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Menu;
import model.cart.CartItem;
import model.order.Order;
import model.order.OrderItem;
import model.promo.CashbackPromo;
import model.promo.DeliveryPromo;
import model.promo.DiscountPromo;
import model.promo.Promo;
import model.promo.PromoCode;
import model.user.Member;
import model.user.User;
import utils.LocalDateUtils;

/**
 *
 * @author Kenzie Taqiyassar
 */
public class CheckoutPage extends javax.swing.JFrame {

    /**
     * Creates new form Checkout
     */
    public CheckoutPage() {
        initComponents();
        loadPromoList();
        loadPrintTextArea();
    }
    DefaultTableModel d;

    void loadPromoList() {
        d = (DefaultTableModel) tableListPromoCheckout.getModel();
        d.setRowCount(0);

        HashMap<String, PromoCode> promos = DataManager.promos;

        for (Map.Entry<String, PromoCode> entry : promos.entrySet()) {
            String promoCode = entry.getKey();
            PromoCode promo = entry.getValue();
            String promoStartAt = LocalDateUtils.convertLocalDateToString(promo.getStartedAt());
            String promoEndAt = LocalDateUtils.convertLocalDateToString(promo.getExpiredAt());
            int percentCut = promo.getPercentCut();
            double minTransaction = promo.getMinApplicablePrice();
            double maxCut = promo.getMaxPriceCut();
//            , promoStartAt, promoEndAt, percentCut, minTransaction, maxCut
            // Add the menu data to the table
            Object[] rowData = {promoCode};
            d.addRow(rowData);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        logoutButton = new javax.swing.JLabel();
        logoutButton1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ApplyPromoButton = new javax.swing.JButton();
        checkOutButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotaCheckout = new javax.swing.JTextArea();
        txtSelectedPromoCode = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableListPromoCheckout = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Payment");

        logoutButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutButtonMouseClicked(evt);
            }
        });

        logoutButton1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        logoutButton1.setText("Cart");
        logoutButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(logoutButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 470, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(373, 373, 373)
                .addComponent(logoutButton)
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoutButton1)
                    .addComponent(jLabel1)
                    .addComponent(logoutButton))
                .addGap(16, 16, 16))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Apply Promo :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Promo Code:");

        ApplyPromoButton.setText("Apply");
        ApplyPromoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ApplyPromoButtonMouseClicked(evt);
            }
        });
        ApplyPromoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApplyPromoButtonActionPerformed(evt);
            }
        });

        checkOutButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        checkOutButton.setText("Checkout");
        checkOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkOutButtonMouseClicked(evt);
            }
        });
        checkOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutButtonActionPerformed(evt);
            }
        });

        txtNotaCheckout.setEditable(false);
        txtNotaCheckout.setColumns(20);
        txtNotaCheckout.setRows(5);
        jScrollPane2.setViewportView(txtNotaCheckout);

        txtSelectedPromoCode.setText("nothing selected");
        txtSelectedPromoCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSelectedPromoCodeActionPerformed(evt);
            }
        });

        tableListPromoCheckout.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Available Promo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableListPromoCheckout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListPromoCheckoutMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableListPromoCheckout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(checkOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtSelectedPromoCode, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel2)))
                                .addGap(253, 253, 253))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(ApplyPromoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSelectedPromoCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(ApplyPromoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(checkOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(174, 174, 174))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void logoutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseClicked
        // TODO add your handling code here:
        this.dispose();
        Login login = new Login();
        login.setVisible(true);

    }//GEN-LAST:event_logoutButtonMouseClicked

    private void ApplyPromoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApplyPromoButtonActionPerformed
        // TODO add your handling code here:
        HashMap<String, PromoCode> promos = DataManager.promos;
        HashMap<String, User> users = DataManager.users;

        String promoId = txtSelectedPromoCode.getText();

        try {
            if (users.containsKey(userIDLogin)) {
                User member = users.get(userIDLogin);
                if (promos.containsKey(promoId)) {
                    PromoCode promo = promos.get(promoId);
                    ((Member) member).applyPromoCode(promo);
                } else {
                    System.out.println("APPLY_PROMO FAILED: NON EXISTENT CUSTOMER OR MENU");
                    JOptionPane.showMessageDialog(this, "APPLY_PROMO FAILED: NON EXISTENT CUSTOMER OR MENU");
                }
            } else {
                JOptionPane.showMessageDialog(this, "APPLY_PROMO FAILED: NON EXISTENT CUSTOMER OR MENU");
                System.out.println("APPLY_PROMO FAILED: NON EXISTENT CUSTOMER OR MENU");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }


    }//GEN-LAST:event_ApplyPromoButtonActionPerformed

    private void ApplyPromoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ApplyPromoButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ApplyPromoButtonMouseClicked

    private void loadPrintTextArea() {
        String userID = Login.userIDLogin;
        HashMap<String, User> users = DataManager.users;
        if (users.containsKey(userID)) {
            User member = users.get(userID);
            member.viewCartItems();
        } else {
            User guest = users.get(userID);
            guest.viewCartItems();
        }
    }

    private void checkOutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkOutButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_checkOutButtonMouseClicked

    StringBuilder populateOrderSummary(User user) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nKode Pelanggan: ").append(user.getUserID());
        sb.append("\nNama: ").append(user.getUserName());
        sb.append("\nNomor Pesanan: ").append(user.getOrderList().getLastOrders().getOrderNumber());
        sb.append("\nTanggal Pesanan: ").append(user.getOrderList().getLastOrders().getCreatedAt());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        sb.append(String.format("\n%3s | %-20s | %3s | %8s \n", "No", "Menu", "Qty", "Subtotal"));
        sb.append("==================================================\n");
        int i = 1;
        // require last order
        OrderItem lastOrdered = user.getOrderList().getLastOrders();
        for (CartItem cartItem : lastOrdered.getOrderedItem()) {
            Menu menu = cartItem.getMenu();
            String menuName = menu.getMenuName().length() >= 20 ? menu.getMenuName().substring(0, 20) : menu.getMenuName();
            String subtotal = formatter.format(menu.getPrice() * cartItem.getQuantity());
            sb.append(String.format("%3d | %-20s | %3d | %8s \n", i, menuName, cartItem.getQuantity(), subtotal));
            i++;
        }
        sb.append("==================================================\n");
        String subtotal = formatter.format(lastOrdered.getItemSubTotal()); // subtotal
        String delivery = formatter.format(user.getShippingCost()); // biaya ongkir
        String total = formatter.format(user.getOrderList().getTotalPrice(lastOrdered.getItemSubTotal())); // total harga
        String balance = formatter.format(user.getBalance()); // saldo
        sb.append(String.format("%-27s: %9s\n", "Total", subtotal));
        if (user instanceof Member member) {
            if (member.getUserPromo() != null) {
                if (member.getUserPromo() instanceof DiscountPromo) {
                    String discount = formatter.format(-member.getUserPromo().getPricePromo(lastOrdered.getItemSubTotal()));
                    sb.append(String.format("%-27s: %9s\n", "PROMO: " + member.getUserPromo().getPromoCode(), discount));
                }
            }
        }
        sb.append(String.format("%-27s: %9s\n", "Ongkos kirim", delivery));
        if (user instanceof Member member) {
            if (member.getUserPromo() != null) {
                if (member.getUserPromo() instanceof DeliveryPromo) {
                    String deliveryOff = formatter.format(-member.getUserPromo().getPricePromo(lastOrdered.getItemSubTotal()));
                    sb.append(String.format("%-27s: %9s\n", "PROMO: " + member.getUserPromo().getPromoCode(), deliveryOff));
                }
            }
        }
        sb.append("==================================================\n");
        sb.append(String.format("%-27s: %9s\n", "Total", total));
        if (user instanceof Member member) {
            if (member.getUserPromo()!= null) {
                if (member.getUserPromo() instanceof CashbackPromo) {
                    String cashback = formatter.format(member.getUserPromo().getPricePromo(lastOrdered.getItemSubTotal()));
                    sb.append(String.format("%-27s: %9s\n", "PROMO: " + member.getUserPromo().getPromoCode(), cashback));
                }
            }
        }
        sb.append(String.format("%-27s: %9s\n", "Sisa Saldo", balance)); // shopping cart Saldo bukan Sisa saldo
        sb.append("\n");
        return sb;
    }

    private void checkOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOutButtonActionPerformed
        // TODO add your handling code here:

        String userID = Login.userIDLogin;
        HashMap<String, User> users = DataManager.users;

        if (users.containsKey(userID)) {
            User member = users.get(userID);
            member.checkout();
            txtNotaCheckout.setText(populateOrderSummary(member.getOrderList().getUser()).toString());
            member.getOrderList().getLastOrderDetails();           
            dispose();
            CheckoutSucces succes = new CheckoutSucces();
            succes.setVisible(true);
        } else if (users.containsKey(userID)) {
            User guest = users.get(userID);
            guest.checkout();
            txtNotaCheckout.setText(populateOrderSummary(guest.getOrderList().getUser()).toString());
            dispose();
            CheckoutSucces succes = new CheckoutSucces();
            succes.setVisible(true);
        } else {
            System.out.println("CHECKOUT FAILED: NON EXISTENT CUSTOMER");
        }


    }//GEN-LAST:event_checkOutButtonActionPerformed

    private void logoutButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButton1MouseClicked
        // TODO add your handling code here:
        dispose();
        UserPage user = new UserPage();
        user.setVisible(true);
    }//GEN-LAST:event_logoutButton1MouseClicked

    private void txtSelectedPromoCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSelectedPromoCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSelectedPromoCodeActionPerformed

    private void tableListPromoCheckoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListPromoCheckoutMouseClicked
        // TODO add your handling code here:
        d = (DefaultTableModel) tableListPromoCheckout.getModel();
        int selectedIndex = tableListPromoCheckout.getSelectedRow();

        txtSelectedPromoCode.setText(d.getValueAt(selectedIndex, 0).toString());
    }//GEN-LAST:event_tableListPromoCheckoutMouseClicked

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
            java.util.logging.Logger.getLogger(CheckoutPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckoutPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckoutPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckoutPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckoutPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ApplyPromoButton;
    private javax.swing.JButton checkOutButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel logoutButton;
    private javax.swing.JLabel logoutButton1;
    private javax.swing.JTable tableListPromoCheckout;
    private javax.swing.JTextArea txtNotaCheckout;
    private javax.swing.JTextField txtSelectedPromoCode;
    // End of variables declaration//GEN-END:variables
}
