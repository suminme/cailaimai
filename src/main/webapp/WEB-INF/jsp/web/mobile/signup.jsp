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
				<a href="javascript:;">注册</a>
			</h1>
		</header>
		<div class="am-g">
			<div class="am-u-md-12 am-u-sm-centered">
				<form id="signup_form" method="post" action="${env.host}/signup/">
					<br />
					<div class="am-form-group am-form-group-lg am-form-icon">
						<i class="am-icon-user c10"></i> <input id="mobile" type="text"
							name="mobile" class="am-form-field" value="" placeholder="手机号码"
							autocomplete="off" required focus />
					</div>
					<div class="am-form-horizontal am-g-collapse">
						<div class="am-form-group">
							<div class="am-u-sm-6 am-u-md-6">
								<input id="captcha" type="text" name="captcha"
									class="am-form-field" value="" placeholder="验证码"
									autocomplete="off" required />
							</div>
							<div class="am-u-sm-6 am-u-md-6">
								<button id="captcha_button"
									class="am-btn am-btn-primary am-margin-left f-r"
									style="padding: 1.3rem; width: 90%" type="button">获取验证码</button>
							</div>
						</div>
					</div>
					<div class="am-form-group am-form-group-lg am-form-icon"
						style="margin-top: -10px">
						<i class="am-icon-key c10"></i> <input id="password"
							type="password" name="password" class="am-form-field" value=""
							placeholder="密码" autocomplete="off" required />
					</div>
					<div class="am-form-group">
						<label class="am-checkbox"><input name="protocal"
							type="checkbox">同意${config.site.title}<a class="link"
							href="${env.host}/faq/about.html#a2" target="_blank">用户协议</a> </label>
					</div>
					<button type="submit"
						class="form_submit am-btn am-btn-block am-btn-warning">注册</button>
					<div class="am-form-group am-form-group-lg">
						<a class="am-fl am-margin-top-lg link"
							href="${env.host}/m/signin/">返回登陆</a>
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
			MOBILE.signup.init();
		});
	</script>
</body>
</html>