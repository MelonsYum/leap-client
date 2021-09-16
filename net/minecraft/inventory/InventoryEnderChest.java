/*    */ package net.minecraft.inventory;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import net.minecraft.tileentity.TileEntityEnderChest;
/*    */ 
/*    */ public class InventoryEnderChest
/*    */   extends InventoryBasic {
/*    */   private TileEntityEnderChest associatedChest;
/*    */   private static final String __OBFID = "CL_00001759";
/*    */   
/*    */   public InventoryEnderChest() {
/* 16 */     super("container.enderchest", false, 27);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setChestTileEntity(TileEntityEnderChest chestTileEntity) {
/* 21 */     this.associatedChest = chestTileEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void loadInventoryFromNBT(NBTTagList p_70486_1_) {
/*    */     int var2;
/* 28 */     for (var2 = 0; var2 < getSizeInventory(); var2++)
/*    */     {
/* 30 */       setInventorySlotContents(var2, null);
/*    */     }
/*    */     
/* 33 */     for (var2 = 0; var2 < p_70486_1_.tagCount(); var2++) {
/*    */       
/* 35 */       NBTTagCompound var3 = p_70486_1_.getCompoundTagAt(var2);
/* 36 */       int var4 = var3.getByte("Slot") & 0xFF;
/*    */       
/* 38 */       if (var4 >= 0 && var4 < getSizeInventory())
/*    */       {
/* 40 */         setInventorySlotContents(var4, ItemStack.loadItemStackFromNBT(var3));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagList saveInventoryToNBT() {
/* 47 */     NBTTagList var1 = new NBTTagList();
/*    */     
/* 49 */     for (int var2 = 0; var2 < getSizeInventory(); var2++) {
/*    */       
/* 51 */       ItemStack var3 = getStackInSlot(var2);
/*    */       
/* 53 */       if (var3 != null) {
/*    */         
/* 55 */         NBTTagCompound var4 = new NBTTagCompound();
/* 56 */         var4.setByte("Slot", (byte)var2);
/* 57 */         var3.writeToNBT(var4);
/* 58 */         var1.appendTag((NBTBase)var4);
/*    */       } 
/*    */     } 
/*    */     
/* 62 */     return var1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 70 */     return (this.associatedChest != null && !this.associatedChest.func_145971_a(playerIn)) ? false : super.isUseableByPlayer(playerIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void openInventory(EntityPlayer playerIn) {
/* 75 */     if (this.associatedChest != null)
/*    */     {
/* 77 */       this.associatedChest.func_145969_a();
/*    */     }
/*    */     
/* 80 */     super.openInventory(playerIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void closeInventory(EntityPlayer playerIn) {
/* 85 */     if (this.associatedChest != null)
/*    */     {
/* 87 */       this.associatedChest.func_145970_b();
/*    */     }
/*    */     
/* 90 */     super.closeInventory(playerIn);
/* 91 */     this.associatedChest = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\InventoryEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */