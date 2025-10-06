aws ecs create-service \
    --cluster sportsmanagement-cluster \
    --service-name sportsmanagement-service \
    --task-definition sportsmanagement:1 \
    --desired-count 2 \
    --launch-type FARGATE \
    --platform-version LATEST \
    --health-check-grace-period-seconds 120 \
    --network-configuration "awsvpcConfiguration={subnets=[subnet-40352527,subnet-82869bde],securityGroups=[sg-08f239ee98a24bb99],assignPublicIp=ENABLED}" \
    --load-balancers "targetGroupArn=arn:aws:elasticloadbalancing:us-east-1:860756134448:targetgroup/sportsmanagement-tg/afe13e2a73d8304f,containerName=sports_app,containerPort=8080"
