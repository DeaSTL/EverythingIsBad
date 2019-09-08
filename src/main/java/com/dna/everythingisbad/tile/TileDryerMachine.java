package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.capabilities.ModFluidHandler;
import com.dna.everythingisbad.capabilities.ModItemHandler;
import com.dna.everythingisbad.init.DryerRecipes;
import com.dna.everythingisbad.utils.prototypes.DryerRecipePrototype;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileDryerMachine extends TileMachineBase {
    public TileDryerMachine() {
        super("dryer_machine");
        this.itemStackHadler = new ModItemHandler(2);
        this.fluidHandler = new ModFluidHandler(false,true);
        setFinishedProgress(2400);
        itemStackHadler.setExtractor(true);
        this.displayName = "Dryer Machine";
    }

    @Override
    public void update() {
        super.update();
        if(hasNecessaryItems()){
            if(itemStackHadler.getStackInSlot(1).getCount() < itemStackHadler.getSlotLimit(1)){
                stepProgress();
                reduceEnergy();
                if(getProgress() >= getFinishedProgress()){
                    insertOutput();
                    reduceInput();
                    setProgress(0);
                }
            }
        }else{
            setProgress(0);
        }
    }
    //Checks to make sure that all conditions for the recipe are met
    public boolean hasNecessaryItems(){
        FluidStack fluidStack = fluidHandler.getFluidTank().getFluid();
        if(fluidStack != null) {
            DryerRecipePrototype recipe = DryerRecipes.getRecipe(fluidStack.getFluid());
            if(recipe != null) {
                setFinishedProgress(recipe.getDuration());
                //Has a bucket of the fluid required for the recipe
                if (fluidStack.amount >= 1000) {
                    //Has enough energy for one tick of the recipe
                    if(energyHandler.getEnergyStored() >= recipe.getEnergyConsumed() / recipe.getDuration()){
                        Item itemInOutput = itemStackHadler.getStackInSlot(1).getItem();
                        if(itemInOutput == Items.AIR || itemInOutput == recipe.getOutput().getItem()) {
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                   return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public void insertOutput(){
        if(hasNecessaryItems()){
            FluidStack fluidStack = fluidHandler.getFluidTank().getFluid();
            DryerRecipePrototype recipe = DryerRecipes.getRecipe(fluidStack.getFluid());
            itemStackHadler.addItem(1,recipe.getOutput().copy(),false);
        }
    }
    public void reduceInput(){
        if(hasNecessaryItems()){
            fluidHandler.getFluidTank().drain(1000,true);
        }
    }
    public void reduceEnergy(){
        if(hasNecessaryItems()){
            FluidStack fluidStack = fluidHandler.getFluidTank().getFluid();
            DryerRecipePrototype recipe = DryerRecipes.getRecipe(fluidStack.getFluid());
            energyHandler.reduceEnergy(recipe.getEnergyConsumed() / recipe.getDuration(),false);
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(energyHandler);
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){

            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHadler);
        }
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){

            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidHandler);
        }
        return super.getCapability(capability, facing);
    }
}
