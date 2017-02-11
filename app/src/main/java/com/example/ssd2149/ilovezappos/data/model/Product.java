package com.example.ssd2149.ilovezappos.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.ssd2149.ilovezappos.BR;

/**
 * Created by ssd21 on 2/8/2017.
 */

public class Product extends BaseObservable{
        private String brandname= " ";
        private String thumbnailImageUrl= " ";
        private String productId= " ";
        private String originalPrice= " ";
        private String styleId= " ";
        private String colorId= " ";
        private String price= " ";
        private String percentOff= " ";
        private String productUrl= " ";
        private String productName= " ";

    public Product(String brandname, String thumbnailImageUrl, String productId, String originalPrice, String styleId, String colorId, String price, String percentOff, String productUrl, String productName)
    {
        this.brandname=brandname;
        this.thumbnailImageUrl=thumbnailImageUrl;
        this.productId=productId;
        this.originalPrice=originalPrice;
        this.styleId=styleId;
        this.colorId=colorId;
        this.price=price;
        this.percentOff=percentOff;
        this.productUrl=productUrl;
        this.productName=productName;
    }
@Bindable
    public String getBrandname() {return brandname;}
@Bindable
    public String getThumbnailImageUrl() {return thumbnailImageUrl;}
@Bindable
    public String getProductId() {return productId;}
@Bindable
    public String getOriginalPrice() {return originalPrice;}
@Bindable
    public String getStyleId() {return styleId;}
@Bindable
    public String getColorId() {return colorId;}
@Bindable
    public String getPrice() {return price;}
@Bindable
    public String getPercentOff() {return percentOff;}
@Bindable
    public String getProductUrl() {return productUrl;}
@Bindable
    public String getProductName() {return productName;}
@Bindable
    public void setBrandname(String brandname) {this.brandname= brandname; notifyPropertyChanged(BR.brandname);}
@Bindable
    public void setThumbnailImageUrl(String thumbnailImageUrl) {this.thumbnailImageUrl= thumbnailImageUrl; notifyPropertyChanged(BR.thumbnailImageUrl);}
@Bindable
    public void setProductId(String productId) {this.productId= productId; notifyPropertyChanged(BR.productId);}
@Bindable
    public void setOriginalPrice(String originalPrice) {this.originalPrice= originalPrice; notifyPropertyChanged(BR.originalPrice);}
@Bindable
    public void setStyleId(String styleId) {this.styleId= styleId; notifyPropertyChanged(BR.styleId);}
@Bindable
    public void setColorId(String colorId) {this.colorId= colorId; notifyPropertyChanged(BR.colorId);}
@Bindable
    public void setPrice(String price) {this.price= price; notifyPropertyChanged(BR.price);}
@Bindable
    public void setPercentOff(String percentOff) {this.percentOff= percentOff; notifyPropertyChanged(BR.percentOff);}
@Bindable
    public void setProductUrl(String productUrl) {this.productUrl= productUrl; notifyPropertyChanged(BR.productUrl);}
@Bindable
    public void setProductName(String productName) {this.productName= productName; notifyPropertyChanged(BR.productName);}
}
