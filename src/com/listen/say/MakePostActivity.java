package com.listen.say;

import com.listen.say.utils.NavigationBarUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MakePostActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_makepost);

		setNavigationBar(findViewById(R.id.navigationbar));
	}

	private void setNavigationBar(View navigationBar) {
		NavigationBarUtil.setNavigationBar(navigationBar, R.drawable.ic_navi_back, R.drawable.navi_chatroom, R.string.my);
		ImageView left = (ImageView) navigationBar.findViewById(R.id.navi_left);
		left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onPause() {
		PrintLog.log(getClass().getName() + ":onpause");
		super.onPause();
	}

	@Override
	public void onResume() {
		PrintLog.log(getClass().getName() + ":onresume");
		super.onResume();
	}

	@Override
	public void onStop() {
		PrintLog.log(getClass().getName() + ":onstop");
		super.onStop();
	}
}
