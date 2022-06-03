package FerreteriaMVC;

import FerreteriaMVC.vista.VistaProducto;
import FerreteriaMVC.controlador.ControladorProducto;
/*** @author Javier Criado, 15/05/2022:20:10:30  ***/
public class FerreteriaApp {
    public static void main(String args[]) {
        VistaProducto vista=new VistaProducto();
        ControladorProducto controlador=new ControladorProducto(vista);
        controlador.inicia();
    }

}
