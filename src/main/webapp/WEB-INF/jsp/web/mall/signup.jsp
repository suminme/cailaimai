<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="include/header.jsp" />
<body class="common-home page-common-home layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="include/navbar.jsp" />
			<div class="main-columns container-full">
				<div class="row">
					<div class="signup"
						style="background-color:#F8E300;background-position: top center;background-repeat:no-repeat;background-image: url('${env.staticHost}/mall/images/slider/lb01.jpg');">
						<div class="container">
							<div class="box-warp">
								<div class="box">
									<div class="form">
										<div class="title">账户注册</div>
										<form id="signup_form" method="post"
											action="${env.host}/signup/">
											<!-- 
											<div class="field">
												<label for="name"> <i class="fa fa-user"></i>
												</label> <input id="name" type="text" name="name" class="login-text"
													value="" placeholder="昵称" required focus />
											</div>
											 -->
											<div class="field">
												<label for="mobile"> <i class="fa fa-user"></i>
												</label> <input id="mobile" type="text" name="mobile"
													class="login-text" value="" placeholder="手机号码"
													autocomplete="off" required focus />
											</div>
											<div class="field">
												<label for="password"> <i class="fa fa-lock"></i>
												</label> <input id="password" type="password" name="password"
													class="login-text" value="" placeholder="密码"
													autocomplete="off" required />
											</div>
											<div class="field" style="position: relative;">
												<label for="captcha"> <i class="fa fa-bolt"></i>
												</label> <input id="captcha" type="text" name="captcha"
													class="login-text" value="" placeholder="验证码"
													autocomplete="off" required />
												<div class="opt" style="position: absolute;top: 11px;right: 10px;">
													<a id="captcha_button" href="javascript:;">获取验证码</a>
												</div>
											</div>
											<div class="error"></div>
											<div class="links">
												<label><input name="protocal" type="checkbox"
													style="vertical-align: bottom;">同意${config.site.title}<a
													href="${env.host}/faq/about.html#a2" target="_blank">用户协议</a></label>
											</div>
											<div class="submit">
												<button class="form_submit" type="submit">注 册</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="include/footbar.jsp" />
	</div>
</body>
<jsp:include page="include/footer.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		MALL.signup.init();
	});
</script>