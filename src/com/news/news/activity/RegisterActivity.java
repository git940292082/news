package com.news.news.activity;
import org.json.JSONException;
import org.json.JSONObject;

import com.news.news.R;
import com.news.news.server.RegLogin_Server;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	// 【功能目标】
	// 假设正确的用户名和密码分别是admin | admin888
	// 当用户输入的用户名不足4位，且密码不足6位时，按钮时禁用的
	// 当用户名和密码的长度均达到标准，则启用按钮
	// 点击“登录”按钮进行验证，并使用Toast提示结果

	// 【开发步骤】
	// 1. 声明全局变量：“登录”按钮控件，输入“用户名”和“密码”的输入框控件
	// 【开发步骤--新】
	// (新)1. 声明全局变量：2个输入框控件
	private Button btnAchieve;
	private EditText etUsername;
	private EditText etPassword;
	private EditText etRePassword;
	private TextView title_mid;
	private ImageView title_left;
	private EditText etEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		//设置标题栏--start
		//标题栏开始
		title_mid=(TextView)findViewById(R.id.title_mid);
		title_mid.setText("新用户注册");
		title_left=(ImageView)findViewById(R.id.title_left);
		title_left.setVisibility(View.VISIBLE);
		//标题栏结束
		title_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		//设置标题栏--end
		// 2.1. 初始化以上声明的所有控件
		// (新)2. 初始化以上声明的所有变量：2个输入框控件
		btnAchieve = (Button) findViewById(R.id.rg_btn_achieve);
		etUsername = (EditText) findViewById(R.id.rg_et_username);
		etEmail = (EditText) findViewById(R.id.rg_et_email);
		etPassword = (EditText) findViewById(R.id.rg_et_password);
		etRePassword = (EditText) findViewById(R.id.rg_et_repassword);
		// 2.2. 将按钮默认禁用
		btnAchieve.setEnabled(false);

		// 4. 创建监听器(成员内部类)对象
		InnerOnClickListener listener = new InnerOnClickListener();
		// (新)4. 创建监听器(实现了接口的内部类)的对象
		InnerTextWatcher watcher = new InnerTextWatcher();

		// 5. 为按钮配置监听器
		btnAchieve.setOnClickListener(listener);
		// (新)5. 为2个输入框配置监听器
		etUsername.addTextChangedListener(watcher);
		etPassword.addTextChangedListener(watcher);
		etRePassword.addTextChangedListener(watcher);
	}

	// (新)3. 自定义内部类，实现TextWatcher接口
	private class InnerTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			// (新)【实现监听方法】
			String username = etUsername.getText().toString().trim();
			String password = etPassword.getText().toString();
			String repassword = etRePassword.getText().toString();
			btnAchieve.setEnabled( password.length() >= 3&& repassword.length() >= 3);

		}

	}

	// 3. 使用成员内部类的语法，实现OnClickListener接口
	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 【监听方法】
			// 1. 准备初始数据
			// -- 从输入框控件中获取用户输入的用户名和密码
			String username = etUsername.getText().toString().trim();
			String email = etEmail.getText().toString().trim();
			String password = etPassword.getText().toString().trim();
			String repassword = etRePassword.getText().toString().trim();
			String msg;

			// -- 判断用户输入的用户名和密码是否正确，并运算出结果
			if (password.equals(repassword)) {
				RegLogin_Server server=new RegLogin_Server(RegisterActivity.this);
				String s=server.register(username,password,email);
				msg=s;
			} else {
				msg = "两次输入密码不一样";
			}

			// 3. 提示结果
			// -- 使用Toast提示结果
			Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
			if(msg.equals("注册成功")){
				startActivity(new Intent(RegisterActivity.this,MainActivity.class));
				finish();
			}
		}

	}
}
