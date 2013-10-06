package com.flatverse.stc;

import com.flatverse.stc.content.ContentManager;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class GameView extends View {
	private Level level;

	public GameView(Context context) {
		super(context);
		
		ContentManager cm = new ContentManager();
		level = new Level(this, cm.getNextInitializer());
	}

	@Override
	public void onDraw(Canvas canvas) {
		level.update(canvas);
		
		level.draw(canvas);
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {}
		
		invalidate();
	}
}
