package ibf.ssf.assessment.ssf_assessment_shoppingcart.models;

import java.util.List;

public class Order {

    private List<Item> itemlist;
    private ShippingAddress shippingAddress;

    public Order(List<Item> itemlist, ShippingAddress shippingAddress) {
        this.itemlist = itemlist;
        this.shippingAddress = shippingAddress;
    }

 
}
