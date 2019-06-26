package nsh.dengfeng.gisplan.selfservice.view;

import nsh.dengfeng.gisplan.selfservice.entity.Selfservice;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;

/**
 * 银行自助设备视图
 * 
 * @author 赵琦
 *
 */
public class SelfserviceView {
    private Selfservice selfservice;
    private String dotName;
    private StoreGeometry sg;

    public SelfserviceView() {}

    public Selfservice getSelfservice() {
        return selfservice;
    }

    public void setSelfservice(Selfservice selfservice) {
        this.selfservice = selfservice;
    }

    public String getDotName() {
        return dotName;
    }

    public void setDotName(String dotName) {
        this.dotName = dotName;
    }

    public StoreGeometry getSg() {
        return sg;
    }

    public void setSg(StoreGeometry sg) {
        this.sg = sg;
    }


}
