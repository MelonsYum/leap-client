/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ 
/*     */ public class TileEntityBanner extends TileEntity {
/*     */   private int baseColor;
/*     */   private NBTTagList field_175118_f;
/*     */   private boolean field_175119_g;
/*     */   private List field_175122_h;
/*     */   private List field_175123_i;
/*     */   private String field_175121_j;
/*     */   private static final String __OBFID = "CL_00002044";
/*     */   
/*     */   public void setItemValues(ItemStack p_175112_1_) {
/*  27 */     this.field_175118_f = null;
/*     */     
/*  29 */     if (p_175112_1_.hasTagCompound() && p_175112_1_.getTagCompound().hasKey("BlockEntityTag", 10)) {
/*     */       
/*  31 */       NBTTagCompound var2 = p_175112_1_.getTagCompound().getCompoundTag("BlockEntityTag");
/*     */       
/*  33 */       if (var2.hasKey("Patterns"))
/*     */       {
/*  35 */         this.field_175118_f = (NBTTagList)var2.getTagList("Patterns", 10).copy();
/*     */       }
/*     */       
/*  38 */       if (var2.hasKey("Base", 99))
/*     */       {
/*  40 */         this.baseColor = var2.getInteger("Base");
/*     */       }
/*     */       else
/*     */       {
/*  44 */         this.baseColor = p_175112_1_.getMetadata() & 0xF;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  49 */       this.baseColor = p_175112_1_.getMetadata() & 0xF;
/*     */     } 
/*     */     
/*  52 */     this.field_175122_h = null;
/*  53 */     this.field_175123_i = null;
/*  54 */     this.field_175121_j = "";
/*  55 */     this.field_175119_g = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound compound) {
/*  60 */     super.writeToNBT(compound);
/*  61 */     compound.setInteger("Base", this.baseColor);
/*     */     
/*  63 */     if (this.field_175118_f != null)
/*     */     {
/*  65 */       compound.setTag("Patterns", (NBTBase)this.field_175118_f);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound compound) {
/*  71 */     super.readFromNBT(compound);
/*  72 */     this.baseColor = compound.getInteger("Base");
/*  73 */     this.field_175118_f = compound.getTagList("Patterns", 10);
/*  74 */     this.field_175122_h = null;
/*  75 */     this.field_175123_i = null;
/*  76 */     this.field_175121_j = null;
/*  77 */     this.field_175119_g = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet getDescriptionPacket() {
/*  85 */     NBTTagCompound var1 = new NBTTagCompound();
/*  86 */     writeToNBT(var1);
/*  87 */     return (Packet)new S35PacketUpdateTileEntity(this.pos, 6, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBaseColor() {
/*  92 */     return this.baseColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getBaseColor(ItemStack stack) {
/*  97 */     NBTTagCompound var1 = stack.getSubCompound("BlockEntityTag", false);
/*  98 */     return (var1 != null && var1.hasKey("Base")) ? var1.getInteger("Base") : stack.getMetadata();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_175113_c(ItemStack p_175113_0_) {
/* 103 */     NBTTagCompound var1 = p_175113_0_.getSubCompound("BlockEntityTag", false);
/* 104 */     return (var1 != null && var1.hasKey("Patterns")) ? var1.getTagList("Patterns", 10).tagCount() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_175114_c() {
/* 109 */     func_175109_g();
/* 110 */     return this.field_175122_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_175110_d() {
/* 115 */     func_175109_g();
/* 116 */     return this.field_175123_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_175116_e() {
/* 121 */     func_175109_g();
/* 122 */     return this.field_175121_j;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175109_g() {
/* 127 */     if (this.field_175122_h == null || this.field_175123_i == null || this.field_175121_j == null)
/*     */     {
/* 129 */       if (!this.field_175119_g) {
/*     */         
/* 131 */         this.field_175121_j = "";
/*     */       }
/*     */       else {
/*     */         
/* 135 */         this.field_175122_h = Lists.newArrayList();
/* 136 */         this.field_175123_i = Lists.newArrayList();
/* 137 */         this.field_175122_h.add(EnumBannerPattern.BASE);
/* 138 */         this.field_175123_i.add(EnumDyeColor.func_176766_a(this.baseColor));
/* 139 */         this.field_175121_j = "b" + this.baseColor;
/*     */         
/* 141 */         if (this.field_175118_f != null)
/*     */         {
/* 143 */           for (int var1 = 0; var1 < this.field_175118_f.tagCount(); var1++) {
/*     */             
/* 145 */             NBTTagCompound var2 = this.field_175118_f.getCompoundTagAt(var1);
/* 146 */             EnumBannerPattern var3 = EnumBannerPattern.func_177268_a(var2.getString("Pattern"));
/*     */             
/* 148 */             if (var3 != null) {
/*     */               
/* 150 */               this.field_175122_h.add(var3);
/* 151 */               int var4 = var2.getInteger("Color");
/* 152 */               this.field_175123_i.add(EnumDyeColor.func_176766_a(var4));
/* 153 */               this.field_175121_j = String.valueOf(this.field_175121_j) + var3.func_177273_b() + var4;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_175117_e(ItemStack p_175117_0_) {
/* 163 */     NBTTagCompound var1 = p_175117_0_.getSubCompound("BlockEntityTag", false);
/*     */     
/* 165 */     if (var1 != null && var1.hasKey("Patterns", 9)) {
/*     */       
/* 167 */       NBTTagList var2 = var1.getTagList("Patterns", 10);
/*     */       
/* 169 */       if (var2.tagCount() > 0) {
/*     */         
/* 171 */         var2.removeTag(var2.tagCount() - 1);
/*     */         
/* 173 */         if (var2.hasNoTags()) {
/*     */           
/* 175 */           p_175117_0_.getTagCompound().removeTag("BlockEntityTag");
/*     */           
/* 177 */           if (p_175117_0_.getTagCompound().hasNoTags())
/*     */           {
/* 179 */             p_175117_0_.setTagCompound(null);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum EnumBannerPattern
/*     */   {
/* 188 */     BASE("BASE", 0, "base", "b"),
/* 189 */     SQUARE_BOTTOM_LEFT("SQUARE_BOTTOM_LEFT", 1, "square_bottom_left", "bl", "   ", "   ", "#  "),
/* 190 */     SQUARE_BOTTOM_RIGHT("SQUARE_BOTTOM_RIGHT", 2, "square_bottom_right", "br", "   ", "   ", "  #"),
/* 191 */     SQUARE_TOP_LEFT("SQUARE_TOP_LEFT", 3, "square_top_left", "tl", "#  ", "   ", "   "),
/* 192 */     SQUARE_TOP_RIGHT("SQUARE_TOP_RIGHT", 4, "square_top_right", "tr", "  #", "   ", "   "),
/* 193 */     STRIPE_BOTTOM("STRIPE_BOTTOM", 5, "stripe_bottom", "bs", "   ", "   ", "###"),
/* 194 */     STRIPE_TOP("STRIPE_TOP", 6, "stripe_top", "ts", "###", "   ", "   "),
/* 195 */     STRIPE_LEFT("STRIPE_LEFT", 7, "stripe_left", "ls", "#  ", "#  ", "#  "),
/* 196 */     STRIPE_RIGHT("STRIPE_RIGHT", 8, "stripe_right", "rs", "  #", "  #", "  #"),
/* 197 */     STRIPE_CENTER("STRIPE_CENTER", 9, "stripe_center", "cs", " # ", " # ", " # "),
/* 198 */     STRIPE_MIDDLE("STRIPE_MIDDLE", 10, "stripe_middle", "ms", "   ", "###", "   "),
/* 199 */     STRIPE_DOWNRIGHT("STRIPE_DOWNRIGHT", 11, "stripe_downright", "drs", "#  ", " # ", "  #"),
/* 200 */     STRIPE_DOWNLEFT("STRIPE_DOWNLEFT", 12, "stripe_downleft", "dls", "  #", " # ", "#  "),
/* 201 */     STRIPE_SMALL("STRIPE_SMALL", 13, "small_stripes", "ss", "# #", "# #", "   "),
/* 202 */     CROSS("CROSS", 14, "cross", "cr", "# #", " # ", "# #"),
/* 203 */     STRAIGHT_CROSS("STRAIGHT_CROSS", 15, "straight_cross", "sc", " # ", "###", " # "),
/* 204 */     TRIANGLE_BOTTOM("TRIANGLE_BOTTOM", 16, "triangle_bottom", "bt", "   ", " # ", "# #"),
/* 205 */     TRIANGLE_TOP("TRIANGLE_TOP", 17, "triangle_top", "tt", "# #", " # ", "   "),
/* 206 */     TRIANGLES_BOTTOM("TRIANGLES_BOTTOM", 18, "triangles_bottom", "bts", "   ", "# #", " # "),
/* 207 */     TRIANGLES_TOP("TRIANGLES_TOP", 19, "triangles_top", "tts", " # ", "# #", "   "),
/* 208 */     DIAGONAL_LEFT("DIAGONAL_LEFT", 20, "diagonal_left", "ld", "## ", "#  ", "   "),
/* 209 */     DIAGONAL_RIGHT("DIAGONAL_RIGHT", 21, "diagonal_up_right", "rd", "   ", "  #", " ##"),
/* 210 */     DIAGONAL_LEFT_MIRROR("DIAGONAL_LEFT_MIRROR", 22, "diagonal_up_left", "lud", "   ", "#  ", "## "),
/* 211 */     DIAGONAL_RIGHT_MIRROR("DIAGONAL_RIGHT_MIRROR", 23, "diagonal_right", "rud", " ##", "  #", "   "),
/* 212 */     CIRCLE_MIDDLE("CIRCLE_MIDDLE", 24, "circle", "mc", "   ", " # ", "   "),
/* 213 */     RHOMBUS_MIDDLE("RHOMBUS_MIDDLE", 25, "rhombus", "mr", " # ", "# #", " # "),
/* 214 */     HALF_VERTICAL("HALF_VERTICAL", 26, "half_vertical", "vh", "## ", "## ", "## "),
/* 215 */     HALF_HORIZONTAL("HALF_HORIZONTAL", 27, "half_horizontal", "hh", "###", "###", "   "),
/* 216 */     HALF_VERTICAL_MIRROR("HALF_VERTICAL_MIRROR", 28, "half_vertical_right", "vhr", " ##", " ##", " ##"),
/* 217 */     HALF_HORIZONTAL_MIRROR("HALF_HORIZONTAL_MIRROR", 29, "half_horizontal_bottom", "hhb", "   ", "###", "###"),
/* 218 */     BORDER("BORDER", 30, "border", "bo", "###", "# #", "###"),
/* 219 */     CURLY_BORDER("CURLY_BORDER", 31, "curly_border", "cbo", (String)new ItemStack(Blocks.vine)),
/* 220 */     CREEPER("CREEPER", 32, "creeper", "cre", (String)new ItemStack(Items.skull, 1, 4)),
/* 221 */     GRADIENT("GRADIENT", 33, "gradient", "gra", "# #", " # ", " # "),
/* 222 */     GRADIENT_UP("GRADIENT_UP", 34, "gradient_up", "gru", " # ", " # ", "# #"),
/* 223 */     BRICKS("BRICKS", 35, "bricks", "bri", (String)new ItemStack(Blocks.brick_block)),
/* 224 */     SKULL("SKULL", 36, "skull", "sku", (String)new ItemStack(Items.skull, 1, 1)),
/* 225 */     FLOWER("FLOWER", 37, "flower", "flo", (String)new ItemStack((Block)Blocks.red_flower, 1, BlockFlower.EnumFlowerType.OXEYE_DAISY.func_176968_b())),
/* 226 */     MOJANG("MOJANG", 38, "mojang", "moj", (String)new ItemStack(Items.golden_apple, 1, 1));
/*     */     
/*     */     private String field_177284_N;
/*     */     private String field_177285_O;
/*     */     private String[] field_177291_P;
/*     */     private ItemStack field_177290_Q;
/* 232 */     private static final EnumBannerPattern[] $VALUES = new EnumBannerPattern[] { BASE, SQUARE_BOTTOM_LEFT, SQUARE_BOTTOM_RIGHT, SQUARE_TOP_LEFT, SQUARE_TOP_RIGHT, STRIPE_BOTTOM, STRIPE_TOP, STRIPE_LEFT, STRIPE_RIGHT, STRIPE_CENTER, STRIPE_MIDDLE, STRIPE_DOWNRIGHT, STRIPE_DOWNLEFT, STRIPE_SMALL, CROSS, STRAIGHT_CROSS, TRIANGLE_BOTTOM, TRIANGLE_TOP, TRIANGLES_BOTTOM, TRIANGLES_TOP, DIAGONAL_LEFT, DIAGONAL_RIGHT, DIAGONAL_LEFT_MIRROR, DIAGONAL_RIGHT_MIRROR, CIRCLE_MIDDLE, RHOMBUS_MIDDLE, HALF_VERTICAL, HALF_HORIZONTAL, HALF_VERTICAL_MIRROR, HALF_HORIZONTAL_MIRROR, BORDER, CURLY_BORDER, CREEPER, GRADIENT, GRADIENT_UP, BRICKS, SKULL, FLOWER, MOJANG };
/*     */     
/*     */     private static final String __OBFID = "CL_00002043";
/*     */     
/*     */     EnumBannerPattern(String p_i45670_1_, int p_i45670_2_, String p_i45670_3_, String p_i45670_4_) {
/* 237 */       this.field_177291_P = new String[3];
/* 238 */       this.field_177284_N = p_i45670_3_;
/* 239 */       this.field_177285_O = p_i45670_4_;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     EnumBannerPattern(String p_i45671_1_, int p_i45671_2_, String p_i45671_3_, String p_i45671_4_, ItemStack p_i45671_5_) {
/* 245 */       this.field_177290_Q = p_i45671_5_;
/*     */     }
/*     */     static {
/*     */     
/*     */     }
/*     */     EnumBannerPattern(String p_i45672_1_, int p_i45672_2_, String p_i45672_3_, String p_i45672_4_, String p_i45672_5_, String p_i45672_6_, String p_i45672_7_) {
/* 251 */       this.field_177291_P[0] = p_i45672_5_;
/* 252 */       this.field_177291_P[1] = p_i45672_6_;
/* 253 */       this.field_177291_P[2] = p_i45672_7_;
/*     */     }
/*     */ 
/*     */     
/*     */     public String func_177271_a() {
/* 258 */       return this.field_177284_N;
/*     */     }
/*     */ 
/*     */     
/*     */     public String func_177273_b() {
/* 263 */       return this.field_177285_O;
/*     */     }
/*     */ 
/*     */     
/*     */     public String[] func_177267_c() {
/* 268 */       return this.field_177291_P;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_177270_d() {
/* 273 */       return !(this.field_177290_Q == null && this.field_177291_P[0] == null);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_177269_e() {
/* 278 */       return (this.field_177290_Q != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack func_177272_f() {
/* 283 */       return this.field_177290_Q;
/*     */     }
/*     */ 
/*     */     
/*     */     public static EnumBannerPattern func_177268_a(String p_177268_0_) {
/* 288 */       EnumBannerPattern[] var1 = values();
/* 289 */       int var2 = var1.length;
/*     */       
/* 291 */       for (int var3 = 0; var3 < var2; var3++) {
/*     */         
/* 293 */         EnumBannerPattern var4 = var1[var3];
/*     */         
/* 295 */         if (var4.field_177285_O.equals(p_177268_0_))
/*     */         {
/* 297 */           return var4;
/*     */         }
/*     */       } 
/*     */       
/* 301 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\TileEntityBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */