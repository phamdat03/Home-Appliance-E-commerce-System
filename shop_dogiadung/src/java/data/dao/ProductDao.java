/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data.dao;

import java.util.List;
import model.Product;

/**
 *
 * @author Dat Pham
 */
public interface ProductDao {
    Product findById(int id);
    List<Product> findAll();
    List<Product> findByCategoryId(int categoryId); // Lấy sản phẩm theo danh mục
    List<Product> searchProduct(String keyword);
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(int id);
}