<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Order"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container py-5" style="margin-top: 90px;">
    <h2 class="mb-4">Đơn hàng của bạn</h2>

    <c:if test="${empty orders}">
        <p>Bạn chưa có đơn hàng nào.</p>
    </c:if>

    <c:if test="${not empty orders}">
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Mã đơn hàng</th>
                    <th>Ngày đặt</th>
                    <th>Tổng tiền</th>
                    <th>Địa chỉ giao hàng</th>
                    <th>Trạng thái</th>
                    <th>Chi tiết</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.orderDate}</td>
                        <td>${order.totalAmount}</td>
                        <td>${order.shippingAddress}</td>
                        <td>${order.status}</td>
                        <td>
                            <a class="btn btn-sm btn-primary" 
                               href="${pageContext.request.contextPath}/order-detail?id=${order.orderId}">
                               Xem chi tiết
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
