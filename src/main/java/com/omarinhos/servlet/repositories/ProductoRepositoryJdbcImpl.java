package com.omarinhos.servlet.repositories;

import com.omarinhos.servlet.configs.MysqlConn;
import com.omarinhos.servlet.configs.Repository;
import com.omarinhos.servlet.models.entities.Categoria;
import com.omarinhos.servlet.models.entities.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
@RepositoryJdbc
public class ProductoRepositoryJdbcImpl implements CrudRepository<Producto> {

    @Inject
    @MysqlConn
    private Connection conn;
    @Inject
    private Logger log;

    @PostConstruct
    public void iniciar() {
        log.info("iniciando el beans " + this.getClass().getName());
    }

    @PreDestroy
    public void destruir() {
        log.info("destruyendo el beans " + this.getClass().getName());
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p " +
                    " inner join categorias as c on (p.categoria_id = c.id) order by p.id ASC");
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
            rs.close();
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stmt =
                     conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p" +
                " inner join categorias as c on (p.categoria_id = c.id) WHERE p.id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre = ?, precio = ?, sku = ?, categoria_id = ? where id = ?";
        } else {
            sql = "insert into productos (nombre, precio, sku, categoria_id, fecha_registro) values (?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(5, producto.getId());
            } else {
                stmt.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "delete from productos where id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        Categoria c = new Categoria();
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        p.setSku(rs.getString("sku"));
        return p;
    }
}
