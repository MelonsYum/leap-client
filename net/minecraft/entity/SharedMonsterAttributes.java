/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class SharedMonsterAttributes {
/*  18 */   private static final Logger logger = LogManager.getLogger();
/*  19 */   public static final IAttribute maxHealth = (IAttribute)(new RangedAttribute(null, "generic.maxHealth", 20.0D, 0.0D, Double.MAX_VALUE)).setDescription("Max Health").setShouldWatch(true);
/*  20 */   public static final IAttribute followRange = (IAttribute)(new RangedAttribute(null, "generic.followRange", 32.0D, 0.0D, 2048.0D)).setDescription("Follow Range");
/*  21 */   public static final IAttribute knockbackResistance = (IAttribute)(new RangedAttribute(null, "generic.knockbackResistance", 0.0D, 0.0D, 1.0D)).setDescription("Knockback Resistance");
/*  22 */   public static final IAttribute movementSpeed = (IAttribute)(new RangedAttribute(null, "generic.movementSpeed", 0.699999988079071D, 0.0D, Double.MAX_VALUE)).setDescription("Movement Speed").setShouldWatch(true);
/*  23 */   public static final IAttribute attackDamage = (IAttribute)new RangedAttribute(null, "generic.attackDamage", 2.0D, 0.0D, Double.MAX_VALUE);
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001695";
/*     */ 
/*     */ 
/*     */   
/*     */   public static NBTTagList writeBaseAttributeMapToNBT(BaseAttributeMap p_111257_0_) {
/*  31 */     NBTTagList var1 = new NBTTagList();
/*  32 */     Iterator<IAttributeInstance> var2 = p_111257_0_.getAllAttributes().iterator();
/*     */     
/*  34 */     while (var2.hasNext()) {
/*     */       
/*  36 */       IAttributeInstance var3 = var2.next();
/*  37 */       var1.appendTag((NBTBase)writeAttributeInstanceToNBT(var3));
/*     */     } 
/*     */     
/*  40 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static NBTTagCompound writeAttributeInstanceToNBT(IAttributeInstance p_111261_0_) {
/*  48 */     NBTTagCompound var1 = new NBTTagCompound();
/*  49 */     IAttribute var2 = p_111261_0_.getAttribute();
/*  50 */     var1.setString("Name", var2.getAttributeUnlocalizedName());
/*  51 */     var1.setDouble("Base", p_111261_0_.getBaseValue());
/*  52 */     Collection var3 = p_111261_0_.func_111122_c();
/*     */     
/*  54 */     if (var3 != null && !var3.isEmpty()) {
/*     */       
/*  56 */       NBTTagList var4 = new NBTTagList();
/*  57 */       Iterator<AttributeModifier> var5 = var3.iterator();
/*     */       
/*  59 */       while (var5.hasNext()) {
/*     */         
/*  61 */         AttributeModifier var6 = var5.next();
/*     */         
/*  63 */         if (var6.isSaved())
/*     */         {
/*  65 */           var4.appendTag((NBTBase)writeAttributeModifierToNBT(var6));
/*     */         }
/*     */       } 
/*     */       
/*  69 */       var1.setTag("Modifiers", (NBTBase)var4);
/*     */     } 
/*     */     
/*  72 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static NBTTagCompound writeAttributeModifierToNBT(AttributeModifier p_111262_0_) {
/*  80 */     NBTTagCompound var1 = new NBTTagCompound();
/*  81 */     var1.setString("Name", p_111262_0_.getName());
/*  82 */     var1.setDouble("Amount", p_111262_0_.getAmount());
/*  83 */     var1.setInteger("Operation", p_111262_0_.getOperation());
/*  84 */     var1.setLong("UUIDMost", p_111262_0_.getID().getMostSignificantBits());
/*  85 */     var1.setLong("UUIDLeast", p_111262_0_.getID().getLeastSignificantBits());
/*  86 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_151475_a(BaseAttributeMap p_151475_0_, NBTTagList p_151475_1_) {
/*  91 */     for (int var2 = 0; var2 < p_151475_1_.tagCount(); var2++) {
/*     */       
/*  93 */       NBTTagCompound var3 = p_151475_1_.getCompoundTagAt(var2);
/*  94 */       IAttributeInstance var4 = p_151475_0_.getAttributeInstanceByName(var3.getString("Name"));
/*     */       
/*  96 */       if (var4 != null) {
/*     */         
/*  98 */         applyModifiersToAttributeInstance(var4, var3);
/*     */       }
/*     */       else {
/*     */         
/* 102 */         logger.warn("Ignoring unknown attribute '" + var3.getString("Name") + "'");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void applyModifiersToAttributeInstance(IAttributeInstance p_111258_0_, NBTTagCompound p_111258_1_) {
/* 109 */     p_111258_0_.setBaseValue(p_111258_1_.getDouble("Base"));
/*     */     
/* 111 */     if (p_111258_1_.hasKey("Modifiers", 9)) {
/*     */       
/* 113 */       NBTTagList var2 = p_111258_1_.getTagList("Modifiers", 10);
/*     */       
/* 115 */       for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */         
/* 117 */         AttributeModifier var4 = readAttributeModifierFromNBT(var2.getCompoundTagAt(var3));
/*     */         
/* 119 */         if (var4 != null) {
/*     */           
/* 121 */           AttributeModifier var5 = p_111258_0_.getModifier(var4.getID());
/*     */           
/* 123 */           if (var5 != null)
/*     */           {
/* 125 */             p_111258_0_.removeModifier(var5);
/*     */           }
/*     */           
/* 128 */           p_111258_0_.applyModifier(var4);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AttributeModifier readAttributeModifierFromNBT(NBTTagCompound p_111259_0_) {
/* 139 */     UUID var1 = new UUID(p_111259_0_.getLong("UUIDMost"), p_111259_0_.getLong("UUIDLeast"));
/*     */ 
/*     */     
/*     */     try {
/* 143 */       return new AttributeModifier(var1, p_111259_0_.getString("Name"), p_111259_0_.getDouble("Amount"), p_111259_0_.getInteger("Operation"));
/*     */     }
/* 145 */     catch (Exception var3) {
/*     */       
/* 147 */       logger.warn("Unable to create attribute: " + var3.getMessage());
/* 148 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\SharedMonsterAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */