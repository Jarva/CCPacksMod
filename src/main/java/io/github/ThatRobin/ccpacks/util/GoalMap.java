package io.github.ThatRobin.ccpacks.util;

import com.google.common.collect.Lists;
import io.github.ThatRobin.ccpacks.factories.TaskFactories.TaskRegistry;
import io.github.ThatRobin.ccpacks.factories.TaskFactories.TaskType;
import net.minecraft.util.Identifier;

import java.util.List;

public class GoalMap {

    public static List<TaskType> goals = Lists.newArrayList(TaskRegistry.get(new Identifier("ccpacks", "goal")));

    public List<TaskType> getGoals() {
        return this.goals;
    }
}
