package palvelinohjelmointi.autonlampimaksi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import palvelinohjelmointi.autonlampimaksi.models.Car;
import palvelinohjelmointi.autonlampimaksi.models.Defaproduct;
import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.Offer;
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
		// VOISI KATSOA MIKÄ ON KALLEIN PAKETTI JA VALITA SEN... USEAMPI VAIHTOEHTO MAHDOLLINEN (ILMAN AUTON MALLIA)
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
		// HINTA ON VIELÄ VAIN TYÖNVELOITUS
		offer.setTotalPrice(enterprise.getHourRate() * time + enterprise.getTimeToInnerCable() * enterprise.getHourRate());
		offer.setCar(car);
		
		saveOfferIfNotSaved(offer);
	}
	
	public List<String> getPartsOfTheOffer(Defaproduct dp) {
		List<String> parts = new ArrayList<>();
		
		if (!dp.getEngineheaters().isEmpty()) {
			parts.add("Moottorilämmitin");
		}
		if (!dp.getHaaroitukset().isEmpty()) {
			if (dp.getHaaroitukset().contains("+")) {
				parts.add("Sisähaaroitussarja sekä johtosarjoja");
			} else {
				parts.add("Sisähaaroitussarja");
			}
		}
		if (!dp.getKytkikset().isEmpty()) {
			if (dp.getKytkikset().contains("+")) {
				parts.add("Kytkentäsarjan sekä jatkojohtoa");
			} else {
				parts.add("Kytkentäsarja");
			}
		}
				
		return parts;
	}

}














