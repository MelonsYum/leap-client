/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPressurePlate
/*     */   extends BlockBasePressurePlate {
/*  18 */   public static final PropertyBool POWERED = PropertyBool.create("powered");
/*     */   
/*     */   private final Sensitivity sensitivity;
/*     */   private static final String __OBFID = "CL_00000289";
/*     */   
/*     */   protected BlockPressurePlate(Material p_i45693_1_, Sensitivity p_i45693_2_) {
/*  24 */     super(p_i45693_1_);
/*  25 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)POWERED, Boolean.valueOf(false)));
/*  26 */     this.sensitivity = p_i45693_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getRedstoneStrength(IBlockState p_176576_1_) {
/*  31 */     return ((Boolean)p_176576_1_.getValue((IProperty)POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockState setRedstoneStrength(IBlockState p_176575_1_, int p_176575_2_) {
/*  36 */     return p_176575_1_.withProperty((IProperty)POWERED, Boolean.valueOf((p_176575_2_ > 0)));
/*     */   }
/*     */   
/*     */   protected int computeRedstoneStrength(World worldIn, BlockPos pos) {
/*     */     List var4;
/*  41 */     AxisAlignedBB var3 = getSensitiveAABB(pos);
/*     */ 
/*     */     
/*  44 */     switch (SwitchSensitivity.SENSITIVITY_ARRAY[this.sensitivity.ordinal()]) {
/*     */       
/*     */       case 1:
/*  47 */         var4 = worldIn.getEntitiesWithinAABBExcludingEntity(null, var3);
/*     */         break;
/*     */       
/*     */       case 2:
/*  51 */         var4 = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, var3);
/*     */         break;
/*     */       
/*     */       default:
/*  55 */         return 0;
/*     */     } 
/*     */     
/*  58 */     if (!var4.isEmpty()) {
/*     */       
/*  60 */       Iterator<Entity> var5 = var4.iterator();
/*     */       
/*  62 */       while (var5.hasNext()) {
/*     */         
/*  64 */         Entity var6 = var5.next();
/*     */         
/*  66 */         if (!var6.doesEntityNotTriggerPressurePlate())
/*     */         {
/*  68 */           return 15;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  73 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  81 */     return getDefaultState().withProperty((IProperty)POWERED, Boolean.valueOf((meta == 1)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  89 */     return ((Boolean)state.getValue((IProperty)POWERED)).booleanValue() ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  94 */     return new BlockState(this, new IProperty[] { (IProperty)POWERED });
/*     */   }
/*     */   
/*     */   public enum Sensitivity
/*     */   {
/*  99 */     EVERYTHING("EVERYTHING", 0),
/* 100 */     MOBS("MOBS", 1);
/*     */     
/* 102 */     private static final Sensitivity[] $VALUES = new Sensitivity[] { EVERYTHING, MOBS };
/*     */     private static final String __OBFID = "CL_00000290";
/*     */     
/*     */     static {
/*     */     
/*     */     } }
/*     */   
/*     */   static final class SwitchSensitivity {
/* 110 */     static final int[] SENSITIVITY_ARRAY = new int[(BlockPressurePlate.Sensitivity.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002078";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 117 */         SENSITIVITY_ARRAY[BlockPressurePlate.Sensitivity.EVERYTHING.ordinal()] = 1;
/*     */       }
/* 119 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 126 */         SENSITIVITY_ARRAY[BlockPressurePlate.Sensitivity.MOBS.ordinal()] = 2;
/*     */       }
/* 128 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPressurePlate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */