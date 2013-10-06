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
	
	/**
	 * ref = val
	 * @param ref - gets MODIFED!!!
	 * @param val
	 */
	public static void setEqual(PointF ref, PointF val) {
		ref.x = val.x;
		ref.y = val.y;
	}
}
