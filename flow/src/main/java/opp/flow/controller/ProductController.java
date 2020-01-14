package opp.flow.controller;


import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.ConsumedProduct;
import opp.flow.model.Exercise;
import opp.flow.model.Product;
import opp.flow.model.Training;
import opp.flow.service.BarcodeService;
import opp.flow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private BarcodeService barcodeService;

    @GetMapping("/getProducts")
    public List<String> getProducts(){
        List<String> result = new ArrayList<>();
        List<Product> list = productService.getProducts();
        for(Product l : list) {
            result.add(l.getName());
        }
        return result;
    }
    
    @PostMapping("/addConsumedProduct")
    public ResponseMessage addWorkout(@RequestBody ConsumedProduct consumedProduct){
        consumedProduct.setDate(LocalDate.now());
        System.out.println(consumedProduct.getDate());
        boolean save=productService.saveConsumedProduct(consumedProduct);

        ResponseMessage responseMessage = new ResponseMessage();
        if(save==true) {
            responseMessage.setError_code(ErrorCode.ERROR_CODE_0);
        }else{
            responseMessage.setError_code(ErrorCode.ERROR_CODE_1);
        }
        return responseMessage;
    }
    
    @PostMapping("/addProduct")
    public ResponseMessage addProduct(@RequestBody Product product) {
        ResponseMessage response=new ResponseMessage();
        System.out.println(product);
        
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

    @PostMapping("/addAllergens/{name}")
    public ResponseMessage addAllergensList(@PathVariable("name") String name, @RequestBody List<String> allergens) {
    	ResponseMessage response=new ResponseMessage();
    	String all="";
    	for(String s:allergens) {
    		if(s.isBlank()) continue;
    		all+=s;
    		all+=";";
    	}
    	productService.addAllergens(name, all);
    	System.out.println(all);
    	response.setError_code(ErrorCode.ERROR_CODE_0);
    	return response;
    }
    
    @PostMapping("/saveProductImage/{name}")
    public ResponseMessage saveProductImage(@PathVariable("name") String name, @RequestParam("imageFile") MultipartFile image) {
    	ResponseMessage response=new ResponseMessage();
    	productService.saveProductImage(name, image);
    	response.setError_code(ErrorCode.ERROR_CODE_0);
    	return response;
    }
    
    @PostMapping("/saveProductBarcodeImage/{name}")
    public ResponseMessage saveBarcodeProductImage(@PathVariable("name") String name, @RequestParam("barcodeImageFile") MultipartFile image) {
    	
    	ResponseMessage response=new ResponseMessage();
    	barcodeService.saveBarcodeProductImage(name, image);
    	response.setError_code(ErrorCode.ERROR_CODE_0);
    	return response;
    }
    
    @PostMapping("/findProductbyBarcode")
    public ResponseMessage findProductByBarcode(@RequestParam("imageFile") MultipartFile image) {
    	ResponseMessage response=new ResponseMessage();
    	String productName=barcodeService.findProductName(image);
    	if(productName!=null) {
    		response.setError_code(ErrorCode.ERROR_CODE_0);
    		response.setUsername(productName);
    	}else {
    		response.setError_code(ErrorCode.ERROR_CODE_11);
    		response.setMessage("There is not product with uploaded barcode!");
    	}
    	return response;
    }
}
