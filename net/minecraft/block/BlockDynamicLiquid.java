/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockDynamicLiquid extends BlockLiquid {
/*     */   int field_149815_a;
/*     */   private static final String __OBFID = "CL_00000234";
/*     */   
/*     */   protected BlockDynamicLiquid(Material p_i45403_1_) {
/*  21 */     super(p_i45403_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_180690_f(World worldIn, BlockPos p_180690_2_, IBlockState p_180690_3_) {
/*  26 */     worldIn.setBlockState(p_180690_2_, getStaticLiquidForMaterial(this.blockMaterial).getDefaultState().withProperty((IProperty)LEVEL, p_180690_3_.getValue((IProperty)LEVEL)), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  31 */     int var5 = ((Integer)state.getValue((IProperty)LEVEL)).intValue();
/*  32 */     byte var6 = 1;
/*     */     
/*  34 */     if (this.blockMaterial == Material.lava && !worldIn.provider.func_177500_n())
/*     */     {
/*  36 */       var6 = 2;
/*     */     }
/*     */     
/*  39 */     int var7 = tickRate(worldIn);
/*     */ 
/*     */     
/*  42 */     if (var5 > 0) {
/*     */       
/*  44 */       int var8 = -100;
/*  45 */       this.field_149815_a = 0;
/*     */ 
/*     */       
/*  48 */       for (Iterator<EnumFacing> var9 = EnumFacing.Plane.HORIZONTAL.iterator(); var9.hasNext(); var8 = func_176371_a(worldIn, pos.offset(var10), var8))
/*     */       {
/*  50 */         EnumFacing var10 = var9.next();
/*     */       }
/*     */       
/*  53 */       int var14 = var8 + var6;
/*     */       
/*  55 */       if (var14 >= 8 || var8 < 0)
/*     */       {
/*  57 */         var14 = -1;
/*     */       }
/*     */       
/*  60 */       if (func_176362_e((IBlockAccess)worldIn, pos.offsetUp()) >= 0) {
/*     */         
/*  62 */         int var16 = func_176362_e((IBlockAccess)worldIn, pos.offsetUp());
/*     */         
/*  64 */         if (var16 >= 8) {
/*     */           
/*  66 */           var14 = var16;
/*     */         }
/*     */         else {
/*     */           
/*  70 */           var14 = var16 + 8;
/*     */         } 
/*     */       } 
/*     */       
/*  74 */       if (this.field_149815_a >= 2 && this.blockMaterial == Material.water) {
/*     */         
/*  76 */         IBlockState var17 = worldIn.getBlockState(pos.offsetDown());
/*     */         
/*  78 */         if (var17.getBlock().getMaterial().isSolid()) {
/*     */           
/*  80 */           var14 = 0;
/*     */         }
/*  82 */         else if (var17.getBlock().getMaterial() == this.blockMaterial && ((Integer)var17.getValue((IProperty)LEVEL)).intValue() == 0) {
/*     */           
/*  84 */           var14 = 0;
/*     */         } 
/*     */       } 
/*     */       
/*  88 */       if (this.blockMaterial == Material.lava && var5 < 8 && var14 < 8 && var14 > var5 && rand.nextInt(4) != 0)
/*     */       {
/*  90 */         var7 *= 4;
/*     */       }
/*     */       
/*  93 */       if (var14 == var5) {
/*     */         
/*  95 */         func_180690_f(worldIn, pos, state);
/*     */       }
/*     */       else {
/*     */         
/*  99 */         var5 = var14;
/*     */         
/* 101 */         if (var14 < 0)
/*     */         {
/* 103 */           worldIn.setBlockToAir(pos);
/*     */         }
/*     */         else
/*     */         {
/* 107 */           state = state.withProperty((IProperty)LEVEL, Integer.valueOf(var14));
/* 108 */           worldIn.setBlockState(pos, state, 2);
/* 109 */           worldIn.scheduleUpdate(pos, this, var7);
/* 110 */           worldIn.notifyNeighborsOfStateChange(pos, this);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 116 */       func_180690_f(worldIn, pos, state);
/*     */     } 
/*     */     
/* 119 */     IBlockState var13 = worldIn.getBlockState(pos.offsetDown());
/*     */     
/* 121 */     if (func_176373_h(worldIn, pos.offsetDown(), var13)) {
/*     */       
/* 123 */       if (this.blockMaterial == Material.lava && worldIn.getBlockState(pos.offsetDown()).getBlock().getMaterial() == Material.water) {
/*     */         
/* 125 */         worldIn.setBlockState(pos.offsetDown(), Blocks.stone.getDefaultState());
/* 126 */         func_180688_d(worldIn, pos.offsetDown());
/*     */         
/*     */         return;
/*     */       } 
/* 130 */       if (var5 >= 8)
/*     */       {
/* 132 */         func_176375_a(worldIn, pos.offsetDown(), var13, var5);
/*     */       }
/*     */       else
/*     */       {
/* 136 */         func_176375_a(worldIn, pos.offsetDown(), var13, var5 + 8);
/*     */       }
/*     */     
/* 139 */     } else if (var5 >= 0 && (var5 == 0 || func_176372_g(worldIn, pos.offsetDown(), var13))) {
/*     */       
/* 141 */       Set var15 = func_176376_e(worldIn, pos);
/* 142 */       int var16 = var5 + var6;
/*     */       
/* 144 */       if (var5 >= 8)
/*     */       {
/* 146 */         var16 = 1;
/*     */       }
/*     */       
/* 149 */       if (var16 >= 8) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 154 */       Iterator<EnumFacing> var11 = var15.iterator();
/*     */       
/* 156 */       while (var11.hasNext()) {
/*     */         
/* 158 */         EnumFacing var12 = var11.next();
/* 159 */         func_176375_a(worldIn, pos.offset(var12), worldIn.getBlockState(pos.offset(var12)), var16);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176375_a(World worldIn, BlockPos p_176375_2_, IBlockState p_176375_3_, int p_176375_4_) {
/* 166 */     if (func_176373_h(worldIn, p_176375_2_, p_176375_3_)) {
/*     */       
/* 168 */       if (p_176375_3_.getBlock() != Blocks.air)
/*     */       {
/* 170 */         if (this.blockMaterial == Material.lava) {
/*     */           
/* 172 */           func_180688_d(worldIn, p_176375_2_);
/*     */         }
/*     */         else {
/*     */           
/* 176 */           p_176375_3_.getBlock().dropBlockAsItem(worldIn, p_176375_2_, p_176375_3_, 0);
/*     */         } 
/*     */       }
/*     */       
/* 180 */       worldIn.setBlockState(p_176375_2_, getDefaultState().withProperty((IProperty)LEVEL, Integer.valueOf(p_176375_4_)), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_176374_a(World worldIn, BlockPos p_176374_2_, int p_176374_3_, EnumFacing p_176374_4_) {
/* 186 */     int var5 = 1000;
/* 187 */     Iterator<EnumFacing> var6 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/* 189 */     while (var6.hasNext()) {
/*     */       
/* 191 */       EnumFacing var7 = var6.next();
/*     */       
/* 193 */       if (var7 != p_176374_4_) {
/*     */         
/* 195 */         BlockPos var8 = p_176374_2_.offset(var7);
/* 196 */         IBlockState var9 = worldIn.getBlockState(var8);
/*     */         
/* 198 */         if (!func_176372_g(worldIn, var8, var9) && (var9.getBlock().getMaterial() != this.blockMaterial || ((Integer)var9.getValue((IProperty)LEVEL)).intValue() > 0)) {
/*     */           
/* 200 */           if (!func_176372_g(worldIn, var8.offsetDown(), var9))
/*     */           {
/* 202 */             return p_176374_3_;
/*     */           }
/*     */           
/* 205 */           if (p_176374_3_ < 4) {
/*     */             
/* 207 */             int var10 = func_176374_a(worldIn, var8, p_176374_3_ + 1, var7.getOpposite());
/*     */             
/* 209 */             if (var10 < var5)
/*     */             {
/* 211 */               var5 = var10;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   private Set func_176376_e(World worldIn, BlockPos p_176376_2_) {
/* 223 */     int var3 = 1000;
/* 224 */     EnumSet<EnumFacing> var4 = EnumSet.noneOf(EnumFacing.class);
/* 225 */     Iterator<EnumFacing> var5 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/* 227 */     while (var5.hasNext()) {
/*     */       
/* 229 */       EnumFacing var6 = var5.next();
/* 230 */       BlockPos var7 = p_176376_2_.offset(var6);
/* 231 */       IBlockState var8 = worldIn.getBlockState(var7);
/*     */       
/* 233 */       if (!func_176372_g(worldIn, var7, var8) && (var8.getBlock().getMaterial() != this.blockMaterial || ((Integer)var8.getValue((IProperty)LEVEL)).intValue() > 0)) {
/*     */         int var9;
/*     */ 
/*     */         
/* 237 */         if (func_176372_g(worldIn, var7.offsetDown(), worldIn.getBlockState(var7.offsetDown()))) {
/*     */           
/* 239 */           var9 = func_176374_a(worldIn, var7, 1, var6.getOpposite());
/*     */         }
/*     */         else {
/*     */           
/* 243 */           var9 = 0;
/*     */         } 
/*     */         
/* 246 */         if (var9 < var3)
/*     */         {
/* 248 */           var4.clear();
/*     */         }
/*     */         
/* 251 */         if (var9 <= var3) {
/*     */           
/* 253 */           var4.add(var6);
/* 254 */           var3 = var9;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 259 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176372_g(World worldIn, BlockPos p_176372_2_, IBlockState p_176372_3_) {
/* 264 */     Block var4 = worldIn.getBlockState(p_176372_2_).getBlock();
/* 265 */     return (!(var4 instanceof BlockDoor) && var4 != Blocks.standing_sign && var4 != Blocks.ladder && var4 != Blocks.reeds) ? ((var4.blockMaterial == Material.portal) ? true : var4.blockMaterial.blocksMovement()) : true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176371_a(World worldIn, BlockPos p_176371_2_, int p_176371_3_) {
/* 270 */     int var4 = func_176362_e((IBlockAccess)worldIn, p_176371_2_);
/*     */     
/* 272 */     if (var4 < 0)
/*     */     {
/* 274 */       return p_176371_3_;
/*     */     }
/*     */ 
/*     */     
/* 278 */     if (var4 == 0)
/*     */     {
/* 280 */       this.field_149815_a++;
/*     */     }
/*     */     
/* 283 */     if (var4 >= 8)
/*     */     {
/* 285 */       var4 = 0;
/*     */     }
/*     */     
/* 288 */     return (p_176371_3_ >= 0 && var4 >= p_176371_3_) ? p_176371_3_ : var4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_176373_h(World worldIn, BlockPos p_176373_2_, IBlockState p_176373_3_) {
/* 294 */     Material var4 = p_176373_3_.getBlock().getMaterial();
/* 295 */     return (var4 != this.blockMaterial && var4 != Material.lava && !func_176372_g(worldIn, p_176373_2_, p_176373_3_));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 300 */     if (!func_176365_e(worldIn, pos, state))
/*     */     {
/* 302 */       worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockDynamicLiquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */