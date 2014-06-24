package com.listen.say;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

public class LisayMainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lisay_main);

		TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.tab1, new TimelineActivity());
		ft.add(R.id.tab2, new AuctionActivity());
		ft.add(R.id.tab3, new PersonalActivity());
		ft.commit();
		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator(getCurrentView(R.drawable.tab_discovery))
				.setContent(R.id.tab1));
		tabHost.addTab(tabHost.newTabSpec("tab2")
				.setIndicator(getCurrentView(R.drawable.tab_download))
				.setContent(R.id.tab2));
		tabHost.addTab(tabHost.newTabSpec("tab3")
				.setIndicator(getCurrentView(R.drawable.tab_personal))
				.setContent(R.id.tab3));
		// View firstView = tabHost.findViewById(R.id.tab1);
		//
		// ListView view = (ListView) firstView.findViewById(R.id.timeline);
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// R.layout.testitem);
		// for (int i = 0; i < 15; i++) {
		// adapter.add("²âÊÔ " + i);
		// }
		// view.setAdapter(adapter);
	}

	private View getCurrentView(int iconResourceId) {
		View view = LayoutInflater.from(this).inflate(R.layout.bottom_tab_item,
				null);
		View imageView = view.findViewById(R.id.bottom_tab_icon);
		imageView.setBackgroundResource(iconResourceId);
		return view;
	}
}
