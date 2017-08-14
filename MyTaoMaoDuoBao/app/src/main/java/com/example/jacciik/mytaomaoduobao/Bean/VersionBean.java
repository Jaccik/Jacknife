package com.example.jacciik.mytaomaoduobao.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VersionBean {

@SerializedName("data")
@Expose
private Data data;
@SerializedName("error_code")
@Expose
private String errorCode;
@SerializedName("error_msg")
@Expose
private String errorMsg;

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

public String getErrorCode() {
return errorCode;
}

public void setErrorCode(String errorCode) {
this.errorCode = errorCode;
}

public String getErrorMsg() {
return errorMsg;
}

public void setErrorMsg(String errorMsg) {
this.errorMsg = errorMsg;
}

}
