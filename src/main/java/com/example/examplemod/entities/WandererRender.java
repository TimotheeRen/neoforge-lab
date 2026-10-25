package com.example.examplemod.entities;

import net.minecraft.client.renderer.entity.MobRenderer;

import com.example.examplemod.ExampleMod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class WandererRender
    extends MobRenderer<Wanderer, LivingEntityRenderState, WandererModel> {

  private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
      ExampleMod.MODID,
      "textures/entity/wanderer.png");

  public WandererRender(EntityRendererProvider.Context context) {
    super(
        context,
        new WandererModel(
            context.bakeLayer(WandererModel.LAYER_LOCATION)),
        0.5F);
  }

  @Override
  public LivingEntityRenderState createRenderState() {
    return new LivingEntityRenderState();
  }

  @Override
  public Identifier getTextureLocation(LivingEntityRenderState state) {
    return TEXTURE;
  }
}
