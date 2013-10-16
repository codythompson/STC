package com.flatverse.stc.content;

import java.util.List;

import android.content.Context;

import com.flatverse.stc.gameobject.GameObject;

public interface LevelInitializer {
	List<GameObject> getGameObjects(Context context);
}
