package com.voxtype.keyboard.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/voxtype/keyboard/database/VoxTypeDatabase;", "Landroidx/room/RoomDatabase;", "()V", "correctionDao", "Lcom/voxtype/keyboard/database/CorrectionDao;", "statsDao", "Lcom/voxtype/keyboard/database/StatsDao;", "transcriptionDao", "Lcom/voxtype/keyboard/database/TranscriptionDao;", "userDictionaryDao", "Lcom/voxtype/keyboard/database/UserDictionaryDao;", "wordFrequencyDao", "Lcom/voxtype/keyboard/database/WordFrequencyDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.voxtype.keyboard.database.TranscriptionEntry.class, com.voxtype.keyboard.database.CorrectionEntry.class, com.voxtype.keyboard.database.DailyStats.class, com.voxtype.keyboard.database.WordFrequency.class, com.voxtype.keyboard.database.UserDictionary.class}, version = 1, exportSchema = true)
@androidx.room.TypeConverters(value = {com.voxtype.keyboard.database.DateConverters.class})
public abstract class VoxTypeDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.voxtype.keyboard.database.VoxTypeDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final com.voxtype.keyboard.database.VoxTypeDatabase.Companion Companion = null;
    
    public VoxTypeDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.voxtype.keyboard.database.TranscriptionDao transcriptionDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.voxtype.keyboard.database.CorrectionDao correctionDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.voxtype.keyboard.database.StatsDao statsDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.voxtype.keyboard.database.WordFrequencyDao wordFrequencyDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.voxtype.keyboard.database.UserDictionaryDao userDictionaryDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/voxtype/keyboard/database/VoxTypeDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/voxtype/keyboard/database/VoxTypeDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.voxtype.keyboard.database.VoxTypeDatabase getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}