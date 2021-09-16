/*    */ package net.minecraft.client.renderer.texture;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import optifine.Config;
/*    */ import shadersmod.client.ShadersTex;
/*    */ 
/*    */ public class TextureClock
/*    */   extends TextureAtlasSprite
/*    */ {
/*    */   private double field_94239_h;
/*    */   private double field_94240_i;
/*    */   private static final String __OBFID = "CL_00001070";
/*    */   
/*    */   public TextureClock(String p_i1285_1_) {
/* 16 */     super(p_i1285_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateAnimation() {
/* 21 */     if (!this.framesTextureData.isEmpty()) {
/*    */       
/* 23 */       Minecraft var1 = Minecraft.getMinecraft();
/* 24 */       double var2 = 0.0D;
/*    */       
/* 26 */       if (var1.theWorld != null && var1.thePlayer != null) {
/*    */         
/* 28 */         float var7 = var1.theWorld.getCelestialAngle(1.0F);
/* 29 */         var2 = var7;
/*    */         
/* 31 */         if (!var1.theWorld.provider.isSurfaceWorld())
/*    */         {
/* 33 */           var2 = Math.random();
/*    */         }
/*    */       } 
/*    */       
/*    */       double var71;
/*    */       
/* 39 */       for (var71 = var2 - this.field_94239_h; var71 < -0.5D; var71++);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 44 */       while (var71 >= 0.5D)
/*    */       {
/* 46 */         var71--;
/*    */       }
/*    */       
/* 49 */       var71 = MathHelper.clamp_double(var71, -1.0D, 1.0D);
/* 50 */       this.field_94240_i += var71 * 0.1D;
/* 51 */       this.field_94240_i *= 0.8D;
/* 52 */       this.field_94239_h += this.field_94240_i;
/*    */       
/*    */       int var6;
/* 55 */       for (var6 = (int)((this.field_94239_h + 1.0D) * this.framesTextureData.size()) % this.framesTextureData.size(); var6 < 0; var6 = (var6 + this.framesTextureData.size()) % this.framesTextureData.size());
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 60 */       if (var6 != this.frameCounter) {
/*    */         
/* 62 */         this.frameCounter = var6;
/*    */         
/* 64 */         if (Config.isShaders()) {
/*    */           
/* 66 */           ShadersTex.uploadTexSub(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
/*    */         }
/*    */         else {
/*    */           
/* 70 */           TextureUtil.uploadTextureMipmap(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\TextureClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */