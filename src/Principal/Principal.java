/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Principal.Logica.Herramientas;
import Principal.Logica.clsExportarExcel;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author lmartinez
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    clsExportarExcel obj;
    int LayoutX;
    int LayoutY;
    private static DefaultTableModel modeloTablaUser = null;
    private final String[] cols = {"ID", "MC", "Description", "Manufactura", "Modelo", "Numero de Serie", "Fecha Alta", "Mantenimiento", "Costo Equipo", "PO Compra", "Numero Pedimento", "Fecha Cruce",
    "Pais Origen", "Numero Asset", "Peso Equipo", "Estatus", "Calibracion en Planta", "Edificio", "Comentarios", "Calibracion 17025"};
    String pattern = "d MMM y";
SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    public Principal() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        mostrar("");
        limpiarCampos();
        
        tblRegistros1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                pasarDatos();
            }
        });
        
        txtID1.setEnabled(false);
        
        kGradientPanel8.setVisible(false);
        
        
    }
    
    public ArrayList<String> traerLocacion() {

        ArrayList<String> categorias = new ArrayList<>();

        try {
            Statement st = Herramientas.cn.createStatement();
            ResultSet rs = st.executeQuery("select nombre from locacion");

            while (rs.next()) {

                categorias.add(rs.getString("nombre"));
                //System.out.println(erroresList);
            }

            return categorias;
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }
    
    public String seleccionRuta() {

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        //jfc.setControlButtonsAreShown(false);
        jfc.showDialog(new JLabel(), "Select");
        File file = jfc.getSelectedFile();

        
        if(file != null){
        if (file.isDirectory()) {
            System.out.println("Carpeta:" + file.getAbsolutePath());
        } else if (file.isFile()) {
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "No ha seleccionado una ubicacion", "WARNING", JOptionPane.WARNING_MESSAGE);
            return "";
        }
        return file.getAbsolutePath();

    }
    
    
    
    public void mostrar(Object... obj) {
        String sql = "";

        if (obj.length == 1 && obj[0].equals("")) {
            sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion";
        } else {

            if ((int) obj[1] == 0) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.mc like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 1) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.description like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 2) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.manufactura like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 3) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.modelo like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 4) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.serial_number like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 5) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.fecha_alta like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 6) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.po like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 7) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.numero_pedimento like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 8) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.pais_origen like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 9) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.asset like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 10) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.estatus like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 11) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.edificio like '%"+obj[0]+"%'";
            }
            if ((int) obj[1] == 12) {
                sql = "select e.id_equipo, e.mc, e.description, e.manufactura, e.modelo, e.serial_number, e.fecha_alta, e.mantenimiento, e.costo_equipo, e.po, e.numero_pedimento, e.fecha_cruce, e.pais_origen, e.asset, e.peso, e.estatus, e.calibracion_planta, l.nombre, e.comentarios, e.calibracio_17025 from equipo e inner join locacion l on e.edificio=l.id_locacion where e.calibracion_17025 like '%"+obj[0]+"%'";
            }
