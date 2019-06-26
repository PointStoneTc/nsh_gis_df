package nsh.dengfeng.gisplan.dot.view;

import java.util.ArrayList;
import java.util.List;

import nsh.dengfeng.gisplan.dot.entity.Dot;
import nsh.dengfeng.gisplan.product.view.BankProductView;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;

/**
 * 网点视图
 * @author 赵琦
 *
 */
public class DotView {
    private Dot dot;
    private StoreGeometry sg;
    private List<BankProductView> products;
    
    public DotView() {
        products = new ArrayList<BankProductView>();
    }

    public Dot getDot() {
        return dot;
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public StoreGeometry getSg() {
        return sg;
    }

    public void setSg(StoreGeometry sg) {
        this.sg = sg;
    }

    public List<BankProductView> getProducts() {
        return products;
    }

    public void setProducts(List<BankProductView> products) {
        this.products = products;
    }
  
}
