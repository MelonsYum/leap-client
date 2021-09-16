/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPane
/*     */   extends Block {
/*  23 */   public static final PropertyBool NORTH = PropertyBool.create("north");
/*  24 */   public static final PropertyBool EAST = PropertyBool.create("east");
/*  25 */   public static final PropertyBool SOUTH = PropertyBool.create("south");
/*  26 */   public static final PropertyBool WEST = PropertyBool.create("west");
/*     */   
/*     */   private final boolean field_150099_b;
/*     */   private static final String __OBFID = "CL_00000322";
/*     */   
/*     */   protected BlockPane(Material p_i45675_1_, boolean p_i45675_2_) {
/*  32 */     super(p_i45675_1_);
/*  33 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)NORTH, Boolean.valueOf(false)).withProperty((IProperty)EAST, Boolean.valueOf(false)).withProperty((IProperty)SOUTH, Boolean.valueOf(false)).withProperty((IProperty)WEST, Boolean.valueOf(false)));
/*  34 */     this.field_150099_b = p_i45675_2_;
/*  35 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  44 */     return state.withProperty((IProperty)NORTH, Boolean.valueOf(canPaneConnectToBlock(worldIn.getBlockState(pos.offsetNorth()).getBlock()))).withProperty((IProperty)SOUTH, Boolean.valueOf(canPaneConnectToBlock(worldIn.getBlockState(pos.offsetSouth()).getBlock()))).withProperty((IProperty)WEST, Boolean.valueOf(canPaneConnectToBlock(worldIn.getBlockState(pos.offsetWest()).getBlock()))).withProperty((IProperty)EAST, Boolean.valueOf(canPaneConnectToBlock(worldIn.getBlockState(pos.offsetEast()).getBlock())));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  54 */     return !this.field_150099_b ? null : super.getItemDropped(state, rand, fortune);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/*  69 */     return (worldIn.getBlockState(pos).getBlock() == this) ? false : super.shouldSideBeRendered(worldIn, pos, side);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/*  79 */     boolean var7 = canPaneConnectToBlock(worldIn.getBlockState(pos.offsetNorth()).getBlock());
/*  80 */     boolean var8 = canPaneConnectToBlock(worldIn.getBlockState(pos.offsetSouth()).getBlock());
/*  81 */     boolean var9 = canPaneConnectToBlock(worldIn.getBlockState(pos.offsetWest()).getBlock());
/*  82 */     boolean var10 = canPaneConnectToBlock(worldIn.getBlockState(pos.offsetEast()).getBlock());
/*     */     
/*  84 */     if ((!var9 || !var10) && (var9 || var10 || var7 || var8)) {
/*     */       
/*  86 */       if (var9)
/*     */       {
/*  88 */         setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
/*  89 */         super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */       }
/*  91 */       else if (var10)
/*     */       {
/*  93 */         setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
/*  94 */         super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  99 */       setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
/* 100 */       super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */     } 
/*     */     
/* 103 */     if ((!var7 || !var8) && (var9 || var10 || var7 || var8)) {
/*     */       
/* 105 */       if (var7)
/*     */       {
/* 107 */         setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
/* 108 */         super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */       }
/* 110 */       else if (var8)
/*     */       {
/* 112 */         setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
/* 113 */         super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 118 */       setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
/* 119 */       super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/* 128 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 133 */     float var3 = 0.4375F;
/* 134 */     float var4 = 0.5625F;
/* 135 */     float var5 = 0.4375F;
/* 136 */     float var6 = 0.5625F;
/* 137 */     boolean var7 = canPaneConnectToBlock(access.getBlockState(pos.offsetNorth()).getBlock());
/* 138 */     boolean var8 = canPaneConnectToBlock(access.getBlockState(pos.offsetSouth()).getBlock());
/* 139 */     boolean var9 = canPaneConnectToBlock(access.getBlockState(pos.offsetWest()).getBlock());
/* 140 */     boolean var10 = canPaneConnectToBlock(access.getBlockState(pos.offsetEast()).getBlock());
/*     */     
/* 142 */     if ((!var9 || !var10) && (var9 || var10 || var7 || var8)) {
/*     */       
/* 144 */       if (var9)
/*     */       {
/* 146 */         var3 = 0.0F;
/*     */       }
/* 148 */       else if (var10)
/*     */       {
/* 150 */         var4 = 1.0F;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 155 */       var3 = 0.0F;
/* 156 */       var4 = 1.0F;
/*     */     } 
/*     */     
/* 159 */     if ((!var7 || !var8) && (var9 || var10 || var7 || var8)) {
/*     */       
/* 161 */       if (var7)
/*     */       {
/* 163 */         var5 = 0.0F;
/*     */       }
/* 165 */       else if (var8)
/*     */       {
/* 167 */         var6 = 1.0F;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 172 */       var5 = 0.0F;
/* 173 */       var6 = 1.0F;
/*     */     } 
/*     */     
/* 176 */     setBlockBounds(var3, 0.0F, var5, var4, 1.0F, var6);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean canPaneConnectToBlock(Block p_150098_1_) {
/* 181 */     return !(!p_150098_1_.isFullBlock() && p_150098_1_ != this && p_150098_1_ != Blocks.glass && p_150098_1_ != Blocks.stained_glass && p_150098_1_ != Blocks.stained_glass_pane && !(p_150098_1_ instanceof BlockPane));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSilkHarvest() {
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 191 */     return EnumWorldBlockLayer.CUTOUT_MIPPED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 199 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 204 */     return new BlockState(this, new IProperty[] { (IProperty)NORTH, (IProperty)EAST, (IProperty)WEST, (IProperty)SOUTH });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */