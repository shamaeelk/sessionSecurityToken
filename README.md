# sessionSecurityToken

maven project for secure java object when pass into session.

## feature
	a. which encrypt java object return create a string token.
	b. decrypt security token and return java object

## Environment
- Java 8 or higher

## dependency
- com.google.code.gson

## Encryption method
```
public String SecurityToken.encodeToken(Object pojo);
```
### example:-
```
String token = SecurityToken.encodeToken(user);
```

## Decryption method
```
public <T> T SecurityToken.decodeToken(String token,Class<T> classType );
```
### example:-
```
User user = SecurityToken.decodeToken(token, User.class);
```

## Implementation
- download jar and set class path of jar into project

