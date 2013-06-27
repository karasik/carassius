package client;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class Map
{
	// globalId to chunk number
	private TreeMap<Integer, Point> idToChunk
		= new TreeMap<Integer, Point>();
	
	private int ysize, xsize;
	private Chunk[][] chunks;
	Map(int ysize, int xsize, ArrayList<Entity> list) {
		this.ysize = ysize;
		this.xsize = xsize;
		chunks = new Chunk[ysize][xsize];
		
		for(int y = 0; y < ysize; y++) {
			for(int x = 0; x < xsize; x++) {
				chunks[y][x] = new Chunk();
			}
		}
		
		for(Entity entity : list) {
			int x = entity.getPosition().x;
			int y = entity.getPosition().y;
			
			getChunk(y, x).entities.put(entity.globalId, entity);
			
			Point point = Chunk.getChunkNumber(y, x);
			idToChunk.put(entity.globalId, point);
		}
	}	
	
	private Chunk getChunk(int y, int x) {
		Point point = Chunk.getChunkNumber(y, x);
		return chunks[point.y][point.x];
	}
	
//	private TreeMap<Integer, Entity> entities;
	Player player = null;
	
//	Map() {
//		entities = new TreeMap<Integer, Entity>();
//	}
	
//	void addEntity(Entity entity) {
//		entities.put(entity.globalId, entity);
//	}
	
//	void addEntity(Entity entity) {
//		entities.put(entity.globalId, entity);
//	}
	
	ArrayList<Collection<Entity>> getEntitiesToIterate() {
		ArrayList<Collection<Entity>> list =
				new ArrayList<Collection<Entity>>();
		
		Point point = player.getPosition();
		Point playerChunk = Chunk.getChunkNumber(point.y, point.x);
		
		for(int y = Math.max(playerChunk.y - Global.chunksFrameToLoad, 0); 
				y <= Math.min(playerChunk.y + Global.chunksFrameToLoad, ysize-1); y++)
		{
			for(int x = Math.max(playerChunk.x - Global.chunksFrameToLoad, 0); 
					x <= Math.min(playerChunk.x + Global.chunksFrameToLoad, xsize-1); x++)
			{
				list.add(chunks[y][x].entities.values());
			}
		}
		
		return list;
	}
	
	Entity getEntity(int index) {
		Point chunkN = idToChunk.get(index);
		if(chunkN == null) {
			Point zeros = new Point(0,0);
			idToChunk.put(index, zeros);
			chunkN = zeros;
		}
		
		TreeMap<Integer, Entity> map = chunks[chunkN.y][chunkN.x].entities;
		if(!map.containsKey(index)) {
			map.put(index, new Entity(index));
		}
		return map.get(index);
	}
	
	void moveEntity(Entity entity, int ycoord, int xcoord) {
		Point chunkNewN = Chunk.getChunkNumber(ycoord, xcoord);
		Point chunkOldN = idToChunk.get(entity.globalId);
		
		if(chunkNewN.x == chunkOldN.x && chunkNewN.y == chunkOldN.y)
			return;
		
		Chunk chunkNew = chunks[chunkNewN.y][chunkNewN.x];
		Chunk chunkOld = chunks[chunkOldN.y][chunkOldN.x];
		
		chunkOld.entities.remove(entity.globalId);
		chunkNew.entities.put(entity.globalId, entity);
		
		idToChunk.put(entity.globalId, chunkNewN);
	}
	
	private static class Chunk {
		TreeMap<Integer, Entity> entities =
				new TreeMap<Integer, Entity>();
		
		static Point getChunkNumber(int y, int x) {
			return new Point(	y/Global.tileHeight/Global.chunkSize,
								x/Global.tileWidth/Global.chunkSize );
		}
	}
}

