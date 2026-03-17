# Short Video Platform

## Project Structure

```text
short-video-platform/
├── backend/
│   ├── .gitattributes
│   ├── .gitignore
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── .mvn/
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
│   ├── .gitignore
│   ├── README.md
│   ├── eslint.config.js
│   ├── index.html
│   ├── package-lock.json
│   ├── package.json
│   ├── tsconfig.app.json
│   ├── tsconfig.json
│   ├── tsconfig.node.json
│   ├── vite.config.ts
│   ├── node_modules/
│   ├── public/
│   └── src/
│       ├── api/
│       │   ├── authApi.ts
│       │   ├── client.ts
│       │   └── videoApi.ts
│       ├── pages/
│       │   ├── FeedPage.tsx
│       │   ├── LoginPage.tsx
│       │   ├── RegisterPage.tsx
│       │   └── UploadPage.tsx
│       ├── types/
│       │   ├── auth.ts
│       │   └── video.ts
│       ├── App.tsx
│       ├── index.css
│       ├── main.tsx
│       ├── router.tsx
│       └── vite-env.d.ts
├── docker/
│   └── docker-compose.yml
├── docs/
├── .gitignore
└── README.md
```

## Dev Commands

```bash
# ===== frontend =====
cd frontend
npm install              # install deps
npm run dev              # start dev server

# ===== backend =====
cd backend
./mvnw spring-boot:run   # run backend
./mvnw clean package     # build jar

# ===== docker =====
cd docker

docker compose up -d     # start containers
docker compose down -v   # stop and remove containers
docker compose start     # start existing containers
docker compose stop      # stop without removing

docker ps                # list running containers

# ===== postgres =====
docker exec -it svp-postgres psql -U postgres   # enter psql
\c short_video_platform                         # use db
\dt                                             # list tables

SELECT id, username, email FROM users;          # users
SELECT id, user_id, description FROM videos;    # videos

\q                                              # exit

# ===== api test =====
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"bob","email":"bob@test.com","password":"123456"}'

curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"bob@test.com","password":"123456"}'

curl http://localhost:8080/feed                 # fetch feed
```