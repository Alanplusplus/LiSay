package com.listen.say.view.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;

public class RoundNetImageView extends View {

	private static final int ERROR = 1;
	private static final int SUCCESS = 2;
	private int mDefaultImageRes = 0;
	private String mUrl;

	private final Paint mPaint = new Paint();

	private final Paint mDefaultPaint = new Paint();

	private int mImageState = ERROR;

	private ImageLoader mImageLoader;

	private ImageContainer mImageContainer;

	private Bitmap mDefaultBitmap;

	private DrawFilter mFilter = new PaintFlagsDrawFilter(0,
			Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

	private final Rect mBoundRect = new Rect();

	private int mBitmapWidth = 1;
	private int mBitmapHeight = 1;

	public RoundNetImageView(Context context) {
		super(context);

	}

	public RoundNetImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundNetImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setDefaultImageRes(int res) {
		mDefaultImageRes = res;
	}

	public void setImageUrl(String url, ImageLoader imageLoader) {
		mUrl = url;
		mImageState = ERROR;
		mImageLoader = imageLoader;
		loadImageIfNecessary(false);
	}

	private void setDefaultImageOrNull() {
		mImageState = ERROR;
	}

	/**
	 * Loads the image for the view if it isn't already loaded.
	 * 
	 * @param isInLayoutPass
	 *            True if this was invoked from a layout pass, false otherwise.
	 */
	void loadImageIfNecessary(final boolean isInLayoutPass) {
		int width = getWidth();
		int height = getHeight();

		boolean wrapWidth = false, wrapHeight = false;
		if (getLayoutParams() != null) {
			wrapWidth = getLayoutParams().width == LayoutParams.WRAP_CONTENT;
			wrapHeight = getLayoutParams().height == LayoutParams.WRAP_CONTENT;
		}

		// if the view's bounds aren't known yet, and this is not a
		// wrap-content/wrap-content
		// view, hold off on loading the image.
		boolean isFullyWrapContent = wrapWidth && wrapHeight;
		if (width == 0 && height == 0 && !isFullyWrapContent) {
			return;
		}

		// if the URL to be loaded in this view is empty, cancel any old
		// requests and clear the
		// currently loaded image.
		if (TextUtils.isEmpty(mUrl)) {
			if (mImageContainer != null) {
				mImageContainer.cancelRequest();
				mImageContainer = null;
			}
			setDefaultImageOrNull();
			return;
		}

		// if there was an old request in this view, check if it needs to be
		// canceled.
		if (mImageContainer != null && mImageContainer.getRequestUrl() != null) {
			if (mImageContainer.getRequestUrl().equals(mUrl)) {
				// if the request is from the same URL, return.
				return;
			} else {
				// if there is a pre-existing request, cancel it if it's
				// fetching a different URL.
				mImageContainer.cancelRequest();
				setDefaultImageOrNull();
			}
		}

		// Calculate the max image width / height to use while ignoring
		// WRAP_CONTENT dimens.
		int maxWidth = wrapWidth ? 0 : width;
		int maxHeight = wrapHeight ? 0 : height;

		// The pre-existing content of this view didn't match the current URL.
		// Load the new image
		// from the network.
		ImageContainer newContainer = mImageLoader.get(mUrl,
				new ImageListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if (mImageState != ERROR) {
							mImageState = ERROR;
							invalidate();
						}
					}

					@Override
					public void onResponse(final ImageContainer response,
							boolean isImmediate) {
						// If this was an immediate response that was delivered
						// inside of a layout
						// pass do not set the image immediately as it will
						// trigger a requestLayout
						// inside of a layout. Instead, defer setting the image
						// by posting back to
						// the main thread.
						if (isImmediate && isInLayoutPass) {
							post(new Runnable() {
								@Override
								public void run() {
									onResponse(response, false);
								}
							});
							return;
						}

						if (response.getBitmap() != null) {
							// setImageBitmap(response.getBitmap());
							mPaint.setShader(new BitmapShader(response
									.getBitmap(), TileMode.CLAMP,
									TileMode.CLAMP));
							mImageState = SUCCESS;
							mBitmapWidth = response.getBitmap().getWidth();
							mBitmapHeight = response.getBitmap().getHeight();
						} else if (mDefaultImageRes != 0) {
							mImageState = ERROR;
						}
						invalidate();
					}
				}, maxWidth, maxHeight);

		// update the ImageContainer to be the new bitmap container.
		mImageContainer = newContainer;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.setDrawFilter(mFilter);
		canvas.save();
		if (mImageState == ERROR) {
			drawDefault(canvas);
		} else {
			drawRoundBitmap(canvas);
		}
		canvas.restore();
	}

	private void drawDefault(Canvas canvas) {
		if (mDefaultImageRes != 0) {
			if (mDefaultBitmap == null || mDefaultBitmap.isRecycled()) {
				mDefaultBitmap = loadBitmap(mDefaultImageRes);
			}

			canvas.drawBitmap(mDefaultBitmap, null, mBoundRect, mDefaultPaint);
		}
	}

	private Bitmap loadBitmap(int resId) {
		return BitmapFactory.decodeResource(getResources(), resId);
	}

	private void drawRoundBitmap(Canvas canvas) {
		int bWidth = mBitmapWidth;
		int bHeight = mBitmapHeight;
		int d = Math.min(bWidth, bHeight);
		if (d == 0) {// impossible
			return;
		}
		float radius = d / 2.0f;
		int width = mBoundRect.width();
		float scale = width / (float) d;
		float xOffset = (radius + bWidth / 2.0f) / 2;
		float yOffset = (radius + bHeight / 2.0f) / 2;
		int cnt = canvas.save();
		canvas.scale(scale, scale, mBoundRect.centerX(), mBoundRect.centerY());
		canvas.translate(mBoundRect.centerX() - xOffset, mBoundRect.centerY()
				- yOffset);

		canvas.drawCircle(xOffset, yOffset, radius, mPaint);
		canvas.restoreToCount(cnt);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		mBoundRect.set(0, 0, right - left, bottom - top);
		loadImageIfNecessary(true);
	}

	@Override
	protected void onDetachedFromWindow() {
		if (mImageContainer != null) {
			// If the view was bound to an image request, cancel it and clear
			// out the image from the view.
			mImageContainer.cancelRequest();
			if (mDefaultBitmap != null && !mDefaultBitmap.isRecycled()) {
				mDefaultBitmap.recycle();
				mDefaultBitmap = null;
			}
			// also clear out the container so we can reload the image if
			// necessary.
			mImageContainer = null;
		}
		super.onDetachedFromWindow();
	}
}
