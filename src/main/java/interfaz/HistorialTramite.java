/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import dominio.Automovil;
import dominio.Licencia;
import dominio.Persona;
import dominio.Placa;
import implementaciones.LicenciaDAO;
import implementaciones.PersonaDAO;
import implementaciones.PlacaDAO;
import implementaciones.VehiculoDAO;
import interfaces.ILicenciaDAO;
import interfaces.IPersonaDAO;
import interfaces.IPlacaDAO;
import interfaces.IVehiculoDAO;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import utils.ConfiguracionPaginado;
import interfaz.ConsultaPersona;

/**
 *
 * @author Usuario
 */
public class HistorialTramite extends javax.swing.JFrame {

    private final IPersonaDAO a = new PersonaDAO();
    private final ConfiguracionPaginado configPaginado;
    private static final Logger LOG = Logger.getLogger(PersonaDAO.class.getName());
    private final ILicenciaDAO b = new LicenciaDAO();
    private final IPlacaDAO c = new PlacaDAO();
    private final IVehiculoDAO d = new VehiculoDAO();
    private final String rfc;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HistorialTramite.class.getName());

    public HistorialTramite(String rfc) {
        initComponents();
        setResizable(false);
        this.configPaginado = new ConfiguracionPaginado(0, 3);
        this.rfc = rfc;
        this.insertarDatosPersona();
        this.cargarTablaLicencia();
        this.cargarTablaPlaca();
        this.cargarTablaVehiculo();
        setLocationRelativeTo(null);
    }

    public HistorialTramite() {
        initComponents();
        this.configPaginado = new ConfiguracionPaginado(0, 3);
        this.rfc = null;
        setLocationRelativeTo(null);
    }

    private void insertarDatosPersona() {
        if (rfc == null) {
            return;
        }
        Persona persona = this.a.buscarPersonasRFC(rfc);
        LOG.log(Level.INFO, "Cargando historial para: {0} {1} {2}",
                new Object[]{persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno()});
    }

    private void cargarTablaLicencia() {
        if (rfc == null) {
            return;
        }
        Persona persona = a.buscarPersonasRFC(rfc);
        try {
            List<Licencia> listaLicencia = b.consultaLicencias(configPaginado, persona);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();
            modeloTabla.setRowCount(0);
            for (Licencia licencia : listaLicencia) {
                Calendar fechaEmision = licencia.getFechaEmision();
                Date date = fechaEmision.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaE = sdf.format(date);
                Object[] fila = {
                    licencia.getTipoLicencia(),
                    licencia.getVigencia(),
                    licencia.getCosto(),
                    fechaE
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al cargar tabla de licencias: " + e.getMessage(), e);
        }
    }

    private void cargarTablaPlaca() {
        if (rfc == null) {
            return;
        }
        Persona persona = a.buscarPersonasRFC(rfc);
        try {
            List<Placa> listaPlaca = c.consultaPlacas(configPaginado, persona);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable2.getModel();
            modeloTabla.setRowCount(0);
            for (Placa placa : listaPlaca) {

                Object[] filaAdapter = {
                    placa.getPlaca(),
                    placa.getEstado(),
                    placa.getCosto(),
                    placa.getAutomovil().getSerie()
                };
                modeloTabla.addRow(filaAdapter);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al cargar tabla de placas: " + e.getMessage(), e);
        }
    }

    private void cargarTablaVehiculo() {
        if (rfc == null) {
            return;
        }
        Persona persona = a.buscarPersonasRFC(rfc);
        try {
            List<Automovil> listaAutomovil = d.consultaVehiculos(configPaginado, persona);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable3.getModel();
            modeloTabla.setRowCount(0);
            for (Automovil auto : listaAutomovil) {
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
            LOG.log(Level.SEVERE, "Error al cargar tabla de vehículos: " + e.getMessage(), e);
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnRetrocederLicencia = new javax.swing.JButton();
        btnRetrocederVehiculos = new javax.swing.JButton();
        lblAtras = new javax.swing.JLabel();
        btnAvanzarPlacas = new javax.swing.JButton();
        btnAvanzarVehiculos = new javax.swing.JButton();
        btnRetrocederPlacas = new javax.swing.JButton();
        btnAvanzarLicencia = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Num. Serie", "Marca", "Linea", "Color", "Modelo"
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
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, 1000, 150));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Placa", "Estado", "Costo", "Num. Serie Vehiculo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 1000, 150));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tipo", "Vigencia", "Costo", "Fecha de emision"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 1000, 130));

        btnRetrocederLicencia.setBackground(new java.awt.Color(168, 51, 99));
        btnRetrocederLicencia.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 10)); // NOI18N
        btnRetrocederLicencia.setText("Retroceder");
        btnRetrocederLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocederLicenciaActionPerformed(evt);
            }
        });
        getContentPane().add(btnRetrocederLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 260, -1, -1));

        btnRetrocederVehiculos.setBackground(new java.awt.Color(168, 51, 99));
        btnRetrocederVehiculos.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 10)); // NOI18N
        btnRetrocederVehiculos.setText("Retroceder");
        btnRetrocederVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocederVehiculosActionPerformed(evt);
            }
        });
        getContentPane().add(btnRetrocederVehiculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 670, -1, -1));

        lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtrasMouseClicked(evt);
            }
        });
        getContentPane().add(lblAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 80));

        btnAvanzarPlacas.setBackground(new java.awt.Color(168, 51, 99));
        btnAvanzarPlacas.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 10)); // NOI18N
        btnAvanzarPlacas.setText("Avanzar");
        btnAvanzarPlacas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarPlacasActionPerformed(evt);
            }
        });
        getContentPane().add(btnAvanzarPlacas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 440, 100, -1));

        btnAvanzarVehiculos.setBackground(new java.awt.Color(168, 51, 99));
        btnAvanzarVehiculos.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 10)); // NOI18N
        btnAvanzarVehiculos.setText("Avanzar");
        btnAvanzarVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarVehiculosActionPerformed(evt);
            }
        });
        getContentPane().add(btnAvanzarVehiculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 630, 100, -1));

        btnRetrocederPlacas.setBackground(new java.awt.Color(168, 51, 99));
        btnRetrocederPlacas.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 10)); // NOI18N
        btnRetrocederPlacas.setText("Retroceder");
        btnRetrocederPlacas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocederPlacasActionPerformed(evt);
            }
        });
        getContentPane().add(btnRetrocederPlacas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 480, -1, -1));

        btnAvanzarLicencia.setBackground(new java.awt.Color(168, 51, 99));
        btnAvanzarLicencia.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 10)); // NOI18N
        btnAvanzarLicencia.setText("Avanzar");
        btnAvanzarLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarLicenciaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAvanzarLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 220, 100, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/HistorialTramite.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Métodos de Paginación
     */
    private void avanzarPagina() {
        this.configPaginado.avanzarPagina();
        this.cargarTablaLicencia();
    }

    private void retrocederPagina() {
        this.configPaginado.retrocederPagina();
        this.cargarTablaLicencia();
    }

    private void avanzarPaginaP() {
        this.configPaginado.avanzarPagina();
        this.cargarTablaPlaca();
    }

    private void retrocederPaginaP() {
        this.configPaginado.retrocederPagina();
        this.cargarTablaPlaca();
    }

    private void avanzarPaginaV() {
        this.configPaginado.avanzarPagina();
        this.cargarTablaVehiculo();
    }

    private void retrocederPaginaV() {
        this.configPaginado.retrocederPagina();
        this.cargarTablaVehiculo();
    }
    private void btnAvanzarLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarLicenciaActionPerformed
        this.avanzarPagina();
    }//GEN-LAST:event_btnAvanzarLicenciaActionPerformed

    private void btnRetrocederLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrocederLicenciaActionPerformed
        this.retrocederPagina();
    }//GEN-LAST:event_btnRetrocederLicenciaActionPerformed

    private void btnAvanzarPlacasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarPlacasActionPerformed
        this.avanzarPaginaP();
    }//GEN-LAST:event_btnAvanzarPlacasActionPerformed

    private void btnRetrocederPlacasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrocederPlacasActionPerformed
        this.retrocederPaginaP();
    }//GEN-LAST:event_btnRetrocederPlacasActionPerformed

    private void btnAvanzarVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarVehiculosActionPerformed
        this.avanzarPaginaV();
    }//GEN-LAST:event_btnAvanzarVehiculosActionPerformed

    private void lblAtrasMouseEntered(java.awt.event.MouseEvent evt) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void lblAtrasMouseExited(java.awt.event.MouseEvent evt) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    private void btnRetrocederVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrocederVehiculosActionPerformed
        this.retrocederPaginaV();
    }//GEN-LAST:event_btnRetrocederVehiculosActionPerformed

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked

        ConsultaPersona ventanaAnterior = new ConsultaPersona();
        ventanaAnterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblAtrasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvanzarLicencia;
    private javax.swing.JButton btnAvanzarPlacas;
    private javax.swing.JButton btnAvanzarVehiculos;
    private javax.swing.JButton btnRetrocederLicencia;
    private javax.swing.JButton btnRetrocederPlacas;
    private javax.swing.JButton btnRetrocederVehiculos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lblAtras;
    // End of variables declaration//GEN-END:variables
}
