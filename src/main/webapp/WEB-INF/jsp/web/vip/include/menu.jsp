<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.vmenu {
	width: 150px;
	font: 12px/150% Arial, Verdana, "\5b8b\4f53";
}

#sub {
	padding: 0px 0 0 0px;
}

#menu dt {
	font-size: 13px;
}

#menu dl dt {
	height: 28px;
	line-height: 28px;
	color: #333;
	font-weight: 700;
}

#menu dl dd {
	line-height: 24px;
}

#menu dl dd a {
	color: #777;
}

#menu .curr a {
	color: #ff9100;
}

#menu dl dd a:hover, a:focus {
	color: #ff9100;
}
</style>
<aside id="sidebar-left" class="col-md-2 vmenu">
	<column id="column-left" class="sidebar">
	<div id="sub">
		<div id="menu">
			<dl class="fore1">
				<dt>订单中心</dt>
				<dd class="fore1_1">
					<a id="menu_order" href="${env.host}/vip/order/list/">我的订单</a>
				</dd>
			</dl>
			<dl class="fore1">
				<dt>信息维护</dt>
				<dd class="fore1_1">
					<a id="user_address" href="${env.host}/vip/user/address/list/">收货地址</a>
				</dd>
				<dd class="fore1_1">
					<a id="user_invoice" href="${env.host}/vip/user/invoice/list/">开票信息</a>
				</dd>
			</dl>
			<dl class="fore1">
				<dt>个人中心</dt>
				<dd class="fore1_1">
					<a id="user_profile" href="${env.host}/vip/user/profile/">个人信息</a>
				</dd>
				<dd class="fore1_1">
					<a id="user_security" href="${env.host}/vip/user/security/">账号安全</a>
				</dd>
			</dl>
		</div>
	</div>
	</column>
</aside>