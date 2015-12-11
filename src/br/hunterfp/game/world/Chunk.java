package br.hunterfp.game.world;

import java.util.Random;

import br.hunterfp.renderEngine.CubeFaces;
import br.hunterfp.renderEngine.RenderManager;

public class Chunk {

	Block[][][] blocks = new Block[16][50][16];
	
	int x;
	int z;
	
	public Chunk(int x, int z){
		this.x = x;
		this.z = z;
		//Grass
		for(int X = 0 ; X < 16 ; X++){
			for(int Z = 0 ; Z< 16 ; Z++){
				for(int Y = 0 ; Y< new Random().nextInt(3)+1 ; Y++){
					blocks[X][Y][Z] = new Block(BlockType.GRASS);
				}
			}	
		}
	}
	
	public void render(){
		for(int loop_x = 0; loop_x < 16 ; loop_x++){
			for(int loop_y = 0 ; loop_y < 50 ; loop_y++){
				for(int loop_z = 0 ; loop_z < 16; loop_z++){
					if(blocks[loop_x][loop_y][loop_z] == null)
						continue;
					if(loop_z==15 || blocks[loop_x][loop_y][loop_z+1] == null){
						RenderManager.renderCubeFace(x*16+loop_x, loop_y, z*16+loop_z, CubeFaces.FRONT);
					}if(loop_z==0 || blocks[loop_x][loop_y][loop_z-1] == null){
						RenderManager.renderCubeFace(x*16+loop_x, loop_y, z*16+loop_z, CubeFaces.BACK);
					}if(loop_y == 50 || blocks[loop_x][loop_y+1][loop_z] == null){
						RenderManager.renderCubeFace(x*16+loop_x, loop_y, z*16+loop_z, CubeFaces.TOP);
					}if(loop_y==0 || blocks[loop_x][loop_y-1][loop_z] == null){
						RenderManager.renderCubeFace(x*16+loop_x, loop_y, z*16+loop_z, CubeFaces.BOTTOM);
					}if(loop_x==0 || blocks[loop_x-1][loop_y][loop_z] == null){
						RenderManager.renderCubeFace(x*16+loop_x, loop_y, z*16+loop_z, CubeFaces.LEFT);
					}if(loop_x==15 || blocks[loop_x+1][loop_y][loop_z] == null){
						RenderManager.renderCubeFace(x*16+loop_x, loop_y, z*16+loop_z, CubeFaces.RIGHT);
					}
					
					//RenderManager.renderCube(x*16+loop_x, loop_y, z*16+loop_z);
				}
			}
		}
	}
}
