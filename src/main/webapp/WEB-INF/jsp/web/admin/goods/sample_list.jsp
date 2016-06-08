<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">商品管理</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">样品申请列表</header>
		<div class="table-responsive">
			<table id="goods_sample_list" class="table table-striped m-b-none"
				data-ride="datatables">
				<thead>
					<tr>
						<th style="width: 40px;" class="center">ID</th>
						<th style="width: 120px;" class="center">用户</th>
						<th>商品名称</th>
						<th>收货地址</th>
						<th>备注</th>
						<th style="width: 160px;" class="center">申请时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${datas}">
						<tr>
							<td class="center">${data.id}</td>
							<td class="center" title="${data.userId}">${data.userMobile}</td>
							<td><a target="_blank"
								href="${env.host}/mall/goods/${data.goodsId}.html">${data.goodsName}</a></td>
							<td>${data.address}</td>
							<td>${data.about}&nbsp;</td>
							<td class="center" title="${data.createTime}">${fn:substring(data.createTime,0,16)}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
</section>
<script>
	$(document).ready(function() {
		var table = $("#goods_sample_list");
		ADMIN.ui.table(table);
	});
</script>