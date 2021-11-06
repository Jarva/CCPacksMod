package io.github.ThatRobin.ccpacks.util;

public interface OnLoadResourceManager {

    static void addSingleListener(DataLoader loader) {
        OnLoadResourceManageImpl.addSingleListener(loader);
    }
}
