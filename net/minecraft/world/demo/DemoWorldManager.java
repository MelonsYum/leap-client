/*     */ package net.minecraft.world.demo;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*     */ import net.minecraft.server.management.ItemInWorldManager;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class DemoWorldManager extends ItemInWorldManager {
/*     */   private boolean field_73105_c;
/*     */   private boolean demoTimeExpired;
/*     */   private int field_73104_e;
/*     */   private int field_73102_f;
/*     */   private static final String __OBFID = "CL_00001429";
/*     */   
/*     */   public DemoWorldManager(World worldIn) {
/*  22 */     super(worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateBlockRemoving() {
/*  27 */     super.updateBlockRemoving();
/*  28 */     this.field_73102_f++;
/*  29 */     long var1 = this.theWorld.getTotalWorldTime();
/*  30 */     long var3 = var1 / 24000L + 1L;
/*     */     
/*  32 */     if (!this.field_73105_c && this.field_73102_f > 20) {
/*     */       
/*  34 */       this.field_73105_c = true;
/*  35 */       this.thisPlayerMP.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(5, 0.0F));
/*     */     } 
/*     */     
/*  38 */     this.demoTimeExpired = (var1 > 120500L);
/*     */     
/*  40 */     if (this.demoTimeExpired)
/*     */     {
/*  42 */       this.field_73104_e++;
/*     */     }
/*     */     
/*  45 */     if (var1 % 24000L == 500L) {
/*     */       
/*  47 */       if (var3 <= 6L)
/*     */       {
/*  49 */         this.thisPlayerMP.addChatMessage((IChatComponent)new ChatComponentTranslation("demo.day." + var3, new Object[0]));
/*     */       }
/*     */     }
/*  52 */     else if (var3 == 1L) {
/*     */       
/*  54 */       if (var1 == 100L)
/*     */       {
/*  56 */         this.thisPlayerMP.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(5, 101.0F));
/*     */       }
/*  58 */       else if (var1 == 175L)
/*     */       {
/*  60 */         this.thisPlayerMP.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(5, 102.0F));
/*     */       }
/*  62 */       else if (var1 == 250L)
/*     */       {
/*  64 */         this.thisPlayerMP.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(5, 103.0F));
/*     */       }
/*     */     
/*  67 */     } else if (var3 == 5L && var1 % 24000L == 22000L) {
/*     */       
/*  69 */       this.thisPlayerMP.addChatMessage((IChatComponent)new ChatComponentTranslation("demo.day.warning", new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendDemoReminder() {
/*  78 */     if (this.field_73104_e > 100) {
/*     */       
/*  80 */       this.thisPlayerMP.addChatMessage((IChatComponent)new ChatComponentTranslation("demo.reminder", new Object[0]));
/*  81 */       this.field_73104_e = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180784_a(BlockPos p_180784_1_, EnumFacing p_180784_2_) {
/*  87 */     if (this.demoTimeExpired) {
/*     */       
/*  89 */       sendDemoReminder();
/*     */     }
/*     */     else {
/*     */       
/*  93 */       super.func_180784_a(p_180784_1_, p_180784_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180785_a(BlockPos p_180785_1_) {
/*  99 */     if (!this.demoTimeExpired)
/*     */     {
/* 101 */       super.func_180785_a(p_180785_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180237_b(BlockPos p_180237_1_) {
/* 107 */     return this.demoTimeExpired ? false : super.func_180237_b(p_180237_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryUseItem(EntityPlayer p_73085_1_, World worldIn, ItemStack p_73085_3_) {
/* 115 */     if (this.demoTimeExpired) {
/*     */       
/* 117 */       sendDemoReminder();
/* 118 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 122 */     return super.tryUseItem(p_73085_1_, worldIn, p_73085_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_180236_a(EntityPlayer p_180236_1_, World worldIn, ItemStack p_180236_3_, BlockPos p_180236_4_, EnumFacing p_180236_5_, float p_180236_6_, float p_180236_7_, float p_180236_8_) {
/* 128 */     if (this.demoTimeExpired) {
/*     */       
/* 130 */       sendDemoReminder();
/* 131 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 135 */     return super.func_180236_a(p_180236_1_, worldIn, p_180236_3_, p_180236_4_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\demo\DemoWorldManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */