/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockRedstoneOre
/*     */   extends Block
/*     */ {
/*     */   private final boolean isOn;
/*     */   private static final String __OBFID = "CL_00000294";
/*     */   
/*     */   public BlockRedstoneOre(boolean p_i45420_1_) {
/*  24 */     super(Material.rock);
/*     */     
/*  26 */     if (p_i45420_1_)
/*     */     {
/*  28 */       setTickRandomly(true);
/*     */     }
/*     */     
/*  31 */     this.isOn = p_i45420_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/*  39 */     return 30;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
/*  44 */     setOn(worldIn, pos);
/*  45 */     super.onBlockClicked(worldIn, pos, playerIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
/*  53 */     setOn(worldIn, pos);
/*  54 */     super.onEntityCollidedWithBlock(worldIn, pos, entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  59 */     setOn(worldIn, pos);
/*  60 */     return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setOn(World worldIn, BlockPos p_176352_2_) {
/*  65 */     spawnRedstoneParticles(worldIn, p_176352_2_);
/*     */     
/*  67 */     if (this == Blocks.redstone_ore)
/*     */     {
/*  69 */       worldIn.setBlockState(p_176352_2_, Blocks.lit_redstone_ore.getDefaultState());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  75 */     if (this == Blocks.lit_redstone_ore)
/*     */     {
/*  77 */       worldIn.setBlockState(pos, Blocks.redstone_ore.getDefaultState());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  88 */     return Items.redstone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDroppedWithBonus(int fortune, Random random) {
/*  96 */     return quantityDropped(random) + random.nextInt(fortune + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 104 */     return 4 + random.nextInt(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/* 115 */     super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
/*     */     
/* 117 */     if (getItemDropped(state, worldIn.rand, fortune) != Item.getItemFromBlock(this)) {
/*     */       
/* 119 */       int var6 = 1 + worldIn.rand.nextInt(5);
/* 120 */       dropXpOnBlockBreak(worldIn, pos, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 126 */     if (this.isOn)
/*     */     {
/* 128 */       spawnRedstoneParticles(worldIn, pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void spawnRedstoneParticles(World worldIn, BlockPos p_180691_2_) {
/* 134 */     Random var3 = worldIn.rand;
/* 135 */     double var4 = 0.0625D;
/*     */     
/* 137 */     for (int var6 = 0; var6 < 6; var6++) {
/*     */       
/* 139 */       double var7 = (p_180691_2_.getX() + var3.nextFloat());
/* 140 */       double var9 = (p_180691_2_.getY() + var3.nextFloat());
/* 141 */       double var11 = (p_180691_2_.getZ() + var3.nextFloat());
/*     */       
/* 143 */       if (var6 == 0 && !worldIn.getBlockState(p_180691_2_.offsetUp()).getBlock().isOpaqueCube())
/*     */       {
/* 145 */         var9 = p_180691_2_.getY() + var4 + 1.0D;
/*     */       }
/*     */       
/* 148 */       if (var6 == 1 && !worldIn.getBlockState(p_180691_2_.offsetDown()).getBlock().isOpaqueCube())
/*     */       {
/* 150 */         var9 = p_180691_2_.getY() - var4;
/*     */       }
/*     */       
/* 153 */       if (var6 == 2 && !worldIn.getBlockState(p_180691_2_.offsetSouth()).getBlock().isOpaqueCube())
/*     */       {
/* 155 */         var11 = p_180691_2_.getZ() + var4 + 1.0D;
/*     */       }
/*     */       
/* 158 */       if (var6 == 3 && !worldIn.getBlockState(p_180691_2_.offsetNorth()).getBlock().isOpaqueCube())
/*     */       {
/* 160 */         var11 = p_180691_2_.getZ() - var4;
/*     */       }
/*     */       
/* 163 */       if (var6 == 4 && !worldIn.getBlockState(p_180691_2_.offsetEast()).getBlock().isOpaqueCube())
/*     */       {
/* 165 */         var7 = p_180691_2_.getX() + var4 + 1.0D;
/*     */       }
/*     */       
/* 168 */       if (var6 == 5 && !worldIn.getBlockState(p_180691_2_.offsetWest()).getBlock().isOpaqueCube())
/*     */       {
/* 170 */         var7 = p_180691_2_.getX() - var4;
/*     */       }
/*     */       
/* 173 */       if (var7 < p_180691_2_.getX() || var7 > (p_180691_2_.getX() + 1) || var9 < 0.0D || var9 > (p_180691_2_.getY() + 1) || var11 < p_180691_2_.getZ() || var11 > (p_180691_2_.getZ() + 1))
/*     */       {
/* 175 */         worldIn.spawnParticle(EnumParticleTypes.REDSTONE, var7, var9, var11, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack createStackedBlock(IBlockState state) {
/* 182 */     return new ItemStack(Blocks.redstone_ore);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRedstoneOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */