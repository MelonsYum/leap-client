/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityMagmaCube;
/*    */ 
/*    */ public class ModelMagmaCube
/*    */   extends ModelBase {
/*  9 */   ModelRenderer[] segments = new ModelRenderer[8];
/*    */   
/*    */   ModelRenderer core;
/*    */   private static final String __OBFID = "CL_00000842";
/*    */   
/*    */   public ModelMagmaCube() {
/* 15 */     for (int var1 = 0; var1 < this.segments.length; var1++) {
/*    */       
/* 17 */       byte var2 = 0;
/* 18 */       int var3 = var1;
/*    */       
/* 20 */       if (var1 == 2) {
/*    */         
/* 22 */         var2 = 24;
/* 23 */         var3 = 10;
/*    */       }
/* 25 */       else if (var1 == 3) {
/*    */         
/* 27 */         var2 = 24;
/* 28 */         var3 = 19;
/*    */       } 
/*    */       
/* 31 */       this.segments[var1] = new ModelRenderer(this, var2, var3);
/* 32 */       this.segments[var1].addBox(-4.0F, (16 + var1), -4.0F, 8, 1, 8);
/*    */     } 
/*    */     
/* 35 */     this.core = new ModelRenderer(this, 0, 16);
/* 36 */     this.core.addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 45 */     EntityMagmaCube var5 = (EntityMagmaCube)p_78086_1_;
/* 46 */     float var6 = var5.prevSquishFactor + (var5.squishFactor - var5.prevSquishFactor) * p_78086_4_;
/*    */     
/* 48 */     if (var6 < 0.0F)
/*    */     {
/* 50 */       var6 = 0.0F;
/*    */     }
/*    */     
/* 53 */     for (int var7 = 0; var7 < this.segments.length; var7++)
/*    */     {
/* 55 */       (this.segments[var7]).rotationPointY = -(4 - var7) * var6 * 1.7F;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 64 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 65 */     this.core.render(p_78088_7_);
/*    */     
/* 67 */     for (int var8 = 0; var8 < this.segments.length; var8++)
/*    */     {
/* 69 */       this.segments[var8].render(p_78088_7_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelMagmaCube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */