package com.listen.say.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.listen.say.R;

public class AuctionListAdapter extends BaseAdapter {

	protected List<Object> data;
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;

	public AuctionListAdapter(LayoutInflater inflater, List<Object> _data,
			ImageLoader imageLoader) {
		mInflater = inflater;
		data = _data;
		mImageLoader = imageLoader;
	}

	public void setData(List<Object> data) {
		this.data = data;
		this.notifyDataSetChanged();
	}

	public List<Object> getData() {
		return this.data;
	}

	public void clear() {
		this.data.clear();
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (this.data == null)
			return 0;
		try {
			return this.data.size();
		} catch (Exception e) {
			return 1;
		}
	}

	@Override
	public Object getItem(int position) {
		if (this.data == null)
			return null;

		if (this.data.size() > position) {
			return this.data.get(position);
		}

		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object value = getItem(position);// this.data.get(position);
		View view = null;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.item_auction, null);
		} else {
			view = convertView;
		}
		if (value != null) {
			TextView nameTextView = (TextView) view.findViewById(R.id.name);
			nameTextView.setText((CharSequence) value);
			NetworkImageView imageView = (NetworkImageView) view
					.findViewById(R.id.thumb);
			imageView.setDefaultImageResId(R.drawable.bg_item_auction);
			imageView.setErrorImageResId(R.drawable.bg_item_auction);
			String url = position % 3 == 0 ? "ddd"
					: "http://res.qingting.fm/uploadfile/2014/0511/20140511100126623.jpg";
			imageView.setImageUrl(url, mImageLoader);
		}

		return view;
	}

}
