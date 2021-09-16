/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.StringUtils;
/*     */ 
/*     */ public class TileEntitySkull
/*     */   extends TileEntity {
/*     */   private int skullType;
/*     */   private int skullRotation;
/*  18 */   private GameProfile playerProfile = null;
/*     */   
/*     */   private static final String __OBFID = "CL_00000364";
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/*  23 */     super.writeToNBT(compound);
/*  24 */     compound.setByte("SkullType", (byte)(this.skullType & 0xFF));
/*  25 */     compound.setByte("Rot", (byte)(this.skullRotation & 0xFF));
/*     */     
/*  27 */     if (this.playerProfile != null) {
/*     */       
/*  29 */       NBTTagCompound var2 = new NBTTagCompound();
/*  30 */       NBTUtil.writeGameProfile(var2, this.playerProfile);
/*  31 */       compound.setTag("Owner", (NBTBase)var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/*  37 */     super.readFromNBT(compound);
/*  38 */     this.skullType = compound.getByte("SkullType");
/*  39 */     this.skullRotation = compound.getByte("Rot");
/*     */     
/*  41 */     if (this.skullType == 3)
/*     */     {
/*  43 */       if (compound.hasKey("Owner", 10)) {
/*     */         
/*  45 */         this.playerProfile = NBTUtil.readGameProfileFromNBT(compound.getCompoundTag("Owner"));
/*     */       }
/*  47 */       else if (compound.hasKey("ExtraType", 8)) {
/*     */         
/*  49 */         String var2 = compound.getString("ExtraType");
/*     */         
/*  51 */         if (!StringUtils.isNullOrEmpty(var2)) {
/*     */           
/*  53 */           this.playerProfile = new GameProfile(null, var2);
/*  54 */           func_152109_d();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public GameProfile getPlayerProfile() {
/*  62 */     return this.playerProfile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet getDescriptionPacket() {
/*  70 */     NBTTagCompound var1 = new NBTTagCompound();
/*  71 */     writeToNBT(var1);
/*  72 */     return (Packet)new S35PacketUpdateTileEntity(this.pos, 4, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/*  77 */     this.skullType = type;
/*  78 */     this.playerProfile = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerProfile(GameProfile playerProfile) {
/*  83 */     this.skullType = 3;
/*  84 */     this.playerProfile = playerProfile;
/*  85 */     func_152109_d();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_152109_d() {
/*  90 */     this.playerProfile = updateGameprofile(this.playerProfile);
/*  91 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public static GameProfile updateGameprofile(GameProfile input) {
/*  96 */     if (input != null && !StringUtils.isNullOrEmpty(input.getName())) {
/*     */       
/*  98 */       if (input.isComplete() && input.getProperties().containsKey("textures"))
/*     */       {
/* 100 */         return input;
/*     */       }
/* 102 */       if (MinecraftServer.getServer() == null)
/*     */       {
/* 104 */         return input;
/*     */       }
/*     */ 
/*     */       
/* 108 */       GameProfile var1 = MinecraftServer.getServer().getPlayerProfileCache().getGameProfileForUsername(input.getName());
/*     */       
/* 110 */       if (var1 == null)
/*     */       {
/* 112 */         return input;
/*     */       }
/*     */ 
/*     */       
/* 116 */       Property var2 = (Property)Iterables.getFirst(var1.getProperties().get("textures"), null);
/*     */       
/* 118 */       if (var2 == null)
/*     */       {
/* 120 */         var1 = MinecraftServer.getServer().getMinecraftSessionService().fillProfileProperties(var1, true);
/*     */       }
/*     */       
/* 123 */       return var1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     return input;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSkullType() {
/* 135 */     return this.skullType;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSkullRotation() {
/* 140 */     return this.skullRotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkullRotation(int rotation) {
/* 145 */     this.skullRotation = rotation;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntitySkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */