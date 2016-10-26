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
	// ������Ŀ�꡿
	// ������ȷ���û���������ֱ���admin | admin888
	// ���û�������û�������4λ�������벻��6λʱ����ťʱ���õ�
	// ���û���������ĳ��Ⱦ��ﵽ��׼�������ð�ť
	// �������¼����ť������֤����ʹ��Toast��ʾ���

	// ���������衿
	// 1. ����ȫ�ֱ���������¼����ť�ؼ������롰�û������͡����롱�������ؼ�
	// ����������--�¡�
	// (��)1. ����ȫ�ֱ�����2�������ؼ�
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
		// 1.4�汾:�˴�����������������Ӧ�ó����ȫ��context����ͨ��activity��getApplicationContext������ȡ
		Instan();
		// 2.1. ��ʼ���������������пؼ�
		SetView();
		etUsername.setText(sp_user.getName());
		etPassword.setText(sp_user.getPass());
		if (!(sp_user.getIcon().equals(""))) {
			image.setImageBitmap(BitmapUtils.StringToIcon(sp_user.getIcon()));
		}
		//�ж��ǲ����Զ���¼
		//		App.user.setUser((String) login.get("name"));
		//		App.user.setEmail((String) login.get("email"));
		//		App.user.setPass((String) login.get("pwd"));
		//		startActivity(new Intent(LoginActivity.this, MainActivity.class));
		//		finish();
		achieve=(TextView)findViewById(R.id.lg_tv_rg);
		forPwd=(TextView)findViewById(R.id.lg_tv_forget);

		SetOnClick();
		// 2.2. ����ťĬ�Ͻ���
		btnLogin.setEnabled(false);
		String username = etUsername.getText().toString().trim();
		String password = etPassword.getText().toString();
		if (password.length() >= 3) {
			btnLogin.setEnabled(true);
		}
		// (��)4. ����������(ʵ���˽ӿڵ��ڲ���)�Ķ���
		InnerTextWatcher watcher = new InnerTextWatcher();
		// (��)5. Ϊ2����������ü�����
		etUsername.addTextChangedListener(watcher);
		etPassword.addTextChangedListener(watcher);
	}
	/**
	 *  ʵ��������*/
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
	 *  ����¼�*/
	private void SetOnClick() {
		forPwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginActivity.this,ForGetPwdActivity1.class));
			}
		});
		//ע�ᰴť�ĵ���¼�
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
	 *  QQ��¼*/
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
					Toast.makeText(getApplicationContext(), "��¼ʧ��",Toast.LENGTH_SHORT).show();
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
		// (��)2. ��ʼ���������������б�����2�������ؼ�
		image=(CircleImageView)findViewById(R.id.Ci_image);
		btnLogin = (Button) findViewById(R.id.lg_btn_login);
		etUsername = (EditText) findViewById(R.id.lg_et_username);
		etPassword = (EditText) findViewById(R.id.lg_et_password);
		//��ס����
		//�Զ���¼
		qq=(ImageButton)findViewById(R.id.qq_Button);
		weibo=(ImageButton)findViewById(R.id.weibo_Button);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 4. ����������(��Ա�ڲ���)����
		InnerOnClickListener listener = new InnerOnClickListener();
		// 5. Ϊ��ť���ü�����
		btnLogin.setOnClickListener(listener);
	}

	// (��)3. �Զ����ڲ��࣬ʵ��TextWatcher�ӿ�
	private class InnerTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			// (��)��ʵ�ּ���������
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

	// 3. ʹ�ó�Ա�ڲ�����﷨��ʵ��OnClickListener�ӿ�
	private class InnerOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			mDialog = new AlertDialog.Builder(LoginActivity.this).create();
			mDialog.setCanceledOnTouchOutside(false);// ���õ����ĻDialog����ʧ
			mDialog.show();
			// ע��˴�Ҫ����show֮�� ����ᱨ�쳣
			mDialog.setContentView(R.layout.loading_process_dialog_anim);
			// ������������
			// 1. ׼����ʼ����
			// -- �������ؼ��л�ȡ�û�������û���������
			username = etUsername.getText().toString().trim();
			password = etPassword.getText().toString();
			// -- �ж��û�������û����������Ƿ���ȷ������������

			String msg =new RegLogin_Server(getApplicationContext()).login(username, password);

			// 3. ��ʾ���
			// -- ʹ��Toast��ʾ���
			Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
			if (msg.equals("��¼�ɹ���")) {
				finish();
			}
			mDialog.dismiss();
		}
	}

	/**
	 * ����һ����������(String)��ȡbitmapͼ��
	 * 
	 * @param imageUri
	 * @return
	 * @throws MalformedURLException
	 */
	public static Bitmap getbitmap(String imageUri) {
		// ��ʾ�����ϵ�ͼƬ
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
