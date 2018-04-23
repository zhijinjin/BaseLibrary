# BaseLibrary
网络请求基础包

通信使用的EventBus 

直接继承BaseHttpActivity或者BaseHttpFragment
支持get、post、put请求

回调重写方法：

  // 请求成功
  public void onHttpResponse(HttpResult result, int id){

  }
  
  //请求失败
  public void onHttpFailure(HttpResult result, int id){

  }

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.zhijinjin:BaseLibrary:v1.0'
	}
