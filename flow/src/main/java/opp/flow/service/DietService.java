package opp.flow.service;

import opp.flow.model.*;
import opp.flow.repository.*;
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


    public int addDiet(String description, String username) {
        List<Diet> diets = new ArrayList<>(dietRepository.findAll());
        for(Diet diet : diets){
            if(diet.getUsername().equals(username)){
                return -1;
            }
        }
        if (description == null){
            return 1;
        }
        Diet diet = new Diet(description, username);
        dietRepository.save(diet);
        return 0;
    }

    public boolean addProductLimitation(String username, Long productID){
        ProductLimitation productLimitation = new ProductLimitation(username, productID);
        productLimitationRepository.save(productLimitation);
        return true;
    }

    public boolean addCategoryLimitation(String username, Long categoryID){
        CategoryLimitation categoryLimitation = new CategoryLimitation(username, categoryID);
        categoryLimitationRepository.save(categoryLimitation);
        return true;
    }


}
