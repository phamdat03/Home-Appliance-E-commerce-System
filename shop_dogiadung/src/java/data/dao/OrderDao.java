package data.dao;

import java.util.List;
import model.Order;

public interface OrderDao {
    Order getPendingOrder(int userId); // giỏ hàng hiện tại
    int createOrder(int userId); // tạo order pending mới, trả về order_id
    boolean updateOrderStatus(int orderId, String status);
    List<Order> getOrdersByUser(int userId); // lịch sử đơn hàng
    Order getOrderById(int orderId);
}
