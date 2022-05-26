package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Alejandro
 */
public class ModeloDAO {

    private Producto registroProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setCodigo(rs.getInt("codigo"));
        producto.setNombre(rs.getString("nombre"));
        return producto;
    }

    public static int numProductos() throws Exception {
        ResultSet consulta = Conexion.consulta("select count(*) as cantidad from producto;");
        PreparedStatement ps = Conexion.getPreparedStatement("select * from producto");
        ps.execute();
        ps.getResultSet();
        ResultSet rs = ps.getResultSet();
        if (rs.next()) {
            return rs.getInt("cantidad");
        } else {
            throw new Exception("numProductos. No se ha podido realizar la consulta");
        }
    }

    public static Producto getProductos(int page, int tamanioPage) throws Exception {
        ModeloDAO modeloDAO = new ModeloDAO();
        String sql = "Select * from producto where codigo=1";
        PreparedStatement ps = Conexion.getPreparedStatement(sql);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        if (rs.next()) {
            return modeloDAO.registroProducto(rs);
        } else {
            throw new Exception("getProductos. No ha podido realizarse la consulta");
        }
    }

    public static Producto getProducto(int codigo) {

        try {

        } catch (Exception e) {

        }
    }

    public static int actualizarProducto(Producto producto) {

    }

    public static int insertarProducto(Producto producto) {

    }

    public static int borrarProducto(int codigo) {
        try {
            String sql = "DELETE FROM producto WHERE codigo=" + codigo;
            PreparedStatement ps = Conexion.getPreparedStatement(sql);
            return ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("No se ha podido borrar el producto");
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        getProductos(1, 2);
    }
}
