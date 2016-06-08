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
					<div class="signin" style="background-color:#F8E300;background-position: top center;background-repeat:no-repeat;background-image: url('${env.staticHost}/mall/images/slider/lb01.jpg');">
						<div class="container">
							<div class="box-warp">
								<div class="box">
									<div class="form">
										<div class="title">账户登录</div>
										<form id="signin_form" method="post"
											action="${env.host}/signin/">
											<div class="field">
												<label for="username"> <i class="fa fa-user"></i>
												</label> <input id="username" type="text" name="username"
													class="login-text" value="" placeholder="手机号码"
													autocomplete="off" required focus />
											</div>
											<div class="field">
												<label for="password"> <i class="fa fa-lock"></i>
												</label> <input id="password" type="password" name="password"
													class="login-text" value="" placeholder="密码"
													autocomplete="off" required />
											</div>
											<div class="error"></div>
											<div class="links">
												<a href="${env.host}/forget/" class="forget-pwd">忘记密码</a> <a
													href="${env.host}/signup/" class="right">免费注册</a>
											</div>
											<div class="submit">
												<button class="form_submit" type="submit">登 录</button>
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
		MALL.signin.init("${r}");
	});
</script>