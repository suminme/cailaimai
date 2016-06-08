<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript"
	src="${env.staticHost}/mall/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="${env.staticHost}/mall/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${env.staticHost}/mall/js/jquery.themepunch.plugins.min.js"></script>
<script type="text/javascript"
	src="${env.staticHost}/mall/js/jquery.themepunch.revolution.min.js"></script>
<script type="text/javascript"
	src="${env.staticHost}/ec/vendor/art/jquery.artDialog.js?skin=default"></script>
<script type="text/javascript"
	src="${env.staticHost}/ec/vendor/art/iframeTools.source.js"></script>
<script src="${env.staticHost}/ec/vendor/md5/md5.js"></script>
<script type="text/javascript" src="${env.staticHost}/ec/js/util.js"></script>
<script type="text/javascript" src="${env.staticHost}/ec/js/mall.js"></script>
<script type="text/javascript" src="${env.staticHost}/mall/js/custom.js"></script>
<script>
	UTIL.init({
		host : "${env.host}"
	});
	MALL.init("${signinUser.id}");
</script>
</html>