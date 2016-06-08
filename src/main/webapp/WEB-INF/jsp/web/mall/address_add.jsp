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
	<form id="address_form"
		action="${env.host}/mall/order/address/add/" method="post">
		<table class="at">
			<tr>
				<th><font color="red">*</font>联系人:</th>
				<td><input type="text" name="name" required /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>手机:</th>
				<td><input type="text" name="phone" required /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>省:</th>
				<td><select name="provinceId" required>
				</select> <input type="hidden" name="province" value="" /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>市:</th>
				<td><select name="cityId" required>
				</select> <input type="hidden" name="city" value="" /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>区:</th>
				<td><select name="areaId" required>
				</select> <input type="hidden" name="area" value="" /></td>
			</tr>
			<tr>
				<th><font color="red">*</font>详细地址:</th>
				<td><textarea name="detail" required></textarea></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;"><button type="submit">保存收货地址</button></td>
			</tr>
		</table>
	</form>
</body>
<script src="${env.staticHost}/mall/js/jquery-2.1.1.min.js"></script>
<script src="${env.staticHost}/ec/js/util.js"></script>
<script>
	var rank = [ "provinceId", "cityId", "areaId" ];
	var defaultVal = [ "", "", "" ];
	$(document).ready(function() {
		initSelect(0, 0);
		var form = $("#address_form");
		UTIL.ajax.form(form, function() {
			atext(form);
			return true;
		}, function(json, formSubmit) {
			if(json.success) {
				parent.addAddress(json.data);
			} else {
				alert("保存失败," + json.message);
			}
		});
	});
	function atext(form) {
		var p = form.find("[name='provinceId']").find("option:selected").text();
		form.find("[name='province']").val(p);
		var c = form.find("[name='cityId']").find("option:selected").text();
		form.find("[name='city']").val(c);
		var a = form.find("[name='areaId']").find("option:selected").text();
		form.find("[name='area']").val(a);
	}
	function initSelect(index, pid) {
		if (index >= rank.length) {
			return;
		}
		var target = $("[name='" + rank[index] + "']");
		getAddressData(pid, function(data) {
			target.empty();
			if (data == null || data.length == 0) {
				return;
			}
			data.unshift({
				"id" : "",
				"text" : "===请选择==="
			});
			var dfi = data[0].id;
			if (defaultVal[index] != "") {
				dfi = defaultVal[index];
				defaultVal[index] = "";
			}
			$.each(data, function(k, v) {
				var option = $("<option>").val(v.id).text(v.text);
				target.append(option);
			});
			target.val(dfi);
			initSelect(index + 1, dfi);
		});
		target.unbind("change").change(function() {
			initSelect(index + 1, $(this).val());
		});
	}
	function getAddressData(pid, callback) {
		if (pid == "") {
			callback(null);
		}
		UTIL.ajax.get("${env.host}/mall/order/address/data/", {
			pid : pid
		}, function(json) {
			if (!json.success) {
				return;
			}
			callback(json.data);
		});
	}
</script>