//            if( (int)obj[1] == 2)
//            {
//                sql = "select m.id_material,m.part_number, m.streamline, l.nombre, m.UOM, m.qty, m.max, m.min from material m inner join location l on m.id_location=l.id_location where l.nombre like '%" + obj[0]+"%'" ;
//            }
        }

        modeloTablaUser = new DefaultTableModel(null, cols) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        
        
        tblRegistros.setModel(modeloTablaUser);
        tblRegistros1.setModel(modeloTablaUser);
        String[] datos = new String[25];
        try {
            Statement st = Herramientas.cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
                datos[12] = rs.getString(13);
                datos[13] = rs.getString(14);
                datos[14] = rs.getString(15);
                datos[15] = rs.getString(16);
                datos[16] = rs.getString(17);
                datos[17] = rs.getString(18);
                datos[18] = rs.getString(19);
                datos[19] = rs.getString(20);

                modeloTablaUser.addRow(datos);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        //tblInventario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resizeColumnWidth(tblRegistros);
        tblRegistros.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 20; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 330) {
                width = 330;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        kGradientPanel2 = new PnlGrad.KGradientPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        kGradientPanel1 = new PnlGrad.KGradientPanel();
        kGradientPanel7 = new PnlGrad.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDescription = new rojerusan.RSMetroTextPlaceHolder();
        jLabel4 = new javax.swing.JLabel();
        txtManufactura = new rojerusan.RSMetroTextPlaceHolder();
        jLabel5 = new javax.swing.JLabel();
        txtModelo = new rojerusan.RSMetroTextPlaceHolder();
        jLabel6 = new javax.swing.JLabel();
        txtSerialNumber = new rojerusan.RSMetroTextPlaceHolder();
        jLabel7 = new javax.swing.JLabel();
        jdcFechaAlta = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jcbMantenimiento = new rojerusan.RSComboMetro();
        jLabel9 = new javax.swing.JLabel();
        txtCostoEquipo = new rojerusan.RSMetroTextPlaceHolder();
        jLabel10 = new javax.swing.JLabel();
        txtPOCompra = new rojerusan.RSMetroTextPlaceHolder();
        jLabel11 = new javax.swing.JLabel();
        txtNumeroPedimento = new rojerusan.RSMetroTextPlaceHolder();
        jLabel12 = new javax.swing.JLabel();
        jdcFechaCruce = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jcbPaisOrigen = new rojerusan.RSComboMetro();
        jLabel14 = new javax.swing.JLabel();
        txtNumeroAsset = new rojerusan.RSMetroTextPlaceHolder();
        jLabel15 = new javax.swing.JLabel();
        txtPesoEquipo = new rojerusan.RSMetroTextPlaceHolder();
        jLabel16 = new javax.swing.JLabel();
        jcbEstatus = new rojerusan.RSComboMetro();
        jLabel17 = new javax.swing.JLabel();
        jcbCalibracionPlanta = new rojerusan.RSComboMetro();
        jLabel18 = new javax.swing.JLabel();
        jcbEdificio = new rojerusan.RSComboMetro();
        jLabel19 = new javax.swing.JLabel();
        txtComentarios = new rojerusan.RSMetroTextPlaceHolder();
        jLabel20 = new javax.swing.JLabel();
        jcbCalibracion17025 = new rojerusan.RSComboMetro();
        txtMc = new rojerusan.RSMetroTextPlaceHolder();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        kGradientPanel8 = new PnlGrad.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        kGradientPanel9 = new PnlGrad.KGradientPanel();
        kGradientPanel10 = new PnlGrad.KGradientPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtDescription1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel23 = new javax.swing.JLabel();
        txtManufactura1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel24 = new javax.swing.JLabel();
        txtModelo1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel25 = new javax.swing.JLabel();
        txtSerialNumber1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel26 = new javax.swing.JLabel();
        jdcFechaAlta1 = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jcbMantenimiento1 = new rojerusan.RSComboMetro();
        jLabel28 = new javax.swing.JLabel();
        txtCostoEquipo1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel29 = new javax.swing.JLabel();
        txtPOCompra1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel30 = new javax.swing.JLabel();
        txtNumeroPedimento1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel31 = new javax.swing.JLabel();
        jdcFechaCruce1 = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jcbPaisOrigen1 = new rojerusan.RSComboMetro();
        jLabel33 = new javax.swing.JLabel();
        txtNumeroAsset1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel34 = new javax.swing.JLabel();
        txtPesoEquipo1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel35 = new javax.swing.JLabel();
        jcbEstatus1 = new rojerusan.RSComboMetro();
        jLabel36 = new javax.swing.JLabel();
        jcbCalibracionPlanta1 = new rojerusan.RSComboMetro();
        jLabel37 = new javax.swing.JLabel();
        jcbEdificio1 = new rojerusan.RSComboMetro();
        jLabel38 = new javax.swing.JLabel();
        txtComentarios1 = new rojerusan.RSMetroTextPlaceHolder();
        jLabel39 = new javax.swing.JLabel();
        jcbCalibracion17026 = new rojerusan.RSComboMetro();
        txtMc1 = new rojerusan.RSMetroTextPlaceHolder();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel42 = new javax.swing.JLabel();
        txtID1 = new javax.swing.JTextField();
        rSMaterialButtonRectangle6 = new rojerusan.RSMaterialButtonRectangle();
        jPanel2 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jcbParametro = new rojerusan.RSComboMetro();
        jLabel41 = new javax.swing.JLabel();
        txtParametro = new rojerusan.RSMetroTextPlaceHolder();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle4 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle5 = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegistros1 = new javax.swing.JTable();
        kGradientPanel3 = new PnlGrad.KGradientPanel();
        kGradientPanel4 = new PnlGrad.KGradientPanel();
        kGradientPanel5 = new PnlGrad.KGradientPanel();
        kGradientPanel6 = new PnlGrad.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        BtnCerrar = new javax.swing.JLabel();
        BtnMaximizar = new javax.swing.JLabel();
        BtnMinimizar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jTabbedPane1.setBackground(new java.awt.Color(51, 153, 255));

        kGradientPanel2.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel2.setkGradientFocus(300);
        kGradientPanel2.setkStartColor(new java.awt.Color(51, 153, 255));

        kGradientPanel1.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel1.setkGradientFocus(300);
        kGradientPanel1.setkStartColor(new java.awt.Color(51, 153, 255));

        kGradientPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTRAR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        kGradientPanel7.setkEndColor(new java.awt.Color(204, 255, 255));
        kGradientPanel7.setkGradientFocus(800);
        kGradientPanel7.setkStartColor(new java.awt.Color(204, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MC");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Descripcion");

        txtDescription.setForeground(new java.awt.Color(0, 0, 0));
        txtDescription.setText("20");
        txtDescription.setToolTipText("Intervalo en Dias");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Manufactura");

        txtManufactura.setForeground(new java.awt.Color(0, 0, 0));
        txtManufactura.setText("X");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Modelo");

        txtModelo.setForeground(new java.awt.Color(0, 0, 0));
        txtModelo.setText("XNS");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Numero de Serie");

        txtSerialNumber.setForeground(new java.awt.Color(0, 0, 0));
        txtSerialNumber.setText("US17202311045");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Fecha Alta");

        jdcFechaAlta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Mantenimiento");

        jcbMantenimiento.setBackground(new java.awt.Color(0, 0, 0));
        jcbMantenimiento.setForeground(new java.awt.Color(0, 0, 0));
        jcbMantenimiento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        jcbMantenimiento.setColorFondo(new java.awt.Color(255, 255, 255));
        jcbMantenimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Costo Equipo");

        txtCostoEquipo.setForeground(new java.awt.Color(0, 0, 0));
        txtCostoEquipo.setText("15000");
        txtCostoEquipo.setToolTipText("Ingresar Cantidad en Dolares");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("PO de Compra");

        txtPOCompra.setForeground(new java.awt.Color(0, 0, 0));
        txtPOCompra.setText("80112023");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Numero de Pedimento");
        jLabel11.setToolTipText("");

        txtNumeroPedimento.setForeground(new java.awt.Color(0, 0, 0));
        txtNumeroPedimento.setText("102245240");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Fecha Cruce");

        jdcFechaCruce.setFocusable(false);
        jdcFechaCruce.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Pais Origen");

        jcbPaisOrigen.setForeground(new java.awt.Color(0, 0, 0));
        jcbPaisOrigen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afganistan", "Albania", "Alemania", "Andorra", "Angola", "Antigua y Barbuda", "Arabia Saudita", "Argelia", "Argentina", "Armenia", "Australia", "Austria", "Azerbaiyan", "Bahamas", "Bahrein", "Bangladesh", "Barbados", "Belarus", "Belgica", "Belice", "Benin", "Bhutan", "Bolivia", "Bosnia y Herzegovina", "Botswana", "Brasil", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Camboya", "Camerun", "Canada", "Chad", "Chequia", "Chile", "China", "Chipre", "Colombia", "Comoras", "Congo", "Costa Rica", "Côte d’Ivoire", "Croacia", "Cuba", "Dinamarca", "Djibouti", "Dominica", "Ecuador", "Egipto", "El Salvador", "Emiratos Arabes Unidos", "Eritrea", "Eslovaquia", "Eslovenia", "España", "Estados Unidos de America", "Estonia", "Eswatini", "Etiopia", "Federacion de Rusia", "Fiji", "Filipinas", "Finlandia", "Francia", "Gabon", "Gambia", "Georgia", "Ghana", "Granada", "Grecia", "Guatemala", "Guinea", "Guinea Ecuatorial", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungria", "India", "Indonesia", "Iran", "Iraq", "Irlanda", "Islandia", "Islas Cook", "Islas Marshall", "Islas Salomon", "Israel", "Italia", "Jamaica", "Japon", "Jordania", "Kazajstan", "Kenya", "Kirguistan", "Kiribati", "Kuwait", "Lesotho", "Letonia", "Libano", "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo", "Madagascar", "Malasia", "Malawi", "Maldivas", "Mali", "Malta", "Marruecos", "Mauricio", "Mauritania", "Mexico", "Micronesia", "Monaco", "Mongolia", "Montenegro", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Nicaragua", "Niger", "Nigeria", "Niue", "Noruega", "Nueva Zelandia", "Oman", "Paises Bajos", "Pakistan", "Palau", "Panama", "Papua Nueva Guinea", "Paraguay", "Peru", "Polonia", "Portugal", "Qatar", "Reino Unido de Gran Bretaña e Irlanda del Norte", "Republica Arabe Siria", "Republica Centroafricana", "Republica de Corea", "Republica de Macedonia del Norte", "Republica de Moldova", "Republica Democratica del Congo", "Republica Democratica Popular Lao", "Republica Dominicana", "Republica Popular Democratica de Corea", "Republica Unida de Tanzania", "Rumania", "Rwanda", "Saint Kitts y Nevis", "Samoa", "San Marino", "San Vicente y las Granadinas", "Santa Lucia", "Santa Sede", "Santo Tome y Principe", "Senegal", "Serbia", "Seychelles", "Sierra Leona", "Singapur", "Somalia", "Sri Lanka", "Sudafrica", "Sudan", "Sudan del Sur", "Suecia", "Suiza", "Suriname", "Tailandia", "Tayikistan", "Timor-Leste", "Togo", "Tonga", "Trinidad y Tabago", "Tunez", "Türkiye", "Turkmenistan", "Tuvalu", "Ucrania", "Uganda", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Viet Nam", "Yemen", "Zambia", "Zimbabwe" }));
        jcbPaisOrigen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Numero Asset");

        txtNumeroAsset.setForeground(new java.awt.Color(0, 0, 0));
        txtNumeroAsset.setText("500");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Peso Equipo");

        txtPesoEquipo.setForeground(new java.awt.Color(0, 0, 0));
        txtPesoEquipo.setText("50");
        txtPesoEquipo.setToolTipText("Ingresar peso en KG");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Estatus");

        jcbEstatus.setForeground(new java.awt.Color(0, 0, 0));
        jcbEstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
        jcbEstatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Calibracion en Planta");

        jcbCalibracionPlanta.setForeground(new java.awt.Color(0, 0, 0));
        jcbCalibracionPlanta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        jcbCalibracionPlanta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Edificio / Planta");

        jcbEdificio.setForeground(new java.awt.Color(0, 0, 0));
        jcbEdificio.setModel(new javax.swing.DefaultComboBoxModel(traerLocacion().toArray()));
        jcbEdificio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Comentarios");

        txtComentarios.setForeground(new java.awt.Color(0, 0, 0));
        txtComentarios.setText("Usar alcohol isopropilico");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Requiere Calibracion 17025");

        jcbCalibracion17025.setForeground(new java.awt.Color(0, 0, 0));
        jcbCalibracion17025.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        jcbCalibracion17025.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtMc.setForeground(new java.awt.Color(0, 0, 0));
        txtMc.setText("1");

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(51, 153, 255));
        rSMaterialButtonRectangle1.setText("Registrar");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel7Layout = new javax.swing.GroupLayout(kGradientPanel7);
        kGradientPanel7.setLayout(kGradientPanel7Layout);
        kGradientPanel7Layout.setHorizontalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPesoEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbMantenimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMc, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtManufactura, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcFechaAlta, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jcbEstatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(txtCostoEquipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPOCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtNumeroPedimento, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jdcFechaCruce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jcbPaisOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                            .addComponent(txtNumeroAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jcbCalibracionPlanta, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jcbEdificio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbCalibracion17025, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        kGradientPanel7Layout.setVerticalGroup(
            kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel7Layout.createSequentialGroup()
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtManufactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSerialNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jdcFechaAlta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCostoEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPOCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumeroPedimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcFechaCruce, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addComponent(txtNumeroAsset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jcbPaisOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPesoEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbEstatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbCalibracionPlanta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbEdificio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCalibracion17025, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel7Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        kGradientPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "REGISTROS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        kGradientPanel8.setkEndColor(new java.awt.Color(204, 255, 255));
        kGradientPanel8.setkGradientFocus(200);
        kGradientPanel8.setkStartColor(new java.awt.Color(204, 255, 255));

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "MC", "Descripcion", "Manufactura", "Modelo", "Serie", "Fecha Alta", "Mantenimiento", "Costo Equipo", "PO Compra", "Numero Pedimento", "Fecha Cruce", "Pais Origen", "Asset", "Peso", "Estatus", "Calibracion Planta", "Planta", "Comentarios", "Calibracion 17025"
            }
        ));
        jScrollPane1.setViewportView(tblRegistros);

        javax.swing.GroupLayout kGradientPanel8Layout = new javax.swing.GroupLayout(kGradientPanel8);
        kGradientPanel8.setLayout(kGradientPanel8Layout);
        kGradientPanel8Layout.setHorizontalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        kGradientPanel8Layout.setVerticalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Altas", kGradientPanel1);

        kGradientPanel9.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel9.setkStartColor(new java.awt.Color(51, 153, 255));

        kGradientPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modificar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        kGradientPanel10.setkEndColor(new java.awt.Color(204, 255, 255));
        kGradientPanel10.setkGradientFocus(800);
        kGradientPanel10.setkStartColor(new java.awt.Color(204, 255, 255));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("MC");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Descripcion");

        txtDescription1.setForeground(new java.awt.Color(0, 0, 0));
        txtDescription1.setText("20");
        txtDescription1.setToolTipText("Intervalo en Dias");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Manufactura");

        txtManufactura1.setForeground(new java.awt.Color(0, 0, 0));
        txtManufactura1.setText("X");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Modelo");

        txtModelo1.setForeground(new java.awt.Color(0, 0, 0));
        txtModelo1.setText("XNS");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Numero de Serie");

        txtSerialNumber1.setForeground(new java.awt.Color(0, 0, 0));
        txtSerialNumber1.setText("US17202311045");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Fecha Alta");

        jdcFechaAlta1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Mantenimiento");

        jcbMantenimiento1.setForeground(new java.awt.Color(0, 0, 0));
        jcbMantenimiento1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        jcbMantenimiento1.setColorFondo(new java.awt.Color(255, 255, 255));
        jcbMantenimiento1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Costo Equipo");

        txtCostoEquipo1.setForeground(new java.awt.Color(0, 0, 0));
        txtCostoEquipo1.setText("15000");
        txtCostoEquipo1.setToolTipText("Ingresar Cantidad en Dolares");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("PO de Compra");

        txtPOCompra1.setForeground(new java.awt.Color(0, 0, 0));
        txtPOCompra1.setText("80112023");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Numero de Pedimento");
        jLabel30.setToolTipText("");

        txtNumeroPedimento1.setForeground(new java.awt.Color(0, 0, 0));
        txtNumeroPedimento1.setText("102245240");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Fecha Cruce");

        jdcFechaCruce1.setFocusable(false);
        jdcFechaCruce1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Pais Origen");

        jcbPaisOrigen1.setForeground(new java.awt.Color(0, 0, 0));
        jcbPaisOrigen1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afganistan", "Albania", "Alemania", "Andorra", "Angola", "Antigua y Barbuda", "Arabia Saudita", "Argelia", "Argentina", "Armenia", "Australia", "Austria", "Azerbaiyan", "Bahamas", "Bahrein", "Bangladesh", "Barbados", "Belarus", "Belgica", "Belice", "Benin", "Bhutan", "Bolivia", "Bosnia y Herzegovina", "Botswana", "Brasil", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Camboya", "Camerun", "Canada", "Chad", "Chequia", "Chile", "China", "Chipre", "Colombia", "Comoras", "Congo", "Costa Rica", "Côte d’Ivoire", "Croacia", "Cuba", "Dinamarca", "Djibouti", "Dominica", "Ecuador", "Egipto", "El Salvador", "Emiratos Arabes Unidos", "Eritrea", "Eslovaquia", "Eslovenia", "España", "Estados Unidos de America", "Estonia", "Eswatini", "Etiopia", "Federacion de Rusia", "Fiji", "Filipinas", "Finlandia", "Francia", "Gabon", "Gambia", "Georgia", "Ghana", "Granada", "Grecia", "Guatemala", "Guinea", "Guinea Ecuatorial", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungria", "India", "Indonesia", "Iran", "Iraq", "Irlanda", "Islandia", "Islas Cook", "Islas Marshall", "Islas Salomon", "Israel", "Italia", "Jamaica", "Japon", "Jordania", "Kazajstan", "Kenya", "Kirguistan", "Kiribati", "Kuwait", "Lesotho", "Letonia", "Libano", "Liberia", "Libia", "Liechtenstein", "Lituania", "Luxemburgo", "Madagascar", "Malasia", "Malawi", "Maldivas", "Mali", "Malta", "Marruecos", "Mauricio", "Mauritania", "Mexico", "Micronesia", "Monaco", "Mongolia", "Montenegro", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Nicaragua", "Niger", "Nigeria", "Niue", "Noruega", "Nueva Zelandia", "Oman", "Paises Bajos", "Pakistan", "Palau", "Panama", "Papua Nueva Guinea", "Paraguay", "Peru", "Polonia", "Portugal", "Qatar", "Reino Unido de Gran Bretaña e Irlanda del Norte", "Republica Arabe Siria", "Republica Centroafricana", "Republica de Corea", "Republica de Macedonia del Norte", "Republica de Moldova", "Republica Democratica del Congo", "Republica Democratica Popular Lao", "Republica Dominicana", "Republica Popular Democratica de Corea", "Republica Unida de Tanzania", "Rumania", "Rwanda", "Saint Kitts y Nevis", "Samoa", "San Marino", "San Vicente y las Granadinas", "Santa Lucia", "Santa Sede", "Santo Tome y Principe", "Senegal", "Serbia", "Seychelles", "Sierra Leona", "Singapur", "Somalia", "Sri Lanka", "Sudafrica", "Sudan", "Sudan del Sur", "Suecia", "Suiza", "Suriname", "Tailandia", "Tayikistan", "Timor-Leste", "Togo", "Tonga", "Trinidad y Tabago", "Tunez", "Türkiye", "Turkmenistan", "Tuvalu", "Ucrania", "Uganda", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Viet Nam", "Yemen", "Zambia", "Zimbabwe" }));
        jcbPaisOrigen1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Numero Asset");

        txtNumeroAsset1.setForeground(new java.awt.Color(0, 0, 0));
        txtNumeroAsset1.setText("500");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Peso Equipo");

        txtPesoEquipo1.setForeground(new java.awt.Color(0, 0, 0));
        txtPesoEquipo1.setText("50");
        txtPesoEquipo1.setToolTipText("Ingresar peso en KG");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Estatus");

        jcbEstatus1.setForeground(new java.awt.Color(0, 0, 0));
        jcbEstatus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
        jcbEstatus1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Calibracion en Planta");

        jcbCalibracionPlanta1.setForeground(new java.awt.Color(0, 0, 0));
        jcbCalibracionPlanta1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        jcbCalibracionPlanta1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Edificio / Planta");

        jcbEdificio1.setForeground(new java.awt.Color(0, 0, 0));
        jcbEdificio1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alliance Juarez", "EPL", "Alliance Canada" }));
        jcbEdificio1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Comentarios");

        txtComentarios1.setForeground(new java.awt.Color(0, 0, 0));
        txtComentarios1.setText("Usar alcohol isopropilico");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Requiere Calibracion 17025");

        jcbCalibracion17026.setForeground(new java.awt.Color(0, 0, 0));
        jcbCalibracion17026.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));
        jcbCalibracion17026.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtMc1.setForeground(new java.awt.Color(0, 0, 0));
        txtMc1.setText("1");

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(51, 153, 255));
        rSMaterialButtonRectangle2.setText("Guardar");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("ID");

        txtID1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtID1.setText("100");
        txtID1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 2));

        rSMaterialButtonRectangle6.setBackground(new java.awt.Color(51, 153, 255));
        rSMaterialButtonRectangle6.setText("Limpiar");
        rSMaterialButtonRectangle6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel10Layout = new javax.swing.GroupLayout(kGradientPanel10);
        kGradientPanel10.setLayout(kGradientPanel10Layout);
        kGradientPanel10Layout.setHorizontalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel10Layout.createSequentialGroup()
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPesoEquipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbMantenimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMc1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel10Layout.createSequentialGroup()
                                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtManufactura1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSerialNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcFechaAlta1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                            .addGroup(kGradientPanel10Layout.createSequentialGroup()
                                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jcbEstatus1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(txtCostoEquipo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(kGradientPanel10Layout.createSequentialGroup()
                                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPOCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtNumeroPedimento1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jdcFechaCruce1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jcbPaisOrigen1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                            .addComponent(txtNumeroAsset1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                    .addGroup(kGradientPanel10Layout.createSequentialGroup()
                                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jcbCalibracionPlanta1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jcbEdificio1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtComentarios1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(kGradientPanel10Layout.createSequentialGroup()
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbCalibracion17026, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtID1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSMaterialButtonRectangle6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        kGradientPanel10Layout.setVerticalGroup(
            kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel10Layout.createSequentialGroup()
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel10Layout.createSequentialGroup()
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(kGradientPanel10Layout.createSequentialGroup()
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(jLabel24)
                                .addComponent(jLabel25))
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtManufactura1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtModelo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSerialNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jdcFechaAlta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCostoEquipo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPOCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumeroPedimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbMantenimiento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdcFechaCruce1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(kGradientPanel10Layout.createSequentialGroup()
                        .addComponent(txtNumeroAsset1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jcbPaisOrigen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPesoEquipo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbEstatus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbCalibracionPlanta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtComentarios1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbEdificio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel10Layout.createSequentialGroup()
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbCalibracion17026, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(txtID1)))
                    .addGroup(kGradientPanel10Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(kGradientPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Buscar Por:");

        jcbParametro.setForeground(new java.awt.Color(0, 0, 0));
        jcbParametro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MC", "Descripcion", "Manufactura", "Modelo", "Numero de Serie", "Fecha Alta", "PO de Compra", "Numero de Pedimento", "Pais Origen", "Numero Asset", "Estatus", "Edificio", "Calibracion 17025" }));
        jcbParametro.setColorFondo(new java.awt.Color(255, 255, 255));
        jcbParametro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Introduzca Parametro");

        txtParametro.setForeground(new java.awt.Color(0, 0, 0));

        rSMaterialButtonRectangle3.setText("Buscar");
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle4.setText("To Excel");
        rSMaterialButtonRectangle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle4ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle5.setText("Mostrar Todo");
        rSMaterialButtonRectangle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle5ActionPerformed(evt);
            }
        });

        tblRegistros1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "MC", "Descripcion", "Manufactura", "Modelo", "Serie", "Fecha Alta", "Mantenimiento", "Costo Equipo", "PO Compra", "Numero Pedimento", "Fecha Cruce", "Pais Origen", "Asset", "Peso", "Estatus", "Calibracion Planta", "Planta", "Comentarios", "Calibracion 17025"
            }
        ));
        jScrollPane2.setViewportView(tblRegistros1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbParametro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtParametro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSMaterialButtonRectangle5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtParametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSMaterialButtonRectangle5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout kGradientPanel9Layout = new javax.swing.GroupLayout(kGradientPanel9);
        kGradientPanel9.setLayout(kGradientPanel9Layout);
        kGradientPanel9Layout.setHorizontalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        kGradientPanel9Layout.setVerticalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kGradientPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Busqueda", kGradientPanel9);

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
            .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jTabbedPane2)
                    .addGap(0, 0, 0)))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
            .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jTabbedPane2)
                    .addGap(0, 0, 0)))
        );

        jTabbedPane1.addTab("Calibracion", kGradientPanel2);

        kGradientPanel3.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel3.setkGradientFocus(300);
        kGradientPanel3.setkStartColor(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Locaciones", kGradientPanel3);

        kGradientPanel4.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel4.setkGradientFocus(300);
        kGradientPanel4.setkStartColor(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Registro de Mantenimientos", kGradientPanel4);

        kGradientPanel5.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel5.setkGradientFocus(300);
        kGradientPanel5.setkStartColor(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Busqueda", kGradientPanel5);

        kGradientPanel6.setkEndColor(new java.awt.Color(51, 153, 255));
        kGradientPanel6.setkGradientFocus(300);
        kGradientPanel6.setkStartColor(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout kGradientPanel6Layout = new javax.swing.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 988, Short.MAX_VALUE)
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Reportes", kGradientPanel6);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setToolTipText("");
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        BtnCerrar.setBackground(new java.awt.Color(51, 51, 51));
        BtnCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Principal/Recursos/Iconos/Cerrar.png"))); // NOI18N
        BtnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnCerrar.setOpaque(true);
        BtnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCerrarMouseClicked(evt);
            }
        });

        BtnMaximizar.setBackground(new java.awt.Color(51, 51, 51));
        BtnMaximizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnMaximizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Principal/Recursos/Iconos/Maximizar.png"))); // NOI18N
        BtnMaximizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnMaximizar.setOpaque(true);
        BtnMaximizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnMaximizarMouseClicked(evt);
            }
        });

        BtnMinimizar.setBackground(new java.awt.Color(51, 51, 51));
        BtnMinimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BtnMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Principal/Recursos/Iconos/Minimizar.png"))); // NOI18N
        BtnMinimizar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BtnMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnMinimizar.setOpaque(true);
        BtnMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnMinimizarMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LeCaSys");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnMaximizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(BtnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(BtnMaximizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCerrarMouseClicked
        if(evt.getButton()==java.awt.event.MouseEvent.BUTTON1){
            System.exit(0);
        }
    }//GEN-LAST:event_BtnCerrarMouseClicked

    private void BtnMaximizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnMaximizarMouseClicked
        if(evt.getButton()==java.awt.event.MouseEvent.BUTTON1){
            this.setExtendedState(MAXIMIZED_BOTH);
        }
    }//GEN-LAST:event_BtnMaximizarMouseClicked

    private void BtnMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnMinimizarMouseClicked
        if(evt.getButton()==java.awt.event.MouseEvent.BUTTON1){
            this.setExtendedState(ICONIFIED);
        }
    }//GEN-LAST:event_BtnMinimizarMouseClicked

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        this.setLocation(evt.getXOnScreen()-LayoutX, evt.getYOnScreen()-LayoutY);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        if(evt.getButton()==java.awt.event.MouseEvent.BUTTON1){
            LayoutX = evt.getX();
            LayoutY = evt.getY();
        }
    }//GEN-LAST:event_jPanel1MousePressed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(jdcFechaAlta.getDate());
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(jdcFechaCruce.getDate());

        String fecha_in= calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        String fecha_out=calendar2.get(Calendar.YEAR)+"-"+calendar2.get(Calendar.MONTH)+"-"+calendar2.get(Calendar.DAY_OF_MONTH);
        
