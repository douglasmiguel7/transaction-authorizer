package com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity

import com.gh.douglasmiguel7.transactionAuthorizer.core.domain.Transaction
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.sql.Types
import java.util.UUID
import org.hibernate.annotations.JdbcTypeCode

@Table(name = "transaction")
@Entity(name = "transaction")
data class TransactionEntity(

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @JdbcTypeCode(Types.BINARY)
  val id: UUID?,
  val accountId: UUID,
  val totalAmount: BigDecimal,
  val mcc: String,
  val merchant: String,
  val code: String,
) {
  fun toDomain(): Transaction {
    return Transaction(
      id = id,
      accountId = accountId,
      totalAmount = totalAmount,
      mcc = mcc,
      merchant = merchant,
      code = code,
    )
  }

  companion object {
    fun fromDomain(transaction: Transaction) : TransactionEntity {
      return TransactionEntity(
        id = transaction.id,
        accountId = transaction.accountId,
        totalAmount = transaction.totalAmount,
        mcc = transaction.mcc,
        merchant = transaction.merchant,
        code = transaction.code!!,
      )
    }
  }
}