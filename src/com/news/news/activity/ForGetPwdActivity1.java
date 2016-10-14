package com.news.news.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by admin on 2016/9/7.
 */
import com.news.news.R;
import com.news.news.server.RegLogin_Server;

public class ForGetPwdActivity1 extends Activity implements View.OnClickListener{
    private TextView title_mid;
    private ImageView title_left;
    private Button btnAchieve;
    private EditText etUsername;
    private EditText etClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpwd1);
        //璁剧疆鏍囬锟�-start
        //鏍囬鏍忓紑锟�
        title_mid=(TextView)findViewById(R.id.title_mid);
        title_mid.setText("纭鐢ㄦ埛淇℃伅");
        title_left=(ImageView)findViewById(R.id.title_left);
        title_left.setVisibility(View.VISIBLE);
        //鏍囬鏍忕粨锟�
        title_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //璁剧疆鏍囬锟�-end
        btnAchieve = (Button) findViewById(R.id.fgpwd_btn_achieve);
        etUsername = (EditText) findViewById(R.id.fgpwd_et_username);
        etClass = (EditText) findViewById(R.id.fgpwd_et_class);
        // 5. 涓烘寜閽厤缃洃鍚櫒
        btnAchieve.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String username = etUsername.getText().toString().trim();
        String classs = etClass.getText().toString().trim();
        long a=-1;
        if (username.equals("")){
            Toast.makeText(getApplicationContext(), "鐢ㄦ埛鍚嶄笉鑳戒负锟�", Toast.LENGTH_SHORT).show();
        }else{
            RegLogin_Server server=new RegLogin_Server(getApplicationContext());
            a=server.forGetPwd(username,classs);
        }
        if (!(a<0)){
            startActivity(new Intent(getApplicationContext(), ForGetPwdActivity2.class).putExtra("id", a).putExtra("name", username).putExtra("class", classs));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "鐢ㄦ埛鍚嶆垨鑰呴偖绠变笉姝ｇ‘", Toast.LENGTH_SHORT).show();
        }
    }

}
