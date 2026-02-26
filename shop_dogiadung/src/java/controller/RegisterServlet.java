package controller;

import data.dao.UserDao;
import data.impl.UserImpl;
import data.utils.API;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private final UserDao userDao = new UserImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.removeAttribute("errors"); // Xóa lỗi cũ
        session.removeAttribute("exist_user");

        String fullName = request.getParameter("full_name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String agree = request.getParameter("agree-term");

        boolean hasError = false;
        StringBuilder errors = new StringBuilder();

        // Validate
        if(fullName == null || fullName.isEmpty()){
            errors.append("Full Name is required.<br>");
            hasError = true;
        }
        if(phone == null || !phone.matches("^\\d{10}$")){
            errors.append("Phone must be 10 digits.<br>");
            hasError = true;
        }
        if(address == null || address.isEmpty()){
            errors.append("Address is required.<br>");
            hasError = true;
        }
        if(email == null || !email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$")){
            errors.append("Email is invalid.<br>");
            hasError = true;
        }
        if(password == null || password.isEmpty()){
            errors.append("Password is required.<br>");
            hasError = true;
        }
        if(repassword == null || !repassword.equals(password)){
            errors.append("Password and Repeat Password must match.<br>");
            hasError = true;
        }
        if(agree == null){
            errors.append("You must agree to Terms of Service.<br>");
            hasError = true;
        }

        // Check email or phone tồn tại
        if(userDao.findByEmail(email) != null){
            errors.append("Email already exists.<br>");
            hasError = true;
        }

        if(hasError){
            session.setAttribute("errors", errors.toString());
            response.sendRedirect("register");
            return;
        }

        // Tạo user mới
        User user = new User();
        user.setFullName(fullName);
        user.setPhone(phone);
        user.setAddress(address);
        user.setEmail(email);
        user.setPasswordHash(password); // Hàm save() sẽ hash
        user.setRole("customer");

        boolean success = userDao.insertUser(user);
        if(success){
            session.setAttribute("user", user);
            response.sendRedirect("home");
        } else {
            session.setAttribute("errors", "Register failed, please try again.");
            response.sendRedirect("register");
        }
    }
}
