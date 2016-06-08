package ec.web.admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ec.core.goods.model.CoreGoods;
import ec.core.goods.model.CoreGoodsImg;
import ec.core.goods.model.CoreGoodsMeta;
import ec.core.goods.model.CoreGoodsSample;
import ec.core.goods.service.CoreGoodsService;
import ec.core.mall.model.CoreMall;
import ec.core.mall.model.CoreMallType;
import ec.core.mall.model.CoreMallTypeMeta;
import ec.core.mall.model.CoreMallTypeMetaValue;
import ec.core.mall.service.CoreMallService;
import ec.core.user.model.CoreUser;
import ec.system.file.model.SystemFile;
import ec.system.file.service.SystemFileService;
import ec.web.mall.service.WebService;
import framework.data.json.JSON;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Controller
@RequestMapping("/admin/goods")
public class WebAdminGoodsController {

	/**
	 * 样品列表
	 */
	@RequestMapping(value = "/sample/list/", method = RequestMethod.GET)
	public String sampleList(@RequestParam("status") int status, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<CoreGoodsSample> datas = this.getCoreGoodsService().getGoodsSampleListByStatus(status);
		request.setAttribute("datas", datas);
		return "web/admin/goods/sample_list";
	}

	/**
	 * 商品列表
	 */
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CoreGoods> goodsList = this.getCoreGoodsService().getGoodsList();
		request.setAttribute("goodsList", goodsList);
		return "web/admin/goods/list";
	}

	/**
	 * 添加商品
	 */
	@RequestMapping(value = "/add/", method = RequestMethod.GET)
	public String add(Long typeId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (null == typeId) {
			List<CoreMall> mallList = this.getCoreMallService().getMallList();
			request.setAttribute("mallList", mallList);
			Map<Long, List<CoreMallType>> typeMap = this.getCoreMallService().getTypeMap();
			request.setAttribute("typeMap", typeMap);
			return "web/admin/goods/add";
		} else {
			this.dealFormInfo(typeId, request);
			return "web/admin/goods/form";
		}
	}

	/**
	 * 更新商品
	 */
	@RequestMapping(value = "/update/", method = RequestMethod.GET)
	public String update(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreGoods goods = this.getCoreGoodsService().getGoodsByCode(code);
		this.dealFormInfo(goods, request);
		return "web/admin/goods/form";
	}

	/**
	 * 商品提交
	 */
	@RequestMapping(value = "/submit/", method = RequestMethod.POST)
	public JSON submit(@ModelAttribute CoreGoods goods, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser user = this.getWebService().getSigninUser(request);
		List<CoreGoodsImg> imgs = this.getImgsFromSubmitRequest(request);
		List<CoreGoodsMeta> metas = this.getMetasFromSubmitRequest(request);
		this.getCoreGoodsService().goodsFormValidate(goods, imgs, metas);
		this.getCoreGoodsService().saveOrUpdateGoods(goods, imgs, metas, user);
		return JSON.getJson(true);
	}

	/**
	 * 删除商品
	 */
	@RequestMapping(value = "/delete/", method = RequestMethod.GET)
	public JSON delete(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CoreUser user = this.getWebService().getSigninUser(request);
		this.getCoreGoodsService().deleteGoods(id, user);
		return JSON.getJson(true);
	}

	/**
	 * 图片上传
	 */
	@RequestMapping(value = "/image/upload/", method = RequestMethod.POST)
	public void imageUpload(@RequestParam("typeId") Long typeId, boolean ckeditor, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CoreMallType type = this.getCoreMallService().getTypeById(typeId);
		File uploadFile = this.getSystemFileService().getFileFromRequest(request, 0);
		SystemFile fileModel = this.getSystemFileService().fileUpload("goods",
				type.getMallId() + "-" + String.valueOf(typeId), uploadFile.getName(), uploadFile);
		if (!ckeditor) {
			JSON.getJson(fileModel).write(response);
		} else {
			String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
			String path = (this.getSystemFileService().getStorageService().getHost() + "/" + fileModel.getBucketKey())
					.replace("\\", "/");
			String result = "<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
					+ CKEditorFuncNum + ", '" + path + "' , '上传成功');</script>";
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(result);
		}
	}

	/**
	 * 组装商品表单信息
	 */
	private void dealFormInfo(Long typeId, HttpServletRequest request) {
		CoreGoods goods = new CoreGoods();
		goods.setTypeId(typeId);
		this.dealFormInfo(goods, request);
	}

	/**
	 * 组装商品表单信息
	 */
	private void dealFormInfo(CoreGoods goods, HttpServletRequest request) {
		request.setAttribute("goods", goods);
		CoreMallType type = this.getCoreMallService().getTypeById(goods.getTypeId());
		request.setAttribute("type", type);
		CoreMall mall = this.getCoreMallService().getMallById(type.getMallId());
		request.setAttribute("mall", mall);
		Map<Long, List<CoreMallTypeMeta>> typeMetaMap = this.getCoreMallService().getTypeMetaMap();
		request.setAttribute("typeMetaMap", typeMetaMap);
		Map<Long, List<CoreMallTypeMetaValue>> typeMetaValueMap = this.getCoreMallService().getTypeMetaValueMap();
		request.setAttribute("typeMetaValueMap", typeMetaValueMap);
		this.dealFormMetaInfo(goods, typeMetaMap, request);
		this.dealFormImgInfo(type, goods, request);
	}

	/**
	 * 组装商品表单属性信息
	 */
	@SuppressWarnings("unchecked")
	private void dealFormMetaInfo(CoreGoods goods, Map<Long, List<CoreMallTypeMeta>> typeMetaMap,
			HttpServletRequest request) {
		List<CoreGoodsMeta> metas = this.getCoreGoodsService().getGoodsMetaListByGoods(goods.getId());
		if (null == metas || metas.size() == 0) {
			return;
		}
		List<CoreMallTypeMeta> typeMetas = typeMetaMap.get(goods.getTypeId());
		Map<Long, Object> metaMap = new HashMap<Long, Object>();
		for (CoreGoodsMeta meta : metas) {
			CoreMallTypeMeta tm = null;
			for (CoreMallTypeMeta t : typeMetas) {
				if (t.getId().equals(meta.getTypeMetaId())) {
					tm = t;
					break;
				}
			}
			if (null == tm) {
				break;
			}
			Long key = meta.getTypeMetaId();
			if (tm.isMultiple()) {
				Object mp = metaMap.get(key);
				if (null == mp) {
					mp = new HashMap<Long, String>();
				}
				((Map<Long, String>) mp).put(meta.getTypeMetaValueId(), "true");
				metaMap.put(key, mp);
			} else {
				metaMap.put(key, meta);
			}
		}
		request.setAttribute("metaMap", metaMap);
	}

	/**
	 * 组装商品表单图片信息
	 */
	private void dealFormImgInfo(CoreMallType type, CoreGoods goods, HttpServletRequest request) {
		List<CoreGoodsImg> imgs = null;
		if (null != goods.getId()) {
			imgs = this.getCoreGoodsService().getGoodsImgListByGoods(goods.getId());
		}
		if (null == imgs) {
			imgs = new ArrayList<CoreGoodsImg>();
		}
		for (int n = 0; n < 5; n++) {
			CoreGoodsImg i = null;
			if (imgs.size() < (n + 1)) {
				i = new CoreGoodsImg();
				imgs.add(i);
			}
		}
		request.setAttribute("imgList", imgs);
	}

	/**
	 * 从提交的request中获取商品图片
	 */
	public List<CoreGoodsImg> getImgsFromSubmitRequest(HttpServletRequest request) {
		String[] imgIds = request.getParameterValues("imgId");
		List<CoreGoodsImg> imgs = new ArrayList<CoreGoodsImg>();
		for (String imgId : imgIds) {
			if (null == imgId || "".equals(imgId.trim())) {
				continue;
			}
			CoreGoodsImg img = new CoreGoodsImg();
			img.setFileId(Long.parseLong(imgId));
			img.setMain(imgs.size() == 0);
			imgs.add(img);
		}
		return imgs;
	}

	/**
	 * 从提交的request中获取商品属性值
	 */
	public List<CoreGoodsMeta> getMetasFromSubmitRequest(HttpServletRequest request) {
		String prefix = "meta-";
		List<CoreGoodsMeta> metas = new ArrayList<CoreGoodsMeta>();
		for (String name : request.getParameterMap().keySet()) {
			if (!name.startsWith(prefix)) {
				continue;
			}
			Long metaId = Long.parseLong(name.substring(prefix.length()));
			for (String v : request.getParameterValues(name)) {
				if (null == v || "".equals(v)) {
					continue;
				}
				CoreGoodsMeta meta = new CoreGoodsMeta();
				meta.setTypeMetaId(metaId);
				meta.setTypeMetaValueId(Long.parseLong(v));
				metas.add(meta);
			}
		}
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
	private CoreGoodsService coreGoodsService;

	/**
	 * 
	 */
	@Resource
	private SystemFileService systemFileService;

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

	public CoreGoodsService getCoreGoodsService() {
		return coreGoodsService;
	}

	public void setCoreGoodsService(CoreGoodsService coreGoodsService) {
		this.coreGoodsService = coreGoodsService;
	}

	public SystemFileService getSystemFileService() {
		return systemFileService;
	}

	public void setSystemFileService(SystemFileService systemFileService) {
		this.systemFileService = systemFileService;
	}
}