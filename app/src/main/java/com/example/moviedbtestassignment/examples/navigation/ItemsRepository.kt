package com.example.moviedbtestassignment.examples.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface ItemsRepository {

    fun getItems():StateFlow<List<String>>

    fun addItem(item:String)

    fun clearItems()

    companion object{
        fun get():ItemsRepository = ItemsRepositoryImpl
    }

    object ItemsRepositoryImpl:ItemsRepository{

        private val items = MutableStateFlow(generateFakeItems())
        override fun getItems(): StateFlow<List<String>> {
            return items
        }

        override fun addItem(item: String) {
            items.update { it + item }
        }

        override fun clearItems() {
            items.update { emptyList() }
        }


        private fun generateFakeItems():List<String>{
            val list = mutableListOf<String>()
            for (i in 0..10){
                list.add("Item $i")
            }
            return list
        }
    }

}