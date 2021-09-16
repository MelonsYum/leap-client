/*     */ package net.minecraft.block;
/*     */ 
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityTNTPrimed;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockTNT
/*     */   extends Block {
/*  23 */   public static final PropertyBool field_176246_a = PropertyBool.create("explode");
/*     */   
/*     */   private static final String __OBFID = "CL_00000324";
/*     */   
/*     */   public BlockTNT() {
/*  28 */     super(Material.tnt);
/*  29 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176246_a, Boolean.valueOf(false)));
/*  30 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  35 */     super.onBlockAdded(worldIn, pos, state);
/*     */     
/*  37 */     if (worldIn.isBlockPowered(pos)) {
/*     */       
/*  39 */       onBlockDestroyedByPlayer(worldIn, pos, state.withProperty((IProperty)field_176246_a, Boolean.valueOf(true)));
/*  40 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  46 */     if (worldIn.isBlockPowered(pos)) {
/*     */       
/*  48 */       onBlockDestroyedByPlayer(worldIn, pos, state.withProperty((IProperty)field_176246_a, Boolean.valueOf(true)));
/*  49 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
/*  58 */     if (!worldIn.isRemote) {
/*     */       
/*  60 */       EntityTNTPrimed var4 = new EntityTNTPrimed(worldIn, (pos.getX() + 0.5F), (pos.getY() + 0.5F), (pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy());
/*  61 */       var4.fuse = worldIn.rand.nextInt(var4.fuse / 4) + var4.fuse / 8;
/*  62 */       worldIn.spawnEntityInWorld((Entity)var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
/*  71 */     func_180692_a(worldIn, pos, state, (EntityLivingBase)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180692_a(World worldIn, BlockPos p_180692_2_, IBlockState p_180692_3_, EntityLivingBase p_180692_4_) {
/*  76 */     if (!worldIn.isRemote)
/*     */     {
/*  78 */       if (((Boolean)p_180692_3_.getValue((IProperty)field_176246_a)).booleanValue()) {
/*     */         
/*  80 */         EntityTNTPrimed var5 = new EntityTNTPrimed(worldIn, (p_180692_2_.getX() + 0.5F), (p_180692_2_.getY() + 0.5F), (p_180692_2_.getZ() + 0.5F), p_180692_4_);
/*  81 */         worldIn.spawnEntityInWorld((Entity)var5);
/*  82 */         worldIn.playSoundAtEntity((Entity)var5, "game.tnt.primed", 1.0F, 1.0F);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  89 */     if (playerIn.getCurrentEquippedItem() != null) {
/*     */       
/*  91 */       Item var9 = playerIn.getCurrentEquippedItem().getItem();
/*     */       
/*  93 */       if (var9 == Items.flint_and_steel || var9 == Items.fire_charge) {
/*     */         
/*  95 */         func_180692_a(worldIn, pos, state.withProperty((IProperty)field_176246_a, Boolean.valueOf(true)), (EntityLivingBase)playerIn);
/*  96 */         worldIn.setBlockToAir(pos);
/*     */         
/*  98 */         if (var9 == Items.flint_and_steel) {
/*     */           
/* 100 */           playerIn.getCurrentEquippedItem().damageItem(1, (EntityLivingBase)playerIn);
/*     */         }
/* 102 */         else if (!playerIn.capabilities.isCreativeMode) {
/*     */           
/* 104 */           (playerIn.getCurrentEquippedItem()).stackSize--;
/*     */         } 
/*     */         
/* 107 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
/* 119 */     if (!worldIn.isRemote && entityIn instanceof EntityArrow) {
/*     */       
/* 121 */       EntityArrow var5 = (EntityArrow)entityIn;
/*     */       
/* 123 */       if (var5.isBurning()) {
/*     */         
/* 125 */         func_180692_a(worldIn, pos, worldIn.getBlockState(pos).withProperty((IProperty)field_176246_a, Boolean.valueOf(true)), (var5.shootingEntity instanceof EntityLivingBase) ? (EntityLivingBase)var5.shootingEntity : null);
/* 126 */         worldIn.setBlockToAir(pos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDropFromExplosion(Explosion explosionIn) {
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 144 */     return getDefaultState().withProperty((IProperty)field_176246_a, Boolean.valueOf(((meta & 0x1) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 152 */     return ((Boolean)state.getValue((IProperty)field_176246_a)).booleanValue() ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 157 */     return new BlockState(this, new IProperty[] { (IProperty)field_176246_a });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */