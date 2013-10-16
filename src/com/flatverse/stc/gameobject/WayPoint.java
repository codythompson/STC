package com.flatverse.stc.gameobject;

import com.flatverse.stc.util.PointUtils;

import android.graphics.PointF;
import android.util.SparseIntArray;

public class WayPoint {

	// static method
	private static int wayPointInc = 0;
	public static int GetNewWayPointId() {
		return wayPointInc++;
	}
	// end static fields and methods
	
	public final int id;
	
	public PointF location;
	
	public WayPoint next, prev;
	public SparseIntArray hopDirectory;
	public Route owner, connectedRoute;
	public int connectedWayPointId;
	
	public String name = "WayPoint";
	
	public WayPoint(PointF location) {
		id = GetNewWayPointId();
		
		this.location = location;
		
		hopDirectory = new SparseIntArray();
		connectedWayPointId = -1;
	}
	
	public int getNextHopId(int destWayPointId) {
		return hopDirectory.get(destWayPointId, -1);
	}
	
	public void connect(WayPoint other) {
		connectedRoute = other.owner;
		other.connectedRoute = owner;
		connectedWayPointId = other.id;
		other.connectedWayPointId = id;
		PointUtils.setEqual(other.location, location);
		owner.updateDirectories();
		other.owner.updateDirectories();
	}
	
	@Override
	public String toString() {
		return "{" + name + " (" + location.x + ", " + location.y + ")}"; 
	}
}
