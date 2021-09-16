/*     */ package net.minecraft.item;
/*     */ 
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemTool
/*     */   extends Item {
/*     */   private Set effectiveBlocksTool;
/*  16 */   protected float efficiencyOnProperMaterial = 4.0F;
/*     */ 
/*     */   
/*     */   private float damageVsEntity;
/*     */   
/*     */   protected Item.ToolMaterial toolMaterial;
/*     */   
/*     */   private static final String __OBFID = "CL_00000019";
/*     */ 
/*     */   
/*     */   protected ItemTool(float p_i45333_1_, Item.ToolMaterial p_i45333_2_, Set p_i45333_3_) {
/*  27 */     this.toolMaterial = p_i45333_2_;
/*  28 */     this.effectiveBlocksTool = p_i45333_3_;
/*  29 */     this.maxStackSize = 1;
/*  30 */     setMaxDamage(p_i45333_2_.getMaxUses());
/*  31 */     this.efficiencyOnProperMaterial = p_i45333_2_.getEfficiencyOnProperMaterial();
/*  32 */     this.damageVsEntity = p_i45333_1_ + p_i45333_2_.getDamageVsEntity();
/*  33 */     setCreativeTab(CreativeTabs.tabTools);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getStrVsBlock(ItemStack stack, Block p_150893_2_) {
/*  38 */     return this.effectiveBlocksTool.contains(p_150893_2_) ? this.efficiencyOnProperMaterial : 1.0F;
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
/*     */   public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
/*  50 */     stack.damageItem(2, attacker);
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn) {
/*  59 */     if (blockIn.getBlockHardness(worldIn, pos) != 0.0D)
/*     */     {
/*  61 */       stack.damageItem(1, playerIn);
/*     */     }
/*     */     
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item.ToolMaterial getToolMaterial() {
/*  77 */     return this.toolMaterial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemEnchantability() {
/*  85 */     return this.toolMaterial.getEnchantability();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getToolMaterialName() {
/*  93 */     return this.toolMaterial.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
/* 104 */     return (this.toolMaterial.getBaseItemForRepair() == repair.getItem()) ? true : super.getIsRepairable(toRepair, repair);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Multimap getItemAttributeModifiers() {
/* 112 */     Multimap var1 = super.getItemAttributeModifiers();
/* 113 */     var1.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Tool modifier", this.damageVsEntity, 0));
/* 114 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */