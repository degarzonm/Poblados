package com.zeldem.pobladosmod.util.structures.house;

import com.zeldem.pobladosmod.PobladosMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.SlabType;
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
		if(!mundo.isRemote) {
		Sala principal = new Sala(mundo,entrada	,orientacion
												,Helpers.rand(3, 6)
												,Helpers.rand(2, 5)
												,Helpers.rand(1, 4)
												,Helpers.rand(4, 7));
		}
	}
	
}
