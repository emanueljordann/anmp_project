package com.example.anmproject.model;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BudgetingDao_Impl implements BudgetingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Budgeting> __insertionAdapterOfBudgeting;

  private final EntityDeletionOrUpdateAdapter<Budgeting> __deletionAdapterOfBudgeting;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBudgeting;

  public BudgetingDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBudgeting = new EntityInsertionAdapter<Budgeting>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Budgeting` (`idUser`,`name`,`budget`,`uuid`) VALUES (?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Budgeting value) {
        if (value.getIdUser() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getIdUser());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getBudget() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, value.getBudget());
        }
        stmt.bindLong(4, value.getUuid());
      }
    };
    this.__deletionAdapterOfBudgeting = new EntityDeletionOrUpdateAdapter<Budgeting>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Budgeting` WHERE `uuid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Budgeting value) {
        stmt.bindLong(1, value.getUuid());
      }
    };
    this.__preparedStmtOfUpdateBudgeting = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE budgeting SET name=?, budget=? WHERE uuid= ? and idUser=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final Budgeting... budgeting) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBudgeting.insert(budgeting);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteBudgeting(final Budgeting budgeting) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfBudgeting.handle(budgeting);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateBudgeting(final String id, final String userid, final String nama,
      final int budget) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBudgeting.acquire();
    int _argIndex = 1;
    if (nama == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, nama);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, budget);
    _argIndex = 3;
    if (id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, id);
    }
    _argIndex = 4;
    if (userid == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, userid);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateBudgeting.release(_stmt);
    }
  }

  @Override
  public List<Budgeting> selectAllBudgeting() {
    final String _sql = "SELECT * FROM budgeting";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIdUser = CursorUtil.getColumnIndexOrThrow(_cursor, "idUser");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfBudget = CursorUtil.getColumnIndexOrThrow(_cursor, "budget");
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
      final List<Budgeting> _result = new ArrayList<Budgeting>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Budgeting _item;
        final String _tmpIdUser;
        if (_cursor.isNull(_cursorIndexOfIdUser)) {
          _tmpIdUser = null;
        } else {
          _tmpIdUser = _cursor.getString(_cursorIndexOfIdUser);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final Integer _tmpBudget;
        if (_cursor.isNull(_cursorIndexOfBudget)) {
          _tmpBudget = null;
        } else {
          _tmpBudget = _cursor.getInt(_cursorIndexOfBudget);
        }
        _item = new Budgeting(_tmpIdUser,_tmpName,_tmpBudget);
        final int _tmpUuid;
        _tmpUuid = _cursor.getInt(_cursorIndexOfUuid);
        _item.setUuid(_tmpUuid);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Budgeting> selectBudgeting(final String id) {
    final String _sql = "SELECT * FROM budgeting WHERE idUser= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIdUser = CursorUtil.getColumnIndexOrThrow(_cursor, "idUser");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfBudget = CursorUtil.getColumnIndexOrThrow(_cursor, "budget");
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
      final List<Budgeting> _result = new ArrayList<Budgeting>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Budgeting _item;
        final String _tmpIdUser;
        if (_cursor.isNull(_cursorIndexOfIdUser)) {
          _tmpIdUser = null;
        } else {
          _tmpIdUser = _cursor.getString(_cursorIndexOfIdUser);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final Integer _tmpBudget;
        if (_cursor.isNull(_cursorIndexOfBudget)) {
          _tmpBudget = null;
        } else {
          _tmpBudget = _cursor.getInt(_cursorIndexOfBudget);
        }
        _item = new Budgeting(_tmpIdUser,_tmpName,_tmpBudget);
        final int _tmpUuid;
        _tmpUuid = _cursor.getInt(_cursorIndexOfUuid);
        _item.setUuid(_tmpUuid);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
