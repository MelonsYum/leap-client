/*     */ package net.minecraft.block.state;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPistonBase;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPistonStructureHelper
/*     */ {
/*     */   private final World field_177261_a;
/*     */   private final BlockPos field_177259_b;
/*     */   private final BlockPos field_177260_c;
/*     */   private final EnumFacing field_177257_d;
/*  20 */   private final List field_177258_e = Lists.newArrayList();
/*  21 */   private final List field_177256_f = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00002033";
/*     */   
/*     */   public BlockPistonStructureHelper(World worldIn, BlockPos p_i45664_2_, EnumFacing p_i45664_3_, boolean p_i45664_4_) {
/*  26 */     this.field_177261_a = worldIn;
/*  27 */     this.field_177259_b = p_i45664_2_;
/*     */     
/*  29 */     if (p_i45664_4_) {
/*     */       
/*  31 */       this.field_177257_d = p_i45664_3_;
/*  32 */       this.field_177260_c = p_i45664_2_.offset(p_i45664_3_);
/*     */     }
/*     */     else {
/*     */       
/*  36 */       this.field_177257_d = p_i45664_3_.getOpposite();
/*  37 */       this.field_177260_c = p_i45664_2_.offset(p_i45664_3_, 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177253_a() {
/*  43 */     this.field_177258_e.clear();
/*  44 */     this.field_177256_f.clear();
/*  45 */     Block var1 = this.field_177261_a.getBlockState(this.field_177260_c).getBlock();
/*     */     
/*  47 */     if (!BlockPistonBase.func_180696_a(var1, this.field_177261_a, this.field_177260_c, this.field_177257_d, false)) {
/*     */       
/*  49 */       if (var1.getMobilityFlag() != 1)
/*     */       {
/*  51 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  55 */       this.field_177256_f.add(this.field_177260_c);
/*  56 */       return true;
/*     */     } 
/*     */     
/*  59 */     if (!func_177251_a(this.field_177260_c))
/*     */     {
/*  61 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  65 */     for (int var2 = 0; var2 < this.field_177258_e.size(); var2++) {
/*     */       
/*  67 */       BlockPos var3 = this.field_177258_e.get(var2);
/*     */       
/*  69 */       if (this.field_177261_a.getBlockState(var3).getBlock() == Blocks.slime_block && !func_177250_b(var3))
/*     */       {
/*  71 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  75 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_177251_a(BlockPos p_177251_1_) {
/*  81 */     Block var2 = this.field_177261_a.getBlockState(p_177251_1_).getBlock();
/*     */     
/*  83 */     if (var2.getMaterial() == Material.air)
/*     */     {
/*  85 */       return true;
/*     */     }
/*  87 */     if (!BlockPistonBase.func_180696_a(var2, this.field_177261_a, p_177251_1_, this.field_177257_d, false))
/*     */     {
/*  89 */       return true;
/*     */     }
/*  91 */     if (p_177251_1_.equals(this.field_177259_b))
/*     */     {
/*  93 */       return true;
/*     */     }
/*  95 */     if (this.field_177258_e.contains(p_177251_1_))
/*     */     {
/*  97 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 101 */     int var3 = 1;
/*     */     
/* 103 */     if (var3 + this.field_177258_e.size() > 12)
/*     */     {
/* 105 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 109 */     while (var2 == Blocks.slime_block) {
/*     */       
/* 111 */       BlockPos var4 = p_177251_1_.offset(this.field_177257_d.getOpposite(), var3);
/* 112 */       var2 = this.field_177261_a.getBlockState(var4).getBlock();
/*     */       
/* 114 */       if (var2.getMaterial() == Material.air || !BlockPistonBase.func_180696_a(var2, this.field_177261_a, var4, this.field_177257_d, false) || var4.equals(this.field_177259_b)) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 119 */       var3++;
/*     */       
/* 121 */       if (var3 + this.field_177258_e.size() > 12)
/*     */       {
/* 123 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 127 */     int var10 = 0;
/*     */     
/*     */     int var5;
/* 130 */     for (var5 = var3 - 1; var5 >= 0; var5--) {
/*     */       
/* 132 */       this.field_177258_e.add(p_177251_1_.offset(this.field_177257_d.getOpposite(), var5));
/* 133 */       var10++;
/*     */     } 
/*     */     
/* 136 */     var5 = 1;
/*     */ 
/*     */     
/*     */     while (true) {
/* 140 */       BlockPos var6 = p_177251_1_.offset(this.field_177257_d, var5);
/* 141 */       int var7 = this.field_177258_e.indexOf(var6);
/*     */       
/* 143 */       if (var7 > -1) {
/*     */         
/* 145 */         func_177255_a(var10, var7);
/*     */         
/* 147 */         for (int var8 = 0; var8 <= var7 + var10; var8++) {
/*     */           
/* 149 */           BlockPos var9 = this.field_177258_e.get(var8);
/*     */           
/* 151 */           if (this.field_177261_a.getBlockState(var9).getBlock() == Blocks.slime_block && !func_177250_b(var9))
/*     */           {
/* 153 */             return false;
/*     */           }
/*     */         } 
/*     */         
/* 157 */         return true;
/*     */       } 
/*     */       
/* 160 */       var2 = this.field_177261_a.getBlockState(var6).getBlock();
/*     */       
/* 162 */       if (var2.getMaterial() == Material.air)
/*     */       {
/* 164 */         return true;
/*     */       }
/*     */       
/* 167 */       if (!BlockPistonBase.func_180696_a(var2, this.field_177261_a, var6, this.field_177257_d, true) || var6.equals(this.field_177259_b))
/*     */       {
/* 169 */         return false;
/*     */       }
/*     */       
/* 172 */       if (var2.getMobilityFlag() == 1) {
/*     */         
/* 174 */         this.field_177256_f.add(var6);
/* 175 */         return true;
/*     */       } 
/*     */       
/* 178 */       if (this.field_177258_e.size() >= 12)
/*     */       {
/* 180 */         return false;
/*     */       }
/*     */       
/* 183 */       this.field_177258_e.add(var6);
/* 184 */       var10++;
/* 185 */       var5++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_177255_a(int p_177255_1_, int p_177255_2_) {
/* 193 */     ArrayList var3 = Lists.newArrayList();
/* 194 */     ArrayList var4 = Lists.newArrayList();
/* 195 */     ArrayList var5 = Lists.newArrayList();
/* 196 */     var3.addAll(this.field_177258_e.subList(0, p_177255_2_));
/* 197 */     var4.addAll(this.field_177258_e.subList(this.field_177258_e.size() - p_177255_1_, this.field_177258_e.size()));
/* 198 */     var5.addAll(this.field_177258_e.subList(p_177255_2_, this.field_177258_e.size() - p_177255_1_));
/* 199 */     this.field_177258_e.clear();
/* 200 */     this.field_177258_e.addAll(var3);
/* 201 */     this.field_177258_e.addAll(var4);
/* 202 */     this.field_177258_e.addAll(var5);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_177250_b(BlockPos p_177250_1_) {
/* 207 */     EnumFacing[] var2 = EnumFacing.values();
/* 208 */     int var3 = var2.length;
/*     */     
/* 210 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 212 */       EnumFacing var5 = var2[var4];
/*     */       
/* 214 */       if (var5.getAxis() != this.field_177257_d.getAxis() && !func_177251_a(p_177250_1_.offset(var5)))
/*     */       {
/* 216 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 220 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177254_c() {
/* 225 */     return this.field_177258_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177252_d() {
/* 230 */     return this.field_177256_f;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\state\BlockPistonStructureHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */