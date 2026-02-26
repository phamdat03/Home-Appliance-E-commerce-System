package data.impl;

import data.dao.UserDao;
import data.driver.MySQLDriver;
import data.utils.API;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserImpl implements UserDao {

    private final Connection con = MySQLDriver.getConnection();
    private static final Logger LOGGER = Logger.getLogger(UserImpl.class.getName());

    // Helper: convert ResultSet → User
    private User mapUser(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("user_id"));
        u.setFullName(rs.getString("full_name"));
        u.setEmail(rs.getString("email"));
        u.setPhone(rs.getString("phone"));
        u.setPasswordHash(rs.getString("password_hash")); // CHUẨN
        u.setAddress(rs.getString("address"));
        u.setRole(rs.getString("role"));
        u.setCreatedAt(rs.getTimestamp("created_at"));
        return u;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setInt(1, id);
            try (ResultSet rs = sttm.executeQuery()) {
                if (rs.next()) return mapUser(rs);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi findById()", ex);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setString(1, email);
            try (ResultSet rs = sttm.executeQuery()) {
                if (rs.next()) return mapUser(rs);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi findByEmail()", ex);
        }
        return null;
    }

    @Override
    public boolean checkLogin(String email, String passwordPlain) {

        // HASH mật khẩu nhập từ form
        String hashed = API.getMd5(passwordPlain);

        String sql = "SELECT * FROM users WHERE email = ? AND password_hash = ?";

        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setString(1, email);
            sttm.setString(2, hashed);

            try (ResultSet rs = sttm.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi checkLogin()", ex);
        }

        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (PreparedStatement sttm = con.prepareStatement(sql);
             ResultSet rs = sttm.executeQuery()) {

            while (rs.next()) {
                list.add(mapUser(rs));
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi findAll()", ex);
        }
        return list;
    }

    @Override
    public boolean insertUser(User user) {

        String sql = """
            INSERT INTO users(full_name, email, phone, password_hash, address, role)
            VALUES(?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement sttm = con.prepareStatement(sql)) {

            sttm.setString(1, user.getFullName());
            sttm.setString(2, user.getEmail());
            sttm.setString(3, user.getPhone());

            // LUÔN HASH mật khẩu trước khi lưu
            sttm.setString(4, API.getMd5(user.getPasswordHash()));

            sttm.setString(5, user.getAddress());
            sttm.setString(6, user.getRole());

            return sttm.executeUpdate() > 0;

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi save()", ex);
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        String sql = """
            UPDATE users SET
                full_name = ?, email = ?, phone = ?, address = ?, role = ?
            WHERE user_id = ?
        """;

        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setString(1, user.getFullName());
            sttm.setString(2, user.getEmail());
            sttm.setString(3, user.getPhone());
            sttm.setString(4, user.getAddress());
            sttm.setString(5, user.getRole());
            sttm.setInt(6, user.getUserId());

            return sttm.executeUpdate() > 0;

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi update()", ex);
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setInt(1, id);
            return sttm.executeUpdate() > 0;

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Lỗi delete()", ex);
        }

        return false;
    }
}
