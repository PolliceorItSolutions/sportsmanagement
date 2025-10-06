aws ecs register-task-definition \
    --family sportsmanagement \
    --container-definitions '[{
        "name": "sports_app",
        "image": "860756134448.dkr.ecr.us-east-1.amazonaws.com/sportsmanagement:prod",
        "cpu": 256,
        "memory": 512,
        "essential": true,
        "portMappings": [{
            "containerPort": 8080,
            "hostPort": 8080,
            "protocol": "tcp"
        }],
        "environment": [
            {"name": "SPRING_PROFILES_ACTIVE", "value": "prod"},
            {"name": "AWS_SECRETS_ENABLED", "value": "true"}
        ]
    }]' \
    --requires-compatibilities FARGATE \
    --network-mode awsvpc \
    --cpu 256 \
    --memory 512 \
    --execution-role-arn ecs-task-execution-role
