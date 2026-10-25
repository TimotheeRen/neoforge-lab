package com.example.examplemod.entities;

import com.example.examplemod.ExampleMod;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class WandererModel extends EntityModel<LivingEntityRenderState> {

  public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
      Identifier.fromNamespaceAndPath(
          ExampleMod.MODID, "wanderer"),
      "main");

  public final ModelPart bb_main;

  public WandererModel(ModelPart root) {
    super(root);
    this.bb_main = root.getChild("bb_main");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    partdefinition.addOrReplaceChild(
        "bb_main",
        CubeListBuilder.create()
            .texOffs(16, 0)
            .addBox(
                -1.0F, -13.0F, -1.0F,
                2.0F, 2.0F, 2.0F,
                new CubeDeformation(0.0F))
            .texOffs(0, 14)
            .addBox(
                -2.0F, -14.0F, -2.0F,
                4.0F, 1.0F, 4.0F,
                new CubeDeformation(0.0F))
            .texOffs(0, 0)
            .addBox(
                -2.0F, -11.0F, -2.0F,
                4.0F, 10.0F, 4.0F,
                new CubeDeformation(0.0F))
            .texOffs(16, 4)
            .addBox(
                -1.0F, -2.0F, 1.0F,
                2.0F, 2.0F, 2.0F,
                new CubeDeformation(0.0F))
            .texOffs(16, 8)
            .addBox(
                -1.0F, -2.0F, -3.0F,
                2.0F, 2.0F, 2.0F,
                new CubeDeformation(0.0F)),
        PartPose.offset(0.0F, 24.0F, 0.0F));

    return LayerDefinition.create(meshdefinition, 32, 32);
  }

  @Override
  public void setupAnim(LivingEntityRenderState state) {
    super.setupAnim(state);
  }
}
