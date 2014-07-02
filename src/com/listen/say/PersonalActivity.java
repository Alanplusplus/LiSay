package com.listen.say;

import com.listen.say.utils.NavigationBarUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PersonalActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_personal, null);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.testitem);
		for (int i = 0; i < 15; i++) {
			adapter.add("���� " + i);
		}
		ListView listView = (ListView) view.findViewById(R.id.personal);
		listView.setAdapter(adapter);
		setNavigationBar(view.findViewById(R.id.navigationbar));
		PrintLog.log(getClass().getName() + ":oncreateview");
		return view;
	}

	private void setNavigationBar(View navigationBar) {
		NavigationBarUtil.setNavigationBar(navigationBar, 0, 0, R.string.my);
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
