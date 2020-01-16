package opp.flow.service;

import opp.flow.model.*;
//ovo je javilo
import opp.flow.repository.*;
//do tu
import opp.flow.repository.ProductLimitationRepository;
import opp.flow.repository.ProductRepository;
//i ovo do kraja
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

	@Autowired
	private ConsumedProductRepository consumedProductRepository;

	@Autowired
	private NutritionalValuesLimitationRepository nutritionalValuesLimitationRepository;

	@Autowired
	private CategoryLimitationRepository categoryLimitationRepository;

    @Autowired
    private ProductLimitationRepository productLimitationRepository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

    public List<Product> getProducts(){
    	return productRepository.findAll();
	}

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

	public boolean saveConsumedProduct(ConsumedProduct consumedProduct) {
		boolean okay = true;
		double mass = (Double.parseDouble(consumedProduct.getMass())) / 100;
		System.out.println(mass);
		Product product = productRepository.findByname(consumedProduct.getProductName());
		double energy = product.getEnergy() * mass;
		double fat = product.getFat() * mass;
		double saturatedFattyAcids = product.getSaturatedFattyAcids() * mass;
		double carbohydrates = product.getCarbohydrates() * mass;
		double sugars = product.getSugars() * mass;
		double protein = product.getProtein() * mass;
		double salt = product.getSalt() * mass;

		List<ConsumedProduct> consumedProducts = consumedProductRepository.findByusername(consumedProduct.getUsername());
		if (consumedProducts != null) {
			for (ConsumedProduct con : consumedProducts) {

				if (con.getDate().isEqual(consumedProduct.getDate())) {
					mass = (Double.parseDouble(con.getMass())) / 100;
					energy += product.getEnergy() * mass;
					fat += product.getFat() * mass;
					saturatedFattyAcids += product.getSaturatedFattyAcids() * mass;
					carbohydrates += product.getCarbohydrates() * mass;
					sugars += product.getSugars() * mass;
					protein += product.getProtein() * mass;
					salt += product.getSalt() * mass;
				}
			}
		}
		NutritionalValuesLimitation nutritionalValuesLimitation = nutritionalValuesLimitationRepository.findByusername(consumedProduct.getUsername());

		List<CategoryLimitation> categoryLimitation = categoryLimitationRepository.findByusername(consumedProduct.getUsername());
		String productName = consumedProduct.getProductName();
		Product prod = productRepository.findByname(productName);
		if (categoryLimitation != null) {

			String cat = prod.getCategory();
			if (cat.length() != 0) {
				ProductCategory category = productCategoryRepository.findByname(cat);
				for (CategoryLimitation catLim : categoryLimitation) {
					if (catLim.getCategoryName().equals(category.getName()))
						okay = false;
				}
			}
		}
		List<ProductLimitation> productLimitations = productLimitationRepository.findByusername(consumedProduct.getUsername());
		if (productLimitations != null) {
			for (ProductLimitation pl : productLimitations) {
				if (prod.getName().equals(pl.getProductName()))
					okay = false;
			}
		}
		if (nutritionalValuesLimitation != null) {
			System.out.println(energy);
			if (nutritionalValuesLimitation.getEnergyLimit() < energy)
				okay = false;
			if (nutritionalValuesLimitation.getFatLimit() < fat)
				okay = false;
			if (nutritionalValuesLimitation.getSaturatedFattyAcidsLimit() < saturatedFattyAcids)
				okay = false;
			if (nutritionalValuesLimitation.getCarbohydratesLimit() < carbohydrates)
				okay = false;
			if (nutritionalValuesLimitation.getSugarsLimit() < sugars)
				okay = false;
			if (nutritionalValuesLimitation.getProteinLimit() < protein)
				okay = false;
			if (nutritionalValuesLimitation.getSaltLimit() < salt)
				okay = false;
		}
		if (okay) {
			consumedProduct.setDate(LocalDate.now().plusDays(1));
			consumedProductRepository.save(consumedProduct);
		}
		return okay;

	}

	public void updateProduct(Product updateProduct, String name) {
		Product product=productRepository.findByname(name);
		try {
			product.replaceAttributes(updateProduct);
		}catch(Exception e) {
			e.printStackTrace();
		}
		productRepository.save(product);
	}
	public void deleteProduct(Product product) {
		Product pc=productRepository.findByname(product.getName());
		product.setId(pc.getId());
    	productRepository.deleteById(pc.getId());
	}

	public List<ProductPost> getProductList() {
		List<Product> productList=productRepository.findAll();
		List<ProductPost> productPostList = new ArrayList<>();
		for(Product p: productList){
			ProductPost post = new ProductPost(p.getName());
			productPostList.add(post);
		}
		return productPostList;
	}

	public List<Product> getFullProductList(){
    	List<Product> productList = productRepository.findAll();
    	return productList;

	}
}

