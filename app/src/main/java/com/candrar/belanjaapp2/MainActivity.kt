package com.candrar.belanjaapp2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.candrar.belanjaapp2.adapter.ListProductAdapter
import com.candrar.belanjaapp2.data.model.Product
import com.candrar.belanjaapp2.ext.toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var list: ArrayList<Product> = arrayListOf()

    private var listProductAdapter: ListProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showListProducts()
        setupAddProduct()
        setListClickAction()
    }

    private fun showListProducts() = App.instance.repository.get({
        list.addAll(it)

        listProductAdapter = ListProductAdapter(list)

        rvProducts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listProductAdapter
            setHasFixedSize(true)
        }

        setListClickAction()

    }, {
        it.printStackTrace()
        it.message?.toast(this)
    })

    private fun setupAddProduct() {
        btnAddProduct.setOnClickListener {
            startActivityForResult(
                DetailProductActivity.addIntent(this),
                DetailProductActivity.REQUEST_CODE_DETAIL_PRODUCT
            )
        }
    }

    private fun setListClickAction() {
        listProductAdapter?.setOnItemClickCallback(object : ListProductAdapter.OnItemClickCallback {
            override fun onItemClick(data: Product) {
                startActivityForResult(
                    DetailProductActivity.editIntent(this@MainActivity, data),
                    DetailProductActivity.REQUEST_CODE_DETAIL_PRODUCT
                )
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DetailProductActivity.REQUEST_CODE_DETAIL_PRODUCT &&
            resultCode == DetailProductActivity.RESULT_CODE_RELOAD_DATA
        ) {
            list = arrayListOf()
            showListProducts()
        }

    }
}