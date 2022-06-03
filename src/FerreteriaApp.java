package FerreteriaMVC;

import FerreteriaMVC.vista.VistaProducto;
import FerreteriaMVC.controlador.ControladorProducto;
import FerreteriaMVC.modelo.Conexion;
import FerreteriaMVC.modelo.ProductoDAO;
import static FerreteriaMVC.modelo.ProductoDAO.getPrimerProducto;
/*** @author Javier Criado, 15/05/2022:20:10:30  ***/
public class FerreteriaApp {
    public static void main(String args[]) throws Exception {
        VistaProducto vista=new VistaProducto();
        ControladorProducto controlador=new ControladorProducto(vista);
        controlador.inicia();
        /*Conexion.getConexionBdFerreteria();
        System.out.println(ProductoDAO.getPrimerProducto());
        System.out.println(ProductoDAO.getUltimoProducto());
        System.out.println(ProductoDAO.getSiguienteProducto(5));
        System.out.println(ProductoDAO.getAnteriorProducto(5));*/
    }

}
