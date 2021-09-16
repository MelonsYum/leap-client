/*     */ package net.minecraft.client.renderer.entity;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelHorse;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.LayeredTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class RenderHorse extends RenderLiving {
/*  16 */   private static final Map field_110852_a = Maps.newHashMap();
/*  17 */   private static final ResourceLocation whiteHorseTextures = new ResourceLocation("textures/entity/horse/horse_white.png");
/*  18 */   private static final ResourceLocation muleTextures = new ResourceLocation("textures/entity/horse/mule.png");
/*  19 */   private static final ResourceLocation donkeyTextures = new ResourceLocation("textures/entity/horse/donkey.png");
/*  20 */   private static final ResourceLocation zombieHorseTextures = new ResourceLocation("textures/entity/horse/horse_zombie.png");
/*  21 */   private static final ResourceLocation skeletonHorseTextures = new ResourceLocation("textures/entity/horse/horse_skeleton.png");
/*     */   
/*     */   private static final String __OBFID = "CL_00001000";
/*     */   
/*     */   public RenderHorse(RenderManager p_i46170_1_, ModelHorse p_i46170_2_, float p_i46170_3_) {
/*  26 */     super(p_i46170_1_, (ModelBase)p_i46170_2_, p_i46170_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180580_a(EntityHorse p_180580_1_, float p_180580_2_) {
/*  31 */     float var3 = 1.0F;
/*  32 */     int var4 = p_180580_1_.getHorseType();
/*     */     
/*  34 */     if (var4 == 1) {
/*     */       
/*  36 */       var3 *= 0.87F;
/*     */     }
/*  38 */     else if (var4 == 2) {
/*     */       
/*  40 */       var3 *= 0.92F;
/*     */     } 
/*     */     
/*  43 */     GlStateManager.scale(var3, var3, var3);
/*  44 */     super.preRenderCallback((EntityLivingBase)p_180580_1_, p_180580_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_180581_a(EntityHorse p_180581_1_) {
/*  49 */     if (!p_180581_1_.func_110239_cn()) {
/*     */       
/*  51 */       switch (p_180581_1_.getHorseType()) {
/*     */ 
/*     */         
/*     */         default:
/*  55 */           return whiteHorseTextures;
/*     */         
/*     */         case 1:
/*  58 */           return donkeyTextures;
/*     */         
/*     */         case 2:
/*  61 */           return muleTextures;
/*     */         
/*     */         case 3:
/*  64 */           return zombieHorseTextures;
/*     */         case 4:
/*     */           break;
/*  67 */       }  return skeletonHorseTextures;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  72 */     return func_110848_b(p_180581_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ResourceLocation func_110848_b(EntityHorse p_110848_1_) {
/*  78 */     String var2 = p_110848_1_.getHorseTexture();
/*     */     
/*  80 */     if (!p_110848_1_.func_175507_cI())
/*     */     {
/*  82 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  86 */     ResourceLocation var3 = (ResourceLocation)field_110852_a.get(var2);
/*     */     
/*  88 */     if (var3 == null) {
/*     */       
/*  90 */       var3 = new ResourceLocation(var2);
/*  91 */       Minecraft.getMinecraft().getTextureManager().loadTexture(var3, (ITextureObject)new LayeredTexture(p_110848_1_.getVariantTexturePaths()));
/*  92 */       field_110852_a.put(var2, var3);
/*     */     } 
/*     */     
/*  95 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 105 */     func_180580_a((EntityHorse)p_77041_1_, p_77041_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 113 */     return func_180581_a((EntityHorse)p_110775_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */