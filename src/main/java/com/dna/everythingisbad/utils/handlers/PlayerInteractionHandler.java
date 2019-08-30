package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.helpers.TimeHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerInteractionHandler {

    public static void reallyMilked(PlayerInteractEvent.EntityInteract event){
        Entity target = event.getTarget();
        String target_name = target.getName();

        EntityPlayer player = event.getEntityPlayer();
        //EnumHand hand = player.getActiveHand();
        // I had trouble with this, since it spits an error every time I use it

        ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        // This works however, but it's ugly
        FluidStack fluidstack = new FluidStack(ModFluids.DIARIA.getFluid(),1);
        ItemStack bucket = FluidUtil.getFilledBucket(fluidstack);

        if (target_name.equals("Pig")) {
            //Main.logger.info("Pig milked");
            if (itemstack.getItem() == Items.BUCKET && !player.capabilities.isCreativeMode) {
                player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
                itemstack.shrink(1);
                if (itemstack.isEmpty()) {

                    player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,bucket);

                } else if (!player.inventory.addItemStackToInventory(bucket)) {
                    player.dropItem(bucket, false);
                }
            }
        }
    }

    public static void wolfFed(PlayerInteractEvent.EntityInteract event){
        Entity target = event.getTarget();

        String target_name = target.getName();

        EntityPlayer player = event.getEntityPlayer();

        ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
        if (target_name.equals("Wolf")) {
            if (itemstack.getItem() == ModItems.JESUS_MEAT_COOKED || itemstack.getItem() == ModItems.JESUS_MEAT_RAW){
                EntityWolf wolf = (EntityWolf)target;
                wolf.addPotionEffect(new PotionEffect(Potion.getPotionById(25), TimeHelper.fromSeconds(30), 1));
            }
        }
    }
}
