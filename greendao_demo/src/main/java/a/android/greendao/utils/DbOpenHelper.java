package a.android.greendao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.StandardDatabase;
import a.android.greendao.dao.PersonDao;
import a.android.greendao.dao.DaoMaster;
import a.android.greendao.dao.DaoSession;
public class DbOpenHelper extends DaoMaster.DevOpenHelper {

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //切记不要调用super.onUpgrade(db,oldVersion,newVersion)
        if (oldVersion < newVersion) {
            //  DbBackUpHelper.getInstance().migrate(db, TransactionBlackBeanDao.class);
            MigrationHelper.migrate(new StandardDatabase(db),
                    PersonDao.class);
        }


    }
}
