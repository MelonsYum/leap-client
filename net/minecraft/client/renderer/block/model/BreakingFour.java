/*     */ package net.minecraft.client.renderer.block.model;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ public class BreakingFour
/*     */   extends BakedQuad
/*     */ {
/*     */   private final TextureAtlasSprite texture;
/*     */   private static final String __OBFID = "CL_00002492";
/*     */   
/*     */   public BreakingFour(BakedQuad p_i46217_1_, TextureAtlasSprite p_i46217_2_) {
/*  14 */     super(Arrays.copyOf(p_i46217_1_.func_178209_a(), (p_i46217_1_.func_178209_a()).length), p_i46217_1_.field_178213_b, FaceBakery.func_178410_a(p_i46217_1_.func_178209_a()));
/*  15 */     this.texture = p_i46217_2_;
/*  16 */     func_178217_e();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178217_e() {
/*  21 */     for (int var1 = 0; var1 < 4; var1++)
/*     */     {
/*  23 */       func_178216_a(var1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178216_a(int p_178216_1_) {
/*  29 */     int step = this.field_178215_a.length / 4;
/*  30 */     int var2 = step * p_178216_1_;
/*  31 */     float var3 = Float.intBitsToFloat(this.field_178215_a[var2]);
/*  32 */     float var4 = Float.intBitsToFloat(this.field_178215_a[var2 + 1]);
/*  33 */     float var5 = Float.intBitsToFloat(this.field_178215_a[var2 + 2]);
/*  34 */     float var6 = 0.0F;
/*  35 */     float var7 = 0.0F;
/*     */     
/*  37 */     switch (SwitchEnumFacing.field_178419_a[this.face.ordinal()]) {
/*     */       
/*     */       case 1:
/*  40 */         var6 = var3 * 16.0F;
/*  41 */         var7 = (1.0F - var5) * 16.0F;
/*     */         break;
/*     */       
/*     */       case 2:
/*  45 */         var6 = var3 * 16.0F;
/*  46 */         var7 = var5 * 16.0F;
/*     */         break;
/*     */       
/*     */       case 3:
/*  50 */         var6 = (1.0F - var3) * 16.0F;
/*  51 */         var7 = (1.0F - var4) * 16.0F;
/*     */         break;
/*     */       
/*     */       case 4:
/*  55 */         var6 = var3 * 16.0F;
/*  56 */         var7 = (1.0F - var4) * 16.0F;
/*     */         break;
/*     */       
/*     */       case 5:
/*  60 */         var6 = var5 * 16.0F;
/*  61 */         var7 = (1.0F - var4) * 16.0F;
/*     */         break;
/*     */       
/*     */       case 6:
/*  65 */         var6 = (1.0F - var5) * 16.0F;
/*  66 */         var7 = (1.0F - var4) * 16.0F;
/*     */         break;
/*     */     } 
/*  69 */     this.field_178215_a[var2 + 4] = Float.floatToRawIntBits(this.texture.getInterpolatedU(var6));
/*  70 */     this.field_178215_a[var2 + 4 + 1] = Float.floatToRawIntBits(this.texture.getInterpolatedV(var7));
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/*  75 */     static final int[] field_178419_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002491";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/*  82 */         field_178419_a[EnumFacing.DOWN.ordinal()] = 1;
/*     */       }
/*  84 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  91 */         field_178419_a[EnumFacing.UP.ordinal()] = 2;
/*     */       }
/*  93 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 100 */         field_178419_a[EnumFacing.NORTH.ordinal()] = 3;
/*     */       }
/* 102 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 109 */         field_178419_a[EnumFacing.SOUTH.ordinal()] = 4;
/*     */       }
/* 111 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 118 */         field_178419_a[EnumFacing.WEST.ordinal()] = 5;
/*     */       }
/* 120 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 127 */         field_178419_a[EnumFacing.EAST.ordinal()] = 6;
/*     */       }
/* 129 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\BreakingFour.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */