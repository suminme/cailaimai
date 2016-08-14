<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body>
	<form id="cart_form" action="${env.host}/m/order/confirm/"
		method="post">
		<div class="nc-main">
			<header data-am-widget="header" class="am-header bgc5">
				<div class="am-header-left am-header-nav">
					<a href="javascript:;" onclick="history.back()"> <i
						class="am-header-icon am-icon-angle-left am-icon-md"></i>
					</a>
				</div>
				<div
					class="am-header-title am-header-title-txt am-form-group am-form-icon am-form-feedback">
					购物车</div>
				<div class="am-header-nav am-header-right">
					<a href="${env.host}/m/"><i class="am-icon-home am-icon-sm"></i></a>
				</div>
			</header>
			<c:if test="${cart.count > 0}">
				<ul class="am-list am-margin-top-sm">
					<c:forEach var="goodsMap" items="${cart.goodsMap}">
						<li class="cart-title" style="font-size:13px;"><div
								style="padding:5px;">
								<b>${goodsMap.key.name}</b>：${goodsMap.key.freightAbout}
							</div></li>
						<c:forEach var="cartGoods" items="${goodsMap.value}">
							<li mallId="${cartGoods.goods.mallId}"
								class="goods-check am-cf am-padding-top-sm am-padding-bottom-sm"
								style="border: 1px dotted #dedede;border-width: 1px 0;">
								<!--  --> <input name="cartId" type="hidden"
								value="${cartGoods.id}" class="sfv"> <!--  --> <input
								name="goodsId" type="hidden" value="${cartGoods.goods.id}"
								class="sfv"> <!--  --> <input name="price" type="hidden"
								value="${cartGoods.goods.price}">
								<div class="am-u-sm-11" style="width:100%;">
									<div class="am-c">
										<input type="checkbox" class="jdcheckbox" name="goodsCheck"
											value="${cartGoods.goods.id}" checked="checked"><a
											class="link am-text-truncate"
											href="${env.host}/m/mall/${cartGoods.goods.id}.html">${cartGoods.goods.name}</a>
										<a class="c2 am-fr" href="javascript:;"
											onclick="MOBILE.cart.del('${cartGoods.id}',this)">删除</a>
									</div>
									<div class="am-cf am-margin-top-sm">
										<img
											src="${env.storageHost}/${cartGoods.goods.imagePath}@!goodslistsmall"
											class="am-fl am-u-sm-3 am-padding-0" /> <span
											class="am-cf am-fl am-u-sm-9 c2 am-text-sm"> 【单价】<span
											class="pricestr">${cartGoods.goods.price}</span> <br />
											【销量】${cartGoods.goods.totalSale} <br />
											【库存】${cartGoods.goods.stock} <br /> 【单位】
											${cartGoods.goods.unit}<br /> 【数量】<input name="amount"
											class="sfv" type="number" style="width:80px;height:20px;"
											value="${cartGoods.amount}" stock="${cartGoods.goods.stock}"
											at="${cartGoods.goods.name}" autocomplete="off">
										</span>
									</div>
									<div class="am-cf">
										<strong class="c4 am-fr">￥<span class="goodsTotal">${cartGoods.goods.price}</span></strong>
									</div>
								</div>
							</li>
						</c:forEach>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${cart.count eq 0}">
				<ul class="am-list am-margin-top-sm">
					<li class="border-no am-text-center am-padding-xl c2"><i
						class="am-icon-shopping-cart am-icon-lg am-margin-right-sm"></i>您的购物车是空的，
						<a class="link am-inline" href="${env.host}/m/">去首页</a></li>
				</ul>
			</c:if>
			<div data-am-widget="navbar"
				class="am-navbar am-cf bgc1 nc-navbar nc-navbar-sticky">
				<div class="am-u-sm-7">
					<a href="javascript:;" onclick="MOBILE.cart.checkAll();"><strong>全选(<span
							class="count"></span>)
					</strong></a> <strong class="am-padding-left-sm c4"><span
						class="price sumPrice">&nbsp;&nbsp;<em
							class="total pricestr"></em></span></strong>
				</div>
				<a href="javascript:;"
					class="submit-btn am-u-sm-4 am-text-center am-padding-0">去结算</a>
			</div>
			<div nc-tips width="120px"></div>
		</div>
	</form>
	<jsp:include page="../include/footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			MOBILE.init.tabbar(2);
			MOBILE.cart.list();
		});
	</script>
</body>
</html>