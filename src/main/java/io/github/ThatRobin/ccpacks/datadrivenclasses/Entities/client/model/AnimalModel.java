package io.github.ThatRobin.ccpacks.datadrivenclasses.Entities.client.model;

import io.github.ThatRobin.ccpacks.CCPacksMain;
import io.github.ThatRobin.ccpacks.datadrivenclasses.Entities.DDAnimalEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnimalModel extends AnimatedGeoModel<DDAnimalEntity> {
    @Override
    public Identifier getAnimationFileLocation(DDAnimalEntity entity) {
        return new Identifier(CCPacksMain.MODID, "animations/vehicle.animation.json");
    }

    @Override
    public Identifier getModelLocation(DDAnimalEntity entity) {
        return new Identifier(CCPacksMain.MODID, "geo/vehicle.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DDAnimalEntity entity) {
        return new Identifier(CCPacksMain.MODID, "textures/model/entity/vehicle.png");
    }
}