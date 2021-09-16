/*    */ package leap.util;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CombatUtil
/*    */ {
/* 13 */   public static Minecraft mc = Minecraft.getMinecraft();
/*    */   
/*    */   public void faceEntity(Entity cur, double speed2) {
/* 16 */     float[] rotations = getRotationsNeeded(cur);
/* 17 */     if (rotations != null) {
/* 18 */       mc.thePlayer.rotationYaw = (float)limitAngleChange(mc.thePlayer.prevRotationYaw, rotations[0], speed2);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static float[] faceTarget(Entity target, float p_70625_2_, float p_70625_3_, boolean miss) {
/* 24 */     double var6, var4 = target.posX - mc.thePlayer.posX;
/* 25 */     double var8 = target.posZ - mc.thePlayer.posZ;
/*    */     
/* 27 */     if (target instanceof EntityLivingBase) {
/* 28 */       EntityLivingBase var10 = (EntityLivingBase)target;
/* 29 */       var6 = var10.posY + var10.getEyeHeight() - 
/* 30 */         mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
/*    */     } else {
/* 32 */       var6 = ((target.getEntityBoundingBox()).minY + (target.getEntityBoundingBox()).maxY) / 2.0D - 
/* 33 */         mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
/*    */     } 
/* 35 */     Random rnd = new Random();
/* 36 */     double var14 = MathHelper.sqrt_double(var4 * var4 + var8 * var8);
/* 37 */     float var12 = (float)(Math.atan2(var8, var4) * 180.0D / Math.PI) - 90.0F;
/* 38 */     float var13 = (float)-(Math.atan2(var6 - ((target instanceof net.minecraft.entity.player.EntityPlayer) ? 0.25D : 0.0D), var14) * 
/* 39 */       180.0D / Math.PI);
/* 40 */     float pitch = changeRotation(mc.thePlayer.rotationPitch, var13, p_70625_3_);
/* 41 */     float yaw = changeRotation(mc.thePlayer.rotationYaw, var12, p_70625_2_);
/* 42 */     return new float[] { yaw, pitch };
/*    */   }
/*    */   
/*    */   public static float changeRotation(float p_70663_1_, float p_70663_2_, float p_70663_3_) {
/* 46 */     float var4 = MathHelper.wrapAngleTo180_float(p_70663_2_ - p_70663_1_);
/* 47 */     if (var4 > p_70663_3_)
/* 48 */       var4 = p_70663_3_; 
/* 49 */     if (var4 < -p_70663_3_)
/* 50 */       var4 = -p_70663_3_; 
/* 51 */     return p_70663_1_ + var4;
/*    */   }
/*    */   public float[] getRotationsNeeded(Entity entity) {
/*    */     double diffY;
/* 55 */     if (entity == null) {
/* 56 */       return null;
/*    */     }
/* 58 */     double diffX = entity.posX - mc.thePlayer.posX;
/*    */     
/* 60 */     if (entity instanceof EntityLivingBase) {
/* 61 */       EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
/* 62 */       diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() * 0.9D - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
/*    */     } else {
/* 64 */       diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0D - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
/*    */     } 
/* 66 */     double diffZ = entity.posZ - mc.thePlayer.posZ;
/* 67 */     double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
/* 68 */     float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
/* 69 */     float pitch = (float)-(Math.atan2(diffY, dist) * 180.0D / Math.PI);
/* 70 */     return new float[] { mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw), mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - mc.thePlayer.rotationPitch) };
/*    */   }
/*    */   
/*    */   public double limitAngleChange(double current, double intended, double speed2) {
/* 74 */     double change = intended - current;
/* 75 */     if (change > speed2) {
/* 76 */       change = speed2;
/* 77 */     } else if (change < -speed2) {
/* 78 */       change = -speed2;
/*    */     } 
/* 80 */     return current + change;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\CombatUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */