package me.skykistler.engine.view;

import me.skykistler.engine.model.Model;

public interface View {
	public void init(Model model, Viewport viewport);

	public void render();

	public long getWindow();
}
