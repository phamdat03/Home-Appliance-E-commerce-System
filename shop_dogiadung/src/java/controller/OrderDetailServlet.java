package controller;

import data.impl.OrderDetailImpl;
import data.impl.OrderImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.User;

@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/order-detail"})
public class OrderDetailServlet extends HttpServlet {

    private OrderImpl orderDao = new OrderImpl();
    private OrderDetailImpl detailDao = new OrderDetailImpl();

    @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");

    // Chưa login thì chuyển login
    if (user == null) {
        resp.sendRedirect(req.getContextPath() + "/login");
        return;
    }

    // Lấy orderId từ URL
    String idParam = req.getParameter("id");

    if (idParam == null || idParam.isEmpty()) {
        // Không có id → quay về danh sách đơn
        resp.sendRedirect(req.getContextPath() + "/my-orders");
        return;
    }

    int orderId;
    try {
        orderId = Integer.parseInt(idParam);
    } catch (NumberFormatException e) {
        resp.sendRedirect(req.getContextPath() + "/my-orders");
        return;
    }

    // Kiểm tra đơn có thuộc user không
    Order order = orderDao.getOrderById(orderId);
    if (order == null || order.getUserId() != user.getUserId()) {
        resp.sendRedirect(req.getContextPath() + "/my-orders");
        return;
    }

    // Lấy danh sách chi tiết
    List<OrderDetail> details = detailDao.getOrderDetails(orderId);

    req.setAttribute("order", order);
    req.setAttribute("details", details);

    req.getRequestDispatcher("/views/order_detail.jsp").forward(req, resp);
}

}
