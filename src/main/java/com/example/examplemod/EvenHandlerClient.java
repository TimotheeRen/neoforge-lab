package com.example.examplemod;

import com.example.examplemod.menus.ConverterScreen;
import com.example.examplemod.menus.ModMenus;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = ExampleMod.MODID, value = Dist.CLIENT)
public class EvenHandlerClient {
  @SubscribeEvent
  public static void registerScreens(RegisterMenuScreensEvent event) {
    event.register(ModMenus.CONVERTER.get(), ConverterScreen::new);
  }
}
