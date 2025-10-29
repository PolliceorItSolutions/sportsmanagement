# Check if the role exists
aws iam get-role --role-name ecs-task-execution-role

# List the policies attached to the role
aws iam list-attached-role-policies --role-name ecs-task-execution-role
