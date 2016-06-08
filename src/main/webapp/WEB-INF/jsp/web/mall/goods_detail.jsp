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
						<li><a href="${env.host}/mall/${mall.sign}/">${mall.name}</a></li>
						<li><a href="${env.host}/mall/${mall.sign}/${type.id}/">${type.name}</a></li>
						<li>${goods.name}</li>
					</ul>
				</div>
			</div>
			<div class="main-columns container space-20">
				<div class="row">
					<div id="sidebar-main" class="col-md-9">
						<div id="content">
							<div class="product-info">
								<div class="row">
									<div class="product-info-bg">
										<div
											class="col-lg-6 col-md-6 col-sm-6 col-xs-12 image-container">
											<div id="main_image" class="image">
												<c:forEach var="img" items="${imgs}" varStatus="status">
													<c:if test="${status.index==0}">
														<a target="_blank" class="info_colorbox"
															href="${env.storageHost}/${img.imgPath}"> <img
															class="product-image-zoom img-responsive"
															src="${env.storageHost}/${img.imgPath}" /></a>
													</c:if>
												</c:forEach>
											</div>
											<div id="image-additional"
												class="image-additional olw-carousel horital">
												<div id="image-additional-carousel" class="space-top-5">
													<c:forEach var="img" items="${imgs}" varStatus="status">
														<div class="item clearfix">
															<a href="javascript:;" onclick="showthisimg(this)"><img
																class="product-image-zoom img-responsive"
																src="${env.storageHost}/${img.imgPath}" /></a>
														</div>
													</c:forEach>
												</div>
											</div>
										</div>
										<div
											class="product-view col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
											<h1 class="title-product">${goods.name}</h1>
											<div class="rating"></div>
											<div class="price detail">
												<ul class="list-unstyled">
													<li><span class="price-new">¥ ${goods.price}</span></li>
												</ul>
											</div>
											<ul class="list-unstyled inline">
												<c:if test="${goods.stock >= 0}">
													<li><b>库存</b>：${goods.stock}</li>
												</c:if>
												<li><b>单位</b>：${goods.unit}</li>
												<li><b>销量</b>：${goods.totalSale}</li>
											</ul>
											<hr>
											<ul class="list-unstyled">
												<c:forEach var="meta" items="${metas}">
													<li><span class="check-box text-orange"><i
															class="fa fa-check"></i></span><b>${meta[0]}</b>: ${meta[1]}</li>
												</c:forEach>
											</ul>
											<hr>
											<ul class="list-unstyled">
												<li><b>运费</b>: ${mall.freightAbout}</li>
											</ul>
											<hr>
											<div class="rating">${goods.description}</div>
											<hr>
											<div id="product">
												<div class="product-meta">
													<div class="clearfix">
														<div class="qty space-padding-r20 pull-left">
															<div class="quantity-adder clearfix">
																<div class="quantity-number pull-left">
																	<input type="text" name="amount" size="6" value="1"
																		class="form-control" style="height: 46px;" />
																</div>
															</div>
														</div>
														<div class="cart pull-left">
															<button type="button" class="btn btn-lg btn-orange"
																onclick="addToCart()">添加到购物车</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<c:if test="${goods.sample eq true}">
									<div
										style="width: 100%; height: 80px; margin: 20px 0 20px 0; background-color: #eeeeee;">
										<div style="padding: 12px 10px 12px 10px;">
											<table>
												<tr>
													<td style="width: 160px;">
														<h2 style="margin: 0px; padding-bottom: 8px;">免费拿样</h2>
														<p>进货先拿样，购物有保障!</p>
													</td>
													<td style="vertical-align: top;">
														<div style="padding-top: 7px;">注册用户，每样商品享有一次免费申请样品的机会，如果正式订购的商品和样品有差异，可申请无条件退换货（样品随机发货，不挑色，不挑码）。</div>
													</td>
													<td
														style="vertical-align: top; width: 135px; text-align: center">
														<div style="padding-top: 4px;">
															<a
																href="${env.host}/mall/goods/sample/apply/?goodsId=${goods.id}"
																class="btn btn-lg btn-success"
																style="padding: 18px 23px">免费申请样品</a>
														</div>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</c:if>
								<div class="tab-v4">
									<div class="tab-heading">
										<ul class="nav nav-tabs" role="tablist">
											<li class="active"><a href="#tab-detail"
												data-toggle="tab">&nbsp;商品明细&nbsp;</a></li>
											<li><a href="#tab-meta" data-toggle="tab">&nbsp;订购说明&nbsp;</a></li>
										</ul>
									</div>
									<div class="tab-content width-80">
										<div id="tab-detail" class="tab-pane active">

											<div>${goods.content}</div>
										</div>
										<div id="tab-meta" class="tab-pane">${goods.cert}</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<aside id="sidebar-left" class="col-md-3">
						<column id="column-left" class="hidden-xs sidebar">
						<div class="panel panel-v3 panel-default bestseller">
							<div class="panel-heading">
								<h4 class="panel-title">热卖商品</h4>
							</div>
							<div class="products-block">
								<div class="row products-row">
									<c:forEach var="goods" items="${suggestGoodsList}"
										varStatus="status">
										<div
											class="col-lg-6 col-md-6 col-sm-6 col-xs-12 product-col suggestgoods">
											<div class="product-block item-default border">
												<div class="block-img text-center">
													<div class="image">
														<a class="img"
															href="${env.host}/mall/goods/${goods.id}.html"> <img
															class="img-responsive suggest_goods_img"
															src="${env.storageHost}/${goods.imagePath}@!goodslistsmall" />
														</a>
													</div>
												</div>
												<div class="product-meta">
													<div class="top">
														<h3 class="name" itemprop="name">
															<a href="${env.host}/mall/goods/${goods.id}.html"
																title="${goods.name}">${goods.name}</a>
														</h3>
														<div class="price">
															<span class="price-new">¥ ${goods.price}</span>
														</div>
														<div>
															销量: ${goods.totalSale}
														</div>
														<div class="add_to_cart_side">
															<a href="javascript:;"
																onclick="MALL.cart.add('${goods.id}',null,false)"><i
																class="md md-add-shopping-cart"></i>加入购物车</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						</column>
					</aside>
				</div>
			</div>
		</div>
		<jsp:include page="include/footbar.jsp" />
	</div>
</body>
<jsp:include page="include/footer.jsp" />
<script>
	function showthisimg(ob) {
		var url = $(ob).find("img").attr("src");
		$("#main_image").find("a").attr("href", url).find("img").attr("src",
				url);
	}
	function addToCart() {
		var amount = $("[name='amount']").val();
		MALL.cart.add("${goods.id}", amount, false);
	}
	$(document).ready(function() {
		var amount = $("[name='amount']");
		amount.change(function() {
			var p = $(this).val();
			$(this).val(toDecimal(p));
		});
		amount.val(toDecimal(amount.val()));
	});
</script>