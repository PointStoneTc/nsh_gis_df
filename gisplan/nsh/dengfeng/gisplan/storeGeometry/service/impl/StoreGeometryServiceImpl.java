package nsh.dengfeng.gisplan.storeGeometry.service.impl;

import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nsh.dengfeng.gisplan.storeGeometry.entity.CoordinateSysEnum;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;
import nsh.dengfeng.gisplan.storeGeometry.service.StoreGeometryServiceI;

@Service("storeGeometryService")
@Transactional
public class StoreGeometryServiceImpl extends CommonServiceImpl implements StoreGeometryServiceI {

    @Override
    public StoreGeometry findByFk(Long fkId, String fkKey) {
        String hql = "from StoreGeometry where fkId = ? and fkKey = ?";
        List<StoreGeometry> list = findHql(hql, fkId, fkKey);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void copyProperties(StoreGeometry r, StoreGeometry t) {
        t.setLongitude(r.getLongitude());
        t.setLatitude(r.getLatitude());
    }

    @Override
    public boolean saveMain(StoreGeometry sg) throws Exception {
        sg.setCoordinateSys(CoordinateSysEnum.BD09.getDesc());
        sg.setCoordinateSysCode(CoordinateSysEnum.BD09.getKey());
        save(sg);

        return true;
    }

    @Override
    public boolean updateMain(StoreGeometry sg) throws Exception {
        String sql = "update gp_store_geometry set longitude = ?, latitude = ? where fk_id = ? and fk_key = ?";
        Integer i = executeSql(sql, sg.getLongitude(), sg.getLatitude(), sg.getFkId(), sg.getFkKey());
        return i.intValue() == 1 ? true : false;
    }

    @Override
    public boolean setParentCoordinate(Long parentId, String parentKey, Long selfId, String selfKey) throws Exception {
        StoreGeometry parentSg = findByFk(parentId, parentKey);
        StoreGeometry selfSg = findByFk(selfId, selfKey);

        if (selfSg == null) {
            selfSg = new StoreGeometry();
            selfSg.setFkId(selfId);
            selfSg.setFkKey(selfKey);
            selfSg.setCoordinateSys(CoordinateSysEnum.BD09.getDesc());
            selfSg.setCoordinateSysCode(CoordinateSysEnum.BD09.getKey());
            selfSg.setLongitude(parentSg.getLongitude());
            selfSg.setLatitude(parentSg.getLatitude());
            save(selfSg);
        } else {
            selfSg.setLongitude(parentSg.getLongitude());
            selfSg.setLatitude(parentSg.getLatitude());
            saveOrUpdate(selfSg);
        }

        return true;
    }

}
