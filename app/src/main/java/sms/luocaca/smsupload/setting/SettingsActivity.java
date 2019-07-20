package sms.luocaca.smsupload.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sms.luocaca.smsupload.R;
import sms.luocaca.smsupload.util.SPUtil;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatActivity {


    private EditText url_edit;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_setting);


        url_edit = findViewById(R.id.url_edit);
        textView = findViewById(R.id.textView);

        String host = SPUtil.get(this, "host", "").toString();
        if (!TextUtils.isEmpty(host))
            textView.setText(host);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_edit.setText(textView.getText());
            }
        });


        url_edit_para = findViewById(R.id.url_edit_para);
        textView1 = findViewById(R.id.textView1);


        String param = SPUtil.get(this, "param", "").toString();
        if (!TextUtils.isEmpty(param))
            textView1.setText(param);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url_edit_para.setText(textView1.getText());
            }
        });

    }


    public static void start(Context context) {
        Intent starter = new Intent(context, SettingsActivity.class);
        context.startActivity(starter);
    }

    public void 保存上传地址(View view) {

        if (TextUtils.isEmpty(url_edit.getText())) {
            Toast.makeText(this, "保存地址要填写", Toast.LENGTH_SHORT).show();
            return;
        }
        SPUtil.put(this, "host", url_edit.getText().toString());
        textView.setText(url_edit.getText().toString());


    }


    private EditText url_edit_para;
    private TextView textView1;

    public void 设置上传的参数(View view) {
        if (TextUtils.isEmpty(url_edit_para.getText())) {
            Toast.makeText(this, "保存地址要填写", Toast.LENGTH_SHORT).show();
            return;
        }
        SPUtil.put(this, "param", url_edit_para.getText().toString());
        textView1.setText(url_edit_para.getText().toString());

    }
}
