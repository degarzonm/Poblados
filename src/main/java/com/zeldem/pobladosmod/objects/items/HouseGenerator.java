package com.zeldem.pobladosmod.objects.items;

import com.zeldem.pobladosmod.PobladosMod;
import com.zeldem.pobladosmod.util.structures.house.House;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HouseGenerator extends Item{
	
	public HouseGenerator(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		
		World mundo= context.getWorld();
		BlockPos pos= context.getPos();
	
		Direction orientacion=context.getPlayer().getHorizontalFacing();
		House h1=new House( mundo, pos,3,orientacion);
		h1.buildHouse();
		return ActionResultType.SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
