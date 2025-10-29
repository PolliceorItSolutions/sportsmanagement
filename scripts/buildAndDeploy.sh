cd ..
docker build -t sportsmanagement:latest .
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 860756134448.dkr.ecr.us-east-1.amazonaws.com
docker tag sportsmanagement:latest 860756134448.dkr.ecr.us-east-1.amazonaws.com/sportsmanagement:prod
docker push 860756134448.dkr.ecr.us-east-1.amazonaws.com/sportsmanagement:prod
aws ecs register-task-definition --cli-input-json file://task-definition.json