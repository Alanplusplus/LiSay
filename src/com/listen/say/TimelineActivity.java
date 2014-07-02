package com.listen.say;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.listen.say.adapter.TimelineAdapter;
import com.listen.say.utils.LruImageCache;
import com.listen.say.utils.NavigationBarUtil;

public class TimelineActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		RequestQueue bitmapQueue = Volley.newRequestQueue(getActivity());
		LruImageCache imageCache = LruImageCache.instance();
		ImageLoader imageLoader = new ImageLoader(bitmapQueue, imageCache);
		View view = inflater.inflate(R.layout.activity_timeline, null);
		TimelineAdapter adapter = new TimelineAdapter(inflater, null,
				imageLoader);
		List<Object> dataList = new ArrayList<Object>();
		for (int i = 0; i < 15; i++) {
			dataList.add("测试 " + i);
		}
		adapter.setData(dataList);
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
		String arrayString = "http://api2.qingting.fm/v5/media/categories/96";
		String urlString = "http://api2.qingting.fm/v5/media/topics/5021769?current_time=1403789";
		RequestQueue queue = Volley.newRequestQueue(getActivity());
		queue.add(new JsonObjectRequest(Method.GET, urlString, null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						PrintLog.log(response.toString());
					}
				}, null));
		queue.add(new JsonArrayRequest(arrayString, new Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				Gson gson = new Gson();
				for (int i = 0; i < response.length(); i++) {
					try {
						response.get(i);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				PrintLog.log(response.toString());

			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				PrintLog.log(error.getLocalizedMessage());
			}
		}));
		queue.start();
		return view;
	}

	private class Test {
		private int id;
		private String name;
		private String originalname;
		private String icon;
		String type;
		String itemtype;
		String parentid;
		String parenttype;
		long updatetime;
		int listorder;
		String desc;
		String thumb;
	};

	private void setNavigationBar(View navigationBar) {
		NavigationBarUtil.setNavigationBar(navigationBar, 0,
				R.drawable.ic_navi_new, 0);
		ImageView right = (ImageView) navigationBar
				.findViewById(R.id.navi_right);
		right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						MakePostActivity.class);
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
