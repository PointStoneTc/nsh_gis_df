package nsh.dengfeng.gisplan.merchant.service;

import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;

import nsh.dengfeng.gisplan.merchant.view.MerchantRegisterListView;
import nsh.dengfeng.gisplan.merchant.view.MerchantView;

public interface MerchantServiceI extends CommonService {
    /**
     * @Title:商家列表
     * 
     * @param merchantRegisterListView
     * @param dotId 网点id
     * @param dataGrid
     */
    void getRegisterList(MerchantRegisterListView merchantRegisterListView, Long dotId, DataGrid dataGrid);

    /**
     * @Title:商家详情页
     * 
     * @param id 商家id
     * @return
     */
    MerchantView getRegisterView(Long id);

    /**
     * @Title:保存商家
     * 
     * @param merchantView
     * @param dotIds
     * @return
     * @throws Exception
     */
    boolean registerSaveMain(MerchantView merchantView, String dotIds) throws Exception;

    /**
     * @Title:更新商家
     * 
     * @param merchantView
     * @param dotIds
     * @return
     * @throws Exception
     */
    boolean registerUpdateMain(MerchantView merchantView, String dotIds) throws Exception;

    /**
     * @Title:删除商家
     * 
     * @param id 商家id
     * @return
     * @throws Exception
     */
    boolean registerDeleteMain(Long id) throws Exception;

}
