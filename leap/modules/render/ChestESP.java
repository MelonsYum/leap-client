/*    */ package leap.modules.render;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.Random;
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.util.ColorUtil;
/*    */ import leap.util.RenderUtil;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.tileentity.TileEntityChest;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChestESP
/*    */   extends Module
/*    */ {
/*    */   public ChestESP() {
/* 21 */     super("ChestESP", 0, Module.Category.RENDER);
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 25 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 29 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 33 */     if (e instanceof leap.events.listeners.EventRenderWorld)
/*    */     {
/* 35 */       for (Object v : (Minecraft.getMinecraft()).theWorld.loadedTileEntityList) {
/* 36 */         if (v instanceof TileEntityChest) {
/* 37 */           TileEntityChest chest = (TileEntityChest)v;
/* 38 */           int x = chest.getPos().getX();
/* 39 */           int y = chest.getPos().getY();
/* 40 */           int z = chest.getPos().getZ();
/* 41 */           double xPos = x - RenderManager.renderPosX;
/* 42 */           double yPos = y - RenderManager.renderPosY;
/* 43 */           double zPos = z - RenderManager.renderPosZ;
/*    */           
/* 45 */           int chestdis = (int)mc.thePlayer.getDistance(x, y, z);
/* 46 */           Random chestrandom = new Random();
/*    */           
/* 48 */           Color color = new Color(ColorUtil.getRainbow(0.5F, 1.0F, 1.0F, 1L));
/*    */           
/* 50 */           RenderUtil.drawBlockESP(xPos, yPos, zPos, 0.0F, 200.0F, 255.0F, 0.2F, 1.0F, 1.0F, 1.0F, 0.0F, 0.5F);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\ChestESP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */