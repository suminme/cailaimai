(function() {
	'use strict';
	var MALL = {
		init : {},
		signin : {},
		signup : {},
		signout : {},
		forget : {},
		captcha : {},
		cart : {},
		order : {}
	};
	window.MALL = MALL;
}());
(function() {
	'use strict';
	var sid;
	MALL.init = function(signinUserId) {
		sid = signinUserId;
		if (signinUserId != "") {
			MALL.signout.auto(sid);
		}
		$("#mall_search").submit(function(e) {
			var s = $(this).find("[name='keyword']").val();
			if($.trim(s)=="") {
				e.preventDefault();
			}
		});
	}
	MALL.signout.auto = function() {
		$.cookie("lot", new Date().getTime(), {
			path : UTIL.config.contextPath + "/"
		});
		document.onkeydown = function() {
			$.cookie("lot", new Date().getTime(), {
				path : UTIL.config.contextPath + "/"
			});
		}
		document.onmousemove = function() {
			$.cookie("lot", new Date().getTime(), {
				path : UTIL.config.contextPath + "/"
			});
		}
		var si = setInterval(function() {
			var lot = $.cookie("lot");
			if (new Date().getTime() - lot > 1000 * 60 * 30) {
				clearInterval(si);
				location.href = UTIL.config.host + "/signout/";
				alert("登陆超时");
			}
		}, 60 * 1000);
	}
	MALL.signin.init = function(r) {
		var form = $("#signin_form");
		var error = form.find(".error");
		var password = "";
		UTIL.ajax.form(form, function(form) {
			var pb = form.find("[name='password']");
			password = pb.val();
			pb.val($.md5(password));
			error.hide();
			return true;
		}, function(json, formSubmit) {
			if (json.success) {
				MALL.signin.cookie(json.data);
				var hash = window.location.hash;
				formSubmit.html("登陆成功").attr("disabled", true);
				setTimeout(function() {
					location.href = r + hash;
				}, 100);
			} else {
				error.html(json.message).show();
				form.find("[name='password']").val(password);
				return;
			}
		});
	}
	MALL.signin.cookie = function(token) {
		$.cookie("token", token.token, {
			path : token.path
		});
	}
	MALL.signup.init = function() {
		var form = $("#signup_form");
		var password = "";
		var error = form.find(".error");
		UTIL.ajax.form(form, function(form) {
			var protocal = form.find("[name='protocal']")[0].checked;
			if (!protocal) {
				error.html("您必须先同意并遵守我们的服务条款").show();
				return false;
			}
			var pb = form.find("[name='password']");
			password = pb.val();
			pb.val($.md5(password));
			return true;
		}, function(json, formSubmit) {
			if (json.success) {
				formSubmit.html("注册成功").attr("disabled", true);
				setTimeout(function() {
					location.href = UTIL.config.host + "/";
				}, 100);
			} else {
				error.html(json.message).show();
				form.find("[name='password']").val(password);
				return;
			}
		});
		form.find("#captcha_button").click(function() {
			var mobile = form.find("[name='mobile']").val();
			MALL.captcha.send(mobile, "signup", $(this));
		});
	}
	MALL.forget.init = function() {
		var form = $("#forget_form");
		var password = "";
		var error = form.find(".error");
		UTIL.ajax.form(form, function(form, autoValidateResult) {
			var pb = form.find("[name='password']");
			password = pb.val();
			pb.val($.md5(password));
			return true;
		}, function(json, formSubmit) {
			if (json.success) {
				formSubmit.html("重置成功").attr("disabled", true);
				setTimeout(function() {
					location.href = UTIL.config.host + "/signin/";
				}, 500);
			} else {
				alert(json.message);
				form.find("[name='password']").val(password);
				return;
			}
		});
		form.find("#captcha_button").click(function() {
			var mobile = form.find("[name='mobile']").val();
			MALL.captcha.send(mobile, "forget", $(this));
		});
	}
	MALL.captcha.send = function(mobile, usedFor, button) {
		var defaultButtonText = button.html();
		button.html("发送中...")[0].disabled = true;
		var url = UTIL.config.host + "/captcha/";
		UTIL.ajax.get(url, {
			mobile : mobile,
			usedFor : usedFor
		}, function(json) {
			if (json.success) {
				alert("验证码已发送");
				var waitSecond = json.data;
				var i = setInterval(function() {
					if (--waitSecond <= 0) {
						clearInterval(i);
						button.html(defaultButtonText)[0].disabled = false;
					} else {
						button.html(waitSecond + "秒后重新获取");
					}
				}, 1 * 1000);
			} else {
				alert(json.message);
				button.html(defaultButtonText)[0].disabled = false;
				return;
			}
		});
	}
	MALL.cart.add = function(goodsId, amount, redirect) {
		var url = UTIL.config.host + "/mall/cart/add/";
		url = url + "?goodsId=" + goodsId;
		if (amount != null) {
			url = url + "&amount=" + amount;
		}
		// if (sid == "" || redirect) {
		if (true) {
			url = url + "&back=" + window.location.href;
			location.href = url;
			return;
		}
		UTIL.ajax.post(url, null, function(json) {
			if (json.success) {
				$("#cart_count").html(json.data.count);
				$("#cart_money").html(json.data.money);
				alert("成功添加到购物车");
			} else {
				alert("添加到购物车失败");
			}
		});
	}
	MALL.cart.list = function(goodsId, amount, redirect) {
		$(".submit-btn").click(function(e) {
			var c = MALL.cart.compute();
			if (c.count == 0) {
				alert("请选择商品");
				return;
			}
			var at = null;
			$.each($("[name='amount']"), function() {
				if ($(this)[0].disabled) {
					return true;
				}
				var s = parseFloat(toDecimal($(this).attr("stock")));
				var a = parseFloat(toDecimal($(this).val()));
				if (a > s) {
					at = $(this).attr("at");
					return false;
				}
			});
			if (at != null) {
				alert("[" + at + "]库存不足,请联系管理员");
				return;
			}
			$("#cart_form").submit();
		});
		$("[name='mallCheck']").click(function() {
			var c = $(this)[0].checked;
			var mallId = $(this).val();
			var ob = $("[mallId='" + mallId + "']");
			ob.find("[name='goodsCheck']")[0].checked = c;
			MALL.cart.compute();
		});
		$("[name='amount']").change(function() {
			MALL.cart.compute();
		});
		$("[name='goodsCheck']").change(function() {
			MALL.cart.compute();
		});
		MALL.cart.compute();
	}
	MALL.cart.compute = function() {
		var count = 0;
		var total = 0;
		var mc = {};
		var mt = {};
		$.each($(".pricestr"), function() {
			var p = $(this).html();
			$(this).html(toDecimal(p));
		});
		$.each($(".goods-check"), function(e) {
			var item = $(this);
			var mallId = item.attr("mallId");
			var amount = parseFloat(item.find("[name='amount']").val());
			var price = parseFloat(item.find("[name='price']").val());
			if (amount <= 0) {
				amount = 1;
			}
			if (!mt[mallId]) {
				mt[mallId] = 0;
			}
			var st = price * amount;
			if (item.find("[name='goodsCheck']")[0].checked) {
				count++;
				total = total + st;
				mt[mallId] = mt[mallId] + st;
				item.addClass("item-selected");
				item.find(".sfv").attr("disabled", false);
			} else {
				mc[mallId] = "n";
				item.removeClass("item-selected");
				item.find(".sfv").attr("disabled", true);
			}
			item.find("[name='amount']").val(toDecimal(amount));
		});
		$.each($("[mallTotal]"), function() {
			var mallId = $(this).attr("mallTotal");
			$(this).html("¥" + toDecimal(mt[mallId]));
		});
		$.each($("[name='mallCheck']"), function() {
			if (typeof (mc[$(this).val()]) == "undefined") {
				$(this)[0].checked = true;
			} else {
				$(this)[0].checked = false;
			}
		});
		$(".count").html(toDecimal(count));
		$(".total").html("¥" + toDecimal(total));
		return {
			"count" : count,
			"total" : total
		};
	}
	MALL.cart.del = function(id, t) {
		if (!confirm("确认删除吗?")) {
			return;
		}
		var url = UTIL.config.host + "/mall/cart/delete/";
		UTIL.ajax.post(url, {
			id : id
		}, function(json) {
			if (json.success) {
				MALL.cart.compute();
			} else {
				alert("删除失败");
			}
			location.reload();
		});
	}
	MALL.order.form = function() {
		var form = $("#order_form");
		$(".submit-btn").click(function(e) {
			form.submit();
		});
		MALL.order.compute();
	}
	MALL.order.compute = function() {
		var count = 0;
		var total = 0;
		var addressId = 0;
		$.each($("[name='addressId']"), function() {
			if ($(this)[0].checked) {
				addressId = parseInt($(this).val());
				return true;
			}
		});
		$.each($(".pricestr"), function() {
			var p = $(this).html();
			$(this).html(toDecimal(p));
		});
		$.each($("[name='orderMoney']"), function(k, v) {
			var item = $(this);
			var money = item.val();
			var freight = $("[name='orderFreight']").eq(k).val();
			if (addressId <= 0) {
				freight = 0;
			}
			var t = parseFloat(money) + parseFloat(freight);
			$(this).parent().find(".ctotal").html("¥" + toDecimal(t));
			$(this).parent().find(".cfreight").html("¥" + toDecimal(freight));
			total = total + t;
		});
		$(".total").html("¥" + toDecimal(total));
		$(".count").html($("[name='goodsId']").length);
	}
}());
function toDecimal(x) {
	var tx = /^[0-9]+.?[0-9]*$/;
	if (!tx.test(x)) {
		return "0.00";
	}
	var f = Math.round(x * 100) / 100;
	var s = f.toString();
	var rs = s.indexOf('.');
	if (rs < 0) {
		rs = s.length;
		s += '.';
	}
	while (s.length <= rs + 2) {
		s += '0';
	}
	return s;
}
function fomatFloat(src, pos) {
	return Math.round(src * Math.pow(10, pos)) / Math.pow(10, pos);
}