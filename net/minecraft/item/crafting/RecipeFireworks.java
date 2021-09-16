/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.ItemDye;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeFireworks
/*     */   implements IRecipe
/*     */ {
/*     */   private ItemStack field_92102_a;
/*     */   private static final String __OBFID = "CL_00000083";
/*     */   
/*     */   public boolean matches(InventoryCrafting p_77569_1_, World worldIn) {
/*  23 */     this.field_92102_a = null;
/*  24 */     int var3 = 0;
/*  25 */     int var4 = 0;
/*  26 */     int var5 = 0;
/*  27 */     int var6 = 0;
/*  28 */     int var7 = 0;
/*  29 */     int var8 = 0;
/*     */     
/*  31 */     for (int var9 = 0; var9 < p_77569_1_.getSizeInventory(); var9++) {
/*     */       
/*  33 */       ItemStack var10 = p_77569_1_.getStackInSlot(var9);
/*     */       
/*  35 */       if (var10 != null)
/*     */       {
/*  37 */         if (var10.getItem() == Items.gunpowder) {
/*     */           
/*  39 */           var4++;
/*     */         }
/*  41 */         else if (var10.getItem() == Items.firework_charge) {
/*     */           
/*  43 */           var6++;
/*     */         }
/*  45 */         else if (var10.getItem() == Items.dye) {
/*     */           
/*  47 */           var5++;
/*     */         }
/*  49 */         else if (var10.getItem() == Items.paper) {
/*     */           
/*  51 */           var3++;
/*     */         }
/*  53 */         else if (var10.getItem() == Items.glowstone_dust) {
/*     */           
/*  55 */           var7++;
/*     */         }
/*  57 */         else if (var10.getItem() == Items.diamond) {
/*     */           
/*  59 */           var7++;
/*     */         }
/*  61 */         else if (var10.getItem() == Items.fire_charge) {
/*     */           
/*  63 */           var8++;
/*     */         }
/*  65 */         else if (var10.getItem() == Items.feather) {
/*     */           
/*  67 */           var8++;
/*     */         }
/*  69 */         else if (var10.getItem() == Items.gold_nugget) {
/*     */           
/*  71 */           var8++;
/*     */         }
/*     */         else {
/*     */           
/*  75 */           if (var10.getItem() != Items.skull)
/*     */           {
/*  77 */             return false;
/*     */           }
/*     */           
/*  80 */           var8++;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  85 */     var7 += var5 + var8;
/*     */     
/*  87 */     if (var4 <= 3 && var3 <= 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  92 */       if (var4 >= 1 && var3 == 1 && var7 == 0) {
/*     */         
/*  94 */         this.field_92102_a = new ItemStack(Items.fireworks);
/*     */         
/*  96 */         if (var6 > 0) {
/*     */           
/*  98 */           NBTTagCompound var16 = new NBTTagCompound();
/*  99 */           NBTTagCompound var19 = new NBTTagCompound();
/* 100 */           NBTTagList var23 = new NBTTagList();
/*     */           
/* 102 */           for (int var24 = 0; var24 < p_77569_1_.getSizeInventory(); var24++) {
/*     */             
/* 104 */             ItemStack var26 = p_77569_1_.getStackInSlot(var24);
/*     */             
/* 106 */             if (var26 != null && var26.getItem() == Items.firework_charge && var26.hasTagCompound() && var26.getTagCompound().hasKey("Explosion", 10))
/*     */             {
/* 108 */               var23.appendTag((NBTBase)var26.getTagCompound().getCompoundTag("Explosion"));
/*     */             }
/*     */           } 
/*     */           
/* 112 */           var19.setTag("Explosions", (NBTBase)var23);
/* 113 */           var19.setByte("Flight", (byte)var4);
/* 114 */           var16.setTag("Fireworks", (NBTBase)var19);
/* 115 */           this.field_92102_a.setTagCompound(var16);
/*     */         } 
/*     */         
/* 118 */         return true;
/*     */       } 
/* 120 */       if (var4 == 1 && var3 == 0 && var6 == 0 && var5 > 0 && var8 <= 1) {
/*     */         
/* 122 */         this.field_92102_a = new ItemStack(Items.firework_charge);
/* 123 */         NBTTagCompound var16 = new NBTTagCompound();
/* 124 */         NBTTagCompound var19 = new NBTTagCompound();
/* 125 */         byte var22 = 0;
/* 126 */         ArrayList<Integer> var12 = Lists.newArrayList();
/*     */         
/* 128 */         for (int var13 = 0; var13 < p_77569_1_.getSizeInventory(); var13++) {
/*     */           
/* 130 */           ItemStack var14 = p_77569_1_.getStackInSlot(var13);
/*     */           
/* 132 */           if (var14 != null)
/*     */           {
/* 134 */             if (var14.getItem() == Items.dye) {
/*     */               
/* 136 */               var12.add(Integer.valueOf(ItemDye.dyeColors[var14.getMetadata() & 0xF]));
/*     */             }
/* 138 */             else if (var14.getItem() == Items.glowstone_dust) {
/*     */               
/* 140 */               var19.setBoolean("Flicker", true);
/*     */             }
/* 142 */             else if (var14.getItem() == Items.diamond) {
/*     */               
/* 144 */               var19.setBoolean("Trail", true);
/*     */             }
/* 146 */             else if (var14.getItem() == Items.fire_charge) {
/*     */               
/* 148 */               var22 = 1;
/*     */             }
/* 150 */             else if (var14.getItem() == Items.feather) {
/*     */               
/* 152 */               var22 = 4;
/*     */             }
/* 154 */             else if (var14.getItem() == Items.gold_nugget) {
/*     */               
/* 156 */               var22 = 2;
/*     */             }
/* 158 */             else if (var14.getItem() == Items.skull) {
/*     */               
/* 160 */               var22 = 3;
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 165 */         int[] var25 = new int[var12.size()];
/*     */         
/* 167 */         for (int var27 = 0; var27 < var25.length; var27++)
/*     */         {
/* 169 */           var25[var27] = ((Integer)var12.get(var27)).intValue();
/*     */         }
/*     */         
/* 172 */         var19.setIntArray("Colors", var25);
/* 173 */         var19.setByte("Type", var22);
/* 174 */         var16.setTag("Explosion", (NBTBase)var19);
/* 175 */         this.field_92102_a.setTagCompound(var16);
/* 176 */         return true;
/*     */       } 
/* 178 */       if (var4 == 0 && var3 == 0 && var6 == 1 && var5 > 0 && var5 == var7) {
/*     */         
/* 180 */         ArrayList<Integer> var15 = Lists.newArrayList();
/*     */         
/* 182 */         for (int var17 = 0; var17 < p_77569_1_.getSizeInventory(); var17++) {
/*     */           
/* 184 */           ItemStack var11 = p_77569_1_.getStackInSlot(var17);
/*     */           
/* 186 */           if (var11 != null)
/*     */           {
/* 188 */             if (var11.getItem() == Items.dye) {
/*     */               
/* 190 */               var15.add(Integer.valueOf(ItemDye.dyeColors[var11.getMetadata() & 0xF]));
/*     */             }
/* 192 */             else if (var11.getItem() == Items.firework_charge) {
/*     */               
/* 194 */               this.field_92102_a = var11.copy();
/* 195 */               this.field_92102_a.stackSize = 1;
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 200 */         int[] var18 = new int[var15.size()];
/*     */         
/* 202 */         for (int var20 = 0; var20 < var18.length; var20++)
/*     */         {
/* 204 */           var18[var20] = ((Integer)var15.get(var20)).intValue();
/*     */         }
/*     */         
/* 207 */         if (this.field_92102_a != null && this.field_92102_a.hasTagCompound()) {
/*     */           
/* 209 */           NBTTagCompound var21 = this.field_92102_a.getTagCompound().getCompoundTag("Explosion");
/*     */           
/* 211 */           if (var21 == null)
/*     */           {
/* 213 */             return false;
/*     */           }
/*     */ 
/*     */           
/* 217 */           var21.setIntArray("FadeColors", var18);
/* 218 */           return true;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 223 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 228 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
/* 242 */     return this.field_92102_a.copy();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipeSize() {
/* 250 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutput() {
/* 255 */     return this.field_92102_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
/* 260 */     ItemStack[] var2 = new ItemStack[p_179532_1_.getSizeInventory()];
/*     */     
/* 262 */     for (int var3 = 0; var3 < var2.length; var3++) {
/*     */       
/* 264 */       ItemStack var4 = p_179532_1_.getStackInSlot(var3);
/*     */       
/* 266 */       if (var4 != null && var4.getItem().hasContainerItem())
/*     */       {
/* 268 */         var2[var3] = new ItemStack(var4.getItem().getContainerItem());
/*     */       }
/*     */     } 
/*     */     
/* 272 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\RecipeFireworks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */