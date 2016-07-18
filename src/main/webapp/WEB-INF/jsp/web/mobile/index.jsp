<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="include/header.jsp" />
<body>
	<div class="nc-main">
		<header data-am-widget="header" class="am-header bgc5">
			<div class="am-header-left am-header-nav" style="line-height: 44px;">
				<a href="${env.host}/m/"><img
					src="${env.staticHost}/mall/images/logo.png"
					style="width: 80px; height: auto;" /></a>
			</div>
			<div
				class="am-header-title am-form-group am-form-icon am-form-feedback"
				style="margin: 0 5px 0 95px">
				<input nc-search type="search" class="am-form-field am-radius"
					placeholder="搜索"> <span class="am-icon-search c2"></span>
			</div>
		</header>
		<div>
			<ul class="am-slides">
				<li><a><img style="width: 100%; height: auto;"
						src="${env.staticHost}/mall/images/slider/01.jpg" /></a></li>
			</ul>
		</div>
		<ul class="am-avg-sm-3 menu am-margin-top-xs">
			<li class="bgc7" style="margin-left: 0.1rem"><a
				href="${env.host}/m/mall/wood-board/" class="am-block">木工板材</a></li>
			<li class="bgc4"><a href="${env.host}/m/mall/digital-graphic/"
				class="am-block">数字图文</a></li>
			<li class="bgc6"><a href="${env.host}/m/mall/shine-font/"
				class="am-block">发光标识</a></li>
		</ul>
		<ul class="am-avg-sm-3 menu am-margin-bottom" style="margin-top: 1px;">
			<li class="bgc0" style="margin-left: 0.1rem"><a
				href="${env.host}/m/mall/electrical/" class="am-block">LED照明</a></li>
			<li class="bgc9"><a href="javascript:;" class="am-block">有机板材</a></li>
			<li class="bgc8"><a href="javascript:;" class="am-block">五金配件</a></li>
		</ul>
		<ul class="am-list productlist">
			<c:forEach var="goods" items="${suggestGoodsList}">
				<li class="am-padding-sm" style="line-height: 30px;">
					<div>
						<div class="am-cf">
							<div class="am-fl am-ellipsis" style="width: calc(100% - 8rem)">
								<a href="${env.host}/m/mall/${goods.id}.html" class="link">${goods.name}</a>
								<span class="tip am-text-xs am-margin-left-sm"></span>
							</div>
							<strong class="c4 am-fr">¥${goods.price}</strong>
						</div>
						<div class="am-text-sm">
							<a href="javascript:void(0)">销量：${goods.totalSale}&nbsp;&nbsp;&nbsp;库存:
								${goods.stock} </a> <a href="javascript:;"
								onclick="MOBILE.cart.add('${goods.id}', 1);location.reload();"
								style="background-color: orange; border-color: orange; color: #fff; padding: 0 5px 0 5px;"
								class=" am-fr am-text-xs am-text-center am-margin-top-xs">加入购物车</a>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
		<jsp:include page="include/footbar.jsp" />
	</div>
	<jsp:include page="include/footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			MOBILE.init.tabbar(0);
		});
	</script>
</body>
</html>