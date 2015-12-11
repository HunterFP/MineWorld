package br.hunterfp.renderEngine;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import br.hunterfp.game.GameManager;
import br.hunterfp.game.Player;
import br.hunterfp.game.world.Chunk;
import br.hunterfp.game.world.ChunkManager;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class RenderManager {

	static ChunkManager chkManager = new ChunkManager();
	static Matrix4f projMatrix = new Matrix4f();
    static Matrix4f viewMatrix = new Matrix4f();
    static FloatBuffer fb = BufferUtils.createFloatBuffer(16);
    static Player p = GameManager.getPlayer();
    
    static int displayListID = GL11.glGenLists(1);
    
    public static void render(int width, int height){
    	p.updateCamera();
    	projMatrix.setPerspective(45.0f, (float)width/height,
                0.01f, 100.0f).get(fb);
    	glMatrixMode(GL_PROJECTION);
        glLoadMatrixf(fb);
        
        viewMatrix.setLookAt(p.getEyeLocation(),
                p.getPointingDirection(),
                new Vector3f(0,1,0)).get(fb);
        p.getPointingDirection();
        glMatrixMode(GL_MODELVIEW);
        glLoadMatrixf(fb);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glNewList(displayListID, GL_COMPILE);
        for(int x = 5 ; x < 10 ; x++){
			for(int z = 5; z < 10 ; z++){
				chkManager.chunks[x][z].render();
			}
		}
        renderCube(0,0,0);
        GL11.glEnd();
        glEndList();
        glCallList(displayListID);
       
    }
    
    public static void renderCubeFace(float x, float y, float z,CubeFaces face){    	
    	switch(face){
    		case TOP:
    			GL11.glColor3f(0.8f,0.8f,0.8f);
    			GL11.glVertex3f(x, y+1f, z+1f);
    	        GL11.glVertex3f(x+1f, y+1f, z+1f);
    	        GL11.glVertex3f(x+1f, y+1f, z);
    	        GL11.glVertex3f(x, y+1f, z);

    			break;
    		case BOTTOM:
    			GL11.glColor3f(0.2f,0.2f,0.2f);
    			GL11.glVertex3f(x, y, z+1f);        
    	        GL11.glVertex3f(x, y, z);        
    	        GL11.glVertex3f(x+1f, y, z);
    	        GL11.glVertex3f(x+1f, y, z+1f);
    			break;
    		case FRONT:
    			GL11.glColor3f(0.6f,0.6f,0.6f);
    			GL11.glVertex3f(x+1f, y+1f, z+1f);
    	        GL11.glVertex3f(x, y+1f, z+1f);
    	        GL11.glVertex3f(x, y, z+1f);
    	        GL11.glVertex3f(x+1f, y, z+1f);
    			break;
    		case BACK:
    			GL11.glColor3f(0.4f,0.4f,0.4f);
    			GL11.glVertex3f(x+1f, y, z);
    	        GL11.glVertex3f(x, y, z);
    	        GL11.glVertex3f(x, y+1f, z);
    	        GL11.glVertex3f(x+1f, y+1f, z);
    			break;
    		case LEFT:
    			GL11.glColor3f(0.6f,0.6f,0.6f);
    			GL11.glVertex3f(x, y+1f, z+1f);
    	        GL11.glVertex3f(x, y+1f, z);
    	        GL11.glVertex3f(x, y, z);
    	        GL11.glVertex3f(x, y, z+1f);
    			break;
    		case RIGHT:
    			GL11.glColor3f(0.4f,0.4f,0.4f);
    			GL11.glVertex3f(x+1f, y+1f, z);
    	        GL11.glVertex3f(x+1f, y+1f, z+1f);
    	        GL11.glVertex3f(x+1f, y, z+1f);
    	        GL11.glVertex3f(x+1f, y, z);
    			break;
    	}
    }
    
	public static void renderCube(float x, float y, float z){
		renderCubeFace(x,y,z,CubeFaces.FRONT);
		renderCubeFace(x,y,z,CubeFaces.BACK);
		renderCubeFace(x,y,z,CubeFaces.TOP);
		renderCubeFace(x,y,z,CubeFaces.BOTTOM);
		renderCubeFace(x,y,z,CubeFaces.LEFT);
		renderCubeFace(x,y,z,CubeFaces.RIGHT);
	}
	
}
