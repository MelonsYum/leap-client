/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class BlockLog extends BlockRotatedPillar {
/*  16 */   public static final PropertyEnum AXIS_PROP = PropertyEnum.create("axis", EnumAxis.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000266";
/*     */   
/*     */   public BlockLog() {
/*  21 */     super(Material.wood);
/*  22 */     setCreativeTab(CreativeTabs.tabBlock);
/*  23 */     setHardness(2.0F);
/*  24 */     setStepSound(soundTypeWood);
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/*  29 */     byte var4 = 4;
/*  30 */     int var5 = var4 + 1;
/*     */     
/*  32 */     if (worldIn.isAreaLoaded(pos.add(-var5, -var5, -var5), pos.add(var5, var5, var5))) {
/*     */       
/*  34 */       Iterator<BlockPos> var6 = BlockPos.getAllInBox(pos.add(-var4, -var4, -var4), pos.add(var4, var4, var4)).iterator();
/*     */       
/*  36 */       while (var6.hasNext()) {
/*     */         
/*  38 */         BlockPos var7 = var6.next();
/*  39 */         IBlockState var8 = worldIn.getBlockState(var7);
/*     */         
/*  41 */         if (var8.getBlock().getMaterial() == Material.leaves && !((Boolean)var8.getValue((IProperty)BlockLeaves.field_176236_b)).booleanValue())
/*     */         {
/*  43 */           worldIn.setBlockState(var7, var8.withProperty((IProperty)BlockLeaves.field_176236_b, Boolean.valueOf(true)), 4);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  51 */     return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty((IProperty)AXIS_PROP, EnumAxis.func_176870_a(facing.getAxis()));
/*     */   }
/*     */   
/*     */   public enum EnumAxis
/*     */     implements IStringSerializable {
/*  56 */     X("X", 0, "x"),
/*  57 */     Y("Y", 1, "y"),
/*  58 */     Z("Z", 2, "z"),
/*  59 */     NONE("NONE", 3, "none");
/*     */     
/*     */     private final String field_176874_e;
/*  62 */     private static final EnumAxis[] $VALUES = new EnumAxis[] { X, Y, Z, NONE };
/*     */     
/*     */     private static final String __OBFID = "CL_00002100";
/*     */     
/*     */     EnumAxis(String p_i45708_1_, int p_i45708_2_, String p_i45708_3_) {
/*  67 */       this.field_176874_e = p_i45708_3_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String toString() {
/*  72 */       return this.field_176874_e;
/*     */     }
/*     */ 
/*     */     
/*     */     public static EnumAxis func_176870_a(EnumFacing.Axis p_176870_0_) {
/*  77 */       switch (BlockLog.SwitchAxis.field_180167_a[p_176870_0_.ordinal()]) {
/*     */         
/*     */         case 1:
/*  80 */           return X;
/*     */         
/*     */         case 2:
/*  83 */           return Y;
/*     */         
/*     */         case 3:
/*  86 */           return Z;
/*     */       } 
/*     */       
/*  89 */       return NONE;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String getName() {
/*  95 */       return this.field_176874_e;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchAxis
/*     */   {
/* 101 */     static final int[] field_180167_a = new int[(EnumFacing.Axis.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002101";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 108 */         field_180167_a[EnumFacing.Axis.X.ordinal()] = 1;
/*     */       }
/* 110 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 117 */         field_180167_a[EnumFacing.Axis.Y.ordinal()] = 2;
/*     */       }
/* 119 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 126 */         field_180167_a[EnumFacing.Axis.Z.ordinal()] = 3;
/*     */       }
/* 128 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */