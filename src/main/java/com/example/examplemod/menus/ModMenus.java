package com.example.examplemod.menus;

import java.util.function.Supplier;

import com.example.examplemod.ExampleMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
  public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU,
      ExampleMod.MODID);

  public static final Supplier<MenuType<ConverterMenu>> CONVERTER = MENU_TYPES.register("converter",
      () -> new MenuType<>(ConverterMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
