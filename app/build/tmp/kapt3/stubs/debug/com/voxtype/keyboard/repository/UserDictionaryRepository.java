package com.voxtype.keyboard.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00170\u0016J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001e\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u0004\u0018\u00010\u00112\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\u0006\u0010\"\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010#\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/voxtype/keyboard/repository/UserDictionaryRepository;", "", "database", "Lcom/voxtype/keyboard/database/VoxTypeDatabase;", "(Lcom/voxtype/keyboard/database/VoxTypeDatabase;)V", "userDictionaryDao", "Lcom/voxtype/keyboard/database/dao/UserDictionaryEntryDao;", "addWord", "", "word", "", "replacement", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllWords", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWord", "Lcom/voxtype/keyboard/database/entities/UserDictionaryEntry;", "(Lcom/voxtype/keyboard/database/entities/UserDictionaryEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWordByText", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWords", "Lkotlinx/coroutines/flow/Flow;", "", "getRecentlyAddedWords", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalWordCount", "getWordById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordByText", "searchWords", "query", "updateWord", "app_debug"})
public final class UserDictionaryRepository {
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.VoxTypeDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.dao.UserDictionaryEntryDao userDictionaryDao = null;
    
    public UserDictionaryRepository(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.VoxTypeDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.UserDictionaryEntry>> getAllWords() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addWord(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    java.lang.String replacement, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.UserDictionaryEntry word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.UserDictionaryEntry word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getWordById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.UserDictionaryEntry> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getWordByText(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.UserDictionaryEntry> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object searchWords(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserDictionaryEntry>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getRecentlyAddedWords(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserDictionaryEntry>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTotalWordCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteWordByText(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteAllWords(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}