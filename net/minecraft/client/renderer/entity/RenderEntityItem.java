/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import java.util.Random;
/*     */ import leap.Client;
/*     */ import leap.util.ItemUtil;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class RenderEntityItem
/*     */   extends Render
/*     */ {
/*     */   public static RenderItem field_177080_a;
/*  20 */   private Random field_177079_e = new Random();
/*     */   
/*     */   private static final String __OBFID = "CL_00002442";
/*     */   
/*     */   public RenderEntityItem(RenderManager p_i46167_1_, RenderItem p_i46167_2_) {
/*  25 */     super(p_i46167_1_);
/*  26 */     field_177080_a = p_i46167_2_;
/*  27 */     this.shadowSize = 0.15F;
/*  28 */     this.shadowOpaque = 0.75F;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_177077_a(EntityItem p_177077_1_, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
/*  33 */     ItemStack var10 = p_177077_1_.getEntityItem();
/*  34 */     Item var11 = var10.getItem();
/*     */     
/*  36 */     if (var11 == null)
/*     */     {
/*  38 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  42 */     boolean var12 = p_177077_9_.isAmbientOcclusionEnabled();
/*  43 */     int var13 = func_177078_a(var10);
/*  44 */     float var14 = 0.25F;
/*  45 */     float var15 = MathHelper.sin((p_177077_1_.func_174872_o() + p_177077_8_) / 10.0F + p_177077_1_.hoverStart) * 0.1F + 0.1F;
/*  46 */     GlStateManager.translate((float)p_177077_2_, (float)p_177077_4_ + var15 + 0.25F, (float)p_177077_6_);
/*     */ 
/*     */     
/*  49 */     if (var12 || (this.renderManager.options != null && this.renderManager.options.fancyGraphics)) {
/*     */       
/*  51 */       float var16 = ((p_177077_1_.func_174872_o() + p_177077_8_) / 20.0F + p_177077_1_.hoverStart) * 57.295776F;
/*  52 */       GlStateManager.rotate(var16, 0.0F, 1.0F, 0.0F);
/*     */     } 
/*     */     
/*  55 */     if (!var12) {
/*     */       
/*  57 */       float var16 = -0.0F * (var13 - 1) * 0.5F;
/*  58 */       float var17 = -0.0F * (var13 - 1) * 0.5F;
/*  59 */       float var18 = -0.046875F * (var13 - 1) * 0.5F;
/*  60 */       GlStateManager.translate(var16, var17, var18);
/*     */     } 
/*     */     
/*  63 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  64 */     return var13;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int func_177078_a(ItemStack p_177078_1_) {
/*  70 */     byte var2 = 1;
/*     */     
/*  72 */     if (p_177078_1_.stackSize > 48) {
/*     */       
/*  74 */       var2 = 5;
/*     */     }
/*  76 */     else if (p_177078_1_.stackSize > 32) {
/*     */       
/*  78 */       var2 = 4;
/*     */     }
/*  80 */     else if (p_177078_1_.stackSize > 16) {
/*     */       
/*  82 */       var2 = 3;
/*     */     }
/*  84 */     else if (p_177078_1_.stackSize > 1) {
/*     */       
/*  86 */       var2 = 2;
/*     */     } 
/*     */     
/*  89 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177075_a(EntityItem p_177075_1_, double p_177075_2_, double p_177075_4_, double p_177075_6_, float p_177075_8_, float p_177075_9_) {
/*  94 */     ItemStack var10 = p_177075_1_.getEntityItem();
/*  95 */     if (Client.getModule("ItemPhysics").isEnabled()) {
/*  96 */       ItemUtil.doRender((Entity)p_177075_1_, p_177075_2_, p_177075_4_, p_177075_6_, p_177075_8_, p_177075_9_);
/*     */     }
/*     */     
/*  99 */     if (Client.getModule("ItemPhysics").isEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 103 */     this.field_177079_e.setSeed(187L);
/* 104 */     boolean var11 = false;
/*     */     
/* 106 */     if (bindEntityTexture((Entity)p_177075_1_)) {
/*     */       
/* 108 */       this.renderManager.renderEngine.getTexture(func_177076_a(p_177075_1_)).func_174936_b(false, false);
/* 109 */       var11 = true;
/*     */     } 
/*     */     
/* 112 */     GlStateManager.enableRescaleNormal();
/* 113 */     GlStateManager.alphaFunc(516, 0.1F);
/* 114 */     GlStateManager.enableBlend();
/* 115 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 116 */     GlStateManager.pushMatrix();
/* 117 */     IBakedModel var12 = getField_177080_a().getItemModelMesher().getItemModel(var10);
/* 118 */     int var13 = func_177077_a(p_177075_1_, p_177075_2_, p_177075_4_, p_177075_6_, p_177075_9_, var12);
/*     */     
/* 120 */     for (int var14 = 0; var14 < var13; var14++) {
/*     */       
/* 122 */       if (var12.isAmbientOcclusionEnabled()) {
/*     */         
/* 124 */         GlStateManager.pushMatrix();
/*     */         
/* 126 */         if (var14 > 0) {
/*     */           
/* 128 */           float var15 = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
/* 129 */           float var16 = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
/* 130 */           float var17 = (this.field_177079_e.nextFloat() * 2.0F - 1.0F) * 0.15F;
/* 131 */           GlStateManager.translate(var15, var16, var17);
/*     */         } 
/*     */         
/* 134 */         GlStateManager.scale(0.5F, 0.5F, 0.5F);
/*     */         
/* 136 */         getField_177080_a().func_180454_a(var10, var12);
/* 137 */         GlStateManager.popMatrix();
/*     */       }
/*     */       else {
/*     */         
/* 141 */         getField_177080_a().func_180454_a(var10, var12);
/* 142 */         GlStateManager.translate(0.0F, 0.0F, 0.046875F);
/*     */       } 
/*     */     } 
/*     */     
/* 146 */     GlStateManager.popMatrix();
/* 147 */     GlStateManager.disableRescaleNormal();
/* 148 */     GlStateManager.disableBlend();
/* 149 */     bindEntityTexture((Entity)p_177075_1_);
/*     */     
/* 151 */     if (var11)
/*     */     {
/* 153 */       this.renderManager.renderEngine.getTexture(func_177076_a(p_177075_1_)).func_174935_a();
/*     */     }
/*     */     
/* 156 */     super.doRender((Entity)p_177075_1_, p_177075_2_, p_177075_4_, p_177075_6_, p_177075_8_, p_177075_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_177076_a(EntityItem p_177076_1_) {
/* 161 */     return TextureMap.locationBlocksTexture;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 169 */     return func_177076_a((EntityItem)p_110775_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 180 */     func_177075_a((EntityItem)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */   
/*     */   public RenderItem getField_177080_a() {
/* 184 */     return field_177080_a;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderEntityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */