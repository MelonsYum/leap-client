/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.RenderHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityLargeExplodeFX
/*    */   extends EntityFX {
/* 15 */   private static final ResourceLocation field_110127_a = new ResourceLocation("textures/entity/explosion.png");
/*    */   
/*    */   private int field_70581_a;
/*    */   
/*    */   private int field_70584_aq;
/*    */   
/*    */   private TextureManager theRenderEngine;
/*    */   private float field_70582_as;
/*    */   private static final String __OBFID = "CL_00000910";
/*    */   
/*    */   protected EntityLargeExplodeFX(TextureManager p_i1213_1_, World worldIn, double p_i1213_3_, double p_i1213_5_, double p_i1213_7_, double p_i1213_9_, double p_i1213_11_, double p_i1213_13_) {
/* 26 */     super(worldIn, p_i1213_3_, p_i1213_5_, p_i1213_7_, 0.0D, 0.0D, 0.0D);
/* 27 */     this.theRenderEngine = p_i1213_1_;
/* 28 */     this.field_70584_aq = 6 + this.rand.nextInt(4);
/* 29 */     this.particleRed = this.particleGreen = this.particleBlue = this.rand.nextFloat() * 0.6F + 0.4F;
/* 30 */     this.field_70582_as = 1.0F - (float)p_i1213_9_ * 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 35 */     int var9 = (int)((this.field_70581_a + p_180434_3_) * 15.0F / this.field_70584_aq);
/*    */     
/* 37 */     if (var9 <= 15) {
/*    */       
/* 39 */       this.theRenderEngine.bindTexture(field_110127_a);
/* 40 */       float var10 = (var9 % 4) / 4.0F;
/* 41 */       float var11 = var10 + 0.24975F;
/* 42 */       float var12 = (var9 / 4) / 4.0F;
/* 43 */       float var13 = var12 + 0.24975F;
/* 44 */       float var14 = 2.0F * this.field_70582_as;
/* 45 */       float var15 = (float)(this.prevPosX + (this.posX - this.prevPosX) * p_180434_3_ - interpPosX);
/* 46 */       float var16 = (float)(this.prevPosY + (this.posY - this.prevPosY) * p_180434_3_ - interpPosY);
/* 47 */       float var17 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * p_180434_3_ - interpPosZ);
/* 48 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 49 */       GlStateManager.disableLighting();
/* 50 */       RenderHelper.disableStandardItemLighting();
/* 51 */       p_180434_1_.startDrawingQuads();
/* 52 */       p_180434_1_.func_178960_a(this.particleRed, this.particleGreen, this.particleBlue, 1.0F);
/* 53 */       p_180434_1_.func_178980_d(0.0F, 1.0F, 0.0F);
/* 54 */       p_180434_1_.func_178963_b(240);
/* 55 */       p_180434_1_.addVertexWithUV((var15 - p_180434_4_ * var14 - p_180434_7_ * var14), (var16 - p_180434_5_ * var14), (var17 - p_180434_6_ * var14 - p_180434_8_ * var14), var11, var13);
/* 56 */       p_180434_1_.addVertexWithUV((var15 - p_180434_4_ * var14 + p_180434_7_ * var14), (var16 + p_180434_5_ * var14), (var17 - p_180434_6_ * var14 + p_180434_8_ * var14), var11, var12);
/* 57 */       p_180434_1_.addVertexWithUV((var15 + p_180434_4_ * var14 + p_180434_7_ * var14), (var16 + p_180434_5_ * var14), (var17 + p_180434_6_ * var14 + p_180434_8_ * var14), var10, var12);
/* 58 */       p_180434_1_.addVertexWithUV((var15 + p_180434_4_ * var14 - p_180434_7_ * var14), (var16 - p_180434_5_ * var14), (var17 + p_180434_6_ * var14 - p_180434_8_ * var14), var10, var13);
/* 59 */       Tessellator.getInstance().draw();
/* 60 */       GlStateManager.doPolygonOffset(0.0F, 0.0F);
/* 61 */       GlStateManager.enableLighting();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBrightnessForRender(float p_70070_1_) {
/* 67 */     return 61680;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 75 */     this.prevPosX = this.posX;
/* 76 */     this.prevPosY = this.posY;
/* 77 */     this.prevPosZ = this.posZ;
/* 78 */     this.field_70581_a++;
/*    */     
/* 80 */     if (this.field_70581_a == this.field_70584_aq)
/*    */     {
/* 82 */       setDead();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 88 */     return 3;
/*    */   }
/*    */   
/*    */   public static class Factory
/*    */     implements IParticleFactory
/*    */   {
/*    */     private static final String __OBFID = "CL_00002598";
/*    */     
/*    */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 97 */       return new EntityLargeExplodeFX(Minecraft.getMinecraft().getTextureManager(), worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityLargeExplodeFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */