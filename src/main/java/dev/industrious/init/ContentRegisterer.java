package dev.industrious.init;

import dev.industrious.utils.InitUtils;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public abstract class ContentRegisterer<T> {
    public abstract Registry<T> GetRegistry();

    public abstract void RegisterContent();

    public void Register(String id, T regObj) {
        Register(ModId(id), regObj);
    }

    public void Register(Identifier id, T regObj) {
        Registry.register(GetRegistry(), id, regObj);
    }

    // Just a quick shorthand
    public Identifier ModId(String id) {
        return InitUtils.ModId(id);
    }
}
