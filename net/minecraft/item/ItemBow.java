/*     */ package net.minecraft.item;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemBow extends Item {
/*  14 */   public static final String[] bowPullIconNameArray = new String[] { "pulling_0", "pulling_1", "pulling_2" };
/*     */   
/*     */   private static final String __OBFID = "CL_00001777";
/*     */   
/*     */   public ItemBow() {
/*  19 */     this.maxStackSize = 1;
/*  20 */     setMaxDamage(384);
/*  21 */     setCreativeTab(CreativeTabs.tabCombat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft) {
/*  31 */     boolean var5 = !(!playerIn.capabilities.isCreativeMode && EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) <= 0);
/*     */     
/*  33 */     if (var5 || playerIn.inventory.hasItem(Items.arrow)) {
/*     */       
/*  35 */       int var6 = getMaxItemUseDuration(stack) - timeLeft;
/*  36 */       float var7 = var6 / 20.0F;
/*  37 */       var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
/*     */       
/*  39 */       if (var7 < 0.1D) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  44 */       if (var7 > 1.0F)
/*     */       {
/*  46 */         var7 = 1.0F;
/*     */       }
/*     */       
/*  49 */       EntityArrow var8 = new EntityArrow(worldIn, (EntityLivingBase)playerIn, var7 * 2.0F);
/*     */       
/*  51 */       if (var7 == 1.0F)
/*     */       {
/*  53 */         var8.setIsCritical(true);
/*     */       }
/*     */       
/*  56 */       int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
/*     */       
/*  58 */       if (var9 > 0)
/*     */       {
/*  60 */         var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);
/*     */       }
/*     */       
/*  63 */       int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
/*     */       
/*  65 */       if (var10 > 0)
/*     */       {
/*  67 */         var8.setKnockbackStrength(var10);
/*     */       }
/*     */       
/*  70 */       if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
/*     */       {
/*  72 */         var8.setFire(100);
/*     */       }
/*     */       
/*  75 */       stack.damageItem(1, (EntityLivingBase)playerIn);
/*  76 */       worldIn.playSoundAtEntity((Entity)playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
/*     */       
/*  78 */       if (var5) {
/*     */         
/*  80 */         var8.canBePickedUp = 2;
/*     */       }
/*     */       else {
/*     */         
/*  84 */         playerIn.inventory.consumeInventoryItem(Items.arrow);
/*     */       } 
/*     */       
/*  87 */       playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*     */       
/*  89 */       if (!worldIn.isRemote)
/*     */       {
/*  91 */         worldIn.spawnEntityInWorld((Entity)var8);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
/* 102 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack stack) {
/* 110 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack stack) {
/* 118 */     return EnumAction.BOW;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 126 */     if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(Items.arrow))
/*     */     {
/* 128 */       playerIn.setItemInUse(itemStackIn, getMaxItemUseDuration(itemStackIn));
/*     */     }
/*     */     
/* 131 */     return itemStackIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 139 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemBow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */