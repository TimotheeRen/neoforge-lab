package com.example.examplemod;

import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;

public class SkyEquipments {
  public static final TagKey<Block> INCORRECT_BLOCKS_FOR_DROPS = TagKey.create(BuiltInRegistries.BLOCK.key(),
      Identifier.fromNamespaceAndPath(ExampleMod.MODID, "incorrect_for_sky_tool"));
  public static final TagKey<Item> REPAIR_SKY_MATERIAL = TagKey.create(BuiltInRegistries.ITEM.key(),
      Identifier.fromNamespaceAndPath(ExampleMod.MODID, "repair_sky_material"));

  public static final ToolMaterial SKY_TOOL_MATERIAL = new ToolMaterial(INCORRECT_BLOCKS_FOR_DROPS, 200, 5f, 1.5f, 20,
      REPAIR_SKY_MATERIAL);

  public static final ResourceKey<EquipmentAsset> SKY_ASSET = ResourceKey.create(EquipmentAssets.ROOT_ID,
      Identifier.fromNamespaceAndPath("examplemod", "sky"));

  public static final ArmorMaterial SKY_ARMOR_MATERIAL = new ArmorMaterial(15,
      Map.of(
          ArmorType.HELMET, 3,
          ArmorType.CHESTPLATE, 8,
          ArmorType.LEGGINGS, 6,
          ArmorType.BOOTS, 3),
      20,
      SoundEvents.ARMOR_EQUIP_DIAMOND,
      5,
      0,
      REPAIR_SKY_MATERIAL,
      SKY_ASSET);

  public static final DeferredItem<Item> SKY_HELMET = ModItems.ITEMS.registerItem("sky_helmet",
      props -> new Item(props.humanoidArmor(SKY_ARMOR_MATERIAL, ArmorType.HELMET)));
  public static final DeferredItem<Item> SKY_CHESTPLATE = ModItems.ITEMS.registerItem("sky_chestplate",
      props -> new Item(props.humanoidArmor(SKY_ARMOR_MATERIAL, ArmorType.CHESTPLATE)));
  public static final DeferredItem<Item> SKY_LEGGINGS = ModItems.ITEMS.registerItem("sky_leggings",
      props -> new Item(props.humanoidArmor(SKY_ARMOR_MATERIAL, ArmorType.LEGGINGS)));
  public static final DeferredItem<Item> SKY_BOOTS = ModItems.ITEMS.registerItem("sky_boots",
      props -> new Item(props.humanoidArmor(SKY_ARMOR_MATERIAL, ArmorType.BOOTS)));

  public static final Supplier<Item> SKY_SWORD = ModItems.ITEMS.registerItem("sky_sword", props -> new Item(
      props.sword(SKY_TOOL_MATERIAL, 5f, -2f)));

  public static void init() {
  }
}
