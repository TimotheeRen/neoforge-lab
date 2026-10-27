package com.example.examplemod.items;

import java.util.function.Supplier;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.blocks.ModBlocks;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
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
  public static final Supplier<Item> TURKEY = ITEMS.registerSimpleItem("turkey",
      props -> props.component(DataComponents.CONSUMABLE, Consumable.builder()
          .consumeSeconds(2f)
          .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.STRENGTH, 600, 5)))
          .sound(SoundEvents.ARMOR_EQUIP_CHAIN)
          .build()));
  public static final Supplier<BlockItem> MUD = ITEMS.registerSimpleBlockItem("mud_block", ModBlocks.MUD);
  public static final Supplier<BlockItem> IRON_CHEST = ITEMS.registerSimpleBlockItem("iron_chest",
      ModBlocks.IRON_CHEST);
  public static final Supplier<BlockItem> CONVERTER = ITEMS.registerSimpleBlockItem("converter",
      ModBlocks.CONVERTER);
}
