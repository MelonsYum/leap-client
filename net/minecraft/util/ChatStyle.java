/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import net.minecraft.event.HoverEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChatStyle
/*     */ {
/*     */   private ChatStyle parentStyle;
/*     */   private EnumChatFormatting color;
/*     */   private Boolean bold;
/*     */   private Boolean italic;
/*     */   private Boolean underlined;
/*     */   private Boolean strikethrough;
/*     */   private Boolean obfuscated;
/*     */   private ClickEvent chatClickEvent;
/*     */   private HoverEvent chatHoverEvent;
/*     */   private String insertion;
/*  33 */   private static final ChatStyle rootStyle = new ChatStyle()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001267";
/*     */       
/*     */       public EnumChatFormatting getColor() {
/*  38 */         return null;
/*     */       }
/*     */       
/*     */       public boolean getBold() {
/*  42 */         return false;
/*     */       }
/*     */       
/*     */       public boolean getItalic() {
/*  46 */         return false;
/*     */       }
/*     */       
/*     */       public boolean getStrikethrough() {
/*  50 */         return false;
/*     */       }
/*     */       
/*     */       public boolean getUnderlined() {
/*  54 */         return false;
/*     */       }
/*     */       
/*     */       public boolean getObfuscated() {
/*  58 */         return false;
/*     */       }
/*     */       
/*     */       public ClickEvent getChatClickEvent() {
/*  62 */         return null;
/*     */       }
/*     */       
/*     */       public HoverEvent getChatHoverEvent() {
/*  66 */         return null;
/*     */       }
/*     */       
/*     */       public String getInsertion() {
/*  70 */         return null;
/*     */       }
/*     */       
/*     */       public ChatStyle setColor(EnumChatFormatting color) {
/*  74 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatStyle setBold(Boolean p_150227_1_) {
/*  78 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatStyle setItalic(Boolean italic) {
/*  82 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatStyle setStrikethrough(Boolean strikethrough) {
/*  86 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatStyle setUnderlined(Boolean underlined) {
/*  90 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatStyle setObfuscated(Boolean obfuscated) {
/*  94 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatStyle setChatClickEvent(ClickEvent event) {
/*  98 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatStyle setChatHoverEvent(HoverEvent event) {
/* 102 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public ChatStyle setParentStyle(ChatStyle parent) {
/* 106 */         throw new UnsupportedOperationException();
/*     */       }
/*     */       
/*     */       public String toString() {
/* 110 */         return "Style.ROOT";
/*     */       }
/*     */       
/*     */       public ChatStyle createShallowCopy() {
/* 114 */         return this;
/*     */       }
/*     */       
/*     */       public ChatStyle createDeepCopy() {
/* 118 */         return this;
/*     */       }
/*     */       
/*     */       public String getFormattingCode() {
/* 122 */         return "";
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001266";
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumChatFormatting getColor() {
/* 132 */     return (this.color == null) ? getParent().getColor() : this.color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBold() {
/* 140 */     return (this.bold == null) ? getParent().getBold() : this.bold.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getItalic() {
/* 148 */     return (this.italic == null) ? getParent().getItalic() : this.italic.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getStrikethrough() {
/* 156 */     return (this.strikethrough == null) ? getParent().getStrikethrough() : this.strikethrough.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getUnderlined() {
/* 164 */     return (this.underlined == null) ? getParent().getUnderlined() : this.underlined.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getObfuscated() {
/* 172 */     return (this.obfuscated == null) ? getParent().getObfuscated() : this.obfuscated.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 180 */     return (this.bold == null && this.italic == null && this.strikethrough == null && this.underlined == null && this.obfuscated == null && this.color == null && this.chatClickEvent == null && this.chatHoverEvent == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClickEvent getChatClickEvent() {
/* 188 */     return (this.chatClickEvent == null) ? getParent().getChatClickEvent() : this.chatClickEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HoverEvent getChatHoverEvent() {
/* 196 */     return (this.chatHoverEvent == null) ? getParent().getChatHoverEvent() : this.chatHoverEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInsertion() {
/* 204 */     return (this.insertion == null) ? getParent().getInsertion() : this.insertion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setColor(EnumChatFormatting color) {
/* 213 */     this.color = color;
/* 214 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setBold(Boolean p_150227_1_) {
/* 223 */     this.bold = p_150227_1_;
/* 224 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setItalic(Boolean italic) {
/* 233 */     this.italic = italic;
/* 234 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setStrikethrough(Boolean strikethrough) {
/* 243 */     this.strikethrough = strikethrough;
/* 244 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setUnderlined(Boolean underlined) {
/* 253 */     this.underlined = underlined;
/* 254 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setObfuscated(Boolean obfuscated) {
/* 263 */     this.obfuscated = obfuscated;
/* 264 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setChatClickEvent(ClickEvent event) {
/* 272 */     this.chatClickEvent = event;
/* 273 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setChatHoverEvent(HoverEvent event) {
/* 281 */     this.chatHoverEvent = event;
/* 282 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setInsertion(String insertion) {
/* 290 */     this.insertion = insertion;
/* 291 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle setParentStyle(ChatStyle parent) {
/* 300 */     this.parentStyle = parent;
/* 301 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormattingCode() {
/* 309 */     if (isEmpty())
/*     */     {
/* 311 */       return (this.parentStyle != null) ? this.parentStyle.getFormattingCode() : "";
/*     */     }
/*     */ 
/*     */     
/* 315 */     StringBuilder var1 = new StringBuilder();
/*     */     
/* 317 */     if (getColor() != null)
/*     */     {
/* 319 */       var1.append(getColor());
/*     */     }
/*     */     
/* 322 */     if (getBold())
/*     */     {
/* 324 */       var1.append(EnumChatFormatting.BOLD);
/*     */     }
/*     */     
/* 327 */     if (getItalic())
/*     */     {
/* 329 */       var1.append(EnumChatFormatting.ITALIC);
/*     */     }
/*     */     
/* 332 */     if (getUnderlined())
/*     */     {
/* 334 */       var1.append(EnumChatFormatting.UNDERLINE);
/*     */     }
/*     */     
/* 337 */     if (getObfuscated())
/*     */     {
/* 339 */       var1.append(EnumChatFormatting.OBFUSCATED);
/*     */     }
/*     */     
/* 342 */     if (getStrikethrough())
/*     */     {
/* 344 */       var1.append(EnumChatFormatting.STRIKETHROUGH);
/*     */     }
/*     */     
/* 347 */     return var1.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ChatStyle getParent() {
/* 356 */     return (this.parentStyle == null) ? rootStyle : this.parentStyle;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 361 */     return "Style{hasParent=" + ((this.parentStyle != null) ? 1 : 0) + ", color=" + this.color + ", bold=" + this.bold + ", italic=" + this.italic + ", underlined=" + this.underlined + ", obfuscated=" + this.obfuscated + ", clickEvent=" + getChatClickEvent() + ", hoverEvent=" + getChatHoverEvent() + ", insertion=" + getInsertion() + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 366 */     if (this == p_equals_1_)
/*     */     {
/* 368 */       return true;
/*     */     }
/* 370 */     if (!(p_equals_1_ instanceof ChatStyle))
/*     */     {
/* 372 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 376 */     ChatStyle var2 = (ChatStyle)p_equals_1_;
/*     */ 
/*     */     
/* 379 */     if (getBold() == var2.getBold() && getColor() == var2.getColor() && getItalic() == var2.getItalic() && getObfuscated() == var2.getObfuscated() && getStrikethrough() == var2.getStrikethrough() && getUnderlined() == var2.getUnderlined()) {
/*     */ 
/*     */ 
/*     */       
/* 383 */       if (getChatClickEvent() != null)
/*     */       
/* 385 */       { if (!getChatClickEvent().equals(var2.getChatClickEvent()))
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 424 */           boolean var10000 = false;
/* 425 */           return var10000; }  } else if (var2.getChatClickEvent() != null) { boolean var10000 = false; return var10000; }  if (getChatHoverEvent() != null) { if (!getChatHoverEvent().equals(var2.getChatHoverEvent()))
/*     */           return false;  } else if (var2.getChatHoverEvent() != null) { return false; }
/*     */        if (getInsertion() != null) { if (!getInsertion().equals(var2.getInsertion()))
/*     */           return false;  }
/*     */       else if (var2.getInsertion() != null) { return false; }
/*     */        return true;
/* 431 */     }  return false; } public int hashCode() { int var1 = this.color.hashCode();
/* 432 */     var1 = 31 * var1 + this.bold.hashCode();
/* 433 */     var1 = 31 * var1 + this.italic.hashCode();
/* 434 */     var1 = 31 * var1 + this.underlined.hashCode();
/* 435 */     var1 = 31 * var1 + this.strikethrough.hashCode();
/* 436 */     var1 = 31 * var1 + this.obfuscated.hashCode();
/* 437 */     var1 = 31 * var1 + this.chatClickEvent.hashCode();
/* 438 */     var1 = 31 * var1 + this.chatHoverEvent.hashCode();
/* 439 */     var1 = 31 * var1 + this.insertion.hashCode();
/* 440 */     return var1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle createShallowCopy() {
/* 450 */     ChatStyle var1 = new ChatStyle();
/* 451 */     var1.bold = this.bold;
/* 452 */     var1.italic = this.italic;
/* 453 */     var1.strikethrough = this.strikethrough;
/* 454 */     var1.underlined = this.underlined;
/* 455 */     var1.obfuscated = this.obfuscated;
/* 456 */     var1.color = this.color;
/* 457 */     var1.chatClickEvent = this.chatClickEvent;
/* 458 */     var1.chatHoverEvent = this.chatHoverEvent;
/* 459 */     var1.parentStyle = this.parentStyle;
/* 460 */     var1.insertion = this.insertion;
/* 461 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatStyle createDeepCopy() {
/* 470 */     ChatStyle var1 = new ChatStyle();
/* 471 */     var1.setBold(Boolean.valueOf(getBold()));
/* 472 */     var1.setItalic(Boolean.valueOf(getItalic()));
/* 473 */     var1.setStrikethrough(Boolean.valueOf(getStrikethrough()));
/* 474 */     var1.setUnderlined(Boolean.valueOf(getUnderlined()));
/* 475 */     var1.setObfuscated(Boolean.valueOf(getObfuscated()));
/* 476 */     var1.setColor(getColor());
/* 477 */     var1.setChatClickEvent(getChatClickEvent());
/* 478 */     var1.setChatHoverEvent(getChatHoverEvent());
/* 479 */     var1.setInsertion(getInsertion());
/* 480 */     return var1;
/*     */   }
/*     */   
/*     */   public static class Serializer
/*     */     implements JsonDeserializer, JsonSerializer
/*     */   {
/*     */     private static final String __OBFID = "CL_00001268";
/*     */     
/*     */     public ChatStyle deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 489 */       if (p_deserialize_1_.isJsonObject()) {
/*     */         
/* 491 */         ChatStyle var4 = new ChatStyle();
/* 492 */         JsonObject var5 = p_deserialize_1_.getAsJsonObject();
/*     */         
/* 494 */         if (var5 == null)
/*     */         {
/* 496 */           return null;
/*     */         }
/*     */ 
/*     */         
/* 500 */         if (var5.has("bold"))
/*     */         {
/* 502 */           var4.bold = Boolean.valueOf(var5.get("bold").getAsBoolean());
/*     */         }
/*     */         
/* 505 */         if (var5.has("italic"))
/*     */         {
/* 507 */           var4.italic = Boolean.valueOf(var5.get("italic").getAsBoolean());
/*     */         }
/*     */         
/* 510 */         if (var5.has("underlined"))
/*     */         {
/* 512 */           var4.underlined = Boolean.valueOf(var5.get("underlined").getAsBoolean());
/*     */         }
/*     */         
/* 515 */         if (var5.has("strikethrough"))
/*     */         {
/* 517 */           var4.strikethrough = Boolean.valueOf(var5.get("strikethrough").getAsBoolean());
/*     */         }
/*     */         
/* 520 */         if (var5.has("obfuscated"))
/*     */         {
/* 522 */           var4.obfuscated = Boolean.valueOf(var5.get("obfuscated").getAsBoolean());
/*     */         }
/*     */         
/* 525 */         if (var5.has("color"))
/*     */         {
/* 527 */           var4.color = (EnumChatFormatting)p_deserialize_3_.deserialize(var5.get("color"), EnumChatFormatting.class);
/*     */         }
/*     */         
/* 530 */         if (var5.has("insertion"))
/*     */         {
/* 532 */           var4.insertion = var5.get("insertion").getAsString();
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 538 */         if (var5.has("clickEvent")) {
/*     */           
/* 540 */           JsonObject var6 = var5.getAsJsonObject("clickEvent");
/*     */           
/* 542 */           if (var6 != null) {
/*     */             
/* 544 */             JsonPrimitive var7 = var6.getAsJsonPrimitive("action");
/* 545 */             ClickEvent.Action var8 = (var7 == null) ? null : ClickEvent.Action.getValueByCanonicalName(var7.getAsString());
/* 546 */             JsonPrimitive var9 = var6.getAsJsonPrimitive("value");
/* 547 */             String var10 = (var9 == null) ? null : var9.getAsString();
/*     */             
/* 549 */             if (var8 != null && var10 != null && var8.shouldAllowInChat())
/*     */             {
/* 551 */               var4.chatClickEvent = new ClickEvent(var8, var10);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 556 */         if (var5.has("hoverEvent")) {
/*     */           
/* 558 */           JsonObject var6 = var5.getAsJsonObject("hoverEvent");
/*     */           
/* 560 */           if (var6 != null) {
/*     */             
/* 562 */             JsonPrimitive var7 = var6.getAsJsonPrimitive("action");
/* 563 */             HoverEvent.Action var11 = (var7 == null) ? null : HoverEvent.Action.getValueByCanonicalName(var7.getAsString());
/* 564 */             IChatComponent var12 = (IChatComponent)p_deserialize_3_.deserialize(var6.get("value"), IChatComponent.class);
/*     */             
/* 566 */             if (var11 != null && var12 != null && var11.shouldAllowInChat())
/*     */             {
/* 568 */               var4.chatHoverEvent = new HoverEvent(var11, var12);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 573 */         return var4;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 578 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonElement serialize(ChatStyle p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 584 */       if (p_serialize_1_.isEmpty())
/*     */       {
/* 586 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 590 */       JsonObject var4 = new JsonObject();
/*     */       
/* 592 */       if (p_serialize_1_.bold != null)
/*     */       {
/* 594 */         var4.addProperty("bold", p_serialize_1_.bold);
/*     */       }
/*     */       
/* 597 */       if (p_serialize_1_.italic != null)
/*     */       {
/* 599 */         var4.addProperty("italic", p_serialize_1_.italic);
/*     */       }
/*     */       
/* 602 */       if (p_serialize_1_.underlined != null)
/*     */       {
/* 604 */         var4.addProperty("underlined", p_serialize_1_.underlined);
/*     */       }
/*     */       
/* 607 */       if (p_serialize_1_.strikethrough != null)
/*     */       {
/* 609 */         var4.addProperty("strikethrough", p_serialize_1_.strikethrough);
/*     */       }
/*     */       
/* 612 */       if (p_serialize_1_.obfuscated != null)
/*     */       {
/* 614 */         var4.addProperty("obfuscated", p_serialize_1_.obfuscated);
/*     */       }
/*     */       
/* 617 */       if (p_serialize_1_.color != null)
/*     */       {
/* 619 */         var4.add("color", p_serialize_3_.serialize(p_serialize_1_.color));
/*     */       }
/*     */       
/* 622 */       if (p_serialize_1_.insertion != null)
/*     */       {
/* 624 */         var4.add("insertion", p_serialize_3_.serialize(p_serialize_1_.insertion));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 629 */       if (p_serialize_1_.chatClickEvent != null) {
/*     */         
/* 631 */         JsonObject var5 = new JsonObject();
/* 632 */         var5.addProperty("action", p_serialize_1_.chatClickEvent.getAction().getCanonicalName());
/* 633 */         var5.addProperty("value", p_serialize_1_.chatClickEvent.getValue());
/* 634 */         var4.add("clickEvent", (JsonElement)var5);
/*     */       } 
/*     */       
/* 637 */       if (p_serialize_1_.chatHoverEvent != null) {
/*     */         
/* 639 */         JsonObject var5 = new JsonObject();
/* 640 */         var5.addProperty("action", p_serialize_1_.chatHoverEvent.getAction().getCanonicalName());
/* 641 */         var5.add("value", p_serialize_3_.serialize(p_serialize_1_.chatHoverEvent.getValue()));
/* 642 */         var4.add("hoverEvent", (JsonElement)var5);
/*     */       } 
/*     */       
/* 645 */       return (JsonElement)var4;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 651 */       return serialize((ChatStyle)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ChatStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */