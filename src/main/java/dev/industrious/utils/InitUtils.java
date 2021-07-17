package dev.industrious.utils;

import dev.industrious.Industrious;
import net.minecraft.util.Identifier;

public class InitUtils {
    public static Identifier ModId(String id) {
        return new Identifier(Industrious.MOD_ID, id);
    }
}
