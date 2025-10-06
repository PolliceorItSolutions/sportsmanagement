# Build stage
FROM maven:3.9-eclipse-temurin-17-alpine as builder
WORKDIR /build

# Copy only pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine

# Install additional dependencies if needed
RUN apk add --no-cache tzdata

# Create non-root user
RUN addgroup -S app && adduser -S app -G app

# Create necessary directories with proper permissions
RUN mkdir /app && chown -R app:app /app

# Set working directory
WORKDIR /app

# Copy the jar from builder stage
COPY --from=builder /build/target/*.jar app.jar

# Switch to non-root user
USER app

# Configure environment
ENV SPRING_PROFILES_ACTIVE=prod
ENV TZ=UTC
ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -Djava.security.egd=file:/dev/./urandom \
               -XX:+ExitOnOutOfMemoryError \
               -XX:+HeapDumpOnOutOfMemoryError \
               -XX:HeapDumpPath=/app/heap-dump.hprof"

# Expose the application port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/sportsmanagementservices/health || exit 1

# Start the application
ENTRYPOINT ["sh", "-c", "exec java ${JAVA_OPTS} -jar app.jar"]