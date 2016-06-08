(function() {
	'use strict';
	var VIP = {
		user : {},
		mall : {},
		hash : {},
		ui : {}
	};
	window.VIP = VIP;
}());
(function() {
	'use strict';
	VIP.mall.orderList = function() {
		var table = $("#order_list");
		VIP.ui.table(table);
	}
	VIP.user.profile = function() {
		var form = $("#user_profile_form");
		UTIL.ajax.form(form, null, function(json, formSubmit) {
			if (json.success) {
				alert("修改成功");
			} else {
				alert(json.message);
				return;
			}
		});
	}
	VIP.user.security = function() {
		var form = $("#user_security_form");
		var oldPassword, password;
		UTIL.ajax.form(form, function(form) {
			var p = form.find("[name='password']");
			var op = form.find("[name='oldPassword']");
			var p2 = form.find("[name='password2']").val();
			password = p.val();
			oldPassword = op.val();
			if (password != p2) {
				alert("两次输入的密码不一致");
				return false;
			}
			p.val($.md5(password));
			op.val($.md5(oldPassword));
			return true;
		}, function(json, formSubmit) {
			form.find("[name='password']").val(password);
			form.find("[name='oldPassword']").val(oldPassword);
			if (json.success) {
				alert("修改成功");
				formSubmit.html("修改成功").attr("disabled", true);
				setTimeout(function() {
					location.reload();
				}, 500);
			} else {
				alert(json.message);
				return;
			}
		});
	}
	VIP.user.addressList = function() {
		var table = $("#address_list");
		VIP.ui.table(table);
	}
	VIP.user.addressDataText = function(form) {
		var p = form.find("[name='provinceId']").find("option:selected").text();
		form.find("[name='province']").val(p);
		var c = form.find("[name='cityId']").find("option:selected").text();
		form.find("[name='city']").val(c);
		var a = form.find("[name='areaId']").find("option:selected").text();
		form.find("[name='area']").val(a);
	}
	VIP.user.addressForm = function(callback) {
		var form = $("#address_form");
		UTIL.ajax.form(form, function() {
			VIP.user.addressDataText(form);
			return true;
		}, function(json, formSubmit) {
			if (callback) {
				callback(json, formSubmit);
				return;
			}
			if (json.success) {
				alert("提交成功");
				VIP.hash.change("/vip/user/address/list/");
			} else {
				alert(json.message);
				return;
			}
		});
	}
	VIP.user.addressDelete = function(id) {
		if (!confirm("确认删除吗?")) {
			return;
		}
		var url = UTIL.config.host + "/vip/user/address/delete/";
		UTIL.ajax.get(url, {
			id : id
		}, function(json) {
			if (json.success) {
				alert("删除成功");
				location.reload();
			} else {
				alert("删除失败" + json.message);
			}
		});
	}
	VIP.user.invoiceList = function() {
		var table = $("#invoice_list");
		VIP.ui.table(table);
	}
	VIP.user.invoiceForm = function(callback) {
		var form = $("#invoice_form");
		UTIL.ajax.form(form, null, function(json, formSubmit) {
			if (callback) {
				callback(json, formSubmit);
				return;
			}
			if (json.success) {
				alert("提交成功");
				VIP.hash.change("/vip/user/invoice/list/");
			} else {
				alert(json.message);
				return;
			}
		});
	}
	VIP.user.invoiceDelete = function(id) {
		if (!confirm("确认删除吗?")) {
			return;
		}
		var url = UTIL.config.host + "/vip/user/invoice/delete/";
		UTIL.ajax.get(url, {
			id : id
		}, function(json) {
			if (json.success) {
				alert("删除成功");
				location.reload();
			} else {
				alert("删除失败" + json.message);
			}
		});
	}
	VIP.ui.table = function(table) {
		
	}
}());