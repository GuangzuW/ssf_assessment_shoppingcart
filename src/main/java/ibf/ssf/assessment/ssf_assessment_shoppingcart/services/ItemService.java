package ibf.ssf.assessment.ssf_assessment_shoppingcart.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import ibf.ssf.assessment.ssf_assessment_shoppingcart.models.Item;

@Service
public class ItemService {

    public static final String[] ITEM_NAMES = {
		"apple","orange", "bread", "cheese", "chicken","mineral water", "instant noodles"
	};

    private final Set<String> itemNames;

    public ItemService() {
		itemNames = new HashSet<>(Arrays.asList(ITEM_NAMES));
	}

    public List<ObjectError> validateItem(Item item) {
		List<ObjectError> errors = new LinkedList<>();
		FieldError error;

		if (!itemNames.contains(item.getItem().toLowerCase())) {
			error = new FieldError("item", "item"
					, "We do not stock %s".formatted(item.getItem()));
			errors.add(error);
		}
		return errors;
	}

}
