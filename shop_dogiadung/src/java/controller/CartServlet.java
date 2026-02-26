package controller;

import data.impl.ProductImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    private final ProductImpl productDao = new ProductImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Clear cart nếu có param
        if ("true".equals(request.getParameter("clear"))) {
            session.setAttribute("cart", new ArrayList<Product>());
        }

        request.setAttribute("title", "Cart Detail");
        request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        // Kiểm tra đăng nhập
        if (user == null) {
            // Lưu trang hiện tại để redirect sau login
            String redirectUrl = request.getParameter("redirect");
            if (redirectUrl == null || redirectUrl.isEmpty()) {
                redirectUrl = request.getContextPath() + "/home";
            }
            session.setAttribute("redirectAfterLogin", redirectUrl);
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Xử lý hành động
        String action = request.getParameter("action");
        int productId = request.getParameter("productId") == null ? -1 : Integer.parseInt(request.getParameter("productId"));

        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        switch (action) {

            case "add":
                doAdd(cart, productId);
                session.setAttribute("cart", cart);

                String redirectUrl = request.getParameter("redirect");
                if (redirectUrl == null || redirectUrl.isEmpty()) {
                    redirectUrl = request.getContextPath() + "/home";
                }
                response.sendRedirect(redirectUrl);
                return;

            case "update":
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                doUpdate(cart, productId, quantity);
                break;

            case "delete":
                doDelete(cart, productId);
                break;

            case "checkout":
                // Chuyển sang trang checkout
                session.setAttribute("cart", cart);
                response.sendRedirect(request.getContextPath() + "/checkout");
                return;
        }

        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void doAdd(List<Product> cart, int productId) {
        Product product = productDao.findById(productId);
        if (product == null) return;

        for (Product p : cart) {
            if (p.getProductId() == productId) {
                p.setQuantity(p.getQuantity() + 1);
                return;
            }
        }

        Product copy = new Product(product);
        copy.setQuantity(1);
        cart.add(copy);
    }

    private void doUpdate(List<Product> cart, int productId, int quantity) {
        if (quantity <= 0) return;
        for (Product p : cart) {
            if (p.getProductId() == productId) {
                p.setQuantity(quantity);
                return;
            }
        }
    }

    private void doDelete(List<Product> cart, int productId) {
        cart.removeIf(p -> p.getProductId() == productId);
    }
}
