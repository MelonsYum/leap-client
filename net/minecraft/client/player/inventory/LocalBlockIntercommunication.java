/*    */ package net.minecraft.client.player.inventory;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.IInteractionObject;
/*    */ 
/*    */ public class LocalBlockIntercommunication
/*    */   implements IInteractionObject
/*    */ {
/*    */   private String field_175126_a;
/*    */   private IChatComponent field_175125_b;
/*    */   private static final String __OBFID = "CL_00002571";
/*    */   
/*    */   public LocalBlockIntercommunication(String p_i46277_1_, IChatComponent p_i46277_2_) {
/* 17 */     this.field_175126_a = p_i46277_1_;
/* 18 */     this.field_175125_b = p_i46277_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 23 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 31 */     return this.field_175125_b.getUnformattedText();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasCustomName() {
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGuiID() {
/* 44 */     return this.field_175126_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent getDisplayName() {
/* 49 */     return this.field_175125_b;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\player\inventory\LocalBlockIntercommunication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */