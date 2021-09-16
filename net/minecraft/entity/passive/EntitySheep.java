/*     */ package net.minecraft.entity.passive;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIEatGrass;
/*     */ import net.minecraft.entity.ai.EntityAIFollowParent;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMate;
/*     */ import net.minecraft.entity.ai.EntityAIPanic;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySheep
/*     */   extends EntityAnimal {
/*  42 */   private final InventoryCrafting inventoryCrafting = new InventoryCrafting(new Container()
/*     */       {
/*     */         private static final String __OBFID = "CL_00001649";
/*     */         
/*     */         public boolean canInteractWith(EntityPlayer playerIn) {
/*  47 */           return false;
/*     */         }
/*  49 */       },  2, 1);
/*  50 */   private static final Map field_175514_bm = Maps.newEnumMap(EnumDyeColor.class);
/*     */ 
/*     */ 
/*     */   
/*     */   private int sheepTimer;
/*     */ 
/*     */   
/*  57 */   private EntityAIEatGrass entityAIEatGrass = new EntityAIEatGrass((EntityLiving)this);
/*     */   
/*     */   private static final String __OBFID = "CL_00001648";
/*     */   
/*     */   public static float[] func_175513_a(EnumDyeColor p_175513_0_) {
/*  62 */     return (float[])field_175514_bm.get(p_175513_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySheep(World worldIn) {
/*  67 */     super(worldIn);
/*  68 */     setSize(0.9F, 1.3F);
/*  69 */     ((PathNavigateGround)getNavigator()).func_179690_a(true);
/*  70 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  71 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.25D));
/*  72 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIMate(this, 1.0D));
/*  73 */     this.tasks.addTask(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.1D, Items.wheat, false));
/*  74 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowParent(this, 1.1D));
/*  75 */     this.tasks.addTask(5, (EntityAIBase)this.entityAIEatGrass);
/*  76 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
/*  77 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*  78 */     this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  79 */     this.inventoryCrafting.setInventorySlotContents(0, new ItemStack(Items.dye, 1, 0));
/*  80 */     this.inventoryCrafting.setInventorySlotContents(1, new ItemStack(Items.dye, 1, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/*  85 */     this.sheepTimer = this.entityAIEatGrass.getEatingGrassTimer();
/*  86 */     super.updateAITasks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  95 */     if (this.worldObj.isRemote)
/*     */     {
/*  97 */       this.sheepTimer = Math.max(0, this.sheepTimer - 1);
/*     */     }
/*     */     
/* 100 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 105 */     super.applyEntityAttributes();
/* 106 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
/* 107 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 112 */     super.entityInit();
/* 113 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 121 */     if (!getSheared())
/*     */     {
/* 123 */       entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, func_175509_cj().func_176765_a()), 0.0F);
/*     */     }
/*     */     
/* 126 */     int var3 = this.rand.nextInt(2) + 1 + this.rand.nextInt(1 + p_70628_2_);
/*     */     
/* 128 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 130 */       if (isBurning()) {
/*     */         
/* 132 */         dropItem(Items.cooked_mutton, 1);
/*     */       }
/*     */       else {
/*     */         
/* 136 */         dropItem(Items.mutton, 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 143 */     return Item.getItemFromBlock(Blocks.wool);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 148 */     if (p_70103_1_ == 10) {
/*     */       
/* 150 */       this.sheepTimer = 40;
/*     */     }
/*     */     else {
/*     */       
/* 154 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadRotationPointY(float p_70894_1_) {
/* 160 */     return (this.sheepTimer <= 0) ? 0.0F : ((this.sheepTimer >= 4 && this.sheepTimer <= 36) ? 1.0F : ((this.sheepTimer < 4) ? ((this.sheepTimer - p_70894_1_) / 4.0F) : (-((this.sheepTimer - 40) - p_70894_1_) / 4.0F)));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHeadRotationAngleX(float p_70890_1_) {
/* 165 */     if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
/*     */       
/* 167 */       float var2 = ((this.sheepTimer - 4) - p_70890_1_) / 32.0F;
/* 168 */       return 0.62831855F + 0.2199115F * MathHelper.sin(var2 * 28.7F);
/*     */     } 
/*     */ 
/*     */     
/* 172 */     return (this.sheepTimer > 0) ? 0.62831855F : (this.rotationPitch / 57.295776F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(EntityPlayer p_70085_1_) {
/* 181 */     ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
/*     */     
/* 183 */     if (var2 != null && var2.getItem() == Items.shears && !getSheared() && !isChild()) {
/*     */       
/* 185 */       if (!this.worldObj.isRemote) {
/*     */         
/* 187 */         setSheared(true);
/* 188 */         int var3 = 1 + this.rand.nextInt(3);
/*     */         
/* 190 */         for (int var4 = 0; var4 < var3; var4++) {
/*     */           
/* 192 */           EntityItem var5 = entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, func_175509_cj().func_176765_a()), 1.0F);
/* 193 */           var5.motionY += (this.rand.nextFloat() * 0.05F);
/* 194 */           var5.motionX += ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
/* 195 */           var5.motionZ += ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
/*     */         } 
/*     */       } 
/*     */       
/* 199 */       var2.damageItem(1, (EntityLivingBase)p_70085_1_);
/* 200 */       playSound("mob.sheep.shear", 1.0F, 1.0F);
/*     */     } 
/*     */     
/* 203 */     return super.interact(p_70085_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 211 */     super.writeEntityToNBT(tagCompound);
/* 212 */     tagCompound.setBoolean("Sheared", getSheared());
/* 213 */     tagCompound.setByte("Color", (byte)func_175509_cj().func_176765_a());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 221 */     super.readEntityFromNBT(tagCompund);
/* 222 */     setSheared(tagCompund.getBoolean("Sheared"));
/* 223 */     func_175512_b(EnumDyeColor.func_176764_b(tagCompund.getByte("Color")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 231 */     return "mob.sheep.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 239 */     return "mob.sheep.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 247 */     return "mob.sheep.say";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/* 252 */     playSound("mob.sheep.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDyeColor func_175509_cj() {
/* 257 */     return EnumDyeColor.func_176764_b(this.dataWatcher.getWatchableObjectByte(16) & 0xF);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175512_b(EnumDyeColor p_175512_1_) {
/* 262 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/* 263 */     this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xF0 | p_175512_1_.func_176765_a() & 0xF)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getSheared() {
/* 271 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x10) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSheared(boolean p_70893_1_) {
/* 279 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 281 */     if (p_70893_1_) {
/*     */       
/* 283 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x10)));
/*     */     }
/*     */     else {
/*     */       
/* 287 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFEF)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumDyeColor func_175510_a(Random p_175510_0_) {
/* 293 */     int var1 = p_175510_0_.nextInt(100);
/* 294 */     return (var1 < 5) ? EnumDyeColor.BLACK : ((var1 < 10) ? EnumDyeColor.GRAY : ((var1 < 15) ? EnumDyeColor.SILVER : ((var1 < 18) ? EnumDyeColor.BROWN : ((p_175510_0_.nextInt(500) == 0) ? EnumDyeColor.PINK : EnumDyeColor.WHITE))));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySheep func_180491_b(EntityAgeable p_180491_1_) {
/* 299 */     EntitySheep var2 = (EntitySheep)p_180491_1_;
/* 300 */     EntitySheep var3 = new EntitySheep(this.worldObj);
/* 301 */     var3.func_175512_b(func_175511_a(this, var2));
/* 302 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void eatGrassBonus() {
/* 311 */     setSheared(false);
/*     */     
/* 313 */     if (isChild())
/*     */     {
/* 315 */       addGrowth(60);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 321 */     p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
/* 322 */     func_175512_b(func_175510_a(this.worldObj.rand));
/* 323 */     return p_180482_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   private EnumDyeColor func_175511_a(EntityAnimal p_175511_1_, EntityAnimal p_175511_2_) {
/* 328 */     int var6, var3 = ((EntitySheep)p_175511_1_).func_175509_cj().getDyeColorDamage();
/* 329 */     int var4 = ((EntitySheep)p_175511_2_).func_175509_cj().getDyeColorDamage();
/* 330 */     this.inventoryCrafting.getStackInSlot(0).setItemDamage(var3);
/* 331 */     this.inventoryCrafting.getStackInSlot(1).setItemDamage(var4);
/* 332 */     ItemStack var5 = CraftingManager.getInstance().findMatchingRecipe(this.inventoryCrafting, ((EntitySheep)p_175511_1_).worldObj);
/*     */ 
/*     */     
/* 335 */     if (var5 != null && var5.getItem() == Items.dye) {
/*     */       
/* 337 */       var6 = var5.getMetadata();
/*     */     }
/*     */     else {
/*     */       
/* 341 */       var6 = this.worldObj.rand.nextBoolean() ? var3 : var4;
/*     */     } 
/*     */     
/* 344 */     return EnumDyeColor.func_176766_a(var6);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 349 */     return 0.95F * this.height;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable p_90011_1_) {
/* 354 */     return func_180491_b(p_90011_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 359 */     field_175514_bm.put(EnumDyeColor.WHITE, new float[] { 1.0F, 1.0F, 1.0F });
/* 360 */     field_175514_bm.put(EnumDyeColor.ORANGE, new float[] { 0.85F, 0.5F, 0.2F });
/* 361 */     field_175514_bm.put(EnumDyeColor.MAGENTA, new float[] { 0.7F, 0.3F, 0.85F });
/* 362 */     field_175514_bm.put(EnumDyeColor.LIGHT_BLUE, new float[] { 0.4F, 0.6F, 0.85F });
/* 363 */     field_175514_bm.put(EnumDyeColor.YELLOW, new float[] { 0.9F, 0.9F, 0.2F });
/* 364 */     field_175514_bm.put(EnumDyeColor.LIME, new float[] { 0.5F, 0.8F, 0.1F });
/* 365 */     field_175514_bm.put(EnumDyeColor.PINK, new float[] { 0.95F, 0.5F, 0.65F });
/* 366 */     field_175514_bm.put(EnumDyeColor.GRAY, new float[] { 0.3F, 0.3F, 0.3F });
/* 367 */     field_175514_bm.put(EnumDyeColor.SILVER, new float[] { 0.6F, 0.6F, 0.6F });
/* 368 */     field_175514_bm.put(EnumDyeColor.CYAN, new float[] { 0.3F, 0.5F, 0.6F });
/* 369 */     field_175514_bm.put(EnumDyeColor.PURPLE, new float[] { 0.5F, 0.25F, 0.7F });
/* 370 */     field_175514_bm.put(EnumDyeColor.BLUE, new float[] { 0.2F, 0.3F, 0.7F });
/* 371 */     field_175514_bm.put(EnumDyeColor.BROWN, new float[] { 0.4F, 0.3F, 0.2F });
/* 372 */     field_175514_bm.put(EnumDyeColor.GREEN, new float[] { 0.4F, 0.5F, 0.2F });
/* 373 */     field_175514_bm.put(EnumDyeColor.RED, new float[] { 0.6F, 0.2F, 0.2F });
/* 374 */     field_175514_bm.put(EnumDyeColor.BLACK, new float[] { 0.1F, 0.1F, 0.1F });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntitySheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */