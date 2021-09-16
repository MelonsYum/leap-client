/*     */ package net.minecraft.item;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSlab;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemSlab
/*     */   extends ItemBlock
/*     */ {
/*     */   private final BlockSlab field_150949_c;
/*     */   private final BlockSlab field_179226_c;
/*     */   private static final String __OBFID = "CL_00000071";
/*     */   
/*     */   public ItemSlab(Block p_i45782_1_, BlockSlab p_i45782_2_, BlockSlab p_i45782_3_) {
/*  20 */     super(p_i45782_1_);
/*  21 */     this.field_150949_c = p_i45782_2_;
/*  22 */     this.field_179226_c = p_i45782_3_;
/*  23 */     setMaxDamage(0);
/*  24 */     setHasSubtypes(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetadata(int damage) {
/*  33 */     return damage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack stack) {
/*  42 */     return this.field_150949_c.getFullSlabName(stack.getMetadata());
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
/*  53 */     if (stack.stackSize == 0)
/*     */     {
/*  55 */       return false;
/*     */     }
/*  57 */     if (!playerIn.func_175151_a(pos.offset(side), side, stack))
/*     */     {
/*  59 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  63 */     Object var9 = this.field_150949_c.func_176553_a(stack);
/*  64 */     IBlockState var10 = worldIn.getBlockState(pos);
/*     */     
/*  66 */     if (var10.getBlock() == this.field_150949_c) {
/*     */       
/*  68 */       IProperty var11 = this.field_150949_c.func_176551_l();
/*  69 */       Comparable var12 = var10.getValue(var11);
/*  70 */       BlockSlab.EnumBlockHalf var13 = (BlockSlab.EnumBlockHalf)var10.getValue((IProperty)BlockSlab.HALF_PROP);
/*     */       
/*  72 */       if (((side == EnumFacing.UP && var13 == BlockSlab.EnumBlockHalf.BOTTOM) || (side == EnumFacing.DOWN && var13 == BlockSlab.EnumBlockHalf.TOP)) && var12 == var9) {
/*     */         
/*  74 */         IBlockState var14 = this.field_179226_c.getDefaultState().withProperty(var11, var12);
/*     */         
/*  76 */         if (worldIn.checkNoEntityCollision(this.field_179226_c.getCollisionBoundingBox(worldIn, pos, var14)) && worldIn.setBlockState(pos, var14, 3)) {
/*     */           
/*  78 */           worldIn.playSoundEffect((pos.getX() + 0.5F), (pos.getY() + 0.5F), (pos.getZ() + 0.5F), this.field_179226_c.stepSound.getPlaceSound(), (this.field_179226_c.stepSound.getVolume() + 1.0F) / 2.0F, this.field_179226_c.stepSound.getFrequency() * 0.8F);
/*  79 */           stack.stackSize--;
/*     */         } 
/*     */         
/*  82 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     return func_180615_a(stack, worldIn, pos.offset(side), var9) ? true : super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos p_179222_2_, EnumFacing p_179222_3_, EntityPlayer p_179222_4_, ItemStack p_179222_5_) {
/*  92 */     BlockPos var6 = p_179222_2_;
/*  93 */     IProperty var7 = this.field_150949_c.func_176551_l();
/*  94 */     Object var8 = this.field_150949_c.func_176553_a(p_179222_5_);
/*  95 */     IBlockState var9 = worldIn.getBlockState(p_179222_2_);
/*     */     
/*  97 */     if (var9.getBlock() == this.field_150949_c) {
/*     */       
/*  99 */       boolean var10 = (var9.getValue((IProperty)BlockSlab.HALF_PROP) == BlockSlab.EnumBlockHalf.TOP);
/*     */       
/* 101 */       if (((p_179222_3_ == EnumFacing.UP && !var10) || (p_179222_3_ == EnumFacing.DOWN && var10)) && var8 == var9.getValue(var7))
/*     */       {
/* 103 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 107 */     p_179222_2_ = p_179222_2_.offset(p_179222_3_);
/* 108 */     IBlockState var11 = worldIn.getBlockState(p_179222_2_);
/* 109 */     return (var11.getBlock() == this.field_150949_c && var8 == var11.getValue(var7)) ? true : super.canPlaceBlockOnSide(worldIn, var6, p_179222_3_, p_179222_4_, p_179222_5_);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_180615_a(ItemStack p_180615_1_, World worldIn, BlockPos p_180615_3_, Object p_180615_4_) {
/* 114 */     IBlockState var5 = worldIn.getBlockState(p_180615_3_);
/*     */     
/* 116 */     if (var5.getBlock() == this.field_150949_c) {
/*     */       
/* 118 */       Comparable var6 = var5.getValue(this.field_150949_c.func_176551_l());
/*     */       
/* 120 */       if (var6 == p_180615_4_) {
/*     */         
/* 122 */         IBlockState var7 = this.field_179226_c.getDefaultState().withProperty(this.field_150949_c.func_176551_l(), var6);
/*     */         
/* 124 */         if (worldIn.checkNoEntityCollision(this.field_179226_c.getCollisionBoundingBox(worldIn, p_180615_3_, var7)) && worldIn.setBlockState(p_180615_3_, var7, 3)) {
/*     */           
/* 126 */           worldIn.playSoundEffect((p_180615_3_.getX() + 0.5F), (p_180615_3_.getY() + 0.5F), (p_180615_3_.getZ() + 0.5F), this.field_179226_c.stepSound.getPlaceSound(), (this.field_179226_c.stepSound.getVolume() + 1.0F) / 2.0F, this.field_179226_c.stepSound.getFrequency() * 0.8F);
/* 127 */           p_180615_1_.stackSize--;
/*     */         } 
/*     */         
/* 130 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 134 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */