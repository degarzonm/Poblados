package com.zeldem.pobladosmod.util.structures.house;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Sala{
	World mundo;
	BlockPos e;	//puerta
	Direction orientacion;
	int delta_i, delta_d;
	int frente;
	int h;
	ArrayList<BlockState> bloques_pared=new ArrayList<>();
	ArrayList<BlockState> bloques_suelo=new ArrayList<>();
	
	ArrayList<BlockState> bloques_columna=new ArrayList<>();
	ArrayList<BlockState> bloques_mueble=new ArrayList<>();
	
	public Sala(World mundo, BlockPos entrada,Direction orientacion,int frente, int d_i, int d_d, int altura) {
		this.mundo=mundo;
		delta_i=d_i>=1?d_i:1;
		delta_d=d_d>=2?d_d:2;
		this.frente=frente>=3?frente:3;
		this.h=altura>=3?altura:3;
		this.e=entrada;
		this.orientacion=orientacion;
		
		bloques_pared.add(Blocks.WHITE_CONCRETE.getDefaultState());
		bloques_pared.add(Blocks.CYAN_CONCRETE.getDefaultState());
		bloques_pared.add(Blocks.COBBLESTONE.getDefaultState());
		bloques_pared.add(Blocks.BRICKS.getDefaultState());
		bloques_pared.add(Blocks.CRACKED_STONE_BRICKS.getDefaultState());
		
		
		buildParedes(altura,bloques_pared.get(Helpers.rand(0, bloques_pared.size()-1)));
		buildPuertas(Blocks.BIRCH_DOOR);
		buildTecho(Blocks.BIRCH_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.BOTTOM));
		
		bloques_suelo.add(Blocks.OAK_PLANKS.getDefaultState());
		bloques_suelo.add(Blocks.SPRUCE_PLANKS.getDefaultState());
		bloques_suelo.add(Blocks.DARK_OAK_PLANKS.getDefaultState());
		
		buildSuelo(bloques_suelo);
		
		
		bloques_columna.add(Blocks.OAK_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z));
		
		
		
		buildDecoraciones(bloques_columna, bloques_mueble);
	}
		
	
	public void buildParedes(int h, BlockState block) {
		
		for(int j=0;j<=h;j++) {
			for(int i=-delta_d;i<=delta_i;i++) {
				Helpers.setBlock_relative(mundo, e, orientacion, i, j, 0, block);
				Helpers.setBlock_relative(mundo, e, orientacion, i, j, frente, block);
			}
			for(int k=0;k<=frente;k++) {
				Helpers.setBlock_relative(mundo, e, orientacion, delta_i, j, k, block);
				Helpers.setBlock_relative(mundo, e, orientacion, -delta_d, j, k, block);
			}
		}
	}
	
	public void buildPuertas(Block puerta){
		mundo.setBlockState(e.up(), puerta.getDefaultState().with(DoorBlock.FACING, orientacion), 3);
        mundo.setBlockState(e.add(0, 2, 0), puerta.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER).with(DoorBlock.FACING, orientacion), 3);
		
	}
	
	
	
	public void buildTecho(BlockState block) {
		for(int i=-delta_d-1;i<=delta_i+1;i++) {
			for(int k=-1;k<=frente+1;k++) {
				Helpers.setBlock_relative(mundo, e, orientacion, i, h, k, block);
			}
		}
	}
	
	public void buildTecho_triangulo(BlockState block) {
		for(int i=-delta_d-1;i<e.getX();i++) {
			for(int k=-1;k<=frente+1;k++) {
				Helpers.setBlock_relative(mundo, e, orientacion, i, 0, k, block.with(StairsBlock.FACING, Direction.EAST));
			}
		}
	}
	
	public void buildSuelo(ArrayList<BlockState> bloques) {
		for(int i=-delta_d+1;i<delta_i;i++) {
			for(int k=1;k<frente;k++) {
				Helpers.setBlock_relative(mundo, e, orientacion, i, 0, k, bloques.get(Helpers.rand(0, bloques.size()-1)));
			}
		}
	}
	
	public void buildDecoraciones(	ArrayList<BlockState> columnas,
								//	ArrayList<BlockState> ventanas,
								//	ArrayList<BlockState> luces, 
									ArrayList<BlockState> muebles) {
		//luces:
		{
			Helpers.setBlock_relative(mundo, e, orientacion, delta_i-1, h-1, 1, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, Boolean.TRUE));
			Helpers.setBlock_relative(mundo, e, orientacion, -delta_d+1, h-1, 1, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, Boolean.TRUE));
			Helpers.setBlock_relative(mundo, e, orientacion, delta_i-1, h-1, frente-1, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, Boolean.TRUE));
			Helpers.setBlock_relative(mundo, e, orientacion, -delta_d+1, h-1, frente-1, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, Boolean.TRUE));
		}
		
		//ventanas
		{
			for(int j=2;j<h;j++) {
				for (int i=-delta_d+1;i<delta_i;i++) {
					Helpers.setBlock_relative(mundo,e,orientacion,i,j,frente,Blocks.GLASS_PANE.getDefaultState());
				if((i>1 || i<-1) ) {
					Helpers.setBlock_relative(mundo,e,orientacion,i,j,0,Blocks.GLASS_PANE.getDefaultState());
					}
				
				}
				for (int k=1;k<frente;k++) {
					Helpers.setBlock_relative(mundo,e,orientacion,delta_i,j,k,Blocks.GLASS_PANE.getDefaultState());
					Helpers.setBlock_relative(mundo,e,orientacion,-delta_d,j,k,Blocks.GLASS_PANE.getDefaultState());
				}
				
			}
		}
		
		//columnas
		{
			BlockState columna=columnas.get(Helpers.rand(0, columnas.size()-1));
			for(int j=1;j<h;j++) {
				if(j==h-1) {
					for(int i=-delta_d;i<=delta_i;i++) {
						Helpers.setBlock_relative(mundo, e, orientacion, i, j, 0, columna.with(RotatedPillarBlock.AXIS, Direction.Axis.X));
						Helpers.setBlock_relative(mundo, e, orientacion, i, j, frente, columna.with(RotatedPillarBlock.AXIS, Direction.Axis.X));
					}
					for(int k=0;k<=frente;k++) {
						Helpers.setBlock_relative(mundo, e, orientacion, delta_i, j, k, columna);
						Helpers.setBlock_relative(mundo, e, orientacion, -delta_d, j, k, columna);
					}
				}
				
				Helpers.setBlock_relative(mundo, e, orientacion, -delta_d, j, 0, columna.with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
				Helpers.setBlock_relative(mundo, e, orientacion, delta_i, j, 0, columna.with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
				Helpers.setBlock_relative(mundo, e, orientacion, -delta_d, j, frente, columna.with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
				Helpers.setBlock_relative(mundo, e, orientacion, delta_i, j, frente, columna.with(RotatedPillarBlock.AXIS, Direction.Axis.Y));
				
			}
		}
		
		
	}
	
}
