<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<main class="h-100 gradient-custom">
  <div class="container py-5">

    <c:set var="cart" value="${sessionScope.cart}" />

    <div class="row d-flex justify-content-center my-4">
      <!-- Cart Items -->
      <div class="col-md-8">
        <div class="card mb-4">
          <div class="card-header py-3 d-flex justify-content-between align-items-center">
            <h5 class="mb-0">Cart - <c:out value="${cart != null ? cart.size() : 0}"/> items</h5>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary btn-sm" style="background-color:#28a745; color:white;">
              <i class="fas fa-arrow-left me-1"></i> Tiếp tục mua
            </a>
          </div>
          <div class="card-body">
            <c:choose>
              <c:when test="${cart != null && !cart.isEmpty()}">
                <c:forEach var="item" items="${cart}">
                  <div class="row mb-4">
                    <div class="col-lg-3 col-md-12 mb-4 mb-lg-0">
                      <img src="assets/images/${item.imageUrl}" class="w-100 rounded" alt="${item.name}"/>
                    </div>
                    <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
                      <p><strong>${item.name}</strong></p>
                      <p>${item.description}</p>
                      <!-- Xóa sản phẩm -->
                      <form action="${pageContext.request.contextPath}/cart" method="post" class="d-inline">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="productId" value="${item.productId}" />
                        <button type="submit" class="btn btn-danger btn-sm me-1 mb-2">
                          <i class="fas fa-trash"></i> Xóa
                        </button>
                      </form>
                    </div>
                    <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
                      <form action="${pageContext.request.contextPath}/cart" method="post" class="d-flex align-items-center mb-2">
                        <input type="hidden" name="action" value="update"/>
                        <input type="hidden" name="productId" value="${item.productId}" />
                        <input type="number" name="quantity" min="1" value="${item.quantity}" class="form-control me-2" style="width:80px;"/>
                        <button type="submit" class="btn btn-primary btn-sm">Update</button>
                      </form>
                      <p class="text-start text-md-center"><strong>${item.price * item.quantity}₫</strong></p>
                    </div>
                  </div>
                  <hr/>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <p>Giỏ hàng trống.</p>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>

      <!-- Summary -->
      <div class="col-md-4">
        <div class="card mb-4">
          <div class="card-body">
            <h5>Summary</h5>
            <ul class="list-group list-group-flush">
              <li class="list-group-item d-flex justify-content-between">
                Products
                <span>
                  <c:set var="total" value="0"/>
                  <c:forEach var="item" items="${cart}">
                    <c:set var="total" value="${total + (item.price * item.quantity)}"/>
                  </c:forEach>
                  ${total}₫
                </span>
              </li>
              <li class="list-group-item d-flex justify-content-between">
                Shipping
                <span>Gratis</span>
              </li>
              <li class="list-group-item d-flex justify-content-between">
                <strong>Total amount</strong>
                <span><strong>${total}₫</strong></span>
              </li>
            </ul>
            <a href="${pageContext.request.contextPath}/checkout" class="btn btn-success w-100 mt-3" style="background-color:#28a745; color:white;">Go to checkout</a>
          </div>
        </div>
      </div>

    </div>
  </div>
</main>
