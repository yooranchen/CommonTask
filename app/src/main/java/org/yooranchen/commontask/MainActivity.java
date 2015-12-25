package org.yooranchen.commontask;

import org.yooranchen.commontask.callback.Observer;
import org.yooranchen.commontask.common.CommonTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.tv_text);

        // 第二种实现方式，直接声明
        findViewById(R.id.btn_start1).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mText.append("启动异步任务2\n");
                CommonTask<String> task = new CommonTask<String>() {
                    public Boolean doInBackground() {
                        try {
                            //子线程通知刷新
                            notifyObservers("子线程开始更新");
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        notifyObservers("休眠3秒异步任务2执行结束");
                        return true;
                    }
                };
                // 添加回调
                task.setObserver(new Observer<String>() {
                    public void onNext(final String data) {
                        //存在子线程通知刷新的内容
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 参数在这里强转成需要个类型 (String )data,(boolean)data ,
                                mText.append(data + "\n");
                            }
                        });
                    }

                    @Override
                    public void onCompleted() {
                        mText.append("任务完成");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
                // 启动任务
                task.startTask();
            }
        });
    }
}
