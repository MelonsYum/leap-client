/*     */ package net.minecraft.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ 
/*     */ public class ItemEnchantedBook
/*     */   extends Item {
/*     */   private static final String __OBFID = "CL_00000025";
/*     */   
/*     */   public boolean hasEffect(ItemStack stack) {
/*  20 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isItemTool(ItemStack stack) {
/*  28 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRarity getRarity(ItemStack stack) {
/*  36 */     return (func_92110_g(stack).tagCount() > 0) ? EnumRarity.UNCOMMON : super.getRarity(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagList func_92110_g(ItemStack p_92110_1_) {
/*  41 */     NBTTagCompound var2 = p_92110_1_.getTagCompound();
/*  42 */     return (var2 != null && var2.hasKey("StoredEnchantments", 9)) ? (NBTTagList)var2.getTag("StoredEnchantments") : new NBTTagList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
/*  53 */     super.addInformation(stack, playerIn, tooltip, advanced);
/*  54 */     NBTTagList var5 = func_92110_g(stack);
/*     */     
/*  56 */     if (var5 != null)
/*     */     {
/*  58 */       for (int var6 = 0; var6 < var5.tagCount(); var6++) {
/*     */         
/*  60 */         short var7 = var5.getCompoundTagAt(var6).getShort("id");
/*  61 */         short var8 = var5.getCompoundTagAt(var6).getShort("lvl");
/*     */         
/*  63 */         if (Enchantment.func_180306_c(var7) != null)
/*     */         {
/*  65 */           tooltip.add(Enchantment.func_180306_c(var7).getTranslatedName(var8));
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEnchantment(ItemStack p_92115_1_, EnchantmentData p_92115_2_) {
/*  76 */     NBTTagList var3 = func_92110_g(p_92115_1_);
/*  77 */     boolean var4 = true;
/*     */     
/*  79 */     for (int var5 = 0; var5 < var3.tagCount(); var5++) {
/*     */       
/*  81 */       NBTTagCompound var6 = var3.getCompoundTagAt(var5);
/*     */       
/*  83 */       if (var6.getShort("id") == p_92115_2_.enchantmentobj.effectId) {
/*     */         
/*  85 */         if (var6.getShort("lvl") < p_92115_2_.enchantmentLevel)
/*     */         {
/*  87 */           var6.setShort("lvl", (short)p_92115_2_.enchantmentLevel);
/*     */         }
/*     */         
/*  90 */         var4 = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  95 */     if (var4) {
/*     */       
/*  97 */       NBTTagCompound var7 = new NBTTagCompound();
/*  98 */       var7.setShort("id", (short)p_92115_2_.enchantmentobj.effectId);
/*  99 */       var7.setShort("lvl", (short)p_92115_2_.enchantmentLevel);
/* 100 */       var3.appendTag((NBTBase)var7);
/*     */     } 
/*     */     
/* 103 */     if (!p_92115_1_.hasTagCompound())
/*     */     {
/* 105 */       p_92115_1_.setTagCompound(new NBTTagCompound());
/*     */     }
/*     */     
/* 108 */     p_92115_1_.getTagCompound().setTag("StoredEnchantments", (NBTBase)var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getEnchantedItemStack(EnchantmentData p_92111_1_) {
/* 116 */     ItemStack var2 = new ItemStack(this);
/* 117 */     addEnchantment(var2, p_92111_1_);
/* 118 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_92113_a(Enchantment p_92113_1_, List<ItemStack> p_92113_2_) {
/* 123 */     for (int var3 = p_92113_1_.getMinLevel(); var3 <= p_92113_1_.getMaxLevel(); var3++)
/*     */     {
/* 125 */       p_92113_2_.add(getEnchantedItemStack(new EnchantmentData(p_92113_1_, var3)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public WeightedRandomChestContent getRandomEnchantedBook(Random p_92114_1_) {
/* 131 */     return func_92112_a(p_92114_1_, 1, 1, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public WeightedRandomChestContent func_92112_a(Random p_92112_1_, int p_92112_2_, int p_92112_3_, int p_92112_4_) {
/* 136 */     ItemStack var5 = new ItemStack(Items.book, 1, 0);
/* 137 */     EnchantmentHelper.addRandomEnchantment(p_92112_1_, var5, 30);
/* 138 */     return new WeightedRandomChestContent(var5, p_92112_2_, p_92112_3_, p_92112_4_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemEnchantedBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */