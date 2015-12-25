package org.yooranchen.commontask.callback;

/**
 * org.yooranchen.commontask.callback
 * <p/>
 * <p/>
 * Created by yooranchen on 12/4/15.
 */
public interface Observer<T> {
    /**
     * 任务进行中,子线程执行
     *
     * @param t
     */
    void onNext(T t);

    /**
     * 任务完成,主线程执行
     */
    void onCompleted();

    /**
     * 报错,子线程执行
     */
    void onError(Throwable throwable);
}
