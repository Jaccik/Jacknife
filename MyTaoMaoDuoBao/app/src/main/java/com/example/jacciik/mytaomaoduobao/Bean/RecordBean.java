package com.example.jacciik.mytaomaoduobao.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Jacciik on 2017/8/8.
 */
@Entity
public class RecordBean {
    @Id(autoincrement = true) private Long id;
    private String name;
    @Generated(hash = 386345896)
    public RecordBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 96196931)
    public RecordBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
