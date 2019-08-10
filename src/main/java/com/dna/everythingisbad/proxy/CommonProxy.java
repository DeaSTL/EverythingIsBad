package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.init.ModSmeltingRecipes;
import com.dna.everythingisbad.utils.handlers.ServerTimeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy{
    public void registerItemRenderer(Item item, int meta, String id) {}

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        Main.logger.info("Pre Intializing");
    }

    @Override
    public void init(FMLInitializationEvent event) {

        Main.logger.info("Intializing");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        Main.logger.info("Post Intializing");
        ModSmeltingRecipes.init();
        ModPotions.registerPotions();


        //MinecraftForge.EVENT_BUS.register(new KeyHandler());
        //MinecraftForge.EVENT_BUS.register(new TestHandler());
    }
}
