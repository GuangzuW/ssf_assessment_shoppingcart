package ibf.ssf.assessment.ssf_assessment_shoppingcart.models;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ShippingAddress {

    @NotEmpty(message = "Name is mandatory field")
    @Size(min=2,message = "Last Name must be between 3 to 100 characters")
    private String name;

    @NotEmpty(message = "Address is mandatory field")
    private String address;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address;} 
    public void setAddress(String address) { this.address = address; }
    @Override
    public String toString() {
        return "ShippingAddress [name=" + name + ", address=" + address + "]";
    }
    
    public static ShippingAddress create(JsonObject json) {
		ShippingAddress shippingAddress= new ShippingAddress();
		shippingAddress.setName(json.getString("name"));
		shippingAddress.setAddress(json.getString("address"));
		return shippingAddress;
	}
}
