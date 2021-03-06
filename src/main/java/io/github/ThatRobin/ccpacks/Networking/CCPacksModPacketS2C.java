package io.github.ThatRobin.ccpacks.Networking;

import io.github.ThatRobin.ccpacks.Choice.Choice;
import io.github.ThatRobin.ccpacks.Choice.ChoiceLayer;
import io.github.ThatRobin.ccpacks.Choice.ChoiceLayers;
import io.github.ThatRobin.ccpacks.Choice.ChoiceRegistry;
import io.github.ThatRobin.ccpacks.Component.ChoiceComponent;
import io.github.ThatRobin.ccpacks.Component.ModComponents;
import io.github.ThatRobin.ccpacks.Screen.ChooseChoiceScreen;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class CCPacksModPacketS2C {

    @Environment(EnvType.CLIENT)
    public static void register() {
        ClientPlayConnectionEvents.INIT.register(((clientPlayNetworkHandler, minecraftClient) -> {
            ClientPlayNetworking.registerReceiver(CCPacksModPackets.CONFIRM_CHOICE, CCPacksModPacketS2C::chosenPowers);
            ClientPlayNetworking.registerReceiver(CCPacksModPackets.OPEN_CHOICE_SCREEN, CCPacksModPacketS2C::openChoiceScreen);
        }));
    }

    @Environment(EnvType.CLIENT)
    private static void openChoiceScreen(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        boolean showDirtBackground = packetByteBuf.readBoolean();
        Identifier choiceLayer = packetByteBuf.readIdentifier();
        minecraftClient.execute(() -> {
            ArrayList<ChoiceLayer> layers = new ArrayList<>();
            ChoiceComponent component = ModComponents.CHOICE.get(minecraftClient.player);
            ChoiceLayer layer = ChoiceLayers.getLayer(choiceLayer);
            if(layer.isEnabled() && !component.hasChoice(layer)) {
                layers.add(layer);
            }
            minecraftClient.setScreen(new ChooseChoiceScreen(layers,0, showDirtBackground));
        });
    }

    @Environment(EnvType.CLIENT)
    private static void chosenPowers(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        ChoiceLayer layer = ChoiceLayers.getLayer(packetByteBuf.readIdentifier());
        Choice choice = ChoiceRegistry.get(packetByteBuf.readIdentifier());
        ActionFactory<Entity>.Instance action = choice.getAction();
        if(action != null) {
            action.accept(minecraftClient.player);
        }
        minecraftClient.execute(() -> {
            ChoiceComponent component = ModComponents.CHOICE.get(minecraftClient.player);
            component.setChoice(layer, choice);
        });
    }

}
