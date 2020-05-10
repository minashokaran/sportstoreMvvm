package com.example.spstore;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import api.StoreSingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import model.Banner;
import model.Product;
import product_list.BannerAdapter;
import product_list.ProductAdapter;
import product_list.ProductListActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private MainViewModel viewModel;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProductAdapter latestProductAdapter;
    private ProductAdapter popularProductAdapter;
    private BannerAdapter bannerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new MainViewModel();
        Observe();
        setupViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private void Observe() {
        viewModel.latestProducts().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new StoreSingleObserver<List<Product>>(this) {
            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onSuccess(List<Product> products) {
                latestProductAdapter.setProducts(products);
            }
        });
        viewModel.popularProducts().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new StoreSingleObserver<List<Product>>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Product> products) {
                        popularProductAdapter.setProducts(products);
                    }
                });

        viewModel.banners().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new StoreSingleObserver<List<Banner>>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);

                    }

                    @Override
                    public void onSuccess(List<Banner> banners) {
                        bannerAdapter.setBanners(banners);
                    }
                });
    }

    private void setupViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        // RecyclerView latestProductRv = findViewById(R.id.rv_main_latestProducts);
        //  latestProductRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        // latestProductAdapter = new ProductAdapter();
        // latestProductRv.setAdapter(latestProductAdapter);

        RecyclerView popularProductRv = findViewById(R.id.rv_main_popularProducts);
        popularProductRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        popularProductAdapter = new ProductAdapter();
        popularProductRv.setAdapter(popularProductAdapter);

        RecyclerView sliderRv = findViewById(R.id.rv_main_slider);
        sliderRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(sliderRv);
        bannerAdapter = new BannerAdapter();
        sliderRv.setAdapter(bannerAdapter);

    }

    @Override
    public int getCoordinatorLayoutId() {
        return R.id.coordinator_main;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_main_viewAllLatestProducts:
                intent.putExtra(ProductListActivity.EXTRA_KEY_SORT, Product.SORT_LATEST);
                break;
            case R.id.rv_main_popularProducts:
                intent.putExtra(ProductListActivity.EXTRA_KEY_SORT, Product.SORT_POPULAR);
                break;
        }
        startActivity(intent);
    }
}


