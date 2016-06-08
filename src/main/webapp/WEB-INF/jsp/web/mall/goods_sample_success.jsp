<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="include/header.jsp" />
<style>
#content {
	border-top: 2px solid #7ABD54;
}

.gs-area {
	padding: 5px 0 0px 10px;
	width: 100%;
}

.gs-area .img {
	float: left;
	width: 85px;
}

.gs-area .p-name a {
	color: #777;
	font-size: 14px;
}

.gs-area .p-extra {
	color: #aaa;
	padding-top: 12px;
}

.gs-area .img img {
	width: 80px;
	height: 80px;
	border: 1px solid #e8e8e8;
}

.btn-area {
	padding: 20px 0 10px 0;
}

.btn-ordershow {
	border: 1px solid #ddd;
	background-color: #fff;
	padding: 6px 16px;
	border-radius: 2px;
	color: #666;
}

.btn-submit {
	border: 1px solid #e4393c;
	background-color: #e4393c;
	padding: 6px 16px;
	border-radius: 2px;
	color: #fff;
}

.btn-submit:hover {
	color: #fff;
}
</style>
<body class="product-category-18 page-category layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="include/navbar.jsp" />
			<div class="main-columns container space-40" style="padding: 20px;">
				<div class="row">
					<div id="content" class="space-padding-20 bg-white cart">
						<div>
							<h4 class="ftx-02">
								<i class="fa fa-check"></i>样品申请成功！
							</h4>
							<div class="gs-area">
								<div class="img">
									<img src="${env.storageHost}/${goods.imagePath}" />
								</div>
								<div class="info">
									<div class="p-name">
										<a href="${env.host}/mall/goods/${goods.id}.html"
											title="${goods.name}">${goods.name}</a>
									</div>
									<div class="p-extra">
										<span class="txt">数量：1</span><br /> <span class="txt">
											收获地址：${sample.address}</span>
									</div>
								</div>
							</div>
							<div class="btn-area">
								<a class="btn-ordershow" href="${env.host}/mall/goods/${goods.id}.html">继续购物</a>
							</div>
							<div style="clear: both;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="include/footbar.jsp" />
	</div>
</body>
<jsp:include page="include/footer.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		MALL.cart.list();
	});
</script>