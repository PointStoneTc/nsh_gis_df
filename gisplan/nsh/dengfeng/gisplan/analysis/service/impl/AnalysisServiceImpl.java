package nsh.dengfeng.gisplan.analysis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jodd.bean.BeanUtil;
import nsh.dengfeng.gisplan.analysis.entity.DotMerchantCount;
import nsh.dengfeng.gisplan.analysis.entity.ProductMerchantCount;
import nsh.dengfeng.gisplan.analysis.service.AnalysisServiceI;
import nsh.dengfeng.gisplan.analysis.view.BigScreen;
import nsh.dengfeng.gisplan.dot.entity.DotTypeEnum;
import nsh.dengfeng.gisplan.dot.service.DotServiceI;
import nsh.dengfeng.gisplan.product.entity.BusinessEnum;
import nsh.dengfeng.gisplan.util.KeyValueObj;

@Service("analysisService")
@Transactional
public class AnalysisServiceImpl extends CommonServiceImpl implements AnalysisServiceI {
    @Autowired
    private DotServiceI dotService;

    @Override
    public BigScreen packagBigScreen() {
        BigScreen bigScreen = new BigScreen();
        bigScreen.setDm_count(findDotMerchantCount(""));
        bigScreen.setDot_list(dotService.findAllList());
        bigScreen.setPm_count_business(findProductMerchantCountByBusiness());
        bigScreen.setPm_count_name(findProductMerchantCountByName());

        List<KeyValueObj> dotTypeList = new ArrayList<KeyValueObj>();
        for (DotTypeEnum dte : DotTypeEnum.values())
            dotTypeList.add(new KeyValueObj(dte.getKey(), dte.getDesc()));
        bigScreen.setDot_type_list(dotTypeList);

        return bigScreen;
    }

    @Override
    public List<DotMerchantCount> findDotMerchantCount(String type) {

        StringBuffer sql = new StringBuffer("select v.dot_id, v.dot_name, v.merchant_ct from vi_analysis_dot_merchant_count v where ");
        if (oConvertUtils.isEmpty(type)) {
            type = DotTypeEnum.ZH.getKey();
            sql.append("v.type_code = '").append(type).append("'");
            type = DotTypeEnum.ZSFLC.getKey();
            sql.append(" or v.type_code = '").append(type).append("' ");
        }

        sql.append("order by v.merchant_ct asc");
        List<Map<String, Object>> rs = findForJdbc(sql.toString());
        List<DotMerchantCount> list = new ArrayList<DotMerchantCount>();

        for (Map<String, Object> rsMap : rs) {
            DotMerchantCount dmc = new DotMerchantCount();
            BeanUtil.setProperty(dmc, "dotId", rsMap.get("dot_id"));
            BeanUtil.setProperty(dmc, "dotName", rsMap.get("dot_name"));
            BeanUtil.setProperty(dmc, "merchantCt", rsMap.get("merchant_ct"));
            list.add(dmc);
        }
        return list;
    }

    @Override
    public List<ProductMerchantCount> findProductMerchantCountByBusiness() {
        StringBuffer sql = new StringBuffer("select v.business, v.merchant_ct from vi_analysis_business_merchant_count v");
        List<Map<String, Object>> rs = findForJdbc(sql.toString());
        List<ProductMerchantCount> list = new ArrayList<ProductMerchantCount>();

        for (Map<String, Object> rsMap : rs) {
            ProductMerchantCount dmc = new ProductMerchantCount();
            BeanUtil.setProperty(dmc, "business", rsMap.get("business"));
            BeanUtil.setProperty(dmc, "merchantCt", rsMap.get("merchant_ct"));
            list.add(dmc);
        }
        return list;
    }

    @Override
    public Map<BusinessEnum, List<ProductMerchantCount>> findProductMerchantCountByName() {
        StringBuffer sql = new StringBuffer("select v.business_code, v.product_name, v.merchant_ct from vi_analysis_productname_merchant_count v");
        List<Map<String, Object>> rs = findForJdbc(sql.toString());
        Map<BusinessEnum, List<ProductMerchantCount>> map = new HashMap<BusinessEnum, List<ProductMerchantCount>>();

        for (BusinessEnum be : BusinessEnum.values())
            map.put(be, new ArrayList<ProductMerchantCount>());

        for (Map<String, Object> rsMap : rs) {
            ProductMerchantCount dmc = new ProductMerchantCount();
            BeanUtil.setProperty(dmc, "productName", rsMap.get("product_name"));
            BeanUtil.setProperty(dmc, "merchantCt", rsMap.get("merchant_ct"));

            String business_code = (String) rsMap.get("business_code");
            map.get(BusinessEnum.getEnumByKey(business_code)).add(dmc);
        }
        return map;
    }

}
