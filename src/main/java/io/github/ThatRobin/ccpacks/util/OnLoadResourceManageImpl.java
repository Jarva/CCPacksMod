package io.github.ThatRobin.ccpacks.util;

import io.github.ThatRobin.ccpacks.CCPacksMain;

public class OnLoadResourceManageImpl implements OnLoadResourceManager {



    public static void addSingleListener(DataLoader loader) {
        CCPacksMain.ccPacksRegistry.registerResources(loader, loader.dataType);
    }
}
