<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="include/header.jsp" />
<body class="common-home page-common-home layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="include/navbar.jsp" />
			<div class="main-columns container-full">
				<div class="row">
					<div id="sidebar-main" class="col-md-12">
						<div id="content" class="space-padding-0">
							<div class="homebuilder clearfix home4">
								<div class="pav-container container" style="margin: 30px auto">
									<div class="pav-inner">
										<div class="row row-level-1">
											<div class="row-inner clearfix">
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
													<div class="col-inner">
														<div class="layerslider-wrapper">
															<div class="bannercontainer banner-boxed"
																style="padding: 0; margin: 0;">
																<div id="sliderlayer" class="rev_slider boxedbanner"
																	style="width: 100%; height: 312px; max-height: 312px;">
																	<ul>
																		<li data-masterspeed="300" data-transition="random"
																			data-slotamount="7"
																			data-thumb="${env.staticHost}/mall/images/slider/01.jpg">
																			<img
																			src="${env.staticHost}/mall/images/slider/01.jpg" />
																		</li>
																		<li data-masterspeed="300" data-transition="random"
																			data-slotamount="7"
																			data-thumb="${env.staticHost}/mall/images/slider/02.jpg">
																			<img
																			src="${env.staticHost}/mall/images/slider/02.jpg" />
																		</li>
																		<li data-masterspeed="300" data-transition="random"
																			data-slotamount="7"
																			data-thumb="${env.staticHost}/mall/images/slider/03.jpg">
																			<img
																			src="${env.staticHost}/mall/images/slider/03.jpg" />
																		</li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="container">
									<div class="ms">
										<ul>
											<li><a href="${env.host}/mall/wood-board/"><img
													src="${env.staticHost}/mall/images/mall/b01.png" /></a></li>
											<li><a href="${env.host}/mall/digital-graphic/"><img
													src="${env.staticHost}/mall/images/mall/b02.png" /></a></li>
											<li><a href="${env.host}/mall/shine-font/"><img
													src="${env.staticHost}/mall/images/mall/b03.png" /></a></li>
											<li><a href="${env.host}/mall/electrical/"><img
													src="${env.staticHost}/mall/images/mall/b04.png" /></a></li>
											<li><a href="javascript:;"><img
													src="${env.staticHost}/mall/images/mall/b05.png" /></a></li>
											<li><a href="javascript:;"><img
													src="${env.staticHost}/mall/images/mall/b06.png" /></a></li>
										</ul>
									</div>
								</div>
								<div style="clear: both; padding-bottom: 40px;"></div>
								<div class="pav-container container" style="margin: 0 auto 30px">
									<div class="pav-inner">
										<div class="row row-level-1">
											<div class="row-inner clearfix">
												<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
													<div class="col-inner over-hidden">
														<div
															class="panel col-nopadding featured-products panel-v3 panel-default space-30">
															<div class="panel-heading">
																<h4 class="panel-title">热卖商品</h4>
															</div>
															<div class="panel-body space-padding-0">
																<div class="box-products owl-carousel-play"
																	id="productfeaturedcarousel7" data-ride="owlcarousel">
																	<div class="owl-carousel product-grid block-content">
																		<div class="item active products-block">
																			<div class="row products-row">
																				<c:forEach var="goods" items="${suggestGoodsList}">
																					<div
																						class="col-md-3 col-sm-3 col-xs-12 product-col border">
																						<div class="product-block item-default">
																							<div class="block-img text-center">
																								<div class="image">
																									<a class="img"
																										href="${env.host}/mall/goods/${goods.id}.html">
																										<img class="img-responsive"
																										style="width: 217px; height: 217px;"
																										src="${env.storageHost}/${goods.imagePath}@!goodslist" />
																									</a>
																								</div>
																							</div>
																							<div class="product-meta">
																								<div class="top">
																									<h3 class="name" title="${goods.name}" style="white-space: nowrap;overflow: hidden;">
																										<a
																											href="${env.host}/mall/goods/${goods.id}.html">${goods.name}</a>
																									</h3>
																									<div class="rating"></div>
																									<div>
																										销量:${goods.totalSale}
																									</div>
																									<div class="price">
																										<span class="price-new">¥
																											${goods.price}</span>
																									</div>
																								</div>
																							</div>
																						</div>
																					</div>
																				</c:forEach>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<div class="col-inner">
														<div class="widget-images space-10">
															<div class="widget-inner box-content clearfix">
																<div class="image-item">
																	<a href="javascript:;"><img class="img-responsive"
																		src="${env.staticHost}/mall/images/slider/b01.jpg" /></a>
																</div>
															</div>
														</div>
														<div class="widget-images space-10">
															<div class="widget-inner box-content clearfix">
																<div class="image-item">
																	<a href="${env.host}/faq/new.html#a1"><img
																		class="img-responsive"
																		src="${env.staticHost}/mall/images/slider/b02.jpg" /></a>
																</div>
															</div>
														</div>
														<div class="widget-images space-10">
															<div class="widget-inner box-content clearfix">
																<div class="image-item">
																	<a href="${env.host}/faq/new.html#a2"><img
																		class="img-responsive"
																		src="${env.staticHost}/mall/images/slider/b03.jpg" /></a>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="pav-container container" style="margin: 0 auto 30px">
									<div class="pav-inner">
										<div class="row row-level-1">
											<div class="row-inner clearfix">
												<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
													<div class="col-inner"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="pav-container container">
									<div class="pav-inner">
										<div class="row row-level-1">
											<div class="row-inner clearfix">
												<div class="col-lg-12 col-md-4 col-sm-6 col-xs-12">
													<div class="col-inner"></div>
												</div>
											</div>
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
	</div>
</body>
<jsp:include page="include/footer.jsp" />