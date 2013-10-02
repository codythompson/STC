package com.flatverse.stc;

import android.graphics.Canvas;

public interface GameObject {
	public void update();
	public void draw(Canvas canvas, Camera camera);
}
