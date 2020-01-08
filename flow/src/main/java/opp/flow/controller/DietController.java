package opp.flow.controller;
import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.*;
import opp.flow.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping("/{username}/addDiet")
    public ResponseMessage addDiet(@PathVariable String username,  String description) {
        ResponseMessage response=new ResponseMessage();
        int save=dietService.addDiet(description, username);
        if(save == 0) {
            response.setError_code(ErrorCode.ERROR_CODE_0);
            response.setMessage("You successfully added a new diet!");
        }else if(save == -1) {
            response.setError_code(ErrorCode.ERROR_CODE_10);
            response.setMessage("Diet was already added!");
        }else{
            response.setError_code(ErrorCode.ERROR_CODE_9);
            response.setMessage("Description must not be empty!");
        }
        return response;
    }

    @PostMapping("/{username}/addProductLimitation")
    public ResponseMessage addDiet(@PathVariable String username, Product product) {
        ResponseMessage response=new ResponseMessage();
        dietService.addProductLimitation(username, product.getId());
        response.setError_code(ErrorCode.ERROR_CODE_0);
        response.setMessage("You successfully added a product limitation!");
        return response;
    }

    @PostMapping("/{username}/addCategoryLimitation")
    public ResponseMessage addCategoryLimitation(@PathVariable String username, ProductCategory productCategory) {
        ResponseMessage response = new ResponseMessage();
        dietService.addCategoryLimitation(username, productCategory.getId());
        response.setError_code(ErrorCode.ERROR_CODE_0);
        response.setMessage("You successfully added a category limitation!");
        return response;
    }

    //jedna od stvari koja se upisuje je i username tako da se može uzet model NutritionalValuesLimitation
    /*@PostMapping("/addNutritionalValuesLimitation/{username}")
    public ResponseMessage addNutritionalValuesLimitation(@RequestBody NutritionalValuesLimitation nutritionalValuesLimitation) {
        ResponseMessage response = new ResponseMessage();
        dietService.addNutritionalValuesLimitation(nutritionalValuesLimitation);
        response.setError_code(ErrorCode.ERROR_CODE_0);
        response.setMessage("You added nutritional value limitation successfully");
        return response;
    }*/

    //ili ovako
/*    @PostMapping("/addNutritionalValuesLimitation/{username}")
    public ResponseMessage addNutritionalValuesLimitation(@RequestParam("energy") double energy, @RequestParam("fat") double fat,
                                                          @RequestParam("saturatedFattyAcids") double saturatedFattyAcids, @RequestParam("carbohydrates") double carbohydrates,
                                                          @RequestParam("sugars") double sugars,  @RequestParam("protein") double protein, @RequestParam("salt") double salt,
                                                          @PathVariable("username") String username) {
        ResponseMessage response = new ResponseMessage();
        NutritionalValuesLimitation nutritionalValuesLimitation = new NutritionalValuesLimitation(username, energy, fat, saturatedFattyAcids, carbohydrates, sugars, protein, salt);
        dietService.addNutritionalValuesLimitation(nutritionalValuesLimitation);
        response.setError_code(ErrorCode.ERROR_CODE_0);
        response.setMessage("You added nutritional value limitation successfully");
        return response;
    }*/


}
