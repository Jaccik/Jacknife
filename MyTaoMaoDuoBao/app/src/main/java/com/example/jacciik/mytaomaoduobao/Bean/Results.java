package com.example.jacciik.mytaomaoduobao.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {

@SerializedName("n_tbk_item")
@Expose
private List<NTbkItem> nTbkItem = null;

public List<NTbkItem> getNTbkItem() {
return nTbkItem;
}

public void setNTbkItem(List<NTbkItem> nTbkItem) {
this.nTbkItem = nTbkItem;
}

}