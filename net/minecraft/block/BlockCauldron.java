/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntityBanner;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCauldron
/*     */   extends Block
/*     */ {
/*  28 */   public static final PropertyInteger field_176591_a = PropertyInteger.create("level", 0, 3);
/*     */   
/*     */   private static final String __OBFID = "CL_00000213";
/*     */   
/*     */   public BlockCauldron() {
/*  33 */     super(Material.iron);
/*  34 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176591_a, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/*  44 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
/*  45 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  46 */     float var7 = 0.125F;
/*  47 */     setBlockBounds(0.0F, 0.0F, 0.0F, var7, 1.0F, 1.0F);
/*  48 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  49 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var7);
/*  50 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  51 */     setBlockBounds(1.0F - var7, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  52 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  53 */     setBlockBounds(0.0F, 0.0F, 1.0F - var7, 1.0F, 1.0F, 1.0F);
/*  54 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  55 */     setBlockBoundsForItemRender();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  63 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
/*  81 */     int var5 = ((Integer)state.getValue((IProperty)field_176591_a)).intValue();
/*  82 */     float var6 = pos.getY() + (6.0F + (3 * var5)) / 16.0F;
/*     */     
/*  84 */     if (!worldIn.isRemote && entityIn.isBurning() && var5 > 0 && (entityIn.getEntityBoundingBox()).minY <= var6) {
/*     */       
/*  86 */       entityIn.extinguish();
/*  87 */       func_176590_a(worldIn, pos, state, var5 - 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  93 */     if (worldIn.isRemote)
/*     */     {
/*  95 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  99 */     ItemStack var9 = playerIn.inventory.getCurrentItem();
/*     */     
/* 101 */     if (var9 == null)
/*     */     {
/* 103 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 107 */     int var10 = ((Integer)state.getValue((IProperty)field_176591_a)).intValue();
/* 108 */     Item var11 = var9.getItem();
/*     */     
/* 110 */     if (var11 == Items.water_bucket) {
/*     */       
/* 112 */       if (var10 < 3) {
/*     */         
/* 114 */         if (!playerIn.capabilities.isCreativeMode)
/*     */         {
/* 116 */           playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, new ItemStack(Items.bucket));
/*     */         }
/*     */         
/* 119 */         func_176590_a(worldIn, pos, state, 3);
/*     */       } 
/*     */       
/* 122 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     if (var11 == Items.glass_bottle) {
/*     */       
/* 130 */       if (var10 > 0) {
/*     */         
/* 132 */         if (!playerIn.capabilities.isCreativeMode) {
/*     */           
/* 134 */           ItemStack var13 = new ItemStack((Item)Items.potionitem, 1, 0);
/*     */           
/* 136 */           if (!playerIn.inventory.addItemStackToInventory(var13)) {
/*     */             
/* 138 */             worldIn.spawnEntityInWorld((Entity)new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, var13));
/*     */           }
/* 140 */           else if (playerIn instanceof EntityPlayerMP) {
/*     */             
/* 142 */             ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
/*     */           } 
/*     */           
/* 145 */           var9.stackSize--;
/*     */           
/* 147 */           if (var9.stackSize <= 0)
/*     */           {
/* 149 */             playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
/*     */           }
/*     */         } 
/*     */         
/* 153 */         func_176590_a(worldIn, pos, state, var10 - 1);
/*     */       } 
/*     */       
/* 156 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 160 */     if (var10 > 0 && var11 instanceof ItemArmor) {
/*     */       
/* 162 */       ItemArmor var12 = (ItemArmor)var11;
/*     */       
/* 164 */       if (var12.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER && var12.hasColor(var9)) {
/*     */         
/* 166 */         var12.removeColor(var9);
/* 167 */         func_176590_a(worldIn, pos, state, var10 - 1);
/* 168 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 172 */     if (var10 > 0 && var11 instanceof net.minecraft.item.ItemBanner && TileEntityBanner.func_175113_c(var9) > 0) {
/*     */       
/* 174 */       ItemStack var13 = var9.copy();
/* 175 */       var13.stackSize = 1;
/* 176 */       TileEntityBanner.func_175117_e(var13);
/*     */       
/* 178 */       if (var9.stackSize <= 1 && !playerIn.capabilities.isCreativeMode) {
/*     */         
/* 180 */         playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, var13);
/*     */       }
/*     */       else {
/*     */         
/* 184 */         if (!playerIn.inventory.addItemStackToInventory(var13)) {
/*     */           
/* 186 */           worldIn.spawnEntityInWorld((Entity)new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, var13));
/*     */         }
/* 188 */         else if (playerIn instanceof EntityPlayerMP) {
/*     */           
/* 190 */           ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
/*     */         } 
/*     */         
/* 193 */         if (!playerIn.capabilities.isCreativeMode)
/*     */         {
/* 195 */           var9.stackSize--;
/*     */         }
/*     */       } 
/*     */       
/* 199 */       if (!playerIn.capabilities.isCreativeMode)
/*     */       {
/* 201 */         func_176590_a(worldIn, pos, state, var10 - 1);
/*     */       }
/*     */       
/* 204 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 208 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176590_a(World worldIn, BlockPos p_176590_2_, IBlockState p_176590_3_, int p_176590_4_) {
/* 218 */     worldIn.setBlockState(p_176590_2_, p_176590_3_.withProperty((IProperty)field_176591_a, Integer.valueOf(MathHelper.clamp_int(p_176590_4_, 0, 3))), 2);
/* 219 */     worldIn.updateComparatorOutputLevel(p_176590_2_, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillWithRain(World worldIn, BlockPos pos) {
/* 227 */     if (worldIn.rand.nextInt(20) == 1) {
/*     */       
/* 229 */       IBlockState var3 = worldIn.getBlockState(pos);
/*     */       
/* 231 */       if (((Integer)var3.getValue((IProperty)field_176591_a)).intValue() < 3)
/*     */       {
/* 233 */         worldIn.setBlockState(pos, var3.cycleProperty((IProperty)field_176591_a), 2);
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
/* 245 */     return Items.cauldron;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 250 */     return Items.cauldron;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 255 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 260 */     return ((Integer)worldIn.getBlockState(pos).getValue((IProperty)field_176591_a)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 268 */     return getDefaultState().withProperty((IProperty)field_176591_a, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 276 */     return ((Integer)state.getValue((IProperty)field_176591_a)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 281 */     return new BlockState(this, new IProperty[] { (IProperty)field_176591_a });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockCauldron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */