package sms.luocaca.smsupload;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import sms.luocaca.smsupload.entity.Sms;
import sms.luocaca.smsupload.setting.SettingsActivity;
import sms.luocaca.smsupload.util.GsonUtil;
import sms.luocaca.smsupload.util.SPUtil;
import sms.luocaca.smsupload.util.SmUtil;

public class MainActivity extends AppCompatActivity {

    TextView content;
    private String host;
    private String param;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        host = SPUtil.get(this, "host", "").toString();
//        param = SPUtil.get(this, "param", "").toString();

        OkHttpUtils.init(this.getApplication());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        content = findViewById(R.id.content);

        requestAllPower();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton upload = (FloatingActionButton) findViewById(R.id.upload);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Snackbar.make(view, "正在获取短信列表获取后才可以上传", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

//                content.setText(SmUtil.getSmsInPhone(view.getContext()));


//                               List<SMSBean> smsBeanList = new RexseeSMS(view.getContext()).getThreads(999);


//                content.append(smsBeanList.size() + "跳");

//                String str  = new RexseeSMS(view.getContext()).getData(null,0);



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<Sms> sms = SmUtil.getSmsInPhoneList(view.getContext());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                content.setText(
                                        GsonUtil.formateJson(GsonUtil.getGson().toJson(sms)));
                            }
                        });
                    }
                }).start();


//                List<SMSBean> list = new RexseeSMS(view.getContext()).getThreads(0);


//                if (sms.size() >= list.size()) {



//                content.setText(new PhoneInfoUtils(view.getContext()).getNativePhoneNumber());

//                } else {
//                    content.setText(
//                            GsonUtil.formateJson(GsonUtil.getGson().toJson(list)));
//                }

//                content.setText(
//                        GsonUtil.formateJson(GsonUtil.getGson().toJson(SmUtil.getSmsInPhoneList(view.getContext())))
//                );


//                content.setText(
//                        GsonUtil.formateJson(GsonUtil.getGson().toJson(new RexseeSMS(view.getContext()).getThreads(0)))
//                );

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (TextUtils.isEmpty(host)) {
                    Snackbar.make(v, "请先,点击右上角设置按钮，设置上传的地址与参数", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                if (content.getText().toString().contains("功能简介")) {
                    Snackbar.make(v, "您好像为点击右下角的获取短信按钮，请先获取", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }


                Snackbar.make(v, "开始上传了哦---请稍等", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                List<String> st = new ArrayList<>();
                st.add(content.getText().toString());


                //参数为设置 直接传body进去
                if (TextUtils.isEmpty(param)) {
                    OkHttpUtils.post(host)
                            .tag(this)
                            .upJson(content.getText().toString())
//                            .addUrlParams("sms",st)
//                            .params("sms","hello")
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    //请求成功
                                    Snackbar.make(v, "上传返回code" + response.code(), Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                }
                            });
                } else {
                    OkHttpUtils.post(host)
                            .tag(this)
//                        .upJson(content.getText().toString())
//                        .addUrlParams("sms",st)
                            .params(param, content.getText().toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    //请求成功
                                    Snackbar.make(v, "上传返回code ：" + response.code(), Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                }
                            });
                }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SettingsActivity.start(MainActivity.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void requestAllPower() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        host = SPUtil.get(this, "host", "").toString();
        param = SPUtil.get(this, "param", "").toString();
    }
}
