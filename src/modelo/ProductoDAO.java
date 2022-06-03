package FerreteriaMVC.modelo;
import java.sql.*;
import java.util.*;
/*** @author Javier Criado, 15/05/2022:20:03:14  ***/
public class ProductoDAO {
    /**
     * Convierte un registro de la tabla producto a un objeto Producto
     * @param rs ResulSet obtenido de una consulta
     * @return el objeto que representa al registro obtenido
     * @throws SQLException 
     */
    private static Producto registroProducto(ResultSet rs)  {
        Producto p=new Producto();
        try {
            p.setCodigo(rs.getInt("codigo"));
            p.setNombre(rs.getString("nombre"));
            p.setPrecioVenta(rs.getDouble("precio_venta"));
            p.setPrecioCompra(rs.getDouble("precio_compra"));
            p.setStock(rs.getInt("stock"));
            return p;
        } catch (SQLException ex) {
        }
        return null;
    }
    /**
     * Número total de registros que hay en la tabla producto
     * @return el número de productos que hay en la base de datos
     * @throws Exception 
     */
    public static int numProductos() throws Exception {
        ResultSet rs=Conexion.consulta("SELECT count(*) AS numProds FROM producto");
        if (rs!=null && rs.next()) {
            return rs.getInt("numProds");
        }
        return -1;
    }
    
    /**
     * Devuelve una lista con la página de productos que se le solicita
     * en función del tamaño de ésta
     * @param pag página de productos que queremos obtener
     * @param tamPag tamaño de la página
     * @return la página de productos pedida
     * @throws Exception 
     */
    public static  List<Producto> getProductos(int pag, int tamPag) throws Exception {
        int numProds=ProductoDAO.numProductos();
        if (pag<0 || pag>Math.ceil((double)numProds/tamPag)) {
            throw new Exception("getProductos: Error número de página no válido: "+pag);
        }
        String sql=String.format("SELECT * FROM producto ORDER BY codigo LIMIT %d,%d",(pag-1)*tamPag,tamPag);
		try {
			List<Producto> res=new ArrayList<>();
			ResultSet rs=Conexion.consulta(sql);
			if (rs!=null) { // la consulta ha generado un result set
				Producto p;
				while(rs.next()) {
					p=ProductoDAO.registroProducto(rs);
					res.add(p);
				}
			}
			Conexion.close();
			return res; // si ejecutamos un insert, delete o update la lista estará vacía
		} catch (Exception e) {
			throw new Exception(e.getMessage()+"\nEjecutando: "+sql+"\n");
		}
	}
    
    /**
     * Obtiene el registro producto con clave codigo y lo devuelve como
     * un objeto de la clase Producto
     * @param codigo
     * @return el producto de la tabla de productos cuyo clave sea codigo, null si no hay éxito
     * @throws Exception 
     */
    public static Producto getProducto(int codigo) throws Exception {
        String sql="SELECT * FROM producto WHERE codigo=?";
        PreparedStatement ps=Conexion.getPreparedStatement(sql);
        ps.setInt(1, codigo);
        if (!ps.execute()) {
            throw new Exception("getProducto: Error accediendo a la tabla de productos. Código de producto: "+codigo);
        }
        ResultSet rs=ps.getResultSet();
        if (rs.next()) {
            return ProductoDAO.registroProducto(rs);
        }
        //throw new Exception("getProducto: Error código de producto "+codigo+" no encontrado.");
        return null; // devolvemos null si el producto no se encuentra
    }
    
    /**
     * Actualiza el producto cuya clave sea p.getCodigo() con los datos
     * que conlleva el objeto p en sus variables miembro
     * @param p producto a actualizar
     * @return 0 si error 1 si éxito (número de filas afectadas)
     * @throws Exception 
     */
    public static int actualizarProducto(Producto p) throws Exception {
        String sql="UPDATE producto SET codigo=?, nombre=?, precio_compra=?, precio_venta=?, stock=? WHERE codigo=?";
        PreparedStatement ps=Conexion.getPreparedStatement(sql);
        ps.setInt(1,p.getCodigo());
        ps.setString(2,p.getNombre());
        ps.setDouble(3,p.getPrecioCompra());
        ps.setDouble(4,p.getPrecioVenta());
        ps.setInt(5,p.getStock());
        ps.setInt(6,p.getCodigo());
        return ps.executeUpdate();
    }
    
