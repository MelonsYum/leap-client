/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ 
/*     */ public class GuiKeyBindingList
/*     */   extends GuiListExtended {
/*     */   private final GuiControls field_148191_k;
/*     */   private final Minecraft mc;
/*     */   private final GuiListExtended.IGuiListEntry[] listEntries;
/*  16 */   private int maxListLabelWidth = 0;
/*     */   
/*     */   private static final String __OBFID = "CL_00000732";
/*     */   
/*     */   public GuiKeyBindingList(GuiControls p_i45031_1_, Minecraft mcIn) {
/*  21 */     super(mcIn, GuiControls.width, GuiControls.height, 63, GuiControls.height - 32, 20);
/*  22 */     this.field_148191_k = p_i45031_1_;
/*  23 */     this.mc = mcIn;
/*  24 */     KeyBinding[] var3 = (KeyBinding[])ArrayUtils.clone((Object[])mcIn.gameSettings.keyBindings);
/*  25 */     this.listEntries = new GuiListExtended.IGuiListEntry[var3.length + KeyBinding.getKeybinds().size()];
/*  26 */     Arrays.sort((Object[])var3);
/*  27 */     int var4 = 0;
/*  28 */     String var5 = null;
/*  29 */     KeyBinding[] var6 = var3;
/*  30 */     int var7 = var3.length;
/*     */     
/*  32 */     for (int var8 = 0; var8 < var7; var8++) {
/*     */       
/*  34 */       KeyBinding var9 = var6[var8];
/*  35 */       String var10 = var9.getKeyCategory();
/*     */       
/*  37 */       if (!var10.equals(var5)) {
/*     */         
/*  39 */         var5 = var10;
/*  40 */         this.listEntries[var4++] = new CategoryEntry(var10);
/*     */       } 
/*     */       
/*  43 */       int var11 = mcIn.fontRendererObj.getStringWidth(I18n.format(var9.getKeyDescription(), new Object[0]));
/*     */       
/*  45 */       if (var11 > this.maxListLabelWidth)
/*     */       {
/*  47 */         this.maxListLabelWidth = var11;
/*     */       }
/*     */       
/*  50 */       this.listEntries[var4++] = new KeyEntry(var9, null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/*  56 */     return this.listEntries.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiListExtended.IGuiListEntry getListEntry(int p_148180_1_) {
/*  64 */     return this.listEntries[p_148180_1_];
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/*  69 */     return super.getScrollBarX() + 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getListWidth() {
/*  77 */     return super.getListWidth() + 32;
/*     */   }
/*     */   
/*     */   public class CategoryEntry
/*     */     implements GuiListExtended.IGuiListEntry
/*     */   {
/*     */     private final String labelText;
/*     */     private final int labelWidth;
/*     */     private static final String __OBFID = "CL_00000734";
/*     */     
/*     */     public CategoryEntry(String p_i45028_2_) {
/*  88 */       this.labelText = I18n.format(p_i45028_2_, new Object[0]);
/*  89 */       this.labelWidth = GuiKeyBindingList.this.mc.fontRendererObj.getStringWidth(this.labelText);
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
/*  94 */       GuiKeyBindingList.this.mc.fontRendererObj.drawString(this.labelText, (GuiScreen.width / 2 - this.labelWidth / 2), (y + slotHeight - GuiKeyBindingList.this.mc.fontRendererObj.FONT_HEIGHT - 1), 16777215);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean mousePressed(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
/*  99 */       return false;
/*     */     }
/*     */     
/*     */     public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}
/*     */     
/*     */     public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
/*     */   }
/*     */   
/*     */   public class KeyEntry
/*     */     implements GuiListExtended.IGuiListEntry
/*     */   {
/*     */     private final KeyBinding field_148282_b;
/*     */     private final String field_148283_c;
/*     */     private final GuiButton btnChangeKeyBinding;
/*     */     private final GuiButton btnReset;
/*     */     private static final String __OBFID = "CL_00000735";
/*     */     
/*     */     private KeyEntry(KeyBinding p_i45029_2_) {
/* 117 */       this.field_148282_b = p_i45029_2_;
/* 118 */       this.field_148283_c = I18n.format(p_i45029_2_.getKeyDescription(), new Object[0]);
/* 119 */       this.btnChangeKeyBinding = new GuiButton(0, 0, 0, 75, 18, I18n.format(p_i45029_2_.getKeyDescription(), new Object[0]));
/* 120 */       this.btnReset = new GuiButton(0, 0, 0, 50, 18, I18n.format("controls.reset", new Object[0]));
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
/* 125 */       boolean var9 = (GuiKeyBindingList.this.field_148191_k.buttonId == this.field_148282_b);
/* 126 */       GuiKeyBindingList.this.mc.fontRendererObj.drawString(this.field_148283_c, (x + 90 - GuiKeyBindingList.this.maxListLabelWidth), (y + slotHeight / 2 - GuiKeyBindingList.this.mc.fontRendererObj.FONT_HEIGHT / 2), 16777215);
/* 127 */       this.btnReset.xPosition = x + 190;
/* 128 */       this.btnReset.yPosition = y;
/* 129 */       this.btnReset.enabled = (this.field_148282_b.getKeyCode() != this.field_148282_b.getKeyCodeDefault());
/* 130 */       this.btnReset.drawButton(GuiKeyBindingList.this.mc, mouseX, mouseY);
/* 131 */       this.btnChangeKeyBinding.xPosition = x + 105;
/* 132 */       this.btnChangeKeyBinding.yPosition = y;
/* 133 */       this.btnChangeKeyBinding.displayString = GameSettings.getKeyDisplayString(this.field_148282_b.getKeyCode());
/* 134 */       boolean var10 = false;
/*     */       
/* 136 */       if (this.field_148282_b.getKeyCode() != 0) {
/*     */         
/* 138 */         KeyBinding[] var11 = GuiKeyBindingList.this.mc.gameSettings.keyBindings;
/* 139 */         int var12 = var11.length;
/*     */         
/* 141 */         for (int var13 = 0; var13 < var12; var13++) {
/*     */           
/* 143 */           KeyBinding var14 = var11[var13];
/*     */           
/* 145 */           if (var14 != this.field_148282_b && var14.getKeyCode() == this.field_148282_b.getKeyCode()) {
/*     */             
/* 147 */             var10 = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 153 */       if (var9) {
/*     */         
/* 155 */         this.btnChangeKeyBinding.displayString = EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + this.btnChangeKeyBinding.displayString + EnumChatFormatting.WHITE + " <";
/*     */       }
/* 157 */       else if (var10) {
/*     */         
/* 159 */         this.btnChangeKeyBinding.displayString = EnumChatFormatting.RED + this.btnChangeKeyBinding.displayString;
/*     */       } 
/*     */       
/* 162 */       this.btnChangeKeyBinding.drawButton(GuiKeyBindingList.this.mc, mouseX, mouseY);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean mousePressed(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
/* 167 */       if (this.btnChangeKeyBinding.mousePressed(GuiKeyBindingList.this.mc, p_148278_2_, p_148278_3_)) {
/*     */         
/* 169 */         GuiKeyBindingList.this.field_148191_k.buttonId = this.field_148282_b;
/* 170 */         return true;
/*     */       } 
/* 172 */       if (this.btnReset.mousePressed(GuiKeyBindingList.this.mc, p_148278_2_, p_148278_3_)) {
/*     */         
/* 174 */         GuiKeyBindingList.this.mc.gameSettings.setOptionKeyBinding(this.field_148282_b, this.field_148282_b.getKeyCodeDefault());
/* 175 */         KeyBinding.resetKeyBindingArrayAndHash();
/* 176 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 180 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
/* 186 */       this.btnChangeKeyBinding.mouseReleased(x, y);
/* 187 */       this.btnReset.mouseReleased(x, y);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
/*     */     
/*     */     KeyEntry(KeyBinding p_i45030_2_, Object p_i45030_3_) {
/* 194 */       this(p_i45030_2_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiKeyBindingList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */