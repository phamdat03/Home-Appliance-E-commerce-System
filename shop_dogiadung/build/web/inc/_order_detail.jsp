<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    .order-box {
        background: #fff;
        padding: 25px;
        border-radius: 12px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.08);
    }

    .order-title {
        font-size: 24px;
        font-weight: 600;
        color: #333;
    }

    .table thead tr {
        background: #f5f5f5;
        font-weight: 600;
    }

    .total-money-box {
        background: #f8f9fa;
        padding: 18px;
        border-radius: 12px;
        font-size: 20px;
        font-weight: 600;
    }
</style>

<div class="container py-5" style="margin-top: 90px; max-width: 900px;">
    
    <div class="order-box">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <span class="order-title">🧾 Chi tiết đơn hàng #${order.orderId}</span>
        </div>

        <hr>

        <table class="table table-bordered table-hover align-middle">
            <thead>
                <tr>
                    <th style="width: 40%;">Sản phẩm</th>
                    <th class="text-center" style="width: 10%;">SL</th>
                    <th class="text-end" style="width: 20%;">Giá</th>
                    <th class="text-end" style="width: 20%;">Tạm tính</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="d" items="${details}">
                    <tr>
                        <td>${d.productName}</td>
                        <td class="text-center">${d.quantity}</td>
                        <td class="text-end">${d.price} đ</td>
                        <td class="text-end">${d.quantity * d.price} đ</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="text-end mt-4 total-money-box">
            Tổng tiền: <span class="text-danger">${order.totalAmount} đ</span>
        </div>
    </div>

</div>
