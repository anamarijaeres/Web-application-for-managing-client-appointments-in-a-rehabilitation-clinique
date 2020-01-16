package opp.flow.controller;
import opp.flow.ErrorCode;
import opp.flow.ResponseMessage;
import opp.flow.model.*;
import opp.flow.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping("/{username}/addDiet")
    public ResponseMessage addDiet(@RequestBody Diet diet) {
        ResponseMessage response=new ResponseMessage();
        int save=dietService.addDiet(diet);
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
    public ResponseMessage addProductLimitation(@PathVariable("username") String username, @RequestBody String productName) {
        ResponseMessage response=new ResponseMessage();
        String pName = productName.replaceAll("=","");
        boolean save = dietService.addProductLimitation(username, pName);
        if(save){
            response.setError_code(ErrorCode.ERROR_CODE_0);
            response.setMessage("You successfully added a product limitation!");
        }
        else{
            response.setError_code(ErrorCode.ERROR_CODE_9);
            response.setMessage("You already added that product limitation!");
        }
        return response;
    }


    @PostMapping("/{username}/addCategoryLimitation")
    public ResponseMessage addCategoryLimitation(@PathVariable("username") String username, @RequestBody String categoryName) {
        ResponseMessage response=new ResponseMessage();
        String cName = categoryName.replaceAll("=","");
        boolean save = dietService.addCategoryLimitation(username, cName);
        if(save){
            response.setError_code(ErrorCode.ERROR_CODE_0);
            response.setMessage("You successfully added a category limitation!");
        }
        else{
            response.setError_code(ErrorCode.ERROR_CODE_9);
            response.setMessage("You already added that category limitation!");
        }
        return response;
    }

    @PostMapping("/addNutritionalValuesLimitation/{username}")
    public ResponseMessage addNutritionalValuesLimitation(@RequestBody NutritionalValuesLimitation nutritionalValuesLimitation) {
        ResponseMessage response = new ResponseMessage();
        dietService.addNutritionalValuesLimitation(nutritionalValuesLimitation);
        response.setError_code(ErrorCode.ERROR_CODE_0);
        response.setMessage("You added nutritional value limitation successfully!");
        return response;
    }


}