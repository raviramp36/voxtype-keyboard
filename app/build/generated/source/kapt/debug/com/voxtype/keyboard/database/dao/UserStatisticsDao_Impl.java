package com.voxtype.keyboard.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.voxtype.keyboard.database.entities.UserStatistics;
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
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserStatisticsDao_Impl implements UserStatisticsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserStatistics> __insertionAdapterOfUserStatistics;

  private final EntityDeletionOrUpdateAdapter<UserStatistics> __deletionAdapterOfUserStatistics;

  private final EntityDeletionOrUpdateAdapter<UserStatistics> __updateAdapterOfUserStatistics;

  private final SharedSQLiteStatement __preparedStmtOfDeleteStatisticsBeforeDate;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllStatistics;

  public UserStatisticsDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserStatistics = new EntityInsertionAdapter<UserStatistics>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_statistics` (`id`,`date`,`wordsTyped`,`wpm`,`appsUsed`,`languagesUsed`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserStatistics entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getDate() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDate());
        }
        statement.bindLong(3, entity.getWordsTyped());
        statement.bindDouble(4, entity.getWpm());
        if (entity.getAppsUsed() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAppsUsed());
        }
        if (entity.getLanguagesUsed() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getLanguagesUsed());
        }
      }
    };
    this.__deletionAdapterOfUserStatistics = new EntityDeletionOrUpdateAdapter<UserStatistics>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `user_statistics` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserStatistics entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfUserStatistics = new EntityDeletionOrUpdateAdapter<UserStatistics>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_statistics` SET `id` = ?,`date` = ?,`wordsTyped` = ?,`wpm` = ?,`appsUsed` = ?,`languagesUsed` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserStatistics entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getDate() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDate());
        }
        statement.bindLong(3, entity.getWordsTyped());
        statement.bindDouble(4, entity.getWpm());
        if (entity.getAppsUsed() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAppsUsed());
        }
        if (entity.getLanguagesUsed() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getLanguagesUsed());
        }
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteStatisticsBeforeDate = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM user_statistics WHERE date < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllStatistics = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM user_statistics";
        return _query;
      }
    };
  }

  @Override
  public Object insertStatistics(final UserStatistics statistics,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserStatistics.insertAndReturnId(statistics);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteStatistics(final UserStatistics statistics,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUserStatistics.handle(statistics);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateStatistics(final UserStatistics statistics,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserStatistics.handle(statistics);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteStatisticsBeforeDate(final String beforeDate,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteStatisticsBeforeDate.acquire();
        int _argIndex = 1;
        if (beforeDate == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, beforeDate);
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
          __preparedStmtOfDeleteStatisticsBeforeDate.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllStatistics(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllStatistics.acquire();
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
          __preparedStmtOfDeleteAllStatistics.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getStatisticsById(final long id,
      final Continuation<? super UserStatistics> $completion) {
    final String _sql = "SELECT * FROM user_statistics WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserStatistics>() {
      @Override
      @Nullable
      public UserStatistics call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWordsTyped = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsTyped");
          final int _cursorIndexOfWpm = CursorUtil.getColumnIndexOrThrow(_cursor, "wpm");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfLanguagesUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "languagesUsed");
          final UserStatistics _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpWordsTyped;
            _tmpWordsTyped = _cursor.getInt(_cursorIndexOfWordsTyped);
            final float _tmpWpm;
            _tmpWpm = _cursor.getFloat(_cursorIndexOfWpm);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final String _tmpLanguagesUsed;
            if (_cursor.isNull(_cursorIndexOfLanguagesUsed)) {
              _tmpLanguagesUsed = null;
            } else {
              _tmpLanguagesUsed = _cursor.getString(_cursorIndexOfLanguagesUsed);
            }
            _result = new UserStatistics(_tmpId,_tmpDate,_tmpWordsTyped,_tmpWpm,_tmpAppsUsed,_tmpLanguagesUsed);
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
  public Object getStatisticsByDate(final String date,
      final Continuation<? super UserStatistics> $completion) {
    final String _sql = "SELECT * FROM user_statistics WHERE date = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserStatistics>() {
      @Override
      @Nullable
      public UserStatistics call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWordsTyped = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsTyped");
          final int _cursorIndexOfWpm = CursorUtil.getColumnIndexOrThrow(_cursor, "wpm");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfLanguagesUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "languagesUsed");
          final UserStatistics _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpWordsTyped;
            _tmpWordsTyped = _cursor.getInt(_cursorIndexOfWordsTyped);
            final float _tmpWpm;
            _tmpWpm = _cursor.getFloat(_cursorIndexOfWpm);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final String _tmpLanguagesUsed;
            if (_cursor.isNull(_cursorIndexOfLanguagesUsed)) {
              _tmpLanguagesUsed = null;
            } else {
              _tmpLanguagesUsed = _cursor.getString(_cursorIndexOfLanguagesUsed);
            }
            _result = new UserStatistics(_tmpId,_tmpDate,_tmpWordsTyped,_tmpWpm,_tmpAppsUsed,_tmpLanguagesUsed);
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
  public Flow<List<UserStatistics>> getAllStatistics() {
    final String _sql = "SELECT * FROM user_statistics ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_statistics"}, new Callable<List<UserStatistics>>() {
      @Override
      @NonNull
      public List<UserStatistics> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWordsTyped = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsTyped");
          final int _cursorIndexOfWpm = CursorUtil.getColumnIndexOrThrow(_cursor, "wpm");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfLanguagesUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "languagesUsed");
          final List<UserStatistics> _result = new ArrayList<UserStatistics>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserStatistics _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpWordsTyped;
            _tmpWordsTyped = _cursor.getInt(_cursorIndexOfWordsTyped);
            final float _tmpWpm;
            _tmpWpm = _cursor.getFloat(_cursorIndexOfWpm);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final String _tmpLanguagesUsed;
            if (_cursor.isNull(_cursorIndexOfLanguagesUsed)) {
              _tmpLanguagesUsed = null;
            } else {
              _tmpLanguagesUsed = _cursor.getString(_cursorIndexOfLanguagesUsed);
            }
            _item = new UserStatistics(_tmpId,_tmpDate,_tmpWordsTyped,_tmpWpm,_tmpAppsUsed,_tmpLanguagesUsed);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getStatisticsByDateRange(final String startDate, final String endDate,
      final Continuation<? super List<UserStatistics>> $completion) {
    final String _sql = "SELECT * FROM user_statistics WHERE date >= ? AND date <= ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    _argIndex = 2;
    if (endDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, endDate);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserStatistics>>() {
      @Override
      @NonNull
      public List<UserStatistics> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWordsTyped = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsTyped");
          final int _cursorIndexOfWpm = CursorUtil.getColumnIndexOrThrow(_cursor, "wpm");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfLanguagesUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "languagesUsed");
          final List<UserStatistics> _result = new ArrayList<UserStatistics>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserStatistics _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpWordsTyped;
            _tmpWordsTyped = _cursor.getInt(_cursorIndexOfWordsTyped);
            final float _tmpWpm;
            _tmpWpm = _cursor.getFloat(_cursorIndexOfWpm);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final String _tmpLanguagesUsed;
            if (_cursor.isNull(_cursorIndexOfLanguagesUsed)) {
              _tmpLanguagesUsed = null;
            } else {
              _tmpLanguagesUsed = _cursor.getString(_cursorIndexOfLanguagesUsed);
            }
            _item = new UserStatistics(_tmpId,_tmpDate,_tmpWordsTyped,_tmpWpm,_tmpAppsUsed,_tmpLanguagesUsed);
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
  public Object getRecentStatistics(final int limit,
      final Continuation<? super List<UserStatistics>> $completion) {
    final String _sql = "SELECT * FROM user_statistics ORDER BY date DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserStatistics>>() {
      @Override
      @NonNull
      public List<UserStatistics> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWordsTyped = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsTyped");
          final int _cursorIndexOfWpm = CursorUtil.getColumnIndexOrThrow(_cursor, "wpm");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfLanguagesUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "languagesUsed");
          final List<UserStatistics> _result = new ArrayList<UserStatistics>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserStatistics _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpWordsTyped;
            _tmpWordsTyped = _cursor.getInt(_cursorIndexOfWordsTyped);
            final float _tmpWpm;
            _tmpWpm = _cursor.getFloat(_cursorIndexOfWpm);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final String _tmpLanguagesUsed;
            if (_cursor.isNull(_cursorIndexOfLanguagesUsed)) {
              _tmpLanguagesUsed = null;
            } else {
              _tmpLanguagesUsed = _cursor.getString(_cursorIndexOfLanguagesUsed);
            }
            _item = new UserStatistics(_tmpId,_tmpDate,_tmpWordsTyped,_tmpWpm,_tmpAppsUsed,_tmpLanguagesUsed);
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
  public Object getTotalWordsTypedInRange(final String startDate, final String endDate,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT SUM(wordsTyped) FROM user_statistics WHERE date >= ? AND date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    _argIndex = 2;
    if (endDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, endDate);
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
  public Object getAverageWpmInRange(final String startDate, final String endDate,
      final Continuation<? super Float> $completion) {
    final String _sql = "SELECT AVG(wpm) FROM user_statistics WHERE date >= ? AND date <= ? AND wpm > 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    _argIndex = 2;
    if (endDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, endDate);
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

  @Override
  public Object getMaxWpmInRange(final String startDate, final String endDate,
      final Continuation<? super Float> $completion) {
    final String _sql = "SELECT MAX(wpm) FROM user_statistics WHERE date >= ? AND date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    _argIndex = 2;
    if (endDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, endDate);
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

  @Override
  public Object getUniqueAppsUsedInRange(final String startDate, final String endDate,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(DISTINCT CASE WHEN appsUsed != '' THEN appsUsed END) FROM user_statistics WHERE date >= ? AND date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    _argIndex = 2;
    if (endDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, endDate);
    }
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
  public Object getUniqueLanguagesUsedInRange(final String startDate, final String endDate,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(DISTINCT CASE WHEN languagesUsed != '' THEN languagesUsed END) FROM user_statistics WHERE date >= ? AND date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    _argIndex = 2;
    if (endDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, endDate);
    }
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
  public Object getLastWeekStatistics(
      final Continuation<? super List<UserStatistics>> $completion) {
    final String _sql = "SELECT * FROM user_statistics ORDER BY date DESC LIMIT 7";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserStatistics>>() {
      @Override
      @NonNull
      public List<UserStatistics> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWordsTyped = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsTyped");
          final int _cursorIndexOfWpm = CursorUtil.getColumnIndexOrThrow(_cursor, "wpm");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfLanguagesUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "languagesUsed");
          final List<UserStatistics> _result = new ArrayList<UserStatistics>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserStatistics _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpWordsTyped;
            _tmpWordsTyped = _cursor.getInt(_cursorIndexOfWordsTyped);
            final float _tmpWpm;
            _tmpWpm = _cursor.getFloat(_cursorIndexOfWpm);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final String _tmpLanguagesUsed;
            if (_cursor.isNull(_cursorIndexOfLanguagesUsed)) {
              _tmpLanguagesUsed = null;
            } else {
              _tmpLanguagesUsed = _cursor.getString(_cursorIndexOfLanguagesUsed);
            }
            _item = new UserStatistics(_tmpId,_tmpDate,_tmpWordsTyped,_tmpWpm,_tmpAppsUsed,_tmpLanguagesUsed);
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
  public Object getLastMonthStatistics(
      final Continuation<? super List<UserStatistics>> $completion) {
    final String _sql = "SELECT * FROM user_statistics ORDER BY date DESC LIMIT 30";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserStatistics>>() {
      @Override
      @NonNull
      public List<UserStatistics> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfWordsTyped = CursorUtil.getColumnIndexOrThrow(_cursor, "wordsTyped");
          final int _cursorIndexOfWpm = CursorUtil.getColumnIndexOrThrow(_cursor, "wpm");
          final int _cursorIndexOfAppsUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "appsUsed");
          final int _cursorIndexOfLanguagesUsed = CursorUtil.getColumnIndexOrThrow(_cursor, "languagesUsed");
          final List<UserStatistics> _result = new ArrayList<UserStatistics>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserStatistics _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpWordsTyped;
            _tmpWordsTyped = _cursor.getInt(_cursorIndexOfWordsTyped);
            final float _tmpWpm;
            _tmpWpm = _cursor.getFloat(_cursorIndexOfWpm);
            final String _tmpAppsUsed;
            if (_cursor.isNull(_cursorIndexOfAppsUsed)) {
              _tmpAppsUsed = null;
            } else {
              _tmpAppsUsed = _cursor.getString(_cursorIndexOfAppsUsed);
            }
            final String _tmpLanguagesUsed;
            if (_cursor.isNull(_cursorIndexOfLanguagesUsed)) {
              _tmpLanguagesUsed = null;
            } else {
              _tmpLanguagesUsed = _cursor.getString(_cursorIndexOfLanguagesUsed);
            }
            _item = new UserStatistics(_tmpId,_tmpDate,_tmpWordsTyped,_tmpWpm,_tmpAppsUsed,_tmpLanguagesUsed);
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
