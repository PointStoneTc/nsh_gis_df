package nsh.dengfeng.gisplan.merchant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import nsh.dengfeng.gisplan.dot.service.DotServiceI;
import nsh.dengfeng.gisplan.merchant.service.MerchantServiceI;
import nsh.dengfeng.gisplan.merchant.view.MerchantRegisterListView;
import nsh.dengfeng.gisplan.merchant.view.MerchantView;

/**
 * @Title: Controller
 * @Description: 商户
 * @author 赵琦
 * @date 2019-03-18 10:23:00
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    /*--------------------------------------------
    |             Variable                       |
    ============================================*/
    private String message;

    /*--------------------------------------------
    |             Injection                      |
    ============================================*/
    @Autowired
    private DotServiceI dotService;

    @Autowired
    private MerchantServiceI merchantService;

    /*--------------------------------------------
    |             method                       |
    ============================================*/

    /**
     * @Title:商户列表
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "list", method = RequestMethod.GET)
    public ModelAndView registerList(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/merchant/register_list");
        mv.addObject("dots", dotService.findAllDot());
        return mv;
    }

    /**
     * @Title:商户datagrid数据
     * 
     * @param merchantRegisterListView
     * @param dotId
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(value = "register", params = "datagrid", method = RequestMethod.POST)
    @ResponseBody
    public void registerDatagrid(MerchantRegisterListView merchantRegisterListView, Long dotId, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        merchantService.getRegisterList(merchantRegisterListView, dotId, dataGrid);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * @Title:商户新增
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toAdd", method = RequestMethod.GET)
    public ModelAndView registerToAdd(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/merchant/register_view");
        mv.addObject("view", new MerchantView());
        return mv;
    }

    /**
     * @Title:商户更新
     * 
     * @param id 商户id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toUpdate", method = RequestMethod.GET)
    public ModelAndView registerToUpdate(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/merchant/register_view");
        MerchantView MerchantView = merchantService.getRegisterView(id);
        mv.addObject("view", MerchantView);
        return mv;
    }

    /**
     * @Title:商户查看详情
     * 
     * @param id 商户id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toDetail", method = RequestMethod.GET)
    public ModelAndView registerToDetail(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/merchant/register_detail");
        MerchantView merchantView;
        try {
            merchantView = merchantService.getRegisterView(id);
            mv.addObject("view", merchantView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    /**
     * @Title:商户保存或更新
     * 
     * @param merchantView
     * @param dotIds
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerSaveOrUpdate(MerchantView merchantView, String dotIds, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            if (oConvertUtils.isNotEmpty(merchantView.getMerchant().getId())) { // 更新
                sucess = merchantService.registerUpdateMain(merchantView, dotIds);
                j.setSuccess(sucess);
                j.setMsg("更新成功!");
                logger.info("update sucess: " + merchantView);
            } else {
                sucess = merchantService.registerSaveMain(merchantView, dotIds);
                j.setSuccess(sucess);
                j.setMsg("保存成功!");
                logger.info("save sucess: " + merchantView);
            }
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("保存异常, 请联系管理员!");
            logger.error("saveOrUpdate error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:商户删除
     * 
     * @param id 商户id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerDel(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            sucess = merchantService.registerDeleteMain(id);
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
