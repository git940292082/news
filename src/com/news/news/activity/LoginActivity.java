package com.news.news.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.news.news.R;
import com.news.news.app.App;
import com.news.news.entity.User;
import com.news.news.server.PreferencesService;
import com.news.news.server.RegLogin_Server;
import com.news.news.ui.CircleImageView;
import com.news.news.untils.BitmapUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.utils.AsynLoadImgBack;
import com.tencent.utils.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
	private AlertDialog mDialog;
	private String username;
	private String password;
	private TextView forPwd;
	private Tencent mTencent;
	private ImageButton weibo;
	private ImageButton qq;
	private QQAuth mQQAuth;
	private UserInfo mInfo;
	private User sp_user;
	private User user=new User();
	private RegLogin_Server reLoServer;
	private CircleImageView image;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 1.4版本:此处需新增参数，传入应用程序的全局context，可通过activity的getApplicationContext方法获取
		Instan();
		// 2.1. 初始化以上声明的所有控件
		SetView();
		etUsername.setText(sp_user.getName());
		etPassword.setText(sp_user.getPass());
		if (!(sp_user.getIcon().equals(""))) {
			image.setImageBitmap(BitmapUtils.StringToIcon(sp_user.getIcon()));
		}
		//判断是不是自动登录
		//		App.user.setUser((String) login.get("name"));
		//		App.user.setEmail((String) login.get("email"));
		//		App.user.setPass((String) login.get("pwd"));
		//		startActivity(new Intent(LoginActivity.this, MainActivity.class));
		//		finish();
		achieve=(TextView)findViewById(R.id.lg_tv_rg);
		forPwd=(TextView)findViewById(R.id.lg_tv_forget);

		SetOnClick();
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
	/**
	 *  实例化对象*/
	private void Instan() {
		perfer=new PreferencesService(this);
		sp_user=perfer.getUserPerferences();
		reLoServer=new RegLogin_Server(getApplicationContext());
	}
	@Override
	protected void onStart() {
		super.onStart();
		mQQAuth = QQAuth.createInstance(App.QQID, getApplicationContext());
		mTencent = Tencent.createInstance(App.QQID,getApplicationContext());
	}
	/**
	 *  点击事件*/
	private void SetOnClick() {
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
		qq.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onClickLogin();
			}
		});
	}

	/**
	 *  QQ登录*/
	private void onClickLogin() {
		if (!mQQAuth.isSessionValid()) {
			IUiListener listener = new BaseUiListener() {
				@Override
				protected void doComplete(JSONObject values) {
					updateUserInfo();
				}
			};
			mQQAuth.login(this, "all", listener);
			// mTencent.loginWithOEM(this, "all",
			// listener,"10000144","10000144","xxxx");
			mTencent.login(this, "all", listener);
		} else {
			mQQAuth.logout(this);
			updateUserInfo();
		}
	}
	private void updateUserInfo() {
		if (mQQAuth != null && mQQAuth.isSessionValid()) {
			IUiListener listener = new IUiListener() {

				@Override
				public void onError(UiError e) {
					Toast.makeText(getApplicationContext(), "登录失败",Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onComplete(final Object response) {
					Handler handler=new Handler();
					handler.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								final JSONObject json = (JSONObject) response;
								String name = json.getString("nickname");
								user.setName(name);
								final String string = json.getString("figureurl_qq_2");
								AsyncHttpClient client=new AsyncHttpClient();
								client.get(string, new AsyncHttpResponseHandler() {
									@Override
									public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
										// TODO Auto-generated method stub
										Bitmap bitmap = BitmapFactory.decodeByteArray(arg2, 0, arg2.length);
										user.setIcon(BitmapUtils.IconToString(bitmap));
										try {
											reLoServer.userSave(user);
											finish();
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
											finish();
										}
										
									}
									
									@Override
									public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
										// TODO Auto-generated method stub
										
									}
								});
								Log.i("213", string);
								
							} catch (JSONException e) {

							}
						}
					});
				}
				@Override
				public void onCancel() {
				}
			};
			mInfo = new UserInfo(this, mQQAuth.getQQToken());
			mInfo.getUserInfo(listener);

		} else {
		}
	}
	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			Log.i("TAG",response.toString());
			doComplete((JSONObject) response);
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {
		}

		@Override
		public void onCancel() {
		}
	}
	private void SetView() {
		// (新)2. 初始化以上声明的所有变量：2个输入框控件
		image=(CircleImageView)findViewById(R.id.Ci_image);
		btnLogin = (Button) findViewById(R.id.lg_btn_login);
		etUsername = (EditText) findViewById(R.id.lg_et_username);
		etPassword = (EditText) findViewById(R.id.lg_et_password);
		//记住密码
		//自动登录
		qq=(ImageButton)findViewById(R.id.qq_Button);
		weibo=(ImageButton)findViewById(R.id.weibo_Button);
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
			if (username.equals(sp_user.getName())) {
				image.setImageBitmap(BitmapUtils.StringToIcon(sp_user.getIcon()));
			}else{
				image.setImageResource(R.drawable.iconimage);
			}
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
				finish();
			}
			mDialog.dismiss();
		}
	}

	/**
	 * 根据一个网络连接(String)获取bitmap图像
	 * 
	 * @param imageUri
	 * @return
	 * @throws MalformedURLException
	 */
	public static Bitmap getbitmap(String imageUri) {
		// 显示网络上的图片
		Bitmap bitmap = null;
		try {
			URL myFileUrl = new URL(imageUri);
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}
}
