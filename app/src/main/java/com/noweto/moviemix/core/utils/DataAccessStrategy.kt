package com.noweto.moviemix.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 * Get data from local data source
 * call apis
 * save data into local Db
 */
fun<T,A> performGetOperations(
                dataBaseQuery:()->LiveData<T>,
                networkCall:suspend ()->Resource<A>,
                saveCallResults:suspend(A) ->Unit
                ):LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        //~~ start with loading
        emit(Resource.loading())
        //~~ make local db as a default data base
        val source = dataBaseQuery.invoke().map { Resource.localSuccess(it) }
        emitSource(source)
        //~~ call api
        val responseStatus = networkCall.invoke()

        if (responseStatus.status==Resource.Status.REMOTE_SUCCESS){
            //~~ save api result
            saveCallResults(responseStatus.data!!)
        }else if (responseStatus.status==Resource.Status.ERROR)
            //~~ show error message
            emit(Resource.error(responseStatus.message!!))
            //~~ set default data source [ Local Db ]
            emitSource(source)

        }

/**
 * return result in liveData scope ..
 */

fun <T> performSearchOperations(networkCall: suspend () -> Resource<T>)
    :LiveData<Resource<T>> =

    liveData (Dispatchers.IO){
        //~~ start with loading status ..
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if(responseStatus.status==Resource.Status.REMOTE_SUCCESS){
            //~~ api is called successfully
            val source = responseStatus.data
            emit(Resource.remoteSuccess(source))

        }else if(responseStatus.status==Resource.Status.ERROR){
            emit(Resource.error(responseStatus.message!!))
        }
    }