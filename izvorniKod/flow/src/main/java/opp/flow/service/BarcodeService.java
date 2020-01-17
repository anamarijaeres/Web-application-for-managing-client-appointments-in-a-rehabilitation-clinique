package opp.flow.service;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collections;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import opp.flow.ImgCompare;
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

	public String findProductName(MultipartFile file) {
		String name=null;
		List<ImgCompare> imgComp=new ArrayList<>();
		
		try {
			byte[] imageA=file.getBytes();
			InputStream isA=new ByteArrayInputStream(imageA);
			BufferedImage biA=ImageIO.read(isA);
			DataBuffer dbA=biA.getData().getDataBuffer();
			int sizeA=dbA.getSize();
			
			List<Barcode> barcodeList=barcodeRepository.findAll();
			
			for(Barcode barcode : barcodeList) {
				Byte[] byteB=barcode.getImage();
				if(byteB==null) {
					imgComp.add(new ImgCompare(barcode.getProductName(), 0));
					continue;
				}
				byte[] imageB=new byte[byteB.length];
				
				int i=-1;
				for(Byte b:byteB) {
					i++;
					imageB[i]=b.byteValue();
				}
				InputStream isB=new ByteArrayInputStream(imageB);
				BufferedImage biB=ImageIO.read(isB);
				DataBuffer dbB=biB.getData().getDataBuffer();
				int sizeB=dbB.getSize();
				int count=0;
				float percentage=0;
				
				if(sizeA==sizeB) {
					
					for(int j=0; j<sizeA; ++j) {
						if(dbA.getElem(j)==dbB.getElem(j)) {
							count++;
						}
					}
					percentage=(count/sizeA)*100;
				}
				imgComp.add(new ImgCompare(barcode.getProductName(), Math.round(percentage)));
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		Collections.sort(imgComp);
		try {
			ImgCompare img=imgComp.get(0);
			if(img.getPercentage()<90) {
				name=null;
			}else {
				name=img.getName();
			}
		}catch(Exception e) {
			name=null;
		}
		
		return name;
	}
}
