package io.github.ThatRobin.ccpacks.registries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.github.ThatRobin.ccpacks.factories.ContentFactories.ContentTypes;
import io.github.ThatRobin.ccpacks.factories.ContentFactories.ContentTypesClient;
import io.github.ThatRobin.ccpacks.util.DataLoader;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ContentManager extends DataLoader {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    public ContentManager() {
        super(GSON, "content");
    }

    @Override
    public void apply(Map<Identifier, JsonObject> map) {
        map.forEach(ContentTypes::new);
        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            map.forEach(ContentTypesClient::new);
        }
    }

}
