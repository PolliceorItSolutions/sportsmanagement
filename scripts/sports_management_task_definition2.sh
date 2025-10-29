aws ecs register-task-definition \
    --family sportsmanagement \
    --requires-compatibilities FARGATE \
    --network-mode awsvpc \
    --cpu 256 \
    --memory 512 \
    --execution-role-arn arn:aws:iam::860756134448:role/ecs-task-execution-role \
    --task-role-arn arn:aws:iam::860756134448:role/ecs-task-execution-role \
    --container-definitions '[
      {
        "name": "sports_app",
        "image": "860756134448.dkr.ecr.us-east-1.amazonaws.com/sportsmanagement:prod",
        "portMappings": [
          {
            "containerPort": 8080,
            "protocol": "tcp"
          }
        ],
        "environment": [
          {
            "name": "SPRING_PROFILES_ACTIVE",
            "value": "prod"
          },
          {
            "name": "AWS_SECRETS_ENABLED",
            "value": "true"
          },
          {
            "name": "AWS_SECRETSMANAGER_REGION",
            "value": "us-east-1"
          }
        ],
        "healthCheck": {
          "command": ["CMD-SHELL", "wget --no-verbose --tries=1 --spider http://localhost:8080/sportsmanagementservices/health || exit 1"],
          "interval": 30,
          "timeout": 5,
          "retries": 3,
          "startPeriod": 60
        }
      }
    ]'
