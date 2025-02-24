package galena.oreganized.client.render.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import galena.oreganized.Oreganized;
import galena.oreganized.index.OEffects;
import galena.oreganized.index.OFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;

public class OGui extends ForgeGui {

    protected static final ResourceLocation STUNNING_LOCATION = Oreganized.modLoc("textures/misc/stunning_outline.png");
    protected static final ResourceLocation STUNNING_VIGNETTE_LOCATION = Oreganized.modLoc( "textures/misc/stunning_overlay.png");

    public OGui(Minecraft mc) {
        super(mc);
    }

    @Override
    public void render(PoseStack pPoseStack, float pPartialTick) {
        this.screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        this.screenHeight = this.minecraft.getWindow().getGuiScaledHeight();
        RenderSystem.enableBlend();

        if (this.minecraft.player.hasEffect(OEffects.STUNNING.get())) {
            this.renderTextureOverlay(STUNNING_VIGNETTE_LOCATION, 1);
            this.renderTextureOverlay(STUNNING_LOCATION, 0.8F);
        }

        if (this.minecraft.player.isEyeInFluidType(OFluids.MOLTEN_LEAD_TYPE.get()))
            this.renderTextureOverlay(STUNNING_VIGNETTE_LOCATION, 1);
    }
}
