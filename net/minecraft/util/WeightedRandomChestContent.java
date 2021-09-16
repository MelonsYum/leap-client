/*    */ package net.minecraft.util;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntityDispenser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeightedRandomChestContent
/*    */   extends WeightedRandom.Item
/*    */ {
/*    */   private ItemStack theItemId;
/*    */   private int theMinimumChanceToGenerateItem;
/*    */   private int theMaximumChanceToGenerateItem;
/*    */   private static final String __OBFID = "CL_00001505";
/*    */   
/*    */   public WeightedRandomChestContent(Item p_i45311_1_, int p_i45311_2_, int p_i45311_3_, int p_i45311_4_, int p_i45311_5_) {
/* 27 */     super(p_i45311_5_);
/* 28 */     this.theItemId = new ItemStack(p_i45311_1_, 1, p_i45311_2_);
/* 29 */     this.theMinimumChanceToGenerateItem = p_i45311_3_;
/* 30 */     this.theMaximumChanceToGenerateItem = p_i45311_4_;
/*    */   }
/*    */ 
/*    */   
/*    */   public WeightedRandomChestContent(ItemStack p_i1558_1_, int p_i1558_2_, int p_i1558_3_, int p_i1558_4_) {
/* 35 */     super(p_i1558_4_);
/* 36 */     this.theItemId = p_i1558_1_;
/* 37 */     this.theMinimumChanceToGenerateItem = p_i1558_2_;
/* 38 */     this.theMaximumChanceToGenerateItem = p_i1558_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void generateChestContents(Random p_177630_0_, List p_177630_1_, IInventory p_177630_2_, int p_177630_3_) {
/* 43 */     for (int var4 = 0; var4 < p_177630_3_; var4++) {
/*    */       
/* 45 */       WeightedRandomChestContent var5 = (WeightedRandomChestContent)WeightedRandom.getRandomItem(p_177630_0_, p_177630_1_);
/* 46 */       int var6 = var5.theMinimumChanceToGenerateItem + p_177630_0_.nextInt(var5.theMaximumChanceToGenerateItem - var5.theMinimumChanceToGenerateItem + 1);
/*    */       
/* 48 */       if (var5.theItemId.getMaxStackSize() >= var6) {
/*    */         
/* 50 */         ItemStack var7 = var5.theItemId.copy();
/* 51 */         var7.stackSize = var6;
/* 52 */         p_177630_2_.setInventorySlotContents(p_177630_0_.nextInt(p_177630_2_.getSizeInventory()), var7);
/*    */       }
/*    */       else {
/*    */         
/* 56 */         for (int var9 = 0; var9 < var6; var9++) {
/*    */           
/* 58 */           ItemStack var8 = var5.theItemId.copy();
/* 59 */           var8.stackSize = 1;
/* 60 */           p_177630_2_.setInventorySlotContents(p_177630_0_.nextInt(p_177630_2_.getSizeInventory()), var8);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void func_177631_a(Random p_177631_0_, List p_177631_1_, TileEntityDispenser p_177631_2_, int p_177631_3_) {
/* 68 */     for (int var4 = 0; var4 < p_177631_3_; var4++) {
/*    */       
/* 70 */       WeightedRandomChestContent var5 = (WeightedRandomChestContent)WeightedRandom.getRandomItem(p_177631_0_, p_177631_1_);
/* 71 */       int var6 = var5.theMinimumChanceToGenerateItem + p_177631_0_.nextInt(var5.theMaximumChanceToGenerateItem - var5.theMinimumChanceToGenerateItem + 1);
/*    */       
/* 73 */       if (var5.theItemId.getMaxStackSize() >= var6) {
/*    */         
/* 75 */         ItemStack var7 = var5.theItemId.copy();
/* 76 */         var7.stackSize = var6;
/* 77 */         p_177631_2_.setInventorySlotContents(p_177631_0_.nextInt(p_177631_2_.getSizeInventory()), var7);
/*    */       }
/*    */       else {
/*    */         
/* 81 */         for (int var9 = 0; var9 < var6; var9++) {
/*    */           
/* 83 */           ItemStack var8 = var5.theItemId.copy();
/* 84 */           var8.stackSize = 1;
/* 85 */           p_177631_2_.setInventorySlotContents(p_177631_0_.nextInt(p_177631_2_.getSizeInventory()), var8);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static List func_177629_a(List p_177629_0_, WeightedRandomChestContent... p_177629_1_) {
/* 93 */     ArrayList<? super WeightedRandomChestContent> var2 = Lists.newArrayList(p_177629_0_);
/* 94 */     Collections.addAll(var2, p_177629_1_);
/* 95 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\WeightedRandomChestContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */