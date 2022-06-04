
import modelo.*;
import vista.VistaProducto;
import controlador.ControladorProducto;
import modelo.Conexion;
import modelo.ProductoDAO;
/*** @author Javier Criado, 15/05/2022:20:10:30  ***/
public class FerreteriaApp {
    public static void main(String args[]) throws Exception {
        VistaProducto vista=new VistaProducto();
        ControladorProducto controlador=new ControladorProducto(vista);
        controlador.inicia();

    }
}
