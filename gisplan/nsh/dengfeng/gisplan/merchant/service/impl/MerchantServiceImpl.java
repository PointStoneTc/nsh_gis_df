package nsh.dengfeng.gisplan.merchant.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jodd.bean.BeanUtil;
import nsh.dengfeng.gisplan.dot.service.DotServiceI;
import nsh.dengfeng.gisplan.merchant.entity.Merchant;
import nsh.dengfeng.gisplan.merchant.entity.MerchantDot;
import nsh.dengfeng.gisplan.merchant.service.MerchantServiceI;
import nsh.dengfeng.gisplan.merchant.view.MerchantDotView;
import nsh.dengfeng.gisplan.merchant.view.MerchantRegisterListView;
import nsh.dengfeng.gisplan.merchant.view.MerchantView;
import nsh.dengfeng.gisplan.product.service.ProductServiceI;
import nsh.dengfeng.gisplan.product.view.MerchantBankProductView;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreKeyEnum;
import nsh.dengfeng.gisplan.storeGeometry.service.StoreGeometryServiceI;

@Service("merchantService")
@Transactional
public class MerchantServiceImpl extends CommonServiceImpl implements MerchantServiceI {
    @Autowired
    private DotServiceI dotService;
    
    @Autowired
    private ProductServiceI productService;
    @Autowired
    private StoreGeometryServiceI storeGeometryService;

    @Override
    public void getRegisterList(MerchantRegisterListView merchantRegisterListView, Long dotId, DataGrid dataGrid) {
        StringBuffer sql = new StringBuffer(
                "select m.id, m.code, m.name, m.address, m.charge_person, m.phone, m.type, m.management_type, m.management_range, m.account_manager, m.expansion_time, g.longitude, g.latitude ")
                        .append("from gp_merchant m ").append("left join gp_store_geometry g on m.id = g.fk_id and g.fk_key = '").append(StoreKeyEnum.SH.getKey()).append("' ")
                        .append("where m.is_delete = '0'");

        if (oConvertUtils.isNotEmpty(merchantRegisterListView.getName())) // 名称
            sql.append(" and m.name like '%").append(merchantRegisterListView.getName()).append("%'");

        if (null != dotId)
            sql.append(" and m.id in(select merchant_id from gp_merchant_dot where dot_id = ").append(dotId).append(")");

        if (oConvertUtils.isNotEmpty(merchantRegisterListView.getTypeCode())) // 商户类型
            sql.append(" and m.type_code = '").append(merchantRegisterListView.getTypeCode()).append("'");

        if (oConvertUtils.isNotEmpty(merchantRegisterListView.getManagementTypeCode())) // 经营大类
            sql.append(" and m.management_type_code = '").append(merchantRegisterListView.getManagementTypeCode()).append("'");

        if (oConvertUtils.isNotEmpty(merchantRegisterListView.getManagementRangeCode())) // 经营范围
            sql.append(" and m.management_range_code = '").append(merchantRegisterListView.getManagementRangeCode()).append("'");

        Long count = getCountForJdbc("select count(1) from (" + sql.toString() + ") t");
        sql.append(" ORDER BY m.create_date DESC");
        List<Map<String, Object>> rs = findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());
        List<MerchantRegisterListView> list = new ArrayList<MerchantRegisterListView>();
        for (Map<String, Object> rsMap : rs) {
            MerchantRegisterListView mr = new MerchantRegisterListView();
            BeanUtil.setProperty(mr, "id", rsMap.get("id"));
            BeanUtil.setProperty(mr, "code", rsMap.get("code"));
            BeanUtil.setProperty(mr, "name", rsMap.get("name"));
            BeanUtil.setProperty(mr, "address", rsMap.get("address"));
            BeanUtil.setProperty(mr, "chargePerson", rsMap.get("charge_person"));
            BeanUtil.setProperty(mr, "phone", rsMap.get("phone"));
            BeanUtil.setProperty(mr, "type", rsMap.get("type"));
            BeanUtil.setProperty(mr, "managementType", rsMap.get("management_type"));
            BeanUtil.setProperty(mr, "managementRange", rsMap.get("management_range"));
            BeanUtil.setProperty(mr, "accountManager", rsMap.get("account_manager"));
            BeanUtil.setProperty(mr, "expansionTime", rsMap.get("expansion_time"));
            BeanUtil.setProperty(mr, "longitude", rsMap.get("longitude"));
            BeanUtil.setProperty(mr, "latitude", rsMap.get("latitude"));
            list.add(mr);
        }

        dataGrid.setTotal(count.intValue());
        dataGrid.setResults(list);
    }

    @Override
    public MerchantView getRegisterView(Long id) {
        MerchantView merchantView = new MerchantView();
        Merchant merchant = getEntity(Merchant.class, id);
        StoreGeometry sg = storeGeometryService.findByFk(id, StoreKeyEnum.SH.getKey());
        List<MerchantDotView> dots = dotService.findByMerchantId(id);
        List<MerchantBankProductView> products = productService.findByMerchantId(id);
        merchantView.setMerchant(merchant);
        merchantView.setSg(sg);
        merchantView.setDots(dots);
        merchantView.setProducts(products);
        return merchantView;
    }

    @Override
    public boolean registerSaveMain(MerchantView merchantView, String dotIds) throws Exception {
        // 1.保存商家
        merchantView.getMerchant().setIsDelete("0");
        save(merchantView.getMerchant());

        // 2.保存坐标
        if (merchantView.getSg().getLongitude() != null && merchantView.getSg().getLatitude() != null) {
            merchantView.getSg().setFkId(merchantView.getMerchant().getId());
            merchantView.getSg().setFkKey((StoreKeyEnum.SH.getKey()));
            storeGeometryService.saveMain(merchantView.getSg());
        }

        // 3.保存银行
        if (dotIds.length() > 0) {
            List<MerchantDot> dotList = new ArrayList<MerchantDot>();
            for (String id : dotIds.split(",")) {
                MerchantDot md = new MerchantDot();
                md.setMerchantId(merchantView.getMerchant().getId());
                md.setDotId(Long.valueOf(id));
                dotList.add(md);
            }
            batchSave(dotList);
        }

        return true;
    }

    @Override
    public boolean registerUpdateMain(MerchantView merchantView, String dotIds) throws Exception {
        // 1.保存商家
        Merchant m = get(Merchant.class, merchantView.getMerchant().getId());
        MyBeanUtils.copyBeanNotNull2Bean(merchantView.getMerchant(), m);
        saveOrUpdate(m);

        // 2.保存坐标
        if (merchantView.getSg().getLongitude() != null && merchantView.getSg().getLatitude() != null) {
            storeGeometryService.updateMain(merchantView.getSg());
        }

        // 2.保存银行
        StringBuffer sql = new StringBuffer();
        if (dotIds.length() == 0) { // 删除所有数据
            sql.append("delete from gp_merchant_dot where merchant_id = ?");
            executeSql(sql.toString(), merchantView.getMerchant().getId());
        } else { // 先新增，后删除
            sql.append("insert into gp_merchant_dot (merchant_id, dot_id) ").append("select ").append(merchantView.getMerchant().getId())
                    .append(" , id from gp_dot where id in(").append(dotIds).append(") ")
                    .append("and id not in(select dot_id from gp_merchant_dot where merchant_id = ?)");
            executeSql(sql.toString(), merchantView.getMerchant().getId());

            sql.setLength(0);
            sql.append("delete from gp_merchant_dot where dot_id not in(").append(dotIds).append(") and merchant_id = ?");
            executeSql(sql.toString(), merchantView.getMerchant().getId());
        }

        return true;
    }

    @Override
    public boolean registerDeleteMain(Long id) throws Exception {
        String sql = "update gp_merchant set is_delete = '1', delete_date = NOW() where id = ?";
        Integer i = executeSql(sql, id);
        return i.intValue() == 1 ? true : false;
    }

}
