/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityPiston
/*     */   extends TileEntity
/*     */   implements IUpdatePlayerListBox
/*     */ {
/*     */   private IBlockState field_174932_a;
/*     */   private EnumFacing field_174931_f;
/*     */   private boolean extending;
/*     */   private boolean shouldHeadBeRendered;
/*     */   private float progress;
/*     */   private float lastProgress;
/*  27 */   private List field_174933_k = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00000369";
/*     */   
/*     */   public TileEntityPiston() {}
/*     */   
/*     */   public TileEntityPiston(IBlockState p_i45665_1_, EnumFacing p_i45665_2_, boolean p_i45665_3_, boolean p_i45665_4_) {
/*  34 */     this.field_174932_a = p_i45665_1_;
/*  35 */     this.field_174931_f = p_i45665_2_;
/*  36 */     this.extending = p_i45665_3_;
/*  37 */     this.shouldHeadBeRendered = p_i45665_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState func_174927_b() {
/*  42 */     return this.field_174932_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockMetadata() {
/*  47 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExtending() {
/*  55 */     return this.extending;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumFacing func_174930_e() {
/*  60 */     return this.field_174931_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldPistonHeadBeRendered() {
/*  65 */     return this.shouldHeadBeRendered;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_145860_a(float p_145860_1_) {
/*  70 */     if (p_145860_1_ > 1.0F)
/*     */     {
/*  72 */       p_145860_1_ = 1.0F;
/*     */     }
/*     */     
/*  75 */     return this.lastProgress + (this.progress - this.lastProgress) * p_145860_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_174929_b(float p_174929_1_) {
/*  80 */     return this.extending ? ((func_145860_a(p_174929_1_) - 1.0F) * this.field_174931_f.getFrontOffsetX()) : ((1.0F - func_145860_a(p_174929_1_)) * this.field_174931_f.getFrontOffsetX());
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_174928_c(float p_174928_1_) {
/*  85 */     return this.extending ? ((func_145860_a(p_174928_1_) - 1.0F) * this.field_174931_f.getFrontOffsetY()) : ((1.0F - func_145860_a(p_174928_1_)) * this.field_174931_f.getFrontOffsetY());
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_174926_d(float p_174926_1_) {
/*  90 */     return this.extending ? ((func_145860_a(p_174926_1_) - 1.0F) * this.field_174931_f.getFrontOffsetZ()) : ((1.0F - func_145860_a(p_174926_1_)) * this.field_174931_f.getFrontOffsetZ());
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_145863_a(float p_145863_1_, float p_145863_2_) {
/*  95 */     if (this.extending) {
/*     */       
/*  97 */       p_145863_1_ = 1.0F - p_145863_1_;
/*     */     }
/*     */     else {
/*     */       
/* 101 */       p_145863_1_--;
/*     */     } 
/*     */     
/* 104 */     AxisAlignedBB var3 = Blocks.piston_extension.func_176424_a(this.worldObj, this.pos, this.field_174932_a, p_145863_1_, this.field_174931_f);
/*     */     
/* 106 */     if (var3 != null) {
/*     */       
/* 108 */       List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(null, var3);
/*     */       
/* 110 */       if (!var4.isEmpty()) {
/*     */         
/* 112 */         this.field_174933_k.addAll(var4);
/* 113 */         Iterator<Entity> var5 = this.field_174933_k.iterator();
/*     */         
/* 115 */         while (var5.hasNext()) {
/*     */           
/* 117 */           Entity var6 = var5.next();
/*     */           
/* 119 */           if (this.field_174932_a.getBlock() == Blocks.slime_block && this.extending) {
/*     */             
/* 121 */             switch (SwitchAxis.field_177248_a[this.field_174931_f.getAxis().ordinal()]) {
/*     */               
/*     */               case 1:
/* 124 */                 var6.motionX = this.field_174931_f.getFrontOffsetX();
/*     */                 continue;
/*     */               
/*     */               case 2:
/* 128 */                 var6.motionY = this.field_174931_f.getFrontOffsetY();
/*     */                 continue;
/*     */               
/*     */               case 3:
/* 132 */                 var6.motionZ = this.field_174931_f.getFrontOffsetZ();
/*     */                 continue;
/*     */             } 
/*     */             continue;
/*     */           } 
/* 137 */           var6.moveEntity((p_145863_2_ * this.field_174931_f.getFrontOffsetX()), (p_145863_2_ * this.field_174931_f.getFrontOffsetY()), (p_145863_2_ * this.field_174931_f.getFrontOffsetZ()));
/*     */         } 
/*     */ 
/*     */         
/* 141 */         this.field_174933_k.clear();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPistonTileEntity() {
/* 151 */     if (this.lastProgress < 1.0F && this.worldObj != null) {
/*     */       
/* 153 */       this.lastProgress = this.progress = 1.0F;
/* 154 */       this.worldObj.removeTileEntity(this.pos);
/* 155 */       invalidate();
/*     */       
/* 157 */       if (this.worldObj.getBlockState(this.pos).getBlock() == Blocks.piston_extension) {
/*     */         
/* 159 */         this.worldObj.setBlockState(this.pos, this.field_174932_a, 3);
/* 160 */         this.worldObj.notifyBlockOfStateChange(this.pos, this.field_174932_a.getBlock());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 170 */     this.lastProgress = this.progress;
/*     */     
/* 172 */     if (this.lastProgress >= 1.0F) {
/*     */       
/* 174 */       func_145863_a(1.0F, 0.25F);
/* 175 */       this.worldObj.removeTileEntity(this.pos);
/* 176 */       invalidate();
/*     */       
/* 178 */       if (this.worldObj.getBlockState(this.pos).getBlock() == Blocks.piston_extension)
/*     */       {
/* 180 */         this.worldObj.setBlockState(this.pos, this.field_174932_a, 3);
/* 181 */         this.worldObj.notifyBlockOfStateChange(this.pos, this.field_174932_a.getBlock());
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 186 */       this.progress += 0.5F;
/*     */       
/* 188 */       if (this.progress >= 1.0F)
/*     */       {
/* 190 */         this.progress = 1.0F;
/*     */       }
/*     */       
/* 193 */       if (this.extending)
/*     */       {
/* 195 */         func_145863_a(this.progress, this.progress - this.lastProgress + 0.0625F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/* 202 */     super.readFromNBT(compound);
/* 203 */     this.field_174932_a = Block.getBlockById(compound.getInteger("blockId")).getStateFromMeta(compound.getInteger("blockData"));
/* 204 */     this.field_174931_f = EnumFacing.getFront(compound.getInteger("facing"));
/* 205 */     this.lastProgress = this.progress = compound.getFloat("progress");
/* 206 */     this.extending = compound.getBoolean("extending");
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/* 211 */     super.writeToNBT(compound);
/* 212 */     compound.setInteger("blockId", Block.getIdFromBlock(this.field_174932_a.getBlock()));
/* 213 */     compound.setInteger("blockData", this.field_174932_a.getBlock().getMetaFromState(this.field_174932_a));
/* 214 */     compound.setInteger("facing", this.field_174931_f.getIndex());
/* 215 */     compound.setFloat("progress", this.lastProgress);
/* 216 */     compound.setBoolean("extending", this.extending);
/*     */   }
/*     */   
/*     */   static final class SwitchAxis
/*     */   {
/* 221 */     static final int[] field_177248_a = new int[(EnumFacing.Axis.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002034";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 228 */         field_177248_a[EnumFacing.Axis.X.ordinal()] = 1;
/*     */       }
/* 230 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 237 */         field_177248_a[EnumFacing.Axis.Y.ordinal()] = 2;
/*     */       }
/* 239 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 246 */         field_177248_a[EnumFacing.Axis.Z.ordinal()] = 3;
/*     */       }
/* 248 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityPiston.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */