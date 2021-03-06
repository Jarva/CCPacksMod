package io.github.ThatRobin.ccpacks.Factories.ContentFactories;

import io.github.ThatRobin.ccpacks.Util.Portal;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.block.Block;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ContentType {

    private Identifier identifier;
    private ContentFactory<Supplier<?>>.Instance factory;

    private String nameTranslationKey;
    private String descriptionTranslationKey;

    public ContentType(Identifier id, ContentFactory<Supplier<?>>.Instance factory) {
        this.identifier = id;
        this.factory = factory;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public ContentFactory<Supplier<?>>.Instance getFactory() {
        return factory;
    }

    public ContentType setHidden() {
        return this;
    }

    public void setTranslationKeys(String name, String description) {
        this.nameTranslationKey = name;
        this.descriptionTranslationKey = description;
    }

    public Item createItem(ContentType contentType) {
        return (Item) factory.apply(contentType, null).get();
    }

    public Block createBlock(ContentType contentType) {
        return (Block) factory.apply(contentType, null).get();
    }

    public Enchantment createEnchant(ContentType contentType) {
        return (Enchantment) factory.apply(contentType, null).get();
    }

    public KeyBinding createKeybind(ContentType contentType) {
        return (KeyBinding) factory.apply(contentType, null).get();
    }

    public StatusEffect createEffect(ContentType contentType) {
        return (StatusEffect) factory.apply(contentType, null).get();
    }

    public Portal createPortal(ContentType contentType) {
        return (Portal) factory.apply(contentType, null).get();
    }

    public EntityType createProjectile(ContentType contentType) {
        return (EntityType) factory.apply(contentType, null).get();
    }

    public DefaultParticleType createParticle(ContentType contentType) {
        return (DefaultParticleType) factory.apply(contentType, null).get();
    }

    public String getOrCreateNameTranslationKey() {
        if(nameTranslationKey == null || nameTranslationKey.isEmpty()) {
            nameTranslationKey =
                    "item." + identifier.getNamespace() + "." + identifier.getPath() + ".name";
        }
        return nameTranslationKey;
    }

    public TranslatableText getName() {
        return new TranslatableText(getOrCreateNameTranslationKey());
    }

    public String getOrCreateDescriptionTranslationKey() {
        if(descriptionTranslationKey == null || descriptionTranslationKey.isEmpty()) {
            descriptionTranslationKey =
                    "item." + identifier.getNamespace() + "." + identifier.getPath() + ".description";
        }
        return descriptionTranslationKey;
    }

    public TranslatableText getDescription() {
        return new TranslatableText(getOrCreateDescriptionTranslationKey());
    }

    @Override
    public int hashCode() {
        return this.identifier.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof PowerType)) {
            return false;
        }
        Identifier id = ((PowerType)obj).getIdentifier();
        return identifier.equals(id);
    }
}

