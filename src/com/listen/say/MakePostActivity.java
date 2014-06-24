package com.listen.say;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MakePostActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_makepost);

		setNavigationBar(findViewById(R.id.navigationbar));
	}

	private void setNavigationBar(View navigationBar) {
		Button right = (Button) navigationBar.findViewById(R.id.navi_right);
		right.setBackgroundResource(R.drawable.navi_chatroom);

		Button left = (Button) navigationBar.findViewById(R.id.navi_left);
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
