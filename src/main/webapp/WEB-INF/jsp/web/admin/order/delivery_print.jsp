<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="${env.staticHost}/favicon.ico" />
<title>送货单打印</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0
}

body {
	font: 17px/1.5 "Microsoft YaHei", tahoma, arial, 'Hiragino Sans GB',
		'\5b8b\4f53', sans-serif;
	color: #333
}

h1 {
	font-size: 36px;
	text-align: center;
}

h2 {
	font-size: 15px;
	padding-bottom: 5px;
}

.w {
	width: 990px;
	margin: 0 auto;
}

.ct {
	padding-top: 10px;
}

.logo {
	height: 50px;
	width: auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	border-spacing: 0;
}

table td {
	text-align: center;
	vertical-align: top;
}

.topt {
	width: 70%;
}

.topt td {
	text-align: left;
	border-bottom: 1px solid #888;
}

.left td {
	text-align: left;
}

.border {
	border: 1px solid #888;
}

.border tr {
	border-bottom: 1px solid #888;
}

.border td {
	padding: 7px;
}

.qs {
	padding-top: 50px;
	border-bottom: 1px solid #888;
}

.qs table {
	margin-bottom: 10px;
}

.m {
	margin-bottom: 20px;
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
			<h1>送货单</h1>
			<div class="ct">
				<table>
					<tr>
						<td style="width: 50%;"><img class="logo"
							src="${env.staticHost}/mall/images/logo.png" /></td>
						<td>
							<table class="topt">
								<tr>
									<td>订&nbsp;&nbsp;单&nbsp;&nbsp;号：${order.code}</td>
								</tr>
								<tr>
									<td>订单时间：${fn:substring(order.createTime,0,19)}</td>
								</tr>
								<tr>
									<td>出库时间：${fn:substring(order.deliveryTime,0,19)}</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<div class="ct">
				<h2>送货地址：</h2>
				<table class="border left">
					<tr>
						<td colspan="2">
							收件地址：${order.address.province}${order.address.city}${order.address.area}${order.address.detail}
						</td>
					</tr>
					<tr>
						<td>联系人：${order.address.name}</td>
						<td>联系电话：${order.address.phone}</td>
					</tr>
				</table>
			</div>
			<div class="ct">
				<h2>发票信息：</h2>
				<table class="border left">
					<c:if test="${order.invoice eq null}">
						<tr>
							<td>不开票</td>
						</tr>
					</c:if>
					<c:if test="${order.invoice ne null}">
						<tr>
							<td>${order.invoice.company}</td>
							<td>开户银行：${order.invoice.bank}</td>
						</tr>
						<tr>
							<td>税号：${order.invoice.tax}</td>
							<td>银行账号：${order.invoice.account}</td>
						</tr>
						<tr>
							<td>电话：${order.invoice.phone}</td>
							<td>地址：${order.invoice.address}</td>
						</tr>
					</c:if>
				</table>
			</div>
			<div class="ct">
				<h2>商品信息：</h2>
				<table class="border">
					<tr>
						<td>序号</td>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>小计</td>
					</tr>
					<c:forEach var="orderGoods" items="${order.orderGoodsList}"
						varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td>${orderGoods.goodsName}</td>
							<td>x${orderGoods.amount}</td>
							<td>¥${orderGoods.price}</td>
							<td>¥${orderGoods.amount*orderGoods.price}</span></td>
						</tr>
					</c:forEach>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td style="text-align:right;">合计：</td>
						<td>¥${order.pay}</span></td>
					</tr>
				</table>
			</div>
			<div class="ct qs">
				<table>
					<tr>
						<td>签收人：__________________________</td>
						<td>签收日期：20____年______月______日</td>
					</tr>
				</table>
			</div>
			<div class="ct" style="font-size:14px;">
				<table class="left">
					<tr>
						<td>
							<p>温馨提示：</p>
							<p>感谢您选择“材来买”专业的广告材料电商平台！</p>
							<p>如发现商品破损或与样品不符等问题，请及时联系我们的售后人员。</p>
							<p>如配送人员没有送货上门或态度恶劣，您可以通过售后热线进行投诉，或寻求帮助。</p>
							<p>欢迎对我们的工作进行监督，我们将不断改进，直至您满意！祝您生活愉快，工作顺利！</p>
							<p>注：定制商品，属于个性化定制，不接受退换货，谢谢您的理解与支持。</p>
							<p>服务热线：400-921-9928 微信：cailaimai_com QQ：362933227</p>
						</td>
						<td style="width: 400px; text-align: center;"><img
							style="width: 130px; height: auto;"
							src="${env.staticHost}/mall/images/wechat02.jpg" />
							<p>微信扫一扫，关注材来买微信公众号</p>
							<p>及时了解最新特惠活动</p></td>
					</tr>
				</table>
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
	</form>
	<script>
		function toDecimal(x) {
			var tx = /^[0-9]+.?[0-9]*$/;
			if (!tx.test(x)) {
				return "0.00";
			}
			var f = Math.round(x * 100) / 100;
			var s = f.toString();
			var rs = s.indexOf('.');
			if (rs < 0) {
				rs = s.length;
				s += '.';
			}
			while (s.length <= rs + 2) {
				s += '0';
			}
			return s;
		}
		function fomatFloat(src, pos) {
			return Math.round(src * Math.pow(10, pos)) / Math.pow(10, pos);
		}
	</script>
</body>
</html>