/*    */ package optifine;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderPlayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ public class PlayerItemsLayer
/*    */   implements LayerRenderer {
/* 15 */   private RenderPlayer renderPlayer = null;
/*    */ 
/*    */   
/*    */   public PlayerItemsLayer(RenderPlayer renderPlayer) {
/* 19 */     this.renderPlayer = renderPlayer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase entityLiving, float limbSwing, float limbSwingAmount, float partialTicks, float ticksExisted, float headYaw, float rotationPitch, float scale) {
/* 24 */     renderEquippedItems(entityLiving, scale, partialTicks);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void renderEquippedItems(EntityLivingBase entityLiving, float scale, float partialTicks) {
/* 29 */     if (Config.isShowCapes())
/*    */     {
/* 31 */       if (entityLiving instanceof AbstractClientPlayer) {
/*    */         
/* 33 */         AbstractClientPlayer player = (AbstractClientPlayer)entityLiving;
/* 34 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 35 */         GlStateManager.disableRescaleNormal();
/* 36 */         ModelBiped modelBipedMain = (ModelBiped)this.renderPlayer.getMainModel();
/* 37 */         PlayerConfigurations.renderPlayerItems(modelBipedMain, player, scale, partialTicks);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void register(Map renderPlayerMap) {
/* 49 */     Set keys = renderPlayerMap.keySet();
/* 50 */     boolean registered = false;
/* 51 */     Iterator it = keys.iterator();
/*    */     
/* 53 */     while (it.hasNext()) {
/*    */       
/* 55 */       Object key = it.next();
/* 56 */       Object renderer = renderPlayerMap.get(key);
/*    */       
/* 58 */       if (renderer instanceof RenderPlayer) {
/*    */         
/* 60 */         RenderPlayer renderPlayer = (RenderPlayer)renderer;
/* 61 */         renderPlayer.addLayer(new PlayerItemsLayer(renderPlayer));
/* 62 */         registered = true;
/*    */       } 
/*    */     } 
/*    */     
/* 66 */     if (!registered)
/*    */     {
/* 68 */       Config.warn("PlayerItemsLayer not registered");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PlayerItemsLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */