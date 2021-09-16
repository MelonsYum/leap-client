/*     */ package net.minecraft.item;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDirt;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemHoe extends Item {
/*     */   protected Item.ToolMaterial theToolMaterial;
/*     */   private static final String __OBFID = "CL_00000039";
/*     */   
/*     */   public ItemHoe(Item.ToolMaterial p_i45343_1_) {
/*  21 */     this.theToolMaterial = p_i45343_1_;
/*  22 */     this.maxStackSize = 1;
/*  23 */     setMaxDamage(p_i45343_1_.getMaxUses());
/*  24 */     setCreativeTab(CreativeTabs.tabTools);
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
/*  35 */     if (!playerIn.func_175151_a(pos.offset(side), side, stack))
/*     */     {
/*  37 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  41 */     IBlockState var9 = worldIn.getBlockState(pos);
/*  42 */     Block var10 = var9.getBlock();
/*     */     
/*  44 */     if (side != EnumFacing.DOWN && worldIn.getBlockState(pos.offsetUp()).getBlock().getMaterial() == Material.air) {
/*     */       
/*  46 */       if (var10 == Blocks.grass)
/*     */       {
/*  48 */         return func_179232_a(stack, playerIn, worldIn, pos, Blocks.farmland.getDefaultState());
/*     */       }
/*     */       
/*  51 */       if (var10 == Blocks.dirt)
/*     */       {
/*  53 */         switch (SwitchDirtType.field_179590_a[((BlockDirt.DirtType)var9.getValue((IProperty)BlockDirt.VARIANT)).ordinal()]) {
/*     */           
/*     */           case 1:
/*  56 */             return func_179232_a(stack, playerIn, worldIn, pos, Blocks.farmland.getDefaultState());
/*     */           
/*     */           case 2:
/*  59 */             return func_179232_a(stack, playerIn, worldIn, pos, Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, (Comparable)BlockDirt.DirtType.DIRT));
/*     */         } 
/*     */       
/*     */       }
/*     */     } 
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_179232_a(ItemStack p_179232_1_, EntityPlayer p_179232_2_, World worldIn, BlockPos p_179232_4_, IBlockState p_179232_5_) {
/*  70 */     worldIn.playSoundEffect((p_179232_4_.getX() + 0.5F), (p_179232_4_.getY() + 0.5F), (p_179232_4_.getZ() + 0.5F), (p_179232_5_.getBlock()).stepSound.getStepSound(), ((p_179232_5_.getBlock()).stepSound.getVolume() + 1.0F) / 2.0F, (p_179232_5_.getBlock()).stepSound.getFrequency() * 0.8F);
/*     */     
/*  72 */     if (worldIn.isRemote)
/*     */     {
/*  74 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  78 */     worldIn.setBlockState(p_179232_4_, p_179232_5_);
/*  79 */     p_179232_1_.damageItem(1, (EntityLivingBase)p_179232_2_);
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/*  89 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMaterialName() {
/*  98 */     return this.theToolMaterial.toString();
/*     */   }
/*     */   
/*     */   static final class SwitchDirtType
/*     */   {
/* 103 */     static final int[] field_179590_a = new int[(BlockDirt.DirtType.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002179";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 110 */         field_179590_a[BlockDirt.DirtType.DIRT.ordinal()] = 1;
/*     */       }
/* 112 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 119 */         field_179590_a[BlockDirt.DirtType.COARSE_DIRT.ordinal()] = 2;
/*     */       }
/* 121 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemHoe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */