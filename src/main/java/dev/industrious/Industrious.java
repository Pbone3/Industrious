package dev.industrious;

import dev.industrious.init.ContentRegisterer;
import dev.industrious.init.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Industrious implements ModInitializer {
    public static final String MOD_ID = "industrious";

    public static final ModItems ITEMS = new ModItems();

    public static final ItemGroup GROUP_MATERIALS = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "industrious_materials"))
            .icon(() -> new ItemStack(ModItems.Ingots.TIN))
            .appendItems(stacks -> {
                // Raw ores should always be next to the ingot
                // Nugget positions seem to be random in vanilla, I'll put them next to the ingot as well

                stacks.add(ModItems.RawOre.TIN.getStack());
                stacks.add(ModItems.Ingots.TIN.getStack());
                stacks.add(ModItems.Nuggets.TIN.getStack());

                stacks.add(ModItems.Ingots.STEEl.getStack());
            })
            .build();

    @Override
    public void onInitialize() {
        ITEMS.RegisterContent();
    }

    public static <T> void Register(ContentRegisterer<T> contentRegisterer, String id, T obj) {
        contentRegisterer.Register(id, obj);
    }

    public static void RegisterItem(String id, Item obj) {
        Register(ITEMS, id, obj);
    }
}
