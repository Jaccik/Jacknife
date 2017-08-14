package com.example.jacciik.mytaomaoduobao.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TbkItemGetResponse {

@SerializedName("results")
@Expose
private Results results;
@SerializedName("total_results")
@Expose
private Integer totalResults;
@SerializedName("request_id")
@Expose
private String requestId;

public Results getResults() {
return results;
}

public void setResults(Results results) {
this.results = results;
}

public Integer getTotalResults() {
return totalResults;
}

public void setTotalResults(Integer totalResults) {
this.totalResults = totalResults;
}

public String getRequestId() {
return requestId;
}

public void setRequestId(String requestId) {
this.requestId = requestId;
}

}