package io.github.ThatRobin.ccpacks.Registries;

import io.github.ThatRobin.ccpacks.Util.UniversalPower;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class UniversalPowerRegistry {

    private static HashMap<Identifier, UniversalPower> idToUP = new HashMap<>();

    public static UniversalPower register(Identifier id, UniversalPower power) {
        if(idToUP.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate universal power id tried to register: '" + id.toString() + "'");
        }
        idToUP.put(id, power);
        return power;
    }

    public static int size() {
        return idToUP.size();
    }

    public static Iterable<Map.Entry<Identifier, UniversalPower>> entries() {
        return idToUP.entrySet();
    }

    public static UniversalPower get(Identifier id) {
        if(!idToUP.containsKey(id)) {
            throw new IllegalArgumentException("Could not get power from id '" + id.toString() + "', as it was not registered!");
        }
        UniversalPower power = idToUP.get(id);
        return power;
    }

    public static boolean contains(Identifier id) {
        return idToUP.containsKey(id);
    }

    public static void clear() {
        idToUP.clear();
    }

    public static void reset() {
        clear();
    }
}
