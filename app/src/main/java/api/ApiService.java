package api;

import java.util.List;

import io.reactivex.Single;
import model.Banner;
import model.Product;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("product_list/list")
    Single<List<Product>> getProducts(@Query("sort") int sort);

    @GET("banner/slider")
    Single<List<Banner>> getBanners();
}
