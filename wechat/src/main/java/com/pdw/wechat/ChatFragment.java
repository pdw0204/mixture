package com.pdw.wechat;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pdw.assets.MediaUtils;
import com.pdw.wechat.entity.ImageMessage;
import com.pdw.wechat.entity.MediaMessage;
import com.pdw.wechat.entity.Message;
import com.pdw.wechat.entity.TextMessage;
import com.pdw.wechat.entity.TimeMessage;
import com.pdw.wechat.entity.User;
import com.pdw.wechat.view.ChatToolbarView;
import com.pdw.wechat.view.KeyboardWatchLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/16
 */
public class ChatFragment extends Fragment implements MessageSender, KeyboardWatchLayout.KeyboardStateChangedListener {
    private RecyclerView mChatListView;
    private BaseAdapter mAdapter;

    private ChatToolbarView mChatToolView;

    public final static int REQUEST_CODE = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(container.getContext(), R.layout.fragment_chat, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mChatListView = view.findViewById(R.id.rv_chat_list);
        mChatListView.addItemDecoration(new ChatListDecoration(getContext()));
        mAdapter = new BaseAdapter(new ArrayList<>());
        mChatListView.setAdapter(mAdapter);

        mChatToolView = view.findViewById(R.id.chat_tool_view);
        mChatToolView.setMessageSender(this);

        view.findViewById(R.id.iv_nav_return).setOnClickListener(v -> getActivity().finish());
        String title = "Chat";
        if (getArguments() != null) {
            title = getArguments().getString("title");
            if (TextUtils.isEmpty(title)) {
                title = "Chat";
            }
        }
        ((TextView) view.findViewById(R.id.tv_title)).setText(title);

        mChatToolView.setHost(new ChatToolbarView.Host() {
            @Override
            public Activity getActivity() {
                return getActivity();
            }

            @Override
            public Fragment getFragment() {
                return ChatFragment.this;
            }
        });

        for (int i = 0; i < 2; i++) {
            TextMessage message = new TextMessage();
            message.setId(System.nanoTime());
            message.setFrom(i % 3 == 0 ? User.ME : i);
            message.setTo(User.ME);
            message.setCtx(getContext());
            message.setText("这是一条来自demo的消息");
            mAdapter.add(message);
        }
    }

    @Override
    public boolean send(Message message) {
        message.setCtx(getContext());
        if (System.currentTimeMillis() % 2 == 0) {
            mAdapter.add(new TimeMessage(getContext()));
        }
        mAdapter.add(message);
        scrollToBottom();
        return false;
    }

    private void scrollToBottom() {
        mChatListView.scrollToPosition(mAdapter.getItemCount() - 1);
    }

    @Override
    public void onKeyboardStateChanged(boolean active, int keyboardHeight) {
        mChatToolView.onKeyboardStateChanged(active, keyboardHeight);
        if (active) {
            scrollToBottom();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            sendImageMessage(MediaUtils.parseContentData(getContext(), data));
        }
    }

    private void sendImageMessage(String imagePath) {
        if (TextUtils.isEmpty(imagePath)) {
            return;
        }
        MediaMessage mediaMessage = new ImageMessage();
        mediaMessage.setFrom(User.ME);
        mediaMessage.setUri(imagePath);
        mediaMessage.setCtx(getContext());
        send(mediaMessage);
    }

    /******************************** 内部类  *******************************/

    private static class BaseAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final List<Message> messageList;

        public BaseAdapter(List<Message> messageList) {
            this.messageList = messageList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(parent.getContext());
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(messageList.get(position));
            Log.e("pdw", "position:" + position);
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }

        void add(Message message) {
            messageList.add(message);
            notifyItemInserted(messageList.size() - 1);
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(Context ctx) {
            super(View.inflate(ctx, R.layout.view_chat_item_container, null));
        }

        void bind(Message message) {
            ViewGroup parent = (ViewGroup) itemView;
            parent.removeAllViews();
            parent.addView(message.getViewHandler().getView(message));

            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            if (lp == null) {
                lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                itemView.setLayoutParams(lp);
            }
        }
    }
}
