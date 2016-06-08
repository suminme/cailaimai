<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<style>
.at {
	width: 450px;
	color: #666;
}

.at tr {
	height: 40px;
}

.at th {
	width: 80px;
	text-align: right;
	font-weight: normal;
	color: #666;
	padding-right: 5px;
}

.at input {
	width: 300px;
}

.at select {
	width: 300px;
	height: 26px;
}

.at textarea {
	width: 300px;
}
</style>
<body>
	<form id="material_form" action="${env.host}/mall/order/material/add/"
		enctype="multipart/form-data" method="post">
		<table class="at">
			<tr>
				<th><font color="red">*</font>素材名称:</th>
				<td><input type="text" name="name" required /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>附件:</th>
				<td><input type="file" name="file" required /></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><button class="sb"
						type="submit">提交</button></td>
			</tr>
		</table>
	</form>
</body>
<script src="${env.staticHost}/mall/js/jquery-2.1.1.min.js"></script>
<script src="${env.staticHost}/ec/js/util.js"></script>
<script>
	$(document).ready(function() {
		<c:if test="${material ne null}">
		parent.addMaterial({
			id : "${material.id}",
			name : "${material.name}"
		});
		</c:if>
		var form = $("#material_form");
		var url = form.attr("action");
		form.submit(function() {
			var name = form.find("[name='name']").val();
			form.attr("action", url + "?name=" + encodeURI(name));
			form.find(".sb").html("上传中...")[0].disabled = true;
		});
	});
</script>