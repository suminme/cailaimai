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
								<div class="panel-heading">开票信息（<a href="javascript:;" onclick="addNewInvoice()">新增</a>）</div>
								<div class="panel-body" style="border-top: 1px #eee solid;">
									<div class="table-responsive">
										<c:if test="${datas eq null}">
											<div class="center">无数据...</div>
										</c:if>
										<c:if test="${datas ne null}">
											<table id="invoice_list" class="table table-striped m-b-none">
												<thead>
													<tr>
														<th>公司名称</th>
														<th>税号</th>
														<th>开户银行</th>
														<th>银行账号</th>
														<th style="width: 100px;" class="center">操作</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="data" items="${datas}">
														<tr>
															<td>${data.company}</td>
															<td>${data.tax}</td>
															<td>${data.bank}</td>
															<td>${data.account}</td>
															<td class="center"><a
																href="javascript:;"
																onclick="VIP.user.invoiceDelete('${data.id}');"
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
		VIP.user.invoiceList();
		$("#user_invoice").parent().addClass("curr");
	});
	function addNewInvoice() {
		dialog = art.dialog.load('${env.host}/mall/order/invoice/add/', {
			title : '新增开票信息',
			lock : true
		}, false);
	}
	function addInvoice(data) {
		dialog.close();
		location.reload();
	}
</script>