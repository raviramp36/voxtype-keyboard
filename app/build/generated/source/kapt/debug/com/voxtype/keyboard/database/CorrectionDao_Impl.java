package com.voxtype.keyboard.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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
public final class CorrectionDao_Impl implements CorrectionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CorrectionEntry> __insertionAdapterOfCorrectionEntry;

  private final DateConverters __dateConverters = new DateConverters();

  private final SharedSQLiteStatement __preparedStmtOfIncrementFrequency;

  public CorrectionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCorrectionEntry = new EntityInsertionAdapter<CorrectionEntry>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `corrections` (`id`,`timestamp`,`originalText`,`correctedText`,`context`,`frequency`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CorrectionEntry entity) {
        statement.bindLong(1, entity.getId());
        final Long _tmp = __dateConverters.dateToTimestamp(entity.getTimestamp());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, _tmp);
        }
        if (entity.getOriginalText() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getOriginalText());
        }
        if (entity.getCorrectedText() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCorrectedText());
        }
        if (entity.getContext() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getContext());
        }
        statement.bindLong(6, entity.getFrequency());
      }
    };
    this.__preparedStmtOfIncrementFrequency = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE corrections SET frequency = frequency + 1, timestamp = ? WHERE originalText = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final CorrectionEntry correction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCorrectionEntry.insert(correction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object incrementFrequency(final String original, final Date timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementFrequency.acquire();
        int _argIndex = 1;
        final Long _tmp = __dateConverters.dateToTimestamp(timestamp);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, _tmp);
        }
        _argIndex = 2;
        if (original == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, original);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfIncrementFrequency.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object findCorrection(final String text,
      final Continuation<? super CorrectionEntry> $completion) {
    final String _sql = "SELECT * FROM corrections WHERE originalText = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (text == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, text);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CorrectionEntry>() {
      @Override
      @Nullable
      public CorrectionEntry call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfOriginalText = CursorUtil.getColumnIndexOrThrow(_cursor, "originalText");
          final int _cursorIndexOfCorrectedText = CursorUtil.getColumnIndexOrThrow(_cursor, "correctedText");
          final int _cursorIndexOfContext = CursorUtil.getColumnIndexOrThrow(_cursor, "context");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final CorrectionEntry _result;
          if (_cursor.moveToFirst()) {
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
            final String _tmpOriginalText;
            if (_cursor.isNull(_cursorIndexOfOriginalText)) {
              _tmpOriginalText = null;
            } else {
              _tmpOriginalText = _cursor.getString(_cursorIndexOfOriginalText);
            }
            final String _tmpCorrectedText;
            if (_cursor.isNull(_cursorIndexOfCorrectedText)) {
              _tmpCorrectedText = null;
            } else {
              _tmpCorrectedText = _cursor.getString(_cursorIndexOfCorrectedText);
            }
            final String _tmpContext;
            if (_cursor.isNull(_cursorIndexOfContext)) {
              _tmpContext = null;
            } else {
              _tmpContext = _cursor.getString(_cursorIndexOfContext);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            _result = new CorrectionEntry(_tmpId,_tmpTimestamp,_tmpOriginalText,_tmpCorrectedText,_tmpContext,_tmpFrequency);
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
  public Object getMostFrequent(final int limit,
      final Continuation<? super List<CorrectionEntry>> $completion) {
    final String _sql = "SELECT * FROM corrections ORDER BY frequency DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CorrectionEntry>>() {
      @Override
      @NonNull
      public List<CorrectionEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfOriginalText = CursorUtil.getColumnIndexOrThrow(_cursor, "originalText");
          final int _cursorIndexOfCorrectedText = CursorUtil.getColumnIndexOrThrow(_cursor, "correctedText");
          final int _cursorIndexOfContext = CursorUtil.getColumnIndexOrThrow(_cursor, "context");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final List<CorrectionEntry> _result = new ArrayList<CorrectionEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CorrectionEntry _item;
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
            final String _tmpOriginalText;
            if (_cursor.isNull(_cursorIndexOfOriginalText)) {
              _tmpOriginalText = null;
            } else {
              _tmpOriginalText = _cursor.getString(_cursorIndexOfOriginalText);
            }
            final String _tmpCorrectedText;
            if (_cursor.isNull(_cursorIndexOfCorrectedText)) {
              _tmpCorrectedText = null;
            } else {
              _tmpCorrectedText = _cursor.getString(_cursorIndexOfCorrectedText);
            }
            final String _tmpContext;
            if (_cursor.isNull(_cursorIndexOfContext)) {
              _tmpContext = null;
            } else {
              _tmpContext = _cursor.getString(_cursorIndexOfContext);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            _item = new CorrectionEntry(_tmpId,_tmpTimestamp,_tmpOriginalText,_tmpCorrectedText,_tmpContext,_tmpFrequency);
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
  public Object findByWord(final String word,
      final Continuation<? super List<CorrectionEntry>> $completion) {
    final String _sql = "SELECT * FROM corrections WHERE originalText LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (word == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, word);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CorrectionEntry>>() {
      @Override
      @NonNull
      public List<CorrectionEntry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfOriginalText = CursorUtil.getColumnIndexOrThrow(_cursor, "originalText");
          final int _cursorIndexOfCorrectedText = CursorUtil.getColumnIndexOrThrow(_cursor, "correctedText");
          final int _cursorIndexOfContext = CursorUtil.getColumnIndexOrThrow(_cursor, "context");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final List<CorrectionEntry> _result = new ArrayList<CorrectionEntry>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CorrectionEntry _item;
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
            final String _tmpOriginalText;
            if (_cursor.isNull(_cursorIndexOfOriginalText)) {
              _tmpOriginalText = null;
            } else {
              _tmpOriginalText = _cursor.getString(_cursorIndexOfOriginalText);
            }
            final String _tmpCorrectedText;
            if (_cursor.isNull(_cursorIndexOfCorrectedText)) {
              _tmpCorrectedText = null;
            } else {
              _tmpCorrectedText = _cursor.getString(_cursorIndexOfCorrectedText);
            }
            final String _tmpContext;
            if (_cursor.isNull(_cursorIndexOfContext)) {
              _tmpContext = null;
            } else {
              _tmpContext = _cursor.getString(_cursorIndexOfContext);
            }
            final int _tmpFrequency;
            _tmpFrequency = _cursor.getInt(_cursorIndexOfFrequency);
            _item = new CorrectionEntry(_tmpId,_tmpTimestamp,_tmpOriginalText,_tmpCorrectedText,_tmpContext,_tmpFrequency);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
