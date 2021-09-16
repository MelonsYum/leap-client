/*     */ package leap.modules.combat;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.RotationUtils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
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
/*     */ public class BowAimbot
/*     */   extends Module
/*     */ {
/*     */   boolean send;
/*     */   boolean isFiring;
/*     */   public static EntityLivingBase target;
/*  32 */   public BooleanSetting silent = new BooleanSetting("Silent", true);
/*     */   
/*     */   public BowAimbot() {
/*  35 */     super("BowAimbot", 0, Module.Category.COMBAT);
/*  36 */     addSettings(new Setting[] { (Setting)this.silent });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  40 */     super.onEnable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  45 */     target = null;
/*  46 */     super.onDisable();
/*     */   }
/*     */   public boolean shouldAim() {
/*  49 */     if (mc.thePlayer.inventory.getCurrentItem() == null || !(mc.thePlayer.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemBow))
/*  50 */       return false; 
/*  51 */     if (mc.thePlayer.isUsingItem())
/*  52 */       return true; 
/*  53 */     return false;
/*     */   }
/*     */   private EntityLivingBase getTarg() {
/*  56 */     List<EntityLivingBase> loaded = new ArrayList<>();
/*  57 */     for (Object o : mc.theWorld.getLoadedEntityList()) {
/*  58 */       if (o instanceof EntityLivingBase) {
/*  59 */         EntityLivingBase ent = (EntityLivingBase)o;
/*  60 */         if (ent instanceof net.minecraft.entity.player.EntityPlayer && ent != mc.thePlayer && mc.thePlayer.getDistanceToEntity((Entity)ent) < 65.0F) {
/*  61 */           loaded.add(ent);
/*     */         }
/*     */       } 
/*     */     } 
/*  65 */     if (loaded.isEmpty()) {
/*  66 */       return null;
/*     */     }
/*  68 */     loaded.sort((o1, o2) -> {
/*     */           float[] rot1 = RotationUtils.getPredictedRotations(o1);
/*     */           
/*     */           float[] rot2 = RotationUtils.getPredictedRotations(o2);
/*     */           
/*     */           return (int)(getDistanceBetweenAngles(mc.thePlayer.rotationYaw, rot1[0]) + getDistanceBetweenAngles(mc.thePlayer.rotationPitch, rot1[1]) - getDistanceBetweenAngles(mc.thePlayer.rotationYaw, rot2[0]) + getDistanceBetweenAngles(mc.thePlayer.rotationPitch, rot2[1]));
/*     */         });
/*     */     
/*  76 */     EntityLivingBase target = loaded.get(0);
/*  77 */     return target;
/*     */   }
/*     */   
/*     */   public static float[] getRotations(EntityLivingBase ent) {
/*  81 */     double x = ent.posX;
/*  82 */     double z = ent.posZ;
/*  83 */     double y = ent.posY + (ent.getEyeHeight() / 2.0F);
/*  84 */     return getRotationFromPosition(x, z, y);
/*     */   }
/*     */   
/*     */   public static float[] getRotationFromPosition(double x, double z, double y) {
/*  88 */     double xDiff = x - (Minecraft.getMinecraft()).thePlayer.posX;
/*  89 */     double zDiff = z - (Minecraft.getMinecraft()).thePlayer.posZ;
/*  90 */     double yDiff = y - (Minecraft.getMinecraft()).thePlayer.posY - 1.2D;
/*     */     
/*  92 */     double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
/*  93 */     float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0D / Math.PI) - 90.0F;
/*  94 */     float pitch = (float)-(Math.atan2(yDiff, dist) * 180.0D / Math.PI);
/*  95 */     return new float[] { yaw, pitch };
/*     */   }
/*     */   
/*     */   public static float getDistanceBetweenAngles(float angle1, float angle2) {
/*  99 */     float angle = Math.abs(angle1 - angle2) % 360.0F;
/* 100 */     if (angle > 180.0F) {
/* 101 */       angle = 360.0F - angle;
/*     */     }
/* 103 */     return angle;
/*     */   }
/*     */   
/*     */   public static float[] getBowAngles(Entity entity) {
/* 107 */     double xDelta = (entity.posX - entity.lastTickPosX) * 0.4D;
/* 108 */     double zDelta = (entity.posZ - entity.lastTickPosZ) * 0.4D;
/* 109 */     double d = (Minecraft.getMinecraft()).thePlayer.getDistanceToEntity(entity);
/* 110 */     d -= d % 0.8D;
/* 111 */     double xMulti = 1.0D;
/* 112 */     double zMulti = 1.0D;
/* 113 */     boolean sprint = entity.isSprinting();
/* 114 */     xMulti = d / 0.8D * xDelta * (sprint ? 1.25D : 1.0D);
/* 115 */     zMulti = d / 0.8D * zDelta * (sprint ? 1.25D : 1.0D);
/* 116 */     double x = entity.posX + xMulti - (Minecraft.getMinecraft()).thePlayer.posX;
/* 117 */     double z = entity.posZ + zMulti - (Minecraft.getMinecraft()).thePlayer.posZ;
/* 118 */     double y = (Minecraft.getMinecraft()).thePlayer.posY + (Minecraft.getMinecraft()).thePlayer.getEyeHeight() - entity.posY + entity.getEyeHeight();
/* 119 */     double dist = (Minecraft.getMinecraft()).thePlayer.getDistanceToEntity(entity);
/* 120 */     float yaw = (float)Math.toDegrees(Math.atan2(z, x)) - 90.0F;
/* 121 */     double d1 = MathHelper.sqrt_double(x * x + z * z);
/* 122 */     float pitch = (float)-(Math.atan2(y, d1) * 180.0D / Math.PI) + (float)dist * 0.11F;
/*     */     
/* 124 */     return new float[] { yaw, -pitch };
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\BowAimbot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */