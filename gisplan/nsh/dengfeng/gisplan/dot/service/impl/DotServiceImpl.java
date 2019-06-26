package nsh.dengfeng.gisplan.dot.service.impl;

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
import nsh.dengfeng.gisplan.dot.entity.Dot;
import nsh.dengfeng.gisplan.dot.service.DotServiceI;
import nsh.dengfeng.gisplan.dot.view.DotRegisterListView;
import nsh.dengfeng.gisplan.dot.view.DotView;
import nsh.dengfeng.gisplan.merchant.view.MerchantDotView;
import nsh.dengfeng.gisplan.product.service.ProductServiceI;
import nsh.dengfeng.gisplan.product.view.BankProductView;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreKeyEnum;
import nsh.dengfeng.gisplan.storeGeometry.service.StoreGeometryServiceI;

@Service("dotService")
@Transactional
public class DotServiceImpl extends CommonServiceImpl implements DotServiceI {
    @Autowired
    private ProductServiceI productService;
    @Autowired
    private StoreGeometryServiceI storeGeometryService;

    @Override
    public void getRegisterList(DotRegisterListView dotRegisterListView, DataGrid dataGrid) {
        StringBuffer sql = new StringBuffer("select d.id, d.code, d.name, d.address, d.charge_person, d.phone, d.type, d.gateway_in_ip, d.gateway_out_ip, g.longitude, g.latitude ")
                .append("from gp_dot d left join gp_store_geometry g on d.id = g.fk_id and g.fk_key = '").append(StoreKeyEnum.WD.getKey()).append("' ")
                .append("where d.is_delete = '0'");

        if (oConvertUtils.isNotEmpty(dotRegisterListView.getName())) // 名称
            sql.append(" and d.name like '%").append(dotRegisterListView.getName()).append("%'");

        if (oConvertUtils.isNotEmpty(dotRegisterListView.getTypeCode())) // 网点类型
            sql.append(" and d.type_code = '").append(dotRegisterListView.getTypeCode()).append("'");

        Long count = getCountForJdbc("select count(1) from (" + sql.toString() + ") t");
        sql.append(" ORDER BY d.code");
        List<Map<String, Object>> rs = findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());
        List<DotRegisterListView> list = new ArrayList<DotRegisterListView>();
        for (Map<String, Object> rsMap : rs) {
            DotRegisterListView dr = new DotRegisterListView();
            BeanUtil.setProperty(dr, "id", rsMap.get("id"));
            BeanUtil.setProperty(dr, "code", rsMap.get("code"));
            BeanUtil.setProperty(dr, "name", rsMap.get("name"));
            BeanUtil.setProperty(dr, "address", rsMap.get("address"));
            BeanUtil.setProperty(dr, "chargePerson", rsMap.get("charge_person"));
            BeanUtil.setProperty(dr, "phone", rsMap.get("phone"));
            BeanUtil.setProperty(dr, "type", rsMap.get("type"));
            BeanUtil.setProperty(dr, "gatewayInIp", rsMap.get("gateway_in_ip"));
            BeanUtil.setProperty(dr, "gatewayOutIp", rsMap.get("gateway_out_ip"));
            BeanUtil.setProperty(dr, "longitude", rsMap.get("longitude"));
            BeanUtil.setProperty(dr, "latitude", rsMap.get("latitude"));
            list.add(dr);
        }

