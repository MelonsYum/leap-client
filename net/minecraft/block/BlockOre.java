/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockOre
/*     */   extends Block
/*     */ {
/*     */   private static final String __OBFID = "CL_00000282";
/*     */   
/*     */   public BlockOre() {
/*  21 */     super(Material.rock);
/*  22 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  32 */     return (this == Blocks.coal_ore) ? Items.coal : ((this == Blocks.diamond_ore) ? Items.diamond : ((this == Blocks.lapis_ore) ? Items.dye : ((this == Blocks.emerald_ore) ? Items.emerald : ((this == Blocks.quartz_ore) ? Items.quartz : Item.getItemFromBlock(this)))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/*  40 */     return (this == Blocks.lapis_ore) ? (4 + random.nextInt(5)) : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDroppedWithBonus(int fortune, Random random) {
/*  48 */     if (fortune > 0 && Item.getItemFromBlock(this) != getItemDropped((IBlockState)getBlockState().getValidStates().iterator().next(), random, fortune)) {
/*     */       
/*  50 */       int var3 = random.nextInt(fortune + 2) - 1;
/*     */       
/*  52 */       if (var3 < 0)
/*     */       {
/*  54 */         var3 = 0;
/*     */       }
/*     */       
/*  57 */       return quantityDropped(random) * (var3 + 1);
/*     */     } 
/*     */ 
/*     */     
/*  61 */     return quantityDropped(random);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/*  73 */     super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
/*     */     
/*  75 */     if (getItemDropped(state, worldIn.rand, fortune) != Item.getItemFromBlock(this)) {
/*     */       
/*  77 */       int var6 = 0;
/*     */       
/*  79 */       if (this == Blocks.coal_ore) {
/*     */         
/*  81 */         var6 = MathHelper.getRandomIntegerInRange(worldIn.rand, 0, 2);
/*     */       }
/*  83 */       else if (this == Blocks.diamond_ore) {
/*     */         
/*  85 */         var6 = MathHelper.getRandomIntegerInRange(worldIn.rand, 3, 7);
/*     */       }
/*  87 */       else if (this == Blocks.emerald_ore) {
/*     */         
/*  89 */         var6 = MathHelper.getRandomIntegerInRange(worldIn.rand, 3, 7);
/*     */       }
/*  91 */       else if (this == Blocks.lapis_ore) {
/*     */         
/*  93 */         var6 = MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5);
/*     */       }
/*  95 */       else if (this == Blocks.quartz_ore) {
/*     */         
/*  97 */         var6 = MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5);
/*     */       } 
/*     */       
/* 100 */       dropXpOnBlockBreak(worldIn, pos, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/* 106 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 114 */     return (this == Blocks.lapis_ore) ? EnumDyeColor.BLUE.getDyeColorDamage() : 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */