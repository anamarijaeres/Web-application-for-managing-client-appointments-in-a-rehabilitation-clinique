package opp.flow.service;

import opp.flow.model.CategoryLimitation;
import opp.flow.model.DoctorCoach;
import opp.flow.model.Product;
import opp.flow.model.ProductCategory;
import opp.flow.model.ProductLimitation;
import opp.flow.repository.ProductLimitationRepository;
import opp.flow.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductLimitationRepository productLimitationRepository;

    public int addProduct(Product product) {
        if(product.getName() == null) {
            return -1;
        }else{
            Product product1=productRepository.findByname(product.getName());
            if(product1 == null) {
                productRepository.save(product);
                return 0;
            }else {return 1;}
        }
    }

	public void addAllergens(String name, String allergens) {
		Product product=productRepository.findByname(name);
		product.setAllergens(allergens);
		productRepository.save(product);
	}

	public void saveProductImage(String name, MultipartFile file) {
		try {
			Product product=productRepository.findByname(name);
			Byte[] byteObjects=new Byte[file.getBytes().length];
			
			int i=-1;
			for(byte b:file.getBytes()) {
				i++;
				byteObjects[i]=b;
			}
			
			product.setImage(byteObjects);
			productRepository.save(product);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	
}

