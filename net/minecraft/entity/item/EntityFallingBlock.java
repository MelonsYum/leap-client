/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockAnvil;
/*     */ import net.minecraft.block.BlockFalling;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityFallingBlock
/*     */   extends Entity {
/*     */   private IBlockState field_175132_d;
/*     */   public int fallTime;
/*     */   public boolean shouldDropItem = true;
/*     */   private boolean field_145808_f;
/*     */   private boolean hurtEntities;
/*  33 */   private int fallHurtMax = 40;
/*  34 */   private float fallHurtAmount = 2.0F;
/*     */   
/*     */   public NBTTagCompound tileEntityData;
/*     */   private static final String __OBFID = "CL_00001668";
/*     */   
/*     */   public EntityFallingBlock(World worldIn) {
/*  40 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFallingBlock(World worldIn, double p_i45848_2_, double p_i45848_4_, double p_i45848_6_, IBlockState p_i45848_8_) {
/*  45 */     super(worldIn);
/*  46 */     this.field_175132_d = p_i45848_8_;
/*  47 */     this.preventEntitySpawning = true;
/*  48 */     setSize(0.98F, 0.98F);
/*  49 */     setPosition(p_i45848_2_, p_i45848_4_, p_i45848_6_);
/*  50 */     this.motionX = 0.0D;
/*  51 */     this.motionY = 0.0D;
/*  52 */     this.motionZ = 0.0D;
/*  53 */     this.prevPosX = p_i45848_2_;
/*  54 */     this.prevPosY = p_i45848_4_;
/*  55 */     this.prevPosZ = p_i45848_6_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/*  74 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  82 */     Block var1 = this.field_175132_d.getBlock();
/*     */     
/*  84 */     if (var1.getMaterial() == Material.air) {
/*     */       
/*  86 */       setDead();
/*     */     }
/*     */     else {
/*     */       
/*  90 */       this.prevPosX = this.posX;
/*  91 */       this.prevPosY = this.posY;
/*  92 */       this.prevPosZ = this.posZ;
/*     */ 
/*     */       
/*  95 */       if (this.fallTime++ == 0) {
/*     */         
/*  97 */         BlockPos var2 = new BlockPos(this);
/*     */         
/*  99 */         if (this.worldObj.getBlockState(var2).getBlock() == var1) {
/*     */           
/* 101 */           this.worldObj.setBlockToAir(var2);
/*     */         }
/* 103 */         else if (!this.worldObj.isRemote) {
/*     */           
/* 105 */           setDead();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 110 */       this.motionY -= 0.03999999910593033D;
/* 111 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 112 */       this.motionX *= 0.9800000190734863D;
/* 113 */       this.motionY *= 0.9800000190734863D;
/* 114 */       this.motionZ *= 0.9800000190734863D;
/*     */       
/* 116 */       if (!this.worldObj.isRemote) {
/*     */         
/* 118 */         BlockPos var2 = new BlockPos(this);
/*     */         
/* 120 */         if (this.onGround) {
/*     */           
/* 122 */           this.motionX *= 0.699999988079071D;
/* 123 */           this.motionZ *= 0.699999988079071D;
/* 124 */           this.motionY *= -0.5D;
/*     */           
/* 126 */           if (this.worldObj.getBlockState(var2).getBlock() != Blocks.piston_extension) {
/*     */             
/* 128 */             setDead();
/*     */             
/* 130 */             if (!this.field_145808_f && this.worldObj.canBlockBePlaced(var1, var2, true, EnumFacing.UP, null, null) && !BlockFalling.canFallInto(this.worldObj, var2.offsetDown()) && this.worldObj.setBlockState(var2, this.field_175132_d, 3)) {
/*     */               
/* 132 */               if (var1 instanceof BlockFalling)
/*     */               {
/* 134 */                 ((BlockFalling)var1).onEndFalling(this.worldObj, var2);
/*     */               }
/*     */               
/* 137 */               if (this.tileEntityData != null && var1 instanceof net.minecraft.block.ITileEntityProvider) {
/*     */                 
/* 139 */                 TileEntity var3 = this.worldObj.getTileEntity(var2);
/*     */                 
/* 141 */                 if (var3 != null)
/*     */                 {
/* 143 */                   NBTTagCompound var4 = new NBTTagCompound();
/* 144 */                   var3.writeToNBT(var4);
/* 145 */                   Iterator<String> var5 = this.tileEntityData.getKeySet().iterator();
/*     */                   
/* 147 */                   while (var5.hasNext()) {
/*     */                     
/* 149 */                     String var6 = var5.next();
/* 150 */                     NBTBase var7 = this.tileEntityData.getTag(var6);
/*     */                     
/* 152 */                     if (!var6.equals("x") && !var6.equals("y") && !var6.equals("z"))
/*     */                     {
/* 154 */                       var4.setTag(var6, var7.copy());
/*     */                     }
/*     */                   } 
/*     */                   
/* 158 */                   var3.readFromNBT(var4);
/* 159 */                   var3.markDirty();
/*     */                 }
/*     */               
/*     */               } 
/* 163 */             } else if (this.shouldDropItem && !this.field_145808_f && this.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
/*     */               
/* 165 */               entityDropItem(new ItemStack(var1, 1, var1.damageDropped(this.field_175132_d)), 0.0F);
/*     */             }
/*     */           
/*     */           } 
/* 169 */         } else if ((this.fallTime > 100 && !this.worldObj.isRemote && (var2.getY() < 1 || var2.getY() > 256)) || this.fallTime > 600) {
/*     */           
/* 171 */           if (this.shouldDropItem && this.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops"))
/*     */           {
/* 173 */             entityDropItem(new ItemStack(var1, 1, var1.damageDropped(this.field_175132_d)), 0.0F);
/*     */           }
/*     */           
/* 176 */           setDead();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void fall(float distance, float damageMultiplier) {
/* 184 */     Block var3 = this.field_175132_d.getBlock();
/*     */     
/* 186 */     if (this.hurtEntities) {
/*     */       
/* 188 */       int var4 = MathHelper.ceiling_float_int(distance - 1.0F);
/*     */       
/* 190 */       if (var4 > 0) {
/*     */         
/* 192 */         ArrayList var5 = Lists.newArrayList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox()));
/* 193 */         boolean var6 = (var3 == Blocks.anvil);
/* 194 */         DamageSource var7 = var6 ? DamageSource.anvil : DamageSource.fallingBlock;
/* 195 */         Iterator<Entity> var8 = var5.iterator();
/*     */         
/* 197 */         while (var8.hasNext()) {
/*     */           
/* 199 */           Entity var9 = var8.next();
/* 200 */           var9.attackEntityFrom(var7, Math.min(MathHelper.floor_float(var4 * this.fallHurtAmount), this.fallHurtMax));
/*     */         } 
/*     */         
/* 203 */         if (var6 && this.rand.nextFloat() < 0.05000000074505806D + var4 * 0.05D) {
/*     */           
/* 205 */           int var10 = ((Integer)this.field_175132_d.getValue((IProperty)BlockAnvil.DAMAGE)).intValue();
/* 206 */           var10++;
/*     */           
/* 208 */           if (var10 > 2) {
/*     */             
/* 210 */             this.field_145808_f = true;
/*     */           }
/*     */           else {
/*     */             
/* 214 */             this.field_175132_d = this.field_175132_d.withProperty((IProperty)BlockAnvil.DAMAGE, Integer.valueOf(var10));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 226 */     Block var2 = (this.field_175132_d != null) ? this.field_175132_d.getBlock() : Blocks.air;
/* 227 */     ResourceLocation var3 = (ResourceLocation)Block.blockRegistry.getNameForObject(var2);
/* 228 */     tagCompound.setString("Block", (var3 == null) ? "" : var3.toString());
/* 229 */     tagCompound.setByte("Data", (byte)var2.getMetaFromState(this.field_175132_d));
/* 230 */     tagCompound.setByte("Time", (byte)this.fallTime);
/* 231 */     tagCompound.setBoolean("DropItem", this.shouldDropItem);
/* 232 */     tagCompound.setBoolean("HurtEntities", this.hurtEntities);
/* 233 */     tagCompound.setFloat("FallHurtAmount", this.fallHurtAmount);
/* 234 */     tagCompound.setInteger("FallHurtMax", this.fallHurtMax);
/*     */     
/* 236 */     if (this.tileEntityData != null)
/*     */     {
/* 238 */       tagCompound.setTag("TileEntityData", (NBTBase)this.tileEntityData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 247 */     int var2 = tagCompund.getByte("Data") & 0xFF;
/*     */     
/* 249 */     if (tagCompund.hasKey("Block", 8)) {
/*     */       
/* 251 */       this.field_175132_d = Block.getBlockFromName(tagCompund.getString("Block")).getStateFromMeta(var2);
/*     */     }
/* 253 */     else if (tagCompund.hasKey("TileID", 99)) {
/*     */       
/* 255 */       this.field_175132_d = Block.getBlockById(tagCompund.getInteger("TileID")).getStateFromMeta(var2);
/*     */     }
/*     */     else {
/*     */       
/* 259 */       this.field_175132_d = Block.getBlockById(tagCompund.getByte("Tile") & 0xFF).getStateFromMeta(var2);
/*     */     } 
/*     */     
/* 262 */     this.fallTime = tagCompund.getByte("Time") & 0xFF;
/* 263 */     Block var3 = this.field_175132_d.getBlock();
/*     */     
/* 265 */     if (tagCompund.hasKey("HurtEntities", 99)) {
/*     */       
/* 267 */       this.hurtEntities = tagCompund.getBoolean("HurtEntities");
/* 268 */       this.fallHurtAmount = tagCompund.getFloat("FallHurtAmount");
/* 269 */       this.fallHurtMax = tagCompund.getInteger("FallHurtMax");
/*     */     }
/* 271 */     else if (var3 == Blocks.anvil) {
/*     */       
/* 273 */       this.hurtEntities = true;
/*     */     } 
/*     */     
/* 276 */     if (tagCompund.hasKey("DropItem", 99))
/*     */     {
/* 278 */       this.shouldDropItem = tagCompund.getBoolean("DropItem");
/*     */     }
/*     */     
/* 281 */     if (tagCompund.hasKey("TileEntityData", 10))
/*     */     {
/* 283 */       this.tileEntityData = tagCompund.getCompoundTag("TileEntityData");
/*     */     }
/*     */     
/* 286 */     if (var3 == null || var3.getMaterial() == Material.air)
/*     */     {
/* 288 */       this.field_175132_d = Blocks.sand.getDefaultState();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorldObj() {
/* 294 */     return this.worldObj;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHurtEntities(boolean p_145806_1_) {
/* 299 */     this.hurtEntities = p_145806_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRenderOnFire() {
/* 307 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEntityCrashInfo(CrashReportCategory category) {
/* 312 */     super.addEntityCrashInfo(category);
/*     */     
/* 314 */     if (this.field_175132_d != null) {
/*     */       
/* 316 */       Block var2 = this.field_175132_d.getBlock();
/* 317 */       category.addCrashSection("Immitating block ID", Integer.valueOf(Block.getIdFromBlock(var2)));
/* 318 */       category.addCrashSection("Immitating block data", Integer.valueOf(var2.getMetaFromState(this.field_175132_d)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState getBlock() {
/* 324 */     return this.field_175132_d;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */