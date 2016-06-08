<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<!--[if IE]><![endif]-->
<!--[if IE 8 ]><html dir="ltr" lang="en" class="ie8"><![endif]-->
<!--[if IE 9 ]><html dir="ltr" lang="en" class="ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html dir="ltr" class="ltr" lang="zh-CN">
<!--<![endif]-->
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${config.site.title}-${config.site.subtitle}</title>
<meta name="keywords" content="${config.site.title}" />
<meta name="description" content="${config.site.description}" />
<link rel="shortcut icon" href="${env.staticHost}/favicon.ico" />
<link rel="stylesheet" href="${env.staticHost}/mall/css/bootstrap.css" />
<link rel="stylesheet"
	href="${env.staticHost}/mall/css/skins/orange.css" />
<link rel="stylesheet" href="${env.staticHost}/mall/css/typo/typo.css" />
<link rel="stylesheet" href="${env.staticHost}/mall/css/cart/cart.css" />
<link rel="stylesheet" href="${env.staticHost}/mall/css/custom.css" />
<link rel="stylesheet"
	href="${env.staticHost}/mall/fonts/fontawesome/font-awesome.min.css" />
<link rel="stylesheet"
	href="${env.staticHost}/mall/fonts/materialdesigniconic/material-design-iconic-font.min.css" />
<c:if test="${fn:contains(env.host,'www.cailaimai.com')}">
	<script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "//hm.baidu.com/hm.js?27b9147f19726e3d15253056b4aa69f7";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
	</script>
</c:if>
</head>