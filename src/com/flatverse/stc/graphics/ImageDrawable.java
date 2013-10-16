package com.flatverse.stc.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class ImageDrawable extends SinglePointDrawable {
	private Bitmap picture;
	
	public ImageDrawable(Bitmap picture) {
		this.picture = picture;
	}

	@Override
	public void draw(Canvas canvas, PointF location) {
		canvas.drawBitmap(picture, location.x + offset.x, location.y + offset.y, localPaint);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public int getWidth() {
		return picture.getWidth();
	}
	
	public int getHeight() {
		return picture.getHeight();
	}
}
