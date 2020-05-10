package com.example.spstore;

import java.util.List;

import api.ApiService;
import io.reactivex.Single;
import model.Banner;
import model.Product;
import providers.ApiServiceProvider;

public class MainViewModel {
    private ApiService apiService = ApiServiceProvider.provideApiService();

    public Single<List<Product>> latestProducts() {
        return apiService.getProducts(Product.SORT_LATEST);
    }

    public Single<List<Product>> popularProducts() {
        return apiService.getProducts(Product.SORT_POPULAR);

    }

    public Single<List<Banner>> banners() {
        return apiService.getBanners();
    }

}
