package com.voxtype.keyboard.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH\'J\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u000e\u0010\u0015\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u001b\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000e2\u0006\u0010\n\u001a\u00020\u000bH\'J \u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u00a7@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u00182\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ&\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\u0006\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006&"}, d2 = {"Lcom/voxtype/keyboard/database/dao/DictionaryWordDao;", "", "deleteAllWords", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWord", "word", "Lcom/voxtype/keyboard/database/entities/DictionaryWord;", "(Lcom/voxtype/keyboard/database/entities/DictionaryWord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteWordsByLanguage", "language", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWords", "Lkotlinx/coroutines/flow/Flow;", "", "getMostFrequentWords", "limit", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentlyUsedWords", "getTotalWordCount", "getWordById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWordByText", "getWordCountByLanguage", "getWordsByLanguage", "incrementWordFrequency", "date", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWord", "searchWords", "query", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateWord", "app_debug"})
@androidx.room.Dao
public abstract interface DictionaryWordDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.DictionaryWord word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.DictionaryWord word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteWord(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.DictionaryWord word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM dictionary_words WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWordById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.DictionaryWord> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM dictionary_words WHERE word = :word LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWordByText(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.DictionaryWord> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM dictionary_words WHERE language = :language ORDER BY frequency DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> getWordsByLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language);
    
    @androidx.room.Query(value = "SELECT * FROM dictionary_words ORDER BY frequency DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> getAllWords();
    
    @androidx.room.Query(value = "SELECT * FROM dictionary_words WHERE word LIKE \'%\' || :query || \'%\' ORDER BY frequency DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object searchWords(@org.jetbrains.annotations.NotNull
    java.lang.String query, int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> $completion);
    
    @androidx.room.Query(value = "UPDATE dictionary_words SET frequency = frequency + 1, lastUsed = :date WHERE word = :word")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object incrementWordFrequency(@org.jetbrains.annotations.NotNull
    java.lang.String word, @org.jetbrains.annotations.NotNull
    java.util.Date date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM dictionary_words ORDER BY frequency DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getMostFrequentWords(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM dictionary_words ORDER BY lastUsed DESC LIMIT :limit")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRecentlyUsedWords(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.DictionaryWord>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM dictionary_words WHERE language = :language")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWordCountByLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM dictionary_words")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalWordCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM dictionary_words WHERE language = :language")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteWordsByLanguage(@org.jetbrains.annotations.NotNull
    java.lang.String language, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM dictionary_words")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllWords(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}