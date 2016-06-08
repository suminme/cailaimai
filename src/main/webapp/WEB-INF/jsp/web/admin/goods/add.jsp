<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">商品维护</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">添加商品</header>
		<div class="panel-body">
			<form id="goods_add_form" action="${env.host}/admin/goods/add/"
				method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">商品大类<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<select name="typeId" class="form-control" required>
							<option value="">===请选择===</option>
							<c:forEach var="mall" items="${mallList}">
								<optgroup label="${mall.name}">
									<c:forEach var="type" items="${typeMap[mall.id]}">
										<option value="${type.id}">${type.name}</option>
									</c:forEach>
								</optgroup>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-2">
						<button type="submit" class="btn btn-primary">添加</button>
					</div>
				</div>
			</form>
		</div>
	</section>
</section>
<script>
	$(document).ready(function() {
		ADMIN.goods.add();
	});
</script>