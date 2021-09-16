/*     */ package net.minecraft.item;
/*     */ 
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.block.BlockRailBase;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
/*     */ import net.minecraft.dispenser.IBehaviorDispenseItem;
/*     */ import net.minecraft.dispenser.IBlockSource;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemMinecart extends Item {
/*  19 */   private static final IBehaviorDispenseItem dispenserMinecartBehavior = (IBehaviorDispenseItem)new BehaviorDefaultDispenseItem()
/*     */     {
/*  21 */       private final BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();
/*     */       
/*     */       public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/*     */         double var14;
/*  25 */         EnumFacing var3 = BlockDispenser.getFacing(source.getBlockMetadata());
/*  26 */         World var4 = source.getWorld();
/*  27 */         double var5 = source.getX() + var3.getFrontOffsetX() * 1.125D;
/*  28 */         double var7 = Math.floor(source.getY()) + var3.getFrontOffsetY();
/*  29 */         double var9 = source.getZ() + var3.getFrontOffsetZ() * 1.125D;
/*  30 */         BlockPos var11 = source.getBlockPos().offset(var3);
/*  31 */         IBlockState var12 = var4.getBlockState(var11);
/*  32 */         BlockRailBase.EnumRailDirection var13 = (var12.getBlock() instanceof BlockRailBase) ? (BlockRailBase.EnumRailDirection)var12.getValue(((BlockRailBase)var12.getBlock()).func_176560_l()) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/*     */ 
/*     */         
/*  35 */         if (BlockRailBase.func_176563_d(var12)) {
/*     */           
/*  37 */           if (var13.func_177018_c())
/*     */           {
/*  39 */             var14 = 0.6D;
/*     */           }
/*     */           else
/*     */           {
/*  43 */             var14 = 0.1D;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/*  48 */           if (var12.getBlock().getMaterial() != Material.air || !BlockRailBase.func_176563_d(var4.getBlockState(var11.offsetDown())))
/*     */           {
/*  50 */             return this.behaviourDefaultDispenseItem.dispense(source, stack);
/*     */           }
/*     */           
/*  53 */           IBlockState var16 = var4.getBlockState(var11.offsetDown());
/*  54 */           BlockRailBase.EnumRailDirection var17 = (var16.getBlock() instanceof BlockRailBase) ? (BlockRailBase.EnumRailDirection)var16.getValue(((BlockRailBase)var16.getBlock()).func_176560_l()) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/*     */           
/*  56 */           if (var3 != EnumFacing.DOWN && var17.func_177018_c()) {
/*     */             
/*  58 */             var14 = -0.4D;
/*     */           }
/*     */           else {
/*     */             
/*  62 */             var14 = -0.9D;
/*     */           } 
/*     */         } 
/*     */         
/*  66 */         EntityMinecart var18 = EntityMinecart.func_180458_a(var4, var5, var7 + var14, var9, ((ItemMinecart)stack.getItem()).minecartType);
/*     */         
/*  68 */         if (stack.hasDisplayName())
/*     */         {
/*  70 */           var18.setCustomNameTag(stack.getDisplayName());
/*     */         }
/*     */         
/*  73 */         var4.spawnEntityInWorld((Entity)var18);
/*  74 */         stack.splitStack(1);
/*  75 */         return stack;
/*     */       }
/*     */       private static final String __OBFID = "CL_00000050";
/*     */       protected void playDispenseSound(IBlockSource source) {
/*  79 */         source.getWorld().playAuxSFX(1000, source.getBlockPos(), 0);
/*     */       }
/*     */     };
/*     */   
/*     */   private final EntityMinecart.EnumMinecartType minecartType;
/*     */   private static final String __OBFID = "CL_00000049";
/*     */   
/*     */   public ItemMinecart(EntityMinecart.EnumMinecartType p_i45785_1_) {
/*  87 */     this.maxStackSize = 1;
/*  88 */     this.minecartType = p_i45785_1_;
/*  89 */     setCreativeTab(CreativeTabs.tabTransport);
/*  90 */     BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserMinecartBehavior);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 101 */     IBlockState var9 = worldIn.getBlockState(pos);
/*     */     
/* 103 */     if (BlockRailBase.func_176563_d(var9)) {
/*     */       
/* 105 */       if (!worldIn.isRemote) {
/*     */         
/* 107 */         BlockRailBase.EnumRailDirection var10 = (var9.getBlock() instanceof BlockRailBase) ? (BlockRailBase.EnumRailDirection)var9.getValue(((BlockRailBase)var9.getBlock()).func_176560_l()) : BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/* 108 */         double var11 = 0.0D;
/*     */         
/* 110 */         if (var10.func_177018_c())
/*     */         {
/* 112 */           var11 = 0.5D;
/*     */         }
/*     */         
/* 115 */         EntityMinecart var13 = EntityMinecart.func_180458_a(worldIn, pos.getX() + 0.5D, pos.getY() + 0.0625D + var11, pos.getZ() + 0.5D, this.minecartType);
/*     */         
/* 117 */         if (stack.hasDisplayName())
/*     */         {
/* 119 */           var13.setCustomNameTag(stack.getDisplayName());
/*     */         }
/*     */         
/* 122 */         worldIn.spawnEntityInWorld((Entity)var13);
/*     */       } 
/*     */       
/* 125 */       stack.stackSize--;
/* 126 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 130 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */