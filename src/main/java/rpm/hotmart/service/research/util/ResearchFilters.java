package rpm.hotmart.service.research.util;

import java.util.List;

import rpm.hotmart.domain.product.AffiliationType;
import rpm.hotmart.domain.product.Product;

public class ResearchFilters {
	
public static boolean filterProduct(Product produto, List<Integer> negativeProduct) {
		
		boolean success = false;
		float value = produto.getAffiliation().getComission().getPrice().getValue();
		
		if(!negativeProduct.contains(produto.getId())) {
			success = true;
		}else return false;
		
		if(value >= 60 && value <= 300) {
			success = true;
		}else return false;
		
		if(produto.getTemperature() >= 20 && produto.getTemperature() <= 60) {
			success = true;
		}else return false;
		
		if(produto.getReviewRating() == 0 || produto.getReviewRating() >= 3) {
			success = true;
		}else return false;
		
		if(produto.getProductTags().getAffiliationType()==AffiliationType.ANYONE) {
			success = true;
		}else return false;
		
		if(produto.getBlueprint() >= 50) {
			success = true;
		}else return false;
		
		return success;

}
}
