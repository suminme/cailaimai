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
				${mall.name}</div>
			<div class="am-header-nav am-header-right">
				<a href="${env.host}/m/"><i class="am-icon-home am-icon-sm"></i></a>
			</div>
		</header>
		<ul class="am-list productlist">
			<c:forEach var="goods" items="${goodsList}">
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
								onclick="MOBILE.cart.add('${goods.id}', 1);"
								style="background-color: orange; border-color: orange; color: #fff; padding: 0 5px 0 5px;"
								class=" am-fr am-text-xs am-text-center am-margin-top-xs">加入购物车</a>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
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