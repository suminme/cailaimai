<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body class="common-home page-common-home layout-fullwidth">
	<div class="row-offcanvas row-offcanvas-left">
		<div id="page">
			<jsp:include page="../include/navbar.jsp" />
			<div class="main-columns container space-20">
				<div class="row" style="margin-top: 10px !important;">
					<jsp:include page="../include/menu.jsp" />
					<div id="sidebar-main" style="width:1050px;float:left;">
						<div id="content" class="space-padding-0">
							<div class="clearfix"></div>
							<div class="myalllist">
								<div class="panel panel-default">
									<div class="panel-heading">
										<ul class="nav navbar-nav">
											<li role="presentation"><a status=""
												href="${env.host}/vip/order/list/">全部订单</a></li>
											<li role="presentation"><a status="1"
												href="${env.host}/vip/order/list/?status=1">待付款</a></li>
											<li role="presentation"><a status="2"
												href="${env.host}/vip/order/list/?status=2">已付款</a></li>
											<li role="presentation"><a status="3"
												href="${env.host}/vip/order/list/?status=3">已出库</a></li>
											<li role="presentation"><a status="4"
												href="${env.host}/vip/order/list/?status=4">已完成</a></li>
											<li role="presentation"><a status="-1"
												href="${env.host}/vip/order/list/?status=-1">已取消</a></li>
										</ul>
										<div class="clearfix"></div>
									</div>
								</div>
								<div style="margin-top:10px;" class="panel panel-default">
									<div class="panel-heading">
										<table style="width:100%;">
											<tr>
												<th>
													公司名称
												</th>
												<td>
													${config.company.name}
												</td>
												<th>
													对公账户
												</th>
												<td>
													${config.company.account}
												</td>
												<th>
													开户行
												</th>
												<td>
													${config.company.bank}
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<div>
								<c:forEach var="order" items="${orderList}">
									<div class="o-detail">
										<div class="o-top">
											<div class="o-top-c">
												订单编号：<a style="color: #666"
													href="${env.host}/vip/order/detail/?code=${order.code}">${order.code}</a>
												<div class="right">下单日期：${fn:substring(order.createTime,0,16)}</div>
											</div>
										</div>
										<div class="o-content">
											<div class="o-content-c">
												<table class="o-content-t">
													<tr>
														<td class="bd sg"><c:forEach var="orderGoods"
																items="${order.orderGoodsList}" varStatus="status">
																<div class="ginfo">
																	<div class="gimg">
																		<a target="_blank"
																			href="${env.host}/mall/goods/${orderGoods.goodsId}.html"><img
																			src="${env.storageHost}/${orderGoods.goodsImagePath}" /></a>
																	</div>
																	<div>
																		<a target="_blank"
																			href="${env.host}/mall/goods/${orderGoods.goodsId}.html">${orderGoods.goodsName}</a>
																	</div>
																	<div>x${orderGoods.amount}</div>
																	<div style="clear: both;"></div>
																</div>
																<c:if test="${status.last ne true}">
																	<div class="l"></div>
																</c:if>
															</c:forEach></td>
														<td class="bd sm">${order.mallName}</td>
														<td class="bd sm">¥${order.total}<br /> 对公打款
														</td>
														<td class="bd sm"><c:if test="${order.status eq 1}">
																待付款
															</c:if> <c:if test="${order.status eq 2}">
																已付款
															</c:if> <c:if test="${order.status eq 3}">
																已出库
															</c:if> <c:if test="${order.status eq 4}">
																已完成
															</c:if> <c:if test="${order.status eq -1}">
																已取消
															</c:if></td>
														<td class="sm"><a
															href="${env.host}/vip/order/detail/?code=${order.code}">订单详情</a><br />
															<a target="_blank"
															href="${env.host}/vip/order/print/?code=${order.code}">打印</a></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../include/footbar.jsp" />
	</div>
</body>
<jsp:include page="../include/footer.jsp" />
<script>
	$(document).ready(function() {
		$("#menu_order").parent().addClass("curr");
		$("[status='${status}']").parent().addClass("active");
	});
</script>