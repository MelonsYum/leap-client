/*     */ package net.minecraft.client.renderer.block.model;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.vecmath.Vector3f;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class ItemModelGenerator
/*     */ {
/*  17 */   public static final List LAYERS = Lists.newArrayList((Object[])new String[] { "layer0", "layer1", "layer2", "layer3", "layer4" });
/*     */   
/*     */   private static final String __OBFID = "CL_00002488";
/*     */   
/*     */   public ModelBlock func_178392_a(TextureMap p_178392_1_, ModelBlock p_178392_2_) {
/*  22 */     HashMap<String, String> var3 = Maps.newHashMap();
/*  23 */     ArrayList var4 = Lists.newArrayList();
/*     */     
/*  25 */     for (int var5 = 0; var5 < LAYERS.size(); var5++) {
/*     */       
/*  27 */       String var6 = LAYERS.get(var5);
/*     */       
/*  29 */       if (!p_178392_2_.isTexturePresent(var6)) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/*  34 */       String var7 = p_178392_2_.resolveTextureName(var6);
/*  35 */       var3.put(var6, var7);
/*  36 */       TextureAtlasSprite var8 = p_178392_1_.getAtlasSprite((new ResourceLocation(var7)).toString());
/*  37 */       var4.addAll(func_178394_a(var5, var6, var8));
/*     */     } 
/*     */     
/*  40 */     if (var4.isEmpty())
/*     */     {
/*  42 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  46 */     var3.put("particle", p_178392_2_.isTexturePresent("particle") ? p_178392_2_.resolveTextureName("particle") : var3.get("layer0"));
/*  47 */     return new ModelBlock(var4, var3, false, false, new ItemCameraTransforms(p_178392_2_.getThirdPersonTransform(), p_178392_2_.getFirstPersonTransform(), p_178392_2_.getHeadTransform(), p_178392_2_.getInGuiTransform()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List func_178394_a(int p_178394_1_, String p_178394_2_, TextureAtlasSprite p_178394_3_) {
/*  53 */     HashMap<EnumFacing, BlockPartFace> var4 = Maps.newHashMap();
/*  54 */     var4.put(EnumFacing.SOUTH, new BlockPartFace(null, p_178394_1_, p_178394_2_, new BlockFaceUV(new float[] { 0.0F, 0.0F, 16.0F, 16.0F }, 0)));
/*  55 */     var4.put(EnumFacing.NORTH, new BlockPartFace(null, p_178394_1_, p_178394_2_, new BlockFaceUV(new float[] { 16.0F, 0.0F, 0.0F, 16.0F }, 0)));
/*  56 */     ArrayList<BlockPart> var5 = Lists.newArrayList();
/*  57 */     var5.add(new BlockPart(new Vector3f(0.0F, 0.0F, 7.5F), new Vector3f(16.0F, 16.0F, 8.5F), var4, null, true));
/*  58 */     var5.addAll(func_178397_a(p_178394_3_, p_178394_2_, p_178394_1_));
/*  59 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   private List func_178397_a(TextureAtlasSprite p_178397_1_, String p_178397_2_, int p_178397_3_) {
/*  64 */     float var4 = p_178397_1_.getIconWidth();
/*  65 */     float var5 = p_178397_1_.getIconHeight();
/*  66 */     ArrayList<BlockPart> var6 = Lists.newArrayList();
/*  67 */     Iterator<Span> var7 = func_178393_a(p_178397_1_).iterator();
/*     */     
/*  69 */     while (var7.hasNext()) {
/*     */       
/*  71 */       Span var8 = var7.next();
/*  72 */       float var9 = 0.0F;
/*  73 */       float var10 = 0.0F;
/*  74 */       float var11 = 0.0F;
/*  75 */       float var12 = 0.0F;
/*  76 */       float var13 = 0.0F;
/*  77 */       float var14 = 0.0F;
/*  78 */       float var15 = 0.0F;
/*  79 */       float var16 = 0.0F;
/*  80 */       float var17 = 0.0F;
/*  81 */       float var18 = 0.0F;
/*  82 */       float var19 = var8.func_178385_b();
/*  83 */       float var20 = var8.func_178384_c();
/*  84 */       float var21 = var8.func_178381_d();
/*  85 */       SpanFacing var22 = var8.func_178383_a();
/*     */       
/*  87 */       switch (SwitchSpanFacing.field_178390_a[var22.ordinal()]) {
/*     */         
/*     */         case 1:
/*  90 */           var13 = var19;
/*  91 */           var9 = var19;
/*  92 */           var11 = var14 = var20 + 1.0F;
/*  93 */           var15 = var21;
/*  94 */           var10 = var21;
/*  95 */           var16 = var21;
/*  96 */           var12 = var21;
/*  97 */           var17 = 16.0F / var4;
/*  98 */           var18 = 16.0F / (var5 - 1.0F);
/*     */           break;
/*     */         
/*     */         case 2:
/* 102 */           var16 = var21;
/* 103 */           var15 = var21;
/* 104 */           var13 = var19;
/* 105 */           var9 = var19;
/* 106 */           var11 = var14 = var20 + 1.0F;
/* 107 */           var10 = var21 + 1.0F;
/* 108 */           var12 = var21 + 1.0F;
/* 109 */           var17 = 16.0F / var4;
/* 110 */           var18 = 16.0F / (var5 - 1.0F);
/*     */           break;
/*     */         
/*     */         case 3:
/* 114 */           var13 = var21;
/* 115 */           var9 = var21;
/* 116 */           var14 = var21;
/* 117 */           var11 = var21;
/* 118 */           var16 = var19;
/* 119 */           var10 = var19;
/* 120 */           var12 = var15 = var20 + 1.0F;
/* 121 */           var17 = 16.0F / (var4 - 1.0F);
/* 122 */           var18 = 16.0F / var5;
/*     */           break;
/*     */         
/*     */         case 4:
/* 126 */           var14 = var21;
/* 127 */           var13 = var21;
/* 128 */           var9 = var21 + 1.0F;
/* 129 */           var11 = var21 + 1.0F;
/* 130 */           var16 = var19;
/* 131 */           var10 = var19;
/* 132 */           var12 = var15 = var20 + 1.0F;
/* 133 */           var17 = 16.0F / (var4 - 1.0F);
/* 134 */           var18 = 16.0F / var5;
/*     */           break;
/*     */       } 
/* 137 */       float var23 = 16.0F / var4;
/* 138 */       float var24 = 16.0F / var5;
/* 139 */       var9 *= var23;
/* 140 */       var11 *= var23;
/* 141 */       var10 *= var24;
/* 142 */       var12 *= var24;
/* 143 */       var10 = 16.0F - var10;
/* 144 */       var12 = 16.0F - var12;
/* 145 */       var13 *= var17;
/* 146 */       var14 *= var17;
/* 147 */       var15 *= var18;
/* 148 */       var16 *= var18;
/* 149 */       HashMap<EnumFacing, BlockPartFace> var25 = Maps.newHashMap();
/* 150 */       var25.put(var22.func_178367_a(), new BlockPartFace(null, p_178397_3_, p_178397_2_, new BlockFaceUV(new float[] { var13, var15, var14, var16 }, 0)));
/*     */       
/* 152 */       switch (SwitchSpanFacing.field_178390_a[var22.ordinal()]) {
/*     */         
/*     */         case 1:
/* 155 */           var6.add(new BlockPart(new Vector3f(var9, var10, 7.5F), new Vector3f(var11, var10, 8.5F), var25, null, true));
/*     */ 
/*     */         
/*     */         case 2:
/* 159 */           var6.add(new BlockPart(new Vector3f(var9, var12, 7.5F), new Vector3f(var11, var12, 8.5F), var25, null, true));
/*     */ 
/*     */         
/*     */         case 3:
/* 163 */           var6.add(new BlockPart(new Vector3f(var9, var10, 7.5F), new Vector3f(var9, var12, 8.5F), var25, null, true));
/*     */ 
/*     */         
/*     */         case 4:
/* 167 */           var6.add(new BlockPart(new Vector3f(var11, var10, 7.5F), new Vector3f(var11, var12, 8.5F), var25, null, true));
/*     */       } 
/*     */     
/*     */     } 
/* 171 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   private List func_178393_a(TextureAtlasSprite p_178393_1_) {
/* 176 */     int var2 = p_178393_1_.getIconWidth();
/* 177 */     int var3 = p_178393_1_.getIconHeight();
/* 178 */     ArrayList var4 = Lists.newArrayList();
/*     */     
/* 180 */     for (int var5 = 0; var5 < p_178393_1_.getFrameCount(); var5++) {
/*     */       
/* 182 */       int[] var6 = p_178393_1_.getFrameTextureData(var5)[0];
/*     */       
/* 184 */       for (int var7 = 0; var7 < var3; var7++) {
/*     */         
/* 186 */         for (int var8 = 0; var8 < var2; var8++) {
/*     */           
/* 188 */           boolean var9 = !func_178391_a(var6, var8, var7, var2, var3);
/* 189 */           func_178396_a(SpanFacing.UP, var4, var6, var8, var7, var2, var3, var9);
/* 190 */           func_178396_a(SpanFacing.DOWN, var4, var6, var8, var7, var2, var3, var9);
/* 191 */           func_178396_a(SpanFacing.LEFT, var4, var6, var8, var7, var2, var3, var9);
/* 192 */           func_178396_a(SpanFacing.RIGHT, var4, var6, var8, var7, var2, var3, var9);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 197 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178396_a(SpanFacing p_178396_1_, List p_178396_2_, int[] p_178396_3_, int p_178396_4_, int p_178396_5_, int p_178396_6_, int p_178396_7_, boolean p_178396_8_) {
/* 202 */     boolean var9 = (func_178391_a(p_178396_3_, p_178396_4_ + p_178396_1_.func_178372_b(), p_178396_5_ + p_178396_1_.func_178371_c(), p_178396_6_, p_178396_7_) && p_178396_8_);
/*     */     
/* 204 */     if (var9)
/*     */     {
/* 206 */       func_178395_a(p_178396_2_, p_178396_1_, p_178396_4_, p_178396_5_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178395_a(List<Span> p_178395_1_, SpanFacing p_178395_2_, int p_178395_3_, int p_178395_4_) {
/* 212 */     Span var5 = null;
/* 213 */     Iterator<Span> var6 = p_178395_1_.iterator();
/*     */     
/* 215 */     while (var6.hasNext()) {
/*     */       
/* 217 */       Span var7 = var6.next();
/*     */       
/* 219 */       if (var7.func_178383_a() == p_178395_2_) {
/*     */         
/* 221 */         int var8 = p_178395_2_.func_178369_d() ? p_178395_4_ : p_178395_3_;
/*     */         
/* 223 */         if (var7.func_178381_d() == var8) {
/*     */           
/* 225 */           var5 = var7;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 231 */     int var9 = p_178395_2_.func_178369_d() ? p_178395_4_ : p_178395_3_;
/* 232 */     int var10 = p_178395_2_.func_178369_d() ? p_178395_3_ : p_178395_4_;
/*     */     
/* 234 */     if (var5 == null) {
/*     */       
/* 236 */       p_178395_1_.add(new Span(p_178395_2_, var10, var9));
/*     */     }
/*     */     else {
/*     */       
/* 240 */       var5.func_178382_a(var10);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_178391_a(int[] p_178391_1_, int p_178391_2_, int p_178391_3_, int p_178391_4_, int p_178391_5_) {
/* 246 */     return (p_178391_2_ >= 0 && p_178391_3_ >= 0 && p_178391_2_ < p_178391_4_ && p_178391_3_ < p_178391_5_) ? (((p_178391_1_[p_178391_3_ * p_178391_4_ + p_178391_2_] >> 24 & 0xFF) == 0)) : true;
/*     */   }
/*     */ 
/*     */   
/*     */   static class Span
/*     */   {
/*     */     private final ItemModelGenerator.SpanFacing field_178389_a;
/*     */     private int field_178387_b;
/*     */     private int field_178388_c;
/*     */     private final int field_178386_d;
/*     */     private static final String __OBFID = "CL_00002486";
/*     */     
/*     */     public Span(ItemModelGenerator.SpanFacing p_i46216_1_, int p_i46216_2_, int p_i46216_3_) {
/* 259 */       this.field_178389_a = p_i46216_1_;
/* 260 */       this.field_178387_b = p_i46216_2_;
/* 261 */       this.field_178388_c = p_i46216_2_;
/* 262 */       this.field_178386_d = p_i46216_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_178382_a(int p_178382_1_) {
/* 267 */       if (p_178382_1_ < this.field_178387_b) {
/*     */         
/* 269 */         this.field_178387_b = p_178382_1_;
/*     */       }
/* 271 */       else if (p_178382_1_ > this.field_178388_c) {
/*     */         
/* 273 */         this.field_178388_c = p_178382_1_;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemModelGenerator.SpanFacing func_178383_a() {
/* 279 */       return this.field_178389_a;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_178385_b() {
/* 284 */       return this.field_178387_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_178384_c() {
/* 289 */       return this.field_178388_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_178381_d() {
/* 294 */       return this.field_178386_d;
/*     */     }
/*     */   }
/*     */   
/*     */   enum SpanFacing
/*     */   {
/* 300 */     UP("UP", 0, (String)EnumFacing.UP, 0, -1),
/* 301 */     DOWN("DOWN", 1, (String)EnumFacing.DOWN, 0, 1),
/* 302 */     LEFT("LEFT", 2, (String)EnumFacing.EAST, -1, 0),
/* 303 */     RIGHT("RIGHT", 3, (String)EnumFacing.WEST, 1, 0);
/*     */     
/*     */     private final EnumFacing field_178376_e;
/*     */     private final int field_178373_f;
/*     */     private final int field_178374_g;
/* 308 */     private static final SpanFacing[] $VALUES = new SpanFacing[] { UP, DOWN, LEFT, RIGHT };
/*     */     
/*     */     private static final String __OBFID = "CL_00002485";
/*     */     
/*     */     SpanFacing(String p_i46215_1_, int p_i46215_2_, EnumFacing p_i46215_3_, int p_i46215_4_, int p_i46215_5_) {
/* 313 */       this.field_178376_e = p_i46215_3_;
/* 314 */       this.field_178373_f = p_i46215_4_;
/* 315 */       this.field_178374_g = p_i46215_5_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public EnumFacing func_178367_a() {
/* 320 */       return this.field_178376_e;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_178372_b() {
/* 325 */       return this.field_178373_f;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_178371_c() {
/* 330 */       return this.field_178374_g;
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_178369_d() {
/* 335 */       return !(this != DOWN && this != UP);
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchSpanFacing
/*     */   {
/* 341 */     static final int[] field_178390_a = new int[(ItemModelGenerator.SpanFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002487";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 348 */         field_178390_a[ItemModelGenerator.SpanFacing.UP.ordinal()] = 1;
/*     */       }
/* 350 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 357 */         field_178390_a[ItemModelGenerator.SpanFacing.DOWN.ordinal()] = 2;
/*     */       }
/* 359 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 366 */         field_178390_a[ItemModelGenerator.SpanFacing.LEFT.ordinal()] = 3;
/*     */       }
/* 368 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 375 */         field_178390_a[ItemModelGenerator.SpanFacing.RIGHT.ordinal()] = 4;
/*     */       }
/* 377 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\ItemModelGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */