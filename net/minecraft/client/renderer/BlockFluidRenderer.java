/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import optifine.CustomColors;
/*     */ import optifine.RenderEnv;
/*     */ 
/*     */ public class BlockFluidRenderer {
/*  18 */   private TextureAtlasSprite[] field_178272_a = new TextureAtlasSprite[2];
/*  19 */   private TextureAtlasSprite[] field_178271_b = new TextureAtlasSprite[2];
/*     */   
/*     */   private static final String __OBFID = "CL_00002519";
/*     */   
/*     */   public BlockFluidRenderer() {
/*  24 */     func_178268_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_178268_a() {
/*  29 */     TextureMap var1 = Minecraft.getMinecraft().getTextureMapBlocks();
/*  30 */     this.field_178272_a[0] = var1.getAtlasSprite("minecraft:blocks/lava_still");
/*  31 */     this.field_178272_a[1] = var1.getAtlasSprite("minecraft:blocks/lava_flow");
/*  32 */     this.field_178271_b[0] = var1.getAtlasSprite("minecraft:blocks/water_still");
/*  33 */     this.field_178271_b[1] = var1.getAtlasSprite("minecraft:blocks/water_flow");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178270_a(IBlockAccess p_178270_1_, IBlockState p_178270_2_, BlockPos p_178270_3_, WorldRenderer p_178270_4_) {
/*  38 */     BlockLiquid var5 = (BlockLiquid)p_178270_2_.getBlock();
/*  39 */     var5.setBlockBoundsBasedOnState(p_178270_1_, p_178270_3_);
/*  40 */     TextureAtlasSprite[] var6 = (var5.getMaterial() == Material.lava) ? this.field_178272_a : this.field_178271_b;
/*  41 */     RenderEnv renderEnv = RenderEnv.getInstance(p_178270_1_, p_178270_2_, p_178270_3_);
/*  42 */     int var7 = CustomColors.getFluidColor(p_178270_1_, p_178270_2_, p_178270_3_, renderEnv);
/*  43 */     float var8 = (var7 >> 16 & 0xFF) / 255.0F;
/*  44 */     float var9 = (var7 >> 8 & 0xFF) / 255.0F;
/*  45 */     float var10 = (var7 & 0xFF) / 255.0F;
/*  46 */     boolean var11 = var5.shouldSideBeRendered(p_178270_1_, p_178270_3_.offsetUp(), EnumFacing.UP);
/*  47 */     boolean var12 = var5.shouldSideBeRendered(p_178270_1_, p_178270_3_.offsetDown(), EnumFacing.DOWN);
/*  48 */     boolean[] var13 = renderEnv.getBorderFlags();
/*  49 */     var13[0] = var5.shouldSideBeRendered(p_178270_1_, p_178270_3_.offsetNorth(), EnumFacing.NORTH);
/*  50 */     var13[1] = var5.shouldSideBeRendered(p_178270_1_, p_178270_3_.offsetSouth(), EnumFacing.SOUTH);
/*  51 */     var13[2] = var5.shouldSideBeRendered(p_178270_1_, p_178270_3_.offsetWest(), EnumFacing.WEST);
/*  52 */     var13[3] = var5.shouldSideBeRendered(p_178270_1_, p_178270_3_.offsetEast(), EnumFacing.EAST);
/*     */     
/*  54 */     if (!var11 && !var12 && !var13[0] && !var13[1] && !var13[2] && !var13[3])
/*     */     {
/*  56 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  60 */     boolean var14 = false;
/*  61 */     float var15 = 0.5F;
/*  62 */     float var16 = 1.0F;
/*  63 */     float var17 = 0.8F;
/*  64 */     float var18 = 0.6F;
/*  65 */     Material var19 = var5.getMaterial();
/*  66 */     float var20 = func_178269_a(p_178270_1_, p_178270_3_, var19);
/*  67 */     float var21 = func_178269_a(p_178270_1_, p_178270_3_.offsetSouth(), var19);
/*  68 */     float var22 = func_178269_a(p_178270_1_, p_178270_3_.offsetEast().offsetSouth(), var19);
/*  69 */     float var23 = func_178269_a(p_178270_1_, p_178270_3_.offsetEast(), var19);
/*  70 */     double var24 = p_178270_3_.getX();
/*  71 */     double var26 = p_178270_3_.getY();
/*  72 */     double var28 = p_178270_3_.getZ();
/*  73 */     float var30 = 0.001F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (var11) {
/*     */       float var33, var34, var35, var36, var37, var52, var53, var54;
/*  84 */       var14 = true;
/*  85 */       TextureAtlasSprite var31 = var6[0];
/*  86 */       float var32 = (float)BlockLiquid.func_180689_a(p_178270_1_, p_178270_3_, var19);
/*     */       
/*  88 */       if (var32 > -999.0F)
/*     */       {
/*  90 */         var31 = var6[1];
/*     */       }
/*     */       
/*  93 */       p_178270_4_.setSprite(var31);
/*  94 */       var20 -= var30;
/*  95 */       var21 -= var30;
/*  96 */       var22 -= var30;
/*  97 */       var23 -= var30;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 102 */       if (var32 < -999.0F) {
/*     */         
/* 104 */         var33 = var31.getInterpolatedU(0.0D);
/* 105 */         var37 = var31.getInterpolatedV(0.0D);
/* 106 */         var34 = var33;
/* 107 */         var52 = var31.getInterpolatedV(16.0D);
/* 108 */         var35 = var31.getInterpolatedU(16.0D);
/* 109 */         var53 = var52;
/* 110 */         var36 = var35;
/* 111 */         var54 = var37;
/*     */       }
/*     */       else {
/*     */         
/* 115 */         float var55 = MathHelper.sin(var32) * 0.25F;
/* 116 */         float var44 = MathHelper.cos(var32) * 0.25F;
/* 117 */         float var43 = 8.0F;
/* 118 */         var33 = var31.getInterpolatedU((8.0F + (-var44 - var55) * 16.0F));
/* 119 */         var37 = var31.getInterpolatedV((8.0F + (-var44 + var55) * 16.0F));
/* 120 */         var34 = var31.getInterpolatedU((8.0F + (-var44 + var55) * 16.0F));
/* 121 */         var52 = var31.getInterpolatedV((8.0F + (var44 + var55) * 16.0F));
/* 122 */         var35 = var31.getInterpolatedU((8.0F + (var44 + var55) * 16.0F));
/* 123 */         var53 = var31.getInterpolatedV((8.0F + (var44 - var55) * 16.0F));
/* 124 */         var36 = var31.getInterpolatedU((8.0F + (var44 - var55) * 16.0F));
/* 125 */         var54 = var31.getInterpolatedV((8.0F + (-var44 - var55) * 16.0F));
/*     */       } 
/*     */       
/* 128 */       p_178270_4_.func_178963_b(var5.getMixedBrightnessForBlock(p_178270_1_, p_178270_3_));
/* 129 */       p_178270_4_.func_178986_b(var16 * var8, var16 * var9, var16 * var10);
/* 130 */       p_178270_4_.addVertexWithUV(var24 + 0.0D, var26 + var20, var28 + 0.0D, var33, var37);
/* 131 */       p_178270_4_.addVertexWithUV(var24 + 0.0D, var26 + var21, var28 + 1.0D, var34, var52);
/* 132 */       p_178270_4_.addVertexWithUV(var24 + 1.0D, var26 + var22, var28 + 1.0D, var35, var53);
/* 133 */       p_178270_4_.addVertexWithUV(var24 + 1.0D, var26 + var23, var28 + 0.0D, var36, var54);
/*     */       
/* 135 */       if (var5.func_176364_g(p_178270_1_, p_178270_3_.offsetUp())) {
/*     */         
/* 137 */         p_178270_4_.addVertexWithUV(var24 + 0.0D, var26 + var20, var28 + 0.0D, var33, var37);
/* 138 */         p_178270_4_.addVertexWithUV(var24 + 1.0D, var26 + var23, var28 + 0.0D, var36, var54);
/* 139 */         p_178270_4_.addVertexWithUV(var24 + 1.0D, var26 + var22, var28 + 1.0D, var35, var53);
/* 140 */         p_178270_4_.addVertexWithUV(var24 + 0.0D, var26 + var21, var28 + 1.0D, var34, var52);
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     if (var12) {
/*     */       
/* 146 */       p_178270_4_.func_178963_b(var5.getMixedBrightnessForBlock(p_178270_1_, p_178270_3_.offsetDown()));
/* 147 */       p_178270_4_.func_178986_b(var15, var15, var15);
/* 148 */       float var32 = var6[0].getMinU();
/* 149 */       float var33 = var6[0].getMaxU();
/* 150 */       float var34 = var6[0].getMinV();
/* 151 */       float var35 = var6[0].getMaxV();
/* 152 */       p_178270_4_.addVertexWithUV(var24, var26, var28 + 1.0D, var32, var35);
/* 153 */       p_178270_4_.addVertexWithUV(var24, var26, var28, var32, var34);
/* 154 */       p_178270_4_.addVertexWithUV(var24 + 1.0D, var26, var28, var33, var34);
/* 155 */       p_178270_4_.addVertexWithUV(var24 + 1.0D, var26, var28 + 1.0D, var33, var35);
/* 156 */       var14 = true;
/*     */     } 
/*     */     
/* 159 */     for (int var571 = 0; var571 < 4; var571++) {
/*     */       
/* 161 */       int var581 = 0;
/* 162 */       int var59 = 0;
/*     */       
/* 164 */       if (var571 == 0)
/*     */       {
/* 166 */         var59--;
/*     */       }
/*     */       
/* 169 */       if (var571 == 1)
/*     */       {
/* 171 */         var59++;
/*     */       }
/*     */       
/* 174 */       if (var571 == 2)
/*     */       {
/* 176 */         var581--;
/*     */       }
/*     */       
/* 179 */       if (var571 == 3)
/*     */       {
/* 181 */         var581++;
/*     */       }
/*     */       
/* 184 */       BlockPos var60 = p_178270_3_.add(var581, 0, var59);
/* 185 */       TextureAtlasSprite var31 = var6[1];
/* 186 */       p_178270_4_.setSprite(var31);
/*     */       
/* 188 */       if (var13[var571]) {
/*     */         float var36, var37;
/*     */ 
/*     */         
/*     */         double var56, var57, var58, var61;
/*     */ 
/*     */         
/* 195 */         if (var571 == 0) {
/*     */           
/* 197 */           var36 = var20;
/* 198 */           var37 = var23;
/* 199 */           var56 = var24;
/* 200 */           var58 = var24 + 1.0D;
/* 201 */           var57 = var28 + var30;
/* 202 */           var61 = var28 + var30;
/*     */         }
/* 204 */         else if (var571 == 1) {
/*     */           
/* 206 */           var36 = var22;
/* 207 */           var37 = var21;
/* 208 */           var56 = var24 + 1.0D;
/* 209 */           var58 = var24;
/* 210 */           var57 = var28 + 1.0D - var30;
/* 211 */           var61 = var28 + 1.0D - var30;
/*     */         }
/* 213 */         else if (var571 == 2) {
/*     */           
/* 215 */           var36 = var21;
/* 216 */           var37 = var20;
/* 217 */           var56 = var24 + var30;
/* 218 */           var58 = var24 + var30;
/* 219 */           var57 = var28 + 1.0D;
/* 220 */           var61 = var28;
/*     */         }
/*     */         else {
/*     */           
/* 224 */           var36 = var23;
/* 225 */           var37 = var22;
/* 226 */           var56 = var24 + 1.0D - var30;
/* 227 */           var58 = var24 + 1.0D - var30;
/* 228 */           var57 = var28;
/* 229 */           var61 = var28 + 1.0D;
/*     */         } 
/*     */         
/* 232 */         var14 = true;
/* 233 */         float var46 = var31.getInterpolatedU(0.0D);
/* 234 */         float var47 = var31.getInterpolatedU(8.0D);
/* 235 */         float var48 = var31.getInterpolatedV(((1.0F - var36) * 16.0F * 0.5F));
/* 236 */         float var49 = var31.getInterpolatedV(((1.0F - var37) * 16.0F * 0.5F));
/* 237 */         float var50 = var31.getInterpolatedV(8.0D);
/* 238 */         p_178270_4_.func_178963_b(var5.getMixedBrightnessForBlock(p_178270_1_, var60));
/* 239 */         float var51 = 1.0F;
/* 240 */         var51 *= (var571 < 2) ? var17 : var18;
/* 241 */         p_178270_4_.func_178986_b(var16 * var51 * var8, var16 * var51 * var9, var16 * var51 * var10);
/* 242 */         p_178270_4_.addVertexWithUV(var56, var26 + var36, var57, var46, var48);
/* 243 */         p_178270_4_.addVertexWithUV(var58, var26 + var37, var61, var47, var49);
/* 244 */         p_178270_4_.addVertexWithUV(var58, var26 + 0.0D, var61, var47, var50);
/* 245 */         p_178270_4_.addVertexWithUV(var56, var26 + 0.0D, var57, var46, var50);
/* 246 */         p_178270_4_.addVertexWithUV(var56, var26 + 0.0D, var57, var46, var50);
/* 247 */         p_178270_4_.addVertexWithUV(var58, var26 + 0.0D, var61, var47, var50);
/* 248 */         p_178270_4_.addVertexWithUV(var58, var26 + var37, var61, var47, var49);
/* 249 */         p_178270_4_.addVertexWithUV(var56, var26 + var36, var57, var46, var48);
/*     */       } 
/*     */     } 
/*     */     
/* 253 */     p_178270_4_.setSprite(null);
/* 254 */     return var14;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float func_178269_a(IBlockAccess p_178269_1_, BlockPos p_178269_2_, Material p_178269_3_) {
/* 260 */     int var4 = 0;
/* 261 */     float var5 = 0.0F;
/*     */     
/* 263 */     for (int var6 = 0; var6 < 4; var6++) {
/*     */       
/* 265 */       BlockPos var7 = p_178269_2_.add(-(var6 & 0x1), 0, -(var6 >> 1 & 0x1));
/*     */       
/* 267 */       if (p_178269_1_.getBlockState(var7.offsetUp()).getBlock().getMaterial() == p_178269_3_)
/*     */       {
/* 269 */         return 1.0F;
/*     */       }
/*     */       
/* 272 */       IBlockState var8 = p_178269_1_.getBlockState(var7);
/* 273 */       Material var9 = var8.getBlock().getMaterial();
/*     */       
/* 275 */       if (var9 == p_178269_3_) {
/*     */         
/* 277 */         int var10 = ((Integer)var8.getValue((IProperty)BlockLiquid.LEVEL)).intValue();
/*     */         
/* 279 */         if (var10 >= 8 || var10 == 0) {
/*     */           
/* 281 */           var5 += BlockLiquid.getLiquidHeightPercent(var10) * 10.0F;
/* 282 */           var4 += 10;
/*     */         } 
/*     */         
/* 285 */         var5 += BlockLiquid.getLiquidHeightPercent(var10);
/* 286 */         var4++;
/*     */       }
/* 288 */       else if (!var9.isSolid()) {
/*     */         
/* 290 */         var5++;
/* 291 */         var4++;
/*     */       } 
/*     */     } 
/*     */     
/* 295 */     return 1.0F - var5 / var4;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\BlockFluidRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */