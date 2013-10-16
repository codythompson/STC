package com.flatverse.stc.graphics;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public abstract class STCDrawable {
	public float scale = 1;
	public Paint localPaint = new Paint();
	public PointF offset = new PointF(0, 0);
	
	public STCDrawable() {
		localPaint.setColor(Color.WHITE);
	}
	
	public abstract void update();
}
