/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.Setting;
/*    */ import leap.util.RenderUtil;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Hats
/*    */   extends Module
/*    */ {
/* 21 */   public static ModeSetting look = new ModeSetting("Look", "Halo", new String[] { "Beanie", "Halo", "Flat", "Top Hat" });
/*    */   
/*    */   public Hats() {
/* 24 */     super("Hats", 0, Module.Category.RENDER);
/* 25 */     addSettings(new Setting[] { (Setting)look });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 29 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 33 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 37 */     if (e instanceof leap.events.listeners.EventRenderWorld) {
/*    */       
/* 39 */       EntityPlayerSP entity = mc.thePlayer;
/*    */ 
/*    */       
/* 42 */       mc.getRenderManager(); double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/* 43 */       mc.getRenderManager(); double posY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/* 44 */       mc.getRenderManager(); double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/* 45 */       if (look.getMode() == "Flat") {
/* 46 */         if (mc.thePlayer.isSneaking()) {
/* 47 */           RenderUtil.drawHat(posX, posY + entity.height - 0.25D + 0.5D, posZ, (entity.width / 2.0F) - 0.6D, entity.height - 1.75D, 0.0F, 80.0F, 255.0F, 255.0F, 1.0F, 1.0F, 1.0F, 0.2F, 1.0F);
/*    */         } else {
/* 49 */           RenderUtil.drawHat(posX, posY + entity.height - 0.25D + 0.5D, posZ, (entity.width / 2.0F) - 0.6D, entity.height - 1.75D, 0.0F, 80.0F, 255.0F, 255.0F, 1.0F, 1.0F, 1.0F, 0.2F, 1.0F);
/*    */         } 
/*    */       }
/* 52 */       if (look.getMode() == "Beanie")
/* 53 */         if (mc.thePlayer.isSneaking()) {
/* 54 */           RenderUtil.drawHat(posX, posY + entity.height - 0.25D + 0.2D, posZ, (entity.width / 2.0F) - 0.6D, entity.height - 1.35D, 255.0F, 0.0F, 0.0F, 255.0F, 1.0F, 1.0F, 1.0F, 0.2F, 1.0F);
/* 55 */           RenderUtil.drawHat(posX, posY + entity.height - 0.1D + 0.2D, posZ, (entity.width / 2.0F) - 0.2D, entity.height - 1.35D, 255.0F, 0.0F, 0.0F, 255.0F, 1.0F, 1.0F, 1.0F, 0.2F, 1.0F);
/*    */         } else {
/* 57 */           RenderUtil.drawHat(posX, posY + entity.height - 0.25D + 0.5D, posZ, (entity.width / 2.0F) - 0.6D, entity.height - 1.35D, 255.0F, 0.0F, 0.0F, 255.0F, 1.0F, 1.0F, 1.0F, 0.2F, 1.0F);
/* 58 */           RenderUtil.drawHat(posX, posY + entity.height - 0.1D + 0.5D, posZ, (entity.width / 2.0F) - 0.2D, entity.height - 1.35D, 255.0F, 0.0F, 0.0F, 255.0F, 1.0F, 1.0F, 1.0F, 0.2F, 1.0F);
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\Hats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */