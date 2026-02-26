<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="model.Order"%>

<%
    Order order = (Order) request.getAttribute("order");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đặt hàng thành công</title>

    <!-- BOOTSTRAP 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f5f6fa;
        }
        .success-card {
            background: #fff;
            border-radius: 18px;
            padding: 35px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.12);
            text-align: center;
        }
        .success-icon {
            width: 85px;
            height: 85px;
            border-radius: 50%;
            background: #4CAF50;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: auto;
            margin-bottom: 20px;
        }
        .success-icon i {
            font-size: 45px;
            color: white;
        }
        .btn-home {
            background: #d32f2f;
            color: white;
            border-radius: 10px;
            padding: 12px 20px;
            font-weight: 600;
        }
        .btn-home:hover {
            background: #b71c1c;
            color: white;
        }
        .info-box {
            border-radius: 12px;
            background: #ffffff;
            border: 1px solid #e0e0e0;
            padding: 20px;
            margin-top: 25px;
        }
    </style>
</head>

<body>

<div class="container py-5" >

    <div class="success-card mx-auto col-lg-6 col-md-8 col-11">

        <div class="success-icon">
            <i class="bi bi-check-lg"></i>
        </div>

        <h2 class="fw-bold mb-2">Đặt hàng thành công!</h2>

        <p class="text-muted mb-4">
            Cảm ơn bạn đã mua hàng. Đơn hàng của bạn đã được tiếp nhận và đang trong quá trình xử lý.
        </p>

        <a href="home" class="btn btn-home w-100 mb-3">Quay lại trang chủ</a>

        <div class="info-box text-start">
            <h5 class="fw-bold mb-3">Thông tin đơn hàng</h5>

            <% if (order != null) { %>
                <p><strong>Mã đơn hàng: </strong> <%= order.getOrderId() %></p>
                <p><strong>Ngày đặt: </strong> <%= order.getOrderDate() %></p>
                <p><strong>Tổng tiền: </strong> <%= String.format("%,.0f đ", order.getTotalAmount()) %></p>
                <p><strong>Địa chỉ giao hàng: </strong> <%= order.getShippingAddress() %></p>
                <p><strong>Trạng thái: </strong> <span class="text-success fw-bold"><%= order.getStatus() %></span></p>
            <% } else { %>
                <p class="text-danger">Không tìm thấy thông tin đơn hàng!</p>
            <% } %>
        </div>

    </div>

</div>

<!-- BOOTSTRAP ICONS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

</body>
</html>
