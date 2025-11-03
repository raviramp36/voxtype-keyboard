package com.voxtype.keyboard.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u001a\u001bB\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0015H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/voxtype/keyboard/adapter/TranscriptionHistoryAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/voxtype/keyboard/database/TranscriptionEntry;", "Lcom/voxtype/keyboard/adapter/TranscriptionHistoryAdapter$ViewHolder;", "onDeleteClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "copyTextToClipboard", "context", "Landroid/content/Context;", "text", "", "extractAppName", "packageName", "formatTimestamp", "timestamp", "Ljava/util/Date;", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "ViewHolder", "app_debug"})
public final class TranscriptionHistoryAdapter extends androidx.recyclerview.widget.ListAdapter<com.voxtype.keyboard.database.TranscriptionEntry, com.voxtype.keyboard.adapter.TranscriptionHistoryAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.voxtype.keyboard.database.TranscriptionEntry, kotlin.Unit> onDeleteClick = null;
    
    public TranscriptionHistoryAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.voxtype.keyboard.database.TranscriptionEntry, kotlin.Unit> onDeleteClick) {
        super(null);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.voxtype.keyboard.adapter.TranscriptionHistoryAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.adapter.TranscriptionHistoryAdapter.ViewHolder holder, int position) {
    }
    
    private final java.lang.String formatTimestamp(java.util.Date timestamp) {
        return null;
    }
    
    private final java.lang.String extractAppName(java.lang.String packageName) {
        return null;
    }
    
    private final void copyTextToClipboard(android.content.Context context, java.lang.String text) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/voxtype/keyboard/adapter/TranscriptionHistoryAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/voxtype/keyboard/database/TranscriptionEntry;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.voxtype.keyboard.database.TranscriptionEntry> {
        
        public DiffCallback() {
            super();
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.TranscriptionEntry oldItem, @org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.TranscriptionEntry newItem) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.TranscriptionEntry oldItem, @org.jetbrains.annotations.NotNull
        com.voxtype.keyboard.database.TranscriptionEntry newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0011\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lcom/voxtype/keyboard/adapter/TranscriptionHistoryAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "appName", "Landroid/widget/TextView;", "getAppName", "()Landroid/widget/TextView;", "copyButton", "Landroid/widget/ImageButton;", "getCopyButton", "()Landroid/widget/ImageButton;", "deleteButton", "getDeleteButton", "textMode", "getTextMode", "timestampText", "getTimestampText", "transcriptionText", "getTranscriptionText", "app_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView timestampText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView transcriptionText = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView appName = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView textMode = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton copyButton = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton deleteButton = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getTimestampText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getTranscriptionText() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getAppName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getTextMode() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageButton getCopyButton() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageButton getDeleteButton() {
            return null;
        }
    }
}