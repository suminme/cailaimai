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
								<div class="panel-heading">个人信息</div>
								<div class="panel-body" style="border-top:1px #eee solid;">
									<form id="user_profile_form"
										action="${env.host}/vip/user/profile/" method="post"
										class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-2 control-label">手机<font
												class="required">*</font></label>
											<div class="col-sm-10">
												<p class="form-control-static">${signinUser.mobile}</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">昵称<font
												class="required">*</font></label>
											<div class="col-sm-10">
												<input type="text" name="name" class="form-control"
													value="${signinUser.name}" autocomplete="off" required />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">邮箱</label>
											<div class="col-sm-10">
												<input type="email" name="email" class="form-control"
													value="${signinUser.email}" autocomplete="off" /> <span
													class="help-block m-b-none">用于接收网站的相关通知及优惠信息</span>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">注册日期</label>
											<div class="col-sm-10">
												<p class="form-control-static">${fn:substring(signinUser.createTime,0,19)}</p>
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
		VIP.user.profile();
		$("#user_profile").parent().addClass("curr");
	});
</script>