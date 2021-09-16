/*    */ package net.minecraft.client.particle;
/*    */ 
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class EntityParticleEmitter
/*    */   extends EntityFX
/*    */ {
/*    */   private Entity field_174851_a;
/*    */   private int field_174852_ax;
/*    */   private int field_174850_ay;
/*    */   private EnumParticleTypes field_174849_az;
/*    */   private static final String __OBFID = "CL_00002574";
/*    */   
/*    */   public EntityParticleEmitter(World worldIn, Entity p_i46279_2_, EnumParticleTypes p_i46279_3_) {
/* 19 */     super(worldIn, p_i46279_2_.posX, (p_i46279_2_.getEntityBoundingBox()).minY + (p_i46279_2_.height / 2.0F), p_i46279_2_.posZ, p_i46279_2_.motionX, p_i46279_2_.motionY, p_i46279_2_.motionZ);
/* 20 */     this.field_174851_a = p_i46279_2_;
/* 21 */     this.field_174850_ay = 3;
/* 22 */     this.field_174849_az = p_i46279_3_;
/* 23 */     onUpdate();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_180434_a(WorldRenderer p_180434_1_, Entity p_180434_2_, float p_180434_3_, float p_180434_4_, float p_180434_5_, float p_180434_6_, float p_180434_7_, float p_180434_8_) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdate() {
/* 33 */     for (int var1 = 0; var1 < 16; var1++) {
/*    */       
/* 35 */       double var2 = (this.rand.nextFloat() * 2.0F - 1.0F);
/* 36 */       double var4 = (this.rand.nextFloat() * 2.0F - 1.0F);
/* 37 */       double var6 = (this.rand.nextFloat() * 2.0F - 1.0F);
/*    */       
/* 39 */       if (var2 * var2 + var4 * var4 + var6 * var6 <= 1.0D) {
/*    */         
/* 41 */         double var8 = this.field_174851_a.posX + var2 * this.field_174851_a.width / 4.0D;
/* 42 */         double var10 = (this.field_174851_a.getEntityBoundingBox()).minY + (this.field_174851_a.height / 2.0F) + var4 * this.field_174851_a.height / 4.0D;
/* 43 */         double var12 = this.field_174851_a.posZ + var6 * this.field_174851_a.width / 4.0D;
/* 44 */         this.worldObj.spawnParticle(this.field_174849_az, false, var8, var10, var12, var2, var4 + 0.2D, var6, new int[0]);
/*    */       } 
/*    */     } 
/*    */     
/* 48 */     this.field_174852_ax++;
/*    */     
/* 50 */     if (this.field_174852_ax >= this.field_174850_ay)
/*    */     {
/* 52 */       setDead();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFXLayer() {
/* 58 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityParticleEmitter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */