package io.github.ThatRobin.ccpacks.factories.TaskFactories;

import io.github.ThatRobin.ccpacks.datadrivenclasses.Entities.goals.DDAttackGoal;
import io.github.ThatRobin.ccpacks.registries.CCPacksRegistries;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TaskFactories {

    public static Identifier identifier(String path) {
        return new Identifier("mob_tasks", path);
    }

    public static void register() {
        register(new TaskFactory<>(identifier("attack"),
                new SerializableData(),
                data ->
                        (identifier, factory) -> {
                            DDAttackGoal attackGoal = new DDAttackGoal(identifier, factory);
                            return attackGoal;
                        }));
    }

    private static void register(TaskFactory serializer) {
        Registry.register(CCPacksRegistries.TASK_FACTORY, serializer.getSerializerId(), serializer);
    }

}
