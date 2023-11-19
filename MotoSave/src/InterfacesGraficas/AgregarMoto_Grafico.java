/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package InterfacesGraficas;

import AccesoDatos.JDBC.JDBCMotocicletaDAO;
import AccesoDatos.Serializar.SerializarMotocicletaDAO;
import INTERFACES.ConexionBBDD;
import Modelo.Motocicleta;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class AgregarMoto_Grafico extends javax.swing.JFrame {

    //Movimiento JPanel
    private static int mouseX, mouseY;
    private static boolean mousePressed;
    // Atributos clase
    private int idGaraje;
    private String tipoPers;
    // Conexion y BDD
    private JDBCMotocicletaDAO motoDAO;
    private SerializarMotocicletaDAO serialMoto;

    public AgregarMoto_Grafico() {
        initComponents();
        habilitarArrastre(this);
    }

    public AgregarMoto_Grafico(int idGaraje_e, Connection con_e, String tipoPers_e) {
        // Control tipo de metodo de persistencia
        if (tipoPers_e.equals(Enumerados.metodoPersistencia.JDBC.toString())) {
            this.tipoPers = Enumerados.tipoDAO.JDBC_MOTOCICLETA.toString();
            motoDAO = (JDBCMotocicletaDAO) Factorias.FactoriaDAO.crearObjetoDAO(tipoPers, con_e);
        } else {
            this.tipoPers = Enumerados.tipoDAO.SERIALIZAR_MOTOCICLETA.toString();
            serialMoto = (SerializarMotocicletaDAO) Factorias.FactoriaDAO.crearObjetoDAO(tipoPers);
        }

        idGaraje = idGaraje_e;
        initComponents();
        cargarColoresCB();
        habilitarArrastre(this);
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
        L_motosave_AgregarMoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        L_infoAgregar_AgregarMoto = new javax.swing.JLabel();
        L_matricula_AgregarMoto = new javax.swing.JLabel();
        L_marca_AgregarMoto = new javax.swing.JLabel();
        L_modelo_AgregarMoto = new javax.swing.JLabel();
        L_Color_AgregarMoto = new javax.swing.JLabel();
        L_CC_AgregarMoto = new javax.swing.JLabel();
        B_volver_AgregarMoto = new javax.swing.JButton();
        B_Agregar_AgregarMoto = new javax.swing.JButton();
        TF_marca_AgregarMoto = new javax.swing.JTextField();
        TF_modelo_AgregarMoto = new javax.swing.JTextField();
        TF_matricula_AgregarMoto = new javax.swing.JTextField();
        TF_CC_AgregarMoto = new javax.swing.JTextField();
        CB_color_AgregarMoto = new javax.swing.JComboBox<>();
        L_Precio_AgregarMoto = new javax.swing.JLabel();
        TF_precio_AgregarMoto = new javax.swing.JTextField();
        L_informacioGaraje_AgregarMoto = new javax.swing.JLabel();
        L_garaje_AgregarMoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        L_motosave_AgregarMoto.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        L_motosave_AgregarMoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_motosave_AgregarMoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Drawable/MotoSave.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(L_motosave_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 369, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(L_motosave_AgregarMoto, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );

        L_infoAgregar_AgregarMoto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        L_infoAgregar_AgregarMoto.setText("Introduce los siguientes datos:");

        L_matricula_AgregarMoto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        L_matricula_AgregarMoto.setText("Matricula:");

        L_marca_AgregarMoto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        L_marca_AgregarMoto.setText("Marca:");

        L_modelo_AgregarMoto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        L_modelo_AgregarMoto.setText("Modelo:");

        L_Color_AgregarMoto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        L_Color_AgregarMoto.setText("Color:");

        L_CC_AgregarMoto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        L_CC_AgregarMoto.setText("C.C:");

        B_volver_AgregarMoto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        B_volver_AgregarMoto.setText("Volver");
        B_volver_AgregarMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_volver_AgregarMotoActionPerformed(evt);
            }
        });

        B_Agregar_AgregarMoto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        B_Agregar_AgregarMoto.setText("Agregar");
        B_Agregar_AgregarMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Agregar_AgregarMotoActionPerformed(evt);
            }
        });

        TF_matricula_AgregarMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_matricula_AgregarMotoActionPerformed(evt);
            }
        });

        CB_color_AgregarMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_color_AgregarMotoActionPerformed(evt);
            }
        });

        L_Precio_AgregarMoto.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        L_Precio_AgregarMoto.setText("Precio:");

        L_informacioGaraje_AgregarMoto.setText("Vas a agregar una moto en el garaje:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(B_volver_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(B_Agregar_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(L_infoAgregar_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(L_CC_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L_Color_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L_modelo_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L_marca_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(L_matricula_AgregarMoto)
                                    .addComponent(L_Precio_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TF_precio_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TF_CC_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CB_color_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TF_matricula_AgregarMoto, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(TF_marca_AgregarMoto)
                                    .addComponent(TF_modelo_AgregarMoto)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(L_informacioGaraje_AgregarMoto)
                                .addGap(18, 18, 18)
                                .addComponent(L_garaje_AgregarMoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(L_garaje_AgregarMoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(L_informacioGaraje_AgregarMoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addComponent(L_infoAgregar_AgregarMoto)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_matricula_AgregarMoto)
                    .addComponent(TF_matricula_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_marca_AgregarMoto)
                    .addComponent(TF_marca_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_modelo_AgregarMoto)
                    .addComponent(TF_modelo_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_Color_AgregarMoto)
                    .addComponent(CB_color_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_CC_AgregarMoto)
                    .addComponent(TF_CC_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_Precio_AgregarMoto)
                    .addComponent(TF_precio_AgregarMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_volver_AgregarMoto)
                    .addComponent(B_Agregar_AgregarMoto))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CB_color_AgregarMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_color_AgregarMotoActionPerformed

    }//GEN-LAST:event_CB_color_AgregarMotoActionPerformed

    private void B_Agregar_AgregarMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Agregar_AgregarMotoActionPerformed
        String Marca = null;
        String Modelo = null;
        String Color = (String) CB_color_AgregarMoto.getSelectedItem();
        int cilindrada = 0;
        int precio = 0;

        if ((TF_matricula_AgregarMoto.getText().isBlank()) || (TF_matricula_AgregarMoto.getText() == null)) {
            JOptionPane.showMessageDialog(this, "La matricula no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Verificar si la matrícula cumple formato
        if (!INTERFACES.Entradable.validarMatricula(TF_matricula_AgregarMoto.getText())) {
            JOptionPane.showMessageDialog(this, "La matricula no cumple con el formato requerido. Ej: 1234ABC", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //controlar que la marca, el modelo no esten vacios
        if (TF_marca_AgregarMoto.getText().isBlank() || TF_modelo_AgregarMoto.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "La marca y el modelo no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Marca = TF_marca_AgregarMoto.getText();
        Modelo = TF_modelo_AgregarMoto.getText();

        if (TF_CC_AgregarMoto.getText().isBlank() && TF_precio_AgregarMoto.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "La CC y el precio no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Control CC y Precio
        if (INTERFACES.Entradable.esInt(TF_CC_AgregarMoto.getText()) && INTERFACES.Entradable.esInt(TF_precio_AgregarMoto.getText())) {
            cilindrada = Integer.parseInt(TF_CC_AgregarMoto.getText());
            precio = Integer.parseInt(TF_precio_AgregarMoto.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Solo puede introducir numeros en los campos 'CC' y 'Precio'.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Comprobar si la motocicleta ya existe
        if (motoDAO.buscarMoto(TF_matricula_AgregarMoto.getText()) == null) {
            JOptionPane.showMessageDialog(this, "La motocicleta ya existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        Motocicleta newMoto = new Motocicleta(idGaraje, TF_matricula_AgregarMoto.getText(), Marca, Modelo, Color, cilindrada, precio);
        // Guardamos la moto dependiendo del tipo de persistencia que se haya seleccionado en el login.
        if (tipoPers.equals(Enumerados.tipoDAO.JDBC_MOTOCICLETA.toString())) {
            if (motoDAO.altaMoto(newMoto)) {
                JOptionPane.showMessageDialog(this, "La motocicleta se agrego exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar la motocicleta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (serialMoto.altaMoto(newMoto)) {
                JOptionPane.showMessageDialog(this, "La motocicleta se agrego exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar la motocicleta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_B_Agregar_AgregarMotoActionPerformed

    private void B_volver_AgregarMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_volver_AgregarMotoActionPerformed
        this.dispose();
    }//GEN-LAST:event_B_volver_AgregarMotoActionPerformed

    private void TF_matricula_AgregarMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_matricula_AgregarMotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TF_matricula_AgregarMotoActionPerformed

    private void cargarColoresCB() {
        String[] colores = {"Rojo", "Azul", "Verde", "Negro", "Blanco", "Gris", "Amarillo"};
        for (String color : colores) {
            CB_color_AgregarMoto.addItem(color);
        }
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

    private void textoGaraje() {
        L_garaje_AgregarMoto.setText(String.valueOf(idGaraje).toUpperCase());
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
            java.util.logging.Logger.getLogger(AgregarMoto_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarMoto_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarMoto_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarMoto_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarMoto_Grafico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Agregar_AgregarMoto;
    private javax.swing.JButton B_volver_AgregarMoto;
    private javax.swing.JComboBox<String> CB_color_AgregarMoto;
    private javax.swing.JLabel L_CC_AgregarMoto;
    private javax.swing.JLabel L_Color_AgregarMoto;
    private javax.swing.JLabel L_Precio_AgregarMoto;
    private javax.swing.JLabel L_garaje_AgregarMoto;
    private javax.swing.JLabel L_infoAgregar_AgregarMoto;
    private javax.swing.JLabel L_informacioGaraje_AgregarMoto;
    private javax.swing.JLabel L_marca_AgregarMoto;
    private javax.swing.JLabel L_matricula_AgregarMoto;
    private javax.swing.JLabel L_modelo_AgregarMoto;
    private javax.swing.JLabel L_motosave_AgregarMoto;
    private javax.swing.JTextField TF_CC_AgregarMoto;
    private javax.swing.JTextField TF_marca_AgregarMoto;
    private javax.swing.JTextField TF_matricula_AgregarMoto;
    private javax.swing.JTextField TF_modelo_AgregarMoto;
    private javax.swing.JTextField TF_precio_AgregarMoto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
