<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">库存管理</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">库存维护</header>
		<div class="panel-body">
			<form id="stock_form" action="${env.host}/admin/stock/maintenance/"
				method="post" class="form-horizontal">
				<input type="hidden" name="num" value="" />
				<div class="form-group">
					<label class="col-sm-2 control-label">商品<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<select name="goodsCode" class="form-control select2" required>
							<option value="">===请选择===</option>
							<c:forEach var="goods" items="${goodsList}">
								<option value="${goods.code}">${goods.code},${goods.stock},${goods.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">操作<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<select name="operate" class="form-control" required>
							<option value="+">增加</option>
							<option value="-">减少</option>
						</select>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">数量<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="number" name="number" class="form-control" value=""
							required />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">供应商</label>
					<div class="col-sm-10">
						<input type="text" name="supplier" class="form-control" value="" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">说明</label>
					<div class="col-sm-10">
						<textarea name="description" class="form-control"></textarea>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-2">
						<button type="submit" class="btn btn-primary form_submit">提交</button>
					</div>
				</div>
			</form>
		</div>
	</section>
</section>
<script>
	$(document).ready(function() {
		ADMIN.stock.maintenance();
	});
</script>