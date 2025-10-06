# Create the task execution role if it doesn't exist
aws iam create-role --role-name ecs-task-execution-role \
    --assume-role-policy-document '{
      "Version": "2012-10-17",
      "Statement": [
        {
          "Effect": "Allow",
          "Principal": {
            "Service": "ecs-tasks.amazonaws.com"
          },
          "Action": "sts:AssumeRole"
        }
      ]
    }'

# Attach the necessary policy
aws iam attach-role-policy \
    --role-name ecs-task-execution-role \
    --policy-arn arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy
