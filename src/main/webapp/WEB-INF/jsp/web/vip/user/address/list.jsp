<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../../include/header.jsp" />
<body class="common-home page-common-home layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="../../include/navbar.jsp" />
			<div class="main-columns container space-20">
				<div class="row" style="margin-top: 10px !important;">
					<jsp:include page="../../include/menu.jsp" />
					<div id="sidebar-main" style="width: 1050px; float: left;">
						<div id="content" class="space-padding-0">
							<div class="clearfix"></div>
							<div style="margin-top: 10px;" class="panel panel-default">
								<div class="panel-heading">收货地址（<a href="javascript:;" onclick="addNewAddress()">新增</a>）</div>
								<div class="panel-body" style="border-top: 1px #eee solid;">
									<div class="table-responsive">
										<c:if test="${datas eq null}">
											<div class="center">无数据...</div>
										</c:if>
										<c:if test="${datas ne null}">
											<table id="address_list" class="table table-striped m-b-none">
												<thead>
													<tr>
														<th style="width: 120px;" class="center">联系人</th>
														<th style="width: 110px;" class="center">手机</th>
														<th style="width: 100px;" class="center">省</th>
														<th style="width: 100px;" class="center">市</th>
														<th style="width: 140px;" class="center">区/县</th>
														<th>详细地址</th>
														<th style="width: 60px;" class="center">操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="data" items="${datas}">
														<tr>
															<td class="center">${data.name}</td>
															<td class="center">${data.phone}</td>
															<td class="center">${data.province}</td>
															<td class="center">${data.city}</td>
															<td class="center">${data.area}</td>
															<td title="${data.detail}">${fn:substring(data.detail,0,10)}</td>
															<td class="center"><a href="javascript:;"
																onclick="VIP.user.addressDelete('${data.id}');"
																title="删除"><i class="fa fa-trash-o text-danger"></i></a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../../include/footbar.jsp" />
	</div>
</body>
<jsp:include page="../../include/footer.jsp" />
<script>
	$(document).ready(function() {
		VIP.user.addressList();
		$("#user_address").parent().addClass("curr");
	});
	function addNewAddress() {
		dialog = art.dialog.load('${env.host}/mall/order/address/add/', {
			title : '新增收货地址',
			lock : true
		}, false);
	}
	function addAddress(data) {
		dialog.close();
		location.reload();
	}
</script>