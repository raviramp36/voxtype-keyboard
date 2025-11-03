package com.voxtype.keyboard.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00170\u0016J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u000e\u0010\u001d\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00170\u00162\u0006\u0010\u000b\u001a\u00020\nJ\u0016\u0010 \u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J&\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\u0006\u0010\"\u001a\u00020\n2\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/voxtype/keyboard/repository/DictionaryRepository;", "", "database", "Lcom/voxtype/keyboard/database/VoxTypeDatabase;", "(Lcom/voxtype/keyboard/database/VoxTypeDatabase;)V", "dictionaryDao", "Lcom/voxtype/keyboard/database/dao/DictionaryWordDao;", "addWord", "", "word", "", "language", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllWords", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWord", "Lcom/voxtype/keyboard/database/entities/DictionaryWord;", "(Lcom/voxtype/keyboard/database/entities/DictionaryWord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWordsByLanguage", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWords", "Lkotlinx/coroutines/flow/Flow;", "", "getMostFrequentWords", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentlyUsedWords", "getTotalWordCount", "getWordCountByLanguage", "getWordsByLanguage", "incrementWordFrequency", "searchWords", "query", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateWord", "app_debug"})
public final class DictionaryRepository {
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.VoxTypeDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.dao.DictionaryWordDao dictionaryDao = null;
    
    public DictionaryRepository(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.VoxTypeDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> getAllWords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> getWordsByLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addWord(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.DictionaryWord word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.DictionaryWord word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object searchWords(@org.jetbrains.annotations.NotNull
    java.lang.String query, int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object incrementWordFrequency(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getMostFrequentWords(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getRecentlyUsedWords(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getWordCountByLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTotalWordCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteWordsByLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteAllWords(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}