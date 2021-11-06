package io.github.ThatRobin.ccpacks.datadrivenclasses.Entities.goals;

import io.github.ThatRobin.ccpacks.factories.TaskFactories.TaskFactory;
import io.github.ThatRobin.ccpacks.factories.TaskFactories.TaskType;
import net.minecraft.util.Identifier;

public class DDFollowTargetGoal extends TaskType {

    public DDFollowTargetGoal(Identifier id, TaskFactory<TaskType>.Instance factory) {
        super(id, factory);
    }

}
