<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<section class="scrollable padder">
	<div class="m-b-md">
		<h3 class="m-b-none">商品维护</h3>
	</div>
	<section class="panel panel-default">
		<header class="panel-heading">${mall.name}-${type.name}</header>
		<div class="panel-body">
			<form id="goods_submit_form" action="${env.host}/admin/goods/submit/"
				method="post" class="form-horizontal">
				<input type="hidden" name="id" value="${goods.id}" /> <input
					type="hidden" name="code" value="${goods.code}" /> <input
					type="hidden" name="typeId" value="${type.id}" />
				<div class="form-group">
					<label class="col-sm-2 control-label">名称<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="name" class="form-control"
							value="${goods.name}" /> <span class="help-block m-b-none">建议1~20个汉字</span>
					</div>
				</div>
				<c:if test="${imgList ne null}">
					<div class="line line-dashed b-b line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label">图片<font
							class="required">*</font></label>
						<div class="col-sm-10">
							<ul id="goods_image_container" class="preview">
								<c:forEach var="img" items="${imgList}">
									<c:if test="${img.id eq null}">
										<li><input type="hidden" name="imgId" /></li>
									</c:if>
									<c:if test="${img.id ne null}">
										<li class="full"><input type="hidden" name="imgId"
											value="${img.fileId}"><img
											src="${env.storageHost}/${img.imgPath}">
											<div class="operate">
												<a href="javascript:;"
													onclick="ADMIN.goods.imageDelete(this)"><i
													class="fa fa-trash-o"></i></a>
												<div></div>
											</div></li>
									</c:if>
								</c:forEach>
							</ul>
							<div>
								<input id="goods_image" name="image" type="file" /><span
									class="help-block m-b-none">请上传宽高比为1比1的商品图片</span>
							</div>
						</div>
					</div>
				</c:if>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">单位<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<select name="unit" class="form-control m-b" value="${goods.unit}">
							<option value="张">张</option>
							<option value="个">个</option>
							<option value="套">套</option>
							<option value="cm">厘米</option>
							<option value="m">米</option>
							<option value="㎡">平方米</option>
						</select>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">单价<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<input type="text" name="price" class="form-control"
							value="${goods.price}" />
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">上架<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<div class="checkbox">
							<label><input type="checkbox" name="onsale" value="true"
								ck="${goods.onsale}" />是否立即上架</label>
						</div>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">样品<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<div class="checkbox">
							<label><input type="checkbox" name="sample" value="true"
								ck="${goods.sample}" />是否可以申请样品</label>
						</div>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">推荐<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<div class="checkbox">
							<label><input type="checkbox" name="suggest" value="true"
								ck="${goods.suggest}" />推荐到热卖商品中</label>
						</div>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">上传<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<div class="checkbox">
							<label><input type="checkbox" name="upload" value="true"
								ck="${goods.upload}" />是否需要客户上传描述附件</label>
						</div>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">简介<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<textarea name="description" class="form-control">${goods.description}</textarea>
						<span class="help-block m-b-none">建议10~100个汉字</span>
					</div>
				</div>
				<c:forEach var="typeMeta" items="${typeMetaMap[type.id]}">
					<div class="line line-dashed b-b line-lg pull-in"></div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><c:if
								test="${typeMeta.required eq true}">

							</c:if>${typeMeta.name}</label>
						<div class="col-sm-10">
							<c:if test="${typeMeta.multiple eq true}">
								<div class="checkbox">
									<c:forEach var="typeMetaValue"
										items="${typeMetaValueMap[typeMeta.id]}">
										<label><input type="checkbox"
											name="meta-${typeMeta.id}" value="${typeMetaValue.id}"
											ck="${metaMap[typeMeta.id][typeMetaValue.id]}" />${typeMetaValue.value}</label>
									</c:forEach>
								</div>
							</c:if>
							<c:if test="${typeMeta.multiple ne true}">
								<select name="meta-${typeMeta.id}" class="form-control m-b"
									value="${metaMap[typeMeta.id].typeMetaValueId}">
									<option value="">==请选择==</option>
									<c:forEach var="typeMetaValue"
										items="${typeMetaValueMap[typeMeta.id]}">
										<option value="${typeMetaValue.id}">${typeMetaValue.value}</option>
									</c:forEach>
								</select>
							</c:if>
						</div>
					</div>
				</c:forEach>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">检测报告</label>
					<div class="col-sm-10">
						<textarea name="cert" class="form-control">${goods.cert}</textarea>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">内容<font
						class="required">*</font></label>
					<div class="col-sm-10">
						<textarea name="content" class="form-control">${goods.content}</textarea>
					</div>
				</div>
				<div class="line line-dashed b-b line-lg pull-in"></div>
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-2">
						<button type="submit" class="btn btn-primary form_submit">提交</button>
						<a href="#/admin/goods/list/?typeId=${type.id}"
							class="btn btn-default">取消</a>
					</div>
				</div>
			</form>
		</div>
	</section>
</section>
<script>
	$(document).ready(function() {
		ADMIN.goods.submit("${type.id}");
	});
</script>