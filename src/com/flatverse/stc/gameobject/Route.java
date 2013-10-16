// TODO rework the WayPoint data-structure to use a hash-map 
//      instead of maintaining a linked list 
package com.flatverse.stc.gameobject;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.SparseIntArray;

import com.flatverse.stc.graphics.LineSegmentsDrawable;
import com.flatverse.stc.util.PointUtils;

public class Route implements GameObject {
	public final static int DEFAULT_LINE_COLOR = 0xff5050ff;
	
	private static int routeInc = 0;
	public static int GetNewRouteId() {
		return routeInc++;
	}
	//end of static methods and fields
	
	public final int id;
	
	private WayPoint first, last;
	
	private SparseIntArray connectedRouteDir;
	
	private ArrayList<Ship> ships;
	
	private LineSegmentsDrawable dbl;
	
	// helper vars - here so new objects aren't allocated every update
	private ArrayList<Ship> tmpToRemove;
	private ArrayList<WayPoint> tmpWayPts;
	private ArrayList<WayPoint> tmpWayPtsPrev;
	private ArrayList<WayPoint> tmpWayPtsNext;
	private ArrayList<PointF> tmpPts; 
	
	public Route(int lineColor) {
		id = GetNewRouteId();
		
		connectedRouteDir = new SparseIntArray();
		
		ships = new ArrayList<Ship>();
		
		dbl = new LineSegmentsDrawable(lineColor);
		
		tmpToRemove = new ArrayList<Ship>();
		tmpWayPts = new ArrayList<WayPoint>();
		tmpWayPtsPrev = new ArrayList<WayPoint>();
		tmpWayPtsNext = new ArrayList<WayPoint>();
		tmpPts = new ArrayList<PointF>();
	}
	
	public Route() {
		this(DEFAULT_LINE_COLOR);
	}

	@Override
	public void update() {
		dbl.update();

		
		tmpToRemove.clear();
		
		for(Ship ship : ships) {
			ship.update();
			if (ship.currentWayPoint == null) {
				int destRouteId = ship.peekCurrentRouteDest();
				int destWPId = getConnectingWayPointId(destRouteId);
				
				//TODO check if prev waypoint is also null and do something useful (maybe ship garbage collection)
				if (destWPId >= 0) {
					if (ship.prevWayPoint.id == destWPId) {
						tmpToRemove.add(ship);
						ship.prevWayPoint.connectedRoute.addShip(ship, ship.prevWayPoint.connectedWayPointId);
					}
					else {
						int nextWPId = ship.prevWayPoint.getNextHopId(destWPId);
						ship.currentWayPoint = getWayPoint(nextWPId);
					}
				}
				else {
					ship.currentWayPoint = ship.prevWayPoint.next;
				}
			}
		}
		
		for(Ship ship : tmpToRemove) {
			ships.remove(ship);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		dbl.draw(canvas, getPoints());
		
		for(Ship ship : ships) {
			ship.draw(canvas);
		}
	}
	
	private ArrayList<WayPoint> getWayPoints() {
		tmpWayPts.clear();
		
		WayPoint current = first;
		while (current != null) {
			tmpWayPts.add(current);
			current = current.next;
		}
		
		return tmpWayPts;
	}
	
	private ArrayList<PointF> getPoints() {
		tmpPts.clear();
		
		WayPoint current = first;
		while(current != null) {
			tmpPts.add(current.location);
			current = current.next;
		}
		
		return tmpPts;
	}
	
	private WayPoint getWayPoint(int wayPointId) {
		WayPoint current = first;
		while(current != null) {
			if (current.id == wayPointId) {
				return current;
			}
			current = current.next;
		}
		
		return null;
	}
	
	private int getConnectingWayPointId(int routeId) {
		return connectedRouteDir.get(routeId, -1);
	}

	public void addShip(Ship ship, int initialWayPointId) {
		ships.add(ship);
		ship.popCurrentRouteDest();
		if (initialWayPointId < 0) {
			ship.prevWayPoint = first;
			ship.currentWayPoint = first.next;
		}
		else {
			ship.prevWayPoint = getWayPoint(initialWayPointId);
			int destRouteId = ship.peekCurrentRouteDest();
			int destWPId = getConnectingWayPointId(destRouteId);
			ship.currentWayPoint = getWayPoint(ship.prevWayPoint.getNextHopId(destWPId));
			if (ship.currentWayPoint == null) {
				ship.currentWayPoint = ship.prevWayPoint.next;
			}
		}
		// TODO
		// instead of this check lockation of ship and if not where it's 
		// supposed to be set its current way point to it's initial
		// way point on this route
		PointUtils.setEqual(ship.location, ship.prevWayPoint.location);
	}
	
	public void addShip(Ship ship) {
		addShip(ship, -1);
	}

	public void append(WayPoint wayPoint, boolean updateDirs) {
		if (first == null) {
			first = wayPoint;
			last = wayPoint;
		}
		else if (first == last) {
			first.next = wayPoint;
			wayPoint.prev = first;
			last = wayPoint;
		}
		else {
			last.next = wayPoint;
			wayPoint.prev = last;
			last = wayPoint;
		}
		wayPoint.owner = this;
		
		if (updateDirs) {
			updateDirectories();
		}
	}
	
	public void append(ArrayList<WayPoint> wayPoints) {
		for(WayPoint wayPoint : wayPoints) {
			append(wayPoint, false);
		}
		updateDirectories();
	}
	
	public void updateWayPointDirs() {
		ArrayList<WayPoint> wayPoints = getWayPoints();
		
		//tmpWayPtsNext = way points reachable via next pointer
		//tmpWayPtsPrev = way points reachable via prev pointer
		tmpWayPtsNext.clear();
		tmpWayPtsPrev.clear();
		for(WayPoint wayPoint : wayPoints) {
			tmpWayPtsNext.add(wayPoint);
		}
		
		for(WayPoint wayPoint : wayPoints) {
			wayPoint.hopDirectory.clear();
			for(WayPoint prevWayPoint : tmpWayPtsPrev) {
				wayPoint.hopDirectory.append(prevWayPoint.id, wayPoint.prev.id);
			}
			for(WayPoint nextWayPoint : tmpWayPtsNext) {
				if (wayPoint.next != null)
				{
					wayPoint.hopDirectory.append(nextWayPoint.id, wayPoint.next.id);
				}
			}
			tmpWayPtsNext.remove(wayPoint);
			tmpWayPtsPrev.add(wayPoint);
		}
	}
	
	public void updateRouteDir() {
		ArrayList<WayPoint> wayPoints = getWayPoints();
		connectedRouteDir.clear();
		for(WayPoint wayPoint : wayPoints) {
			if (wayPoint.connectedRoute != null) {
				connectedRouteDir.append(wayPoint.connectedRoute.id, wayPoint.id);
			}
		}
	}
	
	public void updateDirectories() {
		updateWayPointDirs();
		updateRouteDir();
	}
}