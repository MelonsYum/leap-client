/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import net.minecraft.stats.StatList;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemWritableBook
/*    */   extends Item
/*    */ {
/*    */   private static final String __OBFID = "CL_00000076";
/*    */   
/*    */   public ItemWritableBook() {
/* 15 */     setMaxStackSize(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 23 */     playerIn.displayGUIBook(itemStackIn);
/* 24 */     playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/* 25 */     return itemStackIn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean validBookPageTagContents(NBTTagCompound p_150930_0_) {
/* 33 */     if (p_150930_0_ == null)
/*    */     {
/* 35 */       return false;
/*    */     }
/* 37 */     if (!p_150930_0_.hasKey("pages", 9))
/*    */     {
/* 39 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 43 */     NBTTagList var1 = p_150930_0_.getTagList("pages", 8);
/*    */     
/* 45 */     for (int var2 = 0; var2 < var1.tagCount(); var2++) {
/*    */       
/* 47 */       String var3 = var1.getStringTagAt(var2);
/*    */       
/* 49 */       if (var3 == null)
/*    */       {
/* 51 */         return false;
/*    */       }
/*    */       
/* 54 */       if (var3.length() > 32767)
/*    */       {
/* 56 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 60 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemWritableBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */