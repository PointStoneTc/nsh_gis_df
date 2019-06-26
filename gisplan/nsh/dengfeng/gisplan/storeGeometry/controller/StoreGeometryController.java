package nsh.dengfeng.gisplan.storeGeometry.controller;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.oConvertUtils;
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

import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;
import nsh.dengfeng.gisplan.storeGeometry.service.StoreGeometryServiceI;

/**
 * @Title: Controller
 * @Description: 存储坐标
 * @author 赵琦
 * @date 2019-03-24 22:48:00
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/storeGeometry")
public class StoreGeometryController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(StoreGeometryController.class);

    /*--------------------------------------------
    |             Variable                       |
    ============================================*/
    private String message;

    /*--------------------------------------------
    |             Injection                      |
    ============================================*/
    @Autowired
    private StoreGeometryServiceI storeGeometryService;

    @Autowired
    private SystemService systemService;

    /*--------------------------------------------
    |             method                       |
    ============================================*/
    /**
     * @Title:坐标更新
     * @param id 外键id
     * @param key 外键类型
     * @param index 父表datagrid的行标
     * @param gridId 父表datagrid的DOM id
     * @param req
     * @return
     */
    @RequestMapping(params = "toUpdate", method = RequestMethod.GET)
    public ModelAndView toUpdate(@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "index", required = true) String index, @RequestParam(value = "gridId", required = true) String gridId, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/storeGeometry/view");
        StoreGeometry sg = storeGeometryService.findByFk(id, key);
        if (sg == null) {
            sg = new StoreGeometry();
            sg.setFkId(id);
            sg.setFkKey(key);
        }
        mv.addObject("index", index);
        mv.addObject("gridId", gridId);
        mv.addObject("sg", sg);
        return mv;
    }

    /**
     * @Title:坐标保存或更新
     * 
     * @param sg
     * @param req
     * @return
     */
    @RequestMapping(params = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson saveOrUpdate(StoreGeometry sg, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean sucess = false;
        try {
            if (oConvertUtils.isNotEmpty(sg.getId())) { // 更新
                sucess = storeGeometryService.updateMain(sg);
                j.setSuccess(sucess);
                j.setMsg("更新成功!");
                logger.info("update sucess: " + sg);
            } else {
                sucess = storeGeometryService.saveMain(sg);
                j.setSuccess(sucess);
                j.setMsg("保存成功!");
                logger.info("save sucess: " + sg);
            }
        } catch (Exception e) {
            j.setSuccess(sucess);
            j.setMsg("保存异常, 请联系管理员!");
            logger.error("saveOrUpdate error", e.getMessage());
        }
        return j;
    }

    /**
     * @Title:使用父亲的坐标
     * @param parentId 父亲的id
     * @param parentKey 父亲key
     * @param selfId 自己的id
     * @param selfKey 自己的key
     * @param req
     * @return
     */
    @RequestMapping(params = "setParentCoordinate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson setParentCoordinate(@RequestParam(value = "parentId", required = true) Long parentId, @RequestParam(value = "parentKey", required = true) String parentKey,
            @RequestParam(value = "selfId", required = true) Long selfId, @RequestParam(value = "selfKey", required = true) String selfKey, HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        boolean success = false;
        try {
            success = storeGeometryService.setParentCoordinate(parentId, parentKey, selfId, selfKey);
            j.setSuccess(success);
            j.setMsg("更新成功!");
            logger.info("update sucess:" + selfId);
        } catch (Exception e) {
            j.setSuccess(success);
            j.setMsg("更新异常, 请联系管理员!");
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
