/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.glu.GLU;
/*     */ 
/*     */ public class ActiveRenderInfo {
/*  19 */   private static final IntBuffer field_178814_a = GLAllocation.createDirectIntBuffer(16);
/*  20 */   private static final FloatBuffer field_178812_b = GLAllocation.createDirectFloatBuffer(16);
/*  21 */   private static final FloatBuffer field_178813_c = GLAllocation.createDirectFloatBuffer(16);
/*  22 */   private static final FloatBuffer field_178810_d = GLAllocation.createDirectFloatBuffer(3);
/*  23 */   private static Vec3 field_178811_e = new Vec3(0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */   
/*     */   private static float rotationX;
/*     */ 
/*     */ 
/*     */   
/*     */   private static float rotationXZ;
/*     */ 
/*     */ 
/*     */   
/*     */   private static float rotationZ;
/*     */ 
/*     */ 
/*     */   
/*     */   private static float rotationYZ;
/*     */ 
/*     */   
/*     */   private static float rotationXY;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000626";
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateRenderInfo(EntityPlayer p_74583_0_, boolean p_74583_1_) {
/*  50 */     GlStateManager.getFloat(2982, field_178812_b);
/*  51 */     GlStateManager.getFloat(2983, field_178813_c);
/*  52 */     GL11.glGetInteger(2978, field_178814_a);
/*  53 */     float var2 = ((field_178814_a.get(0) + field_178814_a.get(2)) / 2);
/*  54 */     float var3 = ((field_178814_a.get(1) + field_178814_a.get(3)) / 2);
/*  55 */     GLU.gluUnProject(var2, var3, 0.0F, field_178812_b, field_178813_c, field_178814_a, field_178810_d);
/*  56 */     field_178811_e = new Vec3(field_178810_d.get(0), field_178810_d.get(1), field_178810_d.get(2));
/*  57 */     int var4 = p_74583_1_ ? 1 : 0;
/*  58 */     float var5 = p_74583_0_.rotationPitch;
/*  59 */     float var6 = p_74583_0_.rotationYaw;
/*  60 */     rotationX = MathHelper.cos(var6 * 3.1415927F / 180.0F) * (1 - var4 * 2);
/*  61 */     rotationZ = MathHelper.sin(var6 * 3.1415927F / 180.0F) * (1 - var4 * 2);
/*  62 */     rotationYZ = -rotationZ * MathHelper.sin(var5 * 3.1415927F / 180.0F) * (1 - var4 * 2);
/*  63 */     rotationXY = rotationX * MathHelper.sin(var5 * 3.1415927F / 180.0F) * (1 - var4 * 2);
/*  64 */     rotationXZ = MathHelper.cos(var5 * 3.1415927F / 180.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 func_178806_a(Entity p_178806_0_, double p_178806_1_) {
/*  69 */     double var3 = p_178806_0_.prevPosX + (p_178806_0_.posX - p_178806_0_.prevPosX) * p_178806_1_;
/*  70 */     double var5 = p_178806_0_.prevPosY + (p_178806_0_.posY - p_178806_0_.prevPosY) * p_178806_1_;
/*  71 */     double var7 = p_178806_0_.prevPosZ + (p_178806_0_.posZ - p_178806_0_.prevPosZ) * p_178806_1_;
/*  72 */     double var9 = var3 + field_178811_e.xCoord;
/*  73 */     double var11 = var5 + field_178811_e.yCoord;
/*  74 */     double var13 = var7 + field_178811_e.zCoord;
/*  75 */     return new Vec3(var9, var11, var13);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Block func_180786_a(World worldIn, Entity p_180786_1_, float p_180786_2_) {
/*  80 */     Vec3 var3 = func_178806_a(p_180786_1_, p_180786_2_);
/*  81 */     BlockPos var4 = new BlockPos(var3);
/*  82 */     IBlockState var5 = worldIn.getBlockState(var4);
/*  83 */     Block var6 = var5.getBlock();
/*     */     
/*  85 */     if (var6.getMaterial().isLiquid()) {
/*     */       
/*  87 */       float var7 = 0.0F;
/*     */       
/*  89 */       if (var5.getBlock() instanceof BlockLiquid)
/*     */       {
/*  91 */         var7 = BlockLiquid.getLiquidHeightPercent(((Integer)var5.getValue((IProperty)BlockLiquid.LEVEL)).intValue()) - 0.11111111F;
/*     */       }
/*     */       
/*  94 */       float var8 = (var4.getY() + 1) - var7;
/*     */       
/*  96 */       if (var3.yCoord >= var8)
/*     */       {
/*  98 */         var6 = worldIn.getBlockState(var4.offsetUp()).getBlock();
/*     */       }
/*     */     } 
/*     */     
/* 102 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3 func_178804_a() {
/* 107 */     return field_178811_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float func_178808_b() {
/* 112 */     return rotationX;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float func_178809_c() {
/* 117 */     return rotationXZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float func_178803_d() {
/* 122 */     return rotationZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float func_178805_e() {
/* 127 */     return rotationYZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float func_178807_f() {
/* 132 */     return rotationXY;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\ActiveRenderInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */