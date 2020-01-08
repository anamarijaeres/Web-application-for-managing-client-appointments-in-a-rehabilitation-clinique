package opp.flow.controller;


import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.Product;
import opp.flow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseMessage addProduct( Product product) {
        ResponseMessage response=new ResponseMessage();
        int save=productService.addProduct(product);
        if(save==0) {
            response.setError_code(ErrorCode.ERROR_CODE_0);
            response.setMessage("You successfully added a new product!");
        }else if(save == 1) {
            response.setError_code(ErrorCode.ERROR_CODE_9);
            response.setMessage("Product name is taken!");
        }else{
            response.setError_code(ErrorCode.ERROR_CODE_10);
            response.setMessage("Product name must not be empty!");
        }
        return response;
    }


}
