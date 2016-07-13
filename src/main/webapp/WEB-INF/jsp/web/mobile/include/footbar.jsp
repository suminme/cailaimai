<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="tabbar" class="am-navbar am-cf bgc1 nc-navbar">
	<ul class="am-navbar-nav am-cf am-avg-sm-4">
		<li><a href="${env.host}/m/"> <span class="am-icon-home"></span>
				<span class="am-navbar-label">首页</span>
		</a></li>
		<li><a href="javascript:;"> <span class="am-icon-bullhorn"></span>
				<span class="am-navbar-label">新手上路</span>
		</a></li>
		<li><a href="${env.host}/m/cart/"><span
				class="am-icon-shopping-cart"><c:if test="${cart.count>0}">
						<span class="am-text-xs nc-badge-shopping-cart ng-binding">${cart.count}</span>
					</c:if> </span><span class="am-navbar-label">购物车</span> </a></li>
		<li><a href="${env.host}/m/personal/"> <span
				class="am-icon-user"></span> <span class="am-navbar-label">个人中心</span>
		</a></li>
	</ul>
</div>