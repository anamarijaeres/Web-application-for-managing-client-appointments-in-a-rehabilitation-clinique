package opp.flow.controller;

import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.ProductCategory;
import opp.flow.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/addProductCategory")
    public ResponseMessage addProductCategory(@RequestBody ProductCategory productCategory) {
        ResponseMessage response=new ResponseMessage();
        boolean save=productCategoryService.addProductCategory(productCategory);
        response.setName(productCategory.getName());
        if(save==true) {
            response.setError_code(ErrorCode.ERROR_CODE_0);
            response.setMessage("You added a category of products successfully");
        }else {
            response.setError_code(ErrorCode.ERROR_CODE_9);
            response.setMessage("Product category name is taken");
        }
        return response;
    }
}
