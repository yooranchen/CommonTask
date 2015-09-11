package org.yooranchen.commontask.common;

import android.content.Context;

/**
 * TestTask1.java
 *
 * @author yooranchen
 * 
 * @DESC: 实例，可根据需要定制和重写方法
 * @DATE: 2015年9月11日 上午9:46:57
 */
public class TestTask1 extends CommonTask<Void, Void> {

	private Context mContext;

	/**
	 * 可传入参数
	 * 
	 * @param context
	 */
	public TestTask1(Context context) {
		super();
		mContext = context;
	}

	public void startTask(Context context) {
		mContext = context;
		super.startTask();
	}

	public void startTask(Void a, Context context) {
		mContext = context;
		super.startTask(a);
	}

	@Override
	public Void doInBackground() {
		return super.doInBackground();
	}

	@Override
	public Void doInBackground(Void params) {
		return super.doInBackground(params);
	}
}
