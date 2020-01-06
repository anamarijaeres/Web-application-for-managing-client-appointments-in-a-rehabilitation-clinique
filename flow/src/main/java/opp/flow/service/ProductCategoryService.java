package opp.flow.service;

import opp.flow.model.ProductCategory;
import opp.flow.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public boolean addProductCategory(ProductCategory productCategory) {
        ProductCategory productCategory1=productCategoryRepository.findByName(productCategory.getName());
        if(productCategory1 == null) {
            productCategoryRepository.save(productCategory);
            return true;
        }else {
            return false;
        }
    }
}
