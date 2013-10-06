package com.flatverse.stc.graphics;

import com.flatverse.stc.util.PointUtils;

import android.graphics.Canvas;
import android.graphics.PointF;

public class LineSegmentDrawable extends SinglePointDrawable {
	public PointF offset;
	
	//declared and instantiated here so a new object isn't
	//allocated on every draw call
	private PointF tempOffset;
	private PointF point2;
	
	public LineSegmentDrawable(PointF offset) {
		super();
		this.offset = offset;
		tempOffset = new PointF(0, 0);
		point2 = new PointF(0, 0);
	}
	
	public LineSegmentDrawable(PointF offset, int localColor) {
		this.offset = offset;
		tempOffset = new PointF(0, 0);
		
		this.localPaint.setColor(localColor);
	}

	@Override
	public void update() {
		//nothing to do
	}

	@Override
	public void draw(Canvas canvas, PointF location) {
		PointUtils.setEqual(tempOffset, offset);
		PointUtils.multEquals(tempOffset, scale);
		PointUtils.setEqual(point2, location);
		PointUtils.addEquals(point2, tempOffset);
		canvas.drawLine(location.x, location.y, tempOffset.x, tempOffset.y, localPaint);
	}

}
