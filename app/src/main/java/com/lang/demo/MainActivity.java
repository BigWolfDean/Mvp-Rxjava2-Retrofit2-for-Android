package com.lang.demo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lang.demo.mvp.presenter.InTheaterMoviePresenter;
import com.lang.demo.mvp.view.InTheaterMovieView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etCity, etCount, etStart, etApiKey;

    private TextView tvResponse;

    private Button btnRequest;

    private InTheaterMoviePresenter moviePresenter = new InTheaterMoviePresenter();

    private InTheaterMovieView inTheaterMovieView = new InTheaterMovieView() {
        @Override
        public void setResponse(String response) {
            tvResponse.setText(response);
        }

        @Override
        public void setError(String error) {
            tvResponse.setText(error);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        moviePresenter.onCreate();
        moviePresenter.onStart();
        moviePresenter.attachView(inTheaterMovieView);
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        etCity = findViewById(R.id.et_city);
        etCount = findViewById(R.id.et_count);
        etStart = findViewById(R.id.et_start);
        etApiKey = findViewById(R.id.et_api_key);
        tvResponse = findViewById(R.id.tv_response);
        btnRequest = findViewById(R.id.btn_request);
        btnRequest.setOnClickListener(this);
        etApiKey.setText("0b2bdeda43b5688921839c8ecb20399b");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_request:
                String city = etCity.getText().toString();
                String countString = etCount.getText().toString();
                String startString = etStart.getText().toString();
                String apiKey = etApiKey.getText().toString();
                if (!TextUtils.isEmpty(apiKey) && !TextUtils.isEmpty(city)
                        && !TextUtils.isEmpty(countString)
                        && !TextUtils.isEmpty(startString)) {
                    moviePresenter.getMovieInfo(apiKey, city, Integer.valueOf(startString), Integer.valueOf(countString));
                } else {
                    moviePresenter.setErrorInfo("输入框不可为空");
                }
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        moviePresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moviePresenter.onStop();
    }
}
