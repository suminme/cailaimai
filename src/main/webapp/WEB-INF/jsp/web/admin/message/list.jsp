<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">消息中心</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">我的消息</header>
		<div class="table-responsive">
			<table id="message_list" class="table table-striped m-b-none">
				<thead>
					<tr>
						<th style="width: 30px;" class="center">ID</th>
						<th>消息内容</th>
						<th style="width: 120px;" class="center">发送人</th>
						<th style="width: 160px;" class="center">发送时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${datas}">
						<tr>
							<td class="center">${data.id}</td>
							<td title="${data.content}">${data.content}</td>
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
		ADMIN.message.list();
	});
</script>