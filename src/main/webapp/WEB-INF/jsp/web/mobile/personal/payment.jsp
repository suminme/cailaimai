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
				付款方式</div>
			<div class="am-header-nav am-header-right">
				<a href="${env.host}/m/"><i class="am-icon-home am-icon-sm"></i></a>
			</div>
		</header>
		<ul class="am-list am-margin-top-sm">
			<li class="am-padding"><span>公司名称</span> </span>
				<div class="c2 am-padding-top-sm am-text-sm">${config.company.name}</div></li>
			<li class="am-padding"><span>公司税号</span> </span>
				<div class="c2 am-padding-top-sm am-text-sm">${config.company.tax}</div></li>
			<li class="am-padding"><span>开户行</span> </span>
				<div class="c2 am-padding-top-sm am-text-sm">${config.company.bank}</div></li>
			<li class="am-padding"><span>对公账号</span> </span>
				<div class="c2 am-padding-top-sm am-text-sm">${config.company.account}</div></li>
			<li></li>
		</ul>
		<div nc-tips width="120px"></div>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>