/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelBox
/*     */ {
/*     */   private PositionTextureVertex[] vertexPositions;
/*     */   private TexturedQuad[] quadList;
/*     */   public final float posX1;
/*     */   public final float posY1;
/*     */   public final float posZ1;
/*     */   public final float posX2;
/*     */   public final float posY2;
/*     */   public final float posZ2;
/*     */   public String field_78247_g;
/*     */   private static final String __OBFID = "CL_00000872";
/*     */   
/*     */   public ModelBox(ModelRenderer p_i46359_1_, int p_i46359_2_, int p_i46359_3_, float p_i46359_4_, float p_i46359_5_, float p_i46359_6_, int p_i46359_7_, int p_i46359_8_, int p_i46359_9_, float p_i46359_10_) {
/*  37 */     this(p_i46359_1_, p_i46359_2_, p_i46359_3_, p_i46359_4_, p_i46359_5_, p_i46359_6_, p_i46359_7_, p_i46359_8_, p_i46359_9_, p_i46359_10_, p_i46359_1_.mirror);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBox(ModelRenderer p_i46301_1_, int p_i46301_2_, int p_i46301_3_, float p_i46301_4_, float p_i46301_5_, float p_i46301_6_, int p_i46301_7_, int p_i46301_8_, int p_i46301_9_, float p_i46301_10_, boolean p_i46301_11_) {
/*  42 */     this.posX1 = p_i46301_4_;
/*  43 */     this.posY1 = p_i46301_5_;
/*  44 */     this.posZ1 = p_i46301_6_;
/*  45 */     this.posX2 = p_i46301_4_ + p_i46301_7_;
/*  46 */     this.posY2 = p_i46301_5_ + p_i46301_8_;
/*  47 */     this.posZ2 = p_i46301_6_ + p_i46301_9_;
/*  48 */     this.vertexPositions = new PositionTextureVertex[8];
/*  49 */     this.quadList = new TexturedQuad[6];
/*  50 */     float var12 = p_i46301_4_ + p_i46301_7_;
/*  51 */     float var13 = p_i46301_5_ + p_i46301_8_;
/*  52 */     float var14 = p_i46301_6_ + p_i46301_9_;
/*  53 */     p_i46301_4_ -= p_i46301_10_;
/*  54 */     p_i46301_5_ -= p_i46301_10_;
/*  55 */     p_i46301_6_ -= p_i46301_10_;
/*  56 */     var12 += p_i46301_10_;
/*  57 */     var13 += p_i46301_10_;
/*  58 */     var14 += p_i46301_10_;
/*     */     
/*  60 */     if (p_i46301_11_) {
/*     */       
/*  62 */       float var15 = var12;
/*  63 */       var12 = p_i46301_4_;
/*  64 */       p_i46301_4_ = var15;
/*     */     } 
/*     */     
/*  67 */     PositionTextureVertex var24 = new PositionTextureVertex(p_i46301_4_, p_i46301_5_, p_i46301_6_, 0.0F, 0.0F);
/*  68 */     PositionTextureVertex var16 = new PositionTextureVertex(var12, p_i46301_5_, p_i46301_6_, 0.0F, 8.0F);
/*  69 */     PositionTextureVertex var17 = new PositionTextureVertex(var12, var13, p_i46301_6_, 8.0F, 8.0F);
/*  70 */     PositionTextureVertex var18 = new PositionTextureVertex(p_i46301_4_, var13, p_i46301_6_, 8.0F, 0.0F);
/*  71 */     PositionTextureVertex var19 = new PositionTextureVertex(p_i46301_4_, p_i46301_5_, var14, 0.0F, 0.0F);
/*  72 */     PositionTextureVertex var20 = new PositionTextureVertex(var12, p_i46301_5_, var14, 0.0F, 8.0F);
/*  73 */     PositionTextureVertex var21 = new PositionTextureVertex(var12, var13, var14, 8.0F, 8.0F);
/*  74 */     PositionTextureVertex var22 = new PositionTextureVertex(p_i46301_4_, var13, var14, 8.0F, 0.0F);
/*  75 */     this.vertexPositions[0] = var24;
/*  76 */     this.vertexPositions[1] = var16;
/*  77 */     this.vertexPositions[2] = var17;
/*  78 */     this.vertexPositions[3] = var18;
/*  79 */     this.vertexPositions[4] = var19;
/*  80 */     this.vertexPositions[5] = var20;
/*  81 */     this.vertexPositions[6] = var21;
/*  82 */     this.vertexPositions[7] = var22;
/*  83 */     this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] { var20, var16, var17, var21 }, p_i46301_2_ + p_i46301_9_ + p_i46301_7_, p_i46301_3_ + p_i46301_9_, p_i46301_2_ + p_i46301_9_ + p_i46301_7_ + p_i46301_9_, p_i46301_3_ + p_i46301_9_ + p_i46301_8_, p_i46301_1_.textureWidth, p_i46301_1_.textureHeight);
/*  84 */     this.quadList[1] = new TexturedQuad(new PositionTextureVertex[] { var24, var19, var22, var18 }, p_i46301_2_, p_i46301_3_ + p_i46301_9_, p_i46301_2_ + p_i46301_9_, p_i46301_3_ + p_i46301_9_ + p_i46301_8_, p_i46301_1_.textureWidth, p_i46301_1_.textureHeight);
/*  85 */     this.quadList[2] = new TexturedQuad(new PositionTextureVertex[] { var20, var19, var24, var16 }, p_i46301_2_ + p_i46301_9_, p_i46301_3_, p_i46301_2_ + p_i46301_9_ + p_i46301_7_, p_i46301_3_ + p_i46301_9_, p_i46301_1_.textureWidth, p_i46301_1_.textureHeight);
/*  86 */     this.quadList[3] = new TexturedQuad(new PositionTextureVertex[] { var17, var18, var22, var21 }, p_i46301_2_ + p_i46301_9_ + p_i46301_7_, p_i46301_3_ + p_i46301_9_, p_i46301_2_ + p_i46301_9_ + p_i46301_7_ + p_i46301_7_, p_i46301_3_, p_i46301_1_.textureWidth, p_i46301_1_.textureHeight);
/*  87 */     this.quadList[4] = new TexturedQuad(new PositionTextureVertex[] { var16, var24, var18, var17 }, p_i46301_2_ + p_i46301_9_, p_i46301_3_ + p_i46301_9_, p_i46301_2_ + p_i46301_9_ + p_i46301_7_, p_i46301_3_ + p_i46301_9_ + p_i46301_8_, p_i46301_1_.textureWidth, p_i46301_1_.textureHeight);
/*  88 */     this.quadList[5] = new TexturedQuad(new PositionTextureVertex[] { var19, var20, var21, var22 }, p_i46301_2_ + p_i46301_9_ + p_i46301_7_ + p_i46301_9_, p_i46301_3_ + p_i46301_9_, p_i46301_2_ + p_i46301_9_ + p_i46301_7_ + p_i46301_9_ + p_i46301_7_, p_i46301_3_ + p_i46301_9_ + p_i46301_8_, p_i46301_1_.textureWidth, p_i46301_1_.textureHeight);
/*     */     
/*  90 */     if (p_i46301_11_)
/*     */     {
/*  92 */       for (int var23 = 0; var23 < this.quadList.length; var23++)
/*     */       {
/*  94 */         this.quadList[var23].flipFace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(WorldRenderer p_178780_1_, float p_178780_2_) {
/* 101 */     for (int var3 = 0; var3 < this.quadList.length; var3++)
/*     */     {
/* 103 */       this.quadList[var3].func_178765_a(p_178780_1_, p_178780_2_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBox func_78244_a(String p_78244_1_) {
/* 109 */     this.field_78247_g = p_78244_1_;
/* 110 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */