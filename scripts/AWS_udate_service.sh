aws ecs update-service \
  --cluster sportsmanagement-cluster \
  --service sportsmanagement-service \
  --task-definition sportsmanagement-task:15 \
  --enable-execute-command \
  --force-new-deployment
