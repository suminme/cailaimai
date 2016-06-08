<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">运费模板</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">模板维护</header>
		<div class="panel-body">
			<form id="freight_form" action="${env.host}/admin/freight/update/"
				method="post" class="form-horizontal">
				<input type="hidden" name="id" value="${data.id}" />
				<div class="form-group">
					<label class="col-sm-2 control-label">名称<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<p class="form-control-static">${data.name}</p>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">发货时间<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<select name="freightDelivery" class="form-control"
							value="${data.freightDelivery}" required>
							<option value="">===请选择===</option>
							<option value="4小时">4小时</option>
							<option value="8小时">8小时</option>
							<option value="24小时">24小时</option>
							<option value="48小时">48小时</option>
							<option value="根据生产安排">根据生产安排</option>
						</select>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">运费<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="number" name="freight" class="form-control"
							value="${data.freight}" required />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">包邮金额<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="number" name="freightFree" class="form-control"
							value="${data.freightFree}" required /> <span
							class="help-block m-b-none">当订单金额大于该金额时包邮，不包邮请输入0</span>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">运费描述<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="freightAbout" class="form-control"
							value="${data.freightAbout}" required /> <span
							class="help-block m-b-none">当描述中涉及包邮金额时请使用{free},涉及运费时请使用{freight}</span>
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
		ADMIN.freight.form();
	});
</script>