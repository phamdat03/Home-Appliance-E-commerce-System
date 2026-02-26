package controller;

import data.impl.OrderImpl;
import data.impl.OrderDetailImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.User;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrderImpl orderDao = new OrderImpl();
    private OrderDetailImpl detailDao = new OrderDetailImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            req.setAttribute("error", "Giỏ hàng của bạn đang trống!");
            req.getRequestDispatcher("/views/cart.jsp").forward(req, resp);
            return;
        }

        // Tính tổng tiền
        double total = 0;
        for (Product p : cart) {
            total += p.getPrice() * p.getQuantity();
        }

        req.setAttribute("cart", cart);
        req.setAttribute("total", total);

        req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
    }

  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");
    HttpSession session = req.getSession();

    User user = (User) session.getAttribute("user");
    if (user == null) {
        resp.sendRedirect(req.getContextPath() + "/login");
        return;
    }
    int userId = user.getUserId();

    List<Product> cart = (List<Product>) session.getAttribute("cart");
    if (cart == null || cart.isEmpty()) {
        req.setAttribute("error", "Giỏ hàng rỗng, không thể thanh toán!");
        req.getRequestDispatcher("/views/cart.jsp").forward(req, resp);
        return;
    }

    double total = 0;
    for (Product p : cart) total += p.getPrice() * p.getQuantity();

    String address = req.getParameter("address");

    int orderId = orderDao.createOrder(userId);

    for (Product p : cart) {
        OrderDetail od = new OrderDetail();
        od.setOrderId(orderId);
        od.setProductId(p.getProductId());
        od.setQuantity(p.getQuantity());
        od.setPrice(p.getPrice());
        detailDao.addOrderDetail(od);
    }

    // Tạo object Order đầy đủ
    Order order = new Order();
    order.setOrderId(orderId);
    order.setTotalAmount(total);
    order.setShippingAddress(address);
    order.setStatus("COMPLETED");
    order.setOrderDate(new java.util.Date());

    orderDao.updateOrderInfo(order);
    orderDao.updateOrderStatus(orderId, "COMPLETED");

    session.removeAttribute("cart");

    // Truyền object Order sang JSP
    req.setAttribute("order", order);

    req.getRequestDispatcher("/views/checkout_success.jsp").forward(req, resp);
}

}