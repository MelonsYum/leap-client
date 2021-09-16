/*     */ package net.minecraft.item;
/*     */ 
/*     */ import com.google.common.collect.HashMultiset;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Multiset;
/*     */ import com.google.common.collect.Multisets;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDirt;
/*     */ import net.minecraft.block.BlockStone;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldSavedData;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.storage.MapData;
/*     */ 
/*     */ public class ItemMap
/*     */   extends ItemMapBase {
/*     */   protected ItemMap() {
/*  29 */     setHasSubtypes(true);
/*     */   }
/*     */   private static final String __OBFID = "CL_00000047";
/*     */   
/*     */   public static MapData loadMapData(int p_150912_0_, World worldIn) {
/*  34 */     String var2 = "map_" + p_150912_0_;
/*  35 */     MapData var3 = (MapData)worldIn.loadItemData(MapData.class, var2);
/*     */     
/*  37 */     if (var3 == null) {
/*     */       
/*  39 */       var3 = new MapData(var2);
/*  40 */       worldIn.setItemData(var2, (WorldSavedData)var3);
/*     */     } 
/*     */     
/*  43 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public MapData getMapData(ItemStack p_77873_1_, World worldIn) {
/*  48 */     String var3 = "map_" + p_77873_1_.getMetadata();
/*  49 */     MapData var4 = (MapData)worldIn.loadItemData(MapData.class, var3);
/*     */     
/*  51 */     if (var4 == null && !worldIn.isRemote) {
/*     */       
/*  53 */       p_77873_1_.setItemDamage(worldIn.getUniqueDataId("map"));
/*  54 */       var3 = "map_" + p_77873_1_.getMetadata();
/*  55 */       var4 = new MapData(var3);
/*  56 */       var4.scale = 3;
/*  57 */       var4.func_176054_a(worldIn.getWorldInfo().getSpawnX(), worldIn.getWorldInfo().getSpawnZ(), var4.scale);
/*  58 */       var4.dimension = (byte)worldIn.provider.getDimensionId();
/*  59 */       var4.markDirty();
/*  60 */       worldIn.setItemData(var3, (WorldSavedData)var4);
/*     */     } 
/*     */     
/*  63 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateMapData(World worldIn, Entity p_77872_2_, MapData p_77872_3_) {
/*  68 */     if (worldIn.provider.getDimensionId() == p_77872_3_.dimension && p_77872_2_ instanceof EntityPlayer) {
/*     */       
/*  70 */       int var4 = 1 << p_77872_3_.scale;
/*  71 */       int var5 = p_77872_3_.xCenter;
/*  72 */       int var6 = p_77872_3_.zCenter;
/*  73 */       int var7 = MathHelper.floor_double(p_77872_2_.posX - var5) / var4 + 64;
/*  74 */       int var8 = MathHelper.floor_double(p_77872_2_.posZ - var6) / var4 + 64;
/*  75 */       int var9 = 128 / var4;
/*     */       
/*  77 */       if (worldIn.provider.getHasNoSky())
/*     */       {
/*  79 */         var9 /= 2;
/*     */       }
/*     */       
/*  82 */       MapData.MapInfo var10 = p_77872_3_.func_82568_a((EntityPlayer)p_77872_2_);
/*  83 */       var10.field_82569_d++;
/*  84 */       boolean var11 = false;
/*     */       
/*  86 */       for (int var12 = var7 - var9 + 1; var12 < var7 + var9; var12++) {
/*     */         
/*  88 */         if ((var12 & 0xF) == (var10.field_82569_d & 0xF) || var11) {
/*     */           
/*  90 */           var11 = false;
/*  91 */           double var13 = 0.0D;
/*     */           
/*  93 */           for (int var15 = var8 - var9 - 1; var15 < var8 + var9; var15++) {
/*     */             
/*  95 */             if (var12 >= 0 && var15 >= -1 && var12 < 128 && var15 < 128) {
/*     */               
/*  97 */               int var16 = var12 - var7;
/*  98 */               int var17 = var15 - var8;
/*  99 */               boolean var18 = (var16 * var16 + var17 * var17 > (var9 - 2) * (var9 - 2));
/* 100 */               int var19 = (var5 / var4 + var12 - 64) * var4;
/* 101 */               int var20 = (var6 / var4 + var15 - 64) * var4;
/* 102 */               HashMultiset var21 = HashMultiset.create();
/* 103 */               Chunk var22 = worldIn.getChunkFromBlockCoords(new BlockPos(var19, 0, var20));
/*     */               
/* 105 */               if (!var22.isEmpty()) {
/*     */                 
/* 107 */                 int var23 = var19 & 0xF;
/* 108 */                 int var24 = var20 & 0xF;
/* 109 */                 int var25 = 0;
/* 110 */                 double var26 = 0.0D;
/*     */ 
/*     */                 
/* 113 */                 if (worldIn.provider.getHasNoSky()) {
/*     */                   
/* 115 */                   int var28 = var19 + var20 * 231871;
/* 116 */                   var28 = var28 * var28 * 31287121 + var28 * 11;
/*     */                   
/* 118 */                   if ((var28 >> 20 & 0x1) == 0) {
/*     */                     
/* 120 */                     var21.add(Blocks.dirt.getMapColor(Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, (Comparable)BlockDirt.DirtType.DIRT)), 10);
/*     */                   }
/*     */                   else {
/*     */                     
/* 124 */                     var21.add(Blocks.stone.getMapColor(Blocks.stone.getDefaultState().withProperty((IProperty)BlockStone.VARIANT_PROP, (Comparable)BlockStone.EnumType.STONE)), 100);
/*     */                   } 
/*     */                   
/* 127 */                   var26 = 100.0D;
/*     */                 }
/*     */                 else {
/*     */                   
/* 131 */                   for (int var28 = 0; var28 < var4; var28++) {
/*     */                     
/* 133 */                     for (int var29 = 0; var29 < var4; var29++) {
/*     */                       
/* 135 */                       int var30 = var22.getHeight(var28 + var23, var29 + var24) + 1;
/* 136 */                       IBlockState var31 = Blocks.air.getDefaultState();
/*     */                       
/* 138 */                       if (var30 > 1) {
/*     */                         
/*     */                         do
/*     */                         {
/* 142 */                           var30--;
/* 143 */                           var31 = var22.getBlockState(new BlockPos(var28 + var23, var30, var29 + var24));
/*     */                         }
/* 145 */                         while (var31.getBlock().getMapColor(var31) == MapColor.airColor && var30 > 0);
/*     */                         
/* 147 */                         if (var30 > 0 && var31.getBlock().getMaterial().isLiquid()) {
/*     */                           Block var33;
/* 149 */                           int var32 = var30 - 1;
/*     */ 
/*     */ 
/*     */                           
/*     */                           do {
/* 154 */                             var33 = var22.getBlock(var28 + var23, var32--, var29 + var24);
/* 155 */                             var25++;
/*     */                           }
/* 157 */                           while (var32 > 0 && var33.getMaterial().isLiquid());
/*     */                         } 
/*     */                       } 
/*     */                       
/* 161 */                       var26 += var30 / (var4 * var4);
/* 162 */                       var21.add(var31.getBlock().getMapColor(var31));
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */                 
/* 167 */                 var25 /= var4 * var4;
/* 168 */                 double var34 = (var26 - var13) * 4.0D / (var4 + 4) + ((var12 + var15 & 0x1) - 0.5D) * 0.4D;
/* 169 */                 byte var35 = 1;
/*     */                 
/* 171 */                 if (var34 > 0.6D)
/*     */                 {
/* 173 */                   var35 = 2;
/*     */                 }
/*     */                 
/* 176 */                 if (var34 < -0.6D)
/*     */                 {
/* 178 */                   var35 = 0;
/*     */                 }
/*     */                 
/* 181 */                 MapColor var36 = (MapColor)Iterables.getFirst((Iterable)Multisets.copyHighestCountFirst((Multiset)var21), MapColor.airColor);
/*     */                 
/* 183 */                 if (var36 == MapColor.waterColor) {
/*     */                   
/* 185 */                   var34 = var25 * 0.1D + (var12 + var15 & 0x1) * 0.2D;
/* 186 */                   var35 = 1;
/*     */                   
/* 188 */                   if (var34 < 0.5D)
/*     */                   {
/* 190 */                     var35 = 2;
/*     */                   }
/*     */                   
/* 193 */                   if (var34 > 0.9D)
/*     */                   {
/* 195 */                     var35 = 0;
/*     */                   }
/*     */                 } 
/*     */                 
/* 199 */                 var13 = var26;
/*     */                 
/* 201 */                 if (var15 >= 0 && var16 * var16 + var17 * var17 < var9 * var9 && (!var18 || (var12 + var15 & 0x1) != 0)) {
/*     */                   
/* 203 */                   byte var37 = p_77872_3_.colors[var12 + var15 * 128];
/* 204 */                   byte var38 = (byte)(var36.colorIndex * 4 + var35);
/*     */                   
/* 206 */                   if (var37 != var38) {
/*     */                     
/* 208 */                     p_77872_3_.colors[var12 + var15 * 128] = var38;
/* 209 */                     p_77872_3_.func_176053_a(var12, var15);
/* 210 */                     var11 = true;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
/* 227 */     if (!worldIn.isRemote) {
/*     */       
/* 229 */       MapData var6 = getMapData(stack, worldIn);
/*     */       
/* 231 */       if (entityIn instanceof EntityPlayer) {
/*     */         
/* 233 */         EntityPlayer var7 = (EntityPlayer)entityIn;
/* 234 */         var6.updateVisiblePlayers(var7, stack);
/*     */       } 
/*     */       
/* 237 */       if (isSelected)
/*     */       {
/* 239 */         updateMapData(worldIn, entityIn, var6);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet createMapDataPacket(ItemStack p_150911_1_, World worldIn, EntityPlayer p_150911_3_) {
/* 246 */     return getMapData(p_150911_1_, worldIn).func_176052_a(p_150911_1_, worldIn, p_150911_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
/* 254 */     if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("map_is_scaling")) {
/*     */       
/* 256 */       MapData var4 = Items.filled_map.getMapData(stack, worldIn);
/* 257 */       stack.setItemDamage(worldIn.getUniqueDataId("map"));
/* 258 */       MapData var5 = new MapData("map_" + stack.getMetadata());
/* 259 */       var5.scale = (byte)(var4.scale + 1);
/*     */       
/* 261 */       if (var5.scale > 4)
/*     */       {
/* 263 */         var5.scale = 4;
/*     */       }
/*     */       
/* 266 */       var5.func_176054_a(var4.xCenter, var4.zCenter, var5.scale);
/* 267 */       var5.dimension = var4.dimension;
/* 268 */       var5.markDirty();
/* 269 */       worldIn.setItemData("map_" + stack.getMetadata(), (WorldSavedData)var5);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
/* 281 */     MapData var5 = getMapData(stack, playerIn.worldObj);
/*     */     
/* 283 */     if (advanced)
/*     */     {
/* 285 */       if (var5 == null) {
/*     */         
/* 287 */         tooltip.add("Unknown map");
/*     */       }
/*     */       else {
/*     */         
/* 291 */         tooltip.add("Scaling at 1:" + (1 << var5.scale));
/* 292 */         tooltip.add("(Level " + var5.scale + "/" + '\004' + ")");
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */