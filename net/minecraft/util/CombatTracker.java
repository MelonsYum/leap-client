/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ public class CombatTracker
/*     */ {
/*  16 */   private final List combatEntries = Lists.newArrayList();
/*     */   
/*     */   private final EntityLivingBase fighter;
/*     */   
/*     */   private int field_94555_c;
/*     */   
/*     */   private int field_152775_d;
/*     */   private int field_152776_e;
/*     */   private boolean field_94552_d;
/*     */   private boolean field_94553_e;
/*     */   private String field_94551_f;
/*     */   private static final String __OBFID = "CL_00001520";
/*     */   
/*     */   public CombatTracker(EntityLivingBase p_i1565_1_) {
/*  30 */     this.fighter = p_i1565_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_94545_a() {
/*  35 */     func_94542_g();
/*     */     
/*  37 */     if (this.fighter.isOnLadder()) {
/*     */       
/*  39 */       Block var1 = this.fighter.worldObj.getBlockState(new BlockPos(this.fighter.posX, (this.fighter.getEntityBoundingBox()).minY, this.fighter.posZ)).getBlock();
/*     */       
/*  41 */       if (var1 == Blocks.ladder)
/*     */       {
/*  43 */         this.field_94551_f = "ladder";
/*     */       }
/*  45 */       else if (var1 == Blocks.vine)
/*     */       {
/*  47 */         this.field_94551_f = "vines";
/*     */       }
/*     */     
/*  50 */     } else if (this.fighter.isInWater()) {
/*     */       
/*  52 */       this.field_94551_f = "water";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_94547_a(DamageSource p_94547_1_, float p_94547_2_, float p_94547_3_) {
/*  58 */     func_94549_h();
/*  59 */     func_94545_a();
/*  60 */     CombatEntry var4 = new CombatEntry(p_94547_1_, this.fighter.ticksExisted, p_94547_2_, p_94547_3_, this.field_94551_f, this.fighter.fallDistance);
/*  61 */     this.combatEntries.add(var4);
/*  62 */     this.field_94555_c = this.fighter.ticksExisted;
/*  63 */     this.field_94553_e = true;
/*     */     
/*  65 */     if (var4.func_94559_f() && !this.field_94552_d && this.fighter.isEntityAlive()) {
/*     */       
/*  67 */       this.field_94552_d = true;
/*  68 */       this.field_152775_d = this.fighter.ticksExisted;
/*  69 */       this.field_152776_e = this.field_152775_d;
/*  70 */       this.fighter.func_152111_bt();
/*     */     } 
/*     */   }
/*     */   
/*     */   public IChatComponent func_151521_b() {
/*     */     Object var3;
/*  76 */     if (this.combatEntries.size() == 0)
/*     */     {
/*  78 */       return new ChatComponentTranslation("death.attack.generic", new Object[] { this.fighter.getDisplayName() });
/*     */     }
/*     */ 
/*     */     
/*  82 */     CombatEntry var1 = func_94544_f();
/*  83 */     CombatEntry var2 = this.combatEntries.get(this.combatEntries.size() - 1);
/*  84 */     IChatComponent var4 = var2.func_151522_h();
/*  85 */     Entity var5 = var2.getDamageSrc().getEntity();
/*     */ 
/*     */     
/*  88 */     if (var1 != null && var2.getDamageSrc() == DamageSource.fall) {
/*     */       
/*  90 */       IChatComponent var6 = var1.func_151522_h();
/*     */       
/*  92 */       if (var1.getDamageSrc() != DamageSource.fall && var1.getDamageSrc() != DamageSource.outOfWorld) {
/*     */         
/*  94 */         if (var6 != null && (var4 == null || !var6.equals(var4))) {
/*     */           
/*  96 */           Entity var9 = var1.getDamageSrc().getEntity();
/*  97 */           ItemStack var8 = (var9 instanceof EntityLivingBase) ? ((EntityLivingBase)var9).getHeldItem() : null;
/*     */           
/*  99 */           if (var8 != null && var8.hasDisplayName())
/*     */           {
/* 101 */             var3 = new ChatComponentTranslation("death.fell.assist.item", new Object[] { this.fighter.getDisplayName(), var6, var8.getChatComponent() });
/*     */           }
/*     */           else
/*     */           {
/* 105 */             var3 = new ChatComponentTranslation("death.fell.assist", new Object[] { this.fighter.getDisplayName(), var6 });
/*     */           }
/*     */         
/* 108 */         } else if (var4 != null) {
/*     */           
/* 110 */           ItemStack var7 = (var5 instanceof EntityLivingBase) ? ((EntityLivingBase)var5).getHeldItem() : null;
/*     */           
/* 112 */           if (var7 != null && var7.hasDisplayName())
/*     */           {
/* 114 */             var3 = new ChatComponentTranslation("death.fell.finish.item", new Object[] { this.fighter.getDisplayName(), var4, var7.getChatComponent() });
/*     */           }
/*     */           else
/*     */           {
/* 118 */             var3 = new ChatComponentTranslation("death.fell.finish", new Object[] { this.fighter.getDisplayName(), var4 });
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 123 */           var3 = new ChatComponentTranslation("death.fell.killer", new Object[] { this.fighter.getDisplayName() });
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 128 */         var3 = new ChatComponentTranslation("death.fell.accident." + func_94548_b(var1), new Object[] { this.fighter.getDisplayName() });
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 133 */       var3 = var2.getDamageSrc().getDeathMessage(this.fighter);
/*     */     } 
/*     */     
/* 136 */     return (IChatComponent)var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLivingBase func_94550_c() {
/* 142 */     EntityLivingBase var1 = null;
/* 143 */     EntityPlayer var2 = null;
/* 144 */     float var3 = 0.0F;
/* 145 */     float var4 = 0.0F;
/* 146 */     Iterator<CombatEntry> var5 = this.combatEntries.iterator();
/*     */     
/* 148 */     while (var5.hasNext()) {
/*     */       
/* 150 */       CombatEntry var6 = var5.next();
/*     */       
/* 152 */       if (var6.getDamageSrc().getEntity() instanceof EntityPlayer && (var2 == null || var6.func_94563_c() > var4)) {
/*     */         
/* 154 */         var4 = var6.func_94563_c();
/* 155 */         var2 = (EntityPlayer)var6.getDamageSrc().getEntity();
/*     */       } 
/*     */       
/* 158 */       if (var6.getDamageSrc().getEntity() instanceof EntityLivingBase && (var1 == null || var6.func_94563_c() > var3)) {
/*     */         
/* 160 */         var3 = var6.func_94563_c();
/* 161 */         var1 = (EntityLivingBase)var6.getDamageSrc().getEntity();
/*     */       } 
/*     */     } 
/*     */     
/* 165 */     if (var2 != null && var4 >= var3 / 3.0F)
/*     */     {
/* 167 */       return (EntityLivingBase)var2;
/*     */     }
/*     */ 
/*     */     
/* 171 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private CombatEntry func_94544_f() {
/* 177 */     CombatEntry var1 = null;
/* 178 */     CombatEntry var2 = null;
/* 179 */     byte var3 = 0;
/* 180 */     float var4 = 0.0F;
/*     */     
/* 182 */     for (int var5 = 0; var5 < this.combatEntries.size(); var5++) {
/*     */       
/* 184 */       CombatEntry var6 = this.combatEntries.get(var5);
/* 185 */       CombatEntry var7 = (var5 > 0) ? this.combatEntries.get(var5 - 1) : null;
/*     */       
/* 187 */       if ((var6.getDamageSrc() == DamageSource.fall || var6.getDamageSrc() == DamageSource.outOfWorld) && var6.func_94561_i() > 0.0F && (var1 == null || var6.func_94561_i() > var4)) {
/*     */         
/* 189 */         if (var5 > 0) {
/*     */           
/* 191 */           var1 = var7;
/*     */         }
/*     */         else {
/*     */           
/* 195 */           var1 = var6;
/*     */         } 
/*     */         
/* 198 */         var4 = var6.func_94561_i();
/*     */       } 
/*     */       
/* 201 */       if (var6.func_94562_g() != null && (var2 == null || var6.func_94563_c() > var3))
/*     */       {
/* 203 */         var2 = var6;
/*     */       }
/*     */     } 
/*     */     
/* 207 */     if (var4 > 5.0F && var1 != null)
/*     */     {
/* 209 */       return var1;
/*     */     }
/* 211 */     if (var3 > 5 && var2 != null)
/*     */     {
/* 213 */       return var2;
/*     */     }
/*     */ 
/*     */     
/* 217 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String func_94548_b(CombatEntry p_94548_1_) {
/* 223 */     return (p_94548_1_.func_94562_g() == null) ? "generic" : p_94548_1_.func_94562_g();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_180134_f() {
/* 228 */     return this.field_94552_d ? (this.fighter.ticksExisted - this.field_152775_d) : (this.field_152776_e - this.field_152775_d);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_94542_g() {
/* 233 */     this.field_94551_f = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_94549_h() {
/* 238 */     int var1 = this.field_94552_d ? 300 : 100;
/*     */     
/* 240 */     if (this.field_94553_e && (!this.fighter.isEntityAlive() || this.fighter.ticksExisted - this.field_94555_c > var1)) {
/*     */       
/* 242 */       boolean var2 = this.field_94552_d;
/* 243 */       this.field_94553_e = false;
/* 244 */       this.field_94552_d = false;
/* 245 */       this.field_152776_e = this.fighter.ticksExisted;
/*     */       
/* 247 */       if (var2)
/*     */       {
/* 249 */         this.fighter.func_152112_bu();
/*     */       }
/*     */       
/* 252 */       this.combatEntries.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingBase func_180135_h() {
/* 258 */     return this.fighter;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\CombatTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */