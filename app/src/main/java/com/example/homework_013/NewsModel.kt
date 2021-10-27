package com.example.homework_013

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_013.model.NewsItem
import com.example.homework_013.network.NetworkClient
import com.example.homework_013.network.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsModel: ViewModel() {

    private val _newsData = MutableLiveData<Resource<List<NewsItem>>>()
    val newsData: LiveData<Resource<List<NewsItem>>> get()=_newsData





    fun loadNews(){
        viewModelScope.launch {
            withContext(IO){
                getNews()
            }
        }
    }


    private suspend fun getNews(){
        _newsData.postValue(Resource.Loading(true))
        try {
            val response = NetworkClient.api.getNews()
            val body = response.body()
            if (response.isSuccessful && body !=null){
                _newsData.postValue(Resource.Success(body, loading = true))
            }else{
                _newsData.postValue(Resource.Error(message=response.message().toString(), loading = true ))
            }
        }catch (e:Exception){
           Log.e("error", "${e.message}")
        }

    }





}