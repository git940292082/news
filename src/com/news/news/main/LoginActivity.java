package com.news.news.main;

import java.util.Map;

import com.news.news.R;
import com.news.news.app.App;
import com.news.news.server.PreferencesService;
import com.news.news.server.RegLogin_Server;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {
	// 【功能目标】
	// 假设正确的用户名和密码分别是admin | admin888
	// 当用户输入的用户名不足4位，且密码不足6位时，按钮时禁用的
	// 当用户名和密码的长度均达到标准，则启用按钮
	// 点击“登录”按钮进行验证，并使用Toast提示结果

	// 【开发步骤】
	// 1. 声明全局变量：“登录”按钮控件，输入“用户名”和“密码”的输入框控件
	// 【开发步骤--新】
	// (新)1. 声明全局变量：2个输入框控件
	private Button btnLogin;
	private EditText etUsername;
	private EditText etPassword;
	private TextView achieve;
	private PreferencesService perfer;
	private CheckBox check_jizhu;
	private AlertDialog mDialog;
	private CheckBox check_zidong;
	private String username;
	private String password;
	private TextView forPwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		perfer=new PreferencesService(this);
		Map<String, Object> login=perfer.getUserPerferences();
		// 2.1. 初始化以上声明的所有控件
		// (新)2. 初始化以上声明的所有变量：2个输入框控件
		btnLogin = (Button) findViewById(R.id.lg_btn_login);
		etUsername = (EditText) findViewById(R.id.lg_et_username);
		etPassword = (EditText) findViewById(R.id.lg_et_password);
		//记住密码
		check_jizhu=(CheckBox)findViewById(R.id.checkBox1);
		//自动登录
		check_zidong=(CheckBox)findViewById(R.id.CheckBox2);
		etUsername.setText((String)login.get("name"));
		etPassword.setText((String)login.get("pwd"));
		//判断是不是自动登录
		if ((Boolean) login.get("boolean")) {
			App.user.setUser((String) login.get("name"));
			App.user.setEmail((String) login.get("classs"));
			App.user.setPass((String) login.get("pwd"));
			startActivity(new Intent(LoginActivity.this, MainActivity.class));
			finish();
		}
		achieve=(TextView)findViewById(R.id.lg_tv_rg);
		forPwd=(TextView)findViewById(R.id.lg_tv_forget);
		forPwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginActivity.this,ForGetPwdActivity1.class));
			}
		});
		//注册按钮的点击事件
		achieve.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
			}
		});
		//自动登录状态改变时，改变记住密码的状态
		check_zidong.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)  check_jizhu.setChecked(true);
			}
		});
		//判断如果自动登录被点击的话，提示请取消自动登录
		check_jizhu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (check_zidong.isChecked()) {
					check_jizhu.setChecked(true);
					Toast.makeText(LoginActivity.this,"请取消勾选自动登录", Toast.LENGTH_SHORT).show();
				}
			}
		});
		// 2.2. 将按钮默认禁用
		btnLogin.setEnabled(false);
		String username = etUsername.getText().toString().trim();
		String password = etPassword.getText().toString();
		if (password.length() >= 3) {
			btnLogin.setEnabled(true);
		}
		// (新)4. 创建监听器(实现了接口的内部类)的对象
		InnerTextWatcher watcher = new InnerTextWatcher();
		// (新)5. 为2个输入框配置监听器
		etUsername.addTextChangedListener(watcher);
		etPassword.addTextChangedListener(watcher);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 4. 创建监听器(成员内部类)对象
		InnerOnClickListener listener = new InnerOnClickListener();
		// 5. 为按钮配置监听器
		btnLogin.setOnClickListener(listener);
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
			btnLogin.setEnabled(password.length() >= 3);

		}

	}

	// 3. 使用成员内部类的语法，实现OnClickListener接口
	private class InnerOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			mDialog = new AlertDialog.Builder(LoginActivity.this).create();
			mDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
			mDialog.show();
			// 注意此处要放在show之后 否则会报异常
			mDialog.setContentView(R.layout.loading_process_dialog_anim);
			// 【监听方法】
			// 1. 准备初始数据
			// -- 从输入框控件中获取用户输入的用户名和密码
			username = etUsername.getText().toString().trim();
			password = etPassword.getText().toString();
			// -- 判断用户输入的用户名和密码是否正确，并运算出结果

			String msg =new RegLogin_Server(getApplicationContext()).login(username, password);

			// 3. 提示结果
			// -- 使用Toast提示结果
			Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
			if (msg.equals("登录成功！")) {
				if (check_zidong.isChecked()) {
					perfer.saveUser(username, password,App.user.getEmail(),true);
				}else {
					if (check_jizhu.isChecked()) perfer.saveUser(username, password, App.user.getEmail(),false);
					else perfer.saveUser(username, "",App.user.getEmail(), false);
				}
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
				finish();
			}
			mDialog.dismiss();
		}

	}

}
