<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel" data-bs-interval="4000">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>

  <div class="carousel-inner">
      
    <div class="carousel-item active carousel-item-height">
      <img src="assets/images/slide1.png" class="d-block w-100 object-fit-contain" alt="Slide 1"  >
      <div class="carousel-caption d-none d-md-block">
        <a href="#" class="btn btn-danger btn-lg mt-3">Mua Ngay</a>
      </div>
    </div>

    <div class="carousel-item carousel-item-height">
      <img src="assets/images/slide2.jpg" class="d-block w-100 object-fit-cover" alt="Slide 2">
      <div class="carousel-caption d-none d-md-block">
        <a href="#" class="btn btn-warning btn-lg mt-3">Khám Phá</a>
      </div>
    </div>

    <div class="carousel-item carousel-item-height">
      <img src="assets/images/slide3.jpg" class="d-block w-100 object-fit-cover" alt="Slide 3">
      <div class="carousel-caption d-none d-md-block">
        <a href="#" class="btn btn-success btn-lg mt-3">Đặt Hàng</a>
      </div>
    </div>
    
  </div>

  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
    
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
<style>
.carousel-item-height {
        height: 500px; /* chỉnh theo ý bạn */
        background-color: #fff; /* nền trắng nếu ảnh không phủ hết */
    }
</style>