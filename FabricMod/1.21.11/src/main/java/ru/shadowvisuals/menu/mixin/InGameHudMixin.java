package ru.shadowvisuals.menu.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Draws a small SV marker next to the local player's nickname on this client only.
 * It does not modify the Minecraft username or send anything to a server.
 */
@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void shadowvisuals$drawLocalBadge(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null || client.options.hudHidden) {
            return;
        }

        Text badge = Text.literal("SV").setStyle(
                Style.EMPTY.withBold(true).withColor(TextColor.fromRgb(0xB979FF))
        );
        Text nickname = Text.literal(client.player.getGameProfile().getName()).setStyle(
                Style.EMPTY.withColor(TextColor.fromRgb(0xF2EAFB))
        );

        int x = 8;
        int y = 8;
        context.drawTextWithShadow(client.textRenderer, badge, x, y, 0xFFFFFFFF);
        int badgeWidth = client.textRenderer.getWidth(badge);
        context.drawTextWithShadow(client.textRenderer, nickname, x + badgeWidth + 5, y, 0xFFFFFFFF);
    }
}
