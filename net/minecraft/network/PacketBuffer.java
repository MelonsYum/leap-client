/*      */ package net.minecraft.network;
/*      */ 
/*      */ import com.google.common.base.Charsets;
/*      */ import io.netty.buffer.ByteBuf;
/*      */ import io.netty.buffer.ByteBufAllocator;
/*      */ import io.netty.buffer.ByteBufInputStream;
/*      */ import io.netty.buffer.ByteBufOutputStream;
/*      */ import io.netty.buffer.ByteBufProcessor;
/*      */ import io.netty.handler.codec.DecoderException;
/*      */ import io.netty.handler.codec.EncoderException;
/*      */ import io.netty.util.ReferenceCounted;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.channels.GatheringByteChannel;
/*      */ import java.nio.channels.ScatteringByteChannel;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.CompressedStreamTools;
/*      */ import net.minecraft.nbt.NBTSizeTracker;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ 
/*      */ public class PacketBuffer extends ByteBuf {
/*      */   private final ByteBuf buf;
/*      */   private static final String __OBFID = "CL_00001251";
/*      */   
/*      */   public PacketBuffer(ByteBuf wrapped) {
/*   36 */     this.buf = wrapped;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getVarIntSize(int input) {
/*   45 */     for (int var1 = 1; var1 < 5; var1++) {
/*      */       
/*   47 */       if ((input & -1 << var1 * 7) == 0)
/*      */       {
/*   49 */         return var1;
/*      */       }
/*      */     } 
/*      */     
/*   53 */     return 5;
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeByteArray(byte[] array) {
/*   58 */     writeVarIntToBuffer(array.length);
/*   59 */     writeBytes(array);
/*      */   }
/*      */ 
/*      */   
/*      */   public byte[] readByteArray() {
/*   64 */     byte[] var1 = new byte[readVarIntFromBuffer()];
/*   65 */     readBytes(var1);
/*   66 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPos readBlockPos() {
/*   71 */     return BlockPos.fromLong(readLong());
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeBlockPos(BlockPos pos) {
/*   76 */     writeLong(pos.toLong());
/*      */   }
/*      */ 
/*      */   
/*      */   public IChatComponent readChatComponent() {
/*   81 */     return IChatComponent.Serializer.jsonToComponent(readStringFromBuffer(32767));
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeChatComponent(IChatComponent component) {
/*   86 */     writeString(IChatComponent.Serializer.componentToJson(component));
/*      */   }
/*      */ 
/*      */   
/*      */   public Enum readEnumValue(Class enumClass) {
/*   91 */     return ((Enum[])enumClass.getEnumConstants())[readVarIntFromBuffer()];
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeEnumValue(Enum value) {
/*   96 */     writeVarIntToBuffer(value.ordinal());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readVarIntFromBuffer() {
/*      */     byte var3;
/*  105 */     int var1 = 0;
/*  106 */     int var2 = 0;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  111 */       var3 = readByte();
/*  112 */       var1 |= (var3 & Byte.MAX_VALUE) << var2++ * 7;
/*      */       
/*  114 */       if (var2 > 5)
/*      */       {
/*  116 */         throw new RuntimeException("VarInt too big");
/*      */       }
/*      */     }
/*  119 */     while ((var3 & 0x80) == 128);
/*      */     
/*  121 */     return var1;
/*      */   }
/*      */   
/*      */   public long readVarLong() {
/*      */     byte var4;
/*  126 */     long var1 = 0L;
/*  127 */     int var3 = 0;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  132 */       var4 = readByte();
/*  133 */       var1 |= (var4 & Byte.MAX_VALUE) << var3++ * 7;
/*      */       
/*  135 */       if (var3 > 10)
/*      */       {
/*  137 */         throw new RuntimeException("VarLong too big");
/*      */       }
/*      */     }
/*  140 */     while ((var4 & 0x80) == 128);
/*      */     
/*  142 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeUuid(UUID uuid) {
/*  147 */     writeLong(uuid.getMostSignificantBits());
/*  148 */     writeLong(uuid.getLeastSignificantBits());
/*      */   }
/*      */ 
/*      */   
/*      */   public UUID readUuid() {
/*  153 */     return new UUID(readLong(), readLong());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeVarIntToBuffer(int input) {
/*  164 */     while ((input & 0xFFFFFF80) != 0) {
/*      */       
/*  166 */       writeByte(input & 0x7F | 0x80);
/*  167 */       input >>>= 7;
/*      */     } 
/*      */     
/*  170 */     writeByte(input);
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeVarLong(long value) {
/*  175 */     while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
/*      */       
/*  177 */       writeByte((int)(value & 0x7FL) | 0x80);
/*  178 */       value >>>= 7L;
/*      */     } 
/*      */     
/*  181 */     writeByte((int)value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeNBTTagCompoundToBuffer(NBTTagCompound nbt) {
/*  189 */     if (nbt == null) {
/*      */       
/*  191 */       writeByte(0);
/*      */     } else {
/*      */ 
/*      */       
/*      */       try {
/*      */         
/*  197 */         CompressedStreamTools.write(nbt, (DataOutput)new ByteBufOutputStream(this));
/*      */       }
/*  199 */       catch (IOException var3) {
/*      */         
/*  201 */         throw new EncoderException(var3);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound readNBTTagCompoundFromBuffer() throws IOException {
/*  211 */     int var1 = readerIndex();
/*  212 */     byte var2 = readByte();
/*      */     
/*  214 */     if (var2 == 0)
/*      */     {
/*  216 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  220 */     readerIndex(var1);
/*  221 */     return CompressedStreamTools.func_152456_a((DataInput)new ByteBufInputStream(this), new NBTSizeTracker(2097152L));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeItemStackToBuffer(ItemStack stack) {
/*  230 */     if (stack == null) {
/*      */       
/*  232 */       writeShort(-1);
/*      */     }
/*      */     else {
/*      */       
/*  236 */       writeShort(Item.getIdFromItem(stack.getItem()));
/*  237 */       writeByte(stack.stackSize);
/*  238 */       writeShort(stack.getMetadata());
/*  239 */       NBTTagCompound var2 = null;
/*      */       
/*  241 */       if (stack.getItem().isDamageable() || stack.getItem().getShareTag())
/*      */       {
/*  243 */         var2 = stack.getTagCompound();
/*      */       }
/*      */       
/*  246 */       writeNBTTagCompoundToBuffer(var2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack readItemStackFromBuffer() throws IOException {
/*  255 */     ItemStack var1 = null;
/*  256 */     short var2 = readShort();
/*      */     
/*  258 */     if (var2 >= 0) {
/*      */       
/*  260 */       byte var3 = readByte();
/*  261 */       short var4 = readShort();
/*  262 */       var1 = new ItemStack(Item.getItemById(var2), var3, var4);
/*  263 */       var1.setTagCompound(readNBTTagCompoundFromBuffer());
/*      */     } 
/*      */     
/*  266 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readStringFromBuffer(int maxLength) {
/*  275 */     int var2 = readVarIntFromBuffer();
/*      */     
/*  277 */     if (var2 > maxLength * 4)
/*      */     {
/*  279 */       throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + var2 + " > " + (maxLength * 4) + ")");
/*      */     }
/*  281 */     if (var2 < 0)
/*      */     {
/*  283 */       throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
/*      */     }
/*      */ 
/*      */     
/*  287 */     String var3 = new String(readBytes(var2).array(), Charsets.UTF_8);
/*      */     
/*  289 */     if (var3.length() > maxLength)
/*      */     {
/*  291 */       throw new DecoderException("The received string length is longer than maximum allowed (" + var2 + " > " + maxLength + ")");
/*      */     }
/*      */ 
/*      */     
/*  295 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PacketBuffer writeString(String string) {
/*  302 */     byte[] var2 = string.getBytes(Charsets.UTF_8);
/*      */     
/*  304 */     if (var2.length > 32767)
/*      */     {
/*  306 */       throw new EncoderException("String too big (was " + string.length() + " bytes encoded, max " + '翿' + ")");
/*      */     }
/*      */ 
/*      */     
/*  310 */     writeVarIntToBuffer(var2.length);
/*  311 */     writeBytes(var2);
/*  312 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int capacity() {
/*  318 */     return this.buf.capacity();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf capacity(int p_capacity_1_) {
/*  323 */     return this.buf.capacity(p_capacity_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int maxCapacity() {
/*  328 */     return this.buf.maxCapacity();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBufAllocator alloc() {
/*  333 */     return this.buf.alloc();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteOrder order() {
/*  338 */     return this.buf.order();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf order(ByteOrder p_order_1_) {
/*  343 */     return this.buf.order(p_order_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf unwrap() {
/*  348 */     return this.buf.unwrap();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDirect() {
/*  353 */     return this.buf.isDirect();
/*      */   }
/*      */ 
/*      */   
/*      */   public int readerIndex() {
/*  358 */     return this.buf.readerIndex();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readerIndex(int p_readerIndex_1_) {
/*  363 */     return this.buf.readerIndex(p_readerIndex_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int writerIndex() {
/*  368 */     return this.buf.writerIndex();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writerIndex(int p_writerIndex_1_) {
/*  373 */     return this.buf.writerIndex(p_writerIndex_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setIndex(int p_setIndex_1_, int p_setIndex_2_) {
/*  378 */     return this.buf.setIndex(p_setIndex_1_, p_setIndex_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int readableBytes() {
/*  383 */     return this.buf.readableBytes();
/*      */   }
/*      */ 
/*      */   
/*      */   public int writableBytes() {
/*  388 */     return this.buf.writableBytes();
/*      */   }
/*      */ 
/*      */   
/*      */   public int maxWritableBytes() {
/*  393 */     return this.buf.maxWritableBytes();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isReadable() {
/*  398 */     return this.buf.isReadable();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isReadable(int p_isReadable_1_) {
/*  403 */     return this.buf.isReadable(p_isReadable_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWritable() {
/*  408 */     return this.buf.isWritable();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWritable(int p_isWritable_1_) {
/*  413 */     return this.buf.isWritable(p_isWritable_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf clear() {
/*  418 */     return this.buf.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf markReaderIndex() {
/*  423 */     return this.buf.markReaderIndex();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf resetReaderIndex() {
/*  428 */     return this.buf.resetReaderIndex();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf markWriterIndex() {
/*  433 */     return this.buf.markWriterIndex();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf resetWriterIndex() {
/*  438 */     return this.buf.resetWriterIndex();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf discardReadBytes() {
/*  443 */     return this.buf.discardReadBytes();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf discardSomeReadBytes() {
/*  448 */     return this.buf.discardSomeReadBytes();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf ensureWritable(int p_ensureWritable_1_) {
/*  453 */     return this.buf.ensureWritable(p_ensureWritable_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int ensureWritable(int p_ensureWritable_1_, boolean p_ensureWritable_2_) {
/*  458 */     return this.buf.ensureWritable(p_ensureWritable_1_, p_ensureWritable_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getBoolean(int p_getBoolean_1_) {
/*  463 */     return this.buf.getBoolean(p_getBoolean_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public byte getByte(int p_getByte_1_) {
/*  468 */     return this.buf.getByte(p_getByte_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public short getUnsignedByte(int p_getUnsignedByte_1_) {
/*  473 */     return this.buf.getUnsignedByte(p_getUnsignedByte_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public short getShort(int p_getShort_1_) {
/*  478 */     return this.buf.getShort(p_getShort_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getUnsignedShort(int p_getUnsignedShort_1_) {
/*  483 */     return this.buf.getUnsignedShort(p_getUnsignedShort_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMedium(int p_getMedium_1_) {
/*  488 */     return this.buf.getMedium(p_getMedium_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getUnsignedMedium(int p_getUnsignedMedium_1_) {
/*  493 */     return this.buf.getUnsignedMedium(p_getUnsignedMedium_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getInt(int p_getInt_1_) {
/*  498 */     return this.buf.getInt(p_getInt_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public long getUnsignedInt(int p_getUnsignedInt_1_) {
/*  503 */     return this.buf.getUnsignedInt(p_getUnsignedInt_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLong(int p_getLong_1_) {
/*  508 */     return this.buf.getLong(p_getLong_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public char getChar(int p_getChar_1_) {
/*  513 */     return this.buf.getChar(p_getChar_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getFloat(int p_getFloat_1_) {
/*  518 */     return this.buf.getFloat(p_getFloat_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public double getDouble(int p_getDouble_1_) {
/*  523 */     return this.buf.getDouble(p_getDouble_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf getBytes(int p_getBytes_1_, ByteBuf p_getBytes_2_) {
/*  528 */     return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf getBytes(int p_getBytes_1_, ByteBuf p_getBytes_2_, int p_getBytes_3_) {
/*  533 */     return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf getBytes(int p_getBytes_1_, ByteBuf p_getBytes_2_, int p_getBytes_3_, int p_getBytes_4_) {
/*  538 */     return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_, p_getBytes_4_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf getBytes(int p_getBytes_1_, byte[] p_getBytes_2_) {
/*  543 */     return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf getBytes(int p_getBytes_1_, byte[] p_getBytes_2_, int p_getBytes_3_, int p_getBytes_4_) {
/*  548 */     return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_, p_getBytes_4_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf getBytes(int p_getBytes_1_, ByteBuffer p_getBytes_2_) {
/*  553 */     return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf getBytes(int p_getBytes_1_, OutputStream p_getBytes_2_, int p_getBytes_3_) throws IOException {
/*  558 */     return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBytes(int p_getBytes_1_, GatheringByteChannel p_getBytes_2_, int p_getBytes_3_) throws IOException {
/*  563 */     return this.buf.getBytes(p_getBytes_1_, p_getBytes_2_, p_getBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setBoolean(int p_setBoolean_1_, boolean p_setBoolean_2_) {
/*  568 */     return this.buf.setBoolean(p_setBoolean_1_, p_setBoolean_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setByte(int p_setByte_1_, int p_setByte_2_) {
/*  573 */     return this.buf.setByte(p_setByte_1_, p_setByte_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setShort(int p_setShort_1_, int p_setShort_2_) {
/*  578 */     return this.buf.setShort(p_setShort_1_, p_setShort_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setMedium(int p_setMedium_1_, int p_setMedium_2_) {
/*  583 */     return this.buf.setMedium(p_setMedium_1_, p_setMedium_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setInt(int p_setInt_1_, int p_setInt_2_) {
/*  588 */     return this.buf.setInt(p_setInt_1_, p_setInt_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setLong(int p_setLong_1_, long p_setLong_2_) {
/*  593 */     return this.buf.setLong(p_setLong_1_, p_setLong_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setChar(int p_setChar_1_, int p_setChar_2_) {
/*  598 */     return this.buf.setChar(p_setChar_1_, p_setChar_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setFloat(int p_setFloat_1_, float p_setFloat_2_) {
/*  603 */     return this.buf.setFloat(p_setFloat_1_, p_setFloat_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setDouble(int p_setDouble_1_, double p_setDouble_2_) {
/*  608 */     return this.buf.setDouble(p_setDouble_1_, p_setDouble_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setBytes(int p_setBytes_1_, ByteBuf p_setBytes_2_) {
/*  613 */     return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setBytes(int p_setBytes_1_, ByteBuf p_setBytes_2_, int p_setBytes_3_) {
/*  618 */     return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setBytes(int p_setBytes_1_, ByteBuf p_setBytes_2_, int p_setBytes_3_, int p_setBytes_4_) {
/*  623 */     return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_, p_setBytes_4_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setBytes(int p_setBytes_1_, byte[] p_setBytes_2_) {
/*  628 */     return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setBytes(int p_setBytes_1_, byte[] p_setBytes_2_, int p_setBytes_3_, int p_setBytes_4_) {
/*  633 */     return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_, p_setBytes_4_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setBytes(int p_setBytes_1_, ByteBuffer p_setBytes_2_) {
/*  638 */     return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int setBytes(int p_setBytes_1_, InputStream p_setBytes_2_, int p_setBytes_3_) throws IOException {
/*  643 */     return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int setBytes(int p_setBytes_1_, ScatteringByteChannel p_setBytes_2_, int p_setBytes_3_) throws IOException {
/*  648 */     return this.buf.setBytes(p_setBytes_1_, p_setBytes_2_, p_setBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf setZero(int p_setZero_1_, int p_setZero_2_) {
/*  653 */     return this.buf.setZero(p_setZero_1_, p_setZero_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean readBoolean() {
/*  658 */     return this.buf.readBoolean();
/*      */   }
/*      */ 
/*      */   
/*      */   public byte readByte() {
/*  663 */     return this.buf.readByte();
/*      */   }
/*      */ 
/*      */   
/*      */   public short readUnsignedByte() {
/*  668 */     return this.buf.readUnsignedByte();
/*      */   }
/*      */ 
/*      */   
/*      */   public short readShort() {
/*  673 */     return this.buf.readShort();
/*      */   }
/*      */ 
/*      */   
/*      */   public int readUnsignedShort() {
/*  678 */     return this.buf.readUnsignedShort();
/*      */   }
/*      */ 
/*      */   
/*      */   public int readMedium() {
/*  683 */     return this.buf.readMedium();
/*      */   }
/*      */ 
/*      */   
/*      */   public int readUnsignedMedium() {
/*  688 */     return this.buf.readUnsignedMedium();
/*      */   }
/*      */ 
/*      */   
/*      */   public int readInt() {
/*  693 */     return this.buf.readInt();
/*      */   }
/*      */ 
/*      */   
/*      */   public long readUnsignedInt() {
/*  698 */     return this.buf.readUnsignedInt();
/*      */   }
/*      */ 
/*      */   
/*      */   public long readLong() {
/*  703 */     return this.buf.readLong();
/*      */   }
/*      */ 
/*      */   
/*      */   public char readChar() {
/*  708 */     return this.buf.readChar();
/*      */   }
/*      */ 
/*      */   
/*      */   public float readFloat() {
/*  713 */     return this.buf.readFloat();
/*      */   }
/*      */ 
/*      */   
/*      */   public double readDouble() {
/*  718 */     return this.buf.readDouble();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readBytes(int p_readBytes_1_) {
/*  723 */     return this.buf.readBytes(p_readBytes_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readSlice(int p_readSlice_1_) {
/*  728 */     return this.buf.readSlice(p_readSlice_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readBytes(ByteBuf p_readBytes_1_) {
/*  733 */     return this.buf.readBytes(p_readBytes_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readBytes(ByteBuf p_readBytes_1_, int p_readBytes_2_) {
/*  738 */     return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readBytes(ByteBuf p_readBytes_1_, int p_readBytes_2_, int p_readBytes_3_) {
/*  743 */     return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_, p_readBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readBytes(byte[] p_readBytes_1_) {
/*  748 */     return this.buf.readBytes(p_readBytes_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readBytes(byte[] p_readBytes_1_, int p_readBytes_2_, int p_readBytes_3_) {
/*  753 */     return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_, p_readBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readBytes(ByteBuffer p_readBytes_1_) {
/*  758 */     return this.buf.readBytes(p_readBytes_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf readBytes(OutputStream p_readBytes_1_, int p_readBytes_2_) throws IOException {
/*  763 */     return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int readBytes(GatheringByteChannel p_readBytes_1_, int p_readBytes_2_) throws IOException {
/*  768 */     return this.buf.readBytes(p_readBytes_1_, p_readBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf skipBytes(int p_skipBytes_1_) {
/*  773 */     return this.buf.skipBytes(p_skipBytes_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeBoolean(boolean p_writeBoolean_1_) {
/*  778 */     return this.buf.writeBoolean(p_writeBoolean_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeByte(int p_writeByte_1_) {
/*  783 */     return this.buf.writeByte(p_writeByte_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeShort(int p_writeShort_1_) {
/*  788 */     return this.buf.writeShort(p_writeShort_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeMedium(int p_writeMedium_1_) {
/*  793 */     return this.buf.writeMedium(p_writeMedium_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeInt(int p_writeInt_1_) {
/*  798 */     return this.buf.writeInt(p_writeInt_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeLong(long p_writeLong_1_) {
/*  803 */     return this.buf.writeLong(p_writeLong_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeChar(int p_writeChar_1_) {
/*  808 */     return this.buf.writeChar(p_writeChar_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeFloat(float p_writeFloat_1_) {
/*  813 */     return this.buf.writeFloat(p_writeFloat_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeDouble(double p_writeDouble_1_) {
/*  818 */     return this.buf.writeDouble(p_writeDouble_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuf p_writeBytes_1_) {
/*  823 */     return this.buf.writeBytes(p_writeBytes_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuf p_writeBytes_1_, int p_writeBytes_2_) {
/*  828 */     return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuf p_writeBytes_1_, int p_writeBytes_2_, int p_writeBytes_3_) {
/*  833 */     return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_, p_writeBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeBytes(byte[] p_writeBytes_1_) {
/*  838 */     return this.buf.writeBytes(p_writeBytes_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeBytes(byte[] p_writeBytes_1_, int p_writeBytes_2_, int p_writeBytes_3_) {
/*  843 */     return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_, p_writeBytes_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuffer p_writeBytes_1_) {
/*  848 */     return this.buf.writeBytes(p_writeBytes_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int writeBytes(InputStream p_writeBytes_1_, int p_writeBytes_2_) throws IOException {
/*  853 */     return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int writeBytes(ScatteringByteChannel p_writeBytes_1_, int p_writeBytes_2_) throws IOException {
/*  858 */     return this.buf.writeBytes(p_writeBytes_1_, p_writeBytes_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf writeZero(int p_writeZero_1_) {
/*  863 */     return this.buf.writeZero(p_writeZero_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int indexOf(int p_indexOf_1_, int p_indexOf_2_, byte p_indexOf_3_) {
/*  868 */     return this.buf.indexOf(p_indexOf_1_, p_indexOf_2_, p_indexOf_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int bytesBefore(byte p_bytesBefore_1_) {
/*  873 */     return this.buf.bytesBefore(p_bytesBefore_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int bytesBefore(int p_bytesBefore_1_, byte p_bytesBefore_2_) {
/*  878 */     return this.buf.bytesBefore(p_bytesBefore_1_, p_bytesBefore_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int bytesBefore(int p_bytesBefore_1_, int p_bytesBefore_2_, byte p_bytesBefore_3_) {
/*  883 */     return this.buf.bytesBefore(p_bytesBefore_1_, p_bytesBefore_2_, p_bytesBefore_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int forEachByte(ByteBufProcessor p_forEachByte_1_) {
/*  888 */     return this.buf.forEachByte(p_forEachByte_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int forEachByte(int p_forEachByte_1_, int p_forEachByte_2_, ByteBufProcessor p_forEachByte_3_) {
/*  893 */     return this.buf.forEachByte(p_forEachByte_1_, p_forEachByte_2_, p_forEachByte_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int forEachByteDesc(ByteBufProcessor p_forEachByteDesc_1_) {
/*  898 */     return this.buf.forEachByteDesc(p_forEachByteDesc_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int forEachByteDesc(int p_forEachByteDesc_1_, int p_forEachByteDesc_2_, ByteBufProcessor p_forEachByteDesc_3_) {
/*  903 */     return this.buf.forEachByteDesc(p_forEachByteDesc_1_, p_forEachByteDesc_2_, p_forEachByteDesc_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf copy() {
/*  908 */     return this.buf.copy();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf copy(int p_copy_1_, int p_copy_2_) {
/*  913 */     return this.buf.copy(p_copy_1_, p_copy_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf slice() {
/*  918 */     return this.buf.slice();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf slice(int p_slice_1_, int p_slice_2_) {
/*  923 */     return this.buf.slice(p_slice_1_, p_slice_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf duplicate() {
/*  928 */     return this.buf.duplicate();
/*      */   }
/*      */ 
/*      */   
/*      */   public int nioBufferCount() {
/*  933 */     return this.buf.nioBufferCount();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuffer nioBuffer() {
/*  938 */     return this.buf.nioBuffer();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuffer nioBuffer(int p_nioBuffer_1_, int p_nioBuffer_2_) {
/*  943 */     return this.buf.nioBuffer(p_nioBuffer_1_, p_nioBuffer_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuffer internalNioBuffer(int p_internalNioBuffer_1_, int p_internalNioBuffer_2_) {
/*  948 */     return this.buf.internalNioBuffer(p_internalNioBuffer_1_, p_internalNioBuffer_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuffer[] nioBuffers() {
/*  953 */     return this.buf.nioBuffers();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuffer[] nioBuffers(int p_nioBuffers_1_, int p_nioBuffers_2_) {
/*  958 */     return this.buf.nioBuffers(p_nioBuffers_1_, p_nioBuffers_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasArray() {
/*  963 */     return this.buf.hasArray();
/*      */   }
/*      */ 
/*      */   
/*      */   public byte[] array() {
/*  968 */     return this.buf.array();
/*      */   }
/*      */ 
/*      */   
/*      */   public int arrayOffset() {
/*  973 */     return this.buf.arrayOffset();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasMemoryAddress() {
/*  978 */     return this.buf.hasMemoryAddress();
/*      */   }
/*      */ 
/*      */   
/*      */   public long memoryAddress() {
/*  983 */     return this.buf.memoryAddress();
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString(Charset p_toString_1_) {
/*  988 */     return this.buf.toString(p_toString_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString(int p_toString_1_, int p_toString_2_, Charset p_toString_3_) {
/*  993 */     return this.buf.toString(p_toString_1_, p_toString_2_, p_toString_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  998 */     return this.buf.hashCode();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object p_equals_1_) {
/* 1003 */     return this.buf.equals(p_equals_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int compareTo(ByteBuf p_compareTo_1_) {
/* 1008 */     return this.buf.compareTo(p_compareTo_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1013 */     return this.buf.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf retain(int p_retain_1_) {
/* 1018 */     return this.buf.retain(p_retain_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public ByteBuf retain() {
/* 1023 */     return this.buf.retain();
/*      */   }
/*      */ 
/*      */   
/*      */   public int refCnt() {
/* 1028 */     return this.buf.refCnt();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean release() {
/* 1033 */     return this.buf.release();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean release(int p_release_1_) {
/* 1038 */     return this.buf.release(p_release_1_);
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\PacketBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */