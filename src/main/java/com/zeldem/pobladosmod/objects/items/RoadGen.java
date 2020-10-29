package com.zeldem.pobladosmod.objects.items;


import com.zeldem.pobladosmod.PobladosMod;
import com.zeldem.pobladosmod.util.structures.house.Helpers;
import com.zeldem.pobladosmod.util.structures.house.Road;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RoadGen extends Item{
	
	public static boolean enEspera=true;
	public static BlockPos tempA;
	public static BlockPos tempB;
	
	public RoadGen(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		
		World mundo= context.getWorld();
		BlockPos pos= context.getPos();
		if(!mundo.isRemote) {
			if(enEspera) {
				tempA=new BlockPos(pos.getX(),pos.getY(),pos.getZ());
				PobladosMod.LOGGER.debug("first block catch at" +Helpers.printBlockCoords(tempA));
				
			}else {
				tempB=new BlockPos(pos.getX(),pos.getY(),pos.getZ());
				PobladosMod.LOGGER.debug("second block catch at" +Helpers.printBlockCoords(tempB)+"\n building road...");
				
				Road camino= new Road(mundo,tempA,tempB,2);
				camino.buildRoad();
				
			}
			enEspera=!enEspera;
		}
		return ActionResultType.SUCCESS;
	}
	
}
