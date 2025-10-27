/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import dominio.Persona;
import implementaciones.PersonaDAO;
import interfaces.IPersonaDAO;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utils.Validadores;

/**
 *
 * @author Usuario
 */
public class ConsultaLicencia extends javax.swing.JFrame {

    private final IPersonaDAO a = new PersonaDAO();
    private final Validadores validadores = new Validadores();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ConsultaLicencia.class.getName());

    /**
     * Creates new form ConsultaLicencia
     */
    public ConsultaLicencia() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);

        txtNombre.setEditable(false);
        txtEdad.setEditable(false);
        txtFechaNacimiento.setEditable(false);
        txtTelefono.setEditable(false);

        this.validarOperacion();

        txtRFC.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                txtRFCKeyTyped(evt);
            }
        });
  
    }

    private void validarOperacion() {
        // Se valida si el campo de Nombre tiene contenido
        if (txtNombre.getText().isBlank()) {
            lblContinuar.setEnabled(false); // Simulación de deshabilitación de JLabel
        } else {
            lblContinuar.setEnabled(true);
        }
    }

    private Persona extraerDatosFormulario() {
        String RFC = txtRFC.getText();
        Persona persona = a.buscarPersonasRFC(RFC);
        return persona;
    }

    private void insertarDatospersona() {
        Persona persona = this.extraerDatosFormulario();

        if (persona == null) {
            JOptionPane.showMessageDialog(this, "No se encontró una persona con el RFC: " + txtRFC.getText(), "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);

            txtNombre.setText("");
            txtEdad.setText("");
            txtFechaNacimiento.setText("");
            txtTelefono.setText("");
            this.validarOperacion();
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
        txtEdad.setText(String.valueOf(edad));
    }

    private boolean validarEdad() {
        String eda = txtEdad.getText();
        if (eda.isEmpty()) {
            return false;
        }
        try {
            int edad = Integer.parseInt(eda);
            return edad >= 18;
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error al parsear edad.", e);
            return false;
        }
    }

    private void validarDatosPersona() {
        String rfc = txtRFC.getText();

        if (txtNombre.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Debe buscar y cargar los datos de la persona antes de continuar.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.validarEdad() == true) {

            CostosLicencia v = new CostosLicencia(rfc);
            v.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Esta persona no puede tramitar licencia por ser menor de edad (requiere 18+).", "Error de Edad", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validarDatosBuscar() {
        String rfc = txtRFC.getText();

        if (rfc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No ha ingresado un RFC a consultar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (validadores.validaRfc(rfc)) {
            JOptionPane.showMessageDialog(this, "El formato del RFC ingresado no es válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (a.validarPersonaRFC(rfc)) {
            this.insertarDatospersona();
            this.validarOperacion();
        } else {
            JOptionPane.showMessageDialog(this, "El RFC ingresado no corresponde a una persona registrada.", "Error de Búsqueda", JOptionPane.ERROR_MESSAGE);

            txtNombre.setText("");
            txtEdad.setText("");
            txtFechaNacimiento.setText("");
            txtTelefono.setText("");
            this.validarOperacion();
        }
    }

    private void txtRFCKeyTyped(java.awt.event.KeyEvent evt) {
        char car = evt.getKeyChar();
        String rfcActual = txtRFC.getText();

        if (!Character.isLetterOrDigit(car)) {
            evt.consume();
            return;
        }

        if (rfcActual.length() >= 13) {
            evt.consume();
        }
    }

    private void lblAtrasMouseEntered(java.awt.event.MouseEvent evt) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void lblAtrasMouseExited(java.awt.event.MouseEvent evt) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblContinuar = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        lblAtras = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtRFC = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblContinuar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblContinuarMouseClicked(evt);
            }
        });
        getContentPane().add(lblContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 710, 300, 50));

        lblBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBuscarMouseClicked(evt);
            }
        });
        getContentPane().add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 270, 150, 50));

        lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtrasMouseClicked(evt);
            }
        });
        getContentPane().add(lblAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, 70));

        txtFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaNacimiento.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtFechaNacimiento.setBorder(null);
        getContentPane().add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 170, 60));

        txtEdad.setBackground(new java.awt.Color(255, 255, 255));
        txtEdad.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtEdad.setBorder(null);
        getContentPane().add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 430, 70));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtNombre.setBorder(null);
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 400, 60));

        txtRFC.setBackground(new java.awt.Color(255, 255, 255));
        txtRFC.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtRFC.setBorder(null);
        getContentPane().add(txtRFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 440, 60));

        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        txtTelefono.setBorder(null);
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 570, 350, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/ConsultaLicencia.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked

        Aplicacion v = new Aplicacion();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblAtrasMouseClicked

    private void lblContinuarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblContinuarMouseClicked

        if (lblContinuar.isEnabled()) {
            this.validarDatosPersona();
        } else {
            JOptionPane.showMessageDialog(this, "Busque un RFC válido y cargue los datos antes de continuar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_lblContinuarMouseClicked

    private void lblBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBuscarMouseClicked
        this.validarDatosBuscar();
    }//GEN-LAST:event_lblBuscarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAtras;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblContinuar;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
