package com.voxtype.keyboard.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00110\u0010H\'J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0015J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u0017H\u0096@\u00a2\u0006\u0002\u0010\u0018J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u001aH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\u0006\u0010\u000e\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ \u0010\u001f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\"\u001a\u00020\u00032\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H\u00a7@\u00a2\u0006\u0002\u0010$J\u001e\u0010%\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u0013H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010\'\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u0017H\u0096@\u00a2\u0006\u0002\u0010\u0018J\u001e\u0010(\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u001aH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010)\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010 J\u0016\u0010*\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006+"}, d2 = {"Lcom/voxtype/keyboard/database/dao/SettingsPreferenceDao;", "", "deleteAllPreferences", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePreference", "preference", "Lcom/voxtype/keyboard/database/entities/SettingsPreference;", "(Lcom/voxtype/keyboard/database/entities/SettingsPreference;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePreferenceByKey", "key", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePreferencesByPattern", "keyPattern", "getAllPreferences", "Lkotlinx/coroutines/flow/Flow;", "", "getBooleanPreference", "", "defaultValue", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFloatPreference", "", "(Ljava/lang/String;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIntPreference", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPreference", "getPreferenceValue", "getPreferencesByPattern", "getStringPreference", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPreference", "insertPreferences", "preferences", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setBooleanPreference", "value", "setFloatPreference", "setIntPreference", "setStringPreference", "updatePreference", "app_debug"})
@androidx.room.Dao
public abstract interface SettingsPreferenceDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPreference(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.SettingsPreference preference, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updatePreference(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.SettingsPreference preference, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deletePreference(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.entities.SettingsPreference preference, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM settings_preferences WHERE key = :key LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.SettingsPreference> $completion);
    
    @androidx.room.Query(value = "SELECT value FROM settings_preferences WHERE key = :key LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPreferenceValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM settings_preferences ORDER BY key")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.SettingsPreference>> getAllPreferences();
    
    @androidx.room.Query(value = "SELECT * FROM settings_preferences WHERE key LIKE :keyPattern ORDER BY key")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPreferencesByPattern(@org.jetbrains.annotations.NotNull
    java.lang.String keyPattern, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.voxtype.keyboard.database.entities.SettingsPreference>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPreferences(@org.jetbrains.annotations.NotNull
    java.util.List<com.voxtype.keyboard.database.entities.SettingsPreference> preferences, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM settings_preferences WHERE key = :key")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deletePreferenceByKey(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM settings_preferences WHERE key LIKE :keyPattern")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deletePreferencesByPattern(@org.jetbrains.annotations.NotNull
    java.lang.String keyPattern, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM settings_preferences")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllPreferences(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setBooleanPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, boolean value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getBooleanPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, boolean defaultValue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setIntPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, int value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getIntPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, int defaultValue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setFloatPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, float value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getFloatPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, float defaultValue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Float> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object setStringPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStringPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String defaultValue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object setBooleanPreference(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.dao.SettingsPreferenceDao $this, @org.jetbrains.annotations.NotNull
        java.lang.String key, boolean value, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object getBooleanPreference(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.dao.SettingsPreferenceDao $this, @org.jetbrains.annotations.NotNull
        java.lang.String key, boolean defaultValue, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object setIntPreference(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.dao.SettingsPreferenceDao $this, @org.jetbrains.annotations.NotNull
        java.lang.String key, int value, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object getIntPreference(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.dao.SettingsPreferenceDao $this, @org.jetbrains.annotations.NotNull
        java.lang.String key, int defaultValue, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object setFloatPreference(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.dao.SettingsPreferenceDao $this, @org.jetbrains.annotations.NotNull
        java.lang.String key, float value, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object getFloatPreference(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.dao.SettingsPreferenceDao $this, @org.jetbrains.annotations.NotNull
        java.lang.String key, float defaultValue, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super java.lang.Float> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object setStringPreference(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.dao.SettingsPreferenceDao $this, @org.jetbrains.annotations.NotNull
        java.lang.String key, @org.jetbrains.annotations.NotNull
        java.lang.String value, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object getStringPreference(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.dao.SettingsPreferenceDao $this, @org.jetbrains.annotations.NotNull
        java.lang.String key, @org.jetbrains.annotations.NotNull
        java.lang.String defaultValue, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
            return null;
        }
    }
}