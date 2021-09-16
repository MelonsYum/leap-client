/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.Tuple;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockSponge extends Block {
/*  26 */   public static final PropertyBool WET_PROP = PropertyBool.create("wet");
/*     */   
/*     */   private static final String __OBFID = "CL_00000311";
/*     */   
/*     */   protected BlockSponge() {
/*  31 */     super(Material.sponge);
/*  32 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)WET_PROP, Boolean.valueOf(false)));
/*  33 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  41 */     return ((Boolean)state.getValue((IProperty)WET_PROP)).booleanValue() ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  46 */     setWet(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  51 */     setWet(worldIn, pos, state);
/*  52 */     super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setWet(World worldIn, BlockPos p_176311_2_, IBlockState p_176311_3_) {
/*  57 */     if (!((Boolean)p_176311_3_.getValue((IProperty)WET_PROP)).booleanValue() && absorbWater(worldIn, p_176311_2_)) {
/*     */       
/*  59 */       worldIn.setBlockState(p_176311_2_, p_176311_3_.withProperty((IProperty)WET_PROP, Boolean.valueOf(true)), 2);
/*  60 */       worldIn.playAuxSFX(2001, p_176311_2_, Block.getIdFromBlock(Blocks.water));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean absorbWater(World worldIn, BlockPos p_176312_2_) {
/*  66 */     LinkedList<Tuple> var3 = Lists.newLinkedList();
/*  67 */     ArrayList<BlockPos> var4 = Lists.newArrayList();
/*  68 */     var3.add(new Tuple(p_176312_2_, Integer.valueOf(0)));
/*  69 */     int var5 = 0;
/*     */ 
/*     */     
/*  72 */     while (!var3.isEmpty()) {
/*     */       
/*  74 */       Tuple var6 = var3.poll();
/*  75 */       BlockPos var7 = (BlockPos)var6.getFirst();
/*  76 */       int var8 = ((Integer)var6.getSecond()).intValue();
/*  77 */       EnumFacing[] var9 = EnumFacing.values();
/*  78 */       int var10 = var9.length;
/*     */       
/*  80 */       for (int var11 = 0; var11 < var10; var11++) {
/*     */         
/*  82 */         EnumFacing var12 = var9[var11];
/*  83 */         BlockPos var13 = var7.offset(var12);
/*     */         
/*  85 */         if (worldIn.getBlockState(var13).getBlock().getMaterial() == Material.water) {
/*     */           
/*  87 */           worldIn.setBlockState(var13, Blocks.air.getDefaultState(), 2);
/*  88 */           var4.add(var13);
/*  89 */           var5++;
/*     */           
/*  91 */           if (var8 < 6)
/*     */           {
/*  93 */             var3.add(new Tuple(var13, Integer.valueOf(var8 + 1)));
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  98 */       if (var5 > 64) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 104 */     Iterator<BlockPos> var14 = var4.iterator();
/*     */     
/* 106 */     while (var14.hasNext()) {
/*     */       
/* 108 */       BlockPos var7 = var14.next();
/* 109 */       worldIn.notifyNeighborsOfStateChange(var7, Blocks.air);
/*     */     } 
/*     */     
/* 112 */     return (var5 > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/* 120 */     list.add(new ItemStack(itemIn, 1, 0));
/* 121 */     list.add(new ItemStack(itemIn, 1, 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 129 */     return getDefaultState().withProperty((IProperty)WET_PROP, Boolean.valueOf(((meta & 0x1) == 1)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 137 */     return ((Boolean)state.getValue((IProperty)WET_PROP)).booleanValue() ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 142 */     return new BlockState(this, new IProperty[] { (IProperty)WET_PROP });
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 147 */     if (((Boolean)state.getValue((IProperty)WET_PROP)).booleanValue()) {
/*     */       
/* 149 */       EnumFacing var5 = EnumFacing.random(rand);
/*     */       
/* 151 */       if (var5 != EnumFacing.UP && !World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offset(var5))) {
/*     */         
/* 153 */         double var6 = pos.getX();
/* 154 */         double var8 = pos.getY();
/* 155 */         double var10 = pos.getZ();
/*     */         
/* 157 */         if (var5 == EnumFacing.DOWN) {
/*     */           
/* 159 */           var8 -= 0.05D;
/* 160 */           var6 += rand.nextDouble();
/* 161 */           var10 += rand.nextDouble();
/*     */         }
/*     */         else {
/*     */           
/* 165 */           var8 += rand.nextDouble() * 0.8D;
/*     */           
/* 167 */           if (var5.getAxis() == EnumFacing.Axis.X) {
/*     */             
/* 169 */             var10 += rand.nextDouble();
/*     */             
/* 171 */             if (var5 == EnumFacing.EAST)
/*     */             {
/* 173 */               var6++;
/*     */             }
/*     */             else
/*     */             {
/* 177 */               var6 += 0.05D;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 182 */             var6 += rand.nextDouble();
/*     */             
/* 184 */             if (var5 == EnumFacing.SOUTH) {
/*     */               
/* 186 */               var10++;
/*     */             }
/*     */             else {
/*     */               
/* 190 */               var10 += 0.05D;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 195 */         worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, var6, var8, var10, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSponge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */