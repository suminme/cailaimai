<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body>
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
		<ul class="am-list am-margin-top-sm">
			<li class="border-no am-text-center am-padding-xl c2"><i
				class="am-icon-shopping-cart am-icon-lg am-margin-right-sm"></i>您的购物车是空的，
				<a class="link am-inline" href="${env.host}/m/">去首页</a></li>
		</ul>
		<div data-am-widget="navbar"
			class="am-navbar am-cf bgc1 nc-navbar nc-navbar-sticky">
			<div class="am-u-sm-7">
				<strong><i class="c3 am-text-lg"></i> 全选</strong> <strong
					class="am-padding-left-sm c4">￥ 0.00</strong>
			</div>
			<a class="am-u-sm-4 am-text-center am-padding-0 bgc3">去结算 </a>
		</div>
		<div nc-tips width="120px"></div>
	</div>
	<jsp:include page="../include/footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			MOBILE.init.tabbar(2);
		});
	</script>
</body>
</html>