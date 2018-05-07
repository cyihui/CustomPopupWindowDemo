package com.cyh.custompopupwindow;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cyh.custompopupwindow.widget.CustomPopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Activity mActivity;
    private TextView tvShow;
    private TextView tvShowBottom;
    private Spinner spinner_vertical;
    private Spinner spinner_horizontal;
    private Spinner spinner_width;
    private Spinner spinner_height;
    private CheckBox checkbox_fit_in_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        tvShow = findViewById(R.id.tv_show);
        tvShowBottom = findViewById(R.id.tv_show_bottom);
        tvShowBottom.setOnClickListener(this);
        tvShow.setOnClickListener(this);
        initPopupWindow();

        spinner_vertical = findViewById(R.id.spinner_vertical);
        ArrayAdapter<String> adapterVertical = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterVertical.addAll(getResources().getStringArray(R.array.vertical_positions));
        adapterVertical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vertical.setAdapter(adapterVertical);

        spinner_horizontal = findViewById(R.id.spinner_horizontal);
        ArrayAdapter<String> adapterHorizonal = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterHorizonal.addAll(getResources().getStringArray(R.array.horizontal_positions));
        adapterHorizonal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_horizontal.setAdapter(adapterHorizonal);

        spinner_width = findViewById(R.id.spinner_width);
        ArrayAdapter<String> adapterWidth = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterWidth.addAll(getResources().getStringArray(R.array.width));
        adapterWidth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_width.setAdapter(adapterWidth);

        spinner_height = findViewById(R.id.spinner_height);
        ArrayAdapter<String> adapterHeight = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterHeight.addAll(getResources().getStringArray(R.array.height));
        adapterHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_height.setAdapter(adapterHeight);

        checkbox_fit_in_screen = findViewById(R.id.checkbox_fit_in_screen);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_show:
                switch (spinner_width.getSelectedItemPosition()) {
                    case 0:
                        mCustomPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                        break;
                    case 1:
                        mCustomPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                        break;
                    case 2:
                        mCustomPopupWindow.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()));
                        break;
                    case 3:
                        mCustomPopupWindow.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240, getResources().getDisplayMetrics()));
                        break;
                    case 4:
                        mCustomPopupWindow.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 480, getResources().getDisplayMetrics()));
                        break;
                    default:
                        throw new IllegalStateException();
                }

                switch (spinner_width.getSelectedItemPosition()) {
                    case 0:
                        mCustomPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        break;
                    case 1:
                        mCustomPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                        break;
                    case 2:
                        mCustomPopupWindow.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()));
                        break;
                    case 3:
                        mCustomPopupWindow.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240, getResources().getDisplayMetrics()));
                        break;
                    case 4:
                        mCustomPopupWindow.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 480, getResources().getDisplayMetrics()));
                        break;
                    default:
                        throw new IllegalStateException();
                }
                final int vertPos;
                switch (spinner_vertical.getSelectedItemPosition()) {
                    case 0:
                        vertPos = CustomPopupWindow.VerticalPosition.ABOVE;
                        break;
                    case 1:
                        vertPos = CustomPopupWindow.VerticalPosition.ALIGN_BOTTOM;
                        break;
                    case 2:
                        vertPos = CustomPopupWindow.VerticalPosition.CENTER;
                        break;
                    case 3:
                        vertPos = CustomPopupWindow.VerticalPosition.ALIGN_TOP;
                        break;
                    case 4:
                        vertPos = CustomPopupWindow.VerticalPosition.BELOW;
                        break;
                    default:
                        throw new IllegalStateException();
                }
                final int horizPos;
                switch (spinner_horizontal.getSelectedItemPosition()) {
                    case 0:
                        horizPos = CustomPopupWindow.HorizontalPosition.LEFT;
                        break;
                    case 1:
                        horizPos = CustomPopupWindow.HorizontalPosition.ALIGN_RIGHT;
                        break;
                    case 2:
                        horizPos = CustomPopupWindow.HorizontalPosition.CENTER;
                        break;
                    case 3:
                        horizPos = CustomPopupWindow.HorizontalPosition.ALIGN_LEFT;
                        break;
                    case 4:
                        horizPos = CustomPopupWindow.HorizontalPosition.RIGHT;
                        break;
                    default:
                        throw new IllegalStateException();
                }
                final boolean fitInScreen = checkbox_fit_in_screen.isChecked();
                mCustomPopupWindow.showPopupWindow(mActivity, tvShow, vertPos, horizPos, 0, 0, fitInScreen);
                break;


            case R.id.tv_show_bottom:
                showBottomPopupWindow();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    WindowManager.LayoutParams params;
    CustomPopupWindow mCustomPopupWindow;

    private void initPopupWindow() {
        mCustomPopupWindow = new CustomPopupWindow(mActivity);
//        params = getWindow().getAttributes();
//
//        params.alpha = 0.7f;
//        getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        mCustomPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
        mCustomPopupWindow.setOnItemClickListener(new CustomPopupWindow.onItemClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(mActivity, "onclick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showBottomPopupWindow() {
        dismiss();
        mCustomPopupWindow.showBottom(mActivity);
    }



    private void dismiss() {
        if (mCustomPopupWindow == null) {
            return;
        }

        if (mCustomPopupWindow.isShowing()) {
            mCustomPopupWindow.dismiss();
        }
    }
}
