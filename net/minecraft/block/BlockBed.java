/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class BlockBed
/*     */   extends BlockDirectional {
/*  26 */   public static final PropertyEnum PART_PROP = PropertyEnum.create("part", EnumPartType.class);
/*  27 */   public static final PropertyBool OCCUPIED_PROP = PropertyBool.create("occupied");
/*     */   
/*     */   private static final String __OBFID = "CL_00000198";
/*     */   
/*     */   public BlockBed() {
/*  32 */     super(Material.cloth);
/*  33 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)PART_PROP, EnumPartType.FOOT).withProperty((IProperty)OCCUPIED_PROP, Boolean.valueOf(false)));
/*  34 */     setBedBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  39 */     if (worldIn.isRemote)
/*     */     {
/*  41 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  45 */     if (state.getValue((IProperty)PART_PROP) != EnumPartType.HEAD) {
/*     */       
/*  47 */       pos = pos.offset((EnumFacing)state.getValue((IProperty)AGE));
/*  48 */       state = worldIn.getBlockState(pos);
/*     */       
/*  50 */       if (state.getBlock() != this)
/*     */       {
/*  52 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  56 */     if (worldIn.provider.canRespawnHere() && worldIn.getBiomeGenForCoords(pos) != BiomeGenBase.hell) {
/*     */       
/*  58 */       if (((Boolean)state.getValue((IProperty)OCCUPIED_PROP)).booleanValue()) {
/*     */         
/*  60 */         EntityPlayer var10 = func_176470_e(worldIn, pos);
/*     */         
/*  62 */         if (var10 != null) {
/*     */           
/*  64 */           playerIn.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
/*  65 */           return true;
/*     */         } 
/*     */         
/*  68 */         state = state.withProperty((IProperty)OCCUPIED_PROP, Boolean.valueOf(false));
/*  69 */         worldIn.setBlockState(pos, state, 4);
/*     */       } 
/*     */       
/*  72 */       EntityPlayer.EnumStatus var11 = playerIn.func_180469_a(pos);
/*     */       
/*  74 */       if (var11 == EntityPlayer.EnumStatus.OK) {
/*     */         
/*  76 */         state = state.withProperty((IProperty)OCCUPIED_PROP, Boolean.valueOf(true));
/*  77 */         worldIn.setBlockState(pos, state, 4);
/*  78 */         return true;
/*     */       } 
/*     */ 
/*     */       
/*  82 */       if (var11 == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
/*     */         
/*  84 */         playerIn.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
/*     */       }
/*  86 */       else if (var11 == EntityPlayer.EnumStatus.NOT_SAFE) {
/*     */         
/*  88 */         playerIn.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
/*     */       } 
/*     */       
/*  91 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  96 */     worldIn.setBlockToAir(pos);
/*  97 */     BlockPos var9 = pos.offset(((EnumFacing)state.getValue((IProperty)AGE)).getOpposite());
/*     */     
/*  99 */     if (worldIn.getBlockState(var9).getBlock() == this)
/*     */     {
/* 101 */       worldIn.setBlockToAir(var9);
/*     */     }
/*     */     
/* 104 */     worldIn.newExplosion(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 5.0F, true, true);
/* 105 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityPlayer func_176470_e(World worldIn, BlockPos p_176470_2_) {
/*     */     EntityPlayer var4;
/* 112 */     Iterator<EntityPlayer> var3 = worldIn.playerEntities.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 117 */       if (!var3.hasNext())
/*     */       {
/* 119 */         return null;
/*     */       }
/*     */       
/* 122 */       var4 = var3.next();
/*     */     }
/* 124 */     while (!var4.isPlayerSleeping() || !var4.playerLocation.equals(p_176470_2_));
/*     */     
/* 126 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 141 */     setBedBounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 146 */     EnumFacing var5 = (EnumFacing)state.getValue((IProperty)AGE);
/*     */     
/* 148 */     if (state.getValue((IProperty)PART_PROP) == EnumPartType.HEAD) {
/*     */       
/* 150 */       if (worldIn.getBlockState(pos.offset(var5.getOpposite())).getBlock() != this)
/*     */       {
/* 152 */         worldIn.setBlockToAir(pos);
/*     */       }
/*     */     }
/* 155 */     else if (worldIn.getBlockState(pos.offset(var5)).getBlock() != this) {
/*     */       
/* 157 */       worldIn.setBlockToAir(pos);
/*     */       
/* 159 */       if (!worldIn.isRemote)
/*     */       {
/* 161 */         dropBlockAsItem(worldIn, pos, state, 0);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 173 */     return (state.getValue((IProperty)PART_PROP) == EnumPartType.HEAD) ? null : Items.bed;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setBedBounds() {
/* 178 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlockPos getSafeExitLocation(World worldIn, BlockPos p_176468_1_, int p_176468_2_) {
/* 186 */     EnumFacing var3 = (EnumFacing)worldIn.getBlockState(p_176468_1_).getValue((IProperty)AGE);
/* 187 */     int var4 = p_176468_1_.getX();
/* 188 */     int var5 = p_176468_1_.getY();
/* 189 */     int var6 = p_176468_1_.getZ();
/*     */     
/* 191 */     for (int var7 = 0; var7 <= 1; var7++) {
/*     */       
/* 193 */       int var8 = var4 - var3.getFrontOffsetX() * var7 - 1;
/* 194 */       int var9 = var6 - var3.getFrontOffsetZ() * var7 - 1;
/* 195 */       int var10 = var8 + 2;
/* 196 */       int var11 = var9 + 2;
/*     */       
/* 198 */       for (int var12 = var8; var12 <= var10; var12++) {
/*     */         
/* 200 */         for (int var13 = var9; var13 <= var11; var13++) {
/*     */           
/* 202 */           BlockPos var14 = new BlockPos(var12, var5, var13);
/*     */           
/* 204 */           if (func_176469_d(worldIn, var14)) {
/*     */             
/* 206 */             if (p_176468_2_ <= 0)
/*     */             {
/* 208 */               return var14;
/*     */             }
/*     */             
/* 211 */             p_176468_2_--;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 217 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean func_176469_d(World worldIn, BlockPos p_176469_1_) {
/* 222 */     return (World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, p_176469_1_.offsetDown()) && !worldIn.getBlockState(p_176469_1_).getBlock().getMaterial().isSolid() && !worldIn.getBlockState(p_176469_1_.offsetUp()).getBlock().getMaterial().isSolid());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/* 233 */     if (state.getValue((IProperty)PART_PROP) == EnumPartType.FOOT)
/*     */     {
/* 235 */       super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMobilityFlag() {
/* 241 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 246 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 251 */     return Items.bed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
/* 256 */     if (playerIn.capabilities.isCreativeMode && state.getValue((IProperty)PART_PROP) == EnumPartType.HEAD) {
/*     */       
/* 258 */       BlockPos var5 = pos.offset(((EnumFacing)state.getValue((IProperty)AGE)).getOpposite());
/*     */       
/* 260 */       if (worldIn.getBlockState(var5).getBlock() == this)
/*     */       {
/* 262 */         worldIn.setBlockToAir(var5);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 272 */     EnumFacing var2 = EnumFacing.getHorizontal(meta);
/* 273 */     return ((meta & 0x8) > 0) ? getDefaultState().withProperty((IProperty)PART_PROP, EnumPartType.HEAD).withProperty((IProperty)AGE, (Comparable)var2).withProperty((IProperty)OCCUPIED_PROP, Boolean.valueOf(((meta & 0x4) > 0))) : getDefaultState().withProperty((IProperty)PART_PROP, EnumPartType.FOOT).withProperty((IProperty)AGE, (Comparable)var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 282 */     if (state.getValue((IProperty)PART_PROP) == EnumPartType.FOOT) {
/*     */       
/* 284 */       IBlockState var4 = worldIn.getBlockState(pos.offset((EnumFacing)state.getValue((IProperty)AGE)));
/*     */       
/* 286 */       if (var4.getBlock() == this)
/*     */       {
/* 288 */         state = state.withProperty((IProperty)OCCUPIED_PROP, var4.getValue((IProperty)OCCUPIED_PROP));
/*     */       }
/*     */     } 
/*     */     
/* 292 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 300 */     byte var2 = 0;
/* 301 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)AGE)).getHorizontalIndex();
/*     */     
/* 303 */     if (state.getValue((IProperty)PART_PROP) == EnumPartType.HEAD) {
/*     */       
/* 305 */       var3 |= 0x8;
/*     */       
/* 307 */       if (((Boolean)state.getValue((IProperty)OCCUPIED_PROP)).booleanValue())
/*     */       {
/* 309 */         var3 |= 0x4;
/*     */       }
/*     */     } 
/*     */     
/* 313 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 318 */     return new BlockState(this, new IProperty[] { (IProperty)AGE, (IProperty)PART_PROP, (IProperty)OCCUPIED_PROP });
/*     */   }
/*     */   
/*     */   public enum EnumPartType
/*     */     implements IStringSerializable {
/* 323 */     HEAD("HEAD", 0, "head"),
/* 324 */     FOOT("FOOT", 1, "foot");
/*     */     
/*     */     private final String field_177036_c;
/* 327 */     private static final EnumPartType[] $VALUES = new EnumPartType[] { HEAD, FOOT };
/*     */     
/*     */     private static final String __OBFID = "CL_00002134";
/*     */     
/*     */     EnumPartType(String p_i45735_1_, int p_i45735_2_, String p_i45735_3_) {
/* 332 */       this.field_177036_c = p_i45735_3_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String toString() {
/* 337 */       return this.field_177036_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 342 */       return this.field_177036_c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */