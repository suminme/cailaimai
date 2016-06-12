package ec.web.mall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.goods.model.CoreGoods;
import ec.core.goods.model.CoreGoodsMeta;
import ec.core.goods.service.CoreGoodsService;
import ec.core.mall.model.CoreMall;
import ec.core.mall.model.CoreMallType;
import ec.core.mall.service.CoreMallService;
import ec.core.user.exception.CoreUserNotExistException;
import ec.core.user.exception.CoreUserValidateException;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserToken;
import ec.core.user.service.CoreUserService;
import ec.system.captcha.service.SystemCaptchaService;
import ec.web.mall.annotation.WebCart;
import ec.web.mall.annotation.WebMallData;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;
import framework.data.web.util.WebUtil;
import net.sf.json.JSONObject;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-04-29
 */
@Controller
@RequestMapping
public class WebMallController {

	/**
	 * 首页
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("suggestGoodsList", this.getCoreGoodsService().getSuggestGoodsListByType(null, 8));
		return "web/mall/index";
	}

	/**
	 * 登陆
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/signin/", method = RequestMethod.GET)
	public String toSignin(String r, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (null == r || "".equals(r)) {
			r = WebUtil.getContextPath(request) + "/";
		}
		request.setAttribute("r", r);
		return "web/mall/signin";
	}

	/**
	 * 登陆
	 */
	@RequestMapping(value = "/signin/", method = RequestMethod.POST)
	public JSON signin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser user = null;
		try {
			user = this.getCoreUserService().getAndCheckUserByUsernameAndPassword(username, password);
		} catch (CoreUserNotExistException e) {
			return JSON.getJson(1, "该手机尚未注册");
		} catch (CoreUserValidateException e) {
			return JSON.getJson(1, "手机号码或密码错误");
		}
		CoreUserToken token = this.getCoreUserService().getCoreUserTokenService().createToken(user.getId(),
				WebUtil.getIpAddr(request));
		JSONObject r = this.getWebService().setToken(user, token, request, response);
		return JSON.getJson(r);
	}

	/**
	 * 注册
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/signup/", method = RequestMethod.GET)
	public String toSignup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mall/signup";
	}

	/**
	 * 注册
	 */
	@RequestMapping(value = "/signup/", method = RequestMethod.POST)
	public JSON signup(CoreUser user, @RequestParam("captcha") String captcha, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!Pattern.compile("^[1]([3-9][0-9]{1})[0-9]{8}$").matcher(user.getMobile()).matches()) {
			return JSON.getJson(1, "手机号码格式不正确");
		}
		if (null == user.getName() || "".equals(user.getName())) {
			user.setName(user.getMobile());
		} else {
			if (!Pattern.compile("(([\u4E00-\u9FA5]{2,10})|([a-zA-Z]{2,10}))").matcher(user.getName()).matches()) {
				return JSON.getJson(1, "昵称只能为2~10个字符的中文或英文");
			}
		}
		this.getSystemCaptchaService().checkAndMarkTargetCaptchaCode(user.getMobile(), captcha);
		this.getCoreUserService().createUser(user);
		CoreUserToken token = this.getCoreUserService().getCoreUserTokenService().createToken(user.getId(),
				WebUtil.getIpAddr(request));
		this.getWebService().setToken(user, token, request, response);
		return JSON.getJson(true);
	}

	/**
	 * 注销
	 */
	@RequestMapping(value = "/signout/", method = RequestMethod.GET)
	public String signout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.getWebService().cleanToken(request, response);
		return "redirect:/";
	}

	/**
	 * 忘记密码
	 */
	@WebCart
	@RequestMapping(value = "/forget/", method = RequestMethod.GET)
	public String toFoget(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "web/mall/forget";
	}

	/**
	 * 忘记密码
	 */
	@RequestMapping(value = "/forget/", method = RequestMethod.POST)
	public JSON foget(@RequestParam("mobile") String mobile, @RequestParam("password") String password,
			@RequestParam("captcha") String captcha, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.getSystemCaptchaService().checkAndMarkTargetCaptchaCode(mobile, captcha);
		this.getCoreUserService().resetPassword(mobile, password);
		return JSON.getJson(true);
	}

	/**
	 * 验证码
	 */
	@RequestMapping(value = "/captcha/")
	public JSON captcha(@RequestParam("mobile") String mobile, @RequestParam("usedFor") String usedFor,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Pattern.compile("^[1]([3-9][0-9]{1})[0-9]{8}$").matcher(mobile).matches()) {
			return JSON.getJson(1, "手机号码格式不正确");
		}
		CoreUser user = this.getCoreUserService().getUserByMobile(mobile);
		if ("signup".equals(usedFor) && null != user) {
			return JSON.getJson(1, "该手机号码已被注册");
		}
		if ("forget".equals(usedFor) && null == user) {
			return JSON.getJson(1, "该手机号码尚未被注册");
		}
		this.getSystemCaptchaService().sendSmsCaptcha(mobile, usedFor, WebUtil.getIpAddr(request));
		return JSON.getJson(60);
	}

	/**
	 * FAQ
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/faq/{name}.html", method = RequestMethod.GET)
	public String faq(@PathVariable String name, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<CoreMall> mallList = this.getCoreMallService().getMallList();
		request.setAttribute("mallList", mallList);
		return "web/mall/faq/" + name;
	}

	/**
	 * 商城主页
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/mall/{mallSign}/", method = RequestMethod.GET)
	public String mall(@PathVariable String mallSign, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreMall mall = this.getCoreMallService().getMallBySign(mallSign);
		request.setAttribute("mall", mall);
		List<CoreGoods> goodsList = this.getCoreGoodsService().getGoodsListByMall(mall.getId());
		request.setAttribute("suggestGoodsList", this.getCoreGoodsService().getSuggestGoodsListByType(null, 8));
		request.setAttribute("goodsList", goodsList);
		List<CoreMallType> mallGoodsTypes = this.getCoreGoodsService().getTypeListByMallWithGoodsCount(mall.getId());
		request.setAttribute("mallGoodsTypes", mallGoodsTypes);
		return "web/mall/type";
	}

	/**
	 * 类别商城
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/mall/{mallSign}/{typeId}/", method = RequestMethod.GET)
	public String mallWithType(@PathVariable String mallSign, @PathVariable Long typeId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreMallType type = this.getCoreMallService().getTypeById(typeId);
		request.setAttribute("type", type);
		CoreMall mall = this.getCoreMallService().getMallById(type.getMallId());
		request.setAttribute("mall", mall);
		List<CoreMallType> mallGoodsTypes = this.getCoreGoodsService()
				.getTypeListByMallWithGoodsCount(type.getMallId());
		request.setAttribute("mallGoodsTypes", mallGoodsTypes);
		List<CoreGoodsMeta> metas = this.dealTypeQueryInfo(request);
		request.setAttribute("suggestGoodsList", this.getCoreGoodsService().getSuggestGoodsListByType(null, 8));
		request.setAttribute("goodsList", this.getCoreGoodsService().getGoodsListByTypeAndMetas(typeId, metas));
		return "web/mall/type_mall";
	}

	/**
	 * 处理商品筛选信息
	 */
	private List<CoreGoodsMeta> dealTypeQueryInfo(HttpServletRequest request) {
		String prefix = "meta-";
		Map<Long, Long> mvp = new HashMap<Long, Long>();
		List<CoreGoodsMeta> metas = new ArrayList<CoreGoodsMeta>();
		for (String name : request.getParameterMap().keySet()) {
			if (!name.startsWith(prefix)) {
				continue;
			}
			for (String v : request.getParameterValues(name)) {
				if (null == v || "".equals(v)) {
					continue;
				}
				Long typeMetaId = Long.parseLong(name.substring(prefix.length()));
				Long typeMetaValueId = Long.parseLong(v);
				CoreGoodsMeta meta = new CoreGoodsMeta();
				meta.setTypeMetaId(typeMetaId);
				meta.setTypeMetaValueId(typeMetaValueId);
				mvp.put(typeMetaId, typeMetaValueId);
				metas.add(meta);
			}
		}
		request.setAttribute("mvp", mvp);
		return metas;
	}

	/**
	 * 
	 */
	@Resource
	private WebService webService;

	/**
	 * 
	 */
	@Resource
	private CoreMallService coreMallService;

	/**
	 * 
	 */
	@Resource
	private CoreUserService coreUserService;

	/**
	 * 
	 */
	@Resource
	private CoreGoodsService coreGoodsService;

	/**
	 * 
	 */
	@Resource
	private SystemCaptchaService systemCaptchaService;

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public CoreMallService getCoreMallService() {
		return coreMallService;
	}

	public void setCoreMallService(CoreMallService coreMallService) {
		this.coreMallService = coreMallService;
	}

	public CoreUserService getCoreUserService() {
		return coreUserService;
	}

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}

	public CoreGoodsService getCoreGoodsService() {
		return coreGoodsService;
	}

	public void setCoreGoodsService(CoreGoodsService coreGoodsService) {
		this.coreGoodsService = coreGoodsService;
	}

	public SystemCaptchaService getSystemCaptchaService() {
		return systemCaptchaService;
	}

	public void setSystemCaptchaService(SystemCaptchaService systemCaptchaService) {
		this.systemCaptchaService = systemCaptchaService;
	}
}