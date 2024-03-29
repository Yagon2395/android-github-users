package com.example.githubusers_testecriar_yago.views.fragments

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import com.example.githubusers_testecriar_yago.R
import com.example.githubusers_testecriar_yago.models.entities.User
import kotlinx.android.synthetic.main.fragment_user_detail.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UserDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UserDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable("user")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWebView()
        setClickListeners()

        repo_web_view.loadUrl(param1?.html_url)
    }

    private fun setClickListeners() {
        repo_back_button.setOnClickListener {
            repo_web_view.goBack()
        }

        repo_forward_button.setOnClickListener {
            repo_web_view.goForward()
        }

        repo_refresh_button.setOnClickListener {
            repo_web_view.reload()
        }
    }

    private fun setupWebView() {
        repo_web_view.setInitialScale(1)
        val webSettings = repo_web_view.settings
        webSettings.setAppCacheEnabled(false)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.domStorageEnabled = true

        repo_web_view.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (repo_back_button != null && repo_forward_button != null && repo_web_view != null && repo_progress_view != null) {
                    repo_back_button.isEnabled = repo_web_view.canGoBack()
                    repo_forward_button.isEnabled = repo_web_view.canGoForward()
                    repo_progress_view.visibility = View.VISIBLE
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (repo_back_button != null && repo_forward_button != null && repo_web_view != null && repo_progress_view != null) {
                    repo_back_button.isEnabled = repo_web_view.canGoBack()
                    repo_forward_button.isEnabled = repo_web_view.canGoForward()
                    repo_progress_view.visibility = View.GONE
                }
            }
        }
    }


}
