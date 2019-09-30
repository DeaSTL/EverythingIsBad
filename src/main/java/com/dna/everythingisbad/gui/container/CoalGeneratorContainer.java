package com.dna.everythingisbad.gui.container;

import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.SlotItemHandler;

public class CoalGeneratorContainer extends DeviceContainerBase {
    public CoalGeneratorContainer(IInventory playerInventory, TileDeviceBase tileentity) {
        super(playerInventory, tileentity);
        //Input Slot
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 0, 57, 35));
    }
}
