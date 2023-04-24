package ru.sr.delegate.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import ru.sr.delegate.data.model.DisplayPrint
import ru.sr.delegate.data.model.News
import ru.sr.delegate.data.model.Post
import ru.sr.payloads.R


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
            pagingSourceFactory = {PagingSoursTest(list) }
        ).flow
    }
}

