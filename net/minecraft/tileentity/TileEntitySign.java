/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import com.google.gson.JsonParseException;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.CommandResultStats;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S33PacketUpdateSign;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentProcessor;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatStyle;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class TileEntitySign
/*     */   extends TileEntity {
/*  24 */   public final IChatComponent[] signText = new IChatComponent[] { (IChatComponent)new ChatComponentText(""), (IChatComponent)new ChatComponentText(""), (IChatComponent)new ChatComponentText(""), (IChatComponent)new ChatComponentText("") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   public int lineBeingEdited = -1;
/*     */   private boolean isEditable = true;
/*     */   private EntityPlayer field_145917_k;
/*  33 */   private final CommandResultStats field_174883_i = new CommandResultStats();
/*     */   
/*     */   private static final String __OBFID = "CL_00000363";
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/*  38 */     super.writeToNBT(compound);
/*     */     
/*  40 */     for (int var2 = 0; var2 < 4; var2++) {
/*     */       
/*  42 */       String var3 = IChatComponent.Serializer.componentToJson(this.signText[var2]);
/*  43 */       compound.setString("Text" + (var2 + 1), var3);
/*     */     } 
/*     */     
/*  46 */     this.field_174883_i.func_179670_b(compound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/*  51 */     this.isEditable = false;
/*  52 */     super.readFromNBT(compound);
/*  53 */     ICommandSender var2 = new ICommandSender()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002039";
/*     */         
/*     */         public String getName() {
/*  58 */           return "Sign";
/*     */         }
/*     */         
/*     */         public IChatComponent getDisplayName() {
/*  62 */           return (IChatComponent)new ChatComponentText(getName());
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean canCommandSenderUseCommand(int permissionLevel, String command) {
/*  67 */           return true;
/*     */         }
/*     */         public void addChatMessage(IChatComponent message) {}
/*     */         public BlockPos getPosition() {
/*  71 */           return TileEntitySign.this.pos;
/*     */         }
/*     */         
/*     */         public Vec3 getPositionVector() {
/*  75 */           return new Vec3(TileEntitySign.this.pos.getX() + 0.5D, TileEntitySign.this.pos.getY() + 0.5D, TileEntitySign.this.pos.getZ() + 0.5D);
/*     */         }
/*     */         
/*     */         public World getEntityWorld() {
/*  79 */           return TileEntitySign.this.worldObj;
/*     */         }
/*     */         
/*     */         public Entity getCommandSenderEntity() {
/*  83 */           return null;
/*     */         }
/*     */         
/*     */         public boolean sendCommandFeedback() {
/*  87 */           return false;
/*     */         }
/*     */         
/*     */         public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {}
/*     */       };
/*  92 */     for (int var3 = 0; var3 < 4; var3++) {
/*     */       
/*  94 */       String var4 = compound.getString("Text" + (var3 + 1));
/*     */ 
/*     */       
/*     */       try {
/*  98 */         IChatComponent var5 = IChatComponent.Serializer.jsonToComponent(var4);
/*     */ 
/*     */         
/*     */         try {
/* 102 */           this.signText[var3] = ChatComponentProcessor.func_179985_a(var2, var5, null);
/*     */         }
/* 104 */         catch (CommandException var7) {
/*     */           
/* 106 */           this.signText[var3] = var5;
/*     */         }
/*     */       
/* 109 */       } catch (JsonParseException var8) {
/*     */         
/* 111 */         this.signText[var3] = (IChatComponent)new ChatComponentText(var4);
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     this.field_174883_i.func_179668_a(compound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet getDescriptionPacket() {
/* 123 */     IChatComponent[] var1 = new IChatComponent[4];
/* 124 */     System.arraycopy(this.signText, 0, var1, 0, 4);
/* 125 */     return (Packet)new S33PacketUpdateSign(this.worldObj, this.pos, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsEditable() {
/* 130 */     return this.isEditable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEditable(boolean p_145913_1_) {
/* 138 */     this.isEditable = p_145913_1_;
/*     */     
/* 140 */     if (!p_145913_1_)
/*     */     {
/* 142 */       this.field_145917_k = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145912_a(EntityPlayer p_145912_1_) {
/* 148 */     this.field_145917_k = p_145912_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer func_145911_b() {
/* 153 */     return this.field_145917_k;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_174882_b(final EntityPlayer p_174882_1_) {
/* 158 */     ICommandSender var2 = new ICommandSender()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002038";
/*     */         
/*     */         public String getName() {
/* 163 */           return p_174882_1_.getName();
/*     */         }
/*     */         
/*     */         public IChatComponent getDisplayName() {
/* 167 */           return p_174882_1_.getDisplayName();
/*     */         }
/*     */         public void addChatMessage(IChatComponent message) {}
/*     */         
/*     */         public boolean canCommandSenderUseCommand(int permissionLevel, String command) {
/* 172 */           return true;
/*     */         }
/*     */         
/*     */         public BlockPos getPosition() {
/* 176 */           return TileEntitySign.this.pos;
/*     */         }
/*     */         
/*     */         public Vec3 getPositionVector() {
/* 180 */           return new Vec3(TileEntitySign.this.pos.getX() + 0.5D, TileEntitySign.this.pos.getY() + 0.5D, TileEntitySign.this.pos.getZ() + 0.5D);
/*     */         }
/*     */         
/*     */         public World getEntityWorld() {
/* 184 */           return p_174882_1_.getEntityWorld();
/*     */         }
/*     */         
/*     */         public Entity getCommandSenderEntity() {
/* 188 */           return (Entity)p_174882_1_;
/*     */         }
/*     */         
/*     */         public boolean sendCommandFeedback() {
/* 192 */           return false;
/*     */         }
/*     */         
/*     */         public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
/* 196 */           TileEntitySign.this.field_174883_i.func_179672_a(this, p_174794_1_, p_174794_2_);
/*     */         }
/*     */       };
/*     */     
/* 200 */     for (int var3 = 0; var3 < this.signText.length; var3++) {
/*     */       
/* 202 */       ChatStyle var4 = (this.signText[var3] == null) ? null : this.signText[var3].getChatStyle();
/*     */       
/* 204 */       if (var4 != null && var4.getChatClickEvent() != null) {
/*     */         
/* 206 */         ClickEvent var5 = var4.getChatClickEvent();
/*     */         
/* 208 */         if (var5.getAction() == ClickEvent.Action.RUN_COMMAND)
/*     */         {
/* 210 */           MinecraftServer.getServer().getCommandManager().executeCommand(var2, var5.getValue());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandResultStats func_174880_d() {
/* 220 */     return this.field_174883_i;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntitySign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */