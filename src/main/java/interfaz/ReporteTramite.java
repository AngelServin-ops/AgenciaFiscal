/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import com.github.lgooddatepicker.components.DatePicker;
import dominio.Persona;
import dominio.TipoTramite;
import dominio.Tramite;
import implementaciones.PersonaDAO;
import implementaciones.TramiteDAO;
import interfaces.IPersonaDAO;
import interfaces.ITramiteDAO;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utils.ConfiguracionPaginado;

/**
 *
 * @author Usuario
 */
public class ReporteTramite extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReporteTramite.class.getName());

    private final IPersonaDAO a = new PersonaDAO();
    private final ConfiguracionPaginado configPaginado;
    private static final Logger LOG = Logger.getLogger(ReporteTramite.class.getName());
    private final ITramiteDAO b = new TramiteDAO();
    private final String rfc;

    public ReporteTramite(String rfc) {
        initComponents();
        setResizable(false);

        this.configPaginado = new ConfiguracionPaginado(0, 5);
        this.rfc = rfc;

        this.insertarDatosPersona();

        LocalDate fechaActual = LocalDate.now();
        LocalDate minFecha = LocalDate.of(1900, 01, 01);
        dpHasta.getSettings().setDateRangeLimits(minFecha, fechaActual);

        if (dpDesde.getComponentDateTextField() != null) {
            dpDesde.getComponentDateTextField().setEnabled(false);
        }
        if (dpHasta.getComponentDateTextField() != null) {
            dpHasta.getComponentDateTextField().setEnabled(false);
        }

    }

    public ReporteTramite() {
        this.rfc = null;
        this.configPaginado = new ConfiguracionPaginado(0, 5);
        initComponents();
        setLocationRelativeTo(null);
    }

    private void insertarDatosPersona() {
        try {
            Persona persona = this.a.buscarPersonasRFC(rfc);

            this.setTitle("Módulo de reportes: Generar reporte para " + persona.getNombre());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al cargar datos de la persona con RFC: " + rfc, e);
        }
    }

    private Date sacarFechaDesde() {
        LocalDate fechaD = this.dpDesde.getDate();
        if (fechaD == null) {
            return null;
        }
        Date fechaDesde = new Date(fechaD.getYear() - 1900, fechaD.getMonthValue() - 1, fechaD.getDayOfMonth());
        return fechaDesde;
    }

    private Date sacarFechaHasta() {
        LocalDate fechaH = this.dpHasta.getDate();
        if (fechaH == null) {
            return null;
        }
        Date fechaHasta = new Date(fechaH.getYear() - 1900, fechaH.getMonthValue() - 1, fechaH.getDayOfMonth());
        return fechaHasta;
    }

    private TipoTramite tipo() {
        String opcion = (String) cbTramite.getSelectedItem();
        TipoTramite tipo;
        if (opcion != null && opcion.equals("Licencia")) {
            tipo = TipoTramite.Licencia;
        } else {
            tipo = TipoTramite.Placa;
        }
        return tipo;
    }

    private void cargarTablaLicencia() {
        if (rfc == null) {
            JOptionPane.showMessageDialog(this, "RFC no definido. No se puede cargar la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Persona persona = a.buscarPersonasRFC(rfc);
        try {
            List<Tramite> listaTramite = b.buscarTramites(configPaginado, persona, this.tipo(), this.sacarFechaDesde(), this.sacarFechaHasta());

            DefaultTableModel modeloTabla = (DefaultTableModel) this.jTable1.getModel();
            modeloTabla.setRowCount(0);

            if (listaTramite == null || listaTramite.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron trámites para los parámetros dados.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (Tramite tramite : listaTramite) {
                Calendar fechaNacimiento = tramite.getFechaEmision();
                Date date = fechaNacimiento.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaE = sdf.format(date);
                Object[] fila = {
                    tramite.getTipo(),
                    tramite.getCosto(),
                    fechaE
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al cargar la tabla de trámites: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generacionReporte() {
        if (rfc == null) {
            JOptionPane.showMessageDialog(this, "RFC no definido. No se puede generar el reporte.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Persona persona = a.buscarPersonasRFC(rfc);

            ConfiguracionPaginado configTotal = new ConfiguracionPaginado(0, Integer.MAX_VALUE);
            List<Tramite> listaTramite = b.buscarTramites(configTotal, persona, this.tipo(), this.sacarFechaDesde(), this.sacarFechaHasta());

            if (listaTramite == null || listaTramite.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay trámites para generar el reporte.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile("src/main/resources/reports/ReporteAgenciaFiscal.jasper");
            List<Map<String, Object>> listaParametros = new ArrayList<>();

            for (Tramite tramite : listaTramite) {
                Calendar fechaNacimiento = tramite.getFechaEmision();
                Date date = fechaNacimiento.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaE = sdf.format(date);
                HashMap<String, Object> parametro = new HashMap<>();
                parametro.put("nombrePersona", persona.getNombre() + " " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno());
                parametro.put("tipoTramite", tramite.getTipo().toString());
                parametro.put("costo", tramite.getCosto());
                parametro.put("fechaEmision", fechaE);
                listaParametros.add(parametro);
            }
            JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(listaParametros);
            JasperPrint informe = JasperFillManager.fillReport(jr, null, datos);
            JasperViewer jv = new JasperViewer(informe, false);
            jv.setVisible(true);
        } catch (JRException e) {
            LOG.log(Level.SEVERE, "Error al generar el reporte", e);
            JOptionPane.showMessageDialog(this, "Error al generar el reporte PDF: " + e.getMessage(), "Error de Reporte", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al buscar trámites para el reporte", e);
            JOptionPane.showMessageDialog(this, "Error al buscar trámites para el reporte.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validaBusqueda() {
        if (rfc == null) {
            return;
        }
        Persona persona = a.buscarPersonasRFC(rfc);

        ConfiguracionPaginado configCheck = new ConfiguracionPaginado(0, 1);

        List<Tramite> resultados = b.buscarTramites(configCheck, persona, this.tipo(), this.sacarFechaDesde(), this.sacarFechaHasta());

        if (resultados == null || resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha encontrado trámites con los parámetros buscados", "ERROR", JOptionPane.ERROR_MESSAGE);
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
        lblBuscar = new javax.swing.JLabel();
        lblAtras = new javax.swing.JLabel();
        cbTramite = new javax.swing.JComboBox<>();
        dpHasta = new com.github.lgooddatepicker.components.DatePicker();
        lblGenerarReporte = new javax.swing.JLabel();
        dpDesde = new com.github.lgooddatepicker.components.DatePicker();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tipo", "Costo", "Fecha Emisión"
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
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 980, 260));

        lblBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBuscarMouseClicked(evt);
            }
        });
        getContentPane().add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 190, 70, 60));

        lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAtrasMouseClicked(evt);
            }
        });
        getContentPane().add(lblAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, 70));

        cbTramite.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Licencia", "Placa" }));
        cbTramite.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        cbTramite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTramiteActionPerformed(evt);
            }
        });
        getContentPane().add(cbTramite, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 310, 60));
        getContentPane().add(dpHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, 60));

        lblGenerarReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGenerarReporteMouseClicked(evt);
            }
        });
        getContentPane().add(lblGenerarReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 680, 250, 80));
        getContentPane().add(dpDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 190, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/ReporteTramite.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblGenerarReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGenerarReporteMouseClicked
        if (rfc == null) {
            JOptionPane.showMessageDialog(this, "El RFC de la persona no está disponible.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dpDesde.getDate() == null) {
            JOptionPane.showMessageDialog(null, "No se ha ingresado una fecha desde", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (dpHasta.getDate() == null) {
            JOptionPane.showMessageDialog(null, "No se ha ingresado una fecha hasta", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {

            Persona persona = a.buscarPersonasRFC(rfc);

            ConfiguracionPaginado configCheck = new ConfiguracionPaginado(0, 1);
            if (b.buscarTramites(configCheck, persona, this.tipo(), this.sacarFechaDesde(), this.sacarFechaHasta()) == null
                    || b.buscarTramites(configCheck, persona, this.tipo(), this.sacarFechaDesde(), this.sacarFechaHasta()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede generar reporte, favor de buscar tramites con los parametros correspondientes", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                this.generacionReporte();
            }
        }
    }//GEN-LAST:event_lblGenerarReporteMouseClicked

    private void lblAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAtrasMouseClicked
        ConsultaPersonaTramite v = new ConsultaPersonaTramite();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_lblAtrasMouseClicked

    private void lblBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBuscarMouseClicked
        if (rfc == null) {
            JOptionPane.showMessageDialog(this, "El RFC de la persona no está disponible.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dpDesde.getDate() == null) {
            JOptionPane.showMessageDialog(null, "No se ha ingresado una fecha desde", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (dpHasta.getDate() == null) {
            JOptionPane.showMessageDialog(null, "No se ha ingresado una fecha hasta", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            this.validaBusqueda();
            this.cargarTablaLicencia();
        }
    }//GEN-LAST:event_lblBuscarMouseClicked

    private void cbTramiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTramiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTramiteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTramite;
    private com.github.lgooddatepicker.components.DatePicker dpDesde;
    private com.github.lgooddatepicker.components.DatePicker dpHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAtras;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblGenerarReporte;
    // End of variables declaration//GEN-END:variables
}
