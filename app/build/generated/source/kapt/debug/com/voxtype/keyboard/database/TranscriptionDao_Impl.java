package com.voxtype.keyboard.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TranscriptionDao_Impl implements TranscriptionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TranscriptionEntry> __insertionAdapterOfTranscriptionEntry;

  private final DateConverters __dateConverters = new DateConverters();

  private final EntityDeletionOrUpdateAdapter<TranscriptionEntry> __updateAdapterOfTranscriptionEntry;

  public TranscriptionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTranscriptionEntry = new EntityInsertionAdapter<TranscriptionEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `transcriptions` (`id`,`timestamp`,`originalAudio`,`rawTranscription`,`finalText`,`userEditedText`,`wordCount`,`characterCount`,`duration`,`appPackage`,`textMode`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TranscriptionEntry entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getTimestamp());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        if (entity.getOriginalAudio() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getOriginalAudio());
        }
        if (entity.getRawTranscription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRawTranscription());
        }
        if (entity.getFinalText() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFinalText());
        }
        if (entity.getUserEditedText() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getUserEditedText());
        }
        statement.bindLong(7, entity.getWordCount());
        statement.bindLong(8, entity.getCharacterCount());
        if (entity.getDuration() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getDuration());
        }
        if (entity.getAppPackage() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getAppPackage());
        }
        if (entity.getTextMode() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getTextMode());
        }
      }
    };
    this.__updateAdapterOfTranscriptionEntry = new EntityDeletionOrUpdateAdapter<TranscriptionEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transcriptions` SET `id` = ?,`timestamp` = ?,`originalAudio` = ?,`rawTranscription` = ?,`finalText` = ?,`userEditedText` = ?,`wordCount` = ?,`characterCount` = ?,`duration` = ?,`appPackage` = ?,`textMode` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TranscriptionEntry entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getTimestamp());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        if (entity.getOriginalAudio() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getOriginalAudio());
        }
        if (entity.getRawTranscription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRawTranscription());
        }
        if (entity.getFinalText() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getFinalText());
        }
        if (entity.getUserEditedText() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getUserEditedText());
        }
        statement.bindLong(7, entity.getWordCount());
        statement.bindLong(8, entity.getCharacterCount());
        if (entity.getDuration() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getDuration());
        }
        if (entity.getAppPackage() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getAppPackage());
        }
        if (entity.getTextMode() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getTextMode());
        }
        statement.bindLong(12, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final TranscriptionEntry transcription,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTranscriptionEntry.insertAndReturnId(transcription);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final TranscriptionEntry transcription,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTranscriptionEntry.handle(transcription);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRecent(final int limit,
      final Continuation<? super List<TranscriptionEntry>> $completion) {
    final String _sql = "SELECT * FROM transcriptions ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TranscriptionEntry>>() {
      @Override
      @NonNull
      public List<TranscriptionEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfOriginalAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAudio");
          final int _cursorIndexOfRawTranscription = CursorUtil.getColumnIndexOrThrow(_cursor, "rawTranscription");
          final int _cursorIndexOfFinalText = CursorUtil.getColumnIndexOrThrow(_cursor, "finalText");
          final int _cursorIndexOfUserEditedText = CursorUtil.getColumnIndexOrThrow(_cursor, "userEditedText");
          final int _cursorIndexOfWordCount = CursorUtil.getColumnIndexOrThrow(_cursor, "wordCount");
          final int _cursorIndexOfCharacterCount = CursorUtil.getColumnIndexOrThrow(_cursor, "characterCount");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfAppPackage = CursorUtil.getColumnIndexOrThrow(_cursor, "appPackage");
          final int _cursorIndexOfTextMode = CursorUtil.getColumnIndexOrThrow(_cursor, "textMode");
          final List<TranscriptionEntry> _result = new ArrayList<TranscriptionEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TranscriptionEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Date _tmpTimestamp;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTimestamp);
            }
            _tmpTimestamp = __dateConverters.fromTimestamp(_tmp);
            final String _tmpOriginalAudio;
            if (_cursor.isNull(_cursorIndexOfOriginalAudio)) {
              _tmpOriginalAudio = null;
            } else {
              _tmpOriginalAudio = _cursor.getString(_cursorIndexOfOriginalAudio);
            }
            final String _tmpRawTranscription;
            if (_cursor.isNull(_cursorIndexOfRawTranscription)) {
              _tmpRawTranscription = null;
            } else {
              _tmpRawTranscription = _cursor.getString(_cursorIndexOfRawTranscription);
            }
            final String _tmpFinalText;
            if (_cursor.isNull(_cursorIndexOfFinalText)) {
              _tmpFinalText = null;
            } else {
              _tmpFinalText = _cursor.getString(_cursorIndexOfFinalText);
            }
            final String _tmpUserEditedText;
            if (_cursor.isNull(_cursorIndexOfUserEditedText)) {
              _tmpUserEditedText = null;
            } else {
              _tmpUserEditedText = _cursor.getString(_cursorIndexOfUserEditedText);
            }
            final int _tmpWordCount;
            _tmpWordCount = _cursor.getInt(_cursorIndexOfWordCount);
            final int _tmpCharacterCount;
            _tmpCharacterCount = _cursor.getInt(_cursorIndexOfCharacterCount);
            final Float _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getFloat(_cursorIndexOfDuration);
            }
            final String _tmpAppPackage;
            if (_cursor.isNull(_cursorIndexOfAppPackage)) {
              _tmpAppPackage = null;
            } else {
              _tmpAppPackage = _cursor.getString(_cursorIndexOfAppPackage);
            }
            final String _tmpTextMode;
            if (_cursor.isNull(_cursorIndexOfTextMode)) {
              _tmpTextMode = null;
            } else {
              _tmpTextMode = _cursor.getString(_cursorIndexOfTextMode);
            }
            _item = new TranscriptionEntry(_tmpId,_tmpTimestamp,_tmpOriginalAudio,_tmpRawTranscription,_tmpFinalText,_tmpUserEditedText,_tmpWordCount,_tmpCharacterCount,_tmpDuration,_tmpAppPackage,_tmpTextMode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getToday(final Continuation<? super List<TranscriptionEntry>> $completion) {
    final String _sql = "SELECT * FROM transcriptions WHERE date(timestamp/1000, 'unixepoch') = date('now') ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TranscriptionEntry>>() {
      @Override
      @NonNull
      public List<TranscriptionEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfOriginalAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAudio");
          final int _cursorIndexOfRawTranscription = CursorUtil.getColumnIndexOrThrow(_cursor, "rawTranscription");
          final int _cursorIndexOfFinalText = CursorUtil.getColumnIndexOrThrow(_cursor, "finalText");
          final int _cursorIndexOfUserEditedText = CursorUtil.getColumnIndexOrThrow(_cursor, "userEditedText");
          final int _cursorIndexOfWordCount = CursorUtil.getColumnIndexOrThrow(_cursor, "wordCount");
          final int _cursorIndexOfCharacterCount = CursorUtil.getColumnIndexOrThrow(_cursor, "characterCount");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfAppPackage = CursorUtil.getColumnIndexOrThrow(_cursor, "appPackage");
          final int _cursorIndexOfTextMode = CursorUtil.getColumnIndexOrThrow(_cursor, "textMode");
          final List<TranscriptionEntry> _result = new ArrayList<TranscriptionEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TranscriptionEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Date _tmpTimestamp;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTimestamp);
            }
            _tmpTimestamp = __dateConverters.fromTimestamp(_tmp);
            final String _tmpOriginalAudio;
            if (_cursor.isNull(_cursorIndexOfOriginalAudio)) {
              _tmpOriginalAudio = null;
            } else {
              _tmpOriginalAudio = _cursor.getString(_cursorIndexOfOriginalAudio);
            }
            final String _tmpRawTranscription;
            if (_cursor.isNull(_cursorIndexOfRawTranscription)) {
              _tmpRawTranscription = null;
            } else {
              _tmpRawTranscription = _cursor.getString(_cursorIndexOfRawTranscription);
            }
            final String _tmpFinalText;
            if (_cursor.isNull(_cursorIndexOfFinalText)) {
              _tmpFinalText = null;
            } else {
              _tmpFinalText = _cursor.getString(_cursorIndexOfFinalText);
            }
            final String _tmpUserEditedText;
            if (_cursor.isNull(_cursorIndexOfUserEditedText)) {
              _tmpUserEditedText = null;
            } else {
              _tmpUserEditedText = _cursor.getString(_cursorIndexOfUserEditedText);
            }
            final int _tmpWordCount;
            _tmpWordCount = _cursor.getInt(_cursorIndexOfWordCount);
            final int _tmpCharacterCount;
            _tmpCharacterCount = _cursor.getInt(_cursorIndexOfCharacterCount);
            final Float _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getFloat(_cursorIndexOfDuration);
            }
            final String _tmpAppPackage;
            if (_cursor.isNull(_cursorIndexOfAppPackage)) {
              _tmpAppPackage = null;
            } else {
              _tmpAppPackage = _cursor.getString(_cursorIndexOfAppPackage);
            }
            final String _tmpTextMode;
            if (_cursor.isNull(_cursorIndexOfTextMode)) {
              _tmpTextMode = null;
            } else {
              _tmpTextMode = _cursor.getString(_cursorIndexOfTextMode);
            }
            _item = new TranscriptionEntry(_tmpId,_tmpTimestamp,_tmpOriginalAudio,_tmpRawTranscription,_tmpFinalText,_tmpUserEditedText,_tmpWordCount,_tmpCharacterCount,_tmpDuration,_tmpAppPackage,_tmpTextMode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLastWeek(final Continuation<? super List<TranscriptionEntry>> $completion) {
    final String _sql = "SELECT * FROM transcriptions WHERE date(timestamp/1000, 'unixepoch') >= date('now', '-7 days') ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TranscriptionEntry>>() {
      @Override
      @NonNull
      public List<TranscriptionEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfOriginalAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "originalAudio");
          final int _cursorIndexOfRawTranscription = CursorUtil.getColumnIndexOrThrow(_cursor, "rawTranscription");
          final int _cursorIndexOfFinalText = CursorUtil.getColumnIndexOrThrow(_cursor, "finalText");
          final int _cursorIndexOfUserEditedText = CursorUtil.getColumnIndexOrThrow(_cursor, "userEditedText");
          final int _cursorIndexOfWordCount = CursorUtil.getColumnIndexOrThrow(_cursor, "wordCount");
          final int _cursorIndexOfCharacterCount = CursorUtil.getColumnIndexOrThrow(_cursor, "characterCount");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfAppPackage = CursorUtil.getColumnIndexOrThrow(_cursor, "appPackage");
          final int _cursorIndexOfTextMode = CursorUtil.getColumnIndexOrThrow(_cursor, "textMode");
          final List<TranscriptionEntry> _result = new ArrayList<TranscriptionEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TranscriptionEntry _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Date _tmpTimestamp;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTimestamp);
            }
            _tmpTimestamp = __dateConverters.fromTimestamp(_tmp);
            final String _tmpOriginalAudio;
            if (_cursor.isNull(_cursorIndexOfOriginalAudio)) {
              _tmpOriginalAudio = null;
            } else {
              _tmpOriginalAudio = _cursor.getString(_cursorIndexOfOriginalAudio);
            }
            final String _tmpRawTranscription;
            if (_cursor.isNull(_cursorIndexOfRawTranscription)) {
              _tmpRawTranscription = null;
            } else {
              _tmpRawTranscription = _cursor.getString(_cursorIndexOfRawTranscription);
            }
            final String _tmpFinalText;
            if (_cursor.isNull(_cursorIndexOfFinalText)) {
              _tmpFinalText = null;
            } else {
              _tmpFinalText = _cursor.getString(_cursorIndexOfFinalText);
            }
            final String _tmpUserEditedText;
            if (_cursor.isNull(_cursorIndexOfUserEditedText)) {
              _tmpUserEditedText = null;
            } else {
              _tmpUserEditedText = _cursor.getString(_cursorIndexOfUserEditedText);
            }
            final int _tmpWordCount;
            _tmpWordCount = _cursor.getInt(_cursorIndexOfWordCount);
            final int _tmpCharacterCount;
            _tmpCharacterCount = _cursor.getInt(_cursorIndexOfCharacterCount);
            final Float _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getFloat(_cursorIndexOfDuration);
            }
            final String _tmpAppPackage;
            if (_cursor.isNull(_cursorIndexOfAppPackage)) {
              _tmpAppPackage = null;
            } else {
              _tmpAppPackage = _cursor.getString(_cursorIndexOfAppPackage);
            }
            final String _tmpTextMode;
            if (_cursor.isNull(_cursorIndexOfTextMode)) {
              _tmpTextMode = null;
            } else {
              _tmpTextMode = _cursor.getString(_cursorIndexOfTextMode);
            }
            _item = new TranscriptionEntry(_tmpId,_tmpTimestamp,_tmpOriginalAudio,_tmpRawTranscription,_tmpFinalText,_tmpUserEditedText,_tmpWordCount,_tmpCharacterCount,_tmpDuration,_tmpAppPackage,_tmpTextMode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTodayCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM transcriptions WHERE date(timestamp/1000, 'unixepoch') = date('now')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTodayWordCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT SUM(wordCount) FROM transcriptions WHERE date(timestamp/1000, 'unixepoch') = date('now')";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
