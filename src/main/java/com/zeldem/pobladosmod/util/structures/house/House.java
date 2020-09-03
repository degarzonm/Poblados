package com.zeldem.pobladosmod.util.structures.house;

import com.zeldem.pobladosmod.PobladosMod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;



public class House {
	
	private World mundo;
	private int x_offset,z_offset;
	BlockPos entrada;
	private int nivel;//define la cantidad de espacios que la casa tendrá
	private Direction orientacion;
	
	public House(World mundo, BlockPos entrada ,int level, Direction orientacion) {
		this.mundo=mundo;
		x_offset=z_offset=0;
		this.entrada=entrada;
		this.nivel=level>=0?level:3;
		this.orientacion=orientacion;
		
	}
	
	public void buildHouse() {
		Sala principal = new Sala(mundo,entrada,Helpers.rand(4, 12),Helpers.rand(1, 8),Helpers.rand(1, 12),Helpers.rand(3, 12));
	}
	
	
	
	class Sala{
		World mundo;
		BlockPos e;	//puerta
		int delta_i, delta_d;
		int frente;
		int altura;
		
		public Sala(World mundo, BlockPos entrada,int frente, int d_i, int d_d, int altura) {
			this.mundo=mundo;
			delta_i=d_i>=1?d_i:1;
			delta_d=d_d>=2?d_d:2;
			this.frente=frente>=2?frente:2;
			this.altura=altura>=2?altura:2;
			this.e=entrada;
			
			buildParedes(altura);
			buildPuertas(Blocks.OAK_DOOR);
		}
			public void buildPuertas(Block puerta){
				mundo.setBlockState(e.up(), puerta.getDefaultState().with(DoorBlock.FACING, orientacion), 3);
	            mundo.setBlockState(e.add(0, 2, 0), puerta.getDefaultState().with(DoorBlock.HALF, DoubleBlockHalf.UPPER).with(DoorBlock.FACING, orientacion), 3);
				
			}
		
		
		
		
		
		private void buildParedes(int altura) {
			int a,b,c,d,r,s,t,u;
			
			switch(orientacion) {
				case SOUTH:
					 default:{
						 PobladosMod.LOGGER.debug("Facing South");
					a=u=delta_d;
					b=t=-delta_i;
					c=s=frente;
					d=r=0;
					
					break;
				}
				case NORTH:{
					PobladosMod.LOGGER.debug("Facing North");
					a=u=delta_d;
					b=t=-delta_i;
					c=r=0;
					d=s=-frente;
					break;
				}
				case WEST:{
					PobladosMod.LOGGER.debug("Facing West");
					a=u=0;
					b=t=-frente;
					c=r=delta_i;
					d=s=-delta_d;
					break;
				}
				case EAST:{
					PobladosMod.LOGGER.debug("Facing East");
					a=t=frente;
					b=u=0;
					c=r=delta_i;
					d=s=-delta_d;
					break;
				}
				
			}
			
			
			if(!mundo.isRemote) {
				for (int h=1;h<=altura;h++) {
					for(int n=e.getX()+b;n<=e.getX()+a;n++) {	
						mundo.setBlockState(new BlockPos(n,e.getY()+h,e.getZ()+r)		, Blocks.COBBLESTONE.getDefaultState(), 3);
						mundo.setBlockState(new BlockPos(n,e.getY()+h,e.getZ()+s)		, Blocks.COBBLESTONE.getDefaultState(), 3);
					}
					
					for(int m=e.getZ()+d;m<=e.getZ()+c;m++) {
						mundo.setBlockState(new BlockPos(e.getX()+t,e.getY()+h,m)		, Blocks.COBBLESTONE.getDefaultState(), 3);
						mundo.setBlockState(new BlockPos(e.getX()+u,e.getY()+h,m)		, Blocks.COBBLESTONE.getDefaultState(), 3);
					}
					
					
				}
			}
		}
		
		
		
	}
	
}
