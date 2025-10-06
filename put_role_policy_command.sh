aws iam put-role-policy \
--role-name ecs-task-execution-role \
--policy-name cloudwatch-logs \
--policy-document '{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "logs:CreateLogStream",
                "logs:PutLogEvents"
            ],
            "Resource": "arn:aws:logs:us-east-1:860756134448:log-group:/ecs/sportsmanagement:*"
        }
    ]
}'