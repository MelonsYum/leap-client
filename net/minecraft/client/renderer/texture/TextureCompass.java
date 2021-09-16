/*     */ package net.minecraft.client.renderer.texture;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import optifine.Config;
/*     */ import shadersmod.client.ShadersTex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextureCompass
/*     */   extends TextureAtlasSprite
/*     */ {
/*     */   public double currentAngle;
/*     */   public double angleDelta;
/*     */   public static String field_176608_l;
/*     */   private static final String __OBFID = "CL_00001071";
/*     */   
/*     */   public TextureCompass(String p_i1286_1_) {
/*  22 */     super(p_i1286_1_);
/*  23 */     field_176608_l = p_i1286_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimation() {
/*  28 */     Minecraft var1 = Minecraft.getMinecraft();
/*     */     
/*  30 */     if (var1.theWorld != null && var1.thePlayer != null) {
/*     */       
/*  32 */       updateCompass((World)var1.theWorld, var1.thePlayer.posX, var1.thePlayer.posZ, var1.thePlayer.rotationYaw, false, false);
/*     */     }
/*     */     else {
/*     */       
/*  36 */       updateCompass((World)null, 0.0D, 0.0D, 0.0D, true, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateCompass(World worldIn, double p_94241_2_, double p_94241_4_, double p_94241_6_, boolean p_94241_8_, boolean p_94241_9_) {
/*  45 */     if (!this.framesTextureData.isEmpty()) {
/*     */       
/*  47 */       double var10 = 0.0D;
/*     */       
/*  49 */       if (worldIn != null && !p_94241_8_) {
/*     */         
/*  51 */         BlockPos var18 = worldIn.getSpawnPoint();
/*  52 */         double var13 = var18.getX() - p_94241_2_;
/*  53 */         double var15 = var18.getZ() - p_94241_4_;
/*  54 */         p_94241_6_ %= 360.0D;
/*  55 */         var10 = -((p_94241_6_ - 90.0D) * Math.PI / 180.0D - Math.atan2(var15, var13));
/*     */         
/*  57 */         if (!worldIn.provider.isSurfaceWorld())
/*     */         {
/*  59 */           var10 = Math.random() * Math.PI * 2.0D;
/*     */         }
/*     */       } 
/*     */       
/*  63 */       if (p_94241_9_) {
/*     */         
/*  65 */         this.currentAngle = var10;
/*     */       } else {
/*     */         double var181;
/*     */ 
/*     */ 
/*     */         
/*  71 */         for (var181 = var10 - this.currentAngle; var181 < -3.141592653589793D; var181 += 6.283185307179586D);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  76 */         while (var181 >= Math.PI)
/*     */         {
/*  78 */           var181 -= 6.283185307179586D;
/*     */         }
/*     */         
/*  81 */         var181 = MathHelper.clamp_double(var181, -1.0D, 1.0D);
/*  82 */         this.angleDelta += var181 * 0.1D;
/*  83 */         this.angleDelta *= 0.8D;
/*  84 */         this.currentAngle += this.angleDelta;
/*     */       } 
/*     */       
/*     */       int var182;
/*     */       
/*  89 */       for (var182 = (int)((this.currentAngle / 6.283185307179586D + 1.0D) * this.framesTextureData.size()) % this.framesTextureData.size(); var182 < 0; var182 = (var182 + this.framesTextureData.size()) % this.framesTextureData.size());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       if (var182 != this.frameCounter) {
/*     */         
/*  96 */         this.frameCounter = var182;
/*     */         
/*  98 */         if (Config.isShaders()) {
/*     */           
/* 100 */           ShadersTex.uploadTexSub(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
/*     */         }
/*     */         else {
/*     */           
/* 104 */           TextureUtil.uploadTextureMipmap(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\TextureCompass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */