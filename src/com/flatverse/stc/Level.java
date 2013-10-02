package com.flatverse.stc;

import java.util.ArrayList;

import android.graphics.Canvas;

public class Level {
	public ArrayList<GameObject> objs;
	public int bgColor;
	
	public Camera camera;
	
	public Level(GameView gameView) {
		objs = new ArrayList<GameObject>();
		
		bgColor = 0xff00000f;
		
		camera = new Camera(gameView.getContext());
		gameView.setOnTouchListener(camera);
	}
	
	public void update() {
		for(GameObject obj : objs) {
			obj.update();
		}
	}
	
	public void draw(Canvas canvas) {
		canvas.drawColor(bgColor);
		
		for(GameObject obj : objs) {
			obj.draw(canvas, camera);
		}
	}
}
