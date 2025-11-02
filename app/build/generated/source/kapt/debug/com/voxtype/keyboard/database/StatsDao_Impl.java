package com.voxtype.keyboard.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
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
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class StatsDao_Impl implements StatsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DailyStats> __insertionAdapterOfDailyStats;

  public StatsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDailyStats = new EntityInsertionAdapter<DailyStats>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `daily_stats` (`date`,`totalWords`,`totalCharacters`,`totalTranscriptions`,`totalDuration`,`correctionsCount`,`appsUsed`,`peakHour`,`averageWordsPerTranscription`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyStats entity) {
        if (entity.getDate() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getDate());
        }
        statement.bindLong(2, entity.getTotalWords());
        statement.bindLong(3, entity.getTotalCharacters());
        statement.bindLong(4, entity.getTotalTranscriptions());
        statement.bindDouble(5, entity.getTotalDuration());
        statement.bindLong(6, entity.getCorrectionsCount());
        if (entity.getAppsUsed() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getAppsUsed());
        }
        if (entity.getPeakHour() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getPeakHour());
        }
        statement.bindDouble(9, entity.getAverageWordsPerTranscription());
      }
    };
  }

  @Override
  public Object insertOrUpdate(final DailyStats stats,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDailyStats.insert(stats);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getStats(final String date, final Continuation<? super DailyStats> $completion) {
    final String _sql = "SELECT * FROM daily_stats WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyStats>() {
      @Override
      @Nullable
      public DailyStats call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalWords = CursorUtil.getColumnIndexOrThrow(_cursor, "totalWords");
          final int _cursorIndexOfTotalCharacters = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCharacters");
          final int _cursorIndexOfTotalTranscriptions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTranscriptions");
          final int _cursorIndexOfTotalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDuration");
          final int _cursorIndexOfCorrectionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctionsCount");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peakHour");
          final int _cursorIndexOfAverageWordsPerTranscription = CursorUtil.getColumnIndexOrThrow(_cursor, "averageWordsPerTranscription");
          final DailyStats _result;
          if (_cursor.moveToFirst()) {
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpTotalWords;
            _tmpTotalWords = _cursor.getInt(_cursorIndexOfTotalWords);
            final int _tmpTotalCharacters;
            _tmpTotalCharacters = _cursor.getInt(_cursorIndexOfTotalCharacters);
            final int _tmpTotalTranscriptions;
            _tmpTotalTranscriptions = _cursor.getInt(_cursorIndexOfTotalTranscriptions);
            final float _tmpTotalDuration;
            _tmpTotalDuration = _cursor.getFloat(_cursorIndexOfTotalDuration);
            final int _tmpCorrectionsCount;
            _tmpCorrectionsCount = _cursor.getInt(_cursorIndexOfCorrectionsCount);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final float _tmpAverageWordsPerTranscription;
            _tmpAverageWordsPerTranscription = _cursor.getFloat(_cursorIndexOfAverageWordsPerTranscription);
            _result = new DailyStats(_tmpDate,_tmpTotalWords,_tmpTotalCharacters,_tmpTotalTranscriptions,_tmpTotalDuration,_tmpCorrectionsCount,_tmpAppsUsed,_tmpPeakHour,_tmpAverageWordsPerTranscription);
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
  public Object getStatsSince(final String startDate,
      final Continuation<? super List<DailyStats>> $completion) {
    final String _sql = "SELECT * FROM daily_stats WHERE date >= ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyStats>>() {
      @Override
      @NonNull
      public List<DailyStats> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalWords = CursorUtil.getColumnIndexOrThrow(_cursor, "totalWords");
          final int _cursorIndexOfTotalCharacters = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCharacters");
          final int _cursorIndexOfTotalTranscriptions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTranscriptions");
          final int _cursorIndexOfTotalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDuration");
          final int _cursorIndexOfCorrectionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctionsCount");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peakHour");
          final int _cursorIndexOfAverageWordsPerTranscription = CursorUtil.getColumnIndexOrThrow(_cursor, "averageWordsPerTranscription");
          final List<DailyStats> _result = new ArrayList<DailyStats>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyStats _item;
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpTotalWords;
            _tmpTotalWords = _cursor.getInt(_cursorIndexOfTotalWords);
            final int _tmpTotalCharacters;
            _tmpTotalCharacters = _cursor.getInt(_cursorIndexOfTotalCharacters);
            final int _tmpTotalTranscriptions;
            _tmpTotalTranscriptions = _cursor.getInt(_cursorIndexOfTotalTranscriptions);
            final float _tmpTotalDuration;
            _tmpTotalDuration = _cursor.getFloat(_cursorIndexOfTotalDuration);
            final int _tmpCorrectionsCount;
            _tmpCorrectionsCount = _cursor.getInt(_cursorIndexOfCorrectionsCount);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final float _tmpAverageWordsPerTranscription;
            _tmpAverageWordsPerTranscription = _cursor.getFloat(_cursorIndexOfAverageWordsPerTranscription);
            _item = new DailyStats(_tmpDate,_tmpTotalWords,_tmpTotalCharacters,_tmpTotalTranscriptions,_tmpTotalDuration,_tmpCorrectionsCount,_tmpAppsUsed,_tmpPeakHour,_tmpAverageWordsPerTranscription);
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
  public Object getTotalWordsSince(final String startDate,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT SUM(totalWords) as total FROM daily_stats WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
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

  @Override
  public Object getLastWeekStats(final Continuation<? super List<DailyStats>> $completion) {
    final String _sql = "SELECT * FROM daily_stats ORDER BY date DESC LIMIT 7";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyStats>>() {
      @Override
      @NonNull
      public List<DailyStats> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalWords = CursorUtil.getColumnIndexOrThrow(_cursor, "totalWords");
          final int _cursorIndexOfTotalCharacters = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCharacters");
          final int _cursorIndexOfTotalTranscriptions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTranscriptions");
          final int _cursorIndexOfTotalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDuration");
          final int _cursorIndexOfCorrectionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctionsCount");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peakHour");
          final int _cursorIndexOfAverageWordsPerTranscription = CursorUtil.getColumnIndexOrThrow(_cursor, "averageWordsPerTranscription");
          final List<DailyStats> _result = new ArrayList<DailyStats>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyStats _item;
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpTotalWords;
            _tmpTotalWords = _cursor.getInt(_cursorIndexOfTotalWords);
            final int _tmpTotalCharacters;
            _tmpTotalCharacters = _cursor.getInt(_cursorIndexOfTotalCharacters);
            final int _tmpTotalTranscriptions;
            _tmpTotalTranscriptions = _cursor.getInt(_cursorIndexOfTotalTranscriptions);
            final float _tmpTotalDuration;
            _tmpTotalDuration = _cursor.getFloat(_cursorIndexOfTotalDuration);
            final int _tmpCorrectionsCount;
            _tmpCorrectionsCount = _cursor.getInt(_cursorIndexOfCorrectionsCount);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final float _tmpAverageWordsPerTranscription;
            _tmpAverageWordsPerTranscription = _cursor.getFloat(_cursorIndexOfAverageWordsPerTranscription);
            _item = new DailyStats(_tmpDate,_tmpTotalWords,_tmpTotalCharacters,_tmpTotalTranscriptions,_tmpTotalDuration,_tmpCorrectionsCount,_tmpAppsUsed,_tmpPeakHour,_tmpAverageWordsPerTranscription);
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
  public Object getRecentStats(final int days,
      final Continuation<? super List<DailyStats>> $completion) {
    final String _sql = "SELECT * FROM daily_stats ORDER BY date DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, days);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DailyStats>>() {
      @Override
      @NonNull
      public List<DailyStats> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTotalWords = CursorUtil.getColumnIndexOrThrow(_cursor, "totalWords");
          final int _cursorIndexOfTotalCharacters = CursorUtil.getColumnIndexOrThrow(_cursor, "totalCharacters");
          final int _cursorIndexOfTotalTranscriptions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTranscriptions");
          final int _cursorIndexOfTotalDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "totalDuration");
          final int _cursorIndexOfCorrectionsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "correctionsCount");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfPeakHour = CursorUtil.getColumnIndexOrThrow(_cursor, "peakHour");
          final int _cursorIndexOfAverageWordsPerTranscription = CursorUtil.getColumnIndexOrThrow(_cursor, "averageWordsPerTranscription");
          final List<DailyStats> _result = new ArrayList<DailyStats>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyStats _item;
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpTotalWords;
            _tmpTotalWords = _cursor.getInt(_cursorIndexOfTotalWords);
            final int _tmpTotalCharacters;
            _tmpTotalCharacters = _cursor.getInt(_cursorIndexOfTotalCharacters);
            final int _tmpTotalTranscriptions;
            _tmpTotalTranscriptions = _cursor.getInt(_cursorIndexOfTotalTranscriptions);
            final float _tmpTotalDuration;
            _tmpTotalDuration = _cursor.getFloat(_cursorIndexOfTotalDuration);
            final int _tmpCorrectionsCount;
            _tmpCorrectionsCount = _cursor.getInt(_cursorIndexOfCorrectionsCount);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final Integer _tmpPeakHour;
            if (_cursor.isNull(_cursorIndexOfPeakHour)) {
              _tmpPeakHour = null;
            } else {
              _tmpPeakHour = _cursor.getInt(_cursorIndexOfPeakHour);
            }
            final float _tmpAverageWordsPerTranscription;
            _tmpAverageWordsPerTranscription = _cursor.getFloat(_cursorIndexOfAverageWordsPerTranscription);
            _item = new DailyStats(_tmpDate,_tmpTotalWords,_tmpTotalCharacters,_tmpTotalTranscriptions,_tmpTotalDuration,_tmpCorrectionsCount,_tmpAppsUsed,_tmpPeakHour,_tmpAverageWordsPerTranscription);
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
  public Object getAverageWordsSince(final String startDate,
      final Continuation<? super Float> $completion) {
    final String _sql = "SELECT AVG(totalWords) FROM daily_stats WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
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
