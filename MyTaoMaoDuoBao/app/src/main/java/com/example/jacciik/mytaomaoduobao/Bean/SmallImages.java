package com.example.jacciik.mytaomaoduobao.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SmallImages {

@SerializedName("string")
@Expose
private List<String> string = null;

public List<String> getString() {
return string;
}

public void setString(List<String> string) {
this.string = string;
}

}