package com.flatverse.stc.gameobject;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.PointF;

import com.flatverse.stc.graphics.LineSegmentsDrawable;

public class Route implements GameObject {
	public final static int LINE_COLOR = 0xff202080;
	
	private ArrayList<PointF> wayPoints;

	private LineSegmentsDrawable dbl;
	
	public Route(ArrayList<PointF> wayPoints) {
		this.wayPoints = wayPoints;
		
		dbl = new LineSegmentsDrawable(LINE_COLOR);
	}

	@Override
	public void update() {
		// nothing to do
	}

	@Override
	public void draw(Canvas canvas) {
		dbl.draw(canvas, wayPoints);
	}

}
