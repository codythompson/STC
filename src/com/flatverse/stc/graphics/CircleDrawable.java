package com.flatverse.stc.graphics;

import android.graphics.Canvas;
import android.graphics.PointF;

public class CircleDrawable extends SinglePointDrawable {
	public float radius;
	
	public CircleDrawable(float radius) {
		this.radius = radius;
	}
	
	public CircleDrawable(float radius, int localColor) {
		this.radius = radius;
		this.localPaint.setColor(localColor);
	}

	@Override
	public void update() {
		//nothing to do
	}

	@Override
	public void draw(Canvas canvas, PointF location) {
		canvas.drawCircle(location.x, location.y, radius, localPaint);
	}

}
