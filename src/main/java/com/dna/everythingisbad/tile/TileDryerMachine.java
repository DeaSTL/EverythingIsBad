package com.dna.everythingisbad.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class TileDryerMachine extends TileMachineBase {
    private NonNullList<ItemStack> tileContents = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);

    public TileDryerMachine() {
        super("dryer_machine");
    }
}
