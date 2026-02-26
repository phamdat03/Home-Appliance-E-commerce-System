<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sign Up</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css">
<style>
body{margin:0;padding:0;font-family:Arial,sans-serif;background:#f2f2f2;}
.signup{display:flex;justify-content:center;align-items:center;min-height:100vh;}
.signup .container{display:flex;max-width:900px;width:100%;background:#fff;border-radius:10px;overflow:hidden;box-shadow:0 0 20px rgba(0,0,0,0.1);}
.signup-image,.signup-form{flex:1;padding:40px;display:flex;flex-direction:column;justify-content:center;}
.signup-image img{width:100%;max-width:350px;border-radius:10px;}
.signup-form h2{margin-bottom:20px;font-size:28px;color:#333;}
.form-group{margin-bottom:15px;position:relative;}
.form-group label{position:absolute;top:10px;left:10px;color:#999;}
.form-group input[type="text"],
.form-group input[type="email"],
.form-group input[type="password"]{width:100%;padding:12px 12px 12px 40px;border:1px solid #ccc;border-radius:5px;outline:none;}
.form-group input:focus{border-color:#ff4b2b;}
.form-button{margin-top:20px;}
.form-button input{width:100%;padding:12px;border:none;background:#DC143C;color:#fff;font-size:16px;font-weight:bold;cursor:pointer;border-radius:5px;}
.form-button input:hover{background:#B01032;}
.signup-image-link{margin-top:15px;display:inline-block;color:#ff4b2b;text-decoration:none;}
.signup-image-link:hover{text-decoration:underline;}
.agree-term-container{display:flex;align-items:center;gap:8px;}
.agree-term-container input[type="checkbox"]{width:18px;height:18px;margin:0;}
.agree-term-container label{position:static;color:#333;font-size:14px;line-height:1.2;}
.error-message{color:red;margin-bottom:15px;}
@media(max-width:768px){.signup .container{flex-direction:column;padding:20px;}.signup-form h2{font-size:24px;}}
</style>
</head>
<body>
<section class="signup">
    <div class="container">
        <div class="signup-image">
            <figure><img src="assets/images/signup-image.jpg" alt="sign up image"></figure>
<!--            <a href="#" class="signup-image-link">I am already member</a>-->
        </div>
        <div class="signup-form">
            <h2 class="form-title">Sign up</h2>
            <c:if test="${not empty sessionScope.errors}">
                <div class="error-message">${sessionScope.errors}</div>
            </c:if>
            <form method="POST" class="register-form" id="register-form">
                <div class="form-group">
                    <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                    <input type="text" name="full_name" id="name" placeholder="Full Name"/>
                </div>
                <div class="form-group">
                    <label for="phone"><i class="zmdi zmdi-smartphone"></i></label>
                    <input type="text" name="phone" id="phone" placeholder="Phone Number"/>
                </div>
                <div class="form-group">
                    <label for="address"><i class="zmdi zmdi-home"></i></label>
                    <input type="text" name="address" id="address" placeholder="Address"/>
                </div>
                <div class="form-group">
                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                    <input type="email" name="email" id="email" placeholder="Email"/>
                </div>
                <div class="form-group">
                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                    <input type="password" name="password" id="pass" placeholder="Password"/>
                </div>
                <div class="form-group">
                    <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                    <input type="password" name="repassword" id="re_pass" placeholder="Repeat Password"/>
                </div>
                <div class="form-group agree-term-container">
                    <input type="checkbox" name="agree-term" id="agree-term"/>
                    <label for="agree-term">I agree all statements in <a href="#" class="term-service">Terms of service</a></label>
                </div>
                <div class="form-group form-button">
                    <input type="submit" name="signup" id="signup" class="form-submit" value="Register"/>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>
