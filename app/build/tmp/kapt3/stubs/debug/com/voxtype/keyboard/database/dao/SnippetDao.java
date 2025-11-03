package com.voxtype.keyboard.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000e0\u0010H\'J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001a\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u001b\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000e0\u00102\u0006\u0010\n\u001a\u00020\u000bH\'J\u000e\u0010\u001d\u001a\u00020\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010!\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\"\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006#"}, d2 = {"Lcom/voxtype/keyboard/database/dao/SnippetDao;", "", "deleteAllSnippets", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSnippet", "snippet", "Lcom/voxtype/keyboard/database/entities/Snippet;", "(Lcom/voxtype/keyboard/database/entities/Snippet;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSnippetsByCategory", "category", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCategories", "", "getAllSnippets", "Lkotlinx/coroutines/flow/Flow;", "getMostUsedSnippets", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSnippetById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSnippetByTrigger", "trigger", "getSnippetCountByCategory", "getSnippetsByCategory", "getTotalSnippetCount", "incrementUsageCount", "insertSnippet", "searchSnippets", "query", "updateSnippet", "app_debug"})
@androidx.room.Dao
public abstract interface SnippetDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertSnippet(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.Snippet snippet, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateSnippet(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.Snippet snippet, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteSnippet(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.Snippet snippet, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM snippets WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSnippetById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.Snippet> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM snippets WHERE trigger = :trigger LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSnippetByTrigger(@org.jetbrains.annotations.NotNull
    java.lang.String trigger, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.Snippet> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM snippets ORDER BY createdDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> getAllSnippets();
    
    @androidx.room.Query(value = "SELECT * FROM snippets WHERE category = :category ORDER BY createdDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.Snippet>> getSnippetsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category);
    
    @androidx.room.Query(value = "SELECT * FROM snippets WHERE trigger LIKE \'%\' || :query || \'%\' OR content LIKE \'%\' || :query || \'%\' ORDER BY usageCount DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object searchSnippets(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.Snippet>> $completion);
    
    @androidx.room.Query(value = "UPDATE snippets SET usageCount = usageCount + 1 WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object incrementUsageCount(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM snippets ORDER BY usageCount DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getMostUsedSnippets(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.Snippet>> $completion);
    
    @androidx.room.Query(value = "SELECT DISTINCT category FROM snippets ORDER BY category")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllCategories(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM snippets WHERE category = :category")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSnippetCountByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM snippets")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalSnippetCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM snippets WHERE category = :category")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteSnippetsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM snippets")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllSnippets(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}