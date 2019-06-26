package nsh.dengfeng.gisplan.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jodd.bean.BeanUtil;
import nsh.dengfeng.gisplan.product.entity.BankProduct;
import nsh.dengfeng.gisplan.product.entity.MerchantBankProduct;
import nsh.dengfeng.gisplan.product.entity.Product;
import nsh.dengfeng.gisplan.product.service.ProductServiceI;
import nsh.dengfeng.gisplan.product.view.BankProductView;
import nsh.dengfeng.gisplan.product.view.MerchantBankProductView;

@Service("productService")
@Transactional
public class ProductServiceImpl extends CommonServiceImpl implements ProductServiceI {
    @Override
    public void getRegisterList(Product product, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(Product.class, dataGrid);
        // 查询条件组装器
        if (oConvertUtils.isNotEmpty(product.getBusinessCode()))
            cq.eq("businessCode", product.getBusinessCode());
        cq.eq("isDelete", "0");

        cq.addOrder("createDate", SortDirection.desc);
        cq.add();
        this.getDataGridReturn(cq, true);
    }

    @Override
    public boolean registerSaveMain(Product product) throws Exception {
        product.setIsDelete("0");
        product.setIsCommit("0");
        save(product);
        return true;
    }

    @Override
    public boolean registerUpdateMain(Product product) throws Exception {
        Product c = get(Product.class, product.getId());
        c.setEffectDate(product.getEffectDate());
        c.setInvalidDate(product.getInvalidDate());
        MyBeanUtils.copyBeanNotNull2Bean(product, c);
        saveOrUpdate(c);
        return true;
    }

    @Override
    public boolean registerDeleteMain(Long id) throws Exception {
        String sql = "update gp_product set is_delete = '1', delete_date = NOW() where id = ?";
        Integer i = executeSql(sql, id);
        return i.intValue() == 1 ? true : false;
    }

    @Override
    public boolean registerCommitMain(Long id) {
        String sql = "update gp_product set is_commit = '1' where id = ?";
        Integer i = executeSql(sql, id);
        return i.intValue() == 1 ? true : false;
    }

    @Override
    public void getBankList(Long dotId, DataGrid dataGrid) {
        List<BankProductView> list = new ArrayList<BankProductView>();
        if (null != dotId) {
            StringBuffer sql = new StringBuffer("select b.id, b.product_id, p.name, p.business, p.is_effect, p.effect_date, p.invalid_date ")
                    .append(" from gp_bank_product b INNER JOIN gp_product p ").append(" ON b.product_id = p.id WHERE p.is_delete = '0' and b.dot_id = ?");
            List<Map<String, Object>> rs = findForJdbc(sql.toString(), dotId);
            for (Map<String, Object> rsMap : rs) {
                BankProductView bpv = new BankProductView();
                BeanUtil.setProperty(bpv, "id", rsMap.get("id"));
                BeanUtil.setProperty(bpv, "productId", rsMap.get("product_id"));
                BeanUtil.setProperty(bpv, "name", rsMap.get("name"));
                BeanUtil.setProperty(bpv, "business", rsMap.get("business"));
                BeanUtil.setProperty(bpv, "isEffect", rsMap.get("is_effect"));
                BeanUtil.setProperty(bpv, "effectDate", rsMap.get("effect_date"));
                BeanUtil.setProperty(bpv, "invalidDate", rsMap.get("invalid_date"));
                list.add(bpv);
            }
        }
        dataGrid.setResults(list);
    }

    @Override
    public boolean bankSave(Long dotId, String productIds) throws Exception {
        List<BankProduct> productList = new ArrayList<BankProduct>();
        for (String pid : productIds.split(",")) {
            BankProduct bp = new BankProduct();
            bp.setDotId(dotId);
            bp.setProductId(Long.valueOf(pid));
            productList.add(bp);
        }
        batchSave(productList);

        return true;
    }

    @Override
    public boolean bankDelete(Long id) throws Exception {
        String sql = "delete from gp_bank_product where id = ?";
        executeSql(sql, id);
        return true;
    }

