# Smart System Monitor & Auto-Scaler Backend

Production-grade backend system for monitoring application/server performance, analyzing health, and making intelligent auto-scaling decisions.

## 🚀 Features

- **Real-time Metrics Collection**: Track CPU, memory, and disk usage
- **Health Analysis**: Automated health status detection (HEALTHY/DEGRADED/CRITICAL)
- **Auto-Scaling Decisions**: Intelligent scaling recommendations based on resource usage
- **Alert System**: Automated alerts for critical conditions
- **RESTful API**: Clean, well-structured REST endpoints

## 🛠️ Tech Stack

- **Java 24** - Latest JDK
- **Spring Boot 3.5.10** - Framework
- **MySQL 8** - Persistent storage
- **Redis** - Caching layer
- **Maven** - Build & dependency management
- **Hibernate/JPA** - ORM
- **Lombok** - Boilerplate reduction

## 📋 Prerequisites

- Java 24+
- Maven 3.9+
- MySQL 8+
- Redis 7+
- Git

## ⚙️ Installation & Setup

### 1. Clone Repository
```bash
git clone <your-repo-url>
cd smart-scaler-backend
```

### 2. Configure Database
```sql
CREATE DATABASE smart_scaler_db;
CREATE USER 'scaler_user'@'localhost' IDENTIFIED BY 'scaler_pass_123';
GRANT ALL PRIVILEGES ON smart_scaler_db.* TO 'scaler_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Start Redis
```bash
redis-server
```

### 4. Configure Application
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/smart_scaler_db
spring.datasource.username=scaler_user
spring.datasource.password=scaler_pass_123
```

### 5. Run Application
```bash
./mvnw spring-boot:run
```

Server starts at: `http://localhost:8080`

## 📡 API Endpoints

### Metrics API
- `POST /api/metrics` - Submit new metric
- `GET /api/metrics` - Get all metrics
- `GET /api/metrics/latest` - Get latest metric
- `GET /api/metrics/server/{serverId}` - Get metrics by server

### Health Analysis API
- `POST /api/health/analyze/{serverId}` - Analyze server health
- `GET /api/health` - Get all health statuses
- `GET /api/health/{serverId}` - Get latest health for server

### Scaling API
- `POST /api/scaling/decide/{serverId}` - Make scaling decision
- `GET /api/scaling` - Get all scaling decisions
- `GET /api/scaling/pending` - Get pending decisions

### Alerts API
- `GET /api/alerts` - Get all alerts
- `GET /api/alerts/server/{serverId}` - Get alerts by server
- `GET /api/alerts/unacknowledged` - Get unacknowledged alerts
- `PUT /api/alerts/{id}/acknowledge` - Acknowledge alert

## 🧪 Example Usage

### Submit Metric
```bash
curl -X POST http://localhost:8080/api/metrics \
  -H "Content-Type: application/json" \
  -d '{
    "serverId": "server-001",
    "cpuUsage": 85.0,
    "memoryUsage": 90.0,
    "diskUsage": 60.0
  }'
```

### Analyze Health
```bash
curl -X POST http://localhost:8080/api/health/analyze/server-001
```

### Get Scaling Decision
```bash
curl -X POST http://localhost:8080/api/scaling/decide/server-001
```

## 🏗️ Architecture
```
┌─────────────────────────────────────┐
│         Controller Layer            │  ← REST API Endpoints
├─────────────────────────────────────┤
│         Service Layer               │  ← Business Logic
├─────────────────────────────────────┤
│         Repository Layer            │  ← Data Access
├─────────────────────────────────────┤
│  MySQL (Persistent) + Redis (Cache) │  ← Storage
└─────────────────────────────────────┘
```

### Database Schema
- **metrics**: Raw performance metrics
- **health_status**: Analyzed health data
- **scaling_decisions**: Auto-scaling recommendations
- **alerts**: System alerts

## 🎯 Key Design Decisions

- **Layered Architecture**: Clean separation of concerns
- **Repository Pattern**: Abstracted data access
- **DTO Pattern**: Separate request/response from entities
- **Automated Health Detection**: Threshold-based analysis
- **Intelligent Scaling**: Resource-based decision making

## 📈 Status Thresholds

- **HEALTHY**: < 60% average resource usage
- **DEGRADED**: 60-80% average resource usage
- **CRITICAL**: ≥ 80% average resource usage

## 🔧 Development

### Run Tests
```bash
./mvnw test
```

### Build JAR
```bash
./mvnw clean package
```

### Run JAR
```bash
java -jar target/smart-scaler-backend-0.0.1-SNAPSHOT.jar
```

## 🚢 Deployment

Ready for deployment to:
- Railway
- Heroku
- AWS Elastic Beanstalk
- Docker/Kubernetes

## 👨‍💻 Author

Built by Lakshya as a portfolio project demonstrating production-grade backend development.

## 📄 License

MIT License