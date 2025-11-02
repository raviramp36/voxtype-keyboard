package com.voxtype.keyboard.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J;\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00d6\u0001J\t\u0010#\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\f\u00a8\u0006$"}, d2 = {"Lcom/voxtype/keyboard/database/WordFrequency;", "", "word", "", "frequency", "", "firstUsed", "Ljava/util/Date;", "lastUsed", "contexts", "(Ljava/lang/String;ILjava/util/Date;Ljava/util/Date;Ljava/lang/String;)V", "getContexts", "()Ljava/lang/String;", "setContexts", "(Ljava/lang/String;)V", "getFirstUsed", "()Ljava/util/Date;", "getFrequency", "()I", "setFrequency", "(I)V", "getLastUsed", "setLastUsed", "(Ljava/util/Date;)V", "getWord", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
@androidx.room.Entity(tableName = "word_frequency", indices = {@androidx.room.Index(value = {"word"}, unique = true)})
public final class WordFrequency {
    @androidx.room.PrimaryKey
    @org.jetbrains.annotations.NotNull
    private final java.lang.String word = null;
    private int frequency;
    @org.jetbrains.annotations.NotNull
    private final java.util.Date firstUsed = null;
    @org.jetbrains.annotations.NotNull
    private java.util.Date lastUsed;
    @org.jetbrains.annotations.NotNull
    private java.lang.String contexts;
    
    public WordFrequency(@org.jetbrains.annotations.NotNull
    java.lang.String word, int frequency, @org.jetbrains.annotations.NotNull
    java.util.Date firstUsed, @org.jetbrains.annotations.NotNull
    java.util.Date lastUsed, @org.jetbrains.annotations.NotNull
    java.lang.String contexts) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getWord() {
        return null;
    }
    
    public final int getFrequency() {
        return 0;
    }
    
    public final void setFrequency(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getFirstUsed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getLastUsed() {
        return null;
    }
    
    public final void setLastUsed(@org.jetbrains.annotations.NotNull
    java.util.Date p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getContexts() {
        return null;
    }
    
    public final void setContexts(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.voxtype.keyboard.database.WordFrequency copy(@org.jetbrains.annotations.NotNull
    java.lang.String word, int frequency, @org.jetbrains.annotations.NotNull
    java.util.Date firstUsed, @org.jetbrains.annotations.NotNull
    java.util.Date lastUsed, @org.jetbrains.annotations.NotNull
    java.lang.String contexts) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}