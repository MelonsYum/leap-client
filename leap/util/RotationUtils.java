/*     */ package leap.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RotationUtils
/*     */ {
/*  19 */   static Minecraft mc = Minecraft.getMinecraft();
/*     */   
/*     */   public static float[] getRotations(Entity e) {
/*  22 */     double deltaX = e.posX + e.posX - e.lastTickPosX - mc.thePlayer.posX;
/*  23 */     double deltaY = e.posY - 3.5D + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
/*  24 */     double deltaZ = e.posZ + e.posZ - e.lastTickPosZ - mc.thePlayer.posZ;
/*  25 */     double distance = Math.sqrt(Math.pow(deltaX, 2.0D) + Math.pow(deltaZ, 2.0D));
/*     */     
/*  27 */     float yaw = (float)Math.toDegrees(-Math.atan(deltaX - deltaZ));
/*  28 */     float pitch = (float)-Math.toDegrees(Math.atan(deltaY / distance));
/*     */     
/*  30 */     if (deltaX < 0.0D && deltaZ < 0.0D) {
/*  31 */       yaw = (float)(90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX)));
/*  32 */     } else if (deltaX > 0.0D && deltaZ < 0.0D) {
/*  33 */       yaw = (float)(-90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX)));
/*     */     } 
/*     */     
/*  36 */     return new float[] { yaw, pitch };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] getRotationFromPosition(double x, double z, double y) {
/*  42 */     double xDiff = x - (Minecraft.getMinecraft()).thePlayer.posX;
/*  43 */     double zDiff = z - (Minecraft.getMinecraft()).thePlayer.posZ;
/*  44 */     double yDiff = y - (Minecraft.getMinecraft()).thePlayer.posY - 1.2D;
/*     */     
/*  46 */     double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
/*  47 */     float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0D / Math.PI) - 90.0F;
/*  48 */     float pitch = (float)-(Math.atan2(yDiff, dist) * 180.0D / Math.PI);
/*  49 */     return new float[] { yaw, pitch };
/*     */   }
/*     */   
/*     */   public static float[] getPredictedRotations(EntityLivingBase ent) {
/*  53 */     double x = ent.posX + ent.posX - ent.lastTickPosX + randomNumber(0.03D, -0.03D);
/*  54 */     double z = ent.posZ + ent.posZ - ent.lastTickPosZ + randomNumber(0.03D, -0.03D);
/*  55 */     double y = ent.posY + (ent.getEyeHeight() / 2.0F);
/*  56 */     return getRotationFromPosition(x, z, y);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float[] getRotationss(double posX, double posY, double posZ) {
/*  61 */     Minecraft mc = Minecraft.getMinecraft();
/*     */     
/*  63 */     double x = posX - mc.thePlayer.posX + randomNumber(0.03D, -0.03D);
/*  64 */     double y = posY - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
/*  65 */     double z = posZ - mc.thePlayer.posZ + randomNumber(0.03D, -0.03D);
/*     */     
/*  67 */     double dist = MathHelper.sqrt_double(x * x + z * z);
/*  68 */     float yaw = (float)(Math.atan2(z, x) * 180.0D / Math.PI) - 90.0F;
/*  69 */     float pitch = (float)-(Math.atan2(y, dist) * 180.0D / Math.PI);
/*  70 */     return new float[] { yaw, pitch };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float[] getRotationsEntity(EntityLivingBase entity) {
/*  77 */     Minecraft mc = Minecraft.getMinecraft();
/*     */     
/*  79 */     if (mc.thePlayer.isSprinting()) {
/*  80 */       return getRotationss(entity.posX + randomNumber(0.03D, -0.03D), entity.posY + entity.getEyeHeight() - 0.4D + randomNumber(0.07D, -0.07D), entity.posZ + randomNumber(0.03D, -0.03D));
/*     */     }
/*  82 */     return getRotationss(entity.posX, entity.posY + entity.getEyeHeight() - 0.4D, entity.posZ);
/*     */   }
/*     */   
/*     */   private static MovingObjectPosition tracePath(World world, float x, float y, float z, float tx, float ty, float tz, float borderSize, HashSet<Entity> excluded) {
/*  86 */     Vec3 startVec = new Vec3(x, y, z);
/*  87 */     Vec3 endVec = new Vec3(tx, ty, tz);
/*  88 */     float minX = (x < tx) ? x : tx;
/*  89 */     float minY = (y < ty) ? y : ty;
/*  90 */     float minZ = (z < tz) ? z : tz;
/*  91 */     float maxX = (x > tx) ? x : tx;
/*  92 */     float maxY = (y > ty) ? y : ty;
/*  93 */     float maxZ = (z > tz) ? z : tz;
/*  94 */     AxisAlignedBB bb = (new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ)).expand(borderSize, borderSize, borderSize);
/*  95 */     ArrayList<Entity> allEntities = (ArrayList<Entity>)world.getEntitiesWithinAABBExcludingEntity(null, bb);
/*  96 */     MovingObjectPosition blockHit = world.rayTraceBlocks(startVec, endVec);
/*  97 */     startVec = new Vec3(x, y, z);
/*  98 */     endVec = new Vec3(tx, ty, tz);
/*  99 */     Entity closestHitEntity = null;
/* 100 */     float closestHit = Float.POSITIVE_INFINITY;
/*     */     
/* 102 */     for (Entity ent : allEntities) {
/* 103 */       if (ent.canBeCollidedWith() && !excluded.contains(ent)) {
/* 104 */         float entBorder = ent.getCollisionBorderSize();
/* 105 */         AxisAlignedBB entityBb = ent.getEntityBoundingBox();
/* 106 */         if (entityBb == null) {
/*     */           continue;
/*     */         }
/* 109 */         entityBb = entityBb.expand(entBorder, entBorder, entBorder);
/* 110 */         MovingObjectPosition intercept = entityBb.calculateIntercept(startVec, endVec);
/* 111 */         if (intercept == null) {
/*     */           continue;
/*     */         }
/* 114 */         float currentHit = (float)intercept.hitVec.distanceTo(startVec);
/* 115 */         if (currentHit >= closestHit && currentHit != 0.0F) {
/*     */           continue;
/*     */         }
/* 118 */         closestHit = currentHit;
/* 119 */         closestHitEntity = ent;
/*     */       } 
/*     */     } 
/* 122 */     if (closestHitEntity != null) {
/* 123 */       blockHit = new MovingObjectPosition(closestHitEntity);
/*     */     }
/* 125 */     return blockHit;
/*     */   }
/*     */   
/*     */   private static MovingObjectPosition tracePathD(World w, double posX, double posY, double posZ, double v, double v1, double v2, float borderSize, HashSet<Entity> exclude) {
/* 129 */     return tracePath(w, (float)posX, (float)posY, (float)posZ, (float)v, (float)v1, (float)v2, borderSize, exclude);
/*     */   }
/*     */   
/*     */   public static MovingObjectPosition rayCast(EntityPlayerSP player, double x, double y, double z) {
/* 133 */     HashSet<Entity> excluded = new HashSet<>();
/* 134 */     excluded.add(player);
/* 135 */     return tracePathD(player.worldObj, player.posX, player.posY + player.getEyeHeight(), player.posZ, x, y, z, 1.0F, excluded);
/*     */   }
/*     */   
/*     */   public static int randomNumber(double d, double e) {
/* 139 */     int ii = (int)(-e + (int)(Math.random() * (d - -e + 1.0D)));
/* 140 */     return ii;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\RotationUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */