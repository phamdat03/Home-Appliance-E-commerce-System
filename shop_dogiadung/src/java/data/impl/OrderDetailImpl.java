package data.impl;

import data.dao.OrderDetailDao;
import data.driver.MySQLDriver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;

public class OrderDetailImpl implements OrderDetailDao {

    private Connection getCon() {
        return MySQLDriver.getConnection();
    }

    @Override
    public boolean addOrderDetail(OrderDetail od) {
        String sql = "INSERT INTO order_details(order_id, product_id, quantity, price) VALUES (?,?,?,?)";

        try (Connection con = getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, od.getOrderId());
            ps.setInt(2, od.getProductId());
            ps.setInt(3, od.getQuantity());
            ps.setDouble(4, od.getPrice());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOrderDetailQuantity(int orderDetailId, int quantity) {
        String sql = "UPDATE order_details SET quantity=? WHERE order_detail_id=?";

        try (Connection con = getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, orderDetailId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteOrderDetail(int orderDetailId) {
        String sql = "DELETE FROM order_details WHERE order_detail_id=?";

        try (Connection con = getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderDetailId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<OrderDetail> getOrderDetails(int orderId) {
        List<OrderDetail> list = new ArrayList<>();

        String sql =
            "SELECT od.order_detail_id, od.order_id, od.product_id, od.quantity, od.price, " +
            "p.name AS product_name, p.image_url AS product_image " +
            "FROM order_details od " +
            "JOIN products p ON od.product_id = p.product_id " +
            "WHERE od.order_id = ?";

        try (Connection con = getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetail od = new OrderDetail();

                    od.setOrderDetailId(rs.getInt("order_detail_id"));
                    od.setOrderId(rs.getInt("order_id"));
                    od.setProductId(rs.getInt("product_id"));
                    od.setQuantity(rs.getInt("quantity"));
                    od.setPrice(rs.getDouble("price"));

                    od.setProductName(rs.getString("product_name"));

                    String img = rs.getString("product_image");

                    if (img != null && !img.isEmpty()) {
                        // KHÔNG encode — giữ nguyên tên file
                        od.setProductImage("/assets/images/" + img);
                    } else {
                        od.setProductImage("/assets/images/no-image.png");
                    }

                    list.add(od);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public OrderDetail getOrderDetailByOrderAndProduct(int orderId, int productId) {

        String sql =
            "SELECT od.order_detail_id, od.order_id, od.product_id, od.quantity, od.price, " +
            "p.name AS product_name, p.image_url AS product_image " +
            "FROM order_details od " +
            "JOIN products p ON od.product_id = p.product_id " +
            "WHERE od.order_id = ? AND od.product_id = ?";

        try (Connection con = getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    OrderDetail od = new OrderDetail();

                    od.setOrderDetailId(rs.getInt("order_detail_id"));
                    od.setOrderId(rs.getInt("order_id"));
                    od.setProductId(rs.getInt("product_id"));
                    od.setQuantity(rs.getInt("quantity"));
                    od.setPrice(rs.getDouble("price"));

                    od.setProductName(rs.getString("product_name"));

                    String img = rs.getString("product_image");

                    if (img != null && !img.isEmpty()) {
                        od.setProductImage("/assets/images/" + img);
                    } else {
                        od.setProductImage("/assets/images/no-image.png");
                    }

                    return od;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
