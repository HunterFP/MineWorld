package br.hunterfp.game;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import br.hunterfp.game.world.ChunkManager;
import br.hunterfp.renderEngine.DisplayManager;

public class GameLoop {
	
	private GLFWErrorCallback errorCallback;
	private ChunkManager chkManager;
	private Player pl;
	private long window;

	public static void main(String[] args){
		GameLoop loop = new GameLoop();
		loop.init();
		loop.loop();
	}
	
	public void init(){
		GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		
		if( GLFW.glfwInit() != GLFW.GLFW_TRUE )
			throw new IllegalStateException("Unable to start GLFW");
		
		DisplayManager.createDisplay();
		this.chkManager = new ChunkManager();
		this.pl = new Player(DisplayManager.getWindow());
		this.window = DisplayManager.getWindow();
		GLFW.glfwSetKeyCallback(this.window, pl.getCallback());
		GameManager.registerPlayer(this.pl);
		//GLFW.glfwSetFramebufferSizeCallback(this.window, pl.getFbback());
		GL.createCapabilities();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
        GL11.glClearDepth(1.0);
		GL11.glFrontFace(GL11.GL_CCW);
		GL11.glCullFace(GL11.GL_BACK);
		GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL); 
        GL11.glMatrixMode(GL11.GL_PROJECTION); 
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	}
	
	public void loop(){
		while( glfwWindowShouldClose(this.window) == GLFW_FALSE ){
			DisplayManager.update();
		}
	}
	
}
