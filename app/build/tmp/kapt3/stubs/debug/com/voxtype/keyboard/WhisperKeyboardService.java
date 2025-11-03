package com.voxtype.keyboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001QB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020&H\u0002J\b\u0010\'\u001a\u00020\u0014H\u0002J\b\u0010(\u001a\u00020\"H\u0002J\b\u0010)\u001a\u00020\u000eH\u0002J\b\u0010*\u001a\u00020\"H\u0002J\b\u0010+\u001a\u00020\"H\u0016J\b\u0010,\u001a\u00020\u0012H\u0016J\b\u0010-\u001a\u00020\"H\u0016J\u0010\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u000eH\u0016J\u001a\u00100\u001a\u00020\"2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020\u000eH\u0016J8\u00104\u001a\u00020\"2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002062\u0006\u00109\u001a\u0002062\u0006\u0010:\u001a\u0002062\u0006\u0010;\u001a\u000206H\u0016J\u0010\u0010<\u001a\u00020\"2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010=\u001a\u00020\"H\u0002J\u0010\u0010>\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020$H\u0002J\u0010\u0010@\u001a\u00020\"2\u0006\u0010A\u001a\u00020\u0014H\u0002J\b\u0010B\u001a\u00020\"H\u0002J\b\u0010C\u001a\u00020\"H\u0002J\b\u0010D\u001a\u00020\"H\u0002J\b\u0010E\u001a\u00020\"H\u0002J\b\u0010F\u001a\u00020\"H\u0002J\b\u0010G\u001a\u00020\"H\u0002J\b\u0010H\u001a\u00020\"H\u0002J\u0010\u0010I\u001a\u00020\"2\u0006\u0010J\u001a\u00020KH\u0002J\b\u0010L\u001a\u00020\"H\u0002J\u001e\u0010M\u001a\u00020\"2\b\b\u0002\u0010N\u001a\u00020\u001c2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010PH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006R"}, d2 = {"Lcom/voxtype/keyboard/WhisperKeyboardService;", "Landroid/inputmethodservice/InputMethodService;", "()V", "advancedSettings", "Lcom/voxtype/keyboard/AdvancedSettingsManager;", "analyticsManager", "Lcom/voxtype/keyboard/AnalyticsManager;", "audioFile", "Ljava/io/File;", "groqProcessor", "Lcom/voxtype/keyboard/GroqProcessor;", "handler", "Landroid/os/Handler;", "isPrivacyMode", "", "isRecording", "isWhisperMode", "keyboardView", "Landroid/view/View;", "lastTranscribedText", "", "mainScope", "Lkotlinx/coroutines/CoroutineScope;", "mediaRecorder", "Landroid/media/MediaRecorder;", "progressBar", "Landroid/widget/ProgressBar;", "recordingStartTime", "", "selectedLanguage", "statusText", "Landroid/widget/TextView;", "voiceButton", "cycleLanguage", "", "detectTextMode", "Lcom/voxtype/keyboard/GroqProcessor$TextMode;", "getCurrentLocale", "Ljava/util/Locale;", "getSelectedLanguage", "handleBackspace", "hasRecordPermission", "loadFeatureSettings", "onCreate", "onCreateInputView", "onDestroy", "onFinishInputView", "finishingInput", "onStartInputView", "info", "Landroid/view/inputmethod/EditorInfo;", "restarting", "onUpdateSelection", "oldSelStart", "", "oldSelEnd", "newSelStart", "newSelEnd", "candidatesStart", "candidatesEnd", "processAudioWithWhisper", "setupQuickActions", "shouldEnhanceText", "mode", "showStatus", "message", "startContinuousRecording", "startRecording", "stopRecording", "switchToNextSubtype", "toggleRecording", "toggleWhisperMode", "updateLanguageButton", "updateUI", "state", "Lcom/voxtype/keyboard/WhisperKeyboardService$RecordingState;", "updateWhisperModeUI", "vibrate", "duration", "pattern", "", "RecordingState", "app_debug"})
public final class WhisperKeyboardService extends android.inputmethodservice.InputMethodService {
    private android.view.View keyboardView;
    private android.view.View voiceButton;
    private android.widget.TextView statusText;
    private android.widget.ProgressBar progressBar;
    private com.voxtype.keyboard.GroqProcessor groqProcessor;
    private com.voxtype.keyboard.AnalyticsManager analyticsManager;
    private com.voxtype.keyboard.AdvancedSettingsManager advancedSettings;
    @org.jetbrains.annotations.Nullable
    private android.media.MediaRecorder mediaRecorder;
    @org.jetbrains.annotations.Nullable
    private java.io.File audioFile;
    private boolean isRecording = false;
    private long recordingStartTime = 0L;
    @org.jetbrains.annotations.NotNull
    private java.lang.String lastTranscribedText = "";
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.CoroutineScope mainScope = null;
    @org.jetbrains.annotations.NotNull
    private final android.os.Handler handler = null;
    private boolean isWhisperMode = false;
    @org.jetbrains.annotations.NotNull
    private java.lang.String selectedLanguage = "auto";
    private boolean isPrivacyMode = false;
    
    public WhisperKeyboardService() {
        super();
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.view.View onCreateInputView() {
        return null;
    }
    
    @java.lang.Override
    public void onStartInputView(@org.jetbrains.annotations.Nullable
    android.view.inputmethod.EditorInfo info, boolean restarting) {
    }
    
    @java.lang.Override
    public void onFinishInputView(boolean finishingInput) {
    }
    
    private final void setupQuickActions() {
    }
    
    private final void switchToNextSubtype() {
    }
    
    private final void handleBackspace() {
    }
    
    private final void toggleRecording() {
    }
    
    private final void startRecording() {
    }
    
    private final void stopRecording() {
    }
    
    private final void startContinuousRecording() {
    }
    
    private final void processAudioWithWhisper(java.io.File audioFile) {
    }
    
    private final boolean shouldEnhanceText(com.voxtype.keyboard.GroqProcessor.TextMode mode) {
        return false;
    }
    
    private final void updateUI(com.voxtype.keyboard.WhisperKeyboardService.RecordingState state) {
    }
    
    private final void showStatus(java.lang.String message) {
    }
    
    private final boolean hasRecordPermission() {
        return false;
    }
    
    private final void vibrate(long duration, long[] pattern) {
    }
    
    @java.lang.Override
    public void onUpdateSelection(int oldSelStart, int oldSelEnd, int newSelStart, int newSelEnd, int candidatesStart, int candidatesEnd) {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    private final java.util.Locale getCurrentLocale() {
        return null;
    }
    
    private final java.lang.String getSelectedLanguage() {
        return null;
    }
    
    private final void loadFeatureSettings() {
    }
    
    private final void toggleWhisperMode() {
    }
    
    private final void updateWhisperModeUI() {
    }
    
    private final void cycleLanguage() {
    }
    
    private final void updateLanguageButton() {
    }
    
    private final com.voxtype.keyboard.GroqProcessor.TextMode detectTextMode() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/voxtype/keyboard/WhisperKeyboardService$RecordingState;", "", "(Ljava/lang/String;I)V", "IDLE", "RECORDING", "PROCESSING", "app_debug"})
    public static enum RecordingState {
        /*public static final*/ IDLE /* = new IDLE() */,
        /*public static final*/ RECORDING /* = new RECORDING() */,
        /*public static final*/ PROCESSING /* = new PROCESSING() */;
        
        RecordingState() {
        }
        
        @org.jetbrains.annotations.NotNull
        public static kotlin.enums.EnumEntries<com.voxtype.keyboard.WhisperKeyboardService.RecordingState> getEntries() {
            return null;
        }
    }
}