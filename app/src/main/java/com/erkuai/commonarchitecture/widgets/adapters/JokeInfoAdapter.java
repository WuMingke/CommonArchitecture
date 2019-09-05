package com.erkuai.commonarchitecture.widgets.adapters;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.bean.JokeInfo;
import com.erkuai.commonarchitecture.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2019/8/9.
 */

public class JokeInfoAdapter extends BaseQuickAdapter<JokeInfo, BaseViewHolder> {

    public JokeInfoAdapter(@Nullable List<JokeInfo> data) {
        super(R.layout.adapter_book_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JokeInfo item) {

        ImageView cover = helper.getView(R.id.cover);
        ImageLoader.loadImage(item.getHeader(), cover);

        TextView title = helper.getView(R.id.title);
        title.setText(item.getText());

        TextView author = helper.getView(R.id.author);
        author.setText(item.getName());

        Log.i("wmk","数据："+item.getText());
    }
}
