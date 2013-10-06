package com.flatverse.stc.graphics;

import android.graphics.Color;
import android.graphics.Paint;

public abstract class STCDrawable {
	public float scale = 1;
	public Paint localPaint = new Paint();
	
	public STCDrawable() {
		localPaint.setColor(Color.WHITE);
	}
	
	public abstract void update();
}
