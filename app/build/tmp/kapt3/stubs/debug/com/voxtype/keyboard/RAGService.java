package com.voxtype.keyboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/voxtype/keyboard/RAGService;", "", "processText", "Lcom/voxtype/keyboard/TranscriptionResponse;", "apiKey", "", "request", "Lcom/voxtype/keyboard/TranscriptionRequest;", "(Ljava/lang/String;Lcom/voxtype/keyboard/TranscriptionRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface RAGService {
    
    @retrofit2.http.POST(value = "process")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object processText(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull
    java.lang.String apiKey, @retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.TranscriptionRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.TranscriptionResponse> $completion);
}