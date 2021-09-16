/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FPSBooster
/*    */   extends Module
/*    */ {
/*    */   public FPSBooster() {
/* 16 */     super("FPSBooster", 0, Module.Category.RENDER);
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 20 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 24 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 28 */     if (e instanceof leap.events.listeners.EventUpdate) {
/* 29 */       EntityLivingBase entity = getFarthest(96.0D);
/* 30 */       if (entity == null) {
/* 31 */         mc.gameSettings.renderDistanceChunks = 4;
/*    */       } else {
/* 33 */         mc.gameSettings.renderDistanceChunks = (mc.thePlayer.getDistanceToEntity((Entity)entity) > 96.0F) ? 6 : (int)(mc.thePlayer.getDistanceToEntity((Entity)entity) / 16.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private EntityLivingBase getFarthest(double range) {
/* 39 */     double dist = range;
/* 40 */     EntityLivingBase target = null;
/* 41 */     for (Object object : mc.theWorld.loadedEntityList) {
/* 42 */       Entity entity = (Entity)object;
/* 43 */       if (entity instanceof EntityLivingBase) {
/* 44 */         EntityLivingBase player = (EntityLivingBase)entity;
/* 45 */         double currentDist = mc.thePlayer.getDistanceToEntity((Entity)player);
/* 46 */         if (currentDist >= dist) {
/* 47 */           dist = currentDist;
/* 48 */           target = player;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 53 */     return target;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\FPSBooster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */