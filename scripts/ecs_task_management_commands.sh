aws ecs list-tasks \
    --cluster sportsmanagement-cluster \
    --service-name sportsmanagement-service \
    --desired-status RUNNING

# Then get the logs for the task:
aws logs get-log-events \
    --log-group-name /ecs/sportsmanagement \
    --log-stream-name ecs/sports_app/<task-id>
