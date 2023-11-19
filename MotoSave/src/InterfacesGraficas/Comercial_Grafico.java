/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package InterfacesGraficas;

import AccesoDatos.JDBC.JDBCMotocicletaDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import INTERFACES.*;

/**
 *
 * @author USER
 */
public class Comercial_Grafico extends javax.swing.JFrame {

    //Movimiento JPanel
    private static int mouseX, mouseY;
    private static boolean mousePressed;
    // Atributos clase
    private String tipoPers;
    private JDBCMotocicletaDAO motoDAO;

    public Comercial_Grafico() {
        initComponents();
        habilitarArrastre(this);
        listarMotosGarajes();
        actualizarComercialVentas();
    }

    public Comercial_Grafico(Connection con_e, String tipoPers_e) {
        this.tipoPers = tipoPers_e;
        motoDAO = new JDBCMotocicletaDAO(con_e);
        initComponents();
        habilitarArrastre(this);
        listarMotosGarajes();
        actualizarComercialVentas();
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
        L_motosave_MoficarMoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        L_introduceMatricula_Inicio = new javax.swing.JLabel();
        TF_introMatricula_comercial = new javax.swing.JTextField();
        B_comprar_moto = new javax.swing.JButton();
        B_motos_vendidas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        L_nombre_comercial = new javax.swing.JLabel();
        L_cartera_comercial = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        S_separador_Inicio = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        T_infoMotos_Comercial = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        L_motosave_MoficarMoto.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        L_motosave_MoficarMoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_motosave_MoficarMoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Drawable/MotoSave.png"))); // NOI18N

        L_introduceMatricula_Inicio.setText("Introduce Matricula:");

        TF_introMatricula_comercial.setName(""); // NOI18N
        TF_introMatricula_comercial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_introMatricula_comercialActionPerformed(evt);
            }
        });

        B_comprar_moto.setText("Comprar Motocicleta");
        B_comprar_moto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_comprar_motoActionPerformed(evt);
            }
        });

        B_motos_vendidas.setText("Motocicletas Vendidas");
        B_motos_vendidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_motos_vendidasActionPerformed(evt);
            }
        });

        jLabel1.setText("Comercial:");

        L_nombre_comercial.setText("Javier");

        L_cartera_comercial.setText("5420");

        jLabel5.setText("Ventas:");

        S_separador_Inicio.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(S_separador_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(B_motos_vendidas, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(L_introduceMatricula_Inicio)
                        .addGap(34, 34, 34)
                        .addComponent(TF_introMatricula_comercial, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(L_nombre_comercial, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(L_cartera_comercial, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(B_comprar_moto, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(L_nombre_comercial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(L_cartera_comercial))
                .addGap(18, 18, 18)
                .addComponent(B_motos_vendidas)
                .addGap(18, 18, 18)
                .addComponent(S_separador_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_introduceMatricula_Inicio)
                    .addComponent(TF_introMatricula_comercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(B_comprar_moto)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("MOTOCICLETAS");

        T_infoMotos_Comercial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Matricula", "Marca", "Modelo", "Color", "Cilindrada CC", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(T_infoMotos_Comercial);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(177, 177, 177))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(L_motosave_MoficarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(L_motosave_MoficarMoto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TF_introMatricula_comercialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_introMatricula_comercialActionPerformed

    }//GEN-LAST:event_TF_introMatricula_comercialActionPerformed

    private void B_comprar_motoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_comprar_motoActionPerformed

    }//GEN-LAST:event_B_comprar_motoActionPerformed

    private void B_motos_vendidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_motos_vendidasActionPerformed
        DefaultTableModel modeloTabla = estructuraTabla();

//        try {
//            int aux = garajeAux.buscarIdGaraje(String.valueOf(CB_garajes_Inicio.getSelectedItem()));
//            for (Motocicleta moto : motoAux.listarMotocicletasGaraje(aux)) {
//                String matricula = moto.getMatricula();
//                String marca = moto.getMarca();
//                String modelo = moto.getModelo();
//                String color = moto.getColor();
//                String cc = String.valueOf(moto.getCC());
//
//                modeloTabla.addRow(new Object[]{matricula, marca, modelo, color, cc});
//            }
//            L_T_nombreGaraje.setText(garajeAux.buscarGaraje(aux).getSucursal());
//            T_infoMotos_Inicio.setModel(modeloTabla);
//        } catch (MotocicletaExcepcion ex) {
//            JOptionPane.showMessageDialog(this, "No hay Motocicletas en este Garaje");
//        }
    }//GEN-LAST:event_B_motos_vendidasActionPerformed

    private DefaultTableModel estructuraTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Matrícula");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Color");
        modeloTabla.addColumn("Cilindrada (cc)");
        modeloTabla.addColumn("Precio (€)");
        return modeloTabla;
    }

    private void listarMotosGarajes() {

        DefaultTableModel modeloTabla = estructuraTabla();

        //recorrer todos los garajes y listar todas las motos.
        T_infoMotos_Comercial.setModel(modeloTabla);
    }

    private void actualizarComercialVentas() {
        L_nombre_comercial.setText("");
        L_cartera_comercial.setText("");

    }

    public static void habilitarArrastre(JFrame frame) {
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed = true;
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mousePressed) {
                    int x = e.getXOnScreen();
                    int y = e.getYOnScreen();
                    frame.setLocation(x - mouseX, y - mouseY);
                }
            }
        });
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
            java.util.logging.Logger.getLogger(Comercial_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Comercial_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Comercial_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Comercial_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Comercial_Grafico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_comprar_moto;
    private javax.swing.JButton B_motos_vendidas;
    private javax.swing.JLabel L_cartera_comercial;
    private javax.swing.JLabel L_introduceMatricula_Inicio;
    private javax.swing.JLabel L_motosave_MoficarMoto;
    private javax.swing.JLabel L_nombre_comercial;
    private javax.swing.JSeparator S_separador_Inicio;
    private javax.swing.JTextField TF_introMatricula_comercial;
    private javax.swing.JTable T_infoMotos_Comercial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
