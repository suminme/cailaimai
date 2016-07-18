<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="include/header.jsp" />
<body>
	<div class="nc-main">
		<header data-am-widget="header" class="am-header bgc5">
			<div class="am-header-left am-header-nav">
				<a href="javascript:;"> <span class="am-icon-user"></span>
				</a>
			</div>
			<h1 class="am-header-title am-header-title-txt">
				<a href="javascript:;">登陆</a>
			</h1>
		</header>
		<div class="am-g">
			<div class="am-u-md-12 am-u-sm-centered">
				<form id="signin_form" method="post" action="${env.host}/signin/">
					<br />
					<div class="am-form-group am-form-group-lg am-form-icon">
						<i class="am-icon-user c10"></i> <input id="username" type="text"
							name="username" class="am-form-field" value="" placeholder="手机号码"
							autocomplete="off" required focus />
					</div>
					<div class="am-form-group am-form-group-lg am-form-icon"
						style="margin-top: -10px">
						<i class="am-icon-key c10"></i> <input id="password"
							type="password" name="password" class="am-form-field" value=""
							placeholder="密码" autocomplete="off" required />
					</div>
					<button type="submit"
						class="form_submit am-btn am-btn-block am-btn-warning">登录</button>
					<div class="am-form-group am-form-group-lg">
						<a class="am-fl am-margin-top-lg link"
							href="${env.host}/m/forget/">忘记密码？</a> <a
							class="am-fr am-margin-top-lg c4" href="${env.host}/m/signup/">免费注册</a>
					</div>
				</form>
			</div>
		</div>
		<jsp:include page="include/footbar.jsp" />
	</div>
	<jsp:include page="include/footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			MOBILE.init.tabbar(3);
			MOBILE.signin.init("${r}");
		});
	</script>
</body>
</html>