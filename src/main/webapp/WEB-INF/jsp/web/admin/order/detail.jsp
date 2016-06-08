<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.hb a {
	margin-left: 5px;
}
</style>
<header class="hb header b-b b-light hidden-print bg-white">
	<c:if test="${order.status ne -1}">
		<a href="javascript:;" class="btn btn-sm btn-warning pull-right"
			onclick="window.print();">打印</a>
	</c:if>
	<c:if test="${order.status eq 1}">
		<a status_change="-1" href="javascript:;"
			class="btn btn-sm btn-danger pull-right">取消订单</a>
		<a status_change="2" href="javascript:;"
			class="btn btn-sm btn-info pull-right">确认收款</a>
	</c:if>
	<c:if test="${order.status eq 2}">
		<a status_change="3" href="javascript:;"
			class="btn btn-sm btn-info pull-right">商品出库</a>
	</c:if>
	<c:if test="${order.status eq 3}">
		<a target="_blank"
			href="${env.host}/admin/order/delivery/print/${order.code}/"
			class="btn btn-sm btn-info pull-right">送货单打印</a>
		<a status_change="4" href="javascript:;"
			class="btn btn-sm btn-success pull-right">完成订单</a>
	</c:if>
	<p>订单明细</p>
</header>
<section class="scrollable wrapper bg-white">
	<div class="row">
		<div class="col-xs-6">
			<h4>#${order.code}</h4>
		</div>
		<div class="col-xs-6 text-right">
			<h5>${fn:substring(order.createTime,0,19)}</h5>
		</div>
	</div>
	<p class="m-t m-b">
		订单状态: <strong status_code="${order.status}"></strong>
	</p>
	<c:if test="${order.material ne null}">
		<p class="m-t m-b hidden-print">
			订单素材: <strong><a target="_blank"
				href="${env.storageHost}/${order.material.filePath}">${order.material.name}</a></strong>
		</p>
	</c:if>
	<p class="m-t m-b">
		订单备注: <strong>${order.about}</strong>
	</p>
	<div class="well b bg-light m-t">
		<div class="row">
			<div class="col-xs-6">
				<strong>收货信息:</strong>
				<c:if test="${order.address eq null}">
					<h4>自提</h4>
				</c:if>
				<c:if test="${order.address ne null}">
					<h4>${order.address.name}</h4>
					<p>${order.address.phone}</p>
					<p>
						${order.address.province},${order.address.city},${order.address.area},${order.address.detail}
					</p>
				</c:if>
			</div>
			<div class="col-xs-6">
				<strong>开票信息:</strong>
				<c:if test="${order.invoice eq null}">
					<h4>不开票</h4>
				</c:if>
				<c:if test="${order.invoice ne null}">
					<h4>${order.invoice.company}</h4>
					<p>税号:${order.invoice.tax}</p>
					<p>开户行:${order.invoice.bank},${order.invoice.account}</p>
					<p>联系人:${order.invoice.phone},${order.invoice.address}</p>
				</c:if>
			</div>
		</div>
	</div>
	<div class="line"></div>
	<table class="table">
		<thead>
			<tr>
				<th width="60" style="text-align: center;">序号</th>
				<th>商品名称</th>
				<th width="140" style="text-align: center;">数量</th>
				<th width="140" style="text-align: center;">单价</th>
				<th width="90" style="text-align: right;">合计</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="orderGoods" items="${order.orderGoodsList}"
				varStatus="status">
				<tr>
					<td style="text-align: center;">${status.index+1}</td>
					<td style="cursor: pointer;"
						onclick="window.open('${env.host}/mall/goods/${orderGoods.goodsId}.html')">${orderGoods.goodsName}</td>
					<td style="text-align: center;">x${orderGoods.amount}</td>
					<td style="text-align: center;">¥${orderGoods.price}</td>
					<td style="text-align: right;">¥${orderGoods.amount*orderGoods.price}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" class="text-right"><strong>商品总额：</strong></td>
				<td style="text-align: right;">¥${order.money}</td>
			</tr>
			<tr>
				<td colspan="4" class="text-right no-border"><strong>运费金额：</strong></td>
				<td style="text-align: right;">¥${order.freight}</td>
			</tr>
			<tr>
				<td colspan="4" class="text-right no-border"><strong>订单总金额：</strong></td>
				<td style="text-align: right;">¥${order.total}</td>
			</tr>
			<tr>
				<td colspan="4" class="text-right no-border"><strong>订单支付金额：</strong></td>
				<td style="text-align: right;"><strong>¥${order.pay}</strong></td>
			</tr>
		</tbody>
	</table>
</section>
<script>
	$(document).ready(function() {
		ADMIN.order.detail("${order.code}");
	});
</script>