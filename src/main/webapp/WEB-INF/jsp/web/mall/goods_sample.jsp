<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="include/header.jsp" />
<body class="product-category-18 page-category layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="include/navbar.jsp" />
			<div class="container">
				<div class="breadcrumbs">
					<ul class="list-unstyled breadcrumb-links">
						<li><a href="${env.host}/">首页</a></li>
						<li>样品申请</li>
					</ul>
				</div>
			</div>
			<div class="main-columns container space">
				<div class="row">
					<div id="content" class="bg-white"
						style="padding: 5px 20px 20px 20px">
						<form id="goods_sample_form"
							action="${env.host}/mall/goods/sample/apply/" method="post">
							<input type="hidden" name="goodsId" value="${goods.id}" />
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
									<h4>2.样品清单</h4>
									<div class="cart-main cart-main-new">
										<div>
											<div class="cart-item-list">
												<div class="cart-tbody">
													<div class="shop">
														<span class="shop-txt"> <a class="shop-name"
															href="${env.host}/mall/${mall.sign}/" target="_blank">${mall.name}</a>
														</span>
													</div>
													<div class="item-list">
														<div class="item-give item-full">
															<div class="goods-check item-last item-item">
																<div class="item-form">
																	<div class="cell p-goods left">
																		<div class="goods-item">
																			<div class="p-img">
																				<a href="${env.host}/mall/goods/${goods.id}.html"
																					target="_blank"><img
																					src="${env.storageHost}/${goods.imagePath}"></a>
																			</div>
																			<div class="item-msg">
																				<div class="p-name">
																					<a href="${env.host}/mall/goods/${goods.id}.html"
																						target="_blank">${goods.name}</a>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="cell p-price right">
																		<em>¥0</em> (免费拿样)
																	</div>
																	<div class="cell p-quantity right">
																		<div class="quantity-form">x1</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="l"></div>
									<h4>3.备注信息</h4>
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
																	<a href="javascript:;" class="submit-btn form_submit">提交申请
																	</a>
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
		var form = $("#goods_sample_form");
		$(".submit-btn").click(function(e) {
			form.submit();
		});
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
		dialog.close();
	}
	function addNewAddress() {
		dialog = art.dialog.load('${env.host}/mall/order/address/add/', {
			title : '新增收货地址',
			lock : true
		}, false);
	}
</script>