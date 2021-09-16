/*     */ package net.minecraft.item;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S2FPacketSetSlot;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.ChatComponentProcessor;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.util.StringUtils;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemEditableBook extends Item {
/*     */   public ItemEditableBook() {
/*  26 */     setMaxStackSize(1);
/*     */   }
/*     */   private static final String __OBFID = "CL_00000077";
/*     */   
/*     */   public static boolean validBookTagContents(NBTTagCompound p_77828_0_) {
/*  31 */     if (!ItemWritableBook.validBookPageTagContents(p_77828_0_))
/*     */     {
/*  33 */       return false;
/*     */     }
/*  35 */     if (!p_77828_0_.hasKey("title", 8))
/*     */     {
/*  37 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  41 */     String var1 = p_77828_0_.getString("title");
/*  42 */     return (var1 != null && var1.length() <= 32) ? p_77828_0_.hasKey("author", 8) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int func_179230_h(ItemStack p_179230_0_) {
/*  48 */     return p_179230_0_.getTagCompound().getInteger("generation");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getItemStackDisplayName(ItemStack stack) {
/*  53 */     if (stack.hasTagCompound()) {
/*     */       
/*  55 */       NBTTagCompound var2 = stack.getTagCompound();
/*  56 */       String var3 = var2.getString("title");
/*     */       
/*  58 */       if (!StringUtils.isNullOrEmpty(var3))
/*     */       {
/*  60 */         return var3;
/*     */       }
/*     */     } 
/*     */     
/*  64 */     return super.getItemStackDisplayName(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
/*  75 */     if (stack.hasTagCompound()) {
/*     */       
/*  77 */       NBTTagCompound var5 = stack.getTagCompound();
/*  78 */       String var6 = var5.getString("author");
/*     */       
/*  80 */       if (!StringUtils.isNullOrEmpty(var6))
/*     */       {
/*  82 */         tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocalFormatted("book.byAuthor", new Object[] { var6 }));
/*     */       }
/*     */       
/*  85 */       tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("book.generation." + var5.getInteger("generation")));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
/*  94 */     if (!worldIn.isRemote)
/*     */     {
/*  96 */       func_179229_a(itemStackIn, playerIn);
/*     */     }
/*     */     
/*  99 */     playerIn.displayGUIBook(itemStackIn);
/* 100 */     playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
/* 101 */     return itemStackIn;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_179229_a(ItemStack p_179229_1_, EntityPlayer p_179229_2_) {
/* 106 */     if (p_179229_1_ != null && p_179229_1_.getTagCompound() != null) {
/*     */       
/* 108 */       NBTTagCompound var3 = p_179229_1_.getTagCompound();
/*     */       
/* 110 */       if (!var3.getBoolean("resolved")) {
/*     */         
/* 112 */         var3.setBoolean("resolved", true);
/*     */         
/* 114 */         if (validBookTagContents(var3)) {
/*     */           
/* 116 */           NBTTagList var4 = var3.getTagList("pages", 8);
/*     */           
/* 118 */           for (int var5 = 0; var5 < var4.tagCount(); var5++) {
/*     */             Object var7;
/* 120 */             String var6 = var4.getStringTagAt(var5);
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 125 */               IChatComponent var11 = IChatComponent.Serializer.jsonToComponent(var6);
/* 126 */               var7 = ChatComponentProcessor.func_179985_a((ICommandSender)p_179229_2_, var11, (Entity)p_179229_2_);
/*     */             }
/* 128 */             catch (Exception var9) {
/*     */               
/* 130 */               var7 = new ChatComponentText(var6);
/*     */             } 
/*     */             
/* 133 */             var4.set(var5, (NBTBase)new NBTTagString(IChatComponent.Serializer.componentToJson((IChatComponent)var7)));
/*     */           } 
/*     */           
/* 136 */           var3.setTag("pages", (NBTBase)var4);
/*     */           
/* 138 */           if (p_179229_2_ instanceof EntityPlayerMP && p_179229_2_.getCurrentEquippedItem() == p_179229_1_) {
/*     */             
/* 140 */             Slot var10 = p_179229_2_.openContainer.getSlotFromInventory((IInventory)p_179229_2_.inventory, p_179229_2_.inventory.currentItem);
/* 141 */             ((EntityPlayerMP)p_179229_2_).playerNetServerHandler.sendPacket((Packet)new S2FPacketSetSlot(0, var10.slotNumber, p_179229_1_));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEffect(ItemStack stack) {
/* 150 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemEditableBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */