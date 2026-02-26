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
import model.Category;

public interface CategoryDao {
    Category findById(int id);
    List<Category> findAll(); // Phương thức này bị lỗi NullPointer trước đó
    boolean save(Category category);
    boolean update(Category category);
    boolean delete(int id);
}