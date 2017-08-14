package com.example.jacciik.mytaomaoduobao.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("appname")
@Expose
private String appname;
@SerializedName("serverVersion")
@Expose
private String serverVersion;
@SerializedName("serverFlag")
@Expose
private String serverFlag;
@SerializedName("lastForce")
@Expose
private String lastForce;
@SerializedName("updateurl")
@Expose
private String updateurl;
@SerializedName("upgradeinfo")
@Expose
private String upgradeinfo;

public String getAppname() {
return appname;
}

public void setAppname(String appname) {
this.appname = appname;
}

public String getServerVersion() {
return serverVersion;
}

public void setServerVersion(String serverVersion) {
this.serverVersion = serverVersion;
}

public String getServerFlag() {
return serverFlag;
}

public void setServerFlag(String serverFlag) {
this.serverFlag = serverFlag;
}

public String getLastForce() {
return lastForce;
}

public void setLastForce(String lastForce) {
this.lastForce = lastForce;
}

public String getUpdateurl() {
return updateurl;
}

public void setUpdateurl(String updateurl) {
this.updateurl = updateurl;
}

public String getUpgradeinfo() {
return upgradeinfo;
}

public void setUpgradeinfo(String upgradeinfo) {
this.upgradeinfo = upgradeinfo;
}

}