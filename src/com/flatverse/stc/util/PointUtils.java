package com.flatverse.stc.util;

import android.graphics.PointF;

public class PointUtils {
	
	/**
	 * val = val + addToVal
	 * @param val - gets modified!!! set to val + addToVal
	 * @param addToVal - added to val
	 */
	public static void addEquals(PointF val, PointF addToVal) {
		val.x += addToVal.x;
		val.y += addToVal.y;
		//return val;
	}
	
	public static void add(PointF val, PointF addToVal, PointF result) {
		result.x = val.x + addToVal.x;
		result.y = val.y + addToVal.y;
	}
	
	/**
	 * val = val - subFromVal
	 * @param val - gets modified!!! set to val - subFromVal
	 * @param subFromVal - subtracted from val
	 */
	public static void subEquals(PointF val, PointF subFromVal) {
		val.x = val.x - subFromVal.x;
		val.y = val.y - subFromVal.y;
		//return val;
	}
	
	public static void sub(PointF val, PointF subFromVal, PointF result) {
		result.x = val.x - subFromVal.x;
		result.y = val.y - subFromVal.y;
	}
	
	/**
	 * val = val * multValBy
	 * @param val - gets MODIFIED!!! set to val * multValBy
	 * @param multValBy - val is multiplied by this
	 */
	public static void multEquals(PointF val, float multValBy) {
		val.x *= multValBy;
		val.y *= multValBy;
		//return val;
	}
	
	public static void mult(PointF val, float multValBy, PointF result) {
		result.x = val.x * multValBy;
		result.y = val.y * multValBy;
	}
	
	/**
	 * val = val / divValBy
	 * @param val - gets MODIFIED!!! val set to val / divValBy
	 * @param divValBy
	 */
	public static void divEquals(PointF val, float divValBy) {
		val.x = val.x / divValBy;
		val.y = val.y / divValBy;
	}
	
	public static void div(PointF val, float divValBy, PointF result) {
		result.x = val.x / divValBy;
		result.y = val.y / divValBy;
	}
	
	/**
	 * ref = val
	 * @param ref - gets MODIFED!!!
	 * @param val
	 */
	public static void setEqual(PointF ref, PointF val) {
		ref.x = val.x;
		ref.y = val.y;
	}
	
	public static boolean areEqual(PointF a, PointF b) {
		return a.x == b.x && a.y == b.y;
	}
	
	public static String toString(PointF val) {
		return "(" + val.x + ", " + val.y + ")";
	}
}
