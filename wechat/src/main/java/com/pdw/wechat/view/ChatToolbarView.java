package com.pdw.wechat.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pdw.wechat.MessageSender;
import com.pdw.wechat.R;
import com.pdw.wechat.entity.TextMessage;
import com.pdw.wechat.entity.User;
import com.pdw.wechat.util.KeyboardUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类描述
 *
 * @author Corgi
 * @date 2021/2/15
 */
public class ChatToolbarView extends LinearLayout implements View.OnClickListener, KeyboardWatchLayout.KeyboardStateChangedListener {

    private RecyclerView mRecyclerView;
    private ViewGroup mInputLayout;
    private Button mSend;
    private View mMore;

    private MessageSender mMessageSender;

    public ChatToolbarView(@NonNull Context context) {
        this(context, null);
    }

    public ChatToolbarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatToolbarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void showMoreFun(boolean show) {
        mRecyclerView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void setMessageSender(MessageSender mMessageSender) {
        this.mMessageSender = mMessageSender;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        onViewCreated(this);
    }

    private EditText mInput;

    private void onViewCreated(@NonNull View view) {
        mInputLayout = view.findViewById(R.id.rl_chat_input);
        mRecyclerView = view.findViewById(R.id.rv_more_list);
        mInput = view.findViewById(R.id.et_input);

        mSend = view.findViewById(R.id.btn_send);
        mMore = view.findViewById(R.id.iv_more);

        mInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    mSend.setVisibility(View.VISIBLE);
                    mMore.setVisibility(View.GONE);
                } else {
                    mSend.setVisibility(View.GONE);
                    mMore.setVisibility(View.VISIBLE);
                }
            }
        });

        mRecyclerView.setAdapter(new ListAdapter(view.getContext()));

        view.findViewById(R.id.iv_voice).setOnClickListener(this);
        view.findViewById(R.id.iv_emoji).setOnClickListener(this);
        view.findViewById(R.id.iv_more).setOnClickListener(this);
        mSend.setOnClickListener(this);
    }

    private static class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final List<String> data = new ArrayList<>();

        public ListAdapter(Context ctx) {
            String[] array = ctx.getResources().getStringArray(R.array.fun_array);
            Collections.addAll(data, array);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(View.inflate(parent.getContext(), R.layout.view_fun_icon, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private boolean keyboardActive;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_emoji) {

        } else if (view.getId() == R.id.iv_more) {
            if (!keyboardActive) {
                KeyboardUtils.showKeyboard(getContext(), mInput);
            } else {
                mRecyclerView.setVisibility(View.VISIBLE);
                mInput.clearFocus();
                KeyboardUtils.hideKeyboard(getContext(), mInputLayout);
            }
        } else if (view.getId() == R.id.iv_voice) {

        } else if (view.getId() == R.id.btn_send) {
            if (mMessageSender != null) {
                mMessageSender.send(new TextMessage().setText(mInput.getText().toString()).setFrom(User.ME));
                mInput.setText("");
            }
        }
    }

    private boolean hasSetup;

    @Override
    public void onKeyboardStateChanged(boolean active, int keyboardHeight) {
        keyboardActive = active;
        if (active) {
            if (!hasSetup) {
                ViewGroup.LayoutParams lp = mRecyclerView.getLayoutParams();
                lp.height = keyboardHeight + getResources().getDimensionPixelSize(R.dimen.dp_1);
                mRecyclerView.setLayoutParams(lp);
                hasSetup = true;
            }
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(String title) {
            ((TextView) (itemView.findViewById(R.id.tv_title))).setText(title);
            itemView.setOnClickListener(v -> {
                Toast.makeText(itemView.getContext(), "open:" + title, Toast.LENGTH_SHORT).show();
            });
        }
    }
}
