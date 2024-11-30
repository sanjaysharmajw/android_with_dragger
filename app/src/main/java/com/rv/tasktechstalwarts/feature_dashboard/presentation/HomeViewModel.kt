package com.rv.tasktechstalwarts.feature_dashboard.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rv.tasktechstalwarts.common.LogUtil
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetRecipesUIState
import com.rv.tasktechstalwarts.feature_dashboard.domain.usecase.GetRecipesUseCase
import com.rv.tasktechstalwarts.common.remote.apis.ErrorResponseHandler
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FavouriteListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.FilterListResponse
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetAddToCartUIState
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.GetFavouriteListUIState
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.Recipe
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.InsertAddToCartUIState
import com.rv.tasktechstalwarts.feature_dashboard.domain.model.InsertFavouriteUIState
import com.rv.tasktechstalwarts.feature_dashboard.domain.usecase.GetAddToCartUseCase
import com.rv.tasktechstalwarts.feature_dashboard.domain.usecase.GetFavouriteListUseCase
import com.rv.tasktechstalwarts.feature_dashboard.domain.usecase.InsertAddToCartUseCase
import com.rv.tasktechstalwarts.feature_dashboard.domain.usecase.InsertFavouriteDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val insertAddToCartUseCase: InsertAddToCartUseCase,
    private val getAddToCartUseCase: GetAddToCartUseCase,
    private val insertFavouriteDataUseCase: InsertFavouriteDataUseCase,
    private val getFavouriteListUseCase: GetFavouriteListUseCase,
    private val logUtil: LogUtil,
) : ViewModel() {

    private val _getRecipesResponse: MutableLiveData<GetRecipesUIState> = MutableLiveData()
    val observeRecipesResponse: LiveData<GetRecipesUIState> = _getRecipesResponse

    private val _insertAddToCart: MutableLiveData<InsertAddToCartUIState?> = MutableLiveData()
    val observeInsertAddToCartData: LiveData<InsertAddToCartUIState?> = _insertAddToCart

    private val _getAddToCartList: MutableLiveData<GetAddToCartUIState> = MutableLiveData()
    val getAddToCartList: LiveData<GetAddToCartUIState> = _getAddToCartList

    private val _insertFavouriteItem: MutableLiveData<InsertFavouriteUIState?> = MutableLiveData()
    val observeInsertFavourite: LiveData<InsertFavouriteUIState?> = _insertFavouriteItem

    private val _getFavouriteItem: MutableLiveData<GetFavouriteListUIState> = MutableLiveData()
    val getFavouriteItem: LiveData<GetFavouriteListUIState> = _getFavouriteItem

    val hideBottomNav = MutableLiveData<Int>()

    fun getRecipesResponse() {
        _getRecipesResponse.value = GetRecipesUIState.IsLoading(true)
        viewModelScope.launch {
            getRecipesUseCase.invoke().catch {
                val error = ErrorResponseHandler(it)
                logUtil.log(TAG, "onErrorResponse${error.getErrors().data}")
                _getRecipesResponse.value =
                    GetRecipesUIState.OnFailure(error.getErrors().message.toString())
            }.collect {
                logUtil.log(TAG, "onResponse: $it")
                _getRecipesResponse.value =
                    GetRecipesUIState.OnSuccess(it)
            }
        }
    }

    fun insertDataAddToCart(recipe: Recipe) {
        _insertAddToCart.value = InsertAddToCartUIState.IsLoading(true)
        viewModelScope.launch {
            insertAddToCartUseCase.invoke(recipe).catch {
                val error = ErrorResponseHandler(it)
                logUtil.log(TAG, "onErrorResponse${error.getErrors().data}")
                _insertAddToCart.value = InsertAddToCartUIState.OnFailure(error.getErrors().toString())
            }.collect{
                logUtil.log(TAG, "onResponse: $it")
                _insertAddToCart.value =
                    InsertAddToCartUIState.OnSuccess(it)
            }
        }
    }

    fun clearInsertAddToCart() {
        _insertAddToCart.value = null
    }

     fun getAddToCartResponse(){
        _getAddToCartList.value = GetAddToCartUIState.IsLoading(true)
         viewModelScope.launch {
             getAddToCartUseCase.invoke().catch {
                 val error = ErrorResponseHandler(it)
                 logUtil.log(TAG, "onErrorResponse${error.getErrors().data}")
                 _getAddToCartList.value = GetAddToCartUIState.OnFailure(error.getErrors().toString())
             }.collect{
                 logUtil.log(TAG, "onResponse: $it")
                 _getAddToCartList.value = GetAddToCartUIState.OnSuccess(it)
             }
         }
    }

    fun addFavouriteData(favouriteListResponse: FavouriteListResponse){
        _insertFavouriteItem.value = InsertFavouriteUIState.IsLoading(true)
        viewModelScope.launch {
            insertFavouriteDataUseCase.invoke(favouriteListResponse).catch {
                val error = ErrorResponseHandler(it)
                logUtil.log(TAG, "onErrorResponse${error.getErrors().data}")
                _insertFavouriteItem.value = InsertFavouriteUIState.OnFailure(error.getErrors().toString())
            }.collect{
                logUtil.log(TAG, "onResponse: $it")
                _insertFavouriteItem.value = InsertFavouriteUIState.OnSuccess(it)
            }
        }
    }

    fun getFavouriteListResponse(){
        _getFavouriteItem.value = GetFavouriteListUIState.IsLoading(true)
        viewModelScope.launch {
            getFavouriteListUseCase.invoke().catch {
                val error = ErrorResponseHandler(it)
                logUtil.log(TAG, "onErrorResponse${error.getErrors().data}")
                _getFavouriteItem.value = GetFavouriteListUIState.OnFailure(error.getErrors().toString())
            }.collect{
                logUtil.log(TAG, "onResponse: $it")
                _getFavouriteItem.value = GetFavouriteListUIState.OnSuccess(it)
            }
        }
    }

    fun clearFavouriteData() {
        _insertFavouriteItem.value = null
    }

    fun getFilterList(): ArrayList<FilterListResponse> {
        var filterListRes = ArrayList<FilterListResponse>()
        for (i in 0..10) {
            filterListRes.add(FilterListResponse("Rajma Chawal", false))
        }
        filterListRes.add(0, FilterListResponse("Foods", true))
        filterListRes.add(1, FilterListResponse("Drinks", false))
        filterListRes.add(2, FilterListResponse("Snacks", false))
        return filterListRes
    }

    companion object {
        val TAG = HomeViewModel::class.java.simpleName
    }
}
