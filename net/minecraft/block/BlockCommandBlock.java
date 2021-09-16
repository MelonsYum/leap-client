/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.command.server.CommandBlockLogic;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityCommandBlock;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCommandBlock
/*     */   extends BlockContainer {
/*  21 */   public static final PropertyBool TRIGGERED_PROP = PropertyBool.create("triggered");
/*     */   
/*     */   private static final String __OBFID = "CL_00000219";
/*     */   
/*     */   public BlockCommandBlock() {
/*  26 */     super(Material.iron);
/*  27 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)TRIGGERED_PROP, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/*  35 */     return (TileEntity)new TileEntityCommandBlock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  40 */     if (!worldIn.isRemote) {
/*     */       
/*  42 */       boolean var5 = worldIn.isBlockPowered(pos);
/*  43 */       boolean var6 = ((Boolean)state.getValue((IProperty)TRIGGERED_PROP)).booleanValue();
/*     */       
/*  45 */       if (var5 && !var6) {
/*     */         
/*  47 */         worldIn.setBlockState(pos, state.withProperty((IProperty)TRIGGERED_PROP, Boolean.valueOf(true)), 4);
/*  48 */         worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/*     */       }
/*  50 */       else if (!var5 && var6) {
/*     */         
/*  52 */         worldIn.setBlockState(pos, state.withProperty((IProperty)TRIGGERED_PROP, Boolean.valueOf(false)), 4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  59 */     TileEntity var5 = worldIn.getTileEntity(pos);
/*     */     
/*  61 */     if (var5 instanceof TileEntityCommandBlock) {
/*     */       
/*  63 */       ((TileEntityCommandBlock)var5).getCommandBlockLogic().trigger(worldIn);
/*  64 */       worldIn.updateComparatorOutputLevel(pos, this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/*  73 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  78 */     TileEntity var9 = worldIn.getTileEntity(pos);
/*  79 */     return (var9 instanceof TileEntityCommandBlock) ? ((TileEntityCommandBlock)var9).getCommandBlockLogic().func_175574_a(playerIn) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/*  89 */     TileEntity var3 = worldIn.getTileEntity(pos);
/*  90 */     return (var3 instanceof TileEntityCommandBlock) ? ((TileEntityCommandBlock)var3).getCommandBlockLogic().getSuccessCount() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/*  95 */     TileEntity var6 = worldIn.getTileEntity(pos);
/*     */     
/*  97 */     if (var6 instanceof TileEntityCommandBlock) {
/*     */       
/*  99 */       CommandBlockLogic var7 = ((TileEntityCommandBlock)var6).getCommandBlockLogic();
/*     */       
/* 101 */       if (stack.hasDisplayName())
/*     */       {
/* 103 */         var7.func_145754_b(stack.getDisplayName());
/*     */       }
/*     */       
/* 106 */       if (!worldIn.isRemote)
/*     */       {
/* 108 */         var7.func_175573_a(worldIn.getGameRules().getGameRuleBooleanValue("sendCommandFeedback"));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 118 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 126 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 134 */     return getDefaultState().withProperty((IProperty)TRIGGERED_PROP, Boolean.valueOf(((meta & 0x1) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 142 */     int var2 = 0;
/*     */     
/* 144 */     if (((Boolean)state.getValue((IProperty)TRIGGERED_PROP)).booleanValue())
/*     */     {
/* 146 */       var2 |= 0x1;
/*     */     }
/*     */     
/* 149 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 154 */     return new BlockState(this, new IProperty[] { (IProperty)TRIGGERED_PROP });
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 159 */     return getDefaultState().withProperty((IProperty)TRIGGERED_PROP, Boolean.valueOf(false));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */