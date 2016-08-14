<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body>
	<div class="nc-main">
		<header data-am-widget="header" class="am-header bgc5">
			<div class="am-header-left am-header-nav">
				<a href="javascript:;" onclick="history.back()"> <i
					class="am-header-icon am-icon-angle-left am-icon-md"></i>
				</a>
			</div>
			<div
				class="am-header-title am-header-title-txt am-form-group am-form-icon am-form-feedback">
				新增地址</div>
			<div class="am-header-nav am-header-right">
				<a href="${env.host}/m/"><i class="am-icon-home am-icon-sm"></i></a>
			</div>
		</header>
		<div class="am-g">
			<div class="am-u-md-12 am-u-sm-centered">
				<form id="address_form" method="post"
					action="${env.host}/m/personal/address/add/">
					<input type="hidden" name="id" value="${data.id}" /> <br />
					<div class="am-form-group am-form-group-lg">
						<input type="text" name="name" class="am-form-field"
							value="${data.name}" placeholder="联系人" autocomplete="off"
							required focus />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<input type="text" name="phone" class="am-form-field"
							value="${data.phone}" placeholder="手机" autocomplete="off"
							required />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<select name="provinceId" class="am-form-field" required>
						</select> <input type="hidden" name="province" value="" />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<select name="cityId" class="am-form-field" required>
						</select> <input type="hidden" name="city" value="" />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<select name="areaId" class="am-form-field" required>
						</select> <input type="hidden" name="area" value="" />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<input type="text" name="detail" class="am-form-field"
							value="${data.detail}" placeholder="详细地址" autocomplete="off"
							required />
					</div>
					<button type="submit"
						class="form_submit am-btn am-btn-block am-btn-warning">提交</button>
				</form>
			</div>
		</div>
		<div nc-tips width="120px"></div>
	</div>
	<jsp:include page="../include/footer.jsp" />
	<script>
		var rank = [ "provinceId", "cityId", "areaId" ];
		var defaultVal = [ "${data.provinceId}", "${data.cityId}", "${data.areaId}" ];
		$(document).ready(function() {
			initSelect(0, 0);
			var r = "${r}";
			var form = $("#address_form");
			UTIL.ajax.form(form, null, function(json, formSubmit) {
				if (json.success) {
					alert("保存成功");
					if (r != "") {
						location.href = r;
					}
					else {
						location.href = "${env.host}/m/personal/address/";
					}
				}
				else {
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
</body>
</html>