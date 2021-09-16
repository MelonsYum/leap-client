/*    */ package net.minecraft.entity.passive;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityAgeable;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityMooshroom
/*    */   extends EntityCow {
/*    */   public EntityMooshroom(World worldIn) {
/* 18 */     super(worldIn);
/* 19 */     setSize(0.9F, 1.3F);
/* 20 */     this.field_175506_bl = (Block)Blocks.mycelium;
/*    */   }
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00001645";
/*    */ 
/*    */   
/*    */   public boolean interact(EntityPlayer p_70085_1_) {
/* 28 */     ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
/*    */     
/* 30 */     if (var2 != null && var2.getItem() == Items.bowl && getGrowingAge() >= 0) {
/*    */       
/* 32 */       if (var2.stackSize == 1) {
/*    */         
/* 34 */         p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(Items.mushroom_stew));
/* 35 */         return true;
/*    */       } 
/*    */       
/* 38 */       if (p_70085_1_.inventory.addItemStackToInventory(new ItemStack(Items.mushroom_stew)) && !p_70085_1_.capabilities.isCreativeMode) {
/*    */         
/* 40 */         p_70085_1_.inventory.decrStackSize(p_70085_1_.inventory.currentItem, 1);
/* 41 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     if (var2 != null && var2.getItem() == Items.shears && getGrowingAge() >= 0) {
/*    */       
/* 47 */       setDead();
/* 48 */       this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY + (this.height / 2.0F), this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*    */       
/* 50 */       if (!this.worldObj.isRemote) {
/*    */         
/* 52 */         EntityCow var3 = new EntityCow(this.worldObj);
/* 53 */         var3.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/* 54 */         var3.setHealth(getHealth());
/* 55 */         var3.renderYawOffset = this.renderYawOffset;
/*    */         
/* 57 */         if (hasCustomName())
/*    */         {
/* 59 */           var3.setCustomNameTag(getCustomNameTag());
/*    */         }
/*    */         
/* 62 */         this.worldObj.spawnEntityInWorld((Entity)var3);
/*    */         
/* 64 */         for (int var4 = 0; var4 < 5; var4++)
/*    */         {
/* 66 */           this.worldObj.spawnEntityInWorld((Entity)new EntityItem(this.worldObj, this.posX, this.posY + this.height, this.posZ, new ItemStack((Block)Blocks.red_mushroom)));
/*    */         }
/*    */         
/* 69 */         var2.damageItem(1, (EntityLivingBase)p_70085_1_);
/* 70 */         playSound("mob.sheep.shear", 1.0F, 1.0F);
/*    */       } 
/*    */       
/* 73 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 77 */     return super.interact(p_70085_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityMooshroom createChild(EntityAgeable p_90011_1_) {
/* 83 */     return new EntityMooshroom(this.worldObj);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityMooshroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */