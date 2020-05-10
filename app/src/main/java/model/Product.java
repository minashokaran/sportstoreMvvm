package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    public static final int SORT_LATEST = 0;
    public static final int SORT_POPULAR = 1;
    public static final int SORT_PRICE_HIGH_TO_LOW = 2;
    public static final int SORT_PRICE_LOW_TO_HIGH = 3;


    public Long getmDiscount() {
        return mDiscount;
    }

    public void setmDiscount(Long mDiscount) {
        this.mDiscount = mDiscount;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public Long getmPreviousPrice() {
        return mPreviousPrice;
    }

    public void setmPreviousPrice(Long mPreviousPrice) {
        this.mPreviousPrice = mPreviousPrice;
    }

    public Long getmPrice() {
        return mPrice;
    }

    public void setmPrice(Long mPrice) {
        this.mPrice = mPrice;
    }

    public Long getmStatus() {
        return mStatus;
    }

    public void setmStatus(Long mStatus) {
        this.mStatus = mStatus;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @SerializedName("discount")
    private Long mDiscount;
    @SerializedName("id")
    private int mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("previous_price")
    private Long mPreviousPrice;
    @SerializedName("price")
    private Long mPrice;
    @SerializedName("status")
    private Long mStatus;
    @SerializedName("title")
    private String mTitle;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mDiscount);
        dest.writeInt(this.mId);
        dest.writeString(this.mImage);
        dest.writeValue(this.mPreviousPrice);
        dest.writeValue(this.mPrice);
        dest.writeValue(this.mStatus);
        dest.writeString(this.mTitle);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.mDiscount = (Long) in.readValue(Long.class.getClassLoader());
        this.mId = in.readInt();
        this.mImage = in.readString();
        this.mPreviousPrice = (Long) in.readValue(Long.class.getClassLoader());
        this.mPrice = (Long) in.readValue(Long.class.getClassLoader());
        this.mStatus = (Long) in.readValue(Long.class.getClassLoader());
        this.mTitle = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
