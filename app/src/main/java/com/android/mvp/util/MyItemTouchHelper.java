package com.android.mvp.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by 徐文龙 on 2015/12/11.
 */
public class MyItemTouchHelper extends ItemTouchHelper.Callback {

    private OnRecyclerItemMoveLinestener onRecyclerItemMoveLinestener;

    public MyItemTouchHelper(OnRecyclerItemMoveLinestener onRecyclerItemMoveLinestener) {
        this.onRecyclerItemMoveLinestener = onRecyclerItemMoveLinestener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN|ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT;

        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.v("dalong", "进行移动");
        onRecyclerItemMoveLinestener.onMoveYidong(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return false;
    }




    private boolean isDifferentItemViewType(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return viewHolder.getItemViewType() != target.getItemViewType();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.v("dalong","进行删除");
//        onRecyclerItemMoveLinestener.onMoceDelete(viewHolder.getAdapterPosition());
    }



    public interface  OnRecyclerItemMoveLinestener{
        public void onMoveYidong(int position1, int position2);
    }


}
