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
					</ul>
				</div>
			</div>
			<div class="main-columns container space-20">
				<div class="row">
					<aside id="sidebar-left" class="col-md-3">
						<column id="column-left" class="hidden-xs sidebar">
						<div class="panel panel-v3 panel-default bestseller">
							<div class="panel-heading">
								<h4 class="panel-title">商品类别</h4>
							</div>
							<div class="products-block">
								<div class="row products-row tree-menu">
									<ul class="accordion" style="padding: 0 0 0 40px;">
										<c:forEach var="mallGoodsType" items="${mallGoodsTypes}">
											<li class="accordion-group"><a
												<c:if test="${mallGoodsType.id eq type.id}">style="font-weight:bold;"</c:if>
												href="${env.host}/mall/${mall.sign}/${mallGoodsType.id}/">${mallGoodsType.name}(${mallGoodsType.goodsCount})</a></li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
						</column>
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
														<div>销量: ${goods.totalSale}</div>
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
					<div id="sidebar-main" class="col-md-9">
						<div id="content">
							<div class="clearfix"></div>
							<div id="products" class="product-grid">
								<div class="products-block col-nopadding">
									<div class="row products-row">
										<c:if test="${goodsList eq null}">
											<div
												style="text-align: center; padding: 90px 0; font-size: 14px;">当前类别条件没有对应的商品哦</div>
										</c:if>
										<c:if test="${goodsList ne null}">
											<c:forEach var="goods" items="${goodsList}"
												varStatus="status">
												<div
													class="col-lg-3 col-md-3 col-sm-3 col-xs-12 product-col border">
													<div class="product-block item-default">
														<div class="block-img text-center">
															<div class="image">
																<c:if test="${goods.suggest eq true}">
																	<span class="product-label sale"><span
																		class="product-label-special">推荐</span></span>
																</c:if>
																<a class="img"
																	href="${env.host}/mall/goods/${goods.id}.html"><img
																	class="img-responsive goods_list_big"
																	style="width: 217px; height: 217px;"
																	src="${env.storageHost}/${goods.imagePath}@!goodslist" />
																</a>
															</div>
														</div>
														<div class="product-meta">
															<div class="top">
																<h3 class="name" itemprop="name"
																	style="white-space: nowrap; overflow: hidden;">
																	<a href="${env.host}/mall/goods/${goods.id}.html"
																		title="${goods.name}">${goods.name}</a>
																</h3>
																<div class="rating"></div>
																<div>销量: ${goods.totalSale}</div>
																<div class="price">
																	<span class="price-new">¥ ${goods.price}</span> <a
																		style="float: right" href="javascript:;"
																		onclick="MALL.cart.add('${goods.id}',null,false)"
																		class="add_to_cart"><i
																		class="md md-add-shopping-cart"></i></a>
																</div>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="include/footbar.jsp" />
	<script type="text/javascript">
		function toQuery(typeMetaId, typeMetaValueId) {
			$("[name='meta-" + typeMetaId + "']").val(typeMetaValueId);
			$("#query_form").submit();
		}
	</script>
</body>
<jsp:include page="include/footer.jsp" />