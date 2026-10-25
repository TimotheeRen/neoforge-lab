package com.example.examplemod;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;

public class Wanderer extends PathfinderMob {
  public Wanderer(EntityType<? extends Wanderer> type, Level level) {
    super(type, level);
  }

  @Override
  @Nullable
  public ItemStack getPickResult() {
    return new ItemStack(ModItems.EMERALD.get());
  }

  @Override
  public float getWalkTargetValue(BlockPos pos, LevelReader level) {
    return 10.0F;
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
    this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 1.0F));
    this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
    this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, true));
  }
}
