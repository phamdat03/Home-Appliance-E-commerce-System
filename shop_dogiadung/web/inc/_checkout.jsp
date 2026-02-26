<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Product> items = (List<Product>) request.getAttribute("cart");

    double total = 0;
    if (items != null) {
        for (Product p : items) {
            total += p.getPrice() * p.getQuantity();
        }
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thanh toán</title>

    <!-- BOOTSTRAP 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f5f6fa;
        }
        .checkout-card {
            border-radius: 15px;
            background: #fff;
            padding: 25px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .order-summary {
            border-radius: 15px;
            background: #ffffff;
            padding: 20px;
            border: 1px solid #e0e0e0;
        }
        .product-item {
            border-bottom: 1px solid #eee;
            padding: 10px 0;
        }
        .product-item:last-child {
            border-bottom: none;
        }
        .btn-checkout {
            background: #d32f2f;
            color: white;
            font-weight: 600;
            border-radius: 10px;
            padding: 12px;
        }
        .btn-checkout:hover {
            background: #b71c1c;
            color: white;
        }
        input, textarea {
            border-radius: 10px !important;
        }
    </style>
</head>

<body>

<div class="container py-5" style="margin-top: 40px;">
    <h2 class="mb-4 fw-bold text-center">Xác nhận & Đặt hàng</h2>

    <div class="row g-4">

        <!-- FORM THÔNG TIN KHÁCH HÀNG -->
        <div class="col-lg-7">
            <div class="checkout-card">

                <h4 class="fw-bold mb-3">Thông tin nhận hàng</h4>

                <form action="checkout" method="post">

                    <div class="mb-3">
                        <label class="form-label">Họ và tên</label>
                        <input type="text" name="fullname" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Số điện thoại</label>
                        <input type="text" name="phone" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa chỉ giao hàng</label>
                        <textarea name="address" rows="3" class="form-control" required></textarea>
                    </div>

                    <button class="btn btn-checkout w-100 mt-3 fs-5">Đặt hàng ngay</button>
                </form>

            </div>
        </div>

        <!-- TÓM TẮT ĐƠN HÀNG -->
        <div class="col-lg-5">
            <div class="order-summary">

                <h4 class="fw-bold mb-3">Đơn hàng của bạn</h4>

                <% if (items != null) { %>
                    <% for (Product p : items) { %>
                        <div class="product-item d-flex justify-content-between">
                            <div>
                                <strong><%= p.getName() %></strong><br>
                                Số lượng: <%= p.getQuantity() %>
                            </div>
                            <div>
                                <%= String.format("%,.0f đ", p.getPrice() * p.getQuantity()) %>
                            </div>
                        </div>
                    <% } %>
                <% } %>

                <hr>

                <div class="d-flex justify-content-between fw-bold fs-5">
                    <span>Tổng cộng:</span>
                    <span><%= String.format("%,.0f đ", total) %></span>
                </div>

            </div>
        </div>

    </div>
</div>

</body>
</html>
