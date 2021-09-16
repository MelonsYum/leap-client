/*     */ package leap.modules.player;
/*     */ 
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.modules.Module;
/*     */ import leap.util.RenderUtil;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*     */ import net.minecraft.network.play.client.C0APacketAnimation;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BedFucking
/*     */   extends Module
/*     */ {
/*     */   private int posX;
/*     */   private int posY;
/*     */   private int posZ;
/*     */   float pitch;
/*     */   float yaw;
/*  32 */   private final Timer time = new Timer();
/*  33 */   private final Timer time2 = new Timer();
/*  34 */   private final Timer time3 = new Timer();
/*     */ 
/*     */   
/*     */   public BedFucking() {
/*  38 */     super("BedFucking", 0, Module.Category.PLAYER);
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  42 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  46 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void faceBlock(double posX, double posY, double posZ) {
/*  50 */     double diffX = posX - mc.thePlayer.posX;
/*  51 */     double diffZ = posZ - mc.thePlayer.posZ;
/*  52 */     double diffY = posY - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
/*  53 */     double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
/*  54 */     float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
/*  55 */     float pitch = (float)-(Math.atan2(diffY, dist) * 180.0D / Math.PI);
/*  56 */     EntityPlayerSP var10000 = mc.thePlayer;
/*  57 */     var10000.rotationPitch += MathHelper.wrapAngleTo180_float(pitch - mc.thePlayer.rotationPitch);
/*  58 */     var10000 = mc.thePlayer;
/*  59 */     var10000.rotationYaw += MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw);
/*     */   }
/*     */   
/*     */   public float[] getBlockRotations(double x, double y, double z) {
/*  63 */     double var4 = x - mc.thePlayer.posX + 0.5D;
/*  64 */     double var6 = z - mc.thePlayer.posZ + 0.5D;
/*  65 */     double var8 = y - mc.thePlayer.posY + mc.thePlayer.getEyeHeight() - 1.0D;
/*  66 */     double var14 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
/*  67 */     float var12 = (float)(Math.atan2(var6, var4) * 180.0D / Math.PI) - 90.0F;
/*  68 */     return new float[] { var12, (float)-(Math.atan2(var8, var14) * 180.0D / Math.PI) };
/*     */   }
/*     */   
/*     */   public Block getBlockAtPos(BlockPos inBlockPos) {
/*  72 */     IBlockState s = mc.theWorld.getBlockState(inBlockPos);
/*  73 */     return s.getBlock();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {
/*  78 */     if (e instanceof leap.events.listeners.EventRenderWorld) {
/*  79 */       for (int y = 4; y >= -4; y--) {
/*  80 */         for (int x = -4; x <= 4; x++) {
/*  81 */           for (int z = -4; z <= 4; z++) {
/*  82 */             this.posX = (int)(mc.thePlayer.posX - 0.5D + x);
/*  83 */             this.posZ = (int)(mc.thePlayer.posZ - 0.5D + z);
/*  84 */             this.posY = (int)(mc.thePlayer.posY - 0.5D + y);
/*  85 */             Block block = getBlockAtPos(new BlockPos(this.posX, this.posY, this.posZ));
/*  86 */             if (block instanceof net.minecraft.block.BlockBed) {
/*  87 */               RenderUtil.drawBlockESP(this.posX, this.posY, this.posZ, 255.0F, 0.0F, 0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.2F, 1.0F);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  94 */     if (e instanceof EventMotion)
/*  95 */       for (int y = 4; y >= -4; y--) {
/*  96 */         for (int x = -4; x <= 4; x++) {
/*  97 */           for (int z = -4; z <= 4; z++) {
/*  98 */             this.posX = (int)(mc.thePlayer.posX - 0.5D + x);
/*  99 */             this.posZ = (int)(mc.thePlayer.posZ - 0.5D + z);
/* 100 */             this.posY = (int)(mc.thePlayer.posY - 0.5D + y);
/* 101 */             Block block = getBlockAtPos(new BlockPos(this.posX, this.posY, this.posZ));
/* 102 */             if (block instanceof net.minecraft.block.BlockBed) {
/* 103 */               long timeLeft = (long)(mc.playerController.curBlockDamageMP / 2.0F);
/* 104 */               if (this.time3.hasTimeElapsed(5000L, true))
/*     */               {
/* 106 */                 this.time3.reset();
/*     */               }
/*     */               
/* 109 */               EventMotion event = (EventMotion)e;
/* 110 */               float[] rotations = getBlockRotations(this.posX, this.posY, this.posZ);
/* 111 */               event.setYaw(rotations[0]);
/* 112 */               event.setPitch(rotations[1]);
/* 113 */               mc.getNetHandler().addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, new BlockPos(this.posX, this.posY, this.posZ), EnumFacing.DOWN));
/* 114 */               if (this.time.hasTimeElapsed(timeLeft, true)) {
/* 115 */                 mc.getNetHandler().addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(this.posX, this.posY, this.posZ), EnumFacing.DOWN));
/* 116 */                 mc.getNetHandler().addToSendQueue((Packet)new C0APacketAnimation());
/*     */ 
/*     */ 
/*     */                 
/* 120 */                 this.time.reset();
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\BedFucking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */