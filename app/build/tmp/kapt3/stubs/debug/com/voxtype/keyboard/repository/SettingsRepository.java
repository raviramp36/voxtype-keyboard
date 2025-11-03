package com.voxtype.keyboard.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001:\u0001&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fJ \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0015J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ \u0010\u001d\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u001eJ \u0010\u001f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u001e\u0010 \u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010!\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010\"\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010!\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u001e\u0010#\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010!\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010$\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u001e\u0010%\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/voxtype/keyboard/repository/SettingsRepository;", "", "database", "Lcom/voxtype/keyboard/database/VoxTypeDatabase;", "(Lcom/voxtype/keyboard/database/VoxTypeDatabase;)V", "settingsDao", "Lcom/voxtype/keyboard/database/dao/SettingsPreferenceDao;", "deleteAllPreferences", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePreference", "key", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPreferences", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/voxtype/keyboard/database/entities/SettingsPreference;", "getBooleanSetting", "", "defaultValue", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFloatSetting", "", "(Ljava/lang/String;FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIntSetting", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPreference", "getPreferenceValue", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStringSetting", "setBooleanSetting", "value", "setFloatSetting", "setIntSetting", "setPreference", "setStringSetting", "SettingsKeys", "app_debug"})
public final class SettingsRepository {
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.VoxTypeDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final com.voxtype.keyboard.database.dao.SettingsPreferenceDao settingsDao = null;
    
    public SettingsRepository(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.database.VoxTypeDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.voxtype.keyboard.database.entities.SettingsPreference>> getAllPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getPreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.database.entities.SettingsPreference> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getPreferenceValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String defaultValue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deletePreference(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteAllPreferences(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setBooleanSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, boolean value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getBooleanSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, boolean defaultValue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setIntSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, int value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getIntSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, int defaultValue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setFloatSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, float value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getFloatSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, float defaultValue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Float> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setStringSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String value, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getStringSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.lang.String defaultValue, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/voxtype/keyboard/repository/SettingsRepository$SettingsKeys;", "", "()V", "ANALYTICS_ENABLED", "", "AUTO_PUNCTUATION", "AUTO_SAVE_TRANSCRIPTIONS", "LANGUAGE_DETECTION", "PRIVACY_MODE", "RECORDING_QUALITY", "SOUND_ENABLED", "THEME_MODE", "VIBRATION_ENABLED", "VOICE_ENABLED", "app_debug"})
    public static final class SettingsKeys {
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String VOICE_ENABLED = "voice_enabled";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String AUTO_PUNCTUATION = "auto_punctuation";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String LANGUAGE_DETECTION = "language_detection";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String VIBRATION_ENABLED = "vibration_enabled";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String SOUND_ENABLED = "sound_enabled";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String THEME_MODE = "theme_mode";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String RECORDING_QUALITY = "recording_quality";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String AUTO_SAVE_TRANSCRIPTIONS = "auto_save_transcriptions";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String PRIVACY_MODE = "privacy_mode";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String ANALYTICS_ENABLED = "analytics_enabled";
        @org.jetbrains.annotations.NotNull
        public static final com.voxtype.keyboard.repository.SettingsRepository.SettingsKeys INSTANCE = null;
        
        private SettingsKeys() {
            super();
        }
    }
}