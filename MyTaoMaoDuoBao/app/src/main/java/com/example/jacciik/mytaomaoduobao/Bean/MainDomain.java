package com.example.jacciik.mytaomaoduobao.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jakewharton.retrofit2.adapter.rxjava2.Result;

import java.util.List;

public class MainDomain {

@SerializedName("result")
@Expose
private List<Result> result = null;

public List<Result> getResult() {
return result;
}

public void setResult(List<Result> result) {
this.result = result;
}

}