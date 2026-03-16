# Short Video Platform

```text
short-video-platform/
├── backend/
│   ├── .mvn/
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── uploads/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── yunfan/
│   │   │   │           └── backend/
│   │   │   │               ├── BackendApplication.java
│   │   │   │               ├── config/
│   │   │   │               │   ├── RedisConfig.java
│   │   │   │               │   └── SecurityConfig.java
│   │   │   │               ├── common/
│   │   │   │               │   └── exception/
│   │   │   │               │       ├── AuthenticationException.java
│   │   │   │               │       ├── GlobalExceptionHandler.java
│   │   │   │               │       └── ResourceConflictException.java
│   │   │   │               ├── user/
│   │   │   │               │   ├── User.java
│   │   │   │               │   ├── UserController.java
│   │   │   │               │   ├── UserRepository.java
│   │   │   │               │   ├── UserService.java
│   │   │   │               │   └── dto/
│   │   │   │               │       ├── LoginRequest.java
│   │   │   │               │       ├── RegisterRequest.java
│   │   │   │               │       └── UserResponse.java
│   │   │   │               └── video/
│   │   │   │                   ├── FeedController.java
│   │   │   │                   ├── Video.java
│   │   │   │                   ├── VideoController.java
│   │   │   │                   ├── VideoRepository.java
│   │   │   │                   ├── VideoService.java
│   │   │   │                   └── dto/
│   │   │   │                       ├── CreateVideoRequest.java
│   │   │   │                       ├── CreateVideoUploadRequest.java
│   │   │   │                       ├── LocalVideoUploadResponse.java
│   │   │   │                       └── VideoResponse.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── target/
├── frontend/
├── docker/
│   └── docker-compose.yml
├── docs/
├── .gitignore
└── README.md
```