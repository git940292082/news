package com.news.news.activity;
import java.io.ByteArrayOutputStream;
import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.entity.User;
import com.news.news.server.PreferencesService;
import com.news.news.server.RegLogin_Server;
import com.news.news.ui.CircleImageView;
import com.news.news.untils.BitmapUtils;
import com.news.news.untils.PhotoUtil;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	// 【功能目标】
	// 假设正确的用户名和密码分别是admin | admin888
	// 当用户输入的用户名不足4位，且密码不足6位时，按钮时禁用的
	// 当用户名和密码的长度均达到标准，则启用按钮
	// 点击“登录”按钮进行验证，并使用Toast提示结果

	private static final int CAMERA_WITH_DATA = 0;
	private static final int CAMERA_CROP_RESULT = 1;
	private static final int ICON_SIZE = 150;
	private static final int PHOTO_PICKED_WITH_DATA = 2;
	private static final int PHOTO_CROP_RESOULT = 3;
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
	private PopupWindow mSetPhotoPop;
	@ViewInject(R.id.Ci_image)
	CircleImageView Ci_image;
	@ViewInject(R.id.Re_Main)
	RelativeLayout Re_Main;
	private File mCurrentPhotoFile;
	private Bitmap imageBitmap;
	private String imgString="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		x.view().inject(this);
		//设置标题栏--start
		//标题栏开始
		title_mid=(TextView)findViewById(R.id.title_mid);
		title_mid.setText("新用户注册");
		title_left=(ImageView)findViewById(R.id.title_left);
		SetOnClick();
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
		Ci_image.setVisibility(View.VISIBLE);
		// 5. 为按钮配置监听器
		btnAchieve.setOnClickListener(listener);
		// (新)5. 为2个输入框配置监听器
		etUsername.addTextChangedListener(watcher);
		etPassword.addTextChangedListener(watcher);
		etRePassword.addTextChangedListener(watcher);
	}

	private void SetOnClick() {
		//标题栏结束
		title_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Ci_image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPop();
			}
		});
	}
	/**
	 *  寮瑰嚭 popupwindow
	 */
	public void showPop(){
		View mainView = LayoutInflater.from(this).inflate(R.layout.alert_setphoto_menu_layout, null);
		Button btnTakePhoto = (Button) mainView.findViewById(R.id.btn_take_photo);
		btnTakePhoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSetPhotoPop.dismiss();
				// 鎷嶇収鑾峰彇
				doTakePhoto();
			}
		});
		Button btnCheckFromGallery = (Button) mainView.findViewById(R.id.btn_check_from_gallery);
		btnCheckFromGallery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSetPhotoPop.dismiss();
				// 鐩稿唽鑾峰彇
				doPickPhotoFromGallery();
			}
		});
		Button btnCancle = (Button) mainView.findViewById(R.id.btn_cancel);
		btnCancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSetPhotoPop.dismiss();
			}
		});
		mSetPhotoPop = new PopupWindow(this);
		mSetPhotoPop.setBackgroundDrawable(new BitmapDrawable());
		mSetPhotoPop.setFocusable(true);
		mSetPhotoPop.setTouchable(true);
		mSetPhotoPop.setOutsideTouchable(true);
		mSetPhotoPop.setContentView(mainView);
		mSetPhotoPop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		mSetPhotoPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		mSetPhotoPop.setAnimationStyle(R.style.bottomStyle);
		mSetPhotoPop.showAtLocation(Re_Main, Gravity.BOTTOM, 0, 0);
		mSetPhotoPop.update();
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
	@Override
	protected void onResume() {
		super.onResume();
		//获取到字符串里的内容并转成图片
		PreferencesService service=new PreferencesService(getApplicationContext());
		User user=service.getUserPerferences();
		BitmapUtils utils=new BitmapUtils();
		String imgString=user.getIcon();
		if (!(imgString.equals(""))) {
			Log.i("TAG", imgString);
			Ci_image.setImageBitmap(utils.StringToIcon(imgString));
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
				String s=server.register(username,password,email,imgString);
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
	/**
	 * 璋冪敤绯荤粺鐩告満鎷嶇収
	 */
	protected void doTakePhoto() {
		try {
			// Launch camera to take photo for selected contact
			File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/Photo");
			if (!file.exists()) {
				file.mkdirs();
			}
			mCurrentPhotoFile = new File(file, PhotoUtil.getRandomFileName());
			final Intent intent = getTakePickIntent(mCurrentPhotoFile);
			startActivityForResult(intent, CAMERA_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, R.string.photoPickerNotFoundText, Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Constructs an intent for capturing a photo and storing it in a temporary
	 * file.
	 */
	public static Intent getTakePickIntent(File f) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		return intent;
	}

	/**
	 * 鐩告満鍓垏鍥剧墖
	 */
	protected void doCropPhoto(File f) {
		try {
			// Add the image to the media store
			MediaScannerConnection.scanFile(this, new String[]{
					f.getAbsolutePath()
			}, new String[]{
					null
			}, null);

			// Launch gallery to crop the photo
			final Intent intent = getCropImageIntent(Uri.fromFile(f));
			startActivityForResult(intent, CAMERA_CROP_RESULT);
		} catch (Exception e) {
			Toast.makeText(this, R.string.photoPickerNotFoundText, Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 鑾峰彇绯荤粺鍓鍥剧墖鐨処ntent.
	 */
	public static Intent getCropImageIntent(Uri photoUri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(photoUri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", ICON_SIZE);
		intent.putExtra("outputY", ICON_SIZE);
		intent.putExtra("return-data", true);
		return intent;
	}

	/**
	 * 浠庣浉鍐岄�夋嫨鍥剧墖
	 */
	protected void doPickPhotoFromGallery() {
		try {
			// Launch picker to choose photo for selected contact
			final Intent intent = getPhotoPickIntent();
			startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, R.string.photoPickerNotFoundText, Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 鑾峰彇璋冪敤鐩稿唽鐨処ntent
	 */
	public static Intent getPhotoPickIntent() {
		Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		return intent;
	}

	/**
	 * 鐩稿唽瑁佸壀鍥剧墖
	 *
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");//璋冪敤Android绯荤粺鑷甫鐨勪竴涓浘鐗囧壀瑁侀〉闈�,
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");//杩涜淇壀
		// aspectX aspectY 鏄楂樼殑姣斾緥
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 鏄鍓浘鐗囧楂�
		intent.putExtra("outputX", ICON_SIZE);
		intent.putExtra("outputY", ICON_SIZE);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTO_CROP_RESOULT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		BitmapUtils utils=new BitmapUtils();
		if (resultCode == RESULT_OK) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			switch (requestCode) {
			case PHOTO_PICKED_WITH_DATA:
				// 鐩稿唽閫夋嫨鍥剧墖鍚庤鍓浘鐗�
				startPhotoZoom(data.getData());
				break;
			case PHOTO_CROP_RESOULT:
				Bundle extras = data.getExtras();
				if (extras != null) {
					imageBitmap = extras.getParcelable("data");
					//imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
					imgString=utils.IconToString(imageBitmap);
					Ci_image.setImageBitmap(imageBitmap);
				}
				break;
			case CAMERA_WITH_DATA:
				// 鐩告満鎷嶇収鍚庤鍓浘鐗�
				doCropPhoto(mCurrentPhotoFile);
				break;
			case CAMERA_CROP_RESULT:
				imageBitmap = data.getParcelableExtra("data");
				imgString=utils.IconToString(imageBitmap);
				// imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				Ci_image.setImageBitmap(imageBitmap);
				break;
			}
		}
	}
}
