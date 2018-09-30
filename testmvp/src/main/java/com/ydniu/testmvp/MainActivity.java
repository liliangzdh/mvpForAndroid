package com.ydniu.testmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ydniu.testmvp.present.UserLoginPresenter;
import com.ydniu.testmvp.view.IUserLoginView;

public class MainActivity extends AppCompatActivity implements IUserLoginView {


    private EditText et_userName, et_password;
    private Button bt_login, bt_clear;
    private ProgressBar pb_load;

    private UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        et_userName = (EditText) findViewById(R.id.et_useName);
        et_password = (EditText) findViewById(R.id.et_password);

        bt_login = (Button) findViewById(R.id.bt_login);
        bt_clear = (Button) findViewById(R.id.bt_clear);
        pb_load = (ProgressBar) findViewById(R.id.pb_load);
        pb_load.setVisibility(View.INVISIBLE);


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                userLoginPresenter.login();
            }
        });


        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getName() {
        return et_userName.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    @Override
    public void clearName() {
        et_userName.setText("");
    }

    @Override
    public void clearPassword() {
        et_password.setText("");
    }

    @Override
    public void showDialog() {
        pb_load.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissDialog() {
        pb_load.setVisibility(View.GONE);
    }

    @Override
    public void showFailError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }


}
