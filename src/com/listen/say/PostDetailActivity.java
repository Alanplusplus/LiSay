package com.listen.say;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PostDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.post_detail);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.testitem);
		for (int i = 0; i < 15; i++) {
			adapter.add("»Ø¸´ " + i);
		}
		ListView listView = (ListView) findViewById(R.id.replylist);
		listView.addHeaderView(LayoutInflater.from(this).inflate(
				R.layout.testhead, null));
		listView.setAdapter(adapter);

		setNavigationBar(findViewById(R.id.navigationbar));
	}

	private void setNavigationBar(View navigationBar) {
		Button right = (Button) navigationBar.findViewById(R.id.navi_right);
		right.setVisibility(View.INVISIBLE);
		
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
	
	public void comment(View v)
	{
		PrintLog.log(getClass().getName() + ":comment");
	}
	
	public void praise(View v) {
		PrintLog.log(getClass().getName() + ":praise");
	}
}
