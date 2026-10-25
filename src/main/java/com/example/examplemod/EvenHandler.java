package com.example.examplemod;

import com.example.examplemod.entities.ModEntities;
import com.example.examplemod.entities.WandererModel;
import com.example.examplemod.entities.WandererRender;
import com.example.examplemod.items.ModItems;
import com.example.examplemod.items.SkyEquipments;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = ExampleMod.MODID)
public class EvenHandler {
  @SubscribeEvent
  public static void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
      event.accept(new ItemStack(ModItems.EGG.get()));
      event.accept(new ItemStack(ModItems.TURKEY.get()));
    } else if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
      event.accept(new ItemStack(ModItems.EMERALD.get()));
    } else if (event.getTabKey() == CreativeModeTabs.COMBAT) {
      event.accept(new ItemStack(SkyEquipments.SKY_SWORD.get()));
      event.accept(new ItemStack(SkyEquipments.SKY_HELMET.get()));
      event.accept(new ItemStack(SkyEquipments.SKY_CHESTPLATE.get()));
      event.accept(new ItemStack(SkyEquipments.SKY_LEGGINGS.get()));
      event.accept(new ItemStack(SkyEquipments.SKY_BOOTS.get()));
    }
  }

  @SubscribeEvent
  public static void modifyComponents(ModifyDefaultComponentsEvent event) {
    event.modify(Items.TNT, (components, context, item) -> {
      components.set(DataComponents.MAX_STACK_SIZE, 8);
      components.set(DataComponents.COOKING_FUEL,
          new CookingFuel(new ResolvableInt.Constant(20 * 5), new ResolvableFloat.Constant(1F)));
    });
  }

  @SubscribeEvent
  public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(ModEntities.WANDERER.get(), WandererRender::new);
  }

  @SubscribeEvent
  public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
    event.registerLayerDefinition(ModEntities.WANDERER_LAYER, WandererModel::createBodyLayer);
  }

  @SubscribeEvent
  public static void createDefaultAttributes(EntityAttributeCreationEvent event) {
    event.put(ModEntities.WANDERER.get(), Mob.createMobAttributes()
        .add(Attributes.MAX_HEALTH, 50)
        .add(Attributes.FOLLOW_RANGE, 30)
        .add(Attributes.ATTACK_DAMAGE, 5)
        .add(Attributes.MOVEMENT_SPEED, 0.4)
        .build());
  }
}
