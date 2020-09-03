package com.zeldem.pobladosmod.objects.items;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;



public class WallGenerator extends SwordItem {

	public WallGenerator(Properties properties) {
		
		super(ItemTier.DIAMOND, 12, -3.0F, properties);
	}
	

	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		
		World mundo= context.getWorld();
		BlockPos pos= context.getPos();
		
		if(!mundo.isRemote) {
			for(int a=2;a<20;a++) {
				for(int i=0;i<10;i++) {
					for(int j=0;j<10;j++) {
						
						BlockPos postemp= new BlockPos(pos.getX()+i, pos.getY()+a, pos.getZ()+j);
						
						mundo.setBlockState(postemp, Blocks.ACACIA_PLANKS.getDefaultState(), 3);
					}
				}
			}
		}
		
		return ActionResultType.SUCCESS;
	}
}