        dataGrid.setTotal(count.intValue());
        dataGrid.setResults(list);
    }

    @Override
    public DotView getRegisterView(Long id) {
        DotView dotView = new DotView();
        Dot dot = getEntity(Dot.class, id);
        StoreGeometry sg = storeGeometryService.findByFk(id, StoreKeyEnum.WD.getKey());
        List<BankProductView> products = productService.findByDotId(id);
        dotView.setDot(dot);
        dotView.setSg(sg);
        dotView.setProducts(products);
        return dotView;
    }

    @Override
    public boolean registerSaveMain(DotView dotView) throws Exception {
        // 1.保存网点
        dotView.getDot().setIsDelete("0");
        save(dotView.getDot());

        // 2.保存坐标
        if (dotView.getSg().getLongitude() != null && dotView.getSg().getLatitude() != null) {
            dotView.getSg().setFkId(dotView.getDot().getId());
            dotView.getSg().setFkKey((StoreKeyEnum.WD.getKey()));
            storeGeometryService.saveMain(dotView.getSg());
        }

        return true;
    }

    @Override
    public boolean registerUpdateMain(DotView dotView) throws Exception {
        // 1.保存网点
        Dot m = get(Dot.class, dotView.getDot().getId());
        MyBeanUtils.copyBeanNotNull2Bean(dotView.getDot(), m);
        saveOrUpdate(m);

        // 2.保存坐标
        if (dotView.getSg().getLongitude() != null && dotView.getSg().getLatitude() != null) {
            storeGeometryService.updateMain(dotView.getSg());
        }

        return true;
    }

    @Override
    public boolean registerDeleteMain(Long id) throws Exception {
        String sql = "update gp_dot set is_delete = '1', delete_date = NOW() where id = ?";
        Integer i = executeSql(sql, id);
        return i.intValue() == 1 ? true : false;
    }

    @Override
    public List<Dot> findAllDot() {
        String hql = "from Dot t where (t.typeCode = 'z' or t.typeCode = 'f' or t.typeCode = 's') and isDelete = '0' order by code";
        List<Dot> list = findHql(hql);
        return list;
    }

    @Override
    public List<DotRegisterListView> findAllList() {
        StringBuffer sql = new StringBuffer();
        for (StoreKeyEnum ske : StoreKeyEnum.values()) {
            sql.append("select d.id, d.name, d.type, g.longitude, g.latitude from gp_dot d join gp_store_geometry g on d.id = g.fk_id and g.fk_key = '").append(ske.getKey())
                    .append("' where d.is_delete = '0' ").append("union ");
        }

        sql.delete(sql.lastIndexOf("union "), sql.length() - 1);

        List<Map<String, Object>> rs = findForJdbc(sql.toString());
        List<DotRegisterListView> list = new ArrayList<DotRegisterListView>();
        for (Map<String, Object> rsMap : rs) {
            DotRegisterListView dr = new DotRegisterListView();
            BeanUtil.setProperty(dr, "id", rsMap.get("id"));
            BeanUtil.setProperty(dr, "name", rsMap.get("name"));
            BeanUtil.setProperty(dr, "type", rsMap.get("type"));
            BeanUtil.setProperty(dr, "longitude", rsMap.get("longitude"));
            BeanUtil.setProperty(dr, "latitude", rsMap.get("latitude"));
            list.add(dr);
        }

        return list;
    }

    @Override
    public List<MerchantDotView> findByMerchantId(Long merchantId) {
        StringBuffer sql = new StringBuffer("select m.id, m.merchant_id, m.dot_id, d.name dot_name from gp_merchant_dot m, gp_dot d ")
                .append("where m.dot_id = d.id and d.is_delete = '0' and m.merchant_id = ?");
        List<Map<String, Object>> rs = findForJdbc(sql.toString(), merchantId);
        List<MerchantDotView> list = new ArrayList<MerchantDotView>();
        for (Map<String, Object> rsMap : rs) {
            MerchantDotView md = new MerchantDotView();
            BeanUtil.setProperty(md, "id", rsMap.get("id"));
            BeanUtil.setProperty(md, "merchantId", rsMap.get("merchant_id"));
            BeanUtil.setProperty(md, "dotId", rsMap.get("dot_id"));
            BeanUtil.setProperty(md, "dotName", rsMap.get("dot_name"));
            list.add(md);
        }
        return list;
    }

    @Override
    public void getOneOrMultList(String excludeIds, DataGrid dataGrid) {
        StringBuffer hql = new StringBuffer("from Dot p where p.isDelete = '0'");
        if (oConvertUtils.isNotEmpty(excludeIds))
            hql.append(" and id not in(").append(excludeIds).append(")");
        List<Dot> dotList = this.findHql(hql.toString());
        dataGrid.setResults(dotList);
        dataGrid.setRows(dotList.size());
    }
}
