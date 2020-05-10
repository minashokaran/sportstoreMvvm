package product_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spstore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import model.Product;
import utils.PriceConverter;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products = new ArrayList<>();

    public ProductAdapter() {
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bindProduct(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImgView;
        private TextView productTitle;
        private TextView productPrice;
        private TextView productPrvPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImgView = itemView.findViewById(R.id.iv_product_image);
            productTitle = itemView.findViewById(R.id.tv_product_title);
            productPrice = itemView.findViewById(R.id.tv_product_price);
            productPrvPrice = itemView.findViewById(R.id.tv_product_prevPrice);
        }


        public void bindProduct(Product product) {
            Picasso.get().load(product.getmImage()).into(productImgView);
            productTitle.setText(product.getmTitle());
            productPrvPrice.setText(PriceConverter.convert(product.getmPreviousPrice()));
        }
    }
}
