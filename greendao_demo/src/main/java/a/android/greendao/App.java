package a.android.greendao;

import android.app.Application;

import org.greenrobot.greendao.query.QueryBuilder;

import a.android.greendao.utils.DbOpenHelper;
import a.android.greendao.dao.PersonDao;
import a.android.greendao.dao.DaoMaster;
import a.android.greendao.dao.DaoSession;


public class App extends Application {

    private static PersonDao personDao;
    public static final String TAG = "GREEN_DAO";
    private boolean encrypt = false;//是否加密数据库

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

        DbOpenHelper devOpenHelper = new DbOpenHelper(application, "greendao.db", null);
        DaoMaster daoMaster;
        if (encrypt) {
            daoMaster = new DaoMaster(devOpenHelper.getEncryptedWritableDb("www"));
        } else {
            daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        }
        setDebug(true);//是否显示日志
        DaoSession daoSession = daoMaster.newSession();
        personDao = daoSession.getPersonDao();

    }

    /**
     * 设置debug模式开启或关闭，默认关闭
     *
     * @param flag
     */
    public void setDebug(boolean flag) {
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }
}
