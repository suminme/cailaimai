<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">运费模板</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">模板列表</header>
		<div class="table-responsive">
			<table id="freight_list" class="table table-striped m-b-none"
				data-ride="datatables">
				<thead>
					<tr>
						<th style="width: 30px;" class="center">ID</th>
						<th style="width: 120px;" class="center">商城</th>
						<th style="width: 120px;" class="center">运费</th>
						<th style="width: 120px;" class="center">包邮金额</th>
						<th class="center">描述</th>
						<th style="width: 150px;" class="center">创建时间</th>
						<th style="width: 90px;" class="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${datas}">
						<tr>
							<td class="center">${data.id}</td>
							<td class="center">${data.name}</td>
							<td class="center">${data.freight}</td>
							<td class="center">${data.freightFree}</td>
							<td class="center">${data.freightAbout}</td>
							<td class="center" title="${data.createTime}">${fn:substring(data.createTime,0,16)}</td>
							<td class="center"><a
								href="#/admin/freight/update/?id=${data.id}" title="更新"><i
									class="fa fa-pencil text-danger"></i></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
</section>
<script>
	$(document).ready(function() {
		ADMIN.freight.list();
	});
</script>