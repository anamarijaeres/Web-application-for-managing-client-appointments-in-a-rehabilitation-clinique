package opp.flow.controller;

import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.AdminPost;
import opp.flow.model.Product;
import opp.flow.model.ProductCategory;
import opp.flow.service.ProductCategoryService;
import opp.flow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/addProductCategory")
    public ResponseMessage addProductCategory( ProductCategory productCategory) {
        ResponseMessage response=new ResponseMessage();
        int save=productCategoryService.addProductCategory(productCategory);
        if(save == 0) {
            response.setError_code(ErrorCode.ERROR_CODE_0);
            response.setMessage("You successfully added a new category of products!");
        }else if(save == 1) {
            response.setError_code(ErrorCode.ERROR_CODE_9);
            response.setMessage("Product category name is taken!");
        }else{
            response.setError_code(ErrorCode.ERROR_CODE_10);
            response.setMessage("Product category name must not be null!");
        }
        return response;
    }

    @GetMapping("/categoryList")
    public List<ProductCategory> getProductCategoryList(){
        return productCategoryService.getProductCategoryList();
    }



}
