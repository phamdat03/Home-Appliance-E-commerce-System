package data.impl;

import model.Category; // Thay đổi package phù hợp
import data.dao.CategoryDao;
import data.driver.MySQLDriver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryImpl implements CategoryDao {

    Connection con = MySQLDriver.getConnection();
    private static final Logger LOGGER = Logger.getLogger(CategoryImpl.class.getName());

    // Phương thức helper để ánh xạ ResultSet sang đối tượng Category
    private Category mapResultSetToCategory(ResultSet rs) throws SQLException {
        Category cat = new Category();
        cat.setCategoryId(rs.getInt("category_id"));
        cat.setName(rs.getString("name"));
        cat.setDescription(rs.getString("description"));
        return cat;
    }

    // LẤY TẤT CẢ DANH MỤC (Phương thức quan trọng cho Navbar)
    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT category_id, name, description FROM categories";
        
        try (PreparedStatement sttm = con.prepareStatement(sql);
             ResultSet rs = sttm.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapResultSetToCategory(rs));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách danh mục", ex);
        }
        return list;
    }

    // TÌM DANH MỤC THEO ID
    @Override
    public Category findById(int id) {
        String sql = "SELECT category_id, name, description FROM categories WHERE category_id = ?";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setInt(1, id);
            try (ResultSet rs = sttm.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCategory(rs);
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm danh mục theo ID", ex);
        }
        return null;
    }

    // THÊM, CẬP NHẬT, XÓA (Chỉ để mẫu)
    @Override
    public boolean save(Category category) { /*...*/ return false; }
    @Override
    public boolean update(Category category) { /*...*/ return false; }
    @Override
    public boolean delete(int id) { /*...*/ return false; }
}