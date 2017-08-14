package com.example.jacciik.mytaomaoduobao.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NTbkItem {

@SerializedName("item_url")
@Expose
private String itemUrl;
@SerializedName("nick")
@Expose
private String nick;
@SerializedName("num_iid")
@Expose
private String numIid;
@SerializedName("pict_url")
@Expose
private String pictUrl;
@SerializedName("provcity")
@Expose
private String provcity;
@SerializedName("reserve_price")
@Expose
private String reservePrice;
@SerializedName("seller_id")
@Expose
private String sellerId;
@SerializedName("small_images")
@Expose
private SmallImages smallImages;
@SerializedName("title")
@Expose
private String title;
@SerializedName("user_type")
@Expose
private Integer userType;
@SerializedName("volume")
@Expose
private Integer volume;
@SerializedName("zk_final_price")
@Expose
private String zkFinalPrice;

public String getItemUrl() {
return itemUrl;
}

public void setItemUrl(String itemUrl) {
this.itemUrl = itemUrl;
}

public String getNick() {
return nick;
}

public void setNick(String nick) {
this.nick = nick;
}

public String getNumIid() {
return numIid;
}

public void setNumIid(String numIid) {
this.numIid = numIid;
}

public String getPictUrl() {
return pictUrl;
}

public void setPictUrl(String pictUrl) {
this.pictUrl = pictUrl;
}

public String getProvcity() {
return provcity;
}

public void setProvcity(String provcity) {
this.provcity = provcity;
}

public String getReservePrice() {
return reservePrice;
}

public void setReservePrice(String reservePrice) {
this.reservePrice = reservePrice;
}

public String getSellerId() {
return sellerId;
}

public void setSellerId(String sellerId) {
this.sellerId = sellerId;
}

public SmallImages getSmallImages() {
return smallImages;
}

public void setSmallImages(SmallImages smallImages) {
this.smallImages = smallImages;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public Integer getUserType() {
return userType;
}

public void setUserType(Integer userType) {
this.userType = userType;
}

public Integer getVolume() {
return volume;
}

public void setVolume(Integer volume) {
this.volume = volume;
}

public String getZkFinalPrice() {
return zkFinalPrice;
}

public void setZkFinalPrice(String zkFinalPrice) {
this.zkFinalPrice = zkFinalPrice;
}

}