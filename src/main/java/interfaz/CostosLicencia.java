/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import dominio.CostoTramite;
import dominio.Persona;
import implementaciones.LicenciaDAO;
import implementaciones.PersonaDAO;
import interfaces.ILicenciaDAO;
import interfaces.IPersonaDAO;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CostosLicencia extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CostosLicencia.class.getName());

    private final IPersonaDAO a = new PersonaDAO();

    private final CostoTramite b = new CostoTramite();
    private final ILicenciaDAO c = new LicenciaDAO();

    private String rfc;

    public CostosLicencia(String rfc) {
        this.rfc = rfc;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        this.inicializarComponentesPersonalizados();
        this.insertarDatosPersona();
        this.insertarDatosTablaCosto();
    }

    /**
     * Creates new form CostosLicencia
     */
    public CostosLicencia() {

        this.rfc = null;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);

    }

    private void inicializarComponentesPersonalizados() {

        cmbVigencia.setModel(new DefaultComboBoxModel<>(new String[]{"1", "2", "3"}));
        cmbVigencia.setSelectedIndex(0);

        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Vigencia", "Costo Normal", "Costo Discapacitado"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(model);

        lblAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        lblSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void insertarDatosPersona() {
        if (this.rfc == null) {
            return;
        }

        Persona persona = this.a.buscarPersonasRFC(rfc);

    }

    private void insertarDatosTablaCosto() {
        DefaultTableModel tblCosto = (DefaultTableModel) jTable1.getModel();
        // Limpiar filas existentes para evitar duplicados
        tblCosto.setRowCount(0);

        // Insertar los costos desde la clase CostoTramite (b)
        tblCosto.addRow(new Object[]{"1 año", b.licencia1N, b.licencia1D});
        tblCosto.addRow(new Object[]{"2 años", b.licencia2N, b.licencia2D});
        tblCosto.addRow(new Object[]{"3 años", b.licencia3N, b.licencia3D});
    }

    private void procesarTramiteLicencia() {
        if (this.rfc == null) {
            JOptionPane.showMessageDialog(this, "Error: El RFC de la persona no está disponible.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Persona persona = this.a.buscarPersonasRFC(rfc);
        if (persona == null) {
            JOptionPane.showMessageDialog(this, "Error: No se pudo encontrar a la persona con el RFC: " + rfc, "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String opcionVigenciaStr = (String) cmbVigencia.getSelectedItem();
        int vigencia = Integer.parseInt(opcionVigenciaStr);

        int estadoDiscapacidad = 0;
        String estado = "NORMAL";
        Double costo = 0.0;

        if (jcbDiscapacidad.isSelected()) {
            estadoDiscapacidad = 1;
            estado = "DISCAPACITADO";
        }

        switch (vigencia) {
            case 1:
                costo = (estadoDiscapacidad == 1) ? b.licencia1D : b.licencia1N;
                break;
            case 2:
                costo = (estadoDiscapacidad == 1) ? b.licencia2D : b.licencia2N;
                break;
            case 3:
                costo = (estadoDiscapacidad == 1) ? b.licencia3D : b.licencia3N;
                break;
            default:
                JOptionPane.showMessageDialog(this, "Error: Vigencia seleccionada no válida.", "Error de Lógica", JOptionPane.ERROR_MESSAGE);
                return;
        }

        c.insertarTramiteLicencia(persona, costo, vigencia, estadoDiscapacidad);

        JOptionPane.showMessageDialog(this,
                "Se ha generado una licencia con estos datos:\n"
                + "Persona: " + persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno() + "\n"
                + "Vigencia (años): " + vigencia + "\n"
                + "Tipo: " + estado + "\n"
                + "Costo: $" + String.format("%.2f", costo),
                "Trámite de Licencia Exitoso",
                JOptionPane.INFORMATION_MESSAGE);

        Aplicacion v = new Aplicacion();
        v.setVisible(true);
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAtras = new javax.swing.JLabel();
        lblSiguiente = new javax.swing.JLabel();
        cmbVigencia = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jcbDiscapacidad = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtrasMouseClicked(evt);
            }
        });
        getContentPane().add(lblAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 100, 80));

        lblSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSiguienteMouseClicked(evt);
            }
        });
        getContentPane().add(lblSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 680, 210, 50));

        cmbVigencia.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        cmbVigencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbVigencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVigenciaActionPerformed(evt);
            }
        });
        getContentPane().add(cmbVigencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 140, 60));

        jScrollPane1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Vigencia", "Costo Normal", "Costo Discapacitado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        jScrollPane2.setViewportView(jScrollPane1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 1000, 260));
        getContentPane().add(jcbDiscapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 20, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/CostosLicencias.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSiguienteMouseClicked
        this.procesarTramiteLicencia();
    }//GEN-LAST:event_lblSiguienteMouseClicked

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked

        ConsultaLicencia v = new ConsultaLicencia();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblAtrasMouseClicked

    private void cmbVigenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVigenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVigenciaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbVigencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox jcbDiscapacidad;
    private javax.swing.JLabel lblAtras;
    private javax.swing.JLabel lblSiguiente;
    // End of variables declaration//GEN-END:variables
}
