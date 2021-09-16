/*     */ package net.minecraft.block;
/*     */ 
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityBeacon;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.HttpUtil;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ public class BlockBeacon
/*     */   extends BlockContainer {
/*     */   private static final String __OBFID = "CL_00000197";
/*     */   
/*     */   public BlockBeacon() {
/*  26 */     super(Material.glass);
/*  27 */     setHardness(3.0F);
/*  28 */     setCreativeTab(CreativeTabs.tabMisc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/*  36 */     return (TileEntity)new TileEntityBeacon();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  41 */     if (worldIn.isRemote)
/*     */     {
/*  43 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  47 */     TileEntity var9 = worldIn.getTileEntity(pos);
/*     */     
/*  49 */     if (var9 instanceof TileEntityBeacon)
/*     */     {
/*  51 */       playerIn.displayGUIChest((IInventory)var9);
/*     */     }
/*     */     
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  73 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/*  78 */     super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
/*     */     
/*  80 */     if (stack.hasDisplayName()) {
/*     */       
/*  82 */       TileEntity var6 = worldIn.getTileEntity(pos);
/*     */       
/*  84 */       if (var6 instanceof TileEntityBeacon)
/*     */       {
/*  86 */         ((TileEntityBeacon)var6).func_145999_a(stack.getDisplayName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  93 */     TileEntity var5 = worldIn.getTileEntity(pos);
/*     */     
/*  95 */     if (var5 instanceof TileEntityBeacon) {
/*     */       
/*  97 */       ((TileEntityBeacon)var5).func_174908_m();
/*  98 */       worldIn.addBlockEvent(pos, this, 1, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 104 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_176450_d(final World worldIn, final BlockPos p_176450_1_) {
/* 109 */     HttpUtil.field_180193_a.submit(new Runnable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002136";
/*     */           
/*     */           public void run() {
/* 114 */             Chunk var1 = worldIn.getChunkFromBlockCoords(p_176450_1_);
/*     */             
/* 116 */             for (int var2 = p_176450_1_.getY() - 1; var2 >= 0; var2--) {
/*     */               
/* 118 */               final BlockPos var3 = new BlockPos(p_176450_1_.getX(), var2, p_176450_1_.getZ());
/*     */               
/* 120 */               if (!var1.canSeeSky(var3)) {
/*     */                 break;
/*     */               }
/*     */ 
/*     */               
/* 125 */               IBlockState var4 = worldIn.getBlockState(var3);
/*     */               
/* 127 */               if (var4.getBlock() == Blocks.beacon)
/*     */               {
/* 129 */                 ((WorldServer)worldIn).addScheduledTask(new Runnable()
/*     */                     {
/*     */                       private static final String __OBFID = "CL_00002135";
/*     */                       
/*     */                       public void run() {
/* 134 */                         TileEntity var1 = worldIn.getTileEntity(var3);
/*     */                         
/* 136 */                         if (var1 instanceof TileEntityBeacon) {
/*     */                           
/* 138 */                           ((TileEntityBeacon)var1).func_174908_m();
/* 139 */                           worldIn.addBlockEvent(var3, Blocks.beacon, 1, 0);
/*     */                         } 
/*     */                       }
/*     */                     });
/*     */               }
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */