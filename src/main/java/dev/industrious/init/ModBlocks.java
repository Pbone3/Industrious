package dev.industrious.init;

import dev.industrious.Industrious;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.fabricmc.fabric.impl.object.builder.FabricBlockInternals;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.BlockSoundGroup;
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
    }

    public enum MaterialStorageBlocks implements ItemConvertible {
        RAW_TIN(Material.STONE, MapColor.YELLOW);

        public final String Name;
        public final Block Block;

        MaterialStorageBlocks(Material material, MapColor color) {
            Name = this.toString().toLowerCase(Locale.ROOT) + "_block";
            Block = new Block(FabricBlockSettings.of(material, color)
                    .breakByTool(FabricToolTags.PICKAXES, MiningLevels.WOOD)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
                    .strength(5.0F, 6.0F));

            Industrious.RegisterBlock(Name, Block);
            Industrious.RegisterItem(Name, new BlockItem(Block, new FabricItemSettings().group(Industrious.GROUP_BLOCKS)));
        }

        @Override
        public Item asItem() {
            return Block.asItem();
        }

        public static void Redundant() {

        }
    }
}
