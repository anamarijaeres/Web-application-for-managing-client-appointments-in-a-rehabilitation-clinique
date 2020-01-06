package opp.flow.controller;


import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.Product;
import opp.flow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseMessage addProduct(@RequestBody Product product) {
        ResponseMessage response=new ResponseMessage();
        boolean save=productService.addProduct(product);
        response.setName(product.getName());
        if(save==true) {
            response.setError_code(ErrorCode.ERROR_CODE_0);
            response.setMessage("You added a product successfully");
        }else {
            response.setError_code(ErrorCode.ERROR_CODE_9);
            response.setMessage("Product name is taken");
        }
        return response;
    }
}
