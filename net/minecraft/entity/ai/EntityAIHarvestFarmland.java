/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockCrops;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.InventoryBasic;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIHarvestFarmland extends EntityAIMoveToBlock {
/*     */   private final EntityVillager field_179504_c;
/*     */   private boolean field_179502_d;
/*     */   private boolean field_179503_e;
/*     */   private int field_179501_f;
/*     */   private static final String __OBFID = "CL_00002253";
/*     */   
/*     */   public EntityAIHarvestFarmland(EntityVillager p_i45889_1_, double p_i45889_2_) {
/*  24 */     super((EntityCreature)p_i45889_1_, p_i45889_2_, 16);
/*  25 */     this.field_179504_c = p_i45889_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  33 */     if (this.field_179496_a <= 0) {
/*     */       
/*  35 */       if (!this.field_179504_c.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
/*     */       {
/*  37 */         return false;
/*     */       }
/*     */       
/*  40 */       this.field_179501_f = -1;
/*  41 */       this.field_179502_d = this.field_179504_c.func_175556_cs();
/*  42 */       this.field_179503_e = this.field_179504_c.func_175557_cr();
/*     */     } 
/*     */     
/*  45 */     return super.shouldExecute();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  53 */     return (this.field_179501_f >= 0 && super.continueExecuting());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  61 */     super.startExecuting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  69 */     super.resetTask();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  77 */     super.updateTask();
/*  78 */     this.field_179504_c.getLookHelper().setLookPosition(this.field_179494_b.getX() + 0.5D, (this.field_179494_b.getY() + 1), this.field_179494_b.getZ() + 0.5D, 10.0F, this.field_179504_c.getVerticalFaceSpeed());
/*     */     
/*  80 */     if (func_179487_f()) {
/*     */       
/*  82 */       World var1 = this.field_179504_c.worldObj;
/*  83 */       BlockPos var2 = this.field_179494_b.offsetUp();
/*  84 */       IBlockState var3 = var1.getBlockState(var2);
/*  85 */       Block var4 = var3.getBlock();
/*     */       
/*  87 */       if (this.field_179501_f == 0 && var4 instanceof BlockCrops && ((Integer)var3.getValue((IProperty)BlockCrops.AGE)).intValue() == 7) {
/*     */         
/*  89 */         var1.destroyBlock(var2, true);
/*     */       }
/*  91 */       else if (this.field_179501_f == 1 && var4 == Blocks.air) {
/*     */         
/*  93 */         InventoryBasic var5 = this.field_179504_c.func_175551_co();
/*     */         
/*  95 */         for (int var6 = 0; var6 < var5.getSizeInventory(); var6++) {
/*     */           
/*  97 */           ItemStack var7 = var5.getStackInSlot(var6);
/*  98 */           boolean var8 = false;
/*     */           
/* 100 */           if (var7 != null)
/*     */           {
/* 102 */             if (var7.getItem() == Items.wheat_seeds) {
/*     */               
/* 104 */               var1.setBlockState(var2, Blocks.wheat.getDefaultState(), 3);
/* 105 */               var8 = true;
/*     */             }
/* 107 */             else if (var7.getItem() == Items.potato) {
/*     */               
/* 109 */               var1.setBlockState(var2, Blocks.potatoes.getDefaultState(), 3);
/* 110 */               var8 = true;
/*     */             }
/* 112 */             else if (var7.getItem() == Items.carrot) {
/*     */               
/* 114 */               var1.setBlockState(var2, Blocks.carrots.getDefaultState(), 3);
/* 115 */               var8 = true;
/*     */             } 
/*     */           }
/*     */           
/* 119 */           if (var8) {
/*     */             
/* 121 */             var7.stackSize--;
/*     */             
/* 123 */             if (var7.stackSize <= 0)
/*     */             {
/* 125 */               var5.setInventorySlotContents(var6, null);
/*     */             }
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 133 */       this.field_179501_f = -1;
/* 134 */       this.field_179496_a = 10;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_179488_a(World worldIn, BlockPos p_179488_2_) {
/* 140 */     Block var3 = worldIn.getBlockState(p_179488_2_).getBlock();
/*     */     
/* 142 */     if (var3 == Blocks.farmland) {
/*     */       
/* 144 */       p_179488_2_ = p_179488_2_.offsetUp();
/* 145 */       IBlockState var4 = worldIn.getBlockState(p_179488_2_);
/* 146 */       var3 = var4.getBlock();
/*     */       
/* 148 */       if (var3 instanceof BlockCrops && ((Integer)var4.getValue((IProperty)BlockCrops.AGE)).intValue() == 7 && this.field_179503_e && (this.field_179501_f == 0 || this.field_179501_f < 0)) {
/*     */         
/* 150 */         this.field_179501_f = 0;
/* 151 */         return true;
/*     */       } 
/*     */       
/* 154 */       if (var3 == Blocks.air && this.field_179502_d && (this.field_179501_f == 1 || this.field_179501_f < 0)) {
/*     */         
/* 156 */         this.field_179501_f = 1;
/* 157 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 161 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIHarvestFarmland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */