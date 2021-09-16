/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
/*     */ import net.minecraft.client.renderer.chunk.RenderChunk;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ViewFrustum
/*     */ {
/*     */   protected final RenderGlobal field_178169_a;
/*     */   protected final World field_178167_b;
/*     */   protected int field_178168_c;
/*     */   protected int field_178165_d;
/*     */   protected int field_178166_e;
/*     */   public RenderChunk[] field_178164_f;
/*     */   
/*     */   public ViewFrustum(World worldIn, int renderDistanceChunks, RenderGlobal p_i46246_3_, IRenderChunkFactory p_i46246_4_) {
/*  19 */     this.field_178169_a = p_i46246_3_;
/*  20 */     this.field_178167_b = worldIn;
/*  21 */     func_178159_a(renderDistanceChunks);
/*  22 */     func_178158_a(p_i46246_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_178158_a(IRenderChunkFactory p_178158_1_) {
/*  27 */     int var2 = this.field_178165_d * this.field_178168_c * this.field_178166_e;
/*  28 */     this.field_178164_f = new RenderChunk[var2];
/*  29 */     int var3 = 0;
/*     */     
/*  31 */     for (int var4 = 0; var4 < this.field_178165_d; var4++) {
/*     */       
/*  33 */       for (int var5 = 0; var5 < this.field_178168_c; var5++) {
/*     */         
/*  35 */         for (int var6 = 0; var6 < this.field_178166_e; var6++) {
/*     */           
/*  37 */           int var7 = (var6 * this.field_178168_c + var5) * this.field_178165_d + var4;
/*  38 */           BlockPos var8 = new BlockPos(var4 * 16, var5 * 16, var6 * 16);
/*  39 */           this.field_178164_f[var7] = p_178158_1_.func_178602_a(this.field_178167_b, this.field_178169_a, var8, var3++);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178160_a() {
/*  47 */     RenderChunk[] var1 = this.field_178164_f;
/*  48 */     int var2 = var1.length;
/*     */     
/*  50 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/*  52 */       RenderChunk var4 = var1[var3];
/*  53 */       var4.func_178566_a();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_178159_a(int renderDistanceChunks) {
/*  59 */     int var2 = renderDistanceChunks * 2 + 1;
/*  60 */     this.field_178165_d = var2;
/*  61 */     this.field_178168_c = 16;
/*  62 */     this.field_178166_e = var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178163_a(double viewEntityX, double viewEntityZ) {
/*  67 */     int var5 = MathHelper.floor_double(viewEntityX) - 8;
/*  68 */     int var6 = MathHelper.floor_double(viewEntityZ) - 8;
/*  69 */     int var7 = this.field_178165_d * 16;
/*     */     
/*  71 */     for (int var8 = 0; var8 < this.field_178165_d; var8++) {
/*     */       
/*  73 */       int var9 = func_178157_a(var5, var7, var8);
/*     */       
/*  75 */       for (int var10 = 0; var10 < this.field_178166_e; var10++) {
/*     */         
/*  77 */         int var11 = func_178157_a(var6, var7, var10);
/*     */         
/*  79 */         for (int var12 = 0; var12 < this.field_178168_c; var12++) {
/*     */           
/*  81 */           int var13 = var12 * 16;
/*  82 */           RenderChunk var14 = this.field_178164_f[(var10 * this.field_178168_c + var12) * this.field_178165_d + var8];
/*  83 */           BlockPos posChunk = var14.func_178568_j();
/*     */           
/*  85 */           if (posChunk.getX() != var9 || posChunk.getY() != var13 || posChunk.getZ() != var11) {
/*     */             
/*  87 */             BlockPos var15 = new BlockPos(var9, var13, var11);
/*     */             
/*  89 */             if (!var15.equals(var14.func_178568_j()))
/*     */             {
/*  91 */               var14.func_178576_a(var15);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_178157_a(int p_178157_1_, int p_178157_2_, int p_178157_3_) {
/* 101 */     int var4 = p_178157_3_ * 16;
/* 102 */     int var5 = var4 - p_178157_1_ + p_178157_2_ / 2;
/*     */     
/* 104 */     if (var5 < 0)
/*     */     {
/* 106 */       var5 -= p_178157_2_ - 1;
/*     */     }
/*     */     
/* 109 */     return var4 - var5 / p_178157_2_ * p_178157_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178162_a(int p_178162_1_, int p_178162_2_, int p_178162_3_, int p_178162_4_, int p_178162_5_, int p_178162_6_) {
/* 114 */     int var7 = MathHelper.bucketInt(p_178162_1_, 16);
/* 115 */     int var8 = MathHelper.bucketInt(p_178162_2_, 16);
/* 116 */     int var9 = MathHelper.bucketInt(p_178162_3_, 16);
/* 117 */     int var10 = MathHelper.bucketInt(p_178162_4_, 16);
/* 118 */     int var11 = MathHelper.bucketInt(p_178162_5_, 16);
/* 119 */     int var12 = MathHelper.bucketInt(p_178162_6_, 16);
/*     */     
/* 121 */     for (int var13 = var7; var13 <= var10; var13++) {
/*     */       
/* 123 */       int var14 = var13 % this.field_178165_d;
/*     */       
/* 125 */       if (var14 < 0)
/*     */       {
/* 127 */         var14 += this.field_178165_d;
/*     */       }
/*     */       
/* 130 */       for (int var15 = var8; var15 <= var11; var15++) {
/*     */         
/* 132 */         int var16 = var15 % this.field_178168_c;
/*     */         
/* 134 */         if (var16 < 0)
/*     */         {
/* 136 */           var16 += this.field_178168_c;
/*     */         }
/*     */         
/* 139 */         for (int var17 = var9; var17 <= var12; var17++) {
/*     */           
/* 141 */           int var18 = var17 % this.field_178166_e;
/*     */           
/* 143 */           if (var18 < 0)
/*     */           {
/* 145 */             var18 += this.field_178166_e;
/*     */           }
/*     */           
/* 148 */           int var19 = (var18 * this.field_178168_c + var16) * this.field_178165_d + var14;
/* 149 */           RenderChunk var20 = this.field_178164_f[var19];
/* 150 */           var20.func_178575_a(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected RenderChunk func_178161_a(BlockPos p_178161_1_) {
/* 158 */     int var2 = MathHelper.bucketInt(p_178161_1_.getX(), 16);
/* 159 */     int var3 = MathHelper.bucketInt(p_178161_1_.getY(), 16);
/* 160 */     int var4 = MathHelper.bucketInt(p_178161_1_.getZ(), 16);
/*     */     
/* 162 */     if (var3 >= 0 && var3 < this.field_178168_c) {
/*     */       
/* 164 */       var2 %= this.field_178165_d;
/*     */       
/* 166 */       if (var2 < 0)
/*     */       {
/* 168 */         var2 += this.field_178165_d;
/*     */       }
/*     */       
/* 171 */       var4 %= this.field_178166_e;
/*     */       
/* 173 */       if (var4 < 0)
/*     */       {
/* 175 */         var4 += this.field_178166_e;
/*     */       }
/*     */       
/* 178 */       int var5 = (var4 * this.field_178168_c + var3) * this.field_178165_d + var2;
/* 179 */       return this.field_178164_f[var5];
/*     */     } 
/*     */ 
/*     */     
/* 183 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\ViewFrustum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */