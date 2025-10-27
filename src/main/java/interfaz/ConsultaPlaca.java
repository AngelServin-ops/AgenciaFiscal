/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import dominio.Persona;
import implementaciones.LicenciaDAO;
import implementaciones.PersonaDAO;
import interfaces.ILicenciaDAO;
import interfaces.IPersonaDAO;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Validadores;

/**
 *
 * @author Jesus
 */
public class ConsultaPlaca extends javax.swing.JFrame {

    private final IPersonaDAO a = new PersonaDAO();
    private final ILicenciaDAO b = new LicenciaDAO();
    private final Validadores validadores = new Validadores();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ConsultaPlaca.class.getName());

    /**
     * Creates new form ConsultaPlaca
     */
    public ConsultaPlaca() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);

        txtNombre.setEditable(false);
        txtEdad1.setEditable(false);
        txtFechaNacimiento.setEditable(false);
        txtTelefono.setEditable(false);

        this.validadOperacion();

        txtRFC2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                txtRFC2KeyTyped(evt);
            }
        });
    }

    private void validadOperacion() {

        if (txtNombre.getText().isBlank()) {

            txtNombre.setText("");
            txtEdad1.setText("");
            txtFechaNacimiento.setText("");
            txtTelefono.setText("");
        }
    }

    private Persona extraerDatosFormulario() {
        String RFC = txtRFC2.getText();

        Persona persona = a.buscarPersonasRFC(RFC);
        return persona;
    }

    private void insertarDatospersona(Persona persona) {
        if (persona == null) {
            return;
        }

        txtNombre.setText(persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno());

        txtTelefono.setText(persona.getTelefono());

        Calendar fechaNacimiento = persona.getFechaNacimiento();
        Date date = fechaNacimiento.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaN = sdf.format(date);

        txtFechaNacimiento.setText(fechaN);

        LocalDate fechaNacimientoLocal = LocalDate.of(fechaNacimiento.get(Calendar.YEAR),
                fechaNacimiento.get(Calendar.MONTH) + 1,
                fechaNacimiento.get(Calendar.DAY_OF_MONTH));
        LocalDate fechaActual = LocalDate.now();
        int edad = Period.between(fechaNacimientoLocal, fechaActual).getYears();

        txtEdad1.setText(String.valueOf(edad));

        boolean licenciaVigente = b.validarLicenciaVigente(persona.getRfc());
        
        logger.info("Estado de Licencia: " + (licenciaVigente ? "Vigente" : "No vigente"));
    }

    private boolean validarEdad() {
        String eda = txtEdad1.getText(); 
        if (eda.isEmpty()) {
            return false;
        }
        try {
            int edad = Integer.parseInt(eda);
            return edad >= 18;
        } catch (NumberFormatException e) {
            logger.severe("Error al parsear edad: " + e.getMessage());
            return false;
        }
    }

    private void validaDatosPersona() {
        String rfc = txtRFC2.getText();

        if (rfc.isEmpty() || !a.validarPersonaRFC(rfc)) {
            JOptionPane.showMessageDialog(this, "Debe buscar y validar un RFC válido antes de continuar.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.validarEdad() == true) {
            if (b.validarLicenciaVigente(rfc) == true) {

                CostosPlacas v = new CostosPlacas(rfc);
                v.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No tiene o no se encontró una licencia vigente para el RFC: " + rfc, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Esta persona no puede tramitar placas por ser menor de edad.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validaDatosBuscar() {
        String rfc = txtRFC2.getText();

        if (rfc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No ha ingresado un RFC a consultar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (validadores.validaRfc(rfc)) {
            JOptionPane.showMessageDialog(this, "El formato del RFC ingresado es inválido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Persona persona = a.buscarPersonasRFC(rfc);

        if (persona != null) {
            this.insertarDatospersona(persona);
            this.validadOperacion();
            JOptionPane.showMessageDialog(this, "Datos de la persona cargados correctamente.", "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {

            if (a.validarPersonaRFC(rfc) == false) {
                JOptionPane.showMessageDialog(this, "El RFC ingresado no es válido o no corresponde a una persona registrada.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró una persona con el RFC: " + rfc, "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);
            }

            txtNombre.setText("");
            txtEdad1.setText("");
            txtFechaNacimiento.setText("");
            txtTelefono.setText("");
        }
    }

    private void txtRFC2KeyTyped(java.awt.event.KeyEvent evt) {
        char car = evt.getKeyChar();
        String rfcActual = txtRFC2.getText();

        if (!Character.isLetterOrDigit(car)) {
            evt.consume();
            return;
        }

        if (rfcActual.length() >= 13) {
            evt.consume();
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

        txtFechaNacimiento = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lblAtras = new javax.swing.JLabel();
        lblContinuar = new javax.swing.JLabel();
        txtEdad1 = new javax.swing.JTextField();
        lblBuscar = new javax.swing.JLabel();
        txtRFC2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFechaNacimiento.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtFechaNacimiento.setBorder(null);
        txtFechaNacimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFechaNacimientoMouseClicked(evt);
            }
        });
        getContentPane().add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, 290, 60));

        txtTelefono.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtTelefono.setBorder(null);
        txtTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTelefonoMouseClicked(evt);
            }
        });
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 530, 490, 50));

        txtNombre.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtNombre.setBorder(null);
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreMouseClicked(evt);
            }
        });
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 510, 60));

        lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtrasMouseClicked(evt);
            }
        });
        getContentPane().add(lblAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, 70));

        lblContinuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblContinuarMouseClicked(evt);
            }
        });
        getContentPane().add(lblContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 700, 310, 60));

        txtEdad1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtEdad1.setBorder(null);
        txtEdad1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEdad1MouseClicked(evt);
            }
        });
        getContentPane().add(txtEdad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 510, 60));

        lblBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBuscarMouseClicked(evt);
            }
        });
        getContentPane().add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, 180, 60));

        txtRFC2.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtRFC2.setBorder(null);
        txtRFC2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRFC2MouseClicked(evt);
            }
        });
        getContentPane().add(txtRFC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 510, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/ConsultaPlaca.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblContinuarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContinuarMouseClicked
        this.validaDatosPersona();
    }//GEN-LAST:event_lblContinuarMouseClicked

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked
        Aplicacion v = new Aplicacion();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblAtrasMouseClicked

    private void lblAtrasMouseEntered(java.awt.event.MouseEvent evt) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void lblAtrasMouseExited(java.awt.event.MouseEvent evt) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtTelefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTelefonoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoMouseClicked

    private void txtFechaNacimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaNacimientoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoMouseClicked

    private void txtEdad1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEdad1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEdad1MouseClicked

    private void txtNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMouseClicked

    private void txtRFC2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRFC2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRFC2MouseClicked

    private void lblBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBuscarMouseClicked
        this.validaDatosBuscar();
    }//GEN-LAST:event_lblBuscarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAtras;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblContinuar;
    private javax.swing.JTextField txtEdad1;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRFC2;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
