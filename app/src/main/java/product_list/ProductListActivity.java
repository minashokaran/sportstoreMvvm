package product_list;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spstore.BaseActivity;
import com.example.spstore.R;

import java.util.List;

import api.StoreSingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import model.Product;

public class ProductListActivity extends BaseActivity {
    private ProductViewModel viewModel = new ProductViewModel();
    private int sortType;
    public static final String EXTRA_KEY_SORT = "sort";
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProductAdapter productAdapter;
    private StoreSingleObserver<List<Product>> productObserver = new StoreSingleObserver<List<Product>>() {
        @Override
        public void onSubscribe(Disposable d) {
            disposable.add(d);
        }

        @Override
        public void onSuccess(List<Product> products) {
            productAdapter.setProducts(products);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        sortType = getIntent().getIntExtra(EXTRA_KEY_SORT, Product.SORT_LATEST);
        setupViews();
        observe();
    }

    private void observe() {
        viewModel.products(sortType).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(productObserver);
    }

    private void setupViews() {
        RecyclerView productRv = findViewById(R.id.rv_list_products);
        productRv.setLayoutManager(new GridLayoutManager(this, 2));
        productAdapter = new ProductAdapter();
        productRv.setAdapter(productAdapter);


        RecyclerView sortChipsRv = findViewById(R.id.rv_list_sort);
        sortChipsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        sortChipsRv.setAdapter(new SortAdapter(this, sortType, new SortAdapter.OnSortClickListener() {
            @Override
            public void onClick(int sortType) {
                ProductListActivity.this.sortType = sortType;
                observe();
            }
        }));
        ImageView backButton = findViewById(R.id.iv_list_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public int getCoordinatorLayoutId() {
        return R.id.coordinator_list;
    }
};