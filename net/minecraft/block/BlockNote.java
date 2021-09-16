/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityNote;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockNote
/*     */   extends BlockContainer {
/*  18 */   private static final List field_176434_a = Lists.newArrayList((Object[])new String[] { "harp", "bd", "snare", "hat", "bassattack" });
/*     */   
/*     */   private static final String __OBFID = "CL_00000278";
/*     */   
/*     */   public BlockNote() {
/*  23 */     super(Material.wood);
/*  24 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  29 */     boolean var5 = worldIn.isBlockPowered(pos);
/*  30 */     TileEntity var6 = worldIn.getTileEntity(pos);
/*     */     
/*  32 */     if (var6 instanceof TileEntityNote) {
/*     */       
/*  34 */       TileEntityNote var7 = (TileEntityNote)var6;
/*     */       
/*  36 */       if (var7.previousRedstoneState != var5) {
/*     */         
/*  38 */         if (var5)
/*     */         {
/*  40 */           var7.func_175108_a(worldIn, pos);
/*     */         }
/*     */         
/*  43 */         var7.previousRedstoneState = var5;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  50 */     if (worldIn.isRemote)
/*     */     {
/*  52 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  56 */     TileEntity var9 = worldIn.getTileEntity(pos);
/*     */     
/*  58 */     if (var9 instanceof TileEntityNote) {
/*     */       
/*  60 */       TileEntityNote var10 = (TileEntityNote)var9;
/*  61 */       var10.changePitch();
/*  62 */       var10.func_175108_a(worldIn, pos);
/*     */     } 
/*     */     
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
/*  71 */     if (!worldIn.isRemote) {
/*     */       
/*  73 */       TileEntity var4 = worldIn.getTileEntity(pos);
/*     */       
/*  75 */       if (var4 instanceof TileEntityNote)
/*     */       {
/*  77 */         ((TileEntityNote)var4).func_175108_a(worldIn, pos);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/*  87 */     return (TileEntity)new TileEntityNote();
/*     */   }
/*     */ 
/*     */   
/*     */   private String func_176433_b(int p_176433_1_) {
/*  92 */     if (p_176433_1_ < 0 || p_176433_1_ >= field_176434_a.size())
/*     */     {
/*  94 */       p_176433_1_ = 0;
/*     */     }
/*     */     
/*  97 */     return field_176434_a.get(p_176433_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
/* 105 */     float var6 = (float)Math.pow(2.0D, (eventParam - 12) / 12.0D);
/* 106 */     worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "note." + func_176433_b(eventID), 3.0F, var6);
/* 107 */     worldIn.spawnParticle(EnumParticleTypes.NOTE, pos.getX() + 0.5D, pos.getY() + 1.2D, pos.getZ() + 0.5D, eventParam / 24.0D, 0.0D, 0.0D, new int[0]);
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 116 */     return 3;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockNote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */