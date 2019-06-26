package nsh.dengfeng.gisplan.merchant.view;

import java.util.ArrayList;
import java.util.List;

import nsh.dengfeng.gisplan.merchant.entity.Merchant;
import nsh.dengfeng.gisplan.product.view.MerchantBankProductView;
import nsh.dengfeng.gisplan.storeGeometry.entity.StoreGeometry;

/**
 * 商户视图
 * 
 * @author 赵琦
 *
 */
public class MerchantView {
    private Merchant merchant;
    private StoreGeometry sg;
    private List<MerchantDotView> dots;
    private List<MerchantBankProductView> products;

    public MerchantView() {
        dots = new ArrayList<MerchantDotView>();
        products = new ArrayList<MerchantBankProductView>();
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public StoreGeometry getSg() {
        return sg;
    }

    public void setSg(StoreGeometry sg) {
        this.sg = sg;
    }

    public List<MerchantDotView> getDots() {
        return dots;
    }

    public void setDots(List<MerchantDotView> dots) {
        this.dots = dots;
    }

    public List<MerchantBankProductView> getProducts() {
        return products;
    }

    public void setProducts(List<MerchantBankProductView> products) {
        this.products = products;
    }

}
