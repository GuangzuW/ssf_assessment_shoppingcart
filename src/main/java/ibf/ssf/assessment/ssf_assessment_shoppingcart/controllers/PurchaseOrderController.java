package ibf.ssf.assessment.ssf_assessment_shoppingcart.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ibf.ssf.assessment.ssf_assessment_shoppingcart.models.Item;
import ibf.ssf.assessment.ssf_assessment_shoppingcart.models.Order;
import ibf.ssf.assessment.ssf_assessment_shoppingcart.models.ShippingAddress;
import ibf.ssf.assessment.ssf_assessment_shoppingcart.services.ItemListService;
import ibf.ssf.assessment.ssf_assessment_shoppingcart.services.ItemService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PurchaseOrderController {

    @Autowired
    ItemListService itemListSvc;

    @Autowired
    ItemService itemSvc;

    private Logger logger = Logger.getLogger(PurchaseOrderController.class.getName());

    @GetMapping(path={"/view1","/"})
    public String getview1(Model model,HttpSession sess){
        sess.invalidate();
        List<Item> itemList=itemListSvc.getAll();
        model.addAttribute("item", new Item());
        model.addAttribute("itemlist", itemList);
        return "view1";
    }

    @PostMapping(path="/addItem")
    public String addItem(@Valid @ModelAttribute("item")Item item,BindingResult result,Model model,HttpSession sess){
        
        if(result.hasErrors()){
            return "view1";
        }
        List<ObjectError> errors = itemSvc.validateItem(item);
        if (!errors.isEmpty()) {
			for (ObjectError err: errors)
				result.addError(err);
			return "view1";
		}
        List<Item> itemList =itemListSvc.addItem(item);
        sess.setAttribute("itemlist", itemList);
        model.addAttribute("item", new Item());
        model.addAttribute("itemlist", itemList);
        return "view1";
    }
    
    @PostMapping(path = "/shippingaddress")
    public String postItemList(Model model,HttpSession sess){
        List<Item> itemlist= (List<Item>)sess.getAttribute("itemlist");
        System.out.println(itemlist);
        if(null==itemlist){
            return "redirect:/view1";
        }
        model.addAttribute("shippingaddress", new ShippingAddress());
        return "view2";
    }

    @PostMapping(path = "/quotation")
    public String postQuotation(Model model, HttpSession sess,@Valid ShippingAddress shippingAddress,BindingResult result){
        if (result.hasErrors()){
            return "view2";
        }
		List<Item> itemlist= (List<Item>)sess.getAttribute("itemlist");
        if(null==itemlist){
            return "redirect:/view1";
        }

        Order order=new Order(itemlist, shippingAddress);
        List<String> listString= itemListSvc.getQuotatioStrings();

        

        model.addAttribute("order", order);
        return "quotation";
    }
}
