package com.zeldem.pobladosmod.util.structures.house;

import com.zeldem.pobladosmod.PobladosMod;
import com.zeldem.pobladosmod.util.structures.house.sala.Sala;

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
												,Helpers.rand(2, 4)
												,Helpers.rand(2, 4)
												,Helpers.rand(6, 8)
												,Helpers.rand(4, 6));
		
		
		
		
		PobladosMod.LOGGER.debug("principal:"+principal);
		
		
		Sala subSalaIzq= new Sala(mundo,principal.getALeftEntrance(),orientacion.rotateYCCW()	
																	,Helpers.rand(1, 4)
																	,Helpers.rand(1, 4)
																	,principal.h-1
																	,principal.frente/2);
		PobladosMod.LOGGER.debug("izq:"+subSalaIzq);
		
		Sala subSalaDer= new Sala(mundo,principal.getARightEntrance(),orientacion.rotateY()	
				,Helpers.rand(1, 4)
				,Helpers.rand(1, 4)
				,principal.h-1
				,principal.frente/2);
		PobladosMod.LOGGER.debug("der:"+subSalaDer);
		
		Sala portico= new Sala(mundo,entrada,orientacion.rotateY().rotateY()	
				,Helpers.rand(1, 4)
				,Helpers.rand(1, 4)
				,principal.h-1
				,4,false,false,false,false);
		}
	}
	
}
