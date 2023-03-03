package ibf.ssf.assessment.ssf_assessment_shoppingcart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf.ssf.assessment.ssf_assessment_shoppingcart.models.Item;
import ibf.ssf.assessment.ssf_assessment_shoppingcart.repositories.ItemListRepository;

@Service
public class ItemListService {

    @Autowired
   ItemListRepository itemListRepo;

   public List<Item> addItem(Item item){

        return itemListRepo.addItem(item);
   }

   public List<Item> getAll(){
        return itemListRepo.getall();
   }

   public List<String> getQuotatioStrings(){
    return itemListRepo.getQuotationListString();
   }
    
}
