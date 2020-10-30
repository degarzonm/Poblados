package com.zeldem.pobladosmod.util.structures.house;

import java.util.ArrayList;

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
	private Vector3f dir;				//vector unitario en la direcciona A a B
	private Vector2f norm;				//vectr normal al unitario, para custiones de grosor del camino
	private int ancho;
	private float dist;					//distancia entre los puntos A y B
	
	
	
	public BlockState[] materiales;
	
	
	public Road(World mundo) {
		
		this(mundo,new BlockPos(0, 0, 0),new BlockPos(0, 0, 0),1,defaultMaterials);
	}
	
	public Road(World mundo,BlockPos A, BlockPos B, int ancho, BlockState[] materiales) {
		this.mundo=mundo;
		this.inicio=A;
		this.termino=B;
		this.ancho=ancho;
		float deltax=termino.getX()-inicio.getX();
		float deltaz=termino.getZ()-inicio.getZ();
		dist=(float)Math.sqrt(deltax*deltax+deltaz*deltaz);
		dir=new Vector3f(deltax/dist,0,deltaz/dist);		
		norm=new Vector2f(deltaz/dist,-deltax/dist);			
		this.materiales=materiales==null?defaultMaterials:materiales;
		
		
	}
	
	public void setOrigin(BlockPos A) {
		this.inicio=A;
	}
	
	public void setDestiny(BlockPos B) {
		this.termino=B;
	}
	
	public BlockState[] getMaterials() {
		return materiales;
	}
	
	public void setMaterials(BlockState[] materiales) {
		this.materiales=materiales;
	}
	
	//da un blockstate aleatorio dentro del arreglo de materiales
	private BlockState randBlock() {
		return materiales[Helpers.rand(0, materiales.length-1)];
	}
	
	public void buildRoad() {
		if(!mundo.isRemote) {
			//
			for(float i=0;i<dist+1;i+=0.7f) {
				
				float x=i*dir.getX()+inicio.getX();
				float z=i*dir.getZ()+inicio.getZ();
				
				for(int j=1;j<ancho;j++) {
					float xt=x+j*norm.x;float xtt=x-j*norm.x;
					float zt=z+j*norm.y;float ztt=z-j*norm.y;
					mundo.setBlockState(new BlockPos(xt,inicio.getY(),zt), randBlock(), 3);
					mundo.setBlockState(new BlockPos(xtt,inicio.getY(),ztt), randBlock(), 3);
				}
				mundo.setBlockState(new BlockPos(x,inicio.getY(),z), randBlock(), 3);
				
			}
			
		}
	}
	
	public static BlockState defaultBlock=Blocks.CRACKED_STONE_BRICKS.getDefaultState();
	public static BlockState[] defaultMaterials= {
			Blocks.CRACKED_STONE_BRICKS.getDefaultState(),
			Blocks.MOSSY_COBBLESTONE.getDefaultState(),
			Blocks.COBBLESTONE.getDefaultState(),
			Blocks.BLUE_TERRACOTTA.getDefaultState(),
			};
	
	
	
	
	
	
	
	
	
}




















