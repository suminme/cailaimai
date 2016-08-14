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
				收货地址</div>
			<div class="am-header-nav am-header-right">
				<a href="${env.host}/m/"><i class="am-icon-home am-icon-sm"></i></a>
			</div>
		</header>
		<ul class="am-list am-margin-top-sm">
			<c:if test="${datas eq null}">
				<li class="border-no am-text-center am-padding-xl c2">没有收货地址</li>
			</c:if>
			<c:if test="${datas ne null}">
				<c:forEach var="data" items="${datas}">
					<li class="am-padding"><span> <a class="link am-inline"
							href="${env.host}/m/personal/address/add/?id=${data.id}">${data.name}(${data.phone})</a><span
							class="am-padding-left-sm"></span><a
							class=" am-fr link am-inline" href="javascript:;"
							onclick="delAddress('${data.id}')">删除</a>
					</span>
						<div class="c2 am-padding-top-sm am-text-sm">
							<i class="am-icon-location-arrow"></i>
							${data.province}${data.city}${data.area}${data.detail}
						</div></li>
				</c:forEach>
			</c:if>
			<li class="am-padding am-text-center"><span><a
					class="link am-inline" href="${env.host}/m/personal/address/add/">新增</a>
			</span></li>
			<li></li>
		</ul>
		<div nc-tips width="120px"></div>
	</div>
	<jsp:include page="../include/footer.jsp" />
	<script>
		function delAddress(id) {
			if (!confirm("确认删除吗?")) {
				return;
			}
			var url = UTIL.config.host + "/m/personal/address/delete/";
			UTIL.ajax.post(url, {
				id : id
			}, function(json) {
				if (json.success) {
					location.reload();
				}
				else {
					alert("删除失败");
				}
			});
		}
	</script>
</body>
</html>