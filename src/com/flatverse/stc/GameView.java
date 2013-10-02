package com.flatverse.stc;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class GameView extends View {
	private Level level;

	public GameView(Context context) {
		super(context);
		
		level = new Level(this);
	}

	@Override
	public void onDraw(Canvas canvas) {
		level.update();
		
		level.draw(canvas);
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {}
		
		invalidate();
	}
}
