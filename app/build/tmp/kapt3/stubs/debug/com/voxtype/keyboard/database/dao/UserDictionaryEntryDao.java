package com.voxtype.keyboard.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000e0\rH\'J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\u001b\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u001d"}, d2 = {"Lcom/voxtype/keyboard/database/dao/UserDictionaryEntryDao;", "", "deleteAllWords", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWord", "word", "Lcom/voxtype/keyboard/database/entities/UserDictionaryEntry;", "(Lcom/voxtype/keyboard/database/entities/UserDictionaryEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWordByText", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWords", "Lkotlinx/coroutines/flow/Flow;", "", "getRecentlyAddedWords", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalWordCount", "getWordById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordByText", "insertWord", "searchWords", "query", "updateWord", "app_debug"})
@androidx.room.Dao
public abstract interface UserDictionaryEntryDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.UserDictionaryEntry word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.UserDictionaryEntry word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.UserDictionaryEntry word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_dictionary_entries WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWordById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.UserDictionaryEntry> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_dictionary_entries WHERE word = :word LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWordByText(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.UserDictionaryEntry> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_dictionary_entries ORDER BY word")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.UserDictionaryEntry>> getAllWords();
    
    @androidx.room.Query(value = "SELECT * FROM user_dictionary_entries WHERE word LIKE \'%\' || :query || \'%\' OR replacement LIKE \'%\' || :query || \'%\' ORDER BY word")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object searchWords(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserDictionaryEntry>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM user_dictionary_entries ORDER BY createdDate DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRecentlyAddedWords(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.UserDictionaryEntry>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM user_dictionary_entries")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalWordCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM user_dictionary_entries WHERE word = :word")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteWordByText(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM user_dictionary_entries")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllWords(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}