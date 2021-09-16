/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ 
/*     */ public class S20PacketEntityProperties
/*     */   implements Packet {
/*     */   private int field_149445_a;
/*  20 */   private final List field_149444_b = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00001341";
/*     */   
/*     */   public S20PacketEntityProperties() {}
/*     */   
/*     */   public S20PacketEntityProperties(int p_i45236_1_, Collection p_i45236_2_) {
/*  27 */     this.field_149445_a = p_i45236_1_;
/*  28 */     Iterator<IAttributeInstance> var3 = p_i45236_2_.iterator();
/*     */     
/*  30 */     while (var3.hasNext()) {
/*     */       
/*  32 */       IAttributeInstance var4 = var3.next();
/*  33 */       this.field_149444_b.add(new Snapshot(var4.getAttribute().getAttributeUnlocalizedName(), var4.getBaseValue(), var4.func_111122_c()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  42 */     this.field_149445_a = data.readVarIntFromBuffer();
/*  43 */     int var2 = data.readInt();
/*     */     
/*  45 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/*  47 */       String var4 = data.readStringFromBuffer(64);
/*  48 */       double var5 = data.readDouble();
/*  49 */       ArrayList<AttributeModifier> var7 = Lists.newArrayList();
/*  50 */       int var8 = data.readVarIntFromBuffer();
/*     */       
/*  52 */       for (int var9 = 0; var9 < var8; var9++) {
/*     */         
/*  54 */         UUID var10 = data.readUuid();
/*  55 */         var7.add(new AttributeModifier(var10, "Unknown synced attribute modifier", data.readDouble(), data.readByte()));
/*     */       } 
/*     */       
/*  58 */       this.field_149444_b.add(new Snapshot(var4, var5, var7));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  67 */     data.writeVarIntToBuffer(this.field_149445_a);
/*  68 */     data.writeInt(this.field_149444_b.size());
/*  69 */     Iterator<Snapshot> var2 = this.field_149444_b.iterator();
/*     */     
/*  71 */     while (var2.hasNext()) {
/*     */       
/*  73 */       Snapshot var3 = var2.next();
/*  74 */       data.writeString(var3.func_151409_a());
/*  75 */       data.writeDouble(var3.func_151410_b());
/*  76 */       data.writeVarIntToBuffer(var3.func_151408_c().size());
/*  77 */       Iterator<AttributeModifier> var4 = var3.func_151408_c().iterator();
/*     */       
/*  79 */       while (var4.hasNext()) {
/*     */         
/*  81 */         AttributeModifier var5 = var4.next();
/*  82 */         data.writeUuid(var5.getID());
/*  83 */         data.writeDouble(var5.getAmount());
/*  84 */         data.writeByte(var5.getOperation());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180754_a(INetHandlerPlayClient p_180754_1_) {
/*  91 */     p_180754_1_.handleEntityProperties(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149442_c() {
/*  96 */     return this.field_149445_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_149441_d() {
/* 101 */     return this.field_149444_b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 109 */     func_180754_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */ 
/*     */   
/*     */   public class Snapshot
/*     */   {
/*     */     private final String field_151412_b;
/*     */     private final double field_151413_c;
/*     */     private final Collection field_151411_d;
/*     */     private static final String __OBFID = "CL_00001342";
/*     */     
/*     */     public Snapshot(String p_i45235_2_, double p_i45235_3_, Collection p_i45235_5_) {
/* 121 */       this.field_151412_b = p_i45235_2_;
/* 122 */       this.field_151413_c = p_i45235_3_;
/* 123 */       this.field_151411_d = p_i45235_5_;
/*     */     }
/*     */ 
/*     */     
/*     */     public String func_151409_a() {
/* 128 */       return this.field_151412_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public double func_151410_b() {
/* 133 */       return this.field_151413_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public Collection func_151408_c() {
/* 138 */       return this.field_151411_d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S20PacketEntityProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */