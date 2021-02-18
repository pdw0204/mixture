package com.pdw.wechat;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public class ChatListDecoration extends RecyclerView.ItemDecoration {

    private int verticalPadding;

    public ChatListDecoration(Context ctx) {
        verticalPadding = ctx.getResources().getDimensionPixelSize(R.dimen.chat_list_item_vertical_padding);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(0, verticalPadding, 0, verticalPadding);
    }
}
