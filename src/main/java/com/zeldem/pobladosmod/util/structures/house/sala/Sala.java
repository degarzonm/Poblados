package com.zeldem.pobladosmod.util.structures.house.sala;

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


import com.zeldem.pobladosmod.util.structures.house.Helpers;

public class Sala{
	World mundo;
	BlockPos e;	//puerta
	public final Direction orientacion;
	public final int delta_i;
	public final int delta_d;
	public final int frente;
	public final int h;
	boolean pared_entrada=true,
			pared_opuesta=true,
			pared_izq=true,
			pared_der=true;
	
	public static final BlockState[] BLOQUES_PARED= {Blocks.WHITE_CONCRETE.getDefaultState()
			,Blocks.COBBLESTONE.getDefaultState()
			,Blocks.BRICKS.getDefaultState()
			,Blocks.BRICKS.getDefaultState()};
	
	
	public Sala(World mundo, BlockPos entrada,Direction orientacion, int d_i, int d_d, int altura, int frente) {
		this.mundo=mundo;
		delta_i=d_i>=1?d_i:1;
		delta_d=d_d>=1?d_d:2;
		this.frente=frente>=3?frente:3;
		this.h=altura>=3?altura:3;
		this.e=entrada;
		this.orientacion=orientacion;
		
		
		albañil();
		
	}

	public Sala(World mundo, BlockPos entrada,Direction orientacion, int d_i, int d_d, int altura, int frente, boolean pared_entrada, boolean pared_frente, boolean pared_izq, boolean pared_der) {
		this.pared_entrada=pared_entrada;
		this.pared_opuesta=pared_frente;
		this.pared_izq=pared_izq;
		this.pared_der=pared_der;
				
		this.mundo=mundo;
		delta_i=d_i>=1?d_i:1;
		delta_d=d_d>=1?d_d:2;
		this.frente=frente>=3?frente:3;
		this.h=altura>=3?altura:3;
		this.e=entrada;
		this.orientacion=orientacion;
				
				
		albañil();
	}
	
	
	public static final BlockState[] BLOQUES_SUELO= {Blocks.OAK_PLANKS.getDefaultState()
													,Blocks.SPRUCE_PLANKS.getDefaultState()
													,Blocks.DARK_OAK_PLANKS.getDefaultState()
													,Blocks.CRACKED_STONE_BRICKS.getDefaultState()};
	
	public static final BlockState[] BLOQUES_COLUMNA= 	{Blocks.SPRUCE_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
														,Blocks.OAK_LOG.getDefaultState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
	};
	
	public static final BlockState[] BLOQUES_TECHO= {Blocks.OAK_SLAB.getDefaultState()
													,Blocks.BIRCH_SLAB.getDefaultState()
													,Blocks.SPRUCE_SLAB.getDefaultState()};
	
	
	public void albañil() {
		limpiarSala();
		
		BlockState bloque_pared=BLOQUES_PARED[Helpers.rand(0, BLOQUES_PARED.length-1)];
		buildParedes(h,bloque_pared);
		
		buildTecho_plano(Blocks.BIRCH_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.BOTTOM));
		buildTecho_triangulo_slabs(BLOQUES_TECHO);
		
		
		buildSuelo(BLOQUES_SUELO);
		
