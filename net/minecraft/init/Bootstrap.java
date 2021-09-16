/*     */ package net.minecraft.init;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockCommandBlock;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.block.BlockFire;
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.block.BlockPumpkin;
/*     */ import net.minecraft.block.BlockSkull;
/*     */ import net.minecraft.block.BlockTNT;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
/*     */ import net.minecraft.dispenser.BehaviorProjectileDispense;
/*     */ import net.minecraft.dispenser.IBehaviorDispenseItem;
/*     */ import net.minecraft.dispenser.IBlockSource;
/*     */ import net.minecraft.dispenser.IPosition;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.IProjectile;
/*     */ import net.minecraft.entity.item.EntityBoat;
/*     */ import net.minecraft.entity.item.EntityExpBottle;
/*     */ import net.minecraft.entity.item.EntityFireworkRocket;
/*     */ import net.minecraft.entity.item.EntityTNTPrimed;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.entity.projectile.EntityEgg;
/*     */ import net.minecraft.entity.projectile.EntityPotion;
/*     */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*     */ import net.minecraft.entity.projectile.EntitySnowball;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemBucket;
/*     */ import net.minecraft.item.ItemDye;
/*     */ import net.minecraft.item.ItemMonsterPlacer;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityDispenser;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.LoggingPrintStream;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class Bootstrap
/*     */ {
/*  58 */   private static final PrintStream SYSOUT = System.out;
/*     */   
/*     */   private static boolean alreadyRegistered = false;
/*     */   
/*  62 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001397";
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isRegistered() {
/*  70 */     return alreadyRegistered;
/*     */   }
/*     */ 
/*     */   
/*     */   static void registerDispenserBehaviors() {
/*  75 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.arrow, new BehaviorProjectileDispense()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001398";
/*     */           
/*     */           protected IProjectile getProjectileEntity(World worldIn, IPosition position) {
/*  80 */             EntityArrow var3 = new EntityArrow(worldIn, position.getX(), position.getY(), position.getZ());
/*  81 */             var3.canBePickedUp = 1;
/*  82 */             return (IProjectile)var3;
/*     */           }
/*     */         });
/*  85 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.egg, new BehaviorProjectileDispense()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001404";
/*     */           
/*     */           protected IProjectile getProjectileEntity(World worldIn, IPosition position) {
/*  90 */             return (IProjectile)new EntityEgg(worldIn, position.getX(), position.getY(), position.getZ());
/*     */           }
/*     */         });
/*  93 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.snowball, new BehaviorProjectileDispense()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001405";
/*     */           
/*     */           protected IProjectile getProjectileEntity(World worldIn, IPosition position) {
/*  98 */             return (IProjectile)new EntitySnowball(worldIn, position.getX(), position.getY(), position.getZ());
/*     */           }
/*     */         });
/* 101 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.experience_bottle, new BehaviorProjectileDispense()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001406";
/*     */           
/*     */           protected IProjectile getProjectileEntity(World worldIn, IPosition position) {
/* 106 */             return (IProjectile)new EntityExpBottle(worldIn, position.getX(), position.getY(), position.getZ());
/*     */           }
/*     */           
/*     */           protected float func_82498_a() {
/* 110 */             return super.func_82498_a() * 0.5F;
/*     */           }
/*     */           
/*     */           protected float func_82500_b() {
/* 114 */             return super.func_82500_b() * 1.25F;
/*     */           }
/*     */         });
/* 117 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.potionitem, new IBehaviorDispenseItem()
/*     */         {
/* 119 */           private final BehaviorDefaultDispenseItem field_150843_b = new BehaviorDefaultDispenseItem();
/*     */           
/*     */           private static final String __OBFID = "CL_00001407";
/*     */           public ItemStack dispense(IBlockSource source, final ItemStack stack) {
/* 123 */             return ItemPotion.isSplash(stack.getMetadata()) ? (new BehaviorProjectileDispense()
/*     */               {
/*     */                 private static final String __OBFID = "CL_00001408";
/*     */                 
/*     */                 protected IProjectile getProjectileEntity(World worldIn, IPosition position) {
/* 128 */                   return (IProjectile)new EntityPotion(worldIn, position.getX(), position.getY(), position.getZ(), stack.copy());
/*     */                 }
/*     */                 
/*     */                 protected float func_82498_a() {
/* 132 */                   return super.func_82498_a() * 0.5F;
/*     */                 }
/*     */                 
/*     */                 protected float func_82500_b() {
/* 136 */                   return super.func_82500_b() * 1.25F;
/*     */                 }
/* 138 */               }).dispense(source, stack) : this.field_150843_b.dispense(source, stack);
/*     */           }
/*     */         });
/* 141 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.spawn_egg, new BehaviorDefaultDispenseItem()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001410";
/*     */           
/*     */           public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 146 */             EnumFacing var3 = BlockDispenser.getFacing(source.getBlockMetadata());
/* 147 */             double var4 = source.getX() + var3.getFrontOffsetX();
/* 148 */             double var6 = (source.getBlockPos().getY() + 0.2F);
/* 149 */             double var8 = source.getZ() + var3.getFrontOffsetZ();
/* 150 */             Entity var10 = ItemMonsterPlacer.spawnCreature(source.getWorld(), stack.getMetadata(), var4, var6, var8);
/*     */             
/* 152 */             if (var10 instanceof net.minecraft.entity.EntityLivingBase && stack.hasDisplayName())
/*     */             {
/* 154 */               ((EntityLiving)var10).setCustomNameTag(stack.getDisplayName());
/*     */             }
/*     */             
/* 157 */             stack.splitStack(1);
/* 158 */             return stack;
/*     */           }
/*     */         });
/* 161 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.fireworks, new BehaviorDefaultDispenseItem()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001411";
/*     */           
/*     */           public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 166 */             EnumFacing var3 = BlockDispenser.getFacing(source.getBlockMetadata());
/* 167 */             double var4 = source.getX() + var3.getFrontOffsetX();
/* 168 */             double var6 = (source.getBlockPos().getY() + 0.2F);
/* 169 */             double var8 = source.getZ() + var3.getFrontOffsetZ();
/* 170 */             EntityFireworkRocket var10 = new EntityFireworkRocket(source.getWorld(), var4, var6, var8, stack);
/* 171 */             source.getWorld().spawnEntityInWorld((Entity)var10);
/* 172 */             stack.splitStack(1);
/* 173 */             return stack;
/*     */           }
/*     */           
/*     */           protected void playDispenseSound(IBlockSource source) {
/* 177 */             source.getWorld().playAuxSFX(1002, source.getBlockPos(), 0);
/*     */           }
/*     */         });
/* 180 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.fire_charge, new BehaviorDefaultDispenseItem()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001412";
/*     */           
/*     */           public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 185 */             EnumFacing var3 = BlockDispenser.getFacing(source.getBlockMetadata());
/* 186 */             IPosition var4 = BlockDispenser.getDispensePosition(source);
/* 187 */             double var5 = var4.getX() + (var3.getFrontOffsetX() * 0.3F);
/* 188 */             double var7 = var4.getY() + (var3.getFrontOffsetX() * 0.3F);
/* 189 */             double var9 = var4.getZ() + (var3.getFrontOffsetZ() * 0.3F);
/* 190 */             World var11 = source.getWorld();
/* 191 */             Random var12 = var11.rand;
/* 192 */             double var13 = var12.nextGaussian() * 0.05D + var3.getFrontOffsetX();
/* 193 */             double var15 = var12.nextGaussian() * 0.05D + var3.getFrontOffsetY();
/* 194 */             double var17 = var12.nextGaussian() * 0.05D + var3.getFrontOffsetZ();
/* 195 */             var11.spawnEntityInWorld((Entity)new EntitySmallFireball(var11, var5, var7, var9, var13, var15, var17));
/* 196 */             stack.splitStack(1);
/* 197 */             return stack;
/*     */           }
/*     */           
/*     */           protected void playDispenseSound(IBlockSource source) {
/* 201 */             source.getWorld().playAuxSFX(1009, source.getBlockPos(), 0);
/*     */           }
/*     */         });
/* 204 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.boat, new BehaviorDefaultDispenseItem()
/*     */         {
/* 206 */           private final BehaviorDefaultDispenseItem field_150842_b = new BehaviorDefaultDispenseItem();
/*     */           
/*     */           public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/*     */             double var13;
/* 210 */             EnumFacing var3 = BlockDispenser.getFacing(source.getBlockMetadata());
/* 211 */             World var4 = source.getWorld();
/* 212 */             double var5 = source.getX() + (var3.getFrontOffsetX() * 1.125F);
/* 213 */             double var7 = source.getY() + (var3.getFrontOffsetY() * 1.125F);
/* 214 */             double var9 = source.getZ() + (var3.getFrontOffsetZ() * 1.125F);
/* 215 */             BlockPos var11 = source.getBlockPos().offset(var3);
/* 216 */             Material var12 = var4.getBlockState(var11).getBlock().getMaterial();
/*     */ 
/*     */             
/* 219 */             if (Material.water.equals(var12)) {
/*     */               
/* 221 */               var13 = 1.0D;
/*     */             }
/*     */             else {
/*     */               
/* 225 */               if (!Material.air.equals(var12) || !Material.water.equals(var4.getBlockState(var11.offsetDown()).getBlock().getMaterial()))
/*     */               {
/* 227 */                 return this.field_150842_b.dispense(source, stack);
/*     */               }
/*     */               
/* 230 */               var13 = 0.0D;
/*     */             } 
/*     */             
/* 233 */             EntityBoat var15 = new EntityBoat(var4, var5, var7 + var13, var9);
/* 234 */             var4.spawnEntityInWorld((Entity)var15);
/* 235 */             stack.splitStack(1);
/* 236 */             return stack;
/*     */           }
/*     */           private static final String __OBFID = "CL_00001413";
/*     */           protected void playDispenseSound(IBlockSource source) {
/* 240 */             source.getWorld().playAuxSFX(1000, source.getBlockPos(), 0);
/*     */           }
/*     */         });
/* 243 */     BehaviorDefaultDispenseItem var0 = new BehaviorDefaultDispenseItem()
/*     */       {
/* 245 */         private final BehaviorDefaultDispenseItem field_150841_b = new BehaviorDefaultDispenseItem();
/*     */         private static final String __OBFID = "CL_00001399";
/*     */         
/*     */         public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 249 */           ItemBucket var3 = (ItemBucket)stack.getItem();
/* 250 */           BlockPos var4 = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
/*     */           
/* 252 */           if (var3.func_180616_a(source.getWorld(), var4)) {
/*     */             
/* 254 */             stack.setItem(Items.bucket);
/* 255 */             stack.stackSize = 1;
/* 256 */             return stack;
/*     */           } 
/*     */ 
/*     */           
/* 260 */           return this.field_150841_b.dispense(source, stack);
/*     */         }
/*     */       };
/*     */     
/* 264 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.lava_bucket, var0);
/* 265 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.water_bucket, var0);
/* 266 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.bucket, new BehaviorDefaultDispenseItem()
/*     */         {
/* 268 */           private final BehaviorDefaultDispenseItem field_150840_b = new BehaviorDefaultDispenseItem(); private static final String __OBFID = "CL_00001400";
/*     */           
/*     */           public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/*     */             Item var8;
/* 272 */             World var3 = source.getWorld();
/* 273 */             BlockPos var4 = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
/* 274 */             IBlockState var5 = var3.getBlockState(var4);
/* 275 */             Block var6 = var5.getBlock();
/* 276 */             Material var7 = var6.getMaterial();
/*     */ 
/*     */             
/* 279 */             if (Material.water.equals(var7) && var6 instanceof BlockLiquid && ((Integer)var5.getValue((IProperty)BlockLiquid.LEVEL)).intValue() == 0) {
/*     */               
/* 281 */               var8 = Items.water_bucket;
/*     */             }
/*     */             else {
/*     */               
/* 285 */               if (!Material.lava.equals(var7) || !(var6 instanceof BlockLiquid) || ((Integer)var5.getValue((IProperty)BlockLiquid.LEVEL)).intValue() != 0)
/*     */               {
/* 287 */                 return super.dispenseStack(source, stack);
/*     */               }
/*     */               
/* 290 */               var8 = Items.lava_bucket;
/*     */             } 
/*     */             
/* 293 */             var3.setBlockToAir(var4);
/*     */             
/* 295 */             if (--stack.stackSize == 0) {
/*     */               
/* 297 */               stack.setItem(var8);
/* 298 */               stack.stackSize = 1;
/*     */             }
/* 300 */             else if (((TileEntityDispenser)source.getBlockTileEntity()).func_146019_a(new ItemStack(var8)) < 0) {
/*     */               
/* 302 */               this.field_150840_b.dispense(source, new ItemStack(var8));
/*     */             } 
/*     */             
/* 305 */             return stack;
/*     */           }
/*     */         });
/* 308 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.flint_and_steel, new BehaviorDefaultDispenseItem()
/*     */         {
/*     */           private boolean field_150839_b = true;
/*     */           private static final String __OBFID = "CL_00001401";
/*     */           
/*     */           protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 314 */             World var3 = source.getWorld();
/* 315 */             BlockPos var4 = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
/*     */             
/* 317 */             if (var3.isAirBlock(var4)) {
/*     */               
/* 319 */               var3.setBlockState(var4, Blocks.fire.getDefaultState());
/*     */               
/* 321 */               if (stack.attemptDamageItem(1, var3.rand))
/*     */               {
/* 323 */                 stack.stackSize = 0;
/*     */               }
/*     */             }
/* 326 */             else if (var3.getBlockState(var4).getBlock() == Blocks.tnt) {
/*     */               
/* 328 */               Blocks.tnt.onBlockDestroyedByPlayer(var3, var4, Blocks.tnt.getDefaultState().withProperty((IProperty)BlockTNT.field_176246_a, Boolean.valueOf(true)));
/* 329 */               var3.setBlockToAir(var4);
/*     */             }
/*     */             else {
/*     */               
/* 333 */               this.field_150839_b = false;
/*     */             } 
/*     */             
/* 336 */             return stack;
/*     */           }
/*     */           
/*     */           protected void playDispenseSound(IBlockSource source) {
/* 340 */             if (this.field_150839_b) {
/*     */               
/* 342 */               source.getWorld().playAuxSFX(1000, source.getBlockPos(), 0);
/*     */             }
/*     */             else {
/*     */               
/* 346 */               source.getWorld().playAuxSFX(1001, source.getBlockPos(), 0);
/*     */             } 
/*     */           }
/*     */         });
/* 350 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.dye, new BehaviorDefaultDispenseItem()
/*     */         {
/*     */           private boolean field_150838_b = true;
/*     */           private static final String __OBFID = "CL_00001402";
/*     */           
/*     */           protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 356 */             if (EnumDyeColor.WHITE == EnumDyeColor.func_176766_a(stack.getMetadata())) {
/*     */               
/* 358 */               World var3 = source.getWorld();
/* 359 */               BlockPos var4 = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
/*     */               
/* 361 */               if (ItemDye.func_179234_a(stack, var3, var4)) {
/*     */                 
/* 363 */                 if (!var3.isRemote)
/*     */                 {
/* 365 */                   var3.playAuxSFX(2005, var4, 0);
/*     */                 }
/*     */               }
/*     */               else {
/*     */                 
/* 370 */                 this.field_150838_b = false;
/*     */               } 
/*     */               
/* 373 */               return stack;
/*     */             } 
/*     */ 
/*     */             
/* 377 */             return super.dispenseStack(source, stack);
/*     */           }
/*     */ 
/*     */           
/*     */           protected void playDispenseSound(IBlockSource source) {
/* 382 */             if (this.field_150838_b) {
/*     */               
/* 384 */               source.getWorld().playAuxSFX(1000, source.getBlockPos(), 0);
/*     */             }
/*     */             else {
/*     */               
/* 388 */               source.getWorld().playAuxSFX(1001, source.getBlockPos(), 0);
/*     */             } 
/*     */           }
/*     */         });
/* 392 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(Blocks.tnt), new BehaviorDefaultDispenseItem()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001403";
/*     */           
/*     */           protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 397 */             World var3 = source.getWorld();
/* 398 */             BlockPos var4 = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
/* 399 */             EntityTNTPrimed var5 = new EntityTNTPrimed(var3, var4.getX() + 0.5D, var4.getY(), var4.getZ() + 0.5D, null);
/* 400 */             var3.spawnEntityInWorld((Entity)var5);
/* 401 */             var3.playSoundAtEntity((Entity)var5, "game.tnt.primed", 1.0F, 1.0F);
/* 402 */             stack.stackSize--;
/* 403 */             return stack;
/*     */           }
/*     */         });
/* 406 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Items.skull, new BehaviorDefaultDispenseItem()
/*     */         {
/*     */           private boolean field_179240_b = true;
/*     */           private static final String __OBFID = "CL_00002278";
/*     */           
/*     */           protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 412 */             World var3 = source.getWorld();
/* 413 */             EnumFacing var4 = BlockDispenser.getFacing(source.getBlockMetadata());
/* 414 */             BlockPos var5 = source.getBlockPos().offset(var4);
/* 415 */             BlockSkull var6 = Blocks.skull;
/*     */             
/* 417 */             if (var3.isAirBlock(var5) && var6.func_176415_b(var3, var5, stack)) {
/*     */               
/* 419 */               if (!var3.isRemote)
/*     */               {
/* 421 */                 var3.setBlockState(var5, var6.getDefaultState().withProperty((IProperty)BlockSkull.field_176418_a, (Comparable)EnumFacing.UP), 3);
/* 422 */                 TileEntity var7 = var3.getTileEntity(var5);
/*     */                 
/* 424 */                 if (var7 instanceof TileEntitySkull) {
/*     */                   
/* 426 */                   if (stack.getMetadata() == 3) {
/*     */                     
/* 428 */                     GameProfile var8 = null;
/*     */                     
/* 430 */                     if (stack.hasTagCompound()) {
/*     */                       
/* 432 */                       NBTTagCompound var9 = stack.getTagCompound();
/*     */                       
/* 434 */                       if (var9.hasKey("SkullOwner", 10)) {
/*     */                         
/* 436 */                         var8 = NBTUtil.readGameProfileFromNBT(var9.getCompoundTag("SkullOwner"));
/*     */                       }
/* 438 */                       else if (var9.hasKey("SkullOwner", 8)) {
/*     */                         
/* 440 */                         var8 = new GameProfile(null, var9.getString("SkullOwner"));
/*     */                       } 
/*     */                     } 
/*     */                     
/* 444 */                     ((TileEntitySkull)var7).setPlayerProfile(var8);
/*     */                   }
/*     */                   else {
/*     */                     
/* 448 */                     ((TileEntitySkull)var7).setType(stack.getMetadata());
/*     */                   } 
/*     */                   
/* 451 */                   ((TileEntitySkull)var7).setSkullRotation(var4.getOpposite().getHorizontalIndex() * 4);
/* 452 */                   Blocks.skull.func_180679_a(var3, var5, (TileEntitySkull)var7);
/*     */                 } 
/*     */                 
/* 455 */                 stack.stackSize--;
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 460 */               this.field_179240_b = false;
/*     */             } 
/*     */             
/* 463 */             return stack;
/*     */           }
/*     */           
/*     */           protected void playDispenseSound(IBlockSource source) {
/* 467 */             if (this.field_179240_b) {
/*     */               
/* 469 */               source.getWorld().playAuxSFX(1000, source.getBlockPos(), 0);
/*     */             }
/*     */             else {
/*     */               
/* 473 */               source.getWorld().playAuxSFX(1001, source.getBlockPos(), 0);
/*     */             } 
/*     */           }
/*     */         });
/* 477 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(Blocks.pumpkin), new BehaviorDefaultDispenseItem()
/*     */         {
/*     */           private boolean field_179241_b = true;
/*     */           private static final String __OBFID = "CL_00002277";
/*     */           
/*     */           protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 483 */             World var3 = source.getWorld();
/* 484 */             BlockPos var4 = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
/* 485 */             BlockPumpkin var5 = (BlockPumpkin)Blocks.pumpkin;
/*     */             
/* 487 */             if (var3.isAirBlock(var4) && var5.func_176390_d(var3, var4)) {
/*     */               
/* 489 */               if (!var3.isRemote)
/*     */               {
/* 491 */                 var3.setBlockState(var4, var5.getDefaultState(), 3);
/*     */               }
/*     */               
/* 494 */               stack.stackSize--;
/*     */             }
/*     */             else {
/*     */               
/* 498 */               this.field_179241_b = false;
/*     */             } 
/*     */             
/* 501 */             return stack;
/*     */           }
/*     */           
/*     */           protected void playDispenseSound(IBlockSource source) {
/* 505 */             if (this.field_179241_b) {
/*     */               
/* 507 */               source.getWorld().playAuxSFX(1000, source.getBlockPos(), 0);
/*     */             }
/*     */             else {
/*     */               
/* 511 */               source.getWorld().playAuxSFX(1001, source.getBlockPos(), 0);
/*     */             } 
/*     */           }
/*     */         });
/* 515 */     BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(Blocks.command_block), new BehaviorDefaultDispenseItem()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002276";
/*     */           
/*     */           protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/* 520 */             World var3 = source.getWorld();
/* 521 */             BlockPos var4 = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
/*     */             
/* 523 */             if (var3.isAirBlock(var4)) {
/*     */               
/* 525 */               if (!var3.isRemote) {
/*     */                 
/* 527 */                 IBlockState var5 = Blocks.command_block.getDefaultState().withProperty((IProperty)BlockCommandBlock.TRIGGERED_PROP, Boolean.valueOf(false));
/* 528 */                 var3.setBlockState(var4, var5, 3);
/* 529 */                 ItemBlock.setTileEntityNBT(var3, var4, stack);
/* 530 */                 var3.notifyNeighborsOfStateChange(source.getBlockPos(), source.getBlock());
/*     */               } 
/*     */               
/* 533 */               stack.stackSize--;
/*     */             } 
/*     */             
/* 536 */             return stack;
/*     */           }
/*     */ 
/*     */           
/*     */           protected void playDispenseSound(IBlockSource source) {}
/*     */ 
/*     */           
/*     */           protected void spawnDispenseParticles(IBlockSource source, EnumFacing facingIn) {}
/*     */         });
/*     */   }
/*     */   
/*     */   public static void register() {
/* 548 */     if (!alreadyRegistered) {
/*     */       
/* 550 */       alreadyRegistered = true;
/*     */       
/* 552 */       if (LOGGER.isDebugEnabled())
/*     */       {
/* 554 */         redirectOutputToLog();
/*     */       }
/*     */       
/* 557 */       Block.registerBlocks();
/* 558 */       BlockFire.func_149843_e();
/* 559 */       Item.registerItems();
/* 560 */       StatList.func_151178_a();
/* 561 */       registerDispenserBehaviors();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void redirectOutputToLog() {
/* 570 */     System.setErr((PrintStream)new LoggingPrintStream("STDERR", System.err));
/* 571 */     System.setOut((PrintStream)new LoggingPrintStream("STDOUT", SYSOUT));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_179870_a(String p_179870_0_) {
/* 576 */     SYSOUT.println(p_179870_0_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\init\Bootstrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */