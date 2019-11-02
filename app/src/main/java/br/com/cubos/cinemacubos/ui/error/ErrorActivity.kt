package br.com.cubos.cinemacubos.ui.error

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import br.com.cubos.cinemacubos.BR
import br.com.cubos.cinemacubos.R

class ErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
            .setContentView<ViewDataBinding>(this, R.layout.activity_error).apply {
                setVariable(BR.onClick, View.OnClickListener {
                    setResult(Activity.RESULT_OK)
                    finishAfterTransition()
                })
            }
    }
}
