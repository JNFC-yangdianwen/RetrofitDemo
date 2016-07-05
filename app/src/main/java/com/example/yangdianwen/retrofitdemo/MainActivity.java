package com.example.yangdianwen.retrofitdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://api.github.com/";
    private ListView listview;
    private ArrayAdapter<JavaBean> arrayAdapter;
    private Retrofit retrofit;
    private Contributions contributions;
    private Call<List<JavaBean>> call;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        listview.setAdapter(arrayAdapter);
        //创建一个retrofit对象
        initRetrofit();
        //创建api接口
        contributions = retrofit.create(Contributions.class);
        //创建一个call模式
        call = contributions.getParamContributions("square","okhttp");
        //执行异步任务
        call.enqueue(new Callback<List<JavaBean>>() {
            //回调callback（执行在ui线程中）
            @Override
            public void onResponse(Call<List<JavaBean>> call, Response<List<JavaBean>> response) {
               List<JavaBean>  body = response.body();
                arrayAdapter.addAll(body);
            }
            @Override
            public void onFailure(Call<List<JavaBean>> call, Throwable t) {

            }
        });
    }
    //初始化retrofit
    @NonNull
    private void initRetrofit() {
        //http://api.github.com/repos/square/retrofit/contributors
       retrofit= new Retrofit
                    .Builder().baseUrl(BASE_URL)
                    //添加数据转换器
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
}
