package com.flatverse.stc.graphics;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.PointF;

public abstract class MultiPointDrawable extends STCDrawable {

	public abstract void draw(Canvas canvas, List<PointF> points);
}
