<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="outsite">
	<div class="socials-theme right">
		<ul class="list-socials">
			<li style="top: 300px;">
				<div class="media">
					<div class="icon-facebook">
						<span class="fa fa-user"></span>
					</div>
					<div class="media-body">
						<span>联系我们</span>
					</div>
				</div>
				<div class="box-content">
					<div class="clearfix" style="text-align: center;">
						<div style="padding-top:10px;padding-bottom:10px;border-bottom:1px #eee solid;">
							<b style="font-size:14px;font-weight:normal;">售前咨询：&nbsp;&nbsp;&nbsp;</b><a target="_blank"
								href="http://wpa.qq.com/msgrd?v=3&uin=362933227&site=qq&menu=yes"><img
								border="0" src="http://wpa.qq.com/pa?p=2:1400983035:41"
								alt="点击这里给我发消息" title="点击这里给我发消息" /></a>
						</div>
						<div style="padding-top:10px;padding-bottom:10px;border-bottom:1px #eee solid;">
							<b style="font-size:14px;font-weight:normal;">售后咨询：&nbsp;&nbsp;&nbsp;</b><a target="_blank"
								href="http://wpa.qq.com/msgrd?v=3&uin=3484167439&site=qq&menu=yes"><img
								border="0" src="http://wpa.qq.com/pa?p=2:1400983035:41"
								alt="点击这里给我发消息" title="点击这里给我发消息" /></a>
						</div>
						<div style="padding-top:10px;">
							<img src="${env.staticHost}/mall/images/wechat01.jpg"
								style="width: 170px; height: 170px;" />
							<p>微信扫一扫关注官方公众号</p>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>
<footer id="footer">
	<div class="container">
		<div class="slogen">
			<ul>
				<li><img
					src="${env.staticHost}/mall/images/slogen/service_items_1.jpg" /></li>
				<li><img
					src="${env.staticHost}/mall/images/slogen/service_items_2.jpg" /></li>
				<li><img
					src="${env.staticHost}/mall/images/slogen/service_items_3.jpg" /></li>
				<li><img
					src="${env.staticHost}/mall/images/slogen/service_items_4.jpg" /></li>
			</ul>
		</div>
	</div>
	<div class="footer-center">
		<div class="container">
			<div class="inside space-padding-tb-40">
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-3 column">
						<div class="custom-logo">
							<img alt="logo" src="${env.staticHost}/mall/images/logo.png">
							<div class="space-top-30">
								公司名称：${config.company.name}<br /> 公司税号：${config.company.tax}<br />
								开户行：${config.company.bank}<br /> 对公账号：${config.company.account}<br />
							</div>
						</div>
					</div>
					<div class="col-md-2 col-sm-6 col-xs-12 column">
						<div class="panel">
							<div class="panel-heading">
								<h5 class="panel-title">关于我们</h5>
							</div>
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a href="${env.host}/faq/about.html#a1">网站介绍</a></li>
									<li><a href="${env.host}/faq/about.html#a2">用户协议</a></li>
									<li><a href="${env.host}/faq/about.html#a3">法律声明</a></li>
									<li><a href="${env.host}/faq/about.html#a4">隐私保护</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-2 col-sm-6 col-xs-12 column">
						<div class="panel">
							<div class="panel-heading">
								<h5 class="panel-title">新手上路</h5>
							</div>
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a href="${env.host}/faq/new.html#a1">购买流程</a></li>
									<li><a href="${env.host}/faq/new.html#a2">物流政策</a></li>
									<li><a href="${env.host}/faq/new.html#a3">订购说明</a></li>
									<li><a href="${env.host}/faq/new.html#a4">打款账号</a></li>
									<li><a href="${env.host}/faq/new.html#a5">申请样品</a></li>
									<li><a href="${env.host}/faq/new.html#a6">购物保障</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-2 col-sm-6 col-xs-12 column">
						<div class="panel">
							<div class="panel-heading">
								<h5 class="panel-title">客户服务</h5>
							</div>
							<div class="panel-body">
								<ul class="list-unstyled">
									<li><a href="${env.host}/faq/custom.html#a1">联系我们</a></li>
									<li><a href="${env.host}/faq/custom.html#a2">投诉建议</a></li>
									<li><a href="${env.host}/faq/custom.html#a3">商务合作</a></li>
									<li><a href="${env.host}/faq/custom.html#a4">招聘信息</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-6 col-md-3 column linef">
						<div class="panel pull-right" style="border-left: 1px;">
							<img src="${env.staticHost}/mall/images/wechat01.jpg"
								style="width: 180px; height: 180px;" />
							<p style="font-size: 13px; margin-left: 10px;">微信扫一扫关注官方公众号</p>
							<p
								style="font-size: 16px; font-weight: bold; margin-left: -16px;">免费客服热线：${config.company.telphone}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</footer>
<div id="powered">
	<div class="container">
		<div class="inside">
			<div class="clearfix space-padding-tb-10">
				<div class="copyright">${config.site.copyright}<a
						target="_blank" href="http://www.miibeian.gov.cn/">${config.site.icp}</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="top" class="bo-social-icons">
	<a href="#" class="bo-social-gray radius-x scrollup"><i
		class="fa fa-angle-up"></i></a>
</div>