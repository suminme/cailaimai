(function() {
	'use strict';
	var UTIL = {
		config : {},
		init : {},
		form : {},
		ajax : {},
		noty : {},
		loader : {},
		mobile : {}
	};
	window.UTIL = UTIL;
}());
(function() {
	'use strict';
	UTIL.init = function(config) {
		UTIL.config = config;
	}
	/**
	 * 
	 */
	UTIL.ajax.get = function(url, data, callback) {
		UTIL.ajax.json("get", url, data, callback);
	}
	/**
	 * 
	 */
	UTIL.ajax.post = function(url, data, callback) {
		UTIL.ajax.json("post", url, data, callback);
	}
	/**
	 * 
	 */
	UTIL.ajax.json = function(method, url, data, callback) {
		$
			.ajax({
				url : url,
				data : data,
				cache : false,
				type : method,
				dataType : "json",
				error : function(r) {
					if (typeof callback != "undefined") {
						callback(eval("({'success':false,'message':'系统错误，请稍后再试'})"));
					}
				},
				success : function(json) {
					if (typeof callback != "undefined") {
						callback(json);
					}
				}
			});
	};
	/**
	 * 
	 */
	UTIL.ajax.form = function(form, validate, callback) {
		form.find("[focus]").focus();
		$.each(form.find("select"), function() {
			var v = $(this).attr("value");
			if (v != "" && typeof (v) != "undefined") {
				$(this).val($(this).attr("value"));
			}
		});
		$.each(form.find("input[type='checkbox']"), function() {
			$(this)[0].checked = ($(this).attr("ck") == "true");
		});
		form.submit(function(e) {
			e.preventDefault();
			if (validate && !validate(form)) {
				return;
			}
			var formdata = $(this).serialize();
			var formSubmit = form.find(".form_submit").attr("disabled", true);
			UTIL.ajax.post(form.attr("action"), formdata, function(json) {
				formSubmit.removeAttr("disabled");
				if (callback) {
					callback(json, formSubmit);
				}
			});
		});
	}
	/**
	 * 
	 */
	UTIL.noty.success = function(text) {
		UTIL.noty.noty("success", text);
	}
	/**
	 * 
	 */
	UTIL.noty.error = function(text) {
		UTIL.noty.noty("error", text);
	}
	/**
	 * 
	 */
	UTIL.noty.noty = function(type, text, layout) {
		noty({
			text : text,
			type : type,
			killer : true,
			timeout : 5000,
			layout : layout ? layout : "topRight",
			closeWith : [ 'button', 'click' ]
		});
	}
	var loading_overlay;
	/**
	 * 
	 */
	UTIL.loader.start = function(text) {
		if (loading_overlay) {
			return;
		}
		loading_overlay = iosOverlay({
			text : text,
			duration : 2000,
			icon : "fa fa-spinner fa-spin"
		});
	}
	/**
	 * 
	 */
	UTIL.loader.stop = function(text) {
		loading_overlay.destroy();
		loading_overlay = null;
	}
	/**
	 * 
	 */
	UTIL.mobile = function() {
		if ( (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) ) {
			return true;
		}
		return false;
	}
}());