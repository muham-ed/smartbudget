package com.muhammad.smartbudget.`data`.database

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.muhammad.smartbudget.`data`.model.GroupBudget
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Double
import kotlin.Int
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
public class GroupBudgetDao_Impl(
  __db: RoomDatabase,
) : GroupBudgetDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfGroupBudget: EntityInsertAdapter<GroupBudget>

  private val __converters: Converters = Converters()

  private val __deleteAdapterOfGroupBudget: EntityDeleteOrUpdateAdapter<GroupBudget>
  init {
    this.__db = __db
    this.__insertAdapterOfGroupBudget = object : EntityInsertAdapter<GroupBudget>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `group_budgets` (`id`,`name`,`totalLimit`,`members`,`ownerId`,`isPremium`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: GroupBudget) {
        statement.bindText(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindDouble(3, entity.totalLimit)
        val _tmp: String? = __converters.fromStringList(entity.members)
        if (_tmp == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmp)
        }
        statement.bindText(5, entity.ownerId)
        val _tmp_1: Int = if (entity.isPremium) 1 else 0
        statement.bindLong(6, _tmp_1.toLong())
      }
    }
    this.__deleteAdapterOfGroupBudget = object : EntityDeleteOrUpdateAdapter<GroupBudget>() {
      protected override fun createQuery(): String = "DELETE FROM `group_budgets` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: GroupBudget) {
        statement.bindText(1, entity.id)
      }
    }
  }

  public override suspend fun insertGroupBudget(budget: GroupBudget): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfGroupBudget.insert(_connection, budget)
  }

  public override suspend fun deleteGroupBudget(budget: GroupBudget): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfGroupBudget.handle(_connection, budget)
  }

  public override fun getAllGroupBudgets(): Flow<List<GroupBudget>> {
    val _sql: String = "SELECT * FROM group_budgets"
    return createFlow(__db, false, arrayOf("group_budgets")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfTotalLimit: Int = getColumnIndexOrThrow(_stmt, "totalLimit")
        val _columnIndexOfMembers: Int = getColumnIndexOrThrow(_stmt, "members")
        val _columnIndexOfOwnerId: Int = getColumnIndexOrThrow(_stmt, "ownerId")
        val _columnIndexOfIsPremium: Int = getColumnIndexOrThrow(_stmt, "isPremium")
        val _result: MutableList<GroupBudget> = mutableListOf()
        while (_stmt.step()) {
          val _item: GroupBudget
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpTotalLimit: Double
          _tmpTotalLimit = _stmt.getDouble(_columnIndexOfTotalLimit)
          val _tmpMembers: List<String>
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfMembers)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfMembers)
          }
          val _tmp_1: List<String>? = __converters.toStringList(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'kotlin.collections.List<kotlin.String>', but it was NULL.")
          } else {
            _tmpMembers = _tmp_1
          }
          val _tmpOwnerId: String
          _tmpOwnerId = _stmt.getText(_columnIndexOfOwnerId)
          val _tmpIsPremium: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsPremium).toInt()
          _tmpIsPremium = _tmp_2 != 0
          _item = GroupBudget(_tmpId,_tmpName,_tmpTotalLimit,_tmpMembers,_tmpOwnerId,_tmpIsPremium)
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
