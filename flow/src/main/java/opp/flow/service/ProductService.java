package opp.flow.service;

import opp.flow.model.Product;
import opp.flow.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public boolean addProduct(Product product) {
        Product product1=productRepository.findByName(product.getName());
        if(product1 == null) {
            productRepository.save(product);
            return true;
        }else {
            return false;
        }
    }
}

