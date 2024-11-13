
package kr.ac.kumoh.ce.s20220736.advanced_counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>(0)    // ㅉㅍ value=0 >> <Int>이기 때문에 저거 생략 가능
//    val count: LiveData<Int>
//        get() = _count
    val count: LiveData<Int> = _count   // 위와 같은 코드

    fun onAdd() {
        _count.value = _count.value?.plus(1)    // NULL이 아닐 때
        //_count.value = _count.value!!.plus(1)     // 절대 NULL이 아니다
    }

    fun onSub() {
        if ((_count.value ?: 0) > 0) {
            _count.value = _count.value?.minus(1)
        }
    }

    fun onReset() {
        _count.value = 0
    }
}