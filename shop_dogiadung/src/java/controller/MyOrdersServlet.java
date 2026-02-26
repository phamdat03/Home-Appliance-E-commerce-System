package controller;

import data.impl.OrderImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.Order;
import model.User;

@WebServlet(name = "MyOrdersServlet", urlPatterns = {"/my-orders"})
public class MyOrdersServlet extends HttpServlet {

    private OrderImpl orderDao = new OrderImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Order> orders = orderDao.findOrdersByUserId(user.getUserId());

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/views/my_orders.jsp").forward(request, response);
    }
}
