package nsh.dengfeng.gisplan.selfservice.controller;

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

import nsh.dengfeng.gisplan.dot.service.DotServiceI;
import nsh.dengfeng.gisplan.selfservice.service.SelfserviceServiceI;
import nsh.dengfeng.gisplan.selfservice.view.SelfserviceListView;
import nsh.dengfeng.gisplan.selfservice.view.SelfserviceView;

/**
 * @Title: Controller
 * @Description: 银行自助设备
 * @author 赵琦
 * @date 2019-03-18 10:23:00
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/selfservice")
public class SelfserviceController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(SelfserviceController.class);

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
    private SelfserviceServiceI selfserviceService;

    @Autowired
    private SystemService systemService;

    /*--------------------------------------------
    |             method                       |
    ============================================*/

    /**
     * @Title:银行自助设备列表
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "list", method = RequestMethod.GET)
    public ModelAndView registerList(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/selfservice/register_list");
        mv.addObject("dots", dotService.findAllDot());
        return mv;
    }

    /**
     * @Title:银行自助设备datagrid数据
     * 
     * @param selfserviceListView
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(value = "register", params = "datagrid", method = RequestMethod.POST)
    @ResponseBody
    public void registerDatagrid(SelfserviceListView selfserviceListView, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        selfserviceService.getRegisterList(selfserviceListView, dataGrid);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * @Title:银行自助设备新增
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toAdd", method = RequestMethod.GET)
    public ModelAndView registerToAdd(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/selfservice/register_view");
        mv.addObject("dots", dotService.findAllDot());
        mv.addObject("view", new SelfserviceView());
        return mv;
    }

    /**
     * @Title:银行自助设备更新
     * 
     * @param id 银行自助设备id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toUpdate", method = RequestMethod.GET)
    public ModelAndView registerToUpdate(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/selfservice/register_view");
        SelfserviceView selfserviceView = selfserviceService.getRegisterView(id);
        mv.addObject("dots", dotService.findAllDot());
        mv.addObject("view", selfserviceView);
        return mv;
    }

    /**
     * @Title:银行自助设备保存或更新
     * 
     * @param selfserviceView
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerSaveOrUpdate(SelfserviceView selfserviceView, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            if (oConvertUtils.isNotEmpty(selfserviceView.getSelfservice().getId())) { // 更新
                sucess = selfserviceService.registerUpdateMain(selfserviceView);
                j.setSuccess(sucess);
                j.setMsg("更新成功!");
                logger.info("update sucess: " + selfserviceView);
            } else {
                sucess = selfserviceService.registerSaveMain(selfserviceView);
                j.setSuccess(sucess);
                j.setMsg("保存成功!");
                logger.info("save sucess: " + selfserviceView);
            }
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("保存异常, 请联系管理员!");
            logger.error("saveOrUpdate error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:银行自助设备删除
     * 
     * @param id 银行自助设备id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerDel(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            sucess = selfserviceService.registerDeleteMain(id);
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
