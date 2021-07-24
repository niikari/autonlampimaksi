package palvelinohjelmointi.autonlampimaksi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import palvelinohjelmointi.autonlampimaksi.models.Car;
import palvelinohjelmointi.autonlampimaksi.models.Defaproduct;
import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.Offer;
import palvelinohjelmointi.autonlampimaksi.repositories.DefaproductRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.OfferRepository;

@Service
public class OfferService {
	
	@Autowired
	private OfferRepository offerRepository;
	
	public void saveOfferIfNotSaved(Offer offer) {
		if (offerRepository.findByCarAndEnterprise(offer.getCar(), offer.getEnterprise())== null) {
			offerRepository.save(offer);
		}
	}
	
	public void newOffer(Enterprise enterprise, List<Defaproduct> products, Car car) {
		Offer offer = new Offer();
		offer.setEnterprise(enterprise);
		Defaproduct product = products.get(0);
		offer.setProduct(product);
		double time = 0;
		if (product.getAika().toUpperCase().equals("A")) {
			time = 1.5;
		} else if (product.getAika().toUpperCase().equals("B")) {
			time = 2;
		} else {
			time = 2.5;
		}
		offer.setTotalPrice(enterprise.getHourRate() * time);
		offer.setCar(car);
		
		saveOfferIfNotSaved(offer);
	}

}
