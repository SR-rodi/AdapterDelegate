package com.test.payloads.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.payloads.R
import com.test.payloads.data.model.DisplayPrint
import com.test.payloads.data.model.News
import com.test.payloads.data.model.Post
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow


class Repository {

    private val list = mutableListOf(
        Post(1, "Новая надежда", R.drawable.test_1, false),
        News(1, "Вот так встреча", false),
        Post(2, "Старая надежда", R.drawable.test_2, false),
        Post(3, "Привет", R.drawable.test_3, true),
        Post(4, "Пока", R.drawable.test_1, false),
        Post(5, "Test", R.drawable.test_3, false),
        Post(6, "Test", R.drawable.test_2, false),
    )

    suspend fun getList(): List<DisplayPrint> {
        delay(1_000)
        return list
    }

    suspend fun favoriteWorker(id: Int) {
        delay(100)
    }

    fun getPagingData(): Flow<PagingData<DisplayPrint>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSoursTest(list) }
        ).flow
    }
}

