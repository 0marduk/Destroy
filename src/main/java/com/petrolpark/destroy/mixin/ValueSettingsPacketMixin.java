package com.petrolpark.destroy.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.petrolpark.destroy.block.entity.behaviour.SmartValueSettingsBehaviour;
import com.petrolpark.destroy.mixin.accessor.ValueSettingsPacketAccessor;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsPacket;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsBehaviour.ValueSettings;

import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;

@Mixin(ValueSettingsPacket.class)
public class ValueSettingsPacketMixin {
    
    /**
     * Pass Block Entities with {@link com.petrolpark.destroy.block.entity.behaviour.SmartValueSettingsBehaviour Smart Value Settings Behaviours} additional information.
     * Copied and minorly adjusted from the {@link com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsPacket Create source code}.
     */
    @Overwrite(remap = false)
	protected void applySettings(ServerPlayer player, SmartBlockEntity be) {
		InteractionHand hand = ((ValueSettingsPacketAccessor)this).getInteractHand();
		Direction side = ((ValueSettingsPacketAccessor)this).getSide();
		for (BlockEntityBehaviour behaviour : be.getAllBehaviours()) {
			if (!(behaviour instanceof ValueSettingsBehaviour valueSettingsBehaviour))
				continue;
			if (!valueSettingsBehaviour.acceptsValueSettings())
				continue;
			if (hand != null) {
				valueSettingsBehaviour.onShortInteract(player, hand, side);
			};
            if (valueSettingsBehaviour instanceof SmartValueSettingsBehaviour smartValueSettingsBehaviour) {
                smartValueSettingsBehaviour.acceptAccessInformation(hand, side);
            };
			valueSettingsBehaviour.setValueSettings(player, new ValueSettings(((ValueSettingsPacketAccessor)this).getRow(), ((ValueSettingsPacketAccessor)this).getValue()), ((ValueSettingsPacketAccessor)this).getCtrlDown());
			return;
		}
	}
};
