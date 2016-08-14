<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body>
	<form id="order_form" action="${env.host}/m/order/submit/"
		method="post">
		<input type="hidden" name="materialId" value="0" />
		<c:forEach var="cartId" items="${cartIds}">
			<input type="hidden" name="cartId" value="${cartId}" />
		</c:forEach>
		<div class="nc-main">
			<header data-am-widget="header" class="am-header bgc5">
				<div class="am-header-left am-header-nav">
					<a href="javascript:;" onclick="history.back()"> <i
						class="am-header-icon am-icon-angle-left am-icon-md"></i>
					</a>
				</div>
				<div
					class="am-header-title am-header-title-txt am-form-group am-form-icon am-form-feedback">
					订单确认</div>
				<div class="am-header-nav am-header-right">
					<a href="${env.host}/m/"><i class="am-icon-home am-icon-sm"></i></a>
				</div>
			</header>
			<ul class="am-list am-margin-top-sm">
				<li class="cart-title" style="font-size:13px;"><div
						style="padding:5px;">
						<b>1.收货地址</b>
					</div></li>
				<li style="border: 1px dotted #dedede;border-width: 1px 0;">
					<div id="addresses" style="padding-left:5px;">
						<div>
							<input type="radio" name="addressId"
								onclick="MOBILE.order.compute()" value="0" checked /><span
								class="address">自提</span>
						</div>
						<c:forEach var="address" items="${addressList}">
							<div>
								<input type="radio" name="addressId"
									onclick="MOBILE.order.compute()" value="${address.id}" /><span
									class="address">${address.name}&nbsp;${address.province},${address.city},${address.area},${address.detail}</span>

							</div>
						</c:forEach>
					</div>
				</li>
				<li class="cart-title" style="font-size:13px;"><div
						style="padding:5px;">
						<b>2.送货清单</b>
					</div></li>
				<li style="border: 1px dotted #dedede;border-width: 1px 0;"
					class="am-cf am-padding-top-sm am-padding-bottom-sm">
					<div>
						<c:forEach var="orderMap" items="${orderMap}">
							<!--  -->
							<input name="orderMoney" type="hidden"
								value="${orderMap.key.money}">
							<!--  -->
							<input name="orderFreight" type="hidden"
								value="${orderMap.key.freight}">
							<c:forEach var="orderGoods" items="${orderMap.value}">
								<!--  -->
								<input name="goodsId" type="hidden"
									value="${orderGoods.goods.id}">
								<!--  -->
								<input name="amount" type="hidden" value="${orderGoods.amount}">
								<!--  -->
								<div class="am-u-sm-11" style="width:100%;">
									<div class="am-cf am-margin-top-sm">
										<img
											src="${env.storageHost}/${orderGoods.goods.imagePath}@!goodslistsmall"
											class="am-fl am-u-sm-3 am-padding-0" /> <span
											class="am-cf am-fl am-u-sm-9 c2 am-text-sm"> 【单价】<span
											class="pricestr">${orderGoods.goods.price}</span> <br />
											【销量】${orderGoods.goods.totalSale} <br />
											【库存】${orderGoods.goods.stock} <br /> 【单位】
											${orderGoods.goods.unit}<br /> 【数量】${orderGoods.amount}
										</span>
									</div>
									<div class="am-cf">
										<strong class="c4 am-fr">￥<span class="goodsTotal">${orderGoods.goods.price}</span></strong>
									</div>
								</div>
							</c:forEach>
						</c:forEach>
					</div>
				</li>
				<li class="cart-title" style="font-size:13px;"><div
						style="padding:5px;">
						<b>3.开票信息</b>
					</div></li>
				<li style="border: 1px dotted #dedede;border-width: 1px 0;">
					<div id="invoices" style="padding-left:5px;">
						<div>
							<input type="radio" name="invoiceId" value="0" checked /><span
								class="address">不开票</span>
						</div>
						<c:forEach var="invoice" items="${invoiceList}">
							<div>
								<input type="radio" name="invoiceId" value="${invoice.id}" /><span
									class="address">${invoice.company}&nbsp;${invoice.tax},${invoice.bank},${invoice.account}</span>
							</div>
						</c:forEach>
					</div>
				</li>
				<li class="cart-title" style="font-size:13px;"><div
						style="padding:5px;">
						<b>4.支付方式</b>
					</div></li>
				<li style="border: 1px dotted #dedede;border-width: 1px 0;">
					<div style="padding-left:5px;">
						<input type="radio" value="-1" checked /><span class="address">对公打款</span>
						<div>企业账号：${config.company.account}</div>
						<div>企业名称：${config.company.name}</div>
						<div>开户行：${config.company.bank}</div>
						<div style="color: red;">提醒：订单提交后请在24小时内完成支付</div>
					</div>
				</li>
				<li class="cart-title" style="font-size:13px;"><div
						style="padding:5px;">
						<b>5.备注</b>
					</div></li>
				<li style="border: 1px dotted #dedede;border-width: 1px 0;">
					<div style="padding-left:5px;">
						<textarea name="about" style="width:100%;"></textarea>
					</div>
				</li>
			</ul>
			<div data-am-widget="navbar"
				class="am-navbar am-cf bgc1 nc-navbar nc-navbar-sticky">
				<div class="am-u-sm-7" style="padding-left:0px;">
					<strong class="am-padding-left-sm c4" style="padding-left:0px;"><span
						class="price sumPrice">&nbsp;&nbsp;<em
							class="total pricestr"></em></span></strong>&nbsp;<span style="font-size:13px;">(运费合计:<span
						class="freightTotal"></span>)
					</span>
				</div>
				<a href="javascript:;"
					class="submit-btn am-u-sm-4 am-text-center am-padding-0 bgc4">提交订单</a>
			</div>
			<div nc-tips width="120px"></div>
		</div>
	</form>
	<jsp:include page="../include/footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			MOBILE.order.form();
		});
	</script>
</body>
</html>