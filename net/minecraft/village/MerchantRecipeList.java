/*     */ package net.minecraft.village;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ 
/*     */ public class MerchantRecipeList
/*     */   extends ArrayList {
/*     */   private static final String __OBFID = "CL_00000127";
/*     */   
/*     */   public MerchantRecipeList() {}
/*     */   
/*     */   public MerchantRecipeList(NBTTagCompound p_i1944_1_) {
/*  18 */     readRecipiesFromTags(p_i1944_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MerchantRecipe canRecipeBeUsed(ItemStack p_77203_1_, ItemStack p_77203_2_, int p_77203_3_) {
/*  26 */     if (p_77203_3_ > 0 && p_77203_3_ < size()) {
/*     */       
/*  28 */       MerchantRecipe var6 = (MerchantRecipe)get(p_77203_3_);
/*  29 */       return (ItemStack.areItemsEqual(p_77203_1_, var6.getItemToBuy()) && ((p_77203_2_ == null && !var6.hasSecondItemToBuy()) || (var6.hasSecondItemToBuy() && ItemStack.areItemsEqual(p_77203_2_, var6.getSecondItemToBuy()))) && p_77203_1_.stackSize >= (var6.getItemToBuy()).stackSize && (!var6.hasSecondItemToBuy() || p_77203_2_.stackSize >= (var6.getSecondItemToBuy()).stackSize)) ? var6 : null;
/*     */     } 
/*     */ 
/*     */     
/*  33 */     for (int var4 = 0; var4 < size(); var4++) {
/*     */       
/*  35 */       MerchantRecipe var5 = (MerchantRecipe)get(var4);
/*     */       
/*  37 */       if (ItemStack.areItemsEqual(p_77203_1_, var5.getItemToBuy()) && p_77203_1_.stackSize >= (var5.getItemToBuy()).stackSize && ((!var5.hasSecondItemToBuy() && p_77203_2_ == null) || (var5.hasSecondItemToBuy() && ItemStack.areItemsEqual(p_77203_2_, var5.getSecondItemToBuy()) && p_77203_2_.stackSize >= (var5.getSecondItemToBuy()).stackSize)))
/*     */       {
/*  39 */         return var5;
/*     */       }
/*     */     } 
/*     */     
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_151391_a(PacketBuffer p_151391_1_) {
/*  49 */     p_151391_1_.writeByte((byte)(size() & 0xFF));
/*     */     
/*  51 */     for (int var2 = 0; var2 < size(); var2++) {
/*     */       
/*  53 */       MerchantRecipe var3 = (MerchantRecipe)get(var2);
/*  54 */       p_151391_1_.writeItemStackToBuffer(var3.getItemToBuy());
/*  55 */       p_151391_1_.writeItemStackToBuffer(var3.getItemToSell());
/*  56 */       ItemStack var4 = var3.getSecondItemToBuy();
/*  57 */       p_151391_1_.writeBoolean((var4 != null));
/*     */       
/*  59 */       if (var4 != null)
/*     */       {
/*  61 */         p_151391_1_.writeItemStackToBuffer(var4);
/*     */       }
/*     */       
/*  64 */       p_151391_1_.writeBoolean(var3.isRecipeDisabled());
/*  65 */       p_151391_1_.writeInt(var3.func_180321_e());
/*  66 */       p_151391_1_.writeInt(var3.func_180320_f());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static MerchantRecipeList func_151390_b(PacketBuffer p_151390_0_) throws IOException {
/*  72 */     MerchantRecipeList var1 = new MerchantRecipeList();
/*  73 */     int var2 = p_151390_0_.readByte() & 0xFF;
/*     */     
/*  75 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/*  77 */       ItemStack var4 = p_151390_0_.readItemStackFromBuffer();
/*  78 */       ItemStack var5 = p_151390_0_.readItemStackFromBuffer();
/*  79 */       ItemStack var6 = null;
/*     */       
/*  81 */       if (p_151390_0_.readBoolean())
/*     */       {
/*  83 */         var6 = p_151390_0_.readItemStackFromBuffer();
/*     */       }
/*     */       
/*  86 */       boolean var7 = p_151390_0_.readBoolean();
/*  87 */       int var8 = p_151390_0_.readInt();
/*  88 */       int var9 = p_151390_0_.readInt();
/*  89 */       MerchantRecipe var10 = new MerchantRecipe(var4, var6, var5, var8, var9);
/*     */       
/*  91 */       if (var7)
/*     */       {
/*  93 */         var10.func_82785_h();
/*     */       }
/*     */       
/*  96 */       var1.add((E)var10);
/*     */     } 
/*     */     
/*  99 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readRecipiesFromTags(NBTTagCompound p_77201_1_) {
/* 104 */     NBTTagList var2 = p_77201_1_.getTagList("Recipes", 10);
/*     */     
/* 106 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 108 */       NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 109 */       add((E)new MerchantRecipe(var4));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound getRecipiesAsTags() {
/* 115 */     NBTTagCompound var1 = new NBTTagCompound();
/* 116 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 118 */     for (int var3 = 0; var3 < size(); var3++) {
/*     */       
/* 120 */       MerchantRecipe var4 = (MerchantRecipe)get(var3);
/* 121 */       var2.appendTag((NBTBase)var4.writeToTags());
/*     */     } 
/*     */     
/* 124 */     var1.setTag("Recipes", (NBTBase)var2);
/* 125 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\village\MerchantRecipeList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */