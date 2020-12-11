package a.android.greendao;

import android.app.Application;

import a.android.greendao.dao.DaoMaster;
import a.android.greendao.dao.DaoSession;
import a.android.greendao.dao.PersonDao;
import a.android.greendao.utils.DbOpenHelper;

public class App extends Application {

    private static PersonDao personDao;
    public static final String TAG = "GREEN_DAO";

    public static PersonDao getPersonDao() {
        return personDao;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        openDb(this);

    }

    //初始化数据库
    public void openDb(Application application) {
        //用自定义的 DbOpenHelper 继承 DaoMaster.DevOpenHelper
        DbOpenHelper devOpenHelper = new DbOpenHelper(application, "greendao-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        personDao = daoSession.getPersonDao();

    }

}
