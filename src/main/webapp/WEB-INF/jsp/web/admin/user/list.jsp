<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">用户管理</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">用户列表</header>
		<div class="table-responsive">
			<table id="user_list" class="table table-striped m-b-none"
				data-ride="datatables">
				<thead>
					<tr>
						<th style="width: 50px;" class="center">ID</th>
						<th style="width: 150px;" class="center">昵称</th>
						<th style="width: 150px;" class="center">手机号</th>
						<th>邮箱</th>
						<th style="width: 110px;" class="center">管理员</th>
						<th style="width: 180px;" class="center">注册时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${datas}">
						<tr>
							<td class="center">${data.id}</td>
							<td class="center">${data.name}</td>
							<td class="center">${data.mobile}</td>
							<td>${data.email}</td>
							<td class="center"><c:if test="${data.adminRank ne 0}">
									<i class='fa fa-check text-success'></i>
								</c:if> <c:if test="${data.adminRank eq 0}">
									<i class='fa fa-times text-danger'></i>
								</c:if></td>
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
		ADMIN.ui.table($("#user_list"));
	});
</script>