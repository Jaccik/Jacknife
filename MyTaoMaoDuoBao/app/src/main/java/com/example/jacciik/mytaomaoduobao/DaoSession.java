package com.example.jacciik.mytaomaoduobao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.jacciik.mytaomaoduobao.Bean.RecordBean;

import com.example.jacciik.mytaomaoduobao.RecordBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig recordBeanDaoConfig;

    private final RecordBeanDao recordBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        recordBeanDaoConfig = daoConfigMap.get(RecordBeanDao.class).clone();
        recordBeanDaoConfig.initIdentityScope(type);

        recordBeanDao = new RecordBeanDao(recordBeanDaoConfig, this);

        registerDao(RecordBean.class, recordBeanDao);
    }
    
    public void clear() {
        recordBeanDaoConfig.clearIdentityScope();
    }

    public RecordBeanDao getRecordBeanDao() {
        return recordBeanDao;
    }

}
