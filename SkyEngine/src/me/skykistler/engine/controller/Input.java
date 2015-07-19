package me.skykistler.engine.controller;

import me.skykistler.engine.view.View;

public interface Input {
	public void init(View view);

	public void update();

	public void bindKey(int key, InputBehavior behavior);

	public void bindMouse(int button, MouseBehavior behavior);
}
