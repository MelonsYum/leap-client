/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ 
/*    */ public class PlayerConfiguration
/*    */ {
/*  8 */   private PlayerItemModel[] playerItemModels = new PlayerItemModel[0];
/*    */   
/*    */   private boolean initialized = false;
/*    */   
/*    */   public void renderPlayerItems(ModelBiped modelBiped, AbstractClientPlayer player, float scale, float partialTicks) {
/* 13 */     if (this.initialized)
/*    */     {
/* 15 */       for (int i = 0; i < this.playerItemModels.length; i++) {
/*    */         
/* 17 */         PlayerItemModel model = this.playerItemModels[i];
/* 18 */         model.render(modelBiped, player, scale, partialTicks);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInitialized() {
/* 25 */     return this.initialized;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInitialized(boolean initialized) {
/* 30 */     this.initialized = initialized;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerItemModel[] getPlayerItemModels() {
/* 35 */     return this.playerItemModels;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addPlayerItemModel(PlayerItemModel playerItemModel) {
/* 40 */     this.playerItemModels = (PlayerItemModel[])Config.addObjectToArray((Object[])this.playerItemModels, playerItemModel);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PlayerConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */