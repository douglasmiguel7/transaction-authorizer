package com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database.entity

import com.gh.douglasmiguel7.transactionAuthorizer.account.application.extension.toUUID
import com.gh.douglasmiguel7.transactionAuthorizer.account.core.domain.Account
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.sql.Types
import java.util.UUID
import org.hibernate.annotations.JdbcType
import org.hibernate.annotations.JdbcTypeCode

@Table(name = "account")
@Entity(name = "account")
data class AccountEntity(

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @JdbcTypeCode(Types.BINARY)
  val id: UUID?,
  val food: BigDecimal,
  val meal: BigDecimal,
  val cash: BigDecimal,
) {
  fun toDomain(): Account {
    return Account(
      id = id.toString(),
      food = food,
      meal = meal,
      cash = cash,
    )
  }

  companion object {
    fun fromDomain(account: Account) : AccountEntity {
      return AccountEntity(
        id = account.id?.toUUID(),
        food = account.food,
        meal = account.meal,
        cash = account.cash,
      )
    }
  }
}