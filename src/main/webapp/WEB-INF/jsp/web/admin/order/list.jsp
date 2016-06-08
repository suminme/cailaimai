<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">订单管理</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">订单列表</header>
		<div class="table-responsive">
			<table id="order_list" class="table table-striped m-b-none"
				data-ride="datatables">
				<thead>
					<tr>
						<th style="width: 110px;" class="center">订单编号</th>
						<th style="width: 110px;" class="center">所属商城</th>
						<th class="center">用户</th>
						<th class="center">商品金额</th>
						<th class="center">运费金额</th>
						<th class="center">订单总额</th>
						<th class="center">支付金额</th>
						<th style="width: 160px;" class="center">创建时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${datas}">
						<tr orderCode="${data.code}">
							<td class="center"><a
								href="#/admin/order/detail/?code=${data.code}">${data.code}</a></td>
							<td class="center">${data.mallName}</td>
							<td class="center">${data.userName}</td>
							<td class="center">${data.money}</td>
							<td class="center">${data.freight}</td>
							<td class="center">${data.total}</td>
							<td class="center">
								<c:if test="${data.status eq 1}">
									<a class="pay" data-type="text"
										data-placement="left" data-title="请输入支付金额" href="javascript:;">${data.pay}</a>
								</c:if>
								<c:if test="${data.status ne 1}">
									${data.pay}
								</c:if>
							</td>
							<td class="center" title="${data.createTime}">${fn:substring(data.createTime,0,19)}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
</section>
<script>
	$(document).ready(function() {
		ADMIN.order.list();
	});
</script>