/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityPickupFX
/*    */   extends EntityFX {
/*    */   private Entity field_174840_a;
/*    */   private Entity field_174843_ax;
/*    */   private int age;
/*    */   private int maxAge;
/*    */   private float field_174841_aA;
/* 18 */   private RenderManager field_174842_aB = Minecraft.getMinecraft().getRenderManager();
/*    */   
/*    */   private static final String __OBFID = "CL_00000930";
/*    */   
/*    */   public EntityPickupFX(World worldIn, Entity p_i1233_2_, Entity p_i1233_3_, float p_i1233_4_) {
/* 23 */     super(worldIn, p_i1233_2_.posX, p_i1233_2_.posY, p_i1233_2_.posZ, p_i1233_2_.motionX, p_i1233_2_.motionY, p_i1233_2_.motionZ);
/* 24 */     this.field_174840_a = p_i1233_2_;
/* 25 */     this.field_174843_ax = p_i1233_3_;
/* 26 */     this.maxAge = 3;
/* 27 */     this.field_174841_aA = p_i1233_4_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {
/* 32 */     float var9 = (this.age + p_180434_3_) / this.maxAge;
/* 33 */     var9 *= var9;
/* 34 */     double var10 = this.field_174840_a.posX;
/* 35 */     double var12 = this.field_174840_a.posY;
/* 36 */     double var14 = this.field_174840_a.posZ;
/* 37 */     double var16 = this.field_174843_ax.lastTickPosX + (this.field_174843_ax.posX - this.field_174843_ax.lastTickPosX) * p_180434_3_;
/* 38 */     double var18 = this.field_174843_ax.lastTickPosY + (this.field_174843_ax.posY - this.field_174843_ax.lastTickPosY) * p_180434_3_ + this.field_174841_aA;
/* 39 */     double var20 = this.field_174843_ax.lastTickPosZ + (this.field_174843_ax.posZ - this.field_174843_ax.lastTickPosZ) * p_180434_3_;
/* 40 */     double var22 = var10 + (var16 - var10) * var9;
/* 41 */     double var24 = var12 + (var18 - var12) * var9;
/* 42 */     double var26 = var14 + (var20 - var14) * var9;
/* 43 */     int var28 = getBrightnessForRender(p_180434_3_);
/* 44 */     int var29 = var28 % 65536;
/* 45 */     int var30 = var28 / 65536;
/* 46 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var29 / 1.0F, var30 / 1.0F);
/* 47 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 48 */     var22 -= interpPosX;
/* 49 */     var24 -= interpPosY;
/* 50 */     var26 -= interpPosZ;
/* 51 */     this.field_174842_aB.renderEntityWithPosYaw(this.field_174840_a, (float)var22, (float)var24, (float)var26, this.field_174840_a.rotationYaw, p_180434_3_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 59 */     this.age++;
/*    */     
/* 61 */     if (this.age == this.maxAge)
/*    */     {
/* 63 */       setDead();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 69 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityPickupFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */