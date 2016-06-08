<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">消息中心</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">发送消息</header>
		<div class="panel-body">
			<form id="message_form" action="${env.host}/admin/message/send/"
				method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">接收人<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<select name="toUserId" class="form-control select2" required>
							<option value="">===请选择===</option>
							<option value="-1">所有会员</option>
							<c:forEach var="user" items="${userList}">
								<option value="${user.id}">${user.name}(${user.mobile})</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">消息内容<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<textarea name="content" class="form-control" rows="10" required></textarea>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-2">
						<button type="submit" class="btn btn-primary form_submit">发送</button>
					</div>
				</div>
			</form>
		</div>
	</section>
</section>
<script>
	$(document).ready(function() {
		ADMIN.message.send();
	});
</script>