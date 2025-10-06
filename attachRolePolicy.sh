# Attach the policy to the role
aws iam attach-role-policy \
    --role-name ecs-task-execution-role \
    --policy-arn arn:aws:iam::860756134448:policy/ECSSecretsManagerPolicy

# Verify all attached policies
aws iam list-attached-role-policies --role-name ecs-task-execution-role
