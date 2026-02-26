<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-dark main-navbar fixed-top shadow"
     style="padding-top:0.3cm; padding-bottom:0.3cm;">
  <div class="container">

    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
      <strong class="text-white">TECH HOME</strong>
    </a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">

      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item"><a class="nav-link active text-white" href="${pageContext.request.contextPath}/home">Trang chủ</a></li>
        <li class="nav-item"><a class="nav-link text-white" href="#products-section">Sản phẩm</a></li>
        <li class="nav-item"><a class="nav-link text-white" href="#">Khuyến mãi</a></li>
        <li class="nav-item"><a class="nav-link text-white" href="#footer-section">Liên hệ</a></li>
      </ul>

      <!-- Search -->
      <form class="d-flex me-3" action="${pageContext.request.contextPath}/search" method="get">
        <input class="form-control me-2" 
               type="search" 
               name="keyword" 
               placeholder="Tìm kiếm sản phẩm..."
               required>
        <button class="btn btn-outline-light" type="submit">
          <i class="fas fa-search"></i>
        </button>
      </form>

      <!-- Cart & User -->
      <div class="d-flex align-items-center">

        <a class="nav-link text-white me-3 position-relative" href="${pageContext.request.contextPath}/cart">
          <i class="fas fa-shopping-cart fa-lg"></i>
          <span class="badge rounded-pill bg-white text-danger position-absolute top-0 start-100 translate-middle">
            <c:choose>
              <c:when test="${not empty sessionScope.cart}">
                <c:set var="sum" value="0"/>
                <c:forEach var="item" items="${sessionScope.cart}">
                  <c:set var="sum" value="${sum + item.quantity}"/>
                </c:forEach>
                ${sum}
              </c:when>
              <c:otherwise>0</c:otherwise>
            </c:choose>
          </span>
        </a>

        <c:choose>
          <c:when test="${sessionScope.user != null}">
            <div class="dropdown">
              <a class="nav-link text-white dropdown-toggle" href="#" data-bs-toggle="dropdown">
                <i class="fas fa-user fa-lg me-1"></i>
                <strong>${sessionScope.user.fullName}</strong>
              </a>
              <ul class="dropdown-menu dropdown-menu-end">
                <li><a class="dropdown-item" href="#">Thông tin cá nhân</a></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/my-orders">Quản lý đơn hàng</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
              </ul>
            </div>
          </c:when>
          <c:otherwise>
            <a class="nav-link text-white" href="${pageContext.request.contextPath}/login">
              <i class="fas fa-user fa-lg me-1"></i> Đăng nhập
            </a>
          </c:otherwise>
        </c:choose>

      </div>
    </div>
  </div>
</nav>