    /**
     * Inserta un nuevo registro con los datos del Producto p
     * @param p producto para insertar
     * @return 0 si error 1 si éxito (número de filas afectadas)
     * @throws Exception 
     */
    public static int insertarProducto(Producto p) throws Exception {
        String sql="INSERT INTO producto VALUES(?,?,?,?,?)";
        PreparedStatement ps=Conexion.getPreparedStatement(sql);
        ps.setInt(1,p.getCodigo());
        ps.setString(2,p.getNombre());
        ps.setDouble(3,p.getPrecioCompra());
        ps.setDouble(4,p.getPrecioVenta());
        ps.setInt(5,p.getStock());
        return ps.executeUpdate();
    }
    /**
     * Borra el registro del producto cuya clave sea codigo
     * @param codigo registro a borrar
     * @return 0 si error, 1 si éxito (número de filas afectadas)
     * @throws Exception 
     */
    public static int borrarProducto(int codigo) throws Exception {
        String sql="DELETE FROM producto WHERE codigo=?";
        PreparedStatement ps=Conexion.getPreparedStatement(sql);
        ps.setInt(1, codigo);
        return ps.executeUpdate();
    }
    
    /**
     * Busca el siguiente producto
     * @param codigo
     * @return
     * @throws Exception 
     */
    public static Producto getSiguienteProducto(int codigo) throws Exception {
        String sql="select * from producto where codigo > ? order by codigo asc limit 1";
        PreparedStatement ps=Conexion.getPreparedStatement(sql);
        ps.setInt(1, codigo);
        if (!ps.execute()) {
            throw new Exception("getProducto: Error, no hay productos registrados.");
        }
        ResultSet rs=ps.getResultSet();
        if (rs.next()) {
            return ProductoDAO.registroProducto(rs);
        }
        //throw new Exception("getProducto: Error código de producto "+codigo+" no encontrado.");
        return null; // devolvemos null si el producto no se encuentra
    }
    
    /**
     * Busca el anterior producto
     * @param codigo
     * @return
     * @throws Exception 
     */
    public static Producto getAnteriorProducto(int codigo) throws Exception {
        String sql="select * from producto where codigo < ? order by codigo desc limit 1";
        PreparedStatement ps=Conexion.getPreparedStatement(sql);
        ps.setInt(1, codigo);
        if (!ps.execute()) {
            throw new Exception("getProducto: Error, no hay productos registrados.");
        }
        ResultSet rs=ps.getResultSet();
        if (rs.next()) {
            return ProductoDAO.registroProducto(rs);
        }
        //throw new Exception("getProducto: Error código de producto "+codigo+" no encontrado.");
        return null; // devolvemos null si el producto no se encuentra
    }
    
    /**
     * Consigue el primer producto de la base de datos
     * @return Producto, El primer producto de la base de datos
     * @throws Exception 
     */
    public static Producto getPrimerProducto() throws Exception {
        String sql="SELECT * FROM producto order by codigo asc limit 1";
        PreparedStatement ps=Conexion.getPreparedStatement(sql);
        if (!ps.execute()) {
            throw new Exception("getProducto: Error, no hay productos registrados.");
        }
        ResultSet rs=ps.getResultSet();
        if (rs.next()) {
            return ProductoDAO.registroProducto(rs);
        }
        //throw new Exception("getProducto: Error código de producto "+codigo+" no encontrado.");
        return null; // devolvemos null si el producto no se encuentra
    }
    /**
     * Consigue el ultimo producto de la base de datos
     * @return Producto, El ultimo producto de la tabla
     * @throws SQLException
     * @throws Exception 
     */
    public static Producto getUltimoProducto() throws SQLException, Exception {
        String sql="SELECT * FROM producto order by codigo desc limit 1";
        PreparedStatement ps=Conexion.getPreparedStatement(sql);
        if (!ps.execute()) {
            throw new Exception("getProducto: Error, no hay productos registrados.");
        }
        ResultSet rs=ps.getResultSet();
        if (rs.next()) {
            return ProductoDAO.registroProducto(rs);
        }
        //throw new Exception("getProducto: Error código de producto "+codigo+" no encontrado.");
        return null; // devolvemos null si el producto no se encuentra
    }
}
