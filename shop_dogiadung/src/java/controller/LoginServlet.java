package controller;

import data.dao.UserDao;
import data.impl.UserImpl;
import data.utils.API;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final UserDao userDao = new UserImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("title", "Login Page");
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String emailPhone = request.getParameter("emailphone");
        String password = request.getParameter("password");

        // Nếu có ký tự '@' thì là email
        boolean isEmail = emailPhone.contains("@");

        try {

            // 1. Check login
            boolean ok = userDao.checkLogin(emailPhone, password);

            if (!ok) {
                request.setAttribute("error", "Sai email/số điện thoại hoặc mật khẩu.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                return;
            }

            // 2. Lấy thông tin User (phải tìm bằng email)
            User user;

            if (isEmail) {
                user = userDao.findByEmail(emailPhone); // Đúng theo UserDao cũ
            } else {
                // *** DAO cũ KHÔNG CÓ findByPhone → bạn phải thêm ***
                // tạm thời: không dùng phone để đăng nhập
                request.setAttribute("error", "Hệ thống hiện chỉ hỗ trợ đăng nhập bằng email.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                return;
            }

            if (user == null) {
                request.setAttribute("error", "Không tìm thấy tài khoản.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                return;
            }

            // 3. Lưu session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            

            // 4. Redirect về home
            response.sendRedirect(request.getContextPath() + "/home");

        } catch (Exception e) {
            request.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
