package com.example.mrnotes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import com.google.common.truth.Truth.assertThat

class RecyclerViewItemCountAssertion(Actual:Int?) : ViewAssertion {
    var count:Int?=Actual
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (view is RecyclerView) {
            var expectedCount=view.adapter?.itemCount
            assertThat((expectedCount)).isEqualTo(count)
        }
    }
}
