Observable结合AsyncTask实现的一个带回调的异步任务
  ----
###	1.设计目的
该类的设计旨在省去书写异步任务时，再次添加回调的方法
###	2.使用

a.	直接声明使用
```java
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
```

b.	继承CommTask,根据需要，`startTask`时无参数时重写`doInBackGround()`方法，带参数则重写`doInBackGround(A  a)`方法
```java
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
```
###	3.待完善的部分
CommonTask目前只支持一种类型的回调监听．．.

