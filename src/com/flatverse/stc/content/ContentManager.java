package com.flatverse.stc.content;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;

import com.flatverse.stc.R;
import com.flatverse.stc.gameobject.GameObject;
import com.flatverse.stc.gameobject.Route;
import com.flatverse.stc.gameobject.Ship;
import com.flatverse.stc.gameobject.WayPoint;
import com.flatverse.stc.graphics.ImageDrawable;
import com.flatverse.stc.util.PointUtils;

public class ContentManager {

	public LevelInitializer getNextInitializer() {
		return new TestInit();
	}
	
	private class TestInit implements LevelInitializer {

		@Override
		public List<GameObject> getGameObjects(Context context) {
			ArrayList<GameObject> gos = new ArrayList<GameObject>();
			
			ArrayList<WayPoint> wayPoints = new ArrayList<WayPoint>();
			wayPoints.add(new WayPoint(new PointF(20, 20)));
			wayPoints.add(new WayPoint(new PointF(200, 250)));
			wayPoints.add(new WayPoint(new PointF(30, 700)));
			wayPoints.add(new WayPoint(new PointF(300, 900)));
			for (int i = 0; i < wayPoints.size(); i++) {
				wayPoints.get(i).name = "r1 - " + i;
			}
			WayPoint wp1 = wayPoints.get(1);
			Route r1 = new Route();
			r1.append(wayPoints);
			gos.add(r1);
			
			wayPoints = new ArrayList<WayPoint>();
			wayPoints.add(new WayPoint(new PointF(700, 700)));
			wayPoints.add(new WayPoint(new PointF(200, 400)));
			wayPoints.add(new WayPoint(new PointF(200, 100)));
			wayPoints.add(new WayPoint(new PointF(500, 20)));
			for (int i = 0; i < wayPoints.size(); i++) {
				wayPoints.get(i).name = "r2 - " + i;
			}
			WayPoint wp2 = wayPoints.get(1);
			Route r2 = new Route(0xff805020);
			r2.append(wayPoints);
			gos.add(r2);
			
			wp2.connect(wp1);
			
			Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.test_ship_1);
			ImageDrawable id = new ImageDrawable(bmp);
			id.offset.x = -(id.getWidth() / 2);
			id.offset.y = -(id.getHeight() / 2);
			Ship ship = new Ship(id);
			ship.speed = 5;
			ship.routeStack.push(r1.id);
			ship.routeStack.push(r2.id);
			//PointUtils.setEqual(ship.location, new PointF());
			r2.addShip(ship, wayPoints.get(3).id);
			//gos.add(ship);
			
			return gos;
		}
		
	}
}
