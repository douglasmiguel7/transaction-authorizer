package com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.repository

import com.gh.douglasmiguel7.transactionAuthorizer.application.output.database.entity.TransactionEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<TransactionEntity, UUID>