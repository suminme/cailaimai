<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<style type="text/css">
table {
	width: 100%;
	border-collapse: collapse;
	border-spacing: 0;
}

.m {
	margin-bottom: 20px;
	background-color: white;
}

.al {
	text-align: left;
}

.ar {
	text-align: right;
}

.clr {
	clear: both;
}

em {
	font-style: normal;
}

.m1 {
	padding: 0 20px 20px;
	border-style: solid;
	border-width: 2px 1px 1px;
	border-color: #aaa #eee #eee;
}

.m1 .tb1 td {
	height: 1.1cm;
	line-height: 1.1cm;
	border-bottom: 1px solid #f5f5f5;
}

.tb1 .t1 {
	width: 50%;
}

.tb1 .t2 {
	width: 50%;
	text-align: right;
}

.tb2 td {
	height: 0.8cm;
	line-height: 0.8cm;
}

.tb2 .t1 {
	width: 2cm;
}

.m2 {
	border: 1px solid #eee;
}

.tb4 th {
	background: #f9f9f9;
	height: 1.1cm;
	line-height: 1.1cm;
	font-weight: normal;
}

.tb4 td {
	height: 1.8cm;
	line-height: 1.8cm;
	border-bottom: 1px solid #f5f5f5;
	text-align: center
}

.tb4 .al {
	text-align: left
}

.tb4 .ar {
	text-align: right
}

.tb4 .gap {
	width: 20px;
}

.tb4 td.gap {
	border-color: #fff;
}

.tb4 .t3, .tb4 .t5, .tb .t7 {
	width: 18%;
}

.tb4 .p-name {
	text-align: left
}

.tb4 .num {
	font-family: verdana
}

.tb4 th {
	text-align: center;
}

.tb4 td {
	text-align: center;
}

.info {
	position: relative;
	top: -1px;
	padding: 20px 0;
	border-top: 1px solid #eee;
}

.info .statistic {
	float: right;
	margin-right: 20px;
}

.info .list {
	line-height: 25px;
}

.info .list .labels, .info .list .price {
	display: inline-block;
	*display: inline;
	*zoom: 1;
	vertical-align: middle;
}

.info .list .labels {
	width: 2.75cm;
	text-align: left;
}

.info .list .price {
	width: 5cm;
	text-align: right;
	font-family: verdana;
	font-style: normal;
}

.info .list em {
	font-size: 18px;
	font-weight: bold;
}

.d1 {
	padding: 10px
}

.d2 {
	text-align: right;
	padding: 10px 0;
	font-size: 14px
}

.m2 {
	padding-left: 1px
}
</style>
<body class="common-home page-common-home layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="../include/navbar.jsp" />
			<div class="container">
				<div class="breadcrumbs">
					<ul class="list-unstyled breadcrumb-links">
						<li><a href="${env.host}/">首页</a></li>
						<li><a href="${env.host}/vip/">个人中心</a></li>
						<li><a href="${env.host}/vip/order/list/">订单中心</a></li>
						<li>我的订单</li>
					</ul>
				</div>
			</div>
			<div class="main-columns container space-20">
				<div class="row">
					<div id="sidebar-main" class="col-md-12">
						<div id="content" class="space-padding-0">
							<div class="m m1">
								<table class="tb1">
									<tr>
										<td class="t1">订单编号：${order.code}</td>
										<td class="t2">订购时间：${fn:substring(order.createTime,0,19)}</td>
									</tr>
								</table>
								<table class="tb2">
									<colgroup>
										<col class="t1">
										<col class="t2">
									</colgroup>
									<c:if test="${order.address eq null}">
										<tr>
											<td>送货方式：</td>
											<td>自提</td>
										</tr>
									</c:if>
									<c:if test="${order.address ne null}">
										<tr>
											<td>客户姓名：</td>
											<td>${order.address.name}</td>
										</tr>
										<tr>
											<td>联系方式：</td>
											<td>${order.address.phone}</td>
										</tr>
										<tr>
											<td>客户地址：</td>
											<td>${order.address.province}${order.address.city}${order.address.area}${order.address.detail}</td>
										</tr>
									</c:if>
									<tr>
										<td>开票信息：</td>
										<td><c:if test="${order.invoice eq null}">
											不开票
											</c:if> <c:if test="${order.invoice ne null}">
											${order.invoice.company}
											</c:if></td>
									</tr>
								</table>
							</div>
							<div class="m m2">
								<table class="tb4">
									<tr>
										<th class="gap"></th>
										<th class="al">商品名称</th>
										<th>商品编号</th>
										<th>数量</th>
										<th class="ar">商品金额</th>
										<th class="gap"></th>
									</tr>
									<c:forEach var="orderGoods" items="${order.orderGoodsList}">
										<tr>
											<td class="gap"></td>
											<td class="al"><div>
													<a target="_blank"
														href="${env.host}/mall/goods/${orderGoods.goodsId}.html">${orderGoods.goodsName}</a>
												</div></td>
											<td>${orderGoods.goodsCode}</td>
											<td><span class="num">${orderGoods.amount}</span></td>
											<td class="ar"><span class="num">&yen;${orderGoods.price}</span></td>
											<td class="gap"></td>
										</tr>
									</c:forEach>
								</table>
								<div class="info">
									<div class="statistic">
										<div class="list">
											<span class="labels">商品总金额：</span> <span class="price">&yen;${order.money}</span>
										</div>
										<div class="list">
											<span class="labels">运费：</span> <span class="price">&yen;${order.freight}</span>
										</div>
										<div class="list">
											<span class="labels">订单总金额：</span> <span class="price"><em>&yen;${order.total}</em></span>
										</div>
										<div class="list">
											<span class="labels">订单支付金额：</span> <span class="price"><em>&yen;${order.pay}</em></span>
										</div>
									</div>
									<div class="clr"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../include/footbar.jsp" />
	</div>
</body>
<jsp:include page="../include/footer.jsp" />