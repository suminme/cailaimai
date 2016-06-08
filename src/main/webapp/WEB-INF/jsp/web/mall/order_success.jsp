<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="include/header.jsp" />
<style>
#content {
	background: #F3FDED !important;
	border-top: 2px solid #7ABD54;
}

.btn-area {
	padding: 13px 0 10px 0;
	clear: both;
}

.btn-ordershow {
	border: 1px solid #ddd;
	background-color: #fff;
	padding: 6px 16px;
	border-radius: 2px;
	color: #666;
}

.d-tip {
	padding-left: 5px;
	font-size: 12px;
}

.d-tips-cont {
	padding-left: 5px;
}

.list-order {
	background-color: #fff;
	border-top: 1px solid #e6e6e6;
	margin-bottom: 8px;
	padding-left: 5px;
	list-style: none;
}

.li-st {
	border-bottom: 1px solid #e6e6e6;
	padding: 10px;
	overflow: hidden;
	color: #333;
}

.fore1 a:link, .fore1 a:visited {
	color: #005a9d;
}

.li-st .fore1, .li-st .fore2 {
	margin-right: 30px;
	float: left;
}

.li-st .fore1, .li-st .fore2 {
	margin-right: 30px;
	float: left;
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
								<i class="fa fa-check"></i>订单提交成功！
							</h4>
							<c:if test="${fn:length(orderList)>1}">
								<h5 class="d-tip">
									亲爱的用户，由于您选购的商品不在同一个仓库，我们有可能分为<span class="ftx-01">${fn:length(orderMap)}</span>次为您配送，请关注个人中心的订单状态和物流状态。
								</h5>
							</c:if>
							<ul class="list-order">
								<c:forEach var="order" items="${orderList}">
									<li class="li-st">
										<div class="fore1">
											订单号：<a
												href="${env.host}/vip/order/detail/?code=${order.code}">${order.code}</a>
										</div>
										<div class="fore2">
											订单总额：<strong class="ftx-01">${order.total}元</strong>
										</div>
										<div class="fore3">
											运费：<strong class="ftx-01">${order.freight}元</strong>
										</div>
									</li>
								</c:forEach>
							</ul>
							<span class="d-tips-cont"> <i></i>重要提醒：请在24小时内完成支付，如有任何问题和疑问，请与
								<span class="ftx-01">站内客服</span> 联系，谨防上当受骗
							</span>
							<div class="btn-area">
								<a class="btn-ordershow" href="${env.host}/">继续购物</a>
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
<script>
	setTimeout(function() {
		location.href = "${env.host}/vip/";
	}, 5000);
</script>