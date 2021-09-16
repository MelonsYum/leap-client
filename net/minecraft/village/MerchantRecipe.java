/*     */ package net.minecraft.village;
/*     */ 
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
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
/*     */ public class MerchantRecipe
/*     */ {
/*     */   private ItemStack itemToBuy;
/*     */   private ItemStack secondItemToBuy;
/*     */   private ItemStack itemToSell;
/*     */   private int toolUses;
/*     */   private int maxTradeUses;
/*     */   private boolean field_180323_f;
/*     */   private static final String __OBFID = "CL_00000126";
/*     */   
/*     */   public MerchantRecipe(NBTTagCompound p_i1940_1_) {
/*  30 */     readFromTags(p_i1940_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe(ItemStack p_i1941_1_, ItemStack p_i1941_2_, ItemStack p_i1941_3_) {
/*  35 */     this(p_i1941_1_, p_i1941_2_, p_i1941_3_, 0, 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe(ItemStack p_i45760_1_, ItemStack p_i45760_2_, ItemStack p_i45760_3_, int p_i45760_4_, int p_i45760_5_) {
/*  40 */     this.itemToBuy = p_i45760_1_;
/*  41 */     this.secondItemToBuy = p_i45760_2_;
/*  42 */     this.itemToSell = p_i45760_3_;
/*  43 */     this.toolUses = p_i45760_4_;
/*  44 */     this.maxTradeUses = p_i45760_5_;
/*  45 */     this.field_180323_f = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe(ItemStack p_i1942_1_, ItemStack p_i1942_2_) {
/*  50 */     this(p_i1942_1_, null, p_i1942_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe(ItemStack p_i1943_1_, Item p_i1943_2_) {
/*  55 */     this(p_i1943_1_, new ItemStack(p_i1943_2_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItemToBuy() {
/*  63 */     return this.itemToBuy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getSecondItemToBuy() {
/*  71 */     return this.secondItemToBuy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSecondItemToBuy() {
/*  79 */     return (this.secondItemToBuy != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItemToSell() {
/*  87 */     return this.itemToSell;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_180321_e() {
/*  92 */     return this.toolUses;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_180320_f() {
/*  97 */     return this.maxTradeUses;
/*     */   }
/*     */ 
/*     */   
/*     */   public void incrementToolUses() {
/* 102 */     this.toolUses++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82783_a(int p_82783_1_) {
/* 107 */     this.maxTradeUses += p_82783_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRecipeDisabled() {
/* 112 */     return (this.toolUses >= this.maxTradeUses);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82785_h() {
/* 117 */     this.toolUses = this.maxTradeUses;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180322_j() {
/* 122 */     return this.field_180323_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromTags(NBTTagCompound p_77390_1_) {
/* 127 */     NBTTagCompound var2 = p_77390_1_.getCompoundTag("buy");
/* 128 */     this.itemToBuy = ItemStack.loadItemStackFromNBT(var2);
/* 129 */     NBTTagCompound var3 = p_77390_1_.getCompoundTag("sell");
/* 130 */     this.itemToSell = ItemStack.loadItemStackFromNBT(var3);
/*     */     
/* 132 */     if (p_77390_1_.hasKey("buyB", 10))
/*     */     {
/* 134 */       this.secondItemToBuy = ItemStack.loadItemStackFromNBT(p_77390_1_.getCompoundTag("buyB"));
/*     */     }
/*     */     
/* 137 */     if (p_77390_1_.hasKey("uses", 99))
/*     */     {
/* 139 */       this.toolUses = p_77390_1_.getInteger("uses");
/*     */     }
/*     */     
/* 142 */     if (p_77390_1_.hasKey("maxUses", 99)) {
/*     */       
/* 144 */       this.maxTradeUses = p_77390_1_.getInteger("maxUses");
/*     */     }
/*     */     else {
/*     */       
/* 148 */       this.maxTradeUses = 7;
/*     */     } 
/*     */     
/* 151 */     if (p_77390_1_.hasKey("rewardExp", 1)) {
/*     */       
/* 153 */       this.field_180323_f = p_77390_1_.getBoolean("rewardExp");
/*     */     }
/*     */     else {
/*     */       
/* 157 */       this.field_180323_f = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeToTags() {
/* 163 */     NBTTagCompound var1 = new NBTTagCompound();
/* 164 */     var1.setTag("buy", (NBTBase)this.itemToBuy.writeToNBT(new NBTTagCompound()));
/* 165 */     var1.setTag("sell", (NBTBase)this.itemToSell.writeToNBT(new NBTTagCompound()));
/*     */     
/* 167 */     if (this.secondItemToBuy != null)
/*     */     {
/* 169 */       var1.setTag("buyB", (NBTBase)this.secondItemToBuy.writeToNBT(new NBTTagCompound()));
/*     */     }
/*     */     
/* 172 */     var1.setInteger("uses", this.toolUses);
/* 173 */     var1.setInteger("maxUses", this.maxTradeUses);
/* 174 */     var1.setBoolean("rewardExp", this.field_180323_f);
/* 175 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\village\MerchantRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */