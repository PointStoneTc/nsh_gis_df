package nsh.dengfeng.gisplan.selfservice.service;

import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;

import nsh.dengfeng.gisplan.selfservice.view.SelfserviceListView;
import nsh.dengfeng.gisplan.selfservice.view.SelfserviceView;

public interface SelfserviceServiceI extends CommonService {
    /**
     * @Title:银行自助设备列表
     * 
     * @param selfserviceListView
     * @param dataGrid
     */
    void getRegisterList(SelfserviceListView selfserviceListView, DataGrid dataGrid);

    /**
     * @Title:银行自助设备详情
     * 
     * @param id
     * @return
     */
    SelfserviceView getRegisterView(Long id);

    /**
     * @Title:保存银行自助设备
     * 
     * @param selfserviceView
     * @return
     * @throws Exception
     */
    boolean registerSaveMain(SelfserviceView selfserviceView) throws Exception;

    /**
     * @Title:更新银行自助设备
     * 
     * @param selfserviceView
     * @return
     * @throws Exception
     */
    boolean registerUpdateMain(SelfserviceView selfserviceView) throws Exception;

    /**
     * @Title:删除银行自助设备
     * 
     * @param id 自助设备id
     * @return
     * @throws Exception
     */
    boolean registerDeleteMain(Long id) throws Exception;
}
