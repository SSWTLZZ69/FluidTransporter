package com.sswtlzz.fluidtransporter.item;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FluidTransporterItem extends Item {

    private static final int MAX_CAPACITY = 1000; // 物品的最大流体容量
    protected FluidTank fluidTank;

    public FluidTransporterItem(Properties properties) {
        super(properties.stacksTo(1));
        fluidTank = new FluidTank(MAX_CAPACITY); // 创建一个最大容量为 1000 的流体槽
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        ItemStack itemStack = pContext.getItemInHand();
        Player player = pContext.getPlayer();
        BlockPos blockPos = pContext.getClickedPos();
        BlockState state = pContext.getLevel().getBlockState(blockPos);
        CompoundTag tag = itemStack.getOrCreateTag();

        // 读取 NBT 数据
        fluidTank.readFromNBT(tag);

        if (state.hasBlockEntity()) {
            BlockEntity blockEntity = pContext.getLevel().getBlockEntity(blockPos);
            IFluidHandler fluids = blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).orElse(null);
            if (fluids == null) {
                return super.useOn(pContext); // 如果目标容器没有流体处理能力，继续执行默认行为
            }

            // 遍历目标容器中的流体槽
            for (int i = 0; i < fluids.getTanks(); i++) {
                FluidStack targetFluid = fluids.getFluidInTank(i);
                int targetCapacity = fluids.getTankCapacity(i);
                // 如果移液器内没有流体
                if (fluidTank.isEmpty()) {
                    //容器中有流体
                    if (!targetFluid.isEmpty()) {
                            int minFluid = Math.min(targetFluid.getAmount(), MAX_CAPACITY);
                            FluidStack drainFluid = fluids.drain(new FluidStack(targetFluid, minFluid), IFluidHandler.FluidAction.EXECUTE);
                            fluidTank.fill(drainFluid, IFluidHandler.FluidAction.EXECUTE);
                            player.swing(pContext.getHand());
                            break;
                    }
                }
                //移液器内有流体
                else {
                    //容器内有流体
                    if (!targetFluid.isEmpty()) {
                        //是同种流体，将移液器内的流体送入容器
                        if (targetFluid.isFluidEqual(fluidTank.getFluid())) {
                            //不处理流体装不下的情况
                            if (targetCapacity >= fluidTank.getFluidAmount() + targetFluid.getAmount()) {
                                int res = fluids.fill(fluidTank.getFluid(), IFluidHandler.FluidAction.EXECUTE);
                                fluidTank.drain(res, IFluidHandler.FluidAction.EXECUTE);
                                player.swing(pContext.getHand());
                                break;
                            }
                        }
                    }
                    else if (targetCapacity >= fluidTank.getFluidAmount()) {
                        int res = fluids.fill(fluidTank.getFluid(), IFluidHandler.FluidAction.EXECUTE);
                        fluidTank.drain(res, IFluidHandler.FluidAction.EXECUTE);
                        player.swing(pContext.getHand());
                        break;
                    }
                }

            }
        }

        // 保存物品的 NBT 数据
        fluidTank.writeToNBT(tag);

        return super.useOn(pContext); // 完成操作后返回
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {


        pTooltipComponents.add(Component.translatable("fluid_transporter.tooltip", Component.translatable(fluidTank.getFluid().getTranslationKey()), fluidTank.getFluidAmount()));
    }




}