    @Override
    public void getBankOneOrMultList(String excludeIds, DataGrid dataGrid) {
        StringBuffer hql = new StringBuffer("from Product p where p.isDelete = '0' and isEffect = '1' and isCommit = '1'");
        if (oConvertUtils.isNotEmpty(excludeIds))
            hql.append(" and id not in(").append(excludeIds).append(")");
        List<Product> productList = this.findHql(hql.toString());
        dataGrid.setResults(productList);
        dataGrid.setRows(productList.size());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BankProductView> findByDotId(Long dotId) {
        DataGrid dataGrid = new DataGrid();
        getBankList(dotId, dataGrid);
        return dataGrid.getResults();
    }

    @Override
    public void getMerchantList(Long merchantId, DataGrid dataGrid) {
        List<MerchantBankProductView> list = new ArrayList<MerchantBankProductView>();
        if (null != merchantId) {
            StringBuffer sql =
                    new StringBuffer("select m.id, m.bank_product_id, b.product_id, p.name, p.business, p.is_effect, p.effect_date, p.invalid_date, d.id dot_id, d.name dot_name ")
                            .append(" from gp_merchant_bank_product m INNER JOIN gp_bank_product b ON m.bank_product_id = b.id ")
                            .append("INNER JOIN gp_product p ON b.product_id = p.id ").append("INNER JOIN gp_dot d ON b.dot_id = d.id ")
                            .append("WHERE p.is_delete = '0' and p.is_effect = '1' and m.merchant_id = ? order by b.dot_id, p.business_code");
            List<Map<String, Object>> rs = findForJdbc(sql.toString(), merchantId);
            for (Map<String, Object> rsMap : rs) {
                MerchantBankProductView mbpv = new MerchantBankProductView();
                BeanUtil.setProperty(mbpv, "id", rsMap.get("id"));
                BeanUtil.setProperty(mbpv, "bankProductId", rsMap.get("bank_product_id"));
                BeanUtil.setProperty(mbpv, "dotId", rsMap.get("dot_id"));
                BeanUtil.setProperty(mbpv, "dotName", rsMap.get("dot_name"));
                BeanUtil.setProperty(mbpv, "productId", rsMap.get("product_id"));
                BeanUtil.setProperty(mbpv, "name", rsMap.get("name"));
                BeanUtil.setProperty(mbpv, "business", rsMap.get("business"));
                BeanUtil.setProperty(mbpv, "isEffect", rsMap.get("is_effect"));
                BeanUtil.setProperty(mbpv, "effectDate", rsMap.get("effect_date"));
                BeanUtil.setProperty(mbpv, "invalidDate", rsMap.get("invalid_date"));
                list.add(mbpv);
            }
        }
        dataGrid.setResults(list);

    }

    @Override
    public boolean merchantSave(Long merchantId, String bankProductIds) throws Exception {
        List<MerchantBankProduct> productList = new ArrayList<MerchantBankProduct>();
        for (String bpid : bankProductIds.split(",")) {
            MerchantBankProduct mbp = new MerchantBankProduct();
            mbp.setMerchantId(merchantId);
            mbp.setBankProductId(Long.valueOf(bpid));
            productList.add(mbp);
        }
        batchSave(productList);

        return true;
    }

    @Override
    public boolean merchantDelete(Long id) throws Exception {
        String sql = "delete from gp_merchant_bank_product where id = ?";
        executeSql(sql, id);
        return true;
    }

    @Override
    public void getMerchantOneOrMultList(String excludeIds, Long merchantId, DataGrid dataGrid) {
        StringBuffer sql = new StringBuffer("select bp.id, p.id product_id, d.name dot_name, p.name, p.business, p.is_effect, p.effect_date, p.invalid_date ")
                .append(" from gp_bank_product bp JOIN gp_product p ON bp.product_id = p.id ").append(" JOIN gp_dot d ON bp.dot_id = d.id ")
                .append("where p.is_delete = '0' and is_effect = '1' and is_commit = '1' and bp.dot_id in(").append("select dot_id from gp_merchant_dot where merchant_id = ?)");
        if (oConvertUtils.isNotEmpty(excludeIds))
            sql.append(" and bp.id not in(").append(excludeIds).append(")");

        sql.append(" order by bp.dot_id, p.business_code");

        List<BankProductView> productList = new ArrayList<BankProductView>();
        List<Map<String, Object>> rs = findForJdbc(sql.toString(), merchantId);
        for (Map<String, Object> rsMap : rs) {
            BankProductView bpv = new BankProductView();
            BeanUtil.setProperty(bpv, "id", rsMap.get("id"));
            BeanUtil.setProperty(bpv, "productId", rsMap.get("product_id"));
            BeanUtil.setProperty(bpv, "dotName", rsMap.get("dot_name"));
            BeanUtil.setProperty(bpv, "name", rsMap.get("name"));
            BeanUtil.setProperty(bpv, "business", rsMap.get("business"));
            BeanUtil.setProperty(bpv, "isEffect", rsMap.get("is_effect"));
            BeanUtil.setProperty(bpv, "effectDate", rsMap.get("effect_date"));
            BeanUtil.setProperty(bpv, "invalidDate", rsMap.get("invalid_date"));
            productList.add(bpv);
        }

        dataGrid.setRows(productList.size());
        dataGrid.setResults(productList);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MerchantBankProductView> findByMerchantId(Long merchantId) {
        DataGrid dataGrid = new DataGrid();
        getMerchantList(merchantId, dataGrid);
        return dataGrid.getResults();
    }

}
