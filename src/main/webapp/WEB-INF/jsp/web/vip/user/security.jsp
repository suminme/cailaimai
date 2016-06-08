<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body class="common-home page-common-home layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="../include/navbar.jsp" />
			<div class="main-columns container space-20">
				<div class="row" style="margin-top: 10px !important;">
					<jsp:include page="../include/menu.jsp" />
					<div id="sidebar-main" style="width: 1050px; float: left;">
						<div id="content" class="space-padding-0">
							<div class="clearfix"></div>
							<div style="margin-top: 10px;" class="panel panel-default">
								<div class="panel-heading">账号安全</div>
								<div class="panel-body" style="border-top: 1px #eee solid;">
									<form id="user_security_form"
										action="${env.host}/vip/user/security/" method="post"
										class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-2 control-label">原密码<font
												class="required">*</font></label>
											<div class="col-sm-10">
												<input type="password" name="oldPassword"
													class="form-control" value="" autocomplete="off" focus
													required />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">新密码<font
												class="required">*</font></label>
											<div class="col-sm-10">
												<input type="password" name="password" class="form-control"
													value="" autocomplete="off" required />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">重复密码<font
												class="required">*</font></label>
											<div class="col-sm-10">
												<input type="password" name="password2" class="form-control"
													value="" autocomplete="off" required />
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4 col-sm-offset-2">
												<button type="submit" class="btn btn-primary form_submit">提交</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../include/footbar.jsp" />
	</div>
</body>
<jsp:include page="../include/footer.jsp" />
<script>
	$(document).ready(function() {
		VIP.user.security();
		$("#user_security").parent().addClass("curr");
	});
</script>