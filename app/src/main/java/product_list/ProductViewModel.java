package product_list;

import java.util.List;

import api.ApiService;
import io.reactivex.Single;
import model.Product;
import providers.ApiServiceProvider;

public class ProductViewModel {
    private ApiService apiService= ApiServiceProvider.provideApiService();

    protected Single<List<Product>> products(Integer sortType){
        return apiService.getProducts(sortType);
    }
}
