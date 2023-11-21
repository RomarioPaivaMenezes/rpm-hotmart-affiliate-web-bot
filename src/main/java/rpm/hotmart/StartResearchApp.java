package rpm.hotmart;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import rpm.hotmart.domain.product.AffiliationType;
import rpm.hotmart.domain.product.Product;
import rpm.hotmart.service.research.HotmartResearchClient;
import rpm.hotmart.service.research.ResearchOrderBy;
import rpm.hotmart.service.research.ResearchParametersManager;
import rpm.hotmart.service.research.util.ProductResearchUtil;
public class StartResearchApp {
	
	private static List<Integer> negativeProduct = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		AppManager appManager = new AppManager(ResearchOrderBy.HOTTEST, new ResearchParametersManager());
		
		List<Product> products = null; 
		JSONObject jSONObject = HotmartResearchClient.startAfilianteResearch(appManager,1);
		int page = jSONObject.getInt("totalPages");
		
		for (int i = 1; i < page; i++) {
			JSONObject responseResearch =  HotmartResearchClient.startAfilianteResearch(appManager, i);
			products = ProductResearchUtil.parseJsonProdutosToObject(responseResearch, products, negativeProduct);
		}
		
		System.out.print("Research Result: "+AffiliationType.APPLICATION);

		if(products!=null) {
			products.forEach(product -> {
		    	System.out.print(";Product:" + product.getProducerName());
				System.out.print(";Product Name:" + product.getName());
				System.out.print(";Temperature:" + product.getTemperature());
				System.out.print(";Blueprint:" + product.getBlueprint());
				System.out.print(";Price:" + product.getPrice().getValue());
				System.out.print(";Commission:" + product.getAffiliation().getComission().getPrice().getValue());
				System.out.print(";Commission Percentage:" + product.getAffiliation().getComission().getPercentage());
				System.out.println(";https://app.hotmart.com/market/details?producerUcode="+product.getProducerUcoder()+"&productUcode="+product.getProductUcoder()+" ");
			});
		}
	}
	
	private boolean listIgnore(int idProduct) {
		return negativeProduct.contains(idProduct);
	}
}
