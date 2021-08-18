package dev.industrious.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.industrious.Industrious;
import dev.industrious.utils.JsonUtils;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

// Automatically registers blast furnace recipes for smelting recipes, unless they say otherwise
// Because I don't like writing a bunch of json
@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Inject(method = "apply", at = @At("HEAD"))
    public void ApplyHook(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo callback) {
        Map<Identifier, JsonElement> newRecipes = new HashMap<>();

        for (Map.Entry<Identifier, JsonElement> entry : map.entrySet()) {
            // Identifier format for reference: "namespace:path"
            Identifier id = entry.getKey();

            if (id.getNamespace().equals(Industrious.MOD_ID)) {
                // Ok, so it's an industrious recipe
                // Is it a smelting one?
                JsonObject jObj = JsonHelper.asObject(entry.getValue(), "top element");
                Recipe<?> recipe = RecipeManager.deserialize(id, jObj);

                if (recipe.getType() == RecipeType.SMELTING) {
                    // Does it want an automatic blasting recipe?
                    // Assume yes if it doesn't say anything
                    if (!jObj.has("industrious_autoBlasting") || jObj.get("industrious_autoBlasting").getAsBoolean()) {
                        // Give it an automatic blasting recipe
                        JsonObject blastingObj = JsonUtils.DeepCopy(jObj);

                        // Change recipe type to blasting
                        JsonUtils.SwapJsonProperty(blastingObj, "type", "minecraft:blasting");

                        // Half cooking time
                        int cookingTime = jObj.get("cookingtime").getAsInt();
                        JsonUtils.SwapJsonProperty(blastingObj, "cookingtime", cookingTime / 2);

                        // Add it to the dictionary of recipes to register
                        newRecipes.put(new Identifier(id.getNamespace(), id.getPath() + "_blasting"), blastingObj);
                    }
                }
            }
        }

        // Add the new blasting recipes
        map.putAll(newRecipes);
    }
}
