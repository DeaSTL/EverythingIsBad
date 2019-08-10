package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTabEverythingBad;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;

public class ItemPoopBrick extends ItemBase {
    public ItemPoopBrick(String name)
    {
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabEverythingBad.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }

}
