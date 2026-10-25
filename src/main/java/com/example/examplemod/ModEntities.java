package com.example.examplemod;

import java.util.function.Supplier;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
  public static final DeferredRegister.Entities ENTITIES = DeferredRegister.createEntities(ExampleMod.MODID);

  public static final Supplier<EntityType<Wanderer>> WANDERER = ENTITIES.registerEntityType("wanderer", Wanderer::new,
      MobCategory.CREATURE, builder -> builder.sized(1.0f, 2.0f));
}
