package opp.flow.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import opp.flow.model.Barcode;
import opp.flow.model.Product;
import opp.flow.repository.BarcodeRepository;

@Service
public class BarcodeService {
	@Autowired
	private BarcodeRepository barcodeRepository;
	
	
	
	public void saveBarcodeProductImage(String name, MultipartFile file) {
		try {
			Barcode barcode=barcodeRepository.findByproductname(name);
			if(barcode==null) {
				barcode=new Barcode(null, name, null);
			}
			Byte[] byteObjects=new Byte[file.getBytes().length];
			
			int i=-1;
			for(byte b:file.getBytes()) {
				i++;
				byteObjects[i]=b;
			}
			
			barcode.setImage(byteObjects);
			barcodeRepository.save(barcode);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
