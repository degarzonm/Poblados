package com.zeldem.pobladosmod.util.structures.house;

import com.zeldem.pobladosmod.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public class Road {

	private World mundo;
	private BlockPos inicio,termino;
	private Vector3f dir;
	private Vector2f norm;
	private int ancho;
	private float dist;
	private BlockState mainBlock=Blocks.GRASS_PATH.getDefaultState();
	private BlockState sideBlock=Blocks.CRACKED_STONE_BRICKS.getDefaultState();
	
	public Road(World mundo) {
		this(mundo,new BlockPos(0, 0, 0),new BlockPos(0, 0, 0),1);
	}
	
	public Road(World mundo,BlockPos A, BlockPos B, int ancho) {
		this.mundo=mundo;
		this.inicio=A;
		this.termino=B;
		this.ancho=ancho;
		float deltax=termino.getX()-inicio.getX();
		float deltaz=termino.getZ()-inicio.getZ();
		dist=(float)Math.sqrt(deltax*deltax+deltaz*deltaz);
		dir=new Vector3f(deltax/dist,0,deltaz/dist);
		norm=new Vector2f(deltaz/dist,-deltax/dist);
		
	}
	
	public void setOrigin(BlockPos A) {
		this.inicio=A;
	}
	
	public void setDestiny(BlockPos B) {
		this.termino=B;
	}
	
	public void buildRoad() {
		if(!mundo.isRemote) {
			for(float i=0;i<dist+1;i+=0.5f) {
				float x=i*dir.getX()+inicio.getX();
				float z=i*dir.getZ()+inicio.getZ();
				
				for(int j=1;j<ancho;j++) {
					float xt=x+j*norm.x;float xtt=x-j*norm.x;
					float zt=z+j*norm.y;float ztt=z-j*norm.y;
					mundo.setBlockState(new BlockPos(xt,inicio.getY(),zt), sideBlock, 3);
					mundo.setBlockState(new BlockPos(xtt,inicio.getY(),ztt), sideBlock, 3);
				}
				mundo.setBlockState(new BlockPos(x,inicio.getY(),z), mainBlock, 3);
				
			}
			
		}
	}
	
	
}
