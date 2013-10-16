package com.flatverse.stc.gameobject;

import java.util.ArrayList;
import java.util.Stack;

import com.flatverse.stc.graphics.SinglePointDrawable;
import com.flatverse.stc.util.PointUtils;

import android.graphics.Canvas;
import android.graphics.PointF;

public class Ship implements GameObject {
	public PointF location;
	public float speed;
	
	public WayPoint currentWayPoint, prevWayPoint;

	public Stack<Integer> routeStack;
	
	public ArrayList<SinglePointDrawable> dbls;
	
	//
	public PointF tmpA, tmpB;

	public Ship(ArrayList<SinglePointDrawable> dbls) {
		location = new PointF(0, 0);
		speed = 0;
		
		routeStack = new Stack<Integer>();
		
		this.dbls = dbls;
		
		//
		tmpA = new PointF(0, 0);
		tmpB = new PointF(0, 0);
	}
	
	public Ship(SinglePointDrawable dbl) {
		this(new ArrayList<SinglePointDrawable>());
		dbls.add(dbl);
	}
	
	@Override
	public void update() {
		for(SinglePointDrawable dbl : dbls) {
			dbl.update();
		}
		
		if (currentWayPoint != null) {
		//TODO caching of the velocity could be implemented here to avoid .length calls 
			
			//safety check for arrival
			if (PointUtils.areEqual(location, currentWayPoint.location)) {
				PointUtils.setEqual(location, currentWayPoint.location);
				prevWayPoint = currentWayPoint;
				currentWayPoint = null; //we need a new waypoint now
				return;
			}
			
			//get vector to waypoint, stored in tmpA
			PointUtils.sub(currentWayPoint.location, location, tmpA);
			//get the unit vector to the waypoint, stored in tmpA
			float mag = tmpA.length();
			PointUtils.divEquals(tmpA, mag);
			//multiply unit vector by ship speed to get velocity per update
			//stored in tmpA - will need for overshot check
			PointUtils.multEquals(tmpA, speed);
			//check if the current vel. per update is greater than distance to waypoint
			// if so we have arrived or overshot the waypoint
			if (tmpA.length() >= mag) {
				PointUtils.setEqual(location, currentWayPoint.location);
				prevWayPoint = currentWayPoint;
				currentWayPoint = null; //we need a new waypoint now
			}
			else {
				//move the ship closer to the waypoint
				PointUtils.addEquals(location, tmpA);
			}
		}
	}

	@Override
	public void draw(Canvas canvas) {
		for(SinglePointDrawable dbl : dbls) {
			dbl.draw(canvas, location);
		}
	}
	
	public int peekCurrentRouteDest() {
		if (routeStack.isEmpty()) {
			return -1;
		}
		
		return routeStack.peek();
	}
	
	public int popCurrentRouteDest() {
		if (routeStack.isEmpty()) {
			return -1;
		}
		
		return routeStack.pop();
	}
}
