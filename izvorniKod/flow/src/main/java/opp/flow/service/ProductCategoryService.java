package opp.flow.service;

import opp.flow.model.AdminPost;
import opp.flow.model.CategoryLimitation;
import opp.flow.model.DoctorCoach;
import opp.flow.model.ProductCategory;
import opp.flow.repository.CategoryLimitationRepository;
import opp.flow.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private CategoryLimitationRepository categoryLimitationRepository;

    public int addProductCategory(ProductCategory productCategory) {
        if(productCategory.getName().isBlank()) {
            return -1;
        }else {
            ProductCategory productCategory1 = productCategoryRepository.findByname(productCategory.getName());
            if (productCategory1 == null) {
                productCategoryRepository.save(productCategory);
                return 0;
            } else {
                return 1;
            }
        }
    }

    public List<ProductCategory> getProductCategoryList() {
        List<ProductCategory>productCategoriesList=productCategoryRepository.findAll();
        return productCategoriesList;
    }

    //uredivanje kategorija proizvoda
    public void updateProductCategory(ProductCategory updateProductCategory, String name) {
        ProductCategory productCategory=productCategoryRepository.findByname(name);
        try {
            productCategory.replaceAttributes(updateProductCategory);
        }catch(Exception e) {
            e.printStackTrace();
        }
        productCategoryRepository.save(productCategory);
    }

    public void deleteProductCategory(ProductCategory productCategory) {
        ProductCategory pc=productCategoryRepository.findByname(productCategory.getName());
        productCategory.setId(pc.getId());
        productCategoryRepository.delete(productCategory);
    }
}
