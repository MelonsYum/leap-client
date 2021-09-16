/*     */ package net.minecraft.item;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPlanks;
/*     */ import net.minecraft.block.IGrowable;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemDye extends Item {
/*  21 */   public static final int[] dyeColors = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
/*     */   
/*     */   private static final String __OBFID = "CL_00000022";
/*     */   
/*     */   public ItemDye() {
/*  26 */     setHasSubtypes(true);
/*  27 */     setMaxDamage(0);
/*  28 */     setCreativeTab(CreativeTabs.tabMaterials);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack stack) {
/*  37 */     int var2 = stack.getMetadata();
/*  38 */     return String.valueOf(getUnlocalizedName()) + "." + EnumDyeColor.func_176766_a(var2).func_176762_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  49 */     if (!playerIn.func_175151_a(pos.offset(side), side, stack))
/*     */     {
/*  51 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  55 */     EnumDyeColor var9 = EnumDyeColor.func_176766_a(stack.getMetadata());
/*     */     
/*  57 */     if (var9 == EnumDyeColor.WHITE) {
/*     */       
/*  59 */       if (func_179234_a(stack, worldIn, pos))
/*     */       {
/*  61 */         if (!worldIn.isRemote)
/*     */         {
/*  63 */           worldIn.playAuxSFX(2005, pos, 0);
/*     */         }
/*     */         
/*  66 */         return true;
/*     */       }
/*     */     
/*  69 */     } else if (var9 == EnumDyeColor.BROWN) {
/*     */       
/*  71 */       IBlockState var10 = worldIn.getBlockState(pos);
/*  72 */       Block var11 = var10.getBlock();
/*     */       
/*  74 */       if (var11 == Blocks.log && var10.getValue((IProperty)BlockPlanks.VARIANT_PROP) == BlockPlanks.EnumType.JUNGLE) {
/*     */         
/*  76 */         if (side == EnumFacing.DOWN)
/*     */         {
/*  78 */           return false;
/*     */         }
/*     */         
/*  81 */         if (side == EnumFacing.UP)
/*     */         {
/*  83 */           return false;
/*     */         }
/*     */         
/*  86 */         pos = pos.offset(side);
/*     */         
/*  88 */         if (worldIn.isAirBlock(pos)) {
/*     */           
/*  90 */           IBlockState var12 = Blocks.cocoa.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, 0, (EntityLivingBase)playerIn);
/*  91 */           worldIn.setBlockState(pos, var12, 2);
/*     */           
/*  93 */           if (!playerIn.capabilities.isCreativeMode)
/*     */           {
/*  95 */             stack.stackSize--;
/*     */           }
/*     */         } 
/*     */         
/*  99 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean func_179234_a(ItemStack p_179234_0_, World worldIn, BlockPos p_179234_2_) {
/* 109 */     IBlockState var3 = worldIn.getBlockState(p_179234_2_);
/*     */     
/* 111 */     if (var3.getBlock() instanceof IGrowable) {
/*     */       
/* 113 */       IGrowable var4 = (IGrowable)var3.getBlock();
/*     */       
/* 115 */       if (var4.isStillGrowing(worldIn, p_179234_2_, var3, worldIn.isRemote)) {
/*     */         
/* 117 */         if (!worldIn.isRemote) {
/*     */           
/* 119 */           if (var4.canUseBonemeal(worldIn, worldIn.rand, p_179234_2_, var3))
/*     */           {
/* 121 */             var4.grow(worldIn, worldIn.rand, p_179234_2_, var3);
/*     */           }
/*     */           
/* 124 */           p_179234_0_.stackSize--;
/*     */         } 
/*     */         
/* 127 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_180617_a(World worldIn, BlockPos p_180617_1_, int p_180617_2_) {
/* 136 */     if (p_180617_2_ == 0)
/*     */     {
/* 138 */       p_180617_2_ = 15;
/*     */     }
/*     */     
/* 141 */     Block var3 = worldIn.getBlockState(p_180617_1_).getBlock();
/*     */     
/* 143 */     if (var3.getMaterial() != Material.air) {
/*     */       
/* 145 */       var3.setBlockBoundsBasedOnState((IBlockAccess)worldIn, p_180617_1_);
/*     */       
/* 147 */       for (int var4 = 0; var4 < p_180617_2_; var4++) {
/*     */         
/* 149 */         double var5 = itemRand.nextGaussian() * 0.02D;
/* 150 */         double var7 = itemRand.nextGaussian() * 0.02D;
/* 151 */         double var9 = itemRand.nextGaussian() * 0.02D;
/* 152 */         worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, (p_180617_1_.getX() + itemRand.nextFloat()), p_180617_1_.getY() + itemRand.nextFloat() * var3.getBlockBoundsMaxY(), (p_180617_1_.getZ() + itemRand.nextFloat()), var5, var7, var9, new int[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
/* 162 */     if (target instanceof EntitySheep) {
/*     */       
/* 164 */       EntitySheep var4 = (EntitySheep)target;
/* 165 */       EnumDyeColor var5 = EnumDyeColor.func_176766_a(stack.getMetadata());
/*     */       
/* 167 */       if (!var4.getSheared() && var4.func_175509_cj() != var5) {
/*     */         
/* 169 */         var4.func_175512_b(var5);
/* 170 */         stack.stackSize--;
/*     */       } 
/*     */       
/* 173 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
/* 188 */     for (int var4 = 0; var4 < 16; var4++)
/*     */     {
/* 190 */       subItems.add(new ItemStack(itemIn, 1, var4));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemDye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */