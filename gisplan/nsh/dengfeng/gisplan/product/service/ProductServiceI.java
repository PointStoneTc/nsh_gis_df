package nsh.dengfeng.gisplan.product.service;

import java.util.List;

import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;

import nsh.dengfeng.gisplan.product.entity.Product;
import nsh.dengfeng.gisplan.product.view.BankProductView;
import nsh.dengfeng.gisplan.product.view.MerchantBankProductView;

public interface ProductServiceI extends CommonService {
    /**
     * @Title:产品列表
     * 
     * @param product 产品
     * @param dataGrid
     */
    void getRegisterList(Product product, DataGrid dataGrid);

    /**
     * @Title:保存产品
     * 
     * @param product 产品
     * @return
     * @throws Exception
     */
    boolean registerSaveMain(Product product) throws Exception;

    /**
     * @Title:更新产品
     * 
     * @param product 产品
     * @return
     * @throws Exception
     */
    boolean registerUpdateMain(Product product) throws Exception;

    /**
     * @Title:删除产品
     * 
     * @param id 产品id
     * @return
     * @throws Exception
     */
    boolean registerDeleteMain(Long id) throws Exception;

    /**
     * @Title: 产品提交
     * 
     * @param id
     * @return
     */
    boolean registerCommitMain(Long id);

    /**
     * @Title:银行产品列表(查看网点银行所有产品)
     * 
     * @param dotId 网点id
     * @param dataGrid
     */
    void getBankList(Long dotId, DataGrid dataGrid);

    /**
     * @Title:保存银行产品(编辑网点时使用)
     * 
     * @param dotId 网点id
     * @param productIds 产品ids
     * @return
     * @throws Exception
     */
    boolean bankSave(Long dotId, String productIds) throws Exception;

    /**
     * @Title:删除银行产品(编辑网点时使用)
     * 
     * @param id 关系id
     * @return
     * @throws Exception
     */
    boolean bankDelete(Long id) throws Exception;

    /**
     * @Title:银行选择产品(编辑网点时使用,列出排除excludeIds之外的所有有效未选产品)
     * 
     * @param excludeIds 排除在外的产品id序列
     * @param dataGrid
     */
    void getBankOneOrMultList(String excludeIds, DataGrid dataGrid);

    /**
     * @Title:通过网点id获取此网点的所有产品信息
     * 
     * @param dotId 网点id
     * @return
     */
    List<BankProductView> findByDotId(Long dotId);

    /**
     * @Title:商户银行产品列表(查看商户银行所有产品)
     * 
     * @param dotId 网点id
     * @param dataGrid
     */
    void getMerchantList(Long merchantId, DataGrid dataGrid);

    /**
     * @Title:保存商户银行产品(编辑商户时使用)
     * 
     * @param merchantId 商户id
     * @param bankProductIds 银行产品ids
     * @return
     * @throws Exception
     */
    boolean merchantSave(Long merchantId, String bankProductIds) throws Exception;

    /**
     * @Title:删除商户银行产品(编辑商户时使用)
     * 
     * @param id 商户银行产品关系id
     * @return
     * @throws Exception
     */
    boolean merchantDelete(Long id) throws Exception;

    /**
     * @Title:商家选择银行产品(编辑网点时使用,列出排除excludeIds之外的所有有效未选产品)
     * 
     * @param excludeIds 排除在外的产品id序列
     * @param merchantId 商户id
     * @param dataGrid
     */
    void getMerchantOneOrMultList(String excludeIds, Long merchantId, DataGrid dataGrid);

    /**
     * @Title:通过商户id获取此商户的所有产品信息
     * 
     * @param id
     * @return
     */
    List<MerchantBankProductView> findByMerchantId(Long merchantId);
}
