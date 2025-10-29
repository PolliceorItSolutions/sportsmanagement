aws ecs update-service \
    --cluster sportsmanagement-cluster \
    --service sportsmanagement-service \
    --task-definition sportsmanagement-task:10 \
    --force-new-deployment
