package io.github.ThatRobin.ccpacks.registries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.github.ThatRobin.ccpacks.factories.TaskFactories.TaskTypes;
import io.github.ThatRobin.ccpacks.util.DataLoader;
import net.minecraft.util.Identifier;

import java.util.Map;

public class TaskManager extends DataLoader {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    public TaskManager() {
        super(GSON, "mob_tasks");
    }

    @Override
    public void apply(Map<Identifier, JsonObject> map) {
        map.forEach(TaskTypes::new);
    }

}
