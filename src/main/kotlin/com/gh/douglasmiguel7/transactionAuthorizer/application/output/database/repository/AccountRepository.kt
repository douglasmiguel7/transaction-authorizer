package com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database.repository

import com.gh.douglasmiguel7.transactionAuthorizer.account.application.output.database.entity.AccountEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<AccountEntity, UUID>