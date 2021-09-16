/*     */ package net.minecraft.item;
/*     */ 
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityPotion;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.potion.PotionHelper;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemPotion
/*     */   extends Item
/*     */ {
/*  33 */   private Map effectCache = Maps.newHashMap();
/*  34 */   private static final Map field_77835_b = Maps.newLinkedHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00000055";
/*     */   
/*     */   public ItemPotion() {
/*  39 */     setMaxStackSize(1);
/*  40 */     setHasSubtypes(true);
/*  41 */     setMaxDamage(0);
/*  42 */     setCreativeTab(CreativeTabs.tabBrewing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEffects(ItemStack p_77832_1_) {
/*  50 */     if (p_77832_1_.hasTagCompound() && p_77832_1_.getTagCompound().hasKey("CustomPotionEffects", 9)) {
/*     */       
/*  52 */       ArrayList<PotionEffect> var7 = Lists.newArrayList();
/*  53 */       NBTTagList var3 = p_77832_1_.getTagCompound().getTagList("CustomPotionEffects", 10);
/*     */       
/*  55 */       for (int var4 = 0; var4 < var3.tagCount(); var4++) {
/*     */         
/*  57 */         NBTTagCompound var5 = var3.getCompoundTagAt(var4);
/*  58 */         PotionEffect var6 = PotionEffect.readCustomPotionEffectFromNBT(var5);
/*     */         
/*  60 */         if (var6 != null)
/*     */         {
/*  62 */           var7.add(var6);
/*     */         }
/*     */       } 
/*     */       
/*  66 */       return var7;
/*     */     } 
/*     */ 
/*     */     
/*  70 */     List var2 = (List)this.effectCache.get(Integer.valueOf(p_77832_1_.getMetadata()));
/*     */     
/*  72 */     if (var2 == null) {
/*     */       
/*  74 */       var2 = PotionHelper.getPotionEffects(p_77832_1_.getMetadata(), false);
/*  75 */       this.effectCache.put(Integer.valueOf(p_77832_1_.getMetadata()), var2);
/*     */     } 
/*     */     
/*  78 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getEffects(int p_77834_1_) {
/*  87 */     List var2 = (List)this.effectCache.get(Integer.valueOf(p_77834_1_));
/*     */     
/*  89 */     if (var2 == null) {
/*     */       
/*  91 */       var2 = PotionHelper.getPotionEffects(p_77834_1_, false);
/*  92 */       this.effectCache.put(Integer.valueOf(p_77834_1_), var2);
/*     */     } 
/*     */     
/*  95 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
/* 104 */     if (!playerIn.capabilities.isCreativeMode)
/*     */     {
/* 106 */       stack.stackSize--;
/*     */     }
/*     */     
/* 109 */     if (!worldIn.isRemote) {
/*     */       
/* 111 */       List var4 = getEffects(stack);
/*     */       
/* 113 */       if (var4 != null) {
/*     */         
/* 115 */         Iterator<PotionEffect> var5 = var4.iterator();
/*     */         
/* 117 */         while (var5.hasNext()) {
/*     */           
/* 119 */           PotionEffect var6 = var5.next();
/* 120 */           playerIn.addPotionEffect(new PotionEffect(var6));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 125 */     playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/*     */     
/* 127 */     if (!playerIn.capabilities.isCreativeMode) {
/*     */       
/* 129 */       if (stack.stackSize <= 0)
/*     */       {
/* 131 */         return new ItemStack(Items.glass_bottle);
/*     */       }
/*     */       
/* 134 */       playerIn.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
/*     */     } 
/*     */     
/* 137 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxItemUseDuration(ItemStack stack) {
/* 145 */     return 32;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction getItemUseAction(ItemStack stack) {
/* 153 */     return EnumAction.DRINK;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 161 */     if (isSplash(itemStackIn.getMetadata())) {
/*     */       
/* 163 */       if (!playerIn.capabilities.isCreativeMode)
/*     */       {
/* 165 */         itemStackIn.stackSize--;
/*     */       }
/*     */       
/* 168 */       worldIn.playSoundAtEntity((Entity)playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/*     */       
/* 170 */       if (!worldIn.isRemote)
/*     */       {
/* 172 */         worldIn.spawnEntityInWorld((Entity)new EntityPotion(worldIn, (EntityLivingBase)playerIn, itemStackIn));
/*     */       }
/*     */       
/* 175 */       playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/* 176 */       return itemStackIn;
/*     */     } 
/*     */ 
/*     */     
/* 180 */     playerIn.setItemInUse(itemStackIn, getMaxItemUseDuration(itemStackIn));
/* 181 */     return itemStackIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSplash(int p_77831_0_) {
/* 190 */     return ((p_77831_0_ & 0x4000) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromDamage(int p_77620_1_) {
/* 195 */     return PotionHelper.func_77915_a(p_77620_1_, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/* 200 */     return (renderPass > 0) ? 16777215 : getColorFromDamage(stack.getMetadata());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEffectInstant(int p_77833_1_) {
/* 205 */     List var2 = getEffects(p_77833_1_);
/*     */     
/* 207 */     if (var2 != null && !var2.isEmpty()) {
/*     */       PotionEffect var4;
/* 209 */       Iterator<PotionEffect> var3 = var2.iterator();
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 214 */         if (!var3.hasNext())
/*     */         {
/* 216 */           return false;
/*     */         }
/*     */         
/* 219 */         var4 = var3.next();
/*     */       }
/* 221 */       while (!Potion.potionTypes[var4.getPotionID()].isInstant());
/*     */       
/* 223 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 227 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getItemStackDisplayName(ItemStack stack) {
/* 233 */     if (stack.getMetadata() == 0)
/*     */     {
/* 235 */       return StatCollector.translateToLocal("item.emptyPotion.name").trim();
/*     */     }
/*     */ 
/*     */     
/* 239 */     String var2 = "";
/*     */     
/* 241 */     if (isSplash(stack.getMetadata()))
/*     */     {
/* 243 */       var2 = String.valueOf(StatCollector.translateToLocal("potion.prefix.grenade").trim()) + " ";
/*     */     }
/*     */     
/* 246 */     List<PotionEffect> var3 = Items.potionitem.getEffects(stack);
/*     */ 
/*     */     
/* 249 */     if (var3 != null && !var3.isEmpty()) {
/*     */       
/* 251 */       String str = ((PotionEffect)var3.get(0)).getEffectName();
/* 252 */       str = String.valueOf(str) + ".postfix";
/* 253 */       return String.valueOf(var2) + StatCollector.translateToLocal(str).trim();
/*     */     } 
/*     */ 
/*     */     
/* 257 */     String var4 = PotionHelper.func_77905_c(stack.getMetadata());
/* 258 */     return String.valueOf(StatCollector.translateToLocal(var4).trim()) + " " + super.getItemStackDisplayName(stack);
/*     */   }
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
/*     */   public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
/* 271 */     if (stack.getMetadata() != 0) {
/*     */       
/* 273 */       List var5 = Items.potionitem.getEffects(stack);
/* 274 */       HashMultimap var6 = HashMultimap.create();
/*     */ 
/*     */       
/* 277 */       if (var5 != null && !var5.isEmpty()) {
/*     */         
/* 279 */         Iterator<PotionEffect> var16 = var5.iterator();
/*     */         
/* 281 */         while (var16.hasNext())
/*     */         {
/* 283 */           PotionEffect var8 = var16.next();
/* 284 */           String var9 = StatCollector.translateToLocal(var8.getEffectName()).trim();
/* 285 */           Potion var10 = Potion.potionTypes[var8.getPotionID()];
/* 286 */           Map var11 = var10.func_111186_k();
/*     */           
/* 288 */           if (var11 != null && var11.size() > 0) {
/*     */             
/* 290 */             Iterator<Map.Entry> var12 = var11.entrySet().iterator();
/*     */             
/* 292 */             while (var12.hasNext()) {
/*     */               
/* 294 */               Map.Entry var13 = var12.next();
/* 295 */               AttributeModifier var14 = (AttributeModifier)var13.getValue();
/* 296 */               AttributeModifier var15 = new AttributeModifier(var14.getName(), var10.func_111183_a(var8.getAmplifier(), var14), var14.getOperation());
/* 297 */               var6.put(((IAttribute)var13.getKey()).getAttributeUnlocalizedName(), var15);
/*     */             } 
/*     */           } 
/*     */           
/* 301 */           if (var8.getAmplifier() > 0)
/*     */           {
/* 303 */             var9 = String.valueOf(var9) + " " + StatCollector.translateToLocal("potion.potency." + var8.getAmplifier()).trim();
/*     */           }
/*     */           
/* 306 */           if (var8.getDuration() > 20)
/*     */           {
/* 308 */             var9 = String.valueOf(var9) + " (" + Potion.getDurationString(var8) + ")";
/*     */           }
/*     */           
/* 311 */           if (var10.isBadEffect()) {
/*     */             
/* 313 */             tooltip.add(EnumChatFormatting.RED + var9);
/*     */             
/*     */             continue;
/*     */           } 
/* 317 */           tooltip.add(EnumChatFormatting.GRAY + var9);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 323 */         String var7 = StatCollector.translateToLocal("potion.empty").trim();
/* 324 */         tooltip.add(EnumChatFormatting.GRAY + var7);
/*     */       } 
/*     */       
/* 327 */       if (!var6.isEmpty()) {
/*     */         
/* 329 */         tooltip.add("");
/* 330 */         tooltip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("potion.effects.whenDrank"));
/* 331 */         Iterator<Map.Entry> var16 = var6.entries().iterator();
/*     */         
/* 333 */         while (var16.hasNext()) {
/*     */           double var20;
/* 335 */           Map.Entry var17 = var16.next();
/* 336 */           AttributeModifier var18 = (AttributeModifier)var17.getValue();
/* 337 */           double var19 = var18.getAmount();
/*     */ 
/*     */           
/* 340 */           if (var18.getOperation() != 1 && var18.getOperation() != 2) {
/*     */             
/* 342 */             var20 = var18.getAmount();
/*     */           }
/*     */           else {
/*     */             
/* 346 */             var20 = var18.getAmount() * 100.0D;
/*     */           } 
/*     */           
/* 349 */           if (var19 > 0.0D) {
/*     */             
/* 351 */             tooltip.add(EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted("attribute.modifier.plus." + var18.getOperation(), new Object[] { ItemStack.DECIMALFORMAT.format(var20), StatCollector.translateToLocal("attribute.name." + (String)var17.getKey()) })); continue;
/*     */           } 
/* 353 */           if (var19 < 0.0D) {
/*     */             
/* 355 */             var20 *= -1.0D;
/* 356 */             tooltip.add(EnumChatFormatting.RED + StatCollector.translateToLocalFormatted("attribute.modifier.take." + var18.getOperation(), new Object[] { ItemStack.DECIMALFORMAT.format(var20), StatCollector.translateToLocal("attribute.name." + (String)var17.getKey()) }));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEffect(ItemStack stack) {
/* 365 */     List var2 = getEffects(stack);
/* 366 */     return (var2 != null && !var2.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
/* 376 */     super.getSubItems(itemIn, tab, subItems);
/*     */ 
/*     */     
/* 379 */     if (field_77835_b.isEmpty())
/*     */     {
/* 381 */       for (int var4 = 0; var4 <= 15; var4++) {
/*     */         
/* 383 */         for (int var5 = 0; var5 <= 1; var5++) {
/*     */           int var6;
/*     */ 
/*     */           
/* 387 */           if (var5 == 0) {
/*     */             
/* 389 */             var6 = var4 | 0x2000;
/*     */           }
/*     */           else {
/*     */             
/* 393 */             var6 = var4 | 0x4000;
/*     */           } 
/*     */           
/* 396 */           for (int var7 = 0; var7 <= 2; var7++) {
/*     */             
/* 398 */             int var8 = var6;
/*     */             
/* 400 */             if (var7 != 0)
/*     */             {
/* 402 */               if (var7 == 1) {
/*     */                 
/* 404 */                 var8 = var6 | 0x20;
/*     */               }
/* 406 */               else if (var7 == 2) {
/*     */                 
/* 408 */                 var8 = var6 | 0x40;
/*     */               } 
/*     */             }
/*     */             
/* 412 */             List var9 = PotionHelper.getPotionEffects(var8, false);
/*     */             
/* 414 */             if (var9 != null && !var9.isEmpty())
/*     */             {
/* 416 */               field_77835_b.put(var9, Integer.valueOf(var8));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 423 */     Iterator<Integer> var10 = field_77835_b.values().iterator();
/*     */     
/* 425 */     while (var10.hasNext()) {
/*     */       
/* 427 */       int var5 = ((Integer)var10.next()).intValue();
/* 428 */       subItems.add(new ItemStack(itemIn, 1, var5));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */