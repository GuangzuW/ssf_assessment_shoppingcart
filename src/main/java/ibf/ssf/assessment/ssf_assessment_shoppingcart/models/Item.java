package ibf.ssf.assessment.ssf_assessment_shoppingcart.models;


import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Item implements Serializable{

    @NotNull(message="You need to select ont item")
    private String item;

    @Min(value = 1, message = "you must order at least 1 item")
    private int quantity;

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int qunatity) { this.quantity = qunatity; }

    @Override
    public String toString() {
        return "Item [item=" + item + ", quantity=" + quantity + "]";
    }


    public static Item create(JsonObject json){
        Item item=new Item();
        item.setItem(json.getString("item"));
        item.setQuantity(json.getInt("quantity"));
        return item;
    }



}
