package dev.industrious;

import dev.industrious.init.ContentRegisterer;
import dev.industrious.init.ModBlocks;
import dev.industrious.init.ModItems;
import dev.industrious.utils.InitUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Industrious implements ModInitializer {
    public static final String MOD_ID = "industrious";

    public static final ModItems ITEMS = new ModItems();
    public static final ModBlocks BLOCKS = new ModBlocks();

    public static final ItemGroup GROUP_MATERIALS = FabricItemGroupBuilder.create(
            InitUtils.ModId("industrious_materials"))
            .icon(ModItems.Ingots.TIN::getStack)
            .appendItems(stacks -> {
                // Raw ores should always be next to the ingot
                // Nugget positions seem to be random in vanilla, I'll put them next to the ingot as well
                stacks.add(ModItems.RawOre.TIN.getStack());
                stacks.add(ModItems.Ingots.TIN.getStack());
                stacks.add(ModItems.Nuggets.TIN.getStack());

                stacks.add(ModItems.Ingots.STEEl.getStack());

                // Misc materials should come after metals
                stacks.add(ModItems.MiscMaterials.RUBY.getStack());
            })
            .build();

    public static final ItemGroup GROUP_BLOCKS = FabricItemGroupBuilder.create(
            InitUtils.ModId("industrious_blocks"))
            .icon(() -> ModBlocks.MaterialStorageBlocks.RAW_TIN.asItem().getDefaultStack())
            .build();

    @Override
    public void onInitialize() {
        ITEMS.RegisterContent();
        BLOCKS.RegisterContent();
    }

    public static <T> void Register(ContentRegisterer<T> contentRegisterer, String id, T obj) {
        contentRegisterer.Register(id, obj);
    }

    public static void RegisterItem(String id, Item obj) {
        Register(ITEMS, id, obj);
    }
    public static void RegisterBlock(String id, Block obj) {
        Register(BLOCKS, id, obj);
    }
    public static void RegisterBlockAndItem(String id, Block obj) {
        BLOCKS.RegisterBlockAndItem(id, obj);
    }
}
