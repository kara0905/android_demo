package com.tuacy.annotationlearning;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tuacy.annotationlearning.annotation.abstractprocessor.CalzonePizza;
import com.tuacy.annotationlearning.annotation.abstractprocessor.MealFactory;
import com.tuacy.annotationlearning.annotation.abstractprocessor.People;
import com.tuacy.annotationlearning.annotation.abstractprocessor.PeopleFactory;
import com.tuacy.annotationlearning.annotation.autowired.AutoWired;
import com.tuacy.annotationlearning.annotation.autowired.AutoWiredProcess;
import com.tuacy.annotationlearning.annotation.butterknife.BindString;
import com.tuacy.annotationlearning.annotation.butterknife.BindView;
import com.tuacy.annotationlearning.annotation.butterknife.ButterKnifeProcess;
import com.tuacy.annotationlearning.annotation.butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //自动绑定view
    @BindView(R.id.text_abstract_processor)
    TextView mTextView;

    //自动绑定String
    @BindString(R.string.click_already)
    String mInfo;

    //自动创建对象，不用我们去new UserInfo()了
    @AutoWired
    UserInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnifeProcess.bind(this);
        AutoWiredProcess.bind(this);
        initData();
    }

    /**
     * 绑定点击事件
     */
    @OnClick({R.id.button_click})
    public void buttonOnCLick(View view) {
        switch (view.getId()) {
            case R.id.button_click:
                mTextView.setText(String.format("%s%s", mInfo, mUserInfo.getName()));
                break;
        }
    }

    private void initData() {
        // 我们是没有显示的去new对象的，通过@AutoCreate注解来完成new
        mUserInfo.setName("AutoWired");

        // 测试下工厂类代码自动生成是否成功
        PeopleFactory peopleFactory = new PeopleFactory();
        People people = peopleFactory.create("Female");
        Log.e("MainActivity", people.getName());

        MealFactory calzonePizzaFactory = new MealFactory();
        CalzonePizza c = (CalzonePizza) calzonePizzaFactory.create("CalzonePizza");
        Log.e("MainActivity", ""+c.getPrice());

    }
}
