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
						<li>手机商城</li>
					</ul>
				</div>
			</div>
			<div class="main-columns container space-20">
				<div class="row">
					<div id="sidebar-main" class="col-md-12">
						<div id="content">
							<div class="clearfix"></div>
							<div class="refine-search panel panel-default panel-v3 space-20">
								
								<div class="panel-body">
									<div class="row">
										<div class="col-sm-12">
											<img src="${env.staticHost}/mall/images/mobile01.jpg" style="width:100%;height:auto;"/>
											<img src="${env.staticHost}/mall/images/mobile02.jpg" style="width:100%;height:auto;"/>
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