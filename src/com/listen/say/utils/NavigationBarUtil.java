package com.listen.say.utils;

import com.listen.say.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NavigationBarUtil {
	public static View setNavigationBar(View navigationBar, int leftRes,
			int rightRes, int titleResId) {
		if (navigationBar == null) {
			return null;
		}
		ImageView left = (ImageView) navigationBar.findViewById(R.id.navi_left);
		if (leftRes == 0) {
			left.setVisibility(View.INVISIBLE);
		} else {
			left.setBackgroundResource(leftRes);
		}
		ImageView right = (ImageView) navigationBar
				.findViewById(R.id.navi_right);
		if (rightRes == 0) {
			right.setVisibility(View.INVISIBLE);

		} else {
			right.setBackgroundResource(rightRes);
		}

		ImageView logo = (ImageView) navigationBar.findViewById(R.id.navi_logo);
		if (titleResId != 0) {
			logo.setVisibility(View.INVISIBLE);
			TextView title = (TextView) navigationBar
					.findViewById(R.id.navi_title);
			title.setVisibility(View.VISIBLE);
			title.setText(titleResId);
		}
		return navigationBar;
	}
}
