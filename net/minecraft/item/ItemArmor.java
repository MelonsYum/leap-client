/*     */ package net.minecraft.item;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
/*     */ import net.minecraft.dispenser.IBehaviorDispenseItem;
/*     */ import net.minecraft.dispenser.IBlockSource;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemArmor extends Item {
/*  23 */   private static final int[] maxDamageArray = new int[] { 11, 16, 15, 13 };
/*  24 */   public static final String[] EMPTY_SLOT_NAMES = new String[] { "minecraft:items/empty_armor_slot_helmet", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_boots" };
/*  25 */   private static final IBehaviorDispenseItem dispenserBehavior = (IBehaviorDispenseItem)new BehaviorDefaultDispenseItem()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001767";
/*     */       
/*     */       protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
/*  30 */         BlockPos var3 = source.getBlockPos().offset(BlockDispenser.getFacing(source.getBlockMetadata()));
/*  31 */         int var4 = var3.getX();
/*  32 */         int var5 = var3.getY();
/*  33 */         int var6 = var3.getZ();
/*  34 */         AxisAlignedBB var7 = new AxisAlignedBB(var4, var5, var6, (var4 + 1), (var5 + 1), (var6 + 1));
/*  35 */         List<EntityLivingBase> var8 = source.getWorld().func_175647_a(EntityLivingBase.class, var7, Predicates.and(IEntitySelector.field_180132_d, (Predicate)new IEntitySelector.ArmoredMob(stack)));
/*     */         
/*  37 */         if (var8.size() > 0) {
/*     */           
/*  39 */           EntityLivingBase var9 = var8.get(0);
/*  40 */           int var10 = (var9 instanceof EntityPlayer) ? 1 : 0;
/*  41 */           int var11 = EntityLiving.getArmorPosition(stack);
/*  42 */           ItemStack var12 = stack.copy();
/*  43 */           var12.stackSize = 1;
/*  44 */           var9.setCurrentItemOrArmor(var11 - var10, var12);
/*     */           
/*  46 */           if (var9 instanceof EntityLiving)
/*     */           {
/*  48 */             ((EntityLiving)var9).setEquipmentDropChance(var11, 2.0F);
/*     */           }
/*     */           
/*  51 */           stack.stackSize--;
/*  52 */           return stack;
/*     */         } 
/*     */ 
/*     */         
/*  56 */         return super.dispenseStack(source, stack);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public final int armorType;
/*     */ 
/*     */ 
/*     */   
/*     */   public final int damageReduceAmount;
/*     */ 
/*     */ 
/*     */   
/*     */   public final int renderIndex;
/*     */ 
/*     */   
/*     */   private final ArmorMaterial material;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001766";
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemArmor(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
/*  81 */     this.material = p_i45325_1_;
/*  82 */     this.armorType = p_i45325_3_;
/*  83 */     this.renderIndex = p_i45325_2_;
/*  84 */     this.damageReduceAmount = p_i45325_1_.getDamageReductionAmount(p_i45325_3_);
/*  85 */     setMaxDamage(p_i45325_1_.getDurability(p_i45325_3_));
/*  86 */     this.maxStackSize = 1;
/*  87 */     setCreativeTab(CreativeTabs.tabCombat);
/*  88 */     BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserBehavior);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/*  93 */     if (renderPass > 0)
/*     */     {
/*  95 */       return 16777215;
/*     */     }
/*     */ 
/*     */     
/*  99 */     int var3 = getColor(stack);
/*     */     
/* 101 */     if (var3 < 0)
/*     */     {
/* 103 */       var3 = 16777215;
/*     */     }
/*     */     
/* 106 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/* 115 */     return this.material.getEnchantability();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArmorMaterial getArmorMaterial() {
/* 123 */     return this.material;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasColor(ItemStack p_82816_1_) {
/* 131 */     return (this.material != ArmorMaterial.LEATHER) ? false : (!p_82816_1_.hasTagCompound() ? false : (!p_82816_1_.getTagCompound().hasKey("display", 10) ? false : p_82816_1_.getTagCompound().getCompoundTag("display").hasKey("color", 3)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColor(ItemStack p_82814_1_) {
/* 139 */     if (this.material != ArmorMaterial.LEATHER)
/*     */     {
/* 141 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 145 */     NBTTagCompound var2 = p_82814_1_.getTagCompound();
/*     */     
/* 147 */     if (var2 != null) {
/*     */       
/* 149 */       NBTTagCompound var3 = var2.getCompoundTag("display");
/*     */       
/* 151 */       if (var3 != null && var3.hasKey("color", 3))
/*     */       {
/* 153 */         return var3.getInteger("color");
/*     */       }
/*     */     } 
/*     */     
/* 157 */     return 10511680;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeColor(ItemStack p_82815_1_) {
/* 166 */     if (this.material == ArmorMaterial.LEATHER) {
/*     */       
/* 168 */       NBTTagCompound var2 = p_82815_1_.getTagCompound();
/*     */       
/* 170 */       if (var2 != null) {
/*     */         
/* 172 */         NBTTagCompound var3 = var2.getCompoundTag("display");
/*     */         
/* 174 */         if (var3.hasKey("color"))
/*     */         {
/* 176 */           var3.removeTag("color");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82813_b(ItemStack p_82813_1_, int p_82813_2_) {
/* 184 */     if (this.material != ArmorMaterial.LEATHER)
/*     */     {
/* 186 */       throw new UnsupportedOperationException("Can't dye non-leather!");
/*     */     }
/*     */ 
/*     */     
/* 190 */     NBTTagCompound var3 = p_82813_1_.getTagCompound();
/*     */     
/* 192 */     if (var3 == null) {
/*     */       
/* 194 */       var3 = new NBTTagCompound();
/* 195 */       p_82813_1_.setTagCompound(var3);
/*     */     } 
/*     */     
/* 198 */     NBTTagCompound var4 = var3.getCompoundTag("display");
/*     */     
/* 200 */     if (!var3.hasKey("display", 10))
/*     */     {
/* 202 */       var3.setTag("display", (NBTBase)var4);
/*     */     }
/*     */     
/* 205 */     var4.setInteger("color", p_82813_2_);
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
/*     */   public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
/* 217 */     return (this.material.getBaseItemForRepair() == repair.getItem()) ? true : super.getIsRepairable(toRepair, repair);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/* 225 */     int var4 = EntityLiving.getArmorPosition(itemStackIn) - 1;
/* 226 */     ItemStack var5 = playerIn.getCurrentArmor(var4);
/*     */     
/* 228 */     if (var5 == null) {
/*     */       
/* 230 */       playerIn.setCurrentItemOrArmor(var4, itemStackIn.copy());
/* 231 */       itemStackIn.stackSize = 0;
/*     */     } 
/*     */     
/* 234 */     return itemStackIn;
/*     */   }
/*     */   
/*     */   public enum ArmorMaterial
/*     */   {
/* 239 */     LEATHER("LEATHER", 0, "leather", 5, (String)new int[] { 1, 3, 2, 1 }, 15),
/* 240 */     CHAIN("CHAIN", 1, "chainmail", 15, (String)new int[] { 2, 5, 4, 1 }, 12),
/* 241 */     IRON("IRON", 2, "iron", 15, (String)new int[] { 2, 6, 5, 2 }, 9),
/* 242 */     GOLD("GOLD", 3, "gold", 7, (String)new int[] { 2, 5, 3, 1 }, 25),
/* 243 */     DIAMOND("DIAMOND", 4, "diamond", 33, (String)new int[] { 3, 8, 6, 3 }, 10);
/*     */     
/*     */     private final String field_179243_f;
/*     */     private final int maxDamageFactor;
/*     */     private final int[] damageReductionAmountArray;
/*     */     private final int enchantability;
/* 249 */     private static final ArmorMaterial[] $VALUES = new ArmorMaterial[] { LEATHER, CHAIN, IRON, GOLD, DIAMOND };
/*     */     
/*     */     private static final String __OBFID = "CL_00001768";
/*     */     
/*     */     ArmorMaterial(String p_i45789_1_, int p_i45789_2_, String p_i45789_3_, int p_i45789_4_, int[] p_i45789_5_, int p_i45789_6_) {
/* 254 */       this.field_179243_f = p_i45789_3_;
/* 255 */       this.maxDamageFactor = p_i45789_4_;
/* 256 */       this.damageReductionAmountArray = p_i45789_5_;
/* 257 */       this.enchantability = p_i45789_6_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public int getDurability(int p_78046_1_) {
/* 262 */       return ItemArmor.maxDamageArray[p_78046_1_] * this.maxDamageFactor;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getDamageReductionAmount(int p_78044_1_) {
/* 267 */       return this.damageReductionAmountArray[p_78044_1_];
/*     */     }
/*     */ 
/*     */     
/*     */     public int getEnchantability() {
/* 272 */       return this.enchantability;
/*     */     }
/*     */ 
/*     */     
/*     */     public Item getBaseItemForRepair() {
/* 277 */       return (this == LEATHER) ? Items.leather : ((this == CHAIN) ? Items.iron_ingot : ((this == GOLD) ? Items.gold_ingot : ((this == IRON) ? Items.iron_ingot : ((this == DIAMOND) ? Items.diamond : null))));
/*     */     }
/*     */ 
/*     */     
/*     */     public String func_179242_c() {
/* 282 */       return this.field_179243_f;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */