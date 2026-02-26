package data.impl;

import data.dao.OrderDao;
import data.driver.MySQLDriver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Order;

public class OrderImpl implements OrderDao {

    @Override
    public Order getPendingOrder(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ? AND status = 'PENDING'";

        try (Connection con = MySQLDriver.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setShippingAddress(rs.getString("shipping_address"));
                return order;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int createOrder(int userId) {
        String sql = """
            INSERT INTO orders (user_id, order_date, status, total_amount)
            VALUES (?, NOW(), 'PENDING', 0)
        """;

        try (Connection con = MySQLDriver.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, userId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // trả order_id mới
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection con = MySQLDriver.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Order> getOrdersByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

        try (Connection con = MySQLDriver.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setStatus(rs.getString("status"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setShippingAddress(rs.getString("shipping_address"));

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

   public boolean updateOrderInfo(Order order) {
    String sql = "UPDATE orders SET total_amount = ?, shipping_address = ? WHERE order_id = ?";

    try (Connection con = MySQLDriver.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDouble(1, order.getTotalAmount());
        ps.setString(2, order.getShippingAddress());
        ps.setInt(3, order.getOrderId());

        return ps.executeUpdate() > 0;

    } catch (Exception e) { e.printStackTrace(); }

    return false;
}
   
   public List<Order> findOrdersByUserId(int userId) {
    List<Order> list = new ArrayList<>();

    String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";

    try (Connection con = MySQLDriver.getConnection();
         PreparedStatement sttm = con.prepareStatement(sql)) {

        sttm.setInt(1, userId);
        ResultSet rs = sttm.executeQuery();

        while (rs.next()) {
            Order o = new Order();
            o.setOrderId(rs.getInt("order_id"));
            o.setUserId(rs.getInt("user_id"));
            o.setOrderDate(rs.getTimestamp("order_date"));
            o.setTotalAmount(rs.getDouble("total_amount"));
            o.setShippingAddress(rs.getString("shipping_address"));
            o.setStatus(rs.getString("status"));
            list.add(o);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}

   @Override
public Order getOrderById(int orderId) {
    String sql = "SELECT * FROM orders WHERE order_id = ?";

    try (Connection con = MySQLDriver.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setUserId(rs.getInt("user_id"));
            order.setOrderDate(rs.getTimestamp("order_date"));
            order.setStatus(rs.getString("status"));
            order.setTotalAmount(rs.getDouble("total_amount"));
            order.setShippingAddress(rs.getString("shipping_address"));
            return order;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}


}
