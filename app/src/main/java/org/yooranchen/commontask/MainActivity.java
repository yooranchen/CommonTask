package org.yooranchen.commontask;

import java.util.Observable;
import java.util.Observer;

import org.yooranchen.commontask.common.CommonTask;
import org.yooranchen.commontask.common.TestTask;

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

		// 第一种实现方式，集成
		findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mText.append("启动异步任务1\n");

				TestTask task = new TestTask();
				// 务必先添加观察回调借口
				task.addObserver(new Observer() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see java.util.Observer#update(java.util.Observable,
					 * java.lang.Object) data 为泛型中的Ｂ
					 */
					public void update(Observable observable, final Object data) {
						// 参数在这里强转成需要个类型 (String )data,(boolean)data ,
						mText.append((CharSequence) data);
						mText.append("\n");
					}
				});
				// 启动任务
				task.startTask();
			}
		});

		// 第二种实现方式，直接声明
		findViewById(R.id.btn_start1).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mText.append("启动异步任务2\n");
				CommonTask<String, String> task = new CommonTask<String, String>() {
					@Override
					public String doInBackground(String params) {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return "参数为>>" + params + "＼n休眠３秒异步任务２执行结束";
					}
				};
				// 添加回调
				task.addObserver(new Observer() {
					public void update(Observable observable, Object data) {
						// 参数在这里强转成需要个类型 (String )data,(boolean)data ,
						mText.append((CharSequence) data);
						mText.append("\n");
					}
				});
				// 启动任务
				task.startTask("这是异步任务2");
			}
		});
	}
}
