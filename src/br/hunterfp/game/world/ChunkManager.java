package br.hunterfp.game.world;

public class ChunkManager {

	public Chunk[][] chunks = new Chunk[16][16];
	
	public ChunkManager(){
		for(int x = 0 ; x < 16 ; x++){
			for(int z = 0; z < 16 ; z++){
				chunks[x][z] = new Chunk(x-8,z-8);
			}
		}
	}
	
}
