package ibf.ssf.assessment.ssf_assessment_shoppingcart.repositories;

import ibf.ssf.assessment.ssf_assessment_shoppingcart.models.Item;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;



@Repository
public class ItemListRepository {
    
    private List<Item> itemList;

    public List<Item> addItem(Item item){
        System.out.printf("additem: %s \n".formatted(item));
        if(itemList==null){
            System.out.printf("additem: %s ,empty itemlist \n".formatted(item));
            itemList=new ArrayList<>();
            itemList.add(item);
        }else{
            Boolean isContain=false;
            for(int i=0;i<itemList.size();i++){
                
                if(item.getItem().equals(itemList.get(i).getItem())){
                    isContain=true;
                    itemList.get(i).setQuantity((itemList.get(i).getQuantity())+(item.getQuantity()));
                }
            }
            if(!isContain){
                itemList.add(item);
            }
        }
        return itemList;
    }

    public List<Item> getall(){
        return itemList;
    }


    public List<String> getQuotationListString(){
        
        List<String> listString=new ArrayList<>();
        for(int i=0;i<itemList.size();i++){
                listString.add(itemList.get(i).getItem());
        }
        return listString;
    }
    
    public JsonArray toJSON(List<String> listString) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (String s : listString) {
            builder.add(s);
        }
        jakarta.json.JsonArray items = builder.build();
        return items;
    }

  
 
}
