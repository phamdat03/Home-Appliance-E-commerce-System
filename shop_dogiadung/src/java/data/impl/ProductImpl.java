package data.impl;

import model.Product;
import data.dao.ProductDao;
import data.driver.MySQLDriver;
import static data.driver.MySQLDriver.getConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductImpl implements ProductDao {

    Connection con = MySQLDriver.getConnection();
    private static final Logger LOGGER = Logger.getLogger(ProductImpl.class.getName());

    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductId(rs.getInt("product_id"));
        p.setCategoryId(rs.getInt("category_id"));
        p.setName(rs.getString("name"));
        p.setDescription(rs.getString("description"));
        p.setPrice(rs.getDouble("price"));
        p.setStock(rs.getInt("stock"));
        p.setImageUrl(rs.getString("image_url"));   // GIỮ NGUYÊN TÊN FILE ẢNH
        p.setCreatedAt(rs.getTimestamp("created_at"));
        return p;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY created_at DESC";

        try (PreparedStatement sttm = con.prepareStatement(sql);
             ResultSet rs = sttm.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy tất cả sản phẩm", ex);
        }
        return list;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ? ORDER BY created_at DESC";

        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setInt(1, categoryId);

            try (ResultSet rs = sttm.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToProduct(rs));
                }
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy sản phẩm theo danh mục", ex);
        }

        return list;
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {

            sttm.setInt(1, id);
            ResultSet rs = sttm.executeQuery();

            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi findById()", ex);
        }

        return null;
    }


    // --------------------- UPDATE STOCK -------------------------
    public boolean updateStock(int productId, int newStock) {
        String sql = "UPDATE products SET stock=? WHERE product_id=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newStock);
            ps.setInt(2, productId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // -------------------- SEARCH PRODUCT ------------------------
    public List<Product> searchProduct(String keyword) {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE name LIKE ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToProduct(rs));  // DÙNG LẠI HÀM MAP
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // Chưa dùng – để nguyên
    @Override
    public boolean save(Product p) { return false; }

    @Override
    public boolean update(Product p) { return false; }

    @Override
    public boolean delete(int id) { return false; }
}
