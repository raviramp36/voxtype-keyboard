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
import com.voxtype.keyboard.database.entities.SettingsPreference;
import java.lang.Boolean;
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
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SettingsPreferenceDao_Impl implements SettingsPreferenceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SettingsPreference> __insertionAdapterOfSettingsPreference;

  private final EntityDeletionOrUpdateAdapter<SettingsPreference> __deletionAdapterOfSettingsPreference;

  private final EntityDeletionOrUpdateAdapter<SettingsPreference> __updateAdapterOfSettingsPreference;

  private final SharedSQLiteStatement __preparedStmtOfDeletePreferenceByKey;

  private final SharedSQLiteStatement __preparedStmtOfDeletePreferencesByPattern;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPreferences;

  public SettingsPreferenceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSettingsPreference = new EntityInsertionAdapter<SettingsPreference>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `settings_preferences` (`key`,`value`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SettingsPreference entity) {
        if (entity.getKey() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getKey());
        }
        if (entity.getValue() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getValue());
        }
      }
    };
    this.__deletionAdapterOfSettingsPreference = new EntityDeletionOrUpdateAdapter<SettingsPreference>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `settings_preferences` WHERE `key` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SettingsPreference entity) {
        if (entity.getKey() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getKey());
        }
      }
    };
    this.__updateAdapterOfSettingsPreference = new EntityDeletionOrUpdateAdapter<SettingsPreference>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `settings_preferences` SET `key` = ?,`value` = ? WHERE `key` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SettingsPreference entity) {
        if (entity.getKey() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getKey());
        }
        if (entity.getValue() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getValue());
        }
        if (entity.getKey() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getKey());
        }
      }
    };
    this.__preparedStmtOfDeletePreferenceByKey = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM settings_preferences WHERE key = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeletePreferencesByPattern = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM settings_preferences WHERE key LIKE ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllPreferences = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM settings_preferences";
        return _query;
      }
    };
  }

  @Override
  public Object insertPreference(final SettingsPreference preference,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSettingsPreference.insert(preference);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPreferences(final List<SettingsPreference> preferences,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSettingsPreference.insert(preferences);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePreference(final SettingsPreference preference,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSettingsPreference.handle(preference);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePreference(final SettingsPreference preference,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSettingsPreference.handle(preference);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePreferenceByKey(final String key,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePreferenceByKey.acquire();
        int _argIndex = 1;
        if (key == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, key);
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
          __preparedStmtOfDeletePreferenceByKey.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePreferencesByPattern(final String keyPattern,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePreferencesByPattern.acquire();
        int _argIndex = 1;
        if (keyPattern == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, keyPattern);
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
          __preparedStmtOfDeletePreferencesByPattern.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllPreferences(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPreferences.acquire();
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
          __preparedStmtOfDeleteAllPreferences.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getPreference(final String key,
      final Continuation<? super SettingsPreference> $completion) {
    final String _sql = "SELECT * FROM settings_preferences WHERE key = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (key == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, key);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SettingsPreference>() {
      @Override
      @Nullable
      public SettingsPreference call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final SettingsPreference _result;
          if (_cursor.moveToFirst()) {
            final String _tmpKey;
            if (_cursor.isNull(_cursorIndexOfKey)) {
              _tmpKey = null;
            } else {
              _tmpKey = _cursor.getString(_cursorIndexOfKey);
            }
            final String _tmpValue;
            if (_cursor.isNull(_cursorIndexOfValue)) {
              _tmpValue = null;
            } else {
              _tmpValue = _cursor.getString(_cursorIndexOfValue);
            }
            _result = new SettingsPreference(_tmpKey,_tmpValue);
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
  public Object getPreferenceValue(final String key,
      final Continuation<? super String> $completion) {
    final String _sql = "SELECT value FROM settings_preferences WHERE key = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (key == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, key);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<String>() {
      @Override
      @Nullable
      public String call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final String _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getString(0);
            }
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
  public Flow<List<SettingsPreference>> getAllPreferences() {
    final String _sql = "SELECT * FROM settings_preferences ORDER BY key";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"settings_preferences"}, new Callable<List<SettingsPreference>>() {
      @Override
      @NonNull
      public List<SettingsPreference> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final List<SettingsPreference> _result = new ArrayList<SettingsPreference>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SettingsPreference _item;
            final String _tmpKey;
            if (_cursor.isNull(_cursorIndexOfKey)) {
              _tmpKey = null;
            } else {
              _tmpKey = _cursor.getString(_cursorIndexOfKey);
            }
            final String _tmpValue;
            if (_cursor.isNull(_cursorIndexOfValue)) {
              _tmpValue = null;
            } else {
              _tmpValue = _cursor.getString(_cursorIndexOfValue);
            }
            _item = new SettingsPreference(_tmpKey,_tmpValue);
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
  public Object getPreferencesByPattern(final String keyPattern,
      final Continuation<? super List<SettingsPreference>> $completion) {
    final String _sql = "SELECT * FROM settings_preferences WHERE key LIKE ? ORDER BY key";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (keyPattern == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, keyPattern);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SettingsPreference>>() {
      @Override
      @NonNull
      public List<SettingsPreference> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final List<SettingsPreference> _result = new ArrayList<SettingsPreference>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SettingsPreference _item;
            final String _tmpKey;
            if (_cursor.isNull(_cursorIndexOfKey)) {
              _tmpKey = null;
            } else {
              _tmpKey = _cursor.getString(_cursorIndexOfKey);
            }
            final String _tmpValue;
            if (_cursor.isNull(_cursorIndexOfValue)) {
              _tmpValue = null;
            } else {
              _tmpValue = _cursor.getString(_cursorIndexOfValue);
            }
            _item = new SettingsPreference(_tmpKey,_tmpValue);
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
  public Object setBooleanPreference(final String key, final boolean value,
      final Continuation<? super Unit> $completion) {
    return SettingsPreferenceDao.DefaultImpls.setBooleanPreference(SettingsPreferenceDao_Impl.this, key, value, $completion);
  }

  @Override
  public Object getBooleanPreference(final String key, final boolean defaultValue,
      final Continuation<? super Boolean> $completion) {
    return SettingsPreferenceDao.DefaultImpls.getBooleanPreference(SettingsPreferenceDao_Impl.this, key, defaultValue, $completion);
  }

  @Override
  public Object setIntPreference(final String key, final int value,
      final Continuation<? super Unit> $completion) {
    return SettingsPreferenceDao.DefaultImpls.setIntPreference(SettingsPreferenceDao_Impl.this, key, value, $completion);
  }

  @Override
  public Object getIntPreference(final String key, final int defaultValue,
      final Continuation<? super Integer> $completion) {
    return SettingsPreferenceDao.DefaultImpls.getIntPreference(SettingsPreferenceDao_Impl.this, key, defaultValue, $completion);
  }

  @Override
  public Object setFloatPreference(final String key, final float value,
      final Continuation<? super Unit> $completion) {
    return SettingsPreferenceDao.DefaultImpls.setFloatPreference(SettingsPreferenceDao_Impl.this, key, value, $completion);
  }

  @Override
  public Object getFloatPreference(final String key, final float defaultValue,
      final Continuation<? super Float> $completion) {
    return SettingsPreferenceDao.DefaultImpls.getFloatPreference(SettingsPreferenceDao_Impl.this, key, defaultValue, $completion);
  }

  @Override
  public Object setStringPreference(final String key, final String value,
      final Continuation<? super Unit> $completion) {
    return SettingsPreferenceDao.DefaultImpls.setStringPreference(SettingsPreferenceDao_Impl.this, key, value, $completion);
  }

  @Override
  public Object getStringPreference(final String key, final String defaultValue,
      final Continuation<? super String> $completion) {
    return SettingsPreferenceDao.DefaultImpls.getStringPreference(SettingsPreferenceDao_Impl.this, key, defaultValue, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
