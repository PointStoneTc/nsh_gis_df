package nsh.dengfeng.gisplan.analysis.view;

import java.util.List;
import java.util.Map;

import nsh.dengfeng.gisplan.analysis.entity.DotMerchantCount;
import nsh.dengfeng.gisplan.analysis.entity.ProductMerchantCount;
import nsh.dengfeng.gisplan.dot.view.DotRegisterListView;
import nsh.dengfeng.gisplan.product.entity.BusinessEnum;
import nsh.dengfeng.gisplan.util.KeyValueObj;

/**
 * 大屏展示
 * 
 * @author 赵琦
 *
 */
public class BigScreen implements java.io.Serializable {
    private static final long serialVersionUID = 8804226913064504142L;
    private List<DotMerchantCount> dm_count; // 网点商家
    private List<DotRegisterListView> dot_list; // 所有的网点
    private List<ProductMerchantCount> pm_count_business; // 产品大类
    private Map<BusinessEnum, List<ProductMerchantCount>> pm_count_name; // 产品小类
    private List<KeyValueObj> dot_type_list; // 网点类型

    public BigScreen() {}

    public List<DotMerchantCount> getDm_count() {
        return dm_count;
    }

    public void setDm_count(List<DotMerchantCount> dm_count) {
        this.dm_count = dm_count;
    }

    public List<DotRegisterListView> getDot_list() {
        return dot_list;
    }

    public void setDot_list(List<DotRegisterListView> dot_list) {
        this.dot_list = dot_list;
    }

    public List<ProductMerchantCount> getPm_count_business() {
        return pm_count_business;
    }

    public void setPm_count_business(List<ProductMerchantCount> pm_count_business) {
        this.pm_count_business = pm_count_business;
    }

    public Map<BusinessEnum, List<ProductMerchantCount>> getPm_count_name() {
        return pm_count_name;
    }

    public void setPm_count_name(Map<BusinessEnum, List<ProductMerchantCount>> map) {
        this.pm_count_name = map;
    }

    public List<KeyValueObj> getDot_type_list() {
        return dot_type_list;
    }

    public void setDot_type_list(List<KeyValueObj> dot_type_list) {
        this.dot_type_list = dot_type_list;
    }

}
