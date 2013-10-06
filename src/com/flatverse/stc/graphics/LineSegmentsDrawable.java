package com.flatverse.stc.graphics;

import java.util.List;

import com.flatverse.stc.util.PointUtils;

import android.graphics.Canvas;
import android.graphics.PointF;

public class LineSegmentsDrawable extends MultiPointDrawable {
	private PointF start = new PointF(0, 0);
	private PointF end = new PointF(0, 0);
	
	public LineSegmentsDrawable(int color) {
		localPaint.setColor(color);
	}
	
	public LineSegmentsDrawable(float width) {
		localPaint.setStrokeWidth(width);
	}
	
	public LineSegmentsDrawable(int color, float width) {
		localPaint.setColor(color);
		localPaint.setStrokeWidth(width);
	}

	@Override
	public void draw(Canvas canvas, List<PointF> points) {
		if (points.size() < 2) {
			return;
		}
		
		for (int i = 1; i < points.size(); i++) {
			PointUtils.setEqual(start, points.get(i - 1));
			PointUtils.setEqual(end, points.get(i));
			canvas.drawLine(start.x, start.y, end.x, end.y, localPaint);
		}
	}

	@Override
	public void update() {
		// nothing to do
	}
}
