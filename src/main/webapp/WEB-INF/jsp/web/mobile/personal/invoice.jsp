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
				开票信息</div>
			<div class="am-header-nav am-header-right">
				<a href="${env.host}/m/"><i class="am-icon-home am-icon-sm"></i></a>
			</div>
		</header>
		<ul class="am-list am-margin-top-sm">
			<c:if test="${datas eq null}">
				<li class="border-no am-text-center am-padding-xl c2">没有开票信息</li>
			</c:if>
			<c:if test="${datas ne null}">
				<c:forEach var="data" items="${datas}">
					<li class="am-padding"><span> ${data.company}<span
							class="am-padding-left-sm"></span>
					</span>
						<div class="c2 am-padding-top-sm am-text-sm">
							<i class="am-icon-location-arrow"></i>
							${data.tax},${data.bank},${data.account},${data.phone},${data.address}
						</div></li>
				</c:forEach>
			</c:if>
			<li></li>
		</ul>
		<div nc-tips width="120px"></div>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>