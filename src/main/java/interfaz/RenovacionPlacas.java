/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import dominio.Automovil;
import dominio.CostoTramite;
import dominio.Persona;
import implementaciones.PersonaDAO;
import implementaciones.PlacaDAO;
import interfaces.IPersonaDAO;
import interfaces.IPlacaDAO;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.Validadores;

/**
 *
 * @author Jesus
 */
public class RenovacionPlacas extends javax.swing.JFrame {

    private final IPersonaDAO a = new PersonaDAO();
    private final CostoTramite b = new CostoTramite();
    private final IPlacaDAO d = new PlacaDAO();
    private static final Logger LOG = Logger.getLogger(PlacaDAO.class.getName());
    private final String rfc;
    private final Validadores validadores = new Validadores();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RenovacionPlacas.class.getName());

    public RenovacionPlacas() {
        initComponents();
        this.setLocationRelativeTo(null);

        this.rfc = null;
    }

    public RenovacionPlacas(String rfc) {
        initComponents();
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.rfc = rfc;
        this.insertarDatosPersona();
    }

    private void insertarDatosPersona() {
        Persona persona = this.a.buscarPersonasRFC(rfc);
        if (persona != null) {
            String nombreCompleto = persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno();
            logger.info("Persona cargada para renovación: " + nombreCompleto);

        }
    }

    private void configurarTablaAuto() {

        DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();
        modeloTabla.setRowCount(0);
    }

    private void cargarTablaAuto(String placa) {
        Persona persona = a.buscarPersonasRFC(rfc);
        try {

            List<Automovil> listaAuto = d.buscarPlacaAutomovilL(placa, persona);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();
            modeloTabla.setRowCount(0);

            if (listaAuto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontró el vehículo asociado a esa placa o no pertenece al RFC.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (Automovil auto : listaAuto) {
                Object[] fila = {
                    auto.getSerie(),
                    auto.getMarca(),
                    auto.getLinea(),
                    auto.getColor(),
                    auto.getModelo()
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al cargar el automóvil en la tabla: " + e.getMessage(), e);
            JOptionPane.showMessageDialog(this, "Ocurrió un error al buscar el vehículo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validaPlaca() {
        String placaTexto = txtPlaca.getText().trim();
        Persona persona = a.buscarPersonasRFC(rfc);

        if (placaTexto.isEmpty() || placaTexto.equals("   -   ")) {
            JOptionPane.showMessageDialog(this, "El campo de texto de placa está vacío", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Automovil auto = d.buscarPlacaAutomovil(placaTexto, persona);
        if (auto == null) {

            ((DefaultTableModel) jTable1.getModel()).setRowCount(0);
            JOptionPane.showMessageDialog(this, "La placa ingresada no ha sido encontrada en el sistema o no pertenece a tu RFC.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.cargarTablaAuto(placaTexto);
    }

    private void insertarPlaca() {
        String placaActual = txtPlaca.getText().trim();
        Persona persona = a.buscarPersonasRFC(rfc);
        Automovil auto = d.buscarPlacaAutomovil(placaActual, persona);

        if (auto == null) {
            JOptionPane.showMessageDialog(this, "No se pudo recuperar el automóvil. Intente buscar la placa de nuevo.", "Error de Renovación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            Double costo = b.placaUsado;

            String nuevaPlaca = d.generarPlaca();

            d.insertarTramitePlacasUsado(persona, auto, nuevaPlaca, costo);

            JOptionPane.showMessageDialog(this, "¡Renovación exitosa! Se ha renovado la placa para el vehiculo:\n"
                    + "Placa anterior: " + placaActual + "\n"
                    + "Placa nueva: " + nuevaPlaca + "\n"
                    + "No. Serie: " + auto.getSerie() + "\n"
                    + "Marca: " + auto.getMarca() + "\n"
                    + "Costo: $" + String.format("%.2f", costo),
                    "Renovación de Placas", JOptionPane.INFORMATION_MESSAGE);

            // Regresar a CostosPlacas después de una renovación exitosa
            lblAtrasMouseClicked(null);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error al insertar la nueva placa.", ex);
            JOptionPane.showMessageDialog(this, "Error al procesar la renovación: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Agrega los manejadores de mouse para simular la funcionalidad de botones
     * y el cambio de cursor.
     */
    private void agregarManejadoresMouse() {
        MouseAdapter handCursorAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        };

        lblAtras.addMouseListener(handCursorAdapter);
        lblContinuar.addMouseListener(handCursorAdapter);
        lblBuscar.addMouseListener(handCursorAdapter);
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
        lblAtras = new javax.swing.JLabel();
        lblContinuar = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        lblBuscar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Serie", "Marca", "Linea", "Color", "Modelo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 830, 290));

        lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtrasMouseClicked(evt);
            }
        });
        getContentPane().add(lblAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 90, 80));

        lblContinuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblContinuarMouseClicked(evt);
            }
        });
        getContentPane().add(lblContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 660, 210, 50));

        txtPlaca.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtPlaca.setBorder(null);
        getContentPane().add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 260, 40));

        lblBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBuscarMouseClicked(evt);
            }
        });
        getContentPane().add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, 200, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/RenovacionPlaca.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblContinuarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContinuarMouseClicked
        // Este evento reemplaza a botonAceptarActionPerformed
        String placaTexto = txtPlaca.getText().trim();
        Persona persona = a.buscarPersonasRFC(rfc);

        // Se valida la placa de nuevo antes de insertar
        if (placaTexto.isEmpty() || placaTexto.equals("   -   ")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una placa para continuar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (d.buscarPlacaAutomovil(placaTexto, persona) == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar y validar la placa antes de continuar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            this.insertarPlaca();
        }
    }//GEN-LAST:event_lblContinuarMouseClicked

    private void lblBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBuscarMouseClicked
        // Este evento reemplaza a btnBuscarActionPerformed
        this.validaPlaca();
    }//GEN-LAST:event_lblBuscarMouseClicked

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked
        // Este evento reemplaza a btnRegresarMouseClicked, regresando a CostosPlacas con el RFC
        CostosPlacas v = new CostosPlacas(rfc);
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblAtrasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAtras;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblContinuar;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
