<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<header id="header-layout" class="header-v4">
	<nav id="topbar">
		<div class="container">
			<div class="topbar-wrap clearfix">
				<div class="pull-left ">
					<ul class="links">
						<li>${config.site.title}、${config.site.subtitle}</li>
				</div>
				<div class="login pull-right ">
					<ul class="links">
						<li><a href="${env.host}/faq/new.html">新手上路</a>|</li>
						<c:if test="${signinUser eq null}">
							<li><a href="${env.host}/signup/">注册</a>|</li>
							<li><a href="${env.host}/signin/">登录</a></li>
						</c:if>
						<c:if test="${signinUser ne null}">
							<c:if test="${signinUser.adminRank>0}">
								<li><a href="${env.host}/admin/">后台管理</a>|</li>
							</c:if>
							<li><a href="${env.host}/vip/">个人中心</a>|</li>
							<li><a href="${env.host}/signout/">退出</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<div class="header-top">
		<div class="container">
			<div class="inside">
				<div class="row">
					<div class="col-xs-12 col-sm-4 col-md-3 text-center">
						<div id="logo" class="logo-store">
							<a href="${env.host}/" class="img-responsive"><img
								src="${env.staticHost}/mall/images/logo.png" /></a>
						</div>
					</div>
					<div class=" col-xs-12 col-sm-4 col-md-3 line">
						<div class="box-support">
							<div class="media">
								<div class="pull-left">
									<i class="icon icon-mail"></i>
								</div>
								<div class="media-body">
									<h6>电子邮件</h6>
									<span><a href="mailto:${config.company.email}">${config.company.email}</a></span>
								</div>
							</div>
						</div>
					</div>
					<div class=" col-xs-12 col-sm-4 col-md-3 line">
						<div class="box-support">
							<div class="media">
								<div class="pull-left">
									<i class="icon icon-phone"></i>
								</div>
								<div class="media-body">
									<h6>联系我们</h6>
									<span>${config.company.telphone}</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 text-center ">
						<div id="cart" class="clearfix">
							<div>
								<div class="cart-inner">
									<a href="${env.host}/mall/cart/"> <i class="icon-cart"></i>
										<div class="wrap-cart">
											<h6 class="text-cart">购物车</h6>
											<span id="cart-total" class="cart-total radius-x">共计<span
												id="cart_count">${cart.count}</span>项 <!--  - <span
												id="cart_money">${cart.money}</span>元</span>-->
										</div>
									</a>
								</div>
							</div>
							<ul class="dropdown-menu content">
								<li><c:if test="${cart.count > 0}">
										<p class="text-center">
											您的购物车共有<span id="cart_count">${cart.count}</span>项商品
											<!--，合计<span
												id="cart_money">${cart.money}</span>元-->
										</p></li>
								</c:if>
								<c:if test="${cart.count <= 0}">
									<p class="text-center">您的购物车是空的哦!</p>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="header-bottom">
		<div class="container">
			<div class="row">
				<div class="col-lg-9 col-md-9 col-sm-6 col-xs-9">
					<div class="main-menu">
						<div id="pav-mainnav" class="pav-mainnav">
							<div class="pav-megamenu">
								<nav id="pav-megamenu" class="pav-megamenu  ">
									<div class="navbar">
										<div id="mainmenutop" class="megamenu" role="navigation">
											<div class="navbar-header">
												<div id="bs-megamenu" class="collapse navbar-collapse">
													<ul class="nav navbar-nav megamenu">
														<li><a href="${env.host}/"><span
																class="menu-title">首页</span></a></li>
														<c:forEach var="mall" items="${mallList}">
															<c:if test="${typeMap[mall.id] eq null}">
																<li><a href="javascript:;"><span
																		class="menu-title">${mall.name}</span></a></li>
															</c:if>
															<c:if test="${typeMap[mall.id] ne null}">
																<li><a href="${env.host}/mall/${mall.sign}/"><span
																		class="menu-title">${mall.name}</span></a></li>
															</c:if>
														</c:forEach>
														<li><a href="${env.host}/faq/new.html"><span
																class="menu-title">新手上路</span></a></li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</nav>
							</div>
						</div>
					</div>
				</div>
				<div class="nav-search col-xs-12 col-sm-6 col-md-3">
					<form id="mall_search" action="${env.host}/mall/search/"
						method="get">
						<div id="search" class="input-group pull-right">
							<input type="text" name="keyword" value="${keyword}" style="color: #000000;"
								placeholder="搜索" class="noborder-right form-control"> <span
								class="input-group-btn">
								<button type="submit" class="btn btn-search">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</header>