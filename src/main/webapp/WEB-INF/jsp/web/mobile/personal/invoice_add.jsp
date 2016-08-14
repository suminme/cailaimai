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
				新增开票</div>
			<div class="am-header-nav am-header-right">
				<a href="${env.host}/m/"><i class="am-icon-home am-icon-sm"></i></a>
			</div>
		</header>
		<div class="am-g">
			<div class="am-u-md-12 am-u-sm-centered">
				<form id="invoice_form" method="post"
					action="${env.host}/m/personal/invoice/add/">
					<input type="hidden" name="id" value="${data.id}" />
					<br />
					<div class="am-form-group am-form-group-lg">
						<input type="text" name="company" class="am-form-field"
							value="${data.company}" placeholder="单位名称" autocomplete="off"
							required focus />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<input type="text" name="tax" class="am-form-field"
							value="${data.tax}" placeholder="税号" autocomplete="off" required />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<input type="text" name="bank" class="am-form-field"
							value="${data.bank}" placeholder="开户银行" autocomplete="off"
							required />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<input type="text" name="account" class="am-form-field"
							value="${data.account}" placeholder="银行账号" autocomplete="off"
							required />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<input type="text" name="address" class="am-form-field"
							value="${data.address}" placeholder="地址" autocomplete="off"
							required />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<input type="text" name="phone" class="am-form-field"
							value="${data.phone}" placeholder="电话" autocomplete="off"
							required />
					</div>
					<div class="am-form-group am-form-group-lg"
						style="margin-top: -10px">
						<input type="text" name="about" class="am-form-field"
							value="${data.about}" placeholder="备注" autocomplete="off" />
					</div>
					<button type="submit"
						class="form_submit am-btn am-btn-block am-btn-warning">提交</button>
				</form>
			</div>
		</div>
		<div nc-tips width="120px"></div>
	</div>
	<jsp:include page="../include/footer.jsp" /><script>
		var r = "${r}";
		$(document).ready(function() {
			var form = $("#invoice_form");
			UTIL.ajax.form(form, null, function(json, formSubmit) {
				if (json.success) {
					alert("保存成功");
					if (r != "") {
						location.href = r;
					}
					else {
						location.href = "${env.host}/m/personal/invoice/";
					}
				}
				else {
					alert("保存失败," + json.message);
				}
			});
		});
	</script>
</body>
</html>