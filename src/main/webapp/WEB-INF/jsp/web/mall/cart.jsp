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
						<li>购物车</li>
					</ul>
				</div>
			</div>
			<div class="main-columns container space-40">
				<div class="row">
					<div id="content" class="space-padding-20 bg-white cart">
						<form id="cart_form" action="${env.host}/mall/order/confirm/"
							method="post">
							<div class="w cart cart-warp">
								<div>
									<div class="cart-main cart-main-new">
										<div id="cart-list">
											<c:forEach var="goodsMap" items="${cart.goodsMap}">
												<div class="cart-item-list">
													<div class="cart-tbody">
														<div class="shop">
															<div class="cart-checkbox">
																<input type="checkbox" name="mallCheck"
																	class="jdcheckbox" value="${goodsMap.key.id}">
															</div>
															<span class="shop-txt"> <a class="shop-name"
																href="${env.host}/mall/${goodsMap.key.sign}/"
																target="_blank">${goodsMap.key.name}</a>
															</span>
															<div class="shop-extra-r">
																${goodsMap.key.freightAbout}，当前合计:<em
																	mallTotal="${goodsMap.key.id}" class="ftx-01 ml5"></em>
															</div>
														</div>
														<div class="item-list">
															<c:forEach var="cartGoods" items="${goodsMap.value}">
																<div class="item-give item-full">
																	<div mallId="${cartGoods.goods.mallId}"
																		class="goods-check item-last item-item">
																		<!--  -->
																		<input name="cartId" type="hidden"
																			value="${cartGoods.id}" class="sfv">
																		<!--  -->
																		<input name="goodsId" type="hidden"
																			value="${cartGoods.goods.id}" class="sfv">
																		<!--  -->
																		<input name="price" type="hidden"
																			value="${cartGoods.goods.price}">
																		<div class="item-form">
																			<div class="cell p-checkbox left">
																				<div class="cart-checkbox">
																					<input type="checkbox" class="jdcheckbox"
																						name="goodsCheck" value="${cartGoods.goods.id}"
																						checked="checked"><span
																						class="line-circle"></span>
																				</div>
																			</div>
																			<div class="cell p-goods left">
																				<div class="goods-item">
																					<div class="p-img">
																						<a
																							href="${env.host}/mall/goods/${cartGoods.goods.id}.html"
																							target="_blank"><img
																							src="${env.storageHost}/${cartGoods.goods.imagePath}@!goodslistsmall"></a>
																					</div>
																					<div class="item-msg">
																						<div class="p-name">
																							<a
																								href="${env.host}/mall/goods/${cartGoods.goods.id}.html"
																								target="_blank">${cartGoods.goods.name}</a>
																						</div>
																					</div>
																				</div>
																			</div>
																			<div class="cell p-ops right">
																				<a class="cart-remove" href="javascript:void(0);"
																					onclick="MALL.cart.del('${cartGoods.id}',this)">删除</a>
																			</div>
																			<div class="cell p-quantity right">
																				<div class="quantity-form">
																					<input name="amount" type="number" class="itxt sfv"
																						value="${cartGoods.amount}" stock="${cartGoods.goods.stock}" at="${cartGoods.goods.name}" autocomplete="off">
																				</div>
																			</div>
																			<div class="cell p-price right">
																				<strong class="pricestr">${cartGoods.goods.price}</strong>
																			</div>
																			<div class="cell p-price right">
																				<strong>库存:${cartGoods.goods.stock}</strong>
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
								</div>
								<div id="cart-floatbar">
									<div class="ui-ceilinglamp-1" style="height: 52px;">
										<div class="cart-toolbar" style="height: 50px;">
											<div class="toolbar-wrap">
												<div class="options-box">
													<!-- 
													<div class="select-all">
														<div class="cart-checkbox">
															<input type="checkbox" checked="checked"
																class="jdcheckbox"> <label class="checked"
																for="">勾选全部商品</label>
														</div>
														全选
													</div>
													<div class="operation">
														<a href="#none" class="remove-batch">删除选中的商品</a>
													</div>
													-->
													<div class="clr"></div>
													<div class="toolbar-right">
														<div class="normal">
															<div class="comm-right">
																<div class="btn-area">
																	<a href="javascript:;" class="submit-btn">去结算 </a>
																</div>
																<div class="price-sum">
																	<div>
																		<span class="txt">总价（不含运费）：</span><span
																			class="price sumPrice">&nbsp;&nbsp;<em
																			class="total"></em></span>
																	</div>
																</div>
																<div class="amount-sum">
																	已选择<em class="count"></em>件商品<b class="up"></b>
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
		MALL.cart.list();
	});
</script>