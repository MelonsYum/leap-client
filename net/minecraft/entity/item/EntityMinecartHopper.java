/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerHopper;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.IHopper;
/*     */ import net.minecraft.tileentity.TileEntityHopper;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMinecartHopper extends EntityMinecartContainer implements IHopper {
/*     */   private boolean isBlocked = true;
/*  23 */   private int transferTicker = -1;
/*     */   
/*     */   private BlockPos field_174900_c;
/*     */   private static final String __OBFID = "CL_00001676";
/*     */   
/*     */   public EntityMinecartHopper(World worldIn) {
/*  29 */     super(worldIn);
/*  30 */     this.field_174900_c = BlockPos.ORIGIN;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartHopper(World worldIn, double p_i1721_2_, double p_i1721_4_, double p_i1721_6_) {
/*  35 */     super(worldIn, p_i1721_2_, p_i1721_4_, p_i1721_6_);
/*  36 */     this.field_174900_c = BlockPos.ORIGIN;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecart.EnumMinecartType func_180456_s() {
/*  41 */     return EntityMinecart.EnumMinecartType.HOPPER;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState func_180457_u() {
/*  46 */     return Blocks.hopper.getDefaultState();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDefaultDisplayTileOffset() {
/*  51 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSizeInventory() {
/*  59 */     return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interactFirst(EntityPlayer playerIn) {
/*  67 */     if (!this.worldObj.isRemote)
/*     */     {
/*  69 */       playerIn.displayGUIChest((IInventory)this);
/*     */     }
/*     */     
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onActivatorRailPass(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {
/*  80 */     boolean var5 = !p_96095_4_;
/*     */     
/*  82 */     if (var5 != getBlocked())
/*     */     {
/*  84 */       setBlocked(var5);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBlocked() {
/*  93 */     return this.isBlocked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlocked(boolean p_96110_1_) {
/* 101 */     this.isBlocked = p_96110_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public World getWorld() {
/* 109 */     return this.worldObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getXPos() {
/* 117 */     return this.posX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYPos() {
/* 125 */     return this.posY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getZPos() {
/* 133 */     return this.posZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 141 */     super.onUpdate();
/*     */     
/* 143 */     if (!this.worldObj.isRemote && isEntityAlive() && getBlocked()) {
/*     */       
/* 145 */       BlockPos var1 = new BlockPos(this);
/*     */       
/* 147 */       if (var1.equals(this.field_174900_c)) {
/*     */         
/* 149 */         this.transferTicker--;
/*     */       }
/*     */       else {
/*     */         
/* 153 */         setTransferTicker(0);
/*     */       } 
/*     */       
/* 156 */       if (!canTransfer()) {
/*     */         
/* 158 */         setTransferTicker(0);
/*     */         
/* 160 */         if (func_96112_aD()) {
/*     */           
/* 162 */           setTransferTicker(4);
/* 163 */           markDirty();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_96112_aD() {
/* 171 */     if (TileEntityHopper.func_145891_a(this))
/*     */     {
/* 173 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 177 */     List<EntityItem> var1 = this.worldObj.func_175647_a(EntityItem.class, getEntityBoundingBox().expand(0.25D, 0.0D, 0.25D), IEntitySelector.selectAnything);
/*     */     
/* 179 */     if (var1.size() > 0)
/*     */     {
/* 181 */       TileEntityHopper.func_145898_a((IInventory)this, var1.get(0));
/*     */     }
/*     */     
/* 184 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void killMinecart(DamageSource p_94095_1_) {
/* 190 */     super.killMinecart(p_94095_1_);
/* 191 */     dropItemWithOffset(Item.getItemFromBlock((Block)Blocks.hopper), 1, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 199 */     super.writeEntityToNBT(tagCompound);
/* 200 */     tagCompound.setInteger("TransferCooldown", this.transferTicker);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 208 */     super.readEntityFromNBT(tagCompund);
/* 209 */     this.transferTicker = tagCompund.getInteger("TransferCooldown");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransferTicker(int p_98042_1_) {
/* 217 */     this.transferTicker = p_98042_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canTransfer() {
/* 225 */     return (this.transferTicker > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGuiID() {
/* 230 */     return "minecraft:hopper";
/*     */   }
/*     */ 
/*     */   
/*     */   public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
/* 235 */     return (Container)new ContainerHopper(playerInventory, (IInventory)this, playerIn);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityMinecartHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */