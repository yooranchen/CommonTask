package org.yooranchen.commontask.common;

import java.util.Observable;

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
public abstract class CommonTask<A, B> {

    private Observer mObserver;

    public void setObserver(Observer observer) {
        mObserver = observer;
    }

    /**
     * 启动任务
     */
    public void startTask() {
        new Task().execute();
    }

    /**
     * 启动异步参数
     *
     * @param a 异步任务传入参数，泛型，在新建子类时声明类型
     */
    public void startTask(A a) {
        new Task().execute(a);
    }

    /**
     * CommonTask.java
     *
     * @author yooranchen
     * @DESC: 类中的异步任务
     * @DATE: 2015年9月11日 上午8:59:56
     */
    private class Task extends AsyncTask<A, Void, B> {
        @SafeVarargs
        @Override
        protected final B doInBackground(A... params) {
            Log.e("doInBackground", "params>>>" + params + ">>length >>" + params.length);
            if (0 == params.length) {
                // 无参数
                return CommonTask.this.doInBackground();
            } else {
                // 有参数
                return CommonTask.this.doInBackground(params[0]);
            }
        }

        @Override
        protected void onPostExecute(B b) {
            super.onPostExecute(b);
            //主线程返回内容
            notifyObservers(b);
        }
    }

    public void notifyObservers(B b) {
        if (null != mObserver) {
            mObserver.update(b);
        }
    }

    /**
     * 异步任务执行方法，为子线程操作
     *
     * @return
     */
    public B doInBackground() {
        return null;
    }

    /**
     * 异步任务执行方法，带参数，为子线程操作
     *
     * @param a
     *
     * @return
     */
    public B doInBackground(A a) {
        return null;
    }
}
