package com.listen.say;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class TimelineActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_timeline, null);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.testitem);
		for (int i = 0; i < 15; i++) {
			adapter.add("²âÊÔ " + i);
		}
		ListView listView = (ListView) view.findViewById(R.id.timeline);
		listView.setAdapter(adapter);
		setNavigationBar(view.findViewById(R.id.navigationbar));
		PrintLog.log(getClass().getName() + "oncreateview");
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				PrintLog.log("onitemclick:" + position);

				startActivity(new Intent(getActivity(),
						PostDetailActivity.class));
			}
		});
		return view;
	}

	private void setNavigationBar(View navigationBar) {
		Button left = (Button) navigationBar.findViewById(R.id.navi_left);
		left.setVisibility(View.INVISIBLE);
		Button right = (Button) navigationBar.findViewById(R.id.navi_right);
		right.setBackgroundResource(R.drawable.navi_edit);
		right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MakePostActivity.class);
				startActivity(intent);
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
