/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.inventory.InventoryBasic;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class EntityAIVillagerInteract extends EntityAIWatchClosest2 {
/*    */   private int field_179478_e;
/*    */   private EntityVillager field_179477_f;
/*    */   private static final String __OBFID = "CL_00002251";
/*    */   
/*    */   public EntityAIVillagerInteract(EntityVillager p_i45886_1_) {
/* 19 */     super((EntityLiving)p_i45886_1_, EntityVillager.class, 3.0F, 0.02F);
/* 20 */     this.field_179477_f = p_i45886_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 28 */     super.startExecuting();
/*    */     
/* 30 */     if (this.field_179477_f.func_175555_cq() && this.closestEntity instanceof EntityVillager && ((EntityVillager)this.closestEntity).func_175557_cr()) {
/*    */       
/* 32 */       this.field_179478_e = 10;
/*    */     }
/*    */     else {
/*    */       
/* 36 */       this.field_179478_e = 0;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 45 */     super.updateTask();
/*    */     
/* 47 */     if (this.field_179478_e > 0) {
/*    */       
/* 49 */       this.field_179478_e--;
/*    */       
/* 51 */       if (this.field_179478_e == 0) {
/*    */         
/* 53 */         InventoryBasic var1 = this.field_179477_f.func_175551_co();
/*    */         
/* 55 */         for (int var2 = 0; var2 < var1.getSizeInventory(); var2++) {
/*    */           
/* 57 */           ItemStack var3 = var1.getStackInSlot(var2);
/* 58 */           ItemStack var4 = null;
/*    */           
/* 60 */           if (var3 != null) {
/*    */             
/* 62 */             Item var5 = var3.getItem();
/*    */ 
/*    */             
/* 65 */             if ((var5 == Items.bread || var5 == Items.potato || var5 == Items.carrot) && var3.stackSize > 3) {
/*    */               
/* 67 */               int var6 = var3.stackSize / 2;
/* 68 */               var3.stackSize -= var6;
/* 69 */               var4 = new ItemStack(var5, var6, var3.getMetadata());
/*    */             }
/* 71 */             else if (var5 == Items.wheat && var3.stackSize > 5) {
/*    */               
/* 73 */               int var6 = var3.stackSize / 2 / 3 * 3;
/* 74 */               int var7 = var6 / 3;
/* 75 */               var3.stackSize -= var6;
/* 76 */               var4 = new ItemStack(Items.bread, var7, 0);
/*    */             } 
/*    */             
/* 79 */             if (var3.stackSize <= 0)
/*    */             {
/* 81 */               var1.setInventorySlotContents(var2, null);
/*    */             }
/*    */           } 
/*    */           
/* 85 */           if (var4 != null) {
/*    */             
/* 87 */             double var11 = this.field_179477_f.posY - 0.30000001192092896D + this.field_179477_f.getEyeHeight();
/* 88 */             EntityItem var12 = new EntityItem(this.field_179477_f.worldObj, this.field_179477_f.posX, var11, this.field_179477_f.posZ, var4);
/* 89 */             float var8 = 0.3F;
/* 90 */             float var9 = this.field_179477_f.rotationYawHead;
/* 91 */             float var10 = this.field_179477_f.rotationPitch;
/* 92 */             var12.motionX = (-MathHelper.sin(var9 / 180.0F * 3.1415927F) * MathHelper.cos(var10 / 180.0F * 3.1415927F) * var8);
/* 93 */             var12.motionZ = (MathHelper.cos(var9 / 180.0F * 3.1415927F) * MathHelper.cos(var10 / 180.0F * 3.1415927F) * var8);
/* 94 */             var12.motionY = (-MathHelper.sin(var10 / 180.0F * 3.1415927F) * var8 + 0.1F);
/* 95 */             var12.setDefaultPickupDelay();
/* 96 */             this.field_179477_f.worldObj.spawnEntityInWorld((Entity)var12);
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIVillagerInteract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */