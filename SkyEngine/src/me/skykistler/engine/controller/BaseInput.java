package me.skykistler.engine.controller;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWKeyCallback;

import me.skykistler.engine.view.View;

public class BaseInput implements Input {
	private View view;

	private HashMap<Integer, ArrayList<InputBehavior>> keyMap;
	private HashMap<Integer, ArrayList<MouseBehavior>> mouseMap;

	private HashMap<Integer, Boolean> keysDownMap, mouseDownMap;

	private DoubleBuffer mouseXbuffer, mouseYbuffer;
	private double mouseX, mouseY;

	public void init(View view) {
		this.view = view;

		keyMap = new HashMap<Integer, ArrayList<InputBehavior>>();
		mouseMap = new HashMap<Integer, ArrayList<MouseBehavior>>();
		keysDownMap = new HashMap<Integer, Boolean>();

		glfwSetKeyCallback(view.getWindow(), (new GLFWKeyCallback() {
			public void invoke(long window, int key, int scancode, int action, int mods) {
				ArrayList<InputBehavior> behaviors = keyMap.get(key);
				if (behaviors == null)
					return;

				switch (action) {
				case GLFW_PRESS:
					for (InputBehavior behavior : behaviors)
						behavior.pressed();
					keysDownMap.put(key, true);
					break;
				case GLFW_RELEASE:
					for (InputBehavior behavior : behaviors)
						behavior.released();
					keysDownMap.put(key, false);
					break;
				}
			}
		}));

		mouseXbuffer = BufferUtils.createDoubleBuffer(1);
		mouseYbuffer = BufferUtils.createDoubleBuffer(1);
	}

	public void update() {
		glfwPollEvents();

		glfwGetCursorPos(view.getWindow(), mouseXbuffer, mouseYbuffer);
		if (mouseX != mouseXbuffer.get(0) || mouseY != mouseYbuffer.get(0)) {
			double deltaX = mouseXbuffer.get(0) - mouseX;
			double deltaY = mouseYbuffer.get(0) - mouseY;
			mouseX = mouseXbuffer.get(0);
			mouseY = mouseYbuffer.get(0);

			for (ArrayList<MouseBehavior> mappings : mouseMap.values())
				for (MouseBehavior behavior : mappings)
					behavior.move(mouseX, mouseY, deltaX, deltaY);
		}

		for (Integer key : keysDownMap.keySet())
			if (keysDownMap.get(key)) {
				ArrayList<InputBehavior> behaviors = keyMap.get(key);
				if (behaviors == null)
					return;

				for (InputBehavior behavior : behaviors)
					behavior.down();
			}

		for (Integer button : mouseDownMap.keySet())
			if (mouseDownMap.get(button)) {
				ArrayList<MouseBehavior> behaviors = mouseMap.get(button);
				if (behaviors == null)
					return;

				for (MouseBehavior behavior : behaviors)
					behavior.down();
			}

	}

	public void bindKey(int key, InputBehavior behavior) {
		ArrayList<InputBehavior> behaviors = keyMap.get(key);
		if (behaviors == null) {
			behaviors = new ArrayList<InputBehavior>();
			keyMap.put(key, behaviors);
		}

		behaviors.add(behavior);
	}

	public void bindMouse(int button, MouseBehavior behavior) {
		ArrayList<MouseBehavior> behaviors = mouseMap.get(button);
		if (behaviors == null) {
			behaviors = new ArrayList<MouseBehavior>();
			mouseMap.put(button, behaviors);
		}

		behaviors.add(behavior);
	}

}
