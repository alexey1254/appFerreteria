package controlador;
import modelo.*;
import java.awt.event.*;
import vista.VistaProducto;


/*** @author Javier Criado, 23/05/2022:19:51:00  ***/
public class ControladorProducto implements ActionListener {
    private VistaProducto vista;
    public ControladorProducto(VistaProducto vista) {
        this.vista=vista;
    }
    
    /**
     * Comprueba si todos los campos estan vacíos
     * @return True, si todos están vacios
     */
    private boolean camposVacios() {
        return  this.vista.getCodigo().length()==0 ||
                this.vista.getNombre().length()==0 ||
                this.vista.getPrecioCompra().length()==0 ||
                this.vista.getPrecioVenta().length()==0 ||
                this.vista.getStock().length()==0;
                
    }
    
    
    public Producto getVistaProducto() {
        int codigo; String nombre; double precioCompra, precioVenta; 
        int stock;
        try {
            codigo=Integer.parseInt(this.vista.getCodigo());
        } catch (NumberFormatException ex) {
            this.vista.mostrarMensaje("Error en el código del producto");
            return null;
        }
        nombre=this.vista.getNombre();
        try {
            precioCompra=Double.parseDouble(this.vista.getPrecioCompra());
        } catch(NumberFormatException ex) {
            this.vista.mostrarMensaje("Error en el Precio de Compra del producto");
            return null;
        }
        try {
            precioVenta=Double.parseDouble(this.vista.getPrecioVenta());
        } catch(NumberFormatException ex) {
            this.vista.mostrarMensaje("Error en el Precio de Venta del producto");
            return null;
        }
        try {
            stock=Integer.parseInt(this.vista.getStock());
        } catch (NumberFormatException ex) {
            this.vista.mostrarMensaje("Error en el stock del producto");
            return null;
        }
        return new Producto(codigo,nombre,precioCompra,precioVenta,stock);
    }
    
    
    public void altaModificacionProducto(String mensaje) {
        if (!camposVacios()) {
            Producto p=this.getVistaProducto();
            if (p!=null) {
                if (p.getNombre().length()>20) {
                    this.vista.mostrarMensaje("Error la longitud del nombre no puede exceder los 20 caracteres.");
                    return;
                }
                try {
                    if (mensaje.equals("introducido")) {
                        ProductoDAO.insertarProducto(p);
                    } else if (mensaje.equals("modificado")) {
                        ProductoDAO.actualizarProducto(p);
                    }
                    this.vista.mostrarMensaje("Producto "+mensaje+" correctamente");
                } catch ( java.sql.SQLIntegrityConstraintViolationException ex) {
                    this.vista.mostrarMensaje("Error la clave o el nombre del producto está duplicado");
                } catch (Exception ex) {
                    this.vista.mostrarMensaje(ex.getMessage());
                }
            }
        } else {
            this.vista.mostrarMensaje("Error alguno de los campos está vacío");
        }
    }
    
    /**
     * Cuando un producto es introducido correctamente
     */
    public void altaProducto() {
        this.altaModificacionProducto("introducido");
    }
    public void modificarProducto() {
        this.altaModificacionProducto("modificado");
    }
    public void _altaProducto() {
        if (!camposVacios()) {
            Producto p=this.getVistaProducto();
            if (p!=null) {
                try {
                    ProductoDAO.insertarProducto(p);
                    this.vista.mostrarMensaje("Producto introducido correctamente");
                } catch ( java.sql.SQLIntegrityConstraintViolationException ex) {
                    this.vista.mostrarMensaje("Error la clave o el nombre del producto está duplicado");
                } catch (Exception ex) {
                    this.vista.mostrarMensaje(ex.getMessage());
                }
            } 
        } else {
            this.vista.mostrarMensaje("Error alguno de los campos está vacío");
        }
    }
    
