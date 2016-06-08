<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="include/header.jsp" />
<script type="text/javascript">
	
</script>
<body class="product-category-18 page-category layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="include/navbar.jsp" />
			<div class="container">
				<div class="breadcrumbs">
					<ul class="list-unstyled breadcrumb-links">
						<li><a href="${env.host}/">首页</a></li>
						<li>订单确认</li>
					</ul>
				</div>
			</div>
			<div class="main-columns container space">
				<div class="row">
					<div id="content" class="bg-white"
						style="padding: 5px 20px 20px 20px">
						<form id="order_form" action="${env.host}/mall/order/submit/"
							method="post">
							<c:forEach var="cartId" items="${cartIds}">
								<input type="hidden" name="cartId" value="${cartId}" />
							</c:forEach>
							<div class="w cart cart-warp order">
								<div>
									<h4>1.收货地址</h4>
									<div>
										<div id="addresses">
											<div>
												<label><input type="radio" name="addressId"
													onclick="MALL.order.compute()" value="0" checked /><span
													class="address">自提</span> </label>
											</div>
											<c:forEach var="address" items="${addressList}">
												<div>
													<label><input type="radio" name="addressId"
														onclick="MALL.order.compute()" value="${address.id}" /><span
														class="address">${address.name}&nbsp;${address.province},${address.city},${address.area},${address.detail}</span>
													</label>
												</div>
											</c:forEach>
										</div>
										<a href="javascript:;" onclick="addNewAddress()"
											style="color: orange;">添加收货地址</a>
										</li>
									</div>
									<div class="l"></div>
									<h4>2.送货清单</h4>
									<div class="cart-main cart-main-new">
										<div>
											<c:forEach var="orderMap" items="${orderMap}">
												<div class="cart-item-list">
													<div class="cart-tbody">
														<div class="shop">
															<span class="shop-txt"> <a class="shop-name"
																href="${env.host}/mall/${orderMap.key.mall.sign}/"
																target="_blank">${orderMap.key.mall.name}</a>
															</span>
															<!--  -->
															<input name="orderMoney" type="hidden"
																value="${orderMap.key.money}">
															<!--  -->
															<input name="orderFreight" type="hidden"
																value="${orderMap.key.freight}">
															<div class="shop-extra-r"
																title="${orderMap.key.mall.freightAbout}">
																运费:<em class="cfreight ftx-01 ml5"></em>，合计:<em
																	class="ctotal ftx-01 ml5"></em>
															</div>
														</div>
														<div class="item-list">
															<c:forEach var="orderGoods" items="${orderMap.value}">
																<div class="item-give item-full">
																	<div mallId="${orderGoods.goods.mallId}"
																		class="goods-check item-last item-item">
																		<!--  -->
																		<input name="goodsId" type="hidden"
																			value="${orderGoods.goods.id}">
																		<!--  -->
																		<input name="amount" type="hidden"
																			value="${orderGoods.amount}">
																		<div class="item-form">
																			<div class="cell p-goods left">
																				<div class="goods-item">
																					<div class="p-img">
																						<a
																							href="${env.host}/mall/goods/${orderGoods.goods.id}.html"
																							target="_blank"><img
																							src="${env.storageHost}/${orderGoods.goods.imagePath}@!goodslistsmall"></a>
																					</div>
																					<div class="item-msg">
																						<div class="p-name">
																							<a
																								href="${env.host}/mall/goods/${orderGoods.goods.id}.html"
																								target="_blank">${orderGoods.goods.name}</a>
																						</div>
																					</div>
																				</div>
																			</div>
																			<div class="cell p-price right">
																				¥<span class="pricestr">${orderGoods.goods.price}</span>
																			</div>
																			<div class="cell p-price right">
																				<div class="quantity-form">
																					x<span class="pricestr">${orderGoods.amount}</span></div>
																			</div>
																		</div>
																	</div>
																</div>
															</c:forEach>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
									<div class="l"></div>
									<h4>3.开票信息</h4>
									<div>
										<div id="invoices">
											<div>
												<label><input type="radio" name="invoiceId"
													value="0" checked /><span class="address">不开票</span> </label>
											</div>
											<c:forEach var="invoice" items="${invoiceList}">
												<div>
													<label><input type="radio" name="invoiceId"
														value="${invoice.id}" /><span class="address">${invoice.company}&nbsp;${invoice.tax},${invoice.bank},${invoice.account}</span>
													</label>
												</div>
											</c:forEach>
										</div>
										<a href="javascript:;" onclick="addNewInvoice()"
											style="color: orange;">添加开票信息</a>
									</div>
									<div class="l"></div>
									<h4>4.支付方式</h4>
									<div>
										<label><input type="radio" value="-1" checked /><span
											class="address">对公打款</span> </label>
										<p>企业账号：${config.company.account}</p>
										<p>企业名称：${config.company.name}</p>
										<p>开户行：${config.company.bank}</p>
										<p style="color: red;">提醒：订单提交后请在24小时内完成支付</p>
									</div>
									<div class="l"></div>
									<h4>5.素材上传</h4>
									<div>
										<div id="material">
											<div>
												<label><input type="radio" name="materialId"
													value="0" checked /><span class="address">不上传</span> </label>
											</div>
											<c:forEach var="material" items="${materialList}">
												<div>
													<label><input type="radio" name="materialId"
														value="${material.id}" /><span class="material">${material.name}</span>
													</label>
												</div>
											</c:forEach>
										</div>
										<a href="javascript:;" onclick="addNewMaterial()"
											style="color: orange;">上传新素材</a>
									</div>
									<div class="l"></div>
									<h4>6.备注信息</h4>
									<div>
										<textarea name="about" class="about"></textarea>
									</div>
								</div>
								<div id="cart-floatbar" style="padding-top: 10px;">
									<div class="ui-ceilinglamp-1" style="height: 52px;">
										<div class="cart-toolbar" style="height: 50px;">
											<div class="toolbar-wrap">
												<div class="options-box">
													<div class="clr"></div>
													<div class="toolbar-right">
														<div class="normal">
															<div class="comm-right">
																<div class="btn-area">
																	<a href="javascript:;" class="submit-btn form_submit">提交订单
																	</a>
																</div>
																<div class="price-sum">
																	<div>
																		<span class="txt">总价（含运费）：</span><span
																			class="price sumPrice">&nbsp;&nbsp;<em
																			class="total"></em></span>
																	</div>
																</div>
																<div class="amount-sum">
																	共计<em class="count"></em>件商品<b class="up"></b>
																</div>
																<div class="clr"></div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
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
		MALL.order.form();
	});
	function addAddress(data) {
		var m = '<div><label><input type="radio" name="addressId" value="'+data.id+'" checked /><span class="address">'
				+ data.name
				+ '&nbsp;'
				+ data.province
				+ ','
				+ data.city
				+ ','
				+ data.area + ',' + data.detail + '<span></label><div>';
		$("#addresses").append(m);
		MALL.order.compute();
		dialog.close();
	}
	function addInvoice(data) {
		var m = '<div><label><input type="radio" name="invoiceId" value="'+data.id+'" checked /><span class="address">'
				+ data.company
				+ '&nbsp;'
				+ data.tax
				+ ','
				+ data.bank
				+ ','
				+ data.account + '<span></label><div>';
		$("#invoices").append(m);
		dialog.close();
	}
	function addMaterial(data) {
		var m = '<div><label><input type="radio" name="materialId" value="'+data.id+'" checked /><span class="material">'
				+ data.name + '<span></label><div>';
		$("#material").append(m);
		dialog.close();
	}
	function addNewAddress() {
		dialog = art.dialog.load('${env.host}/mall/order/address/add/', {
			title : '新增收货地址',
			lock : true
		}, false);
	}
	function addNewInvoice() {
		dialog = art.dialog.load('${env.host}/mall/order/invoice/add/', {
			title : '新增开票信息',
			lock : true
		}, false);
	}
	function addNewMaterial() {
		dialog = art.dialog.open('${env.host}/mall/order/material/add/', {
			title : '新增素材信息',
			width : "500px",
			lock : true
		}, false);
	}
</script>