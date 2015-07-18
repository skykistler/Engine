package me.skykistler.engine.view;

import me.skykistler.engine.model.ModelObject;

public interface ViewObject {
	ModelObject model_obj = null;

	public void init(ModelObject object);

	public void render();
}
