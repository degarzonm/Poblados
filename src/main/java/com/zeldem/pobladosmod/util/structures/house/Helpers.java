package com.zeldem.pobladosmod.util.structures.house;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class Helpers {

	
	public static int rand(int min, int max) { 
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	

	public static boolean setBlock_relative(World mundo, BlockPos e,Direction f, int i, int j, int k, BlockState block) {

		int x,y,z;
		switch(f) {
			case 	SOUTH:
			default:{
				x=i;
				y=j;
				z=k;
				
				break;
			}
			case 	NORTH:{
				x=-i;
				y=j;
				z=-k;
				block=block.rotate(Rotation.CLOCKWISE_180);
				break;
			}
			case EAST:{
				x=k;
				y=j;
				z=-i;
				block=block.rotate(Rotation.COUNTERCLOCKWISE_90);
				break;
			}
			case WEST:{
				x=-k;
				y=j;
				z=i;
				block=block.rotate(Rotation.CLOCKWISE_90);
				break;
			}
		}

		mundo.setBlockState(new BlockPos(e.getX()+x,e.getY()+y,e.getZ()+z), block, 3);
		return true;
	}
	
	public static BlockPos getBlockPos_relative(BlockPos e, Direction ori,int i,int j,int k) {
		BlockPos b;
		int x,y,z;
		switch(ori) {
			case 	SOUTH:
			default:{
				x=i;
				y=j;
				z=k;
				
				break;
			}
			case 	NORTH:{
				x=-i;
				y=j;
				z=-k;
				break;
			}
			case EAST:{
				x=k;
				y=j;
				z=-i;
				break;
			}
			case WEST:{
				x=-k;
				y=j;
				z=i;
				break;
			}
		}
		b=new BlockPos(e.getX()+x,e.getY()+y,e.getZ()+z);
		return b;
	}
	
	public static String printBlockCoords(BlockPos p) {
		return "("+p.getX()+","+p.getY()+","+p.getZ()+")";
	}
	
	
}
