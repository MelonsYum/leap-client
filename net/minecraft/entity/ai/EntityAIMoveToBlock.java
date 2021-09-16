/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityAIMoveToBlock
/*     */   extends EntityAIBase {
/*     */   private final EntityCreature field_179495_c;
/*     */   private final double field_179492_d;
/*     */   protected int field_179496_a;
/*     */   private int field_179493_e;
/*     */   private int field_179490_f;
/*     */   protected BlockPos field_179494_b;
/*     */   private boolean field_179491_g;
/*     */   private int field_179497_h;
/*     */   private static final String __OBFID = "CL_00002252";
/*     */   
/*     */   public EntityAIMoveToBlock(EntityCreature p_i45888_1_, double p_i45888_2_, int p_i45888_4_) {
/*  21 */     this.field_179494_b = BlockPos.ORIGIN;
/*  22 */     this.field_179495_c = p_i45888_1_;
/*  23 */     this.field_179492_d = p_i45888_2_;
/*  24 */     this.field_179497_h = p_i45888_4_;
/*  25 */     setMutexBits(5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  33 */     if (this.field_179496_a > 0) {
/*     */       
/*  35 */       this.field_179496_a--;
/*  36 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  40 */     this.field_179496_a = 200 + this.field_179495_c.getRNG().nextInt(200);
/*  41 */     return func_179489_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  50 */     return (this.field_179493_e >= -this.field_179490_f && this.field_179493_e <= 1200 && func_179488_a(this.field_179495_c.worldObj, this.field_179494_b));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  58 */     this.field_179495_c.getNavigator().tryMoveToXYZ(this.field_179494_b.getX() + 0.5D, (this.field_179494_b.getY() + 1), this.field_179494_b.getZ() + 0.5D, this.field_179492_d);
/*  59 */     this.field_179493_e = 0;
/*  60 */     this.field_179490_f = this.field_179495_c.getRNG().nextInt(this.field_179495_c.getRNG().nextInt(1200) + 1200) + 1200;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  73 */     if (this.field_179495_c.func_174831_c(this.field_179494_b.offsetUp()) > 1.0D) {
/*     */       
/*  75 */       this.field_179491_g = false;
/*  76 */       this.field_179493_e++;
/*     */       
/*  78 */       if (this.field_179493_e % 40 == 0)
/*     */       {
/*  80 */         this.field_179495_c.getNavigator().tryMoveToXYZ(this.field_179494_b.getX() + 0.5D, (this.field_179494_b.getY() + 1), this.field_179494_b.getZ() + 0.5D, this.field_179492_d);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  85 */       this.field_179491_g = true;
/*  86 */       this.field_179493_e--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_179487_f() {
/*  92 */     return this.field_179491_g;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_179489_g() {
/*  97 */     int var1 = this.field_179497_h;
/*  98 */     boolean var2 = true;
/*  99 */     BlockPos var3 = new BlockPos((Entity)this.field_179495_c);
/*     */     
/* 101 */     for (int var4 = 0; var4 <= 1; var4 = (var4 > 0) ? -var4 : (1 - var4)) {
/*     */       
/* 103 */       for (int var5 = 0; var5 < var1; var5++) {
/*     */         
/* 105 */         for (int var6 = 0; var6 <= var5; var6 = (var6 > 0) ? -var6 : (1 - var6)) {
/*     */           
/* 107 */           for (int var7 = (var6 < var5 && var6 > -var5) ? var5 : 0; var7 <= var5; var7 = (var7 > 0) ? -var7 : (1 - var7)) {
/*     */             
/* 109 */             BlockPos var8 = var3.add(var6, var4 - 1, var7);
/*     */             
/* 111 */             if (this.field_179495_c.func_180485_d(var8) && func_179488_a(this.field_179495_c.worldObj, var8)) {
/*     */               
/* 113 */               this.field_179494_b = var8;
/* 114 */               return true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     return false;
/*     */   }
/*     */   
/*     */   protected abstract boolean func_179488_a(World paramWorld, BlockPos paramBlockPos);
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIMoveToBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */