/*     */ package net.minecraft.item;
/*     */ 
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemFood
/*     */   extends Item
/*     */ {
/*     */   public final int itemUseDuration;
/*     */   private final int healAmount;
/*     */   private final float saturationModifier;
/*     */   private final boolean isWolfsFavoriteMeat;
/*     */   private boolean alwaysEdible;
/*     */   private int potionId;
/*     */   private int potionDuration;
/*     */   private int potionAmplifier;
/*     */   private float potionEffectProbability;
/*     */   private static final String __OBFID = "CL_00000036";
/*     */   
/*     */   public ItemFood(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_) {
/*  43 */     this.itemUseDuration = 32;
/*  44 */     this.healAmount = p_i45339_1_;
/*  45 */     this.isWolfsFavoriteMeat = p_i45339_3_;
/*  46 */     this.saturationModifier = p_i45339_2_;
/*  47 */     setCreativeTab(CreativeTabs.tabFood);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFood(int p_i45340_1_, boolean p_i45340_2_) {
/*  52 */     this(p_i45340_1_, 0.6F, p_i45340_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
/*  61 */     stack.stackSize--;
/*  62 */     playerIn.getFoodStats().addStats(this, stack);
/*  63 */     worldIn.playSoundAtEntity((Entity)playerIn, "random.burp", 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
/*  64 */     onFoodEaten(stack, worldIn, playerIn);
/*  65 */     playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*  66 */     return stack;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onFoodEaten(ItemStack p_77849_1_, World worldIn, EntityPlayer p_77849_3_) {
/*  71 */     if (!worldIn.isRemote && this.potionId > 0 && worldIn.rand.nextFloat() < this.potionEffectProbability)
/*     */     {
/*  73 */       p_77849_3_.addPotionEffect(new PotionEffect(this.potionId, this.potionDuration * 20, this.potionAmplifier));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack stack) {
/*  82 */     return 32;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack stack) {
/*  90 */     return EnumAction.EAT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/*  98 */     if (playerIn.canEat(this.alwaysEdible))
/*     */     {
/* 100 */       playerIn.setItemInUse(itemStackIn, getMaxItemUseDuration(itemStackIn));
/*     */     }
/*     */     
/* 103 */     return itemStackIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHealAmount(ItemStack itemStackIn) {
/* 108 */     return this.healAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSaturationModifier(ItemStack itemStackIn) {
/* 113 */     return this.saturationModifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWolfsFavoriteMeat() {
/* 121 */     return this.isWolfsFavoriteMeat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemFood setPotionEffect(int p_77844_1_, int duration, int amplifier, float probability) {
/* 130 */     this.potionId = p_77844_1_;
/* 131 */     this.potionDuration = duration;
/* 132 */     this.potionAmplifier = amplifier;
/* 133 */     this.potionEffectProbability = probability;
/* 134 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemFood setAlwaysEdible() {
/* 142 */     this.alwaysEdible = true;
/* 143 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */