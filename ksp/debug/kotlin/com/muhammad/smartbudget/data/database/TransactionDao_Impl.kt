package com.muhammad.smartbudget.`data`.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.muhammad.smartbudget.`data`.model.Transaction
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class TransactionDao_Impl(
  __db: RoomDatabase,
) : TransactionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfTransaction: EntityInsertAdapter<Transaction>

  private val __deleteAdapterOfTransaction: EntityDeleteOrUpdateAdapter<Transaction>
  init {
    this.__db = __db
    this.__insertAdapterOfTransaction = object : EntityInsertAdapter<Transaction>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `transactions` (`id`,`amount`,`note`,`isExpense`,`category`,`date`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Transaction) {
        statement.bindText(1, entity.id)
        statement.bindDouble(2, entity.amount)
        statement.bindText(3, entity.note)
        val _tmp: Int = if (entity.isExpense) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindText(5, entity.category)
        statement.bindLong(6, entity.date)
      }
    }
    this.__deleteAdapterOfTransaction = object : EntityDeleteOrUpdateAdapter<Transaction>() {
      protected override fun createQuery(): String = "DELETE FROM `transactions` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Transaction) {
        statement.bindText(1, entity.id)
      }
    }
  }

  public override suspend fun insertTransaction(transaction: Transaction): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfTransaction.insert(_connection, transaction)
  }

  public override suspend fun deleteTransaction(transaction: Transaction): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfTransaction.handle(_connection, transaction)
  }

  public override fun getAllTransactions(): Flow<List<Transaction>> {
    val _sql: String = "SELECT * FROM transactions ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("transactions")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _columnIndexOfIsExpense: Int = getColumnIndexOrThrow(_stmt, "isExpense")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _result: MutableList<Transaction> = mutableListOf()
        while (_stmt.step()) {
          val _item: Transaction
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          val _tmpIsExpense: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsExpense).toInt()
          _tmpIsExpense = _tmp != 0
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          _item = Transaction(_tmpId,_tmpAmount,_tmpNote,_tmpIsExpense,_tmpCategory,_tmpDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
