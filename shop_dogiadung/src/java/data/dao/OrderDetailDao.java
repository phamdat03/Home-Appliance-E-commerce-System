package data.dao;

import java.util.List;
import model.OrderDetail;

public interface OrderDetailDao {
    boolean addOrderDetail(OrderDetail od); // thêm sản phẩm vào order
    boolean updateOrderDetailQuantity(int orderDetailId, int quantity);
    boolean deleteOrderDetail(int orderDetailId);
    List<OrderDetail> getOrderDetails(int orderId); // lấy chi tiết order
    OrderDetail getOrderDetailByOrderAndProduct(int orderId, int productId); // để kiểm tra sản phẩm đã có
}
