package com.example.examplemod.menus;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.transfer.StacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ResourceHandlerSlot;

public class ConverterMenu extends AbstractContainerMenu {
  private final StacksResourceHandler<ItemStack, ItemResource> dataInventory;

  public ConverterMenu(int containerId, Inventory playerInventory) {
    this(containerId, playerInventory, new ItemStacksResourceHandler(2));
  }

  @Override
  public boolean stillValid(Player player) {
    return true;
  }

  @Override
  public ItemStack quickMoveStack(final Player player, final int quickMovedSlotIndex) {
    ItemStack quickMovedStack = ItemStack.EMPTY;
    Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

    if (quickMovedSlot != null && quickMovedSlot.hasItem()) {
      ItemStack rawStack = quickMovedSlot.getItem();
      quickMovedStack = rawStack.copy();

      if (quickMovedSlotIndex == 0) {
        if (!this.moveItemStackTo(rawStack, 5, 41, true)) {
          return ItemStack.EMPTY;
        }

        quickMovedSlot.onQuickCraft(rawStack, quickMovedStack);
      } else if (quickMovedSlotIndex >= 5 && quickMovedSlotIndex < 41) {
        if (!this.moveItemStackTo(rawStack, 1, 5, false)) {
          if (quickMovedSlotIndex < 32) {
            if (!this.moveItemStackTo(rawStack, 32, 41, false)) {
              return ItemStack.EMPTY;
            }
          } else if (!this.moveItemStackTo(rawStack, 5, 32, false)) {
            return ItemStack.EMPTY;
          }
        }
      } else if (!this.moveItemStackTo(rawStack, 5, 41, false)) {
        return ItemStack.EMPTY;
      }

      if (rawStack.isEmpty()) {
        quickMovedSlot.setByPlayer(ItemStack.EMPTY);
      } else {
        quickMovedSlot.setChanged();
      }

      quickMovedSlot.onTake(player, rawStack);
    }

    return quickMovedStack;
  }

  public void updateResult() {
    ItemStack input = this.slots.get(0).getItem();
    ItemStack result = getResult(input);
    this.setHandlerStack(1, result);
  }

  public void setHandlerStack(int index, ItemStack stack) {
    this.dataInventory.set(index, stack.isEmpty() ? ItemResource.EMPTY : ItemResource.of(stack), stack.getCount());
  }

  private ItemStack getResult(ItemStack input) {
    if (input.is(Items.IRON_INGOT)) {
      return new ItemStack(Items.GOLD_INGOT);
    }
    return ItemStack.EMPTY;
  }

  private void consumeInput() {
    ItemStack input = this.slots.get(0).getItem();
    if (input.isEmpty()) {
      return;
    }
    input.shrink(1);
    this.setHandlerStack(0, input);
    this.updateResult();
  }

  public ConverterMenu(int containerId, Inventory playerInventory,
      StacksResourceHandler<ItemStack, ItemResource> dataInventory) {
    super(ModMenus.CONVERTER.get(), containerId);
    this.dataInventory = dataInventory;
    this.addSlot(new InputSlot(dataInventory));
    this.addSlot(new ResultSlot(dataInventory));
    this.addStandardInventorySlots(
        playerInventory,
        8,
        84);
  }

  private class InputSlot extends ResourceHandlerSlot {
    public InputSlot(StacksResourceHandler<ItemStack, ItemResource> handler) {
      super(handler, handler::set, 0, 44, 20);
    }

    @Override
    protected void setStackCopy(ItemStack stack) {
      super.setStackCopy(stack);
      updateResult();
    }
  }

  private class ResultSlot extends ResourceHandlerSlot {
    public ResultSlot(StacksResourceHandler<ItemStack, ItemResource> handler) {
      super(handler, handler::set, 1, 96, 20);
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
      return false;
    }

    @Override
    public void onTake(Player player, ItemStack itemStack) {
      super.onTake(player, itemStack);
      if (!player.level().isClientSide()) {
        consumeInput();
      }
    }
  }
}
