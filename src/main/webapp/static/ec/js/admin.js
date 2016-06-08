(function() {
	'use strict';
	var ADMIN = {
		init : {},
		order : {},
		goods : {},
		stock : {},
		freight : {},
		message : {},
		hash : {},
		ui : {}
	};
	window.ADMIN = ADMIN;
}());
(function() {
	'use strict';
	var os;
	ADMIN.init = function(hash, orderStatus) {
		os = orderStatus;
		ADMIN.hash.init(hash);
		setInterval(function() {
			ADMIN.message.unread();
		}, 30000);
		ADMIN.message.unread();
	}
	ADMIN.order.status = function() {
		$.each($("[status_code]"), function() {
			var c = $(this).attr("status_code");
			$(this).html(os[c]);
		});
	}
	ADMIN.order.list = function() {
		var table = $("#order_list");
		ADMIN.ui.table(table);
		$.each(table.find(".pay"), function() {
			ADMIN.order.updatePay($(this));
		});
	}
	ADMIN.order.detail = function(code) {
		$("[status_change]").click(function() {
			var c = $(this).attr("status_change");
			ADMIN.order.statusChange(code, c);
		});
	}
	ADMIN.order.statusChange = function(code, status) {
		var msg = "确认操作吗？";
		if (status == -1) {
			msg = "订单一旦取消不可恢复，确认取消吗？";
		}
		if (!confirm(msg)) {
			return;
		}
		var url = UTIL.config.host + "/admin/order/status/update/";
		UTIL.ajax.post(url, {
			code : code,
			status : status
		}, function(json) {
			if (json.success) {
				ADMIN.hash.reload();
				UTIL.noty.success("操作成功");
			} else {
				UTIL.noty.success("操作失败," + json.message);
			}
		});
	}
	ADMIN.order.updatePay = function(el) {
		var url = UTIL.config.host + "/admin/order/pay/update/";
		var code = el.parents("tr").attr("orderCode");
		el.editable({
			url : url,
			pk : 1,
			emptytext : '请输入',
			validate : function(value) {
				if ($.trim(value) == "") {
					return "请输入金额";
				}
			},
			params : function(params) {
				params.code = code;
				params.pay = params.value;
				return params;
			},
			success : function(json, newValue) {
				if (!json.success) {
					return json.message;
				}
				UTIL.noty.success("订单[" + code + "]金额修改成功");
			}
		});
	}
	var lastMessageId = 0;
	ADMIN.message.unread = function() {
		var url = UTIL.config.host + "/admin/message/unread/";
		var t = '<a href="javascript:;" onclick="ADMIN.message.read(ID,this);" class="media list-group-item"><span class="media-body block m-b-none">CONTENT<br/><small class="text-muted">TIME</small></span></a>';
		UTIL.ajax.get(url, null, function(json) {
			if (!json.success) {
				return;
			}
			var count = $(".umc").html("0");
			var container = $(".umct").html("");
			if (null == json.data || json.data.length <= 0) {
				return;
			}
			count.html(json.data.length);
			var lmi = json.data[0].id;
			if (lmi > lastMessageId) {
				$("#jplayer").jPlayer("play");
			}
			lastMessageId = lmi;
			$.each(json.data, function(k, v) {
				if (k > 3) {
					return false;
				}
				var h = t.replace("ID", v.id);
				h = h.replace("TIME", v.createTime)
				h = h.replace("CONTENT", v.content)
				container.append(h);
			});
		});
	}
	ADMIN.message.read = function(messageId) {
		var url = UTIL.config.host + "/admin/message/read/";
		UTIL.ajax.post(url, {
			messageId : messageId
		}, function(json) {
			if (json.success) {
				ADMIN.message.unread();
			}
		});
	}
	ADMIN.message.send = function() {
		var form = $("#message_form");
		form.find(".select2").select2();
		UTIL.ajax.form(form, null, function(json, formSubmit) {
			if (json.success) {
				ADMIN.message.unread();
				UTIL.noty.success("发送成功");
			} else {
				UTIL.noty.error(json.message);
				return;
			}
		});
	}
	ADMIN.message.list = function() {
		ADMIN.ui.table($("#message_list"));
	}
	ADMIN.freight.list = function() {
		ADMIN.ui.table($("#freight_list"));
	}
	ADMIN.freight.form = function() {
		var form = $("#freight_form");
		UTIL.ajax.form(form, null, function(json, formSubmit) {
			if (json.success) {
				UTIL.noty.success("提交成功");
				ADMIN.hash.change("/admin/freight/list/");
			} else {
				UTIL.noty.error(json.message);
				return;
			}
		});
	}
	ADMIN.stock.maintenance = function() {
		var form = $("#stock_form");
		form.find(".select2").select2(
				{
					templateResult : function(state) {
						if (!state.id) {
							return state.text;
						}
						var t = state.text.split(",");
						var $state = $('<p><b>商品编码:</b>' + t[0]
								+ '</p><p><b>当前库存:</b>' + t[1]
								+ '</p><p><b>商品名称:</b>' + t[2] + '</p>');
						return $state;
					}
				});
		UTIL.ajax.form(form, function() {
			var c = form.find("[name='number']").val();
			var o = form.find("[name='operate']").val();
			form.find("[name='num']").val(((o == "+") ? "" : "-") + c);
			return true;
		}, function(json, formSubmit) {
			if (json.success) {
				UTIL.noty.success("提交成功");
			} else {
				UTIL.noty.error(json.message);
				return;
			}
		});
	}
	ADMIN.goods.add = function() {
		var form = $("#goods_add_form");
		form.find("[name='typeId']").select2();
		form.submit(function(e) {
			e.preventDefault();
			var typeId = form.find("[name='typeId']").val();
			ADMIN.hash.change("/admin/goods/add/?typeId=" + typeId);
		});
	}
	ADMIN.goods.list = function() {
		var table = $("#goods_list");
		ADMIN.ui.table(table);
	}
	ADMIN.goods.del = function(ob, id) {
		if (!confirm("确认删除吗?")) {
			return;
		}
		var url = UTIL.config.host + "/admin/goods/delete/";
		UTIL.ajax.get(url, {
			id : id
		}, function(json) {
			if (json.success) {
				UTIL.noty.success("删除成功");
				ADMIN.hash.reload();
			} else {
				UTIL.noty.error(json.message);
			}
		});
	}
	ADMIN.goods.submit = function(typeId) {
		var form = $("#goods_submit_form");
		var stock = form.find("[name='stock']");
		form.find("[type='submit']").click(function() {
			ADMIN.goods.submitForm = true;
		});
		var price = form.find("[name='price']");
		price.change(function(){
			var p = $(this).val();
			$(this).val(toDecimal(p));
		});
		price.val(toDecimal(price.val()));
		UTIL.ajax.form(form, function() {
			return ADMIN.goods.submitForm;
		}, function(json, formSubmit) {
			if (json.success) {
				UTIL.noty.success("提交成功");
				formSubmit.html("提交成功").attr("disabled", true);
				ADMIN.hash.change("#/admin/goods/list/?typeId=" + typeId);
			} else {
				UTIL.noty.error(json.message);
				return;
			}
		});
		ADMIN.goods.imageUpload(UTIL.config.host, typeId);
		var uploadHost = UTIL.config.host
				+ "/admin/goods/image/upload/?typeId=" + typeId
				+ "&ckeditor=true";
		ADMIN.ui.ckeditor(form.find("[name='cert']"), uploadHost);
		ADMIN.ui.ckeditor(form.find("[name='content']"), uploadHost);
	}
	ADMIN.goods.imageUpload = function(host, typeId) {
		var uploader = $("#goods_image");
		var container = $("#goods_image_container");
		var url = host + "/admin/goods/image/upload/?typeId=" + typeId;
		var mm = '<form id="uploader_form" action="' + url
				+ '" method="post" enctype="multipart/form-data"></form>';
		uploader.wrap(mm);
		var form = $("#uploader_form");
		form.ajaxForm({
			dataType : 'json',
			success : function(json) {
				ADMIN.goods.imageUploadCallback(container, json);
			},
			error : function(xhr) {
				var r = eval("({'success':false,'message':'服务器异常'})");
				ADMIN.goods.imageUploadCallback(container, r);
			}
		});
		uploader.change(function() {
			var file = $(this).val().toLowerCase();
			var index = ADMIN.goods.getImageIndex(container);
			if (index[0] >= 0) {
				if (file.endWith(".jpg") || file.endWith(".jpeg")
						|| file.endWith(".png")) {
					container.find("li").eq(index[0]).append(
							'<div class="loading"></div>');
					ADMIN.goods.submitForm = false;
					form.submit();
				} else {
					alert("请上传jpg或png格式图片");
				}
			} else {
				alert("最多只能上传" + index[1] + "张图片");
				return;
			}
			$(this).val("");
		});
	}
	ADMIN.goods.imageUploadCallback = function(container, json) {
		var index = ADMIN.goods.getImageIndex(container);
		container.find("li").eq(index[0]).find(".loading").remove();
		if (!json.success) {
			alert("上传失败," + json.message);
			return;
		}
		var url = UTIL.config.storageHost + "/" + json.data.bucketKey;
		var li = container.find("li").eq(index[0]);
		li.addClass("full");
		var images = li.find("[name='imgId']");
		images.val(json.data.id);
		li
				.append('<img src="'
						+ url
						+ '"/><div class="operate"><a href="javascript:;" onclick="ADMIN.goods.imageDelete(this)"><i class="fa fa-trash-o"></i></a><div>');
	}
	ADMIN.goods.imageDelete = function(ob) {
		var li = $(ob).parents("li");
		li.removeClass("full");
		li.find("[name='imgId']").val("");
		li.find("img").remove();
		$(ob).parent().remove();
	}
	ADMIN.goods.getImageIndex = function(container) {
		var i = -1;
		var total = container.find("li").length;
		for (var n = 0; n < total; n++) {
			var ip = container.find("[name='imgId']").eq(n);
			if (ip.val() == "") {
				i = n;
				break;
			}
		}
		return [ i, total ];
	}
	ADMIN.goods.stockForm = function(typeId, stock) {
		var form = $("#goods_stock_updarte_form");
		form.find(".operate").change(function() {
			var n = 0;
			var c = form.find("[name='number']").val();
			var o = form.find("[name='operate']").val();
			if (o == "+") {
				n = parseFloat(stock) + parseFloat(c == "" ? "0" : c);
			} else {
				n = parseFloat(stock) - parseFloat(c == "" ? "0" : c);
			}
			$("#stock_after").html(n);
		});
		UTIL.ajax.form(form, function() {
			var c = form.find("[name='number']").val();
			var o = form.find("[name='operate']").val();
			form.find("[name='operateNum']").val(((o == "+") ? "" : "-") + c);
			return true;
		}, function(json, formSubmit) {
			if (json.success) {
				UTIL.noty.success("提交成功");
				formSubmit.html("提交成功").attr("disabled", true);
				ADMIN.hash.change("#/admin/goods/list/?typeId=" + typeId);
			} else {
				UTIL.noty.error(json.message);
				return;
			}
		});
	}
	ADMIN.hash.init = function(hash) {
		ADMIN.hash.history = hash;
		window.onhashchange = function() {
			var hash = window.location.hash.substring(1);
			if (hash != "" && hash != "/") {
				ADMIN.hash.load(window.location.hash.substring(1));
			} else {
				window.location.hash = ADMIN.hash.history;
			}
		}
		window.onhashchange();
	}
	ADMIN.hash.load = function(hash) {
		var url = UTIL.config.host + hash;
		if (url.indexOf("?") > 0) {
			url = url + "&";
		} else {
			url = url + "?";
		}
		url = url + "timestamp=" + new Date().getTime();
		UTIL.loader.start("加载中");
		$("#hash_load").load(url, function(r, s) {
			UTIL.loader.stop();
			if (s == "success") {
				ADMIN.hash.history = hash;
				ADMIN.hash.menu(hash);
				ADMIN.order.status();
			} else {
				location.hash = ADMIN.hash.history;
				UTIL.noty.error("加载出错");
			}
		});
	}
	ADMIN.hash.menu = function(hash) {
		$(".nav li").removeClass("active");
		$.each($(".nav").find("a"), function() {
			var href = $(this).attr("href");
			if (href == ("#" + hash)) {
				$(this).parents("li").addClass("active");
				return true;
			}
		});
	}
	ADMIN.hash.change = function(hash) {
		window.location.hash = hash;
	}
	ADMIN.hash.reload = function() {
		ADMIN.hash.load(window.location.hash.substring(1));
	}
	ADMIN.ui.ckeditor = function(ob, uploadHost) {
		CKEDITOR.editorConfig = function(config) {
			config.filebrowserImageUploadUrl = uploadHost;
			config.toolbar = [
					[ 'Bold', 'Italic', 'Underline', 'TextColor', 'Format',
							'FontSize' ],
					[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight',
							'JustifyBlock' ], [ 'Image', 'Link' ], [ 'Source' ] ];
			config.removeButtons = 'Underline,Subscript,Superscript';
			config.format_tags = 'p;h1;h2;h3;pre';
			config.removeDialogTabs = 'image:advanced;link:advanced';
		};
		$(ob).ckeditor();
	}
	ADMIN.ui.table = function(table) {
		$.each(table.find("[bit]"), function() {
			if ($(this).attr("bit") == "true") {
				$(this).html("<i class='fa fa-check text-success'></i>");
			} else {
				$(this).html("<i class='fa fa-times text-danger'></i>");
			}
		});
		table
				.dataTable({
					"bLengthChange" : false,
					ordering : false,
					"columnDefs" : [ {
						"orderable" : false,
						"aTargets" : [ 'unsortable' ]
					} ],
					bInfo : false,
					"oLanguage" : {
						"sProcessing" : "正在处理...",
						"sLengthMenu" : "每页_MENU_行",
						"sEmptyTable" : "没有找到记录",
						"sZeroRecords" : "没有找到记录",
						"sInfo" : "总记录数_TOTAL_当前显示_START_至_END_",
						"sInfoEmpty" : "",
						"sSearch" : "搜索&nbsp;",
						"sSearchPlaceholder" : "搜索",
						"oPaginate" : {
							"sFirst" : "首页",
							"sLast" : "未页",
							"sNext" : "下页",
							"sPrevious" : "上页"
						}
					},
					"sDom" : "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>"
				});
	}
}());
String.prototype.endWith = function(str) {
	var reg = new RegExp(str + "$");
	return reg.test(this);
}
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