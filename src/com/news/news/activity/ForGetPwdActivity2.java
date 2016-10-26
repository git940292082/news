package com.news.news.activity;

import com.news.news.R;
import com.news.news.database.RegLoginDao;
import com.news.news.entity.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 2016/9/7.
 */
public class ForGetPwdActivity2 extends Activity implements View.OnClickListener{
    private TextView title_mid;
    private ImageView title_left;
    private Button btnAchieve;
    private EditText etPwd;
    private String name;
    private String email;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpwd2);
        name=getIntent().getStringExtra("name");
        id=getIntent().getLongExtra("id",-1);
        email=getIntent().getStringExtra("class");
        //���ñ�����--start
        //��������ʼ
        title_mid=(TextView)findViewById(R.id.title_mid);
        title_mid.setText("�޸�����");
        title_left=(ImageView)findViewById(R.id.title_left);
        title_left.setVisibility(View.VISIBLE);
        //����������
        title_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //���ñ�����--end
        btnAchieve = (Button) findViewById(R.id.fgpwd2_btn_achieve);
        etPwd = (EditText) findViewById(R.id.fgpwd2_et_pwd);
        // 5. Ϊ��ť���ü�����
        btnAchieve.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String pwd = etPwd.getText().toString().trim();
        RegLoginDao reg=new RegLoginDao(getApplicationContext());
        User user=new User();
        user.setId(id);
        user.setEmail(email);
        user.setPass(pwd);
        user.setName(name);
        reg.update(user);
        finish();
    }

}
