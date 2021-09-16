/*     */ package net.minecraft.item;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemBucket
/*     */   extends Item
/*     */ {
/*     */   private Block isFull;
/*     */   private static final String __OBFID = "CL_00000000";
/*     */   
/*     */   public ItemBucket(Block p_i45331_1_) {
/*  25 */     this.maxStackSize = 1;
/*  26 */     this.isFull = p_i45331_1_;
/*  27 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/*  35 */     boolean var4 = (this.isFull == Blocks.air);
/*  36 */     MovingObjectPosition var5 = getMovingObjectPositionFromPlayer(worldIn, playerIn, var4);
/*     */     
/*  38 */     if (var5 == null)
/*     */     {
/*  40 */       return itemStackIn;
/*     */     }
/*     */ 
/*     */     
/*  44 */     if (var5.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */       
/*  46 */       BlockPos var6 = var5.func_178782_a();
/*     */       
/*  48 */       if (!worldIn.isBlockModifiable(playerIn, var6))
/*     */       {
/*  50 */         return itemStackIn;
/*     */       }
/*     */       
/*  53 */       if (var4) {
/*     */         
/*  55 */         if (!playerIn.func_175151_a(var6.offset(var5.field_178784_b), var5.field_178784_b, itemStackIn))
/*     */         {
/*  57 */           return itemStackIn;
/*     */         }
/*     */         
/*  60 */         IBlockState var7 = worldIn.getBlockState(var6);
/*  61 */         Material var8 = var7.getBlock().getMaterial();
/*     */         
/*  63 */         if (var8 == Material.water && ((Integer)var7.getValue((IProperty)BlockLiquid.LEVEL)).intValue() == 0) {
/*     */           
/*  65 */           worldIn.setBlockToAir(var6);
/*  66 */           playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*  67 */           return func_150910_a(itemStackIn, playerIn, Items.water_bucket);
/*     */         } 
/*     */         
/*  70 */         if (var8 == Material.lava && ((Integer)var7.getValue((IProperty)BlockLiquid.LEVEL)).intValue() == 0)
/*     */         {
/*  72 */           worldIn.setBlockToAir(var6);
/*  73 */           playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*  74 */           return func_150910_a(itemStackIn, playerIn, Items.lava_bucket);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  79 */         if (this.isFull == Blocks.air)
/*     */         {
/*  81 */           return new ItemStack(Items.bucket);
/*     */         }
/*     */         
/*  84 */         BlockPos var9 = var6.offset(var5.field_178784_b);
/*     */         
/*  86 */         if (!playerIn.func_175151_a(var9, var5.field_178784_b, itemStackIn))
/*     */         {
/*  88 */           return itemStackIn;
/*     */         }
/*     */         
/*  91 */         if (func_180616_a(worldIn, var9) && !playerIn.capabilities.isCreativeMode) {
/*     */           
/*  93 */           playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*  94 */           return new ItemStack(Items.bucket);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     return itemStackIn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ItemStack func_150910_a(ItemStack p_150910_1_, EntityPlayer p_150910_2_, Item p_150910_3_) {
/* 105 */     if (p_150910_2_.capabilities.isCreativeMode)
/*     */     {
/* 107 */       return p_150910_1_;
/*     */     }
/* 109 */     if (--p_150910_1_.stackSize <= 0)
/*     */     {
/* 111 */       return new ItemStack(p_150910_3_);
/*     */     }
/*     */ 
/*     */     
/* 115 */     if (!p_150910_2_.inventory.addItemStackToInventory(new ItemStack(p_150910_3_)))
/*     */     {
/* 117 */       p_150910_2_.dropPlayerItemWithRandomChoice(new ItemStack(p_150910_3_, 1, 0), false);
/*     */     }
/*     */     
/* 120 */     return p_150910_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180616_a(World worldIn, BlockPos p_180616_2_) {
/* 126 */     if (this.isFull == Blocks.air)
/*     */     {
/* 128 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 132 */     Material var3 = worldIn.getBlockState(p_180616_2_).getBlock().getMaterial();
/* 133 */     boolean var4 = !var3.isSolid();
/*     */     
/* 135 */     if (!worldIn.isAirBlock(p_180616_2_) && !var4)
/*     */     {
/* 137 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 141 */     if (worldIn.provider.func_177500_n() && this.isFull == Blocks.flowing_water) {
/*     */       
/* 143 */       int var5 = p_180616_2_.getX();
/* 144 */       int var6 = p_180616_2_.getY();
/* 145 */       int var7 = p_180616_2_.getZ();
/* 146 */       worldIn.playSoundEffect((var5 + 0.5F), (var6 + 0.5F), (var7 + 0.5F), "random.fizz", 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
/*     */       
/* 148 */       for (int var8 = 0; var8 < 8; var8++)
/*     */       {
/* 150 */         worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var5 + Math.random(), var6 + Math.random(), var7 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 155 */       if (!worldIn.isRemote && var4 && !var3.isLiquid())
/*     */       {
/* 157 */         worldIn.destroyBlock(p_180616_2_, true);
/*     */       }
/*     */       
/* 160 */       worldIn.setBlockState(p_180616_2_, this.isFull.getDefaultState(), 3);
/*     */     } 
/*     */     
/* 163 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */