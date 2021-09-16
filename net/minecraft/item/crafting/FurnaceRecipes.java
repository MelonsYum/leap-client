/*     */ package net.minecraft.item.crafting;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockStoneBrick;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemFishFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ public class FurnaceRecipes
/*     */ {
/*  18 */   private static final FurnaceRecipes smeltingBase = new FurnaceRecipes();
/*     */ 
/*     */   
/*  21 */   private Map smeltingList = Maps.newHashMap();
/*  22 */   private Map experienceList = Maps.newHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00000085";
/*     */   
/*     */   public static FurnaceRecipes instance() {
/*  27 */     return smeltingBase;
/*     */   }
/*     */ 
/*     */   
/*     */   private FurnaceRecipes() {
/*  32 */     addSmeltingRecipeForBlock(Blocks.iron_ore, new ItemStack(Items.iron_ingot), 0.7F);
/*  33 */     addSmeltingRecipeForBlock(Blocks.gold_ore, new ItemStack(Items.gold_ingot), 1.0F);
/*  34 */     addSmeltingRecipeForBlock(Blocks.diamond_ore, new ItemStack(Items.diamond), 1.0F);
/*  35 */     addSmeltingRecipeForBlock((Block)Blocks.sand, new ItemStack(Blocks.glass), 0.1F);
/*  36 */     addSmelting(Items.porkchop, new ItemStack(Items.cooked_porkchop), 0.35F);
/*  37 */     addSmelting(Items.beef, new ItemStack(Items.cooked_beef), 0.35F);
/*  38 */     addSmelting(Items.chicken, new ItemStack(Items.cooked_chicken), 0.35F);
/*  39 */     addSmelting(Items.rabbit, new ItemStack(Items.cooked_rabbit), 0.35F);
/*  40 */     addSmelting(Items.mutton, new ItemStack(Items.cooked_mutton), 0.35F);
/*  41 */     addSmeltingRecipeForBlock(Blocks.cobblestone, new ItemStack(Blocks.stone), 0.1F);
/*  42 */     addSmeltingRecipe(new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.DEFAULT_META), new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.CRACKED_META), 0.1F);
/*  43 */     addSmelting(Items.clay_ball, new ItemStack(Items.brick), 0.3F);
/*  44 */     addSmeltingRecipeForBlock(Blocks.clay, new ItemStack(Blocks.hardened_clay), 0.35F);
/*  45 */     addSmeltingRecipeForBlock((Block)Blocks.cactus, new ItemStack(Items.dye, 1, EnumDyeColor.GREEN.getDyeColorDamage()), 0.2F);
/*  46 */     addSmeltingRecipeForBlock(Blocks.log, new ItemStack(Items.coal, 1, 1), 0.15F);
/*  47 */     addSmeltingRecipeForBlock(Blocks.log2, new ItemStack(Items.coal, 1, 1), 0.15F);
/*  48 */     addSmeltingRecipeForBlock(Blocks.emerald_ore, new ItemStack(Items.emerald), 1.0F);
/*  49 */     addSmelting(Items.potato, new ItemStack(Items.baked_potato), 0.35F);
/*  50 */     addSmeltingRecipeForBlock(Blocks.netherrack, new ItemStack(Items.netherbrick), 0.1F);
/*  51 */     addSmeltingRecipe(new ItemStack(Blocks.sponge, 1, 1), new ItemStack(Blocks.sponge, 1, 0), 0.15F);
/*  52 */     ItemFishFood.FishType[] var1 = ItemFishFood.FishType.values();
/*  53 */     int var2 = var1.length;
/*     */     
/*  55 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/*  57 */       ItemFishFood.FishType var4 = var1[var3];
/*     */       
/*  59 */       if (var4.getCookable())
/*     */       {
/*  61 */         addSmeltingRecipe(new ItemStack(Items.fish, 1, var4.getItemDamage()), new ItemStack(Items.cooked_fish, 1, var4.getItemDamage()), 0.35F);
/*     */       }
/*     */     } 
/*     */     
/*  65 */     addSmeltingRecipeForBlock(Blocks.coal_ore, new ItemStack(Items.coal), 0.1F);
/*  66 */     addSmeltingRecipeForBlock(Blocks.redstone_ore, new ItemStack(Items.redstone), 0.7F);
/*  67 */     addSmeltingRecipeForBlock(Blocks.lapis_ore, new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeColorDamage()), 0.2F);
/*  68 */     addSmeltingRecipeForBlock(Blocks.quartz_ore, new ItemStack(Items.quartz), 0.2F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSmeltingRecipeForBlock(Block p_151393_1_, ItemStack p_151393_2_, float p_151393_3_) {
/*  73 */     addSmelting(Item.getItemFromBlock(p_151393_1_), p_151393_2_, p_151393_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSmelting(Item p_151396_1_, ItemStack p_151396_2_, float p_151396_3_) {
/*  78 */     addSmeltingRecipe(new ItemStack(p_151396_1_, 1, 32767), p_151396_2_, p_151396_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addSmeltingRecipe(ItemStack p_151394_1_, ItemStack p_151394_2_, float p_151394_3_) {
/*  83 */     this.smeltingList.put(p_151394_1_, p_151394_2_);
/*  84 */     this.experienceList.put(p_151394_2_, Float.valueOf(p_151394_3_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getSmeltingResult(ItemStack p_151395_1_) {
/*     */     Map.Entry var3;
/*  92 */     Iterator<Map.Entry> var2 = this.smeltingList.entrySet().iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  97 */       if (!var2.hasNext())
/*     */       {
/*  99 */         return null;
/*     */       }
/*     */       
/* 102 */       var3 = var2.next();
/*     */     }
/* 104 */     while (!func_151397_a(p_151395_1_, (ItemStack)var3.getKey()));
/*     */     
/* 106 */     return (ItemStack)var3.getValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_) {
/* 111 */     return (p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getMetadata() == 32767 || p_151397_2_.getMetadata() == p_151397_1_.getMetadata()));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map getSmeltingList() {
/* 116 */     return this.smeltingList;
/*     */   }
/*     */   
/*     */   public float getSmeltingExperience(ItemStack p_151398_1_) {
/*     */     Map.Entry var3;
/* 121 */     Iterator<Map.Entry> var2 = this.experienceList.entrySet().iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 126 */       if (!var2.hasNext())
/*     */       {
/* 128 */         return 0.0F;
/*     */       }
/*     */       
/* 131 */       var3 = var2.next();
/*     */     }
/* 133 */     while (!func_151397_a(p_151398_1_, (ItemStack)var3.getKey()));
/*     */     
/* 135 */     return ((Float)var3.getValue()).floatValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\crafting\FurnaceRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */