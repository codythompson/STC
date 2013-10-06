package com.flatverse.stc;

import java.util.List;

import com.flatverse.stc.content.LevelInitializer;
import com.flatverse.stc.gameobject.GameObject;

import android.graphics.Canvas;

public class Level {
	public List<GameObject> objs;
	public int bgColor;
	
	public Camera camera;
	
	public Level(GameView gameView, LevelInitializer levelInit) {
		objs = levelInit.getGameObjects();
		
		bgColor = 0xff00000f;
		
		camera = new Camera(gameView.getContext());
		gameView.setOnTouchListener(camera);
	}
	
	public void update(Canvas canvas) {
		camera.update(canvas);
		for(GameObject obj : objs) {
			obj.update();
		}
	}
	
	public void draw(Canvas canvas) {
		canvas.drawColor(bgColor);
		
		for(GameObject obj : objs) {
			obj.draw(canvas);
		}
	}
}