		buildDecoraciones(BLOQUES_COLUMNA);
		buildPuertas(Blocks.BIRCH_DOOR);
	}
	
	

	public void limpiarSala() {
		for(int i=-delta_d+1;i<delta_i;i++) {
			for(int j=1;j<=h;j++) {
				for(int k=1;k<frente;k++) {
					Helpers.setBlock_relative(mundo, e, orientacion, i, j, k, Blocks.AIR.getDefaultState());
				}
			}
		}
	}
	
	public void buildParedes(int h, BlockState block) {
		
		for(int j=0;j<=h;j++) {
			for(int i=-delta_d;i<=delta_i;i++) {
				if(pared_entrada)Helpers.setBlock_relative(mundo, e, orientacion, i, j, 0, block);
				if(pared_opuesta)Helpers.setBlock_relative(mundo, e, orientacion, i, j, frente, block);
			}
			for(int k=0;k<=frente;k++) {
				if(pared_izq)Helpers.setBlock_relative(mundo, e, orientacion, delta_i, j, k, block);
				if(pared_der)Helpers.setBlock_relative(mundo, e, orientacion, -delta_d, j, k, block);
			}
		}
	}
	
	public void buildPuertas(Block puerta){
		mundo.setBlockState(e.up(), puerta.getDefaultState().with(DoorBlock.FACING, orientacion), 3);
        mundo.setBlockState(e.add(0, 2, 0), puerta.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER).with(DoorBlock.FACING, orientacion), 3);
		
	}
	
	
	
	public void buildTecho_plano(BlockState block) {
		for(int i=-delta_d;i<=delta_i;i++) {
			for(int k=0;k<=frente;k++) {
				Helpers.setBlock_relative(mundo, e, orientacion, i, h, k, block);
			}
		}
	}
	
	public void buildTecho_triangulo_slabs(BlockState[] blocks) {
		
			for(int k=-1;k<=frente+1;k++) {
				int r=delta_i+1,s=-delta_d-1;
				for(int i=0;i<=(delta_d+delta_i)/2+1;i++) {
					BlockState block=blocks[Helpers.rand(0, blocks.length-1)];
					Helpers.setBlock_relative(mundo, e, orientacion, r, h+(i/2), k, block.with(SlabBlock.TYPE, i%2==0?SlabType.BOTTOM:SlabType.DOUBLE));
					block=blocks[Helpers.rand(0, blocks.length-1)];
					Helpers.setBlock_relative(mundo, e, orientacion, s, h+(i/2), k, block.with(SlabBlock.TYPE, i%2==0?SlabType.BOTTOM:SlabType.DOUBLE));
					r--;s++;
				}
			}
			
		
	}
	
	public void buildSuelo(BlockState[] bloques) {
		for(int i=-delta_d+1;i<delta_i;i++) {
			for(int k=1;k<frente;k++) {
				Helpers.setBlock_relative(mundo, e, orientacion, i, 0, k, bloques[Helpers.rand(0, bloques.length-1)]);
			}
		}
	}
	
	public void buildDecoraciones(	BlockState[] columnas
								//	ArrayList<BlockState> ventanas,
								//	ArrayList<BlockState> luces, 
								//ArrayList<BlockState> muebles
			) {
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
					if(pared_opuesta)Helpers.setBlock_relative(mundo,e,orientacion,i,j,frente,Blocks.GLASS_PANE.getDefaultState());
				if((i>1 || i<-1) && pared_entrada) {
					if(pared_entrada)Helpers.setBlock_relative(mundo,e,orientacion,i,j,0,Blocks.GLASS_PANE.getDefaultState());
					}
				
				}
				for (int k=1;k<frente;k++) {
					if(pared_izq)Helpers.setBlock_relative(mundo,e,orientacion,delta_i,j,k,Blocks.GLASS_PANE.getDefaultState());
					if(pared_der)Helpers.setBlock_relative(mundo,e,orientacion,-delta_d,j,k,Blocks.GLASS_PANE.getDefaultState());
				}
				
			}
		}
		
		//columnas
		{
			BlockState columna=columnas[Helpers.rand(0, columnas.length-1)];
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
	
	public BlockPos getALeftEntrance(){
		return new BlockPos(Helpers.getBlockPos_relative(e, orientacion, delta_i, 0, Helpers.rand(1, frente-1)));
	}
	
	public BlockPos getAFrontEntrance() {
		return new BlockPos(Helpers.getBlockPos_relative(e, orientacion, Helpers.rand(-delta_d+1, delta_i-1), 0, frente));
	}
	
	public BlockPos getARightEntrance() {
		return new BlockPos(Helpers.getBlockPos_relative(e, orientacion, -delta_d, 0, Helpers.rand(1, frente-1)));
	}

//	public void randomSubSalas(boolean izquierda, boolean alFrente, boolean derecha) {
//		if(izquierda) {
//			if(subSalas==null)subSalas=new ArrayList<>();
//			
//		}
//		if(alFrente) {
//			if(subSalas==null)subSalas=new ArrayList<>();
//			BlockPos subSalaEntrance=getAFrontEntrance();
//			subSalas.add(new Sala(mundo,subSalaEntrance,Direction.SOUTH	,Helpers.rand(1, 4)
//																		,Helpers.rand(1, 4)
//																		,Helpers.rand(3, h)
//																		,frente/2));
//		}
//		if(derecha) {
//			if(subSalas==null)subSalas=new ArrayList<>();
//			BlockPos subSalaEntrance=getARightEntrance();
//			subSalas.add(new Sala(mundo,subSalaEntrance,Direction.WEST	,Helpers.rand(1, 4)
//																		,Helpers.rand(1, 4)
//																		,Helpers.rand(3, h)
//																		,frente/2));
//		}
//		
//		
//	}

	public String toString() {
		return "Sala simple:("+e.getX()+","+e.getY()+","+e.getZ()+")  dir:"+orientacion;
	}
	
}
