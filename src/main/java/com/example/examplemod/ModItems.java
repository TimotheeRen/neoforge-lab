package com.example.examplemod;

import java.util.function.Supplier;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MODID);
  public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister
      .createDataComponents(Registries.DATA_COMPONENT_TYPE, ExampleMod.MODID);

  public static final Supplier<DataComponentType<CounterComponent>> COUNTER = DATA_COMPONENTS.registerComponentType(
      "counter",
      builder -> builder.persistent(CounterComponent.RECORD_CODEC).networkSynchronized(CounterComponent.STREAM_CODEC));

  public static final Supplier<Item> EMERALD = ITEMS.registerItem("emerald", EmeraldItem::new);
  public static final Supplier<Item> EGG = ITEMS.register("egg", registry -> new Item(
      new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registry)).component(COUNTER.get(),
          new CounterComponent(0))));
}
