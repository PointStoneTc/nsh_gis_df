package nsh.dengfeng.gisplan.dot.service;

import java.util.List;

import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;

import nsh.dengfeng.gisplan.dot.entity.Dot;
import nsh.dengfeng.gisplan.dot.view.DotRegisterListView;
import nsh.dengfeng.gisplan.dot.view.DotView;
import nsh.dengfeng.gisplan.merchant.view.MerchantDotView;

public interface DotServiceI extends CommonService {
    /**
     * @Title:网点列表
     * 
     * @param dotRegisterListView
     * @param dataGrid
     */
    void getRegisterList(DotRegisterListView dotRegisterListView, DataGrid dataGrid);

    /**
     * @Title:网点详情
     * 
     * @param id 网点id
     * @return
     * @throws Exception
     */
    DotView getRegisterView(Long id);

    /**
     * @Title:保存网点
     * 
     * @param dotView
     * @return
     * @throws Exception
     */
    boolean registerSaveMain(DotView dotView) throws Exception;

    /**
     * @Title:更新网点
     * 
     * @param dotView
     * @return
     * @throws Exception
     */
    boolean registerUpdateMain(DotView dotView) throws Exception;

    /**
     * @Title:删除网点
     * 
     * @param id 合同id
     * @return
     * @throws Exception
     */
    boolean registerDeleteMain(Long id) throws Exception;

    /**
     * @Title:查找所有网点(除便民服务点)
     * 
     * @return
     * @throws Exception
     */
    List<Dot> findAllDot();

    /**
     * @Title:获取所有的网点
     * 
     * @return
     */
    List<DotRegisterListView> findAllList();

    /**
     * @Title:根据商家id获取所有的商家
     * 
     * @param merchantId
     * @return
     */
    List<MerchantDotView> findByMerchantId(Long merchantId);

    /**
     * @Title:选择网点datagrid
     * 
     * @param excludeIds
     * @param dataGrid
     */
    void getOneOrMultList(String excludeIds, DataGrid dataGrid);
}
