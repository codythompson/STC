package com.flatverse.stc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;

public class Camera implements OnTouchListener {
	private PointF position;
	private float scale;
	
	private PointF downPoint, downPosition;
	private ScaleGestureDetector scaleDetector;
	private PointF scalePoint;
	private int activePointerId;
	
	public Camera(Context context) {
		position = new PointF(0, 0);
		scale = 1;
		
		downPoint = new PointF();
		downPosition = new PointF();
		scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		scalePoint = new PointF(0, 0);
		activePointerId = -1;
	}
	
	public void update(Canvas canvas) {
		canvas.translate(position.x, position.y);
		canvas.scale(scale, scale, scalePoint.x, scalePoint.y);
	}
	
	public void move(float deltX, float deltY) {
		position.x += deltX;
		position.y += deltY;
	}
	
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent mE) {
		scaleDetector.onTouchEvent(mE);
		
		final int action = mE.getActionMasked();

		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			downPoint = new PointF(mE.getX(), mE.getY());
			downPosition.x = position.x;
			downPosition.y = position.y;
			activePointerId = mE.getPointerId(0);
			break;
		case MotionEvent.ACTION_MOVE:
			final int pointerIndex = mE.findPointerIndex(activePointerId);
			
			if (pointerIndex >= 0 && !scaleDetector.isInProgress()) {
				position.x = downPosition.x - (downPoint.x - mE.getX(pointerIndex));
				position.y = downPosition.y - (downPoint.y - mE.getY(pointerIndex));
			}
			break;
		case MotionEvent.ACTION_UP:
			activePointerId = -1;
			break;
		case MotionEvent.ACTION_CANCEL:
			activePointerId = -1;
			break;
		case MotionEvent.ACTION_POINTER_UP:
			final int actionPointerIndex = (mE.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			final int pointerId = mE.getPointerId(actionPointerIndex);
			if (pointerId == activePointerId) {
				int newPointerIndex = mE.getPointerCount() - 1;
				if (newPointerIndex == pointerId) {
					newPointerIndex--;
				}
				downPoint.x = mE.getX(newPointerIndex);
				downPoint.y = mE.getY(newPointerIndex);
				downPosition.x = position.x;
				downPosition.y = position.y;
				activePointerId = mE.getPointerId(newPointerIndex);
			}
			break;
		}
		
		return true;
	}
	
	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			scale *= detector.getScaleFactor();
			scalePoint.x = detector.getFocusX();
			scalePoint.y = detector.getFocusY();
			
			// Don't let the object get too small or too large.
	        scale = Math.max(0.1f, Math.min(scale, 5.0f));
			
			return true;
		}
	}
}
