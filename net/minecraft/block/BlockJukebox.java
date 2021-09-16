/*     */ package net.minecraft.block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockJukebox extends BlockContainer {
/*  22 */   public static final PropertyBool HAS_RECORD = PropertyBool.create("has_record");
/*     */   
/*     */   private static final String __OBFID = "CL_00000260";
/*     */   
/*     */   protected BlockJukebox() {
/*  27 */     super(Material.wood);
/*  28 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)HAS_RECORD, Boolean.valueOf(false)));
/*  29 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  34 */     if (((Boolean)state.getValue((IProperty)HAS_RECORD)).booleanValue()) {
/*     */       
/*  36 */       dropRecord(worldIn, pos, state);
/*  37 */       state = state.withProperty((IProperty)HAS_RECORD, Boolean.valueOf(false));
/*  38 */       worldIn.setBlockState(pos, state, 2);
/*  39 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertRecord(World worldIn, BlockPos pos, IBlockState state, ItemStack recordStack) {
/*  49 */     if (!worldIn.isRemote) {
/*     */       
/*  51 */       TileEntity var5 = worldIn.getTileEntity(pos);
/*     */       
/*  53 */       if (var5 instanceof TileEntityJukebox) {
/*     */         
/*  55 */         ((TileEntityJukebox)var5).setRecord(new ItemStack(recordStack.getItem(), 1, recordStack.getMetadata()));
/*  56 */         worldIn.setBlockState(pos, state.withProperty((IProperty)HAS_RECORD, Boolean.valueOf(true)), 2);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void dropRecord(World worldIn, BlockPos pos, IBlockState state) {
/*  63 */     if (!worldIn.isRemote) {
/*     */       
/*  65 */       TileEntity var4 = worldIn.getTileEntity(pos);
/*     */       
/*  67 */       if (var4 instanceof TileEntityJukebox) {
/*     */         
/*  69 */         TileEntityJukebox var5 = (TileEntityJukebox)var4;
/*  70 */         ItemStack var6 = var5.getRecord();
/*     */         
/*  72 */         if (var6 != null) {
/*     */           
/*  74 */           worldIn.playAuxSFX(1005, pos, 0);
/*  75 */           worldIn.func_175717_a(pos, null);
/*  76 */           var5.setRecord(null);
/*  77 */           float var7 = 0.7F;
/*  78 */           double var8 = (worldIn.rand.nextFloat() * var7) + (1.0F - var7) * 0.5D;
/*  79 */           double var10 = (worldIn.rand.nextFloat() * var7) + (1.0F - var7) * 0.2D + 0.6D;
/*  80 */           double var12 = (worldIn.rand.nextFloat() * var7) + (1.0F - var7) * 0.5D;
/*  81 */           ItemStack var14 = var6.copy();
/*  82 */           EntityItem var15 = new EntityItem(worldIn, pos.getX() + var8, pos.getY() + var10, pos.getZ() + var12, var14);
/*  83 */           var15.setDefaultPickupDelay();
/*  84 */           worldIn.spawnEntityInWorld((Entity)var15);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/*  92 */     dropRecord(worldIn, pos, state);
/*  93 */     super.breakBlock(worldIn, pos, state);
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
/* 104 */     if (!worldIn.isRemote)
/*     */     {
/* 106 */       super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 115 */     return new TileEntityJukebox();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 125 */     TileEntity var3 = worldIn.getTileEntity(pos);
/*     */     
/* 127 */     if (var3 instanceof TileEntityJukebox) {
/*     */       
/* 129 */       ItemStack var4 = ((TileEntityJukebox)var3).getRecord();
/*     */       
/* 131 */       if (var4 != null)
/*     */       {
/* 133 */         return Item.getIdFromItem(var4.getItem()) + 1 - Item.getIdFromItem(Items.record_13);
/*     */       }
/*     */     } 
/*     */     
/* 137 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 145 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 153 */     return getDefaultState().withProperty((IProperty)HAS_RECORD, Boolean.valueOf((meta > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 161 */     return ((Boolean)state.getValue((IProperty)HAS_RECORD)).booleanValue() ? 1 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 166 */     return new BlockState(this, new IProperty[] { (IProperty)HAS_RECORD });
/*     */   }
/*     */   
/*     */   public static class TileEntityJukebox
/*     */     extends TileEntity
/*     */   {
/*     */     private ItemStack record;
/*     */     private static final String __OBFID = "CL_00000261";
/*     */     
/*     */     public void readFromNBT(NBTTagCompound compound) {
/* 176 */       super.readFromNBT(compound);
/*     */       
/* 178 */       if (compound.hasKey("RecordItem", 10)) {
/*     */         
/* 180 */         setRecord(ItemStack.loadItemStackFromNBT(compound.getCompoundTag("RecordItem")));
/*     */       }
/* 182 */       else if (compound.getInteger("Record") > 0) {
/*     */         
/* 184 */         setRecord(new ItemStack(Item.getItemById(compound.getInteger("Record")), 1, 0));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void writeToNBT(NBTTagCompound compound) {
/* 190 */       super.writeToNBT(compound);
/*     */       
/* 192 */       if (getRecord() != null)
/*     */       {
/* 194 */         compound.setTag("RecordItem", (NBTBase)getRecord().writeToNBT(new NBTTagCompound()));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack getRecord() {
/* 200 */       return this.record;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setRecord(ItemStack recordStack) {
/* 205 */       this.record = recordStack;
/* 206 */       markDirty();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockJukebox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */