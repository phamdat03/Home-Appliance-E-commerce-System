<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>Kết quả tìm kiếm</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
</head>

<body>

<div class="container mt-5 pt-4">

    <h3 class="mb-4">
        Kết quả tìm kiếm cho: 
        <span class="text-danger">"${keyword}"</span>
    </h3>

    <c:if test="${empty products}">
        <div class="alert alert-warning">Không tìm thấy sản phẩm nào.</div>
    </c:if>

    <div class="row row-cols-1 row-cols-md-4 g-4">

        <c:forEach var="p" items="${products}">
            <div class="col">
                <div class="card h-100 shadow-sm">

                    <div class="position-relative overflow-hidden" 
                         style="height:200px; display:flex; justify-content:center; align-items:center;">
                        <img src="assets/images/${p.imageUrl}"
                             class="card-img-top img-fluid"
                             style="max-height:100%; width:auto;"
                             alt="${p.name}">
                    </div>

                    <div class="card-body d-flex flex-column">
                        <h5 class="card-title">${p.name}</h5>
                        <p class="text-muted mb-1">Còn: ${p.stock} sản phẩm</p>

                        <div class="mt-auto">
                            <h6 class="price sale mb-2 text-danger fw-bold">${p.price} VND</h6>

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

</body>
</html>
