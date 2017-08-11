package com.example.jacciik.mytaomaoduobao.DAO;


import com.example.jacciik.mytaomaoduobao.Activity.TaoMaoDuoBaoApp;

public class GreenDaoManager
{
  private DaoMaster mDaoMaster;
  private DaoSession mDaoSession;

  private GreenDaoManager()
  {
      init();
  }

  /**
   * 静态内部类，实例化对象使用
   */
  private static class SingleInstanceHolder
  {
      private static final GreenDaoManager INSTANCE = new GreenDaoManager();
  }

  /**
   * 对外唯一实例的接口
   *
   * @return
   */
  public static GreenDaoManager getInstance()
  {
      return SingleInstanceHolder.INSTANCE;
  }

  /**
   * 初始化数据
   */
  private void init()
  {
      DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(TaoMaoDuoBaoApp.getInstance().getApplicationContext(),
                                                                          "record_bean");
      mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
      mDaoSession = mDaoMaster.newSession();
  }
  public DaoMaster getmDaoMaster()
  {
      return mDaoMaster;
  }
  public DaoSession getmDaoSession()
  {
      return mDaoSession;
  }
  public DaoSession getNewSession()
  {
      mDaoSession = mDaoMaster.newSession();
      return mDaoSession;
  }
}