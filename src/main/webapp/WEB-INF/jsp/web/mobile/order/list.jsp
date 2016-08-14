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
				<c:if test="${status==1}">
					待付款订单
				</c:if>
				<c:if test="${status==2}">
					已付款订单
				</c:if>
				<c:if test="${status==3}">
					已出库订单
				</c:if>
				<c:if test="${status==4}">
					已完成订单
				</c:if>
				<c:if test="${status==-1}">
					已取消订单
				</c:if>
				<c:if test="${status eq null}">
					我的订单
				</c:if>
			</div>
			<div class="am-header-nav am-header-right">
				<a href="${env.host}/m/"><i class="am-icon-home am-icon-sm"></i></a>
			</div>
		</header>
		<ul class="am-list productlist">
			<c:forEach var="order" items="${orderList}">
				<li class="am-padding-sm" style="line-height: 30px;">
					<div>
						<div class="am-cf">
							<div class="am-fl am-ellipsis" style="width: calc(100% - 8rem)">
								<a href="${env.host}/m/order/detail/?code=${order.code}" class="link">${order.code}</a>
								<span class="tip am-text-xs am-margin-left-sm"></span>
							</div>
							<strong class="c4 am-fr">¥${order.pay}</strong>
						</div>
						<div class="am-text-sm">
							<a href="javascript:void(0)">下单时间：${fn:substring(order.createTime,0,16)}</a>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
		<div nc-tips width="120px"></div>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>