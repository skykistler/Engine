package me.skykistler.engine.model;

import java.util.ArrayList;

import me.skykistler.engine.view.View;

public interface Model {
	public void init();

	public void update();

	public void add(Model model);

	public ArrayList<View> getViews();
}
