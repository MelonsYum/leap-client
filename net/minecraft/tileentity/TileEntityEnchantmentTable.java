/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerEnchantment;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IInteractionObject;
/*     */ 
/*     */ public class TileEntityEnchantmentTable
/*     */   extends TileEntity implements IUpdatePlayerListBox, IInteractionObject {
/*     */   public int tickCount;
/*     */   public float pageFlip;
/*     */   public float pageFlipPrev;
/*     */   public float field_145932_k;
/*     */   public float field_145929_l;
/*     */   public float bookSpread;
/*     */   public float bookSpreadPrev;
/*     */   public float bookRotation;
/*     */   public float bookRotationPrev;
/*     */   public float field_145924_q;
/*  28 */   private static Random field_145923_r = new Random();
/*     */   
/*     */   private String field_145922_s;
/*     */   private static final String __OBFID = "CL_00000354";
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/*  34 */     super.writeToNBT(compound);
/*     */     
/*  36 */     if (hasCustomName())
/*     */     {
/*  38 */       compound.setString("CustomName", this.field_145922_s);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/*  44 */     super.readFromNBT(compound);
/*     */     
/*  46 */     if (compound.hasKey("CustomName", 8))
/*     */     {
/*  48 */       this.field_145922_s = compound.getString("CustomName");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  57 */     this.bookSpreadPrev = this.bookSpread;
/*  58 */     this.bookRotationPrev = this.bookRotation;
/*  59 */     EntityPlayer var1 = this.worldObj.getClosestPlayer((this.pos.getX() + 0.5F), (this.pos.getY() + 0.5F), (this.pos.getZ() + 0.5F), 3.0D);
/*     */     
/*  61 */     if (var1 != null) {
/*     */       
/*  63 */       double var2 = var1.posX - (this.pos.getX() + 0.5F);
/*  64 */       double var4 = var1.posZ - (this.pos.getZ() + 0.5F);
/*  65 */       this.field_145924_q = (float)Math.atan2(var4, var2);
/*  66 */       this.bookSpread += 0.1F;
/*     */       
/*  68 */       if (this.bookSpread < 0.5F || field_145923_r.nextInt(40) == 0)
/*     */       {
/*  70 */         float var6 = this.field_145932_k;
/*     */ 
/*     */         
/*     */         do {
/*  74 */           this.field_145932_k += (field_145923_r.nextInt(4) - field_145923_r.nextInt(4));
/*     */         }
/*  76 */         while (var6 == this.field_145932_k);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  81 */       this.field_145924_q += 0.02F;
/*  82 */       this.bookSpread -= 0.1F;
/*     */     } 
/*     */     
/*  85 */     while (this.bookRotation >= 3.1415927F)
/*     */     {
/*  87 */       this.bookRotation -= 6.2831855F;
/*     */     }
/*     */     
/*  90 */     while (this.bookRotation < -3.1415927F)
/*     */     {
/*  92 */       this.bookRotation += 6.2831855F;
/*     */     }
/*     */     
/*  95 */     while (this.field_145924_q >= 3.1415927F)
/*     */     {
/*  97 */       this.field_145924_q -= 6.2831855F;
/*     */     }
/*     */     
/* 100 */     while (this.field_145924_q < -3.1415927F)
/*     */     {
/* 102 */       this.field_145924_q += 6.2831855F;
/*     */     }
/*     */     
/*     */     float var7;
/*     */     
/* 107 */     for (var7 = this.field_145924_q - this.bookRotation; var7 >= 3.1415927F; var7 -= 6.2831855F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     while (var7 < -3.1415927F)
/*     */     {
/* 114 */       var7 += 6.2831855F;
/*     */     }
/*     */     
/* 117 */     this.bookRotation += var7 * 0.4F;
/* 118 */     this.bookSpread = MathHelper.clamp_float(this.bookSpread, 0.0F, 1.0F);
/* 119 */     this.tickCount++;
/* 120 */     this.pageFlipPrev = this.pageFlip;
/* 121 */     float var3 = (this.field_145932_k - this.pageFlip) * 0.4F;
/* 122 */     float var8 = 0.2F;
/* 123 */     var3 = MathHelper.clamp_float(var3, -var8, var8);
/* 124 */     this.field_145929_l += (var3 - this.field_145929_l) * 0.9F;
/* 125 */     this.pageFlip += this.field_145929_l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 133 */     return hasCustomName() ? this.field_145922_s : "container.enchant";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 141 */     return (this.field_145922_s != null && this.field_145922_s.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145920_a(String p_145920_1_) {
/* 146 */     this.field_145922_s = p_145920_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getDisplayName() {
/* 151 */     return hasCustomName() ? (IChatComponent)new ChatComponentText(getName()) : (IChatComponent)new ChatComponentTranslation(getName(), new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 156 */     return (Container)new ContainerEnchantment(playerInventory, this.worldObj, this.pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuiID() {
/* 161 */     return "minecraft:enchanting_table";
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityEnchantmentTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */