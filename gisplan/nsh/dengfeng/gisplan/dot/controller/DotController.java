package nsh.dengfeng.gisplan.dot.controller;

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

import nsh.dengfeng.gisplan.dot.entity.Dot;
import nsh.dengfeng.gisplan.dot.service.DotServiceI;
import nsh.dengfeng.gisplan.dot.view.DotRegisterListView;
import nsh.dengfeng.gisplan.dot.view.DotView;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreKeyEnum;
import nsh.dengfeng.gisplan.storeGeometry.service.StoreGeometryServiceI;

/**
 * @Title: Controller
 * @Description: 网点
 * @author 赵琦
 * @date 2019-03-18 10:23:00
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/dot")
public class DotController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(DotController.class);

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
    private StoreGeometryServiceI storeGeometryService;

    @Autowired
    private SystemService systemService;

    /*--------------------------------------------
    |             method                       |
    ============================================*/

    /**
     * @Title:网点列表
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "list", method = RequestMethod.GET)
    public ModelAndView registerList(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/dot/register_list");
        return mv;
    }

    /**
     * @Title:网点datagrid数据
     * 
     * @param dotRegisterListView
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(value = "register", params = "datagrid", method = RequestMethod.POST)
    @ResponseBody
    public void registerDatagrid(DotRegisterListView dotRegisterListView, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        dotService.getRegisterList(dotRegisterListView, dataGrid);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * @Title:网点新增
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toAdd", method = RequestMethod.GET)
    public ModelAndView registerToAdd(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/dot/register_view");
        mv.addObject("view", new DotView());
        return mv;
    }

    /**
     * @Title:网点更新
     * 
     * @param id 网点id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toUpdate", method = RequestMethod.GET)
    public ModelAndView registerToUpdate(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/dot/register_view");
        DotView dotView = new DotView();
        Dot dot = systemService.getEntity(Dot.class, id);
        StoreGeometry sg = storeGeometryService.findByFk(id, StoreKeyEnum.WD.getKey());
        dotView.setDot(dot);
        dotView.setSg(sg);
        mv.addObject("view", dotView);
        return mv;
    }

    /**
     * @Title:网点查看详情
     * 
     * @param id 网点id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "toDetail", method = RequestMethod.GET)
    public ModelAndView registerToDetail(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/dot/register_detail");
        DotView dotView;
        dotView = dotService.getRegisterView(id);
        mv.addObject("view", dotView);
        return mv;
    }

    /**
     * @Title:网点保存或更新
     * 
     * @param dotView
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerSaveOrUpdate(DotView dotView, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            if (oConvertUtils.isNotEmpty(dotView.getDot().getId())) { // 更新
                sucess = dotService.registerUpdateMain(dotView);
                j.setSuccess(sucess);
                j.setMsg("更新成功!");
                logger.info("update sucess: " + dotView);
            } else {
                sucess = dotService.registerSaveMain(dotView);
                j.setSuccess(sucess);
                j.setMsg("保存成功!");
                logger.info("save sucess: " + dotView);
            }
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("保存异常, 请联系管理员!");
            logger.error("saveOrUpdate error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:网点删除
     * 
     * @param id 网点id
     * @param req
     * @return
     */
    @RequestMapping(value = "register", params = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson registerDel(@RequestParam(value = "id", required = true) Long id, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            sucess = dotService.registerDeleteMain(id);
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
     * @Title:选择网点
     * @param excludeIds
     * @param singleSelect
     * @param request
     * @return
     */
    @RequestMapping(value = "select", params = "oneOrMult", method = RequestMethod.GET)
    public ModelAndView selectOneOrMult(@RequestParam(value = "excludeIds", required = true) String excludeIds,
            @RequestParam(value = "singleSelect", required = true) boolean singleSelect, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("gisplan/dot/selectOneOrMult_dialog");
        if (!singleSelect)
            singleSelect = false;
        mv.addObject("singleSelect", singleSelect);
        mv.addObject("excludeIds", excludeIds);
        return mv;
    }

    /**
     * @Title:选择网点datagrid数据
     * @param excludeIds
     * @param request
     * @param response
     * @param dataGrid
     */
    @RequestMapping(value = "select", params = "oneOrMultDatagrid", method = RequestMethod.POST)
    @ResponseBody
    public void selectOneDatagrid(String excludeIds, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        dotService.getOneOrMultList(excludeIds, dataGrid);
        TagUtil.datagrid(response, dataGrid);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
