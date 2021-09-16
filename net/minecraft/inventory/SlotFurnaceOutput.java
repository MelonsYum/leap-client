/*     */ package net.minecraft.inventory;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class SlotFurnaceOutput
/*     */   extends Slot {
/*     */   private EntityPlayer thePlayer;
/*     */   private int field_75228_b;
/*     */   private static final String __OBFID = "CL_00002183";
/*     */   
/*     */   public SlotFurnaceOutput(EntityPlayer p_i45793_1_, IInventory p_i45793_2_, int p_i45793_3_, int p_i45793_4_, int p_i45793_5_) {
/*  20 */     super(p_i45793_2_, p_i45793_3_, p_i45793_4_, p_i45793_5_);
/*  21 */     this.thePlayer = p_i45793_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemValid(ItemStack stack) {
/*  29 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack decrStackSize(int p_75209_1_) {
/*  38 */     if (getHasStack())
/*     */     {
/*  40 */       this.field_75228_b += Math.min(p_75209_1_, (getStack()).stackSize);
/*     */     }
/*     */     
/*  43 */     return super.decrStackSize(p_75209_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
/*  48 */     onCrafting(stack);
/*  49 */     super.onPickupFromSlot(playerIn, stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack p_75210_1_, int p_75210_2_) {
/*  58 */     this.field_75228_b += p_75210_2_;
/*  59 */     onCrafting(p_75210_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onCrafting(ItemStack p_75208_1_) {
/*  67 */     p_75208_1_.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75228_b);
/*     */     
/*  69 */     if (!this.thePlayer.worldObj.isRemote) {
/*     */       
/*  71 */       int var2 = this.field_75228_b;
/*  72 */       float var3 = FurnaceRecipes.instance().getSmeltingExperience(p_75208_1_);
/*     */ 
/*     */       
/*  75 */       if (var3 == 0.0F) {
/*     */         
/*  77 */         var2 = 0;
/*     */       }
/*  79 */       else if (var3 < 1.0F) {
/*     */         
/*  81 */         int var4 = MathHelper.floor_float(var2 * var3);
/*     */         
/*  83 */         if (var4 < MathHelper.ceiling_float_int(var2 * var3) && Math.random() < (var2 * var3 - var4))
/*     */         {
/*  85 */           var4++;
/*     */         }
/*     */         
/*  88 */         var2 = var4;
/*     */       } 
/*     */       
/*  91 */       while (var2 > 0) {
/*     */         
/*  93 */         int var4 = EntityXPOrb.getXPSplit(var2);
/*  94 */         var2 -= var4;
/*  95 */         this.thePlayer.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5D, this.thePlayer.posZ + 0.5D, var4));
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     this.field_75228_b = 0;
/*     */     
/* 101 */     if (p_75208_1_.getItem() == Items.iron_ingot)
/*     */     {
/* 103 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.acquireIron);
/*     */     }
/*     */     
/* 106 */     if (p_75208_1_.getItem() == Items.cooked_fish)
/*     */     {
/* 108 */       this.thePlayer.triggerAchievement((StatBase)AchievementList.cookFish);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\SlotFurnaceOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */