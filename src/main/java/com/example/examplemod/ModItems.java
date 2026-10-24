package com.example.examplemod;

import java.util.function.Supplier;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
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

  @SubscribeEvent
  public static void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
      event.accept(new ItemStack(EGG.get()));
      event.accept(new ItemStack(TURKEY.get()));
    } else if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
      event.accept(new ItemStack(EMERALD.get()));
    } else if (event.getTabKey() == CreativeModeTabs.COMBAT) {
      event.accept(new ItemStack(SkyEquipments.SKY_SWORD.get()));
      event.accept(new ItemStack(SkyEquipments.SKY_HELMET.get()));
      event.accept(new ItemStack(SkyEquipments.SKY_CHESTPLATE.get()));
      event.accept(new ItemStack(SkyEquipments.SKY_LEGGINGS.get()));
      event.accept(new ItemStack(SkyEquipments.SKY_BOOTS.get()));
    }
  }
}
