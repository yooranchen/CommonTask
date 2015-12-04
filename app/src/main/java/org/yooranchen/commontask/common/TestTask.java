package org.yooranchen.commontask.common;

/**
 * TestTask.java
 *
 * @author yooranchen
 * 
 * @DESC:
 * @DATE: 2015年9月11日 上午8:59:28
 * 
 *        doInBackground非抽象方法，可根据需要重写
 */
public class TestTask extends CommonTask<Void, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yooranchen.commontask.common.CommonTask#doInBackground()
	 * 根据需要，重写不带参数的方法
	 */
	@Override
	public String doInBackground() {
		// 进行异步操作
		try {
			Thread.sleep(3000);
			// 任务执行完毕
			return "休眠３秒异步任务执行完毕";
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
