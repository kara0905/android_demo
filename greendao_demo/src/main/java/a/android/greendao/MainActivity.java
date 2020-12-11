package a.android.greendao;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import a.android.greendao.dao.PersonDao;
import a.android.greendao.dao.table.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add, btn_remove, btn_query, btn_clear;

    private EditText et_name;
    private TextView tv_content;
    private Person person;
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_remove = findViewById(R.id.btn_remove);
        btn_query = findViewById(R.id.btn_query);
        btn_clear = findViewById(R.id.btn_clear);
        et_name = findViewById(R.id.et_name);
        tv_content = findViewById(R.id.tv_content);

        btn_add.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        String packageResourcePath = getPackageResourcePath();
        Log.e(App.TAG, "onCreate: " + packageResourcePath);
    }

    @Override
    public void onClick(View view) {
        String name = et_name.getText().toString();

        switch (view.getId()) {
            case R.id.btn_add:
                if (TextUtils.isEmpty(name))
                    return;
                person = App.getPersonDao().queryBuilder().where(PersonDao.Properties.Name.eq(name)).build().unique();
                if (person == null) {
                    person = new Person();
                    person.setName(name);
                    App.getPersonDao().insert(person);
                }
                break;

            case R.id.btn_remove:
                if (TextUtils.isEmpty(name))
                    return;
                person = App.getPersonDao().queryBuilder().where(PersonDao.Properties.Name.eq(name)).build().unique();
                if (person != null) {
                    App.getPersonDao().deleteByKey(person.getId());
                }

                break;
            case R.id.btn_query:
                StringBuilder content = new StringBuilder();
                personList = App.getPersonDao().queryBuilder().list();
                for (Person person : personList) {
                    content.append(person.toString()).append("\n");
                }
                tv_content.setText(content.toString());
                break;
            case R.id.btn_clear:
                App.getPersonDao().deleteAll();
                break;
        }
    }
}
