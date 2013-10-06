package com.flatverse.stc.content;

import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;

import com.flatverse.stc.gameobject.GameObject;
import com.flatverse.stc.gameobject.Route;

public class ContentManager {

	public LevelInitializer getNextInitializer() {
		return new TestInit();
	}
	
	private class TestInit implements LevelInitializer {

		@Override
		public List<GameObject> getGameObjects() {
			ArrayList<GameObject> gos = new ArrayList<GameObject>();
			
			ArrayList<PointF> wayPoints = new ArrayList<PointF>();
			wayPoints.add(new PointF(20, 20));
			wayPoints.add(new PointF(200, 250));
			wayPoints.add(new PointF(20, 500));
			gos.add(new Route(wayPoints));
			
			return gos;
		}
		
	}
}
