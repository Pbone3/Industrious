package dev.industrious.init;

import dev.industrious.Industrious;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.fabricmc.fabric.impl.object.builder.FabricBlockInternals;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

public class ModBlocks extends ContentRegisterer<Block> {
    @Override
    public Registry<Block> GetRegistry() {
        return Registry.BLOCK;
    }

    @Override
    public void RegisterContent() {
        // These are required so the enum's ctor runs.
        MaterialStorageBlocks.Redundant();
        Ores.Redundant();
        MiscBlocks.Redundant();
    }

    public void RegisterBlockAndItem(String name, Block block) {
        Industrious.RegisterBlock(name, block);
        Industrious.RegisterItem(name, new BlockItem(block, new FabricItemSettings().group(Industrious.GROUP_BLOCKS)));
    }

    public enum MaterialStorageBlocks implements ItemConvertible {
        RAW_TIN(Material.STONE, BlockSoundGroup.STONE, MapColor.YELLOW);

        public final String Name;
        public final Block Block;

        MaterialStorageBlocks(Material material, BlockSoundGroup soundGroup, MapColor color) {
            Name = this.toString().toLowerCase(Locale.ROOT) + "_block";
            Block = new Block(FabricBlockSettings.of(material, color)
                    .breakByTool(FabricToolTags.PICKAXES, MiningLevels.WOOD)
                    .requiresTool()
                    .sounds(soundGroup)
                    .strength(5.0F, 6.0F));

            Industrious.RegisterBlockAndItem(Name, Block);
        }

        @Override
        public Item asItem() {
            return Block.asItem();
        }

        public static void Redundant() {

        }
    }

    public enum Ores implements ItemConvertible {
        RUBY(MiningLevels.IRON);

        public final String Name;
        public final Block Block;

        Ores(int miningLevel) {
            Name = this.toString().toLowerCase(Locale.ROOT) + "_ore";
            Block = new OreBlock(FabricBlockSettings.copyOf(Blocks.STONE)
                    .breakByTool(FabricToolTags.PICKAXES, miningLevel)
                    .requiresTool()
                    .strength(3.0F, 3.0F));

            Industrious.RegisterBlockAndItem(Name, Block);
        }

        Ores(float hardness, float resistance, int miningLevel) {
            Name = this.toString().toLowerCase(Locale.ROOT) + "_ore";
            Block = new OreBlock(FabricBlockSettings.copyOf(Blocks.STONE)
                    .breakByTool(FabricToolTags.PICKAXES, miningLevel)
                    .requiresTool()
                    .strength(hardness, resistance));

            Industrious.RegisterBlockAndItem(Name, Block);
        }

        @Override
        public Item asItem() {
            return Block.asItem();
        }

        public static void Redundant() {

        }
    }

    public enum MiscBlocks implements ItemConvertible {
        MACHINE_FRAME(Material.METAL, BlockSoundGroup.METAL, MapColor.GRAY, FabricToolTags.PICKAXES, MiningLevels.STONE, 5.0F, 6.0F);

        public final String Name;
        public final Block Block;

        MiscBlocks(Material material, BlockSoundGroup soundGroup, MapColor color, Tag<Item> tool,
                   int level, float hardness, float resistance) {
            Name = this.toString().toLowerCase(Locale.ROOT) + "_block";
            Block = new Block(FabricBlockSettings.of(material, color)
                    .breakByTool(tool, level)
                    .requiresTool()
                    .sounds(soundGroup)
                    .strength(hardness, resistance));

            Industrious.RegisterBlockAndItem(Name, Block);
        }

        @Override
        public Item asItem() {
            return Block.asItem();
        }

        public static void Redundant() {

        }
    }
}
