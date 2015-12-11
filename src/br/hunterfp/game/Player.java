package br.hunterfp.game;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadMatrixf;
import static org.lwjgl.opengl.GL11.glMatrixMode;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

import br.hunterfp.utils.MathUtils;

public class Player {

	private GLFWKeyCallback kcback;
	private GLFWFramebufferSizeCallback fbCallback;
	private int width;
	private int height;
	private long window;
	
	private float velocity = 1;
    
    private float x = 5;
    private float y = 10;
    private float z = 10;
    private float pitch = 0;
    private float yaw = 180;
    
	
	public Player(long w){
		this.window = w;
		this.kcback = new GLFWKeyCallback(){
			@Override
			public void invoke(long a, int key, int scancode, int action, int mods) {			
				if(key == GLFW.GLFW_KEY_W && action != GLFW.GLFW_RELEASE){
					moveX(true);
				}else if(key == GLFW.GLFW_KEY_S && action != GLFW.GLFW_RELEASE){
					moveX(false);
				}else if(key == GLFW.GLFW_KEY_D && action != GLFW.GLFW_RELEASE){
					moveZ(true);
				}else if(key == GLFW.GLFW_KEY_A && action != GLFW.GLFW_RELEASE){
					moveZ(false);
				}else if(key == GLFW.GLFW_KEY_LEFT_SHIFT && action != GLFW.GLFW_RELEASE){
					moveY(false);
				}else if(key == GLFW.GLFW_KEY_SPACE && action != GLFW.GLFW_RELEASE){
					moveY(true);
				}
			}
		};
	}
	
	double newX = 400;
	double newY = 300;
	
	double deltaX = 0;
	double deltaY = 0;
	
	double lastX = 0;
	double lastY = 0;
	
	public void updateCamera(){
		DoubleBuffer xb = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yb = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(window, xb, yb);
        xb.rewind();yb.rewind();
        
        newX = xb.get();
        newY = yb.get();
        
        deltaX = newX - 400;
        deltaY = newY - 300;
        
        lastX = newX;
        lastY = newY;
        
        yaw = (float) ((yaw - deltaX) % 360);
        pitch = MathUtils.clamp((float) (pitch - deltaY/5 ),0,180);
        
        GLFW.glfwSetCursorPos(window, 400, 300);
        
	}
	
	public GLFWKeyCallback getCallback(){
		return this.kcback;
	}
	
	public void moveX(boolean f){
		if(f){
			this.x += this.velocity * (float)Math.sin(Math.toRadians(yaw));
			this.z += this.velocity * (float)Math.cos(Math.toRadians(yaw));
		}else{
			this.x += this.velocity * -(float)Math.sin(Math.toRadians(yaw));
			this.z += this.velocity * -(float)Math.cos(Math.toRadians(yaw));
		}
	}
	
	public void moveZ(boolean r){
		if(r){
			this.x += this.velocity/1.5f * (float)Math.sin(Math.toRadians(yaw-90f));
			this.z += this.velocity/1.5f * (float)Math.cos(Math.toRadians(yaw-90f));
		}else{
			this.x += this.velocity/1.5f * (float)Math.sin(Math.toRadians(yaw+90f));
			this.z += this.velocity/1.5f * (float)Math.cos(Math.toRadians(yaw+90f));
		}
	}
	
	public void moveY(boolean u){
		if(u){
			this.y += 0.5f;
		}else{
			this.y -= 0.5f;
		}
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public float getZ(){
		return this.z;
	}
	
	public float getPitch(){
		return this.pitch;
	}
	
	public float getYaw(){
		return this.yaw;
	}
	
	public Vector3f getEyeLocation(){
		return new Vector3f(x,y,z);
	}
	
	public Vector3f getPointingDirection(){
		return new Vector3f(x+3*(float)Math.sin(Math.toRadians(yaw)),
				y+4*(float)Math.sin(Math.toRadians(pitch-90)),z+3*(float)Math.cos(Math.toRadians(yaw)));
	}
	
	
}
