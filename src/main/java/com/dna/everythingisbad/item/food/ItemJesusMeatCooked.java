package com.dna.everythingisbad.item.food;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModDimensions;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import com.dna.everythingisbad.utils.ModTeleporter;
import com.dna.everythingisbad.world.dimension.HeavenProvider;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockPortal;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemJesusMeatCooked extends ItemFoodBase {


    public ItemJesusMeatCooked(String name) {
        super(6, 2, true);
        this.setRegistryName(name);
        this.setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);

    }
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));

            entityLiving.addPotionEffect(new PotionEffect(Potion.getPotionById(25),800,4));

            // TODO: Get rid of this and add the real Heaven get in method.
            // This is just a placeholder for testing.

            if (entityplayer instanceof EntityPlayerMP)
            {
                EntityPlayerMP mp = (EntityPlayerMP)entityLiving;
                WorldServer server = mp.getServerWorld();
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
                int dimension = ModDimensions.HEAVEN.getId();
                mp.changeDimension(dimension,new ModTeleporter(server,mp.posX,mp.posY,mp.posZ));
                //Teleporter tellaporter = new Teleporter(server);
                //server.getMinecraftServer().getPlayerList().transferPlayerToDimension(mp,dimension,tellaporter);
            }
        }

        stack.shrink(1);
        return stack;
    }

}