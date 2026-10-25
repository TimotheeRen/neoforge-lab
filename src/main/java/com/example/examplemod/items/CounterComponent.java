package com.example.examplemod.items;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record CounterComponent(int clicks) {
  public static final Codec<CounterComponent> RECORD_CODEC = RecordCodecBuilder.create(instance -> instance.group(
      Codec.INT.fieldOf("clicks").forGetter(CounterComponent::clicks)).apply(instance, CounterComponent::new));

  public static final StreamCodec<ByteBuf, CounterComponent> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT,
      CounterComponent::clicks, CounterComponent::new);
}
