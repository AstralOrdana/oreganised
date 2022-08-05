package galena.oreganized.content.index;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Map;

import static galena.oreganized.content.block.MoltenLeadCauldronBlock.*;

public interface OCauldronInteractions extends CauldronInteraction {

    /*CauldronInteraction FILL_MOLTEN_LEAD = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.emptyBucket(world, pos, player, hand, stack, OBlocks.MOLTEN_LEAD_CAULDRON.get().defaultBlockState().setValue(AGE, 3), SoundEvents.BUCKET_EMPTY_LAVA);

    CauldronInteraction EMPTY_MOLTEN_LEAD = (state, world, pos, player, hand, stack) ->
            CauldronInteraction.fillBucket(state, world, pos, player, hand, stack, new ItemStack(OItems.MOLTEN_LEAD_BUCKET.get()), blockState -> state.getValue(AGE).equals(3), SoundEvents.BUCKET_FILL_LAVA);

    CauldronInteraction FILL_LEAD_BLOCK = (state, world, pos, player, hand, stack) ->
            placeBlock(world, pos, player, hand, stack, OBlocks.MOLTEN_LEAD_CAULDRON.get().defaultBlockState().setValue(AGE, 0), SoundEvents.METAL_PLACE);

    Map<Item, CauldronInteraction> LEAD = CauldronInteraction.newInteractionMap();

    static void register() {
        EMPTY.put(OItems.MOLTEN_LEAD_BUCKET.get(), FILL_MOLTEN_LEAD);
        WATER.put(OItems.MOLTEN_LEAD_BUCKET.get(), FILL_MOLTEN_LEAD);
        LAVA.put(OItems.MOLTEN_LEAD_BUCKET.get(), FILL_MOLTEN_LEAD);
        POWDER_SNOW.put(OItems.MOLTEN_LEAD_BUCKET.get(), FILL_MOLTEN_LEAD);
        LEAD.put(OItems.MOLTEN_LEAD_BUCKET.get(), FILL_MOLTEN_LEAD);

        EMPTY.put(OBlocks.LEAD_BLOCK.get().asItem(), FILL_LEAD_BLOCK);
        WATER.put(OBlocks.LEAD_BLOCK.get().asItem(), FILL_LEAD_BLOCK);
        LAVA.put(OBlocks.LEAD_BLOCK.get().asItem(), FILL_LEAD_BLOCK);
        POWDER_SNOW.put(OBlocks.LEAD_BLOCK.get().asItem(), FILL_LEAD_BLOCK);

        LEAD.put(Items.BUCKET, EMPTY_MOLTEN_LEAD);

        CauldronInteraction.addDefaultInteractions(LEAD);
    }

    static InteractionResult placeBlock(Level world, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, BlockState state, SoundEvent sound) {
        if (!world.isClientSide) {
            Item item = stack.getItem();
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(item));
            world.setBlockAndUpdate(pos, state);
            world.playSound((Player)null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
            world.gameEvent((Entity)null, GameEvent.BLOCK_PLACE, pos);
        }

        return InteractionResult.sidedSuccess(world.isClientSide);
    }*/
}
