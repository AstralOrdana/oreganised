package me.gleep.oreganized.armors;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class STABase extends ArmorItem {
    //Maximum durability of the tint
    public static final int MAX_TINT_DURABILITY = 50;
    private final boolean immuneToFire;
    //Used for tinted durability bar
    private boolean shouldDisplayTint;


    public STABase(ArmorMaterial materialIn, EquipmentSlot slot) {
        super(materialIn, slot, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1));
        this.immuneToFire = materialIn == ArmorMaterials.NETHERITE;
    }

    @Override
    public boolean isFireResistant() {
        return this.immuneToFire;
    }

    /**
     * Called by Piglins to check if a given item prevents hostility on sight. If this is true the Piglins will be neutral to the entity wearing this item, and will not
     * attack on sight. Note: This does not prevent Piglins from becoming hostile due to other actions, nor does it make Piglins that are already hostile stop being so.
     *
     * @param stack
     * @param wearer The entity wearing this ItemStack
     * @return True if piglins are neutral to players wearing this item in an armor slot
     */
    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return stack.getItemEnchantability() == ArmorMaterials.GOLD.getEnchantmentValue();
    }

    @Override
    public boolean hurtEnemy(ItemStack p_41395_, LivingEntity p_41396_, LivingEntity p_41397_) {
        this.decreaseDurabilty(p_41395_, p_41397_);
        return super.hurtEnemy(p_41395_, p_41396_, p_41397_);
    }

    /**
     * Decrease the Tinted durability by one also handles break event
     *
     * @param stack         stack that is the durability be decreased
     * @param entityLiving  entity that is in interaction
     */
    public void decreaseDurabilty(ItemStack stack, LivingEntity entityLiving) {
        if (!(entityLiving instanceof Player)) return;
        Player pl = (Player) entityLiving;
        if (pl.isCreative()) return;
        int durability = stack.getOrCreateTag().getInt("TintedDamage");
        if (durability == 0) {
            durability = MAX_TINT_DURABILITY;
            stack.getOrCreateTag().putInt("TintedDamage", durability);
        }

        if (durability - 1 < 1) {
            if (!stack.isEmpty()) {
                if (!pl.isSilent()) {
                    pl.level.playSound(pl, pl.getX(), pl.getY(), pl.getZ(), SoundEvents.ITEM_BREAK, pl.getSoundSource(), 0.8F, 0.8F + pl.level.random.nextFloat() * 0.4F);
                }

                for (int i = 0; i < 5; ++i) {
                    Vec3 vector = new Vec3(((double)pl.getRandom().nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
                    vector = vector.xRot(-pl.getXRot() * ((float)Math.PI / 180F));
                    vector = vector.yRot(-pl.getYRot() * ((float)Math.PI / 180F));
                    double d0 = (double)(-pl.getRandom().nextFloat()) * 0.6D - 0.3D;
                    Vec3 vector1 = new Vec3(((double)pl.getRandom().nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
                    vector1 = vector1.xRot(-pl.getXRot() * ((float)Math.PI / 180F));
                    vector1 = vector1.yRot(-pl.getYRot() * ((float)Math.PI / 180F));
                    vector1.add(new Vec3(pl.getX(), pl.getEyeY(), pl.getZ()));
                    if (pl.level instanceof ServerLevel) //Forge: Fix MC-2518 spawnParticle is nooped on server, need to use server specific variant
                        ((ServerLevel)pl.level).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, stack), vector1.x, vector1.y, vector1.z, 1, vector.x, vector.y + 0.05D, vector.z, 0.0D);
                    else
                        pl.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, stack), vector1.x, vector1.y, vector1.z, vector.x, vector.y + 0.05D, vector.z);
                }

                ItemStack newArmorPiece = ItemStack.EMPTY;
                if (this.getMaterial().equals(ArmorMaterials.DIAMOND)) {
                    switch (this.getSlot()) {
                        case CHEST -> newArmorPiece = new ItemStack(Items.DIAMOND_CHESTPLATE, 1);
                        case FEET -> newArmorPiece = new ItemStack(Items.DIAMOND_BOOTS, 1);
                        case HEAD -> newArmorPiece = new ItemStack(Items.DIAMOND_HELMET, 1);
                        case LEGS -> newArmorPiece = new ItemStack(Items.DIAMOND_LEGGINGS, 1);
                    }
                } else if (this.getMaterial().equals(ArmorMaterials.GOLD)) {
                    switch (this.getSlot()) {
                        case CHEST -> newArmorPiece = new ItemStack(Items.GOLDEN_CHESTPLATE, 1);
                        case FEET -> newArmorPiece = new ItemStack(Items.GOLDEN_BOOTS, 1);
                        case HEAD -> newArmorPiece = new ItemStack(Items.GOLDEN_HELMET, 1);
                        case LEGS -> newArmorPiece = new ItemStack(Items.GOLDEN_LEGGINGS, 1);
                    }
                } else if (this.getMaterial().equals(ArmorMaterials.NETHERITE)) {
                    switch (this.getSlot()) {
                        case CHEST -> newArmorPiece = new ItemStack(Items.NETHERITE_CHESTPLATE, 1);
                        case FEET -> newArmorPiece = new ItemStack(Items.NETHERITE_BOOTS, 1);
                        case HEAD -> newArmorPiece = new ItemStack(Items.NETHERITE_HELMET, 1);
                        case LEGS -> newArmorPiece = new ItemStack(Items.NETHERITE_LEGGINGS, 1);
                    }
                }

                newArmorPiece.setTag(stack.getTag());
                newArmorPiece.getOrCreateTag().remove("TintedDamage");
                if (pl.getInventory().armor.contains(stack)) {
                    pl.getInventory().armor.set(this.getSlot().getIndex(), newArmorPiece);
                } else {
                    pl.getInventory().setItem(pl.getInventory().findSlotMatchingItem(stack), newArmorPiece);
                }
            }
        } else {
            durability--;
            stack.getOrCreateTag().putInt("TintedDamage", durability);
        }
    }

    /**
     *
     * @return 0.0 for 100% (no damage / full bar), 1.0 for 0% (fully damaged / empty bar)
     */
    @Override
    public int getBarWidth(ItemStack stack) {
        if (this.shouldDisplayTint) {
            return this.getSilverDurabilityForDisplay(stack);
        }
        return stack.getDamageValue() / stack.getMaxDamage();
    }

    public int getSilverDurabilityForDisplay(ItemStack stack) {
        return stack.getOrCreateTag().getInt("TintedDamage") < 1 ? 0 : (MAX_TINT_DURABILITY - stack.getOrCreateTag().getInt("TintedDamage")) / MAX_TINT_DURABILITY;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (this.shouldDisplayTint) {
            return Mth.hsvToRgb(200F / 360F, Math.max(0.0F, this.getBarWidth(stack)), 0.94F);
        }
        return Mth.hsvToRgb(Math.max(0.0F, (float) (1.0F - this.getBarWidth(stack))) / 3.0F, 1.0F, 1.0F);
    }
}
