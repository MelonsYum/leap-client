/*     */ package net.minecraft.village;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.SpawnerAnimals;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class VillageSiege
/*     */ {
/*     */   private World worldObj;
/*     */   private boolean field_75535_b;
/*  19 */   private int field_75536_c = -1;
/*     */   
/*     */   private int field_75533_d;
/*     */   
/*     */   private int field_75534_e;
/*     */   
/*     */   private Village theVillage;
/*     */   private int field_75532_g;
/*     */   private int field_75538_h;
/*     */   private int field_75539_i;
/*     */   private static final String __OBFID = "CL_00001634";
/*     */   
/*     */   public VillageSiege(World worldIn) {
/*  32 */     this.worldObj = worldIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  40 */     if (this.worldObj.isDaytime()) {
/*     */       
/*  42 */       this.field_75536_c = 0;
/*     */     }
/*  44 */     else if (this.field_75536_c != 2) {
/*     */       
/*  46 */       if (this.field_75536_c == 0) {
/*     */         
/*  48 */         float var1 = this.worldObj.getCelestialAngle(0.0F);
/*     */         
/*  50 */         if (var1 < 0.5D || var1 > 0.501D) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  55 */         this.field_75536_c = (this.worldObj.rand.nextInt(10) == 0) ? 1 : 2;
/*  56 */         this.field_75535_b = false;
/*     */         
/*  58 */         if (this.field_75536_c == 2) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  64 */       if (this.field_75536_c != -1) {
/*     */         
/*  66 */         if (!this.field_75535_b) {
/*     */           
/*  68 */           if (!func_75529_b()) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/*  73 */           this.field_75535_b = true;
/*     */         } 
/*     */         
/*  76 */         if (this.field_75534_e > 0) {
/*     */           
/*  78 */           this.field_75534_e--;
/*     */         }
/*     */         else {
/*     */           
/*  82 */           this.field_75534_e = 2;
/*     */           
/*  84 */           if (this.field_75533_d > 0) {
/*     */             
/*  86 */             spawnZombie();
/*  87 */             this.field_75533_d--;
/*     */           }
/*     */           else {
/*     */             
/*  91 */             this.field_75536_c = 2;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_75529_b() {
/* 100 */     List var1 = this.worldObj.playerEntities;
/* 101 */     Iterator<EntityPlayer> var2 = var1.iterator();
/*     */     
/* 103 */     while (var2.hasNext()) {
/*     */       
/* 105 */       EntityPlayer var3 = var2.next();
/*     */       
/* 107 */       if (!var3.func_175149_v()) {
/*     */         
/* 109 */         this.theVillage = this.worldObj.getVillageCollection().func_176056_a(new BlockPos((Entity)var3), 1);
/*     */         
/* 111 */         if (this.theVillage != null && this.theVillage.getNumVillageDoors() >= 10 && this.theVillage.getTicksSinceLastDoorAdding() >= 20 && this.theVillage.getNumVillagers() >= 20) {
/*     */           
/* 113 */           BlockPos var4 = this.theVillage.func_180608_a();
/* 114 */           float var5 = this.theVillage.getVillageRadius();
/* 115 */           boolean var6 = false;
/* 116 */           int var7 = 0;
/*     */ 
/*     */ 
/*     */           
/* 120 */           while (var7 < 10) {
/*     */             
/* 122 */             float var8 = this.worldObj.rand.nextFloat() * 3.1415927F * 2.0F;
/* 123 */             this.field_75532_g = var4.getX() + (int)((MathHelper.cos(var8) * var5) * 0.9D);
/* 124 */             this.field_75538_h = var4.getY();
/* 125 */             this.field_75539_i = var4.getZ() + (int)((MathHelper.sin(var8) * var5) * 0.9D);
/* 126 */             var6 = false;
/* 127 */             Iterator<Village> var9 = this.worldObj.getVillageCollection().getVillageList().iterator();
/*     */             
/* 129 */             while (var9.hasNext()) {
/*     */               
/* 131 */               Village var10 = var9.next();
/*     */               
/* 133 */               if (var10 != this.theVillage && var10.func_179866_a(new BlockPos(this.field_75532_g, this.field_75538_h, this.field_75539_i))) {
/*     */                 
/* 135 */                 var6 = true;
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 140 */             if (var6)
/*     */             {
/* 142 */               var7++;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 147 */           if (var6)
/*     */           {
/* 149 */             return false;
/*     */           }
/*     */           
/* 152 */           Vec3 var11 = func_179867_a(new BlockPos(this.field_75532_g, this.field_75538_h, this.field_75539_i));
/*     */           
/* 154 */           if (var11 != null) {
/*     */             
/* 156 */             this.field_75534_e = 0;
/* 157 */             this.field_75533_d = 20;
/* 158 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   private boolean spawnZombie() {
/*     */     EntityZombie var2;
/* 172 */     Vec3 var1 = func_179867_a(new BlockPos(this.field_75532_g, this.field_75538_h, this.field_75539_i));
/*     */     
/* 174 */     if (var1 == null)
/*     */     {
/* 176 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 184 */       var2 = new EntityZombie(this.worldObj);
/* 185 */       var2.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos((Entity)var2)), null);
/* 186 */       var2.setVillager(false);
/*     */     }
/* 188 */     catch (Exception var4) {
/*     */       
/* 190 */       var4.printStackTrace();
/* 191 */       return false;
/*     */     } 
/*     */     
/* 194 */     var2.setLocationAndAngles(var1.xCoord, var1.yCoord, var1.zCoord, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
/* 195 */     this.worldObj.spawnEntityInWorld((Entity)var2);
/* 196 */     BlockPos var3 = this.theVillage.func_180608_a();
/* 197 */     var2.func_175449_a(var3, this.theVillage.getVillageRadius());
/* 198 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Vec3 func_179867_a(BlockPos p_179867_1_) {
/* 204 */     for (int var2 = 0; var2 < 10; var2++) {
/*     */       
/* 206 */       BlockPos var3 = p_179867_1_.add(this.worldObj.rand.nextInt(16) - 8, this.worldObj.rand.nextInt(6) - 3, this.worldObj.rand.nextInt(16) - 8);
/*     */       
/* 208 */       if (this.theVillage.func_179866_a(var3) && SpawnerAnimals.func_180267_a(EntityLiving.SpawnPlacementType.ON_GROUND, this.worldObj, var3))
/*     */       {
/* 210 */         return new Vec3(var3.getX(), var3.getY(), var3.getZ());
/*     */       }
/*     */     } 
/*     */     
/* 214 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\village\VillageSiege.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */