# ToppingClient

## Topping 플랫폼을 위한 간단한 Java, Skript API  
간단하게 상품 정보를 불러와 자신의 플러그인 및 스크립트에 update checker를 넣어보세요.  
해당 API는 스크립트 애드온으로도, 플러그인으로도 활용하실 수 있습니다.

- 상품 정보 가져오기(Java)
``` java
ToppingClient.getProductInfo(long userId, long productId)  
ToppingClient.getAsyncProductInfo(long userid, long productId,@Nullable Consumer<@NotNull ToppingProduct> thenAccent) 
```

- 상품 정보 가져오기(Skript)
```
on load:
	set {_t} to request for user id of <유저 id> and product id of <상품 id>
	broadcast name of {_t}
	broadcast version of latest version of {_t}
	loop description of {_t}:
		broadcast loop-value
```
- 의존성 추가하기
``` groovy
repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github:toxicity188:ToppingClient:master'
}
```
