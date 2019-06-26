package nsh.dengfeng.gisplan.storeGeometry.service;

import org.jeecgframework.core.common.service.CommonService;

import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;

public interface StoreGeometryServiceI extends CommonService {
    /**
     * @Title: 根据外键查询坐标对象
     * 
     * @param fkId 外键id
     * @param fkKey 外键编码
     * @return
     * @throws Exception
     */
    StoreGeometry findByFk(Long fkId, String fkKey);

    /**
     * @Title: 对象赋值
     * 
     * @param r 原对象
     * @param t 赋值对象
     */
    void copyProperties(StoreGeometry r, StoreGeometry t);

    /**
     * @Title: 新增坐标
     * 
     * @param sg
     * @return
     * @throws Exception
     */
    boolean saveMain(StoreGeometry sg) throws Exception;

    /**
     * @Title: 保存坐标
     * 
     * @param sg
     * @return
     * @throws Exception
     */
    boolean updateMain(StoreGeometry sg) throws Exception;

    /**
     * 
     * @Title:使用父亲的坐标
     * 
     * @param parentId 父亲的id
     * @param parentKey 父亲key
     * @param selfId 自己的id
     * @param selfKey 自己的key
     * @return
     * @throws Exception
     */
    boolean setParentCoordinate(Long parentId, String parentKey, Long selfId, String selfKey) throws Exception;
}
