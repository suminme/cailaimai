package ec.web.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ec.core.goods.model.CoreGoodsSample;
import ec.core.goods.service.CoreGoodsService;
import ec.core.mall.model.CoreMall;
import ec.core.mall.model.CoreMallType;
import ec.core.mall.service.CoreMallService;
import ec.core.user.model.CoreUser;
import ec.core.user.service.CoreUserService;
import ec.web.mall.annotation.WebCart;
import ec.web.mall.annotation.WebMallData;
import ec.web.mall.service.WebService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-04-29
 */
@Controller
@RequestMapping("/mall/goods")
public class WebMallGoodsController {

	/**
	 * 商品明细
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/{goodsId}.html", method = RequestMethod.GET)
	public String goodsDetail(@PathVariable Long goodsId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreGoods goods = this.getCoreGoodsService().getGoodsById(goodsId);
		List<CoreGoodsMeta> metas = this.getCoreGoodsService().getGoodsMetaListByGoods(goods.getId());
		if (null != metas) {
			Map<Long, String[]> metaMap = new HashMap<Long, String[]>();
			for (CoreGoodsMeta meta : metas) {
				Long key = meta.getTypeMetaId();
				String[] s = metaMap.get(key);
				if (null == s) {
					s = new String[] { meta.getTypeMeta(), meta.getTypeMetaValue() };
				} else {
					String v = s[1];
					v = v + "," + meta.getTypeMetaValue();
					s = new String[] { meta.getTypeMeta(), v };
				}
				metaMap.put(key, s);
			}
			request.setAttribute("metas", metaMap.values());
		}
		request.setAttribute("goods", goods);
		this.getAndSetDetailByType(goods.getTypeId(), request);
		request.setAttribute("imgs", this.getCoreGoodsService().getGoodsImgListByGoods(goods.getId()));
		return "web/mall/goods_detail";
	}

	/**
	 * 申请样品
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/sample/apply/", method = RequestMethod.GET)
	public String sampleApply(@RequestParam("goodsId") Long goodsId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		CoreGoods goods = this.getCoreGoodsService().getGoodsById(goodsId);
		if (!goods.isSample()) {
			return null;
		}
		request.setAttribute("goods", goods);
		request.setAttribute("mall", this.getCoreMallService().getMallById(goods.getMallId()));
		request.setAttribute("addressList", this.getCoreUserService().getAddressListByUser(signinUser.getId()));
		return "web/mall/goods_sample";
	}

	/**
	 * 申请样品
	 */
	@WebCart
	@WebMallData
	@RequestMapping(value = "/sample/apply/", method = RequestMethod.POST)
	public String sampleApply(@RequestParam("goodsId") Long goodsId, @RequestParam("addressId") Long addressId,
			String about, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CoreUser signinUser = this.getWebService().getSigninUser(request);
		CoreGoodsSample sample = this.getCoreGoodsService().goodsSampleApply(signinUser, goodsId, addressId, about);
		request.setAttribute("sample", sample);
		CoreGoods goods = this.getCoreGoodsService().getGoodsById(goodsId);
		request.setAttribute("goods", goods);
		request.setAttribute("mall", this.getCoreMallService().getMallById(goods.getMallId()));
		return "web/mall/goods_sample_success";
	}

	/**
	 * 根据类别获取页面信息
	 */
	private CoreMallType getAndSetDetailByType(Long typeId, HttpServletRequest request) {
		CoreMallType type = this.getCoreMallService().getTypeById(typeId);
		request.setAttribute("type", type);
		CoreMall mall = this.getCoreMallService().getMallById(type.getMallId());
		request.setAttribute("mall", mall);
		List<CoreGoods> suggestGoodsList = this.getCoreGoodsService().getSuggestGoodsListByType(null, 8);
		request.setAttribute("suggestGoodsList", suggestGoodsList);
		List<CoreMallType> mallGoodsTypes = this.getCoreGoodsService()
				.getTypeListByMallWithGoodsCount(type.getMallId());
		request.setAttribute("mallGoodsTypes", mallGoodsTypes);
		return type;
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
	private CoreUserService coreUserService;

	/**
	 * 
	 */
	@Resource
	private CoreMallService coreMallService;

	/**
	 * 
	 */
	@Resource
	private CoreGoodsService coreGoodsService;

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public CoreUserService getCoreUserService() {
		return coreUserService;
	}

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}

	public CoreMallService getCoreMallService() {
		return coreMallService;
	}

	public void setCoreMallService(CoreMallService coreMallService) {
		this.coreMallService = coreMallService;
	}

	public CoreGoodsService getCoreGoodsService() {
		return coreGoodsService;
	}

	public void setCoreGoodsService(CoreGoodsService coreGoodsService) {
		this.coreGoodsService = coreGoodsService;
	}
}