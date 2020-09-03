package com.zeldem.pobladosmod.world.gen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;


public class TutorialOreGenerator {

	public static void generateOre(FMLLoadCompleteEvent event) {
		
		for (SurfaceBuilder<?> biome : ForgeRegistries.SURFACE_BUILDERS) {
			
		}
		
	}
	
}
