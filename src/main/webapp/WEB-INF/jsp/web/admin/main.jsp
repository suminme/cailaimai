<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html class="app">
<head>
<meta charset="utf-8" />
<title>${config.site.title}、${config.site.subtitle}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link rel="shortcut icon" href="${env.staticHost}/favicon.ico" />
<link rel="stylesheet" href="${env.staticHost}/admin/css/app.v1.css"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${env.staticHost}/ec/vendor/iosOverlay/iosOverlay.css" />
<link type="text/css" rel="stylesheet"
	href="${env.staticHost}/ec/vendor/select2/select2.min.css" />
<link rel="stylesheet" type="text/css"
	href="${env.staticHost}/ec/vendor/editable/css/bootstrap-editable.css" />
<link rel="stylesheet" type="text/css"
	href="${env.staticHost}/admin/vendor/dataTables/datatables.css" />
<link type="text/css" rel="stylesheet"
	href="${env.staticHost}/admin/css/custom.css" />
<!--[if lt IE 9]> 
<script src="${env.staticHost}/admin/vendor/ie/html5shiv.js"></script>
<script src="${env.staticHost}/admin/vendor/ie/respond.min.js"></script>
<script src="${env.staticHost}/admin/vendor/ie/excanvas.js"></script>
<![endif]-->
</head>
<body class="console">
	<section class="vbox">
		<header
			class="bg-white header header-md navbar navbar-fixed-top-xs box-shadow">
			<div class="navbar-header aside-md dk">
				<a class="btn btn-link visible-xs"
					data-toggle="class:nav-off-screen,open" data-target="#nav,html">
					<i class="fa fa-bars"></i>
				</a> <a href="${env.host}/" class="navbar-brand"><img
					src="${env.staticHost}/admin/images/l.png" class="m-r-sm"> <span
					class="hidden-nav-xs">材来买</span> </a> <a
					class="btn btn-link visible-xs" data-toggle="dropdown"
					data-target=".user"> <i class="fa fa-cog"></i>
				</a>
			</div>
			<ul class="nav navbar-nav navbar-right m-n hidden-xs nav-user user">
				<li class="hidden-xs"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <i class="i i-chat3"></i> <span
						class="badge badge-sm up bg-danger count umc">0</span>
				</a>
					<section class="dropdown-menu aside-xl animated flipInY">
						<section class="panel bg-white">
							<div class="panel-heading b-light bg-light">
								<strong>您当前有<span class="umc"></span>条未读消息
								</strong>
							</div>
							<div class="list-group list-group-alt umct"></div>
							<div class="panel-footer text-sm center">
								<a href="#/admin/message/list/">查看所有消息</a>
							</div>
						</section>
					</section></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <span class="thumb-sm avatar pull-left">
							<img src="${env.staticHost}/admin/images/default_avatar.jpg">
					</span>${signinUser.name}
				</a>
					<ul class="dropdown-menu animated fadeInRight">
						<li><a href="${env.host}/vip/">个人中心</a></li>
						<li class="divider"></li>
						<li><a href="${env.host}/signout/">退出</a></li>
					</ul></li>
			</ul>
		</header>
		<section>
			<section class="hbox stretch">
				<aside class="bg-black aside-md hidden-print hidden-xs" id="nav">
					<section class="vbox">
						<section class="w-f scrollable">
							<div class="slim-scroll" data-height="auto"
								data-disable-fade-out="true" data-distance="0" data-size="10px"
								data-railOpacity="0.2">
								<div class="clearfix wrapper nav-user hidden-xs">
									<div class="dropdown">
										<span class="thumb avatar pull-left m-r"> <img
											src="${env.staticHost}/admin/images/default_avatar.jpg"
											class="dker">
										</span> <span class="hidden-nav-xs clear"> <span
											class="block m-t-xs"> <strong
												class="font-bold text-lt">${signinUser.name}</strong>
										</span> <span class="text-muted text-xs block">管理员</span>
										</span>
									</div>
								</div>
								<!-- nav -->
								<nav class="nav-primary hidden-xs">
									<ul class="nav nav-main" data-ride="collapse">
										<li class="active"><a href="#/admin/dashboard/"> <i
												class="i i-home icon"></i><span class="font-bold">控制台</span>
										</a></li>
										<div
											class="text-muted text-sm hidden-nav-xs padder m-t-sm m-b-sm">订单中心</div>
										<li><a href="javascript:;"> <span
												class="pull-right text-muted"> <i
													class="i i-circle-sm-o text"></i> <i
													class="i i-circle-sm text-active"></i>
											</span> <i class="i i-clip icon"> </i> <span class="font-bold">订单管理</span>
										</a>
											<ul class="nav dk">
												<li><a href="#/admin/order/list/?status=1"> <i
														class="i i-dot"></i><span><span status_code="1"></span>订单</span>
												</a></li>
												<li><a href="#/admin/order/list/?status=2"> <i
														class="i i-dot"></i> <span><span status_code="2"></span>订单</span>
												</a></li>
												<li><a href="#/admin/order/list/?status=3"> <i
														class="i i-dot"></i> <span><span status_code="3"></span>订单</span>
												</a></li>
												<li><a href="#/admin/order/list/?status=4"> <i
														class="i i-dot"></i> <span><span status_code="4"></span>订单</span>
												</a></li>
												<li><a href="#/admin/order/list/?status=-1"> <i
														class="i i-dot"></i> <span><span status_code="-1"></span>订单</span>
												</a></li>
											</ul></li>
										<div
											class="text-muted text-sm hidden-nav-xs padder m-t-sm m-b-sm">商品中心</div>
										<li><a href="javascript:;"> <span
												class="pull-right text-muted"> <i
													class="i i-circle-sm-o text"></i> <i
													class="i i-circle-sm text-active"></i>
											</span> <i class="i i-stack2 icon"> </i> <span class="font-bold">商品管理</span>
										</a>
											<ul class="nav dk">
												<li><a href="#/admin/goods/list/"> <i
														class="i i-dot"></i> <span>商品列表</span>
												</a></li>
												<li><a href="#/admin/goods/add/"> <i
														class="i i-dot"></i> <span>添加商品</span>
												</a></li>
											</ul></li>
										<li><a href="javascript:;"> <span
												class="pull-right text-muted"> <i
													class="i i-circle-sm-o text"></i> <i
													class="i i-circle-sm text-active"></i>
											</span> <i class="i i-bulb icon"> </i> <span class="font-bold">库存管理</span>
										</a>
											<ul class="nav dk">
												<li><a href="#/admin/stock/list/"> <i
														class="i i-dot"></i> <span>库存明细</span>
												</a></li>
												<li><a href="#/admin/stock/maintenance/"> <i
														class="i i-dot"></i> <span>库存维护</span>
												</a></li>
											</ul></li>
										<li><a href="javascript:;"> <span
												class="pull-right text-muted"> <i
													class="i i-circle-sm-o text"></i> <i
													class="i i-circle-sm text-active"></i>
											</span> <i class="i i-data2 icon"> </i> <span class="font-bold">样品管理</span>
										</a>
											<ul class="nav dk">
												<li><a href="#/admin/goods/sample/list/?status=0">
														<i class="i i-dot"></i> <span>申请中</span>
												</a></li>
												<li><a href="#/admin/goods/sample/list/?status=1">
														<i class="i i-dot"></i> <span>已处理</span>
												</a></li>
												<li><a href="#/admin/goods/sample/list/?status=-1">
														<i class="i i-dot"></i> <span>已拒绝</span>
												</a></li>
											</ul></li>
										<li><a href="javascript:;"> <span
												class="pull-right text-muted"> <i
													class="i i-circle-sm-o text"></i> <i
													class="i i-circle-sm text-active"></i>
											</span> <i class="i i-book icon"> </i> <span class="font-bold">运费模板</span>
										</a>
											<ul class="nav dk">
												<li><a href="#/admin/freight/list/"> <i
														class="i i-dot"></i> <span>模板列表</span>
												</a></li>
											</ul></li>
										<div
											class="text-muted text-sm hidden-nav-xs padder m-t-sm m-b-sm">消息中心</div>
										<li><a href="javascript:;"> <span
												class="pull-right text-muted"> <i
													class="i i-circle-sm-o text"></i> <i
													class="i i-circle-sm text-active"></i>
											</span> <i class="i i-mail2 icon"> </i> <span class="font-bold">消息管理</span>
										</a>
											<ul class="nav dk">
												<li><a href="#/admin/message/list/"><b
														class="badge bg-danger lt pull-right umc"></b><i
														class="i i-dot"></i> <span>我的消息</span> </a></li>
												<li><a href="#/admin/message/send/"> <i
														class="i i-dot"></i> <span>发送消息</span>
												</a></li>
											</ul></li>
										<div
											class="text-muted text-sm hidden-nav-xs padder m-t-sm m-b-sm">用户中心</div>
										<li><a href="javascript:;"> <span
												class="pull-right text-muted"> <i
													class="i i-circle-sm-o text"></i> <i
													class="i i-circle-sm text-active"></i>
											</span> <i class="i i-user3 icon"> </i> <span class="font-bold">用户管理</span>
										</a>
											<ul class="nav dk">
												<li><a href="#/admin/user/list/"> <i
														class="i i-dot"></i> <span>用户列表</span>
												</a></li>
											</ul></li>
										<div
											class="text-muted text-sm hidden-nav-xs padder m-t-sm m-b-sm">系统管理</div>
										<li><a href="#/admin/system/config/"> <i
												class="i i-circle-sm-o text-success-lt"></i> <span>网站设置</span>
										</a></li>
									</ul>
								</nav>
							</div>
						</section>
					</section>
				</aside>
				<section id="content">
					<section class="vbox" id="hash_load"></section>
				</section>
			</section>
		</section>
	</section>
	<div id="jplayer" style="display: none;"></div>
	<!-- App -->
	<script src="${env.staticHost}/admin/js/app.v1.js"></script>
	<script src="${env.staticHost}/ec/vendor/md5/md5.js"></script>
	<script
		src="${env.staticHost}/ec/vendor/noty/jquery.noty.packaged.min.js"></script>
	<script src="${env.staticHost}/ec/vendor/iosOverlay/iosOverlay.js"></script>
	<script src="${env.staticHost}/ec/vendor/dropzone/jquery.form.min.js"></script>
	<script src="${env.staticHost}/ec/vendor/ckeditor/ckeditor.js"></script>
	<script src="${env.staticHost}/ec/vendor/ckeditor/adapters/jquery.js"></script>
	<script src="${env.staticHost}/ec/vendor/select2/select2.min.js"></script>
	<script
		src="${env.staticHost}/ec/vendor/editable/js/bootstrap-editable.js"></script>
	<script
		src="${env.staticHost}/admin/vendor/dataTables/jquery.dataTables.min.js"></script>
	<script
		src="${env.staticHost}/admin/vendor/jplayer/jquery.jplayer.min.js"></script>
	<script src="${env.staticHost}/ec/js/util.js"></script>
	<script src="${env.staticHost}/ec/js/admin.js"></script>
	<script>
		$(document).ready(function() {
			UTIL.init({
				host : "${env.host}",
				staticHost : "${env.staticHost}",
				storageHost : "${env.storageHost}"
			});
			ADMIN.init("/admin/dashboard/",${config.order.status});
		});
		$("#jplayer").jPlayer({
			swfPath : "${env.staticHost}/admin/vendor/jplayer/",
			ready : function() {
				$(this).jPlayer("setMedia", {
					mp3 : "${env.staticHost}/admin/vendor/jplayer/6307.mp3"
				});
			},
			supplied : "mp3"
		});
	</script>
</body>
</html>