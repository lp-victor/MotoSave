/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MotoSaveMain;

import AccesoDatos.JDBC.JDBCGarajeDAO;
import AccesoDatos.JDBC.JDBCMotocicletaDAO;
import INTERFACES.AgregarMoto_Grafico;
import INTERFACES.ConexionBBDD;
import Modelo.Garaje;
import Modelo.Motocicleta;
import Modelo.MotocicletaExcepcion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Inicio_Grafico extends javax.swing.JFrame {

    //Movimiento JPanel
    private static int mouseX, mouseY;
    private static boolean mousePressed;
    // Atributos clase.
    private ConexionBBDD conexionBD = new ConexionBBDD();
    private JDBCGarajeDAO garajeAux = new JDBCGarajeDAO(conexionBD.getCon());
    private JDBCMotocicletaDAO motoAux = new JDBCMotocicletaDAO(conexionBD.getCon());
    //Interfaces
    private AgregarMoto_Grafico agregarMoto_F = new AgregarMoto_Grafico();
    /**
     * Creates new form Inicio_Grafico
     */
    public Inicio_Grafico() {
        initComponents();
        llenarCBGaraje();
        llenarCBMotos();
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

        jScrollPane2 = new javax.swing.JScrollPane();
        T_infoMotos_Inicio = new javax.swing.JTable();
        P_panelUsuario_Incio = new javax.swing.JPanel();
        B_agregar_Inicio = new javax.swing.JButton();
        TF_introMatricula_Inicio = new javax.swing.JTextField();
        CB_garajes_Inicio = new javax.swing.JComboBox<>();
        B_salir_Inicio = new javax.swing.JButton();
        B_buscar_Inicio = new javax.swing.JButton();
        B_listar_Inicio = new javax.swing.JButton();
        CB_motos_Inicio = new javax.swing.JComboBox<>();
        B_eliminar_Inicio = new javax.swing.JButton();
        L_seleccionarMoto_Inicio = new javax.swing.JLabel();
        L_introduceMatricula_Inicio = new javax.swing.JLabel();
        L_seleccionaGaraje_Inicio = new javax.swing.JLabel();
        B_modificarMoto_Inicio = new javax.swing.JButton();
        S_separador_Inicio = new javax.swing.JSeparator();
        L_motosave_Inicio = new javax.swing.JLabel();
        L_T_nombreGaraje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        T_infoMotos_Inicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Matricula", "Marca", "Modelo", "Color", "Cilindrada CC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(T_infoMotos_Inicio);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 470, 470));

        P_panelUsuario_Incio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        B_agregar_Inicio.setText("Agregar Motocicleta");
        B_agregar_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_agregar_InicioActionPerformed(evt);
            }
        });

        TF_introMatricula_Inicio.setName(""); // NOI18N
        TF_introMatricula_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TF_introMatricula_InicioActionPerformed(evt);
            }
        });

        CB_garajes_Inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CB_garajes_InicioMouseClicked(evt);
            }
        });
        CB_garajes_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_garajes_InicioActionPerformed(evt);
            }
        });

        B_salir_Inicio.setText("SALIR");
        B_salir_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_salir_InicioActionPerformed(evt);
            }
        });

        B_buscar_Inicio.setText("Buscar Motocicleta");
        B_buscar_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_buscar_InicioActionPerformed(evt);
            }
        });

        B_listar_Inicio.setText("Listar Motocicletas");
        B_listar_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_listar_InicioActionPerformed(evt);
            }
        });

        CB_motos_Inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CB_motos_InicioMouseClicked(evt);
            }
        });

        B_eliminar_Inicio.setText("Eliminar Motocicleta");
        B_eliminar_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_eliminar_InicioActionPerformed(evt);
            }
        });

        L_seleccionarMoto_Inicio.setText("Selecciona motocicleta:");

        L_introduceMatricula_Inicio.setText("Introduce Matricula:");

        L_seleccionaGaraje_Inicio.setText("Selecciona garaje:");

        B_modificarMoto_Inicio.setText("Modificar Motocicleta");

        S_separador_Inicio.setForeground(new java.awt.Color(204, 204, 204));

        L_motosave_Inicio.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        L_motosave_Inicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_motosave_Inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Drawable/MotoSave.png"))); // NOI18N

        javax.swing.GroupLayout P_panelUsuario_IncioLayout = new javax.swing.GroupLayout(P_panelUsuario_Incio);
        P_panelUsuario_Incio.setLayout(P_panelUsuario_IncioLayout);
        P_panelUsuario_IncioLayout.setHorizontalGroup(
            P_panelUsuario_IncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P_panelUsuario_IncioLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(P_panelUsuario_IncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B_modificarMoto_Inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B_agregar_Inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B_listar_Inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_panelUsuario_IncioLayout.createSequentialGroup()
                        .addComponent(CB_garajes_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(P_panelUsuario_IncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(L_seleccionarMoto_Inicio)
                            .addComponent(CB_motos_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(P_panelUsuario_IncioLayout.createSequentialGroup()
                        .addComponent(L_seleccionaGaraje_Inicio)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(B_eliminar_Inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(62, 62, 62))
            .addGroup(P_panelUsuario_IncioLayout.createSequentialGroup()
                .addGroup(P_panelUsuario_IncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P_panelUsuario_IncioLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(B_buscar_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(P_panelUsuario_IncioLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(L_introduceMatricula_Inicio)
                        .addGap(44, 44, 44)
                        .addComponent(TF_introMatricula_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(P_panelUsuario_IncioLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(S_separador_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(P_panelUsuario_IncioLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(B_salir_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addComponent(L_motosave_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        P_panelUsuario_IncioLayout.setVerticalGroup(
            P_panelUsuario_IncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_panelUsuario_IncioLayout.createSequentialGroup()
                .addComponent(L_motosave_Inicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(P_panelUsuario_IncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_introMatricula_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(L_introduceMatricula_Inicio))
                .addGap(18, 18, 18)
                .addComponent(B_buscar_Inicio)
                .addGap(28, 28, 28)
                .addComponent(S_separador_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(P_panelUsuario_IncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L_seleccionaGaraje_Inicio)
                    .addComponent(L_seleccionarMoto_Inicio))
                .addGap(16, 16, 16)
                .addGroup(P_panelUsuario_IncioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_garajes_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CB_motos_Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(B_listar_Inicio)
                .addGap(18, 18, 18)
                .addComponent(B_agregar_Inicio)
                .addGap(18, 18, 18)
                .addComponent(B_modificarMoto_Inicio)
                .addGap(18, 18, 18)
                .addComponent(B_eliminar_Inicio)
                .addGap(33, 33, 33)
                .addComponent(B_salir_Inicio)
                .addGap(36, 36, 36))
        );

        getContentPane().add(P_panelUsuario_Incio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 540));

        L_T_nombreGaraje.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        L_T_nombreGaraje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_T_nombreGaraje.setText("Garaje Nombre");
        getContentPane().add(L_T_nombreGaraje, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 260, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void B_agregar_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_agregar_InicioActionPerformed
        abrirAgregarMoto_grafico();
    }//GEN-LAST:event_B_agregar_InicioActionPerformed

    private void TF_introMatricula_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TF_introMatricula_InicioActionPerformed
        B_buscar_InicioActionPerformed(evt);
    }//GEN-LAST:event_TF_introMatricula_InicioActionPerformed

    private void B_buscar_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_buscar_InicioActionPerformed
        DefaultTableModel modeloTabla = estructuraTabla();

        Motocicleta moto = motoAux.buscarMoto(TF_introMatricula_Inicio.getText());
        String matricula = moto.getMatricula();
        String marca = moto.getMarca();
        String modelo = moto.getModelo();
        String color = moto.getColor();
        String cc = String.valueOf(moto.getCC());
        //Salida
        modeloTabla.addRow(new Object[]{matricula, marca, modelo, color, cc});
        T_infoMotos_Inicio.setModel(modeloTabla);
        L_T_nombreGaraje.setText(garajeAux.buscarGaraje(moto.getIdGaraje()).getNombreGaraje());

    }//GEN-LAST:event_B_buscar_InicioActionPerformed

    private void B_listar_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_listar_InicioActionPerformed
        DefaultTableModel modeloTabla = estructuraTabla();

        try {
            int aux = garajeAux.buscarIdGaraje(String.valueOf(CB_garajes_Inicio.getSelectedItem()));
            for (Motocicleta moto : motoAux.listarMotocicletasGaraje(aux)) {
                String matricula = moto.getMatricula();
                String marca = moto.getMarca();
                String modelo = moto.getModelo();
                String color = moto.getColor();
                String cc = String.valueOf(moto.getCC());

                modeloTabla.addRow(new Object[]{matricula, marca, modelo, color, cc});
            }
            L_T_nombreGaraje.setText(garajeAux.buscarGaraje(aux).getNombreGaraje());
            T_infoMotos_Inicio.setModel(modeloTabla);
        } catch (MotocicletaExcepcion ex) {
            JOptionPane.showMessageDialog(this, "No hay Motocicletas en este Garaje");
        }
    }//GEN-LAST:event_B_listar_InicioActionPerformed

    private void CB_garajes_InicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CB_garajes_InicioMouseClicked

    }//GEN-LAST:event_CB_garajes_InicioMouseClicked

    private void CB_motos_InicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CB_motos_InicioMouseClicked

    }//GEN-LAST:event_CB_motos_InicioMouseClicked

    private void CB_garajes_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_garajes_InicioActionPerformed
        actualizarCBMotos();
    }//GEN-LAST:event_CB_garajes_InicioActionPerformed

    private void B_salir_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_salir_InicioActionPerformed
        conexionBD.desconectarBBDD();
        this.dispose();;
    }//GEN-LAST:event_B_salir_InicioActionPerformed

    private void B_eliminar_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_eliminar_InicioActionPerformed
        // Hacer la doble confirmacion.
        
        String [] motoSeleccionada = (String.valueOf(CB_motos_Inicio.getSelectedItem())).split("-");
        motoAux.bajaMoto(motoSeleccionada[1]);
        
        actualizarCBMotos();
    }//GEN-LAST:event_B_eliminar_InicioActionPerformed
    
    public void abrirAgregarMoto_grafico() {
        int aux = garajeAux.buscarIdGaraje(String.valueOf(CB_garajes_Inicio.getSelectedItem()));
        agregarMoto_F = new AgregarMoto_Grafico(aux, conexionBD.getCon());
        agregarMoto_F.setVisible(true);
        agregarMoto_F.pack();
        agregarMoto_F.setLocationRelativeTo(this);
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

    private DefaultTableModel estructuraTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Matrícula");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Color");
        modeloTabla.addColumn("Cilindrada (cc)");

        return modeloTabla;
    }

    private void llenarCBGaraje() {
        for (Garaje garaje : garajeAux.listarGaraje()) {
            CB_garajes_Inicio.addItem(garaje.getNombreGaraje());
        }
    }

    private void llenarCBMotos() {
        CB_motos_Inicio.removeAllItems();
        try {
            int aux = garajeAux.buscarIdGaraje(String.valueOf(CB_garajes_Inicio.getSelectedItem()));
            for (Motocicleta moto : motoAux.listarMotocicletasGaraje(aux)) {
                CB_motos_Inicio.addItem(moto.getMarca() + "-" + moto.getMatricula());
            }
        } catch (MotocicletaExcepcion ex) {
            JOptionPane.showMessageDialog(this, "No hay Motocicletas en este Garaje");
        }
    }

    public void actualizarCBMotos() {
        CB_motos_Inicio.removeAllItems();
        String garajeSeleccionado = (String) CB_garajes_Inicio.getSelectedItem();
        if (garajeSeleccionado != null) {
            llenarCBMotos();
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
            java.util.logging.Logger.getLogger(Inicio_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio_Grafico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio_Grafico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_agregar_Inicio;
    private javax.swing.JButton B_buscar_Inicio;
    private javax.swing.JButton B_eliminar_Inicio;
    private javax.swing.JButton B_listar_Inicio;
    private javax.swing.JButton B_modificarMoto_Inicio;
    private javax.swing.JButton B_salir_Inicio;
    private javax.swing.JComboBox<String> CB_garajes_Inicio;
    private javax.swing.JComboBox<String> CB_motos_Inicio;
    private javax.swing.JLabel L_T_nombreGaraje;
    private javax.swing.JLabel L_introduceMatricula_Inicio;
    private javax.swing.JLabel L_motosave_Inicio;
    private javax.swing.JLabel L_seleccionaGaraje_Inicio;
    private javax.swing.JLabel L_seleccionarMoto_Inicio;
    private javax.swing.JPanel P_panelUsuario_Incio;
    private javax.swing.JSeparator S_separador_Inicio;
    private javax.swing.JTextField TF_introMatricula_Inicio;
    private javax.swing.JTable T_infoMotos_Inicio;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
