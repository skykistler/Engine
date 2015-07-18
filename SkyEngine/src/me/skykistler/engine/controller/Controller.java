package me.skykistler.engine.controller;

import me.skykistler.engine.model.Model;
import me.skykistler.engine.view.View;

public interface Controller {
	public void init(Model model, View view);

	public void update();
}
