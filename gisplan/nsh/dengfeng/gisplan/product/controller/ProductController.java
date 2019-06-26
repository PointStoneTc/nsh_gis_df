package nsh.dengfeng.gisplan.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import nsh.dengfeng.gisplan.product.entity.Product;
import nsh.dengfeng.gisplan.product.service.ProductServiceI;

/**
 * @Title: Controller
 * @Description: 产品
 * @author 赵琦
 * @date 2019-03-18 10:23:00
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /*--------------------------------------------
    |             Variable                       |
    ============================================*/
    private String message;

    /*--------------------------------------------
    |             Injection                      |
    ============================================*/
    @Autowired
    private ProductServiceI productService;

    @Autowired
    private SystemService systemService;

    /*--------------------------------------------
    |             method                       |
    ============================================*/

    /**
     * @Title:产品列表
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "list", method = RequestMethod.GET)
    public ModelAndView registerList(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/product/register_list");
        return mv;
    }

    /**
     * @Title:产品datagrid数据
     * 
     * @param product
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(value = "register", params = "datagrid", method = RequestMethod.POST)
    @ResponseBody
    public void registerDatagrid(Product product, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        productService.getRegisterList(product, dataGrid);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * @Title:产品新增
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toAdd", method = RequestMethod.GET)
    public ModelAndView registerToAdd(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/product/register_view");
        mv.addObject("view", new Product());
        return mv;
    }

    /**
     * @Title:产品更新
     * 
     * @param id 产品id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toUpdate", method = RequestMethod.GET)
    public ModelAndView registerToUpdate(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/product/register_view");
        Product product = systemService.getEntity(Product.class, id);
        mv.addObject("view", product);
        return mv;
    }

    /**
     * @Title:产品保存或更新
     * 
     * @param product
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerSaveOrUpdate(Product product, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            if (oConvertUtils.isNotEmpty(product.getId())) { // 更新
                sucess = productService.registerUpdateMain(product);
                j.setSuccess(sucess);
                j.setMsg("更新成功!");
                logger.info("update sucess: " + product);
            } else {
                sucess = productService.registerSaveMain(product);
                j.setSuccess(sucess);
                j.setMsg("保存成功!");
                logger.info("save sucess: " + product);
            }
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("保存异常, 请联系管理员!");
            logger.error("saveOrUpdate error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:产品删除
     * 
     * @param id 产品id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerDel(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            sucess = productService.registerDeleteMain(id);
            j.setSuccess(sucess);
            j.setMsg("删除成功!");
            logger.info("update sucess: " + id);
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("删除异常, 请联系管理员!");
            logger.error("update error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:产品提交
     * 
     * @param id 产品id
     * @param req
     * @return
     */
    @RequestMapping(value = "/register", params = "commit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerCommit(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            sucess = productService.registerCommitMain(id);
            j.setSuccess(sucess);
            j.setMsg("更新成功!");
            logger.info("update sucess:" + id);
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("更新异常, 请联系管理员!");
            logger.error("update error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:银行产品datagrid数据
     * 
     * @param dotId
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(value = "bank", params = "datagrid", method = RequestMethod.POST)
    @ResponseBody
    public void bankDatagrid(Long dotId, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        productService.getBankList(dotId, dataGrid);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * @Title:产品保存
     * 
     * @param dotId 网点id
     * @param productIds 产品ids
     * @param req
     * @return
     */
    @RequestMapping(value = "bank", params = "save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson bankSave(@RequestParam(value = "dotId", required = true) Long dotId, @RequestParam(value = "productIds", required = true) String productIds,
            HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            sucess = productService.bankSave(dotId, productIds);
            j.setSuccess(sucess);
            j.setMsg("保存成功!");
            logger.info("save sucess: " + productIds);
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("保存异常, 请联系管理员!");
            logger.error("saveOrUpdate error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:删除银行产品
     * 
     * @param id 银行产品关系id
     * @param req
     * @return
     */
    @RequestMapping(value = "bank", params = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson bankDel(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            sucess = productService.bankDelete(id);
            j.setSuccess(sucess);
            j.setMsg("删除成功!");
            logger.info("update sucess: " + id);
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("删除异常, 请联系管理员!");
            logger.error("update error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:银行选择产品
     * @param excludeIds
     * @param singleSelect
     * @param request
     * @return
     */
    @RequestMapping(value = "/bank/select", params = "oneOrMult", method = RequestMethod.GET)
    public ModelAndView bankSelectOneOrMult(@RequestParam(value = "excludeIds", required = true) String excludeIds,
            @RequestParam(value = "singleSelect", required = true) boolean singleSelect, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("gisplan/product/bankSelectOneOrMult_dialog");
        if (!singleSelect)
            singleSelect = false;
        mv.addObject("singleSelect", singleSelect);
        mv.addObject("excludeIds", excludeIds);
        return mv;
    }

    /**
     * @Title:银行选择产品datagrid数据
     * @param excludeIds
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(value = "/bank/select", params = "oneOrMultDatagrid", method = RequestMethod.POST)
    @ResponseBody
    public void bankSelectOneOrMultDatagrid(String excludeIds, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        productService.getBankOneOrMultList(excludeIds, dataGrid);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * @Title:商户银行产品datagrid数据
     * 
     * @param merchantId 商户id
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(value = "merchant", params = "datagrid", method = RequestMethod.POST)
    @ResponseBody
    public void merchantDatagrid(Long merchantId, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        productService.getMerchantList(merchantId, dataGrid);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * @Title:商户银行产品保存
     * 
     * @param merchantId 商户id
     * @param bankProductIds 银行产品ids
     * @param req
     * @return
     */
    @RequestMapping(value = "merchant", params = "save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson merchantSave(@RequestParam(value = "merchantId", required = true) Long merchantId, @RequestParam(value = "bankProductIds", required = true) String bankProductIds,
            HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            sucess = productService.merchantSave(merchantId, bankProductIds);
            j.setSuccess(sucess);
            j.setMsg("保存成功!");
            logger.info("save sucess: " + bankProductIds);
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("保存异常, 请联系管理员!");
            logger.error("saveOrUpdate error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:删除商户银行产品
     * 
     * @param id 商户银行产品关系id
     * @param req
     * @return
     */
    @RequestMapping(value = "merchant", params = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson merchantDel(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            sucess = productService.merchantDelete(id);
            j.setSuccess(sucess);
            j.setMsg("删除成功!");
            logger.info("update sucess: " + id);
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("删除异常, 请联系管理员!");
            logger.error("update error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:商家选择银行产品
     * 
     * @param excludeIds
     * @param merchantId
     * @param singleSelect
     * @param request
     * @return
     */
    @RequestMapping(value = "/merchant/select", params = "oneOrMult", method = RequestMethod.GET)
    public ModelAndView merchantSelectOneOrMult(@RequestParam(value = "excludeIds", required = true) String excludeIds,
            @RequestParam(value = "merchantId", required = true) String merchantId, @RequestParam(value = "singleSelect", required = true) boolean singleSelect,
            HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("gisplan/product/merchantSelectOneOrMult_dialog");
        if (!singleSelect)
            singleSelect = false;
        mv.addObject("singleSelect", singleSelect);
        mv.addObject("excludeIds", excludeIds);
        mv.addObject("merchantId", merchantId);
        return mv;
    }

    /**
     * @Title:商家选择银行产品datagrid数据
     * 
     * @param excludeIds
     * @param merchantId
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(value = "/merchant/select", params = "oneOrMultDatagrid", method = RequestMethod.POST)
    @ResponseBody
    public void merchantSelectOneOrMultDatagrid(String excludeIds, Long merchantId, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        productService.getMerchantOneOrMultList(excludeIds, merchantId, dataGrid);
        TagUtil.datagrid(response, dataGrid);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
