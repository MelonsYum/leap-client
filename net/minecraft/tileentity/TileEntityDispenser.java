/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerDispenser;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ 
/*     */ public class TileEntityDispenser extends TileEntityLockable implements IInventory {
/*  15 */   private static final Random field_174913_f = new Random();
/*  16 */   private ItemStack[] field_146022_i = new ItemStack[9];
/*     */ 
/*     */   
/*     */   protected String field_146020_a;
/*     */   
/*     */   private static final String __OBFID = "CL_00000352";
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  25 */     return 9;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/*  33 */     return this.field_146022_i[slotIn];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/*  42 */     if (this.field_146022_i[index] != null) {
/*     */ 
/*     */ 
/*     */       
/*  46 */       if ((this.field_146022_i[index]).stackSize <= count) {
/*     */         
/*  48 */         ItemStack itemStack = this.field_146022_i[index];
/*  49 */         this.field_146022_i[index] = null;
/*  50 */         markDirty();
/*  51 */         return itemStack;
/*     */       } 
/*     */ 
/*     */       
/*  55 */       ItemStack var3 = this.field_146022_i[index].splitStack(count);
/*     */       
/*  57 */       if ((this.field_146022_i[index]).stackSize == 0)
/*     */       {
/*  59 */         this.field_146022_i[index] = null;
/*     */       }
/*     */       
/*  62 */       markDirty();
/*  63 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/*  78 */     if (this.field_146022_i[index] != null) {
/*     */       
/*  80 */       ItemStack var2 = this.field_146022_i[index];
/*  81 */       this.field_146022_i[index] = null;
/*  82 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_146017_i() {
/*  92 */     int var1 = -1;
/*  93 */     int var2 = 1;
/*     */     
/*  95 */     for (int var3 = 0; var3 < this.field_146022_i.length; var3++) {
/*     */       
/*  97 */       if (this.field_146022_i[var3] != null && field_174913_f.nextInt(var2++) == 0)
/*     */       {
/*  99 */         var1 = var3;
/*     */       }
/*     */     } 
/*     */     
/* 103 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 111 */     this.field_146022_i[index] = stack;
/*     */     
/* 113 */     if (stack != null && stack.stackSize > getInventoryStackLimit())
/*     */     {
/* 115 */       stack.stackSize = getInventoryStackLimit();
/*     */     }
/*     */     
/* 118 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_146019_a(ItemStack p_146019_1_) {
/* 123 */     for (int var2 = 0; var2 < this.field_146022_i.length; var2++) {
/*     */       
/* 125 */       if (this.field_146022_i[var2] == null || this.field_146022_i[var2].getItem() == null) {
/*     */         
/* 127 */         setInventorySlotContents(var2, p_146019_1_);
/* 128 */         return var2;
/*     */       } 
/*     */     } 
/*     */     
/* 132 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 140 */     return hasCustomName() ? this.field_146020_a : "container.dispenser";
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146018_a(String p_146018_1_) {
/* 145 */     this.field_146020_a = p_146018_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 153 */     return (this.field_146020_a != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/* 158 */     super.readFromNBT(compound);
/* 159 */     NBTTagList var2 = compound.getTagList("Items", 10);
/* 160 */     this.field_146022_i = new ItemStack[getSizeInventory()];
/*     */     
/* 162 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 164 */       NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 165 */       int var5 = var4.getByte("Slot") & 0xFF;
/*     */       
/* 167 */       if (var5 >= 0 && var5 < this.field_146022_i.length)
/*     */       {
/* 169 */         this.field_146022_i[var5] = ItemStack.loadItemStackFromNBT(var4);
/*     */       }
/*     */     } 
/*     */     
/* 173 */     if (compound.hasKey("CustomName", 8))
/*     */     {
/* 175 */       this.field_146020_a = compound.getString("CustomName");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/* 181 */     super.writeToNBT(compound);
/* 182 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 184 */     for (int var3 = 0; var3 < this.field_146022_i.length; var3++) {
/*     */       
/* 186 */       if (this.field_146022_i[var3] != null) {
/*     */         
/* 188 */         NBTTagCompound var4 = new NBTTagCompound();
/* 189 */         var4.setByte("Slot", (byte)var3);
/* 190 */         this.field_146022_i[var3].writeToNBT(var4);
/* 191 */         var2.appendTag((NBTBase)var4);
/*     */       } 
/*     */     } 
/*     */     
/* 195 */     compound.setTag("Items", (NBTBase)var2);
/*     */     
/* 197 */     if (hasCustomName())
/*     */     {
/* 199 */       compound.setString("CustomName", this.field_146020_a);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 209 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 217 */     return (this.worldObj.getTileEntity(this.pos) != this) ? false : ((playerIn.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void openInventory(EntityPlayer playerIn) {}
/*     */ 
/*     */   
/*     */   public void closeInventory(EntityPlayer playerIn) {}
/*     */ 
/*     */   
/*     */   public boolean isItemValidForSlot(int index, ItemStack stack) {
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuiID() {
/* 234 */     return "minecraft:dispenser";
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 239 */     return (Container)new ContainerDispenser((IInventory)playerInventory, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 244 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {}
/*     */   
/*     */   public int getFieldCount() {
/* 251 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 256 */     for (int var1 = 0; var1 < this.field_146022_i.length; var1++)
/*     */     {
/* 258 */       this.field_146022_i[var1] = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */