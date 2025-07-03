package com.example.anmproject.model;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
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
public final class ExpensesDao_Impl implements ExpensesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Expenses> __insertionAdapterOfExpenses;

  private final EntityDeletionOrUpdateAdapter<Expenses> __deletionAdapterOfExpenses;

  public ExpensesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExpenses = new EntityInsertionAdapter<Expenses>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Expenses` (`idUser`,`idBudgeting`,`tanggal`,`nominal`,`deskripsi`,`uuid`) VALUES (?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Expenses value) {
        if (value.getIdUser() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getIdUser());
        }
        if (value.getIdBudgeting() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getIdBudgeting());
        }
        if (value.getTanggal() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTanggal());
        }
        if (value.getNominal() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getNominal());
        }
        if (value.getDeskripsi() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDeskripsi());
        }
        stmt.bindLong(6, value.getUuid());
      }
    };
    this.__deletionAdapterOfExpenses = new EntityDeletionOrUpdateAdapter<Expenses>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Expenses` WHERE `uuid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Expenses value) {
        stmt.bindLong(1, value.getUuid());
      }
    };
  }

  @Override
  public void insertAll(final Expenses... expenses) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfExpenses.insert(expenses);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteExpenses(final Expenses expenses) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfExpenses.handle(expenses);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Expenses> selectAllExpenses() {
    final String _sql = "SELECT * FROM expenses";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIdUser = CursorUtil.getColumnIndexOrThrow(_cursor, "idUser");
      final int _cursorIndexOfIdBudgeting = CursorUtil.getColumnIndexOrThrow(_cursor, "idBudgeting");
      final int _cursorIndexOfTanggal = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggal");
      final int _cursorIndexOfNominal = CursorUtil.getColumnIndexOrThrow(_cursor, "nominal");
      final int _cursorIndexOfDeskripsi = CursorUtil.getColumnIndexOrThrow(_cursor, "deskripsi");
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
      final List<Expenses> _result = new ArrayList<Expenses>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Expenses _item;
        final String _tmpIdUser;
        if (_cursor.isNull(_cursorIndexOfIdUser)) {
          _tmpIdUser = null;
        } else {
          _tmpIdUser = _cursor.getString(_cursorIndexOfIdUser);
        }
        final String _tmpIdBudgeting;
        if (_cursor.isNull(_cursorIndexOfIdBudgeting)) {
          _tmpIdBudgeting = null;
        } else {
          _tmpIdBudgeting = _cursor.getString(_cursorIndexOfIdBudgeting);
        }
        final String _tmpTanggal;
        if (_cursor.isNull(_cursorIndexOfTanggal)) {
          _tmpTanggal = null;
        } else {
          _tmpTanggal = _cursor.getString(_cursorIndexOfTanggal);
        }
        final Integer _tmpNominal;
        if (_cursor.isNull(_cursorIndexOfNominal)) {
          _tmpNominal = null;
        } else {
          _tmpNominal = _cursor.getInt(_cursorIndexOfNominal);
        }
        final String _tmpDeskripsi;
        if (_cursor.isNull(_cursorIndexOfDeskripsi)) {
          _tmpDeskripsi = null;
        } else {
          _tmpDeskripsi = _cursor.getString(_cursorIndexOfDeskripsi);
        }
        _item = new Expenses(_tmpIdUser,_tmpIdBudgeting,_tmpTanggal,_tmpNominal,_tmpDeskripsi);
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
  public List<Expenses> selectExpenses(final String id) {
    final String _sql = "SELECT * FROM expenses WHERE idUser= ?";
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
      final int _cursorIndexOfIdBudgeting = CursorUtil.getColumnIndexOrThrow(_cursor, "idBudgeting");
      final int _cursorIndexOfTanggal = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggal");
      final int _cursorIndexOfNominal = CursorUtil.getColumnIndexOrThrow(_cursor, "nominal");
      final int _cursorIndexOfDeskripsi = CursorUtil.getColumnIndexOrThrow(_cursor, "deskripsi");
      final int _cursorIndexOfUuid = CursorUtil.getColumnIndexOrThrow(_cursor, "uuid");
      final List<Expenses> _result = new ArrayList<Expenses>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Expenses _item;
        final String _tmpIdUser;
        if (_cursor.isNull(_cursorIndexOfIdUser)) {
          _tmpIdUser = null;
        } else {
          _tmpIdUser = _cursor.getString(_cursorIndexOfIdUser);
        }
        final String _tmpIdBudgeting;
        if (_cursor.isNull(_cursorIndexOfIdBudgeting)) {
          _tmpIdBudgeting = null;
        } else {
          _tmpIdBudgeting = _cursor.getString(_cursorIndexOfIdBudgeting);
        }
        final String _tmpTanggal;
        if (_cursor.isNull(_cursorIndexOfTanggal)) {
          _tmpTanggal = null;
        } else {
          _tmpTanggal = _cursor.getString(_cursorIndexOfTanggal);
        }
        final Integer _tmpNominal;
        if (_cursor.isNull(_cursorIndexOfNominal)) {
          _tmpNominal = null;
        } else {
          _tmpNominal = _cursor.getInt(_cursorIndexOfNominal);
        }
        final String _tmpDeskripsi;
        if (_cursor.isNull(_cursorIndexOfDeskripsi)) {
          _tmpDeskripsi = null;
        } else {
          _tmpDeskripsi = _cursor.getString(_cursorIndexOfDeskripsi);
        }
        _item = new Expenses(_tmpIdUser,_tmpIdBudgeting,_tmpTanggal,_tmpNominal,_tmpDeskripsi);
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
  public int selectTotalExpensesBudget(final String id, final String budgetId) {
    final String _sql = "SELECT SUM(nominal) FROM expenses WHERE idUser= ? AND idBudgeting=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    _argIndex = 2;
    if (budgetId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, budgetId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
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
