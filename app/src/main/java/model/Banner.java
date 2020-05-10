package model;

import com.google.gson.annotations.SerializedName;

public class Banner {
    @SerializedName("id")
    private Long mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("link_type")
    private Long mLinkType;
    @SerializedName("link_value")
    private String mLinkValue;

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public Long getmLinkType() {
        return mLinkType;
    }

    public void setmLinkType(Long mLinkType) {
        this.mLinkType = mLinkType;
    }

    public String getmLinkValue() {
        return mLinkValue;
    }

    public void setmLinkValue(String mLinkValue) {
        this.mLinkValue = mLinkValue;
    }
}
