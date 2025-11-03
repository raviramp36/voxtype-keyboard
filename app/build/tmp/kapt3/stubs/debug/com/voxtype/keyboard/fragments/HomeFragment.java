package com.voxtype.keyboard.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u0005H\u0002J.\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u0004H\u0002J\u0016\u0010-\u001a\u00020!2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u0010\u0010/\u001a\u00020!2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020!2\u0006\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u00020!H\u0002J&\u00106\u001a\u0004\u0018\u0001042\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u001a\u0010=\u001a\u00020!2\u0006\u00103\u001a\u0002042\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\b\u0010>\u001a\u00020!H\u0002J\b\u0010?\u001a\u00020!H\u0002J\b\u0010@\u001a\u00020!H\u0002J\u0010\u0010A\u001a\u00020!2\u0006\u0010#\u001a\u00020\u0005H\u0002J\b\u0010B\u001a\u00020!H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006C"}, d2 = {"Lcom/voxtype/keyboard/fragments/HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "allTranscriptions", "", "Lcom/voxtype/keyboard/database/TranscriptionEntry;", "analyticsManager", "Lcom/voxtype/keyboard/AnalyticsManager;", "clearHistoryButton", "Lcom/google/android/material/button/MaterialButton;", "database", "Lcom/voxtype/keyboard/database/VoxTypeDatabase;", "emptyHistoryLayout", "Landroid/widget/LinearLayout;", "greetingText", "Landroid/widget/TextView;", "historyAdapter", "Lcom/voxtype/keyboard/adapter/TranscriptionHistoryAdapter;", "isSearchVisible", "", "quickActionsCard", "Lcom/google/android/material/card/MaterialCardView;", "recentActivityRecycler", "Landroidx/recyclerview/widget/RecyclerView;", "searchHistoryButton", "searchInput", "Lcom/google/android/material/textfield/TextInputEditText;", "searchInputLayout", "Lcom/google/android/material/textfield/TextInputLayout;", "statsCarousel", "streakText", "transcriptionHistoryRecycler", "clearAllHistory", "", "deleteTranscription", "transcription", "displayStats", "todayStats", "Lcom/voxtype/keyboard/TodayStatsModel;", "weeklyStats", "Lcom/voxtype/keyboard/WeeklyStatsModel;", "streak", "", "topWords", "Lcom/voxtype/keyboard/database/WordFrequency;", "displayTranscriptionHistory", "transcriptions", "filterTranscriptions", "query", "", "initializeViews", "view", "Landroid/view/View;", "loadData", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "setupStatsCarousel", "setupTranscriptionHistory", "showClearHistoryDialog", "showDeleteConfirmationDialog", "toggleSearch", "app_debug"})
public final class HomeFragment extends androidx.fragment.app.Fragment {
    private com.voxtype.keyboard.AnalyticsManager analyticsManager;
    private com.voxtype.keyboard.database.VoxTypeDatabase database;
    private android.widget.TextView greetingText;
    private android.widget.TextView streakText;
    private androidx.recyclerview.widget.RecyclerView statsCarousel;
    private com.google.android.material.card.MaterialCardView quickActionsCard;
    private androidx.recyclerview.widget.RecyclerView recentActivityRecycler;
    private androidx.recyclerview.widget.RecyclerView transcriptionHistoryRecycler;
    private android.widget.LinearLayout emptyHistoryLayout;
    private com.google.android.material.button.MaterialButton searchHistoryButton;
    private com.google.android.material.button.MaterialButton clearHistoryButton;
    private com.google.android.material.textfield.TextInputLayout searchInputLayout;
    private com.google.android.material.textfield.TextInputEditText searchInput;
    private com.voxtype.keyboard.adapter.TranscriptionHistoryAdapter historyAdapter;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.voxtype.keyboard.database.TranscriptionEntry> allTranscriptions;
    private boolean isSearchVisible = false;
    
    public HomeFragment() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeViews(android.view.View view) {
    }
    
    private final void setupStatsCarousel() {
    }
    
    private final void setupTranscriptionHistory() {
    }
    
    private final void loadData() {
    }
    
    private final void displayStats(com.voxtype.keyboard.TodayStatsModel todayStats, com.voxtype.keyboard.WeeklyStatsModel weeklyStats, int streak, java.util.List<com.voxtype.keyboard.database.WordFrequency> topWords) {
    }
    
    private final void displayTranscriptionHistory(java.util.List<com.voxtype.keyboard.database.TranscriptionEntry> transcriptions) {
    }
    
    private final void toggleSearch() {
    }
    
    private final void filterTranscriptions(java.lang.String query) {
    }
    
    private final void showDeleteConfirmationDialog(com.voxtype.keyboard.database.TranscriptionEntry transcription) {
    }
    
    private final void showClearHistoryDialog() {
    }
    
    private final void deleteTranscription(com.voxtype.keyboard.database.TranscriptionEntry transcription) {
    }
    
    private final void clearAllHistory() {
    }
}