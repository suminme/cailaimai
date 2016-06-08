<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">系统管理</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">网站设置</header>
		<div class="panel-body">
			<form id="message_form" action="${env.host}/admin/system/config/"
				method="post" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">网站名称<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.site.title}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">网站标题<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.site.subtitle}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备案号<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.site.icp}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">网站简介<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<textarea name="content" class="form-control">${config.site.description}</textarea>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">联系电话<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.company.telphone}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">联系邮箱<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.company.email}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">公司名称<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.company.name}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">公司地址<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.company.address}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">税号<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.company.tax}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">开户行<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.company.bank}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">银行账号<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="content" class="form-control"
							value="${config.company.account}" />
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
		ADMIN.message.send();
	});
</script>