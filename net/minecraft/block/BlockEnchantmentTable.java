/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityEnchantmentTable;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.IInteractionObject;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockEnchantmentTable
/*     */   extends BlockContainer {
/*     */   private static final String __OBFID = "CL_00000235";
/*     */   
/*     */   protected BlockEnchantmentTable() {
/*  24 */     super(Material.rock);
/*  25 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
/*  26 */     setLightOpacity(0);
/*  27 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  32 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  37 */     super.randomDisplayTick(worldIn, pos, state, rand);
/*     */     
/*  39 */     for (int var5 = -2; var5 <= 2; var5++) {
/*     */       
/*  41 */       for (int var6 = -2; var6 <= 2; var6++) {
/*     */         
/*  43 */         if (var5 > -2 && var5 < 2 && var6 == -1)
/*     */         {
/*  45 */           var6 = 2;
/*     */         }
/*     */         
/*  48 */         if (rand.nextInt(16) == 0)
/*     */         {
/*  50 */           for (int var7 = 0; var7 <= 1; var7++) {
/*     */             
/*  52 */             BlockPos var8 = pos.add(var5, var7, var6);
/*     */             
/*  54 */             if (worldIn.getBlockState(var8).getBlock() == Blocks.bookshelf) {
/*     */               
/*  56 */               if (!worldIn.isAirBlock(pos.add(var5 / 2, 0, var6 / 2))) {
/*     */                 break;
/*     */               }
/*     */ 
/*     */               
/*  61 */               worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, pos.getX() + 0.5D, pos.getY() + 2.0D, pos.getZ() + 0.5D, (var5 + rand.nextFloat()) - 0.5D, (var7 - rand.nextFloat() - 1.0F), (var6 + rand.nextFloat()) - 0.5D, new int[0]);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  79 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/*  87 */     return (TileEntity)new TileEntityEnchantmentTable();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  92 */     if (worldIn.isRemote)
/*     */     {
/*  94 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  98 */     TileEntity var9 = worldIn.getTileEntity(pos);
/*     */     
/* 100 */     if (var9 instanceof TileEntityEnchantmentTable)
/*     */     {
/* 102 */       playerIn.displayGui((IInteractionObject)var9);
/*     */     }
/*     */     
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 111 */     super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
/*     */     
/* 113 */     if (stack.hasDisplayName()) {
/*     */       
/* 115 */       TileEntity var6 = worldIn.getTileEntity(pos);
/*     */       
/* 117 */       if (var6 instanceof TileEntityEnchantmentTable)
/*     */       {
/* 119 */         ((TileEntityEnchantmentTable)var6).func_145920_a(stack.getDisplayName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockEnchantmentTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */