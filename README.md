# How to
#### To get a Git project into your build:

##### Step 1. Add the JitPack repository to your build file
###### Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
##### Step 2. Add the dependency
```
	dependencies {
	        implementation 'com.github.liweihua802:kotlin-base:0.01'
	}
```

## 屏幕适配目前自己使用ScreenMatch插件，参考ScreenMatch.txt
