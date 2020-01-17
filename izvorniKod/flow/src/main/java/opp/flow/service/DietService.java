package opp.flow.service;

import opp.flow.model.*;
import opp.flow.repository.*;
import opp.flow.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private ProductLimitationRepository productLimitationRepository;

    @Autowired
    private CategoryLimitationRepository categoryLimitationRepository;

    @Autowired
    private NutritionalValuesLimitationRepository nutritionalValuesLimitationRepository;


    public int addDiet(Diet diett) {
        List<Diet> diets = new ArrayList<>(dietRepository.findAll());
        for(Diet diet : diets){
            if(diet.getUsername().equals(diett.getUsername())){
                return -1;
            }
        }
        if (diett.getDescription() == null){
            return 1;
        }
        dietRepository.save(diett);
        return 0;
    }

    public boolean addProductLimitation(String username, String name){
        List<ProductLimitation>limitedProducts = productLimitationRepository.findAll();
        boolean save = true;
        for(ProductLimitation p: limitedProducts){
            if(p.getProductName().equals(name) && p.getUsername().equals(username)){
                save = false;
            }
        }
        if(save){
            ProductLimitation productLimitation = new ProductLimitation(username, name);
            productLimitationRepository.save(productLimitation);
        }
        return save;
    }

    public boolean addCategoryLimitation(String username, String name){
        List<CategoryLimitation>limitedCategories = categoryLimitationRepository.findAll();
        boolean save = true;
        for(CategoryLimitation c: limitedCategories){
            if(c.getCategoryName().equals(name) && c.getUsername().equals(username)){
                save = false;
            }
        }
        if(save){
            CategoryLimitation categoryLimitation = new CategoryLimitation(username, name);
            categoryLimitationRepository.save(categoryLimitation);
        }
        return save;
    }

    public boolean addNutritionalValuesLimitation(NutritionalValuesLimitation nutritionalValuesLimitation){
        nutritionalValuesLimitationRepository.save(nutritionalValuesLimitation);
        return true;
    }


}