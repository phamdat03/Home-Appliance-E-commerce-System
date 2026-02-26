/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data.dao;

/**
 *
 * @author Dat Pham
 */
import java.util.List;
import model.User;

public interface UserDao {
    User findById(int id);
    User findByEmail(String email); // Thường dùng cho chức năng đăng nhập
    boolean checkLogin(String email, String passwordPlain);
    List<User> findAll();
    boolean insertUser(User user); // Thêm mới
    boolean update(User user);
    boolean delete(int id);
}