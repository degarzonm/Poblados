package com.zeldem.pobladosmod.init;

import com.zeldem.pobladosmod.PobladosMod;
import com.zeldem.pobladosmod.PobladosMod.PobladosItemGroup;
import com.zeldem.pobladosmod.objects.items.*;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid=PobladosMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(PobladosMod.MOD_ID)
public class ItemInit {

	public static final Item palta=null;
	public static final Item house_generator =null;
	public static final Item wall_generator =null;
	public static final Item road_generator=null;
	
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
	
		event.getRegistry().register(new Item(new Item.Properties().group(PobladosItemGroup.POB_ITEMS)
				.food(new Food.Builder().hunger(4).saturation(12).meat().fastToEat().build())).setRegistryName("palta"));
		event.getRegistry().register(new HouseGenerator(new Item.Properties().group(PobladosItemGroup.POB_ITEMS)).setRegistryName("house_generator"));
		event.getRegistry().register(new WallGenerator(new Item.Properties().group(PobladosItemGroup.POB_ITEMS)).setRegistryName("wall_generator"));
		event.getRegistry().register(new RoadGen(new Item.Properties().group(PobladosItemGroup.POB_ITEMS)).setRegistryName("road_generator"));
	}
}
