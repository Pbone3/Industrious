package dev.industrious.init;

import dev.industrious.Industrious;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

public class ModItems extends ContentRegisterer<Item> {
    @Override
    public Registry<Item> GetRegistry() {
        return Registry.ITEM;
    }

    @Override
    public void RegisterContent() {
        // These are required so the enum's ctor runs.
        Ingots.Redundant();
        RawOre.Redundant();
        Nuggets.Redundant();
    }

    public enum Ingots implements ItemConvertible {
        TIN, STEEl;

        public final String Name;
        public final Item Item;

        Ingots() {
            Name = this.toString().toLowerCase(Locale.ROOT) + "_ingot";
            Item = new Item(new FabricItemSettings().group(Industrious.GROUP_MATERIALS));
            Industrious.RegisterItem(Name, Item);
        }

        public ItemStack getStack() {
            return new ItemStack((asItem()));
        }

        @Override
        public Item asItem() {
            return Item;
        }

        public static void Redundant() {

        }
    }

    public enum Nuggets implements ItemConvertible {
        TIN;

        public final String Name;
        public final Item Item;

        Nuggets() {
            Name = this.toString().toLowerCase(Locale.ROOT) + "_nugget";
            Item = new Item(new FabricItemSettings().group(Industrious.GROUP_MATERIALS));
            Industrious.RegisterItem(Name, Item);
        }

        public ItemStack getStack() {
            return new ItemStack((asItem()));
        }

        @Override
        public Item asItem() {
            return Item;
        }

        public static void Redundant() {

        }
    }

    public enum RawOre implements ItemConvertible {
        TIN;

        public final String Name;
        public final Item Item;

        RawOre() {
            Name = "raw_" + this.toString().toLowerCase(Locale.ROOT);
            Item = new Item(new FabricItemSettings().group(Industrious.GROUP_MATERIALS));
            Industrious.RegisterItem(Name, Item);
        }

        public ItemStack getStack() {
            return new ItemStack((asItem()));
        }

        @Override
        public Item asItem() {
            return Item;
        }

        public static void Redundant() {

        }
    }
}
