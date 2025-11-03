package com.voxtype.keyboard.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00180\u001aJ\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00132\u0006\u0010 \u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010!J\u0018\u0010\"\u001a\u0004\u0018\u00010\u00132\u0006\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010#\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0016J\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00180\u001a2\u0006\u0010\f\u001a\u00020\nJ\u000e\u0010%\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010&\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010!J\u001c\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00130\u00182\u0006\u0010(\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010)\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/voxtype/keyboard/repository/SnippetRepository;", "", "database", "Lcom/voxtype/keyboard/database/VoxTypeDatabase;", "(Lcom/voxtype/keyboard/database/VoxTypeDatabase;)V", "snippetDao", "Lcom/voxtype/keyboard/database/dao/SnippetDao;", "addSnippet", "", "trigger", "", "content", "category", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllSnippets", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSnippet", "snippet", "Lcom/voxtype/keyboard/database/entities/Snippet;", "(Lcom/voxtype/keyboard/database/entities/Snippet;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSnippetsByCategory", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCategories", "", "getAllSnippets", "Lkotlinx/coroutines/flow/Flow;", "getMostUsedSnippets", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSnippetById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSnippetByTrigger", "getSnippetCountByCategory", "getSnippetsByCategory", "getTotalSnippetCount", "incrementUsageCount", "searchSnippets", "query", "updateSnippet", "app_debug"})
public final class SnippetRepository {
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.VoxTypeDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.dao.SnippetDao snippetDao = null;
    
    public SnippetRepository(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.VoxTypeDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> getAllSnippets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> getSnippetsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addSnippet(@org.jetbrains.annotations.NotNull
    java.lang.String trigger, @org.jetbrains.annotations.NotNull
    java.lang.String content, @org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateSnippet(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.Snippet snippet, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteSnippet(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.Snippet snippet, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getSnippetById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.Snippet> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getSnippetByTrigger(@org.jetbrains.annotations.NotNull
    java.lang.String trigger, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.Snippet> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object searchSnippets(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.Snippet>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object incrementUsageCount(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getMostUsedSnippets(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.Snippet>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAllCategories(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getSnippetCountByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTotalSnippetCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteSnippetsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteAllSnippets(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}