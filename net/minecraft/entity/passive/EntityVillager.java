/*      */ package net.minecraft.entity.passive;
/*      */ 
/*      */ import com.google.common.base.Predicate;
/*      */ import java.util.Iterator;
/*      */ import java.util.Random;
/*      */ import net.minecraft.enchantment.Enchantment;
/*      */ import net.minecraft.enchantment.EnchantmentData;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.IMerchant;
/*      */ import net.minecraft.entity.INpc;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAIFollowGolem;
/*      */ import net.minecraft.entity.ai.EntityAIHarvestFarmland;
/*      */ import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
/*      */ import net.minecraft.entity.ai.EntityAIMoveIndoors;
/*      */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*      */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*      */ import net.minecraft.entity.ai.EntityAIPlay;
/*      */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*      */ import net.minecraft.entity.ai.EntityAISwimming;
/*      */ import net.minecraft.entity.ai.EntityAITradePlayer;
/*      */ import net.minecraft.entity.ai.EntityAIVillagerInteract;
/*      */ import net.minecraft.entity.ai.EntityAIVillagerMate;
/*      */ import net.minecraft.entity.ai.EntityAIWander;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest2;
/*      */ import net.minecraft.entity.effect.EntityLightningBolt;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.item.EntityXPOrb;
/*      */ import net.minecraft.entity.monster.EntityWitch;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.InventoryBasic;
/*      */ import net.minecraft.item.EnumDyeColor;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.pathfinding.PathNavigateGround;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.stats.StatList;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Tuple;
/*      */ import net.minecraft.village.MerchantRecipe;
/*      */ import net.minecraft.village.MerchantRecipeList;
/*      */ import net.minecraft.village.Village;
/*      */ import net.minecraft.world.DifficultyInstance;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EntityVillager
/*      */   extends EntityAgeable
/*      */   implements INpc, IMerchant
/*      */ {
/*      */   private int randomTickDivider;
/*      */   private boolean isMating;
/*      */   private boolean isPlaying;
/*      */   Village villageObj;
/*      */   private EntityPlayer buyingPlayer;
/*      */   private MerchantRecipeList buyingList;
/*      */   private int timeUntilReset;
/*      */   private boolean needsInitilization;
/*      */   private boolean field_175565_bs;
/*      */   private int wealth;
/*      */   private String lastBuyingPlayer;
/*      */   private int field_175563_bv;
/*      */   private int field_175562_bw;
/*      */   private boolean isLookingForHome;
/*      */   private boolean field_175564_by;
/*      */   private InventoryBasic field_175560_bz;
/*   92 */   private static final ITradeList[][][][] field_175561_bA = new ITradeList[][][][] { { { { new EmeraldForItems(Items.wheat, new PriceInfo(18, 22)), new EmeraldForItems(Items.potato, new PriceInfo(15, 19)), new EmeraldForItems(Items.carrot, new PriceInfo(15, 19)), new ListItemForEmeralds(Items.bread, new PriceInfo(-4, -2)) }, { new EmeraldForItems(Item.getItemFromBlock(Blocks.pumpkin), new PriceInfo(8, 13)), new ListItemForEmeralds(Items.pumpkin_pie, new PriceInfo(-3, -2)) }, { new EmeraldForItems(Item.getItemFromBlock(Blocks.melon_block), new PriceInfo(7, 12)), new ListItemForEmeralds(Items.apple, new PriceInfo(-5, -7)) }, { new ListItemForEmeralds(Items.cookie, new PriceInfo(-6, -10)), new ListItemForEmeralds(Items.cake, new PriceInfo(1, 1)) } }, { { new EmeraldForItems(Items.string, new PriceInfo(15, 20)), new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ItemAndEmeraldToItem(Items.fish, new PriceInfo(6, 6), Items.cooked_fish, new PriceInfo(6, 6)) }, { new ListEnchantedItemForEmeralds((Item)Items.fishing_rod, new PriceInfo(7, 8)) } }, { { new EmeraldForItems(Item.getItemFromBlock(Blocks.wool), new PriceInfo(16, 22)), new ListItemForEmeralds((Item)Items.shears, new PriceInfo(3, 4)) }, { new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 1), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 2), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 3), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 4), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 5), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 6), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 7), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 8), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 9), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 10), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 11), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 12), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 13), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 14), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 15), new PriceInfo(1, 2)) } }, { { new EmeraldForItems(Items.string, new PriceInfo(15, 20)), new ListItemForEmeralds(Items.arrow, new PriceInfo(-12, -8)) }, { new ListItemForEmeralds((Item)Items.bow, new PriceInfo(2, 3)), new ItemAndEmeraldToItem(Item.getItemFromBlock(Blocks.gravel), new PriceInfo(10, 10), Items.flint, new PriceInfo(6, 10)) } } }, { { { new EmeraldForItems(Items.paper, new PriceInfo(24, 36)), new ListEnchantedBookForEmeralds() }, { new EmeraldForItems(Items.book, new PriceInfo(8, 10)), new ListItemForEmeralds(Items.compass, new PriceInfo(10, 12)), new ListItemForEmeralds(Item.getItemFromBlock(Blocks.bookshelf), new PriceInfo(3, 4)) }, { new EmeraldForItems(Items.written_book, new PriceInfo(2, 2)), new ListItemForEmeralds(Items.clock, new PriceInfo(10, 12)), new ListItemForEmeralds(Item.getItemFromBlock(Blocks.glass), new PriceInfo(-5, -3)) }, { new ListEnchantedBookForEmeralds() }, { new ListEnchantedBookForEmeralds() }, { new ListItemForEmeralds(Items.name_tag, new PriceInfo(20, 22)) } } }, { { { new EmeraldForItems(Items.rotten_flesh, new PriceInfo(36, 40)), new EmeraldForItems(Items.gold_ingot, new PriceInfo(8, 10)) }, { new ListItemForEmeralds(Items.redstone, new PriceInfo(-4, -1)), new ListItemForEmeralds(new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeColorDamage()), new PriceInfo(-2, -1)) }, { new ListItemForEmeralds(Items.ender_eye, new PriceInfo(7, 11)), new ListItemForEmeralds(Item.getItemFromBlock(Blocks.glowstone), new PriceInfo(-3, -1)) }, { new ListItemForEmeralds(Items.experience_bottle, new PriceInfo(3, 11)) } } }, { { { new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ListItemForEmeralds((Item)Items.iron_helmet, new PriceInfo(4, 6)) }, { new EmeraldForItems(Items.iron_ingot, new PriceInfo(7, 9)), new ListItemForEmeralds((Item)Items.iron_chestplate, new PriceInfo(10, 14)) }, { new EmeraldForItems(Items.diamond, new PriceInfo(3, 4)), new ListEnchantedItemForEmeralds((Item)Items.diamond_chestplate, new PriceInfo(16, 19)) }, { new ListItemForEmeralds((Item)Items.chainmail_boots, new PriceInfo(5, 7)), new ListItemForEmeralds((Item)Items.chainmail_leggings, new PriceInfo(9, 11)), new ListItemForEmeralds((Item)Items.chainmail_helmet, new PriceInfo(5, 7)), new ListItemForEmeralds((Item)Items.chainmail_chestplate, new PriceInfo(11, 15)) } }, { { new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ListItemForEmeralds(Items.iron_axe, new PriceInfo(6, 8)) }, { new EmeraldForItems(Items.iron_ingot, new PriceInfo(7, 9)), new ListEnchantedItemForEmeralds(Items.iron_sword, new PriceInfo(9, 10)) }, { new EmeraldForItems(Items.diamond, new PriceInfo(3, 4)), new ListEnchantedItemForEmeralds(Items.diamond_sword, new PriceInfo(12, 15)), new ListEnchantedItemForEmeralds(Items.diamond_axe, new PriceInfo(9, 12)) } }, { { new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ListEnchantedItemForEmeralds(Items.iron_shovel, new PriceInfo(5, 7)) }, { new EmeraldForItems(Items.iron_ingot, new PriceInfo(7, 9)), new ListEnchantedItemForEmeralds(Items.iron_pickaxe, new PriceInfo(9, 11)) }, { new EmeraldForItems(Items.diamond, new PriceInfo(3, 4)), new ListEnchantedItemForEmeralds(Items.diamond_pickaxe, new PriceInfo(12, 15)) } } }, { { { new EmeraldForItems(Items.porkchop, new PriceInfo(14, 18)), new EmeraldForItems(Items.chicken, new PriceInfo(14, 18)) }, { new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ListItemForEmeralds(Items.cooked_porkchop, new PriceInfo(-7, -5)), new ListItemForEmeralds(Items.cooked_chicken, new PriceInfo(-8, -6)) } }, { { new EmeraldForItems(Items.leather, new PriceInfo(9, 12)), new ListItemForEmeralds((Item)Items.leather_leggings, new PriceInfo(2, 4)) }, { new ListEnchantedItemForEmeralds((Item)Items.leather_chestplate, new PriceInfo(7, 12)) }, { new ListItemForEmeralds(Items.saddle, new PriceInfo(8, 10)) } } } };
/*      */   
/*      */   private static final String __OBFID = "CL_00001707";
/*      */   
/*      */   public EntityVillager(World worldIn) {
/*   97 */     this(worldIn, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityVillager(World worldIn, int p_i1748_2_) {
/*  102 */     super(worldIn);
/*  103 */     this.field_175560_bz = new InventoryBasic("Items", false, 8);
/*  104 */     setProfession(p_i1748_2_);
/*  105 */     setSize(0.6F, 1.8F);
/*  106 */     ((PathNavigateGround)getNavigator()).func_179688_b(true);
/*  107 */     ((PathNavigateGround)getNavigator()).func_179690_a(true);
/*  108 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  109 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, new Predicate()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002195";
/*      */             
/*      */             public boolean func_179530_a(Entity p_179530_1_) {
/*  114 */               return p_179530_1_ instanceof net.minecraft.entity.monster.EntityZombie;
/*      */             }
/*      */             
/*      */             public boolean apply(Object p_apply_1_) {
/*  118 */               return func_179530_a((Entity)p_apply_1_);
/*      */             }
/*  120 */           },  8.0F, 0.6D, 0.6D));
/*  121 */     this.tasks.addTask(1, (EntityAIBase)new EntityAITradePlayer(this));
/*  122 */     this.tasks.addTask(1, (EntityAIBase)new EntityAILookAtTradePlayer(this));
/*  123 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIMoveIndoors((EntityCreature)this));
/*  124 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
/*  125 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
/*  126 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.6D));
/*  127 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIVillagerMate(this));
/*  128 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIFollowGolem(this));
/*  129 */     this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0F, 1.0F));
/*  130 */     this.tasks.addTask(9, (EntityAIBase)new EntityAIVillagerInteract(this));
/*  131 */     this.tasks.addTask(9, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.6D));
/*  132 */     this.tasks.addTask(10, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0F));
/*  133 */     setCanPickUpLoot(true);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175552_ct() {
/*  138 */     if (!this.field_175564_by) {
/*      */       
/*  140 */       this.field_175564_by = true;
/*      */       
/*  142 */       if (isChild()) {
/*      */         
/*  144 */         this.tasks.addTask(8, (EntityAIBase)new EntityAIPlay(this, 0.32D));
/*      */       }
/*  146 */       else if (getProfession() == 0) {
/*      */         
/*  148 */         this.tasks.addTask(6, (EntityAIBase)new EntityAIHarvestFarmland(this, 0.6D));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175500_n() {
/*  155 */     if (getProfession() == 0)
/*      */     {
/*  157 */       this.tasks.addTask(8, (EntityAIBase)new EntityAIHarvestFarmland(this, 0.6D));
/*      */     }
/*      */     
/*  160 */     super.func_175500_n();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*  165 */     super.applyEntityAttributes();
/*  166 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateAITasks() {
/*  171 */     if (--this.randomTickDivider <= 0) {
/*      */       
/*  173 */       BlockPos var1 = new BlockPos((Entity)this);
/*  174 */       this.worldObj.getVillageCollection().func_176060_a(var1);
/*  175 */       this.randomTickDivider = 70 + this.rand.nextInt(50);
/*  176 */       this.villageObj = this.worldObj.getVillageCollection().func_176056_a(var1, 32);
/*      */       
/*  178 */       if (this.villageObj == null) {
/*      */         
/*  180 */         detachHome();
/*      */       }
/*      */       else {
/*      */         
/*  184 */         BlockPos var2 = this.villageObj.func_180608_a();
/*  185 */         func_175449_a(var2, (int)(this.villageObj.getVillageRadius() * 1.0F));
/*      */         
/*  187 */         if (this.isLookingForHome) {
/*      */           
/*  189 */           this.isLookingForHome = false;
/*  190 */           this.villageObj.setDefaultPlayerReputation(5);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  195 */     if (!isTrading() && this.timeUntilReset > 0) {
/*      */       
/*  197 */       this.timeUntilReset--;
/*      */       
/*  199 */       if (this.timeUntilReset <= 0) {
/*      */         
/*  201 */         if (this.needsInitilization) {
/*      */           
/*  203 */           Iterator<MerchantRecipe> var3 = this.buyingList.iterator();
/*      */           
/*  205 */           while (var3.hasNext()) {
/*      */             
/*  207 */             MerchantRecipe var4 = var3.next();
/*      */             
/*  209 */             if (var4.isRecipeDisabled())
/*      */             {
/*  211 */               var4.func_82783_a(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
/*      */             }
/*      */           } 
/*      */           
/*  215 */           func_175554_cu();
/*  216 */           this.needsInitilization = false;
/*      */           
/*  218 */           if (this.villageObj != null && this.lastBuyingPlayer != null) {
/*      */             
/*  220 */             this.worldObj.setEntityState((Entity)this, (byte)14);
/*  221 */             this.villageObj.setReputationForPlayer(this.lastBuyingPlayer, 1);
/*      */           } 
/*      */         } 
/*      */         
/*  225 */         addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
/*      */       } 
/*      */     } 
/*      */     
/*  229 */     super.updateAITasks();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean interact(EntityPlayer p_70085_1_) {
/*  237 */     ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
/*  238 */     boolean var3 = (var2 != null && var2.getItem() == Items.spawn_egg);
/*      */     
/*  240 */     if (!var3 && isEntityAlive() && !isTrading() && !isChild()) {
/*      */       
/*  242 */       if (!this.worldObj.isRemote && (this.buyingList == null || this.buyingList.size() > 0)) {
/*      */         
/*  244 */         setCustomer(p_70085_1_);
/*  245 */         p_70085_1_.displayVillagerTradeGui(this);
/*      */       } 
/*      */       
/*  248 */       p_70085_1_.triggerAchievement(StatList.timesTalkedToVillagerStat);
/*  249 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  253 */     return super.interact(p_70085_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  259 */     super.entityInit();
/*  260 */     this.dataWatcher.addObject(16, Integer.valueOf(0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  268 */     super.writeEntityToNBT(tagCompound);
/*  269 */     tagCompound.setInteger("Profession", getProfession());
/*  270 */     tagCompound.setInteger("Riches", this.wealth);
/*  271 */     tagCompound.setInteger("Career", this.field_175563_bv);
/*  272 */     tagCompound.setInteger("CareerLevel", this.field_175562_bw);
/*  273 */     tagCompound.setBoolean("Willing", this.field_175565_bs);
/*      */     
/*  275 */     if (this.buyingList != null)
/*      */     {
/*  277 */       tagCompound.setTag("Offers", (NBTBase)this.buyingList.getRecipiesAsTags());
/*      */     }
/*      */     
/*  280 */     NBTTagList var2 = new NBTTagList();
/*      */     
/*  282 */     for (int var3 = 0; var3 < this.field_175560_bz.getSizeInventory(); var3++) {
/*      */       
/*  284 */       ItemStack var4 = this.field_175560_bz.getStackInSlot(var3);
/*      */       
/*  286 */       if (var4 != null)
/*      */       {
/*  288 */         var2.appendTag((NBTBase)var4.writeToNBT(new NBTTagCompound()));
/*      */       }
/*      */     } 
/*      */     
/*  292 */     tagCompound.setTag("Inventory", (NBTBase)var2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  300 */     super.readEntityFromNBT(tagCompund);
/*  301 */     setProfession(tagCompund.getInteger("Profession"));
/*  302 */     this.wealth = tagCompund.getInteger("Riches");
/*  303 */     this.field_175563_bv = tagCompund.getInteger("Career");
/*  304 */     this.field_175562_bw = tagCompund.getInteger("CareerLevel");
/*  305 */     this.field_175565_bs = tagCompund.getBoolean("Willing");
/*      */     
/*  307 */     if (tagCompund.hasKey("Offers", 10)) {
/*      */       
/*  309 */       NBTTagCompound var2 = tagCompund.getCompoundTag("Offers");
/*  310 */       this.buyingList = new MerchantRecipeList(var2);
/*      */     } 
/*      */     
/*  313 */     NBTTagList var5 = tagCompund.getTagList("Inventory", 10);
/*      */     
/*  315 */     for (int var3 = 0; var3 < var5.tagCount(); var3++) {
/*      */       
/*  317 */       ItemStack var4 = ItemStack.loadItemStackFromNBT(var5.getCompoundTagAt(var3));
/*      */       
/*  319 */       if (var4 != null)
/*      */       {
/*  321 */         this.field_175560_bz.func_174894_a(var4);
/*      */       }
/*      */     } 
/*      */     
/*  325 */     setCanPickUpLoot(true);
/*  326 */     func_175552_ct();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canDespawn() {
/*  334 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getLivingSound() {
/*  342 */     return isTrading() ? "mob.villager.haggle" : "mob.villager.idle";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getHurtSound() {
/*  350 */     return "mob.villager.hit";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getDeathSound() {
/*  358 */     return "mob.villager.death";
/*      */   }
/*      */ 
/*      */   
/*      */   public void setProfession(int p_70938_1_) {
/*  363 */     this.dataWatcher.updateObject(16, Integer.valueOf(p_70938_1_));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getProfession() {
/*  368 */     return Math.max(this.dataWatcher.getWatchableObjectInt(16) % 5, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isMating() {
/*  373 */     return this.isMating;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMating(boolean p_70947_1_) {
/*  378 */     this.isMating = p_70947_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPlaying(boolean p_70939_1_) {
/*  383 */     this.isPlaying = p_70939_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPlaying() {
/*  388 */     return this.isPlaying;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRevengeTarget(EntityLivingBase p_70604_1_) {
/*  393 */     super.setRevengeTarget(p_70604_1_);
/*      */     
/*  395 */     if (this.villageObj != null && p_70604_1_ != null) {
/*      */       
/*  397 */       this.villageObj.addOrRenewAgressor(p_70604_1_);
/*      */       
/*  399 */       if (p_70604_1_ instanceof EntityPlayer) {
/*      */         
/*  401 */         byte var2 = -1;
/*      */         
/*  403 */         if (isChild())
/*      */         {
/*  405 */           var2 = -3;
/*      */         }
/*      */         
/*  408 */         this.villageObj.setReputationForPlayer(p_70604_1_.getName(), var2);
/*      */         
/*  410 */         if (isEntityAlive())
/*      */         {
/*  412 */           this.worldObj.setEntityState((Entity)this, (byte)13);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource cause) {
/*  423 */     if (this.villageObj != null) {
/*      */       
/*  425 */       Entity var2 = cause.getEntity();
/*      */       
/*  427 */       if (var2 != null) {
/*      */         
/*  429 */         if (var2 instanceof EntityPlayer)
/*      */         {
/*  431 */           this.villageObj.setReputationForPlayer(var2.getName(), -2);
/*      */         }
/*  433 */         else if (var2 instanceof net.minecraft.entity.monster.IMob)
/*      */         {
/*  435 */           this.villageObj.endMatingSeason();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  440 */         EntityPlayer var3 = this.worldObj.getClosestPlayerToEntity((Entity)this, 16.0D);
/*      */         
/*  442 */         if (var3 != null)
/*      */         {
/*  444 */           this.villageObj.endMatingSeason();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  449 */     super.onDeath(cause);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCustomer(EntityPlayer p_70932_1_) {
/*  454 */     this.buyingPlayer = p_70932_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer getCustomer() {
/*  459 */     return this.buyingPlayer;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTrading() {
/*  464 */     return (this.buyingPlayer != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175550_n(boolean p_175550_1_) {
/*  469 */     if (!this.field_175565_bs && p_175550_1_ && func_175553_cp()) {
/*      */       
/*  471 */       boolean var2 = false;
/*      */       
/*  473 */       for (int var3 = 0; var3 < this.field_175560_bz.getSizeInventory(); var3++) {
/*      */         
/*  475 */         ItemStack var4 = this.field_175560_bz.getStackInSlot(var3);
/*      */         
/*  477 */         if (var4 != null)
/*      */         {
/*  479 */           if (var4.getItem() == Items.bread && var4.stackSize >= 3) {
/*      */             
/*  481 */             var2 = true;
/*  482 */             this.field_175560_bz.decrStackSize(var3, 3);
/*      */           }
/*  484 */           else if ((var4.getItem() == Items.potato || var4.getItem() == Items.carrot) && var4.stackSize >= 12) {
/*      */             
/*  486 */             var2 = true;
/*  487 */             this.field_175560_bz.decrStackSize(var3, 12);
/*      */           } 
/*      */         }
/*      */         
/*  491 */         if (var2) {
/*      */           
/*  493 */           this.worldObj.setEntityState((Entity)this, (byte)18);
/*  494 */           this.field_175565_bs = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  500 */     return this.field_175565_bs;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175549_o(boolean p_175549_1_) {
/*  505 */     this.field_175565_bs = p_175549_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void useRecipe(MerchantRecipe p_70933_1_) {
/*  510 */     p_70933_1_.incrementToolUses();
/*  511 */     this.livingSoundTime = -getTalkInterval();
/*  512 */     playSound("mob.villager.yes", getSoundVolume(), getSoundPitch());
/*  513 */     int var2 = 3 + this.rand.nextInt(4);
/*      */     
/*  515 */     if (p_70933_1_.func_180321_e() == 1 || this.rand.nextInt(5) == 0) {
/*      */       
/*  517 */       this.timeUntilReset = 40;
/*  518 */       this.needsInitilization = true;
/*  519 */       this.field_175565_bs = true;
/*      */       
/*  521 */       if (this.buyingPlayer != null) {
/*      */         
/*  523 */         this.lastBuyingPlayer = this.buyingPlayer.getName();
/*      */       }
/*      */       else {
/*      */         
/*  527 */         this.lastBuyingPlayer = null;
/*      */       } 
/*      */       
/*  530 */       var2 += 5;
/*      */     } 
/*      */     
/*  533 */     if (p_70933_1_.getItemToBuy().getItem() == Items.emerald)
/*      */     {
/*  535 */       this.wealth += (p_70933_1_.getItemToBuy()).stackSize;
/*      */     }
/*      */     
/*  538 */     if (p_70933_1_.func_180322_j())
/*      */     {
/*  540 */       this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY + 0.5D, this.posZ, var2));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void verifySellingItem(ItemStack p_110297_1_) {
/*  550 */     if (!this.worldObj.isRemote && this.livingSoundTime > -getTalkInterval() + 20) {
/*      */       
/*  552 */       this.livingSoundTime = -getTalkInterval();
/*      */       
/*  554 */       if (p_110297_1_ != null) {
/*      */         
/*  556 */         playSound("mob.villager.yes", getSoundVolume(), getSoundPitch());
/*      */       }
/*      */       else {
/*      */         
/*  560 */         playSound("mob.villager.no", getSoundVolume(), getSoundPitch());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public MerchantRecipeList getRecipes(EntityPlayer p_70934_1_) {
/*  567 */     if (this.buyingList == null)
/*      */     {
/*  569 */       func_175554_cu();
/*      */     }
/*      */     
/*  572 */     return this.buyingList;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175554_cu() {
/*  577 */     ITradeList[][][] var1 = field_175561_bA[getProfession()];
/*      */     
/*  579 */     if (this.field_175563_bv != 0 && this.field_175562_bw != 0) {
/*      */       
/*  581 */       this.field_175562_bw++;
/*      */     }
/*      */     else {
/*      */       
/*  585 */       this.field_175563_bv = this.rand.nextInt(var1.length) + 1;
/*  586 */       this.field_175562_bw = 1;
/*      */     } 
/*      */     
/*  589 */     if (this.buyingList == null)
/*      */     {
/*  591 */       this.buyingList = new MerchantRecipeList();
/*      */     }
/*      */     
/*  594 */     int var2 = this.field_175563_bv - 1;
/*  595 */     int var3 = this.field_175562_bw - 1;
/*  596 */     ITradeList[][] var4 = var1[var2];
/*      */     
/*  598 */     if (var3 < var4.length) {
/*      */       
/*  600 */       ITradeList[] var5 = var4[var3];
/*  601 */       ITradeList[] var6 = var5;
/*  602 */       int var7 = var5.length;
/*      */       
/*  604 */       for (int var8 = 0; var8 < var7; var8++) {
/*      */         
/*  606 */         ITradeList var9 = var6[var8];
/*  607 */         var9.func_179401_a(this.buyingList, this.rand);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRecipes(MerchantRecipeList p_70930_1_) {}
/*      */   
/*      */   public IChatComponent getDisplayName() {
/*  616 */     String var1 = getCustomNameTag();
/*      */     
/*  618 */     if (var1 != null && var1.length() > 0)
/*      */     {
/*  620 */       return (IChatComponent)new ChatComponentText(var1);
/*      */     }
/*      */ 
/*      */     
/*  624 */     if (this.buyingList == null)
/*      */     {
/*  626 */       func_175554_cu();
/*      */     }
/*      */     
/*  629 */     String var2 = null;
/*      */     
/*  631 */     switch (getProfession()) {
/*      */       
/*      */       case 0:
/*  634 */         if (this.field_175563_bv == 1) {
/*      */           
/*  636 */           var2 = "farmer"; break;
/*      */         } 
/*  638 */         if (this.field_175563_bv == 2) {
/*      */           
/*  640 */           var2 = "fisherman"; break;
/*      */         } 
/*  642 */         if (this.field_175563_bv == 3) {
/*      */           
/*  644 */           var2 = "shepherd"; break;
/*      */         } 
/*  646 */         if (this.field_175563_bv == 4)
/*      */         {
/*  648 */           var2 = "fletcher";
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1:
/*  654 */         var2 = "librarian";
/*      */         break;
/*      */       
/*      */       case 2:
/*  658 */         var2 = "cleric";
/*      */         break;
/*      */       
/*      */       case 3:
/*  662 */         if (this.field_175563_bv == 1) {
/*      */           
/*  664 */           var2 = "armor"; break;
/*      */         } 
/*  666 */         if (this.field_175563_bv == 2) {
/*      */           
/*  668 */           var2 = "weapon"; break;
/*      */         } 
/*  670 */         if (this.field_175563_bv == 3)
/*      */         {
/*  672 */           var2 = "tool";
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  678 */         if (this.field_175563_bv == 1) {
/*      */           
/*  680 */           var2 = "butcher"; break;
/*      */         } 
/*  682 */         if (this.field_175563_bv == 2)
/*      */         {
/*  684 */           var2 = "leather";
/*      */         }
/*      */         break;
/*      */     } 
/*  688 */     if (var2 != null) {
/*      */       
/*  690 */       ChatComponentTranslation var3 = new ChatComponentTranslation("entity.Villager." + var2, new Object[0]);
/*  691 */       var3.getChatStyle().setChatHoverEvent(func_174823_aP());
/*  692 */       var3.getChatStyle().setInsertion(getUniqueID().toString());
/*  693 */       return (IChatComponent)var3;
/*      */     } 
/*      */ 
/*      */     
/*  697 */     return super.getDisplayName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getEyeHeight() {
/*  704 */     float var1 = 1.62F;
/*      */     
/*  706 */     if (isChild())
/*      */     {
/*  708 */       var1 = (float)(var1 - 0.81D);
/*      */     }
/*      */     
/*  711 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(byte p_70103_1_) {
/*  716 */     if (p_70103_1_ == 12) {
/*      */       
/*  718 */       func_180489_a(EnumParticleTypes.HEART);
/*      */     }
/*  720 */     else if (p_70103_1_ == 13) {
/*      */       
/*  722 */       func_180489_a(EnumParticleTypes.VILLAGER_ANGRY);
/*      */     }
/*  724 */     else if (p_70103_1_ == 14) {
/*      */       
/*  726 */       func_180489_a(EnumParticleTypes.VILLAGER_HAPPY);
/*      */     }
/*      */     else {
/*      */       
/*  730 */       super.handleHealthUpdate(p_70103_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180489_a(EnumParticleTypes p_180489_1_) {
/*  736 */     for (int var2 = 0; var2 < 5; var2++) {
/*      */       
/*  738 */       double var3 = this.rand.nextGaussian() * 0.02D;
/*  739 */       double var5 = this.rand.nextGaussian() * 0.02D;
/*  740 */       double var7 = this.rand.nextGaussian() * 0.02D;
/*  741 */       this.worldObj.spawnParticle(p_180489_1_, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 1.0D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var3, var5, var7, new int[0]);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/*  747 */     p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
/*  748 */     setProfession(this.worldObj.rand.nextInt(5));
/*  749 */     func_175552_ct();
/*  750 */     return p_180482_2_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLookingForHome() {
/*  755 */     this.isLookingForHome = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityVillager func_180488_b(EntityAgeable p_180488_1_) {
/*  760 */     EntityVillager var2 = new EntityVillager(this.worldObj);
/*  761 */     var2.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos((Entity)var2)), (IEntityLivingData)null);
/*  762 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean allowLeashing() {
/*  767 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onStruckByLightning(EntityLightningBolt lightningBolt) {
/*  775 */     if (!this.worldObj.isRemote) {
/*      */       
/*  777 */       EntityWitch var2 = new EntityWitch(this.worldObj);
/*  778 */       var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/*  779 */       var2.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos((Entity)var2)), null);
/*  780 */       this.worldObj.spawnEntityInWorld((Entity)var2);
/*  781 */       setDead();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public InventoryBasic func_175551_co() {
/*  787 */     return this.field_175560_bz;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175445_a(EntityItem p_175445_1_) {
/*  792 */     ItemStack var2 = p_175445_1_.getEntityItem();
/*  793 */     Item var3 = var2.getItem();
/*      */     
/*  795 */     if (func_175558_a(var3)) {
/*      */       
/*  797 */       ItemStack var4 = this.field_175560_bz.func_174894_a(var2);
/*      */       
/*  799 */       if (var4 == null) {
/*      */         
/*  801 */         p_175445_1_.setDead();
/*      */       }
/*      */       else {
/*      */         
/*  805 */         var2.stackSize = var4.stackSize;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_175558_a(Item p_175558_1_) {
/*  812 */     return !(p_175558_1_ != Items.bread && p_175558_1_ != Items.potato && p_175558_1_ != Items.carrot && p_175558_1_ != Items.wheat && p_175558_1_ != Items.wheat_seeds);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175553_cp() {
/*  817 */     return func_175559_s(1);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175555_cq() {
/*  822 */     return func_175559_s(2);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175557_cr() {
/*  827 */     boolean var1 = (getProfession() == 0);
/*  828 */     return var1 ? (!func_175559_s(5)) : (!func_175559_s(1));
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_175559_s(int p_175559_1_) {
/*  833 */     boolean var2 = (getProfession() == 0);
/*      */     
/*  835 */     for (int var3 = 0; var3 < this.field_175560_bz.getSizeInventory(); var3++) {
/*      */       
/*  837 */       ItemStack var4 = this.field_175560_bz.getStackInSlot(var3);
/*      */       
/*  839 */       if (var4 != null) {
/*      */         
/*  841 */         if ((var4.getItem() == Items.bread && var4.stackSize >= 3 * p_175559_1_) || (var4.getItem() == Items.potato && var4.stackSize >= 12 * p_175559_1_) || (var4.getItem() == Items.carrot && var4.stackSize >= 12 * p_175559_1_))
/*      */         {
/*  843 */           return true;
/*      */         }
/*      */         
/*  846 */         if (var2 && var4.getItem() == Items.wheat && var4.stackSize >= 9 * p_175559_1_)
/*      */         {
/*  848 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  853 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175556_cs() {
/*  858 */     for (int var1 = 0; var1 < this.field_175560_bz.getSizeInventory(); var1++) {
/*      */       
/*  860 */       ItemStack var2 = this.field_175560_bz.getStackInSlot(var1);
/*      */       
/*  862 */       if (var2 != null && (var2.getItem() == Items.wheat_seeds || var2.getItem() == Items.potato || var2.getItem() == Items.carrot))
/*      */       {
/*  864 */         return true;
/*      */       }
/*      */     } 
/*      */     
/*  868 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
/*  873 */     if (super.func_174820_d(p_174820_1_, p_174820_2_))
/*      */     {
/*  875 */       return true;
/*      */     }
/*      */ 
/*      */     
/*  879 */     int var3 = p_174820_1_ - 300;
/*      */     
/*  881 */     if (var3 >= 0 && var3 < this.field_175560_bz.getSizeInventory()) {
/*      */       
/*  883 */       this.field_175560_bz.setInventorySlotContents(var3, p_174820_2_);
/*  884 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  888 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable createChild(EntityAgeable p_90011_1_) {
/*  895 */     return func_180488_b(p_90011_1_);
/*      */   }
/*      */   
/*      */   static class EmeraldForItems
/*      */     implements ITradeList
/*      */   {
/*      */     public Item field_179405_a;
/*      */     public EntityVillager.PriceInfo field_179404_b;
/*      */     private static final String __OBFID = "CL_00002194";
/*      */     
/*      */     public EmeraldForItems(Item p_i45815_1_, EntityVillager.PriceInfo p_i45815_2_) {
/*  906 */       this.field_179405_a = p_i45815_1_;
/*  907 */       this.field_179404_b = p_i45815_2_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void func_179401_a(MerchantRecipeList p_179401_1_, Random p_179401_2_) {
/*  912 */       int var3 = 1;
/*      */       
/*  914 */       if (this.field_179404_b != null)
/*      */       {
/*  916 */         var3 = this.field_179404_b.func_179412_a(p_179401_2_);
/*      */       }
/*      */       
/*  919 */       p_179401_1_.add(new MerchantRecipe(new ItemStack(this.field_179405_a, var3, 0), Items.emerald));
/*      */     }
/*      */   }
/*      */   
/*      */   static interface ITradeList
/*      */   {
/*      */     void func_179401_a(MerchantRecipeList param1MerchantRecipeList, Random param1Random);
/*      */   }
/*      */   
/*      */   static class ItemAndEmeraldToItem
/*      */     implements ITradeList
/*      */   {
/*      */     public ItemStack field_179411_a;
/*      */     public EntityVillager.PriceInfo field_179409_b;
/*      */     public ItemStack field_179410_c;
/*      */     public EntityVillager.PriceInfo field_179408_d;
/*      */     private static final String __OBFID = "CL_00002191";
/*      */     
/*      */     public ItemAndEmeraldToItem(Item p_i45813_1_, EntityVillager.PriceInfo p_i45813_2_, Item p_i45813_3_, EntityVillager.PriceInfo p_i45813_4_) {
/*  938 */       this.field_179411_a = new ItemStack(p_i45813_1_);
/*  939 */       this.field_179409_b = p_i45813_2_;
/*  940 */       this.field_179410_c = new ItemStack(p_i45813_3_);
/*  941 */       this.field_179408_d = p_i45813_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void func_179401_a(MerchantRecipeList p_179401_1_, Random p_179401_2_) {
/*  946 */       int var3 = 1;
/*      */       
/*  948 */       if (this.field_179409_b != null)
/*      */       {
/*  950 */         var3 = this.field_179409_b.func_179412_a(p_179401_2_);
/*      */       }
/*      */       
/*  953 */       int var4 = 1;
/*      */       
/*  955 */       if (this.field_179408_d != null)
/*      */       {
/*  957 */         var4 = this.field_179408_d.func_179412_a(p_179401_2_);
/*      */       }
/*      */       
/*  960 */       p_179401_1_.add(new MerchantRecipe(new ItemStack(this.field_179411_a.getItem(), var3, this.field_179411_a.getMetadata()), new ItemStack(Items.emerald), new ItemStack(this.field_179410_c.getItem(), var4, this.field_179410_c.getMetadata())));
/*      */     }
/*      */   }
/*      */   
/*      */   static class ListEnchantedBookForEmeralds
/*      */     implements ITradeList
/*      */   {
/*      */     private static final String __OBFID = "CL_00002193";
/*      */     
/*      */     public void func_179401_a(MerchantRecipeList p_179401_1_, Random p_179401_2_) {
/*  970 */       Enchantment var3 = Enchantment.enchantmentsList[p_179401_2_.nextInt(Enchantment.enchantmentsList.length)];
/*  971 */       int var4 = MathHelper.getRandomIntegerInRange(p_179401_2_, var3.getMinLevel(), var3.getMaxLevel());
/*  972 */       ItemStack var5 = Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(var3, var4));
/*  973 */       int var6 = 2 + p_179401_2_.nextInt(5 + var4 * 10) + 3 * var4;
/*      */       
/*  975 */       if (var6 > 64)
/*      */       {
/*  977 */         var6 = 64;
/*      */       }
/*      */       
/*  980 */       p_179401_1_.add(new MerchantRecipe(new ItemStack(Items.book), new ItemStack(Items.emerald, var6), var5));
/*      */     }
/*      */   }
/*      */   
/*      */   static class ListEnchantedItemForEmeralds
/*      */     implements ITradeList
/*      */   {
/*      */     public ItemStack field_179407_a;
/*      */     public EntityVillager.PriceInfo field_179406_b;
/*      */     private static final String __OBFID = "CL_00002192";
/*      */     
/*      */     public ListEnchantedItemForEmeralds(Item p_i45814_1_, EntityVillager.PriceInfo p_i45814_2_) {
/*  992 */       this.field_179407_a = new ItemStack(p_i45814_1_);
/*  993 */       this.field_179406_b = p_i45814_2_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void func_179401_a(MerchantRecipeList p_179401_1_, Random p_179401_2_) {
/*  998 */       int var3 = 1;
/*      */       
/* 1000 */       if (this.field_179406_b != null)
/*      */       {
/* 1002 */         var3 = this.field_179406_b.func_179412_a(p_179401_2_);
/*      */       }
/*      */       
/* 1005 */       ItemStack var4 = new ItemStack(Items.emerald, var3, 0);
/* 1006 */       ItemStack var5 = new ItemStack(this.field_179407_a.getItem(), 1, this.field_179407_a.getMetadata());
/* 1007 */       var5 = EnchantmentHelper.addRandomEnchantment(p_179401_2_, var5, 5 + p_179401_2_.nextInt(15));
/* 1008 */       p_179401_1_.add(new MerchantRecipe(var4, var5));
/*      */     }
/*      */   }
/*      */   
/*      */   static class ListItemForEmeralds
/*      */     implements ITradeList
/*      */   {
/*      */     public ItemStack field_179403_a;
/*      */     public EntityVillager.PriceInfo field_179402_b;
/*      */     private static final String __OBFID = "CL_00002190";
/*      */     
/*      */     public ListItemForEmeralds(Item p_i45811_1_, EntityVillager.PriceInfo p_i45811_2_) {
/* 1020 */       this.field_179403_a = new ItemStack(p_i45811_1_);
/* 1021 */       this.field_179402_b = p_i45811_2_;
/*      */     }
/*      */ 
/*      */     
/*      */     public ListItemForEmeralds(ItemStack p_i45812_1_, EntityVillager.PriceInfo p_i45812_2_) {
/* 1026 */       this.field_179403_a = p_i45812_1_;
/* 1027 */       this.field_179402_b = p_i45812_2_;
/*      */     }
/*      */     
/*      */     public void func_179401_a(MerchantRecipeList p_179401_1_, Random p_179401_2_) {
/*      */       ItemStack var4, var5;
/* 1032 */       int var3 = 1;
/*      */       
/* 1034 */       if (this.field_179402_b != null)
/*      */       {
/* 1036 */         var3 = this.field_179402_b.func_179412_a(p_179401_2_);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1042 */       if (var3 < 0) {
/*      */         
/* 1044 */         var4 = new ItemStack(Items.emerald, 1, 0);
/* 1045 */         var5 = new ItemStack(this.field_179403_a.getItem(), -var3, this.field_179403_a.getMetadata());
/*      */       }
/*      */       else {
/*      */         
/* 1049 */         var4 = new ItemStack(Items.emerald, var3, 0);
/* 1050 */         var5 = new ItemStack(this.field_179403_a.getItem(), 1, this.field_179403_a.getMetadata());
/*      */       } 
/*      */       
/* 1053 */       p_179401_1_.add(new MerchantRecipe(var4, var5));
/*      */     }
/*      */   }
/*      */   
/*      */   static class PriceInfo
/*      */     extends Tuple
/*      */   {
/*      */     private static final String __OBFID = "CL_00002189";
/*      */     
/*      */     public PriceInfo(int p_i45810_1_, int p_i45810_2_) {
/* 1063 */       super(Integer.valueOf(p_i45810_1_), Integer.valueOf(p_i45810_2_));
/*      */     }
/*      */ 
/*      */     
/*      */     public int func_179412_a(Random p_179412_1_) {
/* 1068 */       return (((Integer)getFirst()).intValue() >= ((Integer)getSecond()).intValue()) ? ((Integer)getFirst()).intValue() : (((Integer)getFirst()).intValue() + p_179412_1_.nextInt(((Integer)getSecond()).intValue() - ((Integer)getFirst()).intValue() + 1));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */