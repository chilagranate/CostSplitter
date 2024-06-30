package com.example.costsplitter.domain.repository

import com.example.costsplitter.data.model.Expense

interface ExpenseRepository {
    suspend fun getExpenses(groupId: String): List<Expense>
    suspend fun addExpense(groupId: String, expense: Expense): String
    suspend fun updateExpense(groupId: String, expense: Expense)
    suspend fun deleteExpense(groupId: String, expenseId: String)
}