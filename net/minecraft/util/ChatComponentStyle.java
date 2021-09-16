/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ChatComponentStyle
/*     */   implements IChatComponent
/*     */ {
/*  15 */   protected List siblings = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   private ChatStyle style;
/*     */   
/*     */   private static final String __OBFID = "CL_00001257";
/*     */ 
/*     */   
/*     */   public IChatComponent appendSibling(IChatComponent component) {
/*  24 */     component.getChatStyle().setParentStyle(getChatStyle());
/*  25 */     this.siblings.add(component);
/*  26 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSiblings() {
/*  34 */     return this.siblings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatComponent appendText(String text) {
/*  42 */     return appendSibling(new ChatComponentText(text));
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent setChatStyle(ChatStyle style) {
/*  47 */     this.style = style;
/*  48 */     Iterator<IChatComponent> var2 = this.siblings.iterator();
/*     */     
/*  50 */     while (var2.hasNext()) {
/*     */       
/*  52 */       IChatComponent var3 = var2.next();
/*  53 */       var3.getChatStyle().setParentStyle(getChatStyle());
/*     */     } 
/*     */     
/*  56 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatStyle getChatStyle() {
/*  61 */     if (this.style == null) {
/*     */       
/*  63 */       this.style = new ChatStyle();
/*  64 */       Iterator<IChatComponent> var1 = this.siblings.iterator();
/*     */       
/*  66 */       while (var1.hasNext()) {
/*     */         
/*  68 */         IChatComponent var2 = var1.next();
/*  69 */         var2.getChatStyle().setParentStyle(this.style);
/*     */       } 
/*     */     } 
/*     */     
/*  73 */     return this.style;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/*  78 */     return Iterators.concat((Iterator)Iterators.forArray((Object[])new ChatComponentStyle[] { this }, ), createDeepCopyIterator(this.siblings));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getUnformattedText() {
/*  87 */     StringBuilder var1 = new StringBuilder();
/*  88 */     Iterator<IChatComponent> var2 = iterator();
/*     */     
/*  90 */     while (var2.hasNext()) {
/*     */       
/*  92 */       IChatComponent var3 = var2.next();
/*  93 */       var1.append(var3.getUnformattedTextForChat());
/*     */     } 
/*     */     
/*  96 */     return var1.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getFormattedText() {
/* 104 */     StringBuilder var1 = new StringBuilder();
/* 105 */     Iterator<IChatComponent> var2 = iterator();
/*     */     
/* 107 */     while (var2.hasNext()) {
/*     */       
/* 109 */       IChatComponent var3 = var2.next();
/* 110 */       var1.append(var3.getChatStyle().getFormattingCode());
/* 111 */       var1.append(var3.getUnformattedTextForChat());
/* 112 */       var1.append(EnumChatFormatting.RESET);
/*     */     } 
/*     */     
/* 115 */     return var1.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Iterator createDeepCopyIterator(Iterable components) {
/* 124 */     Iterator var1 = Iterators.concat(Iterators.transform(components.iterator(), new Function()
/*     */           {
/*     */             private static final String __OBFID = "CL_00001258";
/*     */             
/*     */             public Iterator apply(IChatComponent p_apply_1_) {
/* 129 */               return p_apply_1_.iterator();
/*     */             }
/*     */             
/*     */             public Object apply(Object p_apply_1_) {
/* 133 */               return apply((IChatComponent)p_apply_1_);
/*     */             }
/*     */           }));
/* 136 */     var1 = Iterators.transform(var1, new Function()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001259";
/*     */           
/*     */           public IChatComponent apply(IChatComponent p_apply_1_) {
/* 141 */             IChatComponent var2 = p_apply_1_.createCopy();
/* 142 */             var2.setChatStyle(var2.getChatStyle().createDeepCopy());
/* 143 */             return var2;
/*     */           }
/*     */           
/*     */           public Object apply(Object p_apply_1_) {
/* 147 */             return apply((IChatComponent)p_apply_1_);
/*     */           }
/*     */         });
/* 150 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 155 */     if (this == p_equals_1_)
/*     */     {
/* 157 */       return true;
/*     */     }
/* 159 */     if (!(p_equals_1_ instanceof ChatComponentStyle))
/*     */     {
/* 161 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 165 */     ChatComponentStyle var2 = (ChatComponentStyle)p_equals_1_;
/* 166 */     return (this.siblings.equals(var2.siblings) && getChatStyle().equals(var2.getChatStyle()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 172 */     return 31 * this.style.hashCode() + this.siblings.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 177 */     return "BaseComponent{style=" + this.style + ", siblings=" + this.siblings + '}';
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ChatComponentStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */