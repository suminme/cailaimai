<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body class="product-category-18 page-category layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="../include/navbar.jsp" />
			<div class="container">
				<div class="breadcrumbs">
					<ul class="list-unstyled breadcrumb-links">
						<li><a href="${env.host}/">首页</a></li>
						<li>FAQ</li>
						<li>客户服务</li>
					</ul>
				</div>
			</div>
			<div class="main-columns container space-20">
				<div class="row">
					<aside id="sidebar-left" class="col-md-3">
						<column id="column-left" class="hidden-xs sidebar">
						<div class="panel panel-v3 panel-default bestseller">
							<div class="panel-heading">
								<h4 class="panel-title">客户服务</h4>
							</div>
							<div class="products-block">
								<div class="row products-row tree-menu">
									<ul class="accordion" style="padding: 0 0 0 40px;">
										<li class="accordion-group"><a href="#a1">联系我们</a></li>
										<li class="accordion-group"><a href="#a2">投诉建议</a></li>
										<li class="accordion-group"><a href="#a3">商务合作</a></li>
										<li class="accordion-group"><a href="#a4">招聘信息</a></li>
									</ul>
								</div>
							</div>
						</div>
						</column>
					</aside>
					<div id="sidebar-main" class="col-md-9">
						<div id="content">
							<div class="clearfix"></div>
							<div class="refine-search panel panel-default panel-v3 space-20">
								<div class="panel-heading">
									<h4 class="panel-title">客户服务</h4>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-sm-12">
											<h2 id="a1">一、联系我们</h2>

											<p>（百度地图）</p>

											<p>
												材来买上海体验中心<br /> 上海市浦东新区林恒路174号
											</p>

											<p>
												<br /> （微信公众号二维码、微信二维码、QQ即时通讯）<br />
												咨询热线：400-921-9928；021-61317921<br /> 在线咨询QQ：
												362933227；微信：cailaimai_com
											</p>

											<h2 id="a2">二、投诉建议</h2>

											<p>站内信 发布框</p>

											<h2 id="a3">三、商务合作</h2>

											<p>
												（微信公众号二维码、微信二维码、QQ即时通讯）<br />
												咨询热线：400-921-9928；021-61317921<br /> 在线咨询QQ：
												362933227；微信：cailaimai_com<br /> 邮箱：cai@cailaimai.com
											</p>

											<h2 id="a4">四、招聘信息</h2>

											<p>暂无</p>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/footbar.jsp" />
</body>
<jsp:include page="../include/footer.jsp" />