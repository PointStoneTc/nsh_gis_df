package nsh.dengfeng.gisplan.selfservice.service.impl;

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
import nsh.dengfeng.gisplan.selfservice.entity.Selfservice;
import nsh.dengfeng.gisplan.selfservice.service.SelfserviceServiceI;
import nsh.dengfeng.gisplan.selfservice.view.SelfserviceListView;
import nsh.dengfeng.gisplan.selfservice.view.SelfserviceView;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreKeyEnum;
import nsh.dengfeng.gisplan.storeGeometry.service.StoreGeometryServiceI;

@Service("selfserviceService")
@Transactional
public class SelfserviceServiceImpl extends CommonServiceImpl implements SelfserviceServiceI {
    @Autowired
    private StoreGeometryServiceI storeGeometryService;

    @Override
    public void getRegisterList(SelfserviceListView selfserviceListView, DataGrid dataGrid) {
        StringBuffer sql = new StringBuffer(
                "select s.id, s.dot_id, s.code, s.name, s.address, s.type, g.longitude, g.latitude, gp.longitude parent_longitude, gp.latitude parent_latitude, d.name dot_name ")
                        .append("from gp_selfservice s ").append("left join gp_store_geometry g on s.id = g.fk_id and g.fk_key = '").append(StoreKeyEnum.ZZSB.getKey()).append("' ")
                        .append("left join gp_store_geometry gp on s.dot_id = gp.fk_id and gp.fk_key = '").append(StoreKeyEnum.WD.getKey()).append("' ")
                        .append("join gp_dot d on d.id = s.dot_id ").append("where s.is_delete = '0' and d.is_delete = '0'");

        if (oConvertUtils.isNotEmpty(selfserviceListView.getTypeCode())) // 自助设备类型
            sql.append(" and s.type_code = '").append(selfserviceListView.getTypeCode()).append("'");

        if (oConvertUtils.isNotEmpty(selfserviceListView.getDotId())) // 所属支行
            sql.append(" and s.dot_id = '").append(selfserviceListView.getDotId()).append("'");

        Long count = getCountForJdbc("select count(1) from (" + sql.toString() + ") t");
        sql.append(" ORDER BY s.create_date DESC");
        List<Map<String, Object>> rs = findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());
        List<SelfserviceListView> list = new ArrayList<SelfserviceListView>();
        for (Map<String, Object> rsMap : rs) {
            SelfserviceListView ssv = new SelfserviceListView();
            BeanUtil.setProperty(ssv, "id", rsMap.get("id"));
            BeanUtil.setProperty(ssv, "dotId", rsMap.get("dot_id"));
            BeanUtil.setProperty(ssv, "code", rsMap.get("code"));
            BeanUtil.setProperty(ssv, "name", rsMap.get("name"));
            BeanUtil.setProperty(ssv, "address", rsMap.get("address"));
            BeanUtil.setProperty(ssv, "type", rsMap.get("type"));
            BeanUtil.setProperty(ssv, "dotName", rsMap.get("dot_name"));
            BeanUtil.setProperty(ssv, "longitude", rsMap.get("longitude"));
            BeanUtil.setProperty(ssv, "latitude", rsMap.get("latitude"));
            BeanUtil.setProperty(ssv, "parentLongitude", rsMap.get("parent_longitude"));
            BeanUtil.setProperty(ssv, "parentLatitude", rsMap.get("parent_latitude"));
            list.add(ssv);
        }

        dataGrid.setTotal(count.intValue());
        dataGrid.setResults(list);
    }

    @Override
    public SelfserviceView getRegisterView(Long id) {
        SelfserviceView selfserviceView = new SelfserviceView();
        Selfservice selfservice = getEntity(Selfservice.class, id);
        StoreGeometry sg = storeGeometryService.findByFk(id, StoreKeyEnum.ZZSB.getKey());
        selfserviceView.setSelfservice(selfservice);
        selfserviceView.setSg(sg);
        return selfserviceView;
    }

    @Override
    public boolean registerSaveMain(SelfserviceView selfserviceView) throws Exception {
        // 1.保存自助设备
        selfserviceView.getSelfservice().setIsDelete("0");
        save(selfserviceView.getSelfservice());

        // 2.保存坐标
        if (selfserviceView.getSg().getLongitude() != null && selfserviceView.getSg().getLatitude() != null) {
            selfserviceView.getSg().setFkId(selfserviceView.getSelfservice().getId());
            selfserviceView.getSg().setFkKey((StoreKeyEnum.ZZSB.getKey()));
            storeGeometryService.saveMain(selfserviceView.getSg());
        }

        return true;
    }

    @Override
    public boolean registerUpdateMain(SelfserviceView selfserviceView) throws Exception {
        // 1.保存自助设备
        Selfservice m = get(Selfservice.class, selfserviceView.getSelfservice().getId());
        MyBeanUtils.copyBeanNotNull2Bean(selfserviceView.getSelfservice(), m);
        saveOrUpdate(m);

        // 2.保存坐标
        if (selfserviceView.getSg().getLongitude() != null && selfserviceView.getSg().getLatitude() != null) {
            StoreGeometry g = get(StoreGeometry.class, selfserviceView.getSg().getId());
            storeGeometryService.copyProperties(selfserviceView.getSg(), g);
            storeGeometryService.updateMain(g);
        }

        return true;
    }

    @Override
    public boolean registerDeleteMain(Long id) throws Exception {
        String sql = "update gp_dot set is_delete = '1', delete_date = NOW() where id = ?";
        Integer i = executeSql(sql, id);
        return i.intValue() == 1 ? true : false;
    }
}
