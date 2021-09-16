/*    */ package net.minecraft.entity;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.InventoryMerchant;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.village.MerchantRecipe;
/*    */ import net.minecraft.village.MerchantRecipeList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NpcMerchant
/*    */   implements IMerchant
/*    */ {
/*    */   private InventoryMerchant theMerchantInventory;
/*    */   private EntityPlayer customer;
/*    */   private MerchantRecipeList recipeList;
/*    */   private IChatComponent field_175548_d;
/*    */   private static final String __OBFID = "CL_00001705";
/*    */   
/*    */   public NpcMerchant(EntityPlayer p_i45817_1_, IChatComponent p_i45817_2_) {
/* 26 */     this.customer = p_i45817_1_;
/* 27 */     this.field_175548_d = p_i45817_2_;
/* 28 */     this.theMerchantInventory = new InventoryMerchant(p_i45817_1_, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPlayer getCustomer() {
/* 33 */     return this.customer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCustomer(EntityPlayer p_70932_1_) {}
/*    */   
/*    */   public MerchantRecipeList getRecipes(EntityPlayer p_70934_1_) {
/* 40 */     return this.recipeList;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecipes(MerchantRecipeList p_70930_1_) {
/* 45 */     this.recipeList = p_70930_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void useRecipe(MerchantRecipe p_70933_1_) {
/* 50 */     p_70933_1_.incrementToolUses();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void verifySellingItem(ItemStack p_110297_1_) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public IChatComponent getDisplayName() {
/* 61 */     return (this.field_175548_d != null) ? this.field_175548_d : (IChatComponent)new ChatComponentTranslation("entity.Villager.name", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\NpcMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */