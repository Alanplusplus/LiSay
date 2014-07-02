package com.listen.say;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.listen.say.adapter.AuctionListAdapter;
import com.listen.say.utils.LruImageCache;
import com.listen.say.utils.NavigationBarUtil;

public class AuctionActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		RequestQueue bitmapQueue = Volley.newRequestQueue(getActivity());
		LruImageCache imageCache = LruImageCache.instance();
		ImageLoader imageLoader = new ImageLoader(bitmapQueue, imageCache);
		AuctionListAdapter adapter = new AuctionListAdapter(inflater, null,
				imageLoader);
		View view = inflater.inflate(R.layout.activity_auction, null);
		List<Object> dataList = new ArrayList<Object>();

		for (int i = 0; i < 15; i++) {
			dataList.add("auction " + i);
		}
		adapter.setData(dataList);
		ListView listView = (ListView) view.findViewById(R.id.auctionlist);
		listView.setAdapter(adapter);
		setNavigationBar(view.findViewById(R.id.navigationbar));
		PrintLog.log(getClass().getName() + ":oncreateview");
		return view;
	}

	private void setNavigationBar(View navigationBar) {
		NavigationBarUtil.setNavigationBar(navigationBar, 0,
				R.drawable.ic_navi_auctioninfo, R.string.auction);
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