//        System.out.println("Fecha: "+fecha_in);
//        System.out.println("Fecha: "+fecha_out);
//        System.out.println("Anio: "+ jdcFechaAlta.getDate());


        int id_locacion=0;
        try {
            String sql = "select id_locacion from locacion where nombre= '" + jcbEdificio.getSelectedItem().toString() + "'";
            Statement st2 = Herramientas.cn.createStatement();
            ResultSet rs2 = st2.executeQuery(sql);
            if (rs2.next()) {
                id_locacion = rs2.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }


        
        agregarBD(txtMc.getText(), txtDescription.getText(), txtManufactura.getText(), txtModelo.getText(), txtSerialNumber.getText(), fecha_in, jcbMantenimiento.getSelectedItem().toString(), 
                txtCostoEquipo.getText(), txtPOCompra.getText(), txtNumeroPedimento.getText(), fecha_out, jcbPaisOrigen.getSelectedItem().toString(), 
                txtNumeroAsset.getText(), txtPesoEquipo.getText(), jcbEstatus.getSelectedItem().toString(), jcbCalibracionPlanta.getSelectedItem().toString(), 
                id_locacion, txtComentarios.getText(), jcbCalibracion17025.getSelectedItem().toString());
        
        mostrar("");
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
        
        
        int mc = 0;
        String serial_number="";
        try {
            String sql = "select mc from equipo where mc= '" + txtMc1.getText() + "' and id_equipo!="+txtID1.getText();
            Statement st2 = Herramientas.cn.createStatement();
            ResultSet rs2 = st2.executeQuery(sql);
            if (rs2.next()) {
                mc = rs2.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        
        try {
            String sql = "select serial_number from equipo where serial_number= '" + txtMc1.getText() + "' and id_equipo!="+txtID1.getText();
            Statement st2 = Herramientas.cn.createStatement();
            ResultSet rs2 = st2.executeQuery(sql);
            if (rs2.next()) {
                serial_number = rs2.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        

        if (mc == 0 && serial_number=="") {
            
            Calendar calendar = Calendar.getInstance();
        calendar.setTime(jdcFechaAlta1.getDate());
        
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(jdcFechaCruce1.getDate());

        String fecha_in= calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        String fecha_out=calendar2.get(Calendar.YEAR)+"-"+calendar2.get(Calendar.MONTH)+"-"+calendar2.get(Calendar.DAY_OF_MONTH);
            
            String sqlActualizar = "update equipo set mc='" + txtMc1.getText() + "', description='"+txtDescription1.getText()+"', "
                    + "manufactura='"+txtManufactura1.getText()+"',"
                    + "modelo='"+txtModelo1.getText()+"',"
                     + "serial_number='"+txtSerialNumber1.getText()+"',"
                     + "fecha_alta='"+fecha_in+"',"
                     + "mantenimiento='"+jcbMantenimiento1.getSelectedItem().toString()+"',"
                     + "costo_equipo="+txtCostoEquipo1.getText()+","
                     + "po='"+txtPOCompra1.getText()+"',"
                     + "numero_pedimento='"+txtNumeroPedimento1.getText()+"',"
                     + "fecha_cruce='"+fecha_out+"',"
                     + "pais_origen='"+jcbPaisOrigen1.getSelectedItem().toString()+"',"
                     + "asset='"+txtNumeroAsset1.getText()+"',"
                     + "peso="+txtPesoEquipo1.getText()+","
                     + "estatus='"+jcbEstatus1.getSelectedItem().toString()+"',"
                     + "calibracion_planta='"+jcbCalibracionPlanta1.getSelectedItem().toString()+"',"
                     + "edificio='"+jcbEdificio1.getSelectedItem().toString()+"',"
                     + "comentarios='"+txtComentarios1.getText()+"',"
                     + "calibracio_17025='"+jcbCalibracion17026.getSelectedItem().toString()+"'"
                    + " where id_equipo=" + txtID1.getText();

            PreparedStatement pps;
            try {
                pps = Herramientas.cn.prepareStatement(sqlActualizar);
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                mostrar("");

            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);

            }
            
        } else {

            JOptionPane.showMessageDialog(null, "Este MC o Serial Number ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;

        }
        
        
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
        // TODO add your handling code here:
        int parametro = jcbParametro.getSelectedIndex();
        mostrar(txtParametro.getText(), parametro);
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void rSMaterialButtonRectangle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle5ActionPerformed
        // TODO add your handling code here:
        mostrar("");
    }//GEN-LAST:event_rSMaterialButtonRectangle5ActionPerformed

    
    
    public void pasarDatos() {
        if (tblRegistros1.getSelectedRow() >= 0) {
            txtID1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 0).toString());
            txtMc1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 1).toString());
            
            txtDescription1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 2).toString());
            txtManufactura1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 3).toString());
            txtModelo1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 4).toString());
            txtSerialNumber1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 5).toString());
            //jdcFechaAlta1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 6).toString());
            
            String fecha1=tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 6).toString();
            //Date data1 = new Date(Integer.parseInt(fecha1.split("-")[0]), Integer.parseInt(fecha1.split("-")[1]), Integer.parseInt(fecha1.split("-")[2]));
            
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(fecha1.split("-")[0]), Integer.parseInt(fecha1.split("-")[1]), Integer.parseInt(fecha1.split("-")[2]));
            jdcFechaAlta1.setDate(calendar.getTime());
            
            
            //jcbMantenimiento1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 7).toString());
            jcbMantenimiento1.setSelectedItem(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 7).toString());
            txtCostoEquipo1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 8).toString());
            txtPOCompra1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 9).toString());
            //jdcFechaCruce1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 10).toString());
            
            txtNumeroPedimento1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 10).toString());
            
            String fecha2=tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 11).toString();
            calendar.set(Integer.parseInt(fecha2.split("-")[0]), Integer.parseInt(fecha2.split("-")[1]), Integer.parseInt(fecha2.split("-")[2]));
            jdcFechaCruce1.setDate(calendar.getTime());
            
            //jcbPaisOrigen1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 11).toString());
            jcbPaisOrigen1.setSelectedItem(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 12).toString());
            txtNumeroAsset1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 13).toString());
            txtPesoEquipo1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 14).toString());
            //jcbEstatus1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 14).toString());
            jcbEstatus1.setSelectedItem(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 15).toString());
            //jcbCalibracionPlanta1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 15).toString());
            jcbCalibracionPlanta1.setSelectedItem(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 16).toString());
            //jcbEdificio1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 16).toString());
            jcbEdificio1.setSelectedItem(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 17).toString());
            txtComentarios1.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 18).toString());
            //jcbCalibracion17026.setText(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 18).toString());
            jcbCalibracion17026.setSelectedItem(tblRegistros1.getValueAt(tblRegistros1.getSelectedRow(), 19).toString());

        }
    }
    
    private void rSMaterialButtonRectangle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle4ActionPerformed
        // TODO add your handling code here:
        
        String ruta = seleccionRuta();
//        String ruta=System.getProperty("user.home")+ "\\Desktop\\";

if(ruta==""){
    return;
    
}


        try {
            obj = new clsExportarExcel();
            obj.exportarExcel(tblRegistros1, ruta + "");
            JOptionPane.showMessageDialog(null, "Archivo generado correctamente", "Atencion!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_rSMaterialButtonRectangle4ActionPerformed

    private void rSMaterialButtonRectangle6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle6ActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
    }//GEN-LAST:event_rSMaterialButtonRectangle6ActionPerformed

    public void limpiarCampos(){
        txtMc.setText("");
        txtID1.setText("");
        txtDescription.setText("");
        txtManufactura.setText("");
        txtModelo.setText("");
        txtSerialNumber.setText("");
        jdcFechaAlta.setDate(null);
        jcbMantenimiento.setSelectedIndex(-1);
        txtCostoEquipo.setText(""); 
        txtPOCompra.setText("");
        txtNumeroPedimento.setText("");
        jdcFechaCruce.setDate(null);
        jcbPaisOrigen.setSelectedIndex(-1);
        txtNumeroAsset.setText("");
        txtPesoEquipo.setText("");
        jcbEstatus.setSelectedIndex(-1);
        jcbCalibracionPlanta.setSelectedIndex(-1); 
        jcbEdificio.setSelectedIndex(-1);
        txtComentarios.setText("");
        jcbCalibracion17025.setSelectedIndex(-1);
        
        txtMc1.setText("");
        txtDescription1.setText("");
        txtManufactura1.setText("");
        txtModelo1.setText("");
        txtSerialNumber1.setText("");
        jdcFechaAlta1.setDate(null);
        jcbMantenimiento1.setSelectedIndex(-1);
        txtCostoEquipo1.setText(""); 
        txtPOCompra1.setText("");
        txtNumeroPedimento1.setText("");
        jdcFechaCruce1.setDate(null);
        jcbPaisOrigen1.setSelectedIndex(-1);
        txtNumeroAsset1.setText("");
        txtPesoEquipo1.setText("");
        jcbEstatus1.setSelectedIndex(-1);
        jcbCalibracionPlanta1.setSelectedIndex(-1); 
        jcbEdificio1.setSelectedIndex(-1);
        txtComentarios1.setText("");
        jcbCalibracion17026.setSelectedIndex(-1);
        
        tblRegistros.clearSelection();
        tblRegistros1.clearSelection();
    }
    
    public void agregarBD(String mc, String descripcion, String manufactura, String modelo, String serial_number, String fecha_alta, String mantenimiento, String costo_equipo, String po, String numeroPedimento, String fecha_cruce, String pais_origen, String asset, String peso, String estatus, String calibracion_planta, int edificio, String comentarios, String calibracion_17025) {
        // INSERT INTO MATERIAL BLA BLA BLA
        
        try {
            PreparedStatement pps = Herramientas.cn.prepareStatement("INSERT INTO equipo (mc, description, manufactura, modelo, serial_number, fecha_alta, mantenimiento, costo_equipo,"
                    + " po, numero_pedimento, fecha_cruce, pais_origen, asset, peso, estatus, calibracion_planta, edificio, comentarios, calibracio_17025) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pps.setString(1, mc);
            pps.setString(2, descripcion);
            pps.setString(3, manufactura);
            pps.setString(4, modelo);
            pps.setString(5, serial_number);
            pps.setString(6, fecha_alta);
            pps.setString(7, mantenimiento);
            pps.setDouble(8, Double.parseDouble(costo_equipo));
            pps.setString(9, po);
            pps.setString(10, numeroPedimento);
            pps.setString(11, fecha_cruce);
            pps.setString(12, pais_origen);
            pps.setString(13, asset);
            
            pps.setDouble(14, Double.parseDouble(peso));
            pps.setString(15, estatus);
            pps.setString(16, calibracion_planta);
            pps.setInt(17, edificio);
            pps.setString(18, comentarios);
            pps.setString(19, calibracion_17025);

            pps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Mantenimiento Registrado Correctamente");

            

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BtnCerrar;
    private javax.swing.JLabel BtnMaximizar;
    private javax.swing.JLabel BtnMinimizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private rojerusan.RSComboMetro jcbCalibracion17025;
    private rojerusan.RSComboMetro jcbCalibracion17026;
    private rojerusan.RSComboMetro jcbCalibracionPlanta;
    private rojerusan.RSComboMetro jcbCalibracionPlanta1;
    private rojerusan.RSComboMetro jcbEdificio;
    private rojerusan.RSComboMetro jcbEdificio1;
    private rojerusan.RSComboMetro jcbEstatus;
    private rojerusan.RSComboMetro jcbEstatus1;
    private rojerusan.RSComboMetro jcbMantenimiento;
    private rojerusan.RSComboMetro jcbMantenimiento1;
    private rojerusan.RSComboMetro jcbPaisOrigen;
    private rojerusan.RSComboMetro jcbPaisOrigen1;
    private rojerusan.RSComboMetro jcbParametro;
    private com.toedter.calendar.JDateChooser jdcFechaAlta;
    private com.toedter.calendar.JDateChooser jdcFechaAlta1;
    private com.toedter.calendar.JDateChooser jdcFechaCruce;
    private com.toedter.calendar.JDateChooser jdcFechaCruce1;
    private PnlGrad.KGradientPanel kGradientPanel1;
    private PnlGrad.KGradientPanel kGradientPanel10;
    private PnlGrad.KGradientPanel kGradientPanel2;
    private PnlGrad.KGradientPanel kGradientPanel3;
    private PnlGrad.KGradientPanel kGradientPanel4;
    private PnlGrad.KGradientPanel kGradientPanel5;
    private PnlGrad.KGradientPanel kGradientPanel6;
    private PnlGrad.KGradientPanel kGradientPanel7;
    private PnlGrad.KGradientPanel kGradientPanel8;
    private PnlGrad.KGradientPanel kGradientPanel9;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle4;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle5;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle6;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTable tblRegistros1;
    private rojerusan.RSMetroTextPlaceHolder txtComentarios;
    private rojerusan.RSMetroTextPlaceHolder txtComentarios1;
    private rojerusan.RSMetroTextPlaceHolder txtCostoEquipo;
    private rojerusan.RSMetroTextPlaceHolder txtCostoEquipo1;
    private rojerusan.RSMetroTextPlaceHolder txtDescription;
    private rojerusan.RSMetroTextPlaceHolder txtDescription1;
    private javax.swing.JTextField txtID1;
    private rojerusan.RSMetroTextPlaceHolder txtManufactura;
    private rojerusan.RSMetroTextPlaceHolder txtManufactura1;
    private rojerusan.RSMetroTextPlaceHolder txtMc;
    private rojerusan.RSMetroTextPlaceHolder txtMc1;
    private rojerusan.RSMetroTextPlaceHolder txtModelo;
    private rojerusan.RSMetroTextPlaceHolder txtModelo1;
    private rojerusan.RSMetroTextPlaceHolder txtNumeroAsset;
    private rojerusan.RSMetroTextPlaceHolder txtNumeroAsset1;
    private rojerusan.RSMetroTextPlaceHolder txtNumeroPedimento;
    private rojerusan.RSMetroTextPlaceHolder txtNumeroPedimento1;
    private rojerusan.RSMetroTextPlaceHolder txtPOCompra;
    private rojerusan.RSMetroTextPlaceHolder txtPOCompra1;
    private rojerusan.RSMetroTextPlaceHolder txtParametro;
    private rojerusan.RSMetroTextPlaceHolder txtPesoEquipo;
    private rojerusan.RSMetroTextPlaceHolder txtPesoEquipo1;
    private rojerusan.RSMetroTextPlaceHolder txtSerialNumber;
    private rojerusan.RSMetroTextPlaceHolder txtSerialNumber1;
    // End of variables declaration//GEN-END:variables
}
