package Principal.Logica;

import Principal.Logica.DB.ConexionDB;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;

public class Herramientas {

    static ConexionDB con = new ConexionDB();
    public static Connection cn = con.conexion();

    private final static Pattern pswNamePtrn
            = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");

    public static String preguntarSioNo(String strMensaje) {
        int seleccion = JOptionPane.showOptionDialog(
                null, strMensaje, "Seleccione una operacion", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Si", "No"}, "Si");
        if (seleccion != -1) {
            if ((seleccion + 1) == 1) {
                return "SI";
            } else {
                return "NO";
            }
        }
        return null;
    }
    
    public static String getMes() {
        return new SimpleDateFormat("MMMM").format(Globales.c1.getTime());
        
    }
    
    public static String getWeekOfYear(){
        return Globales.c1.get(Calendar.WEEK_OF_YEAR) + "";
                
    }

    public static String leerFichero(String ruta)
    {
        String contenido="";
        File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
             contenido +=linea+"#";
            //System.out.println(linea);
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return contenido;
    }
    
    public static String leerFicheroNormal(String ruta)
    {
        String contenido="";
        File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
             contenido +=linea;
            //System.out.println(linea);
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return contenido;
    }
    
    public static String leerArchivoTexto(final String ruta)
    {
    
        String contenido="";
        InputStream entradaBytes = ClassLoader.class.getResourceAsStream(ruta);
        BufferedReader lector = new BufferedReader(new InputStreamReader(entradaBytes));
        String linea;
        
        try{
        while((linea= lector.readLine())!=null)
        {
        contenido +=linea+"#";
        }
        
        }
        catch(IOException e)  {
        e.printStackTrace();
    
                }finally{
        
        try{
        
            if(entradaBytes !=null){
            entradaBytes.close();
            }
            if(lector!=null){
            lector.close();
            }
            
        }
        catch(IOException ex){
            ex.printStackTrace();
            
        }
        
        }
        
        
        return contenido;
    }
    
    
    public static void showOnScreen(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if (screen > -1 && screen < gs.length) {
            gs[screen].setFullScreenWindow(frame);
        } else if (gs.length > 0) {
            gs[0].setFullScreenWindow(frame);
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

    public static boolean existeRegistro() {

        return true;
    }
    
    public PrintService buscarImpresora(String nombre) {

        PrintService service = null;

        // Obtienes el Array de impresoras disponibles 
        PrintService[] services = PrinterJob.lookupPrintServices();

        // Buscamos si el nombre de la impresora existe
        for (int i = 0; service == null && i < services.length; i++) {
            if (services[i].getName().indexOf(nombre) >= 0) {
                service = services[i];
            }
        }

        // Retornamos el servicio
        return service;
    }

    public static boolean eliminar(int eliminar, JTable tbl, String tabla, String campo) {
        try {
            String borrar = String.valueOf(tbl.getValueAt(eliminar, 0));
            String strRespuesta = "";
            strRespuesta = preguntarSioNo("Desea eliminar este registro?");
            if (strRespuesta == "SI") {
                try {
                    PreparedStatement pst = cn.prepareStatement("delete from " + tabla + " where " + campo + "=" + borrar);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Eliminación Completa");
                } catch (Exception e) {
                   JOptionPane.showMessageDialog(null, "No se eliminó el registro: Existen dependencias hacia este registro", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Operación Cancelada");
                return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No seleccionó fila");
        }
        return false;
    }

    public static boolean validatePassword(String userName) {

        Matcher mtch = pswNamePtrn.matcher(userName);
        if (mtch.matches()) {
            return true;
        }
        return false;
    }
    
    public static DefaultTableModel mostrar(String sql , String[] cols, Class contexto){
        
        
        DefaultTableModel modeloTablaUser = new DefaultTableModel(null,cols){
    public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
};
        
        String[] datos = new String[cols.length];
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < datos.length; i++) {
                    datos[i] = rs.getString(i+1);
                }
                modeloTablaUser.addRow(datos);
                
            }

        } catch (SQLException ex) {
            //Logger.getLogger(contexto.getClassLoader().getName()).log(Level.SEVERE, null, ex);
            
        }
        return modeloTablaUser;
    }
    

    

    
    
    public static void convertirExcel() throws IOException{
        String contenido = "";
        //contenido= Herramientas.leerArchivoTexto(Globales.RUTA_CONTENIDO);
        contenido = Herramientas.leerFichero(Globales.url);
         PrintWriter salida2;

         
        File prueba = new File(Globales.urlPrueba);
        if (!prueba.exists()) {
            prueba.createNewFile();
        }
        String separarRenglones[] = contenido.split("#");
        salida2 = new PrintWriter(new BufferedWriter(new FileWriter(prueba)));
        for (int i = 0; i < separarRenglones.length; i++) {
            salida2.println(separarRenglones[i]);
        }

        
        
            
        

        if (salida2 != null) {
            salida2.close();

        }

        
        
    }
    
    
}
