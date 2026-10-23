package com.example.examplemod;

import java.util.function.Supplier;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MODID);

  // public static final Supplier<Item> EMERALD =
  // ITEMS.registerSimpleItem("emerald");
  public static final Supplier<Item> EMERALD = ITEMS.registerItem("emerald", EmeraldItem::new);
}
