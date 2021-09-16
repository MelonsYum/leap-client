/*     */ package net.minecraft.client.resources.model;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.block.model.BreakingFour;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.block.model.ModelBlock;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ public class SimpleBakedModel
/*     */   implements IBakedModel
/*     */ {
/*     */   protected final List field_177563_a;
/*     */   protected final List field_177561_b;
/*     */   protected final boolean field_177562_c;
/*     */   protected final boolean ambientOcclusion;
/*     */   protected final TextureAtlasSprite texture;
/*     */   protected final ItemCameraTransforms field_177558_f;
/*     */   private static final String __OBFID = "CL_00002386";
/*     */   
/*     */   public SimpleBakedModel(List p_i46077_1_, List p_i46077_2_, boolean p_i46077_3_, boolean p_i46077_4_, TextureAtlasSprite p_i46077_5_, ItemCameraTransforms p_i46077_6_) {
/*  25 */     this.field_177563_a = p_i46077_1_;
/*  26 */     this.field_177561_b = p_i46077_2_;
/*  27 */     this.field_177562_c = p_i46077_3_;
/*  28 */     this.ambientOcclusion = p_i46077_4_;
/*  29 */     this.texture = p_i46077_5_;
/*  30 */     this.field_177558_f = p_i46077_6_;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177551_a(EnumFacing p_177551_1_) {
/*  35 */     return this.field_177561_b.get(p_177551_1_.ordinal());
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177550_a() {
/*  40 */     return this.field_177563_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGui3d() {
/*  45 */     return this.field_177562_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAmbientOcclusionEnabled() {
/*  50 */     return this.ambientOcclusion;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBuiltInRenderer() {
/*  55 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getTexture() {
/*  60 */     return this.texture;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemCameraTransforms getItemCameraTransforms() {
/*  65 */     return this.field_177558_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Builder
/*     */   {
/*     */     private final List field_177656_a;
/*     */     private final List field_177654_b;
/*     */     private final boolean field_177655_c;
/*     */     private TextureAtlasSprite field_177652_d;
/*     */     private boolean field_177653_e;
/*     */     private ItemCameraTransforms field_177651_f;
/*     */     private static final String __OBFID = "CL_00002385";
/*     */     
/*     */     public Builder(ModelBlock p_i46074_1_) {
/*  80 */       this(p_i46074_1_.func_178309_b(), p_i46074_1_.isAmbientOcclusionEnabled(), new ItemCameraTransforms(p_i46074_1_.getThirdPersonTransform(), p_i46074_1_.getFirstPersonTransform(), p_i46074_1_.getHeadTransform(), p_i46074_1_.getInGuiTransform()));
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder(IBakedModel p_i46075_1_, TextureAtlasSprite p_i46075_2_) {
/*  85 */       this(p_i46075_1_.isGui3d(), p_i46075_1_.isAmbientOcclusionEnabled(), p_i46075_1_.getItemCameraTransforms());
/*  86 */       this.field_177652_d = p_i46075_1_.getTexture();
/*  87 */       EnumFacing[] var3 = EnumFacing.values();
/*  88 */       int var4 = var3.length;
/*     */       
/*  90 */       for (int var5 = 0; var5 < var4; var5++) {
/*     */         
/*  92 */         EnumFacing var6 = var3[var5];
/*  93 */         func_177649_a(p_i46075_1_, p_i46075_2_, var6);
/*     */       } 
/*     */       
/*  96 */       func_177647_a(p_i46075_1_, p_i46075_2_);
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_177649_a(IBakedModel p_177649_1_, TextureAtlasSprite p_177649_2_, EnumFacing p_177649_3_) {
/* 101 */       Iterator<BakedQuad> var4 = p_177649_1_.func_177551_a(p_177649_3_).iterator();
/*     */       
/* 103 */       while (var4.hasNext()) {
/*     */         
/* 105 */         BakedQuad var5 = var4.next();
/* 106 */         func_177650_a(p_177649_3_, (BakedQuad)new BreakingFour(var5, p_177649_2_));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_177647_a(IBakedModel p_177647_1_, TextureAtlasSprite p_177647_2_) {
/* 112 */       Iterator<BakedQuad> var3 = p_177647_1_.func_177550_a().iterator();
/*     */       
/* 114 */       while (var3.hasNext()) {
/*     */         
/* 116 */         BakedQuad var4 = var3.next();
/* 117 */         func_177648_a((BakedQuad)new BreakingFour(var4, p_177647_2_));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private Builder(boolean p_i46076_1_, boolean p_i46076_2_, ItemCameraTransforms p_i46076_3_) {
/* 123 */       this.field_177656_a = Lists.newArrayList();
/* 124 */       this.field_177654_b = Lists.newArrayListWithCapacity(6);
/* 125 */       EnumFacing[] var4 = EnumFacing.values();
/* 126 */       int var5 = var4.length;
/*     */       
/* 128 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/* 130 */         EnumFacing var10000 = var4[var6];
/* 131 */         this.field_177654_b.add(Lists.newArrayList());
/*     */       } 
/*     */       
/* 134 */       this.field_177655_c = p_i46076_1_;
/* 135 */       this.field_177653_e = p_i46076_2_;
/* 136 */       this.field_177651_f = p_i46076_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder func_177650_a(EnumFacing p_177650_1_, BakedQuad p_177650_2_) {
/* 141 */       ((List<BakedQuad>)this.field_177654_b.get(p_177650_1_.ordinal())).add(p_177650_2_);
/* 142 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder func_177648_a(BakedQuad p_177648_1_) {
/* 147 */       this.field_177656_a.add(p_177648_1_);
/* 148 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Builder func_177646_a(TextureAtlasSprite p_177646_1_) {
/* 153 */       this.field_177652_d = p_177646_1_;
/* 154 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public IBakedModel func_177645_b() {
/* 159 */       if (this.field_177652_d == null)
/*     */       {
/* 161 */         throw new RuntimeException("Missing particle!");
/*     */       }
/*     */ 
/*     */       
/* 165 */       return new SimpleBakedModel(this.field_177656_a, this.field_177654_b, this.field_177655_c, this.field_177653_e, this.field_177652_d, this.field_177651_f);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\model\SimpleBakedModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */