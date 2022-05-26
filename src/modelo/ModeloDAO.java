package es.alejandro.programacion.modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alejandro
 */
public class ModeloDAO {

    private static Producto registroProducto(ResultSet rs) {
        Producto producto = new Producto();
        producto.setCodigo(rs.getInt("codigo"));
        producto.setNombre(rs.getString("nombre"));

    // }

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

    public static List<Producto> getProductos(int page, int tamanioPage) throws Exception {
        List<Producto> productos = new ArrayList<>();
        ModeloDAO modeloDAO = new ModeloDAO();
        String sql = "select * from producto limit" + (page - 1 * tamanioPage) + ", " + tamanioPage;
        PreparedStatement ps = Conexion.getPreparedStatement(sql);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            productos.add(modeloDAO.registroProducto(rs));
        }
        return productos;
    }

    public static Producto getProducto(int codigo) throws Exception {

        String sql = "Select * from producto where codigo=" + codigo;
        PreparedStatement ps = Conexion.getPreparedStatement(sql);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        if (rs.next()) {
            return ModeloDAO.registroProducto(rs);
        } else {
            throw new Exception("GetProducto. No ha podido realizarse la consulta");
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
        Conexion.getConexionBdFerreteria();

        ResultSet consulta = Conexion.consulta("select count(nombre) from producto;");
        System.out.println(consulta);
    }
}
