/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatLayerInfo
/*     */ {
/*     */   private final int field_175902_a;
/*     */   private IBlockState field_175901_b;
/*     */   private int layerCount;
/*     */   private int layerMinimumY;
/*     */   private static final String __OBFID = "CL_00000441";
/*     */   
/*     */   public FlatLayerInfo(int p_i45467_1_, Block p_i45467_2_) {
/*  19 */     this(3, p_i45467_1_, p_i45467_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public FlatLayerInfo(int p_i45627_1_, int p_i45627_2_, Block p_i45627_3_) {
/*  24 */     this.layerCount = 1;
/*  25 */     this.field_175902_a = p_i45627_1_;
/*  26 */     this.layerCount = p_i45627_2_;
/*  27 */     this.field_175901_b = p_i45627_3_.getDefaultState();
/*     */   }
/*     */ 
/*     */   
/*     */   public FlatLayerInfo(int p_i45628_1_, int p_i45628_2_, Block p_i45628_3_, int p_i45628_4_) {
/*  32 */     this(p_i45628_1_, p_i45628_2_, p_i45628_3_);
/*  33 */     this.field_175901_b = p_i45628_3_.getStateFromMeta(p_i45628_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLayerCount() {
/*  41 */     return this.layerCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState func_175900_c() {
/*  46 */     return this.field_175901_b;
/*     */   }
/*     */ 
/*     */   
/*     */   private Block func_151536_b() {
/*  51 */     return this.field_175901_b.getBlock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getFillBlockMeta() {
/*  59 */     return this.field_175901_b.getBlock().getMetaFromState(this.field_175901_b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinY() {
/*  67 */     return this.layerMinimumY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMinY(int p_82660_1_) {
/*  75 */     this.layerMinimumY = p_82660_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     String var1;
/*  82 */     if (this.field_175902_a >= 3) {
/*     */       
/*  84 */       ResourceLocation var2 = (ResourceLocation)Block.blockRegistry.getNameForObject(func_151536_b());
/*  85 */       var1 = (var2 == null) ? "null" : var2.toString();
/*     */       
/*  87 */       if (this.layerCount > 1)
/*     */       {
/*  89 */         var1 = String.valueOf(this.layerCount) + "*" + var1;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  94 */       var1 = Integer.toString(Block.getIdFromBlock(func_151536_b()));
/*     */       
/*  96 */       if (this.layerCount > 1)
/*     */       {
/*  98 */         var1 = String.valueOf(this.layerCount) + "x" + var1;
/*     */       }
/*     */     } 
/*     */     
/* 102 */     int var3 = getFillBlockMeta();
/*     */     
/* 104 */     if (var3 > 0)
/*     */     {
/* 106 */       var1 = String.valueOf(var1) + ":" + var3;
/*     */     }
/*     */     
/* 109 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\FlatLayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */