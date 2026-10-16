# Spring Boot Field-Based Dependency Injection

## 📌 Project Overview

This project demonstrates **Field-Based Dependency Injection in Spring Boot** using annotations. The application implements a notification system with two services: Email and SMS.

Spring automatically injects the required dependencies into class fields using the `@Autowired` annotation. The `@Primary` and `@Qualifier` annotations help Spring decide which implementation to inject when multiple beans implement the same interface.

## 🎯 Objectives

- Understand field-based dependency injection.
- Learn how `@Autowired` injects dependencies automatically.
- Understand the use of `@Primary` and `@Qualifier`.
- Learn how to create and use Spring beans.
- Understand the purpose of `@PostConstruct`.
- Implement multiple services using a common interface.

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- Spring Core
- Maven
- Eclipse / Spring Tool Suite (STS)

## 📂 Project Structure

```text
DependencyInjectionApplication
│
├── src/main/java
│   └── com/example/demo
│       ├── DependencyInjectionApplication.java
│       ├── Message.java
│       ├── Email.java
│       ├── Sms.java
│       └── NotificationManager.java
│
├── src/main/resources
│   └── application.properties
│
├── pom.xml
└── README.md
```

## 📖 Classes and Annotations Explained

### 1. Message.java

`Message` is an interface that declares the `sendmessage()` method. Both the Email and SMS services implement this interface.

```java
public interface Message {
    void sendmessage();
}
```

### 2. Email.java

The `Email` class implements the `Message` interface.

- `@Component` registers the class as a Spring bean.
- `@Primary` makes Email the default bean when Spring needs to inject a single `Message` dependency and no specific bean is selected.

```java
@Component
@Primary
public class Email implements Message {
    public void sendmessage() {
        System.out.println("This is an Email Service ");
    }
}
```

### 3. Sms.java

The `Sms` class also implements the `Message` interface.

The `@Component` annotation registers it as a Spring bean. The `@Qualifier("sms")` annotation can select this bean explicitly because Spring's default bean name is `sms`.

```java
@Component
public class Sms implements Message {
    public void sendmessage() {
        System.out.println("This is a SMS Service");
    }
}
```

### 4. NotificationManager.java

This class demonstrates field-based dependency injection.

```java
@Component
public class NotificationManager {

    @Autowired
    private Message message;

    @Autowired
    @Qualifier("sms")
    private Message message1;

    public void showmessage() {
        message.sendmessage();
        message1.sendmessage();
    }
}
```

**Explanation:**

- `@Component` registers `NotificationManager` as a Spring bean.
- `@Autowired` injects the default `Message` implementation, which is `Email` because it has `@Primary`.
- `@Qualifier("sms")` explicitly selects the SMS bean for `message1`.
- `showmessage()` calls both services.

### 5. DependencyInjectionApplication.java

This is the main class of the Spring Boot application.

```java
@SpringBootApplication
public class DependencyInjectionApplication {

    @Autowired
    private NotificationManager notify;

    public static void main(String[] args) {
        SpringApplication.run(
            DependencyInjectionApplication.class, args);
    }

    @PostConstruct
    public void execute() {
        notify.showmessage();
    }
}
```

**Explanation:**

- `@SpringBootApplication` starts the application and enables component scanning and auto-configuration.
- `@Autowired` injects the `NotificationManager` bean.
- `@PostConstruct` runs the `execute()` method after the bean has been created and its dependencies injected.
- `notify.showmessage()` invokes both the Email and SMS services.

## 🔄 How Field-Based Dependency Injection Works

1. Spring Boot starts the application.
2. Spring scans the package and creates the required beans.
3. Spring identifies the `Message` implementations: Email and SMS.
4. `@Primary` selects Email for the unqualified `message` field.
5. `@Qualifier("sms")` selects SMS for the `message1` field.
6. Spring injects `NotificationManager` into the main application class.
7. After dependency injection, `@PostConstruct` executes `notify.showmessage()`.
8. Both notification services print their messages.

## 💻 Expected Output

```text
This is an Email Service
This is a SMS Service
```

Spring Boot may display additional startup logs in the console.

## ▶️ How to Run the Project

1. Open Eclipse or Spring Tool Suite (STS).
2. Import the Spring Boot Maven project.
3. Ensure the required Spring Boot dependencies are configured in `pom.xml`.
4. Open `DependencyInjectionApplication.java`.
5. Right-click and select **Run As → Spring Boot App**.
6. Check the console for the output.

## 📚 Advantages of Field-Based Dependency Injection

- Simple to write and understand.
- Requires fewer lines of code.
- Spring injects dependencies automatically.
- Useful for learning basic dependency injection concepts.

## ⚠️ Limitations of Field-Based Dependency Injection

- Dependencies are not explicitly visible through the constructor.
- Fields cannot be declared `final` for normal field injection.
- Unit testing can be more difficult because dependencies are injected directly into fields.
- Constructor injection is generally preferred for required dependencies in production applications.

## 📝 Conclusion

This project demonstrates field-based dependency injection using Spring Boot annotations. It shows how `@Autowired` injects dependencies, `@Primary` selects the default implementation, `@Qualifier` selects a specific implementation, and `@PostConstruct` executes a method after dependency injection.

The project provides a simple example of how Spring manages objects and connects multiple service classes through an interface.
