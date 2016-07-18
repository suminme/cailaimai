<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body>
	<div class="nc-main">
		<div class="detail">
			<header data-am-widget="header" class="am-header bgc5">
				<div class="am-header-left am-header-nav ">
					<a href="javascript:;" onclick="history.back()"> <i
						class="am-header-icon am-icon-angle-left am-icon-md"></i>
					</a>
				</div>
				<div class="am-header-title">${goods.name}</div>
				<div class="am-header-nav am-header-right">
					<a href="${env.host}/m/"> <i class="am-icon-home am-icon-sm"></i></a>
				</div>
			</header>
			<div>
				<ul class="sliders">
					<c:forEach var="img" items="${imgs}" varStatus="status">
						<li index="${status.index+1}"><img
							style="width: 100%; height: auto;"
							src="${env.storageHost}/${img.imgPath}" /></li>
					</c:forEach>
				</ul>
			</div>
			<div class="am-padding-sm">
				<strong>${goods.name}</strong>
				<div class="am-margin-top-sm am-cf">
					<div class="c4 am-fl am-text-xl">¥${goods.price}</div>
					<div nc-increase class="am-fr am-text-middle buynum">
						<a name="" class="reduce am-fl" href="javascript:void(0)"
							onclick="amountChange(-1)">-</a> <input type="text" name="amount"
							class="number am-fl"
							onkeyup="this.value = this.value.replace(/\D/g, '')"
							onafterpaste="this.value=this.value.replace(/\D/g,'')" value="1" />
						<a name="" class="increase am-fl am-margin-right-sm"
							href="javascript:void(0)" onclick="amountChange(1)">+</a>件
					</div>
				</div>
			</div>
			<div class="item am-padding-sm border-bottom-1">
				<div class="half">
					<span class="c5">单位:</span> <span>${goods.unit}</span>
				</div>
				<div class="half">
					<span class="c5">销量:</span> <span>${goods.totalSale}</span>
				</div>
				<c:if test="${goods.stock >= 0}">
					<div>
						<span class="c5">库存:</span> <span>${goods.stock}</span>
					</div>
				</c:if>
				<div>
					<span class="c5">运费:</span> <span>${mall.freightAbout}</span>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div class="item am-padding-sm border-bottom-1">
				<c:forEach var="meta" items="${metas}">
					<div class="half">
						<span class="c5">${meta[0]}:</span> <span>${meta[1]}</span>
					</div>
				</c:forEach>
				<div style="clear: both;"></div>
			</div>
			<div class="item am-padding-sm">${goods.content}</div>
			<div data-am-widget="navbar"
				class="am-navbar am-cf bgc1 nc-navbar nc-navbar-sticky">
				<div class="am-u-sm-4 am-padding-0 am-text-center">
					<a href="${env.host}/m/mall/cart/"><i class="am-icon-shopping-cart am-icon-sm"></i> 购物车<span>(${cart.count})</span></a>
				</div>
				<a class="am-u-sm-4 am-padding-0 bgc4 am-text-center"
					href="javascript:;" onclick="addCart()">加入购物车</a>
			</div>
		</div>
	</div>
	<jsp:include page="../include/footer.jsp" />
	<script type="text/javascript"
		src="${env.staticHost}/mall/js/jquery.themepunch.revolution.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			sliders();
		});
		function sliders() {
			var index = 0;
			setInterval(function() {
				index = slidersShow(index);
			}, 5000);
		}
		function slidersShow(index) {
			var o = $(".sliders").find("li");
			if (index >= o.length) {
				index = 0;
			}
			o.hide();
			o.eq(index).show();
			return index + 1;
		}
		function amountChange(c) {
			var amount = parseInt($("[name='amount']").val());
			amount = amount + c;
			if (amount <= 0) {
				amount = 1;
			}
			$("[name='amount']").val(amount);
		}
		function addCart() {
			var amount = $("[name='amount']").val();
			MOBILE.cart.add("${goods.id}", amount);
		}
	</script>
</body>
</html>