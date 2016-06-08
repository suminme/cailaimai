<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="${env.staticHost}/favicon.ico" />
<title>订单打印</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0
}

body {
	font: 12px/1.5 "\5b8b\4f53";
	color: #333
}

.w {
	width: 990px;
	margin: 0 auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	border-spacing: 0;
}

.m {
	margin-bottom: 20px;
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

.tb4 td.al {
	text-align: left
}

.tb4 td.ar {
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
/*.tb4{border-collapse:collapse;border:1px solid #000}*/
/*.tb4 th, .tb4 td,.d1{border:1px solid #000}*/
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

.info .list .label, .info .list .price {
	display: inline-block;
	*display: inline;
	*zoom: 1;
	vertical-align: middle;
}

.info .list .label {
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

.logo {
	padding: 10px
}

.footer {
	width: 100%;
	height: 50px;
	line-height: 50px;
	border-top: 1px solid #ededed;
	text-align: center;
}

.v-h {
	margin: 10px 0;
	border-top: 2px solid #ededed;
	text-align: right
}

.print-btn {
	display: inline-block;
	*display: inline;
	*zoom: 1;
	width: 96px;
	height: 50px;
	line-height: 50px;
	background: #e54346;
	color: #fff;
	font-family: 'Microsoft YaHei';
	font-size: 18px;
	font-weight: bold;
	border: 0;
}

.m2 {
	padding-left: 1px
}
</style>
<style type="text/css" media="print">
.v-h {
	display: none;
}
</style>

</head>
<body>
	<form name="form1">

		<div class="w">
			<div class="logo">
				<img style="height: 50px; width: auto;"
					src="${env.staticHost}/mall/images/logo.png"
					alt="${config.site.title}" />
			</div>
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
				</table>
			</div>
			<div class="m m2">
				<table class="tb4">
					<colgroup>
						<col class="gap">
							<col class="t3">
								<col class="t4">
									<col class="t5">
										<col class="t7">
											<col class="gap">
					</colgroup>
					<tr>
						<th class="gap"></th>
						<th class="al">商品编号</th>
						<th>商品名称</th>
						<th>数量</th>
						<th class="ar">商品金额</th>
						<th class="gap"></th>
					</tr>
					<c:forEach var="orderGoods" items="${order.orderGoodsList}">
						<tr>
							<td class="gap"></td>
							<td class="al">${orderGoods.goodsCode}</td>
							<td><div>${orderGoods.goodsName}</div></td>
							<td><span class="num">${orderGoods.amount}</span></td>
							<td class="ar"><span class="num">&yen;${orderGoods.price}</span></td>
							<td class="gap"></td>
						</tr>
					</c:forEach>
				</table>
				<div class="info">
					<div class="statistic">
						<div class="list">
							<span class="label">商品总金额：</span> <span class="price">&yen;${order.money}</span>
						</div>
						<div class="list">
							<span class="label">运费：</span> <span class="price">&yen;${order.freight}</span>
						</div>
						<div class="list">
							<span class="label">订单总金额：</span> <span class="price"><em>&yen;${order.total}</em></span>
						</div>
						<div class="list">
							<span class="label">订单支付金额：</span> <span class="price"><em>&yen;${order.pay}</em></span>
						</div>
					</div>
					<div class="clr"></div>
				</div>
			</div>
		</div>
		<div class="v-h">
			<div class="w">
				<button class="print-btn" onclick="javascript:window.print();">打印</button>
			</div>
		</div>
		<div class="footer">
			<p class="p1">${config.site.title}、${config.site.subtitle}</p>
		</div>
		</div>
	</form>
</body>
</html>