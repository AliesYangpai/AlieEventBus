package org.alie.alieeventbus;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        initListener();
    }

    private void initView() {
        btn1 = findViewById(R.id.btn1);
    }

    private void initListener() {
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                goToNextActivity(SecondActivity.class);
                break;
        }
    }

    private void goToNextActivity(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, clazz));
        startActivity(intent);
    }

    @Student.Subscribe(threadMode = ThreadMode.Async)
    public void receive(Student student) {
        Log.i(TAG, "数据：" + student + " 当前线程：" + Thread.currentThread().getId());
    }
}
