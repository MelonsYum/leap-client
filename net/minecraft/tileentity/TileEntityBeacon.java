/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockStainedGlass;
/*     */ import net.minecraft.block.BlockStainedGlassPane;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerBeacon;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ public class TileEntityBeacon
/*     */   extends TileEntityLockable implements IUpdatePlayerListBox, IInventory {
/*  34 */   public static final Potion[][] effectsList = new Potion[][] { { Potion.moveSpeed, Potion.digSpeed }, { Potion.resistance, Potion.jump }, { Potion.damageBoost }, { Potion.regeneration } };
/*  35 */   private final List field_174909_f = Lists.newArrayList();
/*     */   
/*     */   private long field_146016_i;
/*     */   
/*     */   private float field_146014_j;
/*     */   private boolean isComplete;
/*  41 */   private int levels = -1;
/*     */ 
/*     */   
/*     */   private int primaryEffect;
/*     */ 
/*     */   
/*     */   private int secondaryEffect;
/*     */ 
/*     */   
/*     */   private ItemStack payment;
/*     */ 
/*     */   
/*     */   private String field_146008_p;
/*     */   
/*     */   private static final String __OBFID = "CL_00000339";
/*     */ 
/*     */   
/*     */   public void update() {
/*  59 */     if (this.worldObj.getTotalWorldTime() % 80L == 0L)
/*     */     {
/*  61 */       func_174908_m();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174908_m() {
/*  67 */     func_146003_y();
/*  68 */     func_146000_x();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_146000_x() {
/*  73 */     if (this.isComplete && this.levels > 0 && !this.worldObj.isRemote && this.primaryEffect > 0) {
/*     */       
/*  75 */       double var1 = (this.levels * 10 + 10);
/*  76 */       byte var3 = 0;
/*     */       
/*  78 */       if (this.levels >= 4 && this.primaryEffect == this.secondaryEffect)
/*     */       {
/*  80 */         var3 = 1;
/*     */       }
/*     */       
/*  83 */       int var4 = this.pos.getX();
/*  84 */       int var5 = this.pos.getY();
/*  85 */       int var6 = this.pos.getZ();
/*  86 */       AxisAlignedBB var7 = (new AxisAlignedBB(var4, var5, var6, (var4 + 1), (var5 + 1), (var6 + 1))).expand(var1, var1, var1).addCoord(0.0D, this.worldObj.getHeight(), 0.0D);
/*  87 */       List<EntityPlayer> var8 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, var7);
/*  88 */       Iterator<EntityPlayer> var9 = var8.iterator();
/*     */ 
/*     */       
/*  91 */       while (var9.hasNext()) {
/*     */         
/*  93 */         EntityPlayer var10 = var9.next();
/*  94 */         var10.addPotionEffect(new PotionEffect(this.primaryEffect, 180, var3, true, true));
/*     */       } 
/*     */       
/*  97 */       if (this.levels >= 4 && this.primaryEffect != this.secondaryEffect && this.secondaryEffect > 0) {
/*     */         
/*  99 */         var9 = var8.iterator();
/*     */         
/* 101 */         while (var9.hasNext()) {
/*     */           
/* 103 */           EntityPlayer var10 = var9.next();
/* 104 */           var10.addPotionEffect(new PotionEffect(this.secondaryEffect, 180, 0, true, true));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_146003_y() {
/* 112 */     int var1 = this.levels;
/* 113 */     int var2 = this.pos.getX();
/* 114 */     int var3 = this.pos.getY();
/* 115 */     int var4 = this.pos.getZ();
/* 116 */     this.levels = 0;
/* 117 */     this.field_174909_f.clear();
/* 118 */     this.isComplete = true;
/* 119 */     BeamSegment var5 = new BeamSegment(EntitySheep.func_175513_a(EnumDyeColor.WHITE));
/* 120 */     this.field_174909_f.add(var5);
/* 121 */     boolean var6 = true;
/*     */     
/*     */     int var7;
/* 124 */     for (var7 = var3 + 1; var7 < this.worldObj.getActualHeight(); ) {
/*     */       float[] var10;
/* 126 */       BlockPos var8 = new BlockPos(var2, var7, var4);
/* 127 */       IBlockState var9 = this.worldObj.getBlockState(var8);
/*     */ 
/*     */       
/* 130 */       if (var9.getBlock() == Blocks.stained_glass)
/*     */       
/* 132 */       { var10 = EntitySheep.func_175513_a((EnumDyeColor)var9.getValue((IProperty)BlockStainedGlass.field_176547_a)); }
/*     */       
/*     */       else
/*     */       
/* 136 */       { if (var9.getBlock() != Blocks.stained_glass_pane)
/*     */         
/* 138 */         { if (var9.getBlock().getLightOpacity() >= 15) {
/*     */             
/* 140 */             this.isComplete = false;
/* 141 */             this.field_174909_f.clear();
/*     */             
/*     */             break;
/*     */           } 
/* 145 */           var5.func_177262_a(); }
/*     */         
/*     */         else
/*     */         
/* 149 */         { var10 = EntitySheep.func_175513_a((EnumDyeColor)var9.getValue((IProperty)BlockStainedGlassPane.field_176245_a));
/*     */ 
/*     */           
/* 152 */           if (!var6)
/*     */           {
/* 154 */             var10 = new float[] { (var5.func_177263_b()[0] + var10[0]) / 2.0F, (var5.func_177263_b()[1] + var10[1]) / 2.0F, (var5.func_177263_b()[2] + var10[2]) / 2.0F }; }  }  var7++; }  if (!var6) var10 = new float[] { (var5.func_177263_b()[0] + var10[0]) / 2.0F, (var5.func_177263_b()[1] + var10[1]) / 2.0F, (var5.func_177263_b()[2] + var10[2]) / 2.0F };
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     if (this.isComplete) {
/*     */       
/* 172 */       for (var7 = 1; var7 <= 4; this.levels = var7++) {
/*     */         
/* 174 */         int var14 = var3 - var7;
/*     */         
/* 176 */         if (var14 < 0) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 181 */         boolean var16 = true;
/*     */         
/* 183 */         for (int var17 = var2 - var7; var17 <= var2 + var7 && var16; var17++) {
/*     */           
/* 185 */           for (int var11 = var4 - var7; var11 <= var4 + var7; var11++) {
/*     */             
/* 187 */             Block var12 = this.worldObj.getBlockState(new BlockPos(var17, var14, var11)).getBlock();
/*     */             
/* 189 */             if (var12 != Blocks.emerald_block && var12 != Blocks.gold_block && var12 != Blocks.diamond_block && var12 != Blocks.iron_block) {
/*     */               
/* 191 */               var16 = false;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 197 */         if (!var16) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 203 */       if (this.levels == 0)
/*     */       {
/* 205 */         this.isComplete = false;
/*     */       }
/*     */     } 
/*     */     
/* 209 */     if (!this.worldObj.isRemote && this.levels == 4 && var1 < this.levels) {
/*     */       
/* 211 */       Iterator<EntityPlayer> var13 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, (new AxisAlignedBB(var2, var3, var4, var2, (var3 - 4), var4)).expand(10.0D, 5.0D, 10.0D)).iterator();
/*     */       
/* 213 */       while (var13.hasNext()) {
/*     */         
/* 215 */         EntityPlayer var15 = var13.next();
/* 216 */         var15.triggerAchievement((StatBase)AchievementList.fullBeacon);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_174907_n() {
/* 223 */     return this.field_174909_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public float shouldBeamRender() {
/* 228 */     if (!this.isComplete)
/*     */     {
/* 230 */       return 0.0F;
/*     */     }
/*     */ 
/*     */     
/* 234 */     int var1 = (int)(this.worldObj.getTotalWorldTime() - this.field_146016_i);
/* 235 */     this.field_146016_i = this.worldObj.getTotalWorldTime();
/*     */     
/* 237 */     if (var1 > 1) {
/*     */       
/* 239 */       this.field_146014_j -= var1 / 40.0F;
/*     */       
/* 241 */       if (this.field_146014_j < 0.0F)
/*     */       {
/* 243 */         this.field_146014_j = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 247 */     this.field_146014_j += 0.025F;
/*     */     
/* 249 */     if (this.field_146014_j > 1.0F)
/*     */     {
/* 251 */       this.field_146014_j = 1.0F;
/*     */     }
/*     */     
/* 254 */     return this.field_146014_j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet getDescriptionPacket() {
/* 263 */     NBTTagCompound var1 = new NBTTagCompound();
/* 264 */     writeToNBT(var1);
/* 265 */     return (Packet)new S35PacketUpdateTileEntity(this.pos, 3, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxRenderDistanceSquared() {
/* 270 */     return 65536.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/* 275 */     super.readFromNBT(compound);
/* 276 */     this.primaryEffect = compound.getInteger("Primary");
/* 277 */     this.secondaryEffect = compound.getInteger("Secondary");
/* 278 */     this.levels = compound.getInteger("Levels");
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/* 283 */     super.writeToNBT(compound);
/* 284 */     compound.setInteger("Primary", this.primaryEffect);
/* 285 */     compound.setInteger("Secondary", this.secondaryEffect);
/* 286 */     compound.setInteger("Levels", this.levels);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/* 294 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlot(int slotIn) {
/* 302 */     return (slotIn == 0) ? this.payment : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int index, int count) {
/* 311 */     if (index == 0 && this.payment != null) {
/*     */       
/* 313 */       if (count >= this.payment.stackSize) {
/*     */         
/* 315 */         ItemStack var3 = this.payment;
/* 316 */         this.payment = null;
/* 317 */         return var3;
/*     */       } 
/*     */ 
/*     */       
/* 321 */       this.payment.stackSize -= count;
/* 322 */       return new ItemStack(this.payment.getItem(), count, this.payment.getMetadata());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 327 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getStackInSlotOnClosing(int index) {
/* 337 */     if (index == 0 && this.payment != null) {
/*     */       
/* 339 */       ItemStack var2 = this.payment;
/* 340 */       this.payment = null;
/* 341 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 345 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInventorySlotContents(int index, ItemStack stack) {
/* 354 */     if (index == 0)
/*     */     {
/* 356 */       this.payment = stack;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 365 */     return hasCustomName() ? this.field_146008_p : "container.beacon";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 373 */     return (this.field_146008_p != null && this.field_146008_p.length() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145999_a(String p_145999_1_) {
/* 378 */     this.field_146008_p = p_145999_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInventoryStackLimit() {
/* 387 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseableByPlayer(EntityPlayer playerIn) {
/* 395 */     return (this.worldObj.getTileEntity(this.pos) != this) ? false : ((playerIn.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D));
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
/* 407 */     return !(stack.getItem() != Items.emerald && stack.getItem() != Items.diamond && stack.getItem() != Items.gold_ingot && stack.getItem() != Items.iron_ingot);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuiID() {
/* 412 */     return "minecraft:beacon";
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 417 */     return (Container)new ContainerBeacon((IInventory)playerInventory, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getField(int id) {
/* 422 */     switch (id) {
/*     */       
/*     */       case 0:
/* 425 */         return this.levels;
/*     */       
/*     */       case 1:
/* 428 */         return this.primaryEffect;
/*     */       
/*     */       case 2:
/* 431 */         return this.secondaryEffect;
/*     */     } 
/*     */     
/* 434 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setField(int id, int value) {
/* 440 */     switch (id) {
/*     */       
/*     */       case 0:
/* 443 */         this.levels = value;
/*     */         break;
/*     */       
/*     */       case 1:
/* 447 */         this.primaryEffect = value;
/*     */         break;
/*     */       
/*     */       case 2:
/* 451 */         this.secondaryEffect = value;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getFieldCount() {
/* 457 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearInventory() {
/* 462 */     this.payment = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean receiveClientEvent(int id, int type) {
/* 467 */     if (id == 1) {
/*     */       
/* 469 */       func_174908_m();
/* 470 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 474 */     return super.receiveClientEvent(id, type);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class BeamSegment
/*     */   {
/*     */     private final float[] field_177266_a;
/*     */     
/*     */     private int field_177265_b;
/*     */     private static final String __OBFID = "CL_00002042";
/*     */     
/*     */     public BeamSegment(float[] p_i45669_1_) {
/* 486 */       this.field_177266_a = p_i45669_1_;
/* 487 */       this.field_177265_b = 1;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void func_177262_a() {
/* 492 */       this.field_177265_b++;
/*     */     }
/*     */ 
/*     */     
/*     */     public float[] func_177263_b() {
/* 497 */       return this.field_177266_a;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_177264_c() {
/* 502 */       return this.field_177265_b;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */