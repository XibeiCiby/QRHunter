// Generated by view binder compiler. Do not edit!
package com.example.qrhunter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.qrhunter.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RankInOwnerContentBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView totalScoreOwnerPageTextview;

  @NonNull
  public final TextView usernameOwnerPageTextview;

  private RankInOwnerContentBinding(@NonNull LinearLayout rootView, @NonNull TextView textView4,
      @NonNull TextView totalScoreOwnerPageTextview, @NonNull TextView usernameOwnerPageTextview) {
    this.rootView = rootView;
    this.textView4 = textView4;
    this.totalScoreOwnerPageTextview = totalScoreOwnerPageTextview;
    this.usernameOwnerPageTextview = usernameOwnerPageTextview;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RankInOwnerContentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RankInOwnerContentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.rank_in_owner_content, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RankInOwnerContentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.total_score_owner_page_textview;
      TextView totalScoreOwnerPageTextview = ViewBindings.findChildViewById(rootView, id);
      if (totalScoreOwnerPageTextview == null) {
        break missingId;
      }

      id = R.id.username_owner_page_textview;
      TextView usernameOwnerPageTextview = ViewBindings.findChildViewById(rootView, id);
      if (usernameOwnerPageTextview == null) {
        break missingId;
      }

      return new RankInOwnerContentBinding((LinearLayout) rootView, textView4,
          totalScoreOwnerPageTextview, usernameOwnerPageTextview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
