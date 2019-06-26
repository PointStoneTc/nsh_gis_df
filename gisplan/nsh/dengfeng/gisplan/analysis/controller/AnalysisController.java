package nsh.dengfeng.gisplan.analysis.controller;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import nsh.dengfeng.gisplan.analysis.service.AnalysisServiceI;
import nsh.dengfeng.gisplan.analysis.view.BigScreen;

/**
 * @Title: Controller
 * @Description: 综合分析
 * @author 赵琦
 * @date 2019-03-24 22:49:00
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/analysis")
public class AnalysisController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);

    /*--------------------------------------------
    |             Variable                       |
    ============================================*/
    private String message;

    /*--------------------------------------------
    |             Injection                      |
    ============================================*/
    @Autowired
    private AnalysisServiceI analysisService;


    /*--------------------------------------------
    |             method                       |
    ============================================*/

    /**
     * @Title:大屏展示
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "bigscreen", params = "index", method = RequestMethod.GET)
    public ModelAndView bigscreenIndex(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("gisplan/anlysis/bigscreen_index");
        return mv;
    }

    /**
     * @Title:大屏展示获取数据
     * 
     * @param req
     * @return
     */
    @RequestMapping(value = "bigscreen", params = "getData", method = RequestMethod.GET)
    @ResponseBody
    public AjaxJson bigscreenGetData(HttpServletRequest req) {
        AjaxJson j = new AjaxJson();
        BigScreen bigScreen = analysisService.packagBigScreen();
        j.setObj(bigScreen);
        j.setSuccess(true);
        j.setMsg("组装数据成功!");
        logger.info("package sucess: " + bigScreen);
        return j;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
