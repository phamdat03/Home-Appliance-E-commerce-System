<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<main class="main-content-padding" id="products-section"> 
  <div class="container">

    <!-- CATEGORY DROPDOWN -->
    <div class="d-flex justify-content-between align-items-center mt-4 mb-4">
        <h4 class="fw-bold">Danh mục sản phẩm</h4>

        <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" 
                  type="button" 
                  id="dropdownMenuButton" 
                  data-bs-toggle="dropdown" 
                  aria-expanded="false"
                  style="background-color: #DC143C">
            <c:choose>
                <c:when test="${currentCid == null}">Tất cả sản phẩm</c:when>
                <c:when test="${currentCid == 1}">Nồi Cơm Điện</c:when>
                <c:when test="${currentCid == 2}">Lò Vi Sóng</c:when>
                <c:when test="${currentCid == 3}">Quạt / Máy làm mát</c:when>
                <c:when test="${currentCid == 4}">Máy Lọc Nước</c:when>
                <c:when test="${currentCid == 5}">Máy Hút Bụi</c:when>
                <c:when test="${currentCid == 6}">Máy Xay Đa Năng</c:when>
                <c:otherwise>Tất cả sản phẩm</c:otherwise>
            </c:choose>
          </button>

          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <li><a class="dropdown-item" href="home">Tất Cả</a></li>
            <li><a class="dropdown-item" href="category?cid=1">Nồi Cơm Điện</a></li>
            <li><a class="dropdown-item" href="category?cid=2">Lò Vi Sóng</a></li>
            <li><a class="dropdown-item" href="category?cid=3">Quạt - Máy Làm Mát</a></li>
            <li><a class="dropdown-item" href="category?cid=4">Máy Lọc Nước</a></li>
            <li><a class="dropdown-item" href="category?cid=5">Máy Hút Bụi</a></li>
            <li><a class="dropdown-item" href="category?cid=6">Bếp Xay Đa Năng</a></li>
          </ul>
        </div>
    </div>

    <!-- PRODUCT LIST -->
    <section>
      <div class="text-center">
        <div class="row row-cols-1 row-cols-md-4 g-4">

          <c:forEach var="p" items="${products}">
            <div class="col">
              <div class="card h-100 shadow-sm">

                <!-- IMAGE -->
                <div class="position-relative overflow-hidden" style="height:200px;">
                    <img src="assets/images/${p.imageUrl}"
                         class="card-img-top img-fluid h-100 w-100"
                         style="object-fit: contain;"
                         alt="${p.name}">
                </div>

                <div class="card-body d-flex flex-column">
<!--                  <p class="card-text text-muted small mb-1">Danh mục ${p.categoryId}</p>-->
                  <h5 class="card-title">${p.name}</h5>

                  <p class="text-muted mb-1">Còn: ${p.stock} sản phẩm</p>

                  <div class="mt-auto">
                      <h6 class="price sale mb-2 text-danger fw-bold">${p.price} VND</h6>

                      <!-- FORM THÊM VÀO GIỎ HÀNG -->
                      <form method="post" action="cart">
                          <input type="hidden" name="action" value="add"/>
                          <input type="hidden" name="productId" value="${p.productId}"/>
                          <button type="submit" class="btn btn-danger btn-sm w-100"
                                  <c:if test="${p.stock == 0}">disabled</c:if>>
                              <i class="fas fa-cart-plus me-2"></i> 
                              <c:choose>
                                  <c:when test="${p.stock > 0}">Thêm vào giỏ</c:when>
                                  <c:otherwise>Hết hàng</c:otherwise>
                              </c:choose>
                          </button>
                      </form>
                  </div>
                </div>

              </div>
            </div>
          </c:forEach>

        </div>
      </div>
    </section>

  </div>
</main>
<%@ include file="chatbot.jsp" %>