    /**
     * Modifica un producto
     */
    public void _modificarProducto() {
        if (!camposVacios()) {
            Producto p=this.getVistaProducto();
            if (p!=null) {
                try {
                    ProductoDAO.actualizarProducto(p);
                    this.vista.mostrarMensaje("Producto modificado correctamente");
                } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
                    this.vista.mostrarMensaje("Error la clave o el nombre del producto está duplicado");
                } catch (Exception ex) {
                    this.vista.mostrarMensaje(ex.getMessage());
                }
            }
        } else {
            this.vista.mostrarMensaje("Error alguno de los campos está vacío");
        }
        
    }
    
    /**
     * Busca un producto
     */
    public void buscarProducto() {
        if (this.vista.getBuscar().length()!=0) {
            try {
                int codigo=Integer.parseInt(this.vista.getBuscar());
                Producto p;
                p=ProductoDAO.getProducto(codigo);
                if (p==null) {
                    this.vista.mostrarMensaje("Producto no encontrado");
                } else {
                    this.vista.setCodigo(""+p.getCodigo());
                    this.vista.setNombre(p.getNombre());
                    this.vista.setPrecioCompra(""+p.getPrecioCompra());
                    this.vista.setPrecioVenta(""+p.getPrecioVenta());
                    this.vista.setStock(""+p.getStock());
                }
            } catch (java.lang.NumberFormatException ex) {
                this.vista.mostrarMensaje("Error el código del producto a buscar no es correcto");
            } catch (Exception ex) {
                this.vista.mostrarMensaje(ex.getMessage());
            }

        } else {
            this.vista.mostrarMensaje("El campo de búsqueda está vacío");
        }
    }
    
    /**
     * Le da de baja a un producto
     */
    public void bajaProducto() {
        try {
            int codigo=Integer.parseInt(this.vista.getCodigo());
            if (ProductoDAO.borrarProducto(codigo)==0) {
                this.vista.mostrarMensaje("El producto no existe: "+codigo);
            } else {
                this.vista.mostrarMensaje("Producto borrado correctamente");
                this.vista.limpiarCampos();
            }
        } catch (NumberFormatException ex) {
            this.vista.mostrarMensaje("Código de producto no válido.");
        } catch (Exception ex) {
            this.vista.mostrarMensaje(ex.getMessage());
        }
    }
    
    /**
     * Busca el primer producto de la base de datos
     */
    public void buscarPrimero() {
        try {
            Producto producto;
            producto = ProductoDAO.getPrimerProducto();
                if (producto==null) {
                    this.vista.mostrarMensaje("Producto no encontrado");
                } else {
                    this.vista.setCodigo(""+producto.getCodigo());
                    this.vista.setNombre(producto.getNombre());
                    this.vista.setPrecioCompra(""+producto.getPrecioCompra());
                    this.vista.setPrecioVenta(""+producto.getPrecioVenta());
                    this.vista.setStock(""+producto.getStock());
                }
        } catch (Exception ex) {
            this.vista.mostrarMensaje("No hay un primer producto");
        }
    }
    
    /**
     * Busca el ultimo producto de la base de datos
     */
    public void buscarUltimo() {
        try {
            Producto producto;
            producto = ProductoDAO.getUltimoProducto();
                if (producto==null) {
                    this.vista.mostrarMensaje("Producto no encontrado");
                } else {
                    this.vista.setCodigo(""+producto.getCodigo());
                    this.vista.setNombre(producto.getNombre());
                    this.vista.setPrecioCompra(""+producto.getPrecioCompra());
                    this.vista.setPrecioVenta(""+producto.getPrecioVenta());
                    this.vista.setStock(""+producto.getStock());
                }
        } catch (Exception ex) {
            this.vista.mostrarMensaje("No hay un ultimo producto");
        }
    }
    
    /**
     * Busca el producto anterior o posterior
     * @param anteriorOposterior -1 anterior, otro numero, posterior
     */
    public void anteriorOSiguienteProducto(int anteriorOposterior) {
        try {
            int codigo=Integer.parseInt(this.vista.getCodigo());
            Producto producto = null;
            String mensaje = "";
            if(anteriorOposterior==-1) {
                producto=ProductoDAO.getAnteriorProducto(codigo);
                mensaje = "No hay más productos anteriores.";
            } else {
                producto= ProductoDAO.getSiguienteProducto(codigo);
                mensaje = "No hay mas productos posteriores";
            }
            if (producto==null) {
                this.vista.mostrarMensaje(mensaje);
            } else {
                this.vista.setCodigo(""+producto.getCodigo());
                this.vista.setNombre(producto.getNombre());
                this.vista.setPrecioCompra(""+producto.getPrecioCompra());
                this.vista.setPrecioVenta(""+producto.getPrecioVenta());
                this.vista.setStock(""+producto.getStock());
            }
        } catch (java.lang.NumberFormatException ex) {
            this.vista.mostrarMensaje("Error el código del producto a buscar no es correcto");
        } catch (Exception ex) {
            this.vista.mostrarMensaje(ex.getMessage());
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evento)  {
        if (evento.getActionCommand().equals("Alta")) {
            this.altaProducto();
        } else if (evento.getActionCommand().equals("Baja")) {
            this.bajaProducto();
        } else if (evento.getActionCommand().equals("Modificar")) {
            this.modificarProducto();
        } else if (evento.getActionCommand().equals("buscarPrimero")) {
            this.buscarPrimero();
        } else if (evento.getActionCommand().equals("buscarUltimo")) {
            this.buscarUltimo();
        } else if (evento.getActionCommand().equals("buscarAnterior")) {
            this.anteriorOSiguienteProducto(-1);
        } else if (evento.getActionCommand().equals("buscarSiguiente")) {
            this.anteriorOSiguienteProducto(1);
        } else { // if (evento.getActionCommand().equals("Buscar"))
            this.buscarProducto();
        }
    }
    
    /**
     * Inicia la app con interfaz grafica
     */
    public void inicia() {
        this.vista.setControlador(this);
        this.vista.setVisible(true);
    }
}
