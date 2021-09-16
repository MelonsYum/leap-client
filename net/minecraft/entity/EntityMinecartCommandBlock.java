/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.command.server.CommandBlockLogic;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMinecartCommandBlock
/*     */   extends EntityMinecart {
/*  17 */   private final CommandBlockLogic field_145824_a = new CommandBlockLogic()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001673";
/*     */       
/*     */       public void func_145756_e() {
/*  22 */         EntityMinecartCommandBlock.this.getDataWatcher().updateObject(23, getCustomName());
/*  23 */         EntityMinecartCommandBlock.this.getDataWatcher().updateObject(24, IChatComponent.Serializer.componentToJson(getLastOutput()));
/*     */       }
/*     */       
/*     */       public int func_145751_f() {
/*  27 */         return 1;
/*     */       }
/*     */       
/*     */       public void func_145757_a(ByteBuf p_145757_1_) {
/*  31 */         p_145757_1_.writeInt(EntityMinecartCommandBlock.this.getEntityId());
/*     */       }
/*     */       
/*     */       public BlockPos getPosition() {
/*  35 */         return new BlockPos(EntityMinecartCommandBlock.this.posX, EntityMinecartCommandBlock.this.posY + 0.5D, EntityMinecartCommandBlock.this.posZ);
/*     */       }
/*     */       
/*     */       public Vec3 getPositionVector() {
/*  39 */         return new Vec3(EntityMinecartCommandBlock.this.posX, EntityMinecartCommandBlock.this.posY, EntityMinecartCommandBlock.this.posZ);
/*     */       }
/*     */       
/*     */       public World getEntityWorld() {
/*  43 */         return EntityMinecartCommandBlock.this.worldObj;
/*     */       }
/*     */       
/*     */       public Entity getCommandSenderEntity() {
/*  47 */         return (Entity)EntityMinecartCommandBlock.this;
/*     */       }
/*     */     };
/*  50 */   private int field_145823_b = 0;
/*     */   
/*     */   private static final String __OBFID = "CL_00001672";
/*     */   
/*     */   public EntityMinecartCommandBlock(World worldIn) {
/*  55 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartCommandBlock(World worldIn, double p_i45322_2_, double p_i45322_4_, double p_i45322_6_) {
/*  60 */     super(worldIn, p_i45322_2_, p_i45322_4_, p_i45322_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  65 */     super.entityInit();
/*  66 */     getDataWatcher().addObject(23, "");
/*  67 */     getDataWatcher().addObject(24, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  75 */     super.readEntityFromNBT(tagCompund);
/*  76 */     this.field_145824_a.readDataFromNBT(tagCompund);
/*  77 */     getDataWatcher().updateObject(23, func_145822_e().getCustomName());
/*  78 */     getDataWatcher().updateObject(24, IChatComponent.Serializer.componentToJson(func_145822_e().getLastOutput()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  86 */     super.writeEntityToNBT(tagCompound);
/*  87 */     this.field_145824_a.writeDataToNBT(tagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecart.EnumMinecartType func_180456_s() {
/*  92 */     return EntityMinecart.EnumMinecartType.COMMAND_BLOCK;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState func_180457_u() {
/*  97 */     return Blocks.command_block.getDefaultState();
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandBlockLogic func_145822_e() {
/* 102 */     return this.field_145824_a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onActivatorRailPass(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {
/* 110 */     if (p_96095_4_ && this.ticksExisted - this.field_145823_b >= 4) {
/*     */       
/* 112 */       func_145822_e().trigger(this.worldObj);
/* 113 */       this.field_145823_b = this.ticksExisted;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interactFirst(EntityPlayer playerIn) {
/* 122 */     this.field_145824_a.func_175574_a(playerIn);
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145781_i(int p_145781_1_) {
/* 128 */     super.func_145781_i(p_145781_1_);
/*     */     
/* 130 */     if (p_145781_1_ == 24) {
/*     */ 
/*     */       
/*     */       try {
/* 134 */         this.field_145824_a.func_145750_b(IChatComponent.Serializer.jsonToComponent(getDataWatcher().getWatchableObjectString(24)));
/*     */       }
/* 136 */       catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 141 */     else if (p_145781_1_ == 23) {
/*     */       
/* 143 */       this.field_145824_a.setCommand(getDataWatcher().getWatchableObjectString(23));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityMinecartCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */