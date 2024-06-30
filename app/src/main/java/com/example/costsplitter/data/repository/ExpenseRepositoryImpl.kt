package com.example.costsplitter.data.repository

import com.example.costsplitter.data.model.Expense
import com.example.costsplitter.domain.repository.ExpenseRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ExpenseRepositoryImpl(private val firestore: FirebaseFirestore):ExpenseRepository {

    companion object {
        const val EXPENSES_COLLECTION = "expenses"
    }
    override suspend fun addExpense(groupId: String, expense: Expense): String {
        val expenseRef = firestore.collection(EXPENSES_COLLECTION).document(groupId)
            .collection(EXPENSES_COLLECTION).add(expense).await()
        return expenseRef.id
    }

    override suspend fun updateExpense(groupId: String, expense: Expense) {
        firestore.collection(EXPENSES_COLLECTION).document(expense.id).set(expense).await()
    }

    override suspend fun deleteExpense(groupId: String, expenseId: String) {
        firestore.collection(EXPENSES_COLLECTION).document(expenseId).delete().await()
    }

    override suspend fun getExpenses(groupId: String): List<Expense> {
        val snapshot = firestore.collection(EXPENSES_COLLECTION).document(groupId)
            .collection(EXPENSES_COLLECTION).get().await()
        return snapshot.toObjects(Expense::class.java)
    }
}