package com.example.csc207simulator.game3.GameThreeBackend;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;


public class Grid extends GridView {
    /**
     * Grid builder
     */
    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);
        GameThreeEngine.getInstance().createGrid(context);
        setNumColumns(GameThreeEngine.getInstance().getWIDTH());
        setAdapter(new GridAdapter());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return GameThreeEngine.getInstance().getWIDTH() * GameThreeEngine.getInstance().getHEIGHT();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return GameThreeEngine.getInstance().getelement(position);
        }
    }
}