package org.yooranchen.commontask.common;

import android.os.AsyncTask;
import android.util.Log;

import org.yooranchen.commontask.callback.Observer;

/**
 * com.douhaoxinxi.blueiasc.common.task
 * <p/>
 * Created by yooranchen on 2015-09-11 08:32 .
 * <p/>
 * Desc :使用观察者模式结合异步线程 ,泛型A,B是异步任务声明时传入的三个类型中的传入参数和结果参数
 * <p/>
 * 可以省去异步任务设置回调的过程
 * <p/>
 * A >> 异步任务启动时传入的参数
 * <p/>
 * B >> 回调中返回的参数类型
 */
public abstract class CommonTask<T> {

    private Observer<T> mObserver;

    public void setObserver(Observer<T> observer) {
        mObserver = observer;
    }

    /**
     * 启动任务
     */
    public void startTask() {
        new Task().execute();
    }


    /**
     * CommonTask.java
     *
     * @author yooranchen
     * @DESC: 类中的异步任务
     * @DATE: 2015年9月11日 上午8:59:56
     */
    private class Task extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            CommonTask.this.doInBackground();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            if (null != mObserver) {
                if (b) {
                    mObserver.onCompleted();
                } else {
                    mObserver.onError(new Exception("出错拉!!!"));
                }
            }
        }
    }

    public void notifyObservers(T t) {
        if (null != mObserver) {
            mObserver.onNext(t);
        }
    }

    /**
     * 异步任务执行方法，为子线程操作
     *
     * @return
     */
    public Boolean doInBackground() {
        return null;
    }

}
