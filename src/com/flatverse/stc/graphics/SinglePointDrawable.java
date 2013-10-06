package com.flatverse.stc.graphics;

import android.graphics.Canvas;
import android.graphics.PointF;


public abstract class SinglePointDrawable extends STCDrawable {
	public abstract void draw(Canvas canvas, PointF location);
}
