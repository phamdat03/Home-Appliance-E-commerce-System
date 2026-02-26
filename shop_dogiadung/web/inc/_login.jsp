<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Dùng EL (Expression Language) để lấy đường dẫn gốc --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<section class="vh-100">
  <div class="container py-5 h-100">
    <div class="row d-flex align-items-center justify-content-center h-100">
      
      <%-- Cột hình ảnh (7/12) --%>
      <div class="col-md-8 col-lg-7 col-xl-6">
          <a href="_login.jsp"></a>
        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
          class="img-fluid" alt="Login image">
      </div>
      
      <%-- Cột Form đăng nhập (5/12) --%>
      <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
          
        <%-- FORM: Gửi dữ liệu POST tới /login --%>
        <form action="${contextPath}/login" method="POST">
            
          <%-- Hiển thị thông báo lỗi từ Servlet (nếu có) --%>
          <c:if test="${error != null}">
              <div class="alert alert-danger" role="alert">
                  ${error}
              </div>
          </c:if>
            
          <!-- Email input -->
          <%-- Trong file _login.jsp --%>

            <div class="form-outline mb-4">
              <input type="text" name="emailphone" class="form-control form-control-lg" 
                value="${emailphone}" required/> <%-- ⭐ THÊM value="${emailphone}" --%>
              <label class="form-label" for="form1Example13">Email address or Phone number</label>
            </div>

            <div class="form-outline mb-4">
              <input type="password" name="password" class="form-control form-control-lg" required/>
              <label class="form-label" for="form1Example23">Password</label>
            </div>

          <div class="d-flex justify-content-around align-items-center mb-4">
            <!-- Checkbox -->
            <div class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="form1Example3" checked />
              <label class="form-check-label" for="form1Example3"> Ghi nhớ tôi </label>
            </div>
            <a href="#!">Quên mật khẩu?</a>
            <br>
            <a href="${contextPath}/register" class="text-danger">Đăng ký</a>
          </div>

          <!-- Submit button -->
          <button type="submit" class="btn btn-primary btn-lg btn-block" style="background-color: #DC143C">Đăng nhập</button>

         
         
        </form>
      </div>
    </div>
  </div>
</section>