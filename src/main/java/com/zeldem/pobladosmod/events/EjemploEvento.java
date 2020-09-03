package com.zeldem.pobladosmod.events;

import com.zeldem.pobladosmod.PobladosMod;

import net.minecraftforge.event.TickEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=PobladosMod.MOD_ID, bus = Bus.FORGE)
public class EjemploEvento {

	
	@SubscribeEvent
	public static void catching(TickEvent ev) {
		
		
	}
}
