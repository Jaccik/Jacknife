package com.example.jacciik.mytaomaoduobao.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TbkResult {

@SerializedName("tbk_item_get_response")
@Expose
private TbkItemGetResponse tbkItemGetResponse;

public TbkItemGetResponse getTbkItemGetResponse() {
return tbkItemGetResponse;
}

public void setTbkItemGetResponse(TbkItemGetResponse tbkItemGetResponse) {
this.tbkItemGetResponse = tbkItemGetResponse;
}

}