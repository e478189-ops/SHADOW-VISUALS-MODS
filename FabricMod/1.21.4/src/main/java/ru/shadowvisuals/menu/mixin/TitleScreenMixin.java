package ru.shadowvisuals.menu.mixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.screen.TitleScreen;
@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin {
 private static final Identifier BACKGROUND = Identifier.of("shadowvisuals", "textures/gui/menu_background.png");
 @Inject(method="renderPanoramaBackground", at=@At("HEAD"), cancellable=true)
 private void shadowvisuals$drawStaticBackground(DrawContext context,float delta,CallbackInfo ci) {
  ci.cancel();
  int w=MinecraftClient.getInstance().getWindow().getScaledWidth();
  int h=MinecraftClient.getInstance().getWindow().getScaledHeight();
  context.drawTexture(RenderLayer::getGuiTextured,BACKGROUND,0,0,0.0F,0.0F,w,h,960,540);
 }
}