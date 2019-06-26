package nsh.dengfeng.gisplan.bdmap.controller;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import nsh.dengfeng.gisplan.bdmap.service.BdMapServiceI;

/**
 * @Title: Controller
 * @Description: 地图辅助
 * @author 赵琦
 * @date 2019-03-27 18:35:00
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/bdMap")
public class BdMapController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BdMapController.class);

    /*--------------------------------------------
    |             Variable                       |
    ============================================*/
    private String message;

    /*--------------------------------------------
    |             Injection                      |
    ============================================*/
    @Autowired
    private BdMapServiceI bdMapService;

    /*--------------------------------------------
    |             method                       |
    ============================================*/

    /**
     * @Title:辅助画图,多边形
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "draw", params = "polygon", method = RequestMethod.GET)
    public ModelAndView drawPolygon(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/bdmap/draw_polygon");
        return mv;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
