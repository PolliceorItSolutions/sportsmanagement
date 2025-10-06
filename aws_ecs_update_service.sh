aws ecs update-service \
    --cluster sportsmanagement-cluster \
    --service sportsmanagement-service \
    --task-definition sportsmanagement:3 \
    --force-new-deployment