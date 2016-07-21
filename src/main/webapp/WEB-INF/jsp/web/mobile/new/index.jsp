<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../include/header.jsp" />
<body>
	<div class="nc-main">
		<header data-am-widget="header" class="am-header bgc5">
			<div class="am-header-left am-header-nav">
				<a href="javascript:;"> <span class="am-icon-user"></span>
				</a>
			</div>
			<div
				class="am-header-title am-header-title-txt am-form-group am-form-icon am-form-feedback">
				新手上路</div>
		</header>
		<div>
			<h2 id="a1">一、购买流程</h2>
			<p>材来买，是专业的广告材料电商平台，注册材来买账号后，即可在线选购广告材料。</p>
			<p>在线购买商品，订单提交成功后，请在24小时内付款，逾期订单有可能会取消，付款成功后请关注个人中心订单状态。</p>
			<p>
				购买流程：<br />
				选择商品放入购物车&rarr;结算中心&rarr;银行转账（对公付款）&rarr;站内管理员确认订单&rarr;用户自提商品（或材来买发货）&rarr;签收&rarr;评价
			</p>
			<p>
				咨询热线：400-921-9928；021-61317921<br /> 在线咨询QQ：
				362933227；微信：cailaimai_com
			</p>

			<h2 id="a2">
				<br /> 二、物流政策
			</h2>

			<p>本站所有产品均为出库价，由于木工板材是大体积大重量的产品，运费没有统一的标志，您可以选择自提，也可以选择由材来买配送。</p>

			<p>

				<c:forEach var="mall" items="${mallList}">
					<b>${mall.name}</b>：<br />
													${mall.freightAbout}<br />
					<br />
				</c:forEach>

				未标注的上海区县或外地，数字图文内商品配送费用请联系站内客服。<br />
				注：以上均为指定区域的物流运费，指定区域之外的地方，物流费用请联系站内客服人员。

			</p>

			<h2 id="a3">三、订购说明</h2>

			<p>
				木工板材：<br /> 木工板材类商品，通常24小时内发货，加工定制产品，通常一周左右发货，以实际配送信息为准；<br />
				如订单提交成功，有个别商品遇到断货，我们会及时与您联系；<br /> 数字图文：<br />
				数字图文类商品，属于定制商品，请提供制作文件，并对尺寸、材质、制作要求标注清楚。<br />
				定制类商品，由于显示器的色彩以及制作文件的精度、软件等原因，制作成品都会有一定的误差，属于正常现象。<br />
			</p>

			<p>
				色差告知：<br /> 商品均为实物拍摄 由于拍摄光线不一样以及电脑显示器不同会存在一定色差是难免的 请以收到实物为准。
			</p>

			<p>
				定制商品：<br /> 定制板材和数字图文类别商品，属于定制商品，无法二次销售，非质量问题不接受退换货，请知晓并理解。
			</p>

			<p>
				到货签收<br />
				由于物流运输，可能会影响到包装的望知晓，还请买家谅解，对于运输途中造成的质量问题，请您及时与我们的客服联系，我们将会无条件的免费为您退换货。
			</p>

			<p>
				<br /> 咨询热线：400-921-9928；021-61317921<br /> 在线咨询QQ：
				362933227；微信：cailaimai_com
			</p>

			<h2 id="a4">四、打款账号</h2>

			<p>商品订单提完后，请在24小时内，完成对公付款（银行转账），如24小时后未对订单进行付款，该订单有可能会被取消。</p>

			<p>
				打款信息：<br /> 公司名称：上海钒铂商贸有限公司<br /> 公司税号：91310117MA1J17WP9A<br />
				开户行：中国农业银行上海周东路支行<br /> 账号：03418520040001909
			</p>

			<p>

				咨询热线：400-921-9928；021-61317921<br /> 在线咨询QQ：
				362933227；微信：cailaimai_com
			</p>

			<h2 id="a5">五、申请样品</h2>

			<p>所有注册用户，注册后即可在线免费申请样品，每样商品享有一次免费申请样品的机会，如果正式订购的商品和样品有差异，可申请无条件退换货（样品随机发货，不挑色，不挑码）。</p>

			<p>您还可以前往材来买线下体验中心（上海浦东），实地看样或免费索取样品。</p>

			<p>免费拿样：进货先拿样，购物有保障！</p>

			<p>

				咨询热线：400-921-9928；021-61317921<br /> 在线咨询QQ：
				362933227；微信：cailaimai_com
			</p>

			<h2 id="a6">六、购物保障</h2>

			<p>木工板材类商品，厚度会有正负0.5mm左右误工差，属于市场正常普遍现象，介意误拍，板材商品均为整张起卖。</p>

			<p>商品均为实物拍摄 由于拍摄光线不一样以及电脑显示器不同会存在一定色差是难免的 请以收到实物为准。</p>

			<p>收货时，请务必验收哦，签收后再发现破损问题材来买不承担责任的，若发现破损情况请拍下照片做证据，并在配送单据上注明破损数量，并及时联系我们在线客服。</p>

			<p>

				咨询热线：400-921-9928；021-61317921<br /> 在线咨询QQ：
				362933227；微信：cailaimai_com
			</p>



		</div>
		<jsp:include page="../include/footbar.jsp" />
	</div>
	<jsp:include page="../include/footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			MOBILE.init.tabbar(1);
		});
	</script>
</body>
</html>