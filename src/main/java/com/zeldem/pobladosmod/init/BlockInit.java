package com.zeldem.pobladosmod.init;

import com.zeldem.pobladosmod.PobladosMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(PobladosMod.MOD_ID)
@Mod.EventBusSubscriber(modid = PobladosMod.MOD_ID, bus = Bus.MOD)
public class BlockInit {
	
	public static final Block EJEMPLO_BLOCK = null;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent .Register<Block> event) {
		event.getRegistry().register(new Block(Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(1.5f,6.0f).sound(SoundType.WOOD).harvestLevel(0)).setRegistryName("ejemplo_block"));
	}
	
	
	@SubscribeEvent
	public static void registerBlocksItem(final RegistryEvent .Register<Item> event) {
		event.getRegistry().register(new BlockItem(EJEMPLO_BLOCK, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("ejemplo_block"));
	}
	
	
}
