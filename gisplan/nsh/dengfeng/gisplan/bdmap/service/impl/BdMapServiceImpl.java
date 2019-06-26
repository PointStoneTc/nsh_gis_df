package nsh.dengfeng.gisplan.bdmap.service.impl;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nsh.dengfeng.gisplan.bdmap.service.BdMapServiceI;

@Service("bdMapService")
@Transactional
public class BdMapServiceImpl extends CommonServiceImpl implements BdMapServiceI {

}
