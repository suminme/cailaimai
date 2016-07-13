<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<title>${config.site.title}-${config.site.subtitle}</title>
<meta name="keywords" content="${config.site.title}" />
<meta name="description" content="${config.site.description}" />
<link rel="shortcut icon" href="${env.staticHost}/favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<link rel="stylesheet"
	href="${env.staticHost}/mobile/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${env.staticHost}/mobile/css/app.min.css" />
<link rel="stylesheet"
	href="${env.staticHost}/mobile/css/custom.css" />
</head>