# AndroidUtis

This library provides some basic/advanced Android utils:

* [Context](https://developer.android.com/reference/android/content/Context) Everywhere: 
  Access context anywhere in the app.

* Kotlin extentions: 
 Useful and common Kotlin extentions.
 
* Gson integration
 
* Cache


## Installation

You can include the library in your project using [Jitpack](https://jitpack.io)

### Gradle
* **Step 1.** Add the JitPack repository to your root build.gradle at the end of repositories:

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

* **Step 2.** Add the dependency:

```groovy
dependencies {
	implementation 'com.github.oth-libs:AndroidUtils:0.0.1'
}
```

### Maven
* **Step 1.** Add the JitPack repository to your build file:

```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

* **Step 2.** Add the dependency:

```xml
<dependency>
    <groupId>com.github.oth-libs</groupId>
    <artifactId>AndroidUtils</artifactId>
    <version>0.0.1</version>
</dependency>
```


### SBT
* **Step 1.** Add the JitPack repository to your build.sbt at the end of resolvers:

```groovy
resolvers += "jitpack" at "https://jitpack.io"
```

* **Step 2.** Add the dependency:

```xml
libraryDependencies += "com.github.oth-libs" % "AndroidUtils" % "0.0.1"
```

### LEININGEN
* **Step 1.** Add the JitPack repository to your project.clj at the end of repositories:

```groovy
:repositories [["jitpack" "https://jitpack.io"]]
```

* **Step 2.** Add the dependency:

```xml
:dependencies [[com.github.oth-libs/AndroidUtils "0.0.1"]]
```

 
## How to
To use all the library features, make ```com.oth.utils.UtilsApplication``` the main application name, or extend from it in case you need your own ```android.app.Application``` implementation.

```xml
<application
    android:name="com.oth.utils.UtilsApplication"
    <.../>
</application>
```

### Examples  
*See the sample project for more details*

* Context Everywhere: Access context anywhere using:

```kotlin
ContextHelper.context()
```

* Cache: Cache and read complex objects:

```kotlin
Hawk.put(DATA_KEY, obj)
//
val obj = Hawk.get<ComplexObject>(DATA_KEY, null)
```
  

## Authors

* **Othman El JAzouli** on [LinkedIn](https://www.linkedin.com/in/eljazouliothman/)

## License

The MIT License (MIT)

Copyright (c) 2013-2017 Nacho Lopez

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Acknowledgments

* me, myself and I... for now
