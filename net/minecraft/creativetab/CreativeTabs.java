/*     */ package net.minecraft.creativetab;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoublePlant;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentData;
/*     */ import net.minecraft.enchantment.EnumEnchantmentType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public abstract class CreativeTabs {
/*  16 */   public static final CreativeTabs[] creativeTabArray = new CreativeTabs[12];
/*  17 */   public static final CreativeTabs tabBlock = new CreativeTabs(0, "buildingBlocks")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000006";
/*     */       
/*     */       public Item getTabIconItem() {
/*  22 */         return Item.getItemFromBlock(Blocks.brick_block);
/*     */       }
/*     */     };
/*  25 */   public static final CreativeTabs tabDecorations = new CreativeTabs(1, "decorations")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000010";
/*     */       
/*     */       public Item getTabIconItem() {
/*  30 */         return Item.getItemFromBlock((Block)Blocks.double_plant);
/*     */       }
/*     */       
/*     */       public int getIconItemDamage() {
/*  34 */         return BlockDoublePlant.EnumPlantType.PAEONIA.func_176936_a();
/*     */       }
/*     */     };
/*  37 */   public static final CreativeTabs tabRedstone = new CreativeTabs(2, "redstone")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000011";
/*     */       
/*     */       public Item getTabIconItem() {
/*  42 */         return Items.redstone;
/*     */       }
/*     */     };
/*  45 */   public static final CreativeTabs tabTransport = new CreativeTabs(3, "transportation")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000012";
/*     */       
/*     */       public Item getTabIconItem() {
/*  50 */         return Item.getItemFromBlock(Blocks.golden_rail);
/*     */       }
/*     */     };
/*  53 */   public static final CreativeTabs tabMisc = (new CreativeTabs(4, "misc")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000014";
/*     */       
/*     */       public Item getTabIconItem() {
/*  58 */         return Items.lava_bucket;
/*     */       }
/*  60 */     }).setRelevantEnchantmentTypes(new EnumEnchantmentType[] { EnumEnchantmentType.ALL });
/*  61 */   public static final CreativeTabs tabAllSearch = (new CreativeTabs(5, "search")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000015";
/*     */       
/*     */       public Item getTabIconItem() {
/*  66 */         return Items.compass;
/*     */       }
/*  68 */     }).setBackgroundImageName("item_search.png");
/*  69 */   public static final CreativeTabs tabFood = new CreativeTabs(6, "food")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000016";
/*     */       
/*     */       public Item getTabIconItem() {
/*  74 */         return Items.apple;
/*     */       }
/*     */     };
/*  77 */   public static final CreativeTabs tabTools = (new CreativeTabs(7, "tools")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000017";
/*     */       
/*     */       public Item getTabIconItem() {
/*  82 */         return Items.iron_axe;
/*     */       }
/*  84 */     }).setRelevantEnchantmentTypes(new EnumEnchantmentType[] { EnumEnchantmentType.DIGGER, EnumEnchantmentType.FISHING_ROD, EnumEnchantmentType.BREAKABLE });
/*  85 */   public static final CreativeTabs tabCombat = (new CreativeTabs(8, "combat")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000018";
/*     */       
/*     */       public Item getTabIconItem() {
/*  90 */         return Items.golden_sword;
/*     */       }
/*  92 */     }).setRelevantEnchantmentTypes(new EnumEnchantmentType[] { EnumEnchantmentType.ARMOR, EnumEnchantmentType.ARMOR_FEET, EnumEnchantmentType.ARMOR_HEAD, EnumEnchantmentType.ARMOR_LEGS, EnumEnchantmentType.ARMOR_TORSO, EnumEnchantmentType.BOW, EnumEnchantmentType.WEAPON });
/*  93 */   public static final CreativeTabs tabBrewing = new CreativeTabs(9, "brewing")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000007";
/*     */       
/*     */       public Item getTabIconItem() {
/*  98 */         return (Item)Items.potionitem;
/*     */       }
/*     */     };
/* 101 */   public static final CreativeTabs tabMaterials = new CreativeTabs(10, "materials")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000008";
/*     */       
/*     */       public Item getTabIconItem() {
/* 106 */         return Items.stick;
/*     */       }
/*     */     };
/* 109 */   public static final CreativeTabs tabInventory = (new CreativeTabs(11, "inventory")
/*     */     {
/*     */       private static final String __OBFID = "CL_00000009";
/*     */       
/*     */       public Item getTabIconItem() {
/* 114 */         return Item.getItemFromBlock((Block)Blocks.chest);
/*     */       }
/* 116 */     }).setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
/*     */   
/*     */   private final int tabIndex;
/*     */   
/*     */   private final String tabLabel;
/* 121 */   private String theTexture = "items.png";
/*     */   
/*     */   private boolean hasScrollbar = true;
/*     */   
/*     */   private boolean drawTitle = true;
/*     */   
/*     */   private EnumEnchantmentType[] enchantmentTypes;
/*     */   private ItemStack iconItemStack;
/*     */   private static final String __OBFID = "CL_00000005";
/*     */   
/*     */   public CreativeTabs(int index, String label) {
/* 132 */     this.tabIndex = index;
/* 133 */     this.tabLabel = label;
/* 134 */     creativeTabArray[index] = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTabIndex() {
/* 139 */     return this.tabIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTabLabel() {
/* 144 */     return this.tabLabel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTranslatedTabLabel() {
/* 152 */     return "itemGroup." + getTabLabel();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getIconItemStack() {
/* 157 */     if (this.iconItemStack == null)
/*     */     {
/* 159 */       this.iconItemStack = new ItemStack(getTabIconItem(), 1, getIconItemDamage());
/*     */     }
/*     */     
/* 162 */     return this.iconItemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract Item getTabIconItem();
/*     */   
/*     */   public int getIconItemDamage() {
/* 169 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBackgroundImageName() {
/* 174 */     return this.theTexture;
/*     */   }
/*     */ 
/*     */   
/*     */   public CreativeTabs setBackgroundImageName(String texture) {
/* 179 */     this.theTexture = texture;
/* 180 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawInForegroundOfTab() {
/* 185 */     return this.drawTitle;
/*     */   }
/*     */ 
/*     */   
/*     */   public CreativeTabs setNoTitle() {
/* 190 */     this.drawTitle = false;
/* 191 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldHidePlayerInventory() {
/* 196 */     return this.hasScrollbar;
/*     */   }
/*     */ 
/*     */   
/*     */   public CreativeTabs setNoScrollbar() {
/* 201 */     this.hasScrollbar = false;
/* 202 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTabColumn() {
/* 210 */     return this.tabIndex % 6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTabInFirstRow() {
/* 218 */     return (this.tabIndex < 6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumEnchantmentType[] getRelevantEnchantmentTypes() {
/* 226 */     return this.enchantmentTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeTabs setRelevantEnchantmentTypes(EnumEnchantmentType... types) {
/* 234 */     this.enchantmentTypes = types;
/* 235 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasRelevantEnchantmentType(EnumEnchantmentType p_111226_1_) {
/* 240 */     if (this.enchantmentTypes == null)
/*     */     {
/* 242 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 246 */     EnumEnchantmentType[] var2 = this.enchantmentTypes;
/* 247 */     int var3 = var2.length;
/*     */     
/* 249 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 251 */       EnumEnchantmentType var5 = var2[var4];
/*     */       
/* 253 */       if (var5 == p_111226_1_)
/*     */       {
/* 255 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 259 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayAllReleventItems(List p_78018_1_) {
/* 268 */     Iterator<Item> var2 = Item.itemRegistry.iterator();
/*     */     
/* 270 */     while (var2.hasNext()) {
/*     */       
/* 272 */       Item var3 = var2.next();
/*     */       
/* 274 */       if (var3 != null && var3.getCreativeTab() == this)
/*     */       {
/* 276 */         var3.getSubItems(var3, this, p_78018_1_);
/*     */       }
/*     */     } 
/*     */     
/* 280 */     if (getRelevantEnchantmentTypes() != null)
/*     */     {
/* 282 */       addEnchantmentBooksToList(p_78018_1_, getRelevantEnchantmentTypes());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEnchantmentBooksToList(List<ItemStack> p_92116_1_, EnumEnchantmentType... p_92116_2_) {
/* 291 */     Enchantment[] var3 = Enchantment.enchantmentsList;
/* 292 */     int var4 = var3.length;
/*     */     
/* 294 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 296 */       Enchantment var6 = var3[var5];
/*     */       
/* 298 */       if (var6 != null && var6.type != null) {
/*     */         
/* 300 */         boolean var7 = false;
/*     */         
/* 302 */         for (int var8 = 0; var8 < p_92116_2_.length && !var7; var8++) {
/*     */           
/* 304 */           if (var6.type == p_92116_2_[var8])
/*     */           {
/* 306 */             var7 = true;
/*     */           }
/*     */         } 
/*     */         
/* 310 */         if (var7)
/*     */         {
/* 312 */           p_92116_1_.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(var6, var6.getMaxLevel())));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\creativetab\CreativeTabs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */