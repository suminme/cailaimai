<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">商品管理</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading"> 商品列表 </header>
		<div class="table-responsive">
			<table id="goods_list" class="table table-striped m-b-none"
				data-ride="datatables">
				<thead>
					<tr>
						<th style="width: 110px;" class="center">商品编号</th>
						<th>名称</th>
						<th style="width: 90px;" class="center">库存</th>
						<th style="width: 100px;" class="center">单价</th>
						<th style="width: 90px;" class="center">单位</th>
						<th style="width: 90px;" class="center">上架</th>
						<th style="width: 90px;" class="center">样品</th>
						<th style="width: 90px;" class="center">推荐</th>
						<th style="width: 120px;" class="center">创建时间</th>
						<th style="width: 90px;" class="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${goodsList ne null}">
						<c:forEach var="goods" items="${goodsList}">
							<tr>
								<td class="center"><a target="_blank"
									href="${env.host}/mall/goods/${goods.id}.html">${goods.code}</a></td>
								<td>${goods.name}</td>
								<td class="center"><a
									href="#/admin/stock/list/?goodsCode=${goods.code}">${goods.stock}</a></td>
								<td class="center">${goods.price}</td>
								<td class="center">${goods.unit}</td>
								<td class="center"><span bit="${goods.onsale}"></span></td>
								<td class="center"><span bit="${goods.sample}"></span></td>
								<td class="center"><span bit="${goods.suggest}"></span></td>
								<td class="center" title="${goods.createTime}">${fn:substring(goods.createTime,0,11)}</td>
								<td><a href="#/admin/goods/update/?code=${goods.code}"
									title="更新"><i class="fa fa-pencil text-danger"></i></a>&nbsp;&nbsp;&nbsp;<a
									href="javascript:;"
									onclick="ADMIN.goods.del(this,'${goods.id}');" title="删除"><i
										class="fa fa-trash-o text-danger"></i></a></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</section>
</section>
<script>
	$(document).ready(function() {
		ADMIN.goods.list();
	});
</script>