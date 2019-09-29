package com.dna.everythingisbad.init;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.ArrayList;

public class ModLootTables {
    public static ArrayList<ResourceLocation> LOOT_TABLES = new ArrayList<ResourceLocation>();
    public static final ResourceLocation ENTITY_JESUS_LOOT = LootTableList.register(new ResourceLocation(Reference.MOD_ID,"entities/jesus"));
    public static final ResourceLocation CHEST_EVERYTHINGBAD_LOOT = LootTableList.register(new ResourceLocation(Reference.MOD_ID,"chest/everythingbad"));
    public static final ResourceLocation QUESTION_MARK_BLOCK_LOOT = LootTableList.register(new ResourceLocation(Reference.MOD_ID,"question_mark_block"));
    public static final ResourceLocation ENTITY_ANGEL_LOOT = LootTableList.register(new ResourceLocation(Reference.MOD_ID,"entities/angel"));

    public static ResourceLocation register(String id,String name)
    {
        if (LOOT_TABLES.add(new ResourceLocation(id,name)))
        {

            return new ResourceLocation(id,name);
        }
        else
        {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }

}
