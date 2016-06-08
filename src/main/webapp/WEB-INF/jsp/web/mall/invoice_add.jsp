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
	<form id="invoice_form" action="${env.host}/mall/order/invoice/add/"
		method="post">
		<table class="at">
			<tr>
				<th><font color="red">*</font>单位名称:</th>
				<td><input type="text" name="company" required /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>税号:</th>
				<td><input type="text" name="tax" required /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>开户银行:</th>
				<td><input type="text" name="bank" required /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>银行账号:</th>
				<td><input type="text" name="account" required /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>地址:</th>
				<td><input type="text" name="address" required /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>电话:</th>
				<td><input type="text" name="phone" required /></td>
			</tr>
			<tr>
				<th>备注:</th>
				<td><textarea name="about"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><button
						type="submit">保存开票信息</button></td>
			</tr>
		</table>
	</form>
</body>
<script src="${env.staticHost}/mall/js/jquery-2.1.1.min.js"></script>
<script src="${env.staticHost}/ec/js/util.js"></script>
<script>
	$(document).ready(function() {
		var form = $("#invoice_form");
		UTIL.ajax.form(form,null, function(json, formSubmit) {
			if(json.success) {
				parent.addInvoice(json.data);
			} else {
				alert("保存失败," + json.message);
			}
		});
	});
</script>