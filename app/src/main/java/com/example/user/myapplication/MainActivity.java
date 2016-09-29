package com.example.user.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1, CIRCLE = 2, SQ = 3;
    static int curShape = LINE;
    static int color = 1;
    static int size = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.test01);
        setTitle("간단한 그림판");

        setContentView(new MyGraphicView(this));

    }
    //=====================================================================================================//
    //메뉴생성

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);



        menu.add(0, 1, 0, "선그리기");
        menu.add(0, 2, 0, "원그리기");
        menu.add(0, 3, 0, "사각형그리기");



        SubMenu sMenu = menu.addSubMenu("색상변경==>>");

        sMenu.add(0, 4, 0, "빨강색");
        sMenu.add(0, 5, 0, "파랑색");
        sMenu.add(0, 6, 0, "초록색");

        sMenu.add(0, 7, 0, "사이즈업");
        sMenu.add(0, 8, 0, "사이즈다운");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case 1:
                curShape = LINE;
                return true;
            case 2:
                curShape = CIRCLE;
                return true;
            case 3:
                curShape = SQ;
                return true;
            case 4:
                color = 1;
                return true;
            case 5:
                color = 2;
                return true;
            case 6:
                color = 3;
                return true;
            case 7:
                size += 5;
                return true;
            case 8:
                size -= 5;
                return true;

        }

        return super.onOptionsItemSelected(item);

    }

    //=====================================================================================================//
    private static class MyGraphicView extends View {

        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;

            }

            return true;

        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(size);

            if (color == 1) {
                paint.setColor(Color.RED);
            } else if (color == 2) {
                paint.setColor(Color.BLUE);
            } else {
                paint.setColor(Color.GREEN);
            }

            switch (curShape) {

                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case SQ:
                    canvas.drawRect(startX, startY, stopX, stopY, paint);
                    break;

            }

        }

        public MyGraphicView(Context context) {
            super(context);
        }


    }
}