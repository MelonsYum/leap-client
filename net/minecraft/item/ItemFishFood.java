/*     */ package net.minecraft.item;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.potion.PotionHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class ItemFishFood
/*     */   extends ItemFood
/*     */ {
/*     */   private final boolean cooked;
/*     */   private static final String __OBFID = "CL_00000032";
/*     */   
/*     */   public ItemFishFood(boolean p_i45338_1_) {
/*  21 */     super(0, 0.0F, false);
/*  22 */     this.cooked = p_i45338_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHealAmount(ItemStack itemStackIn) {
/*  27 */     FishType var2 = FishType.getFishTypeForItemStack(itemStackIn);
/*  28 */     return (this.cooked && var2.getCookable()) ? var2.getCookedHealAmount() : var2.getUncookedHealAmount();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSaturationModifier(ItemStack itemStackIn) {
/*  33 */     FishType var2 = FishType.getFishTypeForItemStack(itemStackIn);
/*  34 */     return (this.cooked && var2.getCookable()) ? var2.getCookedSaturationModifier() : var2.getUncookedSaturationModifier();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPotionEffect(ItemStack stack) {
/*  39 */     return (FishType.getFishTypeForItemStack(stack) == FishType.PUFFERFISH) ? PotionHelper.field_151423_m : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onFoodEaten(ItemStack p_77849_1_, World worldIn, EntityPlayer p_77849_3_) {
/*  44 */     FishType var4 = FishType.getFishTypeForItemStack(p_77849_1_);
/*     */     
/*  46 */     if (var4 == FishType.PUFFERFISH) {
/*     */       
/*  48 */       p_77849_3_.addPotionEffect(new PotionEffect(Potion.poison.id, 1200, 3));
/*  49 */       p_77849_3_.addPotionEffect(new PotionEffect(Potion.hunger.id, 300, 2));
/*  50 */       p_77849_3_.addPotionEffect(new PotionEffect(Potion.confusion.id, 300, 1));
/*     */     } 
/*     */     
/*  53 */     super.onFoodEaten(p_77849_1_, worldIn, p_77849_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
/*  63 */     FishType[] var4 = FishType.values();
/*  64 */     int var5 = var4.length;
/*     */     
/*  66 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/*  68 */       FishType var7 = var4[var6];
/*     */       
/*  70 */       if (!this.cooked || var7.getCookable())
/*     */       {
/*  72 */         subItems.add(new ItemStack(this, 1, var7.getItemDamage()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnlocalizedName(ItemStack stack) {
/*  83 */     FishType var2 = FishType.getFishTypeForItemStack(stack);
/*  84 */     return String.valueOf(getUnlocalizedName()) + "." + var2.getUnlocalizedNamePart() + "." + ((this.cooked && var2.getCookable()) ? "cooked" : "raw");
/*     */   }
/*     */   
/*     */   public enum FishType
/*     */   {
/*  89 */     COD("COD", 0, 0, "cod", 2, 0.1F, 5, 0.6F),
/*  90 */     SALMON("SALMON", 1, 1, "salmon", 2, 0.1F, 6, 0.8F),
/*  91 */     CLOWNFISH("CLOWNFISH", 2, 2, "clownfish", 1, 0.1F),
/*  92 */     PUFFERFISH("PUFFERFISH", 3, 3, "pufferfish", 1, 0.1F);
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 174 */       FishType[] var0 = values();
/* 175 */       int var1 = var0.length;
/*     */       
/* 177 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 179 */         FishType var3 = var0[var2];
/* 180 */         itemDamageToFishTypeMap.put(Integer.valueOf(var3.getItemDamage()), var3);
/*     */       } 
/*     */     }
/*     */     
/*     */     private boolean cookable = false;
/*     */     private static final Map itemDamageToFishTypeMap = Maps.newHashMap();
/*     */     private final int itemDamage;
/*     */     private final String unlocalizedNamePart;
/*     */     private final int uncookedHealAmount;
/*     */     private final float uncookedSaturationModifier;
/*     */     private final int cookedHealAmount;
/*     */     private final float cookedSaturationModifier;
/*     */     private static final FishType[] $VALUES = new FishType[] { COD, SALMON, CLOWNFISH, PUFFERFISH };
/*     */     private static final String __OBFID = "CL_00000033";
/*     */     
/*     */     FishType(String p_i45336_1_, int p_i45336_2_, int p_i45336_3_, String p_i45336_4_, int p_i45336_5_, float p_i45336_6_, int p_i45336_7_, float p_i45336_8_) {
/*     */       this.itemDamage = p_i45336_3_;
/*     */       this.unlocalizedNamePart = p_i45336_4_;
/*     */       this.uncookedHealAmount = p_i45336_5_;
/*     */       this.uncookedSaturationModifier = p_i45336_6_;
/*     */       this.cookedHealAmount = p_i45336_7_;
/*     */       this.cookedSaturationModifier = p_i45336_8_;
/*     */       this.cookable = true;
/*     */     }
/*     */     
/*     */     FishType(String p_i45337_1_, int p_i45337_2_, int p_i45337_3_, String p_i45337_4_, int p_i45337_5_, float p_i45337_6_) {
/*     */       this.itemDamage = p_i45337_3_;
/*     */       this.unlocalizedNamePart = p_i45337_4_;
/*     */       this.uncookedHealAmount = p_i45337_5_;
/*     */       this.uncookedSaturationModifier = p_i45337_6_;
/*     */       this.cookedHealAmount = 0;
/*     */       this.cookedSaturationModifier = 0.0F;
/*     */       this.cookable = false;
/*     */     }
/*     */     
/*     */     public int getItemDamage() {
/*     */       return this.itemDamage;
/*     */     }
/*     */     
/*     */     public String getUnlocalizedNamePart() {
/*     */       return this.unlocalizedNamePart;
/*     */     }
/*     */     
/*     */     public int getUncookedHealAmount() {
/*     */       return this.uncookedHealAmount;
/*     */     }
/*     */     
/*     */     public float getUncookedSaturationModifier() {
/*     */       return this.uncookedSaturationModifier;
/*     */     }
/*     */     
/*     */     public int getCookedHealAmount() {
/*     */       return this.cookedHealAmount;
/*     */     }
/*     */     
/*     */     public float getCookedSaturationModifier() {
/*     */       return this.cookedSaturationModifier;
/*     */     }
/*     */     
/*     */     public boolean getCookable() {
/*     */       return this.cookable;
/*     */     }
/*     */     
/*     */     public static FishType getFishTypeForItemDamage(int p_150974_0_) {
/*     */       FishType var1 = (FishType)itemDamageToFishTypeMap.get(Integer.valueOf(p_150974_0_));
/*     */       return (var1 == null) ? COD : var1;
/*     */     }
/*     */     
/*     */     public static FishType getFishTypeForItemStack(ItemStack p_150978_0_) {
/*     */       return (p_150978_0_.getItem() instanceof ItemFishFood) ? getFishTypeForItemDamage(p_150978_0_.getMetadata()) : COD;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemFishFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */