package com.example.examplemod;

import javax.annotation.Nullable;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class Wanderer extends Entity {
  public Wanderer(EntityType<? extends Wanderer> type, Level level) {
    super(type, level);
  }

  @Override
  public void readAdditionalSaveData(ValueInput input) {
  }

  @Override
  public void addAdditionalSaveData(ValueOutput output) {
  }

  @Override
  protected void defineSynchedData(SynchedEntityData.Builder entityData) {
  }

  @Override
  public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
    this.discard();
    return true;
  }

  @Override
  @Nullable
  public ItemStack getPickResult() {
    return new ItemStack(ModItems.EMERALD.get());
  }

  @Override
  public boolean isPickable() {
    return true;
  }
}
