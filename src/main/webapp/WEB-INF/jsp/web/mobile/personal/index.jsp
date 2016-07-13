<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body>
	<div class="nc-main">
		<div class="my body-top-0">
			<div class="head">
				<div class="personal_avatar">
					<img src="${env.staticHost}/mobile/images/default_avatar.jpg" />
				</div>
				<a>${signinUser.name}</a>
			</div>
			<div class="orderstatus am-cf am-text-center bgc1">
				<a href="#" class="am-u-sm-2 border-no-left"> <span
					class="am-icon-download am-text-lg"></span><br /> <span>待付款</span>
				</a> <a href="#" class="am-u-sm-2"> <span
					class="am-icon-credit-card am-text-lg"></span><br /> <span>已付款</span>
				</a> <a href="#" class="am-u-sm-2"> <span
					class="am-icon-bus am-text-lg"></span><br /> <span>已出库</span>
				</a> <a href="#" class="am-u-sm-2"> <span
					class="am-icon-database am-text-lg"></span><br /> <span>已完成</span>
				</a><a href="#" class="am-u-sm-2"> <span
					class="am-icon-code am-text-lg"></span><br /> <span>已取消</span>
				</a>
			</div>
			<ul
				class="am-list am-list-border am-padding-bottom am-margin-bottom-0 am-padding-top">
				<li><a href="${env.host}/m/cart/"> <i
						class="am-icon-shopping-cart am-icon-fw c7"></i><span>购物车</span> <span
						class="am-icon-angle-right f-r "></span>
				</a></li>
				<li><a href="${env.host}/m/personal/address/"> <i
						class="am-icon-credit-card am-icon-fw c8"></i><span>收货地址</span> <span
						class="am-icon-angle-right f-r "></span>
				</a></li>
				<li><a href="${env.host}/m/personal/invoice/"> <i
						class="am-icon-file-text-o am-icon-fw c9"></i><span>开票信息</span> <span
						class="am-icon-angle-right f-r"></span>
				</a></li>
				<li><a href="${env.host}/m/personal/payment/"> <i
						class="am-icon-pencil am-icon-fw c7"></i><span>付款方式</span> <span
						class="am-icon-angle-right f-r"></span>
				</a></li>
				<li></li>
			</ul>
			<div nc-auth class="am-g am-padding am-padding-top-0">
				<a href="${env.host}/m/signout/" style="padding: 1.3rem;"
					class="am-u-sm-12 am-btn am-btn-warning am-radius">退出登录</a>
			</div>
			<jsp:include page="../include/footbar.jsp" />
		</div>
	</div>
	<jsp:include page="../include/footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			MOBILE.init.tabbar(3);
		});
	</script>
</body>
</html>