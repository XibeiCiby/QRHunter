// Generated by view binder compiler. Do not edit!
package com.example.qrhunter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.example.qrhunter.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class MyQrCodesLayoutBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final FrameLayout wrapper;

  private MyQrCodesLayoutBinding(@NonNull FrameLayout rootView, @NonNull FrameLayout wrapper) {
    this.rootView = rootView;
    this.wrapper = wrapper;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MyQrCodesLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MyQrCodesLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.my_qr_codes_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MyQrCodesLayoutBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    FrameLayout wrapper = (FrameLayout) rootView;

    return new MyQrCodesLayoutBinding((FrameLayout) rootView, wrapper);
  }
}
