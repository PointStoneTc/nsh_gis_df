package nsh.dengfeng.gisplan.analysis.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import nsh.dengfeng.gisplan.analysis.entity.DotMerchantCount;
import nsh.dengfeng.gisplan.analysis.entity.ProductMerchantCount;
import nsh.dengfeng.gisplan.analysis.view.BigScreen;
import nsh.dengfeng.gisplan.product.entity.BusinessEnum;

public interface AnalysisServiceI extends CommonService {
    /**
     * 组装大屏数据
     * 
     * @return
     */
    public BigScreen packagBigScreen();

    /**
     * @Title: 按网点类型查询所有的商户数量
     * 
     * @param type 网点类型
     * @return
     */
    public List<DotMerchantCount> findDotMerchantCount(String type);

    /**
     * @Title: 按产品大类查询使用的商户数量
     * 
     * @return
     */
    public List<ProductMerchantCount> findProductMerchantCountByBusiness();


    /**
     * @Title: 按产品小类查询使用的商户数量
     * @return
     */
    public Map<BusinessEnum, List<ProductMerchantCount>> findProductMerchantCountByName();

}
