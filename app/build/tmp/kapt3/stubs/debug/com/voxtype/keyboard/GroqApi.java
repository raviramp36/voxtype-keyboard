package com.voxtype.keyboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006JR\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\f2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\nH\u00a7@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/voxtype/keyboard/GroqApi;", "", "complete", "Lcom/voxtype/keyboard/GroqResponse;", "request", "Lcom/voxtype/keyboard/GroqRequest;", "(Lcom/voxtype/keyboard/GroqRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transcribe", "Lcom/voxtype/keyboard/WhisperResponse;", "model", "Lokhttp3/RequestBody;", "file", "Lokhttp3/MultipartBody$Part;", "responseFormat", "language", "prompt", "temperature", "(Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface GroqApi {
    
    @retrofit2.http.POST(value = "openai/v1/chat/completions")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object complete(@retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.voxtype.keyboard.GroqRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.GroqResponse> $completion);
    
    @retrofit2.http.Multipart
    @retrofit2.http.POST(value = "openai/v1/audio/transcriptions")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object transcribe(@retrofit2.http.Part(value = "model")
    @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody model, @retrofit2.http.Part
    @org.jetbrains.annotations.NotNull
    okhttp3.MultipartBody.Part file, @retrofit2.http.Part(value = "response_format")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody responseFormat, @retrofit2.http.Part(value = "language")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody language, @retrofit2.http.Part(value = "prompt")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody prompt, @retrofit2.http.Part(value = "temperature")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody temperature, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.voxtype.keyboard.WhisperResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}