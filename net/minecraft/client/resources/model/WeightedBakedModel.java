/*     */ package net.minecraft.client.resources.model;
/*     */ 
/*     */ import com.google.common.collect.ComparisonChain;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ 
/*     */ public class WeightedBakedModel
/*     */   implements IBakedModel
/*     */ {
/*     */   private final int totalWeight;
/*     */   private final List models;
/*     */   private final IBakedModel baseModel;
/*     */   private static final String __OBFID = "CL_00002384";
/*     */   
/*     */   public WeightedBakedModel(List p_i46073_1_) {
/*  21 */     this.models = p_i46073_1_;
/*  22 */     this.totalWeight = WeightedRandom.getTotalWeight(p_i46073_1_);
/*  23 */     this.baseModel = ((MyWeighedRandomItem)p_i46073_1_.get(0)).model;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177551_a(EnumFacing p_177551_1_) {
/*  28 */     return this.baseModel.func_177551_a(p_177551_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177550_a() {
/*  33 */     return this.baseModel.func_177550_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGui3d() {
/*  38 */     return this.baseModel.isGui3d();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAmbientOcclusionEnabled() {
/*  43 */     return this.baseModel.isAmbientOcclusionEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBuiltInRenderer() {
/*  48 */     return this.baseModel.isBuiltInRenderer();
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getTexture() {
/*  53 */     return this.baseModel.getTexture();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemCameraTransforms getItemCameraTransforms() {
/*  58 */     return this.baseModel.getItemCameraTransforms();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBakedModel func_177564_a(long p_177564_1_) {
/*  63 */     return ((MyWeighedRandomItem)WeightedRandom.func_180166_a(this.models, Math.abs((int)p_177564_1_ >> 16) % this.totalWeight)).model;
/*     */   }
/*     */   
/*     */   public static class Builder
/*     */   {
/*  68 */     private List field_177678_a = Lists.newArrayList();
/*     */     
/*     */     private static final String __OBFID = "CL_00002383";
/*     */     
/*     */     public Builder add(IBakedModel p_177677_1_, int p_177677_2_) {
/*  73 */       this.field_177678_a.add(new WeightedBakedModel.MyWeighedRandomItem(p_177677_1_, p_177677_2_));
/*  74 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public WeightedBakedModel build() {
/*  79 */       Collections.sort(this.field_177678_a);
/*  80 */       return new WeightedBakedModel(this.field_177678_a);
/*     */     }
/*     */ 
/*     */     
/*     */     public IBakedModel first() {
/*  85 */       return ((WeightedBakedModel.MyWeighedRandomItem)this.field_177678_a.get(0)).model;
/*     */     }
/*     */   }
/*     */   
/*     */   static class MyWeighedRandomItem
/*     */     extends WeightedRandom.Item
/*     */     implements Comparable {
/*     */     protected final IBakedModel model;
/*     */     private static final String __OBFID = "CL_00002382";
/*     */     
/*     */     public MyWeighedRandomItem(IBakedModel p_i46072_1_, int p_i46072_2_) {
/*  96 */       super(p_i46072_2_);
/*  97 */       this.model = p_i46072_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_177634_a(MyWeighedRandomItem p_177634_1_) {
/* 102 */       return ComparisonChain.start().compare(p_177634_1_.itemWeight, this.itemWeight).compare(func_177635_a(), p_177634_1_.func_177635_a()).result();
/*     */     }
/*     */ 
/*     */     
/*     */     protected int func_177635_a() {
/* 107 */       int var1 = this.model.func_177550_a().size();
/* 108 */       EnumFacing[] var2 = EnumFacing.values();
/* 109 */       int var3 = var2.length;
/*     */       
/* 111 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/* 113 */         EnumFacing var5 = var2[var4];
/* 114 */         var1 += this.model.func_177551_a(var5).size();
/*     */       } 
/*     */       
/* 117 */       return var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 122 */       return "MyWeighedRandomItem{weight=" + this.itemWeight + ", model=" + this.model + '}';
/*     */     }
/*     */ 
/*     */     
/*     */     public int compareTo(Object p_compareTo_1_) {
/* 127 */       return func_177634_a((MyWeighedRandomItem)p_compareTo_1_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\model\WeightedBakedModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */