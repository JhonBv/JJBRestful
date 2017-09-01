package clysmauk.jjbrestful.Models;

/**
 * Created by barreij on 23/08/2017.
 */

public class ProductModel {

    private String _productName;
    private String _seller;

    //########################## Getters ###########################

    public String getProductName() {
        return _productName;
    }

    public String getSeller() {
        return _seller;
    }

    //########################## Setters ###########################

    public void setProductName(String prodName) {
        this._productName = prodName;
    }

    public void setProductSeller(String seller) {
        this._seller = seller;
    }

}
