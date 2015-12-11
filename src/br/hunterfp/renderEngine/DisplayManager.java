package br.hunterfp.renderEngine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
 
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class DisplayManager {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static long window;
	
	public static void createDisplay(){
		glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
		
		window = glfwCreateWindow(WIDTH, HEIGHT, "TestWindow", NULL, NULL);
		glfwMakeContextCurrent(window);
		
		glfwSwapInterval(0);
		
		glfwShowWindow(window);
	}
	
	public static void update(){
		GL.createCapabilities();
		glClearColor(0f, 0f, 0.5f, 1f);
		glfwPollEvents();
		glClear(GL_COLOR_BUFFER_BIT);
		glClear(GL_DEPTH_BUFFER_BIT);
		RenderManager.render(WIDTH,HEIGHT);
		glfwSwapBuffers(window);
	}
	
	public static void destroyDisplay(){
		glfwDestroyWindow(window);
	}
	
	public static long getWindow(){
		return window;
	}
	
}
