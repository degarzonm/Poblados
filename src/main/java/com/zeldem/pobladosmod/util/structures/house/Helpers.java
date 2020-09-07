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
	
	
}
