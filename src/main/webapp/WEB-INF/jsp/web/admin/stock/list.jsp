<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">库存管理</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading"> 库存明细 </header>
		<div class="table-responsive">
			<table id="stock_list" class="table table-striped m-b-none"
				data-ride="datatables">
				<thead>
					<tr>
						<th style="width: 120px;" class="center">商品编码</th>
						<th style="width: 120px;" class="center">变更数</th>
						<th>供应商</th>
						<th>说明</th>
						<th style="width: 100px;" class="center">生效</th>
						<th style="width: 120px;" class="center">操作人</th>
						<th style="width: 160px;" class="center">操作时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${datas}">
						<tr>
							<td class="center"><a target="_blank"
								href="${env.host}/mall/goods/${data.goodsId}.html">${data.goodsCode}</a></td>
							<td class="center">${data.num}</td>
							<td title="${data.supplier}">${data.supplier}</td>
							<td title="${data.description}">${data.description}</td>
							<td class="center"><span bit="${data.valid}"></span></td>
							<td class="center">${data.creater}</td>
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
		ADMIN.ui.table($("#stock_list"));
	});
</script